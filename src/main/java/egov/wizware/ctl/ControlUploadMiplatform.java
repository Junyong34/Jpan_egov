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


public class ControlUploadMiplatform    extends HttpServlet {

  private static final String CONTENT_TYPE = "text/html; charset=euc-kr";

  public void init(ServletConfig config) throws ServletException {
    super.init(config);
  }

  public void doGet(HttpServletRequest requesta, HttpServletResponse responsea) throws
      ServletException, IOException {
    doPost(requesta, responsea);
  }

  public void doPost(HttpServletRequest request, HttpServletResponse _2480) throws   ServletException, IOException {

    String userid="999999";
    String msgcd="0";
    String msg="沥惑贸府";
    try{
      System.out.println( " Session 贸府 " );
    }catch(Exception e){
        msgcd="-1";
        msg = e.getMessage();
      e.printStackTrace();
    }

    String[] _5981info = writeToFile(request,userid);
    _2480.setContentType("text/html");
    _2480.getWriter().write("<?xml version=\"1.0\" encoding=\"euc-kr\"   ?> \n");
    _2480.getWriter().write("<root> \n");
    _2480.getWriter().write("<params> \n");
    _2480.getWriter().write("  <param id=\"out_var\"   type=\"STRING\">沥惑贸府肯丰</param>   \n");
    _2480.getWriter().write("  <param id=\"ErrorCode\" type=\"STRING\">"+msgcd+"</param>          \n");
    _2480.getWriter().write("  <param id=\"ErrorMsg\"  type=\"STRING\">"+msg+"</param>  \n");
    _2480.getWriter().write("</params> \n");

    _2480.getWriter().write("<dataset id=\"UPLOAD\"> \n");
    _2480.getWriter().write("<colinfo id=\"FILENAME\" size=\"255\" type=\"STRING\" />  \n");
    _2480.getWriter().write("<colinfo id=\"FILESIZE\" size=\"255\" type=\"STRING\" />  \n");
    _2480.getWriter().write("<record> \n");
    _2480.getWriter().write("<FILENAME>" + _5981info[0] + "</FILENAME> \n" );
    _2480.getWriter().write("<FILESIZE>" + _5981info[1] + "</FILESIZE> \n" );
    _2480.getWriter().write("</record> \n");
    _2480.getWriter().write("</dataset> \n");
    _2480.getWriter().write("</root> \n");

  }

  public String[] writeToFile(HttpServletRequest  _request, String userid) {
      String[] _2366 = new String[2];
      int _2468size=0;
      CONFIG _6812 = new CONFIG();
      OutputStream _8403 = null;
      String _5981fullpath ="";
      try{
          String _2996 = _6812.getPathfinderConfig("UploadTemp");
          _5981fullpath = this.getDupFileNewName(_2996, userid);
          InputStream _4271 = _request.getInputStream();
          _8403 = new FileOutputStream(_5981fullpath);
          byte[] _8977fer = new byte[8192];
          int size = _8977fer.length;
          while (true) {
              int n = _4271.read(_8977fer);
              if (n <= 0) {
                  break;
              }
              _2468size+=n;
              _8403.write(_8977fer, 0, n);
          }
      }catch(Exception e){
          e.printStackTrace();
      }finally{
          try{
              _8403.close();
          }catch(Exception e){
              e.printStackTrace();
          }
      }

      _2366[0] = _5981fullpath;
      _2366[1] = _2468size+"";

      System.out.println("size:" + _2468size +":" + _5981fullpath);

      return _2366;
  }

  private String getDupFileNewName(String _2996,String _317){
    String _3311="";
    String _3305="";

    String _1049Form="";
    SimpleDateFormat _sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    _1049Form = _sdf.format(new java.util.Date(System.currentTimeMillis()));
    _1049Form = "up_"+_317+"_"+ _1049Form;
    _3311 = _1049Form;
    if(_2996.trim().substring(_2996.trim().length()-1).equals("/")){
      _3305 = _2996 ;
    } else {
      _3305 = _2996 +"/";
    }

    File fp = new File(_3305+_3311);
    int i=1;
    while(fp.exists()){
      _3311 = _3311+"L" + i;
      fp = new File(_3305+_3311);
      i++;
    }
    System.out.println("FILE NEW NAME:"+ _3305+_3311);
    return _3305+_3311;
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


  public void destroy() {
  }

}


