package egov.wizware.ria;
import egov.wizware.com.*;
import java.io.*;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

public class WebCrea
{
    private boolean debugon = true;
    private String coldelim="";
    private String rowdelim ="";
    /******
  // 웹크래아 마이빌더 전문 형태 같아서 같이 사용 중.
   * 
   * *//////
   
    public WebCrea()
    {
    }

    public void testInput()
    {
    	//String inputData = "{{{+S1+}}}sal_nodpt_nmemp_nmsal_itmsal_cntitm_pricesal_amtsal_date2005-07-00046강남사업부dddd한문교재30330000200312132005-07-00070강남사업부강남길과학교재39330001287000200311062005-07-00047군자사업부강민규한문교재2022000440000200312122005-07-00071강서사업부강민규과학교재1014000140000200507052005-07-00035부산사업부강상진영어교재34340001156000200310162005-07-00058성수사업부강인중과학교재100200002000000200401282005-07-00010성동사업부고선경영어교재2027000540000200308062005-07-00007수유사업부곽희정국어교재40450001800000200308012005-07-00004수유사업부권미자영어교재40250001000000200307102005-07-00044대구사업부김기수과학교재35350001225000200312022005-07-00098성동사업부김기원과학교재2027000540000200308062005-07-00028강남사업부김길동과학교재23400068000200311062005-07-00002수유사업부김길영불어교재2003000600000200307112005-07-00013수유사업부김길자국어교재45270001215000200310072005-07-00063울산사업부김도준국어교재453000135000200404082005-07-00049강서사업부김영길과학교재2034000680000200312072005-07-00052강서사업부김인하한문교재3003400010200000200312012005-07-00126울산사업부김인현한문교재3423000782000200408242005-07-00029강남사업부김재동과학교재2523000575000200311062005-07-00057강서사업부김종오불어교재45400001800000200312242005-07-00050강서사업부김진석국어교재34340001156000200312072005-07-00041군자사업부김호연불어교재34340001156000200312032005-07-00022수유사업부김홍길일어교재270330008910000200310232005-07-00060성수사업부목진철불어교재2005000010000000200402122005-07-00005부산사업부문정주영어교재300200006000000200307022005-07-00017군자사업부민병철한문교재340240008160000200310092005-07-00066강남사업부박수용과학교재2323000529000200408312005-07-00031강서사업부박수진국어교재77340002618000200311172005-07-00033강서사업부박운석일어교재35330001155000200311272005-07-00053울산사업부박인수국어교재45230001035000200312012005-07-00006부산사업부박인식한문교재3023000690000200307102005-07-00038수유사업부배인덕과학교재3429000986000200311272005-07-00064수유사업부심영수국어교재1001000100000200405012005-07-00032성수사업부안영희국어교재100300003000000200311212005-07-00008강서사업부안재원영어교재3423000782000200308152005-07-00102성동사업부우온식한문교재45270001215000200310092005-07-00062영동사업부유영락국어교재11000010000200402102005-07-00025군자사업부이금형영어교재100330003300000200311122005-07-00043대구사업부이기자국어교재40400001600000200312042005-07-00019군자사업부이나영국어교재38330001254000200311132005-07-00027성수사업부이미숙일어교재3033000990000200311132005-07-00003영동사업부이미자영어교재100030003000000200307172005-07-00024강서사업부이수영영어교재180330005940000200310302005-07-00034강남사업부이수진영어교재45330001485000200311272005-07-00068강남사업부이순신국어교재665000330000200505182005-07-00014군자사업부이승헌한문교재100450004500000200310082005-07-00026군자사업부이승헌일어교재39330001287000200310302005-07-00076군자사업부이승헌2513500337500200507062005-07-00056울산사업부이진영과학교재5002500012500000200312092005-07-00037울산사업부이창희영어교재34340001156000200310022005-07-00055울산사업부이화룡일어교재75250001875000200312092005-07-00040성수사업부임수근불어교재45340001530000200311062005-07-00009성동사업부임진경불어교재36100036000200308042005-07-00087강서사업부장강훈국어교재2022000440000200312122005-07-00061울산사업부정인호국어교재3925000975000200402072005-07-00016강남사업부조승우한문교재120270003240000200310092005-07-00036대구사업부지진희한문교재34330001122000200309182005-07-00065울산사업부최수정과학교재100450004500000200405192005-07-00045강서사업부황명희한문교재2023000460000200312052005-07-00018성동사업부황인수영어교재45270001215000200310092005-07-0003강남사업부이수진영어교재4533000148500020031127{{{+TB_SalesItem+}}}itm_cditm_nmitm_price01과학교재1350002국어교재1200003불어교재1000004영어교재800005일어교재750006한문교재1000007국사교재15000{{{+TB_Department+}}}dpt_cddpt_nmdpt_rmk_status_101강남사업부강남*102강서사업부강서*103군자사업부군자*";
       // String inputData = "{{{+S1+}}}sal_no "; // 공백값을 넣으면 오류 발생 X
        String inputData = "{{{+S1+}}}sal_no"; // 아무값도 없이 넘기면 오류 발생 O
        DOBJ dobj = getInputDOBJ(inputData);
        StringBuffer sbuf  =  getOutputDobj(dobj);
        System.out.println(sbuf);
       
        	
     
        
    }

