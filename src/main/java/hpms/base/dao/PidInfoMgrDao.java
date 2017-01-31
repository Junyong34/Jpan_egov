
package hpms.base.dao;
import java.util.*;
import egov.wizware.com.*;
import egov.wizware.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import com.ibatis.sqlmap.client.SqlMapClient;
import javax.annotation.Resource;
@Repository("PidInfoMgr2016082417883Dao")
public class PidInfoMgrDao extends EgovAbstractDAO
{
    // PID상태 콤보
    public VOBJ MasterComboList_SEL2(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL2", "PID상태 콤보" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("COMPANY_CD", dobj.getRetObject("G").getRecord().get("OWNER_COMPANY_CD"));   //COMPANY_CD
        List rlist = list("PidInfoMgr_2016082417883.MasterComboList_SEL2", param);
        dvobj.setName("SEL2");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // PID Nubmering
    public VOBJ CompletePage_SEL3(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL3", "PID Nubmering" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("PidInfoMgr_2016082417883.CompletePage_SEL3", param);
        dvobj.setName("SEL3");
        dvobj.setRecords(rlist);
        dvobj.Println("SEL3");
        return dvobj;
    }
    // PID Create
    public VOBJ CompletePage_INS4(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"INS4", "PID Create" );
        VOBJ dvobj = dobj.getRetObject("S");           // Input Dataset Object.
        dvobj.Println("INS4");
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap    param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("REGIST_TIME", "");   //작성일시
            param.put("NICKNAME_EXCL", dvobj.getRecord().get("NICKNAME_EXCL"));   //Nickname(社外用)
            param.put("OWNER_ORG_CD", dvobj.getRecord().get("OWNER_ORG_CD"));   //登？部門
            param.put("ITEM_NAME", dvobj.getRecord().get("ITEM_NAME"));   //ITEM명
            param.put("RD_CATEGORY_CD", dvobj.getRecord().get("RD_CATEGORY_CD"));   //RND Category CD
            param.put("NICKNAME", dvobj.getRecord().get("NICKNAME"));   //Nickname공개용
            param.put("RD_THEME", dvobj.getRecord().get("RD_THEME"));   //RND Theme
            param.put("APPLICATION", dvobj.getRecord().get("APPLICATION"));   //用途
            param.put("PID_STATUS_CD", dvobj.getRecord().get("PID_STATUS_CD"));   //PID상태CD
            param.put("REGIST_ORG_CD", dvobj.getRecord().get("REGIST_ORG_CD"));   //オ？ナ？部門(利用部門)
            param.put("OWNER_COMPANY_CD", wutil.substr(dobj.getRetObject("S").getRecord().get("OWNER_COMPANY_CD"),0,3));   //オ？ナ？部門(利用部門)の？社CD
            param.put("PID", dobj.getRetObject("SEL3").getRecord().get("PID"));   //PID
            String  RD_CATEGORY_TYPE="" ;          //RD Category Type
            if(dobj.getRetObject("S").getRecord().get("RD_CATEGORY_CD").equals("RD31"))
            {
                RD_CATEGORY_TYPE = "C";
            }
            else if(dobj.getRetObject("S").getRecord().get("RD_CATEGORY_CD").equals("RD51"))
            {
                RD_CATEGORY_TYPE = "B";
            }
            else if(dobj.getRetObject("S").getRecord().get("RD_CATEGORY_CD").equals("RD71"))
            {
                RD_CATEGORY_TYPE = "S";
            }
            else if(dobj.getRetObject("S").getRecord().get("RD_CATEGORY_CD").equals("RD91"))
            {
                RD_CATEGORY_TYPE = "Z";
            }
            else if(dobj.getRetObject("S").getRecord().get("RD_CATEGORY_CD").equals("RD11"))
            {
                RD_CATEGORY_TYPE = "P";
            }
            else if(dobj.getRetObject("S").getRecord().get("RD_CATEGORY_CD").equals("RD12"))
            {
                RD_CATEGORY_TYPE = "P";
            }
            else if(dobj.getRetObject("S").getRecord().get("RD_CATEGORY_CD").equals("RD13"))
            {
                RD_CATEGORY_TYPE = "P";
            }
            else
            {
                RD_CATEGORY_TYPE = "";
            }
            param.put("RD_CATEGORY_TYPE", RD_CATEGORY_TYPE);   //RD Category Type
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
            insert("PidInfoMgr_2016082417883.CompletePage_INS4",param);
            updcnt++;
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("INS4") ;
        rvobj.Println("INS4");
        return rvobj;
    }
    // PID Search
    public VOBJ CompletePage_SEL4(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL4", "PID Search" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("PID", dobj.getRetObject("SEL3").getRecord().get("PID"));   //PID
        List rlist = list("PidInfoMgr_2016082417883.CompletePage_SEL4", param);
        dvobj.setName("SEL4");
        dvobj.setRecords(rlist);
        dvobj.Println("SEL4");
        return dvobj;
    }
    // PID Search
    public VOBJ Result_loadPage_SEL5(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL5", "PID Search" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("PID", dobj.getRetObject("S1").getRecord().get("PID"));   //PID
        List rlist = list("PidInfoMgr_2016082417883.Result_loadPage_SEL5", param);
        dvobj.setName("SEL5");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        dvobj.Println("SEL5");
        return dvobj;
    }
}