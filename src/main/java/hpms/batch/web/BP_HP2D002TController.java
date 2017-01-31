
package hpms.batch.web;
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
import hpms.batch.service.BP_HP2D002T;
@Controller
public class BP_HP2D002TController
{
    @Autowired
    private BP_HP2D002T BP_HP2D002TService;
    @RequestMapping(value="/20160930162723BP_HP2D002T_BP_HP2D002T_TZ.do")
    public ModelAndView BP_HP2D002T_TZ(ModelMap model, DOBJ idobj) throws Exception
    {
        ModelAndView mav = new ModelAndView("riaView");
        DOBJ odobj = BP_HP2D002TService.BP_HP2D002T_TZ(idobj);
        mav.addObject("WIZDOBJ", odobj);
        mav.addObject("S", odobj.getRetObject("S").getRecordMap());
        mav.addObject("G", odobj.getRetObject("G").getRecordMap());
        mav.addObject("MESSAGE", odobj.getRetmsg());
        return mav;
    }
    @RequestMapping(value="/20160930162723BP_HP2D002T_BP_HP2D00�ｽ�ｽ�ｽ�ｽ髫ｰ證ｦ�ｽ�｢.do")
    public ModelAndView BP_HP2D002T(ModelMap model, DOBJ idobj) throws Exception
    {
        ModelAndView mav = new ModelAndView("riaView");
        DOBJ odobj = BP_HP2D002TService.BP_HP2D002T(idobj);
        mav.addObject("WIZDOBJ", odobj);
        mav.addObject("S", odobj.getRetObject("S").getRecordMap());
        mav.addObject("G", odobj.getRetObject("G").getRecordMap());
        mav.addObject("MESSAGE", odobj.getRetmsg());
        return mav;
    }
    @RequestMapping(value="/20160930162723BP_HP2D002T_BP_HP2D002T_DPR.do")
    public ModelAndView BP_HP2D002T_DPR(ModelMap model, DOBJ idobj) throws Exception
    {
        ModelAndView mav = new ModelAndView("riaView");
        DOBJ odobj = BP_HP2D002TService.BP_HP2D002T_DPR(idobj);
        mav.addObject("WIZDOBJ", odobj);
        mav.addObject("S", odobj.getRetObject("S").getRecordMap());
        mav.addObject("G", odobj.getRetObject("G").getRecordMap());
        mav.addObject("MESSAGE", odobj.getRetmsg());
        return mav;
    }
    @RequestMapping(value="/20160930162723BP_HP2D002T_BP_HP2D002T_CAL.do")
    public ModelAndView BP_HP2D002T_CAL(ModelMap model, DOBJ idobj) throws Exception
    {
        ModelAndView mav = new ModelAndView("riaView");
        DOBJ odobj = BP_HP2D002TService.BP_HP2D002T_CAL(idobj);
        mav.addObject("WIZDOBJ", odobj);
        mav.addObject("S", odobj.getRetObject("S").getRecordMap());
        mav.addObject("G", odobj.getRetObject("G").getRecordMap());
        mav.addObject("MESSAGE", odobj.getRetmsg());
        return mav;
    }
    @RequestMapping(value="/20160930162723BP_HP2D002T_BP_HP2D002T_ALC.do")
    public ModelAndView BP_HP2D002T_ALC(ModelMap model, DOBJ idobj) throws Exception
    {
        ModelAndView mav = new ModelAndView("riaView");
        DOBJ odobj = BP_HP2D002TService.BP_HP2D002T_ALC(idobj);
        mav.addObject("WIZDOBJ", odobj);
        mav.addObject("S", odobj.getRetObject("S").getRecordMap());
        mav.addObject("G", odobj.getRetObject("G").getRecordMap());
        mav.addObject("MESSAGE", odobj.getRetmsg());
        return mav;
    }
    @RequestMapping(value="/20160930162723BP_HP2D002T_BP_HP2D101T.do")
    public ModelAndView BP_HP2D101T(ModelMap model, DOBJ idobj) throws Exception
    {
        ModelAndView mav = new ModelAndView("riaView");
        DOBJ odobj = BP_HP2D002TService.BP_HP2D101T(idobj);
        mav.addObject("WIZDOBJ", odobj);
        mav.addObject("S", odobj.getRetObject("S").getRecordMap());
        mav.addObject("G", odobj.getRetObject("G").getRecordMap());
        mav.addObject("MESSAGE", odobj.getRetmsg());
        return mav;
    }
}