
package hpms.batch.dao;
import java.util.*;
import egov.wizware.com.*;
import egov.wizware.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import com.ibatis.sqlmap.client.SqlMapClient;
import javax.annotation.Resource;
@Repository("BP_HP3D002T_DWH20160930162713Dao")
public class BP_HP3D002T_DWHDao extends EgovAbstractDAO
{
    // EDA Rows count
    public VOBJ BP_HP3D002T_EDA_SEL6(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL6", "EDA Rows count" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("BP_HP3D002T_DWH_20160930162713.BP_HP3D002T_EDA_SEL6", param);
        dvobj.setName("SEL6");
        dvobj.setRecords(rlist);
        dvobj.Println("SEL6");
        return dvobj;
    }
    // GL Data Delete
    public VOBJ BP_HP3D002T_EDA_XIUD4(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"XIUD4", "GL Data Delete" );
        VOBJ dvobj = dobj.getRetObject("S");            // Input Dataset Object.
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            updcnt += delete("BP_HP3D002T_DWH_20160930162713.BP_HP3D002T_EDA_XIUD4",param);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("XIUD4");
        rvobj.Println("XIUD4");
        return rvobj;
    }
    // EDA Select
    public VOBJ BP_HP3D002T_EDA_SEL12(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL12", "EDA Select" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("BP_HP3D002T_DWH_20160930162713.BP_HP3D002T_EDA_SEL12", param);
        dvobj.setName("SEL12");
        dvobj.setRecords(rlist);
        dvobj.Println("SEL12");
        return dvobj;
    }
    // FA Save
    public VOBJ BP_HP3D002T_EDA_INS13(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"INS13", "FA Save" );
        VOBJ dvobj = dobj.getRetObject("SEL12");           //EDA Select Input Object(CALLBP_HP3D002T_EDA_SEL12)
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap    param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("UPDATE_TIME", "SYSDATE");   //갱신일시
            param.put("USE_ORG_CD", dvobj.getRecord().get("USE_ORG_CD"));   //費用？生元部CD
            param.put("REQ_COMPANY_CD", dvobj.getRecord().get("REQ_COMPANY_CD"));   //要求元？社CD
            param.put("ITEM_NAME", dvobj.getRecord().get("ITEM_NAME"));   //ITEM명
            param.put("REQ_ORG_CD", dvobj.getRecord().get("REQ_ORG_CD"));   //要求元部CD
            param.put("UPDATE_USER_ID", dvobj.getRecord().get("UPDATE_USER_ID"));   //갱신자ID
            param.put("SRC_ORG_CD", dvobj.getRecord().get("SRC_ORG_CD"));   //配賦元部CD
            param.put("VAL", dvobj.getRecord().getDouble("VAL")+"");   //Value
            param.put("INVEST_TYPE_CD", dvobj.getRecord().get("INVEST_TYPE_CD"));   //投資？分CD
            param.put("HPMS_ID", dvobj.getRecord().get("HPMS_ID"));   //HPMS_ID
            param.put("UNIT", dvobj.getRecord().get("UNIT"));   //통화단위
            param.put("CUSTOMER_CD", dvobj.getRecord().get("CUSTOMER_CD"));   //顧客CD
            param.put("USE_COMPANY_CD", dvobj.getRecord().get("USE_COMPANY_CD"));   //費用？生元？社CD
            param.put("UPLOAD_FILE_NAME", dvobj.getRecord().get("UPLOAD_FILE_NAME"));   //UPLOAD_FILE_NAME
            param.put("DATA_TYPE", dvobj.getRecord().get("DATA_TYPE"));   //デ？タタイプ
            param.put("SRC_COMPANY_CD", dvobj.getRecord().get("SRC_COMPANY_CD"));   //配賦元？社CD
            param.put("CALC_MST_VER", dvobj.getRecord().get("CALC_MST_VER"));   //CALC_MST_VER
            param.put("UNIT4", dvobj.getRecord().get("UNIT4"));   //UNIT4
            param.put("YYYYMM", dvobj.getRecord().get("YYYYMM"));   //실적월
            param.put("DST_COMPANY_CD", dvobj.getRecord().get("DST_COMPANY_CD"));   //DST_COMPANY_CD
            param.put("DST_ORG_CD", dvobj.getRecord().get("DST_ORG_CD"));   //DST_ORG_CD
            param.put("APPLICATION", dvobj.getRecord().get("APPLICATION"));   //用途
            param.put("VAL_TYPE", dvobj.getRecord().get("VAL_TYPE"));   //VAL_TYPE
            param.put("UNIT3", dvobj.getRecord().get("UNIT3"));   //UNIT3
            param.put("UNIT2", dvobj.getRecord().get("UNIT2"));   //UNIT2
            param.put("PID", dvobj.getRecord().get("PID"));   //PID
            param.put("VAL3", dvobj.getRecord().get("VAL3"));   //VAL3
            param.put("VAL4", dvobj.getRecord().get("VAL4"));   //VAL4
            param.put("VAL2", dvobj.getRecord().get("VAL2"));   //VAL2
            insert("BP_HP3D002T_DWH_20160930162713.BP_HP3D002T_EDA_INS13",param);
            updcnt++;
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("INS13") ;
        rvobj.Println("INS13");
        return rvobj;
    }
    // Existing Data Delete
    public VOBJ BP_HP3D002T_EDA_XIUD9(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"XIUD9", "Existing Data Delete" );
        VOBJ dvobj = dobj.getRetObject("S");            // Input Dataset Object.
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            updcnt += delete("BP_HP3D002T_DWH_20160930162713.BP_HP3D002T_EDA_XIUD9",param);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("XIUD9");
        rvobj.setRetcode(1);
        rvobj.Println("XIUD9");
        return rvobj;
    }
    // FA Rows count
    public VOBJ BP_HP3D002T_FA_SEL6(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL6", "FA Rows count" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("BP_HP3D002T_DWH_20160930162713.BP_HP3D002T_FA_SEL6", param);
        dvobj.setName("SEL6");
        dvobj.setRecords(rlist);
        dvobj.Println("SEL6");
        return dvobj;
    }
    // GL Data Delete
    public VOBJ BP_HP3D002T_FA_XIUD4(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"XIUD4", "GL Data Delete" );
        VOBJ dvobj = dobj.getRetObject("S");            // Input Dataset Object.
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            updcnt += delete("BP_HP3D002T_DWH_20160930162713.BP_HP3D002T_FA_XIUD4",param);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("XIUD4");
        return rvobj;
    }
    // FA Select
    public VOBJ BP_HP3D002T_FA_SEL1(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL1", "FA Select" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("BP_HP3D002T_DWH_20160930162713.BP_HP3D002T_FA_SEL1", param);
        dvobj.setName("SEL1");
        dvobj.setRecords(rlist);
        dvobj.Println("SEL1");
        return dvobj;
    }
    // FA Save
    public VOBJ BP_HP3D002T_FA_INS6(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"INS6", "FA Save" );
        VOBJ dvobj = dobj.getRetObject("SEL1");           //FA Select Input Object(CALLBP_HP3D001T_FA_SEL1)
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap    param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("UPDATE_TIME", "SYSDATE");   //갱신일시
            param.put("USE_ORG_CD", dvobj.getRecord().get("USE_ORG_CD"));   //費用？生元部CD
            param.put("REQ_COMPANY_CD", dvobj.getRecord().get("REQ_COMPANY_CD"));   //要求元？社CD
            param.put("ITEM_NAME", dvobj.getRecord().get("ITEM_NAME"));   //ITEM명
            param.put("REQ_ORG_CD", dvobj.getRecord().get("REQ_ORG_CD"));   //要求元部CD
            param.put("UPDATE_USER_ID", dvobj.getRecord().get("UPDATE_USER_ID"));   //갱신자ID
            param.put("SRC_ORG_CD", dvobj.getRecord().get("SRC_ORG_CD"));   //配賦元部CD
            param.put("VAL", dvobj.getRecord().getDouble("VAL")+"");   //Value
            param.put("INVEST_TYPE_CD", dvobj.getRecord().get("INVEST_TYPE_CD"));   //投資？分CD
            param.put("HPMS_ID", dvobj.getRecord().get("HPMS_ID"));   //HPMS_ID
            param.put("UNIT", dvobj.getRecord().get("UNIT"));   //통화단위
            param.put("CUSTOMER_CD", dvobj.getRecord().get("CUSTOMER_CD"));   //顧客CD
            param.put("USE_COMPANY_CD", dvobj.getRecord().get("USE_COMPANY_CD"));   //費用？生元？社CD
            param.put("UPLOAD_FILE_NAME", dvobj.getRecord().get("UPLOAD_FILE_NAME"));   //UPLOAD_FILE_NAME
            param.put("DATA_TYPE", dvobj.getRecord().get("DATA_TYPE"));   //デ？タタイプ
            param.put("SRC_COMPANY_CD", dvobj.getRecord().get("SRC_COMPANY_CD"));   //配賦元？社CD
            param.put("CALC_MST_VER", dvobj.getRecord().get("CALC_MST_VER"));   //CALC_MST_VER
            param.put("UNIT4", dvobj.getRecord().get("UNIT4"));   //UNIT4
            param.put("YYYYMM", dvobj.getRecord().get("YYYYMM"));   //실적월
            param.put("DST_COMPANY_CD", dvobj.getRecord().get("DST_COMPANY_CD"));   //DST_COMPANY_CD
            param.put("DST_ORG_CD", dvobj.getRecord().get("DST_ORG_CD"));   //DST_ORG_CD
            param.put("APPLICATION", dvobj.getRecord().get("APPLICATION"));   //用途
            param.put("VAL_TYPE", dvobj.getRecord().get("VAL_TYPE"));   //VAL_TYPE
            param.put("UNIT3", dvobj.getRecord().get("UNIT3"));   //UNIT3
            param.put("UNIT2", dvobj.getRecord().get("UNIT2"));   //UNIT2
            param.put("PID", dvobj.getRecord().get("PID"));   //PID
            param.put("VAL3", dvobj.getRecord().get("VAL3"));   //VAL3
            param.put("VAL4", dvobj.getRecord().get("VAL4"));   //VAL4
            param.put("VAL2", dvobj.getRecord().get("VAL2"));   //VAL2
            insert("BP_HP3D002T_DWH_20160930162713.BP_HP3D002T_FA_INS6",param);
            updcnt++;
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("INS6") ;
        return rvobj;
    }
    // Existing Data Delete
    public VOBJ BP_HP3D002T_FA_XIUD9(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"XIUD9", "Existing Data Delete" );
        VOBJ dvobj = dobj.getRetObject("S");            // Input Dataset Object.
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            updcnt += delete("BP_HP3D002T_DWH_20160930162713.BP_HP3D002T_FA_XIUD9",param);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("XIUD9");
        rvobj.setRetcode(1);
        return rvobj;
    }
    // FA Rows count
    public VOBJ BP_HP3D002T_GL_SEL9(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL9", "FA Rows count" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("BP_HP3D002T_DWH_20160930162713.BP_HP3D002T_GL_SEL9", param);
        dvobj.setName("SEL9");
        dvobj.setRecords(rlist);
        dvobj.Println("SEL9");
        return dvobj;
    }
    // GL Data Delete
    public VOBJ BP_HP3D002T_GL_XIUD11(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"XIUD11", "GL Data Delete" );
        VOBJ dvobj = dobj.getRetObject("S");            // Input Dataset Object.
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            updcnt += delete("BP_HP3D002T_DWH_20160930162713.BP_HP3D002T_GL_XIUD11",param);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("XIUD11");
        rvobj.Println("XIUD11");
        return rvobj;
    }
    // GL-AMT Select
    public VOBJ BP_HP3D002T_GL_SEL1(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL1", "GL-AMT Select" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("BP_HP3D002T_DWH_20160930162713.BP_HP3D002T_GL_SEL1", param);
        dvobj.setName("SEL1");
        dvobj.setRecords(rlist);
        dvobj.Println("SEL1");
        return dvobj;
    }
    // GL Data Save
    public VOBJ BP_HP3D002T_GL_INS9(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"INS9", "GL Data Save" );
        VOBJ dvobj = dobj.getRetObject("SEL1");           //GL-AMT Select Input Object(CALLBP_HP3D002T_GL_SEL1)
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap    param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("UPDATE_TIME", "SYSDATE");   //갱신일시
            param.put("USE_ORG_CD", dvobj.getRecord().get("USE_ORG_CD"));   //費用？生元部CD
            param.put("REQ_COMPANY_CD", dvobj.getRecord().get("REQ_COMPANY_CD"));   //要求元？社CD
            param.put("ITEM_NAME", dvobj.getRecord().get("ITEM_NAME"));   //ITEM명
            param.put("REQ_ORG_CD", dvobj.getRecord().get("REQ_ORG_CD"));   //要求元部CD
            param.put("UPDATE_USER_ID", dvobj.getRecord().get("UPDATE_USER_ID"));   //갱신자ID
            param.put("SRC_ORG_CD", dvobj.getRecord().get("SRC_ORG_CD"));   //配賦元部CD
            param.put("VAL", dvobj.getRecord().getDouble("VAL")+"");   //Value
            param.put("INVEST_TYPE_CD", dvobj.getRecord().get("INVEST_TYPE_CD"));   //投資？分CD
            param.put("HPMS_ID", dvobj.getRecord().get("HPMS_ID"));   //HPMS_ID
            param.put("UNIT", dvobj.getRecord().get("UNIT"));   //통화단위
            param.put("CUSTOMER_CD", dvobj.getRecord().get("CUSTOMER_CD"));   //顧客CD
            param.put("USE_COMPANY_CD", dvobj.getRecord().get("USE_COMPANY_CD"));   //費用？生元？社CD
            param.put("UPLOAD_FILE_NAME", dvobj.getRecord().get("UPLOAD_FILE_NAME"));   //UPLOAD_FILE_NAME
            param.put("DATA_TYPE", dvobj.getRecord().get("DATA_TYPE"));   //デ？タタイプ
            param.put("SRC_COMPANY_CD", dvobj.getRecord().get("SRC_COMPANY_CD"));   //配賦元？社CD
            param.put("CALC_MST_VER", dvobj.getRecord().get("CALC_MST_VER"));   //CALC_MST_VER
            param.put("UNIT4", dvobj.getRecord().get("UNIT4"));   //UNIT4
            param.put("YYYYMM", dvobj.getRecord().get("YYYYMM"));   //실적월
            param.put("DST_COMPANY_CD", dvobj.getRecord().get("DST_COMPANY_CD"));   //DST_COMPANY_CD
            param.put("DST_ORG_CD", dvobj.getRecord().get("DST_ORG_CD"));   //DST_ORG_CD
            param.put("APPLICATION", dvobj.getRecord().get("APPLICATION"));   //用途
            param.put("VAL_TYPE", dvobj.getRecord().get("VAL_TYPE"));   //VAL_TYPE
            param.put("UNIT3", dvobj.getRecord().get("UNIT3"));   //UNIT3
            param.put("UNIT2", dvobj.getRecord().get("UNIT2"));   //UNIT2
            param.put("PID", dvobj.getRecord().get("PID"));   //PID
            param.put("VAL3", dvobj.getRecord().get("VAL3"));   //VAL3
            param.put("VAL4", dvobj.getRecord().get("VAL4"));   //VAL4
            param.put("VAL2", dvobj.getRecord().get("VAL2"));   //VAL2
            insert("BP_HP3D002T_DWH_20160930162713.BP_HP3D002T_GL_INS9",param);
            updcnt++;
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("INS9") ;
        rvobj.Println("INS9");
        return rvobj;
    }
    // GL-QTY Select
    public VOBJ BP_HP3D002T_GL_SEL3(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL3", "GL-QTY Select" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("BP_HP3D002T_DWH_20160930162713.BP_HP3D002T_GL_SEL3", param);
        dvobj.setName("SEL3");
        dvobj.setRecords(rlist);
        dvobj.Println("SEL3");
        return dvobj;
    }
    // GL Data Save
    public VOBJ BP_HP3D002T_GL_INS12(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"INS12", "GL Data Save" );
        VOBJ dvobj = dobj.getRetObject("SEL3");           //GL-QTY Select Input Object(CALLBP_HP3D002T_GL_SEL3)
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap    param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("UPDATE_TIME", "SYSDATE");   //갱신일시
            param.put("USE_ORG_CD", dvobj.getRecord().get("USE_ORG_CD"));   //費用？生元部CD
            param.put("REQ_COMPANY_CD", dvobj.getRecord().get("REQ_COMPANY_CD"));   //要求元？社CD
            param.put("ITEM_NAME", dvobj.getRecord().get("ITEM_NAME"));   //ITEM명
            param.put("REQ_ORG_CD", dvobj.getRecord().get("REQ_ORG_CD"));   //要求元部CD
            param.put("UPDATE_USER_ID", dvobj.getRecord().get("UPDATE_USER_ID"));   //갱신자ID
            param.put("SRC_ORG_CD", dvobj.getRecord().get("SRC_ORG_CD"));   //配賦元部CD
            param.put("VAL", dvobj.getRecord().getDouble("VAL")+"");   //Value
            param.put("INVEST_TYPE_CD", dvobj.getRecord().get("INVEST_TYPE_CD"));   //投資？分CD
            param.put("HPMS_ID", dvobj.getRecord().get("HPMS_ID"));   //HPMS_ID
            param.put("UNIT", dvobj.getRecord().get("UNIT"));   //통화단위
            param.put("CUSTOMER_CD", dvobj.getRecord().get("CUSTOMER_CD"));   //顧客CD
            param.put("USE_COMPANY_CD", dvobj.getRecord().get("USE_COMPANY_CD"));   //費用？生元？社CD
            param.put("UPLOAD_FILE_NAME", dvobj.getRecord().get("UPLOAD_FILE_NAME"));   //UPLOAD_FILE_NAME
            param.put("DATA_TYPE", dvobj.getRecord().get("DATA_TYPE"));   //デ？タタイプ
            param.put("SRC_COMPANY_CD", dvobj.getRecord().get("SRC_COMPANY_CD"));   //配賦元？社CD
            param.put("CALC_MST_VER", dvobj.getRecord().get("CALC_MST_VER"));   //CALC_MST_VER
            param.put("UNIT4", dvobj.getRecord().get("UNIT4"));   //UNIT4
            param.put("YYYYMM", dvobj.getRecord().get("YYYYMM"));   //실적월
            param.put("DST_COMPANY_CD", dvobj.getRecord().get("DST_COMPANY_CD"));   //DST_COMPANY_CD
            param.put("DST_ORG_CD", dvobj.getRecord().get("DST_ORG_CD"));   //DST_ORG_CD
            param.put("APPLICATION", dvobj.getRecord().get("APPLICATION"));   //用途
            param.put("VAL_TYPE", dvobj.getRecord().get("VAL_TYPE"));   //VAL_TYPE
            param.put("UNIT3", dvobj.getRecord().get("UNIT3"));   //UNIT3
            param.put("UNIT2", dvobj.getRecord().get("UNIT2"));   //UNIT2
            param.put("PID", dvobj.getRecord().get("PID"));   //PID
            param.put("VAL3", dvobj.getRecord().get("VAL3"));   //VAL3
            param.put("VAL4", dvobj.getRecord().get("VAL4"));   //VAL4
            param.put("VAL2", dvobj.getRecord().get("VAL2"));   //VAL2
            insert("BP_HP3D002T_DWH_20160930162713.BP_HP3D002T_GL_INS12",param);
            updcnt++;
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("INS12") ;
        rvobj.Println("INS12");
        return rvobj;
    }
    // GL-PRICE Select
    public VOBJ BP_HP3D002T_GL_SEL5(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL5", "GL-PRICE Select" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("BP_HP3D002T_DWH_20160930162713.BP_HP3D002T_GL_SEL5", param);
        dvobj.setName("SEL5");
        dvobj.setRecords(rlist);
        dvobj.Println("SEL5");
        return dvobj;
    }
    // GL Data Save
    public VOBJ BP_HP3D002T_GL_INS13(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"INS13", "GL Data Save" );
        VOBJ dvobj = dobj.getRetObject("SEL5");           //GL-PRICE Select Input Object(CALLBP_HP3D002T_GL_SEL5)
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap    param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("UPDATE_TIME", "SYSDATE");   //갱신일시
            param.put("USE_ORG_CD", dvobj.getRecord().get("USE_ORG_CD"));   //費用？生元部CD
            param.put("REQ_COMPANY_CD", dvobj.getRecord().get("REQ_COMPANY_CD"));   //要求元？社CD
            param.put("ITEM_NAME", dvobj.getRecord().get("ITEM_NAME"));   //ITEM명
            param.put("REQ_ORG_CD", dvobj.getRecord().get("REQ_ORG_CD"));   //要求元部CD
            param.put("UPDATE_USER_ID", dvobj.getRecord().get("UPDATE_USER_ID"));   //갱신자ID
            param.put("SRC_ORG_CD", dvobj.getRecord().get("SRC_ORG_CD"));   //配賦元部CD
            param.put("VAL", dvobj.getRecord().getDouble("VAL")+"");   //Value
            param.put("INVEST_TYPE_CD", dvobj.getRecord().get("INVEST_TYPE_CD"));   //投資？分CD
            param.put("HPMS_ID", dvobj.getRecord().get("HPMS_ID"));   //HPMS_ID
            param.put("UNIT", dvobj.getRecord().get("UNIT"));   //통화단위
            param.put("CUSTOMER_CD", dvobj.getRecord().get("CUSTOMER_CD"));   //顧客CD
            param.put("USE_COMPANY_CD", dvobj.getRecord().get("USE_COMPANY_CD"));   //費用？生元？社CD
            param.put("UPLOAD_FILE_NAME", dvobj.getRecord().get("UPLOAD_FILE_NAME"));   //UPLOAD_FILE_NAME
            param.put("DATA_TYPE", dvobj.getRecord().get("DATA_TYPE"));   //デ？タタイプ
            param.put("SRC_COMPANY_CD", dvobj.getRecord().get("SRC_COMPANY_CD"));   //配賦元？社CD
            param.put("CALC_MST_VER", dvobj.getRecord().get("CALC_MST_VER"));   //CALC_MST_VER
            param.put("UNIT4", dvobj.getRecord().get("UNIT4"));   //UNIT4
            param.put("YYYYMM", dvobj.getRecord().get("YYYYMM"));   //실적월
            param.put("DST_COMPANY_CD", dvobj.getRecord().get("DST_COMPANY_CD"));   //DST_COMPANY_CD
            param.put("DST_ORG_CD", dvobj.getRecord().get("DST_ORG_CD"));   //DST_ORG_CD
            param.put("APPLICATION", dvobj.getRecord().get("APPLICATION"));   //用途
            param.put("VAL_TYPE", dvobj.getRecord().get("VAL_TYPE"));   //VAL_TYPE
            param.put("UNIT3", dvobj.getRecord().get("UNIT3"));   //UNIT3
            param.put("UNIT2", dvobj.getRecord().get("UNIT2"));   //UNIT2
            param.put("PID", dvobj.getRecord().get("PID"));   //PID
            param.put("VAL3", dvobj.getRecord().get("VAL3"));   //VAL3
            param.put("VAL4", dvobj.getRecord().get("VAL4"));   //VAL4
            param.put("VAL2", dvobj.getRecord().get("VAL2"));   //VAL2
            insert("BP_HP3D002T_DWH_20160930162713.BP_HP3D002T_GL_INS13",param);
            updcnt++;
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("INS13") ;
        rvobj.Println("INS13");
        return rvobj;
    }
    // Existing Data Delete
    public VOBJ BP_HP3D002T_GL_XIUD9(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"XIUD9", "Existing Data Delete" );
        VOBJ dvobj = dobj.getRetObject("S");            // Input Dataset Object.
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            updcnt += delete("BP_HP3D002T_DWH_20160930162713.BP_HP3D002T_GL_XIUD9",param);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("XIUD9");
        rvobj.setRetcode(1);
        rvobj.Println("XIUD9");
        return rvobj;
    }
}