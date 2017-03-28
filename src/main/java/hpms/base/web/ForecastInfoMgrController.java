
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
import hpms.base.service.ForecastInfoMgr;
@Controller
public class ForecastInfoMgrController
{
    @Autowired
    private ForecastInfoMgr ForecastInfoMgrService;
    @RequestMapping(value="/2016082417899ForecastInfoMgr_loadpage.do")
    public String loadpage(ModelMap model, DOBJ idobj) throws Exception
    {
        DOBJ odobj = ForecastInfoMgrService.loadpage(idobj);
        model.addAttribute("WIZDOBJ", odobj);
        model.addAttribute("G", odobj.getRetObject("G").getRecordMap());
        model.addAttribute("MESSAGE", odobj.getRetmsg());
        return "/jsp/base/ForeacstMgrInfo";
    }
    @RequestMapping(value="/2016082417899ForecastInfoMgr_ForecastTitleSet.do")
    public ModelAndView ForecastTitleSet(ModelMap model, DOBJ idobj) throws Exception
    {
        ModelAndView mav = new ModelAndView("riaView");
        DOBJ odobj = ForecastInfoMgrService.ForecastTitleSet(idobj);
        mav.addObject("WIZDOBJ", odobj);
        mav.addObject("S", odobj.getRetObject("S").getRecordMap());
        mav.addObject("S1", odobj.getRetObject("S1").getRecordMap());
        mav.addObject("G", odobj.getRetObject("G").getRecordMap());
        mav.addObject("MESSAGE", odobj.getRetmsg());
        return mav;
    }
    @RequestMapping(value="/2016082417899ForecastInfoMgr_ForecastTitleSet2.do")
    public ModelAndView ForecastTitleSet2(ModelMap model, DOBJ idobj) throws Exception
    {
        ModelAndView mav = new ModelAndView("riaView");
        DOBJ odobj = ForecastInfoMgrService.ForecastTitleSet2(idobj);
        mav.addObject("WIZDOBJ", odobj);
        mav.addObject("S", odobj.getRetObject("S").getRecordMap());
        mav.addObject("S2", odobj.getRetObject("S2").getRecordMap());
        mav.addObject("G", odobj.getRetObject("G").getRecordMap());
        mav.addObject("MESSAGE", odobj.getRetmsg());
        return mav;
    }
    @RequestMapping(value="/2016082417899ForecastInfoMgr_ServiceID00.do")
    public ModelAndView ServiceID00(ModelMap model, DOBJ idobj) throws Exception
    {
        ModelAndView mav = new ModelAndView("riaView");
        DOBJ odobj = ForecastInfoMgrService.ServiceID00(idobj);
        mav.addObject("WIZDOBJ", odobj);
        mav.addObject("MRG1", odobj.getRetObject("MRG1").getRecords());
        mav.addObject("SEL5", odobj.getRetObject("SEL5").getRecords());
        mav.addObject("G", odobj.getRetObject("G").getRecordMap());
        mav.addObject("MESSAGE", odobj.getRetmsg());
        return mav;
    }
    @RequestMapping(value="/2016082417899ForecastInfoMgr_Forecast_PIDcheck.do")
    public ModelAndView Forecast_PIDcheck(ModelMap model, DOBJ idobj) throws Exception
    {
        ModelAndView mav = new ModelAndView("riaView");
        DOBJ odobj = ForecastInfoMgrService.Forecast_PIDcheck(idobj);
        mav.addObject("WIZDOBJ", odobj);
        mav.addObject("SEL2", odobj.getRetObject("SEL2").getRecords());
        mav.addObject("SEL4", odobj.getRetObject("SEL4").getRecords());
        mav.addObject("G", odobj.getRetObject("G").getRecordMap());
        mav.addObject("MESSAGE", odobj.getRetmsg());
        return mav;
    }
    @RequestMapping(value="/2016082417899ForecastInfoMgr_Forecast_ORGCheck.do")
    public ModelAndView Forecast_ORGCheck(ModelMap model, DOBJ idobj) throws Exception
    {
        ModelAndView mav = new ModelAndView("riaView");
        DOBJ odobj = ForecastInfoMgrService.Forecast_ORGCheck(idobj);
        mav.addObject("WIZDOBJ", odobj);
        mav.addObject("SEL2", odobj.getRetObject("SEL2").getRecords());
        mav.addObject("SEL4", odobj.getRetObject("SEL4").getRecords());
        mav.addObject("S", odobj.getRetObject("S").getRecordMap());
        mav.addObject("S2", odobj.getRetObject("S2").getRecordMap());
        mav.addObject("G", odobj.getRetObject("G").getRecordMap());
        mav.addObject("MESSAGE", odobj.getRetmsg());
        return mav;
    }
    @RequestMapping(value="/2016082417899ForecastInfoMgr_ForecastDataCopy2.do")
    public ModelAndView ForecastDataCopy2(ModelMap model, DOBJ idobj) throws Exception
    {
        ModelAndView mav = new ModelAndView("riaView");
        DOBJ odobj = ForecastInfoMgrService.ForecastDataCopy2(idobj);
        mav.addObject("WIZDOBJ", odobj);
        mav.addObject("G", odobj.getRetObject("G").getRecordMap());
        mav.addObject("MESSAGE", odobj.getRetmsg());
        return mav;
    }
    @RequestMapping(value="/2016082417899ForecastInfoMgr_ForecastDataCopy.do")
    public ModelAndView ForecastDataCopy(ModelMap model, DOBJ idobj) throws Exception
    {
        ModelAndView mav = new ModelAndView("riaView");
        DOBJ odobj = ForecastInfoMgrService.ForecastDataCopy(idobj);
        mav.addObject("WIZDOBJ", odobj);
        mav.addObject("SEL20", odobj.getRetObject("SEL20").getRecords());
        mav.addObject("SEL41", odobj.getRetObject("SEL41").getRecords());
        mav.addObject("G", odobj.getRetObject("G").getRecordMap());
        mav.addObject("MESSAGE", odobj.getRetmsg());
        return mav;
    }
    @RequestMapping(value="/2016082417899ForecastInfoMgr_FCSTCOPYCombo.do")
    public ModelAndView FCSTCOPYCombo(ModelMap model, DOBJ idobj) throws Exception
    {
        ModelAndView mav = new ModelAndView("riaView");
        DOBJ odobj = ForecastInfoMgrService.FCSTCOPYCombo(idobj);
        mav.addObject("WIZDOBJ", odobj);
        mav.addObject("SEL1", odobj.getRetObject("SEL1").getRecords());
        mav.addObject("SEL2", odobj.getRetObject("SEL2").getRecords());
        mav.addObject("G", odobj.getRetObject("G").getRecordMap());
        mav.addObject("MESSAGE", odobj.getRetmsg());
        return mav;
    }
    @RequestMapping(value="/2016082417899ForecastInfoMgr_FCST_TITLE_COMBO.do")
    public ModelAndView FCST_TITLE_COMBO(ModelMap model, DOBJ idobj) throws Exception
    {
        ModelAndView mav = new ModelAndView("riaView");
        DOBJ odobj = ForecastInfoMgrService.FCST_TITLE_COMBO(idobj);
        mav.addObject("WIZDOBJ", odobj);
        mav.addObject("SEL2", odobj.getRetObject("SEL2").getRecords());
        mav.addObject("SEL4", odobj.getRetObject("SEL4").getRecords());
        mav.addObject("SEL6", odobj.getRetObject("SEL6").getRecords());
        mav.addObject("SEL9", odobj.getRetObject("SEL9").getRecords());
        mav.addObject("SEL8", odobj.getRetObject("SEL8").getRecords());
        mav.addObject("SEL11", odobj.getRetObject("SEL11").getRecords());
        mav.addObject("SEL12", odobj.getRetObject("SEL12").getRecords());
        mav.addObject("S", odobj.getRetObject("S").getRecordMap());
        mav.addObject("S5", odobj.getRetObject("S5").getRecordMap());
        mav.addObject("G", odobj.getRetObject("G").getRecordMap());
        mav.addObject("MESSAGE", odobj.getRetmsg());
        return mav;
    }
}