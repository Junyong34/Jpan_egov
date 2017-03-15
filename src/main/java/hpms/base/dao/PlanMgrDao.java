
package hpms.base.dao;
import java.util.*;
import egov.wizware.com.*;
import egov.wizware.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import com.ibatis.sqlmap.client.SqlMapClient;
import javax.annotation.Resource;
@Repository("PlanMgr20160901091138Dao")
public class PlanMgrDao extends EgovAbstractDAO
{
    // ExcelUpload Data
    public VOBJ ExcelNoChecking_PEX2(DOBJ dobj) throws Exception
    {
        CommUtil cu = new CommUtil(dobj);
        HashMap   classinfo = new HashMap();
        classinfo.put("OBJECTID", "PEX2" );
        classinfo.put("PACKAGE", "hpms.UserObject.Excel" );
        classinfo.put("CLASS", "ForcastInOut" );
        classinfo.put("METHOD", "uploadPlanExcel" );
        dobj = cu.callPSExternal(dobj, null, classinfo );
        VOBJ dvobj = dobj.getRetObject("UP01");
        dvobj.setName("PEX2");
        return dvobj;
    }
    // get LogSequence
    public VOBJ ExcelNoChecking_ER01(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"ER01", "get LogSequence" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("LOG_CODE", dobj.getRtncode()+"");   //LOG_CODE
        List rlist = list("PlanMgr_20160901091138.ExcelNoChecking_ER01", param);
        dvobj.setName("ER01");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // Param
    public VOBJ ExcelNoChecking_CVT25(DOBJ dobj) throws Exception
    {
        dobj.setRtnname("CVT25");
        WizUtil wutil = new WizUtil(dobj,"CVT25", "Param" );
        VOBJ dvobj = dobj.getRetObjectCopy("ER01");        //get LogSequence Input Object(CALLExcelNoChecking_ER01)
        String[] outcolumns = 
        {
        "LOG_CODE", "LOG_SEQ"
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
    // WorkTable Save HP2D001W
    public VOBJ ExcelNoChecking_INS28(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"INS28", "WorkTable Save HP2D001W" );
        VOBJ dvobj = dobj.getRetObject("PEX2");           //ExcelUpload Data Input Object(CALLExcelNoChecking_PEX2)
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap    param = null;
        SqlMapClient client = getSqlMapClient();  
        client.startBatch();
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("UPDATE_TIME", "");   //갱신일시
            param.put("USE_COMPANY_CD", dvobj.getRecord().get("USE_COMPANY_CD"));   //費用？生元？社CD
            param.put("DATA_TYPE", dvobj.getRecord().get("DATA_TYPE"));   //デ？タタイプ
            param.put("USE_ORG_CD", dvobj.getRecord().get("USE_ORG_CD"));   //費用？生元部CD
            param.put("REQ_COMPANY_CD", dvobj.getRecord().get("REQ_COMPANY_CD"));   //要求元？社CD
            param.put("REQ_ORG_CD", dvobj.getRecord().get("REQ_ORG_CD"));   //要求元部CD
            param.put("ITEM_NAME", dvobj.getRecord().get("ITEM_NAME"));   //ITEM명
            param.put("YYYYMM", dvobj.getRecord().get("YYYYMM"));   //실적월
            param.put("PROCESSING_FLAG", dvobj.getRecord().get("PROCESSING_FLAG"));   //？理フラグ
            param.put("VAL", dvobj.getRecord().getDouble("VAL")+"");   //Value
            param.put("INVEST_TYPE_CD", dvobj.getRecord().get("INVEST_TYPE_CD"));   //投資？分CD
            param.put("ERR_FLAG", dvobj.getRecord().get("ERR_FLAG"));   //エラ？フラグ
            param.put("HPMS_ID", dvobj.getRecord().get("HPMS_ID"));   //HPMS_ID
            param.put("APPLICATION", dvobj.getRecord().get("APPLICATION"));   //用途
            param.put("PID", dvobj.getRecord().get("PID"));   //PID
            param.put("ERR_MSG", dvobj.getRecord().get("ERR_MSG"));   //오류메시지
            param.put("UNIT", dvobj.getRecord().get("UNIT"));   //통화단위
            param.put("CUSTOMER_CD", dvobj.getRecord().get("CUSTOMER_CD"));   //顧客CD
            param.put("LOG_SEQ", dobj.getRetObject("ER01").getRecord().get("LOG_SEQ"));   //LOG_SEQ
            param.put("UPLOAD_FILE_NAME", dobj.getRetObject("S1").getRecord().get("FILE_NAME"));   //UPLOAD_FILE_NAME
            param.put("USER_ID", dobj.getRetObject("G").getRecord().get("USER_ID"));   //사용자ID
            client.insert("PlanMgr_20160901091138.ExcelNoChecking_INS28",param);
            updcnt++;
        }
        client.executeBatch(); 
       
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("INS28") ;
        return rvobj;
    }
    // MST Cheeck
    public VOBJ ExcelNoChecking_SEL80(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL80", "MST Cheeck" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("LOG_SEQ", dobj.getRetObject("ER01").getRecord().get("LOG_SEQ"));   //LOG_SEQ
        param.put("USER_ID", dobj.getRetObject("G").getRecord().get("USER_ID"));   //사용자ID
        param.put("AUTH_CD", dobj.getRetObject("G").getRecord().get("AUTH_CD"));   //AUTH_CD
        List rlist = list("PlanMgr_20160901091138.ExcelNoChecking_SEL80", param);
        dvobj.setName("SEL80");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // WorkTable Update HP2D001W
    public VOBJ ExcelNoChecking_UPD68(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"UPD68", "WorkTable Update HP2D001W" );
        VOBJ dvobj = dobj.getRetObject("SEL80");           //MST Cheeck Input Object(CALLExcelNoChecking_SEL80)
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap    param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("USE_COMPANY_CD", dvobj.getRecord().get("USE_COMPANY_CD"));   //費用？生元？社CD
            param.put("DATA_TYPE", dvobj.getRecord().get("DATA_TYPE"));   //デ？タタイプ
            param.put("USE_ORG_CD", dvobj.getRecord().get("USE_ORG_CD"));   //費用？生元部CD
            param.put("REQ_COMPANY_CD", dvobj.getRecord().get("REQ_COMPANY_CD"));   //要求元？社CD
            param.put("REQ_ORG_CD", dvobj.getRecord().get("REQ_ORG_CD"));   //要求元部CD
            param.put("ITEM_NAME", dvobj.getRecord().get("ITEM_NAME"));   //ITEM명
            param.put("PROCESSING_FLAG", dvobj.getRecord().get("PROCESSING_FLAG"));   //？理フラグ
            param.put("INVEST_TYPE_CD", dvobj.getRecord().get("INVEST_TYPE_CD"));   //投資？分CD
            param.put("HPMS_ID", dvobj.getRecord().get("HPMS_ID"));   //HPMS_ID
            param.put("APPLICATION", dvobj.getRecord().get("APPLICATION"));   //用途
            param.put("PID", dvobj.getRecord().get("PID"));   //PID
            param.put("CUSTOMER_CD", dvobj.getRecord().get("CUSTOMER_CD"));   //顧客CD
            param.put("LOG_SEQ", dvobj.getRecord().get("LOG_SEQ"));   //LOG_SEQ
            param.put("ERR_FLAG", "1");   //エラ？フラグ
            param.put("ERR_MSG", dobj.getRetObject("SEL80").getRecord().get("CUSTOMER_CD_ERROR")+" "+dobj.getRetObject("SEL80").getRecord().get("DATA_TYPE_ERROR")+" "+dobj.getRetObject("SEL80").getRecord().get("DATA_TYPE_ERROR_1")+" "+dobj.getRetObject("SEL80").getRecord().get("HPMS_ID_ERROR")+" "+dobj.getRetObject("SEL80").getRecord().get("INVEST_TYPE_CD_ERROR")+" "+dobj.getRetObject("SEL80").getRecord().get("ITEM_NAME_ERROR")+" "+dobj.getRetObject("SEL80").getRecord().get("PID_ERROR")+" "+dobj.getRetObject("SEL80").getRecord().get("REQ_COMPANY_CD_ERROR")+" "+dobj.getRetObject("SEL80").getRecord().get("UNIT_ERROR")+" "+dobj.getRetObject("SEL80").getRecord().get("USE_COMPANY_CD_ERROR")+" "+dvobj.getRecord().get("AUTH_ERROR"));   //오류메시지
            param.put("USER_ID", dobj.getRetObject("G").getRecord().get("USER_ID"));   //사용자ID
            updcnt += update("PlanMgr_20160901091138.ExcelNoChecking_UPD68",param);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("UPD68") ;
        return rvobj;
    }
    // Error exist or No
    public VOBJ ExcelNoChecking_SEL78(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL78", "Error exist or No" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("LOG_SEQ", dobj.getRetObject("ER01").getRecord().get("LOG_SEQ"));   //LOG_SEQ
        List rlist = list("PlanMgr_20160901091138.ExcelNoChecking_SEL78", param);
        dvobj.setName("SEL78");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // Duplicated Flag I
    public VOBJ ExcelNoChecking_SEL74(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL74", "Duplicated Flag I" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("LOG_SEQ", dobj.getRetObject("ER01").getRecord().get("LOG_SEQ"));   //LOG_SEQ
        List rlist = list("PlanMgr_20160901091138.ExcelNoChecking_SEL74", param);
        dvobj.setName("SEL74");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // Merge Update ERR_MSG
    public VOBJ ExcelNoChecking_XIUD114(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"XIUD114", "Merge Update ERR_MSG" );
        VOBJ dvobj = dobj.getRetObject("ER01");            //get LogSequence Input Object(CALLExcelNoChecking_ER01)
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("LOG_SEQ", dobj.getRetObject("ER01").getRecord().get("LOG_SEQ"));   //LOG_SEQ
            param.put("ERR_MSG", "Insert Error Duplicated Data");   //오류메시지
            param.put("ERR_FLAG", "1");   //エラ？フラグ
            updcnt += update("PlanMgr_20160901091138.ExcelNoChecking_XIUD114",param);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("XIUD114");
        return rvobj;
    }
    // UNI   HP2D001T
    public VOBJ ExcelNoChecking_XIUD116(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"XIUD116", "UNI   HP2D001T  " );
        VOBJ dvobj = dobj.getRetObject("ER01");            //get LogSequence Input Object(CALLExcelNoChecking_ER01)
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("LOG_SEQ", dvobj.getRecord().get("LOG_SEQ"));   //LOG_SEQ
            updcnt += update("PlanMgr_20160901091138.ExcelNoChecking_XIUD116",param);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("XIUD116");
        return rvobj;
    }
    // Delete HP2D001T
    public VOBJ ExcelNoChecking_XIUD118(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"XIUD118", "Delete HP2D001T" );
        VOBJ dvobj = dobj.getRetObject("ER01");            //get LogSequence Input Object(CALLExcelNoChecking_ER01)
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("LOG_SEQ", dvobj.getRecord().get("LOG_SEQ"));   //LOG_SEQ
            updcnt += delete("PlanMgr_20160901091138.ExcelNoChecking_XIUD118",param);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("XIUD118");
        return rvobj;
    }
    // WorkTable Save HP2D001W
    public VOBJ ExcelNoChecking_INS27(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"INS27", "WorkTable Save HP2D001W" );
        VOBJ dvobj = dobj.getRetObject("PEX2");           //ExcelUpload Data Input Object(CALLExcelNoChecking_PEX2)
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap    param = null;
        SqlMapClient client = getSqlMapClient();  
        client.startBatch();
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("USE_COMPANY_CD", dvobj.getRecord().get("USE_COMPANY_CD"));   //費用？生元？社CD
            param.put("DATA_TYPE", dvobj.getRecord().get("DATA_TYPE"));   //デ？タタイプ
            param.put("USE_ORG_CD", dvobj.getRecord().get("USE_ORG_CD"));   //費用？生元部CD
            param.put("REQ_COMPANY_CD", dvobj.getRecord().get("REQ_COMPANY_CD"));   //要求元？社CD
            param.put("REQ_ORG_CD", dvobj.getRecord().get("REQ_ORG_CD"));   //要求元部CD
            param.put("ITEM_NAME", dvobj.getRecord().get("ITEM_NAME"));   //ITEM명
            param.put("YYYYMM", dvobj.getRecord().get("YYYYMM"));   //실적월
            param.put("PROCESSING_FLAG", dvobj.getRecord().get("PROCESSING_FLAG"));   //？理フラグ
            param.put("INVEST_TYPE_CD", dvobj.getRecord().get("INVEST_TYPE_CD"));   //投資？分CD
            param.put("HPMS_ID", dvobj.getRecord().get("HPMS_ID"));   //HPMS_ID
            param.put("APPLICATION", dvobj.getRecord().get("APPLICATION"));   //用途
            param.put("PID", dvobj.getRecord().get("PID"));   //PID
            param.put("ERR_MSG", dvobj.getRecord().get("ERR_MSG"));   //오류메시지
            param.put("UNIT", dvobj.getRecord().get("UNIT"));   //통화단위
            param.put("CUSTOMER_CD", dvobj.getRecord().get("CUSTOMER_CD"));   //顧客CD
            String  ERR_FLAG="" ;          //エラ？フラグ
            if(dobj.getRetObject("PEX2").getRecord().get("ERR_MSG").equals("")) 
            { 
                ERR_FLAG = "";  
            }
             else 
            {
                ERR_FLAG = "1";
            }
            param.put("ERR_FLAG", ERR_FLAG);   //エラ？フラグ
            param.put("LOG_SEQ", dobj.getRetObject("ER01").getRecord().get("LOG_SEQ"));   //LOG_SEQ
            param.put("UPDATE_TIME", "");   //갱신일시
            param.put("UPLOAD_FILE_NAME", dobj.getRetObject("S1").getRecord().get("FILE_NAME"));   //UPLOAD_FILE_NAME
            param.put("USER_ID", dobj.getRetObject("G").getRecord().get("USER_ID"));   //사용자ID
            param.put("VAL", dvobj.getRecord().get("VAL") +"");   //Value
            client.insert("PlanMgr_20160901091138.ExcelNoChecking_INS27",param);
            updcnt++;
        }
        client.executeBatch(); 
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("INS27") ;
        return rvobj;
    }
    // get ORG_SEQ
    public VOBJ planExcelDownload_SEL3(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL3", "get ORG_SEQ" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        String  ORG_CD="" ;          //ORG_CD
        if(!dobj.getRetObject("S").getRecord().get("ORG_CD").equals(""))
        {
            ORG_CD = dobj.getRetObject("S").getRecord().get("ORG_CD");
        }
        else
        {
            ORG_CD = dobj.getRetObject("S").getRecord().get("COMPANY_CD").substring(3);
        }
        param.put("ORG_CD", ORG_CD);   //ORG_CD
        param.put("COMPANY_CD", dobj.getRetObject("S").getRecord().get("COMPANY_CD").substring(0,3));   //COMPANY_CD
        List rlist = list("PlanMgr_20160901091138.planExcelDownload_SEL3", param);
        dvobj.setName("SEL3");
        dvobj.setRecords(rlist);
        dvobj.Println("SEL3");
        return dvobj;
    }
    // Forecast Data
    public VOBJ planExcelDownload_EXDN2(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"EXDN2", "Forecast Data" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("PID", dobj.getRetObject("S").getRecord().get("PID"));   //PID
        param.put("HPMS_GRP_ID", dobj.getRetObject("S").getRecord().get("HPMS_GRP_ID"));   //HPMS_GRP_ID
        param.put("TO_YYYYMM", dobj.getRetObject("S").getRecord().get("TO_YYYYMM"));   //퇴사월
        param.put("ORG_SEQ", dobj.getRetObject("SEL3").getRecord().get("ORG_SEQ"));   //ORG_SEQ
        param.put("HPMS_ID", dobj.getRetObject("S").getRecord().get("HPMS_ID"));   //HPMS_ID
        param.put("YYYYMM_NULL", dobj.getRetObject("S").getRecord().get("YYYYMM"));   //Multi-YYYYMM
        param.put("FROM_YYYYMM", dobj.getRetObject("S").getRecord().get("FROM_YYYYMM"));   //입사월
        param.put("COMPANY_CD", dobj.getRetObject("S").getRecord().get("COMPANY_CD").substring(0,3));   //COMPANY_CD
        param.put("DATA_TYPE", dobj.getRetObject("S").getRecord().get("DATA_TYPE"));   //デ？タタイプ
        List rlist = list("PlanMgr_20160901091138.planExcelDownload_EXDN2", param);
        dvobj.setName("EXDN2");
        dvobj.setRecords(rlist);
        dvobj.Println("EXDN2");
        return dvobj;
    }
    // Excel File Create
    public VOBJ planExcelDownload_DN01(DOBJ dobj) throws Exception
    {
        CommUtil cu = new CommUtil(dobj);
        HashMap   classinfo = new HashMap();
        classinfo.put("OBJECTID", "DN01" );
        classinfo.put("PACKAGE", "hpms.UserObject.Excel" );
        classinfo.put("CLASS", "ForcastInOut" );
        classinfo.put("METHOD", "downloadPlanExcel" );
        dobj = cu.callPSExternal(dobj, null, classinfo );
        VOBJ dvobj = dobj.getRetObject("DN01");
        dvobj.setName("DN01");
        return dvobj;
    }
    // Forecast Data
    public VOBJ planExcelDownload_EXDN1(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"EXDN1", "Forecast Data" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("PID", dobj.getRetObject("S").getRecord().get("PID"));   //PID
        param.put("HPMS_GRP_ID", dobj.getRetObject("S").getRecord().get("HPMS_GRP_ID"));   //HPMS_GRP_ID
        param.put("TO_YYYYMM", dobj.getRetObject("S").getRecord().get("TO_YYYYMM"));   //퇴사월
        param.put("HPMS_ID", dobj.getRetObject("S").getRecord().get("HPMS_ID"));   //HPMS_ID
        param.put("YYYYMM_NULL", dobj.getRetObject("S").getRecord().get("YYYYMM"));   //Multi-YYYYMM
        param.put("FROM_YYYYMM", dobj.getRetObject("S").getRecord().get("FROM_YYYYMM"));   //입사월
        param.put("DATA_TYPE", dobj.getRetObject("S").getRecord().get("DATA_TYPE"));   //デ？タタイプ
        List rlist = list("PlanMgr_20160901091138.planExcelDownload_EXDN1", param);
        dvobj.setName("EXDN1");
        dvobj.setRecords(rlist);
        dvobj.Println("EXDN1");
        return dvobj;
    }
    // WorkTable Save HP2D001W
    public VOBJ ExcelNoChecking_UNI72(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"UNI72", "WorkTable Save HP2D001W" );
        VOBJ dvobj = dobj.getRetObject("PEX2");           //ExcelUpload Data Input Object(CALLExcelNoChecking_PEX2)
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        HashMap    param= null;
        int        inscnt=0, updcnt =0 ,unicnt=0,  rtncnt=0;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("UPDATE_TIME", "");   //갱신일시
            param.put("USE_COMPANY_CD", dvobj.getRecord().get("USE_COMPANY_CD"));   //費用？生元？社CD
            param.put("DATA_TYPE", dvobj.getRecord().get("DATA_TYPE"));   //デ？タタイプ
            param.put("USE_ORG_CD", dvobj.getRecord().get("USE_ORG_CD"));   //費用？生元部CD
            param.put("REQ_COMPANY_CD", dvobj.getRecord().get("REQ_COMPANY_CD"));   //要求元？社CD
            param.put("REQ_ORG_CD", dvobj.getRecord().get("REQ_ORG_CD"));   //要求元部CD
            param.put("ITEM_NAME", dvobj.getRecord().get("ITEM_NAME"));   //ITEM명
            param.put("YYYYMM", dvobj.getRecord().get("YYYYMM"));   //실적월
            param.put("PROCESSING_FLAG", dvobj.getRecord().get("PROCESSING_FLAG"));   //？理フラグ
            param.put("VAL", dvobj.getRecord().getDouble("VAL")+"");   //Value
            param.put("INVEST_TYPE_CD", dvobj.getRecord().get("INVEST_TYPE_CD"));   //投資？分CD
            param.put("HPMS_ID", dvobj.getRecord().get("HPMS_ID"));   //HPMS_ID
            param.put("APPLICATION", dvobj.getRecord().get("APPLICATION"));   //用途
            param.put("PID", dvobj.getRecord().get("PID"));   //PID
            param.put("UNIT", dvobj.getRecord().get("UNIT"));   //통화단위
            param.put("CUSTOMER_CD", dvobj.getRecord().get("CUSTOMER_CD"));   //顧客CD
            param.put("ERR_FLAG", "");   //エラ？フラグ
            param.put("ERR_MSG", "");   //오류메시지
            param.put("LOG_SEQ", dobj.getRetObject("ER01").getRecord().get("LOG_SEQ"));   //LOG_SEQ
            param.put("UPLOAD_FILE_NAME", dobj.getRetObject("S1").getRecord().get("FILE_NAME"));   //UPLOAD_FILE_NAME
            param.put("USER_ID", dobj.getRetObject("G").getRecord().get("USER_ID"));   //사용자ID
            rtncnt= update("PlanMgr_20160901091138.ExcelNoChecking_UNI72_UPD",param);
            if(rtncnt < 1)
            {
                insert("PlanMgr_20160901091138.ExcelNoChecking_UNI72_INS",param);
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
        rvobj.setName("UNI72") ;
        return rvobj;
    }
    // CompanyList
    public VOBJ initCombo_SEL3(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL3", "CompanyList" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("PlanMgr_20160901091138.initCombo_SEL3", param);
        dvobj.setName("SEL3");
        dvobj.setRecords(rlist);
        dvobj.Println("SEL3");
        return dvobj;
    }
    // CompanyCombo
    public VOBJ initCombo_MRG1(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"MRG1", "CompanyCombo" );
        VOBJ       rvobj= null;
        rvobj = wutil.getMergeObject(dobj, "SEL3","ITEM_CD, ITEM_NM" );
        rvobj.setName("MRG1") ;
        rvobj.setRetcode(1);
        return rvobj;
    }
    // DATA_TYPE
    public VOBJ initCombo_SEL5(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL5", "DATA_TYPE" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("PID", dobj.getRetObject("S").getRecord().get("PID"));   //PID
        param.put("AUTH_CD", dobj.getRetObject("G").getRecord().get("AUTH_CD"));   //AUTH_CD
        List rlist = list("PlanMgr_20160901091138.initCombo_SEL5", param);
        dvobj.setName("SEL5");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        dvobj.Println("SEL5");
        return dvobj;
    }
    // HPMS_GRP
    public VOBJ initCombo_SEL6(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL6", "HPMS_GRP" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("PlanMgr_20160901091138.initCombo_SEL6", param);
        dvobj.setName("SEL6");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        dvobj.Println("SEL6");
        return dvobj;
    }
    // get Cost Data
    public VOBJ errorConfirm_SEL1(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL1", "get Cost Data" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("LOG_SEQ", dobj.getRetObject("S2").getRecord().get("LOG_SEQ"));   //LOG_SEQ
        List rlist = list("PlanMgr_20160901091138.errorConfirm_SEL1", param);
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
        List rlist = list("PlanMgr_20160901091138.errorConfirm_SEL5", param);
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
        String layout ="ERR_MSG,1D,',','' : ERR_FLAG,1D,',','' : DATA_TYPE,1D,',','' : PID,1D,',','' : USE_COMPANY_CD,1D,',','' : USE_ORG_CD,1D,',','' : REQ_COMPANY_CD,1D,',','' : REQ_ORG_CD,1D,',','' : HPMS_ID,1D,',','' : CUSTOMER_CD,1D,',','' : INVEST_TYPE_CD,1D,',','' : APPLICATION,1D,',','' : ITEM_NAME,1D,',','' : UNIT,1D,',','' : UPLOAD_FILE_NAME,1D,'\\r\\n',''";
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
            String   FILE_NAME = "ErrorData"+"_"+dobj.getRetObject("G").getRecord().get("USER_ID")+"_"+dobj.getRetObject("SEL5").getRecord().get("CDATE")+".csv";         //파일이름
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
}