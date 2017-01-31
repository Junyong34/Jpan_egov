
package hpms.common.dao;
import java.util.*;
import egov.wizware.com.*;
import egov.wizware.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import com.ibatis.sqlmap.client.SqlMapClient;
import javax.annotation.Resource;
@Repository("Control20161004133312Dao")
public class ControlDao extends EgovAbstractDAO
{
    // LOCK_FileInfo
    public VOBJ SearchUsingCheck_SEL10(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL10", "LOCK_FileInfo" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("Control_20161004133312.SearchUsingCheck_SEL10", param);
        dvobj.setName("SEL10");
        dvobj.setRecords(rlist);
        dvobj.Println("SEL10");
        return dvobj;
    }
    // isFile
    public VOBJ SearchUsingCheck_FN01(DOBJ dobj) throws Exception
    {
        CommUtil cu = new CommUtil(dobj);
        HashMap   classinfo = new HashMap();
        classinfo.put("OBJECTID", "FN01" );
        classinfo.put("PACKAGE", "hpms.UserObject.BatchExe" );
        classinfo.put("CLASS", "FileUtility" );
        classinfo.put("METHOD", "isFile" );
        dobj = cu.callPSExternal(dobj, null, classinfo );
        VOBJ dvobj = dobj.getRetObject("FN01");
        dvobj.setName("FN01");
        return dvobj;
    }
    // LOCK_FileInfo
    public VOBJ MonthlyUsingCheck_SEL10(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL10", "LOCK_FileInfo" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("Control_20161004133312.MonthlyUsingCheck_SEL10", param);
        dvobj.setName("SEL10");
        dvobj.setRecords(rlist);
        dvobj.Println("SEL10");
        return dvobj;
    }
    // isFile
    public VOBJ MonthlyUsingCheck_FN01(DOBJ dobj) throws Exception
    {
        CommUtil cu = new CommUtil(dobj);
        HashMap   classinfo = new HashMap();
        classinfo.put("OBJECTID", "FN01" );
        classinfo.put("PACKAGE", "hpms.UserObject.BatchExe" );
        classinfo.put("CLASS", "FileUtility" );
        classinfo.put("METHOD", "isFile" );
        dobj = cu.callPSExternal(dobj, null, classinfo );
        VOBJ dvobj = dobj.getRetObject("FN01");
        dvobj.setName("FN01");
        return dvobj;
    }
    // Month Stop Check
    public VOBJ MonthlyUsingCheck_SEL4(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL4", "Month Stop Check" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("Control_20161004133312.MonthlyUsingCheck_SEL4", param);
        dvobj.setName("SEL4");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // LOCK_FileInfo
    public VOBJ saveUsingCheck_SEL10(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL10", "LOCK_FileInfo" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("Control_20161004133312.saveUsingCheck_SEL10", param);
        dvobj.setName("SEL10");
        dvobj.setRecords(rlist);
        dvobj.Println("SEL10");
        return dvobj;
    }
    // isFile
    public VOBJ saveUsingCheck_FN01(DOBJ dobj) throws Exception
    {
        CommUtil cu = new CommUtil(dobj);
        HashMap   classinfo = new HashMap();
        classinfo.put("OBJECTID", "FN01" );
        classinfo.put("PACKAGE", "hpms.UserObject.BatchExe" );
        classinfo.put("CLASS", "FileUtility" );
        classinfo.put("METHOD", "isFile" );
        dobj = cu.callPSExternal(dobj, null, classinfo );
        VOBJ dvobj = dobj.getRetObject("FN01");
        dvobj.setName("FN01");
        return dvobj;
    }
    // isFile
    public VOBJ MonthlyUsingCheck_PEX12(DOBJ dobj) throws Exception
    {
        CommUtil cu = new CommUtil(dobj);
        HashMap   classinfo = new HashMap();
        classinfo.put("OBJECTID", "PEX12" );
        classinfo.put("PACKAGE", "hpms.UserObject.BatchExe" );
        classinfo.put("CLASS", "FileUtility" );
        classinfo.put("METHOD", "isFile" );
        dobj = cu.callPSExternal(dobj, null, classinfo );
        VOBJ dvobj = dobj.getRetObject("FN01");
        dvobj.setName("PEX12");
        return dvobj;
    }
    // isFile
    public VOBJ saveUsingCheck_PEX8(DOBJ dobj) throws Exception
    {
        CommUtil cu = new CommUtil(dobj);
        HashMap   classinfo = new HashMap();
        classinfo.put("OBJECTID", "PEX8" );
        classinfo.put("PACKAGE", "hpms.UserObject.BatchExe" );
        classinfo.put("CLASS", "FileUtility" );
        classinfo.put("METHOD", "isFile" );
        dobj = cu.callPSExternal(dobj, null, classinfo );
        VOBJ dvobj = dobj.getRetObject("FN01");
        dvobj.setName("PEX8");
        return dvobj;
    }
    // isFile
    public VOBJ SearchUsingCheck_PEX6(DOBJ dobj) throws Exception
    {
        CommUtil cu = new CommUtil(dobj);
        HashMap   classinfo = new HashMap();
        classinfo.put("OBJECTID", "PEX6" );
        classinfo.put("PACKAGE", "hpms.UserObject.BatchExe" );
        classinfo.put("CLASS", "FileUtility" );
        classinfo.put("METHOD", "isFile" );
        dobj = cu.callPSExternal(dobj, null, classinfo );
        VOBJ dvobj = dobj.getRetObject("FN01");
        dvobj.setName("PEX6");
        return dvobj;
    }
    // System Using Stop Check
    public VOBJ SearchUsingCheck_SEL1(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL1", "System Using Stop Check" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("Control_20161004133312.SearchUsingCheck_SEL1", param);
        dvobj.setName("SEL1");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // System Using Stop Check
    public VOBJ saveUsingCheck_SEL2(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL2", "System Using Stop Check" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("Control_20161004133312.saveUsingCheck_SEL2", param);
        dvobj.setName("SEL2");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // System Write Stop Check
    public VOBJ saveUsingCheck_SEL3(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL3", "System Write Stop Check" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("Control_20161004133312.saveUsingCheck_SEL3", param);
        dvobj.setName("SEL3");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // System Using Stop Check
    public VOBJ MonthlyUsingCheck_SEL2(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL2", "System Using Stop Check" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("Control_20161004133312.MonthlyUsingCheck_SEL2", param);
        dvobj.setName("SEL2");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // System Write Stop Check
    public VOBJ MonthlyUsingCheck_SEL3(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL3", "System Write Stop Check" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("Control_20161004133312.MonthlyUsingCheck_SEL3", param);
        dvobj.setName("SEL3");
        dvobj.setRecords(rlist);
        return dvobj;
    }
}