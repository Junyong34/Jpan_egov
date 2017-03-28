
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
import hpms.base.service.PidInfoMgr;
@Controller
public class PidInfoMgrController
{
    @Autowired
    private PidInfoMgr PidInfoMgrService;
    @RequestMapping(value="/2016082417883PidInfoMgr_loadpage.do")
    public String loadpage(ModelMap model, DOBJ idobj) throws Exception
    {
        DOBJ odobj = PidInfoMgrService.loadpage(idobj);
        model.addAttribute("WIZDOBJ", odobj);
        model.addAttribute("G", odobj.getRetObject("G").getRecordMap());
        model.addAttribute("MESSAGE", odobj.getRetmsg());
        return "/jsp/base/PIDSave";
    }
    @RequestMapping(value="/2016082417883PidInfoMgr_Result_loadPage.do")
    public ModelAndView Result_loadPage(ModelMap model, DOBJ idobj) throws Exception
    {
        ModelAndView mav = new ModelAndView("riaView");
        DOBJ odobj = PidInfoMgrService.Result_loadPage(idobj);
        mav.addObject("WIZDOBJ", odobj);
        mav.addObject("SEL5", odobj.getRetObject("SEL5").getRecords());
        mav.addObject("G", odobj.getRetObject("G").getRecordMap());
        mav.addObject("MESSAGE", odobj.getRetmsg());
        return mav;
    }
    @RequestMapping(value="/2016082417883PidInfoMgr_MasterComboList.do")
    public ModelAndView MasterComboList(ModelMap model, DOBJ idobj) throws Exception
    {
        ModelAndView mav = new ModelAndView("riaView");
        DOBJ odobj = PidInfoMgrService.MasterComboList(idobj);
        mav.addObject("WIZDOBJ", odobj);
        mav.addObject("SEL2", odobj.getRetObject("SEL2").getRecords());
        mav.addObject("G", odobj.getRetObject("G").getRecordMap());
        mav.addObject("MESSAGE", odobj.getRetmsg());
        return mav;
    }
    @RequestMapping(value="/2016082417883PidInfoMgr_CompletePage.do")
    public String CompletePage(ModelMap model, DOBJ idobj) throws Exception
    {
        DOBJ odobj = PidInfoMgrService.CompletePage(idobj);
        model.addAttribute("WIZDOBJ", odobj);
        model.addAttribute("SEL4", odobj.getRetObject("SEL4").getRecordMap());
        model.addAttribute("SEL7", odobj.getRetObject("SEL7").getRecordMap());
        model.addAttribute("G", odobj.getRetObject("G").getRecordMap());
        model.addAttribute("MESSAGE", odobj.getRetmsg());
        return "/jsp/base/PIDConfirm";
    }
}