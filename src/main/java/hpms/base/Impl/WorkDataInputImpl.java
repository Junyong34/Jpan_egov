
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
import hpms.base.service.WorkDataInput;
@Service("WorkDataInput20160912111169Service")
/**
*/
public class WorkDataInputImpl extends AbstractServiceImpl implements WorkDataInput
{
    @Autowired
    private WorkDataInputDao WorkDataInputdao;
    /**
    egov Frame Work
    */
    public DOBJ workCountUpload(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vPEX2 = WorkDataInputdao.workCountUpload_PEX2(dobj);        //  ExcelUpload Data
        dobj.setRetObject(vPEX2);
        VOBJ vCVT40 = WorkDataInputdao.workCountUpload_CVT40(dobj);        //  TEST
        dobj.setRetObject(vCVT40);
        if( dobj.getRtncode ( ) == 0)
        {
            VOBJ vER01 = WorkDataInputdao.workCountUpload_ER01(dobj);        //  get LogSequence
            dobj.setRetObject(vER01);
            this.workCountUpload_MPD13(dobj);        //  Error Check(Record)
            if(dobj.getRtncode() == 9)
            {
                throw new Exception();
            }
            if(dobj.getRetmsg ( ).equals(""))
            {
                VOBJ vUNI36 = WorkDataInputdao.workCountUpload_UNI36(dobj);        //  HP3D001T(Insert)
                dobj.setRetObject(vUNI36);
            }
        }
        return dobj;
    }
    // Error Check(Record)
    private DOBJ workCountUpload_MPD13(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"MPD13", "Error Check(Record)" );
        dobj.setRtnname("MPD13");
        VOBJ dvobj = dobj.getRetObject("PEX2");         //ExcelUpload Data Input Object(CALLworkCountUpload_PEX2)
        VOBJ       rvobj= null;
        dvobj.first();
        while(dvobj.next())
        {
            if(true) 
            {
            
                dobj.resetObject("GLV30,SEL17,UNI38,SEL33,SEL35,GLV31");
                dobj.setRetObject(dvobj.getRecVobj("R"));
                ////
                String   FLAGGLV30 = "";         //적용구분
                dobj.setGVValue("FLAG",FLAGGLV30);
                VOBJ vSEL17 = WorkDataInputdao.workCountUpload_SEL17(dobj);        //  MasterSearch(Error Check)
                dobj.setRetObject(vSEL17);
                if(!dobj.getRetObject("R").getRecord().get("UNIT").equals("") && dobj.getRetObject("SEL17").getRecord().getInt("CURRENCY_CNT") == 0)
                
                {
                dobj.setRetmsg("1015");
                    VOBJ vUNI38 = WorkDataInputdao.workCountUpload_UNI38(dobj);        //  WorkTable Save HP2D001W
                    dobj.setRetObject(vUNI38);
                }
                else if( dobj.getRetObject("SEL17").getRecord().getDouble("USE_COMPANY_CNT") == 0)
                
                {
                dobj.setRetmsg("1003");
                    VOBJ vUNI38 = WorkDataInputdao.workCountUpload_UNI38(dobj);        //  WorkTable Save HP2D001W
                    dobj.setRetObject(vUNI38);
                }
                else if( dobj.getRetObject("SEL17").getRecord().getInt("PID_CNT") == 0)
                
                {
                dobj.setRetmsg("1000");
                    VOBJ vUNI38 = WorkDataInputdao.workCountUpload_UNI38(dobj);        //  WorkTable Save HP2D001W
                    dobj.setRetObject(vUNI38);
                }
                else if( dobj.getRetObject("SEL17").getRecord().getInt("HPMS_ID_CNT") == 0)
                
                {
                dobj.setRetmsg("1005");
                    VOBJ vUNI38 = WorkDataInputdao.workCountUpload_UNI38(dobj);        //  WorkTable Save HP2D001W
                    dobj.setRetObject(vUNI38);
                }
                else 
                {
                VOBJ vSEL33 = WorkDataInputdao.workCountUpload_SEL33(dobj);        //  ORG_LVL (Login User)
                    dobj.setRetObject(vSEL33);
                    if(dobj.getRetObject("G").getRecord().get("AUTH_CD").equals("50") && !dobj.getRetObject("G").getRecord().get("COMPANY_CD").equals(dobj.getRetObject("R").getRecord().get("USE_COMPANY_CD")))
                    
                    {
                    dobj.setRetmsg("1019");
                        VOBJ vUNI38 = WorkDataInputdao.workCountUpload_UNI38(dobj);        //  WorkTable Save HP2D001W
                        dobj.setRetObject(vUNI38);
                    }
                    else if(dobj.getRetObject("G").getRecord().get("AUTH_CD").equals("50") && dobj.getRetObject("SEL33").getRecord().getInt("ORG_LVL") >= 3)
                    
                    {
                    VOBJ vSEL35 = WorkDataInputdao.workCountUpload_SEL35(dobj);        //  OrgCode(AUTH) Count
                        dobj.setRetObject(vSEL35);
                        if( dobj.getRetObject("SEL35").getRecord().getInt("CNT") == 0)
                        
                        {
                        dobj.setRetmsg("1018");
                            VOBJ vUNI38 = WorkDataInputdao.workCountUpload_UNI38(dobj);        //  WorkTable Save HP2D001W
                            dobj.setRetObject(vUNI38);
                        }
                        else 
                        {
                        ////
                            String   FLAGGLV31 = "A";         //적용구분
                            dobj.setGVValue("FLAG",FLAGGLV31);
                            VOBJ vUNI38 = WorkDataInputdao.workCountUpload_UNI38(dobj);        //  WorkTable Save HP2D001W
                            dobj.setRetObject(vUNI38);
                        }
                    }
                    else 
                    {
                    ////
                        String   FLAGGLV31 = "A";         //적용구분
                        dobj.setGVValue("FLAG",FLAGGLV31);
                        VOBJ vUNI38 = WorkDataInputdao.workCountUpload_UNI38(dobj);        //  WorkTable Save HP2D001W
                        dobj.setRetObject(vUNI38);
                    }
                }
            }
        }
        return dobj;
    }
    ////
    ////
    ////
    public DOBJ errorConfirm(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vSEL1 = WorkDataInputdao.errorConfirm_SEL1(dobj);        //  get Cost Data
        dobj.setRetObject(vSEL1);
        VOBJ vSEL5 = WorkDataInputdao.errorConfirm_SEL5(dobj);        //  FILE_NAME_Info
        dobj.setRetObject(vSEL5);
        VOBJ vPEX5 = WorkDataInputdao.errorConfirm_PEX5(dobj);        //  ROOTPATH
        dobj.setRetObject(vPEX5);
        VOBJ vFBD4 = WorkDataInputdao.errorConfirm_FBD4(dobj);        //  CSV File Build
        dobj.setRetObject(vFBD4);
        VOBJ vDN01 = WorkDataInputdao.errorConfirm_DN01(dobj);        //  downfile seting
        dobj.setRetObject(vDN01);
        return dobj;
    }
    ////
    ////
    ////
    public DOBJ loadpage(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        return dobj;
    }
}