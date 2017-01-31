package egov.wizware.ria;
import egov.wizware.com.*;

import java.io.*;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.*;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.ibatis.scripting.xmltags.TrimSqlNode;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.JSONWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.springframework.web.bind.ServletRequestUtils;

import com.gauce.gsaf.kernel.J;

import antlr.Parser;

public class EzMaker
{
    private boolean debugon = false;
    public EzMaker()
    {
    }

    /*

    {
    "paramwizwre":[
    {
      "Code":"-1", 
      "Msg":"",
      "recordset":
      [ 
        {
          "rowCount":10,
          "outDsList":"SEL1",
          "fieldList":["DEPT_NM", "DEPT_NO", "NO", "NM"],
          "recordList":
              [
                {"DEPT_NM":"망원현대점","DEPT_NO":"202767","NO":"335512","NM":"2.5RT천정형"},
                {"DEPT_NM":"망원현대점","DEPT_NO":"202767","NO":"335512","NM":"2.5RT천정형"}
              ]
          },
          {
            "rowCount":1,
            "outDsList":"SEL2",
             "fieldList":["DEPT_NM", "DEPT_NO", "NO", "NM"],
            "recordList":
            [
              {"DEPT_NM":"망원현대점","DEPT_NO":"202767","NO":"335512","NM":"2.5RT천정형"}
            ]
          }
        ]
      }
    ]
      }
      

        */
    
    private String _Escape(String data)
    {
    	
//    	data = drata.replaceAll("\"", "\\\\\"");
    	data = data.replaceAll("\\\"", "\\\\\"");
    	data = data.replaceAll("\n", "<BR>");
    	data = data.replaceAll("\r", "<BR2>");
        return data;
    }
    // ezmaker JSON 박준용 output
    private String getJsonVobj(VOBJ vobj)
    {
    	
         ArrayList _clist = vobj.getColumnNames();
         int VobjSize =  _clist.size();
         StringBuffer sbuf = new StringBuffer(100000);
         sbuf.append(new StringBuilder("\"rowCount\" :").append(vobj.getRecordCnt()).append(" ,")); // row count
         sbuf.append((new StringBuilder("\"outDsList\" :")).append("\"").append(vobj.getName()).append("\",").toString()); //SEL OBJ
         sbuf.append((new StringBuilder("\"fieldList\" :")).append("[")); //column name start
         
         for (int i = 0; i < VobjSize; i++)
         {
        	  
             sbuf.append("  \"" + _clist.get(i).toString() + "\",");
            
         }
         
        // sbuf.append("  \"" + _clist.get(VobjSize).toString() + "\" ");
         sbuf.append("],"); //column name end
        
         sbuf.append(new StringBuilder("\"recordList\" :").append(""));
         sbuf.append("[ ");
         vobj.first();
         for (; vobj.next(); sbuf.append("},"))
         {
             sbuf.append("{");
             for (int i = 0; i <VobjSize; i++)
             {
                 sbuf.append("  \"" + _clist.get(i).toString() + "\":");
                 sbuf.append("  \"" +  _Escape(vobj.getRecord().get(_clist.get(i))) + "\",");
                 //sbuf.append("  \"" +  vobj.getRecord().get(_clist.get(i)) + "\",");
                 // sbuf.append("  \"" + _cvtChar(vobj.getRecord().get(_clist.get(i))) + "\",");
             }
         }
        
         sbuf.append("]").toString();
        // System.out.println("=======================  \n " + sbuf.toString() +"\n=======================   " );
         return sbuf.toString();
     }

    


    
    //jSON  박준용 output
    public StringBuffer JsonoutputObject(DOBJ dobj) throws Exception
    {
    	
    	 StringBuffer sbuf = new StringBuffer(100000);
    	 ArrayList alist = dobj.getRetObjectKeys();
    	 VOBJ vobj = null;
         sbuf.append("{").toString();
          sbuf.append(" \"paramwizware\": ");
          sbuf.append("{").toString();
         sbuf.append(new StringBuilder(" \"Code\" :").append("\""+dobj.getRtncode()+"\",")); //CODE 에러일경우 -1
         sbuf.append(new StringBuilder(" \"Msg\" :").append("\""+dobj.getRetmsg()+"\" ,"));   // 메시지
         sbuf.append(" \"recordset\" : [").toString();
         
       
     //   System.out.println("@@@@@@@@ 메시지 : "  + dobj.getDetailMessage() + " @@ " + dobj.getErrorMessage() + " # " + dobj.getRetmsg() + " $ " );
    	 for(int i = 0; i < alist.size(); i++)
         {	
    		 
             vobj = dobj.getRetObject(alist.get(i).toString());
            // System.out.println(" vobj get(i) 0부터 : " +  vobj.getRetcode() +  " @@ " + vobj.getName());
             if(vobj.getRetcode() == 1)            	 
             {	
            	 sbuf.append("{").toString();
                 sbuf.append(getJsonVobj(vobj));             
                 sbuf.append("},");
             }
            
         }
    	
    	 sbuf.append("]");
    	 sbuf.append("}");
    	 //sbuf.append("]\n");
    	 sbuf.append("}");
    	 System.out.println("Json  \n" +  sbuf.toString());
         //dobj.setRtncode(1);
         //dobj.setRetmsg(object);
    	 return sbuf;
    }

   
    


