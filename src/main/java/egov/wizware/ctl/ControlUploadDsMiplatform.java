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
import egov.wizware.ria.*;
import java.sql.Connection;


public class ControlUploadDsMiplatform    extends HttpServlet {

  private static final String CONTENT_TYPE = "text/html; charset=euc-kr";

  public void init(ServletConfig config) throws ServletException {
    super.init(config);
  }

  public void doGet(HttpServletRequest requesta, HttpServletResponse responsea) throws
      ServletException, IOException {
    doPost(requesta, responsea);
  }

  public void doPost(HttpServletRequest _2483, HttpServletResponse _2474) throws   ServletException, IOException {

    HashMap _4031 = null;
    HashMap _6671 = new HashMap();
    String _242="";
    String _2330="";

    try{
      System.out.println( " Session 처리 " );
    }catch(Exception e){
      e.printStackTrace();
    }
    DOBJ _6185=null;
    Xinternet _xintinput = new Xinternet();
    System.out.println("inputObjectUpload1000");
    _6185=_xintinput.inputObjectUpload1000(_2483, _2474);

    _4031= _6185.getParam();
    Connection _6773 = null;
    SQLObject _1178 = this.makeQryCmdInfo(_4031);
    QExecutor _6278 = new QExecutor();
    VOBJ _4238  = null;
    try {

      Connector _6782 = new Connector();
      _6773 = _6782.getConnection("PFDB");
      _4238  = _6278.executeQuery (_6773, _1178);

      String _3227="";
      _4238.first();
      if (_4238.next()) {

        if(_4238.getRecord().get("PSTYPE").equals("OBJECT")){
          _3227 =_4238.getRecord().get("PACKAGE").toString() + "." + _4238.getRecord().get("CLASS").toString();
          _6671.put("object", _3227);
          _6671.put("method", _4238.getRecord().get("METHOD").toString());
        }else if(_4238.getRecord().get("PSTYPE").equals("NONEJB")){
          _6671.put("object", "PATH.FINDER.BX.CONTROL.EX.PSQX01C");
          _6671.put("method", "NONEJBEXECUTE");
        }else if(_4238.getRecord().get("PSTYPE").equals("EJB")){
          _6671.put("object", "PATH.FINDER.BX.CONTROL.EX.PSQX01C");
          _6671.put("method", "EJBEXECUTE");
        }

        _6671.put("PACKAGEX", _4238.getRecord().get("PACKAGE").toString());
        _6671.put("CLASSX",   _4238.getRecord().get("CLASS").toString());
        _6671.put("METHODX",  _4238.getRecord().get("METHOD").toString());
        System.out.println("PACKAGE INFO:"+_4238.getRecord().get("PACKAGE").toString()+":" + _4238.getRecord().get("CLASS").toString());

      } else {
        _6278.DispSelectSql(_1178);
        System.out.println(" info(" + _4031 + ") table not found :  ");
      }

    }
    catch (Exception e) {
      e.printStackTrace();
    }finally{
      try{
        _6773.close();
      }catch(Exception e){
        e.printStackTrace();
      }
    }

    if(_4238.getRecord().get("PSTYPE").equals("NONEJB")){
      _6185 = this.NonejbExecute(_6185, _6671, _2483);
    }

    Xinternet _xintout = new Xinternet();
    _xintout.outputObject10000(_6185, _2474);

  }



  public DOBJ NonejbExecute(DOBJ _6185, HashMap _6674, HttpServletRequest _req) {

    String _3227name = _6674.get("PACKAGEX").toString() + "." +   _6674.get("CLASSX").toString() ;
    String _3560 = _6674.get("METHODX").toString();
    //String _3560 = "CTL"+_6674.get("METHODX").toString();
    Class _6893 = null;
    try {
      _6893 = Class.forName( _3227name );
    } catch (ClassNotFoundException ex) {
      ex.printStackTrace();
    }

    Object _7454[] = new Object[1];
    Class[] _7133 = new Class[1];
    _7133[0] = _6185.getClass();
    _7454[0] = _6185;
    DOBJ _2468 = null;
    try {
      Method _3572 = _6893.newInstance().getClass().getMethod(_3560, _7133);
      _2468 = (DOBJ) _3572.invoke(_6893.newInstance(),_7454);
      return _2468;
    }
    catch (NoSuchMethodException e) {
      e.printStackTrace();
      return _2468;
    }
    catch (InvocationTargetException e) {
      e.printStackTrace();
      return _2468;
    }
    catch (IllegalAccessException e) {
      e.printStackTrace();
      return _2468;
    }
    catch (InstantiationException e) {
      e.printStackTrace();
      return _2468;
    }
    catch (Exception e) {
      e.printStackTrace();
      return _2468;
    }
  }

