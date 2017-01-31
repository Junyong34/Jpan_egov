package org;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Hashtable;

import org.Decode30;
/**
* 샘플 서블렛필터
*
*/
public class MyLoginFilter implements Filter {
// 로그 오브젝트
private static Log log = LogFactory.getLog(MyLoginFilter.class);
/**
*필터링처리를 실시
*
* 세션값을 셋팅
*
*/
public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
ServletException {
// 【WebLogic의 경우】
// 1개의 리퀘스트에 대해서 여러번 필터가 실행되어지기 때문에
// 2회째 이후의 필터 실행을 억제할 필요가 있다.
// 이후 코멘트를 빼 주세요.
/*
String filtedFlag = (String) request.getAttribute("MY_LOGIN_FILTER");
if (StringUtils.equals(filtedFlag, "__FILTERED__")) {
chain.doFilter(request, response);
return;
} else {
request.setAttribute("MY_LOGIN_FILTER", "__FILTERED__");
}
*/
HttpServletRequest req = (HttpServletRequest) request;
HttpServletResponse res = (HttpServletResponse) response;
String reqURL = req.getRequestURL().toString();
if (log.isDebugEnabled())
log.debug("MyLoginFilter:" + reqURL);
// 필터링 대샹을 한정하고 있습니다.
//필터는 전 리퀘스트에 대해서 동작하기 때문에
//필터링 대상을 추출합니다.
if (reqURL.endsWith(".jsp") || reqURL.endsWith(".go") || reqURL.endsWith("/")) {

//유저 독자의 로그인 화면
String myLoginPage = req.getContextPath() + "/jsp/menu/LoginMgrIndex.jsp"; 
String nextPage = req.getContextPath() + "/";

//쿠키의 유무를 조사한다.
//String baseCookie = "";
Hashtable decCookie = new Hashtable();
Cookie cookie[] = req.getCookies();

if(cookie != null) {
	String baseCookie = "";
	for (int i = 0 ; i < cookie.length ; i++){
    	if (cookie[i].getName().equals("sys30")){  // 이름 비교
        	baseCookie = cookie[i].getValue();
        	break;
     	}
 	}
	if("".equals(baseCookie)) {
		//쿠키가 없기 때문에 EdoNavi화면 표시
		if (!StringUtils.contains(req.getRequestURI(), myLoginPage)) {
			res.sendRedirect(myLoginPage);   // 로그인 페이지로 돌린다.
			return;
		}
	} else {
		//쿠키가 있을때에 Cookie해독 처리
		Decode30 ed = new Decode30();
		decCookie = ed.decodesys30hash(baseCookie);
		String userid = (String)decCookie.get("USERID");

   //쿠키의 내부를 덤프하는 문장 by 시미즈상@2016.09.09
   //선두에서 import로 이하를 설정
		//import java.util.*;
   //아래는 코멘트 아웃(중복하기 때문)
		//import java.util.HashSet;
		//import java.util.Set;

   //Iterator ite = decCookie.entrySet().iterator();
   //while(ite.hasNext()){
   //   Map.Entry entry=(Map.Entry)ite.next();
   //   System.out.println(entry.getKey()+","+entry.getValue());
   //}


		//try {
		//세션을 무효화 한다.
		//session.invalidate();
        //} catch (IllegalStateException e) {
		//이미 무효화 된 세션의 경우 무시한다.
        //}

       // Session 정보 호출 컨트롤러 
		String url = "redirect:/index.do";
		
		
		//3개의 세션값을 설정한다.
		//HttpSession sess = req.getSession(true);
		//----- 위에는 실제의 업무요건에 응해서 코딩해 주세요. -----
		//----- 아래 부터가 Web Performer에 필요한 세션정보가 됩니다.-----
		//Web Performer에서 최소한 필요한 정보를 셋트한다.
		//（필수）로그인되었는지 어떤지의 플러그
		//sess.setAttribute(AppContext.KEY_ISLOGGEDIN, new  Boolean(true));
		//（필수）로그인 유저ID
		//sess.setAttribute(AppContext.KEY_USER, userid);
		//（필수）로그인 유저의 롤(복수가능)
		//※롤은 Set오브젝트에 셋트해 주세요.
		//롤은 사용하지 않는 경우에도 빈 Set오브젝트를 세트해 주세요.
		//sess.setAttribute(AppContext.KEY_ROLES, getRolesFromUserID(userid));
		//초기 입출력을 표시한다.
		//res.sendRedirect(nextPage);
	}
} else {
	//쿠키가 없기 때문에 EdoNav화면 표시
	if (!StringUtils.contains(req.getRequestURI(), myLoginPage)) {
		res.sendRedirect(myLoginPage);
		return;
	}
}
}

//다음의 필터에 제어를 넘깁니다.
chain.doFilter(request, response);
}
/**
* Role을 해결하는 샘플 메소드
*
* @param userid 유저ID
* @return 롤 셋트
*/
private Set getRolesFromUserID(String userid) {
Set roles = new HashSet();
// 예를 들어, userid로 DB등에 물어보고
// Web Performer에서 사용하는 롤 명을 만들어내는 커스텀 코드를 기술 합니다.
// 이 열에서는 롤 명은 설정되어 있지 않습니다.
return roles;
}
/**
* 필터가 서비스 개시 상태로 될 때에는 Web 콘테너에의해서 호출됩니다.
*/
public void init(FilterConfig filterConfig) throws ServletException {
// 필터의 초기화 처리를 합니다.
}
/**
* 필터가 서비스상태를 끝낼때에, Web콘테너에 의해 호출되어 집니다.
*/
public void destroy() {
// 필터의 종료처리를 합니다.
}
}
