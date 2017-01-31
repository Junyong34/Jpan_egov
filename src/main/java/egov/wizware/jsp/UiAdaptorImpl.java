package egov.wizware.jsp;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.*;
import egov.wizware.com.DOBJ;
import egov.wizware.com.VOBJ;
import java.io.*;
import java.text.*;
import javax.servlet.http.*;
import javax.servlet.*;

public class UiAdaptorImpl implements UiAdaptor
{
    private final int betterform = 0;
    private final int myplatform = 1;
    private final int gauce = 2;
    private final int mybuilder = 3;
    private final int jsp = 4;
    private final int xplatform = 5;

    private final int rianame = jsp;
    protected Log log = LogFactory.getLog(this.getClass());
    private   String _tmpUploadFilePath="";    //upload temp folder ��ġ ����

    // Processbuilder Start Input data : Not Create
    public Object convert(HttpServletRequest request) throws Exception
    {
        System.out.println("ACTION URL convert:" + request.getRequestURI());
        DevArgv argv = new DevArgv();
        _tmpUploadFilePath = argv.getUploadTempFolder();
        DOBJ dobj = null;
        VOBJ _gvobj = null;
        if ((request.getContentType() != null) &&   (request.getContentType().indexOf("multipart") == 0))
        {
           _gvobj = _getSession(request);
           dobj =_fileTrans( request , _gvobj) ;
        }
        else
        {
            request.setCharacterEncoding("UTF-8");
            if(rianame == betterform)
            {
                BTFormXmlBuilder bxml = new BTFormXmlBuilder();
                dobj = bxml.inputObject(request.getInputStream());
            }
            else if(rianame == myplatform)
            {
                try
                {
                   MyPlatFormBuilder myp = new MyPlatFormBuilder();
                   dobj = myp.inputObject(request);
               }
               catch(Exception e)
               {
                   e.printStackTrace();
               }
           }
           else if(rianame == xplatform)
           {
               try
               {
                   XPlatFormBuilder xbtf = new XPlatFormBuilder();
                   dobj = xbtf.inputObject(request);
               }
               catch(Exception e)
               {
                   e.printStackTrace();
               }
           }
           else if(rianame == mybuilder)
           {
               try
               {
                   MyBuilder my = new MyBuilder();
                   dobj = my.inputObject(request);
               }
               catch(Exception e)
               {
                   e.printStackTrace();
               }
           }
           else if(rianame == jsp)
           {
               request.setCharacterEncoding("UTF-8");
               dobj = converte4In(request);
           }

           request.setAttribute("outdatasets", dobj.getOutkeys());
           _gvobj = _getSession(request);

       }
       _gvobj.setName("G");
       dobj.setRetObject(_gvobj);
       dobj.request = request;
       return dobj;
   }

    public Object convertDev(HttpServletRequest request) throws Exception
    {
        System.out.println("ACTION URL DEV convertDev:" + request.getRequestURI() + ":" + request.getContentType());
        DevArgv argv = new DevArgv();
        _tmpUploadFilePath = argv.getUploadTempFolder();
        DOBJ dobj = null;
        VOBJ _gvobj = null;
        if ((request.getContentType() != null) &&   (request.getContentType().indexOf("multipart") == 0))
        {
           _gvobj = _getSession(request);
           dobj =_fileTrans( request , _gvobj) ;
        }
        else
        {
           request.setCharacterEncoding("UTF-8");
           if(rianame == betterform)
           {
               BTFormXmlBuilder bxml = new BTFormXmlBuilder();
               dobj = bxml.inputObject(request.getInputStream());
           }
           else if(rianame == myplatform)
           {
               try
               {
                   MyPlatFormBuilder myp = new MyPlatFormBuilder();
                   dobj = myp.inputObject(request);
               }
               catch(Exception e)
               {
                   e.printStackTrace();
               }
           }
           else if(rianame == xplatform)
           {
               try
               {
                   XPlatFormBuilder xbtf = new XPlatFormBuilder();
                   dobj = xbtf.inputObject(request);
               }
               catch(Exception e)
               {
                   e.printStackTrace();
               }
           }
           else if(rianame == mybuilder)
           {
               try
               {
                   MyBuilder my = new MyBuilder();
                   dobj = my.inputObject(request);
               }
               catch(Exception e)
               {
                   e.printStackTrace();
               }
           }
           else if(rianame == jsp)
           {
               request.setCharacterEncoding("UTF-8");
               dobj = converte4In(request);
           }

           request.setAttribute("outdatasets", dobj.getOutkeys());
           _gvobj = _getSession(request);
       }
      //  argv.DevLog(request.getRequestURI(), "aa", dobj);
        _gvobj.setName("G");
        dobj.setRetObject(_gvobj);
        dobj.request = request;
        _dispObject(dobj);
        return dobj;
    }

