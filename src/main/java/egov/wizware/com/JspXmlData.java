package egov.wizware.com;

import java.util.ArrayList;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import org.jdom.Document;
import java.util.Iterator;
import org.jdom.input.SAXBuilder;
import org.jdom.Element;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.io.StringReader;
public class JspXmlData {
    public JspXmlData() {
    }

    //-----OUT처리 시작
    /* xml output Layout
       <?xml version="1.0" encoding="euc-kr" ?>
       <root>
         <code>0</code>");
         <msg>ssssss</msg>");
         <OBJECTID>
           <row seq="ROW-NUMBER">
             <COL_NAME0> value </COL_NAME1>
             <COL_NAME1> value </COL_NAME1>
           </row>
         </OBJECTID>


         <OBJECTID>
           <row seq="ROW-NUMBER">
             <COL_NAME0> value </COL_NAME1>
             <COL_NAME1> value </COL_NAME1>
           </row>
         </OBJECTID>

       </root>
    */

    private String getXMLVobj(VOBJ vobj)
    {
        ArrayList _clist = vobj.getColumnNames();
        StringBuffer sbuf = new StringBuffer(100000);
        sbuf.append("<"+vobj.getName()+"> \n");
        vobj.first();
        while(vobj.next()){
            sbuf.append("  <row seq=\""+vobj.current+"\"> ");
            for(int i=0;i<_clist.size();i++)
            {
                sbuf.append("\n     <"+_clist.get(i).toString()+"> ");
                sbuf.append(_cvtChar(vobj.getRecord().get(_clist.get(i).toString())));
                sbuf.append("</"+_clist.get(i).toString()+">");
            }
            sbuf.append("\n  </row> \n");
        }
        sbuf.append("</"+vobj.getName()+"> \n");
        return sbuf.toString();
    }
    public StringBuffer getOutXmlData(DOBJ dobj)
    {
        StringBuffer sbuf = new StringBuffer(100000);
        sbuf.append("<?xml version=\"1.0\" encoding=\"euc-kr\" ?> \n");
        sbuf.append("<root> \n");
        sbuf.append("<code value=\""+dobj.getRtncode()+"\" /> \n");
        sbuf.append("<retmsg value=\""+dobj.getRetmsg()+"\" /> \n");
        ArrayList alist = dobj.getRetObjectKeys();
        VOBJ vobj = null;
        for(int i=0;i<alist.size();i++){
            vobj = dobj.getRetObject(alist.get(i).toString());
            if(vobj.getRetcode() ==1){
                sbuf.append(getXMLVobj(vobj));
            }
        }
        sbuf.append("</root>");

        System.out.println(sbuf.toString());
        return sbuf;
    }
    public StringBuffer outLogout()
    {
        StringBuffer xml = new StringBuffer(10000);
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        xml.append("<root>");
        xml.append("<code value=\"-99\" />");
        xml.append("<retmsg value=\"logout\" />");
        xml.append("</root>");
        return xml;
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


    //---------------------------------------OUT처리 종료


    /* INPUT XML Stream Layout
    <root>
       <target>
          <row>
            <SYSID value="DMFS" />
            <MENUID value="1000004003" />
            <EVENTID value="findSawon" />
          </row>
       </target>

       <S>
         <row status="I">
           <COLA> aaa </COLA>
           <COLB> aaa </COLB>
         </row>
       </S>

       <S1>
         <row status="I">
           <COLA> aaa </COLA>
           <COLB> aaa </COLB>
         </row>
       </S1>
     </root>
    */
    public DOBJ inputObject(InputStream in) throws Exception
    {
        DOBJ _dobj = _getXmlData( in);
        return _dobj;
    }
    private  DOBJ _getXmlData(InputStream in){

        DOBJ dobj = new DOBJ();
        VOBJ vobj = null;

        Document doc=null;
        SAXBuilder sax = new SAXBuilder();
        Element root = null;
        Element node = null;
        try{
            InputStreamReader inreader = new InputStreamReader(in, "UTF-8");
            doc = sax.build(inreader);
            root = doc.getRootElement();
            List list = root.getChildren();
            Iterator it = list.iterator();
            while(it.hasNext())
            {
                node = (Element)it.next();
                vobj = _getDataset(node);
                vobj.Println("INPUT");

                if(vobj != null)
                {
                    if(vobj.getName().equals("target"))
                    {
                        dobj.setParam(vobj.getHashRecord());
                    }
                    else
                    {
                        dobj.setRetObject(vobj);
                    }
                }
            }
        }catch(Exception je){
            je.printStackTrace();
        }
        return dobj;
    }
    public DOBJ inputObject(String in) throws Exception
    {
        DOBJ _dobj = _getXmlData( in);
        return _dobj;
    }
    private  DOBJ _getXmlData(String in){

        DOBJ dobj = new DOBJ();
        VOBJ vobj = null;

        Document doc=null;
        SAXBuilder sax = new SAXBuilder(true);

        Element root = null;
        Element node = null;
        try{
            sax.setValidation(true);

            doc = sax.build(in);
            root = doc.getRootElement();
            List list = root.getChildren();
            Iterator it = list.iterator();
            while(it.hasNext())
            {
                node = (Element)it.next();
                vobj = _getDataset(node);
                vobj.Println("INPUT");

                if(vobj != null)
                {
                    if(vobj.getName().equals("target"))
                    {
                        dobj.setParam(vobj.getHashRecord());
                    }
                    else
                    {
                        dobj.setRetObject(vobj);
                    }
                }
            }
        }catch(Exception je){
            je.printStackTrace();
        }
        return dobj;
    }

    public DOBJ inputObject(StringReader in) throws Exception
    {
        DOBJ _dobj = _getXmlData( in);
        return _dobj;
    }
    private  DOBJ _getXmlData(StringReader in){

        DOBJ dobj = new DOBJ();
        VOBJ vobj = null;

        Document doc=null;
        SAXBuilder sax = new SAXBuilder(true);

        Element root = null;
        Element node = null;
        try{
            sax.setValidation(true);

            doc = sax.build(in);
            root = doc.getRootElement();
            List list = root.getChildren();
            Iterator it = list.iterator();
            while(it.hasNext())
            {
                node = (Element)it.next();
                vobj = _getDataset(node);
                vobj.Println("INPUT");

                if(vobj != null)
                {
                    if(vobj.getName().equals("target"))
                    {
                        dobj.setParam(vobj.getHashRecord());
                    }
                    else
                    {
                        dobj.setRetObject(vobj);
                    }
                }
            }
        }catch(Exception je){
            je.printStackTrace();
        }
        return dobj;
    }

    public DOBJ inputObject(InputStreamReader in) throws Exception
    {
        DOBJ _dobj = _getXmlData( in);
        return _dobj;
    }
    private  DOBJ _getXmlData(InputStreamReader in){

        DOBJ dobj = new DOBJ();
        VOBJ vobj = null;

        Document doc=null;
        SAXBuilder sax = new SAXBuilder();
        Element root = null;
        Element node = null;
        try{
            doc = sax.build(in);

            root = doc.getRootElement();
            List list = root.getChildren();
            Iterator it = list.iterator();
            while(it.hasNext())
            {
                node = (Element)it.next();
                vobj = _getDataset(node);
                vobj.Println("INPUT");

                if(vobj != null)
                {
                    if(vobj.getName().equals("target"))
                    {
                        dobj.setParam(vobj.getHashRecord());
                    }
                    else
                    {
                        dobj.setRetObject(vobj);
                    }
                }
            }
        }catch(Exception je){
            je.printStackTrace();
        }
        return dobj;
    }


    private  VOBJ _getDataset(Element dset){  // dataset
        VOBJ rtn = new VOBJ();
        String dsname= dset.getName();   //dateset명
        rtn.setName(dsname);

        Element column = null;
        List clist = null;
        Iterator cit = null;

        Element row = null;
        List rlist = dset.getChildren();  //row list
        Iterator rit = rlist.iterator();
        while(rit.hasNext())
        {
            row = (Element)rit.next();
            HashMap rec = new HashMap();
            if(row.getAttributeValue("status") != null )
            {
                rec.put("IUDFLAG",row.getAttributeValue("status"));
            }


            clist = row.getChildren();  //row list
            for(int i=0;i<clist.size();i++)
            {
                column = (Element)clist.get(i);
                rec.put(column.getName(), column.getText());
            }
            rtn.addRecord(rec);
        }
        return rtn;
    }
    //-------------------------------------------------input 처리 종료
    //--------- CTL XML READ   //
    /*
    <?xml version="1.0" encoding="euc-kr" ?>
     <root>
       <TEST26>
         <MENU id="20101101101161">
           <EVENT id="saveSawon" label="사원저장"  MAKEDATE="20101115"  MAKEUSER="root" >
             <CLASS></CLASS>
             <HLFLAG></HLFLAG>
             <INCLIENT></INCLIENT>
             <RLTYPE>1</RLTYPE>
             <LGAUTH></LGAUTH>
             <RTNTYPE>1</RTNTYPE>
             <EJBNAME></EJBNAME>
             <DFLAG>0</DFLAG>
             <METHOD>saveSawon</METHOD>
             <JSPLOCATION></JSPLOCATION>
             <LGFILE></LGFILE>
             <LOGDISP>0</LOGDISP>
             <FLAGDEV></FLAGDEV>
             <PACKAGE>WIZ.EDU.DEMO</PACKAGE>
             <JSPNAME></JSPNAME>
             <DATEDEV></DATEDEV>
           </EVENT>
    */
   public VOBJ getPFCInfo(String sysid, String menuid, String eventid, String path) throws Exception
   {
       if(path.substring(path.length()-1).equals("/"))
       {
           path += sysid +"_" + menuid +".xml";
       }
       else
       {
           path += "/" + sysid +"_" + menuid +".xml";
       }
       FileInputStream fin = new FileInputStream(path);
       VOBJ _vobj = _getXmlData( fin, sysid, menuid, eventid);
       return _vobj;
    }
    public VOBJ getPFCInfo(String sysid, String menuid, String eventid) throws Exception
    {
        CONFIG _cfg = new CONFIG();
        String path = _cfg.getPathServiceEnv(sysid);
        if(path.substring(path.length()-1).equals("/"))
        {
            path += sysid +"_" + menuid +".xml";
        }
        else
        {
            path += "/" + sysid +"_" + menuid +".xml";
        }
        FileInputStream fin = new FileInputStream(path);
        VOBJ _vobj = _getXmlData( fin, sysid, menuid, eventid);
        return _vobj;
    }
    private  VOBJ _getXmlData(InputStream in, String sysid, String menuid, String eventid)
    {
        VOBJ vobj = null;
        Document doc=null;
        SAXBuilder sax = new SAXBuilder();
        Element root = null;
        Element node = null;
        try{
            InputStreamReader inreader = new InputStreamReader(in, "UTF-8");
            doc = sax.build(inreader);
            root = doc.getRootElement();   //root
            List list = root.getChildren();
            Iterator it = list.iterator();
            while(it.hasNext())
            {
                node = (Element)it.next();   //sysid
                vobj = _getMenuNode(node, menuid, eventid);
                vobj.Println("INPUT");
            }
        }catch(Exception je){
            je.printStackTrace();
        }
        return vobj;
    }

    private  VOBJ _getMenuNode(Element dset, String menuid , String eventid)
    {  // root dataset
        VOBJ rtn = new VOBJ();
        Element row = null;
        List rlist = dset.getChildren();
        Iterator rit = rlist.iterator();
        while(rit.hasNext())
        {
            row = (Element)rit.next();
            if(row.getAttributeValue("id").equals(menuid))
            {
                rtn = _getEventNode(row,  eventid);
                break;
            }
        }
        return rtn;
    }
    private  VOBJ _getEventNode(Element dset, String eventid)
    {  // root dataset
        VOBJ rtn = new VOBJ();

        Element column = null;
        List clist = null;

        Element row = null;
        List rlist = dset.getChildren();  //row list
        Iterator rit = rlist.iterator();
        while(rit.hasNext())
        {
            row = (Element)rit.next();
            if(row.getAttributeValue("id").equals(eventid))
            {
                HashMap rec = new HashMap();
                clist = row.getChildren();
                for(int i=0;i<clist.size();i++)
                {
                    column = (Element)clist.get(i);
                    rec.put(column.getName(), column.getText());
                }
                rtn.addRecord(rec);
                break;
            }
        }
        return rtn;
    }
    //-----------------




    //----------------------------------------------------------Debug 용---
    public StringBuffer inputRequest(HttpServletRequest req)
    {
        System.out.println("======DISPLAY REQUEST INPUT STREAM=====");
        StringBuffer inbuf = new StringBuffer(10000);
        try {

            InputStreamReader inx = new InputStreamReader(req.getInputStream(), "UTF-8");
            char[] buffer = new char[1024];
            String str ="";
            int readcount = 0;
            while((readcount = inx.read(buffer)) != -1)
            {
                if(readcount < 1024) {
                    str = getString(buffer, readcount);
                    inbuf.append(str);
                }
                else
                {
                    inbuf.append(buffer);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println(inbuf);
        return inbuf;
    }
    public StringBuffer dispRequest(HttpServletRequest req)
    {
        System.out.println("======DISPLAY REQUEST INPUT xxxxxx=====");
        StringBuffer inbuf = new StringBuffer(10000);
        try {
            DataInputStream in = new DataInputStream(req.getInputStream());
            String str="";
            int i = 0;
            while ( (str = in.readLine()) != null) {
                System.out.println(str);
                inbuf.append(str);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return inbuf;
    }
    private String getString(char[] buffer, int cnt)
    {
        String str ="";
        for(int i=0;i<cnt;i++){
            str += buffer[i];
        }
        return str;
    }

}
