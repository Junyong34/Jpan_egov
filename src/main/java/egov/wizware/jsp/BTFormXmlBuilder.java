package egov.wizware.jsp;

import egov.wizware.com.*;
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
import org.jdom.Attribute;
import java.io.InputStreamReader;
import java.io.DataInputStream;

public class BTFormXmlBuilder {

    public BTFormXmlBuilder()
    {
    }

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
        try
        {
            InputStreamReader inreader = new InputStreamReader(in, "UTF-8");
            doc = sax.build(inreader);
            root = doc.getRootElement();
            //------------------------
            String outdatasets = root.getAttribute("outdatasets").getValue();
            if(outdatasets !=null)
            {
                String[] outds = outdatasets.split(",");
                dobj.setOutkeys(outds);
            }
            //------------------------

            List list = root.getChildren();
            Iterator it = list.iterator();
            while(it.hasNext())
            {
                node = (Element)it.next();
                if(node.getName().equals("init"))
                {
                    List list_init = node.getChildren();
                    Iterator it_init = list_init.iterator();
                    Element node_init = null;
                    while (it_init.hasNext())
                    {
                        node_init = (Element)it_init.next();
                        vobj = _getDataset(node_init);
                        if(vobj != null)
                        {
                            vobj.setName("init_" + vobj.getName());
                            dobj.setRetObject(vobj);
                        }
                    }
                }
                else
                {
                    vobj = _getDataset(node);
                    if(vobj != null) dobj.setRetObject(vobj);
                }
            }
        }
        catch(Exception je)
        {
            je.printStackTrace();
        }

