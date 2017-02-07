package hpms.common.web;
import java.util.*;
import java.io.*;

import javax.servlet.http.*;

import egov.wizware.com.*;

import javax.annotation.Resource;

import org.Decode30;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import org.springframework.web.servlet.ModelAndView;

import hpms.common.service.CookieLoginInfo;
@Controller
public class CookieLoginInfoController {
    @Autowired
    private CookieLoginInfo CookieLoginInfoService;

   
    @RequestMapping(value = "/2016041114474CookieLoginInfo_CookieMgr.do")
    public String CookieMgr(ModelMap model, DOBJ idobj, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //System.out.println("========== Start ==========");
        //쿠기값 셋팅해서 VOBJ에 담는다.
        String CookieKey = "sys30"; // 쿠키명을 넣는다.
        VOBJ Cookie_vobj = null;

       
        
        //로그인 화면
        String myLoginPage = request.getContextPath() + "/jsp/menu/LoginMgrIndex.jsp";
        String nextPage = request.getContextPath() + "/";

        //쿠키의 유무를 조사한다.
        Cookie cookie[] = request.getCookies();
   
        VOBJ vobj = new VOBJ();
        //System.out.println("========== Setp 1 ========== " + "cookie status :" + cookie);
        // 쿠키값이 존재 할 경우
        if (cookie != null) {
           // System.out.println("========== Setp 2 ========== " + "cookie not null ");
            String baseCookie = "";

            Cookie_vobj = getCookId(request, CookieKey); // 쿠키값 뽑아내기 
            Cookie_vobj.setName("COOK");

            idobj.setRetObject(Cookie_vobj);


            if ("".equals(idobj.getRetObject("COOK").getRecord().get("USER_ID"))) {
               // System.out.println("========== Setp Cookie is not exits========== ");
                //쿠키가 없기 때문에 로그인 화면 표시
                if (!StringUtils.contains(request.getRequestURI(), myLoginPage)) {
                    return "forward:/2016041114474LoginFail.do"; // 로그인 페이지로 돌린다.

                }
            } else {
               // System.out.println("========== Setp Seesion add========== ");
                try{
                	
                	
                
                // 세션 정보 생성 
                DOBJ odobj = CookieLoginInfoService.CookieMgr(idobj);
                model.addAttribute("WIZDOBJ", odobj);
                model.addAttribute("SEL27", odobj.getRetObject("SEL27").getRecords());
                model.addAttribute("SEL31", odobj.getRetObject("SEL31").getRecords());
                model.addAttribute("G", odobj.getRetObject("G").getRecordMap());
                model.addAttribute("MESSAGE", odobj.getRetmsg());

                if (odobj.getRetObject("SEL3").getRecord().getInt("CNT") == 0) {
                   // System.out.println("========== id is not exits========== US0M001T_V01");
                    //아이디가 존재하지않음
                    return "forward:/2016041114474LoginIDisExist.do"; // 로그인 페이지로 돌린다.

                } else {

	                    if (odobj.getRetObject("SEL11").getRecord().getInt("CNT") == 0) {
	                        //부서 권한 코드가 존재 하지 않음.  HP1AU01T  , US0M001T_V01 체크
	                        // 세션생성 일반 권한
	                    	if (odobj.getRetObject("SEL12").getRecord().getInt("CNT") == 0) {
	                    		vobj = odobj.getRetObject("SEL27");
	                          //  System.out.println("========== Setp 11========== " + "  SEL17 set ");
	                    		
	                    	}else{
	                    		
	                    		vobj = odobj.getRetObject("SEL31");
	                    		 // System.out.println("========== Setp 11========== " + "  SEL31 set ");
	                    	}
                        
                    } else {

                        // 세션생성
                        vobj = odobj.getRetObject("SEL17");
                      //  System.out.println("========== Setp 12========== " + "  SEL17 set ");

                    }

                }

                ArrayList _clist = vobj.getColumnNames();
                int LoginColCnt = vobj.getColumnCnt();
                HashMap loginInfo = new HashMap();
                HttpSession session = request.getSession(true);
               // System.out.println("========== Setp 13========== " + "  cookie delete ");
                DeleteCook(request, response); // 쿠키 데이타 사용 끝나면 쿠기 삭제 처리 

                if (_clist.size() == 0) return "forward:/2016041114474Main.do";

                //세션시간
                session.setMaxInactiveInterval(360000); //세션 유지 시간 30분
               // System.out.println("========== Setp 14========== " + "  session time 1800sec ");
                for (int i = 0; i < _clist.size(); i++) {
                    loginInfo.put(_clist.get(i), vobj.getRecord().get(_clist.get(i)));
                }

                session.setAttribute("G", loginInfo);
               // System.out.println("========== Setp 15========== " + "  Session G set ");
                
                
            }catch (Exception e) {
    			e.printStackTrace();
    		}
                
                
                
                return "forward:/2016041114474Main.do";
            }
        } else {
            //쿠키가 없기 때문에 로그인 화면 이동
            System.out.println("========== Cookie null ========== ");
            if (!StringUtils.contains(request.getRequestURI(), myLoginPage)) {
                return "forward:/2016041114474LoginFail.do";
            }
        }


        return "forward:/2016041114474loggout.do";

    }