  public boolean checkSession(HttpServletRequest _req, HttpServletResponse _res){

      boolean _2468=true;
      String _s = "";

      _s = "/jsp/ADMIN/sessiontimeout.jsp";
      HttpSession _2084 = _req.getSession(true);

      if  ((_2084 == null) || (_2084.getAttribute("INFO") == null)) {

          _2468=false;
          ServletContext _servletcontext = getServletContext();
          RequestDispatcher _requestdispatcher = _servletcontext.
          getRequestDispatcher(_s);

          try {
              _requestdispatcher.forward(_req, _res);
          } catch (ServletException e) {
              System.out.println(e);
          } catch (IOException e) {
              System.out.println(e);
          }

      } else {
          _2468=true;
      }
      return _2468;
  }


  public String getUri(String uri) {
    uri = uri.substring(uri.lastIndexOf("/") + 1);
    return uri;
  }

  public String GetCurrentDate(String wsFormat) {
    Date _524 = new Date();
    DateFormat _df = DateFormat.getDateTimeInstance(DateFormat.FULL,
        DateFormat.MEDIUM,
        Locale.KOREA);
    SimpleDateFormat _yyyyMMddE = new SimpleDateFormat(wsFormat);
    String _80;
    _80 = _yyyyMMddE.format(_524);

    return _80;
  }

  public HashMap dataInputHashMap(String _4001, java.util.HashMap _3062,
                                  String _281) {
    java.util.ArrayList _arr = null;
    if (_3062.containsKey(_4001)) {
      _arr = (ArrayList) _3062.get(_4001);
      _arr.add(_281);
      _3062.put(_4001, _arr);
    }
    else {
      _arr = new java.util.ArrayList();
      _arr.add(_281);
      _3062.put(_4001, _arr);
    }
    return _3062;
  }

  public HashMap makeJeonmunVobj(HttpServletRequest _req, HttpServletResponse _res) {
    HashMap _3062 = new HashMap();
    HashMap _719 = new HashMap();
    HashMap _2564 = null;
    ArrayList _2558 = new ArrayList();
//    ColumnObject cobj = null;
    HashMap _7037 = new HashMap();
    ArrayList _6935 = new ArrayList();
    int _3584 =0;

    Enumeration _e = _req.getParameterNames();
    String _4001 = null;
    String[] _3965 = null;
    String _3968 = "";
    while (_e.hasMoreElements()) {
      _4001 = (String) _e.nextElement();
      _3965 = _req.getParameterValues(_4001);

      if (_3965.length < 2) {
        _3968 = _3965[0];
        _3062.put(_4001, _3968);
      } else {
        _3062.put(_4001, _3965);
      }

      _7037.put(_4001,""+_3965.length);
      _719.put(_4001, _3965);
      _6935.add(_4001);
      if(_3584 < _3965.length) _3584 = _3965.length;
    }

    String[] _551=null;
    for(int i=0;i<_3584;i++){
      _2564 = new HashMap();
      for(int ii=0;ii<_6935.size();ii++){
        _551 = (String[])_719.get(_6935.get(ii).toString());
        if(_551.length > i){
          _2564.put(_6935.get(ii),_551[i]);
        }else{
          _2564.put(_6935.get(ii),_551[_551.length-1]);
        }
      }
      _2558.add(_2564);
    }

    VOBJ _227 = new VOBJ();
    _227.setRecords(_2558);
    _227.setColumnRecordCounts(_7037);

    _3062.put("JVOBJECTXX",_227);
    return _3062;
  }


