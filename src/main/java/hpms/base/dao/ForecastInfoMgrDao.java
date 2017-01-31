
package hpms.base.dao;
import java.util.*;
import egov.wizware.com.*;
import egov.wizware.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import com.ibatis.sqlmap.client.SqlMapClient;
import javax.annotation.Resource;
@Repository("ForecastInfoMgr2016082417899Dao")
public class ForecastInfoMgrDao extends EgovAbstractDAO
{
    // MAX(HP2D002T_TZ )
    public VOBJ ForecastDataCopy_SEL11(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL11", "MAX(HP2D002T_TZ )" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("PID", dobj.getRetObject("S3").getRecord().get("PID"));   //PID
        param.put("DATA_TYPE", dobj.getRetObject("S3").getRecord().get("FROM"));   //デ？タタイプ
        List rlist = list("ForecastInfoMgr_2016082417899.ForecastDataCopy_SEL11", param);
        dvobj.setName("SEL11");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // MP MTP LMP(HP2D002T_TZ )
    public VOBJ ForecastDataCopy_SEL17(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL17", "MP MTP LMP(HP2D002T_TZ )" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("PID", dobj.getRetObject("S3").getRecord().get("PID"));   //PID
        param.put("DATA_TYPE", dobj.getRetObject("SEL11").getRecord().get("DATA_TYPE"));   //デ？タタイプ
        List rlist = list("ForecastInfoMgr_2016082417899.ForecastDataCopy_SEL17", param);
        dvobj.setName("SEL17");
        dvobj.setRecords(rlist);
        dvobj.Println("SEL17");
        return dvobj;
    }
    // Forecast INSERT(HP2D001T)
    public VOBJ ForecastDataCopy_INS15(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"INS15", "Forecast INSERT(HP2D001T)" );
        VOBJ dvobj = dobj.getRetObject("SEL17");           //MP MTP LMP(HP2D002T_TZ ) Input Object(CALLForecastDataCopy_SEL17)
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap    param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("UPDATE_TIME", "");   //갱신일시
            param.put("USE_COMPANY_CD", dvobj.getRecord().get("USE_COMPANY_CD"));   //費用？生元？社CD
            param.put("USE_ORG_CD", dvobj.getRecord().get("USE_ORG_CD"));   //費用？生元部CD
            param.put("REQ_COMPANY_CD", dvobj.getRecord().get("REQ_COMPANY_CD"));   //要求元？社CD
            param.put("ITEM_NAME", dvobj.getRecord().get("ITEM_NAME"));   //ITEM명
            param.put("REQ_ORG_CD", dvobj.getRecord().get("REQ_ORG_CD"));   //要求元部CD
            param.put("YYYYMM", dvobj.getRecord().get("YYYYMM"));   //실적월
            param.put("VAL", dvobj.getRecord().getDouble("VAL")+"");   //Value
            param.put("INVEST_TYPE_CD", dvobj.getRecord().get("INVEST_TYPE_CD"));   //投資？分CD
            param.put("HPMS_ID", dvobj.getRecord().get("HPMS_ID"));   //HPMS_ID
            param.put("APPLICATION", dvobj.getRecord().get("APPLICATION"));   //用途
            param.put("PID", dvobj.getRecord().get("PID"));   //PID
            param.put("UNIT", dvobj.getRecord().get("UNIT"));   //통화단위
            param.put("CUSTOMER_CD", dvobj.getRecord().get("CUSTOMER_CD"));   //顧客CD
            param.put("DATA_TYPE", dobj.getRetObject("S3").getRecord().get("TO"));   //デ？タタイプ
            param.put("UPDATE_USER_ID", dobj.getRetObject("G").getRecord().get("USER_ID"));   //갱신자ID
            insert("ForecastInfoMgr_2016082417899.ForecastDataCopy_INS15",param);
            updcnt++;
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("INS15") ;
        String message = "00002";
        dobj.setRetmsg(message);
        return rvobj;
    }
    // Forecast select delete(HP2D001T)
    public VOBJ ForecastDataCopy_SEL20(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL20", "Forecast select delete(HP2D001T)" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("PID", dobj.getRetObject("S3").getRecord().get("PID"));   //PID
        param.put("TO", dobj.getRetObject("S3").getRecord().get("TO"));   //TO
        List rlist = list("ForecastInfoMgr_2016082417899.ForecastDataCopy_SEL20", param);
        dvobj.setName("SEL20");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // Forecast delete(HP2D001T)
    public VOBJ ForecastDataCopy_DEL10(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"DEL10", "Forecast delete(HP2D001T)" );
        VOBJ dvobj = dobj.getRetObject("SEL20");           //Forecast select delete(HP2D001T) Input Object(CALLForecastDataCopy_SEL20)
        dvobj.Println("DEL10");
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
            param.put("HPMS_ID", dvobj.getRecord().get("HPMS_ID"));   //HPMS_ID
            param.put("REQ_COMPANY_CD", dvobj.getRecord().get("REQ_COMPANY_CD"));   //要求元？社CD
            param.put("APPLICATION", dvobj.getRecord().get("APPLICATION"));   //用途
            param.put("REQ_ORG_CD", dvobj.getRecord().get("REQ_ORG_CD"));   //要求元部CD
            param.put("ITEM_NAME", dvobj.getRecord().get("ITEM_NAME"));   //ITEM명
            param.put("YYYYMM", dvobj.getRecord().get("YYYYMM"));   //실적월
            param.put("PID", dvobj.getRecord().get("PID"));   //PID
            param.put("INVEST_TYPE_CD", dvobj.getRecord().get("INVEST_TYPE_CD"));   //投資？分CD
            param.put("CUSTOMER_CD", dvobj.getRecord().get("CUSTOMER_CD"));   //顧客CD
            updcnt += delete("ForecastInfoMgr_2016082417899.ForecastDataCopy_DEL10",param);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("DEL10") ;
        rvobj.Println("DEL10");
        return rvobj;
    }
    // Forecast Select(HP2D001T)
    public VOBJ ForecastDataCopy_SEL12(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL12", "Forecast Select(HP2D001T)" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("PID", dobj.getRetObject("S3").getRecord().get("PID"));   //PID
        param.put("FROM", dobj.getRetObject("S3").getRecord().get("FROM"));   //FROM
        List rlist = list("ForecastInfoMgr_2016082417899.ForecastDataCopy_SEL12", param);
        dvobj.setName("SEL12");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // Forecast INSERT(HP2D001T)
    public VOBJ ForecastDataCopy_INS14(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"INS14", "Forecast INSERT(HP2D001T)" );
        VOBJ dvobj = dobj.getRetObject("SEL12");           //Forecast Select(HP2D001T) Input Object(CALLForecastDataCopy_SEL12)
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap    param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("UPDATE_TIME", "");   //갱신일시
            param.put("USE_COMPANY_CD", dvobj.getRecord().get("USE_COMPANY_CD"));   //費用？生元？社CD
            param.put("USE_ORG_CD", dvobj.getRecord().get("USE_ORG_CD"));   //費用？生元部CD
            param.put("REQ_COMPANY_CD", dvobj.getRecord().get("REQ_COMPANY_CD"));   //要求元？社CD
            param.put("ITEM_NAME", dvobj.getRecord().get("ITEM_NAME"));   //ITEM명
            param.put("REQ_ORG_CD", dvobj.getRecord().get("REQ_ORG_CD"));   //要求元部CD
            param.put("YYYYMM", dvobj.getRecord().get("YYYYMM"));   //실적월
            param.put("VAL", dvobj.getRecord().getDouble("VAL")+"");   //Value
            param.put("INVEST_TYPE_CD", dvobj.getRecord().get("INVEST_TYPE_CD"));   //投資？分CD
            param.put("HPMS_ID", dvobj.getRecord().get("HPMS_ID"));   //HPMS_ID
            param.put("APPLICATION", dvobj.getRecord().get("APPLICATION"));   //用途
            param.put("PID", dvobj.getRecord().get("PID"));   //PID
            param.put("UNIT", dvobj.getRecord().get("UNIT"));   //통화단위
            param.put("CUSTOMER_CD", dvobj.getRecord().get("CUSTOMER_CD"));   //顧客CD
            param.put("DATA_TYPE", dobj.getRetObject("S3").getRecord().get("TO"));   //デ？タタイプ
            param.put("UPDATE_USER_ID", dobj.getRetObject("G").getRecord().get("USER_ID"));   //갱신자ID
            insert("ForecastInfoMgr_2016082417899.ForecastDataCopy_INS14",param);
            updcnt++;
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("INS14") ;
        String message = "00002";
        dobj.setRetmsg(message);
        return rvobj;
    }
    // MAX(HP2D002T_TZ )
    public VOBJ ForecastDataCopy_SEL13(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL13", "MAX(HP2D002T_TZ )" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("PID", dobj.getRetObject("S3").getRecord().get("PID"));   //PID
        String  DATA_TYPE="" ;          //デ？タタイプ
        if(dobj.getRetObject("S3").getRecord().get("FROM").equals("A.DCP"))
        {
           DATA_TYPE = "DCP_A";
            
        }
        else if(dobj.getRetObject("S3").getRecord().get("FROM").equals("P.DCP"))
        {
           DATA_TYPE = "DCP_P";
            
        }
        else if(dobj.getRetObject("S3").getRecord().get("FROM").equals("T.DCP"))
        {
           DATA_TYPE = "DCP_T";
            
        }
        else if(dobj.getRetObject("S3").getRecord().get("FROM").equals("M.DCP"))
        {
           DATA_TYPE = "DCP_M";
            
        }
        else if(dobj.getRetObject("S3").getRecord().get("FROM").equals("E.DCP"))
        {
           DATA_TYPE = "DCP_E";
            
        }
        else 
        {
           DATA_TYPE = "";
            
        }
          param.put("DATA_TYPE", DATA_TYPE);   //デ？タタイプ
        List rlist = list("ForecastInfoMgr_2016082417899.ForecastDataCopy_SEL13", param);
        dvobj.setName("SEL13");
        dvobj.setRecords(rlist);
        dvobj.Println("SEL13");
        return dvobj;
    }
    // Forecast select delete(HP2D002T_TZ)
    public VOBJ ForecastDataCopy_SEL41(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL41", "Forecast select delete(HP2D002T_TZ)" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("PID", dobj.getRetObject("S3").getRecord().get("PID"));   //PID
        param.put("TO", dobj.getRetObject("S3").getRecord().get("TO"));   //TO
        param.put("DATA_TYPE", dobj.getRetObject("SEL13").getRecord().get("DATA_TYPE"));   //デ？タタイプ
        List rlist = list("ForecastInfoMgr_2016082417899.ForecastDataCopy_SEL41", param);
        dvobj.setName("SEL41");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // Forecast delete(HP2D001T)
    public VOBJ ForecastDataCopy_DEL39(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"DEL39", "Forecast delete(HP2D001T)" );
        VOBJ dvobj = dobj.getRetObject("SEL41");           //Forecast select delete(HP2D002T_TZ) Input Object(CALLForecastDataCopy_SEL41)
        dvobj.Println("DEL39");
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
            param.put("HPMS_ID", dvobj.getRecord().get("HPMS_ID"));   //HPMS_ID
            param.put("REQ_COMPANY_CD", dvobj.getRecord().get("REQ_COMPANY_CD"));   //要求元？社CD
            param.put("APPLICATION", dvobj.getRecord().get("APPLICATION"));   //用途
            param.put("REQ_ORG_CD", dvobj.getRecord().get("REQ_ORG_CD"));   //要求元部CD
            param.put("ITEM_NAME", dvobj.getRecord().get("ITEM_NAME"));   //ITEM명
            param.put("YYYYMM", dvobj.getRecord().get("YYYYMM"));   //실적월
            param.put("PID", dvobj.getRecord().get("PID"));   //PID
            param.put("INVEST_TYPE_CD", dvobj.getRecord().get("INVEST_TYPE_CD"));   //投資？分CD
            param.put("CUSTOMER_CD", dvobj.getRecord().get("CUSTOMER_CD"));   //顧客CD
            updcnt += delete("ForecastInfoMgr_2016082417899.ForecastDataCopy_DEL39",param);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("DEL39") ;
        rvobj.Println("DEL39");
        return rvobj;
    }
    // MP MTP LMP(HP2D002T_TZ )
    public VOBJ ForecastDataCopy_SEL18(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL18", "MP MTP LMP(HP2D002T_TZ )" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("PID", dobj.getRetObject("S3").getRecord().get("PID"));   //PID
        param.put("DATA_TYPE", dobj.getRetObject("SEL13").getRecord().get("DATA_TYPE"));   //デ？タタイプ
        List rlist = list("ForecastInfoMgr_2016082417899.ForecastDataCopy_SEL18", param);
        dvobj.setName("SEL18");
        dvobj.setRecords(rlist);
        dvobj.Println("SEL18");
        return dvobj;
    }
    // Forecast INSERT(HP2D001T)
    public VOBJ ForecastDataCopy_INS16(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"INS16", "Forecast INSERT(HP2D001T)" );
        VOBJ dvobj = dobj.getRetObject("SEL18");           //MP MTP LMP(HP2D002T_TZ ) Input Object(CALLForecastDataCopy_SEL18)
        dvobj.Println("INS16");
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap    param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("UPDATE_TIME", "");   //갱신일시
            param.put("USE_COMPANY_CD", dvobj.getRecord().get("USE_COMPANY_CD"));   //費用？生元？社CD
            param.put("UPLOAD_FILE_NAME", dvobj.getRecord().get("UPLOAD_FILE_NAME"));   //UPLOAD_FILE_NAME
            param.put("USE_ORG_CD", dvobj.getRecord().get("USE_ORG_CD"));   //費用？生元部CD
            param.put("REQ_COMPANY_CD", dvobj.getRecord().get("REQ_COMPANY_CD"));   //要求元？社CD
            param.put("ITEM_NAME", dvobj.getRecord().get("ITEM_NAME"));   //ITEM명
            param.put("REQ_ORG_CD", dvobj.getRecord().get("REQ_ORG_CD"));   //要求元部CD
            param.put("YYYYMM", dvobj.getRecord().get("YYYYMM"));   //실적월
            param.put("VAL", dvobj.getRecord().getDouble("VAL")+"");   //Value
            param.put("INVEST_TYPE_CD", dvobj.getRecord().get("INVEST_TYPE_CD"));   //投資？分CD
            param.put("HPMS_ID", dvobj.getRecord().get("HPMS_ID"));   //HPMS_ID
            param.put("APPLICATION", dvobj.getRecord().get("APPLICATION"));   //用途
            param.put("PID", dvobj.getRecord().get("PID"));   //PID
            param.put("UNIT", dvobj.getRecord().get("UNIT"));   //통화단위
            param.put("CUSTOMER_CD", dvobj.getRecord().get("CUSTOMER_CD"));   //顧客CD
            param.put("DATA_TYPE", dobj.getRetObject("S3").getRecord().get("TO"));   //デ？タタイプ
            param.put("UPDATE_USER_ID", dobj.getRetObject("G").getRecord().get("USER_ID"));   //갱신자ID
            insert("ForecastInfoMgr_2016082417899.ForecastDataCopy_INS16",param);
            updcnt++;
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("INS16") ;
        rvobj.Println("INS16");
        String message = "00002";
        dobj.setRetmsg(message);
        return rvobj;
    }
    // Tree ORG
    public VOBJ ForecastDataCopy2_SEL9(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL9", "Tree ORG" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("ORG_CD", dobj.getRetObject("S4").getRecord().get("ORG_CD"));   //ORG_CD
        param.put("COMPANY_CD", wutil.substr(dobj.getRetObject("S4").getRecord().get("COMPANY_CD"),0,3));   //COMPANY_CD
        List rlist = list("ForecastInfoMgr_2016082417899.ForecastDataCopy2_SEL9", param);
        dvobj.setName("SEL9");
        dvobj.setRecords(rlist);
        dvobj.Println("SEL9");
        return dvobj;
    }
    // MAX(HP2D002T_TZ)
    public VOBJ ForecastDataCopy2_SEL39(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL39", "MAX(HP2D002T_TZ)" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("FROM", dobj.getRetObject("S4").getRecord().get("FROM"));   //FROM
        param.put("USE_ORG_CD", dobj.getRetObject("R").getRecord().get("ORG_CD"));   //費用？生元部CD
        param.put("USE_COMPANY_CD", dobj.getRetObject("S4").getRecord().get("COMPANY_CD").substring(0,3));   //費用？生元？社CD
        param.put("S4_FROM", dobj.getRetObject("S4").getRecord().get("FROM"));    //Dynmic Sql Parameter
        List rlist = list("ForecastInfoMgr_2016082417899.ForecastDataCopy2_SEL39", param);
        dvobj.setName("SEL39");
        dvobj.setRecords(rlist);
        dvobj.Println("SEL39");
        return dvobj;
    }
    // Forecast Select(HP2D002T_TZ)
    public VOBJ ForecastDataCopy2_SEL47(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL47", "Forecast Select(HP2D002T_TZ)" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("TO", dobj.getRetObject("S4").getRecord().get("TO"));   //TO
        param.put("USE_ORG_CD", dobj.getRetObject("R").getRecord().get("ORG_CD"));   //費用？生元部CD
        param.put("DATA_TYPE", dobj.getRetObject("SEL39").getRecord().get("DATA_TYPE"));   //デ？タタイプ
        param.put("USE_COMPANY_CD", dobj.getRetObject("S4").getRecord().get("COMPANY_CD").substring(0,3));   //費用？生元？社CD
        List rlist = list("ForecastInfoMgr_2016082417899.ForecastDataCopy2_SEL47", param);
        dvobj.setName("SEL47");
        dvobj.setRecords(rlist);
        dvobj.Println("SEL47");
        return dvobj;
    }
    // Forecast delete(HP2D001T)
    public VOBJ ForecastDataCopy2_DEL46(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"DEL46", "Forecast delete(HP2D001T)" );
        VOBJ dvobj = dobj.getRetObject("SEL47");           // Input Dataset Object.
        dvobj.Println("DEL46");
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
            param.put("HPMS_ID", dvobj.getRecord().get("HPMS_ID"));   //HPMS_ID
            param.put("REQ_COMPANY_CD", dvobj.getRecord().get("REQ_COMPANY_CD"));   //要求元？社CD
            param.put("APPLICATION", dvobj.getRecord().get("APPLICATION"));   //用途
            param.put("REQ_ORG_CD", dvobj.getRecord().get("REQ_ORG_CD"));   //要求元部CD
            param.put("ITEM_NAME", dvobj.getRecord().get("ITEM_NAME"));   //ITEM명
            param.put("YYYYMM", dvobj.getRecord().get("YYYYMM"));   //실적월
            param.put("PID", dvobj.getRecord().get("PID"));   //PID
            param.put("INVEST_TYPE_CD", dvobj.getRecord().get("INVEST_TYPE_CD"));   //投資？分CD
            param.put("CUSTOMER_CD", dvobj.getRecord().get("CUSTOMER_CD"));   //顧客CD
            updcnt += delete("ForecastInfoMgr_2016082417899.ForecastDataCopy2_DEL46",param);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("DEL46") ;
        rvobj.Println("DEL46");
        return rvobj;
    }
    // Forecast Select(HP2D002T_TZ)
    public VOBJ ForecastDataCopy2_SEL16(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL16", "Forecast Select(HP2D002T_TZ)" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("FROM", dobj.getRetObject("SEL39").getRecord().get("DATA_TYPE"));   //FROM
        param.put("USE_ORG_CD", dobj.getRetObject("R").getRecord().get("ORG_CD"));   //費用？生元部CD
        param.put("USE_COMPANY_CD", dobj.getRetObject("S4").getRecord().get("COMPANY_CD").substring(0,3));   //費用？生元？社CD
        List rlist = list("ForecastInfoMgr_2016082417899.ForecastDataCopy2_SEL16", param);
        dvobj.setName("SEL16");
        dvobj.setRecords(rlist);
        dvobj.Println("SEL16");
        return dvobj;
    }
    // Forecast INSERT(HP2D001T)
    public VOBJ ForecastDataCopy2_INS17(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"INS17", "Forecast INSERT(HP2D001T)" );
        VOBJ dvobj = dobj.getRetObject("SEL16");           // Input Dataset Object.
        dvobj.Println("INS17");
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap    param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("UPDATE_TIME", "");   //갱신일시
            param.put("USE_COMPANY_CD", dvobj.getRecord().get("USE_COMPANY_CD"));   //費用？生元？社CD
            param.put("USE_ORG_CD", dvobj.getRecord().get("USE_ORG_CD"));   //費用？生元部CD
            param.put("REQ_COMPANY_CD", dvobj.getRecord().get("REQ_COMPANY_CD"));   //要求元？社CD
            param.put("ITEM_NAME", dvobj.getRecord().get("ITEM_NAME"));   //ITEM명
            param.put("REQ_ORG_CD", dvobj.getRecord().get("REQ_ORG_CD"));   //要求元部CD
            param.put("YYYYMM", dvobj.getRecord().get("YYYYMM"));   //실적월
            param.put("VAL", dvobj.getRecord().getDouble("VAL")+"");   //Value
            param.put("INVEST_TYPE_CD", dvobj.getRecord().get("INVEST_TYPE_CD"));   //投資？分CD
            param.put("HPMS_ID", dvobj.getRecord().get("HPMS_ID"));   //HPMS_ID
            param.put("APPLICATION", dvobj.getRecord().get("APPLICATION"));   //用途
            param.put("PID", dvobj.getRecord().get("PID"));   //PID
            param.put("UNIT", dvobj.getRecord().get("UNIT"));   //통화단위
            param.put("CUSTOMER_CD", dvobj.getRecord().get("CUSTOMER_CD"));   //顧客CD
            param.put("DATA_TYPE", dobj.getRetObject("S4").getRecord().get("TO"));   //デ？タタイプ
            param.put("UPDATE_USER_ID", dobj.getRetObject("G").getRecord().get("USER_ID"));   //갱신자ID
            insert("ForecastInfoMgr_2016082417899.ForecastDataCopy2_INS17",param);
            updcnt++;
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("INS17") ;
        rvobj.Println("INS17");
        String message = "00002";
        dobj.setRetmsg(message);
        return rvobj;
    }
    // Forecast Select(HP2D001T)
    public VOBJ ForecastDataCopy2_SEL23(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL23", "Forecast Select(HP2D001T)" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("TO", dobj.getRetObject("S4").getRecord().get("TO"));   //TO
        param.put("USE_ORG_CD", dobj.getRetObject("R").getRecord().get("ORG_CD"));   //費用？生元部CD
        param.put("USE_COMPANY_CD", dobj.getRetObject("S4").getRecord().get("COMPANY_CD").substring(0,3));   //費用？生元？社CD
        List rlist = list("ForecastInfoMgr_2016082417899.ForecastDataCopy2_SEL23", param);
        dvobj.setName("SEL23");
        dvobj.setRecords(rlist);
        dvobj.Println("SEL23");
        return dvobj;
    }
    // Forecast delete(HP2D001T)
    public VOBJ ForecastDataCopy2_DEL22(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"DEL22", "Forecast delete(HP2D001T)" );
        VOBJ dvobj = dobj.getRetObject("SEL23");           // Input Dataset Object.
        dvobj.Println("DEL22");
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
            param.put("HPMS_ID", dvobj.getRecord().get("HPMS_ID"));   //HPMS_ID
            param.put("REQ_COMPANY_CD", dvobj.getRecord().get("REQ_COMPANY_CD"));   //要求元？社CD
            param.put("APPLICATION", dvobj.getRecord().get("APPLICATION"));   //用途
            param.put("REQ_ORG_CD", dvobj.getRecord().get("REQ_ORG_CD"));   //要求元部CD
            param.put("ITEM_NAME", dvobj.getRecord().get("ITEM_NAME"));   //ITEM명
            param.put("YYYYMM", dvobj.getRecord().get("YYYYMM"));   //실적월
            param.put("PID", dvobj.getRecord().get("PID"));   //PID
            param.put("INVEST_TYPE_CD", dvobj.getRecord().get("INVEST_TYPE_CD"));   //投資？分CD
            param.put("CUSTOMER_CD", dvobj.getRecord().get("CUSTOMER_CD"));   //顧客CD
            updcnt += delete("ForecastInfoMgr_2016082417899.ForecastDataCopy2_DEL22",param);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("DEL22") ;
        rvobj.Println("DEL22");
        return rvobj;
    }
    // Forecast Select(HP2D001T)
    public VOBJ ForecastDataCopy2_SEL12(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL12", "Forecast Select(HP2D001T)" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("FROM", dobj.getRetObject("S4").getRecord().get("FROM"));   //FROM
        param.put("USE_ORG_CD", dobj.getRetObject("R").getRecord().get("ORG_CD"));   //費用？生元部CD
        param.put("USE_COMPANY_CD", dobj.getRetObject("S4").getRecord().get("COMPANY_CD").substring(0,3));   //費用？生元？社CD
        List rlist = list("ForecastInfoMgr_2016082417899.ForecastDataCopy2_SEL12", param);
        dvobj.setName("SEL12");
        dvobj.setRecords(rlist);
        dvobj.Println("SEL12");
        return dvobj;
    }
    // Forecast INSERT(HP2D001T)
    public VOBJ ForecastDataCopy2_INS14(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"INS14", "Forecast INSERT(HP2D001T)" );
        VOBJ dvobj = dobj.getRetObject("SEL12");           // Input Dataset Object.
        dvobj.Println("INS14");
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap    param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("UPDATE_TIME", "");   //갱신일시
            param.put("USE_COMPANY_CD", dvobj.getRecord().get("USE_COMPANY_CD"));   //費用？生元？社CD
            param.put("USE_ORG_CD", dvobj.getRecord().get("USE_ORG_CD"));   //費用？生元部CD
            param.put("REQ_COMPANY_CD", dvobj.getRecord().get("REQ_COMPANY_CD"));   //要求元？社CD
            param.put("ITEM_NAME", dvobj.getRecord().get("ITEM_NAME"));   //ITEM명
            param.put("REQ_ORG_CD", dvobj.getRecord().get("REQ_ORG_CD"));   //要求元部CD
            param.put("YYYYMM", dvobj.getRecord().get("YYYYMM"));   //실적월
            param.put("VAL", dvobj.getRecord().getDouble("VAL")+"");   //Value
            param.put("INVEST_TYPE_CD", dvobj.getRecord().get("INVEST_TYPE_CD"));   //投資？分CD
            param.put("HPMS_ID", dvobj.getRecord().get("HPMS_ID"));   //HPMS_ID
            param.put("APPLICATION", dvobj.getRecord().get("APPLICATION"));   //用途
            param.put("PID", dvobj.getRecord().get("PID"));   //PID
            param.put("UNIT", dvobj.getRecord().get("UNIT"));   //통화단위
            param.put("CUSTOMER_CD", dvobj.getRecord().get("CUSTOMER_CD"));   //顧客CD
            param.put("DATA_TYPE", dobj.getRetObject("S4").getRecord().get("TO"));   //デ？タタイプ
            param.put("UPDATE_USER_ID", dobj.getRetObject("G").getRecord().get("USER_ID"));   //갱신자ID
            insert("ForecastInfoMgr_2016082417899.ForecastDataCopy2_INS14",param);
            updcnt++;
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("INS14") ;
        rvobj.Println("INS14");
        String message = "00002";
        dobj.setRetmsg(message);
        return rvobj;
    }
    // MAX(HP2D002T_TZ)
    public VOBJ ForecastDataCopy2_SEL40(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL40", "MAX(HP2D002T_TZ)" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        String  FROM="" ;          //FROM
        if(dobj.getRetObject("S4").getRecord().get("FROM").equals("A.DCP"))
        {
            FROM = "DCP_A";
        }
        else if(dobj.getRetObject("S4").getRecord().get("FROM").equals("P.DCP"))
        {
            FROM = "DCP_P";
        }
        else if(dobj.getRetObject("S4").getRecord().get("FROM").equals("T.DCP"))
        {
            FROM = "DCP_T";
        }
        else if(dobj.getRetObject("S4").getRecord().get("FROM").equals("M.DCP"))
        {
            FROM = "DCP_M";
        }
        else if(dobj.getRetObject("S4").getRecord().get("FROM").equals("E.DCP"))
        {
            FROM = "DCP_E";
        }
        else
        {
            FROM = "";
        }
        param.put("FROM", FROM);   //FROM
        param.put("USE_ORG_CD", dobj.getRetObject("R").getRecord().get("ORG_CD"));   //費用？生元部CD
        param.put("USE_COMPANY_CD", dobj.getRetObject("S4").getRecord().get("COMPANY_CD").substring(0,3));   //費用？生元？社CD
        List rlist = list("ForecastInfoMgr_2016082417899.ForecastDataCopy2_SEL40", param);
        dvobj.setName("SEL40");
        dvobj.setRecords(rlist);
        dvobj.Println("SEL40");
        return dvobj;
    }
    // Forecast Select(HP2D002T_TZ)
    public VOBJ ForecastDataCopy2_SEL21(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL21", "Forecast Select(HP2D002T_TZ)" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("TO", dobj.getRetObject("S4").getRecord().get("TO"));   //TO
        param.put("USE_ORG_CD", dobj.getRetObject("R").getRecord().get("ORG_CD"));   //費用？生元部CD
        param.put("DATA_TYPE", dobj.getRetObject("SEL40").getRecord().get("DATA_TYPE"));   //デ？タタイプ
        param.put("USE_COMPANY_CD", dobj.getRetObject("S4").getRecord().get("COMPANY_CD").substring(0,3));   //費用？生元？社CD
        List rlist = list("ForecastInfoMgr_2016082417899.ForecastDataCopy2_SEL21", param);
        dvobj.setName("SEL21");
        dvobj.setRecords(rlist);
        dvobj.Println("SEL21");
        return dvobj;
    }
    // Forecast delete(HP2D001T)
    public VOBJ ForecastDataCopy2_DEL10(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"DEL10", "Forecast delete(HP2D001T)" );
        VOBJ dvobj = dobj.getRetObject("SEL21");           // Input Dataset Object.
        dvobj.Println("DEL10");
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
            param.put("HPMS_ID", dvobj.getRecord().get("HPMS_ID"));   //HPMS_ID
            param.put("REQ_COMPANY_CD", dvobj.getRecord().get("REQ_COMPANY_CD"));   //要求元？社CD
            param.put("APPLICATION", dvobj.getRecord().get("APPLICATION"));   //用途
            param.put("REQ_ORG_CD", dvobj.getRecord().get("REQ_ORG_CD"));   //要求元部CD
            param.put("ITEM_NAME", dvobj.getRecord().get("ITEM_NAME"));   //ITEM명
            param.put("YYYYMM", dvobj.getRecord().get("YYYYMM"));   //실적월
            param.put("PID", dvobj.getRecord().get("PID"));   //PID
            param.put("INVEST_TYPE_CD", dvobj.getRecord().get("INVEST_TYPE_CD"));   //投資？分CD
            param.put("CUSTOMER_CD", dvobj.getRecord().get("CUSTOMER_CD"));   //顧客CD
            updcnt += delete("ForecastInfoMgr_2016082417899.ForecastDataCopy2_DEL10",param);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("DEL10") ;
        rvobj.Println("DEL10");
        return rvobj;
    }
    // Forecast Select(HP2D002T_TZ)
    public VOBJ ForecastDataCopy2_SEL20(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL20", "Forecast Select(HP2D002T_TZ)" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("FROM", dobj.getRetObject("SEL40").getRecord().get("DATA_TYPE"));   //FROM
        param.put("USE_ORG_CD", dobj.getRetObject("R").getRecord().get("ORG_CD"));   //費用？生元部CD
        param.put("USE_COMPANY_CD", dobj.getRetObject("S4").getRecord().get("COMPANY_CD").substring(0,3));   //費用？生元？社CD
        List rlist = list("ForecastInfoMgr_2016082417899.ForecastDataCopy2_SEL20", param);
        dvobj.setName("SEL20");
        dvobj.setRecords(rlist);
        dvobj.Println("SEL20");
        return dvobj;
    }
    // Forecast INSERT(HP2D001T)
    public VOBJ ForecastDataCopy2_INS21(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"INS21", "Forecast INSERT(HP2D001T)" );
        VOBJ dvobj = dobj.getRetObject("SEL20");           // Input Dataset Object.
        dvobj.Println("INS21");
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap    param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("UPDATE_TIME", "");   //갱신일시
            param.put("USE_COMPANY_CD", dvobj.getRecord().get("USE_COMPANY_CD"));   //費用？生元？社CD
            param.put("USE_ORG_CD", dvobj.getRecord().get("USE_ORG_CD"));   //費用？生元部CD
            param.put("REQ_COMPANY_CD", dvobj.getRecord().get("REQ_COMPANY_CD"));   //要求元？社CD
            param.put("ITEM_NAME", dvobj.getRecord().get("ITEM_NAME"));   //ITEM명
            param.put("REQ_ORG_CD", dvobj.getRecord().get("REQ_ORG_CD"));   //要求元部CD
            param.put("YYYYMM", dvobj.getRecord().get("YYYYMM"));   //실적월
            param.put("VAL", dvobj.getRecord().getDouble("VAL")+"");   //Value
            param.put("INVEST_TYPE_CD", dvobj.getRecord().get("INVEST_TYPE_CD"));   //投資？分CD
            param.put("HPMS_ID", dvobj.getRecord().get("HPMS_ID"));   //HPMS_ID
            param.put("APPLICATION", dvobj.getRecord().get("APPLICATION"));   //用途
            param.put("PID", dvobj.getRecord().get("PID"));   //PID
            param.put("UNIT", dvobj.getRecord().get("UNIT"));   //통화단위
            param.put("CUSTOMER_CD", dvobj.getRecord().get("CUSTOMER_CD"));   //顧客CD
            param.put("DATA_TYPE", dobj.getRetObject("S4").getRecord().get("TO"));   //デ？タタイプ
            param.put("UPDATE_USER_ID", dobj.getRetObject("G").getRecord().get("USER_ID"));   //갱신자ID
            insert("ForecastInfoMgr_2016082417899.ForecastDataCopy2_INS21",param);
            updcnt++;
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("INS21") ;
        rvobj.Println("INS21");
        String message = "00002";
        dobj.setRetmsg(message);
        return rvobj;
    }
    // Forecast delete(HP2D001T)
    public VOBJ ForecastDataCopy_DEL23(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"DEL23", "Forecast delete(HP2D001T)" );
        VOBJ dvobj = dobj.getRetObject("S3");           // Input Dataset Object.
        dvobj.Println("DEL23");
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap    param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("PID", dvobj.getRecord().get("PID"));   //PID
            param.put("DATA_TYPE", dobj.getRetObject("S3").getRecord().get("TO"));   //デ？タタイプ
            updcnt += delete("ForecastInfoMgr_2016082417899.ForecastDataCopy_DEL23",param);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("DEL23") ;
        rvobj.Println("DEL23");
        return rvobj;
    }
    // Input ORG
    public VOBJ Forecast_ORGCheck_SEL2(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL2", "Input ORG" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("USER_ID", dobj.getRetObject("G").getRecord().get("USER_ID"));   //사용자ID
        param.put("ORG_CD", dobj.getRetObject("S2").getRecord().get("ORG_CD"));   //ORG_CD
        param.put("COMPANY_CD", dobj.getRetObject("S2").getRecord().get("COMPANY_CD").substring(0,3));   //COMPANY_CD
        List rlist = list("ForecastInfoMgr_2016082417899.Forecast_ORGCheck_SEL2", param);
        dvobj.setName("SEL2");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // AUTH_CODE
    public VOBJ Forecast_ORGCheck_SEL4(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL4", "AUTH_CODE" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("ForecastInfoMgr_2016082417899.Forecast_ORGCheck_SEL4", param);
        dvobj.setName("SEL4");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // PID Info
    public VOBJ Forecast_PIDcheck_SEL2(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL2", "PID Info" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("USER_ID", dobj.getRetObject("G").getRecord().get("USER_ID"));   //사용자ID
        param.put("PID", dobj.getRetObject("S1").getRecord().get("PID"));   //PID
        List rlist = list("ForecastInfoMgr_2016082417899.Forecast_PIDcheck_SEL2", param);
        dvobj.setName("SEL2");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        dvobj.Println("SEL2");
        return dvobj;
    }
    // AUTH_CODE
    public VOBJ Forecast_PIDcheck_SEL4(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL4", "AUTH_CODE" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("ForecastInfoMgr_2016082417899.Forecast_PIDcheck_SEL4", param);
        dvobj.setName("SEL4");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // FCST_TILTE Conn
    public VOBJ FCST_TITLE_COMBO_SEL2(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL2", "FCST_TILTE Conn" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("AUTH_CD", dobj.getRetObject("G").getRecord().get("AUTH_CD"));   //AUTH_CD
        List rlist = list("ForecastInfoMgr_2016082417899.FCST_TITLE_COMBO_SEL2", param);
        dvobj.setName("SEL2");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // TO_FCST_TITLE conn
    public VOBJ FCST_TITLE_COMBO_SEL4(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL4", "TO_FCST_TITLE conn" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("PID", dobj.getRetObject("S5").getRecord().get("PID"));   //PID
        param.put("AUTH_CD", dobj.getRetObject("G").getRecord().get("AUTH_CD"));   //AUTH_CD
        List rlist = list("ForecastInfoMgr_2016082417899.FCST_TITLE_COMBO_SEL4", param);
        dvobj.setName("SEL4");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // ORG_FCST_TITLE conn
    public VOBJ FCST_TITLE_COMBO_SEL6(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL6", "ORG_FCST_TITLE conn" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("AUTH_CD", dobj.getRetObject("G").getRecord().get("AUTH_CD"));   //AUTH_CD
        List rlist = list("ForecastInfoMgr_2016082417899.FCST_TITLE_COMBO_SEL6", param);
        dvobj.setName("SEL6");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // ORG_FROM_TITLE conn(COPY)
    public VOBJ FCST_TITLE_COMBO_SEL9(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL9", "ORG_FROM_TITLE conn(COPY)" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("ORG_CD", dobj.getRetObject("S4").getRecord().get("ORG_CD"));   //ORG_CD
        param.put("AUTH_CD", dobj.getRetObject("G").getRecord().get("AUTH_CD"));   //AUTH_CD
        String  COMPANY_CD="" ;          //COMPANY_CD
        if(!dobj.getRetObject("S4").getRecord().get("COMPANY_CD").equals(""))
        {
            COMPANY_CD = dobj.getRetObject("S4").getRecord().get("COMPANY_CD").substring(0,3);
        }
        else
        {
            COMPANY_CD = "";
        }
        param.put("COMPANY_CD", COMPANY_CD);   //COMPANY_CD
        List rlist = list("ForecastInfoMgr_2016082417899.FCST_TITLE_COMBO_SEL9", param);
        dvobj.setName("SEL9");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        dvobj.Println("SEL9");
        return dvobj;
    }
    // ORG_TO_FCST_TITLE conn(COPY)
    public VOBJ FCST_TITLE_COMBO_SEL8(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL8", "ORG_TO_FCST_TITLE conn(COPY)" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("ORG_CD", dobj.getRetObject("S4").getRecord().get("ORG_CD"));   //ORG_CD
        param.put("AUTH_CD", dobj.getRetObject("G").getRecord().get("AUTH_CD"));   //AUTH_CD
        String  COMPANY_CD="" ;          //COMPANY_CD
        if(!dobj.getRetObject("S4").getRecord().get("COMPANY_CD").equals(""))
        {
            COMPANY_CD = dobj.getRetObject("S4").getRecord().get("COMPANY_CD").substring(0,3);
        }
        else
        {
            COMPANY_CD = "";
        }
        param.put("COMPANY_CD", COMPANY_CD);   //COMPANY_CD
        List rlist = list("ForecastInfoMgr_2016082417899.FCST_TITLE_COMBO_SEL8", param);
        dvobj.setName("SEL8");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // DATA_TYPE
    public VOBJ FCSTCOPYCombo_SEL1(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL1", "DATA_TYPE" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("PID", dobj.getRetObject("S2").getRecord().get("PID"));   //PID
        param.put("AUTH_CD", dobj.getRetObject("G").getRecord().get("AUTH_CD"));   //AUTH_CD
        List rlist = list("ForecastInfoMgr_2016082417899.FCSTCOPYCombo_SEL1", param);
        dvobj.setName("SEL1");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // DATA_TYPE
    public VOBJ FCSTCOPYCombo_SEL2(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL2", "DATA_TYPE" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("PID", dobj.getRetObject("S2").getRecord().get("PID"));   //PID
        param.put("AUTH_CD", dobj.getRetObject("G").getRecord().get("AUTH_CD"));   //AUTH_CD
        List rlist = list("ForecastInfoMgr_2016082417899.FCSTCOPYCombo_SEL2", param);
        dvobj.setName("SEL2");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // ORG_SEQ(PID) Check
    public VOBJ Forecast_PIDcheck_SEL6(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL6", "ORG_SEQ(PID) Check" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("ORG_SEQ", dobj.getRetObject("SEL4").getRecord().get("ORG_SEQ_L4"));   //ORG_SEQ
        List rlist = list("ForecastInfoMgr_2016082417899.Forecast_PIDcheck_SEL6", param);
        dvobj.setName("SEL6");
        dvobj.setRecords(rlist);
        dvobj.Println("SEL6");
        return dvobj;
    }
    // LoginUser check ORG
    public VOBJ Forecast_PIDcheck_SEL8(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL8", "LoginUser check ORG" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("ORG_CD", dobj.getRetObject("G").getRecord().get("ORG_CD"));   //ORG_CD
        param.put("COMPANY_CD", dobj.getRetObject("G").getRecord().get("COMPANY_CD"));   //COMPANY_CD
        List rlist = list("ForecastInfoMgr_2016082417899.Forecast_PIDcheck_SEL8", param);
        dvobj.setName("SEL8");
        dvobj.setRecords(rlist);
        dvobj.Println("SEL8");
        return dvobj;
    }
    // LoginUser ORG_SEQ check
    public VOBJ Forecast_PIDcheck_SEL10(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL10", "LoginUser ORG_SEQ check" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("ORG_SEQ", dobj.getRetObject("SEL8").getRecord().get("ORG_SEQ_L4"));   //ORG_SEQ
        List rlist = list("ForecastInfoMgr_2016082417899.Forecast_PIDcheck_SEL10", param);
        dvobj.setName("SEL10");
        dvobj.setRecords(rlist);
        dvobj.Println("SEL10");
        return dvobj;
    }
    // CompanyList
    public VOBJ ServiceID00_SEL3(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL3", "CompanyList" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("ForecastInfoMgr_2016082417899.ServiceID00_SEL3", param);
        dvobj.setName("SEL3");
        dvobj.setRecords(rlist);
        dvobj.Println("SEL3");
        return dvobj;
    }
    // CompanyCombo
    public VOBJ ServiceID00_MRG1(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"MRG1", "CompanyCombo" );
        VOBJ       rvobj= null;
        rvobj = wutil.getMergeObject(dobj, "SEL3","ITEM_CD, ITEM_NM" );
        rvobj.setName("MRG1") ;
        rvobj.setRetcode(1);
        return rvobj;
    }
    // DATA_TYPE
    public VOBJ ServiceID00_SEL5(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL5", "DATA_TYPE" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("AUTH_CD", dobj.getRetObject("G").getRecord().get("AUTH_CD"));   //AUTH_CD
        List rlist = list("ForecastInfoMgr_2016082417899.ServiceID00_SEL5", param);
        dvobj.setName("SEL5");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        dvobj.Println("SEL5");
        return dvobj;
    }
    // forecast title Save2
    public VOBJ ForecastTitleSet2_UNI2(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"UNI2", "forecast title Save2" );
        VOBJ dvobj = dobj.getRetObject("S2");           //사용자 화면에서 발생한 Object입니다.
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        HashMap    param= null;
        int        inscnt=0, updcnt =0 ,unicnt=0,  rtncnt=0;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("DATA_TYPE", dvobj.getRecord().get("DATA_TYPE"));   //デ？タタイプ
            param.put("DATA_TYPE_NM", dvobj.getRecord().get("DATA_TYPE_NM"));   //DATA_TYPE_NM
            param.put("ORG_CD", dvobj.getRecord().get("ORG_CD"));   //ORG_CD
            param.put("COMPANY_CD", wutil.substr(dobj.getRetObject("S2").getRecord().get("COMPANY_CD"),0,3));   //COMPANY_CD
            rtncnt= update("ForecastInfoMgr_2016082417899.ForecastTitleSet2_UNI2_UPD",param);
            if(rtncnt < 1)
            {
                insert("ForecastInfoMgr_2016082417899.ForecastTitleSet2_UNI2_INS",param);
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
        rvobj.setName("UNI2") ;
        return rvobj;
    }
    // forecast title Save
    public VOBJ ForecastTitleSet_UNI3(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"UNI3", "forecast title Save" );
        VOBJ dvobj = dobj.getRetObject("S1");           //사용자 화면에서 발생한 Object입니다.
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        HashMap    param= null;
        int        inscnt=0, updcnt =0 ,unicnt=0,  rtncnt=0;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("DATA_TYPE", dvobj.getRecord().get("DATA_TYPE"));   //デ？タタイプ
            param.put("DATA_TYPE_NM", dvobj.getRecord().get("DATA_TYPE_NM"));   //DATA_TYPE_NM
            param.put("PID", dvobj.getRecord().get("PID"));   //PID
            rtncnt= update("ForecastInfoMgr_2016082417899.ForecastTitleSet_UNI3_UPD",param);
            if(rtncnt < 1)
            {
                insert("ForecastInfoMgr_2016082417899.ForecastTitleSet_UNI3_INS",param);
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
        rvobj.setName("UNI3") ;
        return rvobj;
    }
}