
package hpms.batch.dao;
import java.util.*;
import egov.wizware.com.*;
import egov.wizware.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import com.ibatis.sqlmap.client.SqlMapClient;
import javax.annotation.Resource;
@Repository("BatchMain20161017163962Dao")
public class BatchMainDao extends EgovAbstractDAO
{
    // Batch State Check
    public VOBJ Day_Main_SEL2(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL2", "Batch State Check" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("BatchMain_20161017163962.Day_Main_SEL2", param);
        dvobj.setName("SEL2");
        dvobj.setRecords(rlist);
        dvobj.Println("SEL2");
        return dvobj;
    }
    // Param
    public VOBJ Day_Main_batch(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"batch", "Param" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("VARCHAR_TMP1", "D:/java/batch");   //임시SQL_TEST1
        param.put("VARCHAR_TMP2", "day_batch.bat");   //임시SQL_TEST2
        List rlist = list("BatchMain_20161017163962.Day_Main_batch", param);
        dvobj.setName("batch");
        dvobj.setRecords(rlist);
        dvobj.Println("batch");
        return dvobj;
    }
    // ExcelUpload Data
    public VOBJ Day_Main_PEX5(DOBJ dobj) throws Exception
    {
        CommUtil cu = new CommUtil(dobj);
        HashMap   classinfo = new HashMap();
        classinfo.put("OBJECTID", "PEX5" );
        classinfo.put("PACKAGE", "hpms.UserObject.BatchExe" );
        classinfo.put("CLASS", "Executor" );
        classinfo.put("METHOD", "exe" );
        dobj = cu.callPSExternal(dobj, null, classinfo );
        VOBJ dvobj = dobj.getRetObject("BAT");
        dvobj.setName("PEX5");
        dvobj.Println("PEX5");
        return dvobj;
    }
    // Batch State Check
    public VOBJ Night_Main_SEL2(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL2", "Batch State Check" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("BatchMain_20161017163962.Night_Main_SEL2", param);
        dvobj.setName("SEL2");
        dvobj.setRecords(rlist);
        dvobj.Println("SEL2");
        return dvobj;
    }
    // ExcelUpload Data
    public VOBJ Night_Main_PEX5(DOBJ dobj) throws Exception
    {
        CommUtil cu = new CommUtil(dobj);
        HashMap   classinfo = new HashMap();
        classinfo.put("OBJECTID", "PEX5" );
        classinfo.put("PACKAGE", "hpms.UserObject.BatchExe" );
        classinfo.put("CLASS", "Executor" );
        classinfo.put("METHOD", "exe" );
        dobj = cu.callPSExternal(dobj, null, classinfo );
        VOBJ dvobj = dobj.getRetObject("BAT");
        dvobj.setName("PEX5");
        dvobj.Println("PEX5");
        return dvobj;
    }
}