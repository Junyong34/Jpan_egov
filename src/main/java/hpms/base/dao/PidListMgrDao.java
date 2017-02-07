
package hpms.base.dao;
import java.util.*;
import egov.wizware.com.*;
import egov.wizware.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import com.ibatis.sqlmap.client.SqlMapClient;
import javax.annotation.Resource;
@Repository("PidListMgr20160902161145Dao")
public class PidListMgrDao extends EgovAbstractDAO
{
    // PID Info Search
    public VOBJ ServiceITest_SEL7(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL7", "PID Info Search" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("PidListMgr_20160902161145.ServiceITest_SEL7", param);
        dvobj.setName("SEL7");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        dvobj.Println("SEL7");
        return dvobj;
    }
    // ㅇㅁㄴ
    public VOBJ ServiceITest_INS3(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"INS3", "ㅇㅁㄴ" );
        VOBJ dvobj = dobj.getRetObject("SEL7");           //PID Info Search Input Object(CALLServiceITest_SEL7)
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap    param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("NICKNAME_EXCL", dvobj.getRecord().get("NICKNAME_EXCL"));   //Nickname(社外用)
            param.put("OWNER_ORG_CD", dvobj.getRecord().get("OWNER_ORG_CD"));   //登？部門
            param.put("ITEM_NAME", dvobj.getRecord().get("ITEM_NAME"));   //ITEM명
            param.put("UPDATE_USER_ID", dvobj.getRecord().get("UPDATE_USER_ID"));   //갱신자ID
            param.put("REGIST_USER_ID", dvobj.getRecord().get("REGIST_USER_ID"));   //작성자ID
            param.put("RD_CATEGORY_CD", dvobj.getRecord().get("RD_CATEGORY_CD"));   //RND Category CD
            param.put("REGIST_TIME", dvobj.getRecord().get("REGIST_TIME"));   //작성일시
            param.put("RD_CATEGORY_TYPE", dvobj.getRecord().get("RD_CATEGORY_TYPE"));   //RD Category Type
            param.put("NICKNAME", dvobj.getRecord().get("NICKNAME"));   //Nickname공개용
            param.put("UPDATE_TIME", dvobj.getRecord().get("UPDATE_TIME"));   //갱신일시
            param.put("RD_THEME", dvobj.getRecord().get("RD_THEME"));   //RND Theme
            param.put("APPLICATION", dvobj.getRecord().get("APPLICATION"));   //用途
            param.put("PID_STATUS_CD", dvobj.getRecord().get("PID_STATUS_CD"));   //PID상태CD
            param.put("REGIST_COMPANY_CD", dvobj.getRecord().get("REGIST_COMPANY_CD"));   //登？部門の？社CD
            param.put("PID", dvobj.getRecord().get("PID"));   //PID
            param.put("OWNER_COMPANY_CD", dvobj.getRecord().get("OWNER_COMPANY_CD"));   //オ？ナ？部門(利用部門)の？社CD
            param.put("REGIST_ORG_CD", dvobj.getRecord().get("REGIST_ORG_CD"));   //オ？ナ？部門(利用部門)
            insert("PidListMgr_20160902161145.ServiceITest_INS3",param);
            updcnt++;
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("INS3") ;
        return rvobj;
    }
    // 첫번째
    public VOBJ ServiceITest_UPD2(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"UPD2", "첫번째" );
        VOBJ dvobj = dobj.getRetObject("SEL7");           //PID Info Search Input Object(CALLServiceITest_SEL7)
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap    param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("CD", "MGRSUM");   //코드
            param.put("CD_TYPE", "BATCH_TIME");   //CD_TYPE
            param.put("VAL3", "A");   //VAL3
            updcnt += update("PidListMgr_20160902161145.ServiceITest_UPD2",param);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("UPD2") ;
        return rvobj;
    }
    // 두번째
    public VOBJ ServiceITest_UPD4(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"UPD4", "두번째" );
        VOBJ dvobj = dobj.getRetObject("SEL7");           //PID Info Search Input Object(CALLServiceITest_SEL7)
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap    param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("CD", "MGRSUM");   //코드
            param.put("CD_TYPE", "BATCH_TIME");   //CD_TYPE
            param.put("VAL4", "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");   //VAL4
            updcnt += update("PidListMgr_20160902161145.ServiceITest_UPD4",param);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("UPD4") ;
        return rvobj;
    }
    // 세번
    public VOBJ ServiceITest_UPD6(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"UPD6", "세번" );
        VOBJ dvobj = dobj.getRetObject("SEL7");           //PID Info Search Input Object(CALLServiceITest_SEL7)
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap    param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("CD", "MGRSUM");   //코드
            param.put("CD_TYPE", "BATCH_TIME");   //CD_TYPE
            param.put("VAL5", "C");   //VAL5
            updcnt += update("PidListMgr_20160902161145.ServiceITest_UPD6",param);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("UPD6") ;
        return rvobj;
    }
    // PID Info Search
    public VOBJ PIDListinfo_SEL2(DOBJ dobj) throws Exception
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
        param.put("SUB_PID", dobj.getRetObject("S").getRecord().get("SUB_PID"));   //SUB PID
        param.put("NICKNAME", dobj.getRetObject("S").getRecord().get("NICKNAME"));   //Nickname공개용
        List rlist = list("PidListMgr_20160902161145.PIDListinfo_SEL2", param);
        dvobj.setName("SEL2");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        dvobj.Println("SEL2");
        return dvobj;
    }
}