    public StringBuffer outLogout()
    {
    	/*
	   <row seq="96">
		 <BIGO></BIGO>
		 <JIKGEUPCD>03</JIKGEUPCD>
		 <SEX>\uFFFD??\uFFFD??</SEX>
		 <IPSADATE>20150420</IPSADATE>
		 <SAWONNO>00095</SAWONNO>
		 <DEPTCD>2015</DEPTCD>
		 <JUMINNO>900778176399</JUMINNO>
		 <SAWONNM>\uFFFD??\uFFFD??\uFFFD??</SAWONNM>
		 <ADDR>경남 마산\uFFFD?? \uFFFD??\uFFFD??2\uFFFD??</ADDR>
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
 // ezmaker JSON 박준용 input
    public DOBJ inputJsonObject(HttpServletRequest request)   throws Exception
    {
    	 DOBJ dobj = new DOBJ();
         String xmlData = "";

         HashMap rec = null;
         String parmname ="";
         VOBJ vobj = null;
         
         String jsonObj = request.getParameter("paramwizware");
         String callback = request.getParameter("callback");

        /* if(rec != null)
         {
             VOBJ _vobjs = new VOBJ();
             _vobjs.setName("S");
             _vobjs.addRecord(rec);
             dobj.setRetObject(_vobjs);
         }*/
       //  System.out.println(jsonObj.toString() + " @@@ "  +callback);
         if(request.getParameter("paramwizware") == null)
         {
             System.out.println("***********   Json Parameter Not Found(Input Data set Not Found) ****************");
             return dobj;
         }
         if(debugon) System.out.println(jsonObj);
         if(!jsonObj.equals(""))
         {
        	 JSONParser Jparser = new JSONParser();
             org.json.simple.JSONObject Jobj = (org.json.simple.JSONObject)Jparser.parse(jsonObj);
             JSONArray Jarray = (JSONArray)Jobj.get("paramwizware");
            // System.out.println(Jarray.toJSONString() + " =====================");
             dobj = _getJsonData(Jarray);

            // System.out.println( "이지메이커 Input 데이타 " + Jarray.toJSONString());
 			
         }

         if(debugon)
         {
             System.out.println("====[ INPUT VOBJ DISPLAY ]===");
             String name = "";
             ArrayList<?> inlist = dobj.getRetObjectKeys();
             for(int i=0; i<inlist.size(); i++)
             {
                 name = inlist.get(i).toString();
                
                 dobj.getRetObject(name).Println(name);;
                 dobj.dispRetObjectKeyNames();
                 
             }
            
         }
         return dobj;
    
    }
    public DOBJ inputObject(HttpServletRequest request)   throws Exception
    {
        DOBJ dobj = new DOBJ();
        String xmlData = "";

        HashMap rec = null;
        String parmname ="";
        Enumeration e = request.getParameterNames();
        
        JSONObject jsonObject = new JSONObject(request.getParameter("paramwizware"));
        
         
        // System.out.println(jsonObject.toString() +  " @@ " );
      
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
 // ezmaker JSON 박준용
	private DOBJ _getJsonData(JSONArray Jarray) {
		DOBJ dobj = new DOBJ();
		VOBJ vobj;
		Document doc = null;
		SAXBuilder sax = new SAXBuilder();
		Element root = null;
		Element node = null;
		ArrayList records = new ArrayList();
		HashMap  rec;
		try {
			
			
			for (int i = 0; i < Jarray.size(); i++) { // paramwizware 배열 수
				 
				
				org.json.simple.JSONObject j = (org.json.simple.JSONObject) Jarray.get(i);
				JSONArray recordJson = (JSONArray) j.get("recordList");
				vobj = new VOBJ();
				for (int in = 0; in < recordJson.size(); in++) { // recordList input data 수
					org.json.simple.JSONObject jn = (org.json.simple.JSONObject) recordJson.get(in);
					rec = new HashMap();
				//	System.out.println("======="+ jn.toJSONString());
					if( jn != null){
					Iterator Jkeys = jn.keySet().iterator();
					 
					
				
					while (Jkeys.hasNext()) {
						String JKey = (String) Jkeys.next();
						//System.out.println( "*********** NAME : " +JKey	+ " *** VALUE : "	+ jn.get(JKey));

						if (JKey.equals("flagwizware")) {
							
							rec.put("IUDFLAG", jn.get(JKey));
							//System.out.println("1");
							//System.out.println( "=============================== InDSList : " + j.get("inDsList").toString() + " value : " +jn.get(JKey));
						} else {
							
								String CVTChar = decodeCvtChar(jn.get(JKey).toString());
								rec.put(JKey, jn.get(JKey));
							
							
							//System.out.println("2");
							//System.out.println( " 2 2 InDSList : " + j.get("inDsList").toString() + " value : " +jn.get(JKey));
						}

						

					}
				
					
					vobj.addRecord(rec);
					}//Jkeys null처리
				}
				//System.out.println( "================InDSList : " + j.get("inDsList").toString() + "================== ");	
				// null 데이타셋 처리
				JSONArray recordSize = (JSONArray) j.get("recordList");
				org.json.simple.JSONObject jnSize = (org.json.simple.JSONObject) recordSize.get(0);
				//System.out.println( " aa " + recordSize.get(0).toString() + " toJSONString " + recordSize.toJSONString()  );
				//System.out.println(recordSize.size() + " ##"  +recordSize.listIterator().toString());
				   if(jnSize != null){
					   //System.out.println(j.get("recordList").toString() + " @@ @@ "); 
					   vobj.setName(j.get("inDsList").toString());
				   }
					
					dobj.setRetObject(vobj);
				

			}
		
		} catch (Exception je) {
			je.printStackTrace();
		}

		return dobj;
	}
    //
	private String decodeCvtChar(String data){
	 data = data.replaceAll("amp;", "&");
	 return data;
	
	}
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

        /*/------------------INPUT DATASET?? ???? ????o???? ???? ???? ---------
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
