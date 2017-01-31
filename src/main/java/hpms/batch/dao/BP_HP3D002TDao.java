
package hpms.batch.dao;
import java.util.*;
import egov.wizware.com.*;
import egov.wizware.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import com.ibatis.sqlmap.client.SqlMapClient;
import javax.annotation.Resource;
@Repository("BP_HP3D002T20160930162714Dao")
public class BP_HP3D002TDao extends EgovAbstractDAO
{
    // HP3D101T delete
    public VOBJ BP_HP3D101T_XIUD3(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"XIUD3", "HP3D101T delete" );
        VOBJ dvobj = dobj.getRetObject("S");            // Input Dataset Object.
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            updcnt += delete("BP_HP3D002T_20160930162714.BP_HP3D101T_XIUD3",param);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("XIUD3");
        rvobj.Println("XIUD3");
        return rvobj;
    }
    // HP3D002T Sum
    public VOBJ BP_HP3D101T_SEL1(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL1", "HP3D002T Sum" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("BP_HP3D002T_20160930162714.BP_HP3D101T_SEL1", param);
        dvobj.setName("SEL1");
        dvobj.setRecords(rlist);
        dvobj.Println("SEL1");
        return dvobj;
    }
    // Summary Save
    public VOBJ BP_HP3D101T_INS5(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"INS5", "Summary Save" );
        VOBJ dvobj = dobj.getRetObject("SEL1");           //HP3D002T Sum Input Object(CALLBP_HP3D101T_SEL1)
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap    param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("UPDATE_TIME", "SYSDATE");   //갱신일시
            param.put("USE_COMPANY_CD", dvobj.getRecord().get("USE_COMPANY_CD"));   //費用？生元？社CD
            param.put("DATA_TYPE", dvobj.getRecord().get("DATA_TYPE"));   //デ？タタイプ
            param.put("SRC_COMPANY_CD", dvobj.getRecord().get("SRC_COMPANY_CD"));   //配賦元？社CD
            param.put("USE_ORG_CD", dvobj.getRecord().get("USE_ORG_CD"));   //費用？生元部CD
            param.put("UPDATE_USER_ID", dvobj.getRecord().get("UPDATE_USER_ID"));   //갱신자ID
            param.put("YYYYMM", dvobj.getRecord().get("YYYYMM"));   //실적월
            param.put("SRC_ORG_CD", dvobj.getRecord().get("SRC_ORG_CD"));   //配賦元部CD
            param.put("DST_COMPANY_CD", dvobj.getRecord().get("DST_COMPANY_CD"));   //DST_COMPANY_CD
            param.put("VAL", dvobj.getRecord().getDouble("VAL")+"");   //Value
            param.put("HPMS_ID", dvobj.getRecord().get("HPMS_ID"));   //HPMS_ID
            param.put("DST_ORG_CD", dvobj.getRecord().get("DST_ORG_CD"));   //DST_ORG_CD
            param.put("UNIT2", dvobj.getRecord().get("UNIT2"));   //UNIT2
            param.put("SUBJECT_CD", dvobj.getRecord().get("SUBJECT_CD"));   //科目CD
            param.put("PID", dvobj.getRecord().get("PID"));   //PID
            param.put("UNIT", dvobj.getRecord().get("UNIT"));   //통화단위
            param.put("VAL2", dvobj.getRecord().get("VAL2"));   //VAL2
            insert("BP_HP3D002T_20160930162714.BP_HP3D101T_INS5",param);
            updcnt++;
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("INS5") ;
        rvobj.Println("INS5");
        return rvobj;
    }
    // ALC Result delete
    public VOBJ BP_HP3D002T_ALC_XIUD1(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"XIUD1", "ALC Result delete" );
        VOBJ dvobj = dobj.getRetObject("S");            // Input Dataset Object.
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            updcnt += delete("BP_HP3D002T_20160930162714.BP_HP3D002T_ALC_XIUD1",param);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("XIUD1");
        return rvobj;
    }
    // Dist Target
    public VOBJ BP_HP3D002T_ALC_SEL2(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL2", "Dist Target" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("BP_HP3D002T_20160930162714.BP_HP3D002T_ALC_SEL2", param);
        dvobj.setName("SEL2");
        dvobj.setRecords(rlist);
        dvobj.Println("SEL2");
        return dvobj;
    }
    // SEL HP1DM09T
    // Dist Process Master
    public VOBJ BP_HP3D002T_ALC_SEL14(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL14", "SEL HP1DM09T" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("HPMS_ID", dobj.getRetObject("R").getRecord().get("HPMS_ID"));   //HPMS_ID
        param.put("USE_ORG_CD", dobj.getRetObject("R").getRecord().get("USE_ORG_CD"));   //費用？生元部CD
        param.put("USE_COMPANY_CD", dobj.getRetObject("R").getRecord().get("USE_COMPANY_CD"));   //費用？生元？社CD
        List rlist = list("BP_HP3D002T_20160930162714.BP_HP3D002T_ALC_SEL14", param);
        dvobj.setName("SEL14");
        dvobj.setRecords(rlist);
        dvobj.Println("SEL14");
        return dvobj;
    }
    // Dist Proc by PID
    public VOBJ BP_HP3D002T_ALC_SEL12(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL12", "Dist Proc by PID" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("VAL2", dobj.getRetObject("R").getRecord().get("VAL2"));   //VAL2
        param.put("CUSTOMER_CD", dobj.getRetObject("R").getRecord().get("CUSTOMER_CD"));   //顧客CD
        param.put("VAL4", dobj.getRetObject("R").getRecord().get("VAL4"));   //VAL4
        param.put("VAL3", dobj.getRetObject("R").getRecord().get("VAL3"));   //VAL3
        param.put("UNIT", dobj.getRetObject("R").getRecord().get("UNIT"));   //통화단위
        param.put("PID", dobj.getRetObject("R").getRecord().get("PID"));   //PID
        param.put("UNIT2", dobj.getRetObject("R").getRecord().get("UNIT2"));   //UNIT2
        param.put("UNIT3", dobj.getRetObject("R").getRecord().get("UNIT3"));   //UNIT2
        param.put("VAL_TYPE", dobj.getRetObject("R").getRecord().get("VAL_TYPE"));   //VAL_TYPE
        param.put("APPLICATION", dobj.getRetObject("R").getRecord().get("APPLICATION"));   //用途
        param.put("HPMS_ID", dobj.getRetObject("R").getRecord().get("HPMS_ID"));   //HPMS_ID
        param.put("INVEST_TYPE_CD", dobj.getRetObject("R").getRecord().get("INVEST_TYPE_CD"));   //投資？分CD
        param.put("VAL", dobj.getRetObject("R").getRecord().getDouble("VAL")+"");   //Value
        param.put("DST_HPMS_ID", dobj.getRetObject("SEL14").getRecord().get("DST_HPMS_ID"));   //配賦？理後の項目ID
        param.put("YYYYMM", dobj.getRetObject("R").getRecord().get("YYYYMM"));   //실적월
        param.put("ITEM_NAME", dobj.getRetObject("R").getRecord().get("ITEM_NAME"));   //ITEM명
        param.put("REQ_ORG_CD", dobj.getRetObject("R").getRecord().get("REQ_ORG_CD"));   //要求元部CD
        param.put("UNIT4", dobj.getRetObject("R").getRecord().get("UNIT4"));   //UNIT4
        param.put("REQ_COMPANY_CD", dobj.getRetObject("R").getRecord().get("REQ_COMPANY_CD"));   //要求元？社CD
        param.put("USE_ORG_CD", dobj.getRetObject("R").getRecord().get("USE_ORG_CD"));   //費用？生元部CD
        param.put("CALC_MST_VER", dobj.getRetObject("R").getRecord().get("CALC_MST_VER"));   //CALC_MST_VER
        param.put("DATA_TYPE", dobj.getRetObject("R").getRecord().get("DATA_TYPE"));   //デ？タタイプ
        param.put("USE_COMPANY_CD", dobj.getRetObject("R").getRecord().get("USE_COMPANY_CD"));   //費用？生元？社CD
        List rlist = list("BP_HP3D002T_20160930162714.BP_HP3D002T_ALC_SEL12", param);
        dvobj.setName("SEL12");
        dvobj.setRecords(rlist);
        dvobj.Println("SEL12");
        return dvobj;
    }
    // Dist Data Save
    public VOBJ BP_HP3D002T_ALC_INS13(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"INS13", "Dist Data Save" );
        VOBJ dvobj = dobj.getRetObject("SEL12");           // Input Dataset Object.
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
            param.put("SRC_ORG_CD", dvobj.getRecord().get("SRC_ORG_CD"));   //配賦元部CD
            param.put("VAL", dvobj.getRecord().getDouble("VAL")+"");   //Value
            param.put("INVEST_TYPE_CD", dvobj.getRecord().get("INVEST_TYPE_CD"));   //投資？分CD
            param.put("HPMS_ID", dvobj.getRecord().get("HPMS_ID"));   //HPMS_ID
            param.put("UNIT", dvobj.getRecord().get("UNIT"));   //통화단위
            param.put("CUSTOMER_CD", dvobj.getRecord().get("CUSTOMER_CD"));   //顧客CD
            param.put("USE_COMPANY_CD", dvobj.getRecord().get("USE_COMPANY_CD"));   //費用？生元？社CD
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
            insert("BP_HP3D002T_20160930162714.BP_HP3D002T_ALC_INS13",param);
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
    // MP Data Check
    // Data from MP Exist Check
    public VOBJ BP_HP3D002T_ALC_SEL13(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL13", "MP Data Check" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("BP_HP3D002T_20160930162714.BP_HP3D002T_ALC_SEL13", param);
        dvobj.setName("SEL13");
        dvobj.setRecords(rlist);
        dvobj.Println("SEL13");
        return dvobj;
    }
    // SEL MP Data
    // Latest
    public VOBJ BP_HP3D002T_ALC_SEL4(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL4", "SEL MP Data" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("BP_HP3D002T_20160930162714.BP_HP3D002T_ALC_SEL4", param);
        dvobj.setName("SEL4");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // MP Data Save
    public VOBJ BP_HP3D002T_ALC_INS18(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"INS18", "MP Data Save" );
        VOBJ dvobj = dobj.getRetObject("SEL4");           // Input Dataset Object.
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap    param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("UPDATE_TIME", "SYSDATE");   //갱신일시
            param.put("UPDATE_USER_ID", dobj.getRetObject("G").getRecord().get("USER_ID"));   //갱신자ID
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
            param.put("DATA_TYPE", dvobj.getRecord().get("DATA_TYPE"));   //デ？タタイプ
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
            insert("BP_HP3D002T_20160930162714.BP_HP3D002T_ALC_INS18",param);
            updcnt++;
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("INS18") ;
        return rvobj;
    }
    // CAL Result delete
    public VOBJ BP_HP3D002T_CAL_XIUD1(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"XIUD1", "CAL Result delete" );
        VOBJ dvobj = dobj.getRetObject("S");            // Input Dataset Object.
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            updcnt += delete("BP_HP3D002T_20160930162714.BP_HP3D002T_CAL_XIUD1",param);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("XIUD1");
        rvobj.Println("XIUD1");
        return rvobj;
    }
    // Sel HP1M104T
    public VOBJ BP_HP3D002T_CAL_SEL2(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL2", "Sel HP1M104T" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("BP_HP3D002T_20160930162714.BP_HP3D002T_CAL_SEL2", param);
        dvobj.setName("SEL2");
        dvobj.setRecords(rlist);
        dvobj.Println("SEL2");
        return dvobj;
    }
    // Sel Oper Target
    public VOBJ BP_HP3D002T_CAL_SEL12(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL12", "Sel Oper Target" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("CALC_COL1", dobj.getRetObject("R").getRecord().get("CALC_COL1"));   //項目1
        List rlist = list("BP_HP3D002T_20160930162714.BP_HP3D002T_CAL_SEL12", param);
        dvobj.setName("SEL12");
        dvobj.setRecords(rlist);
        dvobj.Println("SEL12");
        return dvobj;
    }
    // Sel Oper Target
    public VOBJ BP_HP3D002T_CAL_SEL11(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL11", "Sel Oper Target" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("CALC_COL2", dobj.getRetObject("R").getRecord().get("CALC_COL2"));   //項目2
        param.put("CALC_COL1", dobj.getRetObject("R").getRecord().get("CALC_COL1"));   //項目1
        List rlist = list("BP_HP3D002T_20160930162714.BP_HP3D002T_CAL_SEL11", param);
        dvobj.setName("SEL11");
        dvobj.setRecords(rlist);
        dvobj.Println("SEL11");
        return dvobj;
    }
    // Sum Prcs
    public VOBJ BP_HP3D002T_CAL_SEL9(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL9", "Sum Prcs" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("CALC_COL1", dobj.getRetObject("R").getRecord().get("CALC_COL1"));   //項目1
        param.put("NEW_HPMS_ID", dobj.getRetObject("R").getRecord().get("HPMS_ID"));   //NEW_HPMS_ID
        List rlist = list("BP_HP3D002T_20160930162714.BP_HP3D002T_CAL_SEL9", param);
        dvobj.setName("SEL9");
        dvobj.setRecords(rlist);
        dvobj.Println("SEL9");
        return dvobj;
    }
    // Binary Calc
    public VOBJ BP_HP3D002T_CAL_SEL13(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL13", "Binary Calc" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("CALC_FUNC", dobj.getRetObject("R").getRecord().get("CALC_FUNC"));   //計算式
        param.put("CALC_COL2", dobj.getRetObject("R").getRecord().get("CALC_COL2"));   //項目2
        param.put("CALC_COL1", dobj.getRetObject("R").getRecord().get("CALC_COL1"));   //項目1
        param.put("NEW_HPMS_ID", dobj.getRetObject("R").getRecord().get("HPMS_ID"));   //NEW_HPMS_ID
        List rlist = list("BP_HP3D002T_20160930162714.BP_HP3D002T_CAL_SEL13", param);
        dvobj.setName("SEL13");
        dvobj.setRecords(rlist);
        dvobj.Println("SEL13");
        return dvobj;
    }
    // Result Item Save
    public VOBJ BP_HP3D002T_CAL_INS14(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"INS14", "Result Item Save" );
        VOBJ dvobj = dobj.getRetObject("SEL9");           // Input Dataset Object.
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
            param.put("SRC_ORG_CD", dvobj.getRecord().get("SRC_ORG_CD"));   //配賦元部CD
            param.put("VAL", dvobj.getRecord().getDouble("VAL")+"");   //Value
            param.put("INVEST_TYPE_CD", dvobj.getRecord().get("INVEST_TYPE_CD"));   //投資？分CD
            param.put("HPMS_ID", dvobj.getRecord().get("HPMS_ID"));   //HPMS_ID
            param.put("UNIT", dvobj.getRecord().get("UNIT"));   //통화단위
            param.put("CUSTOMER_CD", dvobj.getRecord().get("CUSTOMER_CD"));   //顧客CD
            param.put("USE_COMPANY_CD", dvobj.getRecord().get("USE_COMPANY_CD"));   //費用？生元？社CD
            param.put("DATA_TYPE", dvobj.getRecord().get("DATA_TYPE"));   //デ？タタイプ
            param.put("SRC_COMPANY_CD", dvobj.getRecord().get("SRC_COMPANY_CD"));   //配賦元？社CD
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
            insert("BP_HP3D002T_20160930162714.BP_HP3D002T_CAL_INS14",param);
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
    // Result Item Save
    public VOBJ BP_HP3D002T_CAL_INS22(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"INS22", "Result Item Save" );
        VOBJ dvobj = dobj.getRetObject("SEL13");           // Input Dataset Object.
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
            param.put("SRC_ORG_CD", dvobj.getRecord().get("SRC_ORG_CD"));   //配賦元部CD
            param.put("VAL", dvobj.getRecord().getDouble("VAL")+"");   //Value
            param.put("INVEST_TYPE_CD", dvobj.getRecord().get("INVEST_TYPE_CD"));   //投資？分CD
            param.put("HPMS_ID", dvobj.getRecord().get("HPMS_ID"));   //HPMS_ID
            param.put("UNIT", dvobj.getRecord().get("UNIT"));   //통화단위
            param.put("CUSTOMER_CD", dvobj.getRecord().get("CUSTOMER_CD"));   //顧客CD
            param.put("USE_COMPANY_CD", dvobj.getRecord().get("USE_COMPANY_CD"));   //費用？生元？社CD
            param.put("DATA_TYPE", dvobj.getRecord().get("DATA_TYPE"));   //デ？タタイプ
            param.put("SRC_COMPANY_CD", dvobj.getRecord().get("SRC_COMPANY_CD"));   //配賦元？社CD
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
            insert("BP_HP3D002T_20160930162714.BP_HP3D002T_CAL_INS22",param);
            updcnt++;
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("INS22") ;
        rvobj.Println("INS22");
        return rvobj;
    }
    // HP3D002T delete
    public VOBJ BP_HP3D002T_XIUD16(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"XIUD16", "HP3D002T delete" );
        VOBJ dvobj = dobj.getRetObject("S");            // Input Dataset Object.
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            updcnt += delete("BP_HP3D002T_20160930162714.BP_HP3D002T_XIUD16",param);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("XIUD16");
        rvobj.Println("XIUD16");
        return rvobj;
    }
    // HP3D001T Select
    public VOBJ BP_HP3D002T_SEL3(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL3", "HP3D001T Select" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("BP_HP3D002T_20160930162714.BP_HP3D002T_SEL3", param);
        dvobj.setName("SEL3");
        dvobj.setRecords(rlist);
        dvobj.Println("SEL3");
        return dvobj;
    }
    // HP3D002T save
    public VOBJ BP_HP3D002T_INS14(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"INS14", "HP3D002T save" );
        VOBJ dvobj = dobj.getRetObject("SEL3");           //HP3D001T Select Input Object(CALLBP_HP3D002T_SEL3)
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap    param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("UPDATE_TIME", "");   //갱신일시
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
            insert("BP_HP3D002T_20160930162714.BP_HP3D002T_INS14",param);
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
}