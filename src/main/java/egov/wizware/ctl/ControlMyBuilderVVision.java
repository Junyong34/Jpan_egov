package egov.wizware.ctl;

import java.io.*;
import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import egov.wizware.com.*;
import egov.wizware.dao.*;


public class ControlMyBuilderVVision   extends HttpServlet {

  private static final String CONTENT_TYPE = "text/html; charset=euc-kr";
  public void init(ServletConfig config) throws ServletException
  {
    super.init(config);
  }

  public void doGet(HttpServletRequest requesta, HttpServletResponse responsea) throws  ServletException, IOException
  {
      doPost(requesta, responsea);
  }

  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws   ServletException, IOException
  {
     //req.setCharacterEncoding("UTF-8");
     HashMap target = null;
     HashMap ctlmap = new HashMap();
     DOBJ dobj=null;
     MyBuilder mybuilder = new MyBuilder();

     try
     {
         dobj=mybuilder.inputObject(req.getInputStream()); // 입력 데이터셋
         System.out.println("====[ INPUT VOBJ DISPLAY ]===");
         String name="";
         ArrayList inlist = dobj.getRetObjectKeys();
          for(int i=0;i<inlist.size();i++)
          {
              name = inlist.get(i).toString();
              VOBJ vobj = dobj.getRetObject(name);
              vobj.Println("INPUT");
          }

          //VOBJ gvobj = this.getDevSession();    //개발기
          VOBJ gvobj = checkSession(req,  resp);  //운영기
          if(gvobj == null)
          {
              StringBuffer xml = mybuilder.outLogout();
              forwardx( resp, xml);
              return;
          }

          gvobj.setName("G");
          //gvobj.Println("yy:GLOBAL OBJECT=G");
          dobj.setRetObject("G",gvobj);
      }
      catch (Exception e)
      {
          e.printStackTrace();
      }

      target= dobj.getParam();
      Connection CONNX = null;
      SQLObject sqlx = this.makeQryCmdInfo(target);
      QExecutor dexe = new QExecutor();
      VOBJ info  = null;
      try {

              /*************************LOG INFO SET***********************************/
              TimeInterval tinteval = new TimeInterval();
              CTLLog clog = new CTLLog(2);
              clog.setEventInfo(target.get("SYSID").toString(),target.get("MENUID").toString(),target.get("EVENTID").toString());
              /************************************************************/

              Connector connector = new Connector();
              CONNX = connector.getConnection("DMSH");
              info  = dexe.executeQuery (CONNX, sqlx);

              int disp =0;
              String _dispmode = info.getRecord().get("LOGDISP");
              if(_dispmode==null ||  _dispmode.trim().equals("")   ||  _dispmode.trim().equals("0") ) disp=0;
              else disp=1;
              String  flag = "";

              String object="";
              info.first();
              if (info.next())
              {
                  flag = info.getRecord().get("UPDFLAG");
                  if(info.getRecord().get("PSTYPE").equals("OBJECT")){
                      object =info.getRecord().get("PACKAGE").toString() + "." + info.getRecord().get("CLASS").toString();
                      ctlmap.put("object", object);
                      ctlmap.put("method", info.getRecord().get("METHOD").toString());
                  }else if(info.getRecord().get("PSTYPE").equals("NONEJB")){
                      ctlmap.put("object", "PATH.FINDER.BX.CONTROL.EX.PSQX01C");
                      ctlmap.put("method", "NONEJBEXECUTE");
                  }else if(info.getRecord().get("PSTYPE").equals("EJB"))
                  {
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

              }
              else
              {
                  dexe.DispSelectSql(sqlx);
                  System.out.println(" info(" + target + ") table not found :  ");
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
               forwardx( resp, mybuilder.getOutXmlData(dobj));

               /**********************LOG WRITE**************************************/
               tinteval.setEndTime();
               clog.write(CONNX,dobj.getRetObject("G").getRecord().get("USERID"), tinteval.getInterval()+"");
               /************************************************************/

           }
           catch (Exception e)
           {
               e.printStackTrace();
           }finally
           {
               try
               {
                   CONNX.close();
               }catch(Exception e)
               {
                   e.printStackTrace();
               }
           }
       }

       private VOBJ getDevSession(){
           VOBJ _gvobj = new VOBJ();
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
               else
               {
                   System.out.println("packagenm:"+ packagenm);
                   System.out.println("classnm  :"+ classnm);
                   System.out.println("methodnm :"+ methodnm);
                   System.out.println("objectid :"+ objectid);
               }
           }catch(Exception e){
               e.printStackTrace();
           }
           return _gvobj;
       }

       private VOBJ checkSession(HttpServletRequest req, HttpServletResponse res)
       {
           System.out.println("Session Check Start");
           boolean ret=true;
           String s = "";
           s = "/COM/logout.jsp";
           HttpSession _sesn = req.getSession(true);
           VOBJ gvobj =null;
           if  ((_sesn == null) || (_sesn.getAttribute("G") == null)) {
               System.out.println("Session Not Found:::Logout:" + req.getRemoteAddr());
              return null;
           } else {
               gvobj  = (VOBJ)_sesn.getAttribute("G");
               gvobj.Println("LOGIN SESSION DATA");
           }
           return gvobj;
       }

       public void forward(HttpServletResponse res, StringBuffer data)
       {
           try
           {
               res.setContentType( "text/plain; charset=UTF-8" );
               res.getOutputStream().write(  data.toString().getBytes());
           }
           catch (IOException e)
           {
               System.out.println(e);
           }
       }

       public void forwardx(HttpServletResponse res, StringBuffer data)
       {
           try {
               res.setContentType("text/plain; charset=UTF-8");
               PrintWriter out = new PrintWriter(new OutputStreamWriter(res.getOutputStream(), "UTF-8"), false);

               //System.out.println("PrintWriter:"+data.toString());

               out.write(data.toString());
               out.flush();
           } catch (Exception e) {
               e.printStackTrace();
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


           public String findName(String vals) {
               String inputname = "";
               if (vals != null && vals.length() > 1) {
                   inputname = vals.substring(vals.indexOf("name=\"") +
                                              "name=\"".length(),
                                              vals.length() - 1);
               }
               return inputname;
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
              "       NOTE,             "   +
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


  public String codeCvtUtf8(String str) {
    String newstr = null;
    try {
      newstr = new String(str.getBytes("8859_1"), "UTF-8");
    }
    catch (java.io.UnsupportedEncodingException e) {}

    return newstr;
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

