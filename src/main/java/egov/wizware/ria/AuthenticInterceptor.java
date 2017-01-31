package egov.wizware.ria;

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
     * ���ǿ� ��������(Account)�� �ִ��� ���η� ���� ���θ� üũ�Ѵ�.
     * ��������(Account)�� ��ٸ�, �α��� �������� �̵��Ѵ�.
     */
    @Override
    public boolean preHandle(HttpServletRequest request,  HttpServletResponse response, Object handler) throws Exception
    {
    	
    	//ajax 세션 끊어질때 로그인페이지 돌리기
    	
    	
    	String reqUrl = request.getRequestURL().toString();
      
    	/*if(reqUrl.equals("2016041114474")){
    		System.out.println( " @@@@@@@@@@@@@@@@@@@@@ ");
    	return true;
    	}*/
       /* if(true)
        {
            return true;
        }*/
    
        //----------------------[ login ó�� ���϶��� Session Check ����� skip �Ѵ�. ]----------------------
        String url = request.getRequestURI();
        /*if(url != null && url.indexOf("LoginMgr_loadPage") != -1)
        {
            //url = url.substring(url.lastIndexOf("/"));
            return true;
        }*/
        //-------------------------------------------------------------[login url check End]--------------
       
        HttpSession _sesn = request.getSession(true);
        String AjAXCall =  (String)request.getHeader("AJAX");
        if(AjAXCall != null){
        	// System.out.println(  AjAXCall + " @@@@@@@@@@@@@@@@" +(_sesn.getAttribute("G")));
             if( AjAXCall.equals("true")  && (_sesn.getAttribute("G") == null) ){
             	
       		  response.sendError(500);
       	    }
        	
        }

      
        if  ((_sesn == null) || (_sesn.getAttribute("G") == null))
        {
        	
           System.out.println("AuthenticInterceptor preHandle URL IS SESSION NULL !!" );
           //ModelAndView modelAndView = new ModelAndView("redirect:/"); 
           ModelAndView modelAndView = new ModelAndView("redirect:/jsp/menu/LogOut.jsp"); 
           throw new ModelAndViewDefiningException(modelAndView);
        }

        VOBJ account = (VOBJ) request.getAttribute("G");
        System.out.println("AuthenticInterceptor preHandle URL 1:" + request.getRequestURI() +":" + account);
        return true;
    }
}

