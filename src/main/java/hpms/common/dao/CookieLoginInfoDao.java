
package hpms.common.dao;
import java.util.*;

import egov.wizware.com.*;
import egov.wizware.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

import com.ibatis.sqlmap.client.SqlMapClient;

import javax.annotation.Resource;
@Repository("CookieLoginInfo2016041114474Dao")
public class CookieLoginInfoDao extends EgovAbstractDAO
{
	 // Login ID Check
    public VOBJ CookieMgr_SEL3(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL3", "Login ID Check" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("USER_ID", dobj.getRetObject("COOK").getRecord().get("USER_ID"));   //사용자ID
        System.out.println("Login ID  : " + dobj.getRetObject("COOK").getRecord().get("USER_ID"));
        List rlist = list("CookieLoginInfo_2016041114474.CookieMgr_SEL3", param);
        dvobj.setName("SEL3");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // AUTH_CHECK
    public VOBJ CookieMgr_SEL11(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL11", "AUTH_CHECK" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("USER_ID", dobj.getRetObject("COOK").getRecord().get("USER_ID"));   //사용자ID
        List rlist = list("CookieLoginInfo_2016041114474.CookieMgr_SEL11", param);
        dvobj.setName("SEL11");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // AUTH_CHECK
    public VOBJ CookieMgr_SEL12(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL12", "AUTH_CHECK" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("USER_ID", dobj.getRetObject("COOK").getRecord().get("USER_ID"));   //사용자ID
        List rlist = list("CookieLoginInfo_2016041114474.CookieMgr_SEL12", param);
        dvobj.setName("SEL12");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // USER_AUTH
    public VOBJ CookieMgr_SEL27(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL27", "USER_AUTH" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("USER_ID", dobj.getRetObject("COOK").getRecord().get("USER_ID"));   //사용자ID
        List rlist = list("CookieLoginInfo_2016041114474.CookieMgr_SEL27", param);
        dvobj.setName("SEL27");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // AUTH_ADD
    public VOBJ CookieMgr_SEL29(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL29", "AUTH_ADD" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("USER_ID", dobj.getRetObject("COOK").getRecord().get("USER_ID"));   //사용자ID
        List rlist = list("CookieLoginInfo_2016041114474.CookieMgr_SEL29", param);
        dvobj.setName("SEL29");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // AUTH(10,20)Session_ADD
    public VOBJ CookieMgr_SEL31(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL31", "AUTH(10,20)Session_ADD" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("USER_ID", dobj.getRetObject("COOK").getRecord().get("USER_ID"));   //사용자ID
        param.put("AUTH_CD", dobj.getRetObject("SEL29").getRecord().get("AUTH_CD"));   //AUTH_CD
        List rlist = list("CookieLoginInfo_2016041114474.CookieMgr_SEL31", param);
        dvobj.setName("SEL31");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // USER_AUTH
    public VOBJ CookieMgr_SEL17(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL17", "USER_AUTH" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("USER_ID", dobj.getRetObject("COOK").getRecord().get("USER_ID"));   //사용자ID
        List rlist = list("CookieLoginInfo_2016041114474.CookieMgr_SEL17", param);
        dvobj.setName("SEL17");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    
}