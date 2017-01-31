<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="egov.wizware.jsp.*"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="context" value="${pageContext.request.contextPath}"/>
<%
     session.invalidate();
    // cookie.setMaxAge(-1); 
         Cookie[] cookies = request.getCookies();            // 요청정보로부터 쿠키를 가져온다.
    	for(int i = 0 ; i<cookies.length; i++){   
    	    System.out.println("Logout infO Cookies 0) "+ "path:" +cookies[i].getPath() +" Domain :" +cookies[i].getDomain() + " Name: " + cookies[i].getName() );              
	    	cookies[i].setMaxAge(0);                        // 특정 쿠키를 더 이상 사용하지 못하게 하기 위해서는 쿠키의 유효시간을 만료시킨다.
			cookies[i].setPath("/");
			response.addCookie(cookies[i]);    // 해당 쿠키를 응답에 추가(수정)한다.
	    	System.out.println("  Logout infO Cookies 1 " + "path:" +cookies[i].getPath() +" Domain :" +cookies[i].getDomain() + " Name: " + cookies[i].getName() );    
	    	        
	    
    	} 
  
%> 
<script type="text/javascript">    

    	top.location.href="${context}/jsp/menu/LoginMgrIndex.jsp";
   
</script>