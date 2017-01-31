package egov.wizware.ria;

import egov.wizware.com.*;
import egov.wizware.com.*;
import egov.wizware.dao.*;
import egov.wizware.msg.*;

import java.sql.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;
import org.jdom.Document;
import java.util.Iterator;
import org.jdom.input.SAXBuilder;
import org.jdom.Element;
import java.io.*;


public class CtlXmlFileInfo
{

    public CtlXmlFileInfo()
    {
    }
    //-----------------------xml file read start-----------------------
    /*(read xml format)
    <?xml version="1.0" encoding="euc-kr" ?>
     <root>
       <FNFNH>
         <MENU id="1001">
           <EVENT id="insert">
             <PACKAGE>WIZ.SAMPLE.COM </PACKAGE>
             <CLASS> TESTCTL </CLASS>
             <METHOD> CRUD</METHOD>
             <LOGDISP> 1</LOGDISP>
             <LABEL> 가나다</LABEL>
           </EVENT>
         </MENU>
       </FNFNH>
     </root>
    */
    //-----------------------------------------------------------
    public HashMap inputObject(String _sysid, String _menuid, String _eventid) throws Exception
    {
        File _fp = new File("D:/TEMP/processbuilder.xml");
        FileInputStream _fin = new FileInputStream(_fp);
        HashMap _hmap = _getEventInfoData(_fin, _sysid , _menuid, _eventid);
        return _hmap;
    }
    private  HashMap _getEventInfoData(InputStream in, String _sysid, String _menuid, String _eventid)
    {

        HashMap rtn = null;
        Document doc=null;
        SAXBuilder sax = new SAXBuilder();
        Element root = null;
        Element node = null;
        try{
            InputStreamReader inreader = new InputStreamReader(in, "euc-kr");
            doc = sax.build(inreader);
            root = doc.getRootElement();
            List list = root.getChildren();
            Iterator it = list.iterator();
            while(it.hasNext())
            {
                node = (Element)it.next();
                if(node.getName().equals(_sysid.trim())){
                 rtn = _getEventNode(node, _menuid, _eventid);
                }
            }
        }catch(Exception je){
            je.printStackTrace();
        }
        return rtn;
    }
    private  HashMap  _getEventNode(Element ele, String _menuid, String _eventid)
    {  // SYSID
        HashMap  rtn = null;
        List list = ele.getChildren();
        Element cele = null;
        Iterator it = list.iterator();

        while(it.hasNext())
        {
            cele = (Element)it.next();
            if(cele.getName().equals("MENU"))
            {
                if(cele.getAttributeValue("id").equals(_menuid))
                {
                    rtn = _getEventInfo(cele, _eventid);
                }
            }
        }
        return rtn;
    }
    private  HashMap _getEventInfo(Element ele, String _eventid)  //MENU
    {
        HashMap rtn = null;
        List list = ele.getChildren();
        Element cele = null;

        for(int i=0;i<list.size();i++)
        {
            cele = (Element)list.get(i);  //data
            if(cele.getName().equals("EVENT"))
           {
               if(cele.getAttributeValue("id").equals(_eventid))
               {
                   rtn = _getEventInfo(cele);
               }
            }
        }
        return rtn;
    }
    private  HashMap _getEventInfo(Element ele)  //Event-info
    {
        HashMap rtn = new HashMap();
        List list = ele.getChildren();
        Element cele = null;

        for(int i=0;i<list.size();i++)
        {
            cele = (Element)list.get(i);  //data
            rtn.put(cele.getName(), cele.getText());
        }
        return rtn;
    }

    //-----------------------xml file read end-----------------------
    //***************************************************************


