package egov.wizware.ria;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.util.WebUtils;

import egov.wizware.com.DOBJ;
import egov.wizware.com.VOBJ;


public class CustomSimpleMappingExceptionResolver extends
		SimpleMappingExceptionResolver {
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		
		
		String str = request.getHeader("Accept").toLowerCase();
		HttpSession _sesn = request.getSession(true);

		// json 방식일땐 ajax로 판단 함.	
		//if (str.indexOf("json") > -1 && _sesn.getAttribute("G") !=null) {
		if (str.indexOf("json") > -1 ) {
			String viewName = determineViewName(ex, request);
			
			ModelAndView mv = new ModelAndView(viewName);
			String message =ex.getMessage().toString();
			String Code = "";
			
			if(message.indexOf("ORA-00001") != -1){
				message ="Same data is being uploaded." ;
				Code="1023";
			}
			
			mv.addObject("ajax", message);
			mv.addObject("CODE", Code);
			//System.out.println(viewName + " =======================================" + str);
			return mv;
		} else {
			
			//System.out.println(handler.toString() +  " ## " + ex.getMessage().toString() + " ## " + response.toString() +  " ##  " + request.getRequestURI());
			return super.doResolveException(request, response, handler, ex);
		}
	}
}
