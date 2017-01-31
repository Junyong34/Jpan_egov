
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
import hpms.batch.service.BP_HP3D002T;
@Service("BP_HP3D002T20160930162714Service")
/**
*/
public class BP_HP3D002TImpl extends AbstractServiceImpl implements BP_HP3D002T
{
    @Autowired
    private BP_HP3D002TDao BP_HP3D002Tdao;
    /**
    BP_HP2D002T → BP_HP3D101T
    */
    public DOBJ BP_HP3D101T(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vXIUD3 = BP_HP3D002Tdao.BP_HP3D101T_XIUD3(dobj);        //  HP3D101T delete
        dobj.setRetObject(vXIUD3);
        VOBJ vSEL1 = BP_HP3D002Tdao.BP_HP3D101T_SEL1(dobj);        //  HP3D002T Sum
        dobj.setRetObject(vSEL1);
        VOBJ vINS5 = BP_HP3D002Tdao.BP_HP3D101T_INS5(dobj);        //  Summary Save
        dobj.setRetObject(vINS5);
        return dobj;
    }
    public DOBJ BP_HP3D002T_ALC(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vXIUD1 = BP_HP3D002Tdao.BP_HP3D002T_ALC_XIUD1(dobj);        //  ALC Result delete
        dobj.setRetObject(vXIUD1);
        VOBJ vSEL2 = BP_HP3D002Tdao.BP_HP3D002T_ALC_SEL2(dobj);        //  Dist Target
        dobj.setRetObject(vSEL2);
        this.BP_HP3D002T_ALC_MPD4(dobj);        //  Multi Rec Prcs
        if(dobj.getRtncode() == 9)
        {
            throw new Exception();
        }
        return dobj;
    }
    // Multi Rec Prcs
    private DOBJ BP_HP3D002T_ALC_MPD4(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"MPD4", "Multi Rec Prcs" );
        dobj.setRtnname("MPD4");
        VOBJ dvobj = dobj.getRetObject("SEL2");         //Dist Target Input Object(CALLBP_HP3D002T_ALC_SEL2)
        dvobj.Println("MPD4");
        VOBJ       rvobj= null;
        dvobj.first();
        while(dvobj.next())
        {
            if(true)
            {
                dobj.resetObject("SEL14,SEL12,INS13,SEL13,SEL4,INS18");
                dobj.setRetObject(dvobj.getRecVobj("R"));
                VOBJ vSEL14 = BP_HP3D002Tdao.BP_HP3D002T_ALC_SEL14(dobj);        //  SEL HP1DM09T
                dobj.setRetObject(vSEL14);
                if(!dobj.getRetObject("SEL14").getRecord().get("DST_HPMS_ID").equals("") && dobj.getRetObject("SEL14").getRecord().get("CALC_TYPE").equals(32))
                {
                    VOBJ vSEL13 = BP_HP3D002Tdao.BP_HP3D002T_ALC_SEL13(dobj);        //  MP Data Check
                    dobj.setRetObject(vSEL13);
                    if( dobj.getRetObject("SEL13").getRecord().getInt("CNT") == 0)
                    {
                        VOBJ vSEL4 = BP_HP3D002Tdao.BP_HP3D002T_ALC_SEL4(dobj);        //  SEL MP Data
                        dobj.setRetObject(vSEL4);
                        VOBJ vINS18 = BP_HP3D002Tdao.BP_HP3D002T_ALC_INS18(dobj);        //  MP Data Save
                        dobj.setRetObject(vINS18);
                    }
                }
                else if(!dobj.getRetObject("SEL14").getRecord().get("DST_HPMS_ID").equals("") && dobj.getRetObject("SEL14").getRecord().get("CALC_TYPE").equals(31))
                {
                    VOBJ vSEL12 = BP_HP3D002Tdao.BP_HP3D002T_ALC_SEL12(dobj);        //  Dist Proc by PID
                    dobj.setRetObject(vSEL12);
                    VOBJ vINS13 = BP_HP3D002Tdao.BP_HP3D002T_ALC_INS13(dobj);        //  Dist Data Save
                    dobj.setRetObject(vINS13);
                }
            }
        }
        return dobj;
    }
    public DOBJ BP_HP3D002T_CAL(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vXIUD1 = BP_HP3D002Tdao.BP_HP3D002T_CAL_XIUD1(dobj);        //  CAL Result delete
        dobj.setRetObject(vXIUD1);
        VOBJ vSEL2 = BP_HP3D002Tdao.BP_HP3D002T_CAL_SEL2(dobj);        //  Sel HP1M104T
        dobj.setRetObject(vSEL2);
        this.BP_HP3D002T_CAL_MPD4(dobj);        //  Multi Proc
        if(dobj.getRtncode() == 9)
        {
            throw new Exception();
        }
        return dobj;
    }
    // Multi Proc
    private DOBJ BP_HP3D002T_CAL_MPD4(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"MPD4", "Multi Proc" );
        dobj.setRtnname("MPD4");
        VOBJ dvobj = dobj.getRetObject("SEL2");         //Sel HP1M104T Input Object(CALLBP_HP3D002T_CAL_SEL2)
        VOBJ       rvobj= null;
        dvobj.first();
        while(dvobj.next())
        {
            if(dvobj.getRecord().get("CALC_FUNC").equals("sum"))
            {
                dobj.resetObject("SEL12,SEL9,INS14");
                dobj.setRetObject(dvobj.getRecVobj("R"));
                VOBJ vSEL12 = BP_HP3D002Tdao.BP_HP3D002T_CAL_SEL12(dobj);        //  Sel Oper Target
                dobj.setRetObject(vSEL12);
                VOBJ vSEL9 = BP_HP3D002Tdao.BP_HP3D002T_CAL_SEL9(dobj);        //  Sum Prcs
                dobj.setRetObject(vSEL9);
                VOBJ vINS14 = BP_HP3D002Tdao.BP_HP3D002T_CAL_INS14(dobj);        //  Result Item Save
                dobj.setRetObject(vINS14);
            }
            else
            {
                dobj.resetObject("SEL11,SEL13,INS22");
                dobj.setRetObject(dvobj.getRecVobj("R"));
                VOBJ vSEL11 = BP_HP3D002Tdao.BP_HP3D002T_CAL_SEL11(dobj);        //  Sel Oper Target
                dobj.setRetObject(vSEL11);
                VOBJ vSEL13 = BP_HP3D002Tdao.BP_HP3D002T_CAL_SEL13(dobj);        //  Binary Calc
                dobj.setRetObject(vSEL13);
                VOBJ vINS22 = BP_HP3D002Tdao.BP_HP3D002T_CAL_INS22(dobj);        //  Result Item Save
                dobj.setRetObject(vINS22);
            }
        }
        return dobj;
    }
    /**
    HP3D001T → HP3D002T
    */
    public DOBJ BP_HP3D002T(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vXIUD16 = BP_HP3D002Tdao.BP_HP3D002T_XIUD16(dobj);        //  HP3D002T delete
        dobj.setRetObject(vXIUD16);
        VOBJ vSEL3 = BP_HP3D002Tdao.BP_HP3D002T_SEL3(dobj);        //  HP3D001T Select
        dobj.setRetObject(vSEL3);
        VOBJ vINS14 = BP_HP3D002Tdao.BP_HP3D002T_INS14(dobj);        //  HP3D002T save
        dobj.setRetObject(vINS14);
        return dobj;
    }
}