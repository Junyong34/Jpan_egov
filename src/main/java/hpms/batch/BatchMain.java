
package hpms.batch;
import java.sql.*;
import java.util.*;
import java.util.concurrent.*;
import WIZ.FR.COM.*;
import WIZ.FR.DAO.*;
import WIZ.FR.MSG.*;
import WIZ.FR.UTIL.*;
/**
*/
public class BatchMain
{
    public BatchMain()
    {
    }
    //##**$$Batch_Start
    /** N : Another Batch Exec Disable
    Y : Another Batch Exec Enable
    */
    public DOBJ Batch_Start(DOBJ dobj)
    {
        WizUtil wutil = new WizUtil(dobj);
        Connection Conn = null;
        String message ="";
        try
        {
            Connector connection = new Connector();
            Conn = connection.getConnectionDirect("oracle","jdbc:oracle:thin:@192.168.1.17:1521:orcl","HP_DBA01","HP_DBA01");
            Conn.setAutoCommit(false);
        }
        catch(Exception e)
        {
            dobj.setRetmsg(e,"DataBase Connection Error");
            e.printStackTrace();
            return dobj;
        }
        try
        {
            dobj  = CALLBatch_Start_UPD2(Conn, dobj);           //  flag_N
            Conn.commit();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            wutil.ULogging(e,dobj);
            try
            {
                dobj.setRtncode(-1);
                dobj.setRetmsg(e.getMessage());
                Conn.rollback();
            }
            catch(Exception re)
            {
                re.printStackTrace();
            }
        }
        finally
        {
            try
            {
                Conn.close();
            }
            catch(SQLException e)
            {
                dobj.setRtncode(-1);
                dobj.setRetmsg(e,"DataBase Close Error");
                e.printStackTrace();
            }
        }
        return dobj;
    }
    public DOBJ Batch_Start( DOBJ dobj, Object Connx) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj);
        Connection Conn = (Connection)Connx;
        String message ="";
        dobj  = CALLBatch_Start_UPD2(Conn, dobj);           //  flag_N
        return dobj;
    }
    // flag_N
    public DOBJ CALLBatch_Start_UPD2(Connection Conn, DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"UPD2","flag_N");
        QExecutor qexe = new QExecutor(dobj);
        dobj.setRtnname("UPD2");
        VOBJ dvobj = dobj.getRetObject("S");           //Input Dataset Object
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        int        updcnt =0;
        dvobj.first();
        while(dvobj.next())
        {
            sobj = SQLBatch_Start_UPD2(dobj, dvobj);
            updcnt += qexe.executeUpdate(Conn, sobj);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT","INT");
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("UPD2") ;
        dobj.setRetObject(rvobj);
        return dobj;
    }
    private SQLObject SQLBatch_Start_UPD2(DOBJ dobj, VOBJ dvobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj);
        String   CD ="BATCH_FLAG";   //??
        String   CD_TYPE ="SYS";   //CD_TYPE
        String   VAL1 ="N";   //VAL1
        SQLObject sobj = new SQLObject();
        StringBuffer query = new StringBuffer();
        query.append(" Update HP_DBA01.HP1M106T  \n");
        query.append(" set VAL1=:VAL1  \n");
        query.append(" where CD=:CD  \n");
        query.append(" and CD_TYPE=:CD_TYPE");
        sobj.setSql(query.toString());
        sobj.setString("CD", CD);               //??
        sobj.setString("CD_TYPE", CD_TYPE);               //CD_TYPE
        sobj.setString("VAL1", VAL1);               //VAL1
        return sobj;
    }
    //##**$$Batch_Start
    //##**$$Batch_End
    /** N : Another Batch Exec Disable
    Y : Another Batch Exec Enable
    */
    public DOBJ Batch_End(DOBJ dobj)
    {
        WizUtil wutil = new WizUtil(dobj);
        Connection Conn = null;
        String message ="";
        try
        {
            Connector connection = new Connector();
            Conn = connection.getConnectionDirect("oracle","jdbc:oracle:thin:@192.168.1.17:1521:orcl","HP_DBA01","HP_DBA01");
            Conn.setAutoCommit(false);
        }
        catch(Exception e)
        {
            dobj.setRetmsg(e,"DataBase Connection Error");
            e.printStackTrace();
            return dobj;
        }
        try
        {
            dobj  = CALLBatch_End_UPD2(Conn, dobj);           //  flag_Y
            Conn.commit();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            wutil.ULogging(e,dobj);
            try
            {
                dobj.setRtncode(-1);
                dobj.setRetmsg(e.getMessage());
                Conn.rollback();
            }
            catch(Exception re)
            {
                re.printStackTrace();
            }
        }
        finally
        {
            try
            {
                Conn.close();
            }
            catch(SQLException e)
            {
                dobj.setRtncode(-1);
                dobj.setRetmsg(e,"DataBase Close Error");
                e.printStackTrace();
            }
        }
        return dobj;
    }
    public DOBJ Batch_End( DOBJ dobj, Object Connx) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj);
        Connection Conn = (Connection)Connx;
        String message ="";
        dobj  = CALLBatch_End_UPD2(Conn, dobj);           //  flag_Y
        return dobj;
    }
    // flag_Y
    public DOBJ CALLBatch_End_UPD2(Connection Conn, DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"UPD2","flag_Y");
        QExecutor qexe = new QExecutor(dobj);
        dobj.setRtnname("UPD2");
        VOBJ dvobj = dobj.getRetObject("S");           //Input Dataset Object
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        int        updcnt =0;
        dvobj.first();
        while(dvobj.next())
        {
            sobj = SQLBatch_End_UPD2(dobj, dvobj);
            updcnt += qexe.executeUpdate(Conn, sobj);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT","INT");
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("UPD2") ;
        dobj.setRetObject(rvobj);
        return dobj;
    }
    private SQLObject SQLBatch_End_UPD2(DOBJ dobj, VOBJ dvobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj);
        String   CD ="BATCH_FLAG";   //??
        String   CD_TYPE ="SYS";   //CD_TYPE
        String   VAL1 ="Y";   //VAL1
        SQLObject sobj = new SQLObject();
        StringBuffer query = new StringBuffer();
        query.append(" Update HP_DBA01.HP1M106T  \n");
        query.append(" set VAL1=:VAL1  \n");
        query.append(" where CD=:CD  \n");
        query.append(" and CD_TYPE=:CD_TYPE");
        sobj.setSql(query.toString());
        sobj.setString("CD", CD);               //??
        sobj.setString("CD_TYPE", CD_TYPE);               //CD_TYPE
        sobj.setString("VAL1", VAL1);               //VAL1
        return sobj;
    }
    //##**$$Batch_End
    //##**$$Night_Main
    /**
    */
    public DOBJ Night_Main(DOBJ dobj)
    {
        WizUtil wutil = new WizUtil(dobj);
        Connection Conn = null;
        String message ="";
        try
        {
            Connector connection = new Connector();
            Conn = connection.getConnectionDirect("oracle","jdbc:oracle:thin:@192.168.1.17:1521:orcl","HP_DBA01","HP_DBA01");
            Conn.setAutoCommit(false);
        }
        catch(Exception e)
        {
            dobj.setRetmsg(e,"DataBase Connection Error");
            e.printStackTrace();
            return dobj;
        }
        try
        {
            dobj  = CALLNight_Main_SEL2(Conn, dobj);           //  Batch State Check
            if( dobj.getRetObject("SEL2").getRecord().getInt("CNT") == 1)
            {
                dobj  = CALLNight_Main_batch(Conn, dobj);           //  Param
                dobj  = CALLNight_Main_PEX5(Conn, dobj);           //  ExcelUpload Data
            }
            Conn.commit();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            wutil.ULogging(e,dobj);
            try
            {
                dobj.setRtncode(-1);
                dobj.setRetmsg(e.getMessage());
                Conn.rollback();
            }
            catch(Exception re)
            {
                re.printStackTrace();
            }
        }
        finally
        {
            try
            {
                Conn.close();
            }
            catch(SQLException e)
            {
                dobj.setRtncode(-1);
                dobj.setRetmsg(e,"DataBase Close Error");
                e.printStackTrace();
            }
        }
        return dobj;
    }
    public DOBJ Night_Main( DOBJ dobj, Object Connx) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj);
        Connection Conn = (Connection)Connx;
        String message ="";
        dobj  = CALLNight_Main_SEL2(Conn, dobj);           //  Batch State Check
        if( dobj.getRetObject("SEL2").getRecord().getInt("CNT") == 1)
        {
            dobj  = CALLNight_Main_batch(Conn, dobj);           //  Param
            dobj  = CALLNight_Main_PEX5(Conn, dobj);           //  ExcelUpload Data
        }
        return dobj;
    }
    // Batch State Check
    public DOBJ CALLNight_Main_SEL2(Connection Conn, DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL2","Batch State Check");
        QExecutor qexe = new QExecutor(dobj);
        dobj.setRtnname("SEL2");
        VOBJ dvobj = dobj.getRetObject("S");           //Input Dataset Object
        SQLObject  sobj = SQLNight_Main_SEL2(dobj, dvobj);
        qexe.DispSelectSql(sobj);
        dvobj = qexe.executeQuery(Conn, sobj);
        dvobj.setName("SEL2");
        dvobj.Println("SEL2");
        dobj.setRetObject(dvobj);
        return dobj;
    }
    private SQLObject SQLNight_Main_SEL2(DOBJ dobj, VOBJ dvobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj);
        SQLObject sobj = new SQLObject();
        StringBuffer query = new StringBuffer();
        query.append("SELECT COUNT(*) CNT   \n");
        query.append("  FROM HP1M106T   \n");
        query.append(" WHERE CD_TYPE ='SYS'   \n");
        query.append("   AND CD = 'BATCH_FLAG'   \n");
        query.append("   AND VAL1 = 'Y'  \n");
        sobj.setSql(query.toString());
        return sobj;
    }
    // Param
    public DOBJ CALLNight_Main_batch(Connection Conn, DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"batch","Param");
        QExecutor qexe = new QExecutor(dobj);
        dobj.setRtnname("batch");
        VOBJ dvobj = dobj.getRetObject("S");           //Input Dataset Object
        SQLObject  sobj = SQLNight_Main_batch(dobj, dvobj);
        qexe.DispSelectSql(sobj);
        dvobj = qexe.executeQuery(Conn, sobj);
        dvobj.setName("batch");
        dvobj.Println("batch");
        dobj.setRetObject(dvobj);
        return dobj;
    }
    private SQLObject SQLNight_Main_batch(DOBJ dobj, VOBJ dvobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj);
        SQLObject sobj = new SQLObject();
        StringBuffer query = new StringBuffer();
        query.append("SELECT 'D:/java/batch'    AS PATH   \n");
        query.append("     , 'night_batch.bat'  AS FILE_NAME   \n");
        query.append("     , 'FCST04'           AS D_TYPE   \n");
        query.append("FROM DUAL  \n");
        sobj.setSql(query.toString());
        return sobj;
    }
    // ExcelUpload Data
    public DOBJ CALLNight_Main_PEX5(Connection Conn, DOBJ dobj) throws Exception
    {
        dobj.setRtnname("PEX5");
        CommUtil cu = new CommUtil(dobj);
        HashMap   classinfo = new HashMap();
        classinfo.put("OBJECTID","PEX5");
        classinfo.put("PACKAGE","hpms.UserObject.BatchExe");
        classinfo.put("CLASS","Executor");
        classinfo.put("METHOD","exe");
        dobj = cu.callPSExternal(dobj, null, classinfo );
        VOBJ dvobj = dobj.getRetObject("BAT");
        dvobj.setName("PEX5");
        dobj.setRetObject(dvobj);
        dvobj.Println("PEX5");
        return dobj;
    }
    //##**$$Night_Main
    //##**$$Day_Main
    /**
    */
    public DOBJ Day_Main(DOBJ dobj)
    {
        WizUtil wutil = new WizUtil(dobj);
        Connection Conn = null;
        String message ="";
        try
        {
            Connector connection = new Connector();
            Conn = connection.getConnectionDirect("oracle","jdbc:oracle:thin:@192.168.1.17:1521:orcl","HP_DBA01","HP_DBA01");
            Conn.setAutoCommit(false);
        }
        catch(Exception e)
        {
            dobj.setRetmsg(e,"DataBase Connection Error");
            e.printStackTrace();
            return dobj;
        }
        try
        {
            dobj  = CALLDay_Main_SEL2(Conn, dobj);           //  Batch State Check
            if( dobj.getRetObject("SEL2").getRecord().getInt("CNT") == 1)
            {
                dobj  = CALLDay_Main_batch(Conn, dobj);           //  Param
                dobj  = CALLDay_Main_PEX5(Conn, dobj);           //  ExcelUpload Data
            }
            Conn.commit();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            wutil.ULogging(e,dobj);
            try
            {
                dobj.setRtncode(-1);
                dobj.setRetmsg(e.getMessage());
                Conn.rollback();
            }
            catch(Exception re)
            {
                re.printStackTrace();
            }
        }
        finally
        {
            try
            {
                Conn.close();
            }
            catch(SQLException e)
            {
                dobj.setRtncode(-1);
                dobj.setRetmsg(e,"DataBase Close Error");
                e.printStackTrace();
            }
        }
        return dobj;
    }
    public DOBJ Day_Main( DOBJ dobj, Object Connx) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj);
        Connection Conn = (Connection)Connx;
        String message ="";
        dobj  = CALLDay_Main_SEL2(Conn, dobj);           //  Batch State Check
        if( dobj.getRetObject("SEL2").getRecord().getInt("CNT") == 1)
        {
            dobj  = CALLDay_Main_batch(Conn, dobj);           //  Param
            dobj  = CALLDay_Main_PEX5(Conn, dobj);           //  ExcelUpload Data
        }
        return dobj;
    }
    // Batch State Check
    public DOBJ CALLDay_Main_SEL2(Connection Conn, DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL2","Batch State Check");
        QExecutor qexe = new QExecutor(dobj);
        dobj.setRtnname("SEL2");
        VOBJ dvobj = dobj.getRetObject("S");           //Input Dataset Object
        SQLObject  sobj = SQLDay_Main_SEL2(dobj, dvobj);
        qexe.DispSelectSql(sobj);
        dvobj = qexe.executeQuery(Conn, sobj);
        dvobj.setName("SEL2");
        dvobj.Println("SEL2");
        dobj.setRetObject(dvobj);
        return dobj;
    }
    private SQLObject SQLDay_Main_SEL2(DOBJ dobj, VOBJ dvobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj);
        SQLObject sobj = new SQLObject();
        StringBuffer query = new StringBuffer();
        query.append("SELECT COUNT(*) CNT   \n");
        query.append("  FROM HP1M106T   \n");
        query.append(" WHERE CD_TYPE ='SYS'   \n");
        query.append("   AND CD = 'BATCH_FLAG'   \n");
        query.append("   AND VAL1 = 'Y'  \n");
        sobj.setSql(query.toString());
        return sobj;
    }
    // Param
    public DOBJ CALLDay_Main_batch(Connection Conn, DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"batch","Param");
        QExecutor qexe = new QExecutor(dobj);
        dobj.setRtnname("batch");
        VOBJ dvobj = dobj.getRetObject("S");           //Input Dataset Object
        SQLObject  sobj = SQLDay_Main_batch(dobj, dvobj);
        qexe.DispSelectSql(sobj);
        dvobj = qexe.executeQuery(Conn, sobj);
        dvobj.setName("batch");
        dvobj.Println("batch");
        dobj.setRetObject(dvobj);
        return dobj;
    }
    private SQLObject SQLDay_Main_batch(DOBJ dobj, VOBJ dvobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj);
        String   VARCHAR_TMP1 ="D:/java/batch";   //??SQL_TEST1
        String   VARCHAR_TMP2 ="day_batch.bat";   //??SQL_TEST2
        SQLObject sobj = new SQLObject();
        StringBuffer query = new StringBuffer();
        query.append("SELECT :VARCHAR_TMP1 AS PATH   \n");
        query.append("     , :VARCHAR_TMP2 AS FILE_NAME   \n");
        query.append("FROM DUAL  \n");
        sobj.setSql(query.toString());
        sobj.setString("VARCHAR_TMP1", VARCHAR_TMP1);               //??SQL_TEST1
        sobj.setString("VARCHAR_TMP2", VARCHAR_TMP2);               //??SQL_TEST2
        return sobj;
    }
    // ExcelUpload Data
    public DOBJ CALLDay_Main_PEX5(Connection Conn, DOBJ dobj) throws Exception
    {
        dobj.setRtnname("PEX5");
        CommUtil cu = new CommUtil(dobj);
        HashMap   classinfo = new HashMap();
        classinfo.put("OBJECTID","PEX5");
        classinfo.put("PACKAGE","hpms.UserObject.BatchExe");
        classinfo.put("CLASS","Executor");
        classinfo.put("METHOD","exe");
        dobj = cu.callPSExternal(dobj, null, classinfo );
        VOBJ dvobj = dobj.getRetObject("BAT");
        dvobj.setName("PEX5");
        dobj.setRetObject(dvobj);
        dvobj.Println("PEX5");
        return dobj;
    }
    //##**$$Day_Main
    //##**$$end
}