    private void _dispObject(DOBJ dobj)
    {
        ArrayList _alist = dobj.getRetObjectKeys();
        VOBJ _vobj = null;
        for(int i=0;i<_alist.size();i++)
        {
            _vobj = dobj.getRetObject(_alist.get(i).toString());
            _vobj.Println("INPUT OBJECTS");
        }
    }

    private  DOBJ converte4In(HttpServletRequest request)
    {
        DOBJ dobj = makeDOBJ(request);
        return dobj;
    }

    //-------------------------------------------------------------------
    private VOBJ _getSession(HttpServletRequest req)
    {
        HttpSession _sesn = req.getSession(true);
        VOBJ gvobj =null;

        // TEST �� �ӽ� SESSION ���
        if(true)
        {
             gvobj = new VOBJ();
             HashMap hmap =new HashMap();
             hmap.put("USER_ID","bduser01");
             hmap.put("COMPANY_ID","CP000201");
             gvobj.addRecord(hmap);
             gvobj.setName("G");
             return gvobj;
        }
        //---------------------------------------------------

        if  ((_sesn == null) || (_sesn.getAttribute("G") == null))
        {
            gvobj = new VOBJ();
            gvobj.setName("G");
        }
        else
        {
            gvobj  = (VOBJ)_sesn.getAttribute("G");
        }
        return gvobj;
    }
    //-----------------------------------------------------------------

