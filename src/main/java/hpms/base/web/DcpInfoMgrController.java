
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
import hpms.base.service.DcpInfoMgr;
@Controller
public class DcpInfoMgrController
{
    @Autowired
    private DcpInfoMgr DcpInfoMgrService;
    @RequestMapping(value="/2016082417884DcpInfoMgr_loadpage.do")
    public String loadpage(ModelMap model, DOBJ idobj) throws Exception
    {
        DOBJ odobj = DcpInfoMgrService.loadpage(idobj);
        model.addAttribute("WIZDOBJ", odobj);
        model.addAttribute("G", odobj.getRetObject("G").getRecordMap());
        model.addAttribute("MESSAGE", odobj.getRetmsg());
        return "/jsp/base/DCPMgrInfo";
    }
    @RequestMapping(value="/2016082417884DcpInfoMgr_ApprovalDataSearch.do")
    public ModelAndView ApprovalDataSearch(ModelMap model, DOBJ idobj) throws Exception
    {
        ModelAndView mav = new ModelAndView("riaView");
        DOBJ odobj = DcpInfoMgrService.ApprovalDataSearch(idobj);
        mav.addObject("WIZDOBJ", odobj);
        mav.addObject("SEL2", odobj.getRetObject("SEL2").getRecords());
        mav.addObject("SEL4", odobj.getRetObject("SEL4").getRecords());
        mav.addObject("SEL6", odobj.getRetObject("SEL6").getRecords());
        mav.addObject("SEL8", odobj.getRetObject("SEL8").getRecords());
        mav.addObject("SEL10", odobj.getRetObject("SEL10").getRecords());
        mav.addObject("S", odobj.getRetObject("S").getRecordMap());
        mav.addObject("S2", odobj.getRetObject("S2").getRecordMap());
        mav.addObject("S3", odobj.getRetObject("S3").getRecordMap());
        mav.addObject("G", odobj.getRetObject("G").getRecordMap());
        mav.addObject("MESSAGE", odobj.getRetmsg());
        return mav;
    }
    @RequestMapping(value="/2016082417884DcpInfoMgr_DCPSearchInfo.do")
    public ModelAndView DCPSearchInfo(ModelMap model, DOBJ idobj) throws Exception
    {
        ModelAndView mav = new ModelAndView("riaView");
        DOBJ odobj = DcpInfoMgrService.DCPSearchInfo(idobj);
        mav.addObject("WIZDOBJ", odobj);
        mav.addObject("SEL8", odobj.getRetObject("SEL8").getRecords());
        mav.addObject("SEL4", odobj.getRetObject("SEL4").getRecords());
        mav.addObject("SEL12", odobj.getRetObject("SEL12").getRecords());
        mav.addObject("SEL14", odobj.getRetObject("SEL14").getRecords());
        mav.addObject("SEL11", odobj.getRetObject("SEL11").getRecords());
        mav.addObject("SEL6", odobj.getRetObject("SEL6").getRecords());
        mav.addObject("SEL10", odobj.getRetObject("SEL10").getRecords());
        mav.addObject("G", odobj.getRetObject("G").getRecordMap());
        mav.addObject("MESSAGE", odobj.getRetmsg());
        return mav;
    }
    @RequestMapping(value="/2016082417884DcpInfoMgr_FileInfoAdd.do")
    public ModelAndView FileInfoAdd(ModelMap model, DOBJ idobj) throws Exception
    {
        ModelAndView mav = new ModelAndView("riaView");
        DOBJ odobj = DcpInfoMgrService.FileInfoAdd(idobj);
        mav.addObject("WIZDOBJ", odobj);
        mav.addObject("SEL21", odobj.getRetObject("SEL21").getRecords());
        mav.addObject("G", odobj.getRetObject("G").getRecordMap());
        mav.addObject("MESSAGE", odobj.getRetmsg());
        return mav;
    }
    @RequestMapping(value="/2016082417884DcpInfoMgr_DCP_PIDCheck.do")
    public ModelAndView DCP_PIDCheck(ModelMap model, DOBJ idobj) throws Exception
    {
        ModelAndView mav = new ModelAndView("riaView");
        DOBJ odobj = DcpInfoMgrService.DCP_PIDCheck(idobj);
        mav.addObject("WIZDOBJ", odobj);
        mav.addObject("SEL2", odobj.getRetObject("SEL2").getRecords());
        mav.addObject("SEL5", odobj.getRetObject("SEL5").getRecords());
        mav.addObject("G", odobj.getRetObject("G").getRecordMap());
        mav.addObject("MESSAGE", odobj.getRetmsg());
        return mav;
    }
    @RequestMapping(value="/2016082417884DcpInfoMgr_DCPInfoUpdate.do")
    public ModelAndView DCPInfoUpdate(ModelMap model, DOBJ idobj) throws Exception
    {
        ModelAndView mav = new ModelAndView("riaView");
        DOBJ odobj = DcpInfoMgrService.DCPInfoUpdate(idobj);
        mav.addObject("WIZDOBJ", odobj);
        mav.addObject("SEL6", odobj.getRetObject("SEL6").getRecords());
        mav.addObject("S", odobj.getRetObject("S").getRecordMap());
        mav.addObject("S2", odobj.getRetObject("S2").getRecordMap());
        mav.addObject("S3", odobj.getRetObject("S3").getRecordMap());
        mav.addObject("G", odobj.getRetObject("G").getRecordMap());
        mav.addObject("MESSAGE", odobj.getRetmsg());
        return mav;
    }
    @RequestMapping(value="/2016082417884DcpInfoMgr_DCP_Calculate.do")
    public ModelAndView DCP_Calculate(ModelMap model, DOBJ idobj) throws Exception
    {
        ModelAndView mav = new ModelAndView("riaView");
        DOBJ odobj = DcpInfoMgrService.DCP_Calculate(idobj);
        mav.addObject("WIZDOBJ", odobj);
        mav.addObject("SEL2", odobj.getRetObject("SEL2").getRecords());
        mav.addObject("SEL4", odobj.getRetObject("SEL4").getRecords());
        mav.addObject("G", odobj.getRetObject("G").getRecordMap());
        mav.addObject("MESSAGE", odobj.getRetmsg());
        return mav;
    }
    @RequestMapping(value="/2016082417884DcpInfoMgr_Approval_applicant_ORG_Combo.do")
    public ModelAndView Approval_applicant_ORG_Combo(ModelMap model, DOBJ idobj) throws Exception
    {
        ModelAndView mav = new ModelAndView("riaView");
        DOBJ odobj = DcpInfoMgrService.Approval_applicant_ORG_Combo(idobj);
        mav.addObject("WIZDOBJ", odobj);
        mav.addObject("SEL1", odobj.getRetObject("SEL1").getRecords());
        mav.addObject("G", odobj.getRetObject("G").getRecordMap());
        mav.addObject("MESSAGE", odobj.getRetmsg());
        return mav;
    }
    @RequestMapping(value="/2016082417884DcpInfoMgr_BLOB_DCPFileDown.do")
    public String BLOB_DCPFileDown(ModelMap model, DOBJ idobj) throws Exception
    {
        DOBJ odobj = DcpInfoMgrService.BLOB_DCPFileDown(idobj);
        model.addAttribute("WIZDOBJ", odobj);
        model.addAttribute("SEL8", odobj.getRetObject("SEL8").getRecords());
        model.addAttribute("SEL2", odobj.getRetObject("SEL2").getRecords());
        model.addAttribute("G", odobj.getRetObject("G").getRecordMap());
        model.addAttribute("MESSAGE", odobj.getRetmsg());
        return "/jsp/File/Dn01download";
    }
    @RequestMapping(value="/2016082417884DcpInfoMgr_PIDConfirm.do")
    public ModelAndView PIDConfirm(ModelMap model, DOBJ idobj) throws Exception
    {
        ModelAndView mav = new ModelAndView("riaView");
        DOBJ odobj = DcpInfoMgrService.PIDConfirm(idobj);
        mav.addObject("WIZDOBJ", odobj);
        mav.addObject("SEL2", odobj.getRetObject("SEL2").getRecords());
        mav.addObject("S", odobj.getRetObject("S").getRecordMap());
        mav.addObject("G", odobj.getRetObject("G").getRecordMap());
        mav.addObject("MESSAGE", odobj.getRetmsg());
        return mav;
    }
    @RequestMapping(value="/2016082417884DcpInfoMgr_DCPApproval_Flow.do")
    public ModelAndView DCPApproval_Flow(ModelMap model, DOBJ idobj) throws Exception
    {
        ModelAndView mav = new ModelAndView("riaView");
        DOBJ odobj = DcpInfoMgrService.DCPApproval_Flow(idobj);
        mav.addObject("WIZDOBJ", odobj);
        mav.addObject("SEL19", odobj.getRetObject("SEL19").getRecords());
        mav.addObject("G", odobj.getRetObject("G").getRecordMap());
        mav.addObject("MESSAGE", odobj.getRetmsg());
        return mav;
    }
}