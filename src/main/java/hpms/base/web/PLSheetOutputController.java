
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
import hpms.base.service.PLSheetOutput;
@Controller
public class PLSheetOutputController
{
    @Autowired
    private PLSheetOutput PLSheetOutputService;
    @RequestMapping(value="/20160901101143PLSheetOutput_loadpage.do")
    public String loadpage(ModelMap model, DOBJ idobj) throws Exception
    {
        DOBJ odobj = PLSheetOutputService.loadpage(idobj);
        model.addAttribute("WIZDOBJ", odobj);
        model.addAttribute("G", odobj.getRetObject("G").getRecordMap());
        model.addAttribute("MESSAGE", odobj.getRetmsg());
        return "/jsp/base/PLSheetOutput";
    }
    @RequestMapping(value="/20160901101143PLSheetOutput_ServiceID00.do")
    public ModelAndView ServiceID00(ModelMap model, DOBJ idobj) throws Exception
    {
        ModelAndView mav = new ModelAndView("riaView");
        DOBJ odobj = PLSheetOutputService.ServiceID00(idobj);
        mav.addObject("WIZDOBJ", odobj);
        mav.addObject("G", odobj.getRetObject("G").getRecordMap());
        mav.addObject("MESSAGE", odobj.getRetmsg());
        return mav;
    }
    @RequestMapping(value="/20160901101143PLSheetOutput_ServiceID01.do")
    public ModelAndView ServiceID01(ModelMap model, DOBJ idobj) throws Exception
    {
        ModelAndView mav = new ModelAndView("riaView");
        DOBJ odobj = PLSheetOutputService.ServiceID01(idobj);
        mav.addObject("WIZDOBJ", odobj);
        mav.addObject("G", odobj.getRetObject("G").getRecordMap());
        mav.addObject("MESSAGE", odobj.getRetmsg());
        return mav;
    }
    @RequestMapping(value="/20160901101143PLSheetOutput_PIDCheck.do")
    public ModelAndView PIDCheck(ModelMap model, DOBJ idobj) throws Exception
    {
        ModelAndView mav = new ModelAndView("riaView");
        DOBJ odobj = PLSheetOutputService.PIDCheck(idobj);
        mav.addObject("WIZDOBJ", odobj);
        mav.addObject("SEL1", odobj.getRetObject("SEL1").getRecords());
        mav.addObject("SEL3", odobj.getRetObject("SEL3").getRecords());
        mav.addObject("S2", odobj.getRetObject("S2").getRecordMap());
        mav.addObject("G", odobj.getRetObject("G").getRecordMap());
        mav.addObject("MESSAGE", odobj.getRetmsg());
        return mav;
    }
    @RequestMapping(value="/20160901101143PLSheetOutput_initCombo.do")
    public ModelAndView initCombo(ModelMap model, DOBJ idobj) throws Exception
    {
        ModelAndView mav = new ModelAndView("riaView");
        DOBJ odobj = PLSheetOutputService.initCombo(idobj);
        mav.addObject("WIZDOBJ", odobj);
        mav.addObject("SEL2", odobj.getRetObject("SEL2").getRecords());
        mav.addObject("G", odobj.getRetObject("G").getRecordMap());
        mav.addObject("MESSAGE", odobj.getRetmsg());
        return mav;
    }
    @RequestMapping(value="/20160901101143PLSheetOutput_PLSheetDownload.do")
    public String buildPLSheetDownload(ModelMap model, DOBJ idobj) throws Exception
    {
        DOBJ odobj = PLSheetOutputService.buildPLSheetDownload(idobj);
        model.addAttribute("WIZDOBJ", odobj);
        model.addAttribute("MRG01", odobj.getRetObject("MRG01").getRecords());
        model.addAttribute("leftColumn", odobj.getRetObject("leftColumn").getRecords());
        model.addAttribute("RightColumn", odobj.getRetObject("RightColumn").getRecords());
        model.addAttribute("G", odobj.getRetObject("G").getRecordMap());
        model.addAttribute("MESSAGE", odobj.getRetmsg());
        return "/jsp/File/Dn01download";
    }
}