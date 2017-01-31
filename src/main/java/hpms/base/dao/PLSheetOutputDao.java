
package hpms.base.dao;
import java.util.*;
import egov.wizware.com.*;
import egov.wizware.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import com.ibatis.sqlmap.client.SqlMapClient;
import javax.annotation.Resource;
@Repository("PLSheetOutput20160901101143Dao")
public class PLSheetOutputDao extends EgovAbstractDAO
{
    // DATA_TYPE
    public VOBJ initCombo_SEL2(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL2", "DATA_TYPE" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("PID", dobj.getRetObject("S2").getRecord().get("PID"));   //PID
        param.put("AUTH_CD", dobj.getRetObject("G").getRecord().get("AUTH_CD"));   //AUTH_CD
        List rlist = list("PLSheetOutput_20160901101143.initCombo_SEL2", param);
        dvobj.setName("SEL2");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // get PID Count
    public VOBJ PIDCheck_SEL1(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL1", "get PID Count" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("PID", dobj.getRetObject("S2").getRecord().get("PID"));   //PID
        List rlist = list("PLSheetOutput_20160901101143.PIDCheck_SEL1", param);
        dvobj.setName("SEL1");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // PID_CHECK
    public VOBJ PIDCheck_SEL3(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL3", "PID_CHECK" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("PID", dobj.getRetObject("S2").getRecord().get("PID"));   //PID
        List rlist = list("PLSheetOutput_20160901101143.PIDCheck_SEL3", param);
        dvobj.setName("SEL3");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // PL_TYPE Search
    public VOBJ buildPLSheetDownload_SEL13(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL13", "PL_TYPE Search" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("PID", dobj.getRetObject("S2").getRecord().get("PID"));   //PID
        List rlist = list("PLSheetOutput_20160901101143.buildPLSheetDownload_SEL13", param);
        dvobj.setName("SEL13");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // DCP_Info
    public VOBJ buildPLSheetDownload_SEL01(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL01", "DCP_Info" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("PID", dobj.getRetObject("S2").getRecord().get("PID"));   //PID
        param.put("DATA_TYPE", dobj.getRetObject("S2").getRecord().get("DATA_TYPE"));   //デ？タタイプ
        List rlist = list("PLSheetOutput_20160901101143.buildPLSheetDownload_SEL01", param);
        dvobj.setName("SEL01");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // PL-DATA
    public VOBJ buildPLSheetDownload_SEL06(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL06", "PL-DATA" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("PID", dobj.getRetObject("S2").getRecord().get("PID"));   //PID
        param.put("DATA_TYPE", dobj.getRetObject("S2").getRecord().get("DATA_TYPE"));   //デ？タタイプ
        List rlist = list("PLSheetOutput_20160901101143.buildPLSheetDownload_SEL06", param);
        dvobj.setName("SEL06");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // Final DCP Info
    public VOBJ buildPLSheetDownload_MRG01(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"MRG01", "Final DCP Info" );
        VOBJ       rvobj= null;
        rvobj = wutil.getMergeObject(dobj, "SEL01, SEL02, SEL03, SEL04, SEL05","" );
        rvobj.setName("MRG01") ;
        rvobj.setRetcode(1);
        return rvobj;
    }
    // Final PL-DATA
    public VOBJ buildPLSheetDownload_MRG02(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"MRG02", "Final PL-DATA" );
        VOBJ       rvobj= null;
        rvobj = wutil.getMergeObject(dobj, "SEL06, SEL07, SEL08, SEL09, SEL10","" );
        rvobj.setName("MRG02") ;
        rvobj.setRetcode(1);
        return rvobj;
    }
    // Column info left
    public VOBJ buildPLSheetDownload_leftColumn(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"leftColumn", "Column info left" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("PLSheetOutput_20160901101143.buildPLSheetDownload_leftColumn", param);
        dvobj.setName("leftColumn");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // Column info right
    public VOBJ buildPLSheetDownload_RightColumn(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"RightColumn", "Column info right" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("PLSheetOutput_20160901101143.buildPLSheetDownload_RightColumn", param);
        dvobj.setName("RightColumn");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // Excel Download
    public VOBJ buildPLSheetDownload_DN01(DOBJ dobj) throws Exception
    {
        CommUtil cu = new CommUtil(dobj);
        HashMap   classinfo = new HashMap();
        classinfo.put("OBJECTID", "DN01" );
        classinfo.put("PACKAGE", "hpms.UserObject.Excel" );
        classinfo.put("CLASS", "PLSheet" );
        classinfo.put("METHOD", "downloadSheet" );
        dobj = cu.callPSExternal(dobj, null, classinfo );
        VOBJ dvobj = dobj.getRetObject("DN01");
        dvobj.setName("DN01");
        return dvobj;
    }
    // DCP_Info
    public VOBJ buildPLSheetDownload_SEL02(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL02", "DCP_Info" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("PID", dobj.getRetObject("S2").getRecord().get("PID"));   //PID
        param.put("DATA_TYPE", dobj.getRetObject("S2").getRecord().get("DATA_TYPE"));   //デ？タタイプ
        List rlist = list("PLSheetOutput_20160901101143.buildPLSheetDownload_SEL02", param);
        dvobj.setName("SEL02");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // PL-DATA
    public VOBJ buildPLSheetDownload_SEL07(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL07", "PL-DATA" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("PID", dobj.getRetObject("S2").getRecord().get("PID"));   //PID
        param.put("DATA_TYPE", dobj.getRetObject("S2").getRecord().get("DATA_TYPE"));   //デ？タタイプ
        List rlist = list("PLSheetOutput_20160901101143.buildPLSheetDownload_SEL07", param);
        dvobj.setName("SEL07");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // DCP_Info
    public VOBJ buildPLSheetDownload_SEL03(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL03", "DCP_Info" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("PID", dobj.getRetObject("S2").getRecord().get("PID"));   //PID
        param.put("DATA_TYPE", dobj.getRetObject("S2").getRecord().get("DATA_TYPE"));   //デ？タタイプ
        List rlist = list("PLSheetOutput_20160901101143.buildPLSheetDownload_SEL03", param);
        dvobj.setName("SEL03");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // PL-DATA
    public VOBJ buildPLSheetDownload_SEL08(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL08", "PL-DATA" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("PID", dobj.getRetObject("S2").getRecord().get("PID"));   //PID
        param.put("DATA_TYPE", dobj.getRetObject("S2").getRecord().get("DATA_TYPE"));   //デ？タタイプ
        List rlist = list("PLSheetOutput_20160901101143.buildPLSheetDownload_SEL08", param);
        dvobj.setName("SEL08");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // DCP_Info
    public VOBJ buildPLSheetDownload_SEL04(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL04", "DCP_Info" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("PID", dobj.getRetObject("S2").getRecord().get("PID"));   //PID
        param.put("DATA_TYPE", dobj.getRetObject("S2").getRecord().get("DATA_TYPE"));   //デ？タタイプ
        List rlist = list("PLSheetOutput_20160901101143.buildPLSheetDownload_SEL04", param);
        dvobj.setName("SEL04");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // PL-DATA
    public VOBJ buildPLSheetDownload_SEL09(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL09", "PL-DATA" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("PID", dobj.getRetObject("S2").getRecord().get("PID"));   //PID
        param.put("DATA_TYPE", dobj.getRetObject("S2").getRecord().get("DATA_TYPE"));   //デ？タタイプ
        List rlist = list("PLSheetOutput_20160901101143.buildPLSheetDownload_SEL09", param);
        dvobj.setName("SEL09");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // DCP_Info
    public VOBJ buildPLSheetDownload_SEL05(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL05", "DCP_Info" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("PID", dobj.getRetObject("S2").getRecord().get("PID"));   //PID
        param.put("DATA_TYPE", dobj.getRetObject("S2").getRecord().get("DATA_TYPE"));   //デ？タタイプ
        List rlist = list("PLSheetOutput_20160901101143.buildPLSheetDownload_SEL05", param);
        dvobj.setName("SEL05");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // PL-DATA
    public VOBJ buildPLSheetDownload_SEL10(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL10", "PL-DATA" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("PID", dobj.getRetObject("S2").getRecord().get("PID"));   //PID
        param.put("DATA_TYPE", dobj.getRetObject("S2").getRecord().get("DATA_TYPE"));   //デ？タタイプ
        List rlist = list("PLSheetOutput_20160901101143.buildPLSheetDownload_SEL10", param);
        dvobj.setName("SEL10");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // Column info left
    public VOBJ buildPLSheetDownload_SEL22(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL22", "Column info left" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("PLSheetOutput_20160901101143.buildPLSheetDownload_SEL22", param);
        dvobj.setName("SEL22");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // Column info right
    public VOBJ buildPLSheetDownload_SEL24(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL24", "Column info right" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("PLSheetOutput_20160901101143.buildPLSheetDownload_SEL24", param);
        dvobj.setName("SEL24");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // DCP_TYPE
    public VOBJ buildPLSheetDownload_SEL11(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL11", "DCP_TYPE" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("PID", dobj.getRetObject("S2").getRecord().get("PID"));   //PID
        List rlist = list("PLSheetOutput_20160901101143.buildPLSheetDownload_SEL11", param);
        dvobj.setName("SEL11");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // get PID Count
    public VOBJ ServiceID01_SEL5(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL5", "get PID Count" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("PID", dobj.getRetObject("S2").getRecord().get("PID"));   //PID
        List rlist = list("PLSheetOutput_20160901101143.ServiceID01_SEL5", param);
        dvobj.setName("SEL5");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // DCP_TYPE
    public VOBJ ServiceID01_SEL11(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL11", "DCP_TYPE" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("PID", dobj.getRetObject("S2").getRecord().get("PID"));   //PID
        List rlist = list("PLSheetOutput_20160901101143.ServiceID01_SEL11", param);
        dvobj.setName("SEL11");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // Approval YYYYMM
    public VOBJ ServiceID01_SEL13(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL13", "Approval YYYYMM" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("PID", dobj.getRetObject("S2").getRecord().get("PID"));   //PID
        param.put("DCP_TYPE", dobj.getRetObject("SEL11").getRecord().get("DCP_TYPE"));   //DCP종별코드
        List rlist = list("PLSheetOutput_20160901101143.ServiceID01_SEL13", param);
        dvobj.setName("SEL13");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // PID Head Info
    public VOBJ ServiceID01_SEL01(DOBJ dobj) throws Exception
    {
        dobj.setRtnname("SEL01");
        WizUtil wutil = new WizUtil(dobj,"SEL01", "PID Head Info" );
        VOBJ dvobj = dobj.getRetObjectCopy("SEL11");        //DCP_TYPE에서 생성시킨 OBJECT입니다.(CALLServiceID01_SEL11)
        String[] outcolumns =
        {
            "DCP_TYPE", "PID", "APPROVAL_YYYYMMDD"
        }
        ;;
        HashMap    record =null;
        dvobj.first();
        while(dvobj.next())
        {
            record = dvobj.getRecordMap();
            String   APPROVAL_YYYYMMDD = dobj.getRetObject("SEL13").getRecord().get("APPROVAL_YYYYMMDD");         //승인일
            record.put("APPROVAL_YYYYMMDD",APPROVAL_YYYYMMDD);
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
        dvobj.setName("SEL01") ;
        return dvobj;
    }
    // PL-DATA
    public VOBJ ServiceID01_SEL02(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL02", "PL-DATA" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("PID", dobj.getRetObject("S2").getRecord().get("PID"));   //PID
        param.put("DATA_TYPE", dobj.getRetObject("S2").getRecord().get("DATA_TYPE"));   //デ？タタイプ
        List rlist = list("PLSheetOutput_20160901101143.ServiceID01_SEL02", param);
        dvobj.setName("SEL02");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // Excel Download
    public VOBJ ServiceID01_DN01(DOBJ dobj) throws Exception
    {
        CommUtil cu = new CommUtil(dobj);
        HashMap   classinfo = new HashMap();
        classinfo.put("OBJECTID", "DN01" );
        classinfo.put("PACKAGE", "hpms.UserObject.Excel" );
        classinfo.put("CLASS", "PLSheet" );
        classinfo.put("METHOD", "downloadSheet" );
        dobj = cu.callPSExternal(dobj, null, classinfo );
        VOBJ dvobj = dobj.getRetObject("DN01");
        dvobj.setName("DN01");
        return dvobj;
    }
    // PID/COMPANY
    public VOBJ ServiceID00_SEL2(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL2", "PID/COMPANY" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("PID", "");   //PID
        param.put("ORG_CD", dobj.getRetObject("G").getRecord().get("ORG_CD"));   //ORG_CD
        param.put("COMPANY_CD", dobj.getRetObject("G").getRecord().get("COMPANY_CD"));   //COMPANY_CD
        List rlist = list("PLSheetOutput_20160901101143.ServiceID00_SEL2", param);
        dvobj.setName("SEL2");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // COMPANY
    public VOBJ ServiceID00_SEL3(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL3", "COMPANY" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("ORG_CD", dobj.getRetObject("G").getRecord().get("ORG_CD"));   //ORG_CD
        param.put("COMPANY_CD", dobj.getRetObject("G").getRecord().get("COMPANY_CD"));   //COMPANY_CD
        List rlist = list("PLSheetOutput_20160901101143.ServiceID00_SEL3", param);
        dvobj.setName("SEL3");
        dvobj.setRecords(rlist);
        return dvobj;
    }
}