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


public class ControlDownloadMiplatform    extends HttpServlet {

  private static final String CONTENT_TYPE = "text/html; charset=euc-kr";

  public void init(ServletConfig config) throws ServletException {
    super.init(config);
  }

  public void doGet(HttpServletRequest _2486, HttpServletResponse _2477) throws
      ServletException, IOException {
    doPost(_2486, _2477);
  }

  public void doPost(HttpServletRequest _request, HttpServletResponse response) throws   ServletException, IOException {

    String _317="999999";
    String _3437cd="0";
    String _3437="沥惑贸府";

    try{
      System.out.println( " Session 贸府 " );
    }catch(Exception e){
        _3437cd="-1";
        _3437 = e.getMessage();
        e.printStackTrace();
    }

    Xinternet _xintinput = new Xinternet();
    _xintinput.downLoadObject1000 (_request, response);

  }

  public String[] writeToFile(HttpServletRequest  _request, String _317) {
      String[] _2366 = new String[2];
      int _2468size=0;
      CONFIG _6812 = new CONFIG();
      OutputStream _8403 = null;
      String _5981fullpath ="";
      try{
          String _2996 = _6812.getPathfinderConfig("UploadTemp");
          _5981fullpath = this.getDupFileNewName(_2996, _317);
          InputStream _4271 = _request.getInputStream();
          _8403 = new FileOutputStream(_5981fullpath);
          byte[] _8977fer = new byte[8192];
          int _1349 = _8977fer.length;
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

    File _5777 = new File(_3305+_3311);
    int i=1;
    while(_5777.exists()){
      _3311 = _3311+"L" + i;
      _5777 = new File(_3305+_3311);
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


