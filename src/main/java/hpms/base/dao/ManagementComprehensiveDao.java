
package hpms.base.dao;
import java.util.*;
import egov.wizware.com.*;
import egov.wizware.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import com.ibatis.sqlmap.client.SqlMapClient;
import javax.annotation.Resource;
@Repository("ManagementComprehensive20160901101144Dao")
public class ManagementComprehensiveDao extends EgovAbstractDAO
{
    // Layout_Excel
    public VOBJ buildSheetDownload_SEL5(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL5", "Layout_Excel" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("ORG_CD", dobj.getRetObject("S").getRecord().get("ORG_CD"));   //ORG_CD
        param.put("COMPANY_CD", dobj.getRetObject("S").getRecord().get("COMPANY_CD").substring(0,3));   //COMPANY_CD
        List rlist = list("ManagementComprehensive_20160901101144.buildSheetDownload_SEL5", param);
        dvobj.setName("SEL5");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // Left Data
    public VOBJ buildSheetDownload_SEL02(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL02", "Left Data" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("YYYYMM", dobj.getRetObject("S").getRecord().get("YYYYMM"));   //실적월
        param.put("ORG_CD", dobj.getRetObject("S").getRecord().get("ORG_CD"));   //ORG_CD
        param.put("COMPANY_CD", dobj.getRetObject("S").getRecord().get("COMPANY_CD").substring(0,3));   //COMPANY_CD
        param.put("DATA_TYPE", dobj.getRetObject("S").getRecord().get("DATA_TYPE"));   //デ？タタイプ
        List rlist = list("ManagementComprehensive_20160901101144.buildSheetDownload_SEL02", param);
        dvobj.setName("SEL02");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // Column info left
    public VOBJ buildSheetDownload_leftColumn(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"leftColumn", "Column info left" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("ManagementComprehensive_20160901101144.buildSheetDownload_leftColumn", param);
        dvobj.setName("leftColumn");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // Column info right
    public VOBJ buildSheetDownload_RightColumn(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"RightColumn", "Column info right" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("ManagementComprehensive_20160901101144.buildSheetDownload_RightColumn", param);
        dvobj.setName("RightColumn");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // Excel Download
    public VOBJ buildSheetDownload_DN01(DOBJ dobj) throws Exception
    {
        CommUtil cu = new CommUtil(dobj);
        HashMap   classinfo = new HashMap();
        classinfo.put("OBJECTID", "DN01" );
        classinfo.put("PACKAGE", "hpms.UserObject.Excel" );
        classinfo.put("CLASS", "ManageSheet" );
        classinfo.put("METHOD", "downloadSheet" );
        dobj = cu.callPSExternal(dobj, null, classinfo );
        VOBJ dvobj = dobj.getRetObject("DN01");
        dvobj.setName("DN01");
        return dvobj;
    }
    // Left Data
    public VOBJ buildSheetDownload_SEL03(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL03", "Left Data" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("YYYYMM", dobj.getRetObject("S").getRecord().get("YYYYMM"));   //실적월
        param.put("ORG_CD", dobj.getRetObject("S").getRecord().get("ORG_CD"));   //ORG_CD
        param.put("COMPANY_CD", dobj.getRetObject("S").getRecord().get("COMPANY_CD").substring(0,3));   //COMPANY_CD
        param.put("DATA_TYPE", dobj.getRetObject("S").getRecord().get("DATA_TYPE"));   //デ？タタイプ
        List rlist = list("ManagementComprehensive_20160901101144.buildSheetDownload_SEL03", param);
        dvobj.setName("SEL03");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // Left Data
    public VOBJ buildSheetDownload_SEL3(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL3", "Left Data" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("YYYYMM", dobj.getRetObject("S").getRecord().get("YYYYMM"));   //실적월
        param.put("ORG_CD", dobj.getRetObject("S").getRecord().get("ORG_CD"));   //ORG_CD
        param.put("COMPANY_CD", dobj.getRetObject("S").getRecord().get("COMPANY_CD").substring(0,3));   //COMPANY_CD
        param.put("DATA_TYPE", dobj.getRetObject("S").getRecord().get("DATA_TYPE"));   //デ？タタイプ
        List rlist = list("ManagementComprehensive_20160901101144.buildSheetDownload_SEL3", param);
        dvobj.setName("SEL3");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // DATA_TYPE
    public VOBJ FCST_Title_ComboMgr_SEL1(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL1", "DATA_TYPE" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("ORG_CD", dobj.getRetObject("S").getRecord().get("ORG_CD"));   //ORG_CD
        param.put("AUTH_CD", dobj.getRetObject("G").getRecord().get("AUTH_CD"));   //AUTH_CD
        String  COMPANY_CD="" ;          //COMPANY_CD
        if(!dobj.getRetObject("S").getRecord().get("COMPANY_CD").equals(""))
        {
            COMPANY_CD = dobj.getRetObject("S").getRecord().get("COMPANY_CD").substring(0,3);
        }
        else
        {
            COMPANY_CD = "";
        }
        param.put("COMPANY_CD", COMPANY_CD);   //COMPANY_CD
        List rlist = list("ManagementComprehensive_20160901101144.FCST_Title_ComboMgr_SEL1", param);
        dvobj.setName("SEL1");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        dvobj.Println("SEL1");
        return dvobj;
    }
    // CompanyList
    public VOBJ initCombo_SEL1(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL1", "CompanyList" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("ManagementComprehensive_20160901101144.initCombo_SEL1", param);
        dvobj.setName("SEL1");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        dvobj.Println("SEL1");
        return dvobj;
    }
    // DATA_TYPE
    public VOBJ initCombo_SEL2(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL2", "DATA_TYPE" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("AUTH_CD", dobj.getRetObject("G").getRecord().get("AUTH_CD"));   //AUTH_CD
        List rlist = list("ManagementComprehensive_20160901101144.initCombo_SEL2", param);
        dvobj.setName("SEL2");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // Organization Code
    public VOBJ initCombo_SEL3(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL3", "Organization Code" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("COMPANY_CD", dobj.getRetObject("S").getRecord().get("COMPANY_CD").substring(0,3));   //COMPANY_CD
        List rlist = list("ManagementComprehensive_20160901101144.initCombo_SEL3", param);
        dvobj.setName("SEL3");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        dvobj.Println("SEL3");
        return dvobj;
    }
}