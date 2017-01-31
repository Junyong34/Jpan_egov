
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
import hpms.common.service.CommonCodeMgr;
@Controller
public class CommonCodeMgrController
{
    @Autowired
    private CommonCodeMgr CommonCodeMgrService;
    @RequestMapping(value="/20160704154561CommonCodeMgr_CommonCombo.do")
    public ModelAndView CommonCombo(ModelMap model, DOBJ idobj) throws Exception
    {
        ModelAndView mav = new ModelAndView("riaView");
        DOBJ odobj = CommonCodeMgrService.CommonCombo(idobj);
        mav.addObject("WIZDOBJ", odobj);
        mav.addObject("SEL2", odobj.getRetObject("SEL2").getRecords());
        mav.addObject("SEL4", odobj.getRetObject("SEL4").getRecords());
        mav.addObject("SEL6", odobj.getRetObject("SEL6").getRecords());
        mav.addObject("SEL8", odobj.getRetObject("SEL8").getRecords());
        mav.addObject("SEL10", odobj.getRetObject("SEL10").getRecords());
        mav.addObject("SEL12", odobj.getRetObject("SEL12").getRecords());
        mav.addObject("SEL14", odobj.getRetObject("SEL14").getRecords());
        mav.addObject("SEL16", odobj.getRetObject("SEL16").getRecords());
        mav.addObject("SEL20", odobj.getRetObject("SEL20").getRecords());
        mav.addObject("SEL18", odobj.getRetObject("SEL18").getRecords());
        mav.addObject("G", odobj.getRetObject("G").getRecordMap());
        mav.addObject("MESSAGE", odobj.getRetmsg());
        return mav;
    }
    @RequestMapping(value="/20160704154561CommonCodeMgr_Company_Org_cd.do")
    public ModelAndView Company_Org_cd(ModelMap model, DOBJ idobj) throws Exception
    {
        ModelAndView mav = new ModelAndView("riaView");
        DOBJ odobj = CommonCodeMgrService.Company_Org_cd(idobj);
        mav.addObject("WIZDOBJ", odobj);
        mav.addObject("SEL4", odobj.getRetObject("SEL4").getRecords());
        mav.addObject("SEL2", odobj.getRetObject("SEL2").getRecords());
        mav.addObject("SEL5", odobj.getRetObject("SEL5").getRecords());
        mav.addObject("SEL7", odobj.getRetObject("SEL7").getRecords());
        mav.addObject("G", odobj.getRetObject("G").getRecordMap());
        mav.addObject("MESSAGE", odobj.getRetmsg());
        return mav;
    }
    @RequestMapping(value="/20160704154561CommonCodeMgr_SessionCombo.do")
    public ModelAndView SessionCombo(ModelMap model, DOBJ idobj) throws Exception
    {
        ModelAndView mav = new ModelAndView("riaView");
        DOBJ odobj = CommonCodeMgrService.SessionCombo(idobj);
        mav.addObject("WIZDOBJ", odobj);
        mav.addObject("SEL1", odobj.getRetObject("SEL1").getRecords());
        mav.addObject("G", odobj.getRetObject("G").getRecordMap());
        mav.addObject("MESSAGE", odobj.getRetmsg());
        return mav;
    }
    @RequestMapping(value="/20160704154561CommonCodeMgr_ORGCombobox.do")
    public ModelAndView ORGCombobox(ModelMap model, DOBJ idobj) throws Exception
    {
        ModelAndView mav = new ModelAndView("riaView");
        DOBJ odobj = CommonCodeMgrService.ORGCombobox(idobj);
        mav.addObject("WIZDOBJ", odobj);
        mav.addObject("SEL1", odobj.getRetObject("SEL1").getRecords());
        mav.addObject("G", odobj.getRetObject("G").getRecordMap());
        mav.addObject("MESSAGE", odobj.getRetmsg());
        return mav;
    }
    @RequestMapping(value="/20160704154561CommonCodeMgr_ORGAuthCombobox.do")
    public ModelAndView ORGAuthCombobox(ModelMap model, DOBJ idobj) throws Exception
    {
        ModelAndView mav = new ModelAndView("riaView");
        DOBJ odobj = CommonCodeMgrService.ORGAuthCombobox(idobj);
        mav.addObject("WIZDOBJ", odobj);
        mav.addObject("SEL1", odobj.getRetObject("SEL1").getRecords());
        mav.addObject("G", odobj.getRetObject("G").getRecordMap());
        mav.addObject("MESSAGE", odobj.getRetmsg());
        return mav;
    }
    @RequestMapping(value="/20160704154561CommonCodeMgr_AllDataTypeCombo.do")
    public ModelAndView AllDataTypeCombo(ModelMap model, DOBJ idobj) throws Exception
    {
        ModelAndView mav = new ModelAndView("riaView");
        DOBJ odobj = CommonCodeMgrService.AllDataTypeCombo(idobj);
        mav.addObject("WIZDOBJ", odobj);
        mav.addObject("SEL2", odobj.getRetObject("SEL2").getRecords());
        mav.addObject("G", odobj.getRetObject("G").getRecordMap());
        mav.addObject("MESSAGE", odobj.getRetmsg());
        return mav;
    }
}