        ArrayList klist = dobj.getRetObjectKeys();
        String[] inkey = new String[klist.size()];
        for(int i=0;i<klist.size();i++)
        {
            inkey[i] = klist.get(i).toString();
        }
        dobj.setInkeys(inkey);
        return dobj;
    }
    private  VOBJ _getDataset(Element ele)
    {
        Element row = null;
        HashMap rmap = null;
        Attribute cattr = null;

        String dsname= ele.getName();
        VOBJ vobj = new VOBJ();
        vobj.setName(dsname);

        List rowlist = ele.getChildren();
        for(int idx=0;idx<rowlist.size();idx++)
        {
            row = (Element)rowlist.get(idx);
            rmap = new HashMap();
            for(int i=0;i<row.getAttributes().size();i++)
            {
                cattr = (Attribute)row.getAttributes().get(i);
                rmap.put(cattr.getName(),cattr.getValue());
            }
            vobj.addRecord(rmap);
        }
        return vobj;
    }
    private String[] getDataset(String str)
    {
        int idx = -1;
        str = str.trim();
        String[] strinfo = {"",""};
        if( (idx = str.indexOf(":"))==-1)
        {
            strinfo[0] = str;
        }
        else
        {
            strinfo[0] = str.substring(0,idx).trim();
            strinfo[1] = str.substring(idx+1).trim();
        }
        return strinfo;
    }
    private StringBuffer getOutRowXml(ArrayList clist,  HashMap rmap)
    {
        rmap.put("IUDFLAG","");
        StringBuffer sbuf = new StringBuffer(1000);
        sbuf.append("<row");
        for(int i=0;i<clist.size();i++)
        {
            sbuf.append(" " + clist.get(i) + "=\"" + rmap.get(clist.get(i)) +"\"");
        }
        sbuf.append(" /> ");
        return sbuf;
    }
    private StringBuffer getDatasetXml(VOBJ vobj, String name)
    {
        StringBuffer sbuf = new StringBuffer();
        sbuf.append("<" + name +">");
        ArrayList clist = vobj.getColumnNames();
        vobj.first();
        while(vobj.next())
        {
           sbuf.append( getOutRowXml(clist,  vobj.getRecord().getRecord()) );
        }
        sbuf.append("</"+name+"> ");
        return sbuf;
    }
    private StringBuffer getDatasetXml(VOBJ vobj)
    {
        return getDatasetXml(vobj, vobj.getName());
    }


    public StringBuffer outputObject(DOBJ dobj)  throws Exception
    {
        System.out.println("=====================out===================");
        StringBuffer xml = new StringBuffer();
        xml.append("<DataSet>");

        String dsname="";
        String outtype="";
        String[] outds = null;
        VOBJ _vobj = null;
        for(int i=0;i<dobj.getOutkeys().length;i++)
        {
            outds = getDataset(dobj.getOutkeys()[i]);
            dsname = outds[0];
            outtype = outds[1];  //"S" : Single Nodeset, "G" : Grid Nodeset

            System.out.println(dsname +"=======" + outtype);
            if(dobj.containKey(dsname))
            {
                _vobj = dobj.getRetObject(dsname);
                if(_vobj.getRecordCnt() == 0 && outtype.equals("S"))
                {
                    _vobj = dobj.getRetObject("init_"+dsname);
                    xml.append(getDatasetXml(_vobj, dsname));
                }
                else
                {
                    xml.append(getDatasetXml(_vobj));
                }
            }
            else
            {
                if(dobj.containKey("init_"+dsname))
                {
                    _vobj = dobj.getRetObject("init_"+dsname);
                    xml.append(getDatasetXml(_vobj, dsname));
                }
            }
        }

        /*
        xml.append("<SEL1>");
        xml.append("<row NAME=\"a\" CODE=\"S-a\" LEVEL=\"A\"  IUDFLAG=\"\" />");
        xml.append("<row NAME=\"b\" CODE=\"S-b\" LEVEL=\"B\"  IUDFLAG=\"\" />");
        xml.append("<row NAME=\"b\" CODE=\"S-b\" LEVEL=\"B\"  IUDFLAG=\"\" />");
        xml.append("<row NAME=\"b\" CODE=\"S-b\" LEVEL=\"B\"  IUDFLAG=\"\" />");
        xml.append("</SEL1>");
        xml.append("<SEL2>");
        xml.append("<row NAME=\"a\" CODE=\"S-a\" LEVEL=\"A\" IUDFLAG=\"\" />");
        xml.append("<row NAME=\"b\" CODE=\"S-b\" LEVEL=\"B\" IUDFLAG=\"\" />");
        xml.append("</SEL2>");
        xml.append("<SEL3>");
        xml.append("<row NAME=\"a\" CODE=\"S-a\" LEVEL=\"A\" IUDFLAG=\"\" />");
        xml.append("<row NAME=\"b\" CODE=\"S-b\" LEVEL=\"B\" IUDFLAG=\"\" />");
        xml.append("</SEL3>");
        */
        xml.append("</DataSet>");
        //System.out.println("write xml:"+xml.toString());
        return xml;
    }

    public StringBuffer outLogout()
    {
         StringBuffer xml = new StringBuffer(10000);
         xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
         xml.append("<hashtable>");
         xml.append("<message>");
         xml.append("<code value=\"-99\" />");
         xml.append("<retmsg value=\"logout\" />");
         xml.append("</message>");
         xml.append("</hashtable>");
         return xml;
     }

    private String _cvtChar(String data)
    {
        //& : &amp;   ,  < : &lt; ,  > : &gt;  , ' : &apos; , " : &quot;
        //System.out.println("INPUT DATA:" + data);
        data = data.replaceAll("&","&amp;");
        data = data.replaceAll("<","&lt;");
        data = data.replaceAll(">","&gt;");
        data = data.replaceAll("'","&apos;");
        data = data.replaceAll("\"","&quot;");
        //System.out.println("CVT CHAR DATA:" + data);
        return data;
    }
    public void dispRequest(InputStream inx)
    {
        try {

            DataInputStream in = new DataInputStream(inx);
            String str="";
            int i = 0;
            while ( (str = in.readLine()) != null)
            {
                System.out.println(str);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}


