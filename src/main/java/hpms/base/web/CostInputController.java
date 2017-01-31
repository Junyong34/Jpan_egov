
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
import hpms.base.service.CostInput;
@Controller
public class CostInputController
{
    @Autowired
    private CostInput CostInputService;
    @RequestMapping(value="/20160901101142CostInput_ServiceID00.do")
    public ModelAndView ServiceID00(ModelMap model, DOBJ idobj) throws Exception
    {
        ModelAndView mav = new ModelAndView("riaView");
        DOBJ odobj = CostInputService.ServiceID00(idobj);
        mav.addObject("WIZDOBJ", odobj);
        mav.addObject("SEL33", odobj.getRetObject("SEL33").getRecords());
        mav.addObject("S", odobj.getRetObject("S").getRecordMap());
        mav.addObject("G", odobj.getRetObject("G").getRecordMap());
        mav.addObject("MESSAGE", odobj.getRetmsg());
        return mav;
    }
    @RequestMapping(value="/20160901101142CostInput_loadpage.do")
    public String loadpage(ModelMap model, DOBJ idobj) throws Exception
    {
        DOBJ odobj = CostInputService.loadpage(idobj);
        model.addAttribute("WIZDOBJ", odobj);
        model.addAttribute("G", odobj.getRetObject("G").getRecordMap());
        model.addAttribute("MESSAGE", odobj.getRetmsg());
        return "/jsp/base/CostInputConfirmation";
    }
    @RequestMapping(value="/20160901101142CostInput_Condition_Combo.do")
    public ModelAndView Condition_Combo(ModelMap model, DOBJ idobj) throws Exception
    {
        ModelAndView mav = new ModelAndView("riaView");
        DOBJ odobj = CostInputService.Condition_Combo(idobj);
        mav.addObject("WIZDOBJ", odobj);
        mav.addObject("SEL2", odobj.getRetObject("SEL2").getRecords());
        mav.addObject("G", odobj.getRetObject("G").getRecordMap());
        mav.addObject("MESSAGE", odobj.getRetmsg());
        return mav;
    }
    @RequestMapping(value="/20160901101142CostInput_errorConfirm.do")
    public String errorConfirm(ModelMap model, DOBJ idobj) throws Exception
    {
        DOBJ odobj = CostInputService.errorConfirm(idobj);
        model.addAttribute("WIZDOBJ", odobj);
        model.addAttribute("S9", odobj.getRetObject("S9").getRecordMap());
        model.addAttribute("G", odobj.getRetObject("G").getRecordMap());
        model.addAttribute("MESSAGE", odobj.getRetmsg());
        return "/jsp/File/Dn01download";
    }
    @RequestMapping(value="/20160901101142CostInput_csvdownload.do")
    public String csvdownload(ModelMap model, DOBJ idobj) throws Exception
    {
        DOBJ odobj = CostInputService.csvdownload(idobj);
        model.addAttribute("WIZDOBJ", odobj);
        model.addAttribute("G", odobj.getRetObject("G").getRecordMap());
        model.addAttribute("MESSAGE", odobj.getRetmsg());
        return "/jsp/File/Dn01download";
    }
    @RequestMapping(value="/20160901101142CostInput_CostUpload.do")
    public ModelAndView CostUpload(ModelMap model, DOBJ idobj) throws Exception
    {
        ModelAndView mav = new ModelAndView("riaView");
        DOBJ odobj = CostInputService.CostUpload(idobj);
        mav.addObject("WIZDOBJ", odobj);
        mav.addObject("SEL33", odobj.getRetObject("SEL33").getRecords());
        mav.addObject("G", odobj.getRetObject("G").getRecordMap());
        mav.addObject("MESSAGE", odobj.getRetmsg());
        return mav;
    }
}