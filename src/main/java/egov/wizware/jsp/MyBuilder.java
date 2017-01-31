package egov.wizware.jsp;
import egov.wizware.com.*;
import java.io.*;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

public class MyBuilder
{
    private boolean debugon = true;
    public MyBuilder()
    {
    }

    private String getXMLVobj(VOBJ vobj)
    {
        ArrayList _clist = vobj.getColumnNames();
        StringBuffer sbuf = new StringBuffer(100000);
        sbuf.append((new StringBuilder("<")).append(vobj.getName()).append("> \n").toString());
        //sbuf.append("<" + vobj.getName() + "> \n");
        vobj.first();
        for(; vobj.next(); sbuf.append("\n  </row> \n"))
        {
            sbuf.append((new StringBuilder("<row seq=\"")).append(vobj.current).append("\">").toString());
            for(int i = 0; i < _clist.size(); i++)
            {
                sbuf.append((new StringBuilder("\n <")).append(_clist.get(i).toString()).append(">").toString());
                sbuf.append(_cvtChar(vobj.getRecord().get(_clist.get(i).toString())));
                sbuf.append((new StringBuilder("</")).append(_clist.get(i).toString()).append(">").toString());
            }
        }
        sbuf.append((new StringBuilder("</")).append(vobj.getName()).append("> \n").toString());
        return sbuf.toString();
    }

    public StringBuffer outputObject(DOBJ dobj) throws Exception
    {
        StringBuffer sbuf = new StringBuffer(100000);
        sbuf.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?> \n");
        sbuf.append("<data> \n");
        sbuf.append((new StringBuilder("<code>")).append(dobj.getRtncode()).append("</code> \n").toString());
        sbuf.append((new StringBuilder("<retmsg>")).append(dobj.getRetmsg()).append("</retmsg> \n").toString());
        ArrayList alist = dobj.getRetObjectKeys();
        VOBJ vobj = null;
        for(int i = 0; i < alist.size(); i++)
        {
            vobj = dobj.getRetObject(alist.get(i).toString());
            if(vobj.getRetcode() == 1)
                sbuf.append(getXMLVobj(vobj));
        }
        sbuf.append("</data>");
        //if(debugon)  System.out.println(sbuf.toString());
        return sbuf;
    }
    public StringBuffer outLogout()
    {
        StringBuffer xml = new StringBuffer(1000);
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n");
        xml.append("<data> \n");
        xml.append("<code>-99</code> \n");
        xml.append("<retmsg>logout</retmsg> \n");
        xml.append("</data>");
        if(debugon)  System.out.println(xml);
        return xml;
    }

    private String _cvtChar(String data)
    {
        data = data.replaceAll("&", "&amp;");
        data = data.replaceAll("<", "&lt;");
        data = data.replaceAll(">", "&gt;");
        data = data.replaceAll("'", "&apos;");
        data = data.replaceAll("\"", "&quot;");
        return data;
    }

    public DOBJ inputObject(HttpServletRequest request)   throws Exception
    {
        DOBJ dobj = new DOBJ();
        String xmlData = "";

        HashMap rec = null;
        String parmname ="";
        Enumeration e = request.getParameterNames();
        while (e.hasMoreElements())
        {
           parmname = e.nextElement().toString();
           System.out.println("Input Parameter Name:" + parmname);
           if(!parmname.equals("xmlData") && !parmname.equals("xmlType") )
           {
               if(rec == null) rec = new HashMap();
               rec.put(parmname, request.getParameter(parmname));
           }
        }
        if(rec != null)
        {
            VOBJ _vobjs = new VOBJ();
            _vobjs.setName("S");
            _vobjs.addRecord(rec);
            dobj.setRetObject(_vobjs);
        }

        if(request.getParameter("xmlData") == null)
        {
            System.out.println("***********   xmlData Parameter Not Found(Input Data set Not Found) ****************");
            return dobj;
        }

        xmlData = request.getParameter("xmlData").trim();
        if(debugon) System.out.println(xmlData);
        if(!xmlData.equals(""))
        {
            ByteArrayInputStream bin = new ByteArrayInputStream(xmlData.getBytes("UTF-8"));
            dobj = _getXmlData(bin, request.getParameter("xmlType"));
        }

        if(debugon)
        {
            System.out.println("====[ INPUT VOBJ DISPLAY ]===");
            String name = "";
            ArrayList<?> inlist = dobj.getRetObjectKeys();
            for(int i=0; i<inlist.size(); i++)
            {
                name = inlist.get(i).toString();
                VOBJ vobj = dobj.getRetObject(name);
                vobj.Println("INPUT");
            }
        }
        return dobj;
    }

