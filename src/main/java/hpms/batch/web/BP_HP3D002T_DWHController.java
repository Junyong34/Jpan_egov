
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
import hpms.batch.service.BP_HP3D002T_DWH;
@Controller
public class BP_HP3D002T_DWHController
{
    @Autowired
    private BP_HP3D002T_DWH BP_HP3D002T_DWHService;
    @RequestMapping(value="/20160930162713BP_HP3D002T_DWH_BP_HP3D002T_GL.do")
    public ModelAndView BP_HP3D002T_GL(ModelMap model, DOBJ idobj) throws Exception
    {
        ModelAndView mav = new ModelAndView("riaView");
        DOBJ odobj = BP_HP3D002T_DWHService.BP_HP3D002T_GL(idobj);
        mav.addObject("WIZDOBJ", odobj);
        mav.addObject("XIUD9", odobj.getRetObject("XIUD9").getRecords());
        mav.addObject("G", odobj.getRetObject("G").getRecordMap());
        mav.addObject("MESSAGE", odobj.getRetmsg());
        return mav;
    }
    @RequestMapping(value="/20160930162713BP_HP3D002T_DWH_BP_HP3D001T_FA.do")
    public ModelAndView BP_HP3D002T_FA(ModelMap model, DOBJ idobj) throws Exception
    {
        ModelAndView mav = new ModelAndView("riaView");
        DOBJ odobj = BP_HP3D002T_DWHService.BP_HP3D002T_FA(idobj);
        mav.addObject("WIZDOBJ", odobj);
        mav.addObject("XIUD9", odobj.getRetObject("XIUD9").getRecords());
        mav.addObject("G", odobj.getRetObject("G").getRecordMap());
        mav.addObject("MESSAGE", odobj.getRetmsg());
        return mav;
    }
    @RequestMapping(value="/20160930162713BP_HP3D002T_DWH_BP_HP3D002T_EDA.do")
    public ModelAndView BP_HP3D002T_EDA(ModelMap model, DOBJ idobj) throws Exception
    {
        ModelAndView mav = new ModelAndView("riaView");
        DOBJ odobj = BP_HP3D002T_DWHService.BP_HP3D002T_EDA(idobj);
        mav.addObject("WIZDOBJ", odobj);
        mav.addObject("XIUD9", odobj.getRetObject("XIUD9").getRecords());
        mav.addObject("G", odobj.getRetObject("G").getRecordMap());
        mav.addObject("MESSAGE", odobj.getRetmsg());
        return mav;
    }
}