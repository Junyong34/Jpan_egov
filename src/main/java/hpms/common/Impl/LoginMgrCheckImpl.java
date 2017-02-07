
package hpms.common.Impl;
import java.util.*;

import egov.wizware.com.*;
import egov.wizware.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
import hpms.common.service.LoginMgrCheck;
@Service("LoginMgrCheck2016041114474Service")
/**
*/
public class LoginMgrCheckImpl extends AbstractServiceImpl implements LoginMgrCheck
{
    @Autowired
    private LoginMgrCheckDao LoginMgrCheckdao;
    public DOBJ SessionLogInfo(DOBJ dobj, HttpServletRequest request) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vSEL1 = LoginMgrCheckdao.SessionLogInfo_SEL1(dobj,  request);       
        dobj.setRetObject(vSEL1);
        return dobj;
    }
    public DOBJ wizLoginSession(DOBJ dobj, HttpServletRequest request) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vSEL3 = LoginMgrCheckdao.wizLoginSession_SEL3(dobj,request);        
        dobj.setRetObject(vSEL3);
        
         if( dobj.getRetObject("SEL3").getRecord().getInt("CNT") == 1)
        {
        
        	
        	 
            VOBJ vSEL1 = LoginMgrCheckdao.wizLoginSession_SEL1(dobj,request);       
            dobj.setRetObject(vSEL1);
        }else
        {
        	
            dobj.setRetmsg("Please check UserID Password");
        }
        return dobj;
    }
    
    
    public DOBJ System_Developer_Check(DOBJ dobj, HttpServletRequest request) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        
        
        VOBJ vSEL3 = LoginMgrCheckdao.System_Developer_Check_SEL3(dobj,request);        
        dobj.setRetObject(vSEL3);
        
      
        if( dobj.getRetObject("SEL3").getRecord().getInt("CNT") == 1)
        {
        	 
        	dobj.setRetmsg("1");
        
        }else{
        	
            dobj.setRetmsg("0");
        }
        return dobj;
    }
}