
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
import hpms.base.service.SubPidInfoMgr;
@Service("SubPidInfoMgr20160912111171Service")
/**
*/
public class SubPidInfoMgrImpl extends AbstractServiceImpl implements SubPidInfoMgr
{
    @Autowired
    private SubPidInfoMgrDao SubPidInfoMgrdao;
    public DOBJ sub_PID_NumberSearch(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vSEL2 = SubPidInfoMgrdao.sub_PID_NumberSearch_SEL2(dobj);        //  PID Info Search
        dobj.setRetObject(vSEL2);
        return dobj;
    }
    public DOBJ subPIDResult(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vSEL3 = SubPidInfoMgrdao.subPIDResult_SEL3(dobj);        //  SubPid Numbering
        dobj.setRetObject(vSEL3);
        if( dobj.getRetObject("SEL3").getRecord().getInt("CNT") < 90000)
        {
            VOBJ vINS4 = SubPidInfoMgrdao.subPIDResult_INS4(dobj);        //  Sub_PID Create
            dobj.setRetObject(vINS4);
            VOBJ vINS9 = SubPidInfoMgrdao.subPIDResult_INS9(dobj);        //  Syb_PID Create
            dobj.setRetObject(vINS9);
            VOBJ vSEL2 = SubPidInfoMgrdao.subPIDResult_SEL2(dobj);        //  subPID Search
            dobj.setRetObject(vSEL2);
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
        VOBJ vSEL1 = SubPidInfoMgrdao.Result_loadPage_SEL1(dobj);        //  subPID Search
        dobj.setRetObject(vSEL1);
        return dobj;
    }
    public DOBJ subpidreslut_page(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        return dobj;
    }
    public DOBJ SubPIDPopupPage(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        return dobj;
    }
    public DOBJ loadpage(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        return dobj;
    }
}