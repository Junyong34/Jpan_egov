
package hpms.batch.Impl;
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
import hpms.batch.dao.*;
import hpms.batch.service.BatchMain;
@Service("BatchMain20161017163962Service")
/**
*/
public class BatchMainImpl extends AbstractServiceImpl implements BatchMain
{
    @Autowired
    private BatchMainDao BatchMaindao;
    public DOBJ Day_Main(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vSEL2 = BatchMaindao.Day_Main_SEL2(dobj);        //  Batch State Check
        dobj.setRetObject(vSEL2);
        if( dobj.getRetObject("SEL2").getRecord().getInt("CNT") == 1)
        {
            VOBJ vbatch = BatchMaindao.Day_Main_batch(dobj);        //  Param
            dobj.setRetObject(vbatch);
            VOBJ vPEX5 = BatchMaindao.Day_Main_PEX5(dobj);        //  ExcelUpload Data
            dobj.setRetObject(vPEX5);
        }
        return dobj;
    }
    public DOBJ Night_Main(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vSEL2 = BatchMaindao.Night_Main_SEL2(dobj);        //  Batch State Check
        dobj.setRetObject(vSEL2);
        if( dobj.getRetObject("SEL2").getRecord().getInt("CNT") == 1)
        {
            VOBJ vPEX5 = BatchMaindao.Night_Main_PEX5(dobj);        //  ExcelUpload Data
            dobj.setRetObject(vPEX5);
        }
        return dobj;
    }
}