
package hpms.base.dao;
import java.util.*;
import egov.wizware.com.*;
import egov.wizware.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import com.ibatis.sqlmap.client.SqlMapClient;
import javax.annotation.Resource;
@Repository("CostInput20160901101142Dao")
public class CostInputDao extends EgovAbstractDAO
{
    // ExcelUpload Data
    public VOBJ CostUpload_PEX2(DOBJ dobj) throws Exception
    {
        CommUtil cu = new CommUtil(dobj);
        HashMap   classinfo = new HashMap();
        classinfo.put("OBJECTID", "PEX2" );
        classinfo.put("PACKAGE", "hpms.UserObject.Excel" );
        classinfo.put("CLASS", "CostUpload" );
        classinfo.put("METHOD", "uploadCostExcel" );
        dobj = cu.callPSExternal(dobj, null, classinfo );
        VOBJ dvobj = dobj.getRetObject("UP01");
        dvobj.setName("PEX2");
        dvobj.Println("PEX2");
        return dvobj;
    }
    // TEST
    public VOBJ CostUpload_CVT25(DOBJ dobj) throws Exception
    {
        dobj.setRtnname("CVT25");
        WizUtil wutil = new WizUtil(dobj,"CVT25", "TEST" );
        VOBJ dvobj = dobj.getRetObjectCopy("PEX2");        //ExcelUpload Data Input Object(CALLCostUpload_PEX2)
        String[] outcolumns = 
        {
        "APPLICATION", "CUSTOMER_CD", "DATA_TYPE", "HPMS_ID", "HPMS_ID_NM_JP", "INVEST_TYPE_CD", "INVEST_TYPE_NAME", "ITEM_NAME", "PID", "PID_THEME", "PROCESSING_FLAG", "REQ_COMPANY_CD", "REQ_ORG_CD", "UNIT", "UPDATE_NAME", "UPDATE_TIME", "USE_COMPANY_CD", "USE_ORG_CD", "VAL", "YYYYMM"
        }
        ;;
        HashMap    record =null;
        dvobj.first();
        while(dvobj.next())
        {
            record = dvobj.getRecordMap();
            boolean isfind=false;
            ArrayList alist = new ArrayList();
            Iterator itor = record.keySet().iterator();
            while(itor.hasNext())
            {
            
                alist.add(itor.next().toString());
            }
            for(int i=0;i<alist.size();i++)
            {
            
                isfind = false;
                for(int j=0;j<outcolumns.length;j++)
                {
                
                    if(alist.get(i).equals(outcolumns[j])) 
                    {
                    
                        isfind=true;
                    }
                }
                if(isfind == false)
                {
                
                    record.remove(alist.get(i));
                }
            }
            dvobj.setRecord(record);
        }
        dvobj.setName("CVT25") ;
        return dvobj;
    }
    // get LogSequence
    public VOBJ CostUpload_SEL33(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL33", "get LogSequence" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("CostInput_20160901101142.CostUpload_SEL33", param);
        dvobj.setName("SEL33");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // MasterSearch(Error Check)
    public VOBJ CostUpload_SEL17(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL17", "MasterSearch(Error Check)" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("PID", dobj.getRetObject("R").getRecord().get("PID"));   //PID
        param.put("ITEM_NAME", dobj.getRetObject("R").getRecord().get("ITEM_NAME"));   //ITEM명
        param.put("HPMS_ID", dobj.getRetObject("R").getRecord().get("HPMS_ID"));   //HPMS_ID
        List rlist = list("CostInput_20160901101142.CostUpload_SEL17", param);
        dvobj.setName("SEL17");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // WorkTable Save HP2DM11W
    public VOBJ CostUpload_UNI38(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"UNI38", "WorkTable Save HP2DM11W" );
        VOBJ dvobj = dobj.getRetObject("R");           //Loop (MPD,MIUD) Currect Record Object
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        HashMap    param= null;
        int        inscnt=0, updcnt =0 ,unicnt=0,  rtncnt=0;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("MSGCODE", dobj.getRetmsg());   //MSGCODE
            param.put("UPDATE_TIME", "");   //갱신일시
            param.put("HPMS_ID", dvobj.getRecord().get("HPMS_ID"));   //HPMS_ID
            param.put("APPLICATION", dvobj.getRecord().get("APPLICATION"));   //用途
            param.put("ITEM_NAME", dvobj.getRecord().get("ITEM_NAME"));   //ITEM명
            param.put("YYYYMM", dvobj.getRecord().get("YYYYMM"));   //실적월
            param.put("PID", dvobj.getRecord().get("PID"));   //PID
            param.put("UNIT", dvobj.getRecord().get("UNIT"));   //통화단위
            param.put("VAL", dvobj.getRecord().getDouble("VAL")+"");   //Value
            param.put("DATA_TYPE", dobj.getRetObject("S2").getRecord().get("DATA_TYPE"));   //デ？タタイプ
            param.put("LOG_SEQ", dobj.getRetObject("SEL33").getRecord().get("LOG_SEQ"));   //LOG_SEQ
            param.put("PROCESSING_FLAG", "I");   //？理フラグ
            param.put("UPLOAD_FILE_NAME", dobj.getRetObject("S1").getRecord().get("FILE_NAME"));   //UPLOAD_FILE_NAME
            param.put("USER_ID", dobj.getRetObject("G").getRecord().get("USER_ID"));   //사용자ID
            String  ERR_FLAG="" ;          //エラ？フラグ
            if(!param.get("MSGCODE").toString().equals("") && dobj.getGVString ( "FLAG" ).equals(""))
            {
               ERR_FLAG = "1";
                
            }
            else 
            {
               ERR_FLAG = "0";
                
            }
              param.put("ERR_FLAG", ERR_FLAG);   //エラ？フラグ
            String  ERR_MSG="" ;          //오류메시지
            if(param.get("MSGCODE").toString().equals("1000") && dobj.getGVString ( "FLAG" ).equals(""))
            {
               ERR_MSG = "PID not Found";
                
            }
            else if(param.get("MSGCODE").toString().equals("1001") && dobj.getGVString ( "FLAG" ).equals(""))
            {
               ERR_MSG = "DataType not Found";
                
            }
            else if(param.get("MSGCODE").toString().equals("1002") && dobj.getGVString ( "FLAG" ).equals(""))
            {
               ERR_MSG = "Investment code not Found";
                
            }
            else if(param.get("MSGCODE").toString().equals("1003") && dobj.getGVString ( "FLAG" ).equals(""))
            {
               ERR_MSG = "USE_COMPANY/ORG not Found";
                
            }
            else if(param.get("MSGCODE").toString().equals("1004") && dobj.getGVString ( "FLAG" ).equals(""))
            {
               ERR_MSG = "REQ_COMPANY_ORG not Found";
                
            }
            else if(param.get("MSGCODE").toString().equals("1005") && dobj.getGVString ( "FLAG" ).equals(""))
            {
               ERR_MSG = "HPMS_ID not Found";
                
            }
            else if(param.get("MSGCODE").toString().equals("1006") && dobj.getGVString ( "FLAG" ).equals(""))
            {
               ERR_MSG = "Investment code not Found";
                
            }
            else if(param.get("MSGCODE").toString().equals("1007") && dobj.getGVString ( "FLAG" ).equals(""))
            {
               ERR_MSG = "Customer Code not Found";
                
            }
            else if(param.get("MSGCODE").toString().equals("1008") && dobj.getGVString ( "FLAG" ).equals(""))
            {
               ERR_MSG = "Insert Error not Found";
                
            }
            else if(param.get("MSGCODE").toString().equals("1009") && dobj.getGVString ( "FLAG" ).equals(""))
            {
               ERR_MSG = "DATA TYPE(AUTH) not Found";
                
            }
            else if(param.get("MSGCODE").toString().equals("1010") && dobj.getGVString ( "FLAG" ).equals(""))
            {
               ERR_MSG = "YYYYMM Format Error";
                
            }
            else if(param.get("MSGCODE").toString().equals("1011") && dobj.getGVString ( "FLAG" ).equals(""))
            {
               ERR_MSG = "Number Format Error";
                
            }
            else if(param.get("MSGCODE").toString().equals("1012") && dobj.getGVString ( "FLAG" ).equals(""))
            {
               ERR_MSG = "Length Error(overflow)";
                
            }
            else if(param.get("MSGCODE").toString().equals("1013") && dobj.getGVString ( "FLAG" ).equals(""))
            {
               ERR_MSG = "Insert Error(Key not Found)";
                
            }
            else if(param.get("MSGCODE").toString().equals("1014") && dobj.getGVString ( "FLAG" ).equals(""))
            {
               ERR_MSG = "Insert Error Duplicated Data";
                
            }
            else if(param.get("MSGCODE").toString().equals("1015") && dobj.getGVString ( "FLAG" ).equals(""))
            {
               ERR_MSG = "Currency Code Not Found";
                
            }
            else if(param.get("MSGCODE").toString().equals("1016") && dobj.getGVString ( "FLAG" ).equals(""))
            {
               ERR_MSG = "ITEM_NAME(SC-PART) Not Found";
                
            }
            else 
            {
               ERR_MSG = "";
                
            }
              param.put("ERR_MSG", ERR_MSG);   //오류메시지
            rtncnt= update("CostInput_20160901101142.CostUpload_UNI38_UPD",param);
            if(rtncnt < 1)
            {
                insert("CostInput_20160901101142.CostUpload_UNI38_INS",param);
                inscnt++;
                unicnt++;
                
            }
            else
            {
                updcnt += rtncnt;
                unicnt += rtncnt;
            }
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        rvobj.setHeadColumn("INSCNT" , "INT" );
        rvobj.setHeadColumn("UNICNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        recordx.put("INSCNT",inscnt+"");
        recordx.put("UNICNT",unicnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("UNI38") ;
        return rvobj;
    }
    // HP2DM11T delete
    public VOBJ CostUpload_DEL29(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"DEL29", "HP2DM11T delete" );
        VOBJ dvobj = dobj.getRetObject("R");           //Loop (MPD,MIUD) Currect Record Object
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap    param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("HPMS_ID", dvobj.getRecord().get("HPMS_ID"));   //HPMS_ID
            param.put("APPLICATION", dvobj.getRecord().get("APPLICATION"));   //用途
            param.put("ITEM_NAME", dvobj.getRecord().get("ITEM_NAME"));   //ITEM명
            param.put("YYYYMM", dvobj.getRecord().get("YYYYMM"));   //실적월
            param.put("PID", dvobj.getRecord().get("PID"));   //PID
            param.put("DATA_TYPE", dobj.getRetObject("S2").getRecord().get("DATA_TYPE"));   //デ？タタイプ
            updcnt += delete("CostInput_20160901101142.CostUpload_DEL29",param);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("DEL29") ;
        return rvobj;
    }
    // Data Insert(Loop)HP2D001T
    public VOBJ CostUpload_INS31(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"INS31", "Data Insert(Loop)HP2D001T" );
        VOBJ dvobj = dobj.getRetObject("R");           //Loop (MPD,MIUD) Currect Record Object
        dvobj.Println("INS31");
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap    param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("UPDATE_TIME", "");   //갱신일시
            param.put("UPDATE_USER_ID", dobj.getRetObject("G").getRecord().get("USER_ID"));   //갱신자ID
            param.put("UPLOAD_FILE_NAME", dobj.getRetObject("S1").getRecord().get("FILE_UNNAME"));   //UPLOAD_FILE_NAME
            param.put("HPMS_ID", dvobj.getRecord().get("HPMS_ID"));   //HPMS_ID
            param.put("APPLICATION", dvobj.getRecord().get("APPLICATION"));   //用途
            param.put("ITEM_NAME", dvobj.getRecord().get("ITEM_NAME"));   //ITEM명
            param.put("YYYYMM", dvobj.getRecord().get("YYYYMM"));   //실적월
            param.put("PID", dvobj.getRecord().get("PID"));   //PID
            param.put("UNIT", dvobj.getRecord().get("UNIT"));   //통화단위
            param.put("VAL", dvobj.getRecord().getDouble("VAL")+"");   //Value
            param.put("DATA_TYPE", dobj.getRetObject("S2").getRecord().get("DATA_TYPE"));   //デ？タタイプ
            insert("CostInput_20160901101142.CostUpload_INS31",param);
            updcnt++;
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("INS31") ;
        rvobj.Println("INS31");
        return rvobj;
    }
    // get Cost Data
    public VOBJ csvdownload_SEL1(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL1", "get Cost Data" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("DATA_TYPE", dobj.getRetObject("S2").getRecord().get("DATA_TYPE"));   //デ？タタイプ
        List rlist = list("CostInput_20160901101142.csvdownload_SEL1", param);
        dvobj.setName("SEL1");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // Rebuild
    public VOBJ csvdownload_CVT7(DOBJ dobj) throws Exception
    {
        dobj.setRtnname("CVT7");
        WizUtil wutil = new WizUtil(dobj,"CVT7", "Rebuild" );
        VOBJ dvobj = dobj.getRetObjectCopy("SEL1");        //get Cost Data Input Object(CALLcsvdownload_SEL1)
        String[] outcolumns =
        {
            "APPLICATION", "DATA_TYPE", "HPMS_ID", "HPMS_ID_NM_JP", "ITEM_NAME", "PID", "UNIT", "VAL", "YYYYMM"
        }
        ;;
        HashMap    record =null;
        dvobj.first();
        while(dvobj.next())
        {
            record = dvobj.getRecordMap();
            String APPLICATION="" ;   //用途
            if(dvobj.getRecord().get("APPLICATION").equals("#"))
            {
                APPLICATION = "";
            }
            else
            {
                APPLICATION = dvobj.getRecord().get("APPLICATION");
            }
            record.put("APPLICATION",APPLICATION);
            String ITEM_NAME="" ;   //ITEM명
            if(dvobj.getRecord().get("ITEM_NAME").equals("#"))
            {
                ITEM_NAME = "";
            }
            else
            {
                ITEM_NAME = dvobj.getRecord().get("ITEM_NAME");
            }
            record.put("ITEM_NAME",ITEM_NAME);
            boolean isfind=false;
            ArrayList alist = new ArrayList();
            Iterator itor = record.keySet().iterator();
            while(itor.hasNext())
            {
                alist.add(itor.next().toString());
            }
            for(int i=0;i<alist.size();i++)
            {
                isfind = false;
                for(int j=0;j<outcolumns.length;j++)
                {
                    if(alist.get(i).equals(outcolumns[j]))
                    {
                        isfind=true;
                    }
                }
                if(isfind == false)
                {
                    record.remove(alist.get(i));
                }
            }
            dvobj.setRecord(record);
        }
        dvobj.setName("CVT7") ;
        return dvobj;
    }
    // FILE_NAME_Info
    public VOBJ csvdownload_SEL5(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL5", "FILE_NAME_Info" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("CostInput_20160901101142.csvdownload_SEL5", param);
        dvobj.setName("SEL5");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // ROOTPATH
    public VOBJ csvdownload_PEX5(DOBJ dobj) throws Exception
    {
        CommUtil cu = new CommUtil(dobj);
        HashMap   classinfo = new HashMap();
        classinfo.put("OBJECTID", "PEX5" );
        classinfo.put("PACKAGE", "hpms.UserObject.BatchExe" );
        classinfo.put("CLASS", "FileUtility" );
        classinfo.put("METHOD", "rootPath" );
        dobj = cu.callPSExternal(dobj, null, classinfo );
        VOBJ dvobj = dobj.getRetObject("FN02");
        dvobj.setName("PEX5");
        return dvobj;
    }
    // CSV File Build
    public VOBJ csvdownload_FBD4(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"FBD4", "CSV File Build" );
        VOBJ dvobj = dobj.getRetObject("CVT7");           //Rebuild Input Object(CALLcsvdownload_CVT7)
        String path = dobj.getRetObject("PEX5").getRecord().get("ROOTPATH") + "/ExcelDownLoad";
        String filename = "CostData" + "_" + dobj.getRetObject("G").getRecord().get("USER_ID") + "_" + dobj.getRetObject("SEL5").getRecord().get("CDATE") + ".csv";
        String creategbn ="3";
        String layout ="DATA_TYPE,1D,',','' : PID,1D,',','' : HPMS_ID,1D,',','' : HPMS_ID_NM_JP,1D,',','' : APPLICATION,1D,',','' : ITEM_NAME,1D,',','' : YYYYMM,1D,',','' : UNIT,1D,',','' : VAL,1D,'\\r\\n',''";
        String _4622 ="\n";
        String charset ="UTF-8";
        String filenamex = wutil.makeFile(dobj, dvobj, path, filename, creategbn, layout, _4622, charset);
        VOBJ rvobj = new VOBJ();
        HashMap rec = new HashMap();
        rec.put("FILENAME",filenamex);
        rvobj.addRecord(rec);
        rvobj.setName("FBD4");
        return rvobj;
    }
    // downfile seting
    public VOBJ csvdownload_DN01(DOBJ dobj) throws Exception
    {
        dobj.setRtnname("DN01");
        WizUtil wutil = new WizUtil(dobj,"DN01", "downfile seting" );
        VOBJ dvobj = dobj.getRetObjectCopy("FBD4");        //CSV File Build Input Object(CALLcsvdownload_FBD4)
        String[] outcolumns =
        {
            "FILE_PATH", "FILE_UNNAME", "FILE_NAME"
        }
        ;;
        HashMap    record =null;
        dvobj.first();
        while(dvobj.next())
        {
            record = dvobj.getRecordMap();
            String   FILE_NAME = "CostData"+"_"+dobj.getRetObject("G").getRecord().get("USER_ID")+"_"+dobj.getRetObject("SEL5").getRecord().get("CDATE")+".csv";         //파일이름
            record.put("FILE_NAME",FILE_NAME);
            String   FILE_PATH = dobj.getRetObject("PEX5").getRecord().get("ROOTPATH")+"/ExcelDownLoad";         //파일path
            record.put("FILE_PATH",FILE_PATH);
            String   FILE_UNNAME = dvobj.getRecord().get("FILENAME");         //유니크파일
            record.put("FILE_UNNAME",FILE_UNNAME);
            boolean isfind=false;
            ArrayList alist = new ArrayList();
            Iterator itor = record.keySet().iterator();
            while(itor.hasNext())
            {
                alist.add(itor.next().toString());
            }
            for(int i=0;i<alist.size();i++)
            {
                isfind = false;
                for(int j=0;j<outcolumns.length;j++)
                {
                    if(alist.get(i).equals(outcolumns[j]))
                    {
                        isfind=true;
                    }
                }
                if(isfind == false)
                {
                    record.remove(alist.get(i));
                }
            }
            dvobj.setRecord(record);
        }
        dvobj.setName("DN01") ;
        return dvobj;
    }
    // get Cost Data
    public VOBJ errorConfirm_SEL1(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL1", "get Cost Data" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("LOG_SEQ", dobj.getRetObject("S9").getRecord().get("LOG_SEQ"));   //LOG_SEQ
        List rlist = list("CostInput_20160901101142.errorConfirm_SEL1", param);
        dvobj.setName("SEL1");
        dvobj.setRecords(rlist);
        dvobj.Println("SEL1");
        return dvobj;
    }
    // FILE_NAME_Info
    public VOBJ errorConfirm_SEL5(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL5", "FILE_NAME_Info" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("CostInput_20160901101142.errorConfirm_SEL5", param);
        dvobj.setName("SEL5");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // ROOTPATH
    public VOBJ errorConfirm_PEX5(DOBJ dobj) throws Exception
    {
        CommUtil cu = new CommUtil(dobj);
        HashMap   classinfo = new HashMap();
        classinfo.put("OBJECTID", "PEX5" );
        classinfo.put("PACKAGE", "hpms.UserObject.BatchExe" );
        classinfo.put("CLASS", "FileUtility" );
        classinfo.put("METHOD", "rootPath" );
        dobj = cu.callPSExternal(dobj, null, classinfo );
        VOBJ dvobj = dobj.getRetObject("FN02");
        dvobj.setName("PEX5");
        return dvobj;
    }
    // CSV File Build
    public VOBJ errorConfirm_FBD4(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"FBD4", "CSV File Build" );
        VOBJ dvobj = dobj.getRetObject("SEL1");           //get Cost Data Input Object(CALLerrorConfirm_SEL1)
        String path = dobj.getRetObject("PEX5").getRecord().get("ROOTPATH") + "/ExcelDownLoad";
        String filename = "errorData" + "_" + dobj.getRetObject("G").getRecord().get("USER_ID") + "_" + dobj.getRetObject("SEL5").getRecord().get("CDATE") + ".csv";
        String creategbn ="3";
        String layout ="ERR_MSG,1D,',','' : ERR_FLAG,1D,',','' : DATA_TYPE,1D,',','' : PID,1D,',','' : HPMS_ID,1D,',','' : APPLICATION,1D,',','' : ITEM_NAME,1D,',','' : UPLOAD_FILE_NAME,1D,'\\r\\n',''";
        String _4622 ="\n";
        String charset ="UTF-8";
        String filenamex = wutil.makeFile(dobj, dvobj, path, filename, creategbn, layout, _4622, charset);
        VOBJ rvobj = new VOBJ();
        HashMap rec = new HashMap();
        rec.put("FILENAME",filenamex);
        rvobj.addRecord(rec);
        rvobj.setName("FBD4");
        return rvobj;
    }
    // downfile seting
    public VOBJ errorConfirm_DN01(DOBJ dobj) throws Exception
    {
        dobj.setRtnname("DN01");
        WizUtil wutil = new WizUtil(dobj,"DN01", "downfile seting" );
        VOBJ dvobj = dobj.getRetObjectCopy("FBD4");        //CSV File Build Input Object(CALLerrorConfirm_FBD4)
        String[] outcolumns =
        {
            "FILE_PATH", "FILE_UNNAME", "FILE_NAME"
        }
        ;;
        HashMap    record =null;
        dvobj.first();
        while(dvobj.next())
        {
            record = dvobj.getRecordMap();
            String   FILE_NAME = "errorData"+"_"+dobj.getRetObject("G").getRecord().get("USER_ID")+"_"+dobj.getRetObject("SEL5").getRecord().get("CDATE")+".csv";         //파일이름
            record.put("FILE_NAME",FILE_NAME);
            String   FILE_PATH = dobj.getRetObject("PEX5").getRecord().get("ROOTPATH")+"/ExcelDownLoad";         //파일path
            record.put("FILE_PATH",FILE_PATH);
            String   FILE_UNNAME = dvobj.getRecord().get("FILENAME");         //유니크파일
            record.put("FILE_UNNAME",FILE_UNNAME);
            boolean isfind=false;
            ArrayList alist = new ArrayList();
            Iterator itor = record.keySet().iterator();
            while(itor.hasNext())
            {
                alist.add(itor.next().toString());
            }
            for(int i=0;i<alist.size();i++)
            {
                isfind = false;
                for(int j=0;j<outcolumns.length;j++)
                {
                    if(alist.get(i).equals(outcolumns[j]))
                    {
                        isfind=true;
                    }
                }
                if(isfind == false)
                {
                    record.remove(alist.get(i));
                }
            }
            dvobj.setRecord(record);
        }
        dvobj.setName("DN01") ;
        return dvobj;
    }
    // Condition combo
    public VOBJ Condition_Combo_SEL2(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL2", "Condition combo" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("CostInput_20160901101142.Condition_Combo_SEL2", param);
        dvobj.setName("SEL2");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // ExcelUpload Data
    public VOBJ ServiceID00_PEX2(DOBJ dobj) throws Exception
    {
        CommUtil cu = new CommUtil(dobj);
        HashMap   classinfo = new HashMap();
        classinfo.put("OBJECTID", "PEX2" );
        classinfo.put("PACKAGE", "hpms.UserObject.Excel" );
        classinfo.put("CLASS", "CostUpload" );
        classinfo.put("METHOD", "uploadCostExcel" );
        dobj = cu.callPSExternal(dobj, null, classinfo );
        VOBJ dvobj = dobj.getRetObject("UP01");
        dvobj.setName("PEX2");
        dvobj.Println("PEX2");
        return dvobj;
    }
    // get LogSequence
    public VOBJ ServiceID00_SEL33(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL33", "get LogSequence" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("CostInput_20160901101142.ServiceID00_SEL33", param);
        dvobj.setName("SEL33");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // MasterSearch(Error Check)
    public VOBJ ServiceID00_SEL17(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL17", "MasterSearch(Error Check)" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("UNIT", dobj.getRetObject("R").getRecord().get("UNIT"));   //통화단위
        param.put("PID", dobj.getRetObject("R").getRecord().get("PID"));   //PID
        param.put("YYYYMM", dobj.getRetObject("R").getRecord().get("YYYYMM"));   //실적월
        param.put("ITEM_NAME", dobj.getRetObject("R").getRecord().get("ITEM_NAME"));   //ITEM명
        param.put("HPMS_ID", dobj.getRetObject("R").getRecord().get("HPMS_ID"));   //HPMS_ID
        param.put("USE_COMPANY_CD", dobj.getRetObject("R").getRecord().get("USE_COMPANY_CD"));   //費用？生元？社CD
        List rlist = list("CostInput_20160901101142.ServiceID00_SEL17", param);
        dvobj.setName("SEL17");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // WorkTable Save HP2DM11W
    public VOBJ ServiceID00_UNI38(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"UNI38", "WorkTable Save HP2DM11W" );
        VOBJ dvobj = dobj.getRetObject("R");           //MULTI-분기처리(MPD,MIUD)의 Currect Record Object입니다.
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        HashMap    param= null;
        int        inscnt=0, updcnt =0 ,unicnt=0,  rtncnt=0;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("MSGCODE", dobj.getRetmsg());   //MSGCODE
            param.put("UPDATE_TIME", "");   //갱신일시
            param.put("HPMS_ID", dvobj.getRecord().get("HPMS_ID"));   //HPMS_ID
            param.put("APPLICATION", dvobj.getRecord().get("APPLICATION"));   //用途
            param.put("ITEM_NAME", dvobj.getRecord().get("ITEM_NAME"));   //ITEM명
            param.put("YYYYMM", dvobj.getRecord().get("YYYYMM"));   //실적월
            param.put("PID", dvobj.getRecord().get("PID"));   //PID
            param.put("PROCESSING_FLAG", dvobj.getRecord().get("PROCESSING_FLAG"));   //？理フラグ
            param.put("UNIT", dvobj.getRecord().get("UNIT"));   //통화단위
            param.put("VAL", dvobj.getRecord().getDouble("VAL")+"");   //Value
            param.put("DATA_TYPE", dobj.getRetObject("S2").getRecord().get("DATA_TYPE"));   //デ？タタイプ
            param.put("LOG_SEQ", dobj.getRetObject("SEL33").getRecord().get("LOG_SEQ"));   //LOG_SEQ
            param.put("UPLOAD_FILE_NAME", dobj.getRetObject("S1").getRecord().get("FILE_UNNAME"));   //UPLOAD_FILE_NAME
            param.put("USER_ID", dobj.getRetObject("G").getRecord().get("USER_ID"));   //사용자ID
            String  ERR_FLAG="" ;          //エラ？フラグ
            if(!param.get("MSGCODE").toString().equals("") && dobj.getGVString ( "FLAG" ).equals(""))
            {
                ERR_FLAG = "1";
            }
            else
            {
                ERR_FLAG = "0";
            }
            param.put("ERR_FLAG", ERR_FLAG);   //エラ？フラグ
            String  ERR_MSG="" ;          //오류메시지
            if(param.get("MSGCODE").toString().equals("1000") && dobj.getGVString ( "FLAG" ).equals(""))
            {
                ERR_MSG = "PID not Found";
            }
            else if(param.get("MSGCODE").toString().equals("1001") && dobj.getGVString ( "FLAG" ).equals(""))
            {
                ERR_MSG = "DataType not Found";
            }
            else if(param.get("MSGCODE").toString().equals("1002") && dobj.getGVString ( "FLAG" ).equals(""))
            {
                ERR_MSG = "Investment code not Found";
            }
            else if(param.get("MSGCODE").toString().equals("1003") && dobj.getGVString ( "FLAG" ).equals(""))
            {
                ERR_MSG = "USE_COMPANY/ORG not Found";
            }
            else if(param.get("MSGCODE").toString().equals("1004") && dobj.getGVString ( "FLAG" ).equals(""))
            {
                ERR_MSG = "REQ_COMPANY_ORG not Found";
            }
            else if(param.get("MSGCODE").toString().equals("1005") && dobj.getGVString ( "FLAG" ).equals(""))
            {
                ERR_MSG = "HPMS_ID not Found";
            }
            else if(param.get("MSGCODE").toString().equals("1006") && dobj.getGVString ( "FLAG" ).equals(""))
            {
                ERR_MSG = "Investment code not Found";
            }
            else if(param.get("MSGCODE").toString().equals("1007") && dobj.getGVString ( "FLAG" ).equals(""))
            {
                ERR_MSG = "Customer Code not Found";
            }
            else if(param.get("MSGCODE").toString().equals("1008") && dobj.getGVString ( "FLAG" ).equals(""))
            {
                ERR_MSG = "Insert Error not Found";
            }
            else if(param.get("MSGCODE").toString().equals("1009") && dobj.getGVString ( "FLAG" ).equals(""))
            {
                ERR_MSG = "DATA TYPE(AUTH) not Found";
            }
            else if(param.get("MSGCODE").toString().equals("1010") && dobj.getGVString ( "FLAG" ).equals(""))
            {
                ERR_MSG = "YYYYMM Format Error";
            }
            else if(param.get("MSGCODE").toString().equals("1011") && dobj.getGVString ( "FLAG" ).equals(""))
            {
                ERR_MSG = "Number Format Error";
            }
            else if(param.get("MSGCODE").toString().equals("1012") && dobj.getGVString ( "FLAG" ).equals(""))
            {
                ERR_MSG = "Length Error(overflow)";
            }
            else if(param.get("MSGCODE").toString().equals("1013") && dobj.getGVString ( "FLAG" ).equals(""))
            {
                ERR_MSG = "Insert Error(Key not Found)";
            }
            else if(param.get("MSGCODE").toString().equals("1014") && dobj.getGVString ( "FLAG" ).equals(""))
            {
                ERR_MSG = "Insert Error Duplicated Data";
            }
            else if(param.get("MSGCODE").toString().equals("1015") && dobj.getGVString ( "FLAG" ).equals(""))
            {
                ERR_MSG = "Currency Code Not Found";
            }
            else if(param.get("MSGCODE").toString().equals("1016") && dobj.getGVString ( "FLAG" ).equals(""))
            {
                ERR_MSG = "ITEM_NAME(SC-PART) Not Found";
            }
            else
            {
                ERR_MSG = "";
            }
            param.put("ERR_MSG", ERR_MSG);   //오류메시지
            rtncnt= update("CostInput_20160901101142.ServiceID00_UNI38_UPD",param);
            if(rtncnt < 1)
            {
                insert("CostInput_20160901101142.ServiceID00_UNI38_INS",param);
                inscnt++;
                unicnt++;
            }
            else
            {
                updcnt += rtncnt;
                unicnt += rtncnt;
            }
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        rvobj.setHeadColumn("INSCNT" , "INT" );
        rvobj.setHeadColumn("UNICNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        recordx.put("INSCNT",inscnt+"");
        recordx.put("UNICNT",unicnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("UNI38") ;
        return rvobj;
    }
    // HP2DM11T delete
    public VOBJ ServiceID00_DEL29(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"DEL29", "HP2DM11T delete" );
        VOBJ dvobj = dobj.getRetObject("R");           //MULTI-분기처리(MPD,MIUD)의 Currect Record Object입니다.
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap    param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("HPMS_ID", dvobj.getRecord().get("HPMS_ID"));   //HPMS_ID
            param.put("APPLICATION", dvobj.getRecord().get("APPLICATION"));   //用途
            param.put("ITEM_NAME", dvobj.getRecord().get("ITEM_NAME"));   //ITEM명
            param.put("YYYYMM", dvobj.getRecord().get("YYYYMM"));   //실적월
            param.put("PID", dvobj.getRecord().get("PID"));   //PID
            param.put("DATA_TYPE", dobj.getRetObject("S2").getRecord().get("DATA_TYPE"));   //デ？タタイプ
            updcnt += delete("CostInput_20160901101142.ServiceID00_DEL29",param);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("DEL29") ;
        return rvobj;
    }
    // Data Insert(Loop)HP2D001T
    public VOBJ ServiceID00_INS31(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"INS31", "Data Insert(Loop)HP2D001T" );
        VOBJ dvobj = dobj.getRetObject("R");           //MULTI-분기처리(MPD,MIUD)의 Currect Record Object입니다.
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap    param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("UPDATE_TIME", "");   //갱신일시
            param.put("UPDATE_USER_ID", dobj.getRetObject("G").getRecord().get("USER_ID"));   //갱신자ID
            param.put("UPLOAD_FILE_NAME", dobj.getRetObject("S1").getRecord().get("FILE_UNNAME"));   //UPLOAD_FILE_NAME
            param.put("HPMS_ID", dvobj.getRecord().get("HPMS_ID"));   //HPMS_ID
            param.put("APPLICATION", dvobj.getRecord().get("APPLICATION"));   //用途
            param.put("ITEM_NAME", dvobj.getRecord().get("ITEM_NAME"));   //ITEM명
            param.put("YYYYMM", dvobj.getRecord().get("YYYYMM"));   //실적월
            param.put("PID", dvobj.getRecord().get("PID"));   //PID
            param.put("UNIT", dvobj.getRecord().get("UNIT"));   //통화단위
            param.put("VAL", dvobj.getRecord().getDouble("VAL")+"");   //Value
            param.put("DATA_TYPE", dobj.getRetObject("S2").getRecord().get("DATA_TYPE"));   //デ？タタイプ
            insert("CostInput_20160901101142.ServiceID00_INS31",param);
            updcnt++;
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("INS31") ;
        return rvobj;
    }
}