    public StringBuffer outputObject(DOBJ dobj)
    {
        StringBuffer sbuf  = getOutputDobj(dobj);
        return sbuf;
    }
    private StringBuffer getOutputDobj(DOBJ dobj)
    {
        StringBuffer sbuf = new StringBuffer(100000);

        //*************** WEBCRE 추가 요청 대응: 테스트 head : 나중에 삭제 해야 할것으로 보임 ***********************
         sbuf.append("DUMMY\014\014");
        //*****************************************************************************************************

        ArrayList alist = dobj.getRetObjectKeys();
        boolean isfirst = true;
        VOBJ vobj = null;
        for(int i = 0; i < alist.size(); i++)
        {
            vobj = dobj.getRetObject(alist.get(i).toString());
            //if(vobj.getRetcode() == 1)

            if(isfirst)   isfirst = false;
            else  sbuf.append(this.rowdelim);
            sbuf.append(getOutputVOBJ(vobj));

            //Format 재확인 필요
            //결과 Message 전송부 처리
            //Multi-Set return시 default set의 처리

        }
        
        //메시지 전문 생성
        sbuf.append(this.rowdelim);
        sbuf.append("{{{+"+"MSG"+"+}}}");
        sbuf.append(this.rowdelim);
        sbuf.append("TITLE");
        sbuf.append(this.coldelim);
        sbuf.append(new StringBuilder("CODE").append(this.rowdelim));
        if(dobj.getRetmsg() == null){
        	 sbuf.append("");	
        	
        }else{
        	 sbuf.append(dobj.getRetmsg().toString());
        }
        sbuf.append(this.coldelim);
        sbuf.append(dobj.getRtncode());
        return sbuf;
    }
    private StringBuffer getOutputVOBJ(VOBJ vobj)
    {
        StringBuffer sbuf = new StringBuffer(100000);
        ArrayList clist = vobj.getColumnNames();
        // dataset name처리 부
        sbuf.append("{{{+"+vobj.getName()+"+}}}");
        sbuf.append(this.rowdelim);
        sbuf.append(getOutputHead(clist));
        vobj.first();
        while(vobj.next())
        {
            sbuf.append(this.rowdelim+getOutputRecord(clist, vobj.getRecordMap())) ;
        }
        return sbuf;
    }
    private String getOutputRecord(ArrayList clist, HashMap rec)
    {
    	String value = "";
        String recstr ="";
        for(int i=0;i<clist.size();i++)
        {
        	  if(rec.get(clist.get(i))==null) value ="";
              else value = rec.get(clist.get(i)).toString();
            if(i==0)
            {
            	recstr += value;
            }
            else
            {
            	recstr += this.coldelim + value;
            }
        }
        return recstr;
    }
    private String getOutputHead(ArrayList clist)
    {
        String recstr ="";
        for(int i=0;i<clist.size();i++)
        {
            if(i==0)
            {
                recstr += clist.get(i);
            }
            else
            {
                recstr += this.coldelim + clist.get(i);
            }
        }
        return recstr;
    }


    public DOBJ inputObject(HttpServletRequest request)   throws Exception
    {
        DOBJ dobj = new DOBJ();
        String inputData = "";

        String parmname ="";
        Enumeration e = request.getParameterNames();
        while (e.hasMoreElements())
        {
           parmname = e.nextElement().toString();
           //System.out.println("Input Parameter Name:" + parmname);
           if(parmname.equals("sndata") )
           {
               inputData = request.getParameter(parmname);
               //System.out.println("Input Data:" + inputData);
           }
        }
        dobj = getInputDOBJ(inputData);
        return dobj;
    }

    //{{{+TB_SalesItem+}}}itm_cditm_nmitm_price01과학교재1350002국어교재1200003불어교재1000004영어교재800005일어교재750006한문교재1000007국사교
   private DOBJ getInputDOBJ(String inputData)
   {
        DOBJ dobj = new DOBJ();
        if(inputData.trim().equals(""))
       {
           return dobj;
       }
       if(debugon) System.out.println(inputData);

       String[] recs = inputData.split(this.rowdelim);
       ArrayList rlist = new ArrayList();
       String[] headinfo = null;
       boolean isHeadrow = false;
       String datasetname ="";
      
       for(int i=0;i<recs.length;i++)
       {
           if(isHeadrow)
           {
               isHeadrow = false;
               headinfo = getHeadInfo(recs[i]);
               continue;
           }
         
           if(isDatasetName(recs[i]))
           {
        	
               if(!datasetname.equals(""))
               {
            	  
                   VOBJ vobj = new VOBJ();
                   vobj.setName(datasetname);
                   vobj.setRecords(rlist);
                   dobj.setRetObject(vobj);
               }
               
               isHeadrow = true;
               rlist = new ArrayList();
               datasetname = getDatasetName(recs[i]);
               continue;
           }
     
           if(recs.equals("")) continue;
           System.out.println( recs[i] + " @@ " + headinfo[0]);
           HashMap rec = getRecord(recs[i], headinfo);
           rlist.add(rec);
       }

       if(!datasetname.equals(""))
       {
           VOBJ vobj = new VOBJ();
           vobj.setName(datasetname);
           
         //----------------------- start : 2016.06.03 : empty record
           if(rlist.size() == 0 && headinfo.length > 0)
           {
               HashMap rec = getRecord(headinfo);
               rlist.add(rec);
           }
           //--------------------------------------------- end  : 2016.06.03
           
           vobj.setRecords(rlist);
           dobj.setRetObject(vobj);
       }

       if(debugon)
       {
           System.out.println("====[ INPUT VOBJ DISPLAY ]===");
           dobj.dispRetObjectKeyNames();
           String name = "";
           ArrayList inlist = dobj.getRetObjectKeys();
           for(int i=0; i<inlist.size(); i++)
           {
               name = inlist.get(i).toString();
               VOBJ vobj = dobj.getRetObject(name);
               vobj.Println("INPUT");
           }
       }
       return dobj;
   }

