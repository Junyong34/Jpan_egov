
package hpms.base.Impl;
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
import hpms.base.dao.*;
import hpms.base.service.PlanMgr;
@Service("PlanMgr20160901091138Service")
/**
*/
public class PlanMgrImpl extends AbstractServiceImpl implements PlanMgr
{
    @Autowired
    private PlanMgrDao PlanMgrdao;
    public DOBJ planExcelDownload(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        if(!dobj.getRetObject("S").getRecord().get("COMPANY_CD").equals(""))
        {
            VOBJ vSEL3 = PlanMgrdao.planExcelDownload_SEL3(dobj);        //  get ORG_SEQ
            dobj.setRetObject(vSEL3);
            VOBJ vEXDN2 = PlanMgrdao.planExcelDownload_EXDN2(dobj);        //  Forecast Data
            dobj.setRetObject(vEXDN2);
            VOBJ vDN01 = PlanMgrdao.planExcelDownload_DN01(dobj);        //  Excel File Create
            dobj.setRetObject(vDN01);
        }
        else
        {
            VOBJ vEXDN1 = PlanMgrdao.planExcelDownload_EXDN1(dobj);        //  Forecast Data
            dobj.setRetObject(vEXDN1);
            VOBJ vDN01 = PlanMgrdao.planExcelDownload_DN01(dobj);        //  Excel File Create
            dobj.setRetObject(vDN01);
        }
        return dobj;
    }
    public DOBJ ExcelNoChecking(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vPEX2 = PlanMgrdao.ExcelNoChecking_PEX2(dobj);        //  ExcelUpload Data
        dobj.setRetObject(vPEX2);
        VOBJ vER01 = PlanMgrdao.ExcelNoChecking_ER01(dobj);        //  get LogSequence
        dobj.setRetObject(vER01);
        VOBJ vCVT25 = PlanMgrdao.ExcelNoChecking_CVT25(dobj);        //  Param
        dobj.setRetObject(vCVT25);
        if( dobj.getRtncode ( ) != 0)
        {
            VOBJ vINS27 = PlanMgrdao.ExcelNoChecking_INS27(dobj);        //  WorkTable Save HP2D001W
            dobj.setRetObject(vINS27);
        }
        else if( dobj.getRtncode ( ) == 0)
        {
            VOBJ vINS28 = PlanMgrdao.ExcelNoChecking_INS28(dobj);        //  WorkTable Save HP2D001W
            dobj.setRetObject(vINS28);
            VOBJ vSEL80 = PlanMgrdao.ExcelNoChecking_SEL80(dobj);        //  MST Cheeck
            dobj.setRetObject(vSEL80);
            VOBJ vUPD68 = PlanMgrdao.ExcelNoChecking_UPD68(dobj);        //  WorkTable Update HP2D001W
            dobj.setRetObject(vUPD68);
            VOBJ vSEL78 = PlanMgrdao.ExcelNoChecking_SEL78(dobj);        //  Error exist or No
            dobj.setRetObject(vSEL78);
            if( dobj.getRetObject("SEL78").getRecord().getInt("CNT") > 0)
            {
                dobj.setRetmsg("1022");
            }
            else if(dobj.getRetmsg ( ).equals(""))
            {
                VOBJ vSEL74 = PlanMgrdao.ExcelNoChecking_SEL74(dobj);        //  Duplicated Flag I
                dobj.setRetObject(vSEL74);
                if( dobj.getRetObject("SEL74").getRecord().getInt("CNT") > 0)
                {
                    VOBJ vXIUD114 = PlanMgrdao.ExcelNoChecking_XIUD114(dobj);        //  Merge Update ERR_MSG
                    dobj.setRetObject(vXIUD114);
                    dobj.setRetmsg("1014");
                }
                else if(dobj.getRetmsg ( ).equals("") && dobj.getRetObject("SEL74").getRecord().getInt("CNT") == 0)
                {
                    VOBJ vXIUD116 = PlanMgrdao.ExcelNoChecking_XIUD116(dobj);        //  UNI  HP2D001W
                    dobj.setRetObject(vXIUD116);
                    VOBJ vXIUD118 = PlanMgrdao.ExcelNoChecking_XIUD118(dobj);        //  Delete HP2D001T
                    dobj.setRetObject(vXIUD118);
                }
                else
                {
                }
            }
        }
        return dobj;
    }
    public DOBJ initCombo(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vSEL3 = PlanMgrdao.initCombo_SEL3(dobj);        //  CompanyList
        dobj.setRetObject(vSEL3);
        VOBJ vMRG1 = PlanMgrdao.initCombo_MRG1(dobj);        //  CompanyCombo
        dobj.setRetObject(vMRG1);
        VOBJ vSEL5 = PlanMgrdao.initCombo_SEL5(dobj);        //  DATA_TYPE
        dobj.setRetObject(vSEL5);
        VOBJ vSEL6 = PlanMgrdao.initCombo_SEL6(dobj);        //  HPMS_GRP
        dobj.setRetObject(vSEL6);
        return dobj;
    }
    public DOBJ errorConfirm(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vSEL1 = PlanMgrdao.errorConfirm_SEL1(dobj);        //  get Cost Data
        dobj.setRetObject(vSEL1);
        VOBJ vSEL5 = PlanMgrdao.errorConfirm_SEL5(dobj);        //  FILE_NAME_Info
        dobj.setRetObject(vSEL5);
        VOBJ vPEX5 = PlanMgrdao.errorConfirm_PEX5(dobj);        //  ROOTPATH
        dobj.setRetObject(vPEX5);
        VOBJ vFBD4 = PlanMgrdao.errorConfirm_FBD4(dobj);        //  CSV File Build
        dobj.setRetObject(vFBD4);
        VOBJ vDN01 = PlanMgrdao.errorConfirm_DN01(dobj);        //  downfile seting
        dobj.setRetObject(vDN01);
        return dobj;
    }
    public DOBJ loadpage(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        return dobj;
    }
}