
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
import hpms.base.service.PidListMgr;
@Service("PidListMgr20160902161145Service")
/**
*/
public class PidListMgrImpl extends AbstractServiceImpl implements PidListMgr
{
    @Autowired
    private PidListMgrDao PidListMgrdao;
    public DOBJ ServiceITest(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vSEL7 = PidListMgrdao.ServiceITest_SEL7(dobj);        //  PID Info Search
        dobj.setRetObject(vSEL7);
        VOBJ vINS3 = PidListMgrdao.ServiceITest_INS3(dobj);      
        dobj.setRetObject(vINS3);
        return dobj;
    }
    public DOBJ loadpage(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        return dobj;
    }
    public DOBJ PIDListinfo(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vSEL2 = PidListMgrdao.PIDListinfo_SEL2(dobj);        //  PID Info Search
        dobj.setRetObject(vSEL2);
        return dobj;
    }
}