    //
    private DOBJ _getXmlData(InputStream in, String single)
    {
        DOBJ dobj = new DOBJ();
        VOBJ vobj = null;
        Document doc = null;
        SAXBuilder sax = new SAXBuilder();
        Element root = null;
        Element node = null;
        ArrayList records = new ArrayList();
        HashMap rec = null;
        try
        {
            InputStreamReader inreader = new InputStreamReader(in, "UTF-8");
            doc = sax.build(inreader);
            root = doc.getRootElement();
            List list = root.getChildren();
            for(Iterator it = list.iterator(); it.hasNext();)
            {
                node = (Element)it.next();
                if(node.getName().equals("row"))
                {
                    rec = this._getRecordRow(node);
                    records.add(rec);
                }
                else
                {
                    vobj = _getDataset(node);
                    dobj.setRetObject(vobj);
                }
            }

            if(records.size() != 0)
            {
                VOBJ vobjs = new VOBJ();
                if(single !=null && !single.trim().equals(""))
                {
                    vobjs.setName(single.toUpperCase());
                }
                else
                {
                    vobjs.setName("S1");
                }
                vobjs.setRecords(records);
                dobj.setRetObject(vobjs);
            }
        }
        catch(Exception je)
        {
            je.printStackTrace();
        }

        /*/------------------INPUT DATASET에 대한 보정처리한 이유 ???? ---------
        ArrayList klist = dobj.getRetObjectKeys();
        String inkey[] = new String[klist.size()];
        for(int i = 0; i < klist.size(); i++)
        {
            inkey[i] = klist.get(i).toString();
        }
        dobj.setInkeys(inkey);
        //------------------------------------------------------------------
        */

        return dobj;
    }

    private VOBJ _getDataset(Element dset)
    {
        VOBJ rtn = new VOBJ();
        String dsname = dset.getName().toUpperCase();
        rtn.setName(dsname);
        Element column = null;
        List clist = null;
        Iterator cit = null;
        Element row = null;
        List rlist = dset.getChildren();
        HashMap rec;
        for(Iterator rit = rlist.iterator(); rit.hasNext(); rtn.addRecord(rec))
        {
            row = (Element)rit.next();
            rec = new HashMap();
            String iudflag = null;
            clist = row.getChildren();
            for(int i = 0; i < clist.size(); i++)
            {
                column = (Element)clist.get(i);
                if(column.getName() != null && column.getName().equals("_status_"))
                {
                    iudflag = column.getText();
                    if(iudflag.equals("*"))
                        iudflag = "U";
                    else      if(iudflag.equals("-"))
                        iudflag = "D";
                    else   if(iudflag.equals("+"))
                        iudflag = "I";
                    else
                        iudflag = "";
                }
                else
                {
                    rec.put(column.getName(), column.getText());
                }
            }
            if(iudflag != null)
                rec.put("IUDFLAG", iudflag);
        }
        return rtn;
    }

    //<row>
    private HashMap _getRecordRow(Element row)
    {
        Element column = null;
        List clist = null;
        HashMap rec= new HashMap();
        String iudflag = null;
        clist = row.getChildren();
        for(int i = 0; i < clist.size(); i++)
        {
            column = (Element)clist.get(i);
            if(column.getName() != null && column.getName().equals("_status_"))
            {
                iudflag = column.getText();
                if(iudflag.equals("*"))
                    iudflag = "U";
                else
                if(iudflag.equals("-"))
                    iudflag = "D";
                else
                if(iudflag.equals("+"))
                    iudflag = "I";
                else
                    iudflag = "";
            }
            else
            {
                rec.put(column.getName(), column.getText());
            }
        }
        if(iudflag != null)
            rec.put("IUDFLAG", iudflag);

        return rec;
    }



    public StringBuffer inputRequest(HttpServletRequest req)
    {
        System.out.println("======DISPLAY REQUEST INPUT STREAM=====");
        StringBuffer inbuf = new StringBuffer(10000);
        try
        {
            InputStreamReader inx = new InputStreamReader(req.getInputStream(), "UTF-8");
            char buffer[] = new char[1024];
            String str = "";
            for(int readcount = 0; (readcount = inx.read(buffer)) != -1;)
            {
                if(readcount < 1024)
                {
                    str = getString(buffer, readcount);
                    inbuf.append(str);
                }
                else
                {
                    inbuf.append(buffer);
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        System.out.println(inbuf);
        return inbuf;
    }
    public StringBuffer dispRequest(HttpServletRequest req)
    {
        System.out.println("======DISPLAY REQUEST INPUT xxxxxx=====");
        StringBuffer inbuf = new StringBuffer(10000);
        try
        {
            DataInputStream in = new DataInputStream(req.getInputStream());
            String str = "";
            int i = 0;
            while((str = in.readLine()) != null)
            {
                System.out.println(str);
                inbuf.append(str);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return inbuf;
    }
    private String getString(char buffer[], int cnt)
    {
        String str = "";
        for(int i = 0; i < cnt; i++)
        {
            str = (new StringBuilder(String.valueOf(str))).append(buffer[i]).toString();
        }
        return str;
    }
}
