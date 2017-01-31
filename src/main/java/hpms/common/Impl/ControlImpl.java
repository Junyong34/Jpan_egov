
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
import hpms.common.service.Control;
@Service("Control20161004133312Service")
/**
*/
public class ControlImpl extends AbstractServiceImpl implements Control
{
    @Autowired
    private ControlDao Controldao;
    public DOBJ SearchUsingCheck(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vSEL10 = Controldao.SearchUsingCheck_SEL10(dobj);        //  LOCK_FileInfo
        dobj.setRetObject(vSEL10);
        VOBJ vFN01 = Controldao.SearchUsingCheck_FN01(dobj);        //  isFile
        dobj.setRetObject(vFN01);
        if(dobj.getRetObject("FN01").getRecord().get("ISEXIST_FILE").equals("true"))
        {
            dobj.setRetmsg("0002");
        }
        return dobj;
    }
    public DOBJ MonthlyUsingCheck(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vSEL10 = Controldao.MonthlyUsingCheck_SEL10(dobj);        //  LOCK_FileInfo
        dobj.setRetObject(vSEL10);
        VOBJ vFN01 = Controldao.MonthlyUsingCheck_FN01(dobj);        //  isFile
        dobj.setRetObject(vFN01);
        if(dobj.getRetObject("FN01").getRecord().get("ISEXIST_FILE").equals("true"))
        {
            dobj.setRetmsg("0002");
        }
        else
        {
            VOBJ vSEL4 = Controldao.MonthlyUsingCheck_SEL4(dobj);        //  Month Stop Check
            dobj.setRetObject(vSEL4);
            if( dobj.getRetObject("SEL4").getRecord().getInt("CNT") == 0)
            {
                dobj.setRetmsg("0005");
            }
        }
        return dobj;
    }
    public DOBJ saveUsingCheck(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vSEL10 = Controldao.saveUsingCheck_SEL10(dobj);        //  LOCK_FileInfo
        dobj.setRetObject(vSEL10);
        VOBJ vFN01 = Controldao.saveUsingCheck_FN01(dobj);        //  isFile
        dobj.setRetObject(vFN01);
        if(dobj.getRetObject("FN01").getRecord().get("ISEXIST_FILE").equals("true"))
        {
            dobj.setRetmsg("0002");
        }
        return dobj;
    }
}