   //itm_cditm_nmitm_price01과학교재1350002국어교재1200003불어교재1000004영어교재800005일어교재750006한문교재1000007국사교
    private DOBJ getInputDOBJ_bk(String inputData)
   {
        DOBJ dobj = new DOBJ();
        if(inputData.trim().equals(""))
       {
           return dobj;
       }
       if(debugon) System.out.println(inputData);

       String[] recs = inputData.split(this.rowdelim);
       ArrayList rlist = new ArrayList();
       String[] headinfo = null;
       boolean isHeadrow = false;
       String datasetname ="S";
       for(int i=0;i<recs.length;i++)
       {
           if(isHeadrow)
           {
               isHeadrow = false;
               headinfo = getHeadInfo(recs[i]);
               continue;
           }

           if(isDatasetName(recs[i]))
           {
               isHeadrow = true;
               VOBJ vobj = new VOBJ();
               vobj.setName(datasetname);
               vobj.setRecords(rlist);
               dobj.setRetObject(vobj);

               rlist = new ArrayList();
               datasetname = getDatasetName(recs[i]);
               continue;
           }

           if(recs.equals("")) continue;
           HashMap rec = getRecord(recs[i], headinfo);
           rlist.add(rec);
       }

       if(datasetname.equals("S"))
       {
           VOBJ vobj = new VOBJ();
           vobj.setName(datasetname);
           vobj.setRecords(rlist);
           dobj.setRetObject(vobj);
       }

       if(debugon)
       {
           System.out.println("====[ INPUT VOBJ DISPLAY ]===");
           dobj.dispRetObjectKeyNames();
           String name = "";
           ArrayList inlist = dobj.getRetObjectKeys();
           for(int i=0; i<inlist.size(); i++)
           {
               name = inlist.get(i).toString();
               VOBJ vobj = dobj.getRetObject(name);
               vobj.Println("INPUT");
           }
       }
       return dobj;
   }



    private String[] getHeadInfo(String rec)
    {
        String[] headinfo = rec.split(this.coldelim);
        for(int i=0;i<headinfo.length;i++)
        {
            headinfo[i] = headinfo[i].trim().toUpperCase();
        }
        return headinfo;
    }

    //{{{+TB_SalesItem+}}}
    private boolean isDatasetName(String str)
    {
        if(str.trim().indexOf("{{{+") != -1 && str.trim().indexOf("+}}}") != -1)
        {
            return true;
        }
        return false;
    }
    //----------------------- add : 2016.06.03 : empty record
    private HashMap getRecord(String[] colnames)
    {
        HashMap rec = new HashMap();
        for(int i=0;i<colnames.length;i++)
        {
            rec.put(colnames[i], "");
        }
        return rec;
    }
    private HashMap getRecord(String recstr, String[] colnames)
    {
        String[] colvalues = recstr.split(this.coldelim);
        HashMap rec = new HashMap();
       // System.out.println(colnames.length + " @@@@@@ " +colvalues[0] );
        try{
        for(int i=0;i<colnames.length;i++)
        {
            if(colnames[i].equals("_STATUS_"))   //구분자 값 한번더 확인 필요하다.
            {
                if (colvalues[i].equals("*")) {
                    rec.put("IUDFLAG", "U");
                } else if (colvalues[i].equals("-")) {
                    rec.put("IUDFLAG", "D");
                } else if (colvalues[i].equals("+")) {
                    rec.put("IUDFLAG", "I");
                } else {
                    rec.put("IUDFLAG", "");
                }
            }
            else
            {
                rec.put(colnames[i], colvalues[i]);
            }
        }
        }
        catch(Exception e){
        	
        	System.out.println("에러발생");
        }
        return rec;
    }
    //{{{+TB_SalesItem+}}}
    private String getDatasetName(String dsname)
    {
        String name = dsname.substring(dsname.indexOf("+")+1, dsname.lastIndexOf("+"));  //
        return name;
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
