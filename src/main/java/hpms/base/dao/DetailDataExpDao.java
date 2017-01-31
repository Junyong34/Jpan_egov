
package hpms.base.dao;
import java.util.*;
import egov.wizware.com.*;
import egov.wizware.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import com.ibatis.sqlmap.client.SqlMapClient;
import javax.annotation.Resource;
@Repository("DetailDataExp20160901091139Dao")
public class DetailDataExpDao extends EgovAbstractDAO
{
    // ForeCast download
    public VOBJ ExcelDownload_EXDN(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"EXDN", "ForeCast download" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("PID", dobj.getRetObject("S").getRecord().get("PID"));   //PID
        param.put("YYYYMM_NULL", dobj.getRetObject("S").getRecord().get("YYYYMM"));   //Multi-YYYYMM
        List rlist = list("DetailDataExp_20160901091139.ExcelDownload_EXDN", param);
        dvobj.setName("EXDN");
        dvobj.setRecords(rlist);
        dvobj.Println("EXDN");
        return dvobj;
    }
    // Excel Download
    public VOBJ ExcelDownload_DN01(DOBJ dobj) throws Exception
    {
        CommUtil cu = new CommUtil(dobj);
        HashMap   classinfo = new HashMap();
        classinfo.put("OBJECTID", "DN01" );
        classinfo.put("PACKAGE", "hpms.UserObject.Excel" );
        classinfo.put("CLASS", "ForcastInOut" );
        classinfo.put("METHOD", "downloadPlanExcel" );
        dobj = cu.callPSExternal(dobj, null, classinfo );
        VOBJ dvobj = dobj.getRetObject("DN01");
        dvobj.setName("DN01");
        return dvobj;
    }
}