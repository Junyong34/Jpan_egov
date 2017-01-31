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
import egov.wizware.ria.*;
import java.sql.Connection;


public class LoginServletMiplatform    extends HttpServlet {

  private static final String CONTENT_TYPE = "text/html; charset=euc-kr";

  public void init(ServletConfig config) throws ServletException {
    super.init(config);
  }

  public void doGet(HttpServletRequest requesta, HttpServletResponse responsea) throws
      ServletException, IOException {
    doPost(requesta, responsea);
  }

  public void doPost(HttpServletRequest requestb, HttpServletResponse responseb) throws   ServletException, IOException {

    HashMap jeonmon = null;
    HashMap ctlmap = new HashMap();
    String view="";
    String rtntype="";

    DOBJ dobj=null;
    Xinternet xintinput = new Xinternet();
    dobj=xintinput.inputObject1000(requestb, responseb);

    VOBJ _vobj = dobj.getRetObject("S");
    if(_vobj.getRecord().get("STEP").equals("1")){
        //WIZ.ROLE.MINISTOP.LoginCheck loginchk = new WIZ.ROLE.MINISTOP.LoginCheck();
        //  1: 사용자가 존재하지 않음 , 2:password오류, 3:주민번호 오류
        // 11: interal network이 아닌 경우  (외부 IP Address)
        //     STEP의 값을 '2'로 SETTING하고 SMS 인증 PASSWORD로 재로그인 한다.
        //  0: Login 처리
        //  return object : M1( 전체Menu) , M2( 개인Private Menu) , U1:(사용자 정보)
        //dobj = loginchk.checkLogin(dobj);
    }else {  //STEP 2
        //WIZ.ROLE.MINISTOP.LoginCheck loginchk = new WIZ.ROLE.MINISTOP.LoginCheck();
        // 12: SMS 인증 PASSWORD 처리 오류 발생
        //  0: Login 처리
        //  return object : M1( 전체Menu) , M2( 개인Private Menu) , U1:(사용자 정보)
        //dobj = loginchk.checkLoginSinglePwd(dobj);
    }

    if(dobj.getRtncode()==0){
        try{
            SessionObject _sson = new SessionObject();
            VOBJ _svobj = _sson.getObject(requestb);
            this.makeSession(requestb,_svobj);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    Xinternet xintout = new Xinternet();
    xintout.outputObject10000(dobj, responseb);
  }

  private void makeSession(HttpServletRequest req, VOBJ _vobj) {
      HttpSession session = req.getSession(true);
      session.setAttribute("G", _vobj);
  }

  public boolean checkSession(HttpServletRequest req, HttpServletResponse res){

      boolean ret=true;
      String s = "";

      s = "/jsp/ADMIN/sessiontimeout.jsp";
      HttpSession sesn = req.getSession(true);

      if  ((sesn == null) || (sesn.getAttribute("INFO") == null)) {

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
          ret=true;
      }
      return ret;
  }


  public String getUri(String uri) {
    uri = uri.substring(uri.lastIndexOf("/") + 1);
    return uri;
  }

  public String GetCurrentDate(String wsFormat) {
    Date TodayDate = new Date();
    DateFormat df = DateFormat.getDateTimeInstance(DateFormat.FULL,
        DateFormat.MEDIUM,
        Locale.KOREA);
    SimpleDateFormat yyyyMMddE = new SimpleDateFormat(wsFormat);
    String wsCurrentDate;
    wsCurrentDate = yyyyMMddE.format(TodayDate);

    return wsCurrentDate;
  }

  public HashMap dataInputHashMap(String key, java.util.HashMap param,
                                  String value) {
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

  public HashMap makeJeonmunVobj(HttpServletRequest req, HttpServletResponse res) {
    HashMap param = new HashMap();
    HashMap tmpHash = new HashMap();
    HashMap record = null;
    ArrayList records = new ArrayList();
//    ColumnObject cobj = null;
    HashMap colHash = new HashMap();
    ArrayList columnNms = new ArrayList();
    int maxsize =0;

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
      } else {
        param.put(key, keyVals);
      }

      colHash.put(key,""+keyVals.length);
      tmpHash.put(key, keyVals);
      columnNms.add(key);
      if(maxsize < keyVals.length) maxsize = keyVals.length;
    }

    String[] tmpVals=null;
    for(int i=0;i<maxsize;i++){
      record = new HashMap();
      for(int ii=0;ii<columnNms.size();ii++){
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


  public HashMap makeJeonmunUpVobj(java.util.HashMap param) {

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
          vals[i] = codeCvt( (String) arr.get(i));
        }
        param.put(key, vals);
        tmpHash.put(key,vals);
        colHash.put(key,vals.length+"");
        if(maxsize < vals.length) maxsize = vals.length;

      }  else {
        String tmpval[] = new String[1];
        val = codeCvt( (String) arr.get(0));
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


  public HashMap makeJeonmun(HttpServletRequest req, HttpServletResponse res) {
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


  public HashMap makeJeonmunHashMap(java.util.HashMap param) {

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
          vals[i] = codeCvt( (String) arr.get(i));
        }
        param.put(key, vals);
      }
      else {
        val = codeCvt( (String) arr.get(0));
        param.put(key, val);
      }
    }
    return param;
  }

  public String findName(String vals) {
    String inputname = "";
    if (vals != null && vals.length() > 1) {
      inputname = vals.substring(vals.indexOf("name=\"") + "name=\"".length(),
                                 vals.length() - 1);
    }
    return inputname;
  }


  public HashMap fileTrans(HashMap param, HttpServletRequest req,   HttpServletResponse res) {
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
              System.out.println(tmpUploadFilePath+unifilename + ":" + filename );
              int size = writeToFile(in, tmpUploadFilePath + unifilename, deliminator);

              TransFileInfo info = new TransFileInfo();
              info.setSize(size);
              info.setFiletype(filename.substring(filename.indexOf('.') + 1));
              info.setFilename(codeCvt(filename));
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

  public int writeToFile(DataInputStream in, String filename,  String deliminator) {

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


  private boolean checkL1(HashMap parm){
    boolean rtn = false;
    String L1 = parm.get("SYSID").toString().trim();
    return true;
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
      newname = midname+"V" + i + lastname;
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


