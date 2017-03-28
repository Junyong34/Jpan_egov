
package hpms.base.dao;
import java.util.*;
import egov.wizware.com.*;
import egov.wizware.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import com.ibatis.sqlmap.client.SqlMapClient;
import javax.annotation.Resource;
@Repository("SubPidInfoMgr20160912111171Dao")
public class SubPidInfoMgrDao extends EgovAbstractDAO
{
    // SubPid Numbering
    public VOBJ subPIDResult_SEL3(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL3", "SubPid Numbering" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("SubPidInfoMgr_20160912111171.subPIDResult_SEL3", param);
        dvobj.setName("SEL3");
        dvobj.setRecords(rlist);
        dvobj.Println("SEL3");
        return dvobj;
    }
    // Sub_PID Create
    public VOBJ subPIDResult_INS4(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"INS4", "Sub_PID Create" );
        VOBJ dvobj = dobj.getRetObject("S");           // Input Dataset Object.
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap    param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("REGIST_TIME", "");   //작성일시
            param.put("PID", dvobj.getRecord().get("PID"));   //PID
            param.put("REGIST_USER_ID", dobj.getRetObject("G").getRecord().get("USER_ID"));   //작성자ID
            param.put("SUB_PID", dobj.getRetObject("SEL3").getRecord().get("SUB_PID"));   //SUB PID
            insert("SubPidInfoMgr_20160912111171.subPIDResult_INS4",param);
            updcnt++;
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("INS4") ;
        return rvobj;
    }
    // Syb_PID Create
    public VOBJ subPIDResult_INS9(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"INS9", "Syb_PID Create" );
        VOBJ dvobj = dobj.getRetObject("S");           // Input Dataset Object.
        dvobj.Println("INS9");
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap    param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("REGIST_TIME", "");   //작성일시
            param.put("APPLICATION", dvobj.getRecord().get("APPLICATION"));   //用途
            param.put("PID_STATUS_CD", dvobj.getRecord().get("PID_STATUS_CD"));   //PID상태CD
            param.put("REGIST_ORG_CD", dvobj.getRecord().get("REGIST_ORG_CD"));   //オ？ナ？部門(利用部門)
            param.put("OWNER_COMPANY_CD", wutil.substr(dobj.getRetObject("S").getRecord().get("OWNER_COMPANY_CD"),0,3));   //オ？ナ？部門(利用部門)の？社CD
            String  OWNER_ORG_CD="" ;          //登？部門
            if(dobj.getRetObject("S").getRecord().get("OWNER_ORG_CD").equals("Z")) 
            {
            
                OWNER_ORG_CD = "";
                
            }
             else 
            {
            
                OWNER_ORG_CD = dobj.getRetObject("S").getRecord().get("OWNER_ORG_CD");
            }
            param.put("OWNER_ORG_CD", OWNER_ORG_CD);   //登？部門
            param.put("PID", dobj.getRetObject("SEL3").getRecord().get("SUB_PID"));   //PID
            String  REGIST_COMPANY_CD="" ;          //登？部門の？社CD
            if(!dobj.getRetObject("S").getRecord().get("REGIST_COMPANY_CD").equals(""))
            {
               REGIST_COMPANY_CD = wutil.substr(dobj.getRetObject("S").getRecord().get("REGIST_COMPANY_CD"),0,3);
                
            }
            else 
            {
               REGIST_COMPANY_CD = "";
                
            }
              param.put("REGIST_COMPANY_CD", REGIST_COMPANY_CD);   //登？部門の？社CD
            param.put("REGIST_USER_ID", dobj.getRetObject("G").getRecord().get("USER_ID"));   //작성자ID
            insert("SubPidInfoMgr_20160912111171.subPIDResult_INS9",param);
            updcnt++;
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("INS9") ;
        rvobj.Println("INS9");
        return rvobj;
    }
    // subPID Search
    public VOBJ subPIDResult_SEL2(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL2", "subPID Search" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("PID", dobj.getRetObject("S").getRecord().get("PID"));   //PID
        param.put("SUB_PID", dobj.getRetObject("SEL3").getRecord().get("SUB_PID"));   //SUB PID
        List rlist = list("SubPidInfoMgr_20160912111171.subPIDResult_SEL2", param);
        dvobj.setName("SEL2");
        dvobj.setRecords(rlist);
        dvobj.Println("SEL2");
        return dvobj;
    }
    // MAX MSG PID
    public VOBJ subPIDResult_SEL7(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL7", "MAX MSG PID" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("SubPidInfoMgr_20160912111171.subPIDResult_SEL7", param);
        dvobj.setName("SEL7");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // PID Info Search
    public VOBJ sub_PID_NumberSearch_SEL2(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL2", "PID Info Search" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        String  OWNER_COMPANY_CD="" ;          //オ？ナ？部門(利用部門)の？社CD
        if(dobj.getRetObject("S").getRecord().get("OWNER_COMPANY_CD").equals(""))
        {
            OWNER_COMPANY_CD = "";
        }
        else
        {
            OWNER_COMPANY_CD = wutil.substr(dobj.getRetObject("S").getRecord().get("OWNER_COMPANY_CD"),0,3);
        }
        param.put("OWNER_COMPANY_CD", OWNER_COMPANY_CD);   //オ？ナ？部門(利用部門)の？社CD
        param.put("RD_CATEGORY_CD", dobj.getRetObject("S").getRecord().get("RD_CATEGORY_CD"));   //RND Category CD
        param.put("PID", dobj.getRetObject("S").getRecord().get("PID"));   //PID
        param.put("ITEM_NAME", dobj.getRetObject("S").getRecord().get("ITEM_NAME"));   //ITEM명
        param.put("PID_STATUS_CD", dobj.getRetObject("S").getRecord().get("PID_STATUS_CD"));   //PID상태코드
        param.put("RD_THEME", dobj.getRetObject("S").getRecord().get("RD_THEME"));   //RND Theme
        param.put("OWNER_ORG_CD", dobj.getRetObject("S").getRecord().get("OWNER_ORG_CD"));   //登？部門
        param.put("NICKNAME_EXCL", dobj.getRetObject("S").getRecord().get("NICKNAME_EXCL"));   //Nickname(社外用)
        param.put("NICKNAME", dobj.getRetObject("S").getRecord().get("NICKNAME"));   //Nickname공개용
        List rlist = list("SubPidInfoMgr_20160912111171.sub_PID_NumberSearch_SEL2", param);
        dvobj.setName("SEL2");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        dvobj.Println("SEL2");
        return dvobj;
    }
    // subPID Search
    public VOBJ Result_loadPage_SEL1(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL1", "subPID Search" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("SUB_PID", dobj.getRetObject("S1").getRecord().get("SUB_PID"));   //SUB PID
        List rlist = list("SubPidInfoMgr_20160912111171.Result_loadPage_SEL1", param);
        dvobj.setName("SEL1");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        dvobj.Println("SEL1");
        return dvobj;
    }
}