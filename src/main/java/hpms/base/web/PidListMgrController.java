
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
import hpms.base.service.PidListMgr;
@Controller
public class PidListMgrController
{
    @Autowired
    private PidListMgr PidListMgrService;
    @RequestMapping(value="/20160902161145PidListMgr_loadpage.do")
    public String loadpage(ModelMap model, DOBJ idobj) throws Exception
    {
        DOBJ odobj = PidListMgrService.loadpage(idobj);
        model.addAttribute("WIZDOBJ", odobj);
        model.addAttribute("G", odobj.getRetObject("G").getRecordMap());
        model.addAttribute("MESSAGE", odobj.getRetmsg());
        return "/jsp/base/PIDList";
    }
    @RequestMapping(value="/20160902161145PidListMgr_ServiceITest.do")
    public ModelAndView ServiceITest(ModelMap model, DOBJ idobj) throws Exception
    {
        ModelAndView mav = new ModelAndView("riaView");
        DOBJ odobj = PidListMgrService.ServiceITest(idobj);
        mav.addObject("WIZDOBJ", odobj);
        mav.addObject("SEL7", odobj.getRetObject("SEL7").getRecords());
        mav.addObject("S", odobj.getRetObject("S").getRecordMap());
        mav.addObject("G", odobj.getRetObject("G").getRecordMap());
        mav.addObject("MESSAGE", odobj.getRetmsg());
        return mav;
    }
    @RequestMapping(value="/20160902161145PidListMgr_PIDListinfo.do")
    public ModelAndView PIDListinfo(ModelMap model, DOBJ idobj) throws Exception
    {
        ModelAndView mav = new ModelAndView("riaView");
        DOBJ odobj = PidListMgrService.PIDListinfo(idobj);
        mav.addObject("WIZDOBJ", odobj);
        mav.addObject("SEL2", odobj.getRetObject("SEL2").getRecords());
        mav.addObject("G", odobj.getRetObject("G").getRecordMap());
        mav.addObject("MESSAGE", odobj.getRetmsg());
        return mav;
    }
}