package egov.wizware.jsp;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONFunction;
import net.sf.json.JSONObject;
import  org.json.simple.*;
import  org.json.simple.parser.*;
import org.springframework.asm.Attribute;
import org.springframework.core.MethodParameter;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.view.AbstractView;

import egov.wizware.com.DOBJ;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
public class AjaxView  extends AbstractView
{
    private final int defaultria = 0;
    private final int myplatform = 1;
    private final int xplatform = 11;
    private final int gauce = 2;
    private final int mybuilder = 3;
    private final int jqueryGrid = 5;
    private final int rianame = 5;
    
    public AjaxView()
    {
    	
    }
    protected Log log = LogFactory.getLog(this.getClass());
    @SuppressWarnings("unchecked")
    @Override
    protected void renderMergedOutputModel(Map model, HttpServletRequest request, HttpServletResponse response)  throws Exception
    {
    	
        //System.out.println("--- AjaxView AjaxView --->>>>>"+model );
 
        if(rianame == defaultria)
        {
            DOBJ dobj = (DOBJ) model.get("WIZDOBJ");
            StringBuffer data = new StringBuffer(10000);
            BTFormXmlBuilder xbtf = new BTFormXmlBuilder();
            data = xbtf.outputObject(dobj);
            System.out.println("-------[AjaxView]---->>"+data);
         
            forwardx(response, data);
        }
        else if(rianame == myplatform)
        {
            DOBJ dobj = (DOBJ) model.get("WIZDOBJ");
            MyPlatFormBuilder my = new MyPlatFormBuilder();
            my.outputObject(dobj, response);
        }else if(rianame == jqueryGrid)
        {
            
        	  StringBuffer data = new StringBuffer(10000);
              DOBJ dobj = (DOBJ) model.get("WIZDOBJ");
              JqGrid jq = new JqGrid();
            // data = jq.outputObject(dobj);//XML 방식
        
               data = jq.JsonoutputObject(dobj); //JSON 방식
             
               JSONParser json = new JSONParser();
               org.json.simple.JSONObject jobj = (org.json.simple.JSONObject)json.parse(data.toString());
              
               
              
       //  System.out.println("-------[jqgrid  OutData]---->>"+jobj);  
             // forwardx(response, jobj); //XML방식
            forwardxJSON(response, jobj);//JSON 방식
        }
        else if(rianame == xplatform)
        {
            DOBJ dobj = (DOBJ) model.get("WIZDOBJ");
            XPlatFormBuilder xplt = new XPlatFormBuilder();
            xplt.outputObject(dobj, response);
        }
        else if(rianame == mybuilder)
        {
            StringBuffer data = new StringBuffer(10000);
            DOBJ dobj = (DOBJ) model.get("WIZDOBJ");
            MyBuilder mybder = new MyBuilder();
            data = mybder.outputObject(dobj);

            /*/--------������ DATA ó��
            data = new StringBuffer();
            data.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?> ");
            data.append("<data>                            ");
            data.append("    <row>                         ");
            data.append("      <SAWONNO>20091231</SAWONNO> ");
            data.append("      <DEPTCD>2009</DEPTCD>       ");
            data.append("      <JUMINNO />                 ");
            data.append("      <SAWONNM>aaa</SAWONNM>      ");
            data.append("      <ADDR>1</ADDR>              ");
            data.append("    </row>                        ");
            data.append("</data>                           ");
            //*/
            System.out.println("-------[MyBuilder OutData]---->>"+data);
            forwardx(response, data);
        }
        else if(rianame == gauce)
        {
        }
    }
    public void forwardx(HttpServletResponse res, StringBuffer data)
    {
      try
      {
          res.setContentType("text/plain; charset=UTF-8");
          PrintWriter out = new PrintWriter(new OutputStreamWriter(res.getOutputStream(), "UTF-8"), false);
          out.write(data.toString());
          out.flush();
      }
      catch(Exception e)
      {
          e.printStackTrace();
      }
    }
    public void forwardxJSON(HttpServletResponse res, org.json.simple.JSONObject data)
    {
      try
      {
          res.setContentType("text/plain; charset=UTF-8");
          PrintWriter out = new PrintWriter(new OutputStreamWriter(res.getOutputStream(), "UTF-8"), false);
          out.write(data.toString());
          out.flush();
      }
      catch(Exception e)
      {
          e.printStackTrace();
      }
    }
    public void setMiResultMessage(String code, String Msg)
    {
    }
}
