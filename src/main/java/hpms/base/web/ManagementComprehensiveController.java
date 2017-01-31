
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
import hpms.base.service.ManagementComprehensive;
@Controller
public class ManagementComprehensiveController
{
    @Autowired
    private ManagementComprehensive ManagementComprehensiveService;
    @RequestMapping(value="/20160901101144ManagementComprehensive_loadpage.do")
    public String loadpage(ModelMap model, DOBJ idobj) throws Exception
    {
        DOBJ odobj = ManagementComprehensiveService.loadpage(idobj);
        model.addAttribute("WIZDOBJ", odobj);
        model.addAttribute("G", odobj.getRetObject("G").getRecordMap());
        model.addAttribute("MESSAGE", odobj.getRetmsg());
        return "/jsp/base/MgrComprehensive";
    }
    @RequestMapping(value="/20160901101144ManagementComprehensive_initCombo.do")
    public ModelAndView initCombo(ModelMap model, DOBJ idobj) throws Exception
    {
        ModelAndView mav = new ModelAndView("riaView");
        DOBJ odobj = ManagementComprehensiveService.initCombo(idobj);
        mav.addObject("WIZDOBJ", odobj);
        mav.addObject("SEL1", odobj.getRetObject("SEL1").getRecords());
        mav.addObject("SEL2", odobj.getRetObject("SEL2").getRecords());
        mav.addObject("SEL3", odobj.getRetObject("SEL3").getRecords());
        mav.addObject("G", odobj.getRetObject("G").getRecordMap());
        mav.addObject("MESSAGE", odobj.getRetmsg());
        return mav;
    }
    @RequestMapping(value="/20160901101144ManagementComprehensive_FCST_Title_ComboMgr.do")
    public ModelAndView FCST_Title_ComboMgr(ModelMap model, DOBJ idobj) throws Exception
    {
        ModelAndView mav = new ModelAndView("riaView");
        DOBJ odobj = ManagementComprehensiveService.FCST_Title_ComboMgr(idobj);
        mav.addObject("WIZDOBJ", odobj);
        mav.addObject("SEL1", odobj.getRetObject("SEL1").getRecords());
        mav.addObject("G", odobj.getRetObject("G").getRecordMap());
        mav.addObject("MESSAGE", odobj.getRetmsg());
        return mav;
    }
    @RequestMapping(value="/20160901101144ManagementComprehensive_buildSheet.do")
    public String buildSheetDownload(ModelMap model, DOBJ idobj) throws Exception
    {
        DOBJ odobj = ManagementComprehensiveService.buildSheetDownload(idobj);
        model.addAttribute("WIZDOBJ", odobj);
        model.addAttribute("SEL5", odobj.getRetObject("SEL5").getRecords());
        model.addAttribute("leftColumn", odobj.getRetObject("leftColumn").getRecords());
        model.addAttribute("RightColumn", odobj.getRetObject("RightColumn").getRecords());
        model.addAttribute("S", odobj.getRetObject("S").getRecords());
        model.addAttribute("G", odobj.getRetObject("G").getRecordMap());
        model.addAttribute("MESSAGE", odobj.getRetmsg());
        return "/jsp/File/Dn01download";
    }
}