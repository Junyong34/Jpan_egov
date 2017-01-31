
package hpms.base.web;
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
import hpms.base.service.SubPidInfoMgr;
@Controller
public class SubPidInfoMgrController
{
    @Autowired
    private SubPidInfoMgr SubPidInfoMgrService;
    @RequestMapping(value="/20160912111171SubPidInfoMgr_loadpage.do")
    public String loadpage(ModelMap model, DOBJ idobj) throws Exception
    {
        DOBJ odobj = SubPidInfoMgrService.loadpage(idobj);
        model.addAttribute("WIZDOBJ", odobj);
        model.addAttribute("G", odobj.getRetObject("G").getRecordMap());
        model.addAttribute("MESSAGE", odobj.getRetmsg());
        return "/jsp/base/PIDSearch_SubPID";
    }
    @RequestMapping(value="/20160912111171SubPidInfoMgr_SubPIDPopupPage.do")
    public String SubPIDPopupPage(ModelMap model, DOBJ idobj) throws Exception
    {
        DOBJ odobj = SubPidInfoMgrService.SubPIDPopupPage(idobj);
        model.addAttribute("WIZDOBJ", odobj);
        model.addAttribute("G", odobj.getRetObject("G").getRecordMap());
        model.addAttribute("MESSAGE", odobj.getRetmsg());
        return "/jsp/base/Sub_PIDSavePop";
    }
    @RequestMapping(value="/20160912111171SubPidInfoMgr_subpidreslut_page.do")
    public String subpidreslut_page(ModelMap model, DOBJ idobj) throws Exception
    {
        DOBJ odobj = SubPidInfoMgrService.subpidreslut_page(idobj);
        model.addAttribute("WIZDOBJ", odobj);
        model.addAttribute("G", odobj.getRetObject("G").getRecordMap());
        model.addAttribute("MESSAGE", odobj.getRetmsg());
        return "/jsp/base/subPIDConfirm";
    }
    @RequestMapping(value="/20160912111171SubPidInfoMgr_Result_loadPage.do")
    public ModelAndView Result_loadPage(ModelMap model, DOBJ idobj) throws Exception
    {
        ModelAndView mav = new ModelAndView("riaView");
        DOBJ odobj = SubPidInfoMgrService.Result_loadPage(idobj);
        mav.addObject("WIZDOBJ", odobj);
        mav.addObject("SEL1", odobj.getRetObject("SEL1").getRecords());
        mav.addObject("G", odobj.getRetObject("G").getRecordMap());
        mav.addObject("MESSAGE", odobj.getRetmsg());
        return mav;
    }
    @RequestMapping(value="/20160912111171SubPidInfoMgr_subPIDResult.do")
    public String subPIDResult(ModelMap model, DOBJ idobj) throws Exception
    {
        DOBJ odobj = SubPidInfoMgrService.subPIDResult(idobj);
        model.addAttribute("WIZDOBJ", odobj);
        model.addAttribute("SEL2", odobj.getRetObject("SEL2").getRecordMap());
        model.addAttribute("G", odobj.getRetObject("G").getRecordMap());
        model.addAttribute("MESSAGE", odobj.getRetmsg());
        return "/jsp/base/subPIDConfirm";
    }
    @RequestMapping(value="/20160912111171SubPidInfoMgr_sub_PID_NumberSearch.do")
    public ModelAndView sub_PID_NumberSearch(ModelMap model, DOBJ idobj) throws Exception
    {
        ModelAndView mav = new ModelAndView("riaView");
        DOBJ odobj = SubPidInfoMgrService.sub_PID_NumberSearch(idobj);
        mav.addObject("WIZDOBJ", odobj);
        mav.addObject("SEL2", odobj.getRetObject("SEL2").getRecords());
        mav.addObject("G", odobj.getRetObject("G").getRecordMap());
        mav.addObject("MESSAGE", odobj.getRetmsg());
        return mav;
    }
}