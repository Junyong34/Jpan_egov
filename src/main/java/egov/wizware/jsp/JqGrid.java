package egov.wizware.jsp;
import egov.wizware.com.*;

import java.io.*;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import net.sf.json.*;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

public class JqGrid
{
    private boolean debugon = true;
    public JqGrid()
    {
    }

    private String getXMLVobj(VOBJ vobj)
    {
        ArrayList _clist = vobj.getColumnNames();
        StringBuffer sbuf = new StringBuffer(100000);
        sbuf.append((new StringBuilder("<")).append(vobj.getName()).append("> \n").toString()); //SEL OBJ
        sbuf.append((new StringBuilder("\n  <page")).append(">").toString());
        sbuf.append((new StringBuilder("1")).toString());
        sbuf.append((new StringBuilder("</page")).append(">").toString());
        
        sbuf.append((new StringBuilder("  <total")).append(">").toString());
        sbuf.append((new StringBuilder("60")).toString());
        sbuf.append((new StringBuilder("</total")).append(">\n").toString());
        
        sbuf.append((new StringBuilder("\n  <records")).append(">").toString());
        sbuf.append((new StringBuilder("300")).toString());
        sbuf.append((new StringBuilder("</records")).append(">\n").toString());

      
        vobj.first();
        for(; vobj.next(); sbuf.append("\n</row> \n"))
        {
            sbuf.append((new StringBuilder("<row id=\"")).append(vobj.current).append("\">").toString());
            sbuf.append((new StringBuilder("\n <ItemAttributes")).append(">").toString());
            
            sbuf.append((new StringBuilder("\n  <id")).append(">").toString());
            sbuf.append((new StringBuilder("")).append(vobj.current+1).toString());
            sbuf.append((new StringBuilder("</id")).append(">").toString());

            for(int i = 0; i < _clist.size(); i++)
            {
   
            	  sbuf.append((new StringBuilder("\n  <")).append(_clist.get(i).toString()).append(">").toString());
                  sbuf.append(_cvtChar(vobj.getRecord().get(_clist.get(i).toString())));
                  sbuf.append((new StringBuilder("</")).append(_clist.get(i).toString()).append(">").toString());
            }
            sbuf.append((new StringBuilder("\n </ItemAttributes")).append(">").toString());
        }
        sbuf.append((new StringBuilder("</")).append(vobj.getName()).append("> \n").toString()); //SEL OBJ
        return sbuf.toString();
    }
    // JQBRID JSON 
     private String getJsonVobj(VOBJ vobj) {
    ArrayList _clist = vobj.getColumnNames();
    StringBuffer sbuf = new StringBuffer(100000);
    
    sbuf.append(new StringBuilder(" \"page\" :").append("\"1\","));
    sbuf.append(new StringBuilder("\"total\" :").append("\"200\","));
    sbuf.append(new StringBuilder("\"records\" :").append("\"").append(vobj.getRecordCnt()).append("\" ,\n"));
    sbuf.append((new StringBuilder("\"")).append(vobj.getName()).append("\":[ \n").toString()); //SEL OBJ


    int theSize = _clist.size() - 1;
    int theSize2 = vobj.getColumnCnt();

    vobj.first();

    for (; vobj.next(); sbuf.append("},\n"))
    {
      sbuf.append("{ \"id\":\"" + vobj.current + "\" ");

      for (int i = 0; i < _clist.size(); i++)
      {
        sbuf.append("  \"" + _clist.get(i).toString() + "\":");
          sbuf.append("  \"" + _cvtChar(vobj.getRecord().get(_clist.get(i))) + "\",");
        

      }

    }

    sbuf.append("],\n").toString();

    return sbuf.toString();
  }

  
  //실행
    public StringBuffer outputObject(DOBJ dobj) throws Exception
    {
        StringBuffer sbuf = new StringBuffer(100000);
        sbuf.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?> \n");
        sbuf.append("<rows> \n");
        ArrayList alist = dobj.getRetObjectKeys();
        VOBJ vobj = null;
        for(int i = 0; i < alist.size(); i++)
        {
            vobj = dobj.getRetObject(alist.get(i).toString());
            if(vobj.getRetcode() == 1)
                sbuf.append(getXMLVobj(vobj));
        }
        sbuf.append("</rows>");
        //if(debugon)  System.out.println(sbuf.toString());
        return sbuf;
    }
    //TEST TREE VIEW
    public StringBuffer outputObject2(DOBJ dobj) throws Exception
    {
        StringBuffer sbuf = new StringBuffer(100000);
      
        sbuf.append("[					{ id:1, pId:0, name:'pNode 1', open:true},					{ id:11, pId:1, name:'pNode 11'},					{ id:111, pId:11, name:'leaf node 111'},					{ id:112, pId:11, name:'leaf node 112'},					{ id:113, pId:11, name:'leaf node 113'},					{ id:114, pId:11, name:'leaf node 114'},					{ id:12, pId:1, name:'pNode 12'},					{ id:121, pId:12, name:'leaf node 121'},					{ id:122, pId:12, name:'leaf node 122'},					{ id:123, pId:12, name:'leaf node 123'},					{ id:124, pId:12, name:'leaf node 124'},					{ id:13, pId:1, name:'pNode 13 - no child', isParent:true},					{ id:2, pId:0, name:'pNode 2'},					{ id:21, pId:2, name:'pNode 21', open:true},					{ id:211, pId:21, name:'leaf node 211'},					{ id:212, pId:21, name:'leaf node 212'},					{ id:213, pId:21, name:'leaf node 213'},					{ id:214, pId:21, name:'leaf node 214'},					{ id:22, pId:2, name:'pNode 22'},					{ id:221, pId:22, name:'leaf node 221'},					{ id:222, pId:22, name:'leaf node 222'},					{ id:223, pId:22, name:'leaf node 223'},					{ id:224, pId:22, name:'leaf node 224'},					{ id:23, pId:2, name:'pNode 23'},					{ id:231, pId:23, name:'leaf node 231'},					{ id:232, pId:23, name:'leaf node 232'},					{ id:233, pId:23, name:'leaf node 233'},					{ id:234, pId:23, name:'leaf node 234'},					{ id:3, pId:0, name:'pNode 3 - no child', isParent:true}]");
        //if(debugon)  System.out.println(sbuf.toString());
        return sbuf;
    }
    //jSON 실행
    public StringBuffer JsonoutputObject(DOBJ dobj) throws Exception
    {
    	 StringBuffer sbuf = new StringBuffer(100000);
    	 ArrayList alist = dobj.getRetObjectKeys();
    	 VOBJ vobj = null;
    	 //System.out.println(alist.size()+"@@@"+alist.get(1).toString()+"$$$$"+alist.get(2).toString());
    	// sbuf.append("");
    	  sbuf.append("{\n").toString();
    	 for(int i = 0; i < alist.size(); i++)
         {
             vobj = dobj.getRetObject(alist.get(i).toString());
             if(vobj.getRetcode() == 1)
                 sbuf.append(getJsonVobj(vobj));
         }
    	 sbuf.append("}\n").toString();
    	 return sbuf;
    }
    
    public StringBuffer outLogout()
    {
    	/*
	   <row seq="96">
		 <BIGO></BIGO>
		 <JIKGEUPCD>03</JIKGEUPCD>
		 <SEX>남자</SEX>
		 <IPSADATE>20150420</IPSADATE>
		 <SAWONNO>00095</SAWONNO>
		 <DEPTCD>2015</DEPTCD>
		 <JUMINNO>900778176399</JUMINNO>
		 <SAWONNM>호랑이</SAWONNM>
		 <ADDR>경남 마산시 회원2동</ADDR>
		 <MARRYYN>Y</MARRYYN>
		  </row> 
		  
		    <row id='2'>
			    <cell></cell>
			    <cell>12</cell>
			    <cell>2007-10-06</cell>
			    <cell><![CDATA[Client 2]]></cell>
			    <cell>70102500.00</cell>
			    <cell>140100.00</cell>
			    <cell>840.00</cell>
			    <cell><![CDATA[2]]></cell>
		 </row>
    	 *
    	 */
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

        /*/------------------INPUT DATASET�� ���� ����ó���� ���� ???? ---------
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
