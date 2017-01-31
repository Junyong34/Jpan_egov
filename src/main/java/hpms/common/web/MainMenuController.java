
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
import hpms.common.service.MainMenu;
@Controller
public class MainMenuController
{
    @Autowired
    private MainMenu MainMenuService;
    @RequestMapping(value="/20160412101097MainMenu_nullPage.do")
    public String nullPage(ModelMap model, DOBJ idobj) throws Exception
    {
        DOBJ odobj = MainMenuService.nullPage(idobj);
        model.addAttribute("WIZDOBJ", odobj);
        model.addAttribute("G", odobj.getRetObject("G").getRecordMap());
        model.addAttribute("MESSAGE", odobj.getRetmsg());
        return "/jsp/menu/Main_menu";
    }
    @RequestMapping(value="/20160412101097MainMenu_loadPage.do")
    public ModelAndView loadPage(ModelMap model, DOBJ idobj) throws Exception
    {
        ModelAndView mav = new ModelAndView("riaView");
        DOBJ odobj = MainMenuService.loadPage(idobj);
        mav.addObject("WIZDOBJ", odobj);
        mav.addObject("SEL1", odobj.getRetObject("SEL1").getRecords());
        mav.addObject("G", odobj.getRetObject("G").getRecordMap());
        mav.addObject("S", odobj.getRetObject("S").getRecordMap());
        mav.addObject("G", odobj.getRetObject("G").getRecordMap());
        mav.addObject("MESSAGE", odobj.getRetmsg());
        return mav;
    }
}