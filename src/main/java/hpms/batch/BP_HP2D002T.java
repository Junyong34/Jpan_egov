
package hpms.batch;
import java.sql.*;
import java.util.*;
import java.util.concurrent.*;

import org.springframework.util.StopWatch;

import WIZ.FR.COM.*;
import WIZ.FR.DAO.*;
import WIZ.FR.MSG.*;
import WIZ.FR.UTIL.*;
/**
1.UNiT? ?? ?? VAL?? VAL2, VAL3? ??? ????
*/
public class BP_HP2D002T
{
    public BP_HP2D002T()
    {
    }

    public DOBJ BP_HP2D002T(DOBJ dobj)
    {
        WizUtil wutil = new WizUtil(dobj);
        Connection Conn = null;
        String message ="";
        try
        {
            Connector connection = new Connector();
            if( dobj.containKey("S") && dobj.getRetObject("S").ContainsColumnName("DBURL") && dobj.getRetObject("S").ContainsColumnName("DBID") && dobj.getRetObject("S").ContainsColumnName("DBPWD") )
            {
                VOBJ dvobj = dobj.getRetObject("S");
                Conn = connection.getConnectionDirect("oracle", dvobj.getRecord().get("DBURL"), dvobj.getRecord().get("DBID"), dvobj.getRecord().get("DBPWD") );
            }
            else
            {
                Conn = connection.getConnectionDirect("oracle","jdbc:oracle:thin:@133.232.125.190:1521:orcl","HP_DBA01","HP_DBA01");
            }
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
        	StopWatch stopWatch = new StopWatch("batch start -1 ");
        	stopWatch.start("XIUD16");
            dobj  = CALLBP_HP2D00２T_XIUD16(Conn, dobj);  
            stopWatch.stop();
        	System.out.println("XIUD16 " + stopWatch.getLastTaskTimeMillis() + " ms");
        	
        	stopWatch.start("SEL3");
            dobj  = CALLBP_HP2D00２T_SEL3(Conn, dobj);    
            stopWatch.stop();
        	System.out.println("SEL3 " + stopWatch.getLastTaskTimeMillis() + " ms");
        	
        	stopWatch.start("INS14");
            dobj  = CALLBP_HP2D00２T_INS14(Conn, dobj);     
            stopWatch.stop();
        	System.out.println("INS14 " + stopWatch.getLastTaskTimeMillis() + " ms");
        	
        	System.out.println(stopWatch.toString());
        	System.out.println();
        	System.out.println(stopWatch.prettyPrint());
        	
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
    public DOBJ BP_HP2D002T( DOBJ dobj, Object Connx) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj);
        Connection Conn = (Connection)Connx;
        String message ="";

        dobj  = CALLBP_HP2D00２T_XIUD16(Conn, dobj); 
     
        dobj  = CALLBP_HP2D00２T_SEL3(Conn, dobj);           
        dobj  = CALLBP_HP2D00２T_INS14(Conn, dobj);           
        return dobj;
    }
    public DOBJ CALLBP_HP2D00２T_XIUD16(Connection Conn, DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"XIUD16","HP2D002T delete");
        QExecutor qexe = new QExecutor(dobj);
        dobj.setRtnname("XIUD16");
        VOBJ dvobj = dobj.getRetObject("S");            
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        int        updcnt =0;
        dvobj.first();
        while(dvobj.next())
        {
            sobj = SQLBP_HP2D00２T_XIUD16(dobj, dvobj);
            updcnt += qexe.executeUpdate(Conn, sobj);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT","INT");
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("XIUD16");
        dobj.setRetObject(rvobj);
        return dobj;
    }
    private SQLObject SQLBP_HP2D00２T_XIUD16(DOBJ dobj, VOBJ dvobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj);
        String   DATA_TYPE = dvobj.getRecord().get("DATA_TYPE");   
        SQLObject sobj = new SQLObject();
        StringBuffer query = new StringBuffer();
        query.append(" DELETE FROM HP2D002T  \n");
        query.append(" WHERE (1=1)  \n");
        query.append(" AND VAL_TYPE = 0  \n");
        query.append(" AND DATA_TYPE LIKE DECODE(:DATA_TYPE, '' , '%' ,:DATA_TYPE)|| '%'");
        sobj.setSql(query.toString());
        sobj.setString("DATA_TYPE", DATA_TYPE);               
        return sobj;
    }
    public DOBJ CALLBP_HP2D00２T_SEL3(Connection Conn, DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL3","HP2D001T Select");
        QExecutor qexe = new QExecutor(dobj);
        dobj.setRtnname("SEL3");
        VOBJ dvobj = dobj.getRetObject("S");           
        SQLObject  sobj = SQLBP_HP2D00２T_SEL3(dobj, dvobj);
        qexe.DispSelectSql(sobj);
        dvobj = qexe.executeQuery(Conn, sobj);
        dvobj.setName("SEL3");
        dvobj.Println("SEL3");
        dobj.setRetObject(dvobj);
        return dobj;
    }
    private SQLObject SQLBP_HP2D00２T_SEL3(DOBJ dobj, VOBJ dvobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj);
        String   DATA_TYPE = dobj.getRetObject("S").getRecord().get("DATA_TYPE");   
        SQLObject sobj = new SQLObject();
        StringBuffer query = new StringBuffer();
        query.append("SELECT    \n");
        query.append("          DATA_TYPE                 AS DATA_TYPE    \n");
        query.append("        , PID                       AS PID    \n");
        query.append("        , USE_COMPANY_CD            AS USE_COMPANY_CD    \n");
        query.append("        , USE_ORG_CD                AS USE_ORG_CD    \n");
        query.append("        , REQ_COMPANY_CD            AS REQ_COMPANY_CD    \n");
        query.append("        , REQ_ORG_CD                AS REQ_ORG_CD    \n");
        query.append("        , '#'                       AS SRC_COMPANY_CD    \n");
        query.append("        , '#'                       AS SRC_ORG_CD    \n");
        query.append("        , '#'                       AS DST_COMPANY_CD    \n");
        query.append("        , '#'                       AS DST_ORG_CD    \n");
        query.append("        , HPMS_ID                   AS HPMS_ID    \n");
        query.append("        , INVEST_TYPE_CD            AS INVEST_TYPE_CD    \n");
        query.append("        , APPLICATION               AS APPLICATION    \n");
        query.append("        , ITEM_NAME                 AS ITEM_NAME    \n");
        query.append("        , CUSTOMER_CD               AS CUSTOMER_CD    \n");
        query.append("        , YYYYMM                    AS YYYYMM    \n");
        query.append("        , UNIT                      AS UNIT    \n");
        query.append("        , VAL                       AS VAL    \n");
        query.append("        , F_GET_CURRENCY_TRANS('PLAN', USE_COMPANY_CD, UNIT, YYYYMM, VAL, '2U') AS UNIT2    \n");
        query.append("        , decode(UNIT, '', VAL, nvl(TO_NUMBER(F_GET_CURRENCY_TRANS('PLAN', USE_COMPANY_CD, UNIT, YYYYMM, VAL, '2V')),0)) AS VAL2    \n");
        query.append("        , F_GET_CURRENCY_TRANS('PLAN', USE_COMPANY_CD, UNIT, YYYYMM, VAL, '3U') AS UNIT3    \n");
        query.append("        , decode(UNIT, '', VAL, nvl(TO_NUMBER(F_GET_CURRENCY_TRANS('PLAN', USE_COMPANY_CD, UNIT, YYYYMM, VAL, '3V')),0)) AS VAL3    \n");
        query.append("        , 0                         AS VAL_TYPE    \n");
        query.append("        , 'BP_HP2D002T'             AS UPDATE_USER_ID    \n");
        query.append("        , UPLOAD_FILE_NAME          AS UPLOAD_FILE_NAME    \n");
        query.append("        , TO_CHAR(NULL)             AS CALC_MST_VER    \n");
        query.append("        FROM    \n");
        query.append("        HP2D001T    \n");
        query.append("WHERE    \n");
        query.append("        (1=1)    \n");
        if( !DATA_TYPE.equals("") )
        {
            query.append("       AND DATA_TYPE = :DATA_TYPE       \n");
        }
        query.append("    AND   YYYYMM > F_GET_CONFIRM_YYYYMM()   \n");
        sobj.setSql(query.toString());
        if(!DATA_TYPE.equals(""))
        {
            sobj.setString("DATA_TYPE", DATA_TYPE);               
        }
        return sobj;
    }
    public DOBJ CALLBP_HP2D00２T_INS14(Connection Conn, DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"INS14","HP3D002T save");
        QExecutor qexe = new QExecutor(dobj);
        dobj.setRtnname("INS14");
        VOBJ dvobj = dobj.getRetObject("SEL3");           
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        int        updcnt =0;
        dvobj.first();
        while(dvobj.next())
        {
            sobj = SQLBP_HP2D00２T_INS14(dobj, dvobj);
            updcnt += qexe.executeUpdate(Conn, sobj);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT","INT");
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("INS14") ;
        dobj.setRetObject(rvobj);
        return dobj;
    }
    private SQLObject SQLBP_HP2D00２T_INS14(DOBJ dobj, VOBJ dvobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj);
        String UPDATE_TIME ="";        
        String   USE_ORG_CD = dvobj.getRecord().get("USE_ORG_CD");   
        String   REQ_COMPANY_CD = dvobj.getRecord().get("REQ_COMPANY_CD");   
        String   ITEM_NAME = dvobj.getRecord().get("ITEM_NAME");   
        String   REQ_ORG_CD = dvobj.getRecord().get("REQ_ORG_CD");   
        String   UPDATE_USER_ID = dvobj.getRecord().get("UPDATE_USER_ID");   
        String   SRC_ORG_CD = dvobj.getRecord().get("SRC_ORG_CD");   
        double   VAL = dvobj.getRecord().getDouble("VAL");   
        String   INVEST_TYPE_CD = dvobj.getRecord().get("INVEST_TYPE_CD");   
        String   HPMS_ID = dvobj.getRecord().get("HPMS_ID");   
        String   UNIT = dvobj.getRecord().get("UNIT");   
        String   CUSTOMER_CD = dvobj.getRecord().get("CUSTOMER_CD");   
        String   USE_COMPANY_CD = dvobj.getRecord().get("USE_COMPANY_CD");   
        String   UPLOAD_FILE_NAME = dvobj.getRecord().get("UPLOAD_FILE_NAME");   
        String   DATA_TYPE = dvobj.getRecord().get("DATA_TYPE");   
        String   SRC_COMPANY_CD = dvobj.getRecord().get("SRC_COMPANY_CD");   
        String   CALC_MST_VER = dvobj.getRecord().get("CALC_MST_VER");   
        String   YYYYMM = dvobj.getRecord().get("YYYYMM");   
        String   DST_COMPANY_CD = dvobj.getRecord().get("DST_COMPANY_CD");   
        String   DST_ORG_CD = dvobj.getRecord().get("DST_ORG_CD");   
        String   VAL_TYPE = dvobj.getRecord().get("VAL_TYPE");   
        String   APPLICATION = dvobj.getRecord().get("APPLICATION");   
        String   UNIT3 = dvobj.getRecord().get("UNIT3");   
        String   UNIT2 = dvobj.getRecord().get("UNIT2");   
        String   PID = dvobj.getRecord().get("PID");   
        String   VAL3 = dvobj.getRecord().get("VAL3");   
        String   VAL2 = dvobj.getRecord().get("VAL2");   
        SQLObject sobj = new SQLObject();
        StringBuffer query = new StringBuffer();
        query.append(" Insert into HP_DBA01.HP2D002T (VAL2, VAL3, PID, UNIT2, UNIT3, APPLICATION, VAL_TYPE, DST_ORG_CD, DST_COMPANY_CD, YYYYMM, CALC_MST_VER, SRC_COMPANY_CD, DATA_TYPE, UPLOAD_FILE_NAME, USE_COMPANY_CD, CUSTOMER_CD, UNIT, HPMS_ID, UPDATE_TIME, INVEST_TYPE_CD, VAL, SRC_ORG_CD, UPDATE_USER_ID, REQ_ORG_CD, ITEM_NAME, REQ_COMPANY_CD, USE_ORG_CD)  \n");
        query.append(" values(:VAL2 , :VAL3 , :PID , :UNIT2 , :UNIT3 , :APPLICATION , :VAL_TYPE , :DST_ORG_CD , :DST_COMPANY_CD , :YYYYMM , :CALC_MST_VER , :SRC_COMPANY_CD , :DATA_TYPE , :UPLOAD_FILE_NAME , :USE_COMPANY_CD , :CUSTOMER_CD , :UNIT , :HPMS_ID , SYSDATE, :INVEST_TYPE_CD , :VAL , :SRC_ORG_CD , :UPDATE_USER_ID , :REQ_ORG_CD , :ITEM_NAME , :REQ_COMPANY_CD , :USE_ORG_CD )");
        sobj.setSql(query.toString());
        sobj.setString("USE_ORG_CD", USE_ORG_CD);               
        sobj.setString("REQ_COMPANY_CD", REQ_COMPANY_CD);               
        sobj.setString("ITEM_NAME", ITEM_NAME);               
        sobj.setString("REQ_ORG_CD", REQ_ORG_CD);               
        sobj.setString("UPDATE_USER_ID", UPDATE_USER_ID);               
        sobj.setString("SRC_ORG_CD", SRC_ORG_CD);               
        sobj.setDouble("VAL", VAL);               
        sobj.setString("INVEST_TYPE_CD", INVEST_TYPE_CD);               
        sobj.setString("HPMS_ID", HPMS_ID);               
        sobj.setString("UNIT", UNIT);               
        sobj.setString("CUSTOMER_CD", CUSTOMER_CD);               
        sobj.setString("USE_COMPANY_CD", USE_COMPANY_CD);               
        sobj.setString("UPLOAD_FILE_NAME", UPLOAD_FILE_NAME);               
        sobj.setString("DATA_TYPE", DATA_TYPE);               
        sobj.setString("SRC_COMPANY_CD", SRC_COMPANY_CD);               
        sobj.setString("CALC_MST_VER", CALC_MST_VER);               
        sobj.setString("YYYYMM", YYYYMM);               
        sobj.setString("DST_COMPANY_CD", DST_COMPANY_CD);               
        sobj.setString("DST_ORG_CD", DST_ORG_CD);               
        sobj.setString("VAL_TYPE", VAL_TYPE);               
        sobj.setString("APPLICATION", APPLICATION);               
        sobj.setString("UNIT3", UNIT3);               
        sobj.setString("UNIT2", UNIT2);               
        sobj.setString("PID", PID);               
        sobj.setString("VAL3", VAL3);               
        sobj.setString("VAL2", VAL2);               
        return sobj;
    }

    public DOBJ BP_HP2D002T_DPR(DOBJ dobj)
    {
        WizUtil wutil = new WizUtil(dobj);
        Connection Conn = null;
        String message ="";
        try
        {
            Connector connection = new Connector();
            if( dobj.containKey("S") && dobj.getRetObject("S").ContainsColumnName("DBURL") && dobj.getRetObject("S").ContainsColumnName("DBID") && dobj.getRetObject("S").ContainsColumnName("DBPWD") )
            {
                VOBJ dvobj = dobj.getRetObject("S");
                Conn = connection.getConnectionDirect("oracle", dvobj.getRecord().get("DBURL"), dvobj.getRecord().get("DBID"), dvobj.getRecord().get("DBPWD") );
            }
            else
            {
                Conn = connection.getConnectionDirect("oracle","jdbc:oracle:thin:@133.232.125.190:1521:orcl","HP_DBA01","HP_DBA01");
            }
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
        	
               
            
            
            StopWatch stopWatch = new StopWatch("batch start -2 ");
        	stopWatch.start("XIUD2");
        	 dobj  = CALLBP_HP2D002T_DPR_XIUD2(Conn, dobj);           
            stopWatch.stop();
        	System.out.println("XIUD2 " + stopWatch.getLastTaskTimeMillis() + " ms");
        	
        	stopWatch.start("SEL3");
        	 dobj  = CALLBP_HP2D002T_DPR_SEL3(Conn, dobj);       
            stopWatch.stop();
        	System.out.println("SEL3 " + stopWatch.getLastTaskTimeMillis() + " ms");
        	
        	stopWatch.start("MPD4");
        	dobj  = CALLBP_HP2D002T_DPR_MPD4(Conn, dobj);         
            stopWatch.stop();
        	System.out.println("MPD4 " + stopWatch.getLastTaskTimeMillis() + " ms");
        	
        	System.out.println(stopWatch.toString());
        	System.out.println();
        	System.out.println(stopWatch.prettyPrint());
            
            
            
            
            
            if(dobj.getRtncode() == 9)
            {
                Conn.rollback();
                return dobj;
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
    public DOBJ BP_HP2D002T_DPR( DOBJ dobj, Object Connx) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj);
        Connection Conn = (Connection)Connx;
        String message ="";
        dobj  = CALLBP_HP2D002T_DPR_XIUD2(Conn, dobj);           
        dobj  = CALLBP_HP2D002T_DPR_SEL3(Conn, dobj);           
        dobj  = CALLBP_HP2D002T_DPR_MPD4(Conn, dobj);           
        if(dobj.getRtncode() == 9)
        {
            Conn.rollback();
            return dobj;
        }
        return dobj;
    }
    public DOBJ CALLBP_HP2D002T_DPR_XIUD2(Connection Conn, DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"XIUD2","Depre rows delete");
        QExecutor qexe = new QExecutor(dobj);
        dobj.setRtnname("XIUD2");
        VOBJ dvobj = dobj.getRetObject("S");            
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        int        updcnt =0;
        dvobj.first();
        while(dvobj.next())
        {
            sobj = SQLBP_HP2D002T_DPR_XIUD2(dobj, dvobj);
            updcnt += qexe.executeUpdate(Conn, sobj);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT","INT");
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("XIUD2");
        dobj.setRetObject(rvobj);
        return dobj;
    }
    private SQLObject SQLBP_HP2D002T_DPR_XIUD2(DOBJ dobj, VOBJ dvobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj);
        String   DATA_TYPE = dvobj.getRecord().get("DATA_TYPE");   
        SQLObject sobj = new SQLObject();
        StringBuffer query = new StringBuffer();
        query.append(" DELETE FROM HP2D002T  \n");
        query.append(" WHERE VAL_TYPE = 20  \n");
        query.append(" AND DATA_TYPE LIKE DECODE(:DATA_TYPE, '' , '%' ,:DATA_TYPE)|| '%'");
        sobj.setSql(query.toString());
        sobj.setString("DATA_TYPE", DATA_TYPE);               
        return sobj;
    }
    public DOBJ CALLBP_HP2D002T_DPR_SEL3(Connection Conn, DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL3","Select to depre rows");
        QExecutor qexe = new QExecutor(dobj);
        dobj.setRtnname("SEL3");
        VOBJ dvobj = dobj.getRetObject("S");           
        SQLObject  sobj = SQLBP_HP2D002T_DPR_SEL3(dobj, dvobj);
        qexe.DispSelectSql(sobj);
        dvobj = qexe.executeQuery(Conn, sobj);
        dvobj.setName("SEL3");
        dvobj.Println("SEL3");
        dobj.setRetObject(dvobj);
        return dobj;
    }
    private SQLObject SQLBP_HP2D002T_DPR_SEL3(DOBJ dobj, VOBJ dvobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj);
        String   DATA_TYPE = dobj.getRetObject("S").getRecord().get("DATA_TYPE");   
        SQLObject sobj = new SQLObject();
        StringBuffer query = new StringBuffer();
        query.append("SELECT    \n");
        query.append("                DATA_TYPE    \n");
        query.append("              , PID    \n");
        query.append("              , USE_COMPANY_CD    \n");
        query.append("              , USE_ORG_CD    \n");
        query.append("              , REQ_COMPANY_CD    \n");
        query.append("              , REQ_ORG_CD    \n");
        query.append("              , SRC_COMPANY_CD    \n");
        query.append("              , SRC_ORG_CD    \n");
        query.append("              , DST_COMPANY_CD    \n");
        query.append("              , DST_ORG_CD    \n");
        query.append("              , HPMS_ID    \n");
        query.append("              , INVEST_TYPE_CD    \n");
        query.append("              , APPLICATION    \n");
        query.append("              , ITEM_NAME    \n");
        query.append("              , CUSTOMER_CD    \n");
        query.append("              , YYYYMM    \n");
        query.append("              , UNIT    \n");
        query.append("              , VAL    \n");
        query.append("              , UNIT2    \n");
        query.append("              , VAL2    \n");
        query.append("              , UNIT3    \n");
        query.append("              , VAL3    \n");
        query.append("              , VAL_TYPE    \n");
        query.append("              , CALC_MST_VER    \n");
        query.append("              , 'IV' AS GBN    \n");
        query.append("  FROM HP2D002T    \n");
        query.append(" WHERE VAL_TYPE = 0    \n");
        query.append("   AND VAL > 0     \n");
        query.append("   AND HPMS_ID IN ( SELECT SRC_HPMS_ID FROM HP1DM08T WHERE CALC_TYPE = 20)    \n");
        query.append("   AND YYYYMM > F_GET_CONFIRM_YYYYMM()    \n");
        if( !DATA_TYPE.equals("") )
        {
            query.append("       AND DATA_TYPE = :DATA_TYPE       \n");
        }
        query.append("    UNION ALL    \n");
        query.append(" SELECT    \n");
        query.append("                DATA_TYPE    \n");
        query.append("              , PID    \n");
        query.append("              , USE_COMPANY_CD    \n");
        query.append("              , USE_ORG_CD    \n");
        query.append("              , REQ_COMPANY_CD    \n");
        query.append("              , REQ_ORG_CD    \n");
        query.append("              , SRC_COMPANY_CD    \n");
        query.append("              , SRC_ORG_CD    \n");
        query.append("              , DST_COMPANY_CD    \n");
        query.append("              , DST_ORG_CD    \n");
        query.append("              , HPMS_ID    \n");
        query.append("              , INVEST_TYPE_CD    \n");
        query.append("              , APPLICATION    \n");
        query.append("              , ITEM_NAME    \n");
        query.append("              , CUSTOMER_CD    \n");
        query.append("              , YYYYMM    \n");
        query.append("              , UNIT    \n");
        query.append("              , VAL    \n");
        query.append("              , UNIT2    \n");
        query.append("              , VAL2    \n");
        query.append("              , UNIT3    \n");
        query.append("              , VAL3    \n");
        query.append("              , VAL_TYPE    \n");
        query.append("              , CALC_MST_VER    \n");
        query.append("              , 'RD' AS GBN    \n");
        query.append("  FROM HP2D002T    \n");
        query.append("WHERE  VAL_TYPE = 0    \n");
        query.append("    AND HPMS_ID IN ( SELECT DISTINCT RD_HPMS_ID FROM HP1DM08T WHERE CALC_TYPE = 20)    \n");
        query.append("    AND YYYYMM > F_GET_CONFIRM_YYYYMM()    \n");
        if( !DATA_TYPE.equals("") )
        {
            query.append("       AND DATA_TYPE = :DATA_TYPE       \n");
        }
        sobj.setSql(query.toString());
        if(!DATA_TYPE.equals(""))
        {
            sobj.setString("DATA_TYPE", DATA_TYPE);               
        }
        return sobj;
    }
    public DOBJ CALLBP_HP2D002T_DPR_MPD4(Connection Conn, DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"MPD4","Multi Rows Process");
        dobj.setRtnname("MPD4");
        VOBJ dvobj = dobj.getRetObject("SEL3");         
        dvobj.Println("MPD4");
        VOBJ       rvobj= null;
        
        StopWatch stopWatch = new StopWatch("batch start -3 ");
    	        
       
    	
    	
        
        dvobj.first();
        while(dvobj.next())
        {
            if(dvobj.getRecord().get("GBN").equals("IV"))
            {
                dobj.resetObject("SEL4,SEL5,SEL6,SEL10,INS13,NEW_DRP");
                dobj.setRetObject(dvobj.getRecVobj("R"));
                
                stopWatch.start("SEL4");  
                dobj  = CALLBP_HP2D002T_DPR_SEL4(Conn, dobj);   
                stopWatch.stop();
                System.out.println("SEL4 " + stopWatch.getLastTaskTimeMillis() + " ms");
                
                
                
                if( dobj.getRetObject("SEL4").getRecord().getInt("DEPRECIATION_MONTHS") > 0)
                {
                	stopWatch.start("SEL5");  
                    dobj  = CALLBP_HP2D002T_DPR_SEL5(Conn, dobj);   
                    stopWatch.stop();
                    System.out.println("SEL5 " + stopWatch.getLastTaskTimeMillis() + " ms");
                    
                    stopWatch.start("SEL6");  
                    dobj  = CALLBP_HP2D002T_DPR_SEL6(Conn, dobj);      
                    stopWatch.stop();
                    System.out.println("SEL6 " + stopWatch.getLastTaskTimeMillis() + " ms");
                    
                    stopWatch.start("SEL10");  
                    dobj  = CALLBP_HP2D002T_DPR_SEL10(Conn, dobj);          
                    stopWatch.stop();   
                    System.out.println("SEL10 " + stopWatch.getLastTaskTimeMillis() + " ms");
                    
                    stopWatch.start("INS13");  
                    dobj  = CALLBP_HP2D002T_DPR_INS13(Conn, dobj);   
                    stopWatch.stop();
                    System.out.println("INS13 " + stopWatch.getLastTaskTimeMillis() + " ms");
                    
                    stopWatch.start("DPR_NEW_DRP");  
                    dobj  = CALLBP_HP2D002T_DPR_NEW_DRP(Conn, dobj);   
                    stopWatch.stop();
                    System.out.println("DPR_NEW_DRP " + stopWatch.getLastTaskTimeMillis() + " ms");
                }
            }
            else
            {
                dobj.resetObject("SEL11,SEL21,SEL20,XIUD26,NEW_DRP2");
                dobj.setRetObject(dvobj.getRecVobj("R"));
                dobj  = CALLBP_HP2D002T_DPR_SEL11(Conn, dobj);           
                if(   dobj.getRetObject("R").getRecord().getInt("YYYYMM") >=   dobj.getRetObject("SEL11").getRecord().getInt("M_DCP_PLAN_YYYYMM"))
                {
                	
                    stopWatch.start("SEL21");  
                    dobj  = CALLBP_HP2D002T_DPR_SEL21(Conn, dobj); 
                    stopWatch.stop();
                    System.out.println("SEL21 " + stopWatch.getLastTaskTimeMillis() + " ms");
                    
                    stopWatch.start("SEL20");  
                    dobj  = CALLBP_HP2D002T_DPR_SEL20(Conn, dobj);
                    stopWatch.stop();
                    System.out.println("SEL20 " + stopWatch.getLastTaskTimeMillis() + " ms");
                    
                    stopWatch.start("XIUD26");  
                    dobj  = CALLBP_HP2D002T_DPR_XIUD26(Conn, dobj);  
                    stopWatch.stop();
                    System.out.println("XIUD26 " + stopWatch.getLastTaskTimeMillis() + " ms");
                    
                    stopWatch.start("DPR_NEW_DRP2");  
                    dobj  = CALLBP_HP2D002T_DPR_NEW_DRP2(Conn, dobj);  
                    stopWatch.stop();
                    System.out.println("DPR_NEW_DRP2 " + stopWatch.getLastTaskTimeMillis() + " ms");
                }
            }
        }
        
        System.out.println(stopWatch.toString());
    	System.out.println();
    	System.out.println(stopWatch.prettyPrint());
        return dobj;
    }
    public DOBJ CALLBP_HP2D002T_DPR_SEL4(Connection Conn, DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL4","Sel Depre Months");
        QExecutor qexe = new QExecutor(dobj);
        dobj.setRtnname("SEL4");
        VOBJ dvobj = dobj.getRetObject("S");           
        SQLObject  sobj = SQLBP_HP2D002T_DPR_SEL4(dobj, dvobj);
        qexe.DispSelectSql(sobj);
        dvobj = qexe.executeQuery(Conn, sobj);
        dvobj.setName("SEL4");
        dvobj.Println("SEL4");
        dobj.setRetObject(dvobj);
        return dobj;
    }
    private SQLObject SQLBP_HP2D002T_DPR_SEL4(DOBJ dobj, VOBJ dvobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj);
        String   INVEST_TYPE_CD = dobj.getRetObject("R").getRecord().get("INVEST_TYPE_CD");   
        SQLObject sobj = new SQLObject();
        StringBuffer query = new StringBuffer();
        query.append("SELECT   \n");
        query.append("          INVEST_TYPE_CD   \n");
        query.append("        , INVEST_TYPE_NM   \n");
        query.append("        , DEPRECIATION_MONTHS   \n");
        query.append("FROM   \n");
        query.append("        HP1DM07T   \n");
        query.append("WHERE   \n");
        query.append("        INVEST_TYPE_CD = :INVEST_TYPE_CD  \n");
        sobj.setSql(query.toString());
        sobj.setString("INVEST_TYPE_CD", INVEST_TYPE_CD);               
        return sobj;
    }
    public DOBJ CALLBP_HP2D002T_DPR_SEL11(Connection Conn, DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL11","latest M_DCP_PLAN_YYYYMM");
        QExecutor qexe = new QExecutor(dobj);
        dobj.setRtnname("SEL11");
        VOBJ dvobj = dobj.getRetObject("S");           
        SQLObject  sobj = SQLBP_HP2D002T_DPR_SEL11(dobj, dvobj);
        qexe.DispSelectSql(sobj);
        dvobj = qexe.executeQuery(Conn, sobj);
        dvobj.setName("SEL11");
        dvobj.Println("SEL11");
        dobj.setRetObject(dvobj);
        return dobj;
    }
    private SQLObject SQLBP_HP2D002T_DPR_SEL11(DOBJ dobj, VOBJ dvobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj);
        String   PID = dobj.getRetObject("R").getRecord().get("PID");   
        SQLObject sobj = new SQLObject();
        StringBuffer query = new StringBuffer();
        query.append("SELECT   \n");
        query.append("          PID   \n");
        query.append("        , DCP_TYPE   \n");
        query.append("        , APPROVAL_YYYYMMDD   \n");
        query.append("        , SUBSTR(M_DCP_PLAN_YYYYMMDD, 1, 6) AS M_DCP_PLAN_YYYYMM   \n");
        query.append("FROM   \n");
        query.append("        HP1D001T_TZ   \n");
        query.append("WHERE   \n");
        query.append("        PID = :PID   \n");
        query.append("   AND APPROVAL_YYYYMMDD||TO_CHAR(REGIST_TIME, 'YYYYMMDDHH24MISS')   \n");
        query.append("          = ( SELECT MAX(APPROVAL_YYYYMMDD||TO_CHAR(REGIST_TIME, 'YYYYMMDDHH24MISS'))   \n");
        query.append("               FROM HP1D001T_TZ   \n");
        query.append("              WHERE PID = :PID   \n");
        query.append("              GROUP BY PID   \n");
        query.append("            )   \n");
        query.append("   AND ROWNUM = 1  \n");
        sobj.setSql(query.toString());
        sobj.setString("PID", PID);               
        return sobj;
    }
    public DOBJ CALLBP_HP2D002T_DPR_SEL5(Connection Conn, DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL5","latest M_DCP_PLAN_YYYYMM");
        QExecutor qexe = new QExecutor(dobj);
        dobj.setRtnname("SEL5");
        VOBJ dvobj = dobj.getRetObject("S");           
        SQLObject  sobj = SQLBP_HP2D002T_DPR_SEL5(dobj, dvobj);
        qexe.DispSelectSql(sobj);
        dvobj = qexe.executeQuery(Conn, sobj);
        dvobj.setName("SEL5");
        dvobj.Println("SEL5");
        dobj.setRetObject(dvobj);
        return dobj;
    }
    private SQLObject SQLBP_HP2D002T_DPR_SEL5(DOBJ dobj, VOBJ dvobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj);
        String   PID = dobj.getRetObject("R").getRecord().get("PID");   
        SQLObject sobj = new SQLObject();
        StringBuffer query = new StringBuffer();
        query.append("SELECT   \n");
        query.append("          PID   \n");
        query.append("        , DCP_TYPE   \n");
        query.append("        , APPROVAL_YYYYMMDD   \n");
        query.append("        , SUBSTR(M_DCP_PLAN_YYYYMMDD, 1, 6) AS M_DCP_PLAN_YYYYMM   \n");
        query.append("FROM   \n");
        query.append("        HP1D001T_TZ   \n");
        query.append("WHERE   \n");
        query.append("        PID = :PID   \n");
        query.append("   AND APPROVAL_YYYYMMDD||TO_CHAR(REGIST_TIME, 'YYYYMMDDHH24MISS')   \n");
        query.append("          = ( SELECT MAX(APPROVAL_YYYYMMDD||TO_CHAR(REGIST_TIME, 'YYYYMMDDHH24MISS'))   \n");
        query.append("               FROM HP1D001T_TZ   \n");
        query.append("              WHERE PID = :PID   \n");
        query.append("              GROUP BY PID   \n");
        query.append("            )   \n");
        query.append("   AND ROWNUM = 1  \n");
        sobj.setSql(query.toString());
        sobj.setString("PID", PID);               
        return sobj;
    }
    public DOBJ CALLBP_HP2D002T_DPR_SEL21(Connection Conn, DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL21","Mass Cost Dept");
        QExecutor qexe = new QExecutor(dobj);
        dobj.setRtnname("SEL21");
        VOBJ dvobj = dobj.getRetObject("S");           
        SQLObject  sobj = SQLBP_HP2D002T_DPR_SEL21(dobj, dvobj);
        qexe.DispSelectSql(sobj);
        dvobj = qexe.executeQuery(Conn, sobj);
        dvobj.setName("SEL21");
        dvobj.Println("SEL21");
        dobj.setRetObject(dvobj);
        return dobj;
    }
    private SQLObject SQLBP_HP2D002T_DPR_SEL21(DOBJ dobj, VOBJ dvobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj);
        String   ORG_CD = dobj.getRetObject("R").getRecord().get("USE_ORG_CD");   
        String   COMPANY_CD = dobj.getRetObject("R").getRecord().get("USE_COMPANY_CD");   
        SQLObject sobj = new SQLObject();
        StringBuffer query = new StringBuffer();
        query.append("SELECT   \n");
        query.append("          COMPANY_CD   \n");
        query.append("        , ORG_CD   \n");
        query.append("        , COST_COMPANY_CD   \n");
        query.append("        , COST_ORG_CD   \n");
        query.append("FROM   \n");
        query.append("        HP1DM03T   \n");
        query.append("WHERE   \n");
        query.append("        COMPANY_CD = :COMPANY_CD   \n");
        query.append("  AND   ORG_CD = :ORG_CD  \n");
        sobj.setSql(query.toString());
        sobj.setString("ORG_CD", ORG_CD);               
        sobj.setString("COMPANY_CD", COMPANY_CD);               
        return sobj;
    }
    public DOBJ CALLBP_HP2D002T_DPR_SEL6(Connection Conn, DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL6","Mass Cost Dept");
        QExecutor qexe = new QExecutor(dobj);
        dobj.setRtnname("SEL6");
        VOBJ dvobj = dobj.getRetObject("S");           
        SQLObject  sobj = SQLBP_HP2D002T_DPR_SEL6(dobj, dvobj);
        qexe.DispSelectSql(sobj);
        dvobj = qexe.executeQuery(Conn, sobj);
        dvobj.setName("SEL6");
        dvobj.Println("SEL6");
        dobj.setRetObject(dvobj);
        return dobj;
    }
    private SQLObject SQLBP_HP2D002T_DPR_SEL6(DOBJ dobj, VOBJ dvobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj);
        String   ORG_CD = dobj.getRetObject("R").getRecord().get("USE_ORG_CD");   
        String   COMPANY_CD = dobj.getRetObject("R").getRecord().get("USE_COMPANY_CD");   
        SQLObject sobj = new SQLObject();
        StringBuffer query = new StringBuffer();
        query.append("SELECT   \n");
        query.append("          COMPANY_CD   \n");
        query.append("        , ORG_CD   \n");
        query.append("        , COST_COMPANY_CD   \n");
        query.append("        , COST_ORG_CD   \n");
        query.append("FROM   \n");
        query.append("        HP1DM03T   \n");
        query.append("WHERE   \n");
        query.append("        COMPANY_CD = :COMPANY_CD   \n");
        query.append("  AND   ORG_CD = :ORG_CD  \n");
        sobj.setSql(query.toString());
        sobj.setString("ORG_CD", ORG_CD);               
        sobj.setString("COMPANY_CD", COMPANY_CD);               
        return sobj;
    }
    public DOBJ CALLBP_HP2D002T_DPR_SEL20(Connection Conn, DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL20","Mass Dept Change");
        QExecutor qexe = new QExecutor(dobj);
        dobj.setRtnname("SEL20");
        VOBJ dvobj = dobj.getRetObject("S");           
        SQLObject  sobj = SQLBP_HP2D002T_DPR_SEL20(dobj, dvobj);
        qexe.DispSelectSql(sobj);
        dvobj = qexe.executeQuery(Conn, sobj);
        dvobj.setName("SEL20");
        dvobj.Println("SEL20");
        dobj.setRetObject(dvobj);
        return dobj;
    }
    private SQLObject SQLBP_HP2D002T_DPR_SEL20(DOBJ dobj, VOBJ dvobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj);
        String   VAL2 = dobj.getRetObject("R").getRecord().get("VAL2");   
        String   VAL3 = dobj.getRetObject("R").getRecord().get("VAL3");   
        String   PID = dobj.getRetObject("R").getRecord().get("PID");   
        String   UNIT2 = dobj.getRetObject("R").getRecord().get("UNIT2");   
        String   UNIT3 = dobj.getRetObject("R").getRecord().get("UNIT3");   
        String   VAL_TYPE = dobj.getRetObject("R").getRecord().get("VAL_TYPE");   
        String   APPLICATION = dobj.getRetObject("R").getRecord().get("APPLICATION");   
        String   DST_ORG_CD = dobj.getRetObject("R").getRecord().get("DST_ORG_CD");   
        String   DST_COMPANY_CD = dobj.getRetObject("R").getRecord().get("DST_COMPANY_CD");   
        String   YYYYMM = dobj.getRetObject("R").getRecord().get("YYYYMM");   
        String   CALC_MST_VER = dobj.getRetObject("R").getRecord().get("CALC_MST_VER");   
        String   SRC_COMPANY_CD = dobj.getRetObject("R").getRecord().get("SRC_COMPANY_CD");   
        String   DATA_TYPE = dobj.getRetObject("R").getRecord().get("DATA_TYPE");   
        String   USE_COMPANY_CD = dobj.getRetObject("R").getRecord().get("USE_COMPANY_CD");   
        String   CUSTOMER_CD = dobj.getRetObject("R").getRecord().get("CUSTOMER_CD");   
        String   UNIT = dobj.getRetObject("R").getRecord().get("UNIT");   
        String   HPMS_ID = dobj.getRetObject("R").getRecord().get("HPMS_ID");   
        String   COST_COMPANY_CD = dobj.getRetObject("SEL21").getRecord().get("COST_COMPANY_CD");   
        String   INVEST_TYPE_CD = dobj.getRetObject("R").getRecord().get("INVEST_TYPE_CD");   
        double   VAL = dobj.getRetObject("R").getRecord().getDouble("VAL");   
        String   SRC_ORG_CD = dobj.getRetObject("R").getRecord().get("SRC_ORG_CD");   
        String   COST_ORG_CD = dobj.getRetObject("SEL21").getRecord().get("COST_ORG_CD");   
        String   ITEM_NAME = dobj.getRetObject("R").getRecord().get("ITEM_NAME");   
        String   REQ_ORG_CD = dobj.getRetObject("R").getRecord().get("REQ_ORG_CD");   
        String   REQ_COMPANY_CD = dobj.getRetObject("R").getRecord().get("REQ_COMPANY_CD");   
        String   USE_ORG_CD = dobj.getRetObject("R").getRecord().get("USE_ORG_CD");   
        SQLObject sobj = new SQLObject();
        StringBuffer query = new StringBuffer();
        query.append("SELECT   \n");
        query.append("          Z.DATA_TYPE   \n");
        query.append("        , Z.PID   \n");
        query.append("        , Z.USE_COMPANY_CD          AS OLD_USE_COMPANY_CD   \n");
        query.append("        , Z.USE_ORG_CD                  AS OLD_USE_ORG_CD   \n");
        query.append("        , NVL(:COST_COMPANY_CD, Z.USE_COMPANY_CD)       AS  USE_COMPANY_CD      \n");
        query.append("        , NVL(:COST_ORG_CD, Z.USE_ORG_CD)               AS  USE_ORG_CD         \n");
        query.append("        , Z.REQ_COMPANY_CD   \n");
        query.append("        , Z.REQ_ORG_CD   \n");
        query.append("        , Z.SRC_COMPANY_CD            AS SRC_COMPANY_CD   \n");
        query.append("        , Z.SRC_ORG_CD                AS SRC_ORG_CD   \n");
        query.append("        , Z.DST_COMPANY_CD            AS DST_COMPANY_CD   \n");
        query.append("        , Z.DST_ORG_CD                AS DST_ORG_CD     \n");
        query.append("        , Z.HPMS_ID     AS OLD_HPMS_ID   \n");
        query.append("        , H.MASS_HPMS_ID        AS  HPMS_ID        \n");
        query.append("        , Z.INVEST_TYPE_CD   \n");
        query.append("        , Z.APPLICATION   \n");
        query.append("        , Z.ITEM_NAME   \n");
        query.append("        , Z.CUSTOMER_CD   \n");
        query.append("        , Z.YYYYMM                 \n");
        query.append("        , Z.UNIT   \n");
        query.append("        , Z.VAL   \n");
        query.append("        , Z.UNIT2   \n");
        query.append("        , Z.VAL2   \n");
        query.append("        , Z.UNIT3   \n");
        query.append("        , Z.VAL3   \n");
        query.append("        , 20                                                                            AS VAL_TYPE   \n");
        query.append("        , Z.CALC_MST_VER   \n");
        query.append("   FROM (   \n");
        query.append("            SELECT    :DATA_TYPE         AS DATA_TYPE   \n");
        query.append("                    , :PID               AS PID   \n");
        query.append("                    , :USE_COMPANY_CD    AS USE_COMPANY_CD   \n");
        query.append("                    , :USE_ORG_CD        AS USE_ORG_CD   \n");
        query.append("                    , :REQ_COMPANY_CD    AS REQ_COMPANY_CD   \n");
        query.append("                    , :REQ_ORG_CD        AS REQ_ORG_CD   \n");
        query.append("                    , :SRC_COMPANY_CD    AS SRC_COMPANY_CD   \n");
        query.append("                    , :SRC_ORG_CD        AS SRC_ORG_CD   \n");
        query.append("                    , :DST_COMPANY_CD    AS DST_COMPANY_CD   \n");
        query.append("                    , :DST_ORG_CD        AS DST_ORG_CD   \n");
        query.append("                    , :HPMS_ID           AS HPMS_ID   \n");
        query.append("                    , :INVEST_TYPE_CD    AS INVEST_TYPE_CD   \n");
        query.append("                    , :APPLICATION       AS APPLICATION   \n");
        query.append("                    , :ITEM_NAME         AS ITEM_NAME   \n");
        query.append("                    , :CUSTOMER_CD       AS CUSTOMER_CD   \n");
        query.append("                    , :YYYYMM            AS YYYYMM   \n");
        query.append("                    , :UNIT              AS UNIT   \n");
        query.append("                    , :VAL               AS VAL   \n");
        query.append("                    , :UNIT2             AS UNIT2   \n");
        query.append("                    , :VAL2              AS VAL2   \n");
        query.append("                    , :UNIT3             AS UNIT3   \n");
        query.append("                    , :VAL3              AS VAL3   \n");
        query.append("                    , :VAL_TYPE          AS VAL_TYPE   \n");
        query.append("                    , :CALC_MST_VER      AS CALC_MST_VER   \n");
        query.append("              FROM   \n");
        query.append("                     DUAL   \n");
        query.append("           ) Z   \n");
        query.append("         , (   \n");
        query.append("            SELECT   \n");
        query.append("                     SRC_HPMS_ID   \n");
        query.append("                   , RD_HPMS_ID   \n");
        query.append("                   , MASS_HPMS_ID   \n");
        query.append("              FROM   \n");
        query.append("                    HP1DM08T   \n");
        query.append("             WHERE   \n");
        query.append("                    RD_HPMS_ID = :HPMS_ID   \n");
        query.append("              AND ROWNUM = 1   \n");
        query.append("          ) H  \n");
        sobj.setSql(query.toString());
        sobj.setString("VAL2", VAL2);               
        sobj.setString("VAL3", VAL3);               
        sobj.setString("PID", PID);               
        sobj.setString("UNIT2", UNIT2);               
        sobj.setString("UNIT3", UNIT3);               
        sobj.setString("VAL_TYPE", VAL_TYPE);               
        sobj.setString("APPLICATION", APPLICATION);               
        sobj.setString("DST_ORG_CD", DST_ORG_CD);               
        sobj.setString("DST_COMPANY_CD", DST_COMPANY_CD);               
        sobj.setString("YYYYMM", YYYYMM);               
        sobj.setString("CALC_MST_VER", CALC_MST_VER);               
        sobj.setString("SRC_COMPANY_CD", SRC_COMPANY_CD);               
        sobj.setString("DATA_TYPE", DATA_TYPE);               
        sobj.setString("USE_COMPANY_CD", USE_COMPANY_CD);               
        sobj.setString("CUSTOMER_CD", CUSTOMER_CD);               
        sobj.setString("UNIT", UNIT);               
        sobj.setString("HPMS_ID", HPMS_ID);               
        sobj.setString("COST_COMPANY_CD", COST_COMPANY_CD);               
        sobj.setString("INVEST_TYPE_CD", INVEST_TYPE_CD);               
        sobj.setDouble("VAL", VAL);               
        sobj.setString("SRC_ORG_CD", SRC_ORG_CD);               
        sobj.setString("COST_ORG_CD", COST_ORG_CD);               
        sobj.setString("ITEM_NAME", ITEM_NAME);               
        sobj.setString("REQ_ORG_CD", REQ_ORG_CD);               
        sobj.setString("REQ_COMPANY_CD", REQ_COMPANY_CD);               
        sobj.setString("USE_ORG_CD", USE_ORG_CD);               
        return sobj;
    }
    public DOBJ CALLBP_HP2D002T_DPR_SEL10(Connection Conn, DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL10","Depre Process");
        QExecutor qexe = new QExecutor(dobj);
        dobj.setRtnname("SEL10");
        VOBJ dvobj = dobj.getRetObject("S");           
        SQLObject  sobj = SQLBP_HP2D002T_DPR_SEL10(dobj, dvobj);
        qexe.DispSelectSql(sobj);
        dvobj = qexe.executeQuery(Conn, sobj);
        dvobj.setName("SEL10");
        dvobj.Println("SEL10");
        dobj.setRetObject(dvobj);
        return dobj;
    }
    private SQLObject SQLBP_HP2D002T_DPR_SEL10(DOBJ dobj, VOBJ dvobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj);
        String   VAL2 = dobj.getRetObject("R").getRecord().get("VAL2");   
        String   VAL3 = dobj.getRetObject("R").getRecord().get("VAL3");   
        String   PID = dobj.getRetObject("R").getRecord().get("PID");   
        String   UNIT2 = dobj.getRetObject("R").getRecord().get("UNIT2");   
        String   UNIT3 = dobj.getRetObject("R").getRecord().get("UNIT3");   
        int   DEPRECIATION_MONTHS = dobj.getRetObject("SEL4").getRecord().getInt("DEPRECIATION_MONTHS");   
        String   VAL_TYPE = dobj.getRetObject("R").getRecord().get("VAL_TYPE");   
        String   APPLICATION = dobj.getRetObject("R").getRecord().get("APPLICATION");   
        String   DST_ORG_CD = dobj.getRetObject("R").getRecord().get("DST_ORG_CD");   
        String   DST_COMPANY_CD = dobj.getRetObject("R").getRecord().get("DST_COMPANY_CD");   
        String   YYYYMM = dobj.getRetObject("R").getRecord().get("YYYYMM");   
        String   CALC_MST_VER = dobj.getRetObject("R").getRecord().get("CALC_MST_VER");   
        String   SRC_COMPANY_CD = dobj.getRetObject("R").getRecord().get("SRC_COMPANY_CD");   
        String   DATA_TYPE = dobj.getRetObject("R").getRecord().get("DATA_TYPE");   
        String   M_DCP_PLAN_YYYYMM = dobj.getRetObject("SEL5").getRecord().get("M_DCP_PLAN_YYYYMM");   
        String   USE_COMPANY_CD = dobj.getRetObject("R").getRecord().get("USE_COMPANY_CD");   
        String   CUSTOMER_CD = dobj.getRetObject("R").getRecord().get("CUSTOMER_CD");   
        String   UNIT = dobj.getRetObject("R").getRecord().get("UNIT");   
        String   HPMS_ID = dobj.getRetObject("R").getRecord().get("HPMS_ID");   
        String   COST_COMPANY_CD = dobj.getRetObject("SEL6").getRecord().get("COST_COMPANY_CD");   
        String   INVEST_TYPE_CD = dobj.getRetObject("R").getRecord().get("INVEST_TYPE_CD");   
        double   VAL = dobj.getRetObject("R").getRecord().getDouble("VAL");   
        String   SRC_ORG_CD = dobj.getRetObject("R").getRecord().get("SRC_ORG_CD");   
        String   COST_ORG_CD = dobj.getRetObject("SEL6").getRecord().get("COST_ORG_CD");   
        String   ITEM_NAME = dobj.getRetObject("R").getRecord().get("ITEM_NAME");   
        String   REQ_ORG_CD = dobj.getRetObject("R").getRecord().get("REQ_ORG_CD");   
        String   REQ_COMPANY_CD = dobj.getRetObject("R").getRecord().get("REQ_COMPANY_CD");   
        String   USE_ORG_CD = dobj.getRetObject("R").getRecord().get("USE_ORG_CD");   
        SQLObject sobj = new SQLObject();
        StringBuffer query = new StringBuffer();
        query.append("SELECT   \n");
        query.append("          Z.RNO   \n");
        query.append("        , Z.DATA_TYPE   \n");
        query.append("        , Z.PID   \n");
        query.append("        , Z.USE_COMPANY_CD              AS OLD_USE_COMPANY_CD   \n");
        query.append("        , Z.USE_ORG_CD                  AS OLD_USE_ORG_CD   \n");
        query.append("        , CASE WHEN Z.DEPRECIATION_YYYYMM >= NVL(:M_DCP_PLAN_YYYYMM, '999912')   \n");
        query.append("                    THEN NVL(:COST_COMPANY_CD, Z.USE_COMPANY_CD)            \n");
        query.append("                    ELSE  Z.USE_COMPANY_CD             \n");
        query.append("          END                                                                      AS  USE_COMPANY_CD   \n");
        query.append("        , CASE WHEN Z.DEPRECIATION_YYYYMM >= NVL(:M_DCP_PLAN_YYYYMM, '999912')   \n");
        query.append("                    THEN NVL(:COST_ORG_CD, Z.USE_ORG_CD)            \n");
        query.append("                    ELSE Z.USE_ORG_CD               \n");
        query.append("          END                                                                      AS  USE_ORG_CD   \n");
        query.append("        , Z.REQ_COMPANY_CD   \n");
        query.append("        , Z.REQ_ORG_CD   \n");
        query.append("   \n");
        query.append("        , Z.SRC_COMPANY_CD            AS SRC_COMPANY_CD   \n");
        query.append("        , Z.SRC_ORG_CD                AS SRC_ORG_CD   \n");
        query.append("        , Z.DST_COMPANY_CD            AS DST_COMPANY_CD   \n");
        query.append("        , Z.DST_ORG_CD                AS DST_ORG_CD     \n");
        query.append("        , Z.HPMS_ID                                                                AS OLD_HPMS_ID   \n");
        query.append("        , CASE WHEN Z.DEPRECIATION_YYYYMM >= NVL(:M_DCP_PLAN_YYYYMM, '999912')   \n");
        query.append("                    THEN H.MASS_HPMS_ID            \n");
        query.append("                    ELSE  H.RD_HPMS_ID             \n");
        query.append("          END                                                                      AS  HPMS_ID   \n");
        query.append("        , Z.INVEST_TYPE_CD   \n");
        query.append("        , Z.APPLICATION   \n");
        query.append("        , Z.ITEM_NAME   \n");
        query.append("        , Z.CUSTOMER_CD   \n");
        query.append("        , Z.YYYYMM                  AS OLD_YYYYMM   \n");
        query.append("        , Z.DEPRECIATION_YYYYMM     AS YYYYMM   \n");
        query.append("        , Z.UNIT   \n");
        query.append("        , Z.VAL                     AS OLD_VAL   \n");
        query.append("        , (Z.VAL / NVL(Z.DEPRECIATION_MONTHS,1)) AS VAL   \n");
        query.append("        , Z.UNIT2   \n");
        query.append("        , Z.VAL2                    AS OLD_VAL2   \n");
        query.append("        , (Z.VAL2 / NVL(Z.DEPRECIATION_MONTHS,1)) AS VAL2   \n");
        query.append("        , Z.UNIT3   \n");
        query.append("        , Z.VAL3                    AS OLD_VAL3   \n");
        query.append("        , (Z.VAL3 / NVL(Z.DEPRECIATION_MONTHS,1)) AS VAL3   \n");
        query.append("        , 20                                                                       AS VAL_TYPE   \n");
        query.append("        , Z.CALC_MST_VER   \n");
        query.append("   FROM (   \n");
        query.append("            SELECT    Y.RNO              AS RNO   \n");
        query.append("                    ,  :DATA_TYPE         AS DATA_TYPE   \n");
        query.append("                    , :PID               AS PID   \n");
        query.append("                    , :USE_COMPANY_CD    AS USE_COMPANY_CD   \n");
        query.append("                    , :USE_ORG_CD        AS USE_ORG_CD   \n");
        query.append("                    , :REQ_COMPANY_CD    AS REQ_COMPANY_CD   \n");
        query.append("                    , :REQ_ORG_CD        AS REQ_ORG_CD   \n");
        query.append("                    , :SRC_COMPANY_CD    AS SRC_COMPANY_CD   \n");
        query.append("                    , :SRC_ORG_CD        AS SRC_ORG_CD   \n");
        query.append("                    , :DST_COMPANY_CD    AS DST_COMPANY_CD   \n");
        query.append("                    , :DST_ORG_CD        AS DST_ORG_CD   \n");
        query.append("                    , :HPMS_ID           AS HPMS_ID   \n");
        query.append("                    , :INVEST_TYPE_CD    AS INVEST_TYPE_CD   \n");
        query.append("                    , :APPLICATION       AS APPLICATION   \n");
        query.append("                    , :ITEM_NAME         AS ITEM_NAME   \n");
        query.append("                    , :CUSTOMER_CD       AS CUSTOMER_CD   \n");
        query.append("                    , :YYYYMM            AS YYYYMM   \n");
        query.append("                    , :UNIT              AS UNIT   \n");
        query.append("                    , :VAL               AS VAL   \n");
        query.append("                    , :UNIT2             AS UNIT2   \n");
        query.append("                    , :VAL2              AS VAL2   \n");
        query.append("                    , :UNIT3             AS UNIT3   \n");
        query.append("                    , :VAL3              AS VAL3   \n");
        query.append("                    , :VAL_TYPE          AS VAL_TYPE   \n");
        query.append("                    , :CALC_MST_VER      AS CALC_MST_VER   \n");
        query.append("                    , TO_CHAR(ADD_MONTHS(TO_DATE(:YYYYMM, 'YYYYMM'), Y.RNO - 1), 'YYYYMM') AS  DEPRECIATION_YYYYMM   \n");
        query.append("                    , Y.DEPRECIATION_MONTHS   \n");
        query.append("              FROM   \n");
        query.append("                     DUAL   \n");
        query.append("                 , (SELECT ROWNUM AS RNO, :DEPRECIATION_MONTHS AS DEPRECIATION_MONTHS   \n");
        query.append("                      FROM DUAL   \n");
        query.append("                    CONNECT BY ROWNUM <= :DEPRECIATION_MONTHS   \n");
        query.append("                   ) Y   \n");
        query.append("             ORDER BY RNO   \n");
        query.append("           ) Z   \n");
        query.append("         , (   \n");
        query.append("            SELECT   \n");
        query.append("                     SRC_HPMS_ID   \n");
        query.append("                   , RD_HPMS_ID   \n");
        query.append("                   , MASS_HPMS_ID   \n");
        query.append("              FROM   \n");
        query.append("                    HP1DM08T   \n");
        query.append("             WHERE   \n");
        query.append("                    SRC_HPMS_ID = :HPMS_ID   \n");
        query.append("          ) H  \n");
        sobj.setSql(query.toString());
        sobj.setString("VAL2", VAL2);               
        sobj.setString("VAL3", VAL3);               
        sobj.setString("PID", PID);               
        sobj.setString("UNIT2", UNIT2);               
        sobj.setString("UNIT3", UNIT3);               
        sobj.setInt("DEPRECIATION_MONTHS", DEPRECIATION_MONTHS);               
        sobj.setString("VAL_TYPE", VAL_TYPE);               
        sobj.setString("APPLICATION", APPLICATION);               
        sobj.setString("DST_ORG_CD", DST_ORG_CD);               
        sobj.setString("DST_COMPANY_CD", DST_COMPANY_CD);               
        sobj.setString("YYYYMM", YYYYMM);               
        sobj.setString("CALC_MST_VER", CALC_MST_VER);               
        sobj.setString("SRC_COMPANY_CD", SRC_COMPANY_CD);               
        sobj.setString("DATA_TYPE", DATA_TYPE);               
        sobj.setString("M_DCP_PLAN_YYYYMM", M_DCP_PLAN_YYYYMM);               
        sobj.setString("USE_COMPANY_CD", USE_COMPANY_CD);               
        sobj.setString("CUSTOMER_CD", CUSTOMER_CD);               
        sobj.setString("UNIT", UNIT);               
        sobj.setString("HPMS_ID", HPMS_ID);               
        sobj.setString("COST_COMPANY_CD", COST_COMPANY_CD);               
        sobj.setString("INVEST_TYPE_CD", INVEST_TYPE_CD);               
        sobj.setDouble("VAL", VAL);               
        sobj.setString("SRC_ORG_CD", SRC_ORG_CD);               
        sobj.setString("COST_ORG_CD", COST_ORG_CD);               
        sobj.setString("ITEM_NAME", ITEM_NAME);               
        sobj.setString("REQ_ORG_CD", REQ_ORG_CD);               
        sobj.setString("REQ_COMPANY_CD", REQ_COMPANY_CD);               
        sobj.setString("USE_ORG_CD", USE_ORG_CD);               
        return sobj;
    }
    public DOBJ CALLBP_HP2D002T_DPR_XIUD26(Connection Conn, DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"XIUD26","RD update");
        QExecutor qexe = new QExecutor(dobj);
        dobj.setRtnname("XIUD26");
        VOBJ dvobj = dobj.getRetObject("SEL20");            
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        int        updcnt =0;
        dvobj.first();
        while(dvobj.next())
        {
            sobj = SQLBP_HP2D002T_DPR_XIUD26(dobj, dvobj);
            updcnt += qexe.executeUpdate(Conn, sobj);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT","INT");
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("XIUD26");
        dobj.setRetObject(rvobj);
        return dobj;
    }
    private SQLObject SQLBP_HP2D002T_DPR_XIUD26(DOBJ dobj, VOBJ dvobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj);
        String   APPLICATION = dvobj.getRecord().get("APPLICATION");   
        String   CUSTOMER_CD = dvobj.getRecord().get("CUSTOMER_CD");   
        String   DATA_TYPE = dvobj.getRecord().get("DATA_TYPE");   
        String   DST_COMPANY_CD = dvobj.getRecord().get("DST_COMPANY_CD");   
        String   DST_ORG_CD = dvobj.getRecord().get("DST_ORG_CD");   
        String   HPMS_ID = dvobj.getRecord().get("HPMS_ID");   
        String   INVEST_TYPE_CD = dvobj.getRecord().get("INVEST_TYPE_CD");   
        String   ITEM_NAME = dvobj.getRecord().get("ITEM_NAME");   
        String   OLD_HPMS_ID = dvobj.getRecord().get("OLD_HPMS_ID");   
        String   OLD_USE_COMPANY_CD = dvobj.getRecord().get("OLD_USE_COMPANY_CD");   
        String   OLD_USE_ORG_CD = dvobj.getRecord().get("OLD_USE_ORG_CD");   
        String   PID = dvobj.getRecord().get("PID");   
        String   REQ_COMPANY_CD = dvobj.getRecord().get("REQ_COMPANY_CD");   
        String   REQ_ORG_CD = dvobj.getRecord().get("REQ_ORG_CD");   
        String   SRC_COMPANY_CD = dvobj.getRecord().get("SRC_COMPANY_CD");   
        String   SRC_ORG_CD = dvobj.getRecord().get("SRC_ORG_CD");   
        String   USE_COMPANY_CD = dvobj.getRecord().get("USE_COMPANY_CD");   
        String   USE_ORG_CD = dvobj.getRecord().get("USE_ORG_CD");   
        String   VAL_TYPE = dvobj.getRecord().get("VAL_TYPE");   
        String   YYYYMM = dvobj.getRecord().get("YYYYMM");   
        SQLObject sobj = new SQLObject();
        StringBuffer query = new StringBuffer();
        query.append(" UPDATE HP2D002T  \n");
        query.append(" SET USE_COMPANY_CD = :USE_COMPANY_CD ,  \n");
        query.append(" USE_ORG_CD =  \n");
        query.append(" :USE_ORG_CD , HPMS_ID = :HPMS_ID , VAL_TYPE = :VAL_TYPE , UPDATE_TIME = SYSDATE  \n");
        query.append(" WHERE DATA_TYPE = :DATA_TYPE  \n");
        query.append(" AND PID = :PID  \n");
        query.append(" AND USE_COMPANY_CD = :OLD_USE_COMPANY_CD  \n");
        query.append(" AND  \n");
        query.append(" USE_ORG_CD =  \n");
        query.append(" :OLD_USE_ORG_CD  \n");
        query.append(" AND REQ_COMPANY_CD = :REQ_COMPANY_CD  \n");
        query.append(" AND  \n");
        query.append(" REQ_ORG_CD =  \n");
        query.append(" :REQ_ORG_CD  \n");
        query.append(" AND SRC_COMPANY_CD = :SRC_COMPANY_CD  \n");
        query.append(" AND  \n");
        query.append(" SRC_ORG_CD =  \n");
        query.append(" :SRC_ORG_CD  \n");
        query.append(" AND DST_COMPANY_CD = :DST_COMPANY_CD  \n");
        query.append(" AND  \n");
        query.append(" DST_ORG_CD =  \n");
        query.append(" :DST_ORG_CD  \n");
        query.append(" AND HPMS_ID = :OLD_HPMS_ID  \n");
        query.append(" AND INVEST_TYPE_CD = :INVEST_TYPE_CD  \n");
        query.append(" AND APPLICATION = :APPLICATION  \n");
        query.append(" AND ITEM_NAME = :ITEM_NAME  \n");
        query.append(" AND CUSTOMER_CD = :CUSTOMER_CD  \n");
        query.append(" AND YYYYMM = :YYYYMM  \n");
        query.append(" AND VAL_TYPE = 0");
        sobj.setSql(query.toString());
        sobj.setString("APPLICATION", APPLICATION);               
        sobj.setString("CUSTOMER_CD", CUSTOMER_CD);               
        sobj.setString("DATA_TYPE", DATA_TYPE);               
        sobj.setString("DST_COMPANY_CD", DST_COMPANY_CD);               
        sobj.setString("DST_ORG_CD", DST_ORG_CD);               
        sobj.setString("HPMS_ID", HPMS_ID);               
        sobj.setString("INVEST_TYPE_CD", INVEST_TYPE_CD);               
        sobj.setString("ITEM_NAME", ITEM_NAME);               
        sobj.setString("OLD_HPMS_ID", OLD_HPMS_ID);               
        sobj.setString("OLD_USE_COMPANY_CD", OLD_USE_COMPANY_CD);               
        sobj.setString("OLD_USE_ORG_CD", OLD_USE_ORG_CD);               
        sobj.setString("PID", PID);               
        sobj.setString("REQ_COMPANY_CD", REQ_COMPANY_CD);               
        sobj.setString("REQ_ORG_CD", REQ_ORG_CD);               
        sobj.setString("SRC_COMPANY_CD", SRC_COMPANY_CD);               
        sobj.setString("SRC_ORG_CD", SRC_ORG_CD);               
        sobj.setString("USE_COMPANY_CD", USE_COMPANY_CD);               
        sobj.setString("USE_ORG_CD", USE_ORG_CD);               
        sobj.setString("VAL_TYPE", VAL_TYPE);               
        sobj.setString("YYYYMM", YYYYMM);               
        return sobj;
    }
    public DOBJ CALLBP_HP2D002T_DPR_INS13(Connection Conn, DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"INS13","Derpre Records Save");
        QExecutor qexe = new QExecutor(dobj);
        dobj.setRtnname("INS13");
        VOBJ dvobj = dobj.getRetObject("SEL10");           
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        int        updcnt =0;
        dvobj.first();
        while(dvobj.next())
        {
            sobj = SQLBP_HP2D002T_DPR_INS13(dobj, dvobj);
            updcnt += qexe.executeUpdate(Conn, sobj);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT","INT");
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("INS13") ;
        dobj.setRetObject(rvobj);
        return dobj;
    }
    private SQLObject SQLBP_HP2D002T_DPR_INS13(DOBJ dobj, VOBJ dvobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj);
        String UPDATE_TIME ="SYSDATE";        
        String   USE_ORG_CD = dvobj.getRecord().get("USE_ORG_CD");   
        String   REQ_COMPANY_CD = dvobj.getRecord().get("REQ_COMPANY_CD");   
        String   ITEM_NAME = dvobj.getRecord().get("ITEM_NAME");   
        String   REQ_ORG_CD = dvobj.getRecord().get("REQ_ORG_CD");   
        String   SRC_ORG_CD = dvobj.getRecord().get("SRC_ORG_CD");   
        double   VAL = dvobj.getRecord().getDouble("VAL");   
        String   INVEST_TYPE_CD = dvobj.getRecord().get("INVEST_TYPE_CD");   
        String   HPMS_ID = dvobj.getRecord().get("HPMS_ID");   
        String   UNIT = dvobj.getRecord().get("UNIT");   
        String   CUSTOMER_CD = dvobj.getRecord().get("CUSTOMER_CD");   
        String   USE_COMPANY_CD = dvobj.getRecord().get("USE_COMPANY_CD");   
        String   DATA_TYPE = dvobj.getRecord().get("DATA_TYPE");   
        String   SRC_COMPANY_CD = dvobj.getRecord().get("SRC_COMPANY_CD");   
        String   CALC_MST_VER = dvobj.getRecord().get("CALC_MST_VER");   
        String   YYYYMM = dvobj.getRecord().get("YYYYMM");   
        String   DST_COMPANY_CD = dvobj.getRecord().get("DST_COMPANY_CD");   
        String   DST_ORG_CD = dvobj.getRecord().get("DST_ORG_CD");   
        String   VAL_TYPE = dvobj.getRecord().get("VAL_TYPE");   
        String   APPLICATION = dvobj.getRecord().get("APPLICATION");   
        String   UNIT3 = dvobj.getRecord().get("UNIT3");   
        String   UNIT2 = dvobj.getRecord().get("UNIT2");   
        String   PID = dvobj.getRecord().get("PID");   
        String   VAL3 = dvobj.getRecord().get("VAL3");   
        String   VAL2 = dvobj.getRecord().get("VAL2");   
        SQLObject sobj = new SQLObject();
        StringBuffer query = new StringBuffer();
        query.append(" Insert into HP_DBA01.HP2D002T (VAL2, VAL3, PID, UNIT2, UNIT3, APPLICATION, VAL_TYPE, DST_ORG_CD, DST_COMPANY_CD, YYYYMM, CALC_MST_VER, SRC_COMPANY_CD, DATA_TYPE, USE_COMPANY_CD, CUSTOMER_CD, UNIT, HPMS_ID, UPDATE_TIME, INVEST_TYPE_CD, VAL, SRC_ORG_CD, REQ_ORG_CD, ITEM_NAME, REQ_COMPANY_CD, USE_ORG_CD)  \n");
        query.append(" values(:VAL2 , :VAL3 , :PID , :UNIT2 , :UNIT3 , :APPLICATION , :VAL_TYPE , :DST_ORG_CD , :DST_COMPANY_CD , :YYYYMM , :CALC_MST_VER , :SRC_COMPANY_CD , :DATA_TYPE , :USE_COMPANY_CD , :CUSTOMER_CD , :UNIT , :HPMS_ID , SYSDATE, :INVEST_TYPE_CD , :VAL , :SRC_ORG_CD , :REQ_ORG_CD , :ITEM_NAME , :REQ_COMPANY_CD , :USE_ORG_CD )");
        sobj.setSql(query.toString());
        sobj.setString("USE_ORG_CD", USE_ORG_CD);               
        sobj.setString("REQ_COMPANY_CD", REQ_COMPANY_CD);               
        sobj.setString("ITEM_NAME", ITEM_NAME);               
        sobj.setString("REQ_ORG_CD", REQ_ORG_CD);               
        sobj.setString("SRC_ORG_CD", SRC_ORG_CD);               
        sobj.setDouble("VAL", VAL);               
        sobj.setString("INVEST_TYPE_CD", INVEST_TYPE_CD);               
        sobj.setString("HPMS_ID", HPMS_ID);               
        sobj.setString("UNIT", UNIT);               
        sobj.setString("CUSTOMER_CD", CUSTOMER_CD);               
        sobj.setString("USE_COMPANY_CD", USE_COMPANY_CD);               
        sobj.setString("DATA_TYPE", DATA_TYPE);               
        sobj.setString("SRC_COMPANY_CD", SRC_COMPANY_CD);               
        sobj.setString("CALC_MST_VER", CALC_MST_VER);               
        sobj.setString("YYYYMM", YYYYMM);               
        sobj.setString("DST_COMPANY_CD", DST_COMPANY_CD);               
        sobj.setString("DST_ORG_CD", DST_ORG_CD);               
        sobj.setString("VAL_TYPE", VAL_TYPE);               
        sobj.setString("APPLICATION", APPLICATION);               
        sobj.setString("UNIT3", UNIT3);               
        sobj.setString("UNIT2", UNIT2);               
        sobj.setString("PID", PID);               
        sobj.setString("VAL3", VAL3);               
        sobj.setString("VAL2", VAL2);               
        return sobj;
    }
    public DOBJ CALLBP_HP2D002T_DPR_NEW_DRP2(Connection Conn, DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"NEW_DRP2","check use");
        QExecutor qexe = new QExecutor(dobj);
        dobj.setRtnname("NEW_DRP2");
        VOBJ dvobj = dobj.getRetObject("S");           
        SQLObject  sobj = SQLBP_HP2D002T_DPR_NEW_DRP2(dobj, dvobj);
        dvobj = qexe.executeQuery(Conn, sobj);
        dvobj.setName("NEW_DRP2");
        dvobj.Println("NEW_DRP2");
        dobj.setRetObject(dvobj);
        return dobj;
    }
    private SQLObject SQLBP_HP2D002T_DPR_NEW_DRP2(DOBJ dobj, VOBJ dvobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj);
        String   PID = dobj.getRetObject("R").getRecord().get("PID");   
        String   DATA_TYPE = dobj.getRetObject("R").getRecord().get("DATA_TYPE");   
        SQLObject sobj = new SQLObject();
        StringBuffer query = new StringBuffer();
        query.append("SELECT   \n");
        query.append("          DATA_TYPE           \n");
        query.append("        , PID                 \n");
        query.append("        , USE_COMPANY_CD      \n");
        query.append("        , USE_ORG_CD          \n");
        query.append("        , REQ_COMPANY_CD      \n");
        query.append("        , REQ_ORG_CD          \n");
        query.append("        , SRC_COMPANY_CD      \n");
        query.append("        , SRC_ORG_CD          \n");
        query.append("        , DST_COMPANY_CD      \n");
        query.append("        , DST_ORG_CD          \n");
        query.append("        , HPMS_ID             \n");
        query.append("        , INVEST_TYPE_CD      \n");
        query.append("        , APPLICATION         \n");
        query.append("        , ITEM_NAME           \n");
        query.append("        , CUSTOMER_CD         \n");
        query.append("        , YYYYMM              \n");
        query.append("        , UNIT                \n");
        query.append("        , VAL                 \n");
        query.append("        , UNIT2               \n");
        query.append("        , VAL2                \n");
        query.append("        , UNIT3               \n");
        query.append("        , VAL3                \n");
        query.append("        , VAL_TYPE            \n");
        query.append("        , UPDATE_TIME         \n");
        query.append("        , UPDATE_USER_ID      \n");
        query.append("        , UPLOAD_FILE_NAME    \n");
        query.append("        , CALC_MST_VER        \n");
        query.append("FROM   \n");
        query.append("        HP2D002T   \n");
        query.append("WHERE   \n");
        query.append("        DATA_TYPE = :DATA_TYPE   \n");
        query.append("    AND PID = :PID   \n");
        query.append("    AND VAL_TYPE = '20'  \n");
        sobj.setSql(query.toString());
        sobj.setString("PID", PID);               
        sobj.setString("DATA_TYPE", DATA_TYPE);               
        return sobj;
    }
    public DOBJ CALLBP_HP2D002T_DPR_NEW_DRP(Connection Conn, DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"NEW_DRP","check use");
        QExecutor qexe = new QExecutor(dobj);
        dobj.setRtnname("NEW_DRP");
        VOBJ dvobj = dobj.getRetObject("S");           
        SQLObject  sobj = SQLBP_HP2D002T_DPR_NEW_DRP(dobj, dvobj);
        dvobj = qexe.executeQuery(Conn, sobj);
        dvobj.setName("NEW_DRP");
        dvobj.Println("NEW_DRP");
        dobj.setRetObject(dvobj);
        return dobj;
    }
    private SQLObject SQLBP_HP2D002T_DPR_NEW_DRP(DOBJ dobj, VOBJ dvobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj);
        String   PID = dobj.getRetObject("R").getRecord().get("PID");   
        String   DATA_TYPE = dobj.getRetObject("R").getRecord().get("DATA_TYPE");   
        SQLObject sobj = new SQLObject();
        StringBuffer query = new StringBuffer();
        query.append("SELECT   \n");
        query.append("          DATA_TYPE           \n");
        query.append("        , PID                 \n");
        query.append("        , USE_COMPANY_CD      \n");
        query.append("        , USE_ORG_CD          \n");
        query.append("        , REQ_COMPANY_CD      \n");
        query.append("        , REQ_ORG_CD          \n");
        query.append("        , SRC_COMPANY_CD      \n");
        query.append("        , SRC_ORG_CD          \n");
        query.append("        , DST_COMPANY_CD      \n");
        query.append("        , DST_ORG_CD          \n");
        query.append("        , HPMS_ID             \n");
        query.append("        , INVEST_TYPE_CD      \n");
        query.append("        , APPLICATION         \n");
        query.append("        , ITEM_NAME           \n");
        query.append("        , CUSTOMER_CD         \n");
        query.append("        , YYYYMM              \n");
        query.append("        , UNIT                \n");
        query.append("        , VAL                 \n");
        query.append("        , UNIT2               \n");
        query.append("        , VAL2                \n");
        query.append("        , UNIT3               \n");
        query.append("        , VAL3                \n");
        query.append("        , VAL_TYPE            \n");
        query.append("        , UPDATE_TIME         \n");
        query.append("        , UPDATE_USER_ID      \n");
        query.append("        , UPLOAD_FILE_NAME    \n");
        query.append("        , CALC_MST_VER        \n");
        query.append("FROM   \n");
        query.append("        HP2D002T   \n");
        query.append("WHERE   \n");
        query.append("        DATA_TYPE = :DATA_TYPE   \n");
        query.append("    AND PID = :PID   \n");
        query.append("    AND VAL_TYPE = '20'  \n");
        sobj.setSql(query.toString());
        sobj.setString("PID", PID);               
        sobj.setString("DATA_TYPE", DATA_TYPE);               
        return sobj;
    }

    public DOBJ BP_HP2D002T_CAL(DOBJ dobj)
    {
        WizUtil wutil = new WizUtil(dobj);
        Connection Conn = null;
        String message ="";
        try
        {
            Connector connection = new Connector();
            if( dobj.containKey("S") && dobj.getRetObject("S").ContainsColumnName("DBURL") && dobj.getRetObject("S").ContainsColumnName("DBID") && dobj.getRetObject("S").ContainsColumnName("DBPWD") )
            {
                VOBJ dvobj = dobj.getRetObject("S");
                Conn = connection.getConnectionDirect("oracle", dvobj.getRecord().get("DBURL"), dvobj.getRecord().get("DBID"), dvobj.getRecord().get("DBPWD") );
            }
            else
            {
                Conn = connection.getConnectionDirect("oracle","jdbc:oracle:thin:@133.232.125.190:1521:orcl","HP_DBA01","HP_DBA01");
            }
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
        	
        	StopWatch stopWatch = new StopWatch("batch start -4 ");
        	stopWatch.start("XIUD1");
        	 dobj  = CALLBP_HP2D002T_CAL_XIUD1(Conn, dobj);    
            stopWatch.stop();
        	System.out.println("XIUD1 " + stopWatch.getLastTaskTimeMillis() + " ms");
        	
        	stopWatch.start("SEL2");
        	dobj  = CALLBP_HP2D002T_CAL_SEL2(Conn, dobj);      
            stopWatch.stop();
        	System.out.println("SEL2" + stopWatch.getLastTaskTimeMillis() + " ms");
        	
        	stopWatch.start("MPD4");
        	dobj  = CALLBP_HP2D002T_CAL_MPD4(Conn, dobj);  
            stopWatch.stop();
        	System.out.println("MPD4 " + stopWatch.getLastTaskTimeMillis() + " ms");
        	
        	System.out.println(stopWatch.toString());
        	System.out.println();
        	System.out.println(stopWatch.prettyPrint());
        	
                  
                   
                       
            if(dobj.getRtncode() == 9)
            {
                Conn.rollback();
                return dobj;
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
    public DOBJ BP_HP2D002T_CAL( DOBJ dobj, Object Connx) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj);
        Connection Conn = (Connection)Connx;
        String message ="";
        dobj  = CALLBP_HP2D002T_CAL_XIUD1(Conn, dobj);           
        dobj  = CALLBP_HP2D002T_CAL_SEL2(Conn, dobj);           
        dobj  = CALLBP_HP2D002T_CAL_MPD4(Conn, dobj);           
        if(dobj.getRtncode() == 9)
        {
            Conn.rollback();
            return dobj;
        }
        return dobj;
    }
    public DOBJ CALLBP_HP2D002T_CAL_XIUD1(Connection Conn, DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"XIUD1","CAL Result delete");
        QExecutor qexe = new QExecutor(dobj);
        dobj.setRtnname("XIUD1");
        VOBJ dvobj = dobj.getRetObject("S");            
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        int        updcnt =0;
        dvobj.first();
        while(dvobj.next())
        {
            sobj = SQLBP_HP2D002T_CAL_XIUD1(dobj, dvobj);
            updcnt += qexe.executeUpdate(Conn, sobj);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT","INT");
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("XIUD1");
        dobj.setRetObject(rvobj);
        return dobj;
    }
    private SQLObject SQLBP_HP2D002T_CAL_XIUD1(DOBJ dobj, VOBJ dvobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj);
        String   DATA_TYPE = dvobj.getRecord().get("DATA_TYPE");   
        SQLObject sobj = new SQLObject();
        StringBuffer query = new StringBuffer();
        query.append(" DELETE FROM HP2D002T  \n");
        query.append(" WHERE VAL_TYPE = 10  \n");
        query.append(" AND DATA_TYPE LIKE DECODE(:DATA_TYPE, '' , '%' ,:DATA_TYPE)|| '%'");
        sobj.setSql(query.toString());
        sobj.setString("DATA_TYPE", DATA_TYPE);               
        return sobj;
    }
    public DOBJ CALLBP_HP2D002T_CAL_SEL2(Connection Conn, DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL2","Sel HP1M103T");
        QExecutor qexe = new QExecutor(dobj);
        dobj.setRtnname("SEL2");
        VOBJ dvobj = dobj.getRetObject("S");           
        SQLObject  sobj = SQLBP_HP2D002T_CAL_SEL2(dobj, dvobj);
        qexe.DispSelectSql(sobj);
        dvobj = qexe.executeQuery(Conn, sobj);
        dvobj.setName("SEL2");
        dvobj.Println("SEL2");
        dobj.setRetObject(dvobj);
        return dobj;
    }
    private SQLObject SQLBP_HP2D002T_CAL_SEL2(DOBJ dobj, VOBJ dvobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj);
        SQLObject sobj = new SQLObject();
        StringBuffer query = new StringBuffer();
        query.append("SELECT   \n");
        query.append("           SEQ   \n");
        query.append("         , HPMS_GRP_ID   \n");
        query.append("         , HPMS_ID   \n");
        query.append("         , CALC_FUNC   \n");
        query.append("         , CALC_COL1   \n");
        query.append("         , CALC_COL2   \n");
        query.append("FROM   \n");
        query.append("            HP1M103T   \n");
        query.append("WHERE   \n");
        query.append("         (1=1)   \n");
        query.append("   \n");
        query.append("ORDER BY SEQ  \n");
        sobj.setSql(query.toString());
        return sobj;
    }
    public DOBJ CALLBP_HP2D002T_CAL_MPD4(Connection Conn, DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"MPD4","Multi Proc");
        dobj.setRtnname("MPD4");
        VOBJ dvobj = dobj.getRetObject("SEL2");         
        dvobj.Println("MPD4");
        VOBJ       rvobj= null;
        
        
        StopWatch stopWatch = new StopWatch("batch start -5 ");
    	
    	

    	
    	
        
        
        
        dvobj.first();
        while(dvobj.next())
        {
            if(dvobj.getRecord().get("CALC_FUNC").equals("sum"))
            {
                dobj.resetObject("SUM_SRC,SEL9,INS14");
                dobj.setRetObject(dvobj.getRecVobj("R"));
                
                stopWatch.start("CAL_SUM_SRC");
                dobj  = CALLBP_HP2D002T_CAL_SUM_SRC(Conn, dobj);  
                stopWatch.stop();
            	System.out.println("CAL_SUM_SRC " + stopWatch.getLastTaskTimeMillis() + " ms");
                
                stopWatch.start("SEL9");
                dobj  = CALLBP_HP2D002T_CAL_SEL9(Conn, dobj);  
                stopWatch.stop();
            	System.out.println("SEL9 " + stopWatch.getLastTaskTimeMillis() + " ms");
                
                stopWatch.start("INS14");
                dobj  = CALLBP_HP2D002T_CAL_INS14(Conn, dobj);   
                stopWatch.stop();
            	System.out.println("INS14 " + stopWatch.getLastTaskTimeMillis() + " ms");
            }
            else
            {
                dobj.resetObject("OPR_SRC,SEL13,INS22");
                dobj.setRetObject(dvobj.getRecVobj("R"));
                stopWatch.start("OPR_SRC");
                dobj  = CALLBP_HP2D002T_CAL_OPR_SRC(Conn, dobj);  
                stopWatch.stop();
            	System.out.println("OPR_SRC " + stopWatch.getLastTaskTimeMillis() + " ms");
            	
                stopWatch.start("SEL13");
                dobj  = CALLBP_HP2D002T_CAL_SEL13(Conn, dobj);   
                stopWatch.stop();
            	System.out.println("SEL13 " + stopWatch.getLastTaskTimeMillis() + " ms");
                
                stopWatch.start("INS22");
                dobj  = CALLBP_HP2D002T_CAL_INS22(Conn, dobj);   
                stopWatch.stop();
            	System.out.println("INS22 " + stopWatch.getLastTaskTimeMillis() + " ms");
                
            }
        }
        
        System.out.println(stopWatch.toString());
    	System.out.println();
    	System.out.println(stopWatch.prettyPrint());
        return dobj;
    }
    public DOBJ CALLBP_HP2D002T_CAL_SUM_SRC(Connection Conn, DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SUM_SRC","SUM_SRC");
        QExecutor qexe = new QExecutor(dobj);
        dobj.setRtnname("SUM_SRC");
        VOBJ dvobj = dobj.getRetObject("S");           
        SQLObject  sobj = SQLBP_HP2D002T_CAL_SUM_SRC(dobj, dvobj);
        dvobj = qexe.executeQuery(Conn, sobj);
        dvobj.setName("SUM_SRC");
        dvobj.Println("SUM_SRC");
        dobj.setRetObject(dvobj);
        return dobj;
    }
    private SQLObject SQLBP_HP2D002T_CAL_SUM_SRC(DOBJ dobj, VOBJ dvobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj);
        String   CALC_COL1 = dobj.getRetObject("R").getRecord().get("CALC_COL1");   
        String   DATA_TYPE = dobj.getRetObject("S").getRecord().get("DATA_TYPE");   
        SQLObject sobj = new SQLObject();
        StringBuffer query = new StringBuffer();
        query.append("SELECT   \n");
        query.append("          DATA_TYPE, PID   \n");
        query.append("        , USE_COMPANY_CD, USE_ORG_CD   \n");
        query.append("        , HPMS_ID   \n");
        query.append("        , INVEST_TYPE_CD, APPLICATION, ITEM_NAME, CUSTOMER_CD   \n");
        query.append("        , YYYYMM   \n");
        query.append("        , UNIT   \n");
        query.append("        , VAL        \n");
        query.append("        , UNIT2   \n");
        query.append("        , VAL2   \n");
        query.append("        , UNIT3   \n");
        query.append("        , VAL3   \n");
        query.append("FROM   \n");
        query.append("      HP2D002T   \n");
        query.append("WHERE   \n");
        query.append("      HPMS_ID IN ( SELECT REGEXP_SUBSTR( REPLACE(:CALC_COL1, ' ', ''), '[^,]+', 1, LEVEL)   \n");
        query.append("                     FROM DUAL   \n");
        query.append("                  CONNECT BY REGEXP_SUBSTR( REPLACE(:CALC_COL1, ' ', ''), '[^,]+', 1, LEVEL) IS NOT NULL   \n");
        query.append("                )   \n");
        query.append("   AND VAL_TYPE IN (0, 10, 20)   \n");
        query.append("   AND DATA_TYPE LIKE DECODE(:DATA_TYPE,'','%',:DATA_TYPE)||'%'   \n");
        query.append("   \n");
        query.append("AND YYYYMM > F_GET_CONFIRM_YYYYMM()  \n");
        sobj.setSql(query.toString());
        sobj.setString("CALC_COL1", CALC_COL1);               
        sobj.setString("DATA_TYPE", DATA_TYPE);               
        return sobj;
    }
    public DOBJ CALLBP_HP2D002T_CAL_OPR_SRC(Connection Conn, DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"OPR_SRC","Sel Oper Target");
        QExecutor qexe = new QExecutor(dobj);
        dobj.setRtnname("OPR_SRC");
        VOBJ dvobj = dobj.getRetObject("S");           
        SQLObject  sobj = SQLBP_HP2D002T_CAL_OPR_SRC(dobj, dvobj);
        dvobj = qexe.executeQuery(Conn, sobj);
        dvobj.setName("OPR_SRC");
        dvobj.Println("OPR_SRC");
        dobj.setRetObject(dvobj);
        return dobj;
    }
    private SQLObject SQLBP_HP2D002T_CAL_OPR_SRC(DOBJ dobj, VOBJ dvobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj);
        String   CALC_COL2 = dobj.getRetObject("R").getRecord().get("CALC_COL2");   
        String   CALC_COL1 = dobj.getRetObject("R").getRecord().get("CALC_COL1");   
        String   DATA_TYPE = dobj.getRetObject("S").getRecord().get("DATA_TYPE");   
        SQLObject sobj = new SQLObject();
        StringBuffer query = new StringBuffer();
        query.append("SELECT    \n");
        query.append("        DATA_TYPE, PID    \n");
        query.append("      , USE_COMPANY_CD, USE_ORG_CD    \n");
        query.append("      , HPMS_ID    \n");
        query.append("      , INVEST_TYPE_CD, APPLICATION, ITEM_NAME, CUSTOMER_CD    \n");
        query.append("      , YYYYMM    \n");
        query.append("      , UNIT    \n");
        query.append("      , VAL         \n");
        query.append("      , UNIT2    \n");
        query.append("      , VAL2    \n");
        query.append("      , UNIT3    \n");
        query.append("      , VAL3    \n");
        query.append("FROM    \n");
        query.append("    HP2D002T    \n");
        query.append("WHERE    \n");
        query.append("    (HPMS_ID = :CALC_COL1 or HPMS_ID = :CALC_COL2)    \n");
        query.append(" AND VAL_TYPE IN (0, 10, 20)    \n");
        query.append(" AND YYYYMM > F_GET_CONFIRM_YYYYMM()    \n");
        if( !DATA_TYPE.equals("") )
        {
            query.append("       AND DATA_TYPE = :DATA_TYPE       \n");
        }
        sobj.setSql(query.toString());
        sobj.setString("CALC_COL2", CALC_COL2);               
        sobj.setString("CALC_COL1", CALC_COL1);               
        if(!DATA_TYPE.equals(""))
        {
            sobj.setString("DATA_TYPE", DATA_TYPE);               
        }
        return sobj;
    }
    public DOBJ CALLBP_HP2D002T_CAL_SEL9(Connection Conn, DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL9","Sum Prcs");
        QExecutor qexe = new QExecutor(dobj);
        dobj.setRtnname("SEL9");
        VOBJ dvobj = dobj.getRetObject("S");           
        SQLObject  sobj = SQLBP_HP2D002T_CAL_SEL9(dobj, dvobj);
        qexe.DispSelectSql(sobj);
        dvobj = qexe.executeQuery(Conn, sobj);
        dvobj.setName("SEL9");
        dvobj.Println("SEL9");
        dobj.setRetObject(dvobj);
        return dobj;
    }
    private SQLObject SQLBP_HP2D002T_CAL_SEL9(DOBJ dobj, VOBJ dvobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj);
        String   CALC_COL1 = dobj.getRetObject("R").getRecord().get("CALC_COL1");   
        String   NEW_HPMS_ID = dobj.getRetObject("R").getRecord().get("HPMS_ID");   
        String   DATA_TYPE = dobj.getRetObject("S").getRecord().get("DATA_TYPE");   
        SQLObject sobj = new SQLObject();
        StringBuffer query = new StringBuffer();
        query.append("SELECT   \n");
        query.append("          DATA_TYPE, PID   \n");
        query.append("        , USE_COMPANY_CD, USE_ORG_CD   \n");
        query.append("        , REQ_COMPANY_CD, REQ_ORG_CD   \n");
        query.append("        , '#' AS SRC_COMPANY_CD, '#' AS SRC_ORG_CD   \n");
        query.append("        , '#' AS DST_COMPANY_CD, '#' AS DST_ORG_CD   \n");
        query.append("        , :NEW_HPMS_ID AS HPMS_ID   \n");
        query.append("        , INVEST_TYPE_CD, APPLICATION, ITEM_NAME, CUSTOMER_CD   \n");
        query.append("        , YYYYMM   \n");
        query.append("        , MAX(UNIT)  AS UNIT , NVL(SUM(VAL), 0)  AS VAL   \n");
        query.append("        , MAX(UNIT2) AS UNIT2, NVL(SUM(VAL2), 0) AS VAL2   \n");
        query.append("        , MAX(UNIT3) AS UNIT3, NVL(SUM(VAL3), 0) AS VAL3   \n");
        query.append("        , 10   AS   VAL_TYPE   \n");
        query.append("    FROM (   \n");
        query.append("            SELECT   \n");
        query.append("                      DATA_TYPE, PID   \n");
        query.append("                    , USE_COMPANY_CD, USE_ORG_CD   \n");
        query.append("                    , REQ_COMPANY_CD, REQ_ORG_CD   \n");
        query.append("                    , HPMS_ID   \n");
        query.append("                    , INVEST_TYPE_CD, APPLICATION, ITEM_NAME, CUSTOMER_CD   \n");
        query.append("                    , YYYYMM   \n");
        query.append("                    , MAX(UNIT)  AS UNIT   , NVL(SUM(VAL), 0) AS VAL   \n");
        query.append("                    , MAX(UNIT2) AS UNIT2 , NVL(SUM(VAL2), 0) AS VAL2   \n");
        query.append("                    , MAX(UNIT3) AS UNIT3 , NVL(SUM(VAL3), 0) AS VAL3   \n");
        query.append("            FROM   \n");
        query.append("                  HP2D002T   \n");
        query.append("            WHERE 1=1   \n");
        query.append("              AND HPMS_ID IN ( SELECT REGEXP_SUBSTR( REPLACE(:CALC_COL1, ' ', ''), '[^,]+', 1, LEVEL)   \n");
        query.append("                                 FROM DUAL   \n");
        query.append("                              CONNECT BY REGEXP_SUBSTR( REPLACE(:CALC_COL1, ' ', ''), '[^,]+', 1, LEVEL) IS NOT NULL   \n");
        query.append("                            )   \n");
        query.append("              AND VAL_TYPE IN (0, 10, 20)   \n");
        query.append("              AND YYYYMM > F_GET_CONFIRM_YYYYMM()   \n");
        query.append("              AND DATA_TYPE LIKE DECODE(:DATA_TYPE,'','%',:DATA_TYPE)||'%'   \n");
        query.append("   \n");
        query.append("             GROUP BY   \n");
        query.append("                      DATA_TYPE, PID   \n");
        query.append("                    , USE_COMPANY_CD, USE_ORG_CD   \n");
        query.append("                    , REQ_COMPANY_CD, REQ_ORG_CD   \n");
        query.append("                    , HPMS_ID   \n");
        query.append("                    , INVEST_TYPE_CD, APPLICATION, ITEM_NAME, CUSTOMER_CD   \n");
        query.append("                    , YYYYMM   \n");
        query.append("        )   \n");
        query.append("GROUP BY   \n");
        query.append("        DATA_TYPE, PID   \n");
        query.append("      , USE_COMPANY_CD, USE_ORG_CD   \n");
        query.append("      , REQ_COMPANY_CD, REQ_ORG_CD   \n");
        query.append("      , INVEST_TYPE_CD, APPLICATION, ITEM_NAME, CUSTOMER_CD   \n");
        query.append("      , YYYYMM  \n");
        sobj.setSql(query.toString());
        sobj.setString("CALC_COL1", CALC_COL1);               
        sobj.setString("NEW_HPMS_ID", NEW_HPMS_ID);               
        sobj.setString("DATA_TYPE", DATA_TYPE);               
        return sobj;
    }
    public DOBJ CALLBP_HP2D002T_CAL_SEL13(Connection Conn, DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL13","Calc");
        QExecutor qexe = new QExecutor(dobj);
        dobj.setRtnname("SEL13");
        VOBJ dvobj = dobj.getRetObject("S");           
        SQLObject  sobj = SQLBP_HP2D002T_CAL_SEL13(dobj, dvobj);
        qexe.DispSelectSql(sobj);
        dvobj = qexe.executeQuery(Conn, sobj);
        dvobj.setName("SEL13");
        dvobj.Println("SEL13");
        dobj.setRetObject(dvobj);
        return dobj;
    }
    private SQLObject SQLBP_HP2D002T_CAL_SEL13(DOBJ dobj, VOBJ dvobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj);
        String   CALC_FUNC = dobj.getRetObject("R").getRecord().get("CALC_FUNC");   
        String   CALC_COL2 = dobj.getRetObject("R").getRecord().get("CALC_COL2");   
        String   CALC_COL1 = dobj.getRetObject("R").getRecord().get("CALC_COL1");   
        String   NEW_HPMS_ID = dobj.getRetObject("R").getRecord().get("HPMS_ID");   
        String   DATA_TYPE = dobj.getRetObject("S").getRecord().get("DATA_TYPE");   
        SQLObject sobj = new SQLObject();
        StringBuffer query = new StringBuffer();
        query.append("SELECT    \n");
        query.append("          DATA_TYPE, PID    \n");
        query.append("        , USE_COMPANY_CD, USE_ORG_CD    \n");
        query.append("        , REQ_COMPANY_CD, REQ_ORG_CD    \n");
        query.append("        , '#' AS SRC_COMPANY_CD  , '#' AS SRC_ORG_CD    \n");
        query.append("        , '#' AS DST_COMPANY_CD  , '#' AS DST_ORG_CD    \n");
        query.append("        , HPMS_ID    \n");
        query.append("        , INVEST_TYPE_CD, APPLICATION, ITEM_NAME, CUSTOMER_CD    \n");
        query.append("        , YYYYMM    \n");
        query.append("        , UNIT  , VAL            \n");
        query.append("        , UNIT2 , VAL2    \n");
        query.append("        , UNIT3 , VAL3          \n");
        query.append("        , 10                  AS VAL_TYPE    \n");
        query.append("FROM (    \n");
        query.append("       SELECT    \n");
        query.append("                DECODE(X.DATA_TYPE     , NULL, Y.DATA_TYPE     , X.DATA_TYPE     ) AS DATA_TYPE    \n");
        query.append("              , DECODE(X.PID           , NULL, Y.PID           , X.PID           ) AS PID    \n");
        query.append("              , DECODE(X.USE_COMPANY_CD, NULL, Y.USE_COMPANY_CD, X.USE_COMPANY_CD) AS USE_COMPANY_CD    \n");
        query.append("              , DECODE(X.USE_ORG_CD    , NULL, Y.USE_ORG_CD    , X.USE_ORG_CD    ) AS USE_ORG_CD    \n");
        query.append("              , DECODE(X.REQ_COMPANY_CD, NULL, Y.REQ_COMPANY_CD, X.REQ_COMPANY_CD) AS REQ_COMPANY_CD    \n");
        query.append("              , DECODE(X.REQ_ORG_CD    , NULL, Y.REQ_ORG_CD    , X.REQ_ORG_CD    ) AS REQ_ORG_CD    \n");
        query.append("              , :NEW_HPMS_ID                 AS HPMS_ID    \n");
        query.append("              , DECODE(X.INVEST_TYPE_CD, NULL, Y.INVEST_TYPE_CD, X.INVEST_TYPE_CD) AS INVEST_TYPE_CD    \n");
        query.append("              , DECODE(X.APPLICATION   , NULL, Y.APPLICATION   , X.APPLICATION   ) AS APPLICATION    \n");
        query.append("              , DECODE(X.ITEM_NAME     , NULL, Y.ITEM_NAME     , X.ITEM_NAME     ) AS ITEM_NAME    \n");
        query.append("              , DECODE(X.CUSTOMER_CD   , NULL, Y.CUSTOMER_CD   , X.CUSTOMER_CD   ) AS CUSTOMER_CD    \n");
        query.append("              , DECODE(X.YYYYMM        , NULL, Y.YYYYMM        , X.YYYYMM        ) AS YYYYMM    \n");
        query.append("              , DECODE(X.UNIT          , NULL, Y.UNIT          , X.UNIT          ) AS UNIT    \n");
        query.append("              , DECODE(X.UNIT2         , NULL, Y.UNIT2         , X.UNIT2         ) AS UNIT2    \n");
        query.append("              , DECODE(X.UNIT3         , NULL, Y.UNIT3         , X.UNIT3         ) AS UNIT3    \n");
        query.append("              , CASE :CALC_FUNC WHEN '/' THEN DECODE(NVL(Y.VAL, 0), 0 , -999999, ( NVL(X.VAL, 0) / Y.VAL ) )    \n");
        query.append("                                            WHEN '*' THEN ( NVL(X.VAL, 0) * NVL(Y.VAL, 0) )    \n");
        query.append("                                            WHEN '-' THEN ( NVL(X.VAL, 0) - NVL(Y.VAL, 0) )    \n");
        query.append("                                            ELSE  ( NVL(X.VAL, 0) + NVL(Y.VAL, 0) )    \n");
        query.append("                END                                                                                      AS VAL    \n");
        query.append("              , CASE :CALC_FUNC WHEN '/' THEN DECODE(NVL(Y.VAL2, 0), 0 , -999999, ( NVL(X.VAL2, 0) / Y.VAL2 ) )    \n");
        query.append("                                            WHEN '*' THEN ( NVL(X.VAL2, 0) * NVL(Y.VAL2, 0) )    \n");
        query.append("                                            WHEN '-' THEN ( NVL(X.VAL2, 0) - NVL(Y.VAL2, 0) )    \n");
        query.append("                                            ELSE  ( NVL(X.VAL2, 0) + NVL(Y.VAL2, 0) )    \n");
        query.append("                END                                                                                      AS VAL2    \n");
        query.append("              , CASE :CALC_FUNC WHEN '/' THEN DECODE(NVL(Y.VAL3, 0), 0 , -999999, ( NVL(X.VAL3, 0) / Y.VAL3 ) )    \n");
        query.append("                                            WHEN '*' THEN ( NVL(X.VAL3, 0) * NVL(Y.VAL3, 0) )    \n");
        query.append("                                            WHEN '-' THEN ( NVL(X.VAL3, 0) - NVL(Y.VAL3, 0) )    \n");
        query.append("                                            ELSE  ( NVL(X.VAL3, 0) + NVL(Y.VAL3, 0) )    \n");
        query.append("                END                                                                                      AS VAL3    \n");
        query.append("              , :CALC_FUNC AS CALC_FUCN    \n");
        query.append("          FROM (    \n");
        query.append("                  SELECT    \n");
        query.append("                            DATA_TYPE, PID    \n");
        query.append("                          , USE_COMPANY_CD, USE_ORG_CD    \n");
        query.append("                          , REQ_COMPANY_CD, REQ_ORG_CD    \n");
        query.append("                          , HPMS_ID    \n");
        query.append("                          , INVEST_TYPE_CD, APPLICATION, ITEM_NAME, CUSTOMER_CD    \n");
        query.append("                          , YYYYMM    \n");
        query.append("                          , MAX(UNIT)  AS UNIT  , NVL(SUM(VAL), 0) AS VAL    \n");
        query.append("                          , MAX(UNIT2) AS UNIT2 , NVL(SUM(VAL2), 0) AS VAL2    \n");
        query.append("                          , MAX(UNIT3) AS UNIT3 , NVL(SUM(VAL3), 0) AS VAL3    \n");
        query.append("                  FROM    \n");
        query.append("                        HP2D002T    \n");
        query.append("                  WHERE    \n");
        query.append("                        HPMS_ID = :CALC_COL1    \n");
        query.append("                    AND VAL_TYPE IN (0, 10, 20)    \n");
        query.append("                     AND YYYYMM > F_GET_CONFIRM_YYYYMM()     \n");
        if( !DATA_TYPE.equals("") )
        {
            query.append("       AND DATA_TYPE = :DATA_TYPE       \n");
        }
        query.append("                     GROUP BY    \n");
        query.append("                            DATA_TYPE, PID    \n");
        query.append("                          , USE_COMPANY_CD, USE_ORG_CD    \n");
        query.append("                          , REQ_COMPANY_CD, REQ_ORG_CD    \n");
        query.append("                          , HPMS_ID    \n");
        query.append("                          , INVEST_TYPE_CD, APPLICATION, ITEM_NAME, CUSTOMER_CD    \n");
        query.append("                          , YYYYMM    \n");
        query.append("              ) X    \n");
        query.append("                  FULL OUTER JOIN    \n");
        query.append("              (    \n");
        query.append("                  SELECT    \n");
        query.append("                            DATA_TYPE, PID    \n");
        query.append("                          , USE_COMPANY_CD, USE_ORG_CD    \n");
        query.append("                          , REQ_COMPANY_CD, REQ_ORG_CD    \n");
        query.append("                          , HPMS_ID    \n");
        query.append("                          , INVEST_TYPE_CD, APPLICATION, ITEM_NAME, CUSTOMER_CD    \n");
        query.append("                          , YYYYMM    \n");
        query.append("                          , MAX(UNIT)  AS UNIT  , NVL(SUM(VAL), 0) AS VAL    \n");
        query.append("                          , MAX(UNIT2) AS UNIT2 , NVL(SUM(VAL2), 0) AS VAL2    \n");
        query.append("                          , MAX(UNIT3) AS UNIT3 , NVL(SUM(VAL3), 0) AS VAL3    \n");
        query.append("                  FROM    \n");
        query.append("                          HP2D002T    \n");
        query.append("                  WHERE    \n");
        query.append("                          HPMS_ID = :CALC_COL2    \n");
        query.append("                      AND VAL_TYPE IN (0, 10, 20)    \n");
        query.append("                      AND YYYYMM > F_GET_CONFIRM_YYYYMM()    \n");
        if( !DATA_TYPE.equals("") )
        {
            query.append("       AND DATA_TYPE = :DATA_TYPE       \n");
        }
        query.append("                    GROUP BY    \n");
        query.append("                            DATA_TYPE, PID    \n");
        query.append("                          , USE_COMPANY_CD, USE_ORG_CD    \n");
        query.append("                          , REQ_COMPANY_CD, REQ_ORG_CD    \n");
        query.append("                          , HPMS_ID    \n");
        query.append("                          , INVEST_TYPE_CD, APPLICATION, ITEM_NAME, CUSTOMER_CD    \n");
        query.append("                          , YYYYMM    \n");
        query.append("               ) Y    \n");
        query.append("            ON (    \n");
        query.append("                      X.DATA_TYPE       = Y.DATA_TYPE    \n");
        query.append("                  AND X.PID             = Y.PID    \n");
        query.append("                  AND X.USE_COMPANY_CD  = Y.USE_COMPANY_CD    \n");
        query.append("                  AND X.USE_ORG_CD      = Y.USE_ORG_CD    \n");
        query.append("                  AND X.REQ_COMPANY_CD  = Y.REQ_COMPANY_CD    \n");
        query.append("                  AND X.REQ_ORG_CD      = Y.REQ_ORG_CD    \n");
        query.append("                  AND X.INVEST_TYPE_CD  = Y.INVEST_TYPE_CD    \n");
        query.append("                  AND X.APPLICATION     = Y.APPLICATION    \n");
        query.append("                  AND X.ITEM_NAME       = Y.ITEM_NAME    \n");
        query.append("                  AND X.CUSTOMER_CD     = Y.CUSTOMER_CD    \n");
        query.append("                  AND X.YYYYMM          = Y.YYYYMM    \n");
        query.append("              )    \n");
        query.append("      )    \n");
        query.append("WHERE    \n");
        query.append("      (1=1)    \n");
        query.append("  AND ( VAL != -999999 AND VAL2 != -999999 AND VAL3 != -999999 )    \n");
        query.append(" ORDER BY    \n");
        query.append("            DATA_TYPE, PID    \n");
        query.append("          , USE_COMPANY_CD, USE_ORG_CD    \n");
        query.append("          , REQ_COMPANY_CD, REQ_ORG_CD    \n");
        query.append("          , INVEST_TYPE_CD, APPLICATION, ITEM_NAME, CUSTOMER_CD    \n");
        query.append("          , YYYYMM   \n");
        sobj.setSql(query.toString());
        sobj.setString("CALC_FUNC", CALC_FUNC);               
        sobj.setString("CALC_COL2", CALC_COL2);               
        sobj.setString("CALC_COL1", CALC_COL1);               
        sobj.setString("NEW_HPMS_ID", NEW_HPMS_ID);               
        if(!DATA_TYPE.equals(""))
        {
            sobj.setString("DATA_TYPE", DATA_TYPE);               
        }
        return sobj;
    }
    public DOBJ CALLBP_HP2D002T_CAL_INS14(Connection Conn, DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"INS14","Result Item Save");
        QExecutor qexe = new QExecutor(dobj);
        dobj.setRtnname("INS14");
        VOBJ dvobj = dobj.getRetObject("SEL9");           
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        int        updcnt =0;
        dvobj.first();
        while(dvobj.next())
        {
            sobj = SQLBP_HP2D002T_CAL_INS14(dobj, dvobj);
            updcnt += qexe.executeUpdate(Conn, sobj);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT","INT");
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("INS14") ;
        dobj.setRetObject(rvobj);
        return dobj;
    }
    private SQLObject SQLBP_HP2D002T_CAL_INS14(DOBJ dobj, VOBJ dvobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj);
        String UPDATE_TIME ="SYSDATE";        
        String   USE_COMPANY_CD = dvobj.getRecord().get("USE_COMPANY_CD");   
        String   DATA_TYPE = dvobj.getRecord().get("DATA_TYPE");   
        String   SRC_COMPANY_CD = dvobj.getRecord().get("SRC_COMPANY_CD");   
        String   USE_ORG_CD = dvobj.getRecord().get("USE_ORG_CD");   
        String   REQ_COMPANY_CD = dvobj.getRecord().get("REQ_COMPANY_CD");   
        String   REQ_ORG_CD = dvobj.getRecord().get("REQ_ORG_CD");   
        String   ITEM_NAME = dvobj.getRecord().get("ITEM_NAME");   
        String   YYYYMM = dvobj.getRecord().get("YYYYMM");   
        String   SRC_ORG_CD = dvobj.getRecord().get("SRC_ORG_CD");   
        String   DST_COMPANY_CD = dvobj.getRecord().get("DST_COMPANY_CD");   
        double   VAL = dvobj.getRecord().getDouble("VAL");   
        String   INVEST_TYPE_CD = dvobj.getRecord().get("INVEST_TYPE_CD");   
        String   HPMS_ID = dvobj.getRecord().get("HPMS_ID");   
        String   DST_ORG_CD = dvobj.getRecord().get("DST_ORG_CD");   
        String   APPLICATION = dvobj.getRecord().get("APPLICATION");   
        String   VAL_TYPE = dvobj.getRecord().get("VAL_TYPE");   
        String   UNIT3 = dvobj.getRecord().get("UNIT3");   
        String   UNIT2 = dvobj.getRecord().get("UNIT2");   
        String   PID = dvobj.getRecord().get("PID");   
        String   UNIT = dvobj.getRecord().get("UNIT");   
        String   VAL3 = dvobj.getRecord().get("VAL3");   
        String   CUSTOMER_CD = dvobj.getRecord().get("CUSTOMER_CD");   
        String   VAL2 = dvobj.getRecord().get("VAL2");   
        SQLObject sobj = new SQLObject();
        StringBuffer query = new StringBuffer();
        query.append(" Insert into HP_DBA01.HP2D002T (VAL2, CUSTOMER_CD, VAL3, UNIT, PID, UNIT2, UNIT3, VAL_TYPE, APPLICATION, DST_ORG_CD, HPMS_ID, UPDATE_TIME, INVEST_TYPE_CD, VAL, DST_COMPANY_CD, SRC_ORG_CD, YYYYMM, ITEM_NAME, REQ_ORG_CD, REQ_COMPANY_CD, USE_ORG_CD, SRC_COMPANY_CD, DATA_TYPE, USE_COMPANY_CD)  \n");
        query.append(" values(:VAL2 , :CUSTOMER_CD , :VAL3 , :UNIT , :PID , :UNIT2 , :UNIT3 , :VAL_TYPE , :APPLICATION , :DST_ORG_CD , :HPMS_ID , SYSDATE, :INVEST_TYPE_CD , :VAL , :DST_COMPANY_CD , :SRC_ORG_CD , :YYYYMM , :ITEM_NAME , :REQ_ORG_CD , :REQ_COMPANY_CD , :USE_ORG_CD , :SRC_COMPANY_CD , :DATA_TYPE , :USE_COMPANY_CD )");
        sobj.setSql(query.toString());
        sobj.setString("USE_COMPANY_CD", USE_COMPANY_CD);               
        sobj.setString("DATA_TYPE", DATA_TYPE);               
        sobj.setString("SRC_COMPANY_CD", SRC_COMPANY_CD);               
        sobj.setString("USE_ORG_CD", USE_ORG_CD);               
        sobj.setString("REQ_COMPANY_CD", REQ_COMPANY_CD);               
        sobj.setString("REQ_ORG_CD", REQ_ORG_CD);               
        sobj.setString("ITEM_NAME", ITEM_NAME);               
        sobj.setString("YYYYMM", YYYYMM);               
        sobj.setString("SRC_ORG_CD", SRC_ORG_CD);               
        sobj.setString("DST_COMPANY_CD", DST_COMPANY_CD);               
        sobj.setDouble("VAL", VAL);               
        sobj.setString("INVEST_TYPE_CD", INVEST_TYPE_CD);               
        sobj.setString("HPMS_ID", HPMS_ID);               
        sobj.setString("DST_ORG_CD", DST_ORG_CD);               
        sobj.setString("APPLICATION", APPLICATION);               
        sobj.setString("VAL_TYPE", VAL_TYPE);               
        sobj.setString("UNIT3", UNIT3);               
        sobj.setString("UNIT2", UNIT2);               
        sobj.setString("PID", PID);               
        sobj.setString("UNIT", UNIT);               
        sobj.setString("VAL3", VAL3);               
        sobj.setString("CUSTOMER_CD", CUSTOMER_CD);               
        sobj.setString("VAL2", VAL2);               
        return sobj;
    }
    public DOBJ CALLBP_HP2D002T_CAL_INS22(Connection Conn, DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"INS22","Result Item Save");
        QExecutor qexe = new QExecutor(dobj);
        dobj.setRtnname("INS22");
        VOBJ dvobj = dobj.getRetObject("SEL13");           
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        int        updcnt =0;
        dvobj.first();
        while(dvobj.next())
        {
            sobj = SQLBP_HP2D002T_CAL_INS22(dobj, dvobj);
            updcnt += qexe.executeUpdate(Conn, sobj);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT","INT");
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("INS22") ;
        dobj.setRetObject(rvobj);
        return dobj;
    }
    private SQLObject SQLBP_HP2D002T_CAL_INS22(DOBJ dobj, VOBJ dvobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj);
        String UPDATE_TIME ="SYSDATE";        
        String   USE_COMPANY_CD = dvobj.getRecord().get("USE_COMPANY_CD");   
        String   DATA_TYPE = dvobj.getRecord().get("DATA_TYPE");   
        String   SRC_COMPANY_CD = dvobj.getRecord().get("SRC_COMPANY_CD");   
        String   USE_ORG_CD = dvobj.getRecord().get("USE_ORG_CD");   
        String   REQ_COMPANY_CD = dvobj.getRecord().get("REQ_COMPANY_CD");   
        String   REQ_ORG_CD = dvobj.getRecord().get("REQ_ORG_CD");   
        String   ITEM_NAME = dvobj.getRecord().get("ITEM_NAME");   
        String   YYYYMM = dvobj.getRecord().get("YYYYMM");   
        String   SRC_ORG_CD = dvobj.getRecord().get("SRC_ORG_CD");   
        String   DST_COMPANY_CD = dvobj.getRecord().get("DST_COMPANY_CD");   
        double   VAL = dvobj.getRecord().getDouble("VAL");   
        String   INVEST_TYPE_CD = dvobj.getRecord().get("INVEST_TYPE_CD");   
        String   HPMS_ID = dvobj.getRecord().get("HPMS_ID");   
        String   DST_ORG_CD = dvobj.getRecord().get("DST_ORG_CD");   
        String   APPLICATION = dvobj.getRecord().get("APPLICATION");   
        String   VAL_TYPE = dvobj.getRecord().get("VAL_TYPE");   
        String   UNIT3 = dvobj.getRecord().get("UNIT3");   
        String   UNIT2 = dvobj.getRecord().get("UNIT2");   
        String   PID = dvobj.getRecord().get("PID");   
        String   UNIT = dvobj.getRecord().get("UNIT");   
        String   VAL3 = dvobj.getRecord().get("VAL3");   
        String   CUSTOMER_CD = dvobj.getRecord().get("CUSTOMER_CD");   
        String   VAL2 = dvobj.getRecord().get("VAL2");   
        SQLObject sobj = new SQLObject();
        StringBuffer query = new StringBuffer();
        query.append(" Insert into HP_DBA01.HP2D002T (VAL2, CUSTOMER_CD, VAL3, UNIT, PID, UNIT2, UNIT3, VAL_TYPE, APPLICATION, DST_ORG_CD, HPMS_ID, UPDATE_TIME, INVEST_TYPE_CD, VAL, DST_COMPANY_CD, SRC_ORG_CD, YYYYMM, ITEM_NAME, REQ_ORG_CD, REQ_COMPANY_CD, USE_ORG_CD, SRC_COMPANY_CD, DATA_TYPE, USE_COMPANY_CD)  \n");
        query.append(" values(:VAL2 , :CUSTOMER_CD , :VAL3 , :UNIT , :PID , :UNIT2 , :UNIT3 , :VAL_TYPE , :APPLICATION , :DST_ORG_CD , :HPMS_ID , SYSDATE, :INVEST_TYPE_CD , :VAL , :DST_COMPANY_CD , :SRC_ORG_CD , :YYYYMM , :ITEM_NAME , :REQ_ORG_CD , :REQ_COMPANY_CD , :USE_ORG_CD , :SRC_COMPANY_CD , :DATA_TYPE , :USE_COMPANY_CD )");
        sobj.setSql(query.toString());
        sobj.setString("USE_COMPANY_CD", USE_COMPANY_CD);               
        sobj.setString("DATA_TYPE", DATA_TYPE);               
        sobj.setString("SRC_COMPANY_CD", SRC_COMPANY_CD);               
        sobj.setString("USE_ORG_CD", USE_ORG_CD);               
        sobj.setString("REQ_COMPANY_CD", REQ_COMPANY_CD);               
        sobj.setString("REQ_ORG_CD", REQ_ORG_CD);               
        sobj.setString("ITEM_NAME", ITEM_NAME);               
        sobj.setString("YYYYMM", YYYYMM);               
        sobj.setString("SRC_ORG_CD", SRC_ORG_CD);               
        sobj.setString("DST_COMPANY_CD", DST_COMPANY_CD);               
        sobj.setDouble("VAL", VAL);               
        sobj.setString("INVEST_TYPE_CD", INVEST_TYPE_CD);               
        sobj.setString("HPMS_ID", HPMS_ID);               
        sobj.setString("DST_ORG_CD", DST_ORG_CD);               
        sobj.setString("APPLICATION", APPLICATION);               
        sobj.setString("VAL_TYPE", VAL_TYPE);               
        sobj.setString("UNIT3", UNIT3);               
        sobj.setString("UNIT2", UNIT2);               
        sobj.setString("PID", PID);               
        sobj.setString("UNIT", UNIT);               
        sobj.setString("VAL3", VAL3);               
        sobj.setString("CUSTOMER_CD", CUSTOMER_CD);               
        sobj.setString("VAL2", VAL2);               
        return sobj;
    }

    public DOBJ BP_HP2D002T_ALC(DOBJ dobj)
    {
        WizUtil wutil = new WizUtil(dobj);
        Connection Conn = null;
        String message ="";
        try
        {
            Connector connection = new Connector();
            if( dobj.containKey("S") && dobj.getRetObject("S").ContainsColumnName("DBURL") && dobj.getRetObject("S").ContainsColumnName("DBID") && dobj.getRetObject("S").ContainsColumnName("DBPWD") )
            {
                VOBJ dvobj = dobj.getRetObject("S");
                Conn = connection.getConnectionDirect("oracle", dvobj.getRecord().get("DBURL"), dvobj.getRecord().get("DBID"), dvobj.getRecord().get("DBPWD") );
            }
            else
            {
                Conn = connection.getConnectionDirect("oracle","jdbc:oracle:thin:@133.232.125.190:1521:orcl","HP_DBA01","HP_DBA01");
            }
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
        	
        	StopWatch stopWatch = new StopWatch("batch start - 6 ");
        	stopWatch.start("XIUD1");
        	 dobj  = CALLBP_HP2D002T_ALC_XIUD1(Conn, dobj);         
            stopWatch.stop();
        	System.out.println("XIUD1 " + stopWatch.getLastTaskTimeMillis() + " ms");
        	
        	stopWatch.start("SEL2");
        	dobj  = CALLBP_HP2D002T_ALC_SEL2(Conn, dobj);    
            stopWatch.stop();
        	System.out.println("SEL2" + stopWatch.getLastTaskTimeMillis() + " ms");
        	
        	stopWatch.start("MPD4");
        	dobj  = CALLBP_HP2D002T_ALC_MPD4(Conn, dobj); 
            stopWatch.stop();
        	System.out.println("MPD4 " + stopWatch.getLastTaskTimeMillis() + " ms");
        	
        	System.out.println(stopWatch.toString());
        	System.out.println();
        	System.out.println(stopWatch.prettyPrint());
        	
        	
             
                      
                      
            if(dobj.getRtncode() == 9)
            {
                Conn.rollback();
                return dobj;
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
    public DOBJ BP_HP2D002T_ALC( DOBJ dobj, Object Connx) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj);
        Connection Conn = (Connection)Connx;
        String message ="";
        dobj  = CALLBP_HP2D002T_ALC_XIUD1(Conn, dobj);           
        dobj  = CALLBP_HP2D002T_ALC_SEL2(Conn, dobj);           
        dobj  = CALLBP_HP2D002T_ALC_MPD4(Conn, dobj);           
        if(dobj.getRtncode() == 9)
        {
            Conn.rollback();
            return dobj;
        }
        return dobj;
    }
    public DOBJ CALLBP_HP2D002T_ALC_XIUD1(Connection Conn, DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"XIUD1","ALC Result delete");
        QExecutor qexe = new QExecutor(dobj);
        dobj.setRtnname("XIUD1");
        VOBJ dvobj = dobj.getRetObject("S");            
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        int        updcnt =0;
        dvobj.first();
        while(dvobj.next())
        {
            sobj = SQLBP_HP2D002T_ALC_XIUD1(dobj, dvobj);
            updcnt += qexe.executeUpdate(Conn, sobj);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT","INT");
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("XIUD1");
        dobj.setRetObject(rvobj);
        return dobj;
    }
    private SQLObject SQLBP_HP2D002T_ALC_XIUD1(DOBJ dobj, VOBJ dvobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj);
        String   DATA_TYPE = dvobj.getRecord().get("DATA_TYPE");   
        SQLObject sobj = new SQLObject();
        StringBuffer query = new StringBuffer();
        query.append(" DELETE FROM HP2D002T  \n");
        query.append(" WHERE VAL_TYPE IN ( 31, 32, 33, 34 )  \n");
        query.append(" AND DATA_TYPE LIKE DECODE(:DATA_TYPE, '' , '%' ,:DATA_TYPE)|| '%'");
        sobj.setSql(query.toString());
        sobj.setString("DATA_TYPE", DATA_TYPE);               
        return sobj;
    }
    public DOBJ CALLBP_HP2D002T_ALC_SEL2(Connection Conn, DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL2","Dist Target");
        QExecutor qexe = new QExecutor(dobj);
        dobj.setRtnname("SEL2");
        VOBJ dvobj = dobj.getRetObject("S");           
        SQLObject  sobj = SQLBP_HP2D002T_ALC_SEL2(dobj, dvobj);
        qexe.DispSelectSql(sobj);
        dvobj = qexe.executeQuery(Conn, sobj);
        dvobj.setName("SEL2");
        dvobj.Println("SEL2");
        dobj.setRetObject(dvobj);
        return dobj;
    }
    private SQLObject SQLBP_HP2D002T_ALC_SEL2(DOBJ dobj, VOBJ dvobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj);
        String   DATA_TYPE = dobj.getRetObject("S").getRecord().get("DATA_TYPE");   
        SQLObject sobj = new SQLObject();
        StringBuffer query = new StringBuffer();
        query.append("SELECT   \n");
        query.append("          DATA_TYPE, PID   \n");
        query.append("        , USE_COMPANY_CD, USE_ORG_CD, REQ_COMPANY_CD, REQ_ORG_CD   \n");
        query.append("        , SRC_COMPANY_CD, SRC_ORG_CD, DST_COMPANY_CD, DST_ORG_CD   \n");
        query.append("        , HPMS_ID   \n");
        query.append("        , INVEST_TYPE_CD, APPLICATION, ITEM_NAME, CUSTOMER_CD   \n");
        query.append("        , YYYYMM   \n");
        query.append("        , UNIT,   VAL   \n");
        query.append("        , UNIT2, VAL2   \n");
        query.append("        , UNIT3, VAL3   \n");
        query.append("        , VAL_TYPE, CALC_MST_VER   \n");
        query.append("FROM   \n");
        query.append("        HP2D002T   \n");
        query.append("WHERE   \n");
        query.append("        VAL_TYPE IN ( 0, 20, 10 )   \n");
        query.append("  AND   HPMS_ID IN (   \n");
        query.append("                      SELECT DISTINCT SRC_HPMS_ID   \n");
        query.append("                      FROM HP1DM09T   \n");
        query.append("                   )   \n");
        query.append("   \n");
        query.append("  AND DATA_TYPE LIKE DECODE(:DATA_TYPE,'','%',:DATA_TYPE)||'%'   \n");
        query.append("   \n");
        query.append("  AND YYYYMM > F_GET_CONFIRM_YYYYMM()  \n");
        sobj.setSql(query.toString());
        sobj.setString("DATA_TYPE", DATA_TYPE);               
        return sobj;
    }
    public DOBJ CALLBP_HP2D002T_ALC_MPD4(Connection Conn, DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"MPD4","Multi Rec Prcs");
        dobj.setRtnname("MPD4");
        VOBJ dvobj = dobj.getRetObject("SEL2");         
        dvobj.Println("MPD4");
        VOBJ       rvobj= null;
        
    	StopWatch stopWatch = new StopWatch("batch start - 7 ");

        
        dvobj.first();
        while(dvobj.next())
        {
            if(true)
            {
                dobj.resetObject("SEL3,SEL17,SEL4,INS41,SEL14,SEL12,INS13,SEL16,INS17");
                dobj.setRetObject(dvobj.getRecVobj("R"));
                stopWatch.start("SEL3");
                dobj  = CALLBP_HP2D002T_ALC_SEL3(Conn, dobj);
                stopWatch.stop();
            	System.out.println("SEL3 " + stopWatch.getLastTaskTimeMillis() + " ms");
            	
            	
                if( dobj.getRetObject("SEL3").getRecord().getInt("DST_TYPE") == 1)
                {
                	stopWatch.start("SEL14");
                    dobj  = CALLBP_HP2D002T_ALC_SEL14(Conn, dobj);    
                    stopWatch.stop();
                	System.out.println("SEL14 " + stopWatch.getLastTaskTimeMillis() + " ms");
                	
                    if(!dobj.getRetObject("SEL14").getRecord().get("DST_HPMS_ID").equals("") && dobj.getRetObject("SEL14").getRecord().get("CALC_TYPE").equals("32"))
                    {
                    	stopWatch.start("SEL16");
                        dobj  = CALLBP_HP2D002T_ALC_SEL16(Conn, dobj); 
                        stopWatch.stop();
                    	System.out.println("SEL16 " + stopWatch.getLastTaskTimeMillis() + " ms");
                    	
                        stopWatch.start("INS17");
                        dobj  = CALLBP_HP2D002T_ALC_INS17(Conn, dobj);   
                        stopWatch.stop();
                    	System.out.println("INS17 " + stopWatch.getLastTaskTimeMillis() + " ms");
                    	
                    }
                    else if(!dobj.getRetObject("SEL14").getRecord().get("DST_HPMS_ID").equals("") && dobj.getRetObject("SEL14").getRecord().get("CALC_TYPE").equals("31"))
                    {
                    	stopWatch.start("SEL12");
                        dobj  = CALLBP_HP2D002T_ALC_SEL12(Conn, dobj); 
                        stopWatch.stop();
                    	System.out.println("SEL12 " + stopWatch.getLastTaskTimeMillis() + " ms");
                    	
                        stopWatch.start("INS13");
                        dobj  = CALLBP_HP2D002T_ALC_INS13(Conn, dobj);  
                        stopWatch.stop();
                    	System.out.println("INS13 " + stopWatch.getLastTaskTimeMillis() + " ms");
                    	
                    }
                }
                else if( dobj.getRetObject("SEL3").getRecord().getInt("DST_TYPE") == 2)
                {
                	stopWatch.start("SEL17");
                    dobj  = CALLBP_HP2D002T_ALC_SEL17(Conn, dobj);
                    stopWatch.stop();
                	System.out.println("SEL17 " + stopWatch.getLastTaskTimeMillis() + " ms");
                    
                    if( dobj.getRetObject("SEL17").getRecord().getInt("CNT") == 0)
                    {
                    	stopWatch.start("ALC_SEL4");
                        dobj  = CALLBP_HP2D002T_ALC_SEL4(Conn, dobj);  
                        stopWatch.stop();
                    	System.out.println("ALC_SEL4 " + stopWatch.getLastTaskTimeMillis() + " ms");
                    	
                        stopWatch.start("INS41");
                        dobj  = CALLBP_HP2D002T_ALC_INS41(Conn, dobj);  
                        stopWatch.stop();
                    	System.out.println("INS41 " + stopWatch.getLastTaskTimeMillis() + " ms");
                    }
                }
            }
        }
        
        System.out.println(stopWatch.toString());
    	System.out.println();
    	System.out.println(stopWatch.prettyPrint());
        return dobj;
    }
    public DOBJ CALLBP_HP2D002T_ALC_SEL3(Connection Conn, DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL3","SEL FCST Master");
        QExecutor qexe = new QExecutor(dobj);
        dobj.setRtnname("SEL3");
        VOBJ dvobj = dobj.getRetObject("S");           
        SQLObject  sobj = SQLBP_HP2D002T_ALC_SEL3(dobj, dvobj);
        qexe.DispSelectSql(sobj);
        dvobj = qexe.executeQuery(Conn, sobj);
        dvobj.setName("SEL3");
        dvobj.Println("SEL3");
        dobj.setRetObject(dvobj);
        return dobj;
    }
    private SQLObject SQLBP_HP2D002T_ALC_SEL3(DOBJ dobj, VOBJ dvobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj);
        String   DATA_TYPE = dobj.getRetObject("R").getRecord().get("DATA_TYPE");   
        SQLObject sobj = new SQLObject();
        StringBuffer query = new StringBuffer();
        query.append("SELECT   \n");
        query.append("        DATA_TYPE   \n");
        query.append("      , BATCH_CLAC_FLAG   \n");
        query.append("      , BATCH_DST_FLAG   \n");
        query.append("      , DST_TYPE   \n");
        query.append("      , BATCH_LIST_FLAG   \n");
        query.append("FROM   \n");
        query.append("      HP1DM06T   \n");
        query.append("WHERE   \n");
        query.append("      DATA_TYPE = :DATA_TYPE  \n");
        sobj.setSql(query.toString());
        sobj.setString("DATA_TYPE", DATA_TYPE);               
        return sobj;
    }
    public DOBJ CALLBP_HP2D002T_ALC_SEL17(Connection Conn, DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL17","MP Data Check");
        QExecutor qexe = new QExecutor(dobj);
        dobj.setRtnname("SEL17");
        VOBJ dvobj = dobj.getRetObject("S");           
        SQLObject  sobj = SQLBP_HP2D002T_ALC_SEL17(dobj, dvobj);
        qexe.DispSelectSql(sobj);
        dvobj = qexe.executeQuery(Conn, sobj);
        dvobj.setName("SEL17");
        dvobj.Println("SEL17");
        dobj.setRetObject(dvobj);
        return dobj;
    }
    private SQLObject SQLBP_HP2D002T_ALC_SEL17(DOBJ dobj, VOBJ dvobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj);
        String   DATA_TYPE = dobj.getRetObject("R").getRecord().get("DATA_TYPE");   
        SQLObject sobj = new SQLObject();
        StringBuffer query = new StringBuffer();
        query.append("SELECT COUNT(1) AS CNT   \n");
        query.append(" FROM HP2D002T   \n");
        query.append("WHERE VAL_TYPE IN ( 33, 34 )       \n");
        query.append("  AND DATA_TYPE = :DATA_TYPE  \n");
        sobj.setSql(query.toString());
        sobj.setString("DATA_TYPE", DATA_TYPE);               
        return sobj;
    }
    public DOBJ CALLBP_HP2D002T_ALC_SEL4(Connection Conn, DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL4","SEL MP Data");
        QExecutor qexe = new QExecutor(dobj);
        dobj.setRtnname("SEL4");
        VOBJ dvobj = dobj.getRetObject("S");           
        SQLObject  sobj = SQLBP_HP2D002T_ALC_SEL4(dobj, dvobj);
        qexe.DispSelectSql(sobj);
        dvobj = qexe.executeQuery(Conn, sobj);
        dvobj.setName("SEL4");
        dvobj.Println("SEL4");
        dobj.setRetObject(dvobj);
        return dobj;
    }
    private SQLObject SQLBP_HP2D002T_ALC_SEL4(DOBJ dobj, VOBJ dvobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj);
        String   DATA_TYPE = dobj.getRetObject("R").getRecord().get("DATA_TYPE");   
        SQLObject sobj = new SQLObject();
        StringBuffer query = new StringBuffer();
        query.append("SELECT   \n");
        query.append("          :DATA_TYPE AS DATA_TYPE   \n");
        query.append("        , DATA_TYPE AS OLD_DATA_TYPE   \n");
        query.append("        , PID   \n");
        query.append("        , USE_COMPANY_CD, USE_ORG_CD, REQ_COMPANY_CD, REQ_ORG_CD   \n");
        query.append("        , SRC_COMPANY_CD, SRC_ORG_CD, DST_COMPANY_CD, DST_ORG_CD   \n");
        query.append("        , HPMS_ID   \n");
        query.append("        , INVEST_TYPE_CD, APPLICATION, ITEM_NAME, CUSTOMER_CD   \n");
        query.append("        , YYYYMM   \n");
        query.append("        , UNIT,   VAL   \n");
        query.append("        , UNIT2, VAL2   \n");
        query.append("        , UNIT3, VAL3   \n");
        query.append("        , DECODE(VAL_TYPE, 31, 33, 32, 34, VAL_TYPE) AS VAL_TYPE   \n");
        query.append("        , VAL_TYPE                   AS OLD_VAL_TYPE   \n");
        query.append("        , CALC_MST_VER   \n");
        query.append("FROM   \n");
        query.append("        HP2D002T_TZ   \n");
        query.append("WHERE  (1=1)   \n");
        query.append("  AND   VAL_TYPE IN (31, 32)   \n");
        query.append("  AND   DATA_TYPE = 'MP_'||F_GET_FISCAL_YEAR(SYSDATE)  \n");
        sobj.setSql(query.toString());
        sobj.setString("DATA_TYPE", DATA_TYPE);               
        return sobj;
    }
    public DOBJ CALLBP_HP2D002T_ALC_INS41(Connection Conn, DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"INS41","MP Data Save");
        QExecutor qexe = new QExecutor(dobj);
        dobj.setRtnname("INS41");
        VOBJ dvobj = dobj.getRetObject("SEL4");           
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        int        updcnt =0;
        dvobj.first();
        while(dvobj.next())
        {
            sobj = SQLBP_HP2D002T_ALC_INS41(dobj, dvobj);
            updcnt += qexe.executeUpdate(Conn, sobj);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT","INT");
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("INS41") ;
        dobj.setRetObject(rvobj);
        return dobj;
    }
    private SQLObject SQLBP_HP2D002T_ALC_INS41(DOBJ dobj, VOBJ dvobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj);
        String UPDATE_TIME ="SYSDATE";        
        String   USE_ORG_CD = dvobj.getRecord().get("USE_ORG_CD");   
        String   REQ_COMPANY_CD = dvobj.getRecord().get("REQ_COMPANY_CD");   
        String   ITEM_NAME = dvobj.getRecord().get("ITEM_NAME");   
        String   REQ_ORG_CD = dvobj.getRecord().get("REQ_ORG_CD");   
        String   SRC_ORG_CD = dvobj.getRecord().get("SRC_ORG_CD");   
        double   VAL = dvobj.getRecord().getDouble("VAL");   
        String   INVEST_TYPE_CD = dvobj.getRecord().get("INVEST_TYPE_CD");   
        String   HPMS_ID = dvobj.getRecord().get("HPMS_ID");   
        String   UNIT = dvobj.getRecord().get("UNIT");   
        String   CUSTOMER_CD = dvobj.getRecord().get("CUSTOMER_CD");   
        String   USE_COMPANY_CD = dvobj.getRecord().get("USE_COMPANY_CD");   
        String   DATA_TYPE = dvobj.getRecord().get("DATA_TYPE");   
        String   SRC_COMPANY_CD = dvobj.getRecord().get("SRC_COMPANY_CD");   
        String   CALC_MST_VER = dvobj.getRecord().get("CALC_MST_VER");   
        String   YYYYMM = dvobj.getRecord().get("YYYYMM");   
        String   DST_COMPANY_CD = dvobj.getRecord().get("DST_COMPANY_CD");   
        String   DST_ORG_CD = dvobj.getRecord().get("DST_ORG_CD");   
        String   VAL_TYPE = dvobj.getRecord().get("VAL_TYPE");   
        String   APPLICATION = dvobj.getRecord().get("APPLICATION");   
        String   UNIT3 = dvobj.getRecord().get("UNIT3");   
        String   UNIT2 = dvobj.getRecord().get("UNIT2");   
        String   PID = dvobj.getRecord().get("PID");   
        String   VAL3 = dvobj.getRecord().get("VAL3");   
        String   VAL2 = dvobj.getRecord().get("VAL2");   
        String   UPDATE_USER_ID = dobj.getRetObject("G").getRecord().get("USER_ID");   
        SQLObject sobj = new SQLObject();
        StringBuffer query = new StringBuffer();
        query.append(" Insert into HP_DBA01.HP2D002T (VAL2, VAL3, PID, UNIT2, UNIT3, APPLICATION, VAL_TYPE, DST_ORG_CD, DST_COMPANY_CD, YYYYMM, CALC_MST_VER, SRC_COMPANY_CD, DATA_TYPE, USE_COMPANY_CD, CUSTOMER_CD, UNIT, HPMS_ID, UPDATE_TIME, INVEST_TYPE_CD, VAL, SRC_ORG_CD, UPDATE_USER_ID, REQ_ORG_CD, ITEM_NAME, REQ_COMPANY_CD, USE_ORG_CD)  \n");
        query.append(" values(:VAL2 , :VAL3 , :PID , :UNIT2 , :UNIT3 , :APPLICATION , :VAL_TYPE , :DST_ORG_CD , :DST_COMPANY_CD , :YYYYMM , :CALC_MST_VER , :SRC_COMPANY_CD , :DATA_TYPE , :USE_COMPANY_CD , :CUSTOMER_CD , :UNIT , :HPMS_ID , SYSDATE, :INVEST_TYPE_CD , :VAL , :SRC_ORG_CD , :UPDATE_USER_ID , :REQ_ORG_CD , :ITEM_NAME , :REQ_COMPANY_CD , :USE_ORG_CD )");
        sobj.setSql(query.toString());
        sobj.setString("USE_ORG_CD", USE_ORG_CD);               
        sobj.setString("REQ_COMPANY_CD", REQ_COMPANY_CD);               
        sobj.setString("ITEM_NAME", ITEM_NAME);               
        sobj.setString("REQ_ORG_CD", REQ_ORG_CD);               
        sobj.setString("SRC_ORG_CD", SRC_ORG_CD);               
        sobj.setDouble("VAL", VAL);               
        sobj.setString("INVEST_TYPE_CD", INVEST_TYPE_CD);               
        sobj.setString("HPMS_ID", HPMS_ID);               
        sobj.setString("UNIT", UNIT);               
        sobj.setString("CUSTOMER_CD", CUSTOMER_CD);               
        sobj.setString("USE_COMPANY_CD", USE_COMPANY_CD);               
        sobj.setString("DATA_TYPE", DATA_TYPE);               
        sobj.setString("SRC_COMPANY_CD", SRC_COMPANY_CD);               
        sobj.setString("CALC_MST_VER", CALC_MST_VER);               
        sobj.setString("YYYYMM", YYYYMM);               
        sobj.setString("DST_COMPANY_CD", DST_COMPANY_CD);               
        sobj.setString("DST_ORG_CD", DST_ORG_CD);               
        sobj.setString("VAL_TYPE", VAL_TYPE);               
        sobj.setString("APPLICATION", APPLICATION);               
        sobj.setString("UNIT3", UNIT3);               
        sobj.setString("UNIT2", UNIT2);               
        sobj.setString("PID", PID);               
        sobj.setString("VAL3", VAL3);               
        sobj.setString("VAL2", VAL2);               
        sobj.setString("UPDATE_USER_ID", UPDATE_USER_ID);               
        return sobj;
    }
    public DOBJ CALLBP_HP2D002T_ALC_SEL14(Connection Conn, DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL14","SEL HP1DM09T");
        QExecutor qexe = new QExecutor(dobj);
        dobj.setRtnname("SEL14");
        VOBJ dvobj = dobj.getRetObject("S");           
        SQLObject  sobj = SQLBP_HP2D002T_ALC_SEL14(dobj, dvobj);
        qexe.DispSelectSql(sobj);
        dvobj = qexe.executeQuery(Conn, sobj);
        dvobj.setName("SEL14");
        dvobj.Println("SEL14");
        dobj.setRetObject(dvobj);
        return dobj;
    }
    private SQLObject SQLBP_HP2D002T_ALC_SEL14(DOBJ dobj, VOBJ dvobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj);
        String   HPMS_ID = dobj.getRetObject("R").getRecord().get("HPMS_ID");   
        String   USE_ORG_CD = dobj.getRetObject("R").getRecord().get("USE_ORG_CD");   
        String   USE_COMPANY_CD = dobj.getRetObject("R").getRecord().get("USE_COMPANY_CD");   
        SQLObject sobj = new SQLObject();
        StringBuffer query = new StringBuffer();
        query.append("SELECT   \n");
        query.append("        SRC_COMPANY_CD   \n");
        query.append("      , SRC_ORG_CD   \n");
        query.append("      , SRC_HPMS_ID   \n");
        query.append("      , DST_HPMS_ID   \n");
        query.append("      , CALC_TYPE   \n");
        query.append("FROM   \n");
        query.append("      HP1DM09T   \n");
        query.append("WHERE   \n");
        query.append("      SRC_COMPANY_CD  = :USE_COMPANY_CD   \n");
        query.append("  AND SRC_ORG_CD      = :USE_ORG_CD   \n");
        query.append("  AND SRC_HPMS_ID     = :HPMS_ID  \n");
        sobj.setSql(query.toString());
        sobj.setString("HPMS_ID", HPMS_ID);               
        sobj.setString("USE_ORG_CD", USE_ORG_CD);               
        sobj.setString("USE_COMPANY_CD", USE_COMPANY_CD);               
        return sobj;
    }
    public DOBJ CALLBP_HP2D002T_ALC_SEL12(Connection Conn, DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL12","Dist Proc by PID");
        QExecutor qexe = new QExecutor(dobj);
        dobj.setRtnname("SEL12");
        VOBJ dvobj = dobj.getRetObject("S");           
        SQLObject  sobj = SQLBP_HP2D002T_ALC_SEL12(dobj, dvobj);
        qexe.DispSelectSql(sobj);
        dvobj = qexe.executeQuery(Conn, sobj);
        dvobj.setName("SEL12");
        dvobj.Println("SEL12");
        dobj.setRetObject(dvobj);
        return dobj;
    }
    private SQLObject SQLBP_HP2D002T_ALC_SEL12(DOBJ dobj, VOBJ dvobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj);
        String   VAL2 = dobj.getRetObject("R").getRecord().get("VAL2");   
        String   CUSTOMER_CD = dobj.getRetObject("R").getRecord().get("CUSTOMER_CD");   
        String   VAL3 = dobj.getRetObject("R").getRecord().get("VAL3");   
        String   UNIT = dobj.getRetObject("R").getRecord().get("UNIT");   
        String   PID = dobj.getRetObject("R").getRecord().get("PID");   
        String   UNIT2 = dobj.getRetObject("R").getRecord().get("UNIT2");   
        String   UNIT3 = dobj.getRetObject("R").getRecord().get("UNIT3");   
        String   VAL_TYPE = dobj.getRetObject("R").getRecord().get("VAL_TYPE");   
        String   APPLICATION = dobj.getRetObject("R").getRecord().get("APPLICATION");   
        String   HPMS_ID = dobj.getRetObject("R").getRecord().get("HPMS_ID");   
        String   INVEST_TYPE_CD = dobj.getRetObject("R").getRecord().get("INVEST_TYPE_CD");   
        double   VAL = dobj.getRetObject("R").getRecord().getDouble("VAL");   
        String   DST_HPMS_ID = dobj.getRetObject("SEL14").getRecord().get("DST_HPMS_ID");   
        String   YYYYMM = dobj.getRetObject("R").getRecord().get("YYYYMM");   
        String   ITEM_NAME = dobj.getRetObject("R").getRecord().get("ITEM_NAME");   
        String   REQ_ORG_CD = dobj.getRetObject("R").getRecord().get("REQ_ORG_CD");   
        String   REQ_COMPANY_CD = dobj.getRetObject("R").getRecord().get("REQ_COMPANY_CD");   
        String   USE_ORG_CD = dobj.getRetObject("R").getRecord().get("USE_ORG_CD");   
        String   CALC_MST_VER = dobj.getRetObject("R").getRecord().get("CALC_MST_VER");   
        String   DATA_TYPE = dobj.getRetObject("R").getRecord().get("DATA_TYPE");   
        String   USE_COMPANY_CD = dobj.getRetObject("R").getRecord().get("USE_COMPANY_CD");   
        SQLObject sobj = new SQLObject();
        StringBuffer query = new StringBuffer();
        query.append("SELECT   \n");
        query.append("          Z.RNO   \n");
        query.append("        , Z.DATA_TYPE, Z.PID   \n");
        query.append("        , Z.DST_COMPANY_CD            AS USE_COMPANY_CD   \n");
        query.append("        , Z.DST_ORG_CD                AS USE_ORG_CD   \n");
        query.append("        , NVL(Z.REQ_COMPANY_CD , '#') AS REQ_COMPANY_CD   \n");
        query.append("        , NVL(Z.REQ_ORG_CD , '#')     AS REQ_ORG_CD   \n");
        query.append("        , Z.USE_COMPANY_CD            AS SRC_COMPANY_CD   \n");
        query.append("        , Z.USE_ORG_CD                AS SRC_ORG_CD   \n");
        query.append("        , Z.DST_COMPANY_CD            AS DST_COMPANY_CD   \n");
        query.append("        , Z.DST_ORG_CD                AS DST_ORG_CD   \n");
        query.append("        , Z.HPMS_ID                   AS OLD_HPMS_ID   \n");
        query.append("        , :DST_HPMS_ID                AS  HPMS_ID   \n");
        query.append("        , Z.INVEST_TYPE_CD, Z.APPLICATION, Z.ITEM_NAME, Z.CUSTOMER_CD   \n");
        query.append("        , Z.YYYYMM                  AS YYYYMM   \n");
        query.append("        , Z.UNIT   \n");
        query.append("        , Z.VAL                     AS OLD_VAL   \n");
        query.append("        , (Z.VAL * Z.CHARGING_RATIO) AS VAL   \n");
        query.append("        , Z.UNIT2   \n");
        query.append("        , Z.VAL2                    AS OLD_VAL2   \n");
        query.append("        , (Z.VAL2 * Z.CHARGING_RATIO) AS VAL2   \n");
        query.append("        , Z.UNIT3   \n");
        query.append("        , Z.VAL3                    AS OLD_VAL3   \n");
        query.append("        , (Z.VAL3 * Z.CHARGING_RATIO) AS VAL3   \n");
        query.append("        , 31                          AS VAL_TYPE   \n");
        query.append("        , Z.CALC_MST_VER   \n");
        query.append("        , 'DST+' AS GBN_CD   \n");
        query.append(" FROM (   \n");
        query.append("            SELECT    Y.RNO              AS RNO   \n");
        query.append("                    , :DATA_TYPE         AS DATA_TYPE,      :PID               AS PID   \n");
        query.append("                    , :USE_COMPANY_CD    AS USE_COMPANY_CD, :USE_ORG_CD        AS USE_ORG_CD   \n");
        query.append("                    , :REQ_COMPANY_CD    AS REQ_COMPANY_CD, :REQ_ORG_CD        AS REQ_ORG_CD   \n");
        query.append("                    , :HPMS_ID           AS HPMS_ID   \n");
        query.append("                    , :INVEST_TYPE_CD    AS INVEST_TYPE_CD, :APPLICATION       AS APPLICATION   \n");
        query.append("                    , :ITEM_NAME         AS ITEM_NAME,      :CUSTOMER_CD       AS CUSTOMER_CD   \n");
        query.append("                    , :YYYYMM            AS YYYYMM   \n");
        query.append("                    , :UNIT              AS UNIT,           :VAL               AS VAL   \n");
        query.append("                    , :UNIT2             AS UNIT2,          :VAL2              AS VAL2   \n");
        query.append("                    , :UNIT3             AS UNIT3,          :VAL3              AS VAL3   \n");
        query.append("                    , :VAL_TYPE          AS VAL_TYPE   \n");
        query.append("                    , :CALC_MST_VER      AS CALC_MST_VER   \n");
        query.append("                    , Y.DST_COMPANY_CD   AS DST_COMPANY_CD   \n");
        query.append("                    , Y.DST_ORG_CD          AS DST_ORG_CD   \n");
        query.append("                    , Y.CHARGING_RATIO   AS CHARGING_RATIO   \n");
        query.append("              FROM   \n");
        query.append("                        DUAL   \n");
        query.append("                 , (   \n");
        query.append("                        SELECT    ROWNUM AS RNO   \n");
        query.append("                                      , PID, YYYYMM   \n");
        query.append("                                      , DST_COMPANY_CD   \n");
        query.append("                                      , DST_ORG_CD   \n");
        query.append("                                      , CHARGING_RATIO   \n");
        query.append("                                FROM   \n");
        query.append("                                      HP1DM31T   \n");
        query.append("                                WHERE   \n");
        query.append("                                      PID     = :PID   \n");
        query.append("                                  AND YYYYMM  = :YYYYMM   \n");
        query.append("                   ) Y   \n");
        query.append("             WHERE PID = Y.PID   \n");
        query.append("             ORDER BY RNO   \n");
        query.append("       ) Z   \n");
        query.append("   \n");
        query.append("   \n");
        query.append("   \n");
        query.append("UNION ALL   \n");
        query.append("   \n");
        query.append("   \n");
        query.append("SELECT   \n");
        query.append("          Z.RNO   \n");
        query.append("        , Z.DATA_TYPE, Z.PID   \n");
        query.append("        , Z.USE_COMPANY_CD            AS USE_COMPANY_CD   \n");
        query.append("        , Z.USE_ORG_CD                AS USE_ORG_CD   \n");
        query.append("        , NVL(Z.REQ_COMPANY_CD , '#') AS REQ_COMPANY_CD   \n");
        query.append("        , NVL(Z.REQ_ORG_CD , '#')     AS REQ_ORG_CD   \n");
        query.append("        , Z.USE_COMPANY_CD            AS SRC_COMPANY_CD   \n");
        query.append("        , Z.USE_ORG_CD                AS SRC_ORG_CD   \n");
        query.append("        , Z.DST_COMPANY_CD            AS DST_COMPANY_CD   \n");
        query.append("        , Z.DST_ORG_CD                AS DST_ORG_CD   \n");
        query.append("        , Z.HPMS_ID                   AS OLD_HPMS_ID   \n");
        query.append("        , :DST_HPMS_ID                AS  HPMS_ID   \n");
        query.append("        , Z.INVEST_TYPE_CD, Z.APPLICATION, Z.ITEM_NAME, Z.CUSTOMER_CD   \n");
        query.append("        , Z.YYYYMM                  AS YYYYMM   \n");
        query.append("        , Z.UNIT   \n");
        query.append("        , Z.VAL                     AS OLD_VAL   \n");
        query.append("        , - (Z.VAL * Z.CHARGING_RATIO) AS VAL   \n");
        query.append("        , Z.UNIT2   \n");
        query.append("        , Z.VAL2                    AS OLD_VAL2   \n");
        query.append("        , - (Z.VAL2 * Z.CHARGING_RATIO) AS VAL2   \n");
        query.append("        , Z.UNIT3   \n");
        query.append("        , Z.VAL3                    AS OLD_VAL3   \n");
        query.append("        , - (Z.VAL3 * Z.CHARGING_RATIO) AS VAL3   \n");
        query.append("        , 31                                                                       AS VAL_TYPE   \n");
        query.append("        , Z.CALC_MST_VER   \n");
        query.append("        , 'SRC-' AS GBN_CD   \n");
        query.append(" FROM (   \n");
        query.append("            SELECT    Y.RNO              AS RNO   \n");
        query.append("                    , :DATA_TYPE         AS DATA_TYPE,      :PID               AS PID   \n");
        query.append("                    , :USE_COMPANY_CD    AS USE_COMPANY_CD, :USE_ORG_CD        AS USE_ORG_CD   \n");
        query.append("                    , :REQ_COMPANY_CD    AS REQ_COMPANY_CD, :REQ_ORG_CD        AS REQ_ORG_CD   \n");
        query.append("                    , :HPMS_ID           AS HPMS_ID   \n");
        query.append("                    , :INVEST_TYPE_CD    AS INVEST_TYPE_CD, :APPLICATION       AS APPLICATION   \n");
        query.append("                    , :ITEM_NAME         AS ITEM_NAME,      :CUSTOMER_CD       AS CUSTOMER_CD   \n");
        query.append("                    , :YYYYMM            AS YYYYMM   \n");
        query.append("                    , :UNIT              AS UNIT,           :VAL               AS VAL   \n");
        query.append("                    , :UNIT2             AS UNIT2,          :VAL2              AS VAL2   \n");
        query.append("                    , :UNIT3             AS UNIT3,          :VAL3              AS VAL3   \n");
        query.append("                    , :VAL_TYPE          AS VAL_TYPE   \n");
        query.append("                    , :CALC_MST_VER      AS CALC_MST_VER   \n");
        query.append("                    , Y.DST_COMPANY_CD   AS DST_COMPANY_CD   \n");
        query.append("                    , Y.DST_ORG_CD          AS DST_ORG_CD   \n");
        query.append("                    , Y.CHARGING_RATIO   AS CHARGING_RATIO   \n");
        query.append("              FROM   \n");
        query.append("                        DUAL   \n");
        query.append("                 , (   \n");
        query.append("                        SELECT    ROWNUM AS RNO   \n");
        query.append("                                      , PID, YYYYMM   \n");
        query.append("                                      , DST_COMPANY_CD   \n");
        query.append("                                      , DST_ORG_CD   \n");
        query.append("                                      , CHARGING_RATIO   \n");
        query.append("                                FROM   \n");
        query.append("                                      HP1DM31T   \n");
        query.append("                                WHERE   \n");
        query.append("                                      PID     = :PID   \n");
        query.append("                                  AND YYYYMM  = :YYYYMM   \n");
        query.append("                   ) Y   \n");
        query.append("             WHERE PID = Y.PID   \n");
        query.append("             ORDER BY RNO   \n");
        query.append("       ) Z  \n");
        sobj.setSql(query.toString());
        sobj.setString("VAL2", VAL2);               
        sobj.setString("CUSTOMER_CD", CUSTOMER_CD);               
        sobj.setString("VAL3", VAL3);               
        sobj.setString("UNIT", UNIT);               
        sobj.setString("PID", PID);               
        sobj.setString("UNIT2", UNIT2);               
        sobj.setString("UNIT3", UNIT3);               
        sobj.setString("VAL_TYPE", VAL_TYPE);               
        sobj.setString("APPLICATION", APPLICATION);               
        sobj.setString("HPMS_ID", HPMS_ID);               
        sobj.setString("INVEST_TYPE_CD", INVEST_TYPE_CD);               
        sobj.setDouble("VAL", VAL);               
        sobj.setString("DST_HPMS_ID", DST_HPMS_ID);               
        sobj.setString("YYYYMM", YYYYMM);               
        sobj.setString("ITEM_NAME", ITEM_NAME);               
        sobj.setString("REQ_ORG_CD", REQ_ORG_CD);               
        sobj.setString("REQ_COMPANY_CD", REQ_COMPANY_CD);               
        sobj.setString("USE_ORG_CD", USE_ORG_CD);               
        sobj.setString("CALC_MST_VER", CALC_MST_VER);               
        sobj.setString("DATA_TYPE", DATA_TYPE);               
        sobj.setString("USE_COMPANY_CD", USE_COMPANY_CD);               
        return sobj;
    }
    public DOBJ CALLBP_HP2D002T_ALC_INS13(Connection Conn, DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"INS13","Dist Data Save");
        QExecutor qexe = new QExecutor(dobj);
        dobj.setRtnname("INS13");
        VOBJ dvobj = dobj.getRetObject("SEL12");           
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        int        updcnt =0;
        dvobj.first();
        while(dvobj.next())
        {
            sobj = SQLBP_HP2D002T_ALC_INS13(dobj, dvobj);
            updcnt += qexe.executeUpdate(Conn, sobj);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT","INT");
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("INS13") ;
        dobj.setRetObject(rvobj);
        return dobj;
    }
    private SQLObject SQLBP_HP2D002T_ALC_INS13(DOBJ dobj, VOBJ dvobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj);
        String UPDATE_TIME ="SYSDATE";        
        String   USE_ORG_CD = dvobj.getRecord().get("USE_ORG_CD");   
        String   REQ_COMPANY_CD = dvobj.getRecord().get("REQ_COMPANY_CD");   
        String   ITEM_NAME = dvobj.getRecord().get("ITEM_NAME");   
        String   REQ_ORG_CD = dvobj.getRecord().get("REQ_ORG_CD");   
        String   SRC_ORG_CD = dvobj.getRecord().get("SRC_ORG_CD");   
        double   VAL = dvobj.getRecord().getDouble("VAL");   
        String   INVEST_TYPE_CD = dvobj.getRecord().get("INVEST_TYPE_CD");   
        String   HPMS_ID = dvobj.getRecord().get("HPMS_ID");   
        String   UNIT = dvobj.getRecord().get("UNIT");   
        String   CUSTOMER_CD = dvobj.getRecord().get("CUSTOMER_CD");   
        String   USE_COMPANY_CD = dvobj.getRecord().get("USE_COMPANY_CD");   
        String   DATA_TYPE = dvobj.getRecord().get("DATA_TYPE");   
        String   SRC_COMPANY_CD = dvobj.getRecord().get("SRC_COMPANY_CD");   
        String   CALC_MST_VER = dvobj.getRecord().get("CALC_MST_VER");   
        String   YYYYMM = dvobj.getRecord().get("YYYYMM");   
        String   DST_COMPANY_CD = dvobj.getRecord().get("DST_COMPANY_CD");   
        String   DST_ORG_CD = dvobj.getRecord().get("DST_ORG_CD");   
        String   VAL_TYPE = dvobj.getRecord().get("VAL_TYPE");   
        String   APPLICATION = dvobj.getRecord().get("APPLICATION");   
        String   UNIT3 = dvobj.getRecord().get("UNIT3");   
        String   UNIT2 = dvobj.getRecord().get("UNIT2");   
        String   PID = dvobj.getRecord().get("PID");   
        String   VAL3 = dvobj.getRecord().get("VAL3");   
        String   VAL2 = dvobj.getRecord().get("VAL2");   
        SQLObject sobj = new SQLObject();
        StringBuffer query = new StringBuffer();
        query.append(" Insert into HP_DBA01.HP2D002T (VAL2, VAL3, PID, UNIT2, UNIT3, APPLICATION, VAL_TYPE, DST_ORG_CD, DST_COMPANY_CD, YYYYMM, CALC_MST_VER, SRC_COMPANY_CD, DATA_TYPE, USE_COMPANY_CD, CUSTOMER_CD, UNIT, HPMS_ID, UPDATE_TIME, INVEST_TYPE_CD, VAL, SRC_ORG_CD, REQ_ORG_CD, ITEM_NAME, REQ_COMPANY_CD, USE_ORG_CD)  \n");
        query.append(" values(:VAL2 , :VAL3 , :PID , :UNIT2 , :UNIT3 , :APPLICATION , :VAL_TYPE , :DST_ORG_CD , :DST_COMPANY_CD , :YYYYMM , :CALC_MST_VER , :SRC_COMPANY_CD , :DATA_TYPE , :USE_COMPANY_CD , :CUSTOMER_CD , :UNIT , :HPMS_ID , SYSDATE, :INVEST_TYPE_CD , :VAL , :SRC_ORG_CD , :REQ_ORG_CD , :ITEM_NAME , :REQ_COMPANY_CD , :USE_ORG_CD )");
        sobj.setSql(query.toString());
        sobj.setString("USE_ORG_CD", USE_ORG_CD);               
        sobj.setString("REQ_COMPANY_CD", REQ_COMPANY_CD);               
        sobj.setString("ITEM_NAME", ITEM_NAME);               
        sobj.setString("REQ_ORG_CD", REQ_ORG_CD);               
        sobj.setString("SRC_ORG_CD", SRC_ORG_CD);               
        sobj.setDouble("VAL", VAL);               
        sobj.setString("INVEST_TYPE_CD", INVEST_TYPE_CD);               
        sobj.setString("HPMS_ID", HPMS_ID);               
        sobj.setString("UNIT", UNIT);               
        sobj.setString("CUSTOMER_CD", CUSTOMER_CD);               
        sobj.setString("USE_COMPANY_CD", USE_COMPANY_CD);               
        sobj.setString("DATA_TYPE", DATA_TYPE);               
        sobj.setString("SRC_COMPANY_CD", SRC_COMPANY_CD);               
        sobj.setString("CALC_MST_VER", CALC_MST_VER);               
        sobj.setString("YYYYMM", YYYYMM);               
        sobj.setString("DST_COMPANY_CD", DST_COMPANY_CD);               
        sobj.setString("DST_ORG_CD", DST_ORG_CD);               
        sobj.setString("VAL_TYPE", VAL_TYPE);               
        sobj.setString("APPLICATION", APPLICATION);               
        sobj.setString("UNIT3", UNIT3);               
        sobj.setString("UNIT2", UNIT2);               
        sobj.setString("PID", PID);               
        sobj.setString("VAL3", VAL3);               
        sobj.setString("VAL2", VAL2);               
        return sobj;
    }
    public DOBJ CALLBP_HP2D002T_ALC_SEL16(Connection Conn, DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL16","Dist Proc by Dept");
        QExecutor qexe = new QExecutor(dobj);
        dobj.setRtnname("SEL16");
        VOBJ dvobj = dobj.getRetObject("S");           
        SQLObject  sobj = SQLBP_HP2D002T_ALC_SEL16(dobj, dvobj);
        qexe.DispSelectSql(sobj);
        dvobj = qexe.executeQuery(Conn, sobj);
        dvobj.setName("SEL16");
        dvobj.Println("SEL16");
        dobj.setRetObject(dvobj);
        return dobj;
    }
    private SQLObject SQLBP_HP2D002T_ALC_SEL16(DOBJ dobj, VOBJ dvobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj);
        String   VAL2 = dobj.getRetObject("R").getRecord().get("VAL2");   
        String   CUSTOMER_CD = dobj.getRetObject("R").getRecord().get("CUSTOMER_CD");   
        String   VAL3 = dobj.getRetObject("R").getRecord().get("VAL3");   
        String   UNIT = dobj.getRetObject("R").getRecord().get("UNIT");   
        String   PID = dobj.getRetObject("R").getRecord().get("PID");   
        String   UNIT2 = dobj.getRetObject("R").getRecord().get("UNIT2");   
        String   UNIT3 = dobj.getRetObject("R").getRecord().get("UNIT3");   
        String   VAL_TYPE = dobj.getRetObject("R").getRecord().get("VAL_TYPE");   
        String   APPLICATION = dobj.getRetObject("R").getRecord().get("APPLICATION");   
        String   HPMS_ID = dobj.getRetObject("R").getRecord().get("HPMS_ID");   
        String   INVEST_TYPE_CD = dobj.getRetObject("R").getRecord().get("INVEST_TYPE_CD");   
        double   VAL = dobj.getRetObject("R").getRecord().getDouble("VAL");   
        String   DST_HPMS_ID = dobj.getRetObject("SEL14").getRecord().get("DST_HPMS_ID");   
        String   YYYYMM = dobj.getRetObject("R").getRecord().get("YYYYMM");   
        String   ITEM_NAME = dobj.getRetObject("R").getRecord().get("ITEM_NAME");   
        String   REQ_ORG_CD = dobj.getRetObject("R").getRecord().get("REQ_ORG_CD");   
        String   REQ_COMPANY_CD = dobj.getRetObject("R").getRecord().get("REQ_COMPANY_CD");   
        String   USE_ORG_CD = dobj.getRetObject("R").getRecord().get("USE_ORG_CD");   
        String   CALC_MST_VER = dobj.getRetObject("R").getRecord().get("CALC_MST_VER");   
        String   DATA_TYPE = dobj.getRetObject("R").getRecord().get("DATA_TYPE");   
        String   USE_COMPANY_CD = dobj.getRetObject("R").getRecord().get("USE_COMPANY_CD");   
        SQLObject sobj = new SQLObject();
        StringBuffer query = new StringBuffer();
        query.append("SELECT   \n");
        query.append("          Z.RNO   \n");
        query.append("        , Z.DATA_TYPE, Z.PID   \n");
        query.append("        , Z.DST_COMPANY_CD            AS USE_COMPANY_CD   \n");
        query.append("        , Z.DST_ORG_CD                AS USE_ORG_CD   \n");
        query.append("        , NVL(Z.REQ_COMPANY_CD , '#') AS REQ_COMPANY_CD   \n");
        query.append("        , NVL(Z.REQ_ORG_CD , '#')     AS REQ_ORG_CD   \n");
        query.append("        , Z.USE_COMPANY_CD            AS SRC_COMPANY_CD   \n");
        query.append("        , Z.USE_ORG_CD                AS SRC_ORG_CD   \n");
        query.append("        , Z.DST_COMPANY_CD            AS DST_COMPANY_CD   \n");
        query.append("        , Z.DST_ORG_CD                AS DST_ORG_CD   \n");
        query.append("        , Z.HPMS_ID                   AS OLD_HPMS_ID   \n");
        query.append("        , :DST_HPMS_ID                AS  HPMS_ID   \n");
        query.append("        , Z.INVEST_TYPE_CD, Z.APPLICATION, Z.ITEM_NAME, Z.CUSTOMER_CD   \n");
        query.append("        , Z.YYYYMM                  AS YYYYMM   \n");
        query.append("        , Z.UNIT   \n");
        query.append("        , Z.VAL                     AS OLD_VAL   \n");
        query.append("        , (Z.VAL * Z.CHARGING_RATIO) AS VAL   \n");
        query.append("        , Z.UNIT2   \n");
        query.append("        , Z.VAL2                    AS OLD_VAL2   \n");
        query.append("        , (Z.VAL2 * Z.CHARGING_RATIO) AS VAL2   \n");
        query.append("        , Z.UNIT3   \n");
        query.append("        , Z.VAL3                    AS OLD_VAL3   \n");
        query.append("        , (Z.VAL3 * Z.CHARGING_RATIO) AS VAL3   \n");
        query.append("        , 32                          AS VAL_TYPE   \n");
        query.append("        , Z.CALC_MST_VER   \n");
        query.append("        , 'DST+' AS GBN_CD   \n");
        query.append(" FROM (   \n");
        query.append("            SELECT    Y.RNO              AS RNO   \n");
        query.append("                    , :DATA_TYPE         AS DATA_TYPE,      :PID               AS PID   \n");
        query.append("                    , :USE_COMPANY_CD    AS USE_COMPANY_CD, :USE_ORG_CD        AS USE_ORG_CD   \n");
        query.append("                    , :REQ_COMPANY_CD    AS REQ_COMPANY_CD, :REQ_ORG_CD        AS REQ_ORG_CD   \n");
        query.append("                    , :HPMS_ID           AS HPMS_ID   \n");
        query.append("                    , :INVEST_TYPE_CD    AS INVEST_TYPE_CD, :APPLICATION       AS APPLICATION   \n");
        query.append("                    , :ITEM_NAME         AS ITEM_NAME,      :CUSTOMER_CD       AS CUSTOMER_CD   \n");
        query.append("                    , :YYYYMM            AS YYYYMM   \n");
        query.append("                    , :UNIT              AS UNIT,           :VAL               AS VAL   \n");
        query.append("                    , :UNIT2             AS UNIT2,          :VAL2              AS VAL2   \n");
        query.append("                    , :UNIT3             AS UNIT3,          :VAL3              AS VAL3   \n");
        query.append("                    , :VAL_TYPE          AS VAL_TYPE   \n");
        query.append("                    , :CALC_MST_VER      AS CALC_MST_VER   \n");
        query.append("                    , Y.DST_COMPANY_CD   AS DST_COMPANY_CD   \n");
        query.append("                    , Y.DST_ORG_CD          AS DST_ORG_CD   \n");
        query.append("                    , Y.CHARGING_RATIO   AS CHARGING_RATIO   \n");
        query.append("              FROM   \n");
        query.append("                        DUAL   \n");
        query.append("                 , (   \n");
        query.append("                        SELECT ROWNUM AS RNO   \n");
        query.append("                              ,  SRC_COMPANY_CD, SRC_ORG_CD, YYYYMM   \n");
        query.append("                              , DST_COMPANY_CD   \n");
        query.append("                              , DST_ORG_CD   \n");
        query.append("                              , CHARGING_RATIO   \n");
        query.append("                        FROM   \n");
        query.append("                              HP1DM32T   \n");
        query.append("                        WHERE   \n");
        query.append("                              SRC_COMPANY_CD  = :USE_COMPANY_CD   \n");
        query.append("                          AND SRC_ORG_CD      = :USE_ORG_CD   \n");
        query.append("                          AND YYYYMM          = :YYYYMM   \n");
        query.append("                   ) Y   \n");
        query.append("             ORDER BY RNO   \n");
        query.append("       ) Z   \n");
        query.append("   \n");
        query.append("   \n");
        query.append("   \n");
        query.append("UNION ALL   \n");
        query.append("   \n");
        query.append("   \n");
        query.append("   \n");
        query.append("SELECT   \n");
        query.append("          Z.RNO   \n");
        query.append("        , Z.DATA_TYPE, Z.PID   \n");
        query.append("        , Z.USE_COMPANY_CD            AS USE_COMPANY_CD   \n");
        query.append("        , Z.USE_ORG_CD                AS USE_ORG_CD   \n");
        query.append("        , NVL(Z.REQ_COMPANY_CD , '#') AS REQ_COMPANY_CD   \n");
        query.append("        , NVL(Z.REQ_ORG_CD , '#')     AS REQ_ORG_CD   \n");
        query.append("        , Z.USE_COMPANY_CD            AS SRC_COMPANY_CD   \n");
        query.append("        , Z.USE_ORG_CD                AS SRC_ORG_CD   \n");
        query.append("        , Z.DST_COMPANY_CD            AS DST_COMPANY_CD   \n");
        query.append("        , Z.DST_ORG_CD                AS DST_ORG_CD   \n");
        query.append("        , Z.HPMS_ID                   AS OLD_HPMS_ID   \n");
        query.append("        , :DST_HPMS_ID                AS  HPMS_ID   \n");
        query.append("        , Z.INVEST_TYPE_CD, Z.APPLICATION, Z.ITEM_NAME, Z.CUSTOMER_CD   \n");
        query.append("        , Z.YYYYMM                  AS YYYYMM   \n");
        query.append("        , Z.UNIT   \n");
        query.append("        , Z.VAL                     AS OLD_VAL   \n");
        query.append("        , - (Z.VAL * Z.CHARGING_RATIO) AS VAL   \n");
        query.append("        , Z.UNIT2   \n");
        query.append("        , Z.VAL2                    AS OLD_VAL2   \n");
        query.append("        , - (Z.VAL2 * Z.CHARGING_RATIO) AS VAL2   \n");
        query.append("        , Z.UNIT3   \n");
        query.append("        , Z.VAL3                    AS OLD_VAL3   \n");
        query.append("        , - (Z.VAL3 * Z.CHARGING_RATIO) AS VAL3   \n");
        query.append("        , 32                                                                       AS VAL_TYPE   \n");
        query.append("        , Z.CALC_MST_VER   \n");
        query.append("        , 'SRC-' AS GBN_CD   \n");
        query.append(" FROM (   \n");
        query.append("            SELECT    Y.RNO              AS RNO   \n");
        query.append("                    , :DATA_TYPE         AS DATA_TYPE,      :PID               AS PID   \n");
        query.append("                    , :USE_COMPANY_CD    AS USE_COMPANY_CD, :USE_ORG_CD        AS USE_ORG_CD   \n");
        query.append("                    , :REQ_COMPANY_CD    AS REQ_COMPANY_CD, :REQ_ORG_CD        AS REQ_ORG_CD   \n");
        query.append("                    , :HPMS_ID           AS HPMS_ID   \n");
        query.append("                    , :INVEST_TYPE_CD    AS INVEST_TYPE_CD, :APPLICATION       AS APPLICATION   \n");
        query.append("                    , :ITEM_NAME         AS ITEM_NAME,      :CUSTOMER_CD       AS CUSTOMER_CD   \n");
        query.append("                    , :YYYYMM            AS YYYYMM   \n");
        query.append("                    , :UNIT              AS UNIT,           :VAL               AS VAL   \n");
        query.append("                    , :UNIT2             AS UNIT2,          :VAL2              AS VAL2   \n");
        query.append("                    , :UNIT3             AS UNIT3,          :VAL3              AS VAL3   \n");
        query.append("                    , :VAL_TYPE          AS VAL_TYPE   \n");
        query.append("                    , :CALC_MST_VER      AS CALC_MST_VER   \n");
        query.append("                    , Y.DST_COMPANY_CD   AS DST_COMPANY_CD   \n");
        query.append("                    , Y.DST_ORG_CD          AS DST_ORG_CD   \n");
        query.append("                    , Y.CHARGING_RATIO   AS CHARGING_RATIO   \n");
        query.append("              FROM   \n");
        query.append("                        DUAL   \n");
        query.append("                 , (   \n");
        query.append("                        SELECT ROWNUM AS RNO   \n");
        query.append("                              ,  SRC_COMPANY_CD, SRC_ORG_CD, YYYYMM   \n");
        query.append("                              , DST_COMPANY_CD   \n");
        query.append("                              , DST_ORG_CD   \n");
        query.append("                              , CHARGING_RATIO   \n");
        query.append("                        FROM   \n");
        query.append("                              HP1DM32T   \n");
        query.append("                        WHERE   \n");
        query.append("                              SRC_COMPANY_CD  = :USE_COMPANY_CD   \n");
        query.append("                          AND SRC_ORG_CD      = :USE_ORG_CD   \n");
        query.append("                          AND YYYYMM          = :YYYYMM   \n");
        query.append("                     ) Y   \n");
        query.append("             ORDER BY RNO   \n");
        query.append("       ) Z  \n");
        sobj.setSql(query.toString());
        sobj.setString("VAL2", VAL2);               
        sobj.setString("CUSTOMER_CD", CUSTOMER_CD);               
        sobj.setString("VAL3", VAL3);               
        sobj.setString("UNIT", UNIT);               
        sobj.setString("PID", PID);               
        sobj.setString("UNIT2", UNIT2);               
        sobj.setString("UNIT3", UNIT3);               
        sobj.setString("VAL_TYPE", VAL_TYPE);               
        sobj.setString("APPLICATION", APPLICATION);               
        sobj.setString("HPMS_ID", HPMS_ID);               
        sobj.setString("INVEST_TYPE_CD", INVEST_TYPE_CD);               
        sobj.setDouble("VAL", VAL);               
        sobj.setString("DST_HPMS_ID", DST_HPMS_ID);               
        sobj.setString("YYYYMM", YYYYMM);               
        sobj.setString("ITEM_NAME", ITEM_NAME);               
        sobj.setString("REQ_ORG_CD", REQ_ORG_CD);               
        sobj.setString("REQ_COMPANY_CD", REQ_COMPANY_CD);               
        sobj.setString("USE_ORG_CD", USE_ORG_CD);               
        sobj.setString("CALC_MST_VER", CALC_MST_VER);               
        sobj.setString("DATA_TYPE", DATA_TYPE);               
        sobj.setString("USE_COMPANY_CD", USE_COMPANY_CD);               
        return sobj;
    }
    public DOBJ CALLBP_HP2D002T_ALC_INS17(Connection Conn, DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"INS17","Dist Data Save");
        QExecutor qexe = new QExecutor(dobj);
        dobj.setRtnname("INS17");
        VOBJ dvobj = dobj.getRetObject("SEL16");           
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        int        updcnt =0;
        dvobj.first();
        while(dvobj.next())
        {
            sobj = SQLBP_HP2D002T_ALC_INS17(dobj, dvobj);
            updcnt += qexe.executeUpdate(Conn, sobj);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT","INT");
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("INS17") ;
        dobj.setRetObject(rvobj);
        return dobj;
    }
    private SQLObject SQLBP_HP2D002T_ALC_INS17(DOBJ dobj, VOBJ dvobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj);
        String UPDATE_TIME ="SYSDATE";        
        String   USE_ORG_CD = dvobj.getRecord().get("USE_ORG_CD");   
        String   REQ_COMPANY_CD = dvobj.getRecord().get("REQ_COMPANY_CD");   
        String   ITEM_NAME = dvobj.getRecord().get("ITEM_NAME");   
        String   REQ_ORG_CD = dvobj.getRecord().get("REQ_ORG_CD");   
        String   SRC_ORG_CD = dvobj.getRecord().get("SRC_ORG_CD");   
        double   VAL = dvobj.getRecord().getDouble("VAL");   
        String   INVEST_TYPE_CD = dvobj.getRecord().get("INVEST_TYPE_CD");   
        String   HPMS_ID = dvobj.getRecord().get("HPMS_ID");   
        String   UNIT = dvobj.getRecord().get("UNIT");   
        String   CUSTOMER_CD = dvobj.getRecord().get("CUSTOMER_CD");   
        String   USE_COMPANY_CD = dvobj.getRecord().get("USE_COMPANY_CD");   
        String   DATA_TYPE = dvobj.getRecord().get("DATA_TYPE");   
        String   SRC_COMPANY_CD = dvobj.getRecord().get("SRC_COMPANY_CD");   
        String   CALC_MST_VER = dvobj.getRecord().get("CALC_MST_VER");   
        String   YYYYMM = dvobj.getRecord().get("YYYYMM");   
        String   DST_COMPANY_CD = dvobj.getRecord().get("DST_COMPANY_CD");   
        String   DST_ORG_CD = dvobj.getRecord().get("DST_ORG_CD");   
        String   VAL_TYPE = dvobj.getRecord().get("VAL_TYPE");   
        String   APPLICATION = dvobj.getRecord().get("APPLICATION");   
        String   UNIT3 = dvobj.getRecord().get("UNIT3");   
        String   UNIT2 = dvobj.getRecord().get("UNIT2");   
        String   PID = dvobj.getRecord().get("PID");   
        String   VAL3 = dvobj.getRecord().get("VAL3");   
        String   VAL2 = dvobj.getRecord().get("VAL2");   
        SQLObject sobj = new SQLObject();
        StringBuffer query = new StringBuffer();
        query.append(" Insert into HP_DBA01.HP2D002T (VAL2, VAL3, PID, UNIT2, UNIT3, APPLICATION, VAL_TYPE, DST_ORG_CD, DST_COMPANY_CD, YYYYMM, CALC_MST_VER, SRC_COMPANY_CD, DATA_TYPE, USE_COMPANY_CD, CUSTOMER_CD, UNIT, HPMS_ID, UPDATE_TIME, INVEST_TYPE_CD, VAL, SRC_ORG_CD, REQ_ORG_CD, ITEM_NAME, REQ_COMPANY_CD, USE_ORG_CD)  \n");
        query.append(" values(:VAL2 , :VAL3 , :PID , :UNIT2 , :UNIT3 , :APPLICATION , :VAL_TYPE , :DST_ORG_CD , :DST_COMPANY_CD , :YYYYMM , :CALC_MST_VER , :SRC_COMPANY_CD , :DATA_TYPE , :USE_COMPANY_CD , :CUSTOMER_CD , :UNIT , :HPMS_ID , SYSDATE, :INVEST_TYPE_CD , :VAL , :SRC_ORG_CD , :REQ_ORG_CD , :ITEM_NAME , :REQ_COMPANY_CD , :USE_ORG_CD )");
        sobj.setSql(query.toString());
        sobj.setString("USE_ORG_CD", USE_ORG_CD);               
        sobj.setString("REQ_COMPANY_CD", REQ_COMPANY_CD);               
        sobj.setString("ITEM_NAME", ITEM_NAME);               
        sobj.setString("REQ_ORG_CD", REQ_ORG_CD);               
        sobj.setString("SRC_ORG_CD", SRC_ORG_CD);               
        sobj.setDouble("VAL", VAL);               
        sobj.setString("INVEST_TYPE_CD", INVEST_TYPE_CD);               
        sobj.setString("HPMS_ID", HPMS_ID);               
        sobj.setString("UNIT", UNIT);               
        sobj.setString("CUSTOMER_CD", CUSTOMER_CD);               
        sobj.setString("USE_COMPANY_CD", USE_COMPANY_CD);               
        sobj.setString("DATA_TYPE", DATA_TYPE);               
        sobj.setString("SRC_COMPANY_CD", SRC_COMPANY_CD);               
        sobj.setString("CALC_MST_VER", CALC_MST_VER);               
        sobj.setString("YYYYMM", YYYYMM);               
        sobj.setString("DST_COMPANY_CD", DST_COMPANY_CD);               
        sobj.setString("DST_ORG_CD", DST_ORG_CD);               
        sobj.setString("VAL_TYPE", VAL_TYPE);               
        sobj.setString("APPLICATION", APPLICATION);               
        sobj.setString("UNIT3", UNIT3);               
        sobj.setString("UNIT2", UNIT2);               
        sobj.setString("PID", PID);               
        sobj.setString("VAL3", VAL3);               
        sobj.setString("VAL2", VAL2);               
        return sobj;
    }

    public DOBJ BP_HP2D101T(DOBJ dobj)
    {
        WizUtil wutil = new WizUtil(dobj);
        Connection Conn = null;
        String message ="";
        try
        {
            Connector connection = new Connector();
            if( dobj.containKey("S") && dobj.getRetObject("S").ContainsColumnName("DBURL") && dobj.getRetObject("S").ContainsColumnName("DBID") && dobj.getRetObject("S").ContainsColumnName("DBPWD") )
            {
                VOBJ dvobj = dobj.getRetObject("S");
                Conn = connection.getConnectionDirect("oracle", dvobj.getRecord().get("DBURL"), dvobj.getRecord().get("DBID"), dvobj.getRecord().get("DBPWD") );
            }
            else
            {
                Conn = connection.getConnectionDirect("oracle","jdbc:oracle:thin:@133.232.125.190:1521:orcl","HP_DBA01","HP_DBA01");
            }
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
        	
        	StopWatch stopWatch = new StopWatch("batch start - 8 ");
        	stopWatch.start("XIUD3");
        	 dobj  = CALLBP_HP2D101T_XIUD3(Conn, dobj);          
            stopWatch.stop();
        	System.out.println("XIUD3 " + stopWatch.getLastTaskTimeMillis() + " ms");
        	
        	stopWatch.start("SEL1");
        	  dobj  = CALLBP_HP2D101T_SEL1(Conn, dobj);    
            stopWatch.stop();
        	System.out.println("SEL1" + stopWatch.getLastTaskTimeMillis() + " ms");
        	
        	stopWatch.start("INS5");
        	dobj  = CALLBP_HP2D101T_INS5(Conn, dobj);        
            stopWatch.stop();
        	System.out.println("INS5 " + stopWatch.getLastTaskTimeMillis() + " ms");
        	
        	System.out.println(stopWatch.toString());
        	System.out.println();
        	System.out.println(stopWatch.prettyPrint());
        	
        	
        	
        	
                   
                   
                
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
    public DOBJ BP_HP2D101T( DOBJ dobj, Object Connx) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj);
        Connection Conn = (Connection)Connx;
        String message ="";
        dobj  = CALLBP_HP2D101T_XIUD3(Conn, dobj);           
        dobj  = CALLBP_HP2D101T_SEL1(Conn, dobj);           
        dobj  = CALLBP_HP2D101T_INS5(Conn, dobj);           
        return dobj;
    }
    public DOBJ CALLBP_HP2D101T_XIUD3(Connection Conn, DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"XIUD3","HP2D101T delete");
        QExecutor qexe = new QExecutor(dobj);
        dobj.setRtnname("XIUD3");
        VOBJ dvobj = dobj.getRetObject("S");            
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        int        updcnt =0;
        dvobj.first();
        while(dvobj.next())
        {
            sobj = SQLBP_HP2D101T_XIUD3(dobj, dvobj);
            updcnt += qexe.executeUpdate(Conn, sobj);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT","INT");
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("XIUD3");
        dobj.setRetObject(rvobj);
        return dobj;
    }
    private SQLObject SQLBP_HP2D101T_XIUD3(DOBJ dobj, VOBJ dvobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj);
        String   DATA_TYPE = dvobj.getRecord().get("DATA_TYPE");   
        SQLObject sobj = new SQLObject();
        StringBuffer query = new StringBuffer();
        query.append(" DELETE FROM HP2D101T  \n");
        query.append(" WHERE (1=1)  \n");
        query.append(" AND YYYYMM >= F_GET_FISCAL_YEAR(SYSDATE)|| '04'  \n");
        query.append(" AND DATA_TYPE LIKE DECODE(:DATA_TYPE, '' , '%' ,:DATA_TYPE)|| '%'");
        sobj.setSql(query.toString());
        sobj.setString("DATA_TYPE", DATA_TYPE);               
        return sobj;
    }
    public DOBJ CALLBP_HP2D101T_SEL1(Connection Conn, DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL1","HP3D002T Sum");
        QExecutor qexe = new QExecutor(dobj);
        dobj.setRtnname("SEL1");
        VOBJ dvobj = dobj.getRetObject("S");           
        SQLObject  sobj = SQLBP_HP2D101T_SEL1(dobj, dvobj);
        qexe.DispSelectSql(sobj);
        dvobj = qexe.executeQuery(Conn, sobj);
        dvobj.setName("SEL1");
        dvobj.Println("SEL1");
        dobj.setRetObject(dvobj);
        return dobj;
    }
    private SQLObject SQLBP_HP2D101T_SEL1(DOBJ dobj, VOBJ dvobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj);
        String   DATA_TYPE = dobj.getRetObject("S").getRecord().get("DATA_TYPE");   
        SQLObject sobj = new SQLObject();
        StringBuffer query = new StringBuffer();
        query.append("SELECT   \n");
        query.append("          DATA_TYPE   \n");
        query.append("        , PID   \n");
        query.append("        , USE_COMPANY_CD   \n");
        query.append("        , USE_ORG_CD   \n");
        query.append("        , REQ_COMPANY_CD   \n");
        query.append("        , REQ_ORG_CD   \n");
        query.append("        , SRC_COMPANY_CD   \n");
        query.append("        , SRC_ORG_CD   \n");
        query.append("        , DST_COMPANY_CD   \n");
        query.append("        , DST_ORG_CD   \n");
        query.append("        , HPMS_ID   \n");
        query.append("        , SUBJECT_CD   \n");
        query.append("        , YYYYMM   \n");
        query.append("        , MAX(UNIT3) AS UNIT   \n");
        query.append("        , SUM(VAL3)  AS VAL   \n");
        query.append("        , 'BP_HP2D101T'      AS UPDATE_USER_ID   \n");
        query.append("FROM   \n");
        query.append("      (   \n");
        query.append("        SELECT   \n");
        query.append("                  A.DATA_TYPE   \n");
        query.append("                , A.PID   \n");
        query.append("                , A.USE_COMPANY_CD   \n");
        query.append("                , A.USE_ORG_CD   \n");
        query.append("                , A.REQ_COMPANY_CD   \n");
        query.append("                , A.REQ_ORG_CD   \n");
        query.append("                , A.SRC_COMPANY_CD   \n");
        query.append("                , A.SRC_ORG_CD   \n");
        query.append("                , A.DST_COMPANY_CD   \n");
        query.append("                , A.DST_ORG_CD   \n");
        query.append("                , A.HPMS_ID   \n");
        query.append("                , NVL((SELECT SUBJECT_CD FROM HP1DM03T   \n");
        query.append("                    WHERE COMPANY_CD = A.USE_COMPANY_CD   \n");
        query.append("                      AND ORG_CD = A.USE_ORG_CD), '#') AS SUBJECT_CD   \n");
        query.append("                , A.YYYYMM   \n");
        query.append("                , A.UNIT3   \n");
        query.append("                , A.VAL3   \n");
        query.append("        FROM   \n");
        query.append("                HP2D002T A   \n");
        query.append("        WHERE   \n");
        query.append("                (1=1)   \n");
        query.append("             \n");
        query.append("          AND   A.YYYYMM > F_GET_CONFIRM_YYYYMM()   \n");
        query.append("          AND   A.DATA_TYPE LIKE DECODE(:DATA_TYPE,'','%',:DATA_TYPE)||'%'   \n");
        query.append("   \n");
        query.append("        UNION ALL   \n");
        query.append("   \n");
        query.append("        SELECT   \n");
        query.append("                  A.DATA_TYPE   \n");
        query.append("                , A.PID   \n");
        query.append("                , A.USE_COMPANY_CD   \n");
        query.append("                , A.USE_ORG_CD   \n");
        query.append("                , A.REQ_COMPANY_CD   \n");
        query.append("                , A.REQ_ORG_CD   \n");
        query.append("                , A.SRC_COMPANY_CD   \n");
        query.append("                , A.SRC_ORG_CD   \n");
        query.append("                , A.DST_COMPANY_CD   \n");
        query.append("                , A.DST_ORG_CD   \n");
        query.append("                , A.HPMS_ID   \n");
        query.append("                , NVL((SELECT SUBJECT_CD FROM HP1DM03T   \n");
        query.append("                    WHERE COMPANY_CD = A.USE_COMPANY_CD   \n");
        query.append("                      AND ORG_CD = A.USE_ORG_CD), '#') AS SUBJECT_CD   \n");
        query.append("                , A.YYYYMM   \n");
        query.append("                , A.UNIT3   \n");
        query.append("                , A.VAL3   \n");
        query.append("        FROM   \n");
        query.append("                HP2D002T_TZ A   \n");
        query.append("        WHERE   \n");
        query.append("                (1=1)   \n");
        query.append("          AND   A.DATA_TYPE LIKE 'MP_'||'____'   \n");
        query.append("          AND   A.YYYYMM >= F_GET_FISCAL_YEAR(SYSDATE)||'04'   \n");
        query.append("          AND   A.DATA_TYPE LIKE DECODE(:DATA_TYPE,'','%',:DATA_TYPE)||'%'   \n");
        query.append("   \n");
        query.append("      )   \n");
        query.append("GROUP BY   \n");
        query.append("          DATA_TYPE   \n");
        query.append("        , PID   \n");
        query.append("        , USE_COMPANY_CD   \n");
        query.append("        , USE_ORG_CD   \n");
        query.append("        , REQ_COMPANY_CD   \n");
        query.append("        , REQ_ORG_CD   \n");
        query.append("        , SRC_COMPANY_CD   \n");
        query.append("        , SRC_ORG_CD   \n");
        query.append("        , DST_COMPANY_CD   \n");
        query.append("        , DST_ORG_CD   \n");
        query.append("        , HPMS_ID   \n");
        query.append("        , SUBJECT_CD   \n");
        query.append("        , YYYYMM  \n");
        sobj.setSql(query.toString());
        sobj.setString("DATA_TYPE", DATA_TYPE);               
        return sobj;
    }
    public DOBJ CALLBP_HP2D101T_INS5(Connection Conn, DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"INS5","Summary Save");
        QExecutor qexe = new QExecutor(dobj);
        dobj.setRtnname("INS5");
        VOBJ dvobj = dobj.getRetObject("SEL1");           
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        int        updcnt =0;
        dvobj.first();
        while(dvobj.next())
        {
            sobj = SQLBP_HP2D101T_INS5(dobj, dvobj);
            updcnt += qexe.executeUpdate(Conn, sobj);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT","INT");
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("INS5") ;
        dobj.setRetObject(rvobj);
        return dobj;
    }
    private SQLObject SQLBP_HP2D101T_INS5(DOBJ dobj, VOBJ dvobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj);
        String UPDATE_TIME ="SYSDATE";        
        String   USE_COMPANY_CD = dvobj.getRecord().get("USE_COMPANY_CD");   
        String   DATA_TYPE = dvobj.getRecord().get("DATA_TYPE");   
        String   SRC_COMPANY_CD = dvobj.getRecord().get("SRC_COMPANY_CD");   
        String   USE_ORG_CD = dvobj.getRecord().get("USE_ORG_CD");   
        String   REQ_COMPANY_CD = dvobj.getRecord().get("REQ_COMPANY_CD");   
        String   REQ_ORG_CD = dvobj.getRecord().get("REQ_ORG_CD");   
        String   UPDATE_USER_ID = dvobj.getRecord().get("UPDATE_USER_ID");   
        String   YYYYMM = dvobj.getRecord().get("YYYYMM");   
        String   SRC_ORG_CD = dvobj.getRecord().get("SRC_ORG_CD");   
        String   DST_COMPANY_CD = dvobj.getRecord().get("DST_COMPANY_CD");   
        double   VAL = dvobj.getRecord().getDouble("VAL");   
        String   HPMS_ID = dvobj.getRecord().get("HPMS_ID");   
        String   DST_ORG_CD = dvobj.getRecord().get("DST_ORG_CD");   
        String   SUBJECT_CD = dvobj.getRecord().get("SUBJECT_CD");   
        String   PID = dvobj.getRecord().get("PID");   
        String   UNIT = dvobj.getRecord().get("UNIT");   
        SQLObject sobj = new SQLObject();
        StringBuffer query = new StringBuffer();
        query.append(" Insert into HP_DBA01.HP2D101T (UNIT, PID, SUBJECT_CD, DST_ORG_CD, HPMS_ID, UPDATE_TIME, VAL, DST_COMPANY_CD, SRC_ORG_CD, YYYYMM, UPDATE_USER_ID, REQ_ORG_CD, REQ_COMPANY_CD, USE_ORG_CD, SRC_COMPANY_CD, DATA_TYPE, USE_COMPANY_CD)  \n");
        query.append(" values(:UNIT , :PID , :SUBJECT_CD , :DST_ORG_CD , :HPMS_ID , SYSDATE, :VAL , :DST_COMPANY_CD , :SRC_ORG_CD , :YYYYMM , :UPDATE_USER_ID , :REQ_ORG_CD , :REQ_COMPANY_CD , :USE_ORG_CD , :SRC_COMPANY_CD , :DATA_TYPE , :USE_COMPANY_CD )");
        sobj.setSql(query.toString());
        sobj.setString("USE_COMPANY_CD", USE_COMPANY_CD);               
        sobj.setString("DATA_TYPE", DATA_TYPE);               
        sobj.setString("SRC_COMPANY_CD", SRC_COMPANY_CD);               
        sobj.setString("USE_ORG_CD", USE_ORG_CD);               
        sobj.setString("REQ_COMPANY_CD", REQ_COMPANY_CD);               
        sobj.setString("REQ_ORG_CD", REQ_ORG_CD);               
        sobj.setString("UPDATE_USER_ID", UPDATE_USER_ID);               
        sobj.setString("YYYYMM", YYYYMM);               
        sobj.setString("SRC_ORG_CD", SRC_ORG_CD);               
        sobj.setString("DST_COMPANY_CD", DST_COMPANY_CD);               
        sobj.setDouble("VAL", VAL);               
        sobj.setString("HPMS_ID", HPMS_ID);               
        sobj.setString("DST_ORG_CD", DST_ORG_CD);               
        sobj.setString("SUBJECT_CD", SUBJECT_CD);               
        sobj.setString("PID", PID);               
        sobj.setString("UNIT", UNIT);               
        return sobj;
    }
}