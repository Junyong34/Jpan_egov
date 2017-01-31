
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
import hpms.base.service.BatchControl;
@Service("BatchControl20160912111170Service")
/**
OPTION TABLE :
*/
public class BatchControlImpl extends AbstractServiceImpl implements BatchControl
{
    @Autowired
    private BatchControlDao BatchControldao;
    public DOBJ copyLMP(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vSEL26 = BatchControldao.copyLMP_SEL26(dobj);        //  Before Confirm YYYYMM
        dobj.setRetObject(vSEL26);
        VOBJ vSEL13 = BatchControldao.copyLMP_SEL13(dobj);        //  last month
        dobj.setRetObject(vSEL13);
        VOBJ vSEL2 = BatchControldao.copyLMP_SEL2(dobj);        //  get HP2D002T
        dobj.setRetObject(vSEL2);
        VOBJ vUNI4 = BatchControldao.copyLMP_UNI4(dobj);        //  CopyHP2D002T_TZ
        dobj.setRetObject(vUNI4);
        VOBJ vUNI7 = BatchControldao.copyLMP_UNI7(dobj);        //  OptionTableSave
        dobj.setRetObject(vUNI7);
        VOBJ vUNI14 = BatchControldao.copyLMP_UNI14(dobj);        //  OptionTableSave
        dobj.setRetObject(vUNI14);
        VOBJ vSEL8 = BatchControldao.copyLMP_SEL8(dobj);        //  Confirm Info
        dobj.setRetObject(vSEL8);
        VOBJ vXIUD7 = BatchControldao.copyLMP_XIUD7(dobj);        //  copy HP3D002T_TM
        dobj.setRetObject(vXIUD7);
        VOBJ vDEL9 = BatchControldao.copyLMP_DEL9(dobj);        //  delete HP3D002T
        dobj.setRetObject(vDEL9);
        VOBJ vXIUD24 = BatchControldao.copyLMP_XIUD24(dobj);        //  copy HP2DM11T_TZ
        dobj.setRetObject(vXIUD24);
        VOBJ vDEL14 = BatchControldao.copyLMP_DEL14(dobj);        //  HP2DM11T  Delete
        dobj.setRetObject(vDEL14);
        VOBJ vUPD46 = BatchControldao.copyLMP_UPD46(dobj);        //  Flag Change
        dobj.setRetObject(vUPD46);
        return dobj;
    }
    public DOBJ pageInit(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vSEL2 = BatchControldao.pageInit_SEL2(dobj);        //  ForecastCombo
        dobj.setRetObject(vSEL2);
        VOBJ vSEL4 = BatchControldao.pageInit_SEL4(dobj);        //  YYYYMM
        dobj.setRetObject(vSEL4);
        VOBJ vSEL6 = BatchControldao.pageInit_SEL6(dobj);        //  YYYY
        dobj.setRetObject(vSEL6);
        VOBJ vSEL8 = BatchControldao.pageInit_SEL8(dobj);        //  Stop Info
        dobj.setRetObject(vSEL8);
        VOBJ vSEL10 = BatchControldao.pageInit_SEL10(dobj);        //  Release Info
        dobj.setRetObject(vSEL10);
        VOBJ vSEL19 = BatchControldao.pageInit_SEL19(dobj);        //  MGRSUM
        dobj.setRetObject(vSEL19);
        return dobj;
    }
    public DOBJ copyMP(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vSEL7 = BatchControldao.copyMP_SEL7(dobj);        //  F_YYYMM
        dobj.setRetObject(vSEL7);
        VOBJ vDEL15 = BatchControldao.copyMP_DEL15(dobj);        //  MP_YYYY_YYYYMMDD
        dobj.setRetObject(vDEL15);
        VOBJ vSEL28 = BatchControldao.copyMP_SEL28(dobj);        //  snapshot Search
        dobj.setRetObject(vSEL28);
        VOBJ vDEL12 = BatchControldao.copyMP_DEL12(dobj);        //  MP_YYYY
        dobj.setRetObject(vDEL12);
        VOBJ vSEL2 = BatchControldao.copyMP_SEL2(dobj);        //  get HP2D002T
        dobj.setRetObject(vSEL2);
        VOBJ vINS13 = BatchControldao.copyMP_INS13(dobj);        //  CopyHP2D002T_TZ
        dobj.setRetObject(vINS13);
        VOBJ vINS14 = BatchControldao.copyMP_INS14(dobj);        //  CopyHP2D002T_TZ
        dobj.setRetObject(vINS14);
        VOBJ vUNI5 = BatchControldao.copyMP_UNI5(dobj);        //  OptionTableSave
        dobj.setRetObject(vUNI5);
        VOBJ vSEL3 = BatchControldao.copyMP_SEL3(dobj);        //  Confirm Info
        dobj.setRetObject(vSEL3);
        VOBJ vXIUD13 = BatchControldao.copyMP_XIUD13(dobj);        //  copy HP2DM11T_TZ
        dobj.setRetObject(vXIUD13);
        VOBJ vDEL18 = BatchControldao.copyMP_DEL18(dobj);        //  HP2DM11T  Delete
        dobj.setRetObject(vDEL18);
        VOBJ vUPD9 = BatchControldao.copyMP_UPD9(dobj);        //  R++
        dobj.setRetObject(vUPD9);
        return dobj;
    }
    public DOBJ copyMiddle(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vSEL11 = BatchControldao.copyMiddle_SEL11(dobj);        //  F_YYYMM
        dobj.setRetObject(vSEL11);
        VOBJ vDEL13 = BatchControldao.copyMiddle_DEL13(dobj);        //  MP_YYYY_YYYYMMDD
        dobj.setRetObject(vDEL13);
        VOBJ vSEL16 = BatchControldao.copyMiddle_SEL16(dobj);        //  snapshot Search
        dobj.setRetObject(vSEL16);
        VOBJ vDEL14 = BatchControldao.copyMiddle_DEL14(dobj);        //  snapshot Delete
        dobj.setRetObject(vDEL14);
        VOBJ vSEL2 = BatchControldao.copyMiddle_SEL2(dobj);        //  get HP2D002T
        dobj.setRetObject(vSEL2);
        VOBJ vINS14 = BatchControldao.copyMiddle_INS14(dobj);        //  CopyHP2D002T_TZ
        dobj.setRetObject(vINS14);
        VOBJ vINS16 = BatchControldao.copyMiddle_INS16(dobj);        //  CopyHP2D002T_TZ
        dobj.setRetObject(vINS16);
        VOBJ vUNI8 = BatchControldao.copyMiddle_UNI8(dobj);        //  OptionTableSave
        dobj.setRetObject(vUNI8);
        VOBJ vSEL3 = BatchControldao.copyMiddle_SEL3(dobj);        //  Confirm Info
        dobj.setRetObject(vSEL3);
        VOBJ vXIUD11 = BatchControldao.copyMiddle_XIUD11(dobj);        //  copy HP2DM11T_TZ
        dobj.setRetObject(vXIUD11);
        VOBJ vDEL17 = BatchControldao.copyMiddle_DEL17(dobj);        //  HP2DM11T  Delete
        dobj.setRetObject(vDEL17);
        VOBJ vUPD7 = BatchControldao.copyMiddle_UPD7(dobj);        //  R++
        dobj.setRetObject(vUPD7);
        return dobj;
    }
    public DOBJ LMP_ConfirmCheck(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vSEL17 = BatchControldao.LMP_ConfirmCheck_SEL17(dobj);        //  YYYMM Check
        dobj.setRetObject(vSEL17);
        if( dobj.getRetObject("SEL17").getRecord().getInt("CNT") == 1)
        {
            VOBJ vSEL39 = BatchControldao.LMP_ConfirmCheck_SEL39(dobj);        //  Confirm Flag Check
            dobj.setRetObject(vSEL39);
            if(dobj.getRetObject("SEL39").getRecord().get("VAL2").equals("Y"))
            {
                VOBJ vUPD8 = BatchControldao.LMP_ConfirmCheck_UPD8(dobj);        //  Flag Change
                dobj.setRetObject(vUPD8);
            }
            else
            {
                dobj.setRetmsg("0010");
            }
        }
        else
        {
            dobj.setRetmsg("0009");
        }
        return dobj;
    }
    public DOBJ getConfirmInfo(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vSEL2 = BatchControldao.getConfirmInfo_SEL2(dobj);        //  Confirm Info
        dobj.setRetObject(vSEL2);
        VOBJ vSEL4 = BatchControldao.getConfirmInfo_SEL4(dobj);        //  Confirm YYYMM
        dobj.setRetObject(vSEL4);
        VOBJ vMRG11 = BatchControldao.getConfirmInfo_MRG11(dobj);        //  YYYYMM
        dobj.setRetObject(vMRG11);
        return dobj;
    }
    public DOBJ managedatabuild(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vSEL10 = BatchControldao.managedatabuild_SEL10(dobj);        //  LOCK_FileInfo
        dobj.setRetObject(vSEL10);
        if( dobj.getRetObject("SEL10").getRecord().getInt("CNT") == 1)
        {
            VOBJ vSEL5 = BatchControldao.managedatabuild_SEL5(dobj);        //  Montly Flag
            dobj.setRetObject(vSEL5);
            if( dobj.getRetObject("SEL5").getRecord().getInt("CNT") == 0)
            {
                dobj.setRetmsg("0006");
            }
            else if( dobj.getRetObject("SEL5").getRecord().getInt("CNT") == 1)
            {
                VOBJ vbatch = BatchControldao.managedatabuild_batch(dobj);        //  Param
                dobj.setRetObject(vbatch);
                VOBJ vPEX1 = BatchControldao.managedatabuild_PEX1(dobj);        //  ExcelUpload Data
                dobj.setRetObject(vPEX1);
            }
        }
        else
        {
            dobj.setRetmsg("0008");
        }
        return dobj;
    }
    public DOBJ writeRelease(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vUNI2 = BatchControldao.writeRelease_UNI2(dobj);        //  Write Stop
        dobj.setRetObject(vUNI2);
        VOBJ vSEL2 = BatchControldao.writeRelease_SEL2(dobj);        //  Release Info
        dobj.setRetObject(vSEL2);
        VOBJ vSEL3 = BatchControldao.writeRelease_SEL3(dobj);        //  Stop Info
        dobj.setRetObject(vSEL3);
        return dobj;
    }
    public DOBJ writeStop(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vUNI2 = BatchControldao.writeStop_UNI2(dobj);        //  Write Stop
        dobj.setRetObject(vUNI2);
        VOBJ vSEL4 = BatchControldao.writeStop_SEL4(dobj);        //  Release Info
        dobj.setRetObject(vSEL4);
        VOBJ vSEL5 = BatchControldao.writeStop_SEL5(dobj);        //  Stop Info
        dobj.setRetObject(vSEL5);
        return dobj;
    }
    public DOBJ loadpage(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        return dobj;
    }
}