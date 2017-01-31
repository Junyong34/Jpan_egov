
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
import hpms.common.service.MainMenu;
@Service("MainMenu20160412101097Service")
/**
*/
public class MainMenuImpl extends AbstractServiceImpl implements MainMenu
{
    @Autowired
    private MainMenuDao MainMenudao;
    /**
    SELECT X.MENU_ID
    , X.MENU_NM
    , X.IMAGE_PATH
    , X.URL
    , X.AUTH
    , X.DISP_SEQ
    , X.P_MENU_ID
    FROM HP1C002T X
    WHERE SUBSTR(AUTH, NVL(SUBSTR(:USER_AUTH,1,1),1), 1) = '1'
    CONNECT BY PRIOR X.MENU_ID = X.P_MENU_ID
    START WITH X.P_MENU_ID = '*'
    ORDER SIBLINGS BY X.DISP_SEQ;
    */
    public DOBJ loadPage(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vSEL1 = MainMenudao.loadPage_SEL1(dobj);        
        dobj.setRetObject(vSEL1);
        return dobj;
    }
    public DOBJ nullPage(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        return dobj;
    }
}