  public HashMap makeJeonmunUpVobj(java.util.HashMap _3062) {

    java.util.Collection _4259 = _3062.keySet();
    Iterator _4271Iter = _4259.iterator();
    java.util.ArrayList _arr = null;
    String _284[] = null;
    String _305 = "";
    String _4001 = "";

    HashMap _719 = new HashMap();
    HashMap _2564 = null;
    ArrayList _2558 = new ArrayList();
    HashMap _7037 = new HashMap();
    ArrayList _6935 = new ArrayList();
    int _3584 =0;

    while (_4271Iter.hasNext()) {

      _4001 = (String) _4271Iter.next();
      _arr = (ArrayList) _3062.get(_4001);
      if (_arr.size() > 1) {
        _284 = new String[_arr.size()];
        for (int i = 0; i < _arr.size(); i++) {
          _284[i] = codeCvt( (String) _arr.get(i));
        }
        _3062.put(_4001, _284);
        _719.put(_4001,_284);
        _7037.put(_4001,_284.length+"");
        if(_3584 < _284.length) _3584 = _284.length;

      }  else {
        String _554[] = new String[1];
        _305 = codeCvt( (String) _arr.get(0));
        _3062.put(_4001, _305);
        _554[0] = _305;
        _719.put(_4001,_554);
        _7037.put(_4001,_554.length+"");
        if(_3584 < _554.length) _3584 = _554.length;

      }
      _6935.add(_4001);
    }

    String[] _551=null;
    for(int i=0;i<_3584;i++){
      _2564 = new HashMap();
      for(int ii=0;ii<_6935.size();ii++){
        _551=null;
        _551 = (String[])_719.get(_6935.get(ii).toString());
        if(_551.length > i){
          _2564.put(_6935.get(ii),_551[i]);
        }else{
          _2564.put(_6935.get(ii),_551[_551.length-1]);
        }
      }
      _2558.add(_2564);
    }

    VOBJ _227 = new VOBJ();
    _227.setRecords(_2558);
    _227.setColumnRecordCounts(_7037);

    _3062.put("JVOBJECTXX",_227);
    return _3062;
  }


  public HashMap makeJeonmun(HttpServletRequest _req, HttpServletResponse _res) {
    java.util.HashMap _3062 = new java.util.HashMap();
    Enumeration _e = _req.getParameterNames();
    String _4001 = null;
    String[] _3965 = null;
    String _3968 = "";
    while (_e.hasMoreElements()) {
      _4001 = (String) _e.nextElement();
      _3965 = _req.getParameterValues(_4001);
      if (_3965.length < 2) {
        _3968 = _3965[0];
        _3062.put(_4001, _3968);
      }
      else {
        _3062.put(_4001, _3965);
      }
    }
    return _3062;
  }


  public HashMap makeJeonmunHashMap(java.util.HashMap _3062) {

    java.util.Collection _4259 = _3062.keySet();
    Iterator _4271Iter = _4259.iterator();
    java.util.ArrayList _arr = null;
    String _284[] = null;
    String _305 = "";
    String _4001 = "";

    while (_4271Iter.hasNext()) {

      _4001 = (String) _4271Iter.next();
      _arr = (ArrayList) _3062.get(_4001);

      if (_arr.size() > 1) {
        _284 = new String[_arr.size()];
        for (int i = 0; i < _arr.size(); i++) {
          _284[i] = codeCvt( (String) _arr.get(i));
        }
        _3062.put(_4001, _284);
      }
      else {
        _305 = codeCvt( (String) _arr.get(0));
        _3062.put(_4001, _305);
      }
    }
    return _3062;
  }

  public String findName(String _284) {
    String _4208 = "";
    if (_284 != null && _284.length() > 1) {
      _4208 = _284.substring(_284.indexOf("name=\"") + "name=\"".length(),
                                 _284.length() - 1);
    }
    return _4208;
  }



  public int writeToFile(DataInputStream in, String filename, String deliminator) {

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

  public SQLObject makeQryCmdInfo(HashMap jeonmon) {

    SQLObject sqlx = new SQLObject();
    String query ="";
    query ="SELECT PACKAGE,          "   +
            "       CLASS,            "   +
            "       METHOD,           "   +
            "       PSTYPE,           "   +
            "       SYSID,            "   +
            "       MENUID,           "   +
            "       EVENTID,          "   +
            "       LABEL,            "   +
            "       EVENTTYPE,        "   +
            "       JSPLOCATION,      "   +
            "       RTNTYPE,          "   +                               // RTNTYPE:1 (XML) //RTNTYPE:2 (JSP)
            "       JSPNAME           "   +
            "FROM   PFC01ZT           "   +
            "WHERE SYSID=:parentid  "   +
            "AND   MENUID  =:menuid "      +
            "AND   EVENTID =:eventid"      +
            "AND   DFLAG ='0' " ;

    sqlx.setSql(query);
    sqlx.setString("parentid", jeonmon.get("SYSID").toString().trim());
    sqlx.setString("menuid", jeonmon.get("MENUID").toString().trim());
    sqlx.setString("eventid", jeonmon.get("EVENTID").toString().trim());

    return sqlx;
  }

  public String codeCvt(String str) {
    String newstr = null;
    try {
      newstr = new String(str.getBytes("Cp1252"), "KSC5601");
    }
    catch (java.io.UnsupportedEncodingException e) {}

    return newstr;
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
      newname = midname+"L" + i + lastname;
      fp = new File(newpath+newname);
      i++;
    }
    System.out.println("FILE NEW NAME:"+ newname);
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


