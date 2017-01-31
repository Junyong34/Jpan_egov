
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
import hpms.base.service.PlanMgr;
@Controller
public class PlanMgrController
{
    @Autowired
    private PlanMgr PlanMgrService;
    @RequestMapping(value="/20160901091138PlanMgr_loadpage.do")
    public String loadpage(ModelMap model, DOBJ idobj) throws Exception
    {
        DOBJ odobj = PlanMgrService.loadpage(idobj);
        model.addAttribute("WIZDOBJ", odobj);
        model.addAttribute("G", odobj.getRetObject("G").getRecordMap());
        model.addAttribute("MESSAGE", odobj.getRetmsg());
        return "/jsp/base/PlanInputMgr";
    }
    @RequestMapping(value="/20160901091138PlanMgr_errorConfirm.do")
    public String errorConfirm(ModelMap model, DOBJ idobj) throws Exception
    {
        DOBJ odobj = PlanMgrService.errorConfirm(idobj);
        model.addAttribute("WIZDOBJ", odobj);
        model.addAttribute("G", odobj.getRetObject("G").getRecordMap());
        model.addAttribute("MESSAGE", odobj.getRetmsg());
        return "/jsp/File/Dn01download";
    }
    @RequestMapping(value="/20160901091138PlanMgr_initCombo.do")
    public ModelAndView initCombo(ModelMap model, DOBJ idobj) throws Exception
    {
        ModelAndView mav = new ModelAndView("riaView");
        DOBJ odobj = PlanMgrService.initCombo(idobj);
        mav.addObject("WIZDOBJ", odobj);
        mav.addObject("MRG1", odobj.getRetObject("MRG1").getRecords());
        mav.addObject("SEL5", odobj.getRetObject("SEL5").getRecords());
        mav.addObject("SEL6", odobj.getRetObject("SEL6").getRecords());
        mav.addObject("G", odobj.getRetObject("G").getRecordMap());
        mav.addObject("MESSAGE", odobj.getRetmsg());
        return mav;
    }
    @RequestMapping(value="/20160901091138PlanMgr_ExcelNoChecking.do")
    public ModelAndView ExcelNoChecking(ModelMap model, DOBJ idobj) throws Exception
    {
        ModelAndView mav = new ModelAndView("riaView");
        DOBJ odobj = PlanMgrService.ExcelNoChecking(idobj);
        mav.addObject("WIZDOBJ", odobj);
        mav.addObject("ER01", odobj.getRetObject("ER01").getRecords());
        mav.addObject("G", odobj.getRetObject("G").getRecordMap());
        mav.addObject("MESSAGE", odobj.getRetmsg());
        return mav;
    }
    @RequestMapping(value="/20160901091138PlanMgr_planExcelDownload.do")
    public String planExcelDownload(ModelMap model, DOBJ idobj) throws Exception
    {
        DOBJ odobj = PlanMgrService.planExcelDownload(idobj);
        model.addAttribute("WIZDOBJ", odobj);
        model.addAttribute("G", odobj.getRetObject("G").getRecordMap());
        model.addAttribute("MESSAGE", odobj.getRetmsg());
        return "/jsp/File/Dn01download";
    }
}