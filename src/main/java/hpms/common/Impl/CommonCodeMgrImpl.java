
package hpms.common.Impl;
import java.util.*;
import egov.wizware.com.*;
import egov.wizware.util.*;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import hpms.common.dao.*;
import hpms.common.service.CommonCodeMgr;
@Service("CommonCodeMgr20160704154561Service")
/**
*/
public class CommonCodeMgrImpl extends AbstractServiceImpl implements CommonCodeMgr
{
    @Autowired
    private CommonCodeMgrDao CommonCodeMgrdao;
    public DOBJ AllDataTypeCombo(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vSEL2 = CommonCodeMgrdao.AllDataTypeCombo_SEL2(dobj);        //  DATA_TYPE
        dobj.setRetObject(vSEL2);
        return dobj;
    }
    public DOBJ ORGAuthCombobox(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vSEL2 = CommonCodeMgrdao.ORGAuthCombobox_SEL2(dobj);        //  ORG_LVL
        dobj.setRetObject(vSEL2);
        if(dobj.getRetObject("G").getRecord().get("AUTH_CD").equals("50") && dobj.getRetObject("SEL2").getRecord().getInt("ORG_LVL") > 3)
        {
            VOBJ vSEL27 = CommonCodeMgrdao.ORGAuthCombobox_SEL27(dobj);        //  LVL4-5 SEQ
            dobj.setRetObject(vSEL27);
            VOBJ vSEL11 = CommonCodeMgrdao.ORGAuthCombobox_SEL11(dobj);        //  Organization Code
            dobj.setRetObject(vSEL11);
            VOBJ vSEL1 = CommonCodeMgrdao.ORGAuthCombobox_SEL1(dobj);        //  ORG ComboBox
            dobj.setRetObject(vSEL1);
        }
        else
        {
            VOBJ vSEL7 = CommonCodeMgrdao.ORGAuthCombobox_SEL7(dobj);        //  get ORG_SEQ
            dobj.setRetObject(vSEL7);
            VOBJ vSEL12 = CommonCodeMgrdao.ORGAuthCombobox_SEL12(dobj);        //  Organization Code
            dobj.setRetObject(vSEL12);
            VOBJ vSEL1 = CommonCodeMgrdao.ORGAuthCombobox_SEL1(dobj);        //  ORG ComboBox
            dobj.setRetObject(vSEL1);
        }
        return dobj;
    }
    public DOBJ ORGCombobox(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vSEL3 = CommonCodeMgrdao.ORGCombobox_SEL3(dobj);        //  get ORG_SEQ
        dobj.setRetObject(vSEL3);
        VOBJ vSEL1 = CommonCodeMgrdao.ORGCombobox_SEL1(dobj);        //  Organization Code
        dobj.setRetObject(vSEL1);
        return dobj;
    }
    public DOBJ SessionCombo(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vSEL3 = CommonCodeMgrdao.SessionCombo_SEL3(dobj);        //  get ORG_SEQ
        dobj.setRetObject(vSEL3);
        VOBJ vSEL1 = CommonCodeMgrdao.SessionCombo_SEL1(dobj);        //  Organization Code
        dobj.setRetObject(vSEL1);
        return dobj;
    }
    public DOBJ Company_Org_cd(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vSEL4 = CommonCodeMgrdao.Company_Org_cd_SEL4(dobj);        //  Company Code
        dobj.setRetObject(vSEL4);
        VOBJ vSEL2 = CommonCodeMgrdao.Company_Org_cd_SEL2(dobj);        //  Organization Code
        dobj.setRetObject(vSEL2);
        VOBJ vSEL5 = CommonCodeMgrdao.Company_Org_cd_SEL5(dobj);        //  TotalDataCompany
        dobj.setRetObject(vSEL5);
        VOBJ vSEL7 = CommonCodeMgrdao.Company_Org_cd_SEL7(dobj);        //  Total_ORG
        dobj.setRetObject(vSEL7);
        return dobj;
    }
    public DOBJ CommonCombo(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vSEL2 = CommonCodeMgrdao.CommonCombo_SEL2(dobj);        //  PID STATUS
        dobj.setRetObject(vSEL2);
        VOBJ vSEL4 = CommonCodeMgrdao.CommonCombo_SEL4(dobj);        //  CATEGORY_CD
        dobj.setRetObject(vSEL4);
        VOBJ vSEL6 = CommonCodeMgrdao.CommonCombo_SEL6(dobj);        //  OWNER_ORG
        dobj.setRetObject(vSEL6);
        VOBJ vSEL8 = CommonCodeMgrdao.CommonCombo_SEL8(dobj);        //  CURRENCY_TYPE
        dobj.setRetObject(vSEL8);
        VOBJ vSEL10 = CommonCodeMgrdao.CommonCombo_SEL10(dobj);        //  DATA_TYPE
        dobj.setRetObject(vSEL10);
        VOBJ vSEL12 = CommonCodeMgrdao.CommonCombo_SEL12(dobj);        //  DCP_STATUS
        dobj.setRetObject(vSEL12);
        VOBJ vSEL14 = CommonCodeMgrdao.CommonCombo_SEL14(dobj);        //  Forecast_FCSTA
        dobj.setRetObject(vSEL14);
        VOBJ vSEL16 = CommonCodeMgrdao.CommonCombo_SEL16(dobj);        //  From
        dobj.setRetObject(vSEL16);
        VOBJ vSEL20 = CommonCodeMgrdao.CommonCombo_SEL20(dobj);        //  To
        dobj.setRetObject(vSEL20);
        VOBJ vSEL18 = CommonCodeMgrdao.CommonCombo_SEL18(dobj);        //  HPMS_ID
        dobj.setRetObject(vSEL18);
        return dobj;
    }
}