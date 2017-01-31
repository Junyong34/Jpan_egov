
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
import hpms.base.service.PLSheetOutput;
@Service("PLSheetOutput20160901101143Service")
/**
*/
public class PLSheetOutputImpl extends AbstractServiceImpl implements PLSheetOutput
{
    @Autowired
    private PLSheetOutputDao PLSheetOutputdao;
    public DOBJ initCombo(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vSEL2 = PLSheetOutputdao.initCombo_SEL2(dobj);        //  DATA_TYPE
        dobj.setRetObject(vSEL2);
        return dobj;
    }
    public DOBJ PIDCheck(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vSEL1 = PLSheetOutputdao.PIDCheck_SEL1(dobj);        //  get PID Count
        dobj.setRetObject(vSEL1);
        VOBJ vSEL3 = PLSheetOutputdao.PIDCheck_SEL3(dobj);        //  PID_CHECK
        dobj.setRetObject(vSEL3);
        return dobj;
    }
    public DOBJ buildPLSheetDownload(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vSEL13 = PLSheetOutputdao.buildPLSheetDownload_SEL13(dobj);        //  PL_TYPE Search
        dobj.setRetObject(vSEL13);
        if(dobj.getRetObject("SEL13").getRecord().get("PL_TYPE").equals("PL_S.xlsx"))
        {
            VOBJ vSEL05 = PLSheetOutputdao.buildPLSheetDownload_SEL05(dobj);        //  DCP_Info
            dobj.setRetObject(vSEL05);
            VOBJ vSEL10 = PLSheetOutputdao.buildPLSheetDownload_SEL10(dobj);        //  PL-DATA
            dobj.setRetObject(vSEL10);
            VOBJ vMRG01 = PLSheetOutputdao.buildPLSheetDownload_MRG01(dobj);        //  Final DCP Info
            dobj.setRetObject(vMRG01);
            VOBJ vMRG02 = PLSheetOutputdao.buildPLSheetDownload_MRG02(dobj);        //  Final PL-DATA
            dobj.setRetObject(vMRG02);
            VOBJ vleftColumn = PLSheetOutputdao.buildPLSheetDownload_leftColumn(dobj);        //  Column info left
            dobj.setRetObject(vleftColumn);
            VOBJ vRightColumn = PLSheetOutputdao.buildPLSheetDownload_RightColumn(dobj);        //  Column info right
            dobj.setRetObject(vRightColumn);
            VOBJ vDN01 = PLSheetOutputdao.buildPLSheetDownload_DN01(dobj);        //  Excel Download
            dobj.setRetObject(vDN01);
        }
        else if(dobj.getRetObject("SEL13").getRecord().get("PL_TYPE").equals("PL_Z.xlsx"))
        {
            VOBJ vSEL04 = PLSheetOutputdao.buildPLSheetDownload_SEL04(dobj);        //  DCP_Info
            dobj.setRetObject(vSEL04);
            VOBJ vSEL09 = PLSheetOutputdao.buildPLSheetDownload_SEL09(dobj);        //  PL-DATA
            dobj.setRetObject(vSEL09);
            VOBJ vMRG01 = PLSheetOutputdao.buildPLSheetDownload_MRG01(dobj);        //  Final DCP Info
            dobj.setRetObject(vMRG01);
            VOBJ vMRG02 = PLSheetOutputdao.buildPLSheetDownload_MRG02(dobj);        //  Final PL-DATA
            dobj.setRetObject(vMRG02);
            VOBJ vleftColumn = PLSheetOutputdao.buildPLSheetDownload_leftColumn(dobj);        //  Column info left
            dobj.setRetObject(vleftColumn);
            VOBJ vRightColumn = PLSheetOutputdao.buildPLSheetDownload_RightColumn(dobj);        //  Column info right
            dobj.setRetObject(vRightColumn);
            VOBJ vDN01 = PLSheetOutputdao.buildPLSheetDownload_DN01(dobj);        //  Excel Download
            dobj.setRetObject(vDN01);
        }
        else if(dobj.getRetObject("SEL13").getRecord().get("PL_TYPE").equals("PL_B.xlsx"))
        {
            VOBJ vSEL03 = PLSheetOutputdao.buildPLSheetDownload_SEL03(dobj);        //  DCP_Info
            dobj.setRetObject(vSEL03);
            VOBJ vSEL08 = PLSheetOutputdao.buildPLSheetDownload_SEL08(dobj);        //  PL-DATA
            dobj.setRetObject(vSEL08);
            VOBJ vMRG01 = PLSheetOutputdao.buildPLSheetDownload_MRG01(dobj);        //  Final DCP Info
            dobj.setRetObject(vMRG01);
            VOBJ vMRG02 = PLSheetOutputdao.buildPLSheetDownload_MRG02(dobj);        //  Final PL-DATA
            dobj.setRetObject(vMRG02);
            VOBJ vleftColumn = PLSheetOutputdao.buildPLSheetDownload_leftColumn(dobj);        //  Column info left
            dobj.setRetObject(vleftColumn);
            VOBJ vRightColumn = PLSheetOutputdao.buildPLSheetDownload_RightColumn(dobj);        //  Column info right
            dobj.setRetObject(vRightColumn);
            VOBJ vDN01 = PLSheetOutputdao.buildPLSheetDownload_DN01(dobj);        //  Excel Download
            dobj.setRetObject(vDN01);
        }
        else if(dobj.getRetObject("SEL13").getRecord().get("PL_TYPE").equals("PL_C.xlsx"))
        {
            VOBJ vSEL02 = PLSheetOutputdao.buildPLSheetDownload_SEL02(dobj);        //  DCP_Info
            dobj.setRetObject(vSEL02);
            VOBJ vSEL07 = PLSheetOutputdao.buildPLSheetDownload_SEL07(dobj);        //  PL-DATA
            dobj.setRetObject(vSEL07);
            VOBJ vMRG01 = PLSheetOutputdao.buildPLSheetDownload_MRG01(dobj);        //  Final DCP Info
            dobj.setRetObject(vMRG01);
            VOBJ vMRG02 = PLSheetOutputdao.buildPLSheetDownload_MRG02(dobj);        //  Final PL-DATA
            dobj.setRetObject(vMRG02);
            VOBJ vleftColumn = PLSheetOutputdao.buildPLSheetDownload_leftColumn(dobj);        //  Column info left
            dobj.setRetObject(vleftColumn);
            VOBJ vRightColumn = PLSheetOutputdao.buildPLSheetDownload_RightColumn(dobj);        //  Column info right
            dobj.setRetObject(vRightColumn);
            VOBJ vDN01 = PLSheetOutputdao.buildPLSheetDownload_DN01(dobj);        //  Excel Download
            dobj.setRetObject(vDN01);
        }
        else if(dobj.getRetObject("SEL13").getRecord().get("PL_TYPE").equals("PL_P.xlsx"))
        {
            VOBJ vSEL01 = PLSheetOutputdao.buildPLSheetDownload_SEL01(dobj);        //  DCP_Info
            dobj.setRetObject(vSEL01);
            VOBJ vSEL06 = PLSheetOutputdao.buildPLSheetDownload_SEL06(dobj);        //  PL-DATA
            dobj.setRetObject(vSEL06);
            VOBJ vMRG01 = PLSheetOutputdao.buildPLSheetDownload_MRG01(dobj);        //  Final DCP Info
            dobj.setRetObject(vMRG01);
            VOBJ vMRG02 = PLSheetOutputdao.buildPLSheetDownload_MRG02(dobj);        //  Final PL-DATA
            dobj.setRetObject(vMRG02);
            VOBJ vleftColumn = PLSheetOutputdao.buildPLSheetDownload_leftColumn(dobj);        //  Column info left
            dobj.setRetObject(vleftColumn);
            VOBJ vRightColumn = PLSheetOutputdao.buildPLSheetDownload_RightColumn(dobj);        //  Column info right
            dobj.setRetObject(vRightColumn);
            VOBJ vDN01 = PLSheetOutputdao.buildPLSheetDownload_DN01(dobj);        //  Excel Download
            dobj.setRetObject(vDN01);
        }
        return dobj;
    }
    public DOBJ ServiceID01(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vSEL5 = PLSheetOutputdao.ServiceID01_SEL5(dobj);        //  get PID Count
        dobj.setRetObject(vSEL5);
        if( dobj.getRetObject("SEL5").getRecord().getInt("CNT") > 0)
        {
            VOBJ vSEL11 = PLSheetOutputdao.ServiceID01_SEL11(dobj);        //  DCP_TYPE
            dobj.setRetObject(vSEL11);
            VOBJ vSEL13 = PLSheetOutputdao.ServiceID01_SEL13(dobj);        //  Approval YYYYMM
            dobj.setRetObject(vSEL13);
            VOBJ vSEL01 = PLSheetOutputdao.ServiceID01_SEL01(dobj);        //  PID Head Info
            dobj.setRetObject(vSEL01);
            VOBJ vSEL02 = PLSheetOutputdao.ServiceID01_SEL02(dobj);        //  PL-DATA
            dobj.setRetObject(vSEL02);
            VOBJ vDN01 = PLSheetOutputdao.ServiceID01_DN01(dobj);        //  Excel Download
            dobj.setRetObject(vDN01);
        }
        else
        {
            dobj.setRetmsg("1000");
        }
        return dobj;
    }
    public DOBJ ServiceID00(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vSEL2 = PLSheetOutputdao.ServiceID00_SEL2(dobj);        //  PID/COMPANY
        dobj.setRetObject(vSEL2);
        VOBJ vSEL3 = PLSheetOutputdao.ServiceID00_SEL3(dobj);        //  COMPANY
        dobj.setRetObject(vSEL3);
        return dobj;
    }
    public DOBJ loadpage(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        return dobj;
    }
}