
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
import hpms.base.service.PidInfoMgr;
@Service("PidInfoMgr2016082417883Service")
/**
*/
public class PidInfoMgrImpl extends AbstractServiceImpl implements PidInfoMgr
{
    @Autowired
    private PidInfoMgrDao PidInfoMgrdao;
    public DOBJ MasterComboList(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vSEL2 = PidInfoMgrdao.MasterComboList_SEL2(dobj);        //  PID STATUS
        dobj.setRetObject(vSEL2);
        return dobj;
    }
    public DOBJ CompletePage(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vSEL3 = PidInfoMgrdao.CompletePage_SEL3(dobj);        //  PID Nubmering
        dobj.setRetObject(vSEL3);
        if( dobj.getRetObject("SEL3").getRecord().getInt("CNT") < 90000)
        {
            VOBJ vINS4 = PidInfoMgrdao.CompletePage_INS4(dobj);        //  PID Create
            dobj.setRetObject(vINS4);
            VOBJ vSEL4 = PidInfoMgrdao.CompletePage_SEL4(dobj);        //  PID Search
            dobj.setRetObject(vSEL4);
        }
        else
        {
            dobj.setRetmsg("00009");
        }
        return dobj;
    }
    public DOBJ Result_loadPage(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vSEL5 = PidInfoMgrdao.Result_loadPage_SEL5(dobj);        //  PID Search
        dobj.setRetObject(vSEL5);
        return dobj;
    }
    public DOBJ loadpage(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        return dobj;
    }
}