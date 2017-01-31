
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
import hpms.common.service.CookieLoginInfo;
@Service("CookieLoginInfo2016041114474Service")
/**
*/
public class CookieLoginInfoImpl extends AbstractServiceImpl implements CookieLoginInfo
{
    @Autowired
    private CookieLoginInfoDao CookieLoginInfodao;
    public DOBJ CookieMgr(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vSEL3 = CookieLoginInfodao.CookieMgr_SEL3(dobj);        //  Login ID Check
        dobj.setRetObject(vSEL3);
        if( dobj.getRetObject("SEL3").getRecord().getInt("CNT") == 0)
        {
            dobj.setRetmsg("Please check ID");
        }
        else
        {
            VOBJ vSEL11 = CookieLoginInfodao.CookieMgr_SEL11(dobj);        //  AUTH_CHECK
            dobj.setRetObject(vSEL11);
            if( dobj.getRetObject("SEL11").getRecord().getInt("CNT") == 0)
            {
                VOBJ vSEL12 = CookieLoginInfodao.CookieMgr_SEL12(dobj);        //  AUTH_CHECK
                dobj.setRetObject(vSEL12);
                if( dobj.getRetObject("SEL12").getRecord().getInt("CNT") == 0)
                {
                    VOBJ vSEL27 = CookieLoginInfodao.CookieMgr_SEL27(dobj);        //  USER_AUTH
                    dobj.setRetObject(vSEL27);
                }
                else
                {
                    VOBJ vSEL29 = CookieLoginInfodao.CookieMgr_SEL29(dobj);        //  AUTH_ADD
                    dobj.setRetObject(vSEL29);
                    VOBJ vSEL31 = CookieLoginInfodao.CookieMgr_SEL31(dobj);        //  AUTH(10,20)Session_ADD
                    dobj.setRetObject(vSEL31);
                }
            }
            else
            {
                VOBJ vSEL17 = CookieLoginInfodao.CookieMgr_SEL17(dobj);        //  USER_AUTH
                dobj.setRetObject(vSEL17);
            }
        }
        return dobj;
    }
   
   
}