package egov.wizware.ctl;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.HashMap;
import egov.wizware.com.*;

public class ViewInfo {

  private static final String propPath = "C:\\fmtot\\CONTROL\\";
  ServletContext servletcontext = null;

  public ViewInfo(ServletContext servletcontext ) {
    this.servletcontext = servletcontext;
  }

  public RequestDispatcher getRequestDispatcher(HttpServletRequest req,
                                                DOBJ _6185, HashMap ctl) {

/*
    ServletContext sconfig = null;
    HttpSession sess = req.getSession(true);
    sconfig = sess.getServletContext();
    ServletContext servletcontext = sconfig;
*/

    String retview = "";
    switch (_6185.getRtncode()) {
      case 0:
      default:
        retview = ctl.get("viewinfo").toString();
    }
    System.out.println(retview +":;;;;;;" + ctl.get("viewinfo").toString());
    RequestDispatcher rdp = servletcontext.getRequestDispatcher(retview);
    return rdp;
  }

  public RequestDispatcher getRequestDispatcher(HttpServletRequest _req, DOBJ _6185, String retview) {

 /*
     ServletContext sconfig = null;
     HttpSession sess = req.getSession(true);
     sconfig = sess.getServletContext();
     ServletContext servletcontext = sconfig;
     System.out.println("RETURN VIEW:" +retview);
 */

     RequestDispatcher rdp = servletcontext.getRequestDispatcher(retview);
     return rdp;
  }

  public String getRetMsg(DOBJ _6185) {

    String _2450 = "";

    switch (_6185.getRtncode()) {
      case 0: //--   data 없음
          _2450 = "정상처리 되었습니다.";
        break;

      case -1: //--   data 없음
          _2450 = "Application 오류가 발생 했습니다.(전산담당자 연락이 필요합니다)";
        break;

      case 200: //--   통신업무  MESSAGE 코드 TABLE

        try {
          java.io.File _2978 = new java.io.File(propPath+"CM.MSG");
          if(!_2978.exists()){
              String _unixPath = "/TOTWEB/CONTROL/";
              _2978 = new java.io.File(_unixPath+"CM.MSG");
          }
          java.io.FileInputStream _4211 = new java.io.FileInputStream(_2978);
          java.io.DataInputStream _6569 = new java.io.DataInputStream(_4211);
          java.util.Properties _2777 = new java.util.Properties();
          _2777.load(_6569);
          String _2768 = _2777.getProperty(_6185.getDetailCode() + "");
          if (_2768 != null) {
            _2768 = new String(_2768.getBytes("8859_1"), "KSC5601");
          }
          else {
            _2768 = "프로그램 처리 오류가 발생했습니다(정의가 되지 않은 오류코드("+_6185.getDetailCode()+")";
          }

          if (_2768.indexOf("@@@@@") < 0) {
            _2450 = _2768;
          }
          else {
            _2450 = this.replace(_2768, _6185.getDetailMessage());
          }
        }
        catch (Exception e) {
          e.printStackTrace();
        }
        break;


      default:
        _2450 = "사용자 Message처리 코드 오류입니다.(전산실에 연락하세요)";
        break;

    }

    return _2450;
  }

  public String replace(String _1175, String _3290) {

    String _3143 = "@@@@@";

    if (_1175 == null)
      return null;

    StringBuffer dest = new StringBuffer("");

    try {
      int len = _3143.length();
      int srclen = _1175.length();
      int pos = 0;
      int oldpos = 0;

      while ( (pos = _1175.indexOf(_3143, oldpos)) >= 0) {
        dest.append(_1175.substring(oldpos, pos));
        dest.append(_3290);
        oldpos = pos + len;
      }

      if (oldpos < srclen)
        dest.append(_1175.substring(oldpos, srclen));

    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return dest.toString();
  }

}
