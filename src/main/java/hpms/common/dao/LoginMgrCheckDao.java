
package hpms.common.dao;
import java.util.*;

import egov.wizware.com.*;
import egov.wizware.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

import com.ibatis.sqlmap.client.SqlMapClient;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
@Repository("LoginMgrCheck2016041114474Dao")
public class LoginMgrCheckDao extends EgovAbstractDAO
{
    // 사용자 조회
    public VOBJ SessionLogInfo_SEL1(DOBJ dobj, HttpServletRequest request) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL1", "사용자 조회" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("USER_ID", request.getParameter("UserID"));   //ID
        param.put("USER_PW", request.getParameter("PassWD"));   //pw
        List rlist = list("LoginMgrCheck_2016041114474.SessionLogInfo_SEL1", param);
        dvobj.setName("SEL1");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        dvobj.Println("SEL1");
        return dvobj;
    }
    // 로그인체크
    public VOBJ wizLoginSession_SEL3(DOBJ dobj, HttpServletRequest request) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL3", "로그인체크" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("USER_ID", request.getParameter("UserID"));   //ID
        param.put("USER_PW", request.getParameter("PassWD"));   //pw
        List rlist = list("LoginMgrCheck_2016041114474.wizLoginSession_SEL3", param);
        dvobj.setName("SEL3");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        dvobj.Println("SEL3");
        return dvobj;
    }
    // 사용자 조회
    public VOBJ wizLoginSession_SEL1(DOBJ dobj, HttpServletRequest request) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL1", "사용자 조회" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("USER_ID", request.getParameter("UserID"));   //ID
        param.put("USER_PW", request.getParameter("PassWD"));   //pw
        List rlist = list("LoginMgrCheck_2016041114474.wizLoginSession_SEL1", param);
        dvobj.setName("SEL1");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        dvobj.Println("SEL1");
        return dvobj;
    }
   // 누가 로그인 했는지 체
    public VOBJ System_Developer_Check_SEL3(DOBJ dobj, HttpServletRequest request) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL3", "사용자 조회" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("USER_ID", request.getParameter("UserID"));   //ID
        List rlist = list("LoginMgrCheck_2016041114474.System_Developer_Check_SEL3", param);
        dvobj.setName("SEL3");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        dvobj.Println("SEL3");
        return dvobj;
    }
 
   
}