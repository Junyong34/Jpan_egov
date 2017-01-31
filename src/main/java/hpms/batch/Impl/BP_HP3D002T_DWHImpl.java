
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
import hpms.batch.service.BP_HP3D002T_DWH;
@Service("BP_HP3D002T_DWH20160930162713Service")
/**
*/
public class BP_HP3D002T_DWHImpl extends AbstractServiceImpl implements BP_HP3D002T_DWH
{
    @Autowired
    private BP_HP3D002T_DWHDao BP_HP3D002T_DWHdao;
    /**
    BP_HP3RF51T_BOX → HP3D002T
    */
    public DOBJ BP_HP3D002T_EDA(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vSEL6 = BP_HP3D002T_DWHdao.BP_HP3D002T_EDA_SEL6(dobj);        //  EDA Rows count
        dobj.setRetObject(vSEL6);
        if( dobj.getRetObject("SEL6").getRecord().getInt("CNT") > 0)
        {
            VOBJ vXIUD4 = BP_HP3D002T_DWHdao.BP_HP3D002T_EDA_XIUD4(dobj);        //  GL Data Delete
            dobj.setRetObject(vXIUD4);
            VOBJ vSEL12 = BP_HP3D002T_DWHdao.BP_HP3D002T_EDA_SEL12(dobj);        //  EDA Select
            dobj.setRetObject(vSEL12);
            VOBJ vINS13 = BP_HP3D002T_DWHdao.BP_HP3D002T_EDA_INS13(dobj);        //  FA Save
            dobj.setRetObject(vINS13);
            VOBJ vXIUD9 = BP_HP3D002T_DWHdao.BP_HP3D002T_EDA_XIUD9(dobj);        //  Existing Data Delete
            dobj.setRetObject(vXIUD9);
        }
        return dobj;
    }
    /**
    BP_HP3RF02T_BOX → HP3D002T
    */
    public DOBJ BP_HP3D002T_FA(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vSEL6 = BP_HP3D002T_DWHdao.BP_HP3D002T_FA_SEL6(dobj);        //  FA Rows count
        dobj.setRetObject(vSEL6);
        if( dobj.getRetObject("SEL6").getRecord().getInt("CNT") > 0)
        {
            VOBJ vXIUD4 = BP_HP3D002T_DWHdao.BP_HP3D002T_FA_XIUD4(dobj);        //  GL Data Delete
            dobj.setRetObject(vXIUD4);
            VOBJ vSEL1 = BP_HP3D002T_DWHdao.BP_HP3D002T_FA_SEL1(dobj);        //  FA Select
            dobj.setRetObject(vSEL1);
            VOBJ vINS6 = BP_HP3D002T_DWHdao.BP_HP3D002T_FA_INS6(dobj);        //  FA Save
            dobj.setRetObject(vINS6);
            VOBJ vXIUD9 = BP_HP3D002T_DWHdao.BP_HP3D002T_FA_XIUD9(dobj);        //  Existing Data Delete
            dobj.setRetObject(vXIUD9);
        }
        return dobj;
    }
    /**
    BP_HP3RF01T_BOX → HP3D002T
    */
    public DOBJ BP_HP3D002T_GL(DOBJ dobj) throws Exception
    {
        String  message ="";
        WizUtil wutil = new WizUtil(dobj,"","");
        VOBJ vSEL9 = BP_HP3D002T_DWHdao.BP_HP3D002T_GL_SEL9(dobj);        //  FA Rows count
        dobj.setRetObject(vSEL9);
        if( dobj.getRetObject("SEL9").getRecord().getInt("CNT") > 0)
        {
            VOBJ vXIUD11 = BP_HP3D002T_DWHdao.BP_HP3D002T_GL_XIUD11(dobj);        //  GL Data Delete
            dobj.setRetObject(vXIUD11);
            VOBJ vSEL1 = BP_HP3D002T_DWHdao.BP_HP3D002T_GL_SEL1(dobj);        //  GL-AMT Select
            dobj.setRetObject(vSEL1);
            VOBJ vINS9 = BP_HP3D002T_DWHdao.BP_HP3D002T_GL_INS9(dobj);        //  GL Data Save
            dobj.setRetObject(vINS9);
            VOBJ vSEL3 = BP_HP3D002T_DWHdao.BP_HP3D002T_GL_SEL3(dobj);        //  GL-QTY Select
            dobj.setRetObject(vSEL3);
            VOBJ vINS12 = BP_HP3D002T_DWHdao.BP_HP3D002T_GL_INS12(dobj);        //  GL Data Save
            dobj.setRetObject(vINS12);
            VOBJ vSEL5 = BP_HP3D002T_DWHdao.BP_HP3D002T_GL_SEL5(dobj);        //  GL-PRICE Select
            dobj.setRetObject(vSEL5);
            VOBJ vINS13 = BP_HP3D002T_DWHdao.BP_HP3D002T_GL_INS13(dobj);        //  GL Data Save
            dobj.setRetObject(vINS13);
            VOBJ vXIUD9 = BP_HP3D002T_DWHdao.BP_HP3D002T_GL_XIUD9(dobj);        //  Existing Data Delete
            dobj.setRetObject(vXIUD9);
        }
        return dobj;
    }
}