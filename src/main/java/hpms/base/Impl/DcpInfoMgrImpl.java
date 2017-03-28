
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
import hpms.base.service.DcpInfoMgr;
@Service("DcpInfoMgr2016082417884Service")
/**
*/
public class DcpInfoMgrImpl extends AbstractServiceImpl implements DcpInfoMgr
{
    @Autowired
    private DcpInfoMgrDao DcpInfoMgrdao;
    /**
    파일 업로드 승인날짜 고정 값을로 되어있음 어떻게 진행해야 할지 나오지 않아서  나오면 수정해야함
    */
    public DOBJ FileInfoAdd(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vSEL22 = DcpInfoMgrdao.FileInfoAdd_SEL22(dobj);        //  DCP exist Check
        dobj.setRetObject(vSEL22);
        if( dobj.getRetObject("SEL22").getRecord().getInt("CNT") == 0)
        {
            dobj.setRetmsg("00031");
        }
        else if( dobj.getRetObject("SEL22").getRecord().getInt("CNT") != 0)
        {
            VOBJ vSEL2 = DcpInfoMgrdao.FileInfoAdd_SEL2(dobj);        //  File_length
            dobj.setRetObject(vSEL2);
            if( dobj.getRetObject("F").getRecord().getInt("SEQ") == 72)
            {
                VOBJ vUNI34 = DcpInfoMgrdao.FileInfoAdd_UNI34(dobj);        //  etc 3
                dobj.setRetObject(vUNI34);
                VOBJ vSEL21 = DcpInfoMgrdao.FileInfoAdd_SEL21(dobj);        //  FILE_NAME
                dobj.setRetObject(vSEL21);
            }
            else if( dobj.getRetObject("F").getRecord().getInt("SEQ") == 71)
            {
                VOBJ vUNI33 = DcpInfoMgrdao.FileInfoAdd_UNI33(dobj);        //  etc2
                dobj.setRetObject(vUNI33);
                VOBJ vSEL21 = DcpInfoMgrdao.FileInfoAdd_SEL21(dobj);        //  FILE_NAME
                dobj.setRetObject(vSEL21);
            }
            else if( dobj.getRetObject("F").getRecord().getInt("SEQ") == 70)
            {
                VOBJ vUNI32 = DcpInfoMgrdao.FileInfoAdd_UNI32(dobj);        //  etc 1
                dobj.setRetObject(vUNI32);
                VOBJ vSEL21 = DcpInfoMgrdao.FileInfoAdd_SEL21(dobj);        //  FILE_NAME
                dobj.setRetObject(vSEL21);
            }
            else if( dobj.getRetObject("F").getRecord().getInt("SEQ") == 60)
            {
                VOBJ vUNI31 = DcpInfoMgrdao.FileInfoAdd_UNI31(dobj);        //  CHIP Specification Sheet
                dobj.setRetObject(vUNI31);
                VOBJ vSEL21 = DcpInfoMgrdao.FileInfoAdd_SEL21(dobj);        //  FILE_NAME
                dobj.setRetObject(vSEL21);
            }
            else if( dobj.getRetObject("F").getRecord().getInt("SEQ") == 50)
            {
                VOBJ vUNI30 = DcpInfoMgrdao.FileInfoAdd_UNI30(dobj);        //  DR Slip Sheet
                dobj.setRetObject(vUNI30);
                VOBJ vSEL21 = DcpInfoMgrdao.FileInfoAdd_SEL21(dobj);        //  FILE_NAME
                dobj.setRetObject(vSEL21);
            }
            else if( dobj.getRetObject("F").getRecord().getInt("SEQ") == 40)
            {
                VOBJ vUNI29 = DcpInfoMgrdao.FileInfoAdd_UNI29(dobj);        //  Quote Sheet
                dobj.setRetObject(vUNI29);
                VOBJ vSEL21 = DcpInfoMgrdao.FileInfoAdd_SEL21(dobj);        //  FILE_NAME
                dobj.setRetObject(vSEL21);
            }
            else if( dobj.getRetObject("F").getRecord().getInt("SEQ") == 30)
            {
                VOBJ vUNI28 = DcpInfoMgrdao.FileInfoAdd_UNI28(dobj);        //  Cost Estimate Sheet
                dobj.setRetObject(vUNI28);
                VOBJ vSEL21 = DcpInfoMgrdao.FileInfoAdd_SEL21(dobj);        //  FILE_NAME
                dobj.setRetObject(vSEL21);
            }
            else if( dobj.getRetObject("F").getRecord().getInt("SEQ") == 20)
            {
                VOBJ vUNI27 = DcpInfoMgrdao.FileInfoAdd_UNI27(dobj);        //  PL Sheet
                dobj.setRetObject(vUNI27);
                VOBJ vSEL21 = DcpInfoMgrdao.FileInfoAdd_SEL21(dobj);        //  FILE_NAME
                dobj.setRetObject(vSEL21);
            }
            else if( dobj.getRetObject("F").getRecord().getInt("SEQ") == 10)
            {
                VOBJ vUNI26 = DcpInfoMgrdao.FileInfoAdd_UNI26(dobj);        //  Front Page File
                dobj.setRetObject(vUNI26);
                VOBJ vSEL21 = DcpInfoMgrdao.FileInfoAdd_SEL21(dobj);        //  FILE_NAME
                dobj.setRetObject(vSEL21);
            }
        }
        return dobj;
    }
    public DOBJ DCPApproval_Flow(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vSEL19 = DcpInfoMgrdao.DCPApproval_Flow_SEL19(dobj);        //  DCP exist Check
        dobj.setRetObject(vSEL19);
        if( dobj.getRetObject("SEL19").getRecord().getInt("CNT") == 0)
        {
            dobj.setRetmsg("00031");
        }
        else if( dobj.getRetObject("SEL19").getRecord().getInt("CNT") > 0)
        {
            VOBJ vSEL18 = DcpInfoMgrdao.DCPApproval_Flow_SEL18(dobj);        //  PID Search
            dobj.setRetObject(vSEL18);
            VOBJ vUNI5 = DcpInfoMgrdao.DCPApproval_Flow_UNI5(dobj);        //  Approval Data Update Insert
            dobj.setRetObject(vUNI5);
            VOBJ vSEL4 = DcpInfoMgrdao.DCPApproval_Flow_SEL4(dobj);        //  File Approval Count
            dobj.setRetObject(vSEL4);
            this.DCPApproval_Flow_MPD6(dobj);        //  Loop
            if(dobj.getRtncode() == 9)
            {
                throw new Exception();
            }
            VOBJ vDEL11 = DcpInfoMgrdao.DCPApproval_Flow_DEL11(dobj);        //  File Delete
            dobj.setRetObject(vDEL11);
            VOBJ vSEL13 = DcpInfoMgrdao.DCPApproval_Flow_SEL13(dobj);        //  Confirm(HP2D002T,HP1D001T_TZ)
            dobj.setRetObject(vSEL13);
            this.DCPApproval_Flow_MPD15(dobj);        //  LooP
            if(dobj.getRtncode() == 9)
            {
                throw new Exception();
            }
        }
        return dobj;
    }
    // Loop
    private DOBJ DCPApproval_Flow_MPD6(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"MPD6", "Loop" );
        dobj.setRtnname("MPD6");
        VOBJ dvobj = dobj.getRetObject("SEL4");         //File Approval Count Input Object(CALLDCPApproval_Flow_SEL4)
        VOBJ       rvobj= null;
        dvobj.first();
        while(dvobj.next())
        {
            if(true)
            {
                dobj.resetObject("SEL11,XIUD5,XIUD11");
                dobj.setRetObject(dvobj.getRecVobj("R"));
                VOBJ vSEL11 = DcpInfoMgrdao.DCPApproval_Flow_SEL11(dobj);        //  Approval File exist check
                dobj.setRetObject(vSEL11);
                if( dobj.getRetObject("SEL11").getRecord().getInt("CNT") == 0)
                {
                    VOBJ vXIUD11 = DcpInfoMgrdao.DCPApproval_Flow_XIUD11(dobj);        //  Approval File Table Insert
                    dobj.setRetObject(vXIUD11);
                }
                else if( dobj.getRetObject("SEL11").getRecord().getInt("CNT") >= 1)
                {
                    VOBJ vXIUD5 = DcpInfoMgrdao.DCPApproval_Flow_XIUD5(dobj);        //  Approval File Table Update
                    dobj.setRetObject(vXIUD5);
                }
            }
        }
        return dobj;
    }
    // LooP
    private DOBJ DCPApproval_Flow_MPD15(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"MPD15", "LooP" );
        dobj.setRtnname("MPD15");
        VOBJ dvobj = dobj.getRetObject("SEL13");         //Confirm(HP2D002T,HP1D001T_TZ) Input Object(CALLDCPApproval_Flow_SEL13)
        VOBJ       rvobj= null;
        dvobj.first();
        while(dvobj.next())
        {
            if(true)
            {
                dobj.resetObject("INS19");
                dobj.setRetObject(dvobj.getRecVobj("R"));
                VOBJ vINS19 = DcpInfoMgrdao.DCPApproval_Flow_INS19(dobj);        //  Confirm(HP2D002T_TZ)
                dobj.setRetObject(vINS19);
            }
        }
        return dobj;
    }
    public DOBJ PIDConfirm(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vSEL2 = DcpInfoMgrdao.PIDConfirm_SEL2(dobj);        //  PID exist Check
        dobj.setRetObject(vSEL2);
        return dobj;
    }
    public DOBJ BLOB_DCPFileDown(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vPEX3 = DcpInfoMgrdao.BLOB_DCPFileDown_PEX3(dobj);        //  ROOTPATH
        dobj.setRetObject(vPEX3);
        VOBJ vSEL8 = DcpInfoMgrdao.BLOB_DCPFileDown_SEL8(dobj);        //  Fileinfo
        dobj.setRetObject(vSEL8);
        VOBJ vSEL2 = DcpInfoMgrdao.BLOB_DCPFileDown_SEL2(dobj);        //  Fileinfo
        dobj.setRetObject(vSEL2);
        VOBJ vDN01 = DcpInfoMgrdao.BLOB_DCPFileDown_DN01(dobj);        //  downfile seting
        dobj.setRetObject(vDN01);
        return dobj;
    }
    public DOBJ Approval_applicant_ORG_Combo(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vSEL3 = DcpInfoMgrdao.Approval_applicant_ORG_Combo_SEL3(dobj);        //  get ORG_SEQ
        dobj.setRetObject(vSEL3);
        VOBJ vSEL1 = DcpInfoMgrdao.Approval_applicant_ORG_Combo_SEL1(dobj);        //  Organization Code
        dobj.setRetObject(vSEL1);
        return dobj;
    }
    public DOBJ DCP_Calculate(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vSEL2 = DcpInfoMgrdao.DCP_Calculate_SEL2(dobj);        //  present
        dobj.setRetObject(vSEL2);
        VOBJ vSEL4 = DcpInfoMgrdao.DCP_Calculate_SEL4(dobj);        //  Previous
        dobj.setRetObject(vSEL4);
        return dobj;
    }
    public DOBJ DCPInfoUpdate(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vSEL3 = DcpInfoMgrdao.DCPInfoUpdate_SEL3(dobj);        //  FileUpload DCP_TYPE Check
        dobj.setRetObject(vSEL3);
        if(!dobj.getRetObject("SEL3").getRecord().get("DCP_TYPE").equals(dobj.getRetObject("S2").getRecord().get("DCP_TYPE")))
        {
            VOBJ vDEL9 = DcpInfoMgrdao.DCPInfoUpdate_DEL9(dobj);        //  DCP_TYPE_FIFLE_DELETE
            dobj.setRetObject(vDEL9);
            VOBJ vSEL6 = DcpInfoMgrdao.DCPInfoUpdate_SEL6(dobj);        //  present
            dobj.setRetObject(vSEL6);
            VOBJ vUNI5 = DcpInfoMgrdao.DCPInfoUpdate_UNI5(dobj);        //  04. DCP Info Update&Insert
            dobj.setRetObject(vUNI5);
        }
        else if(dobj.getRetObject("SEL3").getRecord().get("DCP_TYPE").equals(dobj.getRetObject("S2").getRecord().get("DCP_TYPE")))
        {
            VOBJ vSEL6 = DcpInfoMgrdao.DCPInfoUpdate_SEL6(dobj);        //  present
            dobj.setRetObject(vSEL6);
            VOBJ vUNI5 = DcpInfoMgrdao.DCPInfoUpdate_UNI5(dobj);        //  04. DCP Info Update&Insert
            dobj.setRetObject(vUNI5);
        }
        return dobj;
    }
    public DOBJ DCP_PIDCheck(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vSEL2 = DcpInfoMgrdao.DCP_PIDCheck_SEL2(dobj);        //  PID Info
        dobj.setRetObject(vSEL2);
        if( dobj.getRetObject("SEL2").getRecord().getInt("CNT") == 0)
        {
            VOBJ vSEL5 = DcpInfoMgrdao.DCP_PIDCheck_SEL5(dobj);        //  AUTH_CODE
            dobj.setRetObject(vSEL5);
        }
        return dobj;
    }
    public DOBJ DCPSearchInfo(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vSEL8 = DcpInfoMgrdao.DCPSearchInfo_SEL8(dobj);        //  SUB_PID Search(HP1DM12T)
        dobj.setRetObject(vSEL8);
        VOBJ vSEL4 = DcpInfoMgrdao.DCPSearchInfo_SEL4(dobj);        //  PID Search
        dobj.setRetObject(vSEL4);
        VOBJ vSEL12 = DcpInfoMgrdao.DCPSearchInfo_SEL12(dobj);        //  DCP (CNT 0)
        dobj.setRetObject(vSEL12);
        if( dobj.getRetObject("SEL12").getRecord().getInt("CNT") == 1)
        {
            VOBJ vSEL10 = DcpInfoMgrdao.DCPSearchInfo_SEL10(dobj);        //  DCP Search
            dobj.setRetObject(vSEL10);
            VOBJ vSEL6 = DcpInfoMgrdao.DCPSearchInfo_SEL6(dobj);        //  FILE_NAME
            dobj.setRetObject(vSEL6);
        }
        else if( dobj.getRetObject("SEL12").getRecord().getInt("CNT") == 0)
        {
            VOBJ vSEL14 = DcpInfoMgrdao.DCPSearchInfo_SEL14(dobj);        //  Required Return
            dobj.setRetObject(vSEL14);
            VOBJ vSEL11 = DcpInfoMgrdao.DCPSearchInfo_SEL11(dobj);        //  DCP (CNT 0)
            dobj.setRetObject(vSEL11);
            VOBJ vSEL6 = DcpInfoMgrdao.DCPSearchInfo_SEL6(dobj);        //  FILE_NAME
            dobj.setRetObject(vSEL6);
        }
        return dobj;
    }
    public DOBJ ApprovalDataSearch(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vSEL2 = DcpInfoMgrdao.ApprovalDataSearch_SEL2(dobj);        //  A.DCP(APPROVAL_DATE)
        dobj.setRetObject(vSEL2);
        VOBJ vSEL4 = DcpInfoMgrdao.ApprovalDataSearch_SEL4(dobj);        //  P.DCP(APPROVAL_DATE)
        dobj.setRetObject(vSEL4);
        VOBJ vSEL6 = DcpInfoMgrdao.ApprovalDataSearch_SEL6(dobj);        //  T.DCP(APPROVAL_DATE)
        dobj.setRetObject(vSEL6);
        VOBJ vSEL8 = DcpInfoMgrdao.ApprovalDataSearch_SEL8(dobj);        //  M.DCP(APPROVAL_DATE)
        dobj.setRetObject(vSEL8);
        VOBJ vSEL10 = DcpInfoMgrdao.ApprovalDataSearch_SEL10(dobj);        //  E.DCP(APPROVAL_DATE)
        dobj.setRetObject(vSEL10);
        return dobj;
    }
    public DOBJ loadpage(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        return dobj;
    }
}