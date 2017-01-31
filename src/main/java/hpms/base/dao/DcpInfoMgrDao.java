
package hpms.base.dao;
import java.util.*;
import egov.wizware.com.*;
import egov.wizware.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import com.ibatis.sqlmap.client.SqlMapClient;
import javax.annotation.Resource;
@Repository("DcpInfoMgr2016082417884Dao")
public class DcpInfoMgrDao extends EgovAbstractDAO
{
    // ROOTPATH
    public VOBJ BLOB_DCPFileDown_PEX3(DOBJ dobj) throws Exception
    {
        CommUtil cu = new CommUtil(dobj);
        HashMap   classinfo = new HashMap();
        classinfo.put("OBJECTID", "PEX3" );
        classinfo.put("PACKAGE", "hpms.UserObject.BatchExe" );
        classinfo.put("CLASS", "FileUtility" );
        classinfo.put("METHOD", "rootPath" );
        dobj = cu.callPSExternal(dobj, null, classinfo );
        VOBJ dvobj = dobj.getRetObject("FN02");
        dvobj.setName("PEX3");
        return dvobj;
    }
    // Fileinfo
    public VOBJ BLOB_DCPFileDown_SEL8(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL8", "Fileinfo" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("PID", dobj.getRetObject("S").getRecord().get("PID"));   //PID
        param.put("DCP_TYPE", dobj.getRetObject("S").getRecord().get("DCP_TYPE"));   //DCP종별코드
        param.put("SEQ", dobj.getRetObject("S").getRecord().getInt("SEQ")+"");   //일렬번호
        param.put("FILE_PATH", dobj.getRetObject("PEX3").getRecord().get("ROOTPATH")+"/uploadfile");   //파일path
        List rlist = list("DcpInfoMgr_2016082417884.BLOB_DCPFileDown_SEL8", param);
        dvobj.setName("SEL8");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // Fileinfo
    public VOBJ BLOB_DCPFileDown_SEL2(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL2", "Fileinfo" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("PID", dobj.getRetObject("S").getRecord().get("PID"));   //PID
        param.put("DCP_TYPE", dobj.getRetObject("S").getRecord().get("DCP_TYPE"));   //DCP종별코드
        param.put("SEQ", dobj.getRetObject("S").getRecord().getInt("SEQ")+"");   //일렬번호
        param.put("FILE_PATH", dobj.getRetObject("PEX3").getRecord().get("ROOTPATH")+"uploadfile");   //파일path
        List rlist = list("DcpInfoMgr_2016082417884.BLOB_DCPFileDown_SEL2", param);
        dvobj.setName("SEL2");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        String fullname="";
        dvobj.first();
        while(dvobj.next()) 
        {
        
            fullname= wutil.makeFileOverwirte( dobj.getRetObject("SEL8").getRecord().get("FILE_PATH"), dobj.getRetObject("SEL8").getRecord().get("FILE_NAME"),dvobj.getRecord().getBytes("FILE_BIN"));
            dvobj.getRecord().put("FILE_BIN",fullname);
        }
        return dvobj;
    }
    // downfile seting
    public VOBJ BLOB_DCPFileDown_DN01(DOBJ dobj) throws Exception
    {
        dobj.setRtnname("DN01");
        WizUtil wutil = new WizUtil(dobj,"DN01", "downfile seting" );
        VOBJ dvobj = dobj.getRetObjectCopy("SEL8");        //Fileinfo Input Object(CALLBLOB_DCPFileDown_SEL8)
        String[] outcolumns = 
        {
        "FILE_PATH", "FILE_UNNAME", "FILE_NAME"
        }
        ;;
        HashMap    record =null;
        dvobj.first();
        while(dvobj.next())
        {
            record = dvobj.getRecordMap();
            String   FILE_NAME = dvobj.getRecord().get("FILE_NAME");         //파일이름
            record.put("FILE_NAME",FILE_NAME);
            String   FILE_PATH = dvobj.getRecord().get("FILE_PATH");         //파일path
            record.put("FILE_PATH",FILE_PATH);
            String   FILE_UNNAME = dvobj.getRecord().get("FILE_NAME");         //유니크파일
            record.put("FILE_UNNAME",FILE_UNNAME);
            boolean isfind=false;
            ArrayList alist = new ArrayList();
            Iterator itor = record.keySet().iterator();
            while(itor.hasNext())
            {
            
                alist.add(itor.next().toString());
            }
            for(int i=0;i<alist.size();i++)
            {
            
                isfind = false;
                for(int j=0;j<outcolumns.length;j++)
                {
                
                    if(alist.get(i).equals(outcolumns[j])) 
                    {
                    
                        isfind=true;
                    }
                }
                if(isfind == false)
                {
                
                    record.remove(alist.get(i));
                }
            }
            dvobj.setRecord(record);
        }
        dvobj.setName("DN01") ;
        return dvobj;
    }
    // FILE info
    public VOBJ BLOB_DCPFileDown_SEL6(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL6", "FILE info" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("PID", dobj.getRetObject("S").getRecord().get("PID"));   //PID
        param.put("DCP_TYPE", dobj.getRetObject("S").getRecord().get("DCP_TYPE"));   //DCP종별코드
        param.put("SEQ", dobj.getRetObject("S").getRecord().getInt("SEQ")+"");   //일렬번호
        param.put("FILE_PATH", dobj.getRetObject("PEX3").getRecord().get("ROOTPATH")+"uploadfile/");   //파일path
        List rlist = list("DcpInfoMgr_2016082417884.BLOB_DCPFileDown_SEL6", param);
        dvobj.setName("SEL6");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // PID Search
    public VOBJ DCPApproval_Flow_SEL18(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL18", "PID Search" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("PID", dobj.getRetObject("S3").getRecord().get("PID"));   //PID
        List rlist = list("DcpInfoMgr_2016082417884.DCPApproval_Flow_SEL18", param);
        dvobj.setName("SEL18");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // Approval Data Update Insert
    public VOBJ DCPApproval_Flow_UNI5(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"UNI5", "Approval Data Update Insert" );
        VOBJ dvobj = dobj.getRetObject("SEL18");           //PID Search Input Object(CALLDCPApproval_Flow_SEL18)
        dvobj.Println("UNI5");
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        HashMap    param= null;
        int        inscnt=0, updcnt =0 ,unicnt=0,  rtncnt=0;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("REGIST_TIME", "");   //작성일시
            param.put("REGIST_USER_ID", dobj.getRetObject("G").getRecord().get("USER_ID"));   //작성자ID
            param.put("UPDATE_TIME", "");   //갱신일시
            param.put("P_DCP_EXEC_YYYYMMDD", dvobj.getRecord().get("P_DCP_EXEC_YYYYMMDD"));   //P_DCP 실시일
            param.put("BTM_AMT_TP_UNIT", dvobj.getRecord().get("BTM_AMT_TP_UNIT"));   //BottomTP金額の通貨コ？ド
            param.put("SALES_AMT", dvobj.getRecord().getDouble("SALES_AMT")+"");   //라이프매출
            param.put("A_DCP_EXEC_YYYYMMDD", dvobj.getRecord().get("A_DCP_EXEC_YYYYMMDD"));   //A_DCP 실시일
            param.put("P_DCP_EXEC_CNT", dvobj.getRecord().getInt("P_DCP_EXEC_CNT")+"");   //P_DCP 실시회수
            param.put("BTM_AMT_SSP_UNIT", dvobj.getRecord().get("BTM_AMT_SSP_UNIT"));   //BottomSSP金額の通貨コ？ド
            param.put("REQUIRED_RETURN", dvobj.getRecord().getInt("REQUIRED_RETURN")+"");   //Required Return Years
            param.put("APPLICA_ORG_CD", dvobj.getRecord().get("APPLICA_ORG_CD"));   //申請部CD
            param.put("M_DCP_PLAN_YYYYMMDD", dvobj.getRecord().get("M_DCP_PLAN_YYYYMMDD"));   //M.DCP 予定日
            param.put("CLOSING_ORG_CD", dvobj.getRecord().get("CLOSING_ORG_CD"));   //決算部CD
            param.put("BTM_AMT_TP", dvobj.getRecord().getDouble("BTM_AMT_TP")+"");   //BottomTP금액
            param.put("M_DCP_EXEC_CNT", dvobj.getRecord().getInt("M_DCP_EXEC_CNT")+"");   //M_DCP 실시회수
            param.put("BTM_AMT_SSP", dvobj.getRecord().getDouble("BTM_AMT_SSP")+"");   //BottomSSP金額
            param.put("P_DCP_PLAN_YYYYMMDD", dvobj.getRecord().get("P_DCP_PLAN_YYYYMMDD"));   //P.DCP 予定日
            param.put("RESPONS_COMPANY_CD", dvobj.getRecord().get("RESPONS_COMPANY_CD"));   //responsibility？社CD
            param.put("INCOME_AMT", dvobj.getRecord().getDouble("INCOME_AMT")+"");   //영업이익
            param.put("E_DCP_PLAN_YYYYMMDD", dvobj.getRecord().get("E_DCP_PLAN_YYYYMMDD"));   //E.DCP 予定日
            param.put("E_DCP_EXEC_YYYYMMDD", dvobj.getRecord().get("E_DCP_EXEC_YYYYMMDD"));   //E_DCP 실시일
            param.put("RD_COST", dvobj.getRecord().getDouble("RD_COST")+"");   //총개발비
            param.put("M_DCP_EXEC_YYYYMMDD", dvobj.getRecord().get("M_DCP_EXEC_YYYYMMDD"));   //M_DCP 실시일
            param.put("E_DCP_EXEC_CNT", dvobj.getRecord().getInt("E_DCP_EXEC_CNT")+"");   //E_DCP 실시회수
            param.put("NRE_AMT", dvobj.getRecord().getDouble("NRE_AMT")+"");   //내부NRE매출
            param.put("REQUEST_AMT", dvobj.getRecord().getDouble("REQUEST_AMT")+"");   //품의발생액
            param.put("OSC_MARGIN_RATIO", dvobj.getRecord().getDouble("OSC_MARGIN_RATIO")+"");   //판매회사마진율
            param.put("BTM_AMT_ECP_UNIT", dvobj.getRecord().get("BTM_AMT_ECP_UNIT"));   //BottomECP金額の通貨コ？ド
            param.put("BTM_AMT_ECP", dvobj.getRecord().getDouble("BTM_AMT_ECP")+"");   //BottomECP金額
            param.put("PERSON_IN_CHARGE_ID", dvobj.getRecord().get("PERSON_IN_CHARGE_ID"));   //Person in charge ID
            param.put("DCP_TYPE", dvobj.getRecord().get("DCP_TYPE"));   //DCP종별코드
            param.put("A_DCP_PLAN_YYYYMMDD", dvobj.getRecord().get("A_DCP_PLAN_YYYYMMDD"));   //A.DCP 予定日
            param.put("RESPONS_ORG_CD", dvobj.getRecord().get("RESPONS_ORG_CD"));   //responsibility
            param.put("CLOSING_AMT", dvobj.getRecord().getDouble("CLOSING_AMT")+"");   //결산대상금액
            param.put("A_DCP_EXEC_CNT", dvobj.getRecord().getInt("A_DCP_EXEC_CNT")+"");   //A_DCP 실시회수
            param.put("T_DCP_PLAN_YYYYMMDD", dvobj.getRecord().get("T_DCP_PLAN_YYYYMMDD"));   //T.DCP 予定日
            param.put("T_DCP_EXEC_YYYYMMDD", dvobj.getRecord().get("T_DCP_EXEC_YYYYMMDD"));   //T_DCP 실시일
            param.put("T_DCP_EXEC_CNT", dvobj.getRecord().getInt("T_DCP_EXEC_CNT")+"");   //T_DCP 실시회수
            param.put("INCOME_AMT_RATIO", dvobj.getRecord().getDouble("INCOME_AMT_RATIO")+"");   //영업이익율
            param.put("APPLICA_COMPANY_CD", dvobj.getRecord().get("APPLICA_COMPANY_CD"));   //申請？社CD
            param.put("APPROVAL_YYYYMMDD", dobj.getRetObject("S2").getRecord().get("APPROVAL_YYYYMMDD"));   //승인일
            param.put("CLOSING_COMPANY_CD", dvobj.getRecord().get("CLOSING_COMPANY_CD"));   //決算？社CD
            param.put("ITEM_NAME", dobj.getRetObject("S3").getRecord().get("ITEM_NAME"));   //ITEM명
            param.put("NICKNAME", dobj.getRetObject("S3").getRecord().get("NICKNAME"));   //Nickname공개용
            param.put("NICKNAME_EXCL", dobj.getRetObject("S3").getRecord().get("NICKNAME_EXCL"));   //Nickname(社外用)
            param.put("PID", dvobj.getRecord().get("PID"));   //PID
            param.put("PID_STATUS_CD", dobj.getRetObject("S3").getRecord().get("PID_STATUS_CD"));   //PID상태CD
            param.put("RD_CATEGORY_CD", dobj.getRetObject("S3").getRecord().get("RD_CATEGORY_CD"));   //RND Category CD
            param.put("RD_THEME", dobj.getRetObject("S3").getRecord().get("RD_THEME"));   //RND Theme
            param.put("UPDATE_USER_ID", dobj.getRetObject("G").getRecord().get("USER_ID"));   //갱신자ID
            rtncnt= update("DcpInfoMgr_2016082417884.DCPApproval_Flow_UNI5_UPD",param);
            if(rtncnt < 1)
            {
                insert("DcpInfoMgr_2016082417884.DCPApproval_Flow_UNI5_INS",param);
                inscnt++;
                unicnt++;
            }
            else
            {
                updcnt += rtncnt;
                unicnt += rtncnt;
            }
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        rvobj.setHeadColumn("INSCNT" , "INT" );
        rvobj.setHeadColumn("UNICNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        recordx.put("INSCNT",inscnt+"");
        recordx.put("UNICNT",unicnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("UNI5") ;
        rvobj.Println("UNI5");
        return rvobj;
    }
    // File Approval Count
    public VOBJ DCPApproval_Flow_SEL4(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL4", "File Approval Count" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("PID", dobj.getRetObject("S3").getRecord().get("PID"));   //PID
        List rlist = list("DcpInfoMgr_2016082417884.DCPApproval_Flow_SEL4", param);
        dvobj.setName("SEL4");
        dvobj.setRecords(rlist);
        dvobj.Println("SEL4");
        return dvobj;
    }
    // Approval File exist check
    public VOBJ DCPApproval_Flow_SEL11(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL11", "Approval File exist check " );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("PID", dobj.getRetObject("R").getRecord().get("PID"));   //PID
        param.put("DCP_TYPE", dobj.getRetObject("R").getRecord().get("DCP_TYPE"));   //DCP종별코드
        param.put("SEQ", dobj.getRetObject("R").getRecord().getInt("SEQ")+"");   //일렬번호
        List rlist = list("DcpInfoMgr_2016082417884.DCPApproval_Flow_SEL11", param);
        dvobj.setName("SEL11");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // Approval File Table Update
    public VOBJ DCPApproval_Flow_XIUD5(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"XIUD5", "Approval File Table Update " );
        VOBJ dvobj = dobj.getRetObject("R");            //Loop (MPD,MIUD) Currect Record Object
        dvobj.Println("XIUD5");
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("APPROVAL_YYYYMMDD", dobj.getRetObject("S2").getRecord().get("APPROVAL_YYYYMMDD"));   //승인일
            param.put("PID", dobj.getRetObject("R").getRecord().get("PID"));   //PID
            param.put("DCP_TYPE", dobj.getRetObject("R").getRecord().get("DCP_TYPE"));   //DCP종별코드
            param.put("SEQ", dobj.getRetObject("R").getRecord().getInt("SEQ")+"");   //일렬번호
            updcnt += update("DcpInfoMgr_2016082417884.DCPApproval_Flow_XIUD5",param);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("XIUD5");
        return rvobj;
    }
    // Approval File Table Insert
    public VOBJ DCPApproval_Flow_XIUD11(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"XIUD11", "Approval File Table Insert" );
        VOBJ dvobj = dobj.getRetObject("R");            //Loop (MPD,MIUD) Currect Record Object
        dvobj.Println("XIUD11");
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("APPROVAL_YYYYMMDD", dobj.getRetObject("S2").getRecord().get("APPROVAL_YYYYMMDD"));   //승인일
            param.put("PID", dobj.getRetObject("R").getRecord().get("PID"));   //PID
            param.put("DCP_TYPE", dobj.getRetObject("R").getRecord().get("DCP_TYPE"));   //DCP종별코드
            param.put("SEQ", dobj.getRetObject("R").getRecord().getInt("SEQ")+"");   //일렬번호
            insert("DcpInfoMgr_2016082417884.DCPApproval_Flow_XIUD11",param);
            updcnt++;
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("XIUD11");
        return rvobj;
    }
    // File Delete
    public VOBJ DCPApproval_Flow_DEL11(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"DEL11", "File Delete" );
        VOBJ dvobj = dobj.getRetObject("S3");           // Input Dataset Object.
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap    param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("PID", dvobj.getRecord().get("PID"));   //PID
            updcnt += delete("DcpInfoMgr_2016082417884.DCPApproval_Flow_DEL11",param);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("DEL11") ;
        return rvobj;
    }
    // Confirm(HP2D002T,HP1D001T_TZ)
    public VOBJ DCPApproval_Flow_SEL13(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL13", "Confirm(HP2D002T,HP1D001T_TZ)" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("PID", dobj.getRetObject("S3").getRecord().get("PID"));   //PID
        List rlist = list("DcpInfoMgr_2016082417884.DCPApproval_Flow_SEL13", param);
        dvobj.setName("SEL13");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // Confirm(HP2D002T_TZ)
    // DCP_A_20160303 값을 넣을 칼럼 사이즈가 ㅇ존재하지않음 확인해야함
    public VOBJ DCPApproval_Flow_INS19(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"INS19", "Confirm(HP2D002T_TZ)" );
        VOBJ dvobj = dobj.getRetObject("R");           //Loop (MPD,MIUD) Currect Record Object
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap    param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("UPDATE_TIME", "");   //갱신일시
            param.put("USE_ORG_CD", dvobj.getRecord().get("USE_ORG_CD"));   //費用？生元部CD
            param.put("REQ_COMPANY_CD", dvobj.getRecord().get("REQ_COMPANY_CD"));   //要求元？社CD
            param.put("ITEM_NAME", dvobj.getRecord().get("ITEM_NAME"));   //ITEM명
            param.put("REQ_ORG_CD", dvobj.getRecord().get("REQ_ORG_CD"));   //要求元部CD
            param.put("SRC_ORG_CD", dvobj.getRecord().get("SRC_ORG_CD"));   //配賦元部CD
            param.put("VAL", dvobj.getRecord().getDouble("VAL")+"");   //Value
            param.put("INVEST_TYPE_CD", dvobj.getRecord().get("INVEST_TYPE_CD"));   //投資？分CD
            param.put("HPMS_ID", dvobj.getRecord().get("HPMS_ID"));   //HPMS_ID
            param.put("UNIT", dvobj.getRecord().get("UNIT"));   //통화단위
            param.put("CUSTOMER_CD", dvobj.getRecord().get("CUSTOMER_CD"));   //顧客CD
            param.put("USE_COMPANY_CD", dvobj.getRecord().get("USE_COMPANY_CD"));   //費用？生元？社CD
            param.put("UPLOAD_FILE_NAME", dvobj.getRecord().get("UPLOAD_FILE_NAME"));   //UPLOAD_FILE_NAME
            param.put("SRC_COMPANY_CD", dvobj.getRecord().get("SRC_COMPANY_CD"));   //配賦元？社CD
            param.put("CALC_MST_VER", dvobj.getRecord().get("CALC_MST_VER"));   //CALC_MST_VER
            param.put("YYYYMM", dvobj.getRecord().get("YYYYMM"));   //실적월
            param.put("DST_COMPANY_CD", dvobj.getRecord().get("DST_COMPANY_CD"));   //DST_COMPANY_CD
            param.put("DST_ORG_CD", dvobj.getRecord().get("DST_ORG_CD"));   //DST_ORG_CD
            param.put("VAL_TYPE", dvobj.getRecord().get("VAL_TYPE"));   //VAL_TYPE
            param.put("APPLICATION", dvobj.getRecord().get("APPLICATION"));   //用途
            param.put("UNIT3", dvobj.getRecord().get("UNIT3"));   //UNIT3
            param.put("UNIT2", dvobj.getRecord().get("UNIT2"));   //UNIT2
            param.put("PID", dvobj.getRecord().get("PID"));   //PID
            param.put("VAL3", dvobj.getRecord().get("VAL3"));   //VAL3
            param.put("VAL2", dvobj.getRecord().get("VAL2"));   //VAL2
            param.put("APPROVAL_YYYYMMDD", dobj.getRetObject("S2").getRecord().get("APPROVAL_YYYYMMDD"));   //승인일
            param.put("DATA_TYPE", "DCP_"+dobj.getRetObject("S2").getRecord().get("DCP_TYPE")+"_"+dobj.getRetObject("S2").getRecord().get("APPROVAL_YYYYMMDD"));   //デ？タタイプ
            param.put("UPDATE_USER_ID", dobj.getRetObject("G").getRecord().get("USER_ID"));   //갱신자ID
            insert("DcpInfoMgr_2016082417884.DCPApproval_Flow_INS19",param);
            updcnt++;
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("INS19") ;
        return rvobj;
    }
    // Confirm(HP2D002T_TZ)
    // DCP_A_20160303 값을 넣을 칼럼 사이즈가 ㅇ존재하지않음 확인해야함
    public VOBJ DCPApproval_Flow_UNI19(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"UNI19", "Confirm(HP2D002T_TZ)" );
        VOBJ dvobj = dobj.getRetObject("R");           //Loop (MPD,MIUD) Currect Record Object
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        HashMap    param= null;
        int        inscnt=0, updcnt =0 ,unicnt=0,  rtncnt=0;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("UPDATE_TIME", "");   //갱신일시
            param.put("USE_ORG_CD", dvobj.getRecord().get("USE_ORG_CD"));   //費用？生元部CD
            param.put("REQ_COMPANY_CD", dvobj.getRecord().get("REQ_COMPANY_CD"));   //要求元？社CD
            param.put("ITEM_NAME", dvobj.getRecord().get("ITEM_NAME"));   //ITEM명
            param.put("REQ_ORG_CD", dvobj.getRecord().get("REQ_ORG_CD"));   //要求元部CD
            param.put("SRC_ORG_CD", dvobj.getRecord().get("SRC_ORG_CD"));   //配賦元部CD
            param.put("VAL", dvobj.getRecord().getDouble("VAL")+"");   //Value
            param.put("INVEST_TYPE_CD", dvobj.getRecord().get("INVEST_TYPE_CD"));   //投資？分CD
            param.put("HPMS_ID", dvobj.getRecord().get("HPMS_ID"));   //HPMS_ID
            param.put("UNIT", dvobj.getRecord().get("UNIT"));   //통화단위
            param.put("CUSTOMER_CD", dvobj.getRecord().get("CUSTOMER_CD"));   //顧客CD
            param.put("USE_COMPANY_CD", dvobj.getRecord().get("USE_COMPANY_CD"));   //費用？生元？社CD
            param.put("UPLOAD_FILE_NAME", dvobj.getRecord().get("UPLOAD_FILE_NAME"));   //UPLOAD_FILE_NAME
            param.put("SRC_COMPANY_CD", dvobj.getRecord().get("SRC_COMPANY_CD"));   //配賦元？社CD
            param.put("CALC_MST_VER", dvobj.getRecord().get("CALC_MST_VER"));   //CALC_MST_VER
            param.put("YYYYMM", dvobj.getRecord().get("YYYYMM"));   //실적월
            param.put("DST_COMPANY_CD", dvobj.getRecord().get("DST_COMPANY_CD"));   //DST_COMPANY_CD
            param.put("DST_ORG_CD", dvobj.getRecord().get("DST_ORG_CD"));   //DST_ORG_CD
            param.put("VAL_TYPE", dvobj.getRecord().get("VAL_TYPE"));   //VAL_TYPE
            param.put("APPLICATION", dvobj.getRecord().get("APPLICATION"));   //用途
            param.put("UNIT3", dvobj.getRecord().get("UNIT3"));   //UNIT3
            param.put("UNIT2", dvobj.getRecord().get("UNIT2"));   //UNIT2
            param.put("PID", dvobj.getRecord().get("PID"));   //PID
            param.put("VAL3", dvobj.getRecord().get("VAL3"));   //VAL3
            param.put("VAL2", dvobj.getRecord().get("VAL2"));   //VAL2
            param.put("APPROVAL_YYYYMMDD", dobj.getRetObject("S2").getRecord().get("APPROVAL_YYYYMMDD"));   //승인일
            param.put("DATA_TYPE", "DCP_"+dobj.getRetObject("S2").getRecord().get("DCP_TYPE")+"_"+dobj.getRetObject("S2").getRecord().get("APPROVAL_YYYYMMDD"));   //デ？タタイプ
            param.put("UPDATE_USER_ID", dobj.getRetObject("G").getRecord().get("USER_ID"));   //갱신자ID
            rtncnt= update("DcpInfoMgr_2016082417884.DCPApproval_Flow_UNI19_UPD",param);
            if(rtncnt < 1)
            {
                insert("DcpInfoMgr_2016082417884.DCPApproval_Flow_UNI19_INS",param);
                inscnt++;
                unicnt++;
            }
            else
            {
                updcnt += rtncnt;
                unicnt += rtncnt;
            }
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        rvobj.setHeadColumn("INSCNT" , "INT" );
        rvobj.setHeadColumn("UNICNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        recordx.put("INSCNT",inscnt+"");
        recordx.put("UNICNT",unicnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("UNI19") ;
        return rvobj;
    }
    // get ORG_SEQ
    public VOBJ Approval_applicant_ORG_Combo_SEL3(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL3", "get ORG_SEQ" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        String  ORG_CD="" ;          //ORG_CD
        if(!dobj.getRetObject("S2").getRecord().get("APPLICA_COMPANY_CD").equals(""))
        {
            ORG_CD = dobj.getRetObject("S2").getRecord().get("APPLICA_COMPANY_CD").substring(3);
        }
        else
        {
            ORG_CD = "";
        }
        param.put("ORG_CD", ORG_CD);   //ORG_CD
        String  COMPANY_CD="" ;          //COMPANY_CD
        if(!dobj.getRetObject("S2").getRecord().get("APPLICA_COMPANY_CD").equals(""))
        {
            COMPANY_CD = dobj.getRetObject("S2").getRecord().get("APPLICA_COMPANY_CD").substring(0,3);
        }
        else
        {
            COMPANY_CD = "";
        }
        param.put("COMPANY_CD", COMPANY_CD);   //COMPANY_CD
        List rlist = list("DcpInfoMgr_2016082417884.Approval_applicant_ORG_Combo_SEL3", param);
        dvobj.setName("SEL3");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // Organization Code
    public VOBJ Approval_applicant_ORG_Combo_SEL1(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL1", "Organization Code" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("ORG_SEQ", dobj.getRetObject("SEL3").getRecord().get("ORG_SEQ"));   //ORG_SEQ
        List rlist = list("DcpInfoMgr_2016082417884.Approval_applicant_ORG_Combo_SEL1", param);
        dvobj.setName("SEL1");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        dvobj.Println("SEL1");
        return dvobj;
    }
    // present
    public VOBJ DCP_Calculate_SEL2(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL2", "present" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("PID", dobj.getRetObject("S3").getRecord().get("PID"));   //PID
        param.put("DATA_TYPE", dobj.getRetObject("S").getRecord().get("DCP_TYPE"));   //デ？タタイプ
        List rlist = list("DcpInfoMgr_2016082417884.DCP_Calculate_SEL2", param);
        dvobj.setName("SEL2");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        dvobj.Println("SEL2");
        return dvobj;
    }
    // Previous
    public VOBJ DCP_Calculate_SEL4(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL4", "Previous" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("PID", dobj.getRetObject("S3").getRecord().get("PID"));   //PID
        param.put("DCP_TYPE", dobj.getRetObject("S2").getRecord().get("DCP_TYPE"));   //DCP종별코드
        List rlist = list("DcpInfoMgr_2016082417884.DCP_Calculate_SEL4", param);
        dvobj.setName("SEL4");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // FileUpload DCP_TYPE Check
    public VOBJ DCPInfoUpdate_SEL3(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL3", "FileUpload DCP_TYPE Check" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("PID", dobj.getRetObject("S3").getRecord().get("PID"));   //PID
        List rlist = list("DcpInfoMgr_2016082417884.DCPInfoUpdate_SEL3", param);
        dvobj.setName("SEL3");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // present
    public VOBJ DCPInfoUpdate_SEL6(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL6", "present" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("PID", dobj.getRetObject("S3").getRecord().get("PID"));   //PID
        param.put("DATA_TYPE", dobj.getRetObject("S").getRecord().get("DCP_TYPE"));   //デ？タタイプ
        List rlist = list("DcpInfoMgr_2016082417884.DCPInfoUpdate_SEL6", param);
        dvobj.setName("SEL6");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        dvobj.Println("SEL6");
        return dvobj;
    }
    // 04. DCP Info Update&Insert
    public VOBJ DCPInfoUpdate_UNI5(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"UNI5", "04. DCP Info Update&Insert" );
        VOBJ dvobj = dobj.getRetObject("S2");           // Input Dataset Object.
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        HashMap    param= null;
        int        inscnt=0, updcnt =0 ,unicnt=0,  rtncnt=0;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("REGIST_TIME", "");   //작성일시
            param.put("REGIST_USER_ID", dobj.getRetObject("G").getRecord().get("USER_ID"));   //작성자ID
            param.put("UPDATE_TIME", "");   //갱신일시
            param.put("P_DCP_EXEC_YYYYMMDD", dvobj.getRecord().get("P_DCP_EXEC_YYYYMMDD"));   //P_DCP 실시일
            param.put("BTM_AMT_TP_UNIT", dvobj.getRecord().get("BTM_AMT_TP_UNIT"));   //BottomTP金額の通貨コ？ド
            param.put("A_DCP_EXEC_YYYYMMDD", dvobj.getRecord().get("A_DCP_EXEC_YYYYMMDD"));   //A_DCP 실시일
            param.put("P_DCP_EXEC_CNT", dvobj.getRecord().getInt("P_DCP_EXEC_CNT")+"");   //P_DCP 실시회수
            param.put("BTM_AMT_SSP_UNIT", dvobj.getRecord().get("BTM_AMT_SSP_UNIT"));   //BottomSSP金額の通貨コ？ド
            param.put("REQUIRED_RETURN", dvobj.getRecord().getInt("REQUIRED_RETURN")+"");   //Required Return Years
            param.put("APPLICA_ORG_CD", dvobj.getRecord().get("APPLICA_ORG_CD"));   //申請部CD
            param.put("M_DCP_PLAN_YYYYMMDD", dvobj.getRecord().get("M_DCP_PLAN_YYYYMMDD"));   //M.DCP 予定日
            param.put("BTM_AMT_TP", dvobj.getRecord().getDouble("BTM_AMT_TP")+"");   //BottomTP금액
            param.put("CLOSING_ORG_CD", dvobj.getRecord().get("CLOSING_ORG_CD"));   //決算部CD
            param.put("M_DCP_EXEC_CNT", dvobj.getRecord().getInt("M_DCP_EXEC_CNT")+"");   //M_DCP 실시회수
            param.put("BTM_AMT_SSP", dvobj.getRecord().getDouble("BTM_AMT_SSP")+"");   //BottomSSP金額
            param.put("P_DCP_PLAN_YYYYMMDD", dvobj.getRecord().get("P_DCP_PLAN_YYYYMMDD"));   //P.DCP 予定日
            param.put("RESPONS_COMPANY_CD", dvobj.getRecord().get("RESPONS_COMPANY_CD"));   //responsibility？社CD
            param.put("E_DCP_PLAN_YYYYMMDD", dvobj.getRecord().get("E_DCP_PLAN_YYYYMMDD"));   //E.DCP 予定日
            param.put("E_DCP_EXEC_YYYYMMDD", dvobj.getRecord().get("E_DCP_EXEC_YYYYMMDD"));   //E_DCP 실시일
            param.put("M_DCP_EXEC_YYYYMMDD", dvobj.getRecord().get("M_DCP_EXEC_YYYYMMDD"));   //M_DCP 실시일
            param.put("E_DCP_EXEC_CNT", dvobj.getRecord().getInt("E_DCP_EXEC_CNT")+"");   //E_DCP 실시회수
            param.put("OSC_MARGIN_RATIO", dvobj.getRecord().getDouble("OSC_MARGIN_RATIO")+"");   //판매회사마진율
            param.put("REQUEST_AMT", dvobj.getRecord().getDouble("REQUEST_AMT")+"");   //품의발생액
            param.put("BTM_AMT_ECP_UNIT", dvobj.getRecord().get("BTM_AMT_ECP_UNIT"));   //BottomECP金額の通貨コ？ド
            param.put("BTM_AMT_ECP", dvobj.getRecord().getDouble("BTM_AMT_ECP")+"");   //BottomECP金額
            param.put("PERSON_IN_CHARGE_ID", dvobj.getRecord().get("PERSON_IN_CHARGE_ID"));   //Person in charge ID
            param.put("DCP_TYPE", dvobj.getRecord().get("DCP_TYPE"));   //DCP종별코드
            param.put("A_DCP_PLAN_YYYYMMDD", dvobj.getRecord().get("A_DCP_PLAN_YYYYMMDD"));   //A.DCP 予定日
            param.put("CLOSING_AMT", dvobj.getRecord().getDouble("CLOSING_AMT")+"");   //결산대상금액
            param.put("RESPONS_ORG_CD", dvobj.getRecord().get("RESPONS_ORG_CD"));   //responsibility
            param.put("A_DCP_EXEC_CNT", dvobj.getRecord().getInt("A_DCP_EXEC_CNT")+"");   //A_DCP 실시회수
            param.put("T_DCP_PLAN_YYYYMMDD", dvobj.getRecord().get("T_DCP_PLAN_YYYYMMDD"));   //T.DCP 予定日
            param.put("T_DCP_EXEC_YYYYMMDD", dvobj.getRecord().get("T_DCP_EXEC_YYYYMMDD"));   //T_DCP 실시일
            param.put("T_DCP_EXEC_CNT", dvobj.getRecord().getInt("T_DCP_EXEC_CNT")+"");   //T_DCP 실시회수
            String  APPLICA_COMPANY_CD="" ;          //申請？社CD
            if(!dobj.getRetObject("S2").getRecord().get("APPLICA_COMPANY_CD").equals(""))
            {
                APPLICA_COMPANY_CD = dobj.getRetObject("S2").getRecord().get("APPLICA_COMPANY_CD").substring(0,3);
            }
            else
            {
                APPLICA_COMPANY_CD = "";
            }
            param.put("APPLICA_COMPANY_CD", APPLICA_COMPANY_CD);   //申請？社CD
            String  CLOSING_COMPANY_CD="" ;          //決算？社CD
            if(!dobj.getRetObject("S2").getRecord().get("CLOSING_COMPANY_CD").equals(""))
            {
                CLOSING_COMPANY_CD = dobj.getRetObject("S2").getRecord().get("CLOSING_COMPANY_CD").substring(0,3);
            }
            else
            {
                CLOSING_COMPANY_CD = "";
            }
            param.put("CLOSING_COMPANY_CD", CLOSING_COMPANY_CD);   //決算？社CD
            param.put("INCOME_AMT", dobj.getRetObject("SEL6").getRecord().getDouble("INCOME_AMT")+"");   //영업이익
            param.put("INCOME_AMT_RATIO", dobj.getRetObject("SEL6").getRecord().getDouble("INCOME_AMT_RATIO")+"");   //영업이익율
            param.put("NRE_AMT", dobj.getRetObject("SEL6").getRecord().getDouble("NRE_AMT")+"");   //내부NRE매출
            param.put("PID", dobj.getRetObject("S3").getRecord().get("PID"));   //PID
            param.put("RD_COST", dobj.getRetObject("SEL6").getRecord().getDouble("RD_COST")+"");   //총개발비
            param.put("SALES_AMT", dobj.getRetObject("SEL6").getRecord().getDouble("SALES_AMT")+"");   //라이프매출
            param.put("UPDATE_USER_ID", dobj.getRetObject("G").getRecord().get("USER_ID"));   //갱신자ID
            rtncnt= update("DcpInfoMgr_2016082417884.DCPInfoUpdate_UNI5_UPD",param);
            if(rtncnt < 1)
            {
                insert("DcpInfoMgr_2016082417884.DCPInfoUpdate_UNI5_INS",param);
                inscnt++;
                unicnt++;
            }
            else
            {
                updcnt += rtncnt;
                unicnt += rtncnt;
            }
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        rvobj.setHeadColumn("INSCNT" , "INT" );
        rvobj.setHeadColumn("UNICNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        recordx.put("INSCNT",inscnt+"");
        recordx.put("UNICNT",unicnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("UNI5") ;
        return rvobj;
    }
    // DCP_TYPE_FIFLE_DELETE
    public VOBJ DCPInfoUpdate_DEL9(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"DEL9", "DCP_TYPE_FIFLE_DELETE" );
        VOBJ dvobj = dobj.getRetObject("SEL3");           //FileUpload DCP_TYPE Check Input Object(CALLDCPInfoUpdate_SEL3)
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap    param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("DCP_TYPE", dvobj.getRecord().get("DCP_TYPE"));   //DCP종별코드
            param.put("PID", dobj.getRetObject("S3").getRecord().get("PID"));   //PID
            updcnt += delete("DcpInfoMgr_2016082417884.DCPInfoUpdate_DEL9",param);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("DEL9") ;
        return rvobj;
    }
    // DCP Search
    public VOBJ DCP_Calculate_SEL5(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL5", "DCP Search" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("PID", dobj.getRetObject("S").getRecord().get("PID"));   //PID
        List rlist = list("DcpInfoMgr_2016082417884.DCP_Calculate_SEL5", param);
        dvobj.setName("SEL5");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        dvobj.Println("SEL5");
        return dvobj;
    }
    // PID Info
    public VOBJ DCP_PIDCheck_SEL2(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL2", "PID Info" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("USER_ID", dobj.getRetObject("G").getRecord().get("USER_ID"));   //사용자ID
        param.put("PID", dobj.getRetObject("S3").getRecord().get("PID"));   //PID
        List rlist = list("DcpInfoMgr_2016082417884.DCP_PIDCheck_SEL2", param);
        dvobj.setName("SEL2");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        dvobj.Println("SEL2");
        return dvobj;
    }
    // AUTH_CODE
    public VOBJ DCP_PIDCheck_SEL5(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL5", "AUTH_CODE" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("DcpInfoMgr_2016082417884.DCP_PIDCheck_SEL5", param);
        dvobj.setName("SEL5");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // File_length
    public VOBJ FileInfoAdd_SEL2(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL2", "File_length" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("FILE_NAME", "FILENAME");   //파일이름
        List rlist = list("DcpInfoMgr_2016082417884.FileInfoAdd_SEL2", param);
        dvobj.setName("SEL2");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // Front Page File
    public VOBJ FileInfoAdd_UNI26(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"UNI26", "Front Page File" );
        VOBJ dvobj = dobj.getRetObject("F");           // Input Dataset Object.
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        HashMap    param= null;
        int        inscnt=0, updcnt =0 ,unicnt=0,  rtncnt=0;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("SEQ", dvobj.getRecord().getInt("SEQ")+"");   //일렬번호
            param.put("DCP_TYPE", dvobj.getRecord().get("DCP_TYPE"));   //DCP종별코드
            param.put("PID", dvobj.getRecord().get("PID"));   //PID
            param.put("APPROVAL_YYYYMMDD", dvobj.getRecord().get("APPROVAL_YYYYMMDD"));   //승인일
            param.put("FILE_BIN", wutil.inputFileStream(dobj.getRetObject("F").getRecord().get("FILE_PATH"), dobj.getRetObject("F").getRecord().get("FILE_UNNAME")));   //FILE_BIN
            param.put("FILE_NAME", dobj.getRetObject("F").getRecord().get("FILE_NAME"));   //파일이름
            rtncnt= update("DcpInfoMgr_2016082417884.FileInfoAdd_UNI26_UPD",param);
            if(rtncnt < 1)
            {
                insert("DcpInfoMgr_2016082417884.FileInfoAdd_UNI26_INS",param);
                inscnt++;
                unicnt++;
            }
            else
            {
                updcnt += rtncnt;
                unicnt += rtncnt;
            }
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        rvobj.setHeadColumn("INSCNT" , "INT" );
        rvobj.setHeadColumn("UNICNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        recordx.put("INSCNT",inscnt+"");
        recordx.put("UNICNT",unicnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("UNI26") ;
        return rvobj;
    }
    // FILE_NAME
    public VOBJ FileInfoAdd_SEL21(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL21", "FILE_NAME" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("PID", dobj.getRetObject("S3").getRecord().get("PID"));   //PID
        List rlist = list("DcpInfoMgr_2016082417884.FileInfoAdd_SEL21", param);
        dvobj.setName("SEL21");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        dvobj.Println("SEL21");
        return dvobj;
    }
    // PL Sheet
    public VOBJ FileInfoAdd_UNI27(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"UNI27", "PL Sheet" );
        VOBJ dvobj = dobj.getRetObject("F");           // Input Dataset Object.
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        HashMap    param= null;
        int        inscnt=0, updcnt =0 ,unicnt=0,  rtncnt=0;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("SEQ", dvobj.getRecord().getInt("SEQ")+"");   //일렬번호
            param.put("DCP_TYPE", dvobj.getRecord().get("DCP_TYPE"));   //DCP종별코드
            param.put("PID", dvobj.getRecord().get("PID"));   //PID
            param.put("APPROVAL_YYYYMMDD", dvobj.getRecord().get("APPROVAL_YYYYMMDD"));   //승인일
            param.put("FILE_BIN", wutil.inputFileStream(dobj.getRetObject("F").getRecord().get("FILE1_PATH"), dobj.getRetObject("F").getRecord().get("FILE1_UNNAME")));   //FILE_BIN
            param.put("FILE_NAME", dobj.getRetObject("F").getRecord().get("FILE1_NAME"));   //파일이름
            rtncnt= update("DcpInfoMgr_2016082417884.FileInfoAdd_UNI27_UPD",param);
            if(rtncnt < 1)
            {
                insert("DcpInfoMgr_2016082417884.FileInfoAdd_UNI27_INS",param);
                inscnt++;
                unicnt++;
            }
            else
            {
                updcnt += rtncnt;
                unicnt += rtncnt;
            }
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        rvobj.setHeadColumn("INSCNT" , "INT" );
        rvobj.setHeadColumn("UNICNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        recordx.put("INSCNT",inscnt+"");
        recordx.put("UNICNT",unicnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("UNI27") ;
        return rvobj;
    }
    // Cost Estimate Sheet
    public VOBJ FileInfoAdd_UNI28(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"UNI28", "Cost Estimate Sheet" );
        VOBJ dvobj = dobj.getRetObject("F");           // Input Dataset Object.
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        HashMap    param= null;
        int        inscnt=0, updcnt =0 ,unicnt=0,  rtncnt=0;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("SEQ", dvobj.getRecord().getInt("SEQ")+"");   //일렬번호
            param.put("DCP_TYPE", dvobj.getRecord().get("DCP_TYPE"));   //DCP종별코드
            param.put("PID", dvobj.getRecord().get("PID"));   //PID
            param.put("APPROVAL_YYYYMMDD", dvobj.getRecord().get("APPROVAL_YYYYMMDD"));   //승인일
            param.put("FILE_BIN", wutil.inputFileStream(dobj.getRetObject("F").getRecord().get("FILE2_PATH"), dobj.getRetObject("F").getRecord().get("FILE2_UNNAME")));   //FILE_BIN
            param.put("FILE_NAME", dobj.getRetObject("F").getRecord().get("FILE2_NAME"));   //파일이름
            rtncnt= update("DcpInfoMgr_2016082417884.FileInfoAdd_UNI28_UPD",param);
            if(rtncnt < 1)
            {
                insert("DcpInfoMgr_2016082417884.FileInfoAdd_UNI28_INS",param);
                inscnt++;
                unicnt++;
            }
            else
            {
                updcnt += rtncnt;
                unicnt += rtncnt;
            }
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        rvobj.setHeadColumn("INSCNT" , "INT" );
        rvobj.setHeadColumn("UNICNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        recordx.put("INSCNT",inscnt+"");
        recordx.put("UNICNT",unicnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("UNI28") ;
        return rvobj;
    }
    // Quote Sheet
    public VOBJ FileInfoAdd_UNI29(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"UNI29", "Quote Sheet" );
        VOBJ dvobj = dobj.getRetObject("F");           // Input Dataset Object.
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        HashMap    param= null;
        int        inscnt=0, updcnt =0 ,unicnt=0,  rtncnt=0;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("SEQ", dvobj.getRecord().getInt("SEQ")+"");   //일렬번호
            param.put("DCP_TYPE", dvobj.getRecord().get("DCP_TYPE"));   //DCP종별코드
            param.put("PID", dvobj.getRecord().get("PID"));   //PID
            param.put("APPROVAL_YYYYMMDD", dvobj.getRecord().get("APPROVAL_YYYYMMDD"));   //승인일
            param.put("FILE_BIN", wutil.inputFileStream(dobj.getRetObject("F").getRecord().get("FILE3_PATH"), dobj.getRetObject("F").getRecord().get("FILE3_UNNAME")));   //FILE_BIN
            param.put("FILE_NAME", dobj.getRetObject("F").getRecord().get("FILE3_NAME"));   //파일이름
            rtncnt= update("DcpInfoMgr_2016082417884.FileInfoAdd_UNI29_UPD",param);
            if(rtncnt < 1)
            {
                insert("DcpInfoMgr_2016082417884.FileInfoAdd_UNI29_INS",param);
                inscnt++;
                unicnt++;
            }
            else
            {
                updcnt += rtncnt;
                unicnt += rtncnt;
            }
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        rvobj.setHeadColumn("INSCNT" , "INT" );
        rvobj.setHeadColumn("UNICNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        recordx.put("INSCNT",inscnt+"");
        recordx.put("UNICNT",unicnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("UNI29") ;
        return rvobj;
    }
    // DR Slip Sheet
    public VOBJ FileInfoAdd_UNI30(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"UNI30", "DR Slip Sheet" );
        VOBJ dvobj = dobj.getRetObject("F");           // Input Dataset Object.
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        HashMap    param= null;
        int        inscnt=0, updcnt =0 ,unicnt=0,  rtncnt=0;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("SEQ", dvobj.getRecord().getInt("SEQ")+"");   //일렬번호
            param.put("DCP_TYPE", dvobj.getRecord().get("DCP_TYPE"));   //DCP종별코드
            param.put("PID", dvobj.getRecord().get("PID"));   //PID
            param.put("APPROVAL_YYYYMMDD", dvobj.getRecord().get("APPROVAL_YYYYMMDD"));   //승인일
            param.put("FILE_BIN", wutil.inputFileStream(dobj.getRetObject("F").getRecord().get("FILE4_PATH"), dobj.getRetObject("F").getRecord().get("FILE4_UNNAME")));   //FILE_BIN
            param.put("FILE_NAME", dobj.getRetObject("F").getRecord().get("FILE4_NAME"));   //파일이름
            rtncnt= update("DcpInfoMgr_2016082417884.FileInfoAdd_UNI30_UPD",param);
            if(rtncnt < 1)
            {
                insert("DcpInfoMgr_2016082417884.FileInfoAdd_UNI30_INS",param);
                inscnt++;
                unicnt++;
            }
            else
            {
                updcnt += rtncnt;
                unicnt += rtncnt;
            }
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        rvobj.setHeadColumn("INSCNT" , "INT" );
        rvobj.setHeadColumn("UNICNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        recordx.put("INSCNT",inscnt+"");
        recordx.put("UNICNT",unicnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("UNI30") ;
        return rvobj;
    }
    // CHIP Specification Sheet
    public VOBJ FileInfoAdd_UNI31(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"UNI31", "CHIP Specification Sheet" );
        VOBJ dvobj = dobj.getRetObject("F");           // Input Dataset Object.
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        HashMap    param= null;
        int        inscnt=0, updcnt =0 ,unicnt=0,  rtncnt=0;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("SEQ", dvobj.getRecord().getInt("SEQ")+"");   //일렬번호
            param.put("DCP_TYPE", dvobj.getRecord().get("DCP_TYPE"));   //DCP종별코드
            param.put("PID", dvobj.getRecord().get("PID"));   //PID
            param.put("APPROVAL_YYYYMMDD", dvobj.getRecord().get("APPROVAL_YYYYMMDD"));   //승인일
            param.put("FILE_BIN", wutil.inputFileStream(dobj.getRetObject("F").getRecord().get("FILE5_PATH"), dobj.getRetObject("F").getRecord().get("FILE5_UNNAME")));   //FILE_BIN
            param.put("FILE_NAME", dobj.getRetObject("F").getRecord().get("FILE5_NAME"));   //파일이름
            rtncnt= update("DcpInfoMgr_2016082417884.FileInfoAdd_UNI31_UPD",param);
            if(rtncnt < 1)
            {
                insert("DcpInfoMgr_2016082417884.FileInfoAdd_UNI31_INS",param);
                inscnt++;
                unicnt++;
            }
            else
            {
                updcnt += rtncnt;
                unicnt += rtncnt;
            }
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        rvobj.setHeadColumn("INSCNT" , "INT" );
        rvobj.setHeadColumn("UNICNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        recordx.put("INSCNT",inscnt+"");
        recordx.put("UNICNT",unicnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("UNI31") ;
        return rvobj;
    }
    // etc 1
    public VOBJ FileInfoAdd_UNI32(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"UNI32", "etc 1" );
        VOBJ dvobj = dobj.getRetObject("F");           // Input Dataset Object.
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        HashMap    param= null;
        int        inscnt=0, updcnt =0 ,unicnt=0,  rtncnt=0;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("SEQ", dvobj.getRecord().getInt("SEQ")+"");   //일렬번호
            param.put("DCP_TYPE", dvobj.getRecord().get("DCP_TYPE"));   //DCP종별코드
            param.put("PID", dvobj.getRecord().get("PID"));   //PID
            param.put("APPROVAL_YYYYMMDD", dvobj.getRecord().get("APPROVAL_YYYYMMDD"));   //승인일
            param.put("FILE_BIN", wutil.inputFileStream(dobj.getRetObject("F").getRecord().get("FILE6_PATH"), dobj.getRetObject("F").getRecord().get("FILE6_UNNAME")));   //FILE_BIN
            param.put("FILE_NAME", dobj.getRetObject("F").getRecord().get("FILE6_NAME"));   //파일이름
            rtncnt= update("DcpInfoMgr_2016082417884.FileInfoAdd_UNI32_UPD",param);
            if(rtncnt < 1)
            {
                insert("DcpInfoMgr_2016082417884.FileInfoAdd_UNI32_INS",param);
                inscnt++;
                unicnt++;
            }
            else
            {
                updcnt += rtncnt;
                unicnt += rtncnt;
            }
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        rvobj.setHeadColumn("INSCNT" , "INT" );
        rvobj.setHeadColumn("UNICNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        recordx.put("INSCNT",inscnt+"");
        recordx.put("UNICNT",unicnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("UNI32") ;
        return rvobj;
    }
    // etc2
    public VOBJ FileInfoAdd_UNI33(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"UNI33", "etc2" );
        VOBJ dvobj = dobj.getRetObject("F");           // Input Dataset Object.
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        HashMap    param= null;
        int        inscnt=0, updcnt =0 ,unicnt=0,  rtncnt=0;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("SEQ", dvobj.getRecord().getInt("SEQ")+"");   //일렬번호
            param.put("DCP_TYPE", dvobj.getRecord().get("DCP_TYPE"));   //DCP종별코드
            param.put("PID", dvobj.getRecord().get("PID"));   //PID
            param.put("APPROVAL_YYYYMMDD", dvobj.getRecord().get("APPROVAL_YYYYMMDD"));   //승인일
            param.put("FILE_BIN", wutil.inputFileStream(dobj.getRetObject("F").getRecord().get("FILE7_PATH"), dobj.getRetObject("F").getRecord().get("FILE7_UNNAME")));   //FILE_BIN
            param.put("FILE_NAME", dobj.getRetObject("F").getRecord().get("FILE7_NAME"));   //파일이름
            rtncnt= update("DcpInfoMgr_2016082417884.FileInfoAdd_UNI33_UPD",param);
            if(rtncnt < 1)
            {
                insert("DcpInfoMgr_2016082417884.FileInfoAdd_UNI33_INS",param);
                inscnt++;
                unicnt++;
            }
            else
            {
                updcnt += rtncnt;
                unicnt += rtncnt;
            }
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        rvobj.setHeadColumn("INSCNT" , "INT" );
        rvobj.setHeadColumn("UNICNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        recordx.put("INSCNT",inscnt+"");
        recordx.put("UNICNT",unicnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("UNI33") ;
        return rvobj;
    }
    // etc 3
    public VOBJ FileInfoAdd_UNI34(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"UNI34", "etc 3" );
        VOBJ dvobj = dobj.getRetObject("F");           // Input Dataset Object.
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        HashMap    param= null;
        int        inscnt=0, updcnt =0 ,unicnt=0,  rtncnt=0;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("SEQ", dvobj.getRecord().getInt("SEQ")+"");   //일렬번호
            param.put("DCP_TYPE", dvobj.getRecord().get("DCP_TYPE"));   //DCP종별코드
            param.put("PID", dvobj.getRecord().get("PID"));   //PID
            param.put("APPROVAL_YYYYMMDD", dvobj.getRecord().get("APPROVAL_YYYYMMDD"));   //승인일
            param.put("FILE_BIN", wutil.inputFileStream(dobj.getRetObject("F").getRecord().get("FILE8_PATH"), dobj.getRetObject("F").getRecord().get("FILE8_UNNAME")));   //FILE_BIN
            param.put("FILE_NAME", dobj.getRetObject("F").getRecord().get("FILE8_NAME"));   //파일이름
            rtncnt= update("DcpInfoMgr_2016082417884.FileInfoAdd_UNI34_UPD",param);
            if(rtncnt < 1)
            {
                insert("DcpInfoMgr_2016082417884.FileInfoAdd_UNI34_INS",param);
                inscnt++;
                unicnt++;
            }
            else
            {
                updcnt += rtncnt;
                unicnt += rtncnt;
            }
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        rvobj.setHeadColumn("INSCNT" , "INT" );
        rvobj.setHeadColumn("UNICNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        recordx.put("INSCNT",inscnt+"");
        recordx.put("UNICNT",unicnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("UNI34") ;
        return rvobj;
    }
    // SUB_PID Search(HP1DM12T)
    public VOBJ DCPSearchInfo_SEL8(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL8", "SUB_PID Search(HP1DM12T)" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("PID", dobj.getRetObject("S").getRecord().get("PID"));   //PID
        List rlist = list("DcpInfoMgr_2016082417884.DCPSearchInfo_SEL8", param);
        dvobj.setName("SEL8");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        dvobj.Println("SEL8");
        return dvobj;
    }
    // PID Search
    public VOBJ DCPSearchInfo_SEL4(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL4", "PID Search" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("PID", dobj.getRetObject("S").getRecord().get("PID"));   //PID
        List rlist = list("DcpInfoMgr_2016082417884.DCPSearchInfo_SEL4", param);
        dvobj.setName("SEL4");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        dvobj.Println("SEL4");
        return dvobj;
    }
    // DCP (CNT 0)
    public VOBJ DCPSearchInfo_SEL12(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL12", "DCP (CNT 0)" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("PID", dobj.getRetObject("S").getRecord().get("PID"));   //PID
        List rlist = list("DcpInfoMgr_2016082417884.DCPSearchInfo_SEL12", param);
        dvobj.setName("SEL12");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        dvobj.Println("SEL12");
        return dvobj;
    }
    // Required Return
    public VOBJ DCPSearchInfo_SEL14(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL14", "Required Return" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("CD", dobj.getRetObject("SEL4").getRecord().get("RD_CATEGORY_CD"));   //코드
        List rlist = list("DcpInfoMgr_2016082417884.DCPSearchInfo_SEL14", param);
        dvobj.setName("SEL14");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // DCP (CNT 0)
    public VOBJ DCPSearchInfo_SEL11(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL11", "DCP (CNT 0)" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("PID", dobj.getRetObject("S").getRecord().get("PID"));   //PID
        List rlist = list("DcpInfoMgr_2016082417884.DCPSearchInfo_SEL11", param);
        dvobj.setName("SEL11");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        dvobj.Println("SEL11");
        return dvobj;
    }
    // FILE_NAME
    public VOBJ DCPSearchInfo_SEL6(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL6", "FILE_NAME" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("PID", dobj.getRetObject("S").getRecord().get("PID"));   //PID
        List rlist = list("DcpInfoMgr_2016082417884.DCPSearchInfo_SEL6", param);
        dvobj.setName("SEL6");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        dvobj.Println("SEL6");
        return dvobj;
    }
    // DCP Search
    public VOBJ DCPSearchInfo_SEL10(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL10", "DCP Search" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("PID", dobj.getRetObject("S").getRecord().get("PID"));   //PID
        param.put("CD", dobj.getRetObject("SEL4").getRecord().get("RD_CATEGORY_CD"));   //코드
        param.put("CODE", dobj.getRetObject("SEL4").getRecord().get("RD_CATEGORY_CD").substring(2,4));   //코드
        List rlist = list("DcpInfoMgr_2016082417884.DCPSearchInfo_SEL10", param);
        dvobj.setName("SEL10");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        dvobj.Println("SEL10");
        return dvobj;
    }
    // PID exist Check
    public VOBJ PIDConfirm_SEL2(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL2", "PID exist Check" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("PID", dobj.getRetObject("S").getRecord().get("PID"));   //PID
        List rlist = list("DcpInfoMgr_2016082417884.PIDConfirm_SEL2", param);
        dvobj.setName("SEL2");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // Copy Date Delete(HP2D002T )
    public VOBJ DCPApproval_Flow_DEL21(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"DEL21", "Copy Date Delete(HP2D002T )" );
        VOBJ dvobj = dobj.getRetObject("SEL13");           //Confirm(HP2D002T,HP1D001T_TZ) Input Object(CALLDCPApproval_Flow_SEL13)
        VOBJ       rvobj= null;
        int        updcnt =0;
        HashMap    param = null;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("PID", dvobj.getRecord().get("PID"));   //PID
            param.put("DATA_TYPE", dobj.getRetObject("SEL13").getRecord().get("DATA_TYPE"));   //デ？タタイプ
            updcnt += delete("DcpInfoMgr_2016082417884.DCPApproval_Flow_DEL21",param);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("DEL21") ;
        return rvobj;
    }
    // ORG_LVL CHECK(PID)
    public VOBJ DCP_PIDCheck_SEL4(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL4", "ORG_LVL CHECK(PID)" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("ORG_CD", dobj.getRetObject("SEL2").getRecord().get("OWNER_ORG_CD"));   //ORG_CD
        List rlist = list("DcpInfoMgr_2016082417884.DCP_PIDCheck_SEL4", param);
        dvobj.setName("SEL4");
        dvobj.setRecords(rlist);
        dvobj.Println("SEL4");
        return dvobj;
    }
    // ORG_SEQ(PID) Check
    public VOBJ DCP_PIDCheck_SEL6(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL6", "ORG_SEQ(PID) Check" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("ORG_SEQ", dobj.getRetObject("SEL4").getRecord().get("ORG_SEQ_L4"));   //ORG_SEQ
        List rlist = list("DcpInfoMgr_2016082417884.DCP_PIDCheck_SEL6", param);
        dvobj.setName("SEL6");
        dvobj.setRecords(rlist);
        dvobj.Println("SEL6");
        return dvobj;
    }
    // LoginUser check ORG
    public VOBJ DCP_PIDCheck_SEL8(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL8", "LoginUser check ORG" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("ORG_CD", dobj.getRetObject("G").getRecord().get("ORG_CD"));   //ORG_CD
        param.put("COMPANY_CD", dobj.getRetObject("G").getRecord().get("COMPANY_CD"));   //COMPANY_CD
        List rlist = list("DcpInfoMgr_2016082417884.DCP_PIDCheck_SEL8", param);
        dvobj.setName("SEL8");
        dvobj.setRecords(rlist);
        dvobj.Println("SEL8");
        return dvobj;
    }
    // LoginUser ORG_SEQ check
    public VOBJ DCP_PIDCheck_SEL10(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL10", "LoginUser ORG_SEQ check" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("ORG_SEQ", dobj.getRetObject("SEL8").getRecord().get("ORG_SEQ_L4"));   //ORG_SEQ
        List rlist = list("DcpInfoMgr_2016082417884.DCP_PIDCheck_SEL10", param);
        dvobj.setName("SEL10");
        dvobj.setRecords(rlist);
        dvobj.Println("SEL10");
        return dvobj;
    }
    // Check CNT
    public VOBJ DCP_PIDCheck_SEL13(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL13", "Check CNT" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("DcpInfoMgr_2016082417884.DCP_PIDCheck_SEL13", param);
        dvobj.setName("SEL13");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // Check Flag
    public VOBJ DCP_PIDCheck_MRG15(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"MRG15", "Check Flag" );
        VOBJ       rvobj= null;
        rvobj = wutil.getMergeObject(dobj, "SEL13, SEL22","CNT" );
        rvobj.setName("MRG15") ;
        rvobj.setRetcode(1);
        return rvobj;
    }
    // Check CNT
    public VOBJ DCP_PIDCheck_SEL22(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL22", "Check CNT" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("DcpInfoMgr_2016082417884.DCP_PIDCheck_SEL22", param);
        dvobj.setName("SEL22");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // A.DCP(APPROVAL_DATE)
    public VOBJ ApprovalDataSearch_SEL2(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL2", "A.DCP(APPROVAL_DATE)" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("PID", dobj.getRetObject("S3").getRecord().get("PID"));   //PID
        List rlist = list("DcpInfoMgr_2016082417884.ApprovalDataSearch_SEL2", param);
        dvobj.setName("SEL2");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        dvobj.Println("SEL2");
        return dvobj;
    }
    // P.DCP(APPROVAL_DATE)
    public VOBJ ApprovalDataSearch_SEL4(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL4", "P.DCP(APPROVAL_DATE)" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("PID", dobj.getRetObject("S3").getRecord().get("PID"));   //PID
        List rlist = list("DcpInfoMgr_2016082417884.ApprovalDataSearch_SEL4", param);
        dvobj.setName("SEL4");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        dvobj.Println("SEL4");
        return dvobj;
    }
    // T.DCP(APPROVAL_DATE)
    public VOBJ ApprovalDataSearch_SEL6(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL6", "T.DCP(APPROVAL_DATE)" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("PID", dobj.getRetObject("S3").getRecord().get("PID"));   //PID
        List rlist = list("DcpInfoMgr_2016082417884.ApprovalDataSearch_SEL6", param);
        dvobj.setName("SEL6");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        dvobj.Println("SEL6");
        return dvobj;
    }
    // M.DCP(APPROVAL_DATE)
    public VOBJ ApprovalDataSearch_SEL8(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL8", "M.DCP(APPROVAL_DATE)" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("PID", dobj.getRetObject("S3").getRecord().get("PID"));   //PID
        List rlist = list("DcpInfoMgr_2016082417884.ApprovalDataSearch_SEL8", param);
        dvobj.setName("SEL8");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        dvobj.Println("SEL8");
        return dvobj;
    }
    // E.DCP(APPROVAL_DATE)
    public VOBJ ApprovalDataSearch_SEL10(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL10", "E.DCP(APPROVAL_DATE)" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("PID", dobj.getRetObject("S3").getRecord().get("PID"));   //PID
        List rlist = list("DcpInfoMgr_2016082417884.ApprovalDataSearch_SEL10", param);
        dvobj.setName("SEL10");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        dvobj.Println("SEL10");
        return dvobj;
    }
}