    @RequestMapping({"/2016041114474LoginFail.do"})
    public String LoginFail(ModelMap model, DOBJ idobj, HttpServletRequest request)
    throws Exception {
        return "/jsp/menu/LoginFail";

    }

    @RequestMapping({"/2016041114474LoginIDisExist.do"})
    public String LoginIDisExist(ModelMap model, DOBJ idobj, HttpServletRequest request)
    throws Exception {
        return "/jsp/menu/LoginIDisExist";

    }

    @RequestMapping({"/2016041114474Login_ORG_CD_isExist.do" })
    public String Login_ORG_CD_isExist(ModelMap model, DOBJ idobj, HttpServletRequest request)
    throws Exception {
        return "/jsp/menu/Login_ORG_CD_isExist";

    }


    public VOBJ getCookId(HttpServletRequest request, String CookieKey) {
        VOBJ Cookievobj = new VOBJ();
        HashMap hmap = new HashMap();
        String userid = "";
        String cookId = "";
        Cookie[] c = request.getCookies();

        String cookieName = CookieKey; // 쿠키 아이디명 받기
        //System.out.println("========== Setp 3 ========== " + "cookie Name: " + cookieName);
        int cookieFind = -1;

        if (c != null) {
            for (int i = 0; i < c.length; i++) {
                //System.out.println("쿠키 데이타 " + c[i].getName() );
                //System.out.println("========== Setp 4========== " + "cookie Data List  " + c[i].getName());
                if (c[i].getName().equals(cookieName)) {
                //    System.out.println("========== Setp 5========== " + " cookieName--> sys30 :" + i);
                    cookieFind = i;
                    break;
                }
            }
            if (cookieFind != -1) {
            	
            	try{
            		
            		 //System.out.println("========== Setp 6========== " + "  sys30  exist ");
                     cookId = c[cookieFind].getValue(); // 이부분에서 디코더로 암호화 해제  VOBJ 담는다.
                    // System.out.println("========== Setp 7========== " + "  cookID (sys30) encryption : " + cookId);
                     //쿠키가 있을때에 Cookie 복호화 처리
                    // System.out.println("========== Setp 8========== " + "  Decode30 start ");
                     Hashtable decCookie = new Hashtable();
                    // String decCookie = "";
                     Decode30 ed = new Decode30();
                     decCookie = ed.decodesys30hash(cookId);
                     //decCookie = ed.decodesys30str(cookId); 
                     //System.out.println("========== Setp 9========== " + "  Decode30 stop ");
                     userid = (String) decCookie.get("USERID");
                   //  userid = decCookie;
                     System.out.println("========== Setp 10========== " + "  USER_ID " + userid);
            		
            	}catch (Exception e) {
					e.printStackTrace();
				}
               

            }

            hmap.put("USER_ID", userid);

            Cookievobj.addRecord(hmap);

        } else {
           // System.out.println("========== Setp Cookie null========== " + "  Not cookie date ");
            hmap.put("null", "");
            Cookievobj.addRecord(hmap);

        }


        return Cookievobj;
    }


    public String setCookId(HttpServletRequest request, HttpServletResponse response) {
        String cookId = request.getParameter("S_USER_ID");
        cookId = cookId.substring(4);
        Cookie cookie = new Cookie("USER_ID", cookId);
        cookie.setMaxAge(-1); // 쿠키 유지 기간 - 1일
        cookie.setPath("/"); // 모든 경로에서 접근 가능하도록 
        response.addCookie(cookie); // 쿠키저장
        return cookId;
    }

    public void DeleteCook(HttpServletRequest request, HttpServletResponse response) {

  
        Cookie[] cookies = request.getCookies(); // 요청정보로부터 쿠키를 가져온다.
        for (int i = 0; i < cookies.length; i++) {
        	System.out.println("Cookie Info java (01) "+ "path:" +cookies[i].getPath() +" Domain :" +cookies[i].getDomain() + " Name: " + cookies[i].getName() );
            cookies[i].setMaxAge(0); // 특정 쿠키를 더 이상 사용하지 못하게 하기 위해서는  
            cookies[i].setPath("/"); // 모든 경로에서 접근 가능하도록 
            // 쿠키의 유효시간을 만료시킨다.
            response.addCookie(cookies[i]); // 해당 쿠키를 응답에 추가(수정)한다.
            System.out.println("Cookie Info java (2) "+ "path:" + cookies[i].getPath() +" Domain :" +cookies[i].getDomain() + " Name: " + cookies[i].getName());
        }

    }
}