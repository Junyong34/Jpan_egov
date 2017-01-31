
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
import hpms.base.service.CostInput;
@Service("CostInput20160901101142Service")
/**
*/
public class CostInputImpl extends AbstractServiceImpl implements CostInput
{
    @Autowired
    private CostInputDao CostInputdao;
    public DOBJ CostUpload(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vPEX2 = CostInputdao.CostUpload_PEX2(dobj);        //  ExcelUpload Data
        dobj.setRetObject(vPEX2);
        VOBJ vCVT25 = CostInputdao.CostUpload_CVT25(dobj);        //  TEST
        dobj.setRetObject(vCVT25);
        if( dobj.getRtncode ( ) == 0)
        {
            VOBJ vSEL33 = CostInputdao.CostUpload_SEL33(dobj);        //  get LogSequence
            dobj.setRetObject(vSEL33);
            this.CostUpload_MPD13(dobj);        //  Error Check(Record)
            if(dobj.getRtncode() == 9)
            {
                throw new Exception();
            }
            if(dobj.getRetmsg ( ).equals(""))
            {
                this.CostUpload_MPD26(dobj);        //  Data Delete(Loop)HP2D001T
                if(dobj.getRtncode() == 9)
                {
                    throw new Exception();
                }
            }
        }
        return dobj;
    }
    // Error Check(Record)
    private DOBJ CostUpload_MPD13(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"MPD13", "Error Check(Record)" );
        dobj.setRtnname("MPD13");
        VOBJ dvobj = dobj.getRetObject("PEX2");         //ExcelUpload Data Input Object(CALLCostUpload_PEX2)
        VOBJ       rvobj= null;
        dvobj.first();
        while(dvobj.next())
        {
            if(true) 
            {
            
                dobj.resetObject("GLV26,SEL17,UNI38,GLV27");
                dobj.setRetObject(dvobj.getRecVobj("R"));
                ////
                String   FLAGGLV26 = "";         //적용구분
                dobj.setGVValue("FLAG",FLAGGLV26);
                VOBJ vSEL17 = CostInputdao.CostUpload_SEL17(dobj);        //  MasterSearch(Error Check)
                dobj.setRetObject(vSEL17);
                if(dobj.getRetObject("SEL17").getRecord().getDouble("ITEM_NAME_CNT") == 0 && !dobj.getRetObject("R").getRecord().get("ITEM_NAME").equals("#"))
                
                {
                dobj.setRetmsg("1016");
                    VOBJ vUNI38 = CostInputdao.CostUpload_UNI38(dobj);        //  WorkTable Save HP2DM11W
                    dobj.setRetObject(vUNI38);
                }
                else if( dobj.getRetObject("SEL17").getRecord().getInt("PID_CNT") == 0)
                
                {
                dobj.setRetmsg("1000");
                    VOBJ vUNI38 = CostInputdao.CostUpload_UNI38(dobj);        //  WorkTable Save HP2DM11W
                    dobj.setRetObject(vUNI38);
                }
                else if( dobj.getRetObject("SEL17").getRecord().getInt("HPMS_ID_CNT") == 0)
                
                {
                dobj.setRetmsg("1005");
                    VOBJ vUNI38 = CostInputdao.CostUpload_UNI38(dobj);        //  WorkTable Save HP2DM11W
                    dobj.setRetObject(vUNI38);
                }
                else 
                {
                ////
                    String   FLAGGLV27 = "A";         //적용구분
                    dobj.setGVValue("FLAG",FLAGGLV27);
                    VOBJ vUNI38 = CostInputdao.CostUpload_UNI38(dobj);        //  WorkTable Save HP2DM11W
                    dobj.setRetObject(vUNI38);
                }
            }
        }
        return dobj;
    }
    // Data Delete(Loop)HP2D001T
    private DOBJ CostUpload_MPD26(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"MPD26", "Data Delete(Loop)HP2D001T" );
        dobj.setRtnname("MPD26");
        VOBJ dvobj = dobj.getRetObject("PEX2");         //ExcelUpload Data Input Object(CALLCostUpload_PEX2)
        VOBJ       rvobj= null;
        dvobj.first();
        while(dvobj.next())
        {
            if(true) 
            {
            
                dobj.resetObject("DEL29,INS31");
                dobj.setRetObject(dvobj.getRecVobj("R"));
                VOBJ vDEL29 = CostInputdao.CostUpload_DEL29(dobj);        //  HP2DM11T delete
                dobj.setRetObject(vDEL29);
                VOBJ vINS31 = CostInputdao.CostUpload_INS31(dobj);        //  Data Insert(Loop)HP2D001T
                dobj.setRetObject(vINS31);
            }
        }
        return dobj;
    }
    public DOBJ csvdownload(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vSEL1 = CostInputdao.csvdownload_SEL1(dobj);        //  get Cost Data
        dobj.setRetObject(vSEL1);
        VOBJ vCVT7 = CostInputdao.csvdownload_CVT7(dobj);        //  Rebuild
        dobj.setRetObject(vCVT7);
        VOBJ vSEL5 = CostInputdao.csvdownload_SEL5(dobj);        //  FILE_NAME_Info
        dobj.setRetObject(vSEL5);
        VOBJ vPEX5 = CostInputdao.csvdownload_PEX5(dobj);        //  ROOTPATH
        dobj.setRetObject(vPEX5);
        VOBJ vFBD4 = CostInputdao.csvdownload_FBD4(dobj);        //  CSV File Build
        dobj.setRetObject(vFBD4);
        VOBJ vDN01 = CostInputdao.csvdownload_DN01(dobj);        //  downfile seting
        dobj.setRetObject(vDN01);
        return dobj;
    }
    public DOBJ errorConfirm(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vSEL1 = CostInputdao.errorConfirm_SEL1(dobj);        //  get Cost Data
        dobj.setRetObject(vSEL1);
        VOBJ vSEL5 = CostInputdao.errorConfirm_SEL5(dobj);        //  FILE_NAME_Info
        dobj.setRetObject(vSEL5);
        VOBJ vPEX5 = CostInputdao.errorConfirm_PEX5(dobj);        //  ROOTPATH
        dobj.setRetObject(vPEX5);
        VOBJ vFBD4 = CostInputdao.errorConfirm_FBD4(dobj);        //  CSV File Build
        dobj.setRetObject(vFBD4);
        VOBJ vDN01 = CostInputdao.errorConfirm_DN01(dobj);        //  downfile seting
        dobj.setRetObject(vDN01);
        return dobj;
    }
    public DOBJ Condition_Combo(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vSEL2 = CostInputdao.Condition_Combo_SEL2(dobj);        //  Condition combo
        dobj.setRetObject(vSEL2);
        return dobj;
    }
    public DOBJ loadpage(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        return dobj;
    }
    ////
    ////
    public DOBJ ServiceID00(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vPEX2 = CostInputdao.ServiceID00_PEX2(dobj);        //  ExcelUpload Data
        dobj.setRetObject(vPEX2);
        if( dobj.getRtncode ( ) == 0)
        {
            VOBJ vSEL33 = CostInputdao.ServiceID00_SEL33(dobj);        //  get LogSequence
            dobj.setRetObject(vSEL33);
            this.ServiceID00_MPD13(dobj);        //  Error Check(Record)
            if(dobj.getRtncode() == 9)
            {
                throw new Exception();
            }
            if(dobj.getRetmsg ( ).equals(""))
            {
                this.ServiceID00_MPD26(dobj);        //  Data Delete(Loop)HP2D001T
                if(dobj.getRtncode() == 9)
                {
                    throw new Exception();
                }
                this.ServiceID00_MPD82(dobj);        //  Data Save(Loop)HP2D001T
                if(dobj.getRtncode() == 9)
                {
                    throw new Exception();
                }
            }
        }
        return dobj;
    }
    // Error Check(Record)
    private DOBJ ServiceID00_MPD13(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"MPD13", "Error Check(Record)" );
        dobj.setRtnname("MPD13");
        VOBJ dvobj = dobj.getRetObject("PEX2");         //ExcelUpload Data에서 생성시킨 OBJECT입니다.(CALLServiceID00_PEX2)
        VOBJ       rvobj= null;
        dvobj.first();
        while(dvobj.next())
        {
            if(true)
            {
                dobj.resetObject("GLV26,SEL17,UNI38,GLV27");
                dobj.setRetObject(dvobj.getRecVobj("R"));
                ////
                String   FLAGGLV26 = "";         //적용구분
                dobj.setGVValue("FLAG",FLAGGLV26);
                VOBJ vSEL17 = CostInputdao.ServiceID00_SEL17(dobj);        //  MasterSearch(Error Check)
                dobj.setRetObject(vSEL17);
                if( dobj.getRetObject("SEL17").getRecord().getDouble("ITEM_NAME_CNT") == 0)
                {
                    dobj.setRetmsg("1016");
                    VOBJ vUNI38 = CostInputdao.ServiceID00_UNI38(dobj);        //  WorkTable Save HP2DM11W
                    dobj.setRetObject(vUNI38);
                }
                else if(!dobj.getRetObject("R").getRecord().get("UNIT").equals("") && dobj.getRetObject("SEL17").getRecord().getInt("CURRENCY_CNT") == 0)
                {
                    dobj.setRetmsg("1015");
                    VOBJ vUNI38 = CostInputdao.ServiceID00_UNI38(dobj);        //  WorkTable Save HP2DM11W
                    dobj.setRetObject(vUNI38);
                }
                else if(!dobj.getRetObject("R").getRecord().get("PID").equals("000000") && dobj.getRetObject("SEL17").getRecord().getInt("PID_CNT") == 0)
                {
                    dobj.setRetmsg("1000");
                    VOBJ vUNI38 = CostInputdao.ServiceID00_UNI38(dobj);        //  WorkTable Save HP2DM11W
                    dobj.setRetObject(vUNI38);
                }
                else if( dobj.getRetObject("SEL17").getRecord().getInt("HPMS_ID_CNT") == 0)
                {
                    dobj.setRetmsg("1005");
                    VOBJ vUNI38 = CostInputdao.ServiceID00_UNI38(dobj);        //  WorkTable Save HP2DM11W
                    dobj.setRetObject(vUNI38);
                }
                else
                {
                    ////
                    String   FLAGGLV27 = "A";         //적용구분
                    dobj.setGVValue("FLAG",FLAGGLV27);
                    VOBJ vUNI38 = CostInputdao.ServiceID00_UNI38(dobj);        //  WorkTable Save HP2DM11W
                    dobj.setRetObject(vUNI38);
                }
            }
        }
        return dobj;
    }
    // Data Delete(Loop)HP2D001T
    private DOBJ ServiceID00_MPD26(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"MPD26", "Data Delete(Loop)HP2D001T" );
        dobj.setRtnname("MPD26");
        VOBJ dvobj = dobj.getRetObject("PEX2");         //ExcelUpload Data에서 생성시킨 OBJECT입니다.(CALLServiceID00_PEX2)
        VOBJ       rvobj= null;
        dvobj.first();
        while(dvobj.next())
        {
            if(true)
            {
                dobj.resetObject("DEL29");
                dobj.setRetObject(dvobj.getRecVobj("R"));
                VOBJ vDEL29 = CostInputdao.ServiceID00_DEL29(dobj);        //  HP2DM11T delete
                dobj.setRetObject(vDEL29);
            }
        }
        return dobj;
    }
    // Data Save(Loop)HP2D001T
    private DOBJ ServiceID00_MPD82(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"MPD82", "Data Save(Loop)HP2D001T" );
        dobj.setRtnname("MPD82");
        VOBJ dvobj = dobj.getRetObject("PEX2");         //ExcelUpload Data에서 생성시킨 OBJECT입니다.(CALLServiceID00_PEX2)
        VOBJ       rvobj= null;
        dvobj.first();
        while(dvobj.next())
        {
            if(true)
            {
                dobj.resetObject("INS31");
                dobj.setRetObject(dvobj.getRecVobj("R"));
                VOBJ vINS31 = CostInputdao.ServiceID00_INS31(dobj);        //  Data Insert(Loop)HP2D001T
                dobj.setRetObject(vINS31);
            }
        }
        return dobj;
    }
}