package hpms.common.web;

import java.util.*;
import java.io.*;

import javax.servlet.http.*;

import egov.wizware.com.*;

import javax.annotation.Resource;

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

import hpms.common.service.LoginMgrCheck;

@Controller
public class LoginMgrCheckController {
	@Autowired
	private LoginMgrCheck LoginMgrCheckService;

	@RequestMapping(value = "/2016041114474LoginMgrCheck_System_Developer_Check.do")
	public ModelAndView System_Developer_Check(ModelMap model, DOBJ idobj,
			HttpServletRequest request) throws Exception {

		ModelAndView mav = new ModelAndView("riaView");

		DOBJ odobj = LoginMgrCheckService
				.System_Developer_Check(idobj, request);
		mav.addObject("WIZDOBJ", odobj);
		mav.addObject("SEL3", odobj.getRetObject("SEL3").getRecords());
		mav.addObject("G", odobj.getRetObject("G").getRecordMap());
		mav.addObject("MESSAGE", odobj.getRetmsg());
		return mav;
	}

	@RequestMapping(value = "/2016041114474LoginMgrCheck_wizLoginSession.do")
	public ModelAndView wizLoginSession(ModelMap model, DOBJ idobj,
			HttpServletRequest request) throws Exception {

		ModelAndView mav = new ModelAndView("riaView");

		DOBJ odobj = LoginMgrCheckService.wizLoginSession(idobj, request);
		mav.addObject("WIZDOBJ", odobj);
		mav.addObject("SEL3", odobj.getRetObject("SEL3").getRecords());
		mav.addObject("SEL1", odobj.getRetObject("SEL1").getRecords());
		mav.addObject("G", odobj.getRetObject("G").getRecordMap());
		mav.addObject("MESSAGE", odobj.getRetmsg());
		return mav;
	}

	@RequestMapping(value = "/2016041114474LoginMgrCheck_SessionLogInfo.do")
	public String SessionLogInfo(ModelMap model, DOBJ idobj,
			HttpServletRequest request) throws Exception {

		DOBJ odobj = LoginMgrCheckService.SessionLogInfo(idobj, request);

		model.addAttribute("WIZDOBJ", odobj);
		model.addAttribute("SEL1", odobj.getRetObject("SEL1").getRecords());
		model.addAttribute("G", odobj.getRetObject("G").getRecordMap());
		model.addAttribute("MESSAGE", odobj.getRetmsg());

		VOBJ vobj = new VOBJ();

		vobj = odobj.getRetObject("SEL1");
		ArrayList _clist = vobj.getColumnNames();
		int LoginColCnt = vobj.getColumnCnt();

		HashMap loginInfo = new HashMap();
		HttpSession session = request.getSession(true);

		if (_clist.size() == 0)
			return "forward:/2016041114474Main.do";

		session.setMaxInactiveInterval(360000);

		for (int i = 0; i < _clist.size(); i++) {
			loginInfo.put(_clist.get(i), vobj.getRecord().get(_clist.get(i)));
		}
		// #########세션 생성
		session.setAttribute("G", loginInfo);

		return "forward:/2016041114474Main.do";

	}

	@RequestMapping({ "/2016041114474Main.do" })
	public String MainPage(ModelMap model, DOBJ idobj,
			HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession(true);
		HashMap hmap = null;
		hmap = (HashMap) session.getAttribute("G");

		if ((session == null) || session.getAttribute("G") == null
				|| hmap.size() == 0) {
			System.out.println(" NOT SESSION.");
			// 로그인페이지로 돌린다
			return "redirect:/";

		} else {

			model.addAttribute("G", hmap);
			// 세션정보가 살아있으면 메인페이지로 이동
			return "/jsp/menu/Main_menu";

		}

	}

	@RequestMapping({ "/2016041114474loggout.do" })
	public String LogoutPage(ModelMap model, DOBJ idobj,
			HttpServletRequest request) throws Exception {

		return "/jsp/menu/LogOut";

	}
}