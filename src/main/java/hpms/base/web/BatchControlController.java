
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
import hpms.base.service.BatchControl;
@Controller
public class BatchControlController
{
    @Autowired
    private BatchControl BatchControlService;
    @RequestMapping(value="/20160912111170BatchControl_loadpage.do")
    public String loadpage(ModelMap model, DOBJ idobj) throws Exception
    {
        DOBJ odobj = BatchControlService.loadpage(idobj);
        model.addAttribute("WIZDOBJ", odobj);
        model.addAttribute("G", odobj.getRetObject("G").getRecordMap());
        model.addAttribute("MESSAGE", odobj.getRetmsg());
        return "/jsp/base/BatchControl";
    }
    @RequestMapping(value="/20160912111170BatchControl_writeStop.do")
    public ModelAndView writeStop(ModelMap model, DOBJ idobj) throws Exception
    {
        ModelAndView mav = new ModelAndView("riaView");
        DOBJ odobj = BatchControlService.writeStop(idobj);
        mav.addObject("WIZDOBJ", odobj);
        mav.addObject("SEL4", odobj.getRetObject("SEL4").getRecords());
        mav.addObject("SEL5", odobj.getRetObject("SEL5").getRecords());
        mav.addObject("G", odobj.getRetObject("G").getRecordMap());
        mav.addObject("MESSAGE", odobj.getRetmsg());
        return mav;
    }
    @RequestMapping(value="/20160912111170BatchControl_writeRelease.do")
    public ModelAndView writeRelease(ModelMap model, DOBJ idobj) throws Exception
    {
        ModelAndView mav = new ModelAndView("riaView");
        DOBJ odobj = BatchControlService.writeRelease(idobj);
        mav.addObject("WIZDOBJ", odobj);
        mav.addObject("SEL2", odobj.getRetObject("SEL2").getRecords());
        mav.addObject("SEL3", odobj.getRetObject("SEL3").getRecords());
        mav.addObject("S", odobj.getRetObject("S").getRecordMap());
        mav.addObject("S1", odobj.getRetObject("S1").getRecordMap());
        mav.addObject("G", odobj.getRetObject("G").getRecordMap());
        mav.addObject("MESSAGE", odobj.getRetmsg());
        return mav;
    }
    @RequestMapping(value="/20160912111170BatchControl_managedatabuild.do")
    public ModelAndView managedatabuild(ModelMap model, DOBJ idobj) throws Exception
    {
        ModelAndView mav = new ModelAndView("riaView");
        DOBJ odobj = BatchControlService.managedatabuild(idobj);
        mav.addObject("WIZDOBJ", odobj);
        mav.addObject("S", odobj.getRetObject("S").getRecordMap());
        mav.addObject("S1", odobj.getRetObject("S1").getRecordMap());
        mav.addObject("G", odobj.getRetObject("G").getRecordMap());
        mav.addObject("MESSAGE", odobj.getRetmsg());
        return mav;
    }
    @RequestMapping(value="/20160912111170BatchControl_getConfirmInfo.do")
    public ModelAndView getConfirmInfo(ModelMap model, DOBJ idobj) throws Exception
    {
        ModelAndView mav = new ModelAndView("riaView");
        DOBJ odobj = BatchControlService.getConfirmInfo(idobj);
        mav.addObject("WIZDOBJ", odobj);
        mav.addObject("SEL2", odobj.getRetObject("SEL2").getRecords());
        mav.addObject("SEL4", odobj.getRetObject("SEL4").getRecords());
        mav.addObject("MRG11", odobj.getRetObject("MRG11").getRecords());
        mav.addObject("G", odobj.getRetObject("G").getRecordMap());
        mav.addObject("MESSAGE", odobj.getRetmsg());
        return mav;
    }
    @RequestMapping(value="/20160912111170BatchControl_LMP_ConfirmCheck.do")
    public ModelAndView LMP_ConfirmCheck(ModelMap model, DOBJ idobj) throws Exception
    {
        ModelAndView mav = new ModelAndView("riaView");
        DOBJ odobj = BatchControlService.LMP_ConfirmCheck(idobj);
        mav.addObject("WIZDOBJ", odobj);
        mav.addObject("G", odobj.getRetObject("G").getRecordMap());
        mav.addObject("MESSAGE", odobj.getRetmsg());
        return mav;
    }
    @RequestMapping(value="/20160912111170BatchControl_pageInit.do")
    public ModelAndView pageInit(ModelMap model, DOBJ idobj) throws Exception
    {
        ModelAndView mav = new ModelAndView("riaView");
        DOBJ odobj = BatchControlService.pageInit(idobj);
        mav.addObject("WIZDOBJ", odobj);
        mav.addObject("SEL2", odobj.getRetObject("SEL2").getRecords());
        mav.addObject("SEL4", odobj.getRetObject("SEL4").getRecords());
        mav.addObject("SEL6", odobj.getRetObject("SEL6").getRecords());
        mav.addObject("SEL8", odobj.getRetObject("SEL8").getRecords());
        mav.addObject("SEL10", odobj.getRetObject("SEL10").getRecords());
        mav.addObject("SEL19", odobj.getRetObject("SEL19").getRecords());
        mav.addObject("G", odobj.getRetObject("G").getRecordMap());
        mav.addObject("MESSAGE", odobj.getRetmsg());
        return mav;
    }
    @RequestMapping(value="/20160912111170BatchControl_copyLMP.do")
    public ModelAndView copyLMP(ModelMap model, DOBJ idobj) throws Exception
    {
        ModelAndView mav = new ModelAndView("riaView");
        DOBJ odobj = BatchControlService.copyLMP(idobj);
        mav.addObject("WIZDOBJ", odobj);
        mav.addObject("SEL8", odobj.getRetObject("SEL8").getRecords());
        mav.addObject("G", odobj.getRetObject("G").getRecordMap());
        mav.addObject("MESSAGE", odobj.getRetmsg());
        return mav;
    }
    @RequestMapping(value="/20160912111170BatchControl_copyMP.do")
    public ModelAndView copyMP(ModelMap model, DOBJ idobj) throws Exception
    {
        ModelAndView mav = new ModelAndView("riaView");
        DOBJ odobj = BatchControlService.copyMP(idobj);
        mav.addObject("WIZDOBJ", odobj);
        mav.addObject("SEL3", odobj.getRetObject("SEL3").getRecords());
        mav.addObject("G", odobj.getRetObject("G").getRecordMap());
        mav.addObject("MESSAGE", odobj.getRetmsg());
        return mav;
    }
    @RequestMapping(value="/20160912111170BatchControl_copyMiddle.do")
    public ModelAndView copyMiddle(ModelMap model, DOBJ idobj) throws Exception
    {
        ModelAndView mav = new ModelAndView("riaView");
        DOBJ odobj = BatchControlService.copyMiddle(idobj);
        mav.addObject("WIZDOBJ", odobj);
        mav.addObject("SEL3", odobj.getRetObject("SEL3").getRecords());
        mav.addObject("S4", odobj.getRetObject("S4").getRecordMap());
        mav.addObject("G", odobj.getRetObject("G").getRecordMap());
        mav.addObject("MESSAGE", odobj.getRetmsg());
        return mav;
    }
}