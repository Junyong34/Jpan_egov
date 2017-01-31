package egov.wizware.ctl;

import java.io.*;
import java.text.*;
import java.util.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.*;

import egov.wizware.com.*;
import egov.wizware.dao.*;

import java.sql.Connection;


public class ControlJspWizFrameVVision    extends HttpServlet {

  private static final String CONTENT_TYPE = "text/html; charset=euc-kr";

  public void init(ServletConfig config) throws ServletException {
    super.init(config);
  }


  public void doGet(HttpServletRequest requesta, HttpServletResponse responsea) throws   ServletException, IOException {
    doPost(requesta, responsea);
  }

  public void doPost(HttpServletRequest requestb, HttpServletResponse responseb) throws   ServletException, IOException {

    HashMap jeonmon = null;
    HashMap ctlmap = new HashMap();
    String view="";
    String rtntype="";
    //Session Object check처리 및 object set처리
    VOBJ gvobj = new VOBJ();
    try
    {
        // if((gvobj =_checkSession(requestb, responseb))== null)
        //     return;
        /*
        //Session Object Set처리
        //DEV 처리 방법1
        HashMap gmap = new HashMap();
        gmap.put("AGENCY_CD","1031301");
        gmap.put("USER_ID","1031301");
        gmap.put("REG_NO","1348626272");
        gmap.put("AGENCY_NM","(주)은하상사(농협)");
        gmap.put("PHONE","999999");
        gmap.put("OWNER","김정석");
        gmap.put("LOAN","0");
        gmap.put("USER_AUTH","10");
        gvobj.addRecord(gmap);
     */
        //Session Object Set처리
        //DEV처리 방법 2
        //gvobj = this.getDevSession();
        //gvobj.setName("G");
        //gvobj.Println("GLOBAL OBJECT=G");
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }

    DOBJ dobj=null;
    if ((requestb.getContentType() != null) &&   (requestb.getContentType().indexOf("multipart") == 0))
    {
        //System.out.println("-----------  file upload-----------------------");
        requestb.setCharacterEncoding("euc-kr");
        dobj =_fileTrans( requestb , gvobj) ;
        //dobj =_fileTrans( requestb ) ;
    }
    else
    {
        requestb.setCharacterEncoding("euc-kr");
        dobj=makeJeonmunVobj(requestb);
    }
    dobj.setRetObject("G",gvobj);
    CTLLog clog = new CTLLog(2);
    jeonmon= dobj.getParam();
    Connection CONNX = null;
    VOBJ info  = null;
    String  flag = "";
    try {

        TimeInterval tinteval = new TimeInterval();
        if(jeonmon.get("SYSID") == null || jeonmon.get("SYSID").toString().equals("")) {
            System.out.println("ERROR: SYSID NOT FOUND");
        }
        String sysidx=jeonmon.get("SYSID").toString() ;
        clog.setEventInfo(sysidx, jeonmon.get("MENUID").toString(), jeonmon.get("EVENTID").toString());
        Connector connector = new Connector();
        CONNX = connector.getConnection("OracleDS");

        QExecutor dexe = new QExecutor();
        SQLObject sqlx = this.makeQryCmdInfo(jeonmon);
        info  = dexe.executeQuery (CONNX, sqlx);

        int disp =0;
        String _dispmode = info.getRecord().get("LOGDISP");
        if(_dispmode==null ||  _dispmode.trim().equals("")   ||  _dispmode.trim().equals("0") ) disp=0;
        else disp=1;
        info.first();
        if (info.next())
        {
            flag = info.getRecord().get("UPDFLAG");
            ctlmap.put("PACKAGEX", info.getRecord().get("PACKAGE").toString());
            ctlmap.put("CLASSX",   info.getRecord().get("CLASS").toString());
            ctlmap.put("METHODX",  info.getRecord().get("METHOD").toString());
            clog.setMethodInfo(info.getRecord().get("PACKAGE").toString(),info.getRecord().get("CLASS").toString() ,info.getRecord().get("METHOD").toString());
        }
        else
        {
            dexe.DispSelectSql(sqlx);
            System.out.println(" info(" + jeonmon + ") Service Not Found :  ");
        }

        dobj.setDispmode(disp);
        if(flag.equals("1")) clog.DevLog(CONNX, dobj.getRetObject("G").getRecord().get("USER_ID"), dobj);

        dobj = this.NonejbExecute(dobj, ctlmap);
        if(dobj.getRtncode() == -1)  clog.excepWrite(CONNX, dobj.getRetObject("G").getRecord().get("USER_ID"),  dobj.getRetmsg(), "", dobj);

        tinteval.setEndTime();
        clog.write(CONNX,dobj.getRetObject("G").getRecord().get("USER_ID"), tinteval.getInterval()+"");

        if(info.getRecord().get("JSPLOCATION").trim().equals("1")){
            JspXmlData jxml = new JspXmlData();
            forward(responseb, jxml.getOutXmlData(dobj));
        }else {
            //--jsp
            String _fullname = info.getRecord().get("JSPLOCATION").trim();
            if(_fullname!= null && !_fullname.equals("") && !_fullname.substring(_fullname.length()-1).equals("/")) _fullname=  _fullname +"/";
            _fullname = _fullname + info.getRecord().get("JSPNAME");
            forward(requestb,  responseb, _fullname, dobj);
        }

    }
    catch (Exception e) {
        clog.excepWrite(CONNX, dobj.getRetObject("G").getRecord().get("USER_ID"),  e.getMessage() , "", dobj);
        e.printStackTrace();
    }finally{
        try{
            CONNX.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}


private void forward(HttpServletResponse res, StringBuffer data)
{
    try
    {
        res.setContentType("text/plain; charset=UTF-8");
        PrintWriter out = new PrintWriter(new OutputStreamWriter(res.getOutputStream(), "UTF-8"), false);
        out.write(data.toString());
        out.flush();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

private void forward(HttpServletRequest req, HttpServletResponse res, String jspname , DOBJ _dobj){
    HttpSession sesn = req.getSession(true);
    ServletContext servletcontext = getServletContext();
    RequestDispatcher requestdispatcher = servletcontext.getRequestDispatcher(jspname);
    req.setAttribute("retobj", _dobj);

    try {
        requestdispatcher.forward(req, res);
    }
    catch (javax.servlet.ServletException e) {
        e.printStackTrace();
    }
    catch (IOException e) {
        e.printStackTrace();
    }
}

private VOBJ _checkSession(HttpServletRequest req, HttpServletResponse res){

    boolean ret=true;
    String s = "";
    s = "/logout.jsp";
    HttpSession _sesn = req.getSession(true);
    VOBJ gvobj =null;
    if  ((_sesn == null) || (_sesn.getAttribute("G") == null)) {
        ret=false;
        ServletContext servletcontext = getServletContext();
        RequestDispatcher requestdispatcher = servletcontext.
        getRequestDispatcher(s);
        try {
            requestdispatcher.forward(req, res);
        } catch (ServletException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    } else {
        gvobj  = (VOBJ)_sesn.getAttribute("G");
    }
    return gvobj;
}




private VOBJ getDevSession(){
    VOBJ _gvobj = null;
    try{
        CONFIG _cfg = new CONFIG();
        String packagenm= _cfg.getPathfinderConfig("SESSION_PACKAGE") ;
        String classnm  = _cfg.getPathfinderConfig("SESSION_CLASS") ;
        String methodnm = _cfg.getPathfinderConfig("SESSION_METHOD") ;
        String objectid = _cfg.getPathfinderConfig("SESSION_OBJECTID") ;
        if( (packagenm!=null && !packagenm.equals("")) &&
            (classnm  !=null && !classnm.equals(""))   &&
            (methodnm !=null && !methodnm.equals(""))  &&
            (objectid !=null && !objectid.equals(""))    )
        {
            DOBJ dobj = new DOBJ();
            HashMap ctl = new HashMap();
            ctl.put("PACKAGEX", packagenm);
            ctl.put("CLASSX"  , classnm);
            ctl.put("METHODX" , methodnm);
            dobj = this.NonejbExecute(dobj, ctl);
            _gvobj = dobj.getRetObject(objectid);
        }
    }catch(Exception e){
        e.printStackTrace();
    }
    return _gvobj;
}

private DOBJ _fileTrans(HttpServletRequest req, VOBJ gvobj ) {
    HashMap _param  = new HashMap();
    DOBJ dobj = null;
    CONFIG _CONF = new CONFIG();
    String _tmpUploadFilePath= null;
    String _readlinestr = null;
    String str = null;
    String _paramName = null;
    String _paramValue = null;
    String br = "\n";
    int _nameStart = 0, _nameEnd = 0; // name start , name end point
    String _filename = null;
    String _unifilename = null;
    int _tmpi = 0;
    String _inputname = "";
    ArrayList _flist = new ArrayList();

    try {

        DataInputStream in = new DataInputStream(req.getInputStream());
        _readlinestr = in.readLine();
        _tmpUploadFilePath= _CONF.getPathfinderConfig("UploadTemp").trim()  ;
        if(!_tmpUploadFilePath.substring(_tmpUploadFilePath.length()-1).equals("/")) _tmpUploadFilePath+="/";
        int i = 0;
        while ( (str = in.readLine()) != null) {
            _inputname = _findName(str);
            _tmpi = 0;
            if (str.indexOf("Content-Disposition") == 0) {
                _nameStart = str.indexOf  ("name=");
                _nameEnd   = str.indexOf  ("\"", _nameStart + 6);
                _paramName = str.substring(_nameStart + 6, _nameEnd);

                _tmpi = 0;
                if (str.indexOf("filename=") != -1) {
                    _nameStart = str.indexOf("filename=");
                    _nameEnd = str.indexOf("\"", _nameStart + 10);
                    _paramValue = str.substring(_nameStart + 10, _nameEnd);
                    _nameStart = _paramValue.lastIndexOf("\\");
                    if (_nameStart == -1) _nameStart = _paramValue.lastIndexOf("/");
                    _nameStart++;
                    _paramValue = _paramValue.substring(_nameStart, _paramValue.length());
                    _filename = _paramValue;
                    _unifilename = _getDupFileNewName(_tmpUploadFilePath, _filename , gvobj);
                    i++;
                    str = in.readLine(); // Content-Type Header Skip
                    if (str.indexOf("Content-Type") == 0) {
                        str = in.readLine(); // Skip Empty Line
                    }

                    if (!_filename.equals("")) {

                        int size = _writeToFile(in, _tmpUploadFilePath + _unifilename, _readlinestr);

                        TransFileInfo _info = new TransFileInfo();
                        _info.setSize(size);
                        _info.setFiletype(_filename.substring(_filename.indexOf('.') + 1));
                        _info.setFilename(_codeCvt(_filename));
                        //_info.setFilename(_filename);
                        _info.setPath(_tmpUploadFilePath);
                        _info.setUniname(_unifilename);
                        _info.setVarname(_paramName);
                        _flist.add(_info);
                    }
                }
                else if (str.indexOf("contents") != -1) {
              //      System.out.println("contents tag: ok");
                    str = in.readLine(); // Skip Line
                    _paramValue = "";
                    while ( (str = in.readLine()) != null) {
                        if (str.indexOf(_readlinestr) == 0)
                            break;
                        _paramValue = _paramValue + br + str;
                    } // while
                }
                else {
                    str = in.readLine();
                    _paramValue = "";
                    while ( (str = in.readLine()) != null) {
                        if (str.indexOf(_readlinestr) == 0) {
                            _tmpi = 0;
                            break;
                        }
                        if (_tmpi == 0) {
                            _paramValue = _paramValue + str;
                        }
                        else
                            _paramValue = _paramValue + "\n" + str;
                        _tmpi++;
                    }
                    this._dataInputHashMap(_inputname, _param, _paramValue);
                }
            }
        }
        dobj = this.makeJeonmunUpVobj(_param, _flist);
    }
    catch (Exception e) {
        e.printStackTrace();
    }

    return dobj;
}

private DOBJ makeJeonmunUpVobj(HashMap _inparam, ArrayList _flist ) {
    HashMap param = new HashMap();
    HashMap tmpHash = new HashMap();
    int maxsize =0;

    HashMap processkey= new HashMap();
    ArrayList _clist = new ArrayList();
    HashMap   _dsmap = null;

    Iterator _itorx = _inparam.keySet().iterator();

    String key = null;
    String[] keyVals = null;
    String keyVal = "";
    while (_itorx.hasNext()) {
        key = (String) _itorx.next().toString();
        if(key.equals("SYSID")){
            processkey.put("SYSID",((ArrayList)_inparam.get(key)).get(0).toString());
            continue;
        }else if(key.equals("MENUID")){
            processkey.put("MENUID",((ArrayList)_inparam.get(key)).get(0).toString());
            continue;
        }else if(key.equals("EVENTID")){
            processkey.put("EVENTID",((ArrayList)_inparam.get(key)).get(0).toString());
            continue;
        }

        if(key.indexOf("_") == -1) continue;
        boolean _cont = false;
        char[] _tochar = key.toCharArray();
        while(true){
            if(_tochar[0] != 'S') {
                _cont= true;
                break;
            }
            if(_tochar[1]=='_') break;
            if( _tochar[2]=='_' && (  _tochar[1] =='0' || _tochar[1] =='1' ||  _tochar[1] =='2' ||  _tochar[1] =='3' ||  _tochar[1] =='4' || _tochar[1] =='5' ||
                _tochar[1]=='6' || _tochar[1]=='7' ||   _tochar[1]=='8' ||   _tochar[1]=='9' || ( 65 <= (int)_tochar[1]  && (int)_tochar[1]  <= 90 ) ) )
            {
                _cont= false;
                break;
            } else{
                _cont= true;
                break;
            }
        }
        if(_cont ==true) continue;
        _clist.add(key);
      //  System.out.println(key);
        keyVals = _getValues(_inparam.get(key));
        param.put(key, keyVals);

        tmpHash.put(key, keyVals);
        if(maxsize < keyVals.length) maxsize = keyVals.length;
    }

    HashMap _dsfilemap = _setDsFile(_flist);
    DOBJ _dobj = new DOBJ();
    _dsmap= getDSColumnlist(_clist);
    String _dsname="";
    Iterator _itor = _dsmap.keySet().iterator();
    while(_itor.hasNext()){
        _dsname = _itor.next().toString();
        //System.out.println(_dsfilemap);
        VOBJ _vobj = _getVOBJUP( _dsname, _dsmap.get(_dsname),  param,  _dsfilemap.get(_dsname) );
        if(_dsfilemap!= null && _dsfilemap.containsKey(_dsname)) _dsfilemap.remove(_dsname);   //처리한 dataset의 파일은 삭제 처리 한다.
        _vobj.Println("INPUT");
        _dobj.setRetObject(_vobj);
    }

    //-----_dsfilemap에서 처리 되지 않은 dataset을 구성 한다.
    if(_dsfilemap.size() > 0){
        Iterator _itory = _dsfilemap.keySet().iterator();
        while(_itory.hasNext()){
            _dsname = _itory.next().toString();
            VOBJ _vobj =  _getVOBJFILE(_dsname, _dsfilemap.get(_dsname));
            _vobj.Println("INPUT_ONLY_FILE");
            _dobj.setRetObject(_vobj);
        }
    }
    _dobj.setParam(processkey);
    return _dobj;
}




private String _codeCvt(String str) {
    String _newstr = null;
    try {
        //System.out.println("==========================>:"+str);
        _newstr = new String(str.getBytes("8859_1"), "UTF-8");
    }
    catch (java.io.UnsupportedEncodingException e) {}
    return _newstr;
}

/*
   try{

       System.out.println("x:"+new String(_info.getUniname().getBytes("Cp1252"), "KSC5601"));
       System.out.println("x:"+new String(_info.getUniname().getBytes("Cp1252"), "8859_1"));
       System.out.println("x:"+new String(_info.getUniname().getBytes("Cp1252"), "UTF-8"));
       System.out.println("x:"+new String(_info.getUniname().getBytes("Cp1252"), "EUC-KR"));

       System.out.println("x:"+new String(_info.getUniname().getBytes("UTF-8"), "KSC5601"));
       System.out.println("x:"+new String(_info.getUniname().getBytes("UTF-8"), "8859_1"));
       System.out.println("x:"+new String(_info.getUniname().getBytes("UTF-8"), "Cp1252"));
       System.out.println("x:"+new String(_info.getUniname().getBytes("UTF-8"), "EUC-KR"));

       System.out.println("x:"+new String(_info.getUniname().getBytes("8859_1"), "KSC5601"));
       System.out.println("x:"+new String(_info.getUniname().getBytes("8859_1"), "Cp1252"));
       System.out.println("x:"+new String(_info.getUniname().getBytes("8859_1"), "UTF-8"));
       System.out.println("x:"+new String(_info.getUniname().getBytes("8859_1"), "EUC-KR"));

       System.out.println("x:"+new String(_info.getUniname().getBytes("KSC5601"), "Cp1252"));
       System.out.println("x:"+new String(_info.getUniname().getBytes("KSC5601"), "UTF-8"));
       System.out.println("x:"+new String(_info.getUniname().getBytes("KSC5601"), "EUC-KR"));
       System.out.println("x:"+new String(_info.getUniname().getBytes("KSC5601"), "8859_1"));

   }catch(Exception ee){
   }
*/

private DOBJ _fileTrans(HttpServletRequest req ) {
    HashMap _param  = new HashMap();
    DOBJ dobj = null;
    CONFIG _CONF = new CONFIG();
    String _tmpUploadFilePath= null;
    String _readlinestr = null;
    String str = null;
    String _paramName = null;
    String _paramValue = null;
    String br = "\n";
    int _nameStart = 0, _nameEnd = 0; // name start , name end point
    String _filename = null;
    String _unifilename = null;
    int _tmpi = 0;
    String _inputname = "";
    ArrayList _flist = new ArrayList();

    try {
        DataInputStream in = new DataInputStream(req.getInputStream());
        _readlinestr = in.readLine();
        _tmpUploadFilePath= _CONF.getPathfinderConfig("UploadTemp").trim()  ;
        if(!_tmpUploadFilePath.substring(_tmpUploadFilePath.length()-1).equals("/")) _tmpUploadFilePath+="/";
        int i = 0;
        while ( (str = in.readLine()) != null) {
            _inputname = _findName(str);
            _tmpi = 0;
            if (str.indexOf("Content-Disposition") == 0) {
                _nameStart = str.indexOf  ("name=");
                _nameEnd   = str.indexOf  ("\"", _nameStart + 6);
                _paramName = str.substring(_nameStart + 6, _nameEnd);

                _tmpi = 0;
                if (str.indexOf("filename=") != -1) {
                    _nameStart = str.indexOf("filename=");
                    _nameEnd = str.indexOf("\"", _nameStart + 10);
                    _paramValue = str.substring(_nameStart + 10, _nameEnd);
                    _nameStart = _paramValue.lastIndexOf("\\");
                    if (_nameStart == -1) _nameStart = _paramValue.lastIndexOf("/");
                    _nameStart++;
                    _paramValue = _paramValue.substring(_nameStart, _paramValue.length());
                    _filename = _paramValue;
                    _unifilename = _getDupFileNewName(_tmpUploadFilePath, _filename);
                    i++;
                    str = in.readLine(); // Content-Type Header Skip
                    if (str.indexOf("Content-Type") == 0) {
                        str = in.readLine(); // Skip Empty Line
                    }

                    if (!_filename.equals("")) {

                        //_getDupFileNewName(_tmpUploadFilePath, _filename);
                  //      System.out.println(_tmpUploadFilePath+_unifilename + ":" + _filename );
                        int size = _writeToFile(in, _tmpUploadFilePath + _unifilename, _readlinestr);

                        TransFileInfo _info = new TransFileInfo();
                        _info.setSize(size);
                        _info.setFiletype(_filename.substring(_filename.indexOf('.') + 1));
                        //_info.setFilename(_codeCvt(_filename));
                        _info.setFilename(_filename);
                        _info.setPath(_tmpUploadFilePath);
                        _info.setUniname(_unifilename);
                        _info.setVarname(_paramName);
                        _flist.add(_info);
                    }
                }
                else if (str.indexOf("contents") != -1) {
              //      System.out.println("contents tag: ok");
                    str = in.readLine(); // Skip Line
                    _paramValue = "";
                    while ( (str = in.readLine()) != null) {
                        if (str.indexOf(_readlinestr) == 0)
                            break;
                        _paramValue = _paramValue + br + str;
                    } // while
                }
                else {
                    str = in.readLine();
                    _paramValue = "";
                    while ( (str = in.readLine()) != null) {
                        if (str.indexOf(_readlinestr) == 0) {
                            _tmpi = 0;
                            break;
                        }
                        if (_tmpi == 0) {
                            _paramValue = _paramValue + str;
                        }
                        else
                            _paramValue = _paramValue + "\n" + str;
                        _tmpi++;
                    }
                    this._dataInputHashMap(_inputname, _param, _paramValue);
                }
            }
        }
        dobj = this.makeJeonmunUpVobj(_param, _flist);
    }
    catch (Exception e) {
        e.printStackTrace();
    }

    return dobj;
}




private HashMap _dataInputHashMap(String key, java.util.HashMap _param, String value) {
    java.util.ArrayList arr = null;
    if (_param.containsKey(key)) {
        arr = (ArrayList) _param.get(key);
        arr.add(value);
        _param.put(key, arr);
    }
    else {
        arr = new java.util.ArrayList();
        arr.add(value);
        _param.put(key, arr);
    }
    return _param;
}

private int _writeToFile(DataInputStream in, String _filename,  String deliminator) {
    byte ch = 0;
    byte[] buffer      = new byte[1024];
    byte[] _pre_buffer = new byte[1024];
    int x = 0, _delpos = 0;
    int _pre_x = 0;
    int retsize = 0;
    boolean flag = true;
    String _errMsg = "";
    if (deliminator == null)return retsize; // deliminator가 비어 있는지 여부를 체크
    FileOutputStream _outFile =  null;
    try {
        _outFile = new FileOutputStream(_filename);
        while (flag) {
            ch = in.readByte();
            buffer[x++] = ch;
            if (ch == '\n' || x == 1023) {
                String _strTmp = new String(buffer, 0, x);
                if ( (_delpos = _strTmp.indexOf(deliminator)) != -1) {
                    _outFile.write(_pre_buffer, 0, _pre_x - 2); // CR/LF를 제거하기 위해 -2한다.
                    flag = false;
                } else {
                    _outFile.write(_pre_buffer, 0, _pre_x);
                }
                System.arraycopy(buffer, 0, _pre_buffer, 0, x);
                _pre_x = x;
                x = 0;
            }
            retsize++;
        }

    } catch (EOFException eof) {
        return 0;
    }
    catch (IOException e) {
        System.out.println("ERROR : FILE UPLOAD FAILED (class file Upload :: _writeToFile)");
        System.out.println(e);
        return 0;
    }finally{
        try{
            _outFile.flush();
            _outFile.close();
        }catch(Exception e){
            e.printStackTrace();
        }

    }
    return retsize;
}

//대상 방문판매 전용
//yyyyMMddHHmmssSSS
public static String GetCurrentDate()
 {
     Date _DD = new Date();
     SimpleDateFormat _yyyyMMddE = new SimpleDateFormat("yyyyMMddHHmmssSSS");
     String _rtn;
     _rtn = _yyyyMMddE.format(_DD);

     return _rtn;
}

 // /oracle/was/srcgen/upload /oracle/was/srcgen/upload/ D01011_20091014095024572 .ppt (No such file or directory)

private String _getDupFileNewName(String path, String _filename, VOBJ gvobj){

    String _newname=gvobj.getRecord().get("I_USER")+"_" +GetCurrentDate();
    String _newpath="";
    String _lastname="";
    String _name="";
    String _rtn="";
    if(_filename.indexOf(".")!= -1){
        _lastname= _filename.substring(_filename.lastIndexOf("."));
    }
    if(path.trim().substring(path.trim().length()-1).equals("/")){
        _newpath = path ;
    }else{
        _newpath = path +"/";
    }

    _name = _newpath+ _newname + _lastname;
    _rtn = _newname + _lastname;

    File fp = new File(_name);
    int i=1;
    while(fp.exists()){
        _name = _newpath+ _newname+"_V" + i + _lastname;
        _rtn = _newname+"_V" + i + _lastname;
        fp = new File(_name);
        i++;
    }
    return _rtn;
}



private String _getDupFileNewName(String path,String _filename){
    String _newname=_filename;
    //_newname = this._codeCvt(_newname);
    String _newpath="";
    String _lastname="";
    String _midname="";
    if(_filename.indexOf(".")!= -1){
        _midname = _filename.substring(0,_filename.lastIndexOf("."));
        _lastname= _filename.substring(_filename.lastIndexOf("."));
    }

    if(path.trim().substring(path.trim().length()-1).equals("/")){
        _newpath = path ;
    }else{
        _newpath = path +"/";
    }
    File fp = new File(_newpath+_newname);
    int i=1;
    while(fp.exists()){
        _newname = _midname+"_V" + i + _lastname;
        fp = new File(_newpath+_newname);
        i++;
    }
    return _newname;
}
private String _findName(String _vals) {
    String _inputname = "";
    if (_vals != null && _vals.length() > 1) {
        _inputname = _vals.substring(_vals.indexOf("name=\"") + "name=\"".length(),  _vals.length() - 1);
    }
    return _inputname;
}

private VOBJ _getVOBJFILE(String _dsname, Object _flistx)
{
    ArrayList _flist = (ArrayList)_flistx;
    ArrayList _varlist = _getVarnames(_flist);

    ArrayList _records = new ArrayList();
    HashMap   _rec = null;

    String _vname ="";
    for(int i=0;i<_varlist.size();i++){
        _rec = new HashMap();
        _vname = _varlist.get(i).toString();
        ArrayList _fxlist = _getVarFileInfos(_vname, _flist);
        for(int x=0;x<_fxlist.size();x++){
            TransFileInfo _info = (TransFileInfo)_fxlist.get(x);
            _rec.put("FILESIZE",_info.getSize() +"");     // FILESIZE
            _rec.put("PATH"    ,_info.getPath());         // PATH

            /*
            try{
                System.out.println("x:"+new String(_info.getUniname().getBytes("Cp1252"), "KSC5601"));
                System.out.println("x:"+new String(_info.getUniname().getBytes("Cp1252"), "8859_1"));
                System.out.println("x:"+new String(_info.getUniname().getBytes("Cp1252"), "UTF-8"));
                System.out.println("x:"+new String(_info.getUniname().getBytes("Cp1252"), "EUC-KR"));

                System.out.println("x:"+new String(_info.getUniname().getBytes("UTF-8"), "KSC5601"));
                System.out.println("x:"+new String(_info.getUniname().getBytes("UTF-8"), "8859_1"));
                System.out.println("x:"+new String(_info.getUniname().getBytes("UTF-8"), "Cp1252"));
                System.out.println("x:"+new String(_info.getUniname().getBytes("UTF-8"), "EUC-KR"));

                System.out.println("x:"+new String(_info.getUniname().getBytes("8859_1"), "KSC5601"));
                System.out.println("x:"+new String(_info.getUniname().getBytes("8859_1"), "Cp1252"));
                System.out.println("x:"+new String(_info.getUniname().getBytes("8859_1"), "UTF-8"));
                System.out.println("x:"+new String(_info.getUniname().getBytes("8859_1"), "EUC-KR"));

                System.out.println("x:"+new String(_info.getUniname().getBytes("KSC5601"), "Cp1252"));
                System.out.println("x:"+new String(_info.getUniname().getBytes("KSC5601"), "UTF-8"));
                System.out.println("x:"+new String(_info.getUniname().getBytes("KSC5601"), "EUC-KR"));
                System.out.println("x:"+new String(_info.getUniname().getBytes("KSC5601"), "8859_1"));
            }catch(Exception ee){}
            */
            _rec.put("UNIFILENAME",_info.getUniname());   // UNIFILENAME
            _rec.put("UPFILENAME" ,_info.getFilename());  // UPFILENAME
            String _fullpath = _info.getPath().trim();
            if(!_fullpath.substring(_fullpath.length()-1).equals("/")) _fullpath +="/" ;
            _rec.put("FULLPATH",_fullpath + _info.getUniname());               // FULLPATH파일명
            _records.add(_rec);
        }
    }
    VOBJ _vobj = new VOBJ();
    _vobj.setName(_dsname);
    _vobj.setRecords(_records);
    return _vobj;
}

private VOBJ _getVOBJUP(String _dsname, Object _cobj, HashMap param, Object _flistxx)
{
    ArrayList _flist = (ArrayList)_flistxx;
    ArrayList _varlist = _getVarnames(_flist);
    ArrayList _clist = (ArrayList) _cobj;
    String[] _vals = null;
    int  _msize = _getMaxsize( _dsname,  _clist,  param);
    VOBJ _vobj = new VOBJ();
    ArrayList _records = new ArrayList();
    HashMap   _rec = null;
    int _cnt=0;
    for(int x=0;x<_msize;x++)
    {
        _rec = new HashMap();
        for(int i=0;i<_clist.size();i++)
        {
            _vals = (String[]) param.get(_dsname +"_"+ _clist.get(i));
     //       System.out.println(_clist.get(i) +":" + i +":===:"+x +":" + _vals[x]);
            if(_vals.length >= x) {
                _rec.put(_clist.get(i), _vals[x]);
            } else {
                _rec.put(_clist.get(i), "");
            }
        }
        //--- 파일이 존재 할때 처리 한다.
     //   System.out.println(_rec);
        String _vname ="";
        for(int i=0;i<_varlist.size();i++){
            _vname = _varlist.get(i).toString();
            ArrayList _fxlist = _getVarFileInfos(_vname, _flist);
            if(_fxlist!=null && _flist.size() > x){
                TransFileInfo _info = (TransFileInfo)_fxlist.get(x);
                /*
                try{
                  System.out.println("y:"+new String(_info.getUniname().getBytes("Cp1252"), "KSC5601"));
                  System.out.println("y:"+new String(_info.getUniname().getBytes("Cp1252"), "8859_1"));
                  System.out.println("y:"+new String(_info.getUniname().getBytes("Cp1252"), "UTF-8"));
                  System.out.println("y:"+new String(_info.getUniname().getBytes("Cp1252"), "EUC-KR"));

                  System.out.println("y:"+new String(_info.getUniname().getBytes("UTF-8"), "KSC5601"));
                  System.out.println("y:"+new String(_info.getUniname().getBytes("UTF-8"), "8859_1"));
                  System.out.println("y:"+new String(_info.getUniname().getBytes("UTF-8"), "Cp1252"));
                  System.out.println("y:"+new String(_info.getUniname().getBytes("UTF-8"), "EUC-KR"));

                  System.out.println("y:"+new String(_info.getUniname().getBytes("8859_1"), "KSC5601"));
                  System.out.println("y:"+new String(_info.getUniname().getBytes("8859_1"), "Cp1252"));
                  System.out.println("y:"+new String(_info.getUniname().getBytes("8859_1"), "UTF-8"));
                  System.out.println("y:"+new String(_info.getUniname().getBytes("8859_1"), "EUC-KR"));

                  System.out.println("y:"+new String(_info.getUniname().getBytes("KSC5601"), "Cp1252"));
                  System.out.println("y:"+new String(_info.getUniname().getBytes("KSC5601"), "UTF-8"));
                  System.out.println("y:"+new String(_info.getUniname().getBytes("KSC5601"), "EUC-KR"));
                  System.out.println("y:"+new String(_info.getUniname().getBytes("KSC5601"), "8859_1"));

                  System.out.println("y:"+new String(_info.getUniname().getBytes("EUC-KR"), "Cp1252"));
                  System.out.println("y:"+new String(_info.getUniname().getBytes("EUC-KR"), "UTF-8"));
                  System.out.println("y:"+new String(_info.getUniname().getBytes("EUC-KR"), "KSC5601"));
                  System.out.println("y:"+new String(_info.getUniname().getBytes("EUC-KR"), "8859_1"));
                }catch(Exception ee){}
                */
                _rec.put("FILESIZE",_info.getSize() +"");     // FILESIZE
                _rec.put("PATH"    ,_info.getPath());         // PATH
                _rec.put("UNIFILENAME",_info.getUniname());   // UNIFILENAME
                _rec.put("UPFILENAME" ,_info.getFilename());  // UPFILENAME
                String _fullpath = _info.getPath().trim();
                if(!_fullpath.substring(_fullpath.length()-1).equals("/")) _fullpath +="/";
                _fullpath += _info.getUniname();
                _rec.put("FULLPATH",_fullpath);               // FULLPATH파일명
            }
        }
        _records.add(_rec);
    }
    _vobj.setRecords(_records);
    _vobj.setName(_dsname);
    return _vobj;
}

private ArrayList _getVarFileInfos(String varname, ArrayList _flist){
    ArrayList _flistx = new ArrayList();
    for(int i=0;i<_flist.size();i++){
        TransFileInfo _info = (TransFileInfo)_flist.get(i);
        if(varname.equals(_info.getVarname().toString())){
            _flistx.add(_info);
        }
    }
    return _flistx;
}
private ArrayList _getVarnames(ArrayList _flist)
{
    TransFileInfo _info = null;
    HashMap _cmap = new HashMap();
    if(_flist!= null){
        for(int i=0;i<_flist.size();i++){
            _info= (TransFileInfo)_flist.get(i);
            _cmap.put(_info.getVarname(),"");
        }
    }

    ArrayList _clist = new ArrayList();
    Iterator _itor = _cmap.keySet().iterator();
    while(_itor.hasNext()){
        _clist.add(_itor.next());
    }
    return _clist;
}
private HashMap _setDsFile(ArrayList _flist){
    ArrayList _tflist = null;
    String    _tdsname="";
    HashMap   _dsmap = new HashMap();
    TransFileInfo _info = null;
    for(int i=0;i<_flist.size();i++){
        _info = (TransFileInfo)_flist.get(i);
        String _key=_info.getVarname();
        if(_key.indexOf("_") == -1) continue;
        boolean _cont = false;
        char[] _tochar = _key.toCharArray();
        while(true){
            if(_tochar[0] != 'S') {
                _cont= true;
                break;
            }
            if(_tochar[1]=='_') break;
            if( _tochar[2]=='_' && (  _tochar[1] =='0' || _tochar[1] =='1' ||  _tochar[1] =='2' ||  _tochar[1] =='3' ||  _tochar[1] =='4' || _tochar[1] =='5' ||
                                      _tochar[1]=='6' || _tochar[1]=='7' ||   _tochar[1]=='8' ||   _tochar[1]=='9' || ( 65 <= (int)_tochar[1]  && (int)_tochar[1]  <= 90 ) ) )
            {
                _cont= false;
                break;
            } else{
                _cont= true;
                break;
            }
        }
        if(_cont == true) continue;
        _tdsname = _key.substring(0,_key.indexOf("_") );
        _info.setVarname(_key.substring(_key.indexOf("_")+1));

        if(_dsmap == null){
            _dsmap  = new HashMap();
            _tflist = new ArrayList();
            _tflist.add(_info);
            _dsmap.put(_tdsname, _tflist);
        }
        else
        {
            if(_dsmap.containsKey(_tdsname))
            {
                _tflist = (ArrayList)_dsmap.get(_tdsname);
                _tflist.add(_info);
                _dsmap.put(_tdsname, _tflist);
            }
            else
            {
                _tflist = new ArrayList();
                _tflist.add(_info);
                _dsmap.put(_tdsname, _tflist);
            }
        }
    }
    return _dsmap;
}
private String[] _getValues(Object _voj){
  //   System.out.println(_voj);
    ArrayList _vlist = (ArrayList)_voj;
     String[] str = new String[_vlist.size()];
    for(int i=0;i<_vlist.size();i++){
        //str[i] = _vlist.get(i).toString();
        str[i] = _codeCvt(_vlist.get(i).toString());
    }
    return str;
}

private DOBJ makeJeonmunVobj(HttpServletRequest req ) {
    HashMap param = new HashMap();
    HashMap tmpHash = new HashMap();
    int maxsize =0;

    HashMap processkey= new HashMap();
    ArrayList _clist = new ArrayList();
    HashMap   _dsmap = null;
    Enumeration e = req.getParameterNames();
    String key = null;
    String[] keyVals = null;
    String keyVal = "";
    while (e.hasMoreElements()) {
        key = (String) e.nextElement();
        if(key.equals("SYSID")){
            processkey.put("SYSID",req.getParameter(key));
            continue;
        }else if(key.equals("MENUID")){
            processkey.put("MENUID",req.getParameter(key));
            continue;
        }else if(key.equals("EVENTID")){
            processkey.put("EVENTID",req.getParameter(key));
            continue;
        }

        if(key.indexOf("_") == -1) continue;
        boolean _cont = false;
        char[] _tochar = key.toCharArray();
        while(true)
        {
            if(_tochar[0] != 'S') {
                _cont= true;
                break;
            }
            if(_tochar[1]=='_') break;
            if( _tochar[2]=='_' &&
                (  _tochar[1] =='0' || _tochar[1] =='1' ||  _tochar[1]=='2' ||  _tochar[1]=='3' ||  _tochar[1] =='4' ||
                   _tochar[1] =='5' || _tochar[1] =='6' ||  _tochar[1]=='7' ||  _tochar[1]=='8' ||  _tochar[1]=='9'  || ( 10 <= (int)_tochar[1]  && (int)_tochar[1]  <= 99 )
                )
            )
            {
                _cont= false;
                break;
            }
            else
            {
                _cont= true;
                break;
            }
        }
        if(_cont ==true) continue;
        _clist.add(key);
        keyVals = req.getParameterValues(key);
        param.put(key, keyVals);

        tmpHash.put(key, keyVals);
        if(maxsize < keyVals.length) maxsize = keyVals.length;
    }

    DOBJ _dobj = new DOBJ();
    _dsmap= getDSColumnlist(_clist);
    String _dsname="";
    Iterator _itor = _dsmap.keySet().iterator();
    while(_itor.hasNext()){
        _dsname = _itor.next().toString();
        VOBJ _vobj = _getVOBJ( _dsname, _dsmap.get(_dsname) ,  param);
        _vobj.Println("INPUT");
        _dobj.setRetObject(_vobj);
    }
    _dobj.setParam(processkey);
    return _dobj;
}

// dsname_column_a  {a,b,c,d}
// dsname_column_b  {a,b,c,d}
// dsname_column_c  {a,b,c,d}
private VOBJ _getVOBJ(String _dsname, Object _cobj, HashMap param)
{
    ArrayList _clist = (ArrayList) _cobj;
    String[] _vals = null;
    int  _msize = _getMaxsize( _dsname,  _clist,  param);
    VOBJ _vobj = new VOBJ();
    ArrayList _records = new ArrayList();
    HashMap   _rec = null;
    boolean   _isNull = true;   // null reocrd 제외 처리 기능
    int _cnt=0;
    for(int x=0;x<_msize;x++)
    {
        _rec = new HashMap();
        _isNull = true;
        for(int i=0;i<_clist.size();i++)
        {
            _vals = (String[]) param.get(_dsname +"_"+ _clist.get(i));
            if(_vals.length > x) {
                if(_vals[x] != null && !_vals[x].trim().equals("")) _isNull = false;   // null reocrd 제외 처리 기능
                _rec.put(_clist.get(i), _vals[x]);
            } else {
                _isNull = false;
                _rec.put(_clist.get(i), "");
            }
        }

        if(_isNull == false){  // null reocrd 제외 처리 기능
            _records.add(_rec);
        }
    }
    _vobj.setRecords(_records);
    _vobj.setName(_dsname);
    return _vobj;
}

private int _getMaxsize(String _dsname, ArrayList _clist, HashMap param){
    int maxrtn=0;
    String[] vals = null;
    if(_clist != null){
        for(int i=0;i<_clist.size();i++)
        {
            vals = null;

            vals = (String[])param.get(_dsname+"_"+_clist.get(i));
            if(maxrtn < vals.length) maxrtn = vals.length;

      //      System.out.println(_dsname+"_"+_clist.get(i) +":" + param.get(_dsname+"_"+_clist.get(i)) +":" + vals.length +":" + maxrtn );
        }
    }
  //  System.out.println("REUTN MAXSIZE:" + maxrtn);
    return maxrtn;
}

private HashMap getDSColumnlist(ArrayList _namelist){
    HashMap _dslist = new HashMap();
    ArrayList _clist = null;
    String _cname="";
    String _dsname="";
    for(int i=0;i<_namelist.size();i++){
        _cname = _namelist.get(i).toString();
        if(_cname.indexOf("_") != -1)
        {
            _dsname = _cname.substring(0,_cname.indexOf("_"));
            _cname  = _cname.substring(_cname.indexOf("_")+1);
        }
        else
        {
            _dsname = "S";
        }

        if(_dslist.containsKey(_dsname)){
            _clist = (ArrayList)_dslist.get(_dsname);
            if(!findedName(_clist, _cname)){
                _clist.add(_cname);
            }
            _dslist.put(_dsname, _clist);
        }else {
            _clist = new ArrayList();
            _clist.add(_cname);
            _dslist.put(_dsname, _clist);
        }
    }
    return _dslist;
}

private boolean findedName(ArrayList _clist, String _name)
{
    for(int i=0;i<_clist.size();i++)
    {
        if(_name.equals(_clist.get(i).toString())) {
            return true;
        }
    }
    return false;
}



private DOBJ NonejbExecute(DOBJ dobj, HashMap ctl) {

    String objectname = ctl.get("PACKAGEX").toString() + "." +   ctl.get("CLASSX").toString() ;
    String methodname = ctl.get("METHODX").toString();
    //String methodname = "CTL"+ctl.get("METHODX").toString();
    Class commandClass = null;
    try {
      commandClass = Class.forName( objectname );
    } catch (ClassNotFoundException ex) {
      ex.printStackTrace();
    }

    Object arg[] = new Object[1];
    Class[] cls = new Class[1];
    cls[0] = dobj.getClass();
    arg[0] = dobj;
    DOBJ ret = null;
    try {
      Method method = commandClass.newInstance().getClass().getMethod(methodname, cls);
      ret = (DOBJ) method.invoke(commandClass.newInstance(),arg);
      return ret;
    }
    catch (NoSuchMethodException e) {
      e.printStackTrace();
      ret = new DOBJ();
      ret.setRtncode(-900);
      ret.setRetmsg(e.getMessage());
      return ret;
    }
    catch (InvocationTargetException e) {
      e.printStackTrace();
      ret = new DOBJ();
      ret.setRtncode(-800);
      ret.setRetmsg(e.getMessage());

      return ret;
    }
    catch (IllegalAccessException e) {
      e.printStackTrace();
      ret = new DOBJ();
      ret.setRtncode(-700);
      ret.setRetmsg(e.getMessage());

      return ret;
    }
    catch (InstantiationException e) {
      e.printStackTrace();
      ret = new DOBJ();
      ret.setRtncode(-600);
      ret.setRetmsg(e.getMessage());

      return ret;
    }
    catch (Exception e) {
      e.printStackTrace();
      ret = new DOBJ();
      ret.setRtncode(-500);
      ret.setRetmsg(e.getMessage());
      return ret;
    }
  }

  private String getUri(String uri) {
    uri = uri.substring(uri.lastIndexOf("/") + 1);
    return uri;
  }

  private String GetCurrentDate(String wsFormat) {
    Date TodayDate = new Date();
    DateFormat df = DateFormat.getDateTimeInstance(DateFormat.FULL,
        DateFormat.MEDIUM,
        Locale.KOREA);
    SimpleDateFormat yyyyMMddE = new SimpleDateFormat(wsFormat);
    String wsCurrentDate;
    wsCurrentDate = yyyyMMddE.format(TodayDate);

    return wsCurrentDate;
  }

  private HashMap dataInputHashMap(String key, java.util.HashMap param,  String value) {
      System.out.println(key +":" + value);
      java.util.ArrayList arr = null;
      if (param.containsKey(key)) {
          arr = (ArrayList) param.get(key);
          arr.add(value);
          param.put(key, arr);
      }
      else {
          arr = new java.util.ArrayList();
          arr.add(value);
          param.put(key, arr);
      }
      return param;
  }

  private HashMap makeJeonmunUpVobj(java.util.HashMap param) {

    java.util.Collection inCol = param.keySet();
    Iterator inIter = inCol.iterator();
    java.util.ArrayList arr = null;
    String vals[] = null;
    String val = "";
    String key = "";

    HashMap tmpHash = new HashMap();
    HashMap record = null;
    ArrayList records = new ArrayList();
    HashMap colHash = new HashMap();
    ArrayList columnNms = new ArrayList();
    int maxsize =0;

    while (inIter.hasNext()) {

      key = (String) inIter.next();
      arr = (ArrayList) param.get(key);
      if (arr.size() > 1) {
        vals = new String[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
          vals[i] = (String) arr.get(i);
          //vals[i] = codeCvt( (String) arr.get(i));
        }
        param.put(key, vals);
        tmpHash.put(key,vals);
        colHash.put(key,vals.length+"");
        if(maxsize < vals.length) maxsize = vals.length;

      }  else {
        String tmpval[] = new String[1];
        val = (String) arr.get(0);
        //val = _codeCvt( (String) arr.get(0));
        param.put(key, val);
        tmpval[0] = val;
        tmpHash.put(key,tmpval);
        colHash.put(key,tmpval.length+"");
        if(maxsize < tmpval.length) maxsize = tmpval.length;

      }
      columnNms.add(key);
    }

    String[] tmpVals=null;
    for(int i=0;i<maxsize;i++){
      record = new HashMap();
      for(int ii=0;ii<columnNms.size();ii++){
        tmpVals=null;
        tmpVals = (String[])tmpHash.get(columnNms.get(ii).toString());
        if(tmpVals.length > i){
          record.put(columnNms.get(ii),tmpVals[i]);
        }else{
          record.put(columnNms.get(ii),tmpVals[tmpVals.length-1]);
        }
      }
      records.add(record);
    }

    VOBJ vobj = new VOBJ();
    vobj.setRecords(records);
    vobj.setColumnRecordCounts(colHash);

    param.put("JVOBJECTXX",vobj);
    return param;
  }


  private HashMap makeJeonmun(HttpServletRequest req, HttpServletResponse res) {
    java.util.HashMap param = new java.util.HashMap();
    Enumeration e = req.getParameterNames();
    String key = null;
    String[] keyVals = null;
    String keyVal = "";
    while (e.hasMoreElements()) {
      key = (String) e.nextElement();
      keyVals = req.getParameterValues(key);
      if (keyVals.length < 2) {
        keyVal = keyVals[0];
        param.put(key, keyVal);
      }
      else {
        param.put(key, keyVals);
      }
    }
    return param;
  }


  private HashMap makeJeonmunHashMap(java.util.HashMap param) {

    java.util.Collection inCol = param.keySet();
    Iterator inIter = inCol.iterator();
    java.util.ArrayList arr = null;
    String vals[] = null;
    String val = "";
    String key = "";

    while (inIter.hasNext()) {

      key = (String) inIter.next();
      arr = (ArrayList) param.get(key);

      if (arr.size() > 1) {
        vals = new String[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
          vals[i] = _codeCvt( (String) arr.get(i));
        }
        param.put(key, vals);
      }
      else {
        val = _codeCvt( (String) arr.get(0));
        param.put(key, val);
      }
    }
    return param;
  }

  private String findName(String vals) {
    String inputname = "";
    if (vals != null && vals.length() > 1) {
      inputname = vals.substring(vals.indexOf("name=\"") + "name=\"".length(),
                                 vals.length() - 1);
    }
    return inputname;
  }


  private HashMap fileTrans(HashMap param, HttpServletRequest req,   HttpServletResponse res) {
    CONFIG CONF = new CONFIG();
    String tmpUploadFilePath= null;

    String deliminator = null;
    String str = null;
    String paramName = null;
    String paramValue = null;
    String br = "\n";
    int nameStart = 0, nameEnd = 0; // name start , name end point
    String filename = null;
    String unifilename = null;
    int tmpi = 0;
    String inputname = "";
    ArrayList flist = new ArrayList();
    // 각 Parameter를 구분할 deliminator를 읽어들인다.
    try {
      DataInputStream in = new DataInputStream(req.getInputStream());
      deliminator = in.readLine();
      tmpUploadFilePath= CONF.getPathfinderConfig("UploadTemp")  ;

      int i = 0;
      while ( (str = in.readLine()) != null) {
        inputname = findName(str);
        tmpi = 0;

        if (str.indexOf("Content-Disposition") == 0) {
          nameStart = str.indexOf("name=");
          nameEnd = str.indexOf("\"", nameStart + 6);
          paramName = str.substring(nameStart + 6, nameEnd);

          tmpi = 0;
          if (str.indexOf("filename=") != -1) {
            nameStart = str.indexOf("filename=");
            nameEnd = str.indexOf("\"", nameStart + 10);
            paramValue = str.substring(nameStart + 10, nameEnd);
            nameStart = paramValue.lastIndexOf("\\");
            if (nameStart == -1) nameStart = paramValue.lastIndexOf("/");
            nameStart++;
            paramValue = paramValue.substring(nameStart, paramValue.length());
            filename = paramValue;

            //filepath = "tmp" + GetCurrentDate("yyyyMMddHHmmssSSS") + '.' +  filename.substring(filename.indexOf('.') + 1);
            unifilename = getDupFileNewName(tmpUploadFilePath, filename);
            i++;

            str = in.readLine(); // Content-Type Header Skip

            if (str.indexOf("Content-Type") == 0) {
              str = in.readLine(); // Skip Empty Line
            }
            if (!filename.equals("")) {

              getDupFileNewName(tmpUploadFilePath, filename);
              // System.out.println(tmpUploadFilePath+unifilename + ":" + filename );
              int size = writeToFile(in, tmpUploadFilePath + unifilename, deliminator);

              TransFileInfo info = new TransFileInfo();
              info.setSize(size);
              info.setFiletype(filename.substring(filename.indexOf('.') + 1));
              info.setFilename(_codeCvt(filename));
              info.setPath(tmpUploadFilePath);
              info.setUniname(unifilename);
              info.setVarname(paramName);
              flist.add(info);
            }
          }
          else if (str.indexOf("contents") != -1) {
            str = in.readLine(); // Skip Line
            paramValue = "";
            while ( (str = in.readLine()) != null) {
              if (str.indexOf(deliminator) == 0)
                break;
              paramValue = paramValue + br + str;
            } // while
          }
          else {

            str = in.readLine();
            paramValue = "";
            while ( (str = in.readLine()) != null) {
              if (str.indexOf(deliminator) == 0) {
                tmpi = 0;
                break;
              }
              if (tmpi == 0) {
                paramValue = paramValue + str;
              }
              else
                paramValue = paramValue + "\n" + str;
              tmpi++;
            }
            this.dataInputHashMap(inputname, param, paramValue);
          }
        }
      }

      TransFileInfo fparm = null;
      ArrayList  upnames = new ArrayList();
      ArrayList  arr = null;
      for (int j = 0; j < flist.size(); j++) {
        fparm = (TransFileInfo) flist.get(j);
        arr = new ArrayList();
        arr.add(fparm.getUniname());
        param.put(fparm.getVarname(), arr);
        upnames.add(fparm.getVarname());
      }
      param = this.makeJeonmunUpVobj(param);
      param.put("FUPNAMESX",upnames);

    }
    catch (Exception e) {
     e.printStackTrace();
    }

    return param;
  }

  private int writeToFile(DataInputStream in, String filename,  String deliminator) {

    byte ch = 0;
    byte[] buffer = new byte[1024];
    byte[] prebuffer = new byte[1024];
    int x = 0, delpos = 0;
    int prex = 0;
    int retsize = 0;
    boolean flag = true;
    String errMsg = "";
    if (deliminator == null)return retsize; // deliminator가 비어 있는지 여부를 체크

    try {
      FileOutputStream outFile = new FileOutputStream(filename);
      while (flag) {
        ch = in.readByte();

        buffer[x++] = ch;
        if (ch == '\n' || x == 1023) {
          String strTmp = new String(buffer, 0, x);
          if ( (delpos = strTmp.indexOf(deliminator)) != -1) {
            outFile.write(prebuffer, 0, prex - 2); // CR/LF를 제거하기 위해 -2한다.
            flag = false;
          } else {
            outFile.write(prebuffer, 0, prex);
          }
          System.arraycopy(buffer, 0, prebuffer, 0, x);
          prex = x;
          x = 0;
        }
        retsize++;
      }
      outFile.flush();
      outFile.close();
    } catch (EOFException eof) {
      return 0;
    }
    catch (IOException e) {
      System.out.println("ERROR : FILE UPLOAD FAILED (class file Upload :: writeToFile)");
      System.out.println(e);
      return 0;
    }
    return retsize;
  }

  private SQLObject makeQryCmdInfo(HashMap jeonmon) {
    SQLObject sqlx = new SQLObject();
    String query ="";
    query ="SELECT  PACKAGE,          "   +
            "       CLASS,            "   +
            "       METHOD,           "   +
            "       LOGDISP,          "   +
            "       PSTYPE,           "   +
            "       SYSID,            "   +
            "       MENUID,           "   +
            "       EVENTID,          "   +
            "       LABEL,            "   +
            "       EVENTTYPE,        "   +
            "       JSPLOCATION,      "   +
            "       RTNTYPE,          "   +                               // RTNTYPE:1 (XML) //RTNTYPE:2 (JSP)
            "       JSPNAME           "   +
            "       ,DECODE(FLAGDEV,DECODE(TO_CHAR(DATEDEV,'YYYYMMDD'),TO_CHAR(SYSDATE,'YYYYMMDD'),'1','0'),'1','0') UPDFLAG           "   +
            "FROM   PFC01ZT           "   +
            "WHERE SYSID=:SYSID  "   +
            "AND   MENUID  =:MENUID "      +
            "AND   EVENTID =:eventid"      +
            "AND   DFLAG ='0' " ;

    sqlx.setSql(query);
    sqlx.setString("SYSID", jeonmon.get("SYSID").toString().trim());
    sqlx.setString("MENUID", jeonmon.get("MENUID").toString().trim());
    sqlx.setString("EVENTID", jeonmon.get("EVENTID").toString().trim());

    return sqlx;
  }


  public void destroy() {
  }

  private String getDupFileNewName(String path,String filename){
    String newname=filename;
    String newpath="";
    String lastname="";
    String midname="";
    if(filename.indexOf(".")!= -1){
      midname = filename.substring(0,filename.lastIndexOf("."));
      lastname= filename.substring(filename.lastIndexOf("."));
    }

    if(path.trim().substring(path.trim().length()-1).equals("/")){
      newpath = path ;
    }else{
      newpath = path +"/";
    }
    File fp = new File(newpath+newname);
    int i=1;
    while(fp.exists()){
      newname = midname+"V" + i + lastname;
      fp = new File(newpath+newname);
      i++;
    }
   // System.out.println("FILE NEW NAME:"+ newname);
    return newname;
  }

  private boolean isFoundedname(ArrayList rtnname,String name){
    boolean rtn=false;
    for(int i=0;i<rtnname.size();i++){
   //   System.out.println(name.trim()+":" + rtnname.get(i).toString().trim());
      if(name.trim().equals(rtnname.get(i).toString().trim())) {rtn=true; break;}
    }
    return rtn;
  }

}

