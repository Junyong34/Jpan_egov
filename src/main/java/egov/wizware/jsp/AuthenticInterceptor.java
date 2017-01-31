package egov.wizware.jsp;

import javax.servlet.http.*;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;
import egov.wizware.com.DOBJ;
import egov.wizware.com.VOBJ;

public class AuthenticInterceptor extends HandlerInterceptorAdapter {
    /**
     * 세션에 계정정보(Account)가 있는지 여부로 인증 여부를 체크한다.
     * 계정정보(Account)가 없다면, 로그인 페이지로 이동한다.
     */
    @Override
    public boolean preHandle(HttpServletRequest request,  HttpServletResponse response, Object handler) throws Exception
    {

        if(true)
        {
            return true;
        }

        //----------------------[ login 처리 부일때는 Session Check 기능을 skip 한다. ]----------------------
        String url = request.getRequestURI();
        if(url != null && url.indexOf("Login_userinfo.do") != -1)
        {
            //url = url.substring(url.lastIndexOf("/"));
            return true;
        }
        //-------------------------------------------------------------[login url check End]--------------
        HttpSession _sesn = request.getSession(true);
        if  ((_sesn == null) || (_sesn.getAttribute("G") == null))
        {
           System.out.println("AuthenticInterceptor preHandle URL IS SESSION NULL" );
           ModelAndView modelAndView = new ModelAndView("redirect:/login.do");
           throw new ModelAndViewDefiningException(modelAndView);
        }

        VOBJ account = (VOBJ) request.getAttribute("G");
        System.out.println("AuthenticInterceptor preHandle URL 1:" + request.getRequestURI() +":" + account);
        return true;
    }
}

