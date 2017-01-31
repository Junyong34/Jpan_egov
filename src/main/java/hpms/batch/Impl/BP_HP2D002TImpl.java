
package hpms.batch.Impl;
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
import hpms.batch.dao.*;
import hpms.batch.service.BP_HP2D002T;
@Service("BP_HP2D002T20160930162723Service")
/**
*/
public class BP_HP2D002TImpl extends AbstractServiceImpl implements BP_HP2D002T
{
    @Autowired
    private BP_HP2D002TDao BP_HP2D002Tdao;
    public DOBJ BP_HP2D101T(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vXIUD3 = BP_HP2D002Tdao.BP_HP2D101T_XIUD3(dobj);        //  HP2D101T delete
        dobj.setRetObject(vXIUD3);
        VOBJ vSEL1 = BP_HP2D002Tdao.BP_HP2D101T_SEL1(dobj);        //  HP3D002T Sum
        dobj.setRetObject(vSEL1);
        VOBJ vINS5 = BP_HP2D002Tdao.BP_HP2D101T_INS5(dobj);        //  Summary Save
        dobj.setRetObject(vINS5);
        return dobj;
    }
    /**
    2016.11.28 Change Latest MP Data Selection Process
    1. remove MP Data revision rule
    2.select MP_YYYY Data for fiscal year
    */
    public DOBJ BP_HP2D002T_ALC(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vXIUD1 = BP_HP2D002Tdao.BP_HP2D002T_ALC_XIUD1(dobj);        //  ALC Result delete
        dobj.setRetObject(vXIUD1);
        VOBJ vSEL2 = BP_HP2D002Tdao.BP_HP2D002T_ALC_SEL2(dobj);        //  Dist Target
        dobj.setRetObject(vSEL2);
        this.BP_HP2D002T_ALC_MPD4(dobj);        //  Multi Rec Prcs
        if(dobj.getRtncode() == 9)
        {
            throw new Exception();
        }
        return dobj;
    }
    // Multi Rec Prcs
    private DOBJ BP_HP2D002T_ALC_MPD4(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"MPD4", "Multi Rec Prcs" );
        dobj.setRtnname("MPD4");
        VOBJ dvobj = dobj.getRetObject("SEL2");         //Dist Target Input Object(CALLBP_HP2D002T_ALC_SEL2)
        dvobj.Println("MPD4");
        VOBJ       rvobj= null;
        dvobj.first();
        while(dvobj.next())
        {
            if(true)
            {
                dobj.resetObject("SEL3,SEL17,SEL4,INS41,SEL14,SEL12,INS13,SEL16,INS17");
                dobj.setRetObject(dvobj.getRecVobj("R"));
                VOBJ vSEL3 = BP_HP2D002Tdao.BP_HP2D002T_ALC_SEL3(dobj);        //  SEL FCST Master
                dobj.setRetObject(vSEL3);
                if( dobj.getRetObject("SEL3").getRecord().getInt("DST_TYPE") == 1)
                {
                    VOBJ vSEL14 = BP_HP2D002Tdao.BP_HP2D002T_ALC_SEL14(dobj);        //  SEL HP1DM09T
                    dobj.setRetObject(vSEL14);
                    if(!dobj.getRetObject("SEL14").getRecord().get("DST_HPMS_ID").equals("") && dobj.getRetObject("SEL14").getRecord().get("CALC_TYPE").equals(32))
                    {
                        VOBJ vSEL16 = BP_HP2D002Tdao.BP_HP2D002T_ALC_SEL16(dobj);        //  Dist Proc by Dept
                        dobj.setRetObject(vSEL16);
                        VOBJ vINS17 = BP_HP2D002Tdao.BP_HP2D002T_ALC_INS17(dobj);        //  Dist Data Save
                        dobj.setRetObject(vINS17);
                    }
                    else if(!dobj.getRetObject("SEL14").getRecord().get("DST_HPMS_ID").equals("") && dobj.getRetObject("SEL14").getRecord().get("CALC_TYPE").equals(31))
                    {
                        VOBJ vSEL12 = BP_HP2D002Tdao.BP_HP2D002T_ALC_SEL12(dobj);        //  Dist Proc by PID
                        dobj.setRetObject(vSEL12);
                        VOBJ vINS13 = BP_HP2D002Tdao.BP_HP2D002T_ALC_INS13(dobj);        //  Dist Data Save
                        dobj.setRetObject(vINS13);
                    }
                }
                else if( dobj.getRetObject("SEL3").getRecord().getInt("DST_TYPE") == 2)
                {
                    VOBJ vSEL17 = BP_HP2D002Tdao.BP_HP2D002T_ALC_SEL17(dobj);        //  MP Data Check
                    dobj.setRetObject(vSEL17);
                    if( dobj.getRetObject("SEL17").getRecord().getInt("CNT") == 0)
                    {
                        VOBJ vSEL4 = BP_HP2D002Tdao.BP_HP2D002T_ALC_SEL4(dobj);        //  SEL MP Data
                        dobj.setRetObject(vSEL4);
                        VOBJ vINS41 = BP_HP2D002Tdao.BP_HP2D002T_ALC_INS41(dobj);        //  MP Data Save
                        dobj.setRetObject(vINS41);
                    }
                }
            }
        }
        return dobj;
    }
    public DOBJ BP_HP2D002T_CAL(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vXIUD1 = BP_HP2D002Tdao.BP_HP2D002T_CAL_XIUD1(dobj);        //  CAL Result delete
        dobj.setRetObject(vXIUD1);
        VOBJ vSEL2 = BP_HP2D002Tdao.BP_HP2D002T_CAL_SEL2(dobj);        //  Sel HP1M103T
        dobj.setRetObject(vSEL2);
        this.BP_HP2D002T_CAL_MPD4(dobj);        //  Multi Proc
        if(dobj.getRtncode() == 9)
        {
            throw new Exception();
        }
        return dobj;
    }
    // Multi Proc
    private DOBJ BP_HP2D002T_CAL_MPD4(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"MPD4", "Multi Proc" );
        dobj.setRtnname("MPD4");
        VOBJ dvobj = dobj.getRetObject("SEL2");         //Sel HP1M103T Input Object(CALLBP_HP2D002T_CAL_SEL2)
        dvobj.Println("MPD4");
        VOBJ       rvobj= null;
        dvobj.first();
        while(dvobj.next())
        {
            if(dvobj.getRecord().get("CALC_FUNC").equals("sum"))
            {
                dobj.resetObject("SUM_SRC,SEL9,INS14");
                dobj.setRetObject(dvobj.getRecVobj("R"));
                VOBJ vSUM_SRC = BP_HP2D002Tdao.BP_HP2D002T_CAL_SUM_SRC(dobj);        //  SUM_SRC
                dobj.setRetObject(vSUM_SRC);
                VOBJ vSEL9 = BP_HP2D002Tdao.BP_HP2D002T_CAL_SEL9(dobj);        //  Sum Prcs
                dobj.setRetObject(vSEL9);
                VOBJ vINS14 = BP_HP2D002Tdao.BP_HP2D002T_CAL_INS14(dobj);        //  Result Item Save
                dobj.setRetObject(vINS14);
            }
            else
            {
                dobj.resetObject("OPR_SRC,SEL13,INS22");
                dobj.setRetObject(dvobj.getRecVobj("R"));
                VOBJ vOPR_SRC = BP_HP2D002Tdao.BP_HP2D002T_CAL_OPR_SRC(dobj);        //  Sel Oper Target
                dobj.setRetObject(vOPR_SRC);
                VOBJ vSEL13 = BP_HP2D002Tdao.BP_HP2D002T_CAL_SEL13(dobj);        //  Calc
                dobj.setRetObject(vSEL13);
                VOBJ vINS22 = BP_HP2D002Tdao.BP_HP2D002T_CAL_INS22(dobj);        //  Result Item Save
                dobj.setRetObject(vINS22);
            }
        }
        return dobj;
    }
    public DOBJ BP_HP2D002T_DPR(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vXIUD2 = BP_HP2D002Tdao.BP_HP2D002T_DPR_XIUD2(dobj);        //  Depre rows delete
        dobj.setRetObject(vXIUD2);
        VOBJ vSEL3 = BP_HP2D002Tdao.BP_HP2D002T_DPR_SEL3(dobj);        //  Select to depre rows
        dobj.setRetObject(vSEL3);
        this.BP_HP2D002T_DPR_MPD4(dobj);        //  Multi Rows Process
        if(dobj.getRtncode() == 9)
        {
            throw new Exception();
        }
        return dobj;
    }
    // Multi Rows Process
    private DOBJ BP_HP2D002T_DPR_MPD4(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"MPD4", "Multi Rows Process" );
        dobj.setRtnname("MPD4");
        VOBJ dvobj = dobj.getRetObject("SEL3");         //Select to depre rows Input Object(CALLBP_HP2D002T_DPR_SEL3)
        dvobj.Println("MPD4");
        VOBJ       rvobj= null;
        dvobj.first();
        while(dvobj.next())
        {
            if(dvobj.getRecord().get("GBN").equals("IV"))
            {
                dobj.resetObject("SEL4,SEL5,SEL6,SEL10,INS13,NEW_DRP");
                dobj.setRetObject(dvobj.getRecVobj("R"));
                VOBJ vSEL4 = BP_HP2D002Tdao.BP_HP2D002T_DPR_SEL4(dobj);        //  Sel Depre Months
                dobj.setRetObject(vSEL4);
                if( dobj.getRetObject("SEL4").getRecord().getInt("DEPRECIATION_MONTHS") > 0)
                {
                    VOBJ vSEL5 = BP_HP2D002Tdao.BP_HP2D002T_DPR_SEL5(dobj);        //  latest M_DCP_PLAN_YYYYMM
                    dobj.setRetObject(vSEL5);
                    VOBJ vSEL6 = BP_HP2D002Tdao.BP_HP2D002T_DPR_SEL6(dobj);        //  Mass Cost Dept
                    dobj.setRetObject(vSEL6);
                    VOBJ vSEL10 = BP_HP2D002Tdao.BP_HP2D002T_DPR_SEL10(dobj);        //  Depre Process
                    dobj.setRetObject(vSEL10);
                    VOBJ vINS13 = BP_HP2D002Tdao.BP_HP2D002T_DPR_INS13(dobj);        //  Derpre Records Save
                    dobj.setRetObject(vINS13);
                    VOBJ vNEW_DRP = BP_HP2D002Tdao.BP_HP2D002T_DPR_NEW_DRP(dobj);        //  check use
                    dobj.setRetObject(vNEW_DRP);
                }
            }
            else
            {
                dobj.resetObject("SEL11,SEL21,SEL20,XIUD26,NEW_DRP2");
                dobj.setRetObject(dvobj.getRecVobj("R"));
                VOBJ vSEL11 = BP_HP2D002Tdao.BP_HP2D002T_DPR_SEL11(dobj);        //  latest M_DCP_PLAN_YYYYMM
                dobj.setRetObject(vSEL11);
                if(   dobj.getRetObject("R").getRecord().getInt("YYYYMM") >=   dobj.getRetObject("SEL11").getRecord().getInt("M_DCP_PLAN_YYYYMM"))
                {
                    VOBJ vSEL21 = BP_HP2D002Tdao.BP_HP2D002T_DPR_SEL21(dobj);        //  Mass Cost Dept
                    dobj.setRetObject(vSEL21);
                    VOBJ vSEL20 = BP_HP2D002Tdao.BP_HP2D002T_DPR_SEL20(dobj);        //  Mass Dept Change
                    dobj.setRetObject(vSEL20);
                    VOBJ vXIUD26 = BP_HP2D002Tdao.BP_HP2D002T_DPR_XIUD26(dobj);        //  RD update
                    dobj.setRetObject(vXIUD26);
                    VOBJ vNEW_DRP2 = BP_HP2D002Tdao.BP_HP2D002T_DPR_NEW_DRP2(dobj);        //  check use
                    dobj.setRetObject(vNEW_DRP2);
                }
            }
        }
        return dobj;
    }
    public DOBJ BP_HP2D002T(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vXIUD16 = BP_HP2D002Tdao.BP_HP2D002T_XIUD16(dobj);        //  HP2D002T delete
        dobj.setRetObject(vXIUD16);
        VOBJ vSEL3 = BP_HP2D002Tdao.BP_HP2D002T_SEL3(dobj);        //  HP2D001T Select
        dobj.setRetObject(vSEL3);
        VOBJ vINS14 = BP_HP2D002Tdao.BP_HP2D002T_INS14(dobj);        //  HP3D002T save
        dobj.setRetObject(vINS14);
        return dobj;
    }
    public DOBJ BP_HP2D002T_TZ(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vSEL1 = BP_HP2D002Tdao.BP_HP2D002T_TZ_SEL1(dobj);        //  HP3D002T Select
        dobj.setRetObject(vSEL1);
        VOBJ vUNI3 = BP_HP2D002Tdao.BP_HP2D002T_TZ_UNI3(dobj);        //  HP3D002T_TM UNI
        dobj.setRetObject(vUNI3);
        VOBJ vXIUD3 = BP_HP2D002Tdao.BP_HP2D002T_TZ_XIUD3(dobj);        //  HP2D002T delete
        dobj.setRetObject(vXIUD3);
        return dobj;
    }
}