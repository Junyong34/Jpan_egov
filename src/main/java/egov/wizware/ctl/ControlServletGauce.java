package egov.wizware.ctl;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import javax.servlet.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import egov.wizware.com.*;
import egov.wizware.dao.Connector;
import egov.wizware.ria.GauceRia;

public class ControlServletGauce    extends HttpServlet {

  private static final String CONTENT_TYPE = "text/html; charset=euc-kr";

  public void init(ServletConfig config) throws ServletException
  {
    super.init(config);
  }

  public void doGet(HttpServletRequest requesta, HttpServletResponse responsea) throws  ServletException, IOException
  {
    doPost(requesta, responsea);
  }

  public void doPost(HttpServletRequest requestb, HttpServletResponse responseb) throws   ServletException, IOException
  {

    HashMap jeonmon = null;
    HashMap ctlmap = new HashMap();
    String view="";
    String rtntype="";

    try
    {
      System.out.println( " Session 처리 " );
    }catch(Exception e){
      e.printStackTrace();
    }
    DOBJ dobj=null;
    GauceRia gauce = new GauceRia();
    try {
        dobj=gauce.inputObjectGauce(requestb); // 입력 데이터셋
    } catch (Exception e) {
        e.printStackTrace();
    }
    jeonmon= dobj.getParam();

    Connection CONNX = null;
    SQLObject sqlx = this.makeQryCmdInfo(jeonmon);
    QExecutor dexe = new QExecutor();
    VOBJ info  = null;
    try {
        /*************************LOG INFO SET***********************************/
        TimeInterval tinteval = new TimeInterval();
        CTLLog clog = new CTLLog(2);

        clog.setEventInfo(jeonmon.get("SYSID").toString(),jeonmon.get("MENUID").toString(),jeonmon.get("EVENTID").toString());
        /************************************************************/

        Connector connector = new Connector();
        CONNX = connector.getConnection("PFDB");
        info  = dexe.executeQuery (CONNX, sqlx);

        int disp =0;
        String _dispmode = info.getRecord().get("LOGDISP");
        if(_dispmode==null ||  _dispmode.trim().equals("")   ||  _dispmode.trim().equals("0") ) disp=0;
        else disp=1;
        String  flag = "";

        String object="";
        info.first();
        if (info.next()) {
            flag = info.getRecord().get("UPDFLAG");
            if(info.getRecord().get("PSTYPE").equals("OBJECT")){
                object =info.getRecord().get("PACKAGE").toString() + "." + info.getRecord().get("CLASS").toString();
                ctlmap.put("object", object);
                ctlmap.put("method", info.getRecord().get("METHOD").toString());
            }else if(info.getRecord().get("PSTYPE").equals("NONEJB")){
                ctlmap.put("object", "PATH.FINDER.BX.CONTROL.EX.PSQX01C");
                ctlmap.put("method", "NONEJBEXECUTE");
            }else if(info.getRecord().get("PSTYPE").equals("EJB")){
                ctlmap.put("object", "PATH.FINDER.BX.CONTROL.EX.PSQX01C");
                ctlmap.put("method", "EJBEXECUTE");
            }

            ctlmap.put("PACKAGEX", info.getRecord().get("PACKAGE").toString());
            ctlmap.put("CLASSX",   info.getRecord().get("CLASS").toString());
            ctlmap.put("METHODX",  info.getRecord().get("METHOD").toString());
            /*********************LOG INFO SET***************************************/
            clog.setMethodInfo(info.getRecord().get("PACKAGE").toString(),info.getRecord().get("CLASS").toString() ,info.getRecord().get("METHOD").toString());
            /************************************************************/
            System.out.println("PACKAGE INFO:"+info.getRecord().get("PACKAGE").toString()+":" + info.getRecord().get("CLASS").toString());

        } else
        {
            dexe.DispSelectSql(sqlx);
            System.out.println(" info(" + jeonmon + ") table not found :  ");
        }

        dobj.setDispmode(disp);
        if(flag.equals("1")) clog.DevLog(CONNX, dobj.getRetObject("G").getRecord().get("USER_ID"), dobj);

        if(info.getRecord().get("PSTYPE").equals("NONEJB")){
            dobj = this.NonejbExecute(dobj, ctlmap);
            if(dobj.getRtncode() == -1)  clog.excepWrite(CONNX, dobj.getRetObject("G").getRecord().get("USER_ID"),  dobj.getRetmsg(), "", dobj);
        }

        if(dobj.getRtncode() == -1)
        {
            //DOBJ _9200 = xintinput.inputObject1000(requestb);
            //clog.excepWrite(CONNX,dobj.getRetObject("G").getRecord().get("USERID"), dobj.getRetmsg(), "", _9200);
            //clog.excepWrite(CONNX,dobj.getRetObject("G").getRecord().get("USERID"), dobj.getRetmsg(), "");
        }

        dobj.deleteInKeyDatasets();
        dobj.deleteOutKeyDatasets();
        gauce.outputObjectGauce(requestb, responseb, dobj);

        /**********************LOG WRITE**************************************/
        tinteval.setEndTime();
        clog.write(CONNX,dobj.getRetObject("G").getRecord().get("USERID"), tinteval.getInterval()+"");
        /************************************************************/

    }
    catch (Exception e) {
        e.printStackTrace();
    }finally{
        try{
            CONNX.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

public DOBJ NonejbExecute(DOBJ dobj, HashMap ctl) {

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
              "WHERE SYSID=:SYSID  "        +
              "AND   MENUID  =:MENUID "     +
              "AND   EVENTID =:eventid"      +
            "AND   DFLAG ='0' " ;

      sqlx.setSql(query);
      sqlx.setString("SYSID", jeonmon.get("SYSID").toString().trim());
      sqlx.setString("MENUID", jeonmon.get("MENUID").toString().trim());
      sqlx.setString("EVENTID", jeonmon.get("EVENTID").toString().trim());

      return sqlx;
    }

  public SQLObject makeQryCmdInfoxx(HashMap jeonmon) {

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
            "WHERE SYSID=:SYSID  "   +
            "AND   MENUID  =:MENUID "      +
            "AND   EVENTID =:EVENTID" ;

    sqlx.setSql(query);
    sqlx.setString("SYSID", jeonmon.get("SYSID").toString().trim());
    sqlx.setString("MENUID", jeonmon.get("MENUID").toString().trim());
    sqlx.setString("EVENTID", jeonmon.get("EVENTID").toString().trim());

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

