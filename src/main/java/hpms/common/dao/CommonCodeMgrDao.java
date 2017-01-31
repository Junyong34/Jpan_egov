
package hpms.common.dao;
import java.util.*;
import egov.wizware.com.*;
import egov.wizware.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import com.ibatis.sqlmap.client.SqlMapClient;
import javax.annotation.Resource;
@Repository("CommonCodeMgr20160704154561Dao")
public class CommonCodeMgrDao extends EgovAbstractDAO
{
    // DATA_TYPE
    public VOBJ AllDataTypeCombo_SEL2(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL2", "DATA_TYPE" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("CommonCodeMgr_20160704154561.AllDataTypeCombo_SEL2", param);
        dvobj.setName("SEL2");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // ORG_LVL
    public VOBJ ORGAuthCombobox_SEL2(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL2", "ORG_LVL" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("ORG_CD", dobj.getRetObject("S1").getRecord().get("COMPANY_CD").substring(3));   //ORG_CD
        param.put("COMPANY_CD", dobj.getRetObject("S1").getRecord().get("COMPANY_CD").substring(0,3));   //COMPANY_CD
        List rlist = list("CommonCodeMgr_20160704154561.ORGAuthCombobox_SEL2", param);
        dvobj.setName("SEL2");
        dvobj.setRecords(rlist);
        dvobj.Println("SEL2");
        return dvobj;
    }
    // get ORG_SEQ
    public VOBJ ORGAuthCombobox_SEL7(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL7", "get ORG_SEQ" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("ORG_CD", dobj.getRetObject("S1").getRecord().get("COMPANY_CD").substring(3));   //ORG_CD
        param.put("COMPANY_CD", dobj.getRetObject("S1").getRecord().get("COMPANY_CD").substring(0,3));   //COMPANY_CD
        List rlist = list("CommonCodeMgr_20160704154561.ORGAuthCombobox_SEL7", param);
        dvobj.setName("SEL7");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // Organization Code
    public VOBJ ORGAuthCombobox_SEL12(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL12", "Organization Code" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("ORG_SEQ", dobj.getRetObject("SEL7").getRecord().get("ORG_SEQ"));   //ORG_SEQ
        List rlist = list("CommonCodeMgr_20160704154561.ORGAuthCombobox_SEL12", param);
        dvobj.setName("SEL12");
        dvobj.setRecords(rlist);
        dvobj.Println("SEL12");
        return dvobj;
    }
    // ORG ComboBox
    public VOBJ ORGAuthCombobox_SEL1(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL1", "ORG ComboBox" );
        VOBJ       rvobj= null;
        rvobj = wutil.getMergeObject(dobj, "SEL11, SEL12","ITEM_CD, ITEM_NM" );
        rvobj.setName("SEL1") ;
        rvobj.setRetcode(1);
        rvobj.Println("SEL1");
        return rvobj;
    }
    // LVL4-5 SEQ
    public VOBJ ORGAuthCombobox_SEL27(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL27", "LVL4-5 SEQ" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("ORG_CD", dobj.getRetObject("S1").getRecord().get("COMPANY_CD").substring(3));   //ORG_CD
        param.put("COMPANY_CD", dobj.getRetObject("S1").getRecord().get("COMPANY_CD").substring(0,3));   //COMPANY_CD
        List rlist = list("CommonCodeMgr_20160704154561.ORGAuthCombobox_SEL27", param);
        dvobj.setName("SEL27");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // Organization Code
    public VOBJ ORGAuthCombobox_SEL11(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL11", "Organization Code" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("ORG_SEQ_L4", dobj.getRetObject("SEL27").getRecord().get("ORG_SEQ_L4"));   //ORG_SEQ_L4
        param.put("ORG_SEQ_L5", dobj.getRetObject("SEL27").getRecord().get("ORG_SEQ_L5"));   //ORG_SEQ_L5
        List rlist = list("CommonCodeMgr_20160704154561.ORGAuthCombobox_SEL11", param);
        dvobj.setName("SEL11");
        dvobj.setRecords(rlist);
        dvobj.Println("SEL11");
        return dvobj;
    }
    // get ORG_SEQ
    public VOBJ ORGCombobox_SEL3(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL3", "get ORG_SEQ" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        String  ORG_CD="" ;          //ORG_CD
        if(!dobj.getRetObject("S1").getRecord().get("COMPANY_CD").equals(""))
        {
            ORG_CD = dobj.getRetObject("S1").getRecord().get("COMPANY_CD").substring(3);
        }
        else if(!dobj.getRetObject("S2").getRecord().get("CLOSING_COMPANY_CD").equals(""))
        {
            ORG_CD = dobj.getRetObject("S2").getRecord().get("CLOSING_COMPANY_CD").substring(3);
        }
        else if(!dobj.getRetObject("S").getRecord().get("OWNER_COMPANY_CD").equals(""))
        {
            ORG_CD = dobj.getRetObject("S").getRecord().get("OWNER_COMPANY_CD").substring(3);
        }
        else
        {
            ORG_CD = "";
        }
        param.put("ORG_CD", ORG_CD);   //ORG_CD
        String  COMPANY_CD="" ;          //COMPANY_CD
        if(!dobj.getRetObject("S1").getRecord().get("COMPANY_CD").equals(""))
        {
            COMPANY_CD = dobj.getRetObject("S1").getRecord().get("COMPANY_CD").substring(0,3);
        }
        else if(!dobj.getRetObject("S2").getRecord().get("CLOSING_COMPANY_CD").equals(""))
        {
            COMPANY_CD = dobj.getRetObject("S2").getRecord().get("CLOSING_COMPANY_CD").substring(0,3);
        }
        else if(!dobj.getRetObject("S").getRecord().get("OWNER_COMPANY_CD").equals(""))
        {
            COMPANY_CD = dobj.getRetObject("S").getRecord().get("OWNER_COMPANY_CD").substring(0,3);
        }
        else
        {
            COMPANY_CD = "";
        }
        param.put("COMPANY_CD", COMPANY_CD);   //COMPANY_CD
        List rlist = list("CommonCodeMgr_20160704154561.ORGCombobox_SEL3", param);
        dvobj.setName("SEL3");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // Organization Code
    public VOBJ ORGCombobox_SEL1(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL1", "Organization Code" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("ORG_SEQ", dobj.getRetObject("SEL3").getRecord().get("ORG_SEQ"));   //ORG_SEQ
        List rlist = list("CommonCodeMgr_20160704154561.ORGCombobox_SEL1", param);
        dvobj.setName("SEL1");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        dvobj.Println("SEL1");
        return dvobj;
    }
    // get ORG_SEQ
    public VOBJ SessionCombo_SEL3(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL3", "get ORG_SEQ" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        String  ORG_CD="" ;          //ORG_CD
        if(!dobj.getRetObject("G").getRecord().get("ORG_CD").equals(""))
        {
            ORG_CD = dobj.getRetObject("G").getRecord().get("ORG_CD");
        }
        param.put("ORG_CD", ORG_CD);   //ORG_CD
        String  COMPANY_CD="" ;          //COMPANY_CD
        if(!dobj.getRetObject("G").getRecord().get("COMPANY_CD").equals(""))
        {
            COMPANY_CD = dobj.getRetObject("G").getRecord().get("COMPANY_CD");
        }
        param.put("COMPANY_CD", COMPANY_CD);   //COMPANY_CD
        List rlist = list("CommonCodeMgr_20160704154561.SessionCombo_SEL3", param);
        dvobj.setName("SEL3");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // Organization Code
    public VOBJ SessionCombo_SEL1(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL1", "Organization Code" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("ORG_SEQ", dobj.getRetObject("SEL3").getRecord().get("ORG_SEQ"));   //ORG_SEQ
        List rlist = list("CommonCodeMgr_20160704154561.SessionCombo_SEL1", param);
        dvobj.setName("SEL1");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        dvobj.Println("SEL1");
        return dvobj;
    }
    // Company Code
    public VOBJ Company_Org_cd_SEL4(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL4", "Company Code" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("CommonCodeMgr_20160704154561.Company_Org_cd_SEL4", param);
        dvobj.setName("SEL4");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // Organization Code
    public VOBJ Company_Org_cd_SEL2(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL2", "Organization Code" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        String  COMPANY_CD="" ;          //COMPANY_CD
        if(!dobj.getRetObject("G").getRecord().get("COMPANY_CD").equals(""))
        {
            COMPANY_CD = dobj.getRetObject("G").getRecord().get("COMPANY_CD");
        }
        else if(!dobj.getRetObject("S").getRecord().get("COMPANY_CD").equals(""))
        {
            COMPANY_CD = dobj.getRetObject("S").getRecord().get("COMPANY_CD");
        }
        else if(!dobj.getRetObject("S1").getRecord().get("COMPANY_CD").equals(""))
        {
            COMPANY_CD = dobj.getRetObject("S1").getRecord().get("COMPANY_CD");
        }
        else if(!dobj.getRetObject("S2").getRecord().get("COMPANY_CD").equals(""))
        {
            COMPANY_CD = dobj.getRetObject("S2").getRecord().get("COMPANY_CD").substring(0,3);
        }
        else if(!dobj.getRetObject("S3").getRecord().get("COMPANY_CD").equals(""))
        {
            COMPANY_CD = dobj.getRetObject("S3").getRecord().get("COMPANY_CD");
        }
        else if(!dobj.getRetObject("S4").getRecord().get("COMPANY_CD").equals(""))
        {
            COMPANY_CD = dobj.getRetObject("S4").getRecord().get("COMPANY_CD").substring(0,3);
        }
        else if(!dobj.getRetObject("S").getRecord().get("OWNER_COMPANY_CD").equals(""))
        {
            COMPANY_CD = dobj.getRetObject("S").getRecord().get("OWNER_COMPANY_CD").substring(0,3);
        }
        else if(!dobj.getRetObject("G").getRecord().get("COMPANY_CD").equals(""))
        {
            COMPANY_CD = dobj.getRetObject("G").getRecord().get("COMPANY_CD");
        }
        param.put("COMPANY_CD", COMPANY_CD);   //COMPANY_CD
        List rlist = list("CommonCodeMgr_20160704154561.Company_Org_cd_SEL2", param);
        dvobj.setName("SEL2");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        dvobj.Println("SEL2");
        return dvobj;
    }
    // TotalDataCompany
    public VOBJ Company_Org_cd_SEL5(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL5", "TotalDataCompany" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("CommonCodeMgr_20160704154561.Company_Org_cd_SEL5", param);
        dvobj.setName("SEL5");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // Total_ORG
    public VOBJ Company_Org_cd_SEL7(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL7", "Total_ORG" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("CommonCodeMgr_20160704154561.Company_Org_cd_SEL7", param);
        dvobj.setName("SEL7");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // PID STATUS
    public VOBJ CommonCombo_SEL2(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL2", "PID STATUS" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("CommonCodeMgr_20160704154561.CommonCombo_SEL2", param);
        dvobj.setName("SEL2");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // CATEGORY_CD
    public VOBJ CommonCombo_SEL4(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL4", "CATEGORY_CD" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("CommonCodeMgr_20160704154561.CommonCombo_SEL4", param);
        dvobj.setName("SEL4");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // OWNER_ORG
    public VOBJ CommonCombo_SEL6(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL6", "OWNER_ORG" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("ORG_CD", dobj.getRetObject("G").getRecord().get("ORG_CD"));   //ORG_CD
        param.put("COMPANY_CD", dobj.getRetObject("G").getRecord().get("COMPANY_CD"));   //COMPANY_CD
        List rlist = list("CommonCodeMgr_20160704154561.CommonCombo_SEL6", param);
        dvobj.setName("SEL6");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // CURRENCY_TYPE
    public VOBJ CommonCombo_SEL8(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL8", "CURRENCY_TYPE" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("CommonCodeMgr_20160704154561.CommonCombo_SEL8", param);
        dvobj.setName("SEL8");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // DATA_TYPE
    public VOBJ CommonCombo_SEL10(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL10", "DATA_TYPE" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("CommonCodeMgr_20160704154561.CommonCombo_SEL10", param);
        dvobj.setName("SEL10");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // DCP_STATUS
    public VOBJ CommonCombo_SEL12(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL12", "DCP_STATUS" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("CommonCodeMgr_20160704154561.CommonCombo_SEL12", param);
        dvobj.setName("SEL12");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // Forecast_FCSTA
    public VOBJ CommonCombo_SEL14(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL14", "Forecast_FCSTA" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("AUTH_CD", dobj.getRetObject("G").getRecord().get("AUTH_CD"));   //AUTH_CD
        List rlist = list("CommonCodeMgr_20160704154561.CommonCombo_SEL14", param);
        dvobj.setName("SEL14");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // From
    public VOBJ CommonCombo_SEL16(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL16", "From" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("AUTH_CD", dobj.getRetObject("G").getRecord().get("AUTH_CD"));   //AUTH_CD
        List rlist = list("CommonCodeMgr_20160704154561.CommonCombo_SEL16", param);
        dvobj.setName("SEL16");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // To
    public VOBJ CommonCombo_SEL20(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL20", "To" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("AUTH_CD", dobj.getRetObject("G").getRecord().get("AUTH_CD"));   //AUTH_CD
        List rlist = list("CommonCodeMgr_20160704154561.CommonCombo_SEL20", param);
        dvobj.setName("SEL20");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // HPMS_ID
    public VOBJ CommonCombo_SEL18(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL18", "HPMS_ID" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("CommonCodeMgr_20160704154561.CommonCombo_SEL18", param);
        dvobj.setName("SEL18");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        return dvobj;
    }
}