
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
import hpms.base.service.ForecastInfoMgr;
@Service("ForecastInfoMgr2016082417899Service")
/**
*/
public class ForecastInfoMgrImpl extends AbstractServiceImpl implements ForecastInfoMgr
{
    @Autowired
    private ForecastInfoMgrDao ForecastInfoMgrdao;
    /**
    HP2D001T 복사 대상 테이블
    */
    public DOBJ ForecastDataCopy(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        if(dobj.getRetObject("S3").getRecord().get("FROM").equals("A.DCP") || dobj.getRetObject("S3").getRecord().get("FROM").equals("P.DCP") || dobj.getRetObject("S3").getRecord().get("FROM").equals("T.DCP") || dobj.getRetObject("S3").getRecord().get("FROM").equals("M.DCP") || dobj.getRetObject("S3").getRecord().get("FROM").equals("E.DCP"))
        {
            VOBJ vSEL13 = ForecastInfoMgrdao.ForecastDataCopy_SEL13(dobj);        //  MAX(HP2D002T_TZ )
            dobj.setRetObject(vSEL13);
            VOBJ vSEL41 = ForecastInfoMgrdao.ForecastDataCopy_SEL41(dobj);        //  Forecast select delete(HP2D002T_TZ)
            dobj.setRetObject(vSEL41);
            VOBJ vDEL39 = ForecastInfoMgrdao.ForecastDataCopy_DEL39(dobj);        //  Forecast delete(HP2D001T)
            dobj.setRetObject(vDEL39);
            VOBJ vSEL18 = ForecastInfoMgrdao.ForecastDataCopy_SEL18(dobj);        //  MP MTP LMP(HP2D002T_TZ )
            dobj.setRetObject(vSEL18);
            VOBJ vINS16 = ForecastInfoMgrdao.ForecastDataCopy_INS16(dobj);        //  Forecast INSERT(HP2D001T)
            dobj.setRetObject(vINS16);
        }
        else if(!dobj.getRetObject("S3").getRecord().get("FROM").equals("MP") || !dobj.getRetObject("S3").getRecord().get("FROM").equals("MTP") || !dobj.getRetObject("S3").getRecord().get("FROM").equals("LMP") || !dobj.getRetObject("S3").getRecord().get("FROM").equals("A.DCP") || !dobj.getRetObject("S3").getRecord().get("FROM").equals("P.DCP") || !dobj.getRetObject("S3").getRecord().get("FROM").equals("T.DCP") || !dobj.getRetObject("S3").getRecord().get("FROM").equals("M.DCP") || !dobj.getRetObject("S3").getRecord().get("FROM").equals("E.DCP"))
        {
            VOBJ vSEL20 = ForecastInfoMgrdao.ForecastDataCopy_SEL20(dobj);        //  Forecast select delete(HP2D001T)
            dobj.setRetObject(vSEL20);
            VOBJ vDEL10 = ForecastInfoMgrdao.ForecastDataCopy_DEL10(dobj);        //  Forecast delete(HP2D001T)
            dobj.setRetObject(vDEL10);
            VOBJ vSEL12 = ForecastInfoMgrdao.ForecastDataCopy_SEL12(dobj);        //  Forecast Select(HP2D001T)
            dobj.setRetObject(vSEL12);
            VOBJ vINS14 = ForecastInfoMgrdao.ForecastDataCopy_INS14(dobj);        //  Forecast INSERT(HP2D001T)
            dobj.setRetObject(vINS14);
        }
        else if(dobj.getRetObject("S3").getRecord().get("FROM").equals("MP") || dobj.getRetObject("S3").getRecord().get("FROM").equals("MTP") || dobj.getRetObject("S3").getRecord().get("FROM").equals("LMP"))
        {
            VOBJ vSEL11 = ForecastInfoMgrdao.ForecastDataCopy_SEL11(dobj);        //  MAX(HP2D002T_TZ )
            dobj.setRetObject(vSEL11);
            VOBJ vSEL17 = ForecastInfoMgrdao.ForecastDataCopy_SEL17(dobj);        //  MP MTP LMP(HP2D002T_TZ )
            dobj.setRetObject(vSEL17);
            VOBJ vINS15 = ForecastInfoMgrdao.ForecastDataCopy_INS15(dobj);        //  Forecast INSERT(HP2D001T)
            dobj.setRetObject(vINS15);
        }
        return dobj;
    }
    public DOBJ ForecastDataCopy2(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vSEL9 = ForecastInfoMgrdao.ForecastDataCopy2_SEL9(dobj);        //  Tree ORG
        dobj.setRetObject(vSEL9);
        if(dobj.getRetObject("S4").getRecord().get("FROM").equals("FCST01") || dobj.getRetObject("S4").getRecord().get("FROM").equals("FCST02") || dobj.getRetObject("S4").getRecord().get("FROM").equals("FCST03") || dobj.getRetObject("S4").getRecord().get("FROM").equals("FCST04") || dobj.getRetObject("S4").getRecord().get("FROM").equals("FCST05") || dobj.getRetObject("S4").getRecord().get("FROM").equals("FCST06") || dobj.getRetObject("S4").getRecord().get("FROM").equals("FCST07") || dobj.getRetObject("S4").getRecord().get("FROM").equals("FCST08") || dobj.getRetObject("S4").getRecord().get("FROM").equals("FCST09") || dobj.getRetObject("S4").getRecord().get("FROM").equals("FCST10"))
        {
            this.ForecastDataCopy2_MPD11(dobj);        //  loop
            if(dobj.getRtncode() == 9)
            {
                throw new Exception();
            }
        }
        else if(dobj.getRetObject("S4").getRecord().get("FROM").equals("MP") || dobj.getRetObject("S4").getRecord().get("FROM").equals("MTP") || dobj.getRetObject("S4").getRecord().get("FROM").equals("LMP"))
        {
            this.ForecastDataCopy2_MPD13(dobj);        //  loop
            if(dobj.getRtncode() == 9)
            {
                throw new Exception();
            }
        }
        else if(dobj.getRetObject("S4").getRecord().get("FROM").equals("A.DCP") || dobj.getRetObject("S4").getRecord().get("FROM").equals("P.DCP") || dobj.getRetObject("S4").getRecord().get("FROM").equals("T.DCP") || dobj.getRetObject("S4").getRecord().get("FROM").equals("M.DCP") || dobj.getRetObject("S4").getRecord().get("FROM").equals("E.DCP"))
        {
            this.ForecastDataCopy2_MPD17(dobj);        //  loop
            if(dobj.getRtncode() == 9)
            {
                throw new Exception();
            }
        }
        return dobj;
    }
    // loop
    private DOBJ ForecastDataCopy2_MPD13(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"MPD13", "loop" );
        dobj.setRtnname("MPD13");
        VOBJ dvobj = dobj.getRetObject("SEL9");         //Tree ORG Input Object(CALLForecastDataCopy2_SEL9)
        VOBJ       rvobj= null;
        dvobj.first();
        while(dvobj.next())
        {
            if(true)
            {
                dobj.resetObject("SEL39,SEL47,DEL46,SEL16,INS17");
                dobj.setRetObject(dvobj.getRecVobj("R"));
                VOBJ vSEL39 = ForecastInfoMgrdao.ForecastDataCopy2_SEL39(dobj);        //  MAX(HP2D002T_TZ)
                dobj.setRetObject(vSEL39);
                VOBJ vSEL47 = ForecastInfoMgrdao.ForecastDataCopy2_SEL47(dobj);        //  Forecast Select(HP2D002T_TZ)
                dobj.setRetObject(vSEL47);
                VOBJ vDEL46 = ForecastInfoMgrdao.ForecastDataCopy2_DEL46(dobj);        //  Forecast delete(HP2D001T)
                dobj.setRetObject(vDEL46);
                VOBJ vSEL16 = ForecastInfoMgrdao.ForecastDataCopy2_SEL16(dobj);        //  Forecast Select(HP2D002T_TZ)
                dobj.setRetObject(vSEL16);
                VOBJ vINS17 = ForecastInfoMgrdao.ForecastDataCopy2_INS17(dobj);        //  Forecast INSERT(HP2D001T)
                dobj.setRetObject(vINS17);
            }
        }
        return dobj;
    }
    // loop
    private DOBJ ForecastDataCopy2_MPD11(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"MPD11", "loop" );
        dobj.setRtnname("MPD11");
        VOBJ dvobj = dobj.getRetObject("SEL9");         //Tree ORG Input Object(CALLForecastDataCopy2_SEL9)
        VOBJ       rvobj= null;
        dvobj.first();
        while(dvobj.next())
        {
            if(true)
            {
                dobj.resetObject("SEL23,DEL22,SEL12,INS14");
                dobj.setRetObject(dvobj.getRecVobj("R"));
                VOBJ vSEL23 = ForecastInfoMgrdao.ForecastDataCopy2_SEL23(dobj);        //  Forecast Select(HP2D001T)
                dobj.setRetObject(vSEL23);
                VOBJ vDEL22 = ForecastInfoMgrdao.ForecastDataCopy2_DEL22(dobj);        //  Forecast delete(HP2D001T)
                dobj.setRetObject(vDEL22);
                VOBJ vSEL12 = ForecastInfoMgrdao.ForecastDataCopy2_SEL12(dobj);        //  Forecast Select(HP2D001T)
                dobj.setRetObject(vSEL12);
                VOBJ vINS14 = ForecastInfoMgrdao.ForecastDataCopy2_INS14(dobj);        //  Forecast INSERT(HP2D001T)
                dobj.setRetObject(vINS14);
            }
        }
        return dobj;
    }
    // loop
    private DOBJ ForecastDataCopy2_MPD17(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"MPD17", "loop" );
        dobj.setRtnname("MPD17");
        VOBJ dvobj = dobj.getRetObject("SEL9");         //Tree ORG Input Object(CALLForecastDataCopy2_SEL9)
        VOBJ       rvobj= null;
        dvobj.first();
        while(dvobj.next())
        {
            if(true)
            {
                dobj.resetObject("SEL40,SEL21,DEL10,SEL20,INS21");
                dobj.setRetObject(dvobj.getRecVobj("R"));
                VOBJ vSEL40 = ForecastInfoMgrdao.ForecastDataCopy2_SEL40(dobj);        //  MAX(HP2D002T_TZ)
                dobj.setRetObject(vSEL40);
                VOBJ vSEL21 = ForecastInfoMgrdao.ForecastDataCopy2_SEL21(dobj);        //  Forecast Select(HP2D002T_TZ)
                dobj.setRetObject(vSEL21);
                VOBJ vDEL10 = ForecastInfoMgrdao.ForecastDataCopy2_DEL10(dobj);        //  Forecast delete(HP2D001T)
                dobj.setRetObject(vDEL10);
                VOBJ vSEL20 = ForecastInfoMgrdao.ForecastDataCopy2_SEL20(dobj);        //  Forecast Select(HP2D002T_TZ)
                dobj.setRetObject(vSEL20);
                VOBJ vINS21 = ForecastInfoMgrdao.ForecastDataCopy2_INS21(dobj);        //  Forecast INSERT(HP2D001T)
                dobj.setRetObject(vINS21);
            }
        }
        return dobj;
    }
    public DOBJ Forecast_ORGCheck(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vSEL2 = ForecastInfoMgrdao.Forecast_ORGCheck_SEL2(dobj);        //  Input ORG
        dobj.setRetObject(vSEL2);
        if( dobj.getRetObject("SEL2").getRecord().getInt("CNT") == 0)
        {
            VOBJ vSEL4 = ForecastInfoMgrdao.Forecast_ORGCheck_SEL4(dobj);        //  AUTH_CODE
            dobj.setRetObject(vSEL4);
        }
        return dobj;
    }
    public DOBJ Forecast_PIDcheck(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vSEL2 = ForecastInfoMgrdao.Forecast_PIDcheck_SEL2(dobj);        //  PID Info
        dobj.setRetObject(vSEL2);
        if( dobj.getRetObject("SEL2").getRecord().getInt("CNT") == 0)
        {
            VOBJ vSEL4 = ForecastInfoMgrdao.Forecast_PIDcheck_SEL4(dobj);        //  AUTH_CODE
            dobj.setRetObject(vSEL4);
        }
        return dobj;
    }
    public DOBJ FCST_TITLE_COMBO(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vSEL2 = ForecastInfoMgrdao.FCST_TITLE_COMBO_SEL2(dobj);        //  FCST_TILTE Conn
        dobj.setRetObject(vSEL2);
        VOBJ vSEL4 = ForecastInfoMgrdao.FCST_TITLE_COMBO_SEL4(dobj);        //  TO_FCST_TITLE conn
        dobj.setRetObject(vSEL4);
        VOBJ vSEL6 = ForecastInfoMgrdao.FCST_TITLE_COMBO_SEL6(dobj);        //  ORG_FCST_TITLE conn
        dobj.setRetObject(vSEL6);
        VOBJ vSEL9 = ForecastInfoMgrdao.FCST_TITLE_COMBO_SEL9(dobj);        //  ORG_FROM_TITLE conn(COPY)
        dobj.setRetObject(vSEL9);
        VOBJ vSEL8 = ForecastInfoMgrdao.FCST_TITLE_COMBO_SEL8(dobj);        //  ORG_TO_FCST_TITLE conn(COPY)
        dobj.setRetObject(vSEL8);
        return dobj;
    }
    public DOBJ FCSTCOPYCombo(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vSEL1 = ForecastInfoMgrdao.FCSTCOPYCombo_SEL1(dobj);        //  DATA_TYPE
        dobj.setRetObject(vSEL1);
        return dobj;
    }
    public DOBJ ServiceID00(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vSEL3 = ForecastInfoMgrdao.ServiceID00_SEL3(dobj);        //  CompanyList
        dobj.setRetObject(vSEL3);
        VOBJ vMRG1 = ForecastInfoMgrdao.ServiceID00_MRG1(dobj);        //  CompanyCombo
        dobj.setRetObject(vMRG1);
        VOBJ vSEL5 = ForecastInfoMgrdao.ServiceID00_SEL5(dobj);        //  DATA_TYPE
        dobj.setRetObject(vSEL5);
        return dobj;
    }
    public DOBJ ForecastTitleSet2(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vUNI2 = ForecastInfoMgrdao.ForecastTitleSet2_UNI2(dobj);        //  forecast title Save2
        dobj.setRetObject(vUNI2);
        return dobj;
    }
    public DOBJ ForecastTitleSet(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vUNI3 = ForecastInfoMgrdao.ForecastTitleSet_UNI3(dobj);        //  forecast title Save
        dobj.setRetObject(vUNI3);
        return dobj;
    }
    public DOBJ loadpage(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        return dobj;
    }
}