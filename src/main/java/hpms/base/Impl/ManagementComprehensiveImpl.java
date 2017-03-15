
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
import org.springframework.util.StopWatch;

import hpms.base.dao.*;
import hpms.base.service.ManagementComprehensive;
@Service("ManagementComprehensive20160901101144Service")
/**
*/
public class ManagementComprehensiveImpl extends AbstractServiceImpl implements ManagementComprehensive
{
    @Autowired
    private ManagementComprehensiveDao ManagementComprehensivedao;
    public DOBJ buildSheetDownload(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vSEL5 = ManagementComprehensivedao.buildSheetDownload_SEL5(dobj);        //  Layout_Excel
        dobj.setRetObject(vSEL5);
        
        StopWatch SEL02 = new StopWatch();
        SEL02.start();
        VOBJ vSEL02 = ManagementComprehensivedao.buildSheetDownload_SEL02(dobj);        //  Left Data
        SEL02.stop();
        System.out.println("Step 1 Management Sheet Left Search  " +SEL02.toString());
        dobj.setRetObject(vSEL02);
        VOBJ vleftColumn = ManagementComprehensivedao.buildSheetDownload_leftColumn(dobj);        //  Column info left
        dobj.setRetObject(vleftColumn);
        VOBJ vRightColumn = ManagementComprehensivedao.buildSheetDownload_RightColumn(dobj);        //  Column info right
        dobj.setRetObject(vRightColumn);
        VOBJ vDN01 = ManagementComprehensivedao.buildSheetDownload_DN01(dobj);        //  Excel Download
        dobj.setRetObject(vDN01);
        return dobj;
    }
    public DOBJ FCST_Title_ComboMgr(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vSEL1 = ManagementComprehensivedao.FCST_Title_ComboMgr_SEL1(dobj);        //  DATA_TYPE
        dobj.setRetObject(vSEL1);
        return dobj;
    }
    public DOBJ initCombo(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vSEL1 = ManagementComprehensivedao.initCombo_SEL1(dobj);        //  CompanyList
        dobj.setRetObject(vSEL1);
        VOBJ vSEL2 = ManagementComprehensivedao.initCombo_SEL2(dobj);        //  DATA_TYPE
        dobj.setRetObject(vSEL2);
        if(!dobj.getRetObject("S").getRecord().get("COMPANY_CD").equals(""))
        {
            VOBJ vSEL3 = ManagementComprehensivedao.initCombo_SEL3(dobj);        //  Organization Code
            dobj.setRetObject(vSEL3);
        }
        return dobj;
    }
    public DOBJ loadpage(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        return dobj;
    }
}