
package hpms.UserObject.BatchExe;
import java.sql.*;
import java.util.*;
import java.util.concurrent.*;
import WIZ.FR.COM.*;
import WIZ.FR.DAO.*;
import WIZ.FR.MSG.*;
import WIZ.FR.UTIL.*;
/**
*/
public class BatchFlag_HP1C003T
{
    public BatchFlag_HP1C003T()
    {
    }
    //##**$$UI_BatchFlag
    /**
    */
    public DOBJ UI_BatchFlag(DOBJ dobj)
    {
        WizUtil wutil = new WizUtil(dobj);
        Connection Conn = null;
        String message ="";
        try
        {
            Connector connection = new Connector();
            Conn = connection.getConnectionDirect("oracle","jdbc:oracle:thin:@133.232.125.190:1521:orcl","HP_DBA01","HP_DBA01");
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
            dobj  = CALLUI_BatchFlag_UPD2(Conn, dobj);           //  Flag_Y
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
    public DOBJ UI_BatchFlag( DOBJ dobj, Object Connx) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj);
        Connection Conn = (Connection)Connx;
        String message ="";
        dobj  = CALLUI_BatchFlag_UPD2(Conn, dobj);           //  Flag_Y
        return dobj;
    }
    // Flag_Y
    public DOBJ CALLUI_BatchFlag_UPD2(Connection Conn, DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"UPD2","Flag_Y");
        QExecutor qexe = new QExecutor(dobj);
        dobj.setRtnname("UPD2");
        VOBJ dvobj = dobj.getRetObject("S");           //??? ???? ??? Object???.
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        int        updcnt =0;
        dvobj.first();
        while(dvobj.next())
        {
            sobj = SQLUI_BatchFlag_UPD2(dobj, dvobj);
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
    private SQLObject SQLUI_BatchFlag_UPD2(DOBJ dobj, VOBJ dvobj) throws Exception
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
    //##**$$UI_BatchFlag
    //##**$$end
}