    //**************************************************************
    // xml file 생성처리  START  XML FILE Format은 read format 동일
    //-------------------------------------------------------------
    public void createEventXmlFile(String _sysid, String path, String filename)
    {
        Connection Conn = null;
        try
        {
            Connector connection = new Connector();
            Conn = connection.getConnectionDirect("oracle","jdbc:oracle:thin:@soa:1521:K2","TEST","TEST");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        try
        {
            VOBJ vobj  = getEventInfo(Conn, _sysid);
            makefile(vobj, _sysid, path, filename);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                Conn.close();
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
        }
  }

  public DOBJ createEventXmlFile(DOBJ dobj)
  {
      try
      {
          VOBJ vobjS = dobj.getRetObject("S");
          VOBJ vobj = dobj.getRetObject("SEL1");
          String _sysid  = vobjS.getRecord().get("SYSID");
          String path    =vobjS.getRecord().get("PATH");
          String filename=vobjS.getRecord().get("FILENAME");
          makefile(vobj, _sysid, path, filename);
      } catch (Exception e)
      {
          e.printStackTrace();
      }
      return dobj;
  }

  public void makefile(VOBJ vobj, String sysid, String path, String filename)
  {
      String _menuid ="";
      String fullname =path + "/" + filename;
      File _fp = new File(fullname);
      FileOutputStream fout = null;
      OutputStreamWriter owrt  = null;
      try {
          fout = new FileOutputStream(_fp);
          owrt = new OutputStreamWriter(fout, "euc-kr");
          owrt.write("<?xml version=\"1.0\" encoding=\"euc-kr\" ?> \n");
          owrt.write("<root> \n");
          owrt.write("  <"+sysid+"> \n");
          vobj.first();
          while(vobj.next()){
              if(vobj.current==0) {
                  _menuid = vobj.getRecord().get("MENUID");
                  owrt.write("    <MENU id=\""+vobj.getRecord().get("MENUID")+"\"> \n");
                  owrt.write("      <EVENT id=\""+_cvtChar(vobj.getRecord().get("EVENTID"))+ "\" label=\""+_cvtChar(vobj.getRecord().get("LABEL"))+"\" "+  "> \n");
                  owrt.write("        <PACKAGE>"+vobj.getRecord().get("PACKAGE")+"</PACKAGE> \n");
                  owrt.write("        <CLASS>"+vobj.getRecord().get("CLASS")+"</CLASS> \n");
                  owrt.write("        <METHOD>"+vobj.getRecord().get("METHOD")+"</METHOD> \n");
                  owrt.write("        <LOGDISP>"+vobj.getRecord().get("LOGDISP")+"</LOGDISP> \n");
                  owrt.write("      </EVENT> \n");

              }else if(vobj.getRecord().get("MENUID").equals(_menuid)){
                  owrt.write("      <EVENT id=\""+_cvtChar(vobj.getRecord().get("EVENTID"))+ "\" label=\""+_cvtChar(vobj.getRecord().get("LABEL"))+"\" "+  "> \n");
                  owrt.write("        <PACKAGE>"+vobj.getRecord().get("PACKAGE")+"</PACKAGE> \n");
                  owrt.write("        <CLASS>"+vobj.getRecord().get("CLASS")+"</CLASS> \n");
                  owrt.write("        <METHOD>"+vobj.getRecord().get("METHOD")+"</METHOD> \n");
                  owrt.write("        <LOGDISP>"+vobj.getRecord().get("LOGDISP")+"</LOGDISP> \n");
                  owrt.write("      </EVENT> \n");

              }else {
                  owrt.write("    </MENU> \n");
                  _menuid = vobj.getRecord().get("MENUID");
                  owrt.write("    <MENU id=\""+vobj.getRecord().get("MENUID")+"\">  \n");
                  owrt.write("      <EVENT id=\""+_cvtChar(vobj.getRecord().get("EVENTID"))+ "\" label=\""+_cvtChar(vobj.getRecord().get("LABEL"))+"\" "+  "> \n");
                  owrt.write("        <PACKAGE>"+vobj.getRecord().get("PACKAGE")+"</PACKAGE> \n");
                  owrt.write("        <CLASS>"+vobj.getRecord().get("CLASS")+"</CLASS> \n");
                  owrt.write("        <METHOD>"+vobj.getRecord().get("METHOD")+"</METHOD> \n");
                  owrt.write("        <LOGDISP>"+vobj.getRecord().get("LOGDISP")+"</LOGDISP> \n");
                  owrt.write("      </EVENT> \n");

              }
          }
          owrt.write("    </MENU> \n");
          owrt.write("  </"+sysid+"> \n");
          owrt.write("</root> \n");

      }catch(Exception e){
          e.printStackTrace();
      }finally{
          try{
              owrt.close();
              fout.close();
          }catch(Exception e){
              e.printStackTrace();
          }
      }
  }
  public VOBJ getEventInfo(Connection Conn, String sysid) throws Exception
  {
      QExecutor qexe = new QExecutor();
      SQLObject  sobj = SQLPFC01ZT(sysid);
      qexe.DispSelectSql(sobj);
      VOBJ dvobj =  qexe.executeQuery(Conn, sobj);
      dvobj.setName("P");
      return dvobj;
  }
  private SQLObject SQLPFC01ZT(String sysid) throws Exception
  {
      SQLObject sobj = new SQLObject();
      String    query="";
      query +=" SELECT  PACKAGE,  CLASS  , METHOD \n";
      query +="         ,LABEL ,  LOGDISP, SYSID  \n";
      query +="         ,MENUID,  EVENTID         \n";
      query +=" FROM  PFC01ZT                     \n";
      query +=" WHERE  SYSID=:SYSID               \n";
      query +=" ORDER BY SYSID, MENUID,EVENTID              \n";
      sobj.setSql(query);
      sobj.setString("SYSID",sysid);
      return sobj;
  }

  private String _cvtChar(String data){
  //& : &amp;   ,  < : &lt; ,  > : &gt;  , ' : &apos; , " : &quot;
  //System.out.println("INPUT DATA:" + data);
  data = data.replaceAll("&","&amp;");
  data = data.replaceAll("<","&lt;");
  data = data.replaceAll(">","&gt;");
  data = data.replaceAll("'","&apos;");
  data = data.replaceAll("\"","&quot;");
  return data;
}



}