    public  Class getModelName()
    {
        return DOBJ.class;
    }
    private DOBJ makeDOBJ(HttpServletRequest req )
    {
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

        while (e.hasMoreElements())
        {
            key = (String) e.nextElement();
            System.out.println("INPUT KEY:"+key);

            if(key.indexOf("_") == -1)
            {
                continue;
            }
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
        while(_itor.hasNext())
        {
            _dsname = _itor.next().toString();
            VOBJ _vobj = _getVOBJ( _dsname, _dsmap.get(_dsname) ,  param);
            _vobj.Println("INPUT");
            _dobj.setRetObject(_vobj);
        }
        _dobj.setParam(processkey);
        return _dobj;
    }
    private VOBJ _getVOBJ(String _dsname, Object _cobj, HashMap param)
    {
        ArrayList _clist = (ArrayList) _cobj;
        String[] _vals = null;
        int  _msize = _getMaxsize( _dsname,  _clist,  param);
        VOBJ _vobj = new VOBJ();
        ArrayList _records = new ArrayList();
        HashMap   _rec = null;
        boolean   _isNull = true;
        int _cnt=0;
        for(int x=0;x<_msize;x++)
        {
            _rec = new HashMap();
            _isNull = true;
            for(int i=0;i<_clist.size();i++)
            {
                _vals = (String[]) param.get(_dsname +"_"+ _clist.get(i));
                if(_vals.length > x)
                {
                    if(_vals[x] != null && !_vals[x].trim().equals("")) _isNull = false;   // null reocrd \uFFFD\uFFFD��\uFFFD\uFFFD�� 泥섎\uFFFD�� 湲곕\uFFFD��
                    _rec.put(_clist.get(i), _vals[x]);
                }
                else
                {
                    _isNull = false;
                    _rec.put(_clist.get(i), "");
                }
            }
            if(_isNull == false)
            {  // null reocrd \uFFFD\uFFFD��\uFFFD\uFFFD�� 泥섎\uFFFD�� 湲곕\uFFFD��
                _records.add(_rec);
            }
        }
        _vobj.setRecords(_records);
        _vobj.setName(_dsname);
        return _vobj;
    }
    private int _getMaxsize(String _dsname, ArrayList _clist, HashMap param)
    {
        int maxrtn=0;
        String[] vals = null;
        if(_clist != null){
            for(int i=0;i<_clist.size();i++)
            {
                vals = null;
                vals = (String[])param.get(_dsname+"_"+_clist.get(i));
                if(maxrtn < vals.length) maxrtn = vals.length;
            }
        }
        return maxrtn;
    }
    private HashMap getDSColumnlist(ArrayList _namelist)
    {
        HashMap _dslist = new HashMap();
        ArrayList _clist = null;
        String _cname="";
        String _dsname="";
        for(int i=0;i<_namelist.size();i++)
        {
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

            if(_dslist.containsKey(_dsname))
            {
                _clist = (ArrayList)_dslist.get(_dsname);
                if(!findedName(_clist, _cname))
                {
                    _clist.add(_cname);
                }
                _dslist.put(_dsname, _clist);
            }
            else
            {
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
            if(_name.equals(_clist.get(i).toString()))
            {
                return true;
            }
        }
        return false;
    }

    //------------- ���� upload ��ó�� ��� -------------------------

    private String _findName(String _vals)
    {
        String _inputname = "";
        if (_vals != null && _vals.length() > 1) {
            _inputname = _vals.substring(_vals.indexOf("name=\"") + "name=\"".length(),  _vals.length() - 1);
        }
        return _inputname;
    }


    private DOBJ _fileTrans(HttpServletRequest req, VOBJ gvobj )
    {
        HashMap _param  = new HashMap();
        DOBJ dobj = null;
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

        try
        {

            DataInputStream in = new DataInputStream(req.getInputStream());
            _readlinestr = in.readLine();
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
                    if (str.indexOf("filename=") != -1)
                    {
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
                        if (str.indexOf("Content-Type") == 0)
                        {
                            str = in.readLine(); // Skip Empty Line
                        }

                        if (!_filename.equals(""))
                        {
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
                    else if (str.indexOf("contents") != -1)
                    {
                        str = in.readLine(); // Skip Line
                        _paramValue = "";
                        while ( (str = in.readLine()) != null)
                        {
                            if (str.indexOf(_readlinestr) == 0)
                                break;
                            _paramValue = _paramValue + br + str;
                        } // while
                    }
                    else
                    {
                        str = in.readLine();
                        _paramValue = "";
                        while ( (str = in.readLine()) != null)
                        {
                            if (str.indexOf(_readlinestr) == 0)
                            {
                                _tmpi = 0;
                                break;
                            }
                            if (_tmpi == 0)
                            {
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
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return dobj;
    }

    private HashMap _dataInputHashMap(String key, java.util.HashMap _param, String value) {
        java.util.ArrayList arr = null;
        if (_param.containsKey(key))
        {
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
        if (deliminator == null)return retsize; // deliminator�� ��� �ִ��� ���θ� üũ
        FileOutputStream _outFile =  null;
        try
        {
            _outFile = new FileOutputStream(_filename);
            while (flag)
            {
                ch = in.readByte();
                buffer[x++] = ch;
                if (ch == '\n' || x == 1023) {
                    String _strTmp = new String(buffer, 0, x);
                    if ( (_delpos = _strTmp.indexOf(deliminator)) != -1) {
                        _outFile.write(_pre_buffer, 0, _pre_x - 2); // CR/LF�� �����ϱ� ���� -2�Ѵ�.
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


    private String _getDupFileNewName(String path, String _filename, VOBJ gvobj)
    {

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

    private  String GetCurrentDate()
    {
        Date _DD = new Date();
        SimpleDateFormat _yyyyMMddE = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String _rtn;
        _rtn = _yyyyMMddE.format(_DD);

        return _rtn;
    }


    private DOBJ makeJeonmunUpVobj(HashMap _inparam, ArrayList _flist )
    {
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
            while(true)
            {
                if(_tochar[0] != 'S')
                {
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
            if(_dsfilemap!= null && _dsfilemap.containsKey(_dsname)) _dsfilemap.remove(_dsname);   //ó���� dataset�� ������ ���� ó�� �Ѵ�.
            _vobj.Println("INPUT");
            _dobj.setRetObject(_vobj);
        }

        //-----_dsfilemap���� ó�� ���� ���� dataset�� ���� �Ѵ�.
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
    private String[] _getValues(Object _voj)
    {
        ArrayList _vlist = (ArrayList)_voj;
        String[] str = new String[_vlist.size()];
        for(int i=0;i<_vlist.size();i++)
        {
            str[i] = _codeCvt(_vlist.get(i).toString());
        }
        return str;
    }

    private String _codeCvt(String str)
    {
        String _newstr = null;
        try
        {
            _newstr = new String(str.getBytes("8859_1"), "UTF-8");
        }
        catch (java.io.UnsupportedEncodingException e) {}
        return _newstr;
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
                _rec.put("UNIFILENAME",_info.getUniname());   // UNIFILENAME
                _rec.put("UPFILENAME" ,_info.getFilename());  // UPFILENAME
                String _fullpath = _info.getPath().trim();
                if(!_fullpath.substring(_fullpath.length()-1).equals("/")) _fullpath +="/" ;
                _rec.put("FULLPATH",_fullpath + _info.getUniname());               // FULLPATH���ϸ�
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
                if(_vals.length >= x) {
                    _rec.put(_clist.get(i), _vals[x]);
                } else {
                    _rec.put(_clist.get(i), "");
                }
            }
            String _vname ="";
            for(int i=0;i<_varlist.size();i++)
            {
                _vname = _varlist.get(i).toString();
                ArrayList _fxlist = _getVarFileInfos(_vname, _flist);
                if(_fxlist!=null && _flist.size() > x){
                    TransFileInfo _info = (TransFileInfo)_fxlist.get(x);
                    _rec.put("FILESIZE",_info.getSize() +"");     // FILESIZE
                    _rec.put("PATH"    ,_info.getPath());         // PATH
                    _rec.put("UNIFILENAME",_info.getUniname());   // UNIFILENAME
                    _rec.put("UPFILENAME" ,_info.getFilename());  // UPFILENAME
                    String _fullpath = _info.getPath().trim();
                    if(!_fullpath.substring(_fullpath.length()-1).equals("/")) _fullpath +="/";
                    _fullpath += _info.getUniname();
                    _rec.put("FULLPATH",_fullpath);               // FULLPATH���ϸ�
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

}
