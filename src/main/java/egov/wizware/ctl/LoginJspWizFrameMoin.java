package egov.wizware.ctl;

import java.io.*;
import java.util.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.*;
import egov.wizware.com.*;
import egov.wizware.dao.*;
import java.sql.Connection;


public class LoginJspWizFrameMoin    extends HttpServlet {

  private static final String CONTENT_TYPE = "text/html; charset=euc-kr";
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
  }
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws   ServletException, IOException {
    doPost(req, resp);
  }

  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws   ServletException, IOException {
    DOBJ dobj= new DOBJ();
    Connection conn=null;
    try
    {

        String jspname="";
        Connector connector = new Connector();
        conn = connector.getConnection("FNDB");
        HashMap Lmap = new HashMap();
        Lmap.put("USER_ID",req.getParameter("USER_ID"));
        Lmap.put("USER_PWD",req.getParameter("USER_PWD"));
        VOBJ vobj = checkId(conn, Lmap);
        dobj.setRetObject("G", vobj);

        if(vobj.getRetcode() == 1)
        {
            jspname ="/login.jsp";
            forward(req,resp, jspname, dobj);
            // id 오류 (존재하지 않는 id
        }
        else if(vobj.getRetcode() == 2)
        {
            jspname ="/login.jsp";
            forward(req,resp, jspname, dobj);
            // pwassword 오류 (존재하지 않는 id
        }
        else
        {
            jspname ="/main.jsp";
            makeSession(req, vobj);
            forward(req,resp, jspname, dobj);
            // 정상처리
        }
    }catch(Exception e)
    {
        e.printStackTrace();
    }finally
    {
        try{
            conn.close();
        }catch(Exception ee){
            ee.printStackTrace();
        }
    }
  }
  private void forward(HttpServletRequest req, HttpServletResponse res, String jspname , DOBJ dobj){
      HttpSession sesn = req.getSession(true);
      ServletContext servletcontext = getServletContext();
      RequestDispatcher requestdispatcher = servletcontext.getRequestDispatcher(jspname);
      req.setAttribute("retobj", dobj);
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
  private void makeSession(HttpServletRequest req, VOBJ _vobj) {
      HttpSession session = req.getSession(true);
      session.setAttribute("G", _vobj);

  }
  private VOBJ checkId(Connection conn, HashMap login) throws Exception{
      String pwd   = login.get("USER_PWD").toString();
      String userid= login.get("USER_ID").toString().trim();
      QExecutor dexe = new QExecutor();
      SQLObject sqlx = this.makeQryLoginTable(userid);

      VOBJ info = dexe.executeQuery(conn, sqlx);
      if(userid.equals("")) {
          info.setRetcode(1);
      }else if(info.getRecordCnt() ==0 ){
          info.setRetcode(1);
      }else if(!info.getRecord().get("USER_PWD").equals(pwd)) {
          info.setRetcode(2);
      }else {
          info.setRetcode(0);
      }
      return info;
  }
  private SQLObject makeQryLoginTable(String userid){
      SQLObject sqlx = new SQLObject();
      String qry="";
      qry +="SELECT  USER_ID,        \n";
      qry +="        USER_PWD      \n";
      qry +="FROM    CARD.COMLOGIN  \n";
      qry +="WHERE   USER_ID=:USER_ID \n";
      sqlx.setSql(qry);
      sqlx.setString("USER_ID", userid);
      return sqlx;
  }
  public void destroy() {
  }


}


