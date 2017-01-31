
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
import hpms.base.service.DetailDataExp;
/**
*/
@Service("DetailDataExp20160901091139Service")
public class DetailDataExpImpl extends AbstractServiceImpl implements DetailDataExp
{
    @Autowired
    private DetailDataExpDao DetailDataExpdao;
    public DOBJ ExcelDownload(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vEXDN = DetailDataExpdao.ExcelDownload_EXDN(dobj);        //  ForeCast download
        dobj.setRetObject(vEXDN);
        VOBJ vDN01 = DetailDataExpdao.ExcelDownload_DN01(dobj);        //  Excel Download
        dobj.setRetObject(vDN01);
        return dobj;
    }
}