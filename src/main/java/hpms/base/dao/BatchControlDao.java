
package hpms.base.dao;
import java.util.*;
import egov.wizware.com.*;
import egov.wizware.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import com.ibatis.sqlmap.client.SqlMapClient;
import javax.annotation.Resource;
@Repository("BatchControl20160912111170Dao")
public class BatchControlDao extends EgovAbstractDAO
{
    // F_YYYMM
    public VOBJ copyMiddle_SEL11(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL11", "F_YYYMM" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("YYYY", dobj.getRetObject("S4").getRecord().get("YYYY"));   //YYYY
        List rlist = list("BatchControl_20160912111170.copyMiddle_SEL11", param);
        dvobj.setName("SEL11");
        dvobj.setRecords(rlist);
        dvobj.Println("SEL11");
        return dvobj;
    }
    // MP_YYYY_YYYYMMDD
    public VOBJ copyMiddle_DEL13(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"DEL13", "MP_YYYY_YYYYMMDD" );
        VOBJ dvobj = dobj.getRetObject("S4");           // Input Dataset Object.
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap    param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("DATA_TYPE", "MTP"+"_"+dobj.getRetObject("S4").getRecord().get("YYYY"));   //デ？タタイプ
            updcnt += delete("BatchControl_20160912111170.copyMiddle_DEL13",param);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("DEL13") ;
        return rvobj;
    }
    // snapshot Search
    public VOBJ copyMiddle_SEL16(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL16", "snapshot Search" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("APPROVAL_YYYYMMDD", dobj.getRetObject("SEL11").getRecord().get("C_DATE"));   //승인일
        param.put("YYYY", dobj.getRetObject("S4").getRecord().get("YYYY"));   //YYYY
        List rlist = list("BatchControl_20160912111170.copyMiddle_SEL16", param);
        dvobj.setName("SEL16");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // snapshot Delete
    public VOBJ copyMiddle_DEL14(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"DEL14", "snapshot Delete" );
        VOBJ dvobj = dobj.getRetObject("SEL16");           //snapshot Search Input Object(CALLcopyMiddle_SEL16)
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap    param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("DATA_TYPE", dvobj.getRecord().get("DATA_TYPE"));   //デ？タタイプ
            param.put("APPROVAL_YYYYMMDD", dvobj.getRecord().get("APPROVAL_YYYYMMDD"));   //승인일
            updcnt += delete("BatchControl_20160912111170.copyMiddle_DEL14",param);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("DEL14") ;
        return rvobj;
    }
    // get HP2D002T
    public VOBJ copyMiddle_SEL2(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL2", "get HP2D002T " );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("FROM_YYYYMM", dobj.getRetObject("SEL11").getRecord().get("FROM_YYYYMM"));   //입사월
        param.put("DATA_TYPE", dobj.getRetObject("S4").getRecord().get("DATA_TYPE"));   //デ？タタイプ
        List rlist = list("BatchControl_20160912111170.copyMiddle_SEL2", param);
        dvobj.setName("SEL2");
        dvobj.setRecords(rlist);
        dvobj.Println("SEL2");
        return dvobj;
    }
    // CopyHP2D002T_TZ
    public VOBJ copyMiddle_INS14(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"INS14", "CopyHP2D002T_TZ" );
        VOBJ dvobj = dobj.getRetObject("SEL2");           //get HP2D002T  Input Object(CALLcopyMiddle_SEL2)
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap    param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("APPROVAL_YYYYMMDD", "");   //승인일
            param.put("UPDATE_TIME", "");   //갱신일시
            param.put("USE_ORG_CD", dvobj.getRecord().get("USE_ORG_CD"));   //費用？生元部CD
            param.put("REQ_COMPANY_CD", dvobj.getRecord().get("REQ_COMPANY_CD"));   //要求元？社CD
            param.put("ITEM_NAME", dvobj.getRecord().get("ITEM_NAME"));   //ITEM명
            param.put("REQ_ORG_CD", dvobj.getRecord().get("REQ_ORG_CD"));   //要求元部CD
            param.put("SRC_ORG_CD", dvobj.getRecord().get("SRC_ORG_CD"));   //配賦元部CD
            param.put("VAL", dvobj.getRecord().getDouble("VAL")+"");   //Value
            param.put("INVEST_TYPE_CD", dvobj.getRecord().get("INVEST_TYPE_CD"));   //投資？分CD
            param.put("HPMS_ID", dvobj.getRecord().get("HPMS_ID"));   //HPMS_ID
            param.put("UNIT", dvobj.getRecord().get("UNIT"));   //통화단위
            param.put("CUSTOMER_CD", dvobj.getRecord().get("CUSTOMER_CD"));   //顧客CD
            param.put("USE_COMPANY_CD", dvobj.getRecord().get("USE_COMPANY_CD"));   //費用？生元？社CD
            param.put("UPLOAD_FILE_NAME", dvobj.getRecord().get("UPLOAD_FILE_NAME"));   //UPLOAD_FILE_NAME
            param.put("SRC_COMPANY_CD", dvobj.getRecord().get("SRC_COMPANY_CD"));   //配賦元？社CD
            param.put("CALC_MST_VER", dvobj.getRecord().get("CALC_MST_VER"));   //CALC_MST_VER
            param.put("YYYYMM", dvobj.getRecord().get("YYYYMM"));   //실적월
            param.put("DST_COMPANY_CD", dvobj.getRecord().get("DST_COMPANY_CD"));   //DST_COMPANY_CD
            param.put("DST_ORG_CD", dvobj.getRecord().get("DST_ORG_CD"));   //DST_ORG_CD
            param.put("VAL_TYPE", dvobj.getRecord().get("VAL_TYPE"));   //VAL_TYPE
            param.put("APPLICATION", dvobj.getRecord().get("APPLICATION"));   //用途
            param.put("UNIT3", dvobj.getRecord().get("UNIT3"));   //UNIT3
            param.put("UNIT2", dvobj.getRecord().get("UNIT2"));   //UNIT2
            param.put("PID", dvobj.getRecord().get("PID"));   //PID
            param.put("VAL3", dvobj.getRecord().get("VAL3"));   //VAL3
            param.put("VAL2", dvobj.getRecord().get("VAL2"));   //VAL2
            param.put("DATA_TYPE", "MTP"+"_"+dobj.getRetObject("S4").getRecord().get("YYYY")+"_"+dobj.getRetObject("SEL11").getRecord().get("C_DATE"));   //デ？タタイプ
            param.put("UPDATE_USER_ID", dobj.getRetObject("G").getRecord().get("USER_ID"));   //갱신자ID
            insert("BatchControl_20160912111170.copyMiddle_INS14",param);
            updcnt++;
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("INS14") ;
        rvobj.Println("INS14");
        return rvobj;
    }
    // CopyHP2D002T_TZ
    public VOBJ copyMiddle_INS16(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"INS16", "CopyHP2D002T_TZ" );
        VOBJ dvobj = dobj.getRetObject("SEL2");           //get HP2D002T  Input Object(CALLcopyMiddle_SEL2)
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap    param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("APPROVAL_YYYYMMDD", "");   //승인일
            param.put("UPDATE_TIME", "");   //갱신일시
            param.put("USE_ORG_CD", dvobj.getRecord().get("USE_ORG_CD"));   //費用？生元部CD
            param.put("REQ_COMPANY_CD", dvobj.getRecord().get("REQ_COMPANY_CD"));   //要求元？社CD
            param.put("ITEM_NAME", dvobj.getRecord().get("ITEM_NAME"));   //ITEM명
            param.put("REQ_ORG_CD", dvobj.getRecord().get("REQ_ORG_CD"));   //要求元部CD
            param.put("SRC_ORG_CD", dvobj.getRecord().get("SRC_ORG_CD"));   //配賦元部CD
            param.put("VAL", dvobj.getRecord().getDouble("VAL")+"");   //Value
            param.put("INVEST_TYPE_CD", dvobj.getRecord().get("INVEST_TYPE_CD"));   //投資？分CD
            param.put("HPMS_ID", dvobj.getRecord().get("HPMS_ID"));   //HPMS_ID
            param.put("UNIT", dvobj.getRecord().get("UNIT"));   //통화단위
            param.put("CUSTOMER_CD", dvobj.getRecord().get("CUSTOMER_CD"));   //顧客CD
            param.put("USE_COMPANY_CD", dvobj.getRecord().get("USE_COMPANY_CD"));   //費用？生元？社CD
            param.put("UPLOAD_FILE_NAME", dvobj.getRecord().get("UPLOAD_FILE_NAME"));   //UPLOAD_FILE_NAME
            param.put("SRC_COMPANY_CD", dvobj.getRecord().get("SRC_COMPANY_CD"));   //配賦元？社CD
            param.put("CALC_MST_VER", dvobj.getRecord().get("CALC_MST_VER"));   //CALC_MST_VER
            param.put("YYYYMM", dvobj.getRecord().get("YYYYMM"));   //실적월
            param.put("DST_COMPANY_CD", dvobj.getRecord().get("DST_COMPANY_CD"));   //DST_COMPANY_CD
            param.put("DST_ORG_CD", dvobj.getRecord().get("DST_ORG_CD"));   //DST_ORG_CD
            param.put("VAL_TYPE", dvobj.getRecord().get("VAL_TYPE"));   //VAL_TYPE
            param.put("APPLICATION", dvobj.getRecord().get("APPLICATION"));   //用途
            param.put("UNIT3", dvobj.getRecord().get("UNIT3"));   //UNIT3
            param.put("UNIT2", dvobj.getRecord().get("UNIT2"));   //UNIT2
            param.put("PID", dvobj.getRecord().get("PID"));   //PID
            param.put("VAL3", dvobj.getRecord().get("VAL3"));   //VAL3
            param.put("VAL2", dvobj.getRecord().get("VAL2"));   //VAL2
            param.put("DATA_TYPE", "MTP"+"_"+dobj.getRetObject("S4").getRecord().get("YYYY"));   //デ？タタイプ
            param.put("UPDATE_USER_ID", dobj.getRetObject("G").getRecord().get("USER_ID"));   //갱신자ID
            insert("BatchControl_20160912111170.copyMiddle_INS16",param);
            updcnt++;
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("INS16") ;
        rvobj.Println("INS16");
        return rvobj;
    }
    // OptionTableSave
    public VOBJ copyMiddle_UNI8(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"UNI8", "OptionTableSave" );
        VOBJ dvobj = dobj.getRetObject("S4");           // Input Dataset Object.
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        HashMap    param= null;
        int        inscnt=0, updcnt =0 ,unicnt=0,  rtncnt=0;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("UPDATE_TIME", "");   //갱신일시
            param.put("CD", dvobj.getRecord().get("CD")+"_"+dvobj.getRecord().get("DATA_TYPE"));   //코드
            param.put("CD_TYPE", "MCTL");   //CD_TYPE
            param.put("UPDATE_USER_ID", dobj.getRetObject("G").getRecord().get("USER_ID"));   //갱신자ID
            param.put("VAL1", dvobj.getRecord().get("DATA_TYPE"));   //VAL1
            rtncnt= update("BatchControl_20160912111170.copyMiddle_UNI8_UPD",param);
            if(rtncnt < 1)
            {
                insert("BatchControl_20160912111170.copyMiddle_UNI8_INS",param);
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
        rvobj.setName("UNI8") ;
        return rvobj;
    }
    // Confirm Info
    public VOBJ copyMiddle_SEL3(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL3", "Confirm Info" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("CD", dobj.getRetObject("S4").getRecord().get("CD"));   //코드
        param.put("DATA_TYPE", dobj.getRetObject("S4").getRecord().get("DATA_TYPE"));   //デ？タタイプ
        List rlist = list("BatchControl_20160912111170.copyMiddle_SEL3", param);
        dvobj.setName("SEL3");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // copy HP2DM11T_TZ
    public VOBJ copyMiddle_XIUD11(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"XIUD11", "copy HP2DM11T_TZ " );
        VOBJ dvobj = dobj.getRetObject("S4");            // Input Dataset Object.
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("UPDATE_TIME", "");   //갱신일시
            param.put("YYYYMMDD", "");   //年月日
            param.put("DATA_TYPE_1", "MTP"+"_"+dobj.getRetObject("S4").getRecord().get("YYYY"));   //デ？タタイプ
            param.put("UPDATE_USER_ID", dobj.getRetObject("G").getRecord().get("USER_ID"));   //갱신자ID
            param.put("DATA_TYPE", "MTP");   //デ？タタイプ
            insert("BatchControl_20160912111170.copyMiddle_XIUD11",param);
            updcnt++;
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("XIUD11");
        return rvobj;
    }
    // HP2DM11T  Delete
    public VOBJ copyMiddle_DEL17(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"DEL17", "HP2DM11T  Delete" );
        VOBJ dvobj = dobj.getRetObject("S3");           // Input Dataset Object.
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap    param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("DATA_TYPE", "MTP");   //デ？タタイプ
            updcnt += delete("BatchControl_20160912111170.copyMiddle_DEL17",param);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("DEL17") ;
        return rvobj;
    }
    // F_YYYMM
    public VOBJ copyMP_SEL7(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL7", "F_YYYMM" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("YYYY", dobj.getRetObject("S3").getRecord().get("YYYY"));   //YYYY
        List rlist = list("BatchControl_20160912111170.copyMP_SEL7", param);
        dvobj.setName("SEL7");
        dvobj.setRecords(rlist);
        dvobj.Println("SEL7");
        return dvobj;
    }
    // MP_YYYY_YYYYMMDD
    public VOBJ copyMP_DEL15(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"DEL15", "MP_YYYY_YYYYMMDD" );
        VOBJ dvobj = dobj.getRetObject("S3");           // Input Dataset Object.
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap    param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("DATA_TYPE", "MP"+"_"+dobj.getRetObject("S3").getRecord().get("YYYY"));   //デ？タタイプ
            updcnt += delete("BatchControl_20160912111170.copyMP_DEL15",param);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("DEL15") ;
        return rvobj;
    }
    // snapshot Search
    public VOBJ copyMP_SEL28(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL28", "snapshot Search" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("APPROVAL_YYYYMMDD", dobj.getRetObject("SEL7").getRecord().get("C_DATE"));   //승인일
        param.put("YYYY", dobj.getRetObject("S3").getRecord().get("YYYY"));   //YYYY
        List rlist = list("BatchControl_20160912111170.copyMP_SEL28", param);
        dvobj.setName("SEL28");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // MP_YYYY
    public VOBJ copyMP_DEL12(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"DEL12", "MP_YYYY" );
        VOBJ dvobj = dobj.getRetObject("SEL28");           //snapshot Search Input Object(CALLcopyMP_SEL28)
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap    param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("DATA_TYPE", dvobj.getRecord().get("DATA_TYPE"));   //デ？タタイプ
            param.put("APPROVAL_YYYYMMDD", dvobj.getRecord().get("APPROVAL_YYYYMMDD"));   //승인일
            updcnt += delete("BatchControl_20160912111170.copyMP_DEL12",param);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("DEL12") ;
        return rvobj;
    }
    // get HP2D002T
    public VOBJ copyMP_SEL2(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL2", "get HP2D002T " );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("FROM_YYYYMM", dobj.getRetObject("SEL7").getRecord().get("FROM_YYYYMM"));   //입사월
        param.put("DATA_TYPE", dobj.getRetObject("S3").getRecord().get("DATA_TYPE"));   //デ？タタイプ
        List rlist = list("BatchControl_20160912111170.copyMP_SEL2", param);
        dvobj.setName("SEL2");
        dvobj.setRecords(rlist);
        dvobj.Println("SEL2");
        return dvobj;
    }
    // CopyHP2D002T_TZ
    public VOBJ copyMP_INS13(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"INS13", "CopyHP2D002T_TZ" );
        VOBJ dvobj = dobj.getRetObject("SEL2");           //get HP2D002T  Input Object(CALLcopyMP_SEL2)
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap    param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("APPROVAL_YYYYMMDD", "");   //승인일
            param.put("UPDATE_TIME", "");   //갱신일시
            param.put("USE_ORG_CD", dvobj.getRecord().get("USE_ORG_CD"));   //費用？生元部CD
            param.put("REQ_COMPANY_CD", dvobj.getRecord().get("REQ_COMPANY_CD"));   //要求元？社CD
            param.put("ITEM_NAME", dvobj.getRecord().get("ITEM_NAME"));   //ITEM명
            param.put("REQ_ORG_CD", dvobj.getRecord().get("REQ_ORG_CD"));   //要求元部CD
            param.put("SRC_ORG_CD", dvobj.getRecord().get("SRC_ORG_CD"));   //配賦元部CD
            param.put("VAL", dvobj.getRecord().getDouble("VAL")+"");   //Value
            param.put("INVEST_TYPE_CD", dvobj.getRecord().get("INVEST_TYPE_CD"));   //投資？分CD
            param.put("HPMS_ID", dvobj.getRecord().get("HPMS_ID"));   //HPMS_ID
            param.put("UNIT", dvobj.getRecord().get("UNIT"));   //통화단위
            param.put("CUSTOMER_CD", dvobj.getRecord().get("CUSTOMER_CD"));   //顧客CD
            param.put("USE_COMPANY_CD", dvobj.getRecord().get("USE_COMPANY_CD"));   //費用？生元？社CD
            param.put("UPLOAD_FILE_NAME", dvobj.getRecord().get("UPLOAD_FILE_NAME"));   //UPLOAD_FILE_NAME
            param.put("SRC_COMPANY_CD", dvobj.getRecord().get("SRC_COMPANY_CD"));   //配賦元？社CD
            param.put("CALC_MST_VER", dvobj.getRecord().get("CALC_MST_VER"));   //CALC_MST_VER
            param.put("YYYYMM", dvobj.getRecord().get("YYYYMM"));   //실적월
            param.put("DST_COMPANY_CD", dvobj.getRecord().get("DST_COMPANY_CD"));   //DST_COMPANY_CD
            param.put("DST_ORG_CD", dvobj.getRecord().get("DST_ORG_CD"));   //DST_ORG_CD
            param.put("VAL_TYPE", dvobj.getRecord().get("VAL_TYPE"));   //VAL_TYPE
            param.put("APPLICATION", dvobj.getRecord().get("APPLICATION"));   //用途
            param.put("UNIT3", dvobj.getRecord().get("UNIT3"));   //UNIT3
            param.put("UNIT2", dvobj.getRecord().get("UNIT2"));   //UNIT2
            param.put("PID", dvobj.getRecord().get("PID"));   //PID
            param.put("VAL3", dvobj.getRecord().get("VAL3"));   //VAL3
            param.put("VAL2", dvobj.getRecord().get("VAL2"));   //VAL2
            param.put("DATA_TYPE", "MP"+"_"+dobj.getRetObject("S3").getRecord().get("YYYY"));   //デ？タタイプ
            param.put("UPDATE_USER_ID", dobj.getRetObject("G").getRecord().get("USER_ID"));   //갱신자ID
            insert("BatchControl_20160912111170.copyMP_INS13",param);
            updcnt++;
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("INS13") ;
        return rvobj;
    }
    // CopyHP2D002T_TZ
    public VOBJ copyMP_INS14(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"INS14", "CopyHP2D002T_TZ" );
        VOBJ dvobj = dobj.getRetObject("SEL2");           //get HP2D002T  Input Object(CALLcopyMP_SEL2)
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap    param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("APPROVAL_YYYYMMDD", "");   //승인일
            param.put("UPDATE_TIME", "");   //갱신일시
            param.put("USE_ORG_CD", dvobj.getRecord().get("USE_ORG_CD"));   //費用？生元部CD
            param.put("REQ_COMPANY_CD", dvobj.getRecord().get("REQ_COMPANY_CD"));   //要求元？社CD
            param.put("ITEM_NAME", dvobj.getRecord().get("ITEM_NAME"));   //ITEM명
            param.put("REQ_ORG_CD", dvobj.getRecord().get("REQ_ORG_CD"));   //要求元部CD
            param.put("SRC_ORG_CD", dvobj.getRecord().get("SRC_ORG_CD"));   //配賦元部CD
            param.put("VAL", dvobj.getRecord().getDouble("VAL")+"");   //Value
            param.put("INVEST_TYPE_CD", dvobj.getRecord().get("INVEST_TYPE_CD"));   //投資？分CD
            param.put("HPMS_ID", dvobj.getRecord().get("HPMS_ID"));   //HPMS_ID
            param.put("UNIT", dvobj.getRecord().get("UNIT"));   //통화단위
            param.put("CUSTOMER_CD", dvobj.getRecord().get("CUSTOMER_CD"));   //顧客CD
            param.put("USE_COMPANY_CD", dvobj.getRecord().get("USE_COMPANY_CD"));   //費用？生元？社CD
            param.put("UPLOAD_FILE_NAME", dvobj.getRecord().get("UPLOAD_FILE_NAME"));   //UPLOAD_FILE_NAME
            param.put("SRC_COMPANY_CD", dvobj.getRecord().get("SRC_COMPANY_CD"));   //配賦元？社CD
            param.put("CALC_MST_VER", dvobj.getRecord().get("CALC_MST_VER"));   //CALC_MST_VER
            param.put("YYYYMM", dvobj.getRecord().get("YYYYMM"));   //실적월
            param.put("DST_COMPANY_CD", dvobj.getRecord().get("DST_COMPANY_CD"));   //DST_COMPANY_CD
            param.put("DST_ORG_CD", dvobj.getRecord().get("DST_ORG_CD"));   //DST_ORG_CD
            param.put("VAL_TYPE", dvobj.getRecord().get("VAL_TYPE"));   //VAL_TYPE
            param.put("APPLICATION", dvobj.getRecord().get("APPLICATION"));   //用途
            param.put("UNIT3", dvobj.getRecord().get("UNIT3"));   //UNIT3
            param.put("UNIT2", dvobj.getRecord().get("UNIT2"));   //UNIT2
            param.put("PID", dvobj.getRecord().get("PID"));   //PID
            param.put("VAL3", dvobj.getRecord().get("VAL3"));   //VAL3
            param.put("VAL2", dvobj.getRecord().get("VAL2"));   //VAL2
            param.put("DATA_TYPE", "MP"+"_"+dobj.getRetObject("S3").getRecord().get("YYYY")+"_"+dobj.getRetObject("SEL7").getRecord().get("C_DATE"));   //デ？タタイプ
            param.put("UPDATE_USER_ID", dobj.getRetObject("G").getRecord().get("USER_ID"));   //갱신자ID
            insert("BatchControl_20160912111170.copyMP_INS14",param);
            updcnt++;
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("INS14") ;
        return rvobj;
    }
    // OptionTableSave
    public VOBJ copyMP_UNI5(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"UNI5", "OptionTableSave" );
        VOBJ dvobj = dobj.getRetObject("S3");           // Input Dataset Object.
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        HashMap    param= null;
        int        inscnt=0, updcnt =0 ,unicnt=0,  rtncnt=0;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("UPDATE_TIME", "");   //갱신일시
            param.put("CD", dvobj.getRecord().get("CD")+"_"+dvobj.getRecord().get("DATA_TYPE"));   //코드
            param.put("CD_TYPE", "MCTL");   //CD_TYPE
            param.put("UPDATE_USER_ID", dobj.getRetObject("G").getRecord().get("USER_ID"));   //갱신자ID
            param.put("VAL1", dvobj.getRecord().get("DATA_TYPE"));   //VAL1
            rtncnt= update("BatchControl_20160912111170.copyMP_UNI5_UPD",param);
            if(rtncnt < 1)
            {
                insert("BatchControl_20160912111170.copyMP_UNI5_INS",param);
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
        rvobj.setName("UNI5") ;
        return rvobj;
    }
    // Confirm Info
    public VOBJ copyMP_SEL3(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL3", "Confirm Info" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("CD", dobj.getRetObject("S3").getRecord().get("CD"));   //코드
        param.put("DATA_TYPE", dobj.getRetObject("S3").getRecord().get("DATA_TYPE"));   //デ？タタイプ
        List rlist = list("BatchControl_20160912111170.copyMP_SEL3", param);
        dvobj.setName("SEL3");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // copy HP2DM11T_TZ
    public VOBJ copyMP_XIUD13(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"XIUD13", "copy HP2DM11T_TZ " );
        VOBJ dvobj = dobj.getRetObject("S3");            // Input Dataset Object.
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("UPDATE_TIME", "");   //갱신일시
            param.put("YYYYMMDD", "");   //年月日
            param.put("DATA_TYPE_1", "MP"+"_"+dobj.getRetObject("S3").getRecord().get("YYYY"));   //デ？タタイプ
            param.put("UPDATE_USER_ID", dobj.getRetObject("G").getRecord().get("USER_ID"));   //갱신자ID
            param.put("DATA_TYPE", "MP");   //デ？タタイプ
            insert("BatchControl_20160912111170.copyMP_XIUD13",param);
            updcnt++;
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("XIUD13");
        return rvobj;
    }
    // HP2DM11T  Delete
    public VOBJ copyMP_DEL18(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"DEL18", "HP2DM11T  Delete" );
        VOBJ dvobj = dobj.getRetObject("S3");           // Input Dataset Object.
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap    param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("DATA_TYPE", "MP");   //デ？タタイプ
            updcnt += delete("BatchControl_20160912111170.copyMP_DEL18",param);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("DEL18") ;
        return rvobj;
    }
    // Before Confirm YYYYMM
    public VOBJ copyLMP_SEL26(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL26", "Before Confirm YYYYMM" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("BatchControl_20160912111170.copyLMP_SEL26", param);
        dvobj.setName("SEL26");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // last month
    public VOBJ copyLMP_SEL13(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL13", "last month" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("YYYYMM", dobj.getRetObject("SEL26").getRecord().get("YYYYMM"));   //실적월
        List rlist = list("BatchControl_20160912111170.copyLMP_SEL13", param);
        dvobj.setName("SEL13");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // get HP2D002T
    public VOBJ copyLMP_SEL2(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL2", "get HP2D002T " );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("YYYYMM", dobj.getRetObject("SEL13").getRecord().get("MONTHLY_YYYYMM"));   //실적월
        param.put("DATA_TYPE", dobj.getRetObject("S2").getRecord().get("DATA_TYPE"));   //デ？タタイプ
        List rlist = list("BatchControl_20160912111170.copyLMP_SEL2", param);
        dvobj.setName("SEL2");
        dvobj.setRecords(rlist);
        dvobj.Println("SEL2");
        return dvobj;
    }
    // CopyHP2D002T_TZ
    public VOBJ copyLMP_UNI4(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"UNI4", "CopyHP2D002T_TZ" );
        VOBJ dvobj = dobj.getRetObject("SEL2");           //get HP2D002T  Input Object(CALLcopyLMP_SEL2)
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        HashMap    param= null;
        int        inscnt=0, updcnt =0 ,unicnt=0,  rtncnt=0;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("APPROVAL_YYYYMMDD", "");   //승인일
            param.put("UPDATE_TIME", "");   //갱신일시
            param.put("USE_ORG_CD", dvobj.getRecord().get("USE_ORG_CD"));   //費用？生元部CD
            param.put("REQ_COMPANY_CD", dvobj.getRecord().get("REQ_COMPANY_CD"));   //要求元？社CD
            param.put("ITEM_NAME", dvobj.getRecord().get("ITEM_NAME"));   //ITEM명
            param.put("REQ_ORG_CD", dvobj.getRecord().get("REQ_ORG_CD"));   //要求元部CD
            param.put("SRC_ORG_CD", dvobj.getRecord().get("SRC_ORG_CD"));   //配賦元部CD
            param.put("VAL", dvobj.getRecord().getDouble("VAL")+"");   //Value
            param.put("INVEST_TYPE_CD", dvobj.getRecord().get("INVEST_TYPE_CD"));   //投資？分CD
            param.put("HPMS_ID", dvobj.getRecord().get("HPMS_ID"));   //HPMS_ID
            param.put("UNIT", dvobj.getRecord().get("UNIT"));   //통화단위
            param.put("CUSTOMER_CD", dvobj.getRecord().get("CUSTOMER_CD"));   //顧客CD
            param.put("USE_COMPANY_CD", dvobj.getRecord().get("USE_COMPANY_CD"));   //費用？生元？社CD
            param.put("UPLOAD_FILE_NAME", dvobj.getRecord().get("UPLOAD_FILE_NAME"));   //UPLOAD_FILE_NAME
            param.put("SRC_COMPANY_CD", dvobj.getRecord().get("SRC_COMPANY_CD"));   //配賦元？社CD
            param.put("CALC_MST_VER", dvobj.getRecord().get("CALC_MST_VER"));   //CALC_MST_VER
            param.put("YYYYMM", dvobj.getRecord().get("YYYYMM"));   //실적월
            param.put("DST_COMPANY_CD", dvobj.getRecord().get("DST_COMPANY_CD"));   //DST_COMPANY_CD
            param.put("DST_ORG_CD", dvobj.getRecord().get("DST_ORG_CD"));   //DST_ORG_CD
            param.put("VAL_TYPE", dvobj.getRecord().get("VAL_TYPE"));   //VAL_TYPE
            param.put("APPLICATION", dvobj.getRecord().get("APPLICATION"));   //用途
            param.put("UNIT3", dvobj.getRecord().get("UNIT3"));   //UNIT3
            param.put("UNIT2", dvobj.getRecord().get("UNIT2"));   //UNIT2
            param.put("PID", dvobj.getRecord().get("PID"));   //PID
            param.put("VAL3", dvobj.getRecord().get("VAL3"));   //VAL3
            param.put("VAL2", dvobj.getRecord().get("VAL2"));   //VAL2
            param.put("DATA_TYPE", "LMP"+"_"+dobj.getRetObject("SEL13").getRecord().get("MONTHLY_YYYYMM"));   //デ？タタイプ
            param.put("UPDATE_USER_ID", dobj.getRetObject("G").getRecord().get("USER_ID"));   //갱신자ID
            rtncnt= update("BatchControl_20160912111170.copyLMP_UNI4_UPD",param);
            if(rtncnt < 1)
            {
                insert("BatchControl_20160912111170.copyLMP_UNI4_INS",param);
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
        rvobj.setName("UNI4") ;
        return rvobj;
    }
    // OptionTableSave
    public VOBJ copyLMP_UNI7(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"UNI7", "OptionTableSave" );
        VOBJ dvobj = dobj.getRetObject("S2");           // Input Dataset Object.
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        HashMap    param= null;
        int        inscnt=0, updcnt =0 ,unicnt=0,  rtncnt=0;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("UPDATE_TIME", "");   //갱신일시
            param.put("CD", dvobj.getRecord().get("CD")+"_"+dvobj.getRecord().get("DATA_TYPE"));   //코드
            param.put("CD_TYPE", "MCTL");   //CD_TYPE
            param.put("UPDATE_USER_ID", dobj.getRetObject("G").getRecord().get("USER_ID"));   //갱신자ID
            param.put("VAL1", dvobj.getRecord().get("DATA_TYPE"));   //VAL1
            rtncnt= update("BatchControl_20160912111170.copyLMP_UNI7_UPD",param);
            if(rtncnt < 1)
            {
                insert("BatchControl_20160912111170.copyLMP_UNI7_INS",param);
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
        rvobj.setName("UNI7") ;
        return rvobj;
    }
    // OptionTableSave
    public VOBJ copyLMP_UNI14(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"UNI14", "OptionTableSave" );
        VOBJ dvobj = dobj.getRetObject("S2");           // Input Dataset Object.
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        HashMap    param= null;
        int        inscnt=0, updcnt =0 ,unicnt=0,  rtncnt=0;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("UPDATE_TIME", "");   //갱신일시
            param.put("CD", "CONFIRM_YYYYMM");   //코드
            param.put("CD_TYPE", "MONTHLY");   //CD_TYPE
            param.put("UPDATE_USER_ID", dobj.getRetObject("G").getRecord().get("USER_ID"));   //갱신자ID
            param.put("VAL1", dobj.getRetObject("SEL13").getRecord().get("MONTHLY_YYYYMM"));   //VAL1
            rtncnt= update("BatchControl_20160912111170.copyLMP_UNI14_UPD",param);
            if(rtncnt < 1)
            {
                insert("BatchControl_20160912111170.copyLMP_UNI14_INS",param);
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
        rvobj.setName("UNI14") ;
        return rvobj;
    }
    // Confirm Info
    public VOBJ copyLMP_SEL8(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL8", "Confirm Info" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("CD", dobj.getRetObject("S2").getRecord().get("CD"));   //코드
        param.put("DATA_TYPE", dobj.getRetObject("S2").getRecord().get("DATA_TYPE"));   //デ？タタイプ
        List rlist = list("BatchControl_20160912111170.copyLMP_SEL8", param);
        dvobj.setName("SEL8");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // copy HP3D002T_TM
    public VOBJ copyLMP_XIUD7(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"XIUD7", "copy HP3D002T_TM " );
        VOBJ dvobj = dobj.getRetObject("S2");            // Input Dataset Object.
        dvobj.Println("XIUD7");
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("UPDATE_TIME", "");   //갱신일시
            param.put("YYYYMM", dobj.getRetObject("SEL13").getRecord().get("MONTHLY_YYYYMM"));   //실적월
            param.put("UPDATE_USER_ID", dobj.getRetObject("G").getRecord().get("USER_ID"));   //갱신자ID
            insert("BatchControl_20160912111170.copyLMP_XIUD7",param);
            updcnt++;
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("XIUD7");
        rvobj.Println("XIUD7");
        return rvobj;
    }
    // delete HP3D002T
    public VOBJ copyLMP_DEL9(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"DEL9", "delete HP3D002T" );
        VOBJ dvobj = dobj.getRetObject("S2");           // Input Dataset Object.
        dvobj.Println("DEL9");
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap    param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("YYYYMM", dobj.getRetObject("SEL13").getRecord().get("MONTHLY_YYYYMM"));   //실적월
            updcnt += delete("BatchControl_20160912111170.copyLMP_DEL9",param);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("DEL9") ;
        rvobj.Println("DEL9");
        return rvobj;
    }
    // copy HP2DM11T_TZ
    public VOBJ copyLMP_XIUD24(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"XIUD24", "copy HP2DM11T_TZ " );
        VOBJ dvobj = dobj.getRetObject("S2");            // Input Dataset Object.
        dvobj.Println("XIUD24");
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("UPDATE_TIME", "");   //갱신일시
            param.put("DATA_TYPE_1", "FCST"+"_"+dobj.getRetObject("SEL13").getRecord().get("MONTHLY_YYYYMM"));   //デ？タタイプ
            param.put("YYYYMM", dobj.getRetObject("SEL13").getRecord().get("MONTHLY_YYYYMM"));   //실적월
            param.put("UPDATE_USER_ID", dobj.getRetObject("G").getRecord().get("USER_ID"));   //갱신자ID
            param.put("DATA_TYPE", "FCST");   //デ？タタイプ
            insert("BatchControl_20160912111170.copyLMP_XIUD24",param);
            updcnt++;
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("XIUD24");
        rvobj.Println("XIUD24");
        return rvobj;
    }
    // HP2DM11T  Delete
    public VOBJ copyLMP_DEL14(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"DEL14", "HP2DM11T  Delete" );
        VOBJ dvobj = dobj.getRetObject("S2");           // Input Dataset Object.
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap    param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("DATA_TYPE", "FCST");   //デ？タタイプ
            param.put("YYYYMM", dobj.getRetObject("SEL13").getRecord().get("MONTHLY_YYYYMM"));   //실적월
            updcnt += delete("BatchControl_20160912111170.copyLMP_DEL14",param);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("DEL14") ;
        return rvobj;
    }
    // Flag Change
    public VOBJ copyLMP_UPD46(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"UPD46", "Flag Change" );
        VOBJ dvobj = dobj.getRetObject("S2");           // Input Dataset Object.
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap    param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("UPDATE_TIME", "");   //갱신일시
            param.put("CD", "CONFIRM_YYYYMM");   //코드
            param.put("CD_TYPE", "MONTHLY");   //CD_TYPE
            param.put("UPDATE_USER_ID", dobj.getRetObject("G").getRecord().get("USER_ID"));   //갱신자ID
            param.put("VAL2", "Y");   //VAL2
            updcnt += update("BatchControl_20160912111170.copyLMP_UPD46",param);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("UPD46") ;
        return rvobj;
    }
    // ForecastCombo
    public VOBJ pageInit_SEL2(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL2", "ForecastCombo" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("BatchControl_20160912111170.pageInit_SEL2", param);
        dvobj.setName("SEL2");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // YYYYMM
    public VOBJ pageInit_SEL4(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL4", "YYYYMM" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("BatchControl_20160912111170.pageInit_SEL4", param);
        dvobj.setName("SEL4");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // YYYY
    public VOBJ pageInit_SEL6(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL6", "YYYY" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("BatchControl_20160912111170.pageInit_SEL6", param);
        dvobj.setName("SEL6");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // Stop Info
    public VOBJ pageInit_SEL8(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL8", "Stop Info" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("BatchControl_20160912111170.pageInit_SEL8", param);
        dvobj.setName("SEL8");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        dvobj.Println("SEL8");
        return dvobj;
    }
    // Release Info
    public VOBJ pageInit_SEL10(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL10", "Release Info" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("BatchControl_20160912111170.pageInit_SEL10", param);
        dvobj.setName("SEL10");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        dvobj.Println("SEL10");
        return dvobj;
    }
    // MGRSUM
    public VOBJ pageInit_SEL19(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL19", "MGRSUM" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("BatchControl_20160912111170.pageInit_SEL19", param);
        dvobj.setName("SEL19");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // Montly Flag
    public VOBJ pageInit_SEL11(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL11", "Montly Flag" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("BatchControl_20160912111170.pageInit_SEL11", param);
        dvobj.setName("SEL11");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // LABEL
    public VOBJ pageInit_MRG21(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"MRG21", "LABEL" );
        VOBJ       rvobj= null;
        rvobj = wutil.getMergeObject(dobj, "SEL19, SEL17","LABEL" );
        rvobj.setName("MRG21") ;
        rvobj.setRetcode(1);
        return rvobj;
    }
    // BATCH TIME
    public VOBJ pageInit_SEL17(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL17", "BATCH TIME" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("BatchControl_20160912111170.pageInit_SEL17", param);
        dvobj.setName("SEL17");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // R++
    public VOBJ copyMP_UPD9(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"UPD9", "R++" );
        VOBJ dvobj = dobj.getRetObject("SEL7");           //F_YYYMM Input Object(CALLcopyMP_SEL7)
        dvobj.Println("UPD9");
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap    param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("VAL1", dvobj.getRecord().get("VAL1"));   //VAL1
            param.put("CD", "MP");   //코드
            param.put("CD_TYPE", "COST_INPUT");   //CD_TYPE
            updcnt += update("BatchControl_20160912111170.copyMP_UPD9",param);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("UPD9") ;
        rvobj.Println("UPD9");
        return rvobj;
    }
    // R++
    public VOBJ copyMiddle_UPD7(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"UPD7", "R++" );
        VOBJ dvobj = dobj.getRetObject("SEL6");           // Input Dataset Object.
        dvobj.Println("UPD7");
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap    param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("VAL1", dvobj.getRecord().get("VAL1"));   //VAL1
            param.put("CD", "MTP");   //코드
            param.put("CD_TYPE", "COST_INPUT");   //CD_TYPE
            updcnt += update("BatchControl_20160912111170.copyMiddle_UPD7",param);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("UPD7") ;
        rvobj.Println("UPD7");
        return rvobj;
    }
    // YYYMM Check
    public VOBJ LMP_ConfirmCheck_SEL17(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL17", "YYYMM Check" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("BatchControl_20160912111170.LMP_ConfirmCheck_SEL17", param);
        dvobj.setName("SEL17");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // Confirm Flag Check
    public VOBJ LMP_ConfirmCheck_SEL39(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL39", "Confirm Flag Check" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("BatchControl_20160912111170.LMP_ConfirmCheck_SEL39", param);
        dvobj.setName("SEL39");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // Flag Change
    public VOBJ LMP_ConfirmCheck_UPD8(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"UPD8", "Flag Change" );
        VOBJ dvobj = dobj.getRetObject("SEL39");           //Confirm Flag Check Input Object(CALLLMP_ConfirmCheck_SEL39)
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap    param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("UPDATE_TIME", "");   //갱신일시
            param.put("CD", "CONFIRM_YYYYMM");   //코드
            param.put("CD_TYPE", "MONTHLY");   //CD_TYPE
            param.put("UPDATE_USER_ID", dobj.getRetObject("G").getRecord().get("USER_ID"));   //갱신자ID
            param.put("VAL2", "N");   //VAL2
            updcnt += update("BatchControl_20160912111170.LMP_ConfirmCheck_UPD8",param);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("UPD8") ;
        return rvobj;
    }
    // DELETE HP2DM11T
    public VOBJ copyMiddle_DEL12(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"DEL12", "DELETE HP2DM11T" );
        VOBJ dvobj = dobj.getRetObject("S2");           // Input Dataset Object.
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap    param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("DATA_TYPE", "MTP");   //デ？タタイプ
            updcnt += delete("BatchControl_20160912111170.copyMiddle_DEL12",param);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("DEL12") ;
        return rvobj;
    }
    // DELETE HP2DM11T
    public VOBJ copyMP_DEL14(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"DEL14", "DELETE HP2DM11T" );
        VOBJ dvobj = dobj.getRetObject("S2");           // Input Dataset Object.
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap    param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("DATA_TYPE", "MP");   //デ？タタイプ
            updcnt += delete("BatchControl_20160912111170.copyMP_DEL14",param);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("DEL14") ;
        return rvobj;
    }
    // CopyHP2D002T_TZ
    public VOBJ copyMiddle_UNI17(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"UNI17", "CopyHP2D002T_TZ" );
        VOBJ dvobj = dobj.getRetObject("SEL2");           //get HP2D002T  Input Object(CALLcopyMiddle_SEL2)
        dvobj.Println("UNI17");
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        HashMap    param= null;
        int        inscnt=0, updcnt =0 ,unicnt=0,  rtncnt=0;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("APPROVAL_YYYYMMDD", "");   //승인일
            param.put("UPDATE_TIME", "");   //갱신일시
            param.put("USE_ORG_CD", dvobj.getRecord().get("USE_ORG_CD"));   //費用？生元部CD
            param.put("REQ_COMPANY_CD", dvobj.getRecord().get("REQ_COMPANY_CD"));   //要求元？社CD
            param.put("ITEM_NAME", dvobj.getRecord().get("ITEM_NAME"));   //ITEM명
            param.put("REQ_ORG_CD", dvobj.getRecord().get("REQ_ORG_CD"));   //要求元部CD
            param.put("SRC_ORG_CD", dvobj.getRecord().get("SRC_ORG_CD"));   //配賦元部CD
            param.put("VAL", dvobj.getRecord().getDouble("VAL")+"");   //Value
            param.put("INVEST_TYPE_CD", dvobj.getRecord().get("INVEST_TYPE_CD"));   //投資？分CD
            param.put("HPMS_ID", dvobj.getRecord().get("HPMS_ID"));   //HPMS_ID
            param.put("UNIT", dvobj.getRecord().get("UNIT"));   //통화단위
            param.put("CUSTOMER_CD", dvobj.getRecord().get("CUSTOMER_CD"));   //顧客CD
            param.put("USE_COMPANY_CD", dvobj.getRecord().get("USE_COMPANY_CD"));   //費用？生元？社CD
            param.put("UPLOAD_FILE_NAME", dvobj.getRecord().get("UPLOAD_FILE_NAME"));   //UPLOAD_FILE_NAME
            param.put("SRC_COMPANY_CD", dvobj.getRecord().get("SRC_COMPANY_CD"));   //配賦元？社CD
            param.put("CALC_MST_VER", dvobj.getRecord().get("CALC_MST_VER"));   //CALC_MST_VER
            param.put("YYYYMM", dvobj.getRecord().get("YYYYMM"));   //실적월
            param.put("DST_COMPANY_CD", dvobj.getRecord().get("DST_COMPANY_CD"));   //DST_COMPANY_CD
            param.put("DST_ORG_CD", dvobj.getRecord().get("DST_ORG_CD"));   //DST_ORG_CD
            param.put("VAL_TYPE", dvobj.getRecord().get("VAL_TYPE"));   //VAL_TYPE
            param.put("APPLICATION", dvobj.getRecord().get("APPLICATION"));   //用途
            param.put("UNIT3", dvobj.getRecord().get("UNIT3"));   //UNIT3
            param.put("UNIT2", dvobj.getRecord().get("UNIT2"));   //UNIT2
            param.put("PID", dvobj.getRecord().get("PID"));   //PID
            param.put("VAL3", dvobj.getRecord().get("VAL3"));   //VAL3
            param.put("VAL2", dvobj.getRecord().get("VAL2"));   //VAL2
            param.put("DATA_TYPE", "MTP"+"__"+dobj.getRetObject("S3").getRecord().get("YYYY")+"_"+dobj.getRetObject("SEL11").getRecord().get("C_DATE"));   //デ？タタイプ
            param.put("UPDATE_USER_ID", dobj.getRetObject("G").getRecord().get("USER_ID"));   //갱신자ID
            rtncnt= update("BatchControl_20160912111170.copyMiddle_UNI17_UPD",param);
            if(rtncnt < 1)
            {
                insert("BatchControl_20160912111170.copyMiddle_UNI17_INS",param);
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
        rvobj.setName("UNI17") ;
        rvobj.Println("UNI17");
        return rvobj;
    }
    // CopyHP2D002T_TZ
    public VOBJ copyMiddle_UNI4(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"UNI4", "CopyHP2D002T_TZ" );
        VOBJ dvobj = dobj.getRetObject("SEL2");           //get HP2D002T  Input Object(CALLcopyMiddle_SEL2)
        dvobj.Println("UNI4");
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        HashMap    param= null;
        int        inscnt=0, updcnt =0 ,unicnt=0,  rtncnt=0;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("APPROVAL_YYYYMMDD", "");   //승인일
            param.put("UPDATE_TIME", "");   //갱신일시
            param.put("USE_ORG_CD", dvobj.getRecord().get("USE_ORG_CD"));   //費用？生元部CD
            param.put("REQ_COMPANY_CD", dvobj.getRecord().get("REQ_COMPANY_CD"));   //要求元？社CD
            param.put("ITEM_NAME", dvobj.getRecord().get("ITEM_NAME"));   //ITEM명
            param.put("REQ_ORG_CD", dvobj.getRecord().get("REQ_ORG_CD"));   //要求元部CD
            param.put("SRC_ORG_CD", dvobj.getRecord().get("SRC_ORG_CD"));   //配賦元部CD
            param.put("VAL", dvobj.getRecord().getDouble("VAL")+"");   //Value
            param.put("INVEST_TYPE_CD", dvobj.getRecord().get("INVEST_TYPE_CD"));   //投資？分CD
            param.put("HPMS_ID", dvobj.getRecord().get("HPMS_ID"));   //HPMS_ID
            param.put("UNIT", dvobj.getRecord().get("UNIT"));   //통화단위
            param.put("CUSTOMER_CD", dvobj.getRecord().get("CUSTOMER_CD"));   //顧客CD
            param.put("USE_COMPANY_CD", dvobj.getRecord().get("USE_COMPANY_CD"));   //費用？生元？社CD
            param.put("UPLOAD_FILE_NAME", dvobj.getRecord().get("UPLOAD_FILE_NAME"));   //UPLOAD_FILE_NAME
            param.put("SRC_COMPANY_CD", dvobj.getRecord().get("SRC_COMPANY_CD"));   //配賦元？社CD
            param.put("CALC_MST_VER", dvobj.getRecord().get("CALC_MST_VER"));   //CALC_MST_VER
            param.put("YYYYMM", dvobj.getRecord().get("YYYYMM"));   //실적월
            param.put("DST_COMPANY_CD", dvobj.getRecord().get("DST_COMPANY_CD"));   //DST_COMPANY_CD
            param.put("DST_ORG_CD", dvobj.getRecord().get("DST_ORG_CD"));   //DST_ORG_CD
            param.put("VAL_TYPE", dvobj.getRecord().get("VAL_TYPE"));   //VAL_TYPE
            param.put("APPLICATION", dvobj.getRecord().get("APPLICATION"));   //用途
            param.put("UNIT3", dvobj.getRecord().get("UNIT3"));   //UNIT3
            param.put("UNIT2", dvobj.getRecord().get("UNIT2"));   //UNIT2
            param.put("PID", dvobj.getRecord().get("PID"));   //PID
            param.put("VAL3", dvobj.getRecord().get("VAL3"));   //VAL3
            param.put("VAL2", dvobj.getRecord().get("VAL2"));   //VAL2
            param.put("DATA_TYPE", "MTP"+"_"+dobj.getRetObject("S4").getRecord().get("YYYY"));   //デ？タタイプ
            param.put("UPDATE_USER_ID", dobj.getRetObject("G").getRecord().get("USER_ID"));   //갱신자ID
            rtncnt= update("BatchControl_20160912111170.copyMiddle_UNI4_UPD",param);
            if(rtncnt < 1)
            {
                insert("BatchControl_20160912111170.copyMiddle_UNI4_INS",param);
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
        rvobj.setName("UNI4") ;
        rvobj.Println("UNI4");
        return rvobj;
    }
    // CopyHP2D002T_TZ
    public VOBJ copyMP_UNI4(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"UNI4", "CopyHP2D002T_TZ" );
        VOBJ dvobj = dobj.getRetObject("SEL2");           //get HP2D002T  Input Object(CALLcopyMP_SEL2)
        dvobj.Println("UNI4");
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        HashMap    param= null;
        int        inscnt=0, updcnt =0 ,unicnt=0,  rtncnt=0;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("APPROVAL_YYYYMMDD", "");   //승인일
            param.put("UPDATE_TIME", "");   //갱신일시
            param.put("USE_ORG_CD", dvobj.getRecord().get("USE_ORG_CD"));   //費用？生元部CD
            param.put("REQ_COMPANY_CD", dvobj.getRecord().get("REQ_COMPANY_CD"));   //要求元？社CD
            param.put("ITEM_NAME", dvobj.getRecord().get("ITEM_NAME"));   //ITEM명
            param.put("REQ_ORG_CD", dvobj.getRecord().get("REQ_ORG_CD"));   //要求元部CD
            param.put("SRC_ORG_CD", dvobj.getRecord().get("SRC_ORG_CD"));   //配賦元部CD
            param.put("VAL", dvobj.getRecord().getDouble("VAL")+"");   //Value
            param.put("INVEST_TYPE_CD", dvobj.getRecord().get("INVEST_TYPE_CD"));   //投資？分CD
            param.put("HPMS_ID", dvobj.getRecord().get("HPMS_ID"));   //HPMS_ID
            param.put("UNIT", dvobj.getRecord().get("UNIT"));   //통화단위
            param.put("CUSTOMER_CD", dvobj.getRecord().get("CUSTOMER_CD"));   //顧客CD
            param.put("USE_COMPANY_CD", dvobj.getRecord().get("USE_COMPANY_CD"));   //費用？生元？社CD
            param.put("UPLOAD_FILE_NAME", dvobj.getRecord().get("UPLOAD_FILE_NAME"));   //UPLOAD_FILE_NAME
            param.put("SRC_COMPANY_CD", dvobj.getRecord().get("SRC_COMPANY_CD"));   //配賦元？社CD
            param.put("CALC_MST_VER", dvobj.getRecord().get("CALC_MST_VER"));   //CALC_MST_VER
            param.put("YYYYMM", dvobj.getRecord().get("YYYYMM"));   //실적월
            param.put("DST_COMPANY_CD", dvobj.getRecord().get("DST_COMPANY_CD"));   //DST_COMPANY_CD
            param.put("DST_ORG_CD", dvobj.getRecord().get("DST_ORG_CD"));   //DST_ORG_CD
            param.put("VAL_TYPE", dvobj.getRecord().get("VAL_TYPE"));   //VAL_TYPE
            param.put("APPLICATION", dvobj.getRecord().get("APPLICATION"));   //用途
            param.put("UNIT3", dvobj.getRecord().get("UNIT3"));   //UNIT3
            param.put("UNIT2", dvobj.getRecord().get("UNIT2"));   //UNIT2
            param.put("PID", dvobj.getRecord().get("PID"));   //PID
            param.put("VAL3", dvobj.getRecord().get("VAL3"));   //VAL3
            param.put("VAL2", dvobj.getRecord().get("VAL2"));   //VAL2
            param.put("DATA_TYPE", "MP"+"_"+dobj.getRetObject("S3").getRecord().get("YYYY"));   //デ？タタイプ
            param.put("UPDATE_USER_ID", dobj.getRetObject("G").getRecord().get("USER_ID"));   //갱신자ID
            rtncnt= update("BatchControl_20160912111170.copyMP_UNI4_UPD",param);
            if(rtncnt < 1)
            {
                insert("BatchControl_20160912111170.copyMP_UNI4_INS",param);
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
        rvobj.setName("UNI4") ;
        rvobj.Println("UNI4");
        return rvobj;
    }
    // )
    public VOBJ copyMP_UNI10(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"UNI10", ")" );
        VOBJ dvobj = dobj.getRetObject("SEL2");           //get HP2D002T  Input Object(CALLcopyMP_SEL2)
        dvobj.Println("UNI10");
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        HashMap    param= null;
        int        inscnt=0, updcnt =0 ,unicnt=0,  rtncnt=0;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("APPROVAL_YYYYMMDD", "");   //승인일
            param.put("UPDATE_TIME", "");   //갱신일시
            param.put("USE_ORG_CD", dvobj.getRecord().get("USE_ORG_CD"));   //費用？生元部CD
            param.put("REQ_COMPANY_CD", dvobj.getRecord().get("REQ_COMPANY_CD"));   //要求元？社CD
            param.put("ITEM_NAME", dvobj.getRecord().get("ITEM_NAME"));   //ITEM명
            param.put("REQ_ORG_CD", dvobj.getRecord().get("REQ_ORG_CD"));   //要求元部CD
            param.put("SRC_ORG_CD", dvobj.getRecord().get("SRC_ORG_CD"));   //配賦元部CD
            param.put("VAL", dvobj.getRecord().getDouble("VAL")+"");   //Value
            param.put("INVEST_TYPE_CD", dvobj.getRecord().get("INVEST_TYPE_CD"));   //投資？分CD
            param.put("HPMS_ID", dvobj.getRecord().get("HPMS_ID"));   //HPMS_ID
            param.put("UNIT", dvobj.getRecord().get("UNIT"));   //통화단위
            param.put("CUSTOMER_CD", dvobj.getRecord().get("CUSTOMER_CD"));   //顧客CD
            param.put("USE_COMPANY_CD", dvobj.getRecord().get("USE_COMPANY_CD"));   //費用？生元？社CD
            param.put("UPLOAD_FILE_NAME", dvobj.getRecord().get("UPLOAD_FILE_NAME"));   //UPLOAD_FILE_NAME
            param.put("SRC_COMPANY_CD", dvobj.getRecord().get("SRC_COMPANY_CD"));   //配賦元？社CD
            param.put("CALC_MST_VER", dvobj.getRecord().get("CALC_MST_VER"));   //CALC_MST_VER
            param.put("YYYYMM", dvobj.getRecord().get("YYYYMM"));   //실적월
            param.put("DST_COMPANY_CD", dvobj.getRecord().get("DST_COMPANY_CD"));   //DST_COMPANY_CD
            param.put("DST_ORG_CD", dvobj.getRecord().get("DST_ORG_CD"));   //DST_ORG_CD
            param.put("VAL_TYPE", dvobj.getRecord().get("VAL_TYPE"));   //VAL_TYPE
            param.put("APPLICATION", dvobj.getRecord().get("APPLICATION"));   //用途
            param.put("UNIT3", dvobj.getRecord().get("UNIT3"));   //UNIT3
            param.put("UNIT2", dvobj.getRecord().get("UNIT2"));   //UNIT2
            param.put("PID", dvobj.getRecord().get("PID"));   //PID
            param.put("VAL3", dvobj.getRecord().get("VAL3"));   //VAL3
            param.put("VAL2", dvobj.getRecord().get("VAL2"));   //VAL2
            param.put("DATA_TYPE", "MP"+"_"+dobj.getRetObject("S3").getRecord().get("YYYY")+"_"+dobj.getRetObject("SEL7").getRecord().get("C_DATE"));   //デ？タタイプ
            param.put("UPDATE_USER_ID", dobj.getRetObject("G").getRecord().get("USER_ID"));   //갱신자ID
            rtncnt= update("BatchControl_20160912111170.copyMP_UNI10_UPD",param);
            if(rtncnt < 1)
            {
                insert("BatchControl_20160912111170.copyMP_UNI10_INS",param);
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
        rvobj.setName("UNI10") ;
        rvobj.Println("UNI10");
        return rvobj;
    }
    // DELETE HP2DM11T
    public VOBJ copyLMP_DEL25(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"DEL25", "DELETE HP2DM11T" );
        VOBJ dvobj = dobj.getRetObject("S2");           // Input Dataset Object.
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap    param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("DATA_TYPE", "FCST");   //デ？タタイプ
            param.put("YYYYMM", dobj.getRetObject("SEL26").getRecord().get("YYYYMM"));   //실적월
            updcnt += delete("BatchControl_20160912111170.copyLMP_DEL25",param);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("DEL25") ;
        return rvobj;
    }
    // Flag Change
    public VOBJ LMP_ConfirmCheck_UPD45(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"UPD45", "Flag Change" );
        VOBJ dvobj = dobj.getRetObject("SEL39");           //Confirm Flag Check Input Object(CALLLMP_ConfirmCheck_SEL39)
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap    param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("CD", "CONFIRM_YYYYMM");   //코드
            param.put("CD_TYPE", "MONTHLY");   //CD_TYPE
            param.put("VAL2", "N");   //VAL2
            updcnt += update("BatchControl_20160912111170.LMP_ConfirmCheck_UPD45",param);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("UPD45") ;
        return rvobj;
    }
    // Confirm Info
    public VOBJ getConfirmInfo_SEL2(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL2", "Confirm Info" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("CD", dobj.getRetObject("S").getRecord().get("CD"));   //코드
        param.put("DATA_TYPE", dobj.getRetObject("S").getRecord().get("DATA_TYPE"));   //デ？タタイプ
        List rlist = list("BatchControl_20160912111170.getConfirmInfo_SEL2", param);
        dvobj.setName("SEL2");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        dvobj.Println("SEL2");
        return dvobj;
    }
    // Confirm YYYMM
    public VOBJ getConfirmInfo_SEL4(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL4", "Confirm YYYMM" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("CD", "CONFIRM_YYYYMM");   //코드
        List rlist = list("BatchControl_20160912111170.getConfirmInfo_SEL4", param);
        dvobj.setName("SEL4");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        dvobj.Println("SEL4");
        return dvobj;
    }
    // YYYYMM
    public VOBJ getConfirmInfo_MRG11(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"MRG11", "YYYYMM" );
        VOBJ       rvobj= null;
        rvobj = wutil.getMergeObject(dobj, "SEL4","YYYYMM" );
        rvobj.setName("MRG11") ;
        rvobj.setRetcode(1);
        return rvobj;
    }
    // MP R
    public VOBJ copyMiddle_SEL6(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL6", "MP R" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("BatchControl_20160912111170.copyMiddle_SEL6", param);
        dvobj.setName("SEL6");
        dvobj.setRecords(rlist);
        dvobj.Println("SEL6");
        return dvobj;
    }
    // Delete Forecast
    public VOBJ copyMP_XIUD8(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"XIUD8", "Delete Forecast" );
        VOBJ dvobj = dobj.getRetObject("S3");            // Input Dataset Object.
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("TO_YYYYMM", dvobj.getRecord().get("TO_YYYYMM"));   //퇴사월
            param.put("FROM_YYYYMM", dvobj.getRecord().get("FROM_YYYYMM"));   //입사월
            param.put("DATA_TYPE", dvobj.getRecord().get("DATA_TYPE"));   //デ？タタイプ
            updcnt += delete("BatchControl_20160912111170.copyMP_XIUD8",param);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("XIUD8");
        return rvobj;
    }
    // Delete Forecast
    public VOBJ copyMiddle_XIUD8(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"XIUD8", "Delete Forecast" );
        VOBJ dvobj = dobj.getRetObject("S4");            // Input Dataset Object.
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("TO_YYYYMM", dvobj.getRecord().get("TO_YYYYMM"));   //퇴사월
            param.put("FROM_YYYYMM", dvobj.getRecord().get("FROM_YYYYMM"));   //입사월
            param.put("DATA_TYPE", dvobj.getRecord().get("DATA_TYPE"));   //デ？タタイプ
            updcnt += delete("BatchControl_20160912111170.copyMiddle_XIUD8",param);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("XIUD8");
        return rvobj;
    }
    // LOCK_FileInfo
    public VOBJ managedatabuild_SEL10(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL10", "LOCK_FileInfo" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("BatchControl_20160912111170.managedatabuild_SEL10", param);
        dvobj.setName("SEL10");
        dvobj.setRecords(rlist);
        dvobj.Println("SEL10");
        return dvobj;
    }
    // Montly Flag
    public VOBJ managedatabuild_SEL5(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL5", "Montly Flag" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("BatchControl_20160912111170.managedatabuild_SEL5", param);
        dvobj.setName("SEL5");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // Param
    public VOBJ managedatabuild_batch(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"batch", "Param" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("DATA_TYPE", dobj.getRetObject("S1").getRecord().get("DATA_TYPE"));   //デ？タタイプ
        List rlist = list("BatchControl_20160912111170.managedatabuild_batch", param);
        dvobj.setName("batch");
        dvobj.setRecords(rlist);
        dvobj.Println("batch");
        return dvobj;
    }
    // ExcelUpload Data
    public VOBJ managedatabuild_PEX1(DOBJ dobj) throws Exception
    {
        CommUtil cu = new CommUtil(dobj);
        HashMap   classinfo = new HashMap();
        classinfo.put("OBJECTID", "PEX1" );
        classinfo.put("PACKAGE", "hpms.UserObject.BatchExe" );
        classinfo.put("CLASS", "Executor" );
        classinfo.put("METHOD", "exe" );
        dobj = cu.callPSExternal(dobj, null, classinfo );
        VOBJ dvobj = dobj.getRetObject("BAT");
        dvobj.setName("PEX1");
        dvobj.Println("PEX1");
        return dvobj;
    }
    // HP2D002T delete
    public VOBJ copyLMP_DEL6(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"DEL6", "HP2D002T delete" );
        VOBJ dvobj = dobj.getRetObject("S2");           // Input Dataset Object.
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap    param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("DATA_TYPE", dvobj.getRecord().get("DATA_TYPE"));   //デ？タタイプ
            param.put("YYYYMM", dobj.getRetObject("SEL26").getRecord().get("YYYYMM"));   //실적월
            updcnt += delete("BatchControl_20160912111170.copyLMP_DEL6",param);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("DEL6") ;
        return rvobj;
    }
    // get HP2D002T
    public VOBJ copyMiddle_SEL8(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL8", "get HP2D002T " );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("TO_YYYYMM", dobj.getRetObject("S4").getRecord().get("TO_YYYYMM"));   //퇴사월
        param.put("FROM_YYYYMM", dobj.getRetObject("S4").getRecord().get("FROM_YYYYMM"));   //입사월
        param.put("DATA_TYPE", dobj.getRetObject("S4").getRecord().get("DATA_TYPE"));   //デ？タタイプ
        List rlist = list("BatchControl_20160912111170.copyMiddle_SEL8", param);
        dvobj.setName("SEL8");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // get HP2D002T
    public VOBJ copyMP_SEL10(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL10", "get HP2D002T " );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("TO_YYYYMM", dobj.getRetObject("S3").getRecord().get("TO_YYYYMM"));   //퇴사월
        param.put("FROM_YYYYMM", dobj.getRetObject("S3").getRecord().get("FROM_YYYYMM"));   //입사월
        param.put("DATA_TYPE", dobj.getRetObject("S3").getRecord().get("DATA_TYPE"));   //デ？タタイプ
        List rlist = list("BatchControl_20160912111170.copyMP_SEL10", param);
        dvobj.setName("SEL10");
        dvobj.setRecords(rlist);
        dvobj.Println("SEL10");
        return dvobj;
    }
    // copy HP2DM11T_TZ
    public VOBJ copyLMP_XIUD9(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"XIUD9", "copy HP2DM11T_TZ " );
        VOBJ dvobj = dobj.getRetObject("S2");            // Input Dataset Object.
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("YYYYMMDD", "");   //年月日
            param.put("DATA_TYPE_1", "FCST_"+dobj.getRetObject("SEL26").getRecord().get("YYYYMM"));   //デ？タタイプ
            param.put("YYYYMM", dobj.getRetObject("SEL26").getRecord().get("YYYYMM"));   //실적월
            param.put("DATA_TYPE", "FCST");   //デ？タタイプ
            insert("BatchControl_20160912111170.copyLMP_XIUD9",param);
            updcnt++;
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("XIUD9");
        return rvobj;
    }
    // DELETE HP2DM11T
    public VOBJ copyLMP_DEL11(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"DEL11", "DELETE HP2DM11T" );
        VOBJ dvobj = dobj.getRetObject("S2");           // Input Dataset Object.
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap    param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("DATA_TYPE", "FCST");   //デ？タタイプ
            param.put("YYYYMM", dobj.getRetObject("SEL26").getRecord().get("YYYYMM"));   //실적월
            updcnt += delete("BatchControl_20160912111170.copyLMP_DEL11",param);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("DEL11") ;
        return rvobj;
    }
    // isFile
    public VOBJ managedatabuild_PEX10(DOBJ dobj) throws Exception
    {
        CommUtil cu = new CommUtil(dobj);
        HashMap   classinfo = new HashMap();
        classinfo.put("OBJECTID", "PEX10" );
        classinfo.put("PACKAGE", "hpms.UserObject.BatchExe" );
        classinfo.put("CLASS", "FileUtility" );
        classinfo.put("METHOD", "isFile" );
        dobj = cu.callPSExternal(dobj, null, classinfo );
        VOBJ dvobj = dobj.getRetObject("FN01");
        dvobj.setName("PEX10");
        return dvobj;
    }
    // Write Stop
    public VOBJ writeRelease_UNI2(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"UNI2", "Write Stop" );
        VOBJ dvobj = dobj.getRetObject("S");           // Input Dataset Object.
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        HashMap    param= null;
        int        inscnt=0, updcnt =0 ,unicnt=0,  rtncnt=0;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("UPDATE_TIME", "");   //갱신일시
            param.put("CD", dvobj.getRecord().get("CD"));   //코드
            param.put("CD_TYPE", "SYS");   //CD_TYPE
            param.put("UPDATE_USER_ID", dobj.getRetObject("G").getRecord().get("USER_ID"));   //갱신자ID
            param.put("VAL1", "Y");   //VAL1
            rtncnt= update("BatchControl_20160912111170.writeRelease_UNI2_UPD",param);
            if(rtncnt < 1)
            {
                insert("BatchControl_20160912111170.writeRelease_UNI2_INS",param);
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
    // Release Info
    public VOBJ writeRelease_SEL2(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL2", "Release Info" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("BatchControl_20160912111170.writeRelease_SEL2", param);
        dvobj.setName("SEL2");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // Stop Info
    public VOBJ writeRelease_SEL3(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL3", "Stop Info" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("BatchControl_20160912111170.writeRelease_SEL3", param);
        dvobj.setName("SEL3");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // Write Stop
    public VOBJ writeStop_UNI2(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"UNI2", "Write Stop" );
        VOBJ dvobj = dobj.getRetObject("S");           // Input Dataset Object.
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        HashMap    param= null;
        int        inscnt=0, updcnt =0 ,unicnt=0,  rtncnt=0;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("UPDATE_TIME", "");   //갱신일시
            param.put("CD", dvobj.getRecord().get("CD"));   //코드
            param.put("CD_TYPE", "SYS");   //CD_TYPE
            param.put("UPDATE_USER_ID", dobj.getRetObject("G").getRecord().get("USER_ID"));   //갱신자ID
            param.put("VAL1", "N");   //VAL1
            rtncnt= update("BatchControl_20160912111170.writeStop_UNI2_UPD",param);
            if(rtncnt < 1)
            {
                insert("BatchControl_20160912111170.writeStop_UNI2_INS",param);
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
    // Release Info
    public VOBJ writeStop_SEL4(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL4", "Release Info" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("BatchControl_20160912111170.writeStop_SEL4", param);
        dvobj.setName("SEL4");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // Stop Info
    public VOBJ writeStop_SEL5(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL5", "Stop Info" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("BatchControl_20160912111170.writeStop_SEL5", param);
        dvobj.setName("SEL5");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // YYYMM Check
    public VOBJ copyLMP_SEL14(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL14", "YYYMM Check" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("BatchControl_20160912111170.copyLMP_SEL14", param);
        dvobj.setName("SEL14");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // Confirm Flag Check
    public VOBJ copyLMP_SEL15(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL15", "Confirm Flag Check" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("BatchControl_20160912111170.copyLMP_SEL15", param);
        dvobj.setName("SEL15");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // Flag Change
    public VOBJ copyLMP_UPD16(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"UPD16", "Flag Change" );
        VOBJ dvobj = dobj.getRetObject("SEL39");           // Input Dataset Object.
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap    param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("CD", "CONFIRM_YYYYMM");   //코드
            param.put("CD_TYPE", "MONTHLY");   //CD_TYPE
            param.put("VAL2", "N");   //VAL2
            updcnt += update("BatchControl_20160912111170.copyLMP_UPD16",param);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("UPD16") ;
        return rvobj;
    }
    // HP2D002T delete
    public VOBJ copyLMP_DEL13(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"DEL13", "HP2D002T delete" );
        VOBJ dvobj = dobj.getRetObject("S2");           // Input Dataset Object.
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap    param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("DATA_TYPE", dvobj.getRecord().get("DATA_TYPE"));   //デ？タタイプ
            param.put("YYYYMM", dobj.getRetObject("SEL13").getRecord().get("YYYYMM"));   //실적월
            updcnt += delete("BatchControl_20160912111170.copyLMP_DEL13",param);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("DEL13") ;
        return rvobj;
    }
    // delete HP3D002T_TM
    public VOBJ copyLMP_DEL15(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"DEL15", "delete HP3D002T_TM" );
        VOBJ dvobj = dobj.getRetObject("S2");           // Input Dataset Object.
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap    param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("YYYYMM", dobj.getRetObject("SEL13").getRecord().get("YYYYMM"));   //실적월
            updcnt += delete("BatchControl_20160912111170.copyLMP_DEL15",param);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("DEL15") ;
        return rvobj;
    }
    // DELETE HP2DM11T_TZ
    public VOBJ copyLMP_DEL16(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"DEL16", "DELETE HP2DM11T_TZ" );
        VOBJ dvobj = dobj.getRetObject("S2");           // Input Dataset Object.
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap    param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("YYYYMMDD", "");   //年月日
            param.put("YYYYMM", dobj.getRetObject("SEL13").getRecord().get("YYYYMM"));   //실적월
            updcnt += delete("BatchControl_20160912111170.copyLMP_DEL16",param);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("DEL16") ;
        return rvobj;
    }
    // YYYYMM
    public VOBJ getConfirmInfo_SEL9(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL9", "YYYYMM" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("BatchControl_20160912111170.getConfirmInfo_SEL9", param);
        dvobj.setName("SEL9");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // ㅇㅇ
    public VOBJ loadpage_SEL3(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL3", "ㅇㅇ" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("BatchControl_20160912111170.loadpage_SEL3", param);
        dvobj.setName("SEL3");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // Param
    public VOBJ managedatabuild_SEL16(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL16", "Param" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("DATA_TYPE", dobj.getRetObject("S1").getRecord().get("DATA_TYPE"));   //デ？タタイプ
        List rlist = list("BatchControl_20160912111170.managedatabuild_SEL16", param);
        dvobj.setName("SEL16");
        dvobj.setRecords(rlist);
        dvobj.Println("SEL16");
        return dvobj;
    }
    // Flag_N
    public VOBJ managedatabuild_UPD14(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"UPD14", "Flag_N" );
        VOBJ dvobj = dobj.getRetObject("S");           //사용자 화면에서 발생한 Object입니다.
        dvobj.Println("UPD14");
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap    param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("CD", "BATCH_FLAG");   //코드
            param.put("CD_TYPE", "SYS");   //CD_TYPE
            param.put("VAL1", "N");   //VAL1
            updcnt += update("BatchControl_20160912111170.managedatabuild_UPD14",param);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("UPD14") ;
        rvobj.Println("UPD14");
        return rvobj;
    }
}