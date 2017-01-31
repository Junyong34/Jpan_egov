
package hpms.base;
import java.sql.*;
import java.util.*;
import java.util.concurrent.*;
import WIZ.FR.COM.*;
import WIZ.FR.DAO.*;
import WIZ.FR.MSG.*;
import WIZ.FR.UTIL.*;
/**
*/
public class PlanMgrWizFrame
{
    public PlanMgrWizFrame()
    {
    }
    //##**$$WizFramePlanExcel
    /**
    */
    public DOBJ WizFramePlanExcel(DOBJ dobj)
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
            dobj  = CALLWizFramePlanExcel_PEX2(Conn, dobj);           //  ExcelUpload Data
            dobj  = CALLWizFramePlanExcel_ER01(Conn, dobj);           //  get LogSequence
            dobj  = CALLWizFramePlanExcel_CVT25( dobj);        //  Param
            if( dobj.getRtncode ( ) == 0)
            {
                dobj  = CALLWizFramePlanExcel_UNI72(Conn, dobj);           //  WorkTable Save HP2D001W
                dobj  = CALLWizFramePlanExcel_SEL80(Conn, dobj);           //  MST Cheeck
                dobj  = CALLWizFramePlanExcel_UPD68(Conn, dobj);           //  WorkTable Update HP2D001W
                dobj  = CALLWizFramePlanExcel_SEL78(Conn, dobj);           //  Error exist or No
                if( dobj.getRetObject("SEL78").getRecord().getInt("CNT") > 0)
                {
                    dobj.setRetmsg("1022");
                }
                else if(dobj.getRetmsg ( ).equals(""))
                {
                    dobj  = CALLWizFramePlanExcel_SEL74(Conn, dobj);           //  Duplicated Flag I
                    if( dobj.getRetObject("SEL74").getRecord().getInt("CNT") > 0)
                    {
                        dobj  = CALLWizFramePlanExcel_XIUD114(Conn, dobj);           //  Merge Update ERR_MSG
                        dobj.setRetmsg("1014");
                    }
                    else if(dobj.getRetmsg ( ).equals("") && dobj.getRetObject("SEL74").getRecord().getInt("CNT") == 0)
                    {
                        dobj  = CALLWizFramePlanExcel_XIUD116(Conn, dobj);           //  UNI  HP2D001W
                        dobj  = CALLWizFramePlanExcel_XIUD118(Conn, dobj);           //  Delete HP2D001W
                    }
                    else
                    {
                    }
                }
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
    public DOBJ WizFramePlanExcel( DOBJ dobj, Object Connx) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj);
        Connection Conn = (Connection)Connx;
        String message ="";
        dobj  = CALLWizFramePlanExcel_PEX2(Conn, dobj);           //  ExcelUpload Data
        dobj  = CALLWizFramePlanExcel_ER01(Conn, dobj);           //  get LogSequence
        dobj  = CALLWizFramePlanExcel_CVT25( dobj);        //  Param
        if( dobj.getRtncode ( ) == 0)
        {
            dobj  = CALLWizFramePlanExcel_UNI72(Conn, dobj);           //  WorkTable Save HP2D001W
            dobj  = CALLWizFramePlanExcel_SEL80(Conn, dobj);           //  MST Cheeck
            dobj  = CALLWizFramePlanExcel_UPD68(Conn, dobj);           //  WorkTable Update HP2D001W
            dobj  = CALLWizFramePlanExcel_SEL78(Conn, dobj);           //  Error exist or No
            if( dobj.getRetObject("SEL78").getRecord().getInt("CNT") > 0)
            {
                dobj.setRetmsg("1022");
            }
            else if(dobj.getRetmsg ( ).equals(""))
            {
                dobj  = CALLWizFramePlanExcel_SEL74(Conn, dobj);           //  Duplicated Flag I
                if( dobj.getRetObject("SEL74").getRecord().getInt("CNT") > 0)
                {
                    dobj  = CALLWizFramePlanExcel_XIUD114(Conn, dobj);           //  Merge Update ERR_MSG
                    dobj.setRetmsg("1014");
                }
                else if(dobj.getRetmsg ( ).equals("") && dobj.getRetObject("SEL74").getRecord().getInt("CNT") == 0)
                {
                    dobj  = CALLWizFramePlanExcel_XIUD116(Conn, dobj);           //  UNI  HP2D001W
                    dobj  = CALLWizFramePlanExcel_XIUD118(Conn, dobj);           //  Delete HP2D001W
                }
                else
                {
                }
            }
        }
        return dobj;
    }
    // ExcelUpload Data
    public DOBJ CALLWizFramePlanExcel_PEX2(Connection Conn, DOBJ dobj) throws Exception
    {
        dobj.setRtnname("PEX2");
        CommUtil cu = new CommUtil(dobj);
        HashMap   classinfo = new HashMap();
        classinfo.put("OBJECTID","PEX2");
        classinfo.put("PACKAGE","hpms.UserObject.Excel");
        classinfo.put("CLASS","ForcastIn");
        classinfo.put("METHOD","uploadPlanExcel");
        dobj = cu.callPSExternal(dobj, null, classinfo );
        VOBJ dvobj = dobj.getRetObject("UP01");
        dvobj.setName("PEX2");
        dobj.setRetObject(dvobj);
        return dobj;
    }
    // get LogSequence
    public DOBJ CALLWizFramePlanExcel_ER01(Connection Conn, DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"ER01","get LogSequence");
        QExecutor qexe = new QExecutor(dobj);
        dobj.setRtnname("ER01");
        VOBJ dvobj = dobj.getRetObject("S");           //Input Dataset Object
        SQLObject  sobj = SQLWizFramePlanExcel_ER01(dobj, dvobj);
        dvobj = qexe.executeQuery(Conn, sobj);
        dvobj.setName("ER01");
        dvobj.setRetcode(1);
        dobj.setRetObject(dvobj);
        return dobj;
    }
    private SQLObject SQLWizFramePlanExcel_ER01(DOBJ dobj, VOBJ dvobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj);
        String   CODE = dobj.getRtncode()+"";   //??
        SQLObject sobj = new SQLObject();
        StringBuffer query = new StringBuffer();
        query.append("select LOG_SEQ.nextval LOG_SEQ  , :CODE AS CODE FROM DUAL  \n");
        sobj.setSql(query.toString());
        sobj.setString("CODE", CODE);               //??
        return sobj;
    }
    // Param
    public DOBJ CALLWizFramePlanExcel_CVT25(DOBJ dobj) throws Exception
    {
        dobj.setRtnname("CVT25");
        WizUtil wutil = new WizUtil(dobj,"CVT25","Param");
        VOBJ dvobj = dobj.getRetObjectCopy("ER01");        //get LogSequenceInput Object.(CALLWizFramePlanExcel_ER01)
        String[] outcolumns ={"CODE","LOG_SEQ"};
        HashMap    record =null;
        dvobj.first();
        while(dvobj.next())
        {
            record = dvobj.getRecordMap();
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
        dvobj.setName("CVT25") ;
        dobj.setRetObject(dvobj);
        return dobj;
    }
    // WorkTable Save HP2D001W
    public DOBJ CALLWizFramePlanExcel_UNI72(Connection Conn, DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"UNI72","WorkTable Save HP2D001W");
        QExecutor qexe = new QExecutor(dobj);
        dobj.setRtnname("UNI72");
        VOBJ dvobj = dobj.getRetObject("PEX2");           //ExcelUpload DataInput Object.(CALLWizFramePlanExcel_PEX2)
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        int        inscnt=0, updcnt =0 ,unicnt=0,  rtncnt=0;
        dvobj.first();
        while(dvobj.next())
        {
            sobj = SQLWizFramePlanExcel_UNI72UPD(dobj, dvobj);
            rtncnt= qexe.executeUpdate(Conn, sobj);
            if(rtncnt < 1)
            {
                sobj = SQLWizFramePlanExcel_UNI72INS(dobj, dvobj);
                rtncnt = qexe.executeUpdate(Conn, sobj);
                inscnt += rtncnt;
            }
            else
            {
                updcnt += rtncnt;
            }
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT","INT");
        rvobj.setHeadColumn("INSCNT","INT");
        rvobj.setHeadColumn("UNICNT","INT");
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        recordx.put("INSCNT",inscnt+"");
        recordx.put("UNICNT",unicnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("UNI72") ;
        dobj.setRetObject(rvobj);
        return dobj;
    }
    private SQLObject SQLWizFramePlanExcel_UNI72UPD(DOBJ dobj, VOBJ dvobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj);
        String UPDATE_TIME ="";        //????
        String   USE_COMPANY_CD = dvobj.getRecord().get("USE_COMPANY_CD");   //費用？生元？社CD
        String   DATA_TYPE = dvobj.getRecord().get("DATA_TYPE");   //デ？タタイプ
        String   USE_ORG_CD = dvobj.getRecord().get("USE_ORG_CD");   //費用？生元部CD
        String   REQ_COMPANY_CD = dvobj.getRecord().get("REQ_COMPANY_CD");   //要求元？社CD
        String   REQ_ORG_CD = dvobj.getRecord().get("REQ_ORG_CD");   //要求元部CD
        String   ITEM_NAME = dvobj.getRecord().get("ITEM_NAME");   //ITEM?
        String   YYYYMM = dvobj.getRecord().get("YYYYMM");   //???
        String   PROCESSING_FLAG = dvobj.getRecord().get("PROCESSING_FLAG");   //？理フラグ
        double   VAL = dvobj.getRecord().getDouble("VAL");   //Value
        String   INVEST_TYPE_CD = dvobj.getRecord().get("INVEST_TYPE_CD");   //投資？分CD
        String   HPMS_ID = dvobj.getRecord().get("HPMS_ID");   //HPMS_ID
        String   APPLICATION = dvobj.getRecord().get("APPLICATION");   //用途
        String   PID = dvobj.getRecord().get("PID");   //PID
        String   UNIT = dvobj.getRecord().get("UNIT");   //????
        String   CUSTOMER_CD = dvobj.getRecord().get("CUSTOMER_CD");   //顧客CD
        String   ERR_FLAG ="";   //エラ？フラグ
        String   ERR_MSG ="";   //?????
        String   LOG_SEQ = dobj.getRetObject("ER01").getRecord().get("LOG_SEQ");   //LOG_SEQ
        String   UPLOAD_FILE_NAME = dobj.getRetObject("S1").getRecord().get("FILE_NAME");   //UPLOAD_FILE_NAME
        String   USER_ID = dobj.getRetObject("G").getRecord().get("USER_ID");   //???ID
        SQLObject sobj = new SQLObject();
        StringBuffer query = new StringBuffer();
        query.append(" Update HP_DBA01.HP2D001W  \n");
        query.append(" set LOG_SEQ=:LOG_SEQ , UNIT=:UNIT , ERR_MSG=:ERR_MSG , UPDATE_TIME=SYSDATE , ERR_FLAG=:ERR_FLAG , VAL=:VAL , UPLOAD_FILE_NAME=:UPLOAD_FILE_NAME  \n");
        query.append(" where CUSTOMER_CD=:CUSTOMER_CD  \n");
        query.append(" and USER_ID=:USER_ID  \n");
        query.append(" and PID=:PID  \n");
        query.append(" and APPLICATION=:APPLICATION  \n");
        query.append(" and HPMS_ID=:HPMS_ID  \n");
        query.append(" and INVEST_TYPE_CD=:INVEST_TYPE_CD  \n");
        query.append(" and PROCESSING_FLAG=:PROCESSING_FLAG  \n");
        query.append(" and YYYYMM=:YYYYMM  \n");
        query.append(" and ITEM_NAME=:ITEM_NAME  \n");
        query.append(" and  \n");
        query.append(" REQ_ORG_CD=:REQ_ORG_CD  \n");
        query.append(" and REQ_COMPANY_CD=:REQ_COMPANY_CD  \n");
        query.append(" and  \n");
        query.append(" USE_ORG_CD=:USE_ORG_CD  \n");
        query.append(" and DATA_TYPE=:DATA_TYPE  \n");
        query.append(" and USE_COMPANY_CD=:USE_COMPANY_CD");
        sobj.setSql(query.toString());
        sobj.setString("USE_COMPANY_CD", USE_COMPANY_CD);               //費用？生元？社CD
        sobj.setString("DATA_TYPE", DATA_TYPE);               //デ？タタイプ
        sobj.setString("USE_ORG_CD", USE_ORG_CD);               //費用？生元部CD
        sobj.setString("REQ_COMPANY_CD", REQ_COMPANY_CD);               //要求元？社CD
        sobj.setString("REQ_ORG_CD", REQ_ORG_CD);               //要求元部CD
        sobj.setString("ITEM_NAME", ITEM_NAME);               //ITEM?
        sobj.setString("YYYYMM", YYYYMM);               //???
        sobj.setString("PROCESSING_FLAG", PROCESSING_FLAG);               //？理フラグ
        sobj.setDouble("VAL", VAL);               //Value
        sobj.setString("INVEST_TYPE_CD", INVEST_TYPE_CD);               //投資？分CD
        sobj.setString("HPMS_ID", HPMS_ID);               //HPMS_ID
        sobj.setString("APPLICATION", APPLICATION);               //用途
        sobj.setString("PID", PID);               //PID
        sobj.setString("UNIT", UNIT);               //????
        sobj.setString("CUSTOMER_CD", CUSTOMER_CD);               //顧客CD
        sobj.setString("ERR_FLAG", ERR_FLAG);               //エラ？フラグ
        sobj.setString("ERR_MSG", ERR_MSG);               //?????
        sobj.setString("LOG_SEQ", LOG_SEQ);               //LOG_SEQ
        sobj.setString("UPLOAD_FILE_NAME", UPLOAD_FILE_NAME);               //UPLOAD_FILE_NAME
        sobj.setString("USER_ID", USER_ID);               //???ID
        return sobj;
    }
    private SQLObject SQLWizFramePlanExcel_UNI72INS(DOBJ dobj, VOBJ dvobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj);
        String UPDATE_TIME ="";        //????
        String   USE_COMPANY_CD = dvobj.getRecord().get("USE_COMPANY_CD");   //費用？生元？社CD
        String   DATA_TYPE = dvobj.getRecord().get("DATA_TYPE");   //デ？タタイプ
        String   USE_ORG_CD = dvobj.getRecord().get("USE_ORG_CD");   //費用？生元部CD
        String   REQ_COMPANY_CD = dvobj.getRecord().get("REQ_COMPANY_CD");   //要求元？社CD
        String   REQ_ORG_CD = dvobj.getRecord().get("REQ_ORG_CD");   //要求元部CD
        String   ITEM_NAME = dvobj.getRecord().get("ITEM_NAME");   //ITEM?
        String   YYYYMM = dvobj.getRecord().get("YYYYMM");   //???
        String   PROCESSING_FLAG = dvobj.getRecord().get("PROCESSING_FLAG");   //？理フラグ
        double   VAL = dvobj.getRecord().getDouble("VAL");   //Value
        String   INVEST_TYPE_CD = dvobj.getRecord().get("INVEST_TYPE_CD");   //投資？分CD
        String   HPMS_ID = dvobj.getRecord().get("HPMS_ID");   //HPMS_ID
        String   APPLICATION = dvobj.getRecord().get("APPLICATION");   //用途
        String   PID = dvobj.getRecord().get("PID");   //PID
        String   UNIT = dvobj.getRecord().get("UNIT");   //????
        String   CUSTOMER_CD = dvobj.getRecord().get("CUSTOMER_CD");   //顧客CD
        String   ERR_FLAG ="";   //エラ？フラグ
        String   ERR_MSG ="";   //?????
        String   LOG_SEQ = dobj.getRetObject("ER01").getRecord().get("LOG_SEQ");   //LOG_SEQ
        String   UPLOAD_FILE_NAME = dobj.getRetObject("S1").getRecord().get("FILE_NAME");   //UPLOAD_FILE_NAME
        String   USER_ID = dobj.getRetObject("G").getRecord().get("USER_ID");   //???ID
        SQLObject sobj = new SQLObject();
        StringBuffer query = new StringBuffer();
        query.append(" Insert into HP_DBA01.HP2D001W (LOG_SEQ, CUSTOMER_CD, UNIT, USER_ID, ERR_MSG, PID, APPLICATION, HPMS_ID, UPDATE_TIME, ERR_FLAG, INVEST_TYPE_CD, VAL, PROCESSING_FLAG, YYYYMM, ITEM_NAME, REQ_ORG_CD, REQ_COMPANY_CD, USE_ORG_CD, DATA_TYPE, UPLOAD_FILE_NAME, USE_COMPANY_CD)  \n");
        query.append(" values(:LOG_SEQ , :CUSTOMER_CD , :UNIT , :USER_ID , :ERR_MSG , :PID , :APPLICATION , :HPMS_ID , SYSDATE, :ERR_FLAG , :INVEST_TYPE_CD , :VAL , :PROCESSING_FLAG , :YYYYMM , :ITEM_NAME , :REQ_ORG_CD , :REQ_COMPANY_CD , :USE_ORG_CD , :DATA_TYPE , :UPLOAD_FILE_NAME , :USE_COMPANY_CD )");
        sobj.setSql(query.toString());
        sobj.setString("USE_COMPANY_CD", USE_COMPANY_CD);               //費用？生元？社CD
        sobj.setString("DATA_TYPE", DATA_TYPE);               //デ？タタイプ
        sobj.setString("USE_ORG_CD", USE_ORG_CD);               //費用？生元部CD
        sobj.setString("REQ_COMPANY_CD", REQ_COMPANY_CD);               //要求元？社CD
        sobj.setString("REQ_ORG_CD", REQ_ORG_CD);               //要求元部CD
        sobj.setString("ITEM_NAME", ITEM_NAME);               //ITEM?
        sobj.setString("YYYYMM", YYYYMM);               //???
        sobj.setString("PROCESSING_FLAG", PROCESSING_FLAG);               //？理フラグ
        sobj.setDouble("VAL", VAL);               //Value
        sobj.setString("INVEST_TYPE_CD", INVEST_TYPE_CD);               //投資？分CD
        sobj.setString("HPMS_ID", HPMS_ID);               //HPMS_ID
        sobj.setString("APPLICATION", APPLICATION);               //用途
        sobj.setString("PID", PID);               //PID
        sobj.setString("UNIT", UNIT);               //????
        sobj.setString("CUSTOMER_CD", CUSTOMER_CD);               //顧客CD
        sobj.setString("ERR_FLAG", ERR_FLAG);               //エラ？フラグ
        sobj.setString("ERR_MSG", ERR_MSG);               //?????
        sobj.setString("LOG_SEQ", LOG_SEQ);               //LOG_SEQ
        sobj.setString("UPLOAD_FILE_NAME", UPLOAD_FILE_NAME);               //UPLOAD_FILE_NAME
        sobj.setString("USER_ID", USER_ID);               //???ID
        return sobj;
    }
    // MST Cheeck
    public DOBJ CALLWizFramePlanExcel_SEL80(Connection Conn, DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL80","MST Cheeck");
        QExecutor qexe = new QExecutor(dobj);
        dobj.setRtnname("SEL80");
        VOBJ dvobj = dobj.getRetObject("S");           //Input Dataset Object
        SQLObject  sobj = SQLWizFramePlanExcel_SEL80(dobj, dvobj);
        dvobj = qexe.executeQuery(Conn, sobj);
        dvobj.setName("SEL80");
        dobj.setRetObject(dvobj);
        return dobj;
    }
    private SQLObject SQLWizFramePlanExcel_SEL80(DOBJ dobj, VOBJ dvobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj);
        String   LOG_SEQ = dobj.getRetObject("ER01").getRecord().get("LOG_SEQ");   //LOG_SEQ
        String   USER_ID = dobj.getRetObject("G").getRecord().get("USER_ID");   //???ID
        String   AUTH_CD = dobj.getRetObject("G").getRecord().get("AUTH_CD");   //AUTH_CD
        SQLObject sobj = new SQLObject();
        StringBuffer query = new StringBuffer();
        query.append("SELECT   \n");
        query.append("   Z.PID_ERROR,   \n");
        query.append("   Z.PID,   \n");
        query.append("   Z.DATA_TYPE_ERROR,   \n");
        query.append("   Z.DATA_TYPE,   \n");
        query.append("   Z.USE_COMPANY_CD_ERROR,   \n");
        query.append("   Z.USE_COMPANY_CD,   \n");
        query.append("   Z.REQ_COMPANY_CD_ERROR,   \n");
        query.append("   Z.REQ_COMPANY_CD,   \n");
        query.append("   Z.HPMS_ID_ERROR,   \n");
        query.append("   Z.HPMS_ID,   \n");
        query.append("   Z.INVEST_TYPE_CD_ERROR,   \n");
        query.append("   Z.INVEST_TYPE_CD,   \n");
        query.append("   Z.ITEM_NAME_ERROR,   \n");
        query.append("   Z.ITEM_NAME,   \n");
        query.append("   Z.CUSTOMER_CD_ERROR,   \n");
        query.append("   Z.CUSTOMER_CD,   \n");
        query.append("   Z.DATA_TYPE_ERROR_1,   \n");
        query.append("   Z.UNIT_ERROR,   \n");
        query.append("   Z.UNIT,   \n");
        query.append("   Z.USE_ORG_CD,   \n");
        query.append("   Z.REQ_ORG_CD,   \n");
        query.append("   Z.LOG_SEQ,   \n");
        query.append("   Z.PROCESSING_FLAG,   \n");
        query.append("   Z.APPLICATION,   \n");
        query.append("   DECODE(Z.AUTH_ERROR,'','',Z.AUTH_ERROR) AS AUTH_ERROR   \n");
        query.append("     \n");
        query.append("FROM   \n");
        query.append("   \n");
        query.append("(    \n");
        query.append("        \n");
        query.append("    SELECT    \n");
        query.append("            DECODE(X.PID_CNT,0,'PID Not Found','') AS PID_ERROR,   \n");
        query.append("            X.PID,   \n");
        query.append("            DECODE(X.DATA_TYPE_CNT,0,'DataType Error(Auth,NotFound)','') AS DATA_TYPE_ERROR,   \n");
        query.append("            X.DATA_TYPE,   \n");
        query.append("            DECODE(X.USE_COMPANY_CD_CNT,0,'USE_COMPANY/ORG Not Found.','') AS USE_COMPANY_CD_ERROR,   \n");
        query.append("            X.USE_COMPANY_CD,   \n");
        query.append("            DECODE(X.REQ_COMPANY_CD_CNT,0,'REQ_COMPANY_ORG Not Found.','') AS REQ_COMPANY_CD_ERROR,   \n");
        query.append("            X.REQ_COMPANY_CD,   \n");
        query.append("            DECODE(X.HPMS_ID_CNT,0,'HPMS_ID Not Found',1,'') AS HPMS_ID_ERROR,   \n");
        query.append("            X.HPMS_ID,   \n");
        query.append("            DECODE(X.INVEST_TYPE_CD_CNT,0,'Investment code Not Found.','') AS INVEST_TYPE_CD_ERROR,   \n");
        query.append("            X.INVEST_TYPE_CD,   \n");
        query.append("            DECODE(X.ITEM_NAME_CNT,0,'ITEM code Not Fount.','') AS ITEM_NAME_ERROR,   \n");
        query.append("            X.ITEM_NAME,   \n");
        query.append("            DECODE(X.CUSTOMER_CD_CNT,0,'Customer Code Not Found.','') AS CUSTOMER_CD_ERROR,   \n");
        query.append("            X.CUSTOMER_CD,   \n");
        query.append("            DECODE(X.DATA_TYPE_CNT2,0,'DATA TYPE(AUTH) Not Found.','') AS DATA_TYPE_ERROR_1,   \n");
        query.append("            DECODE(X.UNIT_CNT,0,'UNIT code Not Found.','') AS UNIT_ERROR,   \n");
        query.append("            X.UNIT,   \n");
        query.append("            X.USE_ORG_CD,   \n");
        query.append("            X.REQ_ORG_CD,   \n");
        query.append("            X.LOG_SEQ,   \n");
        query.append("            X.PROCESSING_FLAG,   \n");
        query.append("               X.APPLICATION,   \n");
        query.append("          DECODE(   \n");
        query.append("             DECODE(   \n");
        query.append("                        X.EXCEL_OURG_SEQ,'ALL','1', (SELECT COUNT(*) AS CNT FROM(                        \n");
        query.append("                              (SELECT  SUBSTR(D.ORG_SEQ, 0,8)||'00' AS ORG_LVL_4   \n");
        query.append("                               FROM US0M001T_V01 S, HP1DM03T D   \n");
        query.append("                              WHERE S.USER_ID = :USER_ID   \n");
        query.append("                                AND S.COMPANY_INFO_CODE = D.COMPANY_CD   \n");
        query.append("                                AND S.OFFICE_CODE  = D.ORG_CD )   \n");
        query.append("                           ) Y   \n");
        query.append("                     WHERE X.EXCEL_OURG_SEQ = Y.ORG_LVL_4)   \n");
        query.append("                     ) ,0,'IUD Check authority','') AS AUTH_ERROR   \n");
        query.append("                       \n");
        query.append("                      \n");
        query.append("    FROM   \n");
        query.append("    (   \n");
        query.append("     SELECT   \n");
        query.append("           DECODE(A.PID,'000000','0',(SELECT COUNT (A1.PID) PID_CNT   \n");
        query.append("              FROM HP1DM01T A1   \n");
        query.append("             WHERE A1.PID = A.PID))   \n");
        query.append("              AS PID_CNT,   \n");
        query.append("              A.PID,   \n");
        query.append("           (SELECT COUNT (*) CNT   \n");
        query.append("              FROM HP1DM06T A1   \n");
        query.append("             WHERE A1.DATA_TYPE = A.DATA_TYPE)   \n");
        query.append("              AS DATA_TYPE_CNT,   \n");
        query.append("              A.DATA_TYPE,   \n");
        query.append("           (SELECT COUNT (*) USE_COMPANY_CNT   \n");
        query.append("              FROM HP1DM03T A1   \n");
        query.append("             WHERE A1.COMPANY_CD = A.USE_COMPANY_CD AND A1.ORG_CD = A.USE_ORG_CD)   \n");
        query.append("              AS USE_COMPANY_CD_CNT,   \n");
        query.append("              A.USE_COMPANY_CD,   \n");
        query.append("           (SELECT COUNT (*) USE_COMPANY_CNT   \n");
        query.append("              FROM HP1DM03T A1   \n");
        query.append("             WHERE A1.COMPANY_CD = A.REQ_COMPANY_CD AND A1.ORG_CD = A.REQ_ORG_CD)   \n");
        query.append("              AS REQ_COMPANY_CD_CNT,   \n");
        query.append("              A.REQ_COMPANY_CD,   \n");
        query.append("           (SELECT COUNT (*) HPMS_ID_CNT   \n");
        query.append("              FROM HP1DM04T A1   \n");
        query.append("             WHERE A1.HPMS_ID = A.HPMS_ID)   \n");
        query.append("              AS HPMS_ID_CNT,   \n");
        query.append("              A.HPMS_ID,   \n");
        query.append("           DECODE(A.INVEST_TYPE_CD,'#',1,(SELECT COUNT (*) INVEST_TYPE_CNT   \n");
        query.append("              FROM HP1DM07T A1   \n");
        query.append("             WHERE A1.INVEST_TYPE_CD = A.INVEST_TYPE_CD))   \n");
        query.append("              AS INVEST_TYPE_CD_CNT,   \n");
        query.append("              A.INVEST_TYPE_CD,   \n");
        query.append("           DECODE(A.ITEM_NAME ,'#',1,(SELECT COUNT (*) CNT   \n");
        query.append("              FROM HP1DM10T A1   \n");
        query.append("             WHERE A1.ITEM_NAME = A.ITEM_NAME))   \n");
        query.append("              AS ITEM_NAME_CNT,   \n");
        query.append("              A.ITEM_NAME,   \n");
        query.append("           DECODE(A.CUSTOMER_CD,'#',1,(SELECT COUNT (*) CUSTOMER_CNT   \n");
        query.append("              FROM HP1DM20T A1   \n");
        query.append("             WHERE A1.CUSTOMER_CD = A.CUSTOMER_CD))   \n");
        query.append("              AS CUSTOMER_CD_CNT,   \n");
        query.append("              A.CUSTOMER_CD,   \n");
        query.append("           (SELECT COUNT (*) FCST_CNT   \n");
        query.append("              FROM HP1AU03T A1   \n");
        query.append("             WHERE A1.AUTH_CD = A.AUTH_CD AND A1.DATA_TYPE = A.DATA_TYPE)   \n");
        query.append("              AS DATA_TYPE_CNT2,   \n");
        query.append("           (SELECT COUNT (*) CNT   \n");
        query.append("              FROM HP1DM30T A1   \n");
        query.append("             WHERE A1.CURRENCY_CD = A.UNIT)   \n");
        query.append("              AS UNIT_CNT,   \n");
        query.append("              A.UNIT,   \n");
        query.append("              A.REQ_ORG_CD,   \n");
        query.append("              A.USE_ORG_CD,   \n");
        query.append("              A.LOG_SEQ,   \n");
        query.append("               A.PROCESSING_FLAG,   \n");
        query.append("               A.APPLICATION,   \n");
        query.append("         DECODE(A.AUTH_CD,'50',(SELECT  SUBSTR (C.ORG_SEQ, 0, 8) || '00' AS ORG_LVL_4   \n");
        query.append("          FROM HP1DM03T C   \n");
        query.append("         WHERE C.COMPANY_CD || C.ORG_CD = A.USE_COMPANY_CD || A.USE_ORG_CD ) ,'ALL')AS EXCEL_OURG_SEQ        \n");
        query.append("      FROM    \n");
        query.append("      (   \n");
        query.append("          SELECT   \n");
        query.append("               DISTINCT   \n");
        query.append("                  PID,   \n");
        query.append("                  DATA_TYPE,   \n");
        query.append("                  USE_COMPANY_CD,   \n");
        query.append("                  USE_ORG_CD,   \n");
        query.append("                  REQ_COMPANY_CD,   \n");
        query.append("                  REQ_ORG_CD,   \n");
        query.append("                  HPMS_ID,   \n");
        query.append("                  INVEST_TYPE_CD,   \n");
        query.append("                  ITEM_NAME,   \n");
        query.append("                  CUSTOMER_CD,   \n");
        query.append("                  UNIT,   \n");
        query.append("                  :AUTH_CD AS AUTH_CD,   \n");
        query.append("                  LOG_SEQ,   \n");
        query.append("                  PROCESSING_FLAG,   \n");
        query.append("               APPLICATION   \n");
        query.append("          FROM HP2D001W   \n");
        query.append("         WHERE LOG_SEQ = :LOG_SEQ   \n");
        query.append("         ) A   \n");
        query.append("      ) X   \n");
        query.append("    )Z   \n");
        query.append(" WHERE Z.PID_ERROR IS NOT NULL OR DATA_TYPE_ERROR IS NOT NULL   \n");
        query.append(" OR USE_COMPANY_CD_ERROR  IS NOT NULL OR REQ_COMPANY_CD_ERROR  IS NOT NULL   \n");
        query.append(" OR HPMS_ID_ERROR  IS NOT NULL OR INVEST_TYPE_CD_ERROR  IS NOT NULL   \n");
        query.append(" OR ITEM_NAME_ERROR  IS NOT NULL OR CUSTOMER_CD_ERROR  IS NOT NULL   \n");
        query.append(" OR UNIT_ERROR  IS NOT NULL OR AUTH_ERROR  IS NOT NULL  \n");
        sobj.setSql(query.toString());
        sobj.setString("LOG_SEQ", LOG_SEQ);               //LOG_SEQ
        sobj.setString("USER_ID", USER_ID);               //???ID
        sobj.setString("AUTH_CD", AUTH_CD);               //AUTH_CD
        return sobj;
    }
    // WorkTable Update HP2D001W
    public DOBJ CALLWizFramePlanExcel_UPD68(Connection Conn, DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"UPD68","WorkTable Update HP2D001W");
        QExecutor qexe = new QExecutor(dobj);
        dobj.setRtnname("UPD68");
        VOBJ dvobj = dobj.getRetObject("SEL80");           //MST CheeckInput Object.(CALLWizFramePlanExcel_SEL80)
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        int        updcnt =0;
        dvobj.first();
        while(dvobj.next())
        {
            sobj = SQLWizFramePlanExcel_UPD68(dobj, dvobj);
            updcnt += qexe.executeUpdate(Conn, sobj);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT","INT");
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("UPD68") ;
        dobj.setRetObject(rvobj);
        return dobj;
    }
    private SQLObject SQLWizFramePlanExcel_UPD68(DOBJ dobj, VOBJ dvobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj);
        String   USE_COMPANY_CD = dvobj.getRecord().get("USE_COMPANY_CD");   //費用？生元？社CD
        String   DATA_TYPE = dvobj.getRecord().get("DATA_TYPE");   //デ？タタイプ
        String   USE_ORG_CD = dvobj.getRecord().get("USE_ORG_CD");   //費用？生元部CD
        String   REQ_COMPANY_CD = dvobj.getRecord().get("REQ_COMPANY_CD");   //要求元？社CD
        String   REQ_ORG_CD = dvobj.getRecord().get("REQ_ORG_CD");   //要求元部CD
        String   ITEM_NAME = dvobj.getRecord().get("ITEM_NAME");   //ITEM?
        String   PROCESSING_FLAG = dvobj.getRecord().get("PROCESSING_FLAG");   //？理フラグ
        String   INVEST_TYPE_CD = dvobj.getRecord().get("INVEST_TYPE_CD");   //投資？分CD
        String   HPMS_ID = dvobj.getRecord().get("HPMS_ID");   //HPMS_ID
        String   APPLICATION = dvobj.getRecord().get("APPLICATION");   //用途
        String   PID = dvobj.getRecord().get("PID");   //PID
        String   CUSTOMER_CD = dvobj.getRecord().get("CUSTOMER_CD");   //顧客CD
        String   LOG_SEQ = dvobj.getRecord().get("LOG_SEQ");   //LOG_SEQ
        String   ERR_FLAG ="1";   //エラ？フラグ
        String   ERR_MSG = dobj.getRetObject("SEL80").getRecord().get("CUSTOMER_CD_ERROR")+" "+dobj.getRetObject("SEL80").getRecord().get("DATA_TYPE_ERROR")+" "+dobj.getRetObject("SEL80").getRecord().get("DATA_TYPE_ERROR_1")+" "+dobj.getRetObject("SEL80").getRecord().get("HPMS_ID_ERROR")+" "+dobj.getRetObject("SEL80").getRecord().get("INVEST_TYPE_CD_ERROR")+" "+dobj.getRetObject("SEL80").getRecord().get("ITEM_NAME_ERROR")+" "+dobj.getRetObject("SEL80").getRecord().get("PID_ERROR")+" "+dobj.getRetObject("SEL80").getRecord().get("REQ_COMPANY_CD_ERROR")+" "+dobj.getRetObject("SEL80").getRecord().get("UNIT_ERROR")+" "+dobj.getRetObject("SEL80").getRecord().get("USE_COMPANY_CD_ERROR")+" "+dvobj.getRecord().get("AUTH_ERROR");   //?????
        String   USER_ID = dobj.getRetObject("G").getRecord().get("USER_ID");   //???ID
        SQLObject sobj = new SQLObject();
        StringBuffer query = new StringBuffer();
        query.append(" Update HP_DBA01.HP2D001W  \n");
        query.append(" set LOG_SEQ=:LOG_SEQ , ERR_MSG=:ERR_MSG , ERR_FLAG=:ERR_FLAG  \n");
        query.append(" where CUSTOMER_CD=:CUSTOMER_CD  \n");
        query.append(" and USER_ID=:USER_ID  \n");
        query.append(" and PID=:PID  \n");
        query.append(" and APPLICATION=:APPLICATION  \n");
        query.append(" and HPMS_ID=:HPMS_ID  \n");
        query.append(" and INVEST_TYPE_CD=:INVEST_TYPE_CD  \n");
        query.append(" and PROCESSING_FLAG=:PROCESSING_FLAG  \n");
        query.append(" and ITEM_NAME=:ITEM_NAME  \n");
        query.append(" and  \n");
        query.append(" REQ_ORG_CD=:REQ_ORG_CD  \n");
        query.append(" and REQ_COMPANY_CD=:REQ_COMPANY_CD  \n");
        query.append(" and  \n");
        query.append(" USE_ORG_CD=:USE_ORG_CD  \n");
        query.append(" and DATA_TYPE=:DATA_TYPE  \n");
        query.append(" and USE_COMPANY_CD=:USE_COMPANY_CD");
        sobj.setSql(query.toString());
        sobj.setString("USE_COMPANY_CD", USE_COMPANY_CD);               //費用？生元？社CD
        sobj.setString("DATA_TYPE", DATA_TYPE);               //デ？タタイプ
        sobj.setString("USE_ORG_CD", USE_ORG_CD);               //費用？生元部CD
        sobj.setString("REQ_COMPANY_CD", REQ_COMPANY_CD);               //要求元？社CD
        sobj.setString("REQ_ORG_CD", REQ_ORG_CD);               //要求元部CD
        sobj.setString("ITEM_NAME", ITEM_NAME);               //ITEM?
        sobj.setString("PROCESSING_FLAG", PROCESSING_FLAG);               //？理フラグ
        sobj.setString("INVEST_TYPE_CD", INVEST_TYPE_CD);               //投資？分CD
        sobj.setString("HPMS_ID", HPMS_ID);               //HPMS_ID
        sobj.setString("APPLICATION", APPLICATION);               //用途
        sobj.setString("PID", PID);               //PID
        sobj.setString("CUSTOMER_CD", CUSTOMER_CD);               //顧客CD
        sobj.setString("LOG_SEQ", LOG_SEQ);               //LOG_SEQ
        sobj.setString("ERR_FLAG", ERR_FLAG);               //エラ？フラグ
        sobj.setString("ERR_MSG", ERR_MSG);               //?????
        sobj.setString("USER_ID", USER_ID);               //???ID
        return sobj;
    }
    // Error exist or No
    public DOBJ CALLWizFramePlanExcel_SEL78(Connection Conn, DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL78","Error exist or No");
        QExecutor qexe = new QExecutor(dobj);
        dobj.setRtnname("SEL78");
        VOBJ dvobj = dobj.getRetObject("S");           //Input Dataset Object
        SQLObject  sobj = SQLWizFramePlanExcel_SEL78(dobj, dvobj);
        dvobj = qexe.executeQuery(Conn, sobj);
        dvobj.setName("SEL78");
        dobj.setRetObject(dvobj);
        return dobj;
    }
    private SQLObject SQLWizFramePlanExcel_SEL78(DOBJ dobj, VOBJ dvobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj);
        String   LOG_SEQ = dobj.getRetObject("ER01").getRecord().get("LOG_SEQ");   //LOG_SEQ
        SQLObject sobj = new SQLObject();
        StringBuffer query = new StringBuffer();
        query.append("SELECT COUNT(*) CNT   \n");
        query.append("  FROM HP2D001W   \n");
        query.append(" WHERE LOG_SEQ = :LOG_SEQ   \n");
        query.append("AND  ERR_FLAG =1  \n");
        sobj.setSql(query.toString());
        sobj.setString("LOG_SEQ", LOG_SEQ);               //LOG_SEQ
        return sobj;
    }
    // Duplicated Flag I
    public DOBJ CALLWizFramePlanExcel_SEL74(Connection Conn, DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL74","Duplicated Flag I");
        QExecutor qexe = new QExecutor(dobj);
        dobj.setRtnname("SEL74");
        VOBJ dvobj = dobj.getRetObject("S");           //Input Dataset Object
        SQLObject  sobj = SQLWizFramePlanExcel_SEL74(dobj, dvobj);
        dvobj = qexe.executeQuery(Conn, sobj);
        dvobj.setName("SEL74");
        dobj.setRetObject(dvobj);
        return dobj;
    }
    private SQLObject SQLWizFramePlanExcel_SEL74(DOBJ dobj, VOBJ dvobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj);
        String   LOG_SEQ = dobj.getRetObject("ER01").getRecord().get("LOG_SEQ");   //LOG_SEQ
        SQLObject sobj = new SQLObject();
        StringBuffer query = new StringBuffer();
        query.append("SELECT      \n");
        query.append("       COUNT(*) AS CNT   \n");
        query.append("  FROM HP2D001W  A, HP2D001T B   \n");
        query.append("  WHERE A.LOG_SEQ = :LOG_SEQ   \n");
        query.append("    AND A.DATA_TYPE =B.DATA_TYPE   \n");
        query.append("    AND A.PID =B.PID   \n");
        query.append("    AND A.USE_COMPANY_CD =B.USE_COMPANY_CD   \n");
        query.append("    AND A.USE_ORG_CD =B.USE_ORG_CD   \n");
        query.append("    AND A.REQ_COMPANY_CD =B.REQ_COMPANY_CD   \n");
        query.append("    AND A.REQ_ORG_CD =B.REQ_ORG_CD   \n");
        query.append("    AND A.HPMS_ID =B.HPMS_ID   \n");
        query.append("    AND A.INVEST_TYPE_CD =B.INVEST_TYPE_CD   \n");
        query.append("    AND A.APPLICATION =B.APPLICATION   \n");
        query.append("    AND A.ITEM_NAME =B.ITEM_NAME   \n");
        query.append("    AND A.CUSTOMER_CD =B.CUSTOMER_CD   \n");
        query.append("    AND A.YYYYMM =B.YYYYMM   \n");
        query.append("      AND A.PROCESSING_FLAG = 'I'  \n");
        sobj.setSql(query.toString());
        sobj.setString("LOG_SEQ", LOG_SEQ);               //LOG_SEQ
        return sobj;
    }
    // Merge Update ERR_MSG
    public DOBJ CALLWizFramePlanExcel_XIUD114(Connection Conn, DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"XIUD114","Merge Update ERR_MSG");
        QExecutor qexe = new QExecutor(dobj);
        dobj.setRtnname("XIUD114");
        VOBJ dvobj = dobj.getRetObject("ER01");            //get LogSequenceInput Object.(CALLWizFramePlanExcel_ER01)
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        int        updcnt =0;
        dvobj.first();
        while(dvobj.next())
        {
            sobj = SQLWizFramePlanExcel_XIUD114(dobj, dvobj);
            updcnt += qexe.executeUpdate(Conn, sobj);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT","INT");
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("XIUD114");
        dobj.setRetObject(rvobj);
        return dobj;
    }
    private SQLObject SQLWizFramePlanExcel_XIUD114(DOBJ dobj, VOBJ dvobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj);
        String   ERR_FLAG ="1";   //エラ？フラグ
        String   ERR_MSG ="Insert Error Duplicated Data";   //?????
        String   LOG_SEQ = dobj.getRetObject("ER01").getRecord().get("LOG_SEQ");   //LOG_SEQ
        SQLObject sobj = new SQLObject();
        StringBuffer query = new StringBuffer();
        query.append("MERGE INTO HP2D001W X USING   ( SELECT A.USER_ID,           :ERR_FLAG AS ERR_FLAG ,           :ERR_MSG AS ERR_MSG,           A.PROCESSING_FLAG,           A.DATA_TYPE,           A.PID,           A.USE_COMPANY_CD,           A.USE_ORG_CD,           A.REQ_COMPANY_CD,           A.REQ_ORG_CD,           A.HPMS_ID,           A.INVEST_TYPE_CD,           A.APPLICATION,           A.ITEM_NAME,           A.CUSTOMER_CD ,            A.YYYYMM      FROM HP2D001W A, HP2D001T B      WHERE     A.LOG_SEQ = :LOG_SEQ           AND A.DATA_TYPE = B.DATA_TYPE           AND A.PID = B.PID           AND A.USE_COMPANY_CD = B.USE_COMPANY_CD           AND A.USE_ORG_CD = B.USE_ORG_CD           AND A.REQ_COMPANY_CD = B.REQ_COMPANY_CD           AND A.REQ_ORG_CD = B.REQ_ORG_CD           AND A.HPMS_ID = B.HPMS_ID           AND A.INVEST_TYPE_CD = B.INVEST_TYPE_CD           AND A.APPLICATION = B.APPLICATION           AND A.ITEM_NAME = B.ITEM_NAME           AND A.CUSTOMER_CD = B.CUSTOMER_CD           AND A.YYYYMM = B.YYYYMM           AND A.PROCESSING_FLAG='I'     ) Y     ON (    X.DATA_TYPE = Y.DATA_TYPE           AND X.PID = Y.PID           AND X.USE_COMPANY_CD = Y.USE_COMPANY_CD           AND X.USE_ORG_CD = Y.USE_ORG_CD           AND X.REQ_COMPANY_CD = Y.REQ_COMPANY_CD           AND X.REQ_ORG_CD = Y.REQ_ORG_CD           AND X.HPMS_ID = Y.HPMS_ID           AND X.INVEST_TYPE_CD = Y.INVEST_TYPE_CD           AND X.APPLICATION = Y.APPLICATION           AND X.ITEM_NAME = Y.ITEM_NAME           AND X.CUSTOMER_CD = Y.CUSTOMER_CD           AND X.YYYYMM = Y.YYYYMM     ) when matched then UPDATE SET X.ERR_FLAG = Y.ERR_FLAG, X.ERR_MSG=Y.ERR_MSG  \n");
        sobj.setSql(query.toString());
        sobj.setString("ERR_FLAG", ERR_FLAG);               //エラ？フラグ
        sobj.setString("ERR_MSG", ERR_MSG);               //?????
        sobj.setString("LOG_SEQ", LOG_SEQ);               //LOG_SEQ
        return sobj;
    }
    // UNI  HP2D001W
    public DOBJ CALLWizFramePlanExcel_XIUD116(Connection Conn, DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"XIUD116","UNI  HP2D001W");
        QExecutor qexe = new QExecutor(dobj);
        dobj.setRtnname("XIUD116");
        VOBJ dvobj = dobj.getRetObject("ER01");            //get LogSequenceInput Object.(CALLWizFramePlanExcel_ER01)
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        int        updcnt =0;
        dvobj.first();
        while(dvobj.next())
        {
            sobj = SQLWizFramePlanExcel_XIUD116(dobj, dvobj);
            updcnt += qexe.executeUpdate(Conn, sobj);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT","INT");
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("XIUD116");
        dobj.setRetObject(rvobj);
        return dobj;
    }
    private SQLObject SQLWizFramePlanExcel_XIUD116(DOBJ dobj, VOBJ dvobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj);
        String   LOG_SEQ = dvobj.getRecord().get("LOG_SEQ");   //LOG_SEQ
        SQLObject sobj = new SQLObject();
        StringBuffer query = new StringBuffer();
        query.append("MERGE INTO HP2D001T  X USING   ( SELECT A.USER_ID ,           A.PROCESSING_FLAG,           A.DATA_TYPE,           A.PID,           A.USE_COMPANY_CD,           A.USE_ORG_CD,           A.REQ_COMPANY_CD,           A.REQ_ORG_CD,           A.HPMS_ID,           A.INVEST_TYPE_CD,           A.APPLICATION,           A.ITEM_NAME,           A.CUSTOMER_CD ,           A.YYYYMM ,           A.VAL,           A.UNIT,           A.UPLOAD_FILE_NAME,           A.UPDATE_TIME       FROM HP2D001W A      WHERE     A.LOG_SEQ = :LOG_SEQ      ) Y     ON (    X.DATA_TYPE = Y.DATA_TYPE           AND X.PID = Y.PID           AND X.USE_COMPANY_CD = Y.USE_COMPANY_CD           AND X.USE_ORG_CD = Y.USE_ORG_CD           AND X.REQ_COMPANY_CD = Y.REQ_COMPANY_CD           AND X.REQ_ORG_CD = Y.REQ_ORG_CD           AND X.HPMS_ID = Y.HPMS_ID           AND X.INVEST_TYPE_CD = Y.INVEST_TYPE_CD           AND X.APPLICATION = Y.APPLICATION           AND X.ITEM_NAME = Y.ITEM_NAME           AND X.CUSTOMER_CD = Y.CUSTOMER_CD           AND X.YYYYMM = Y.YYYYMM     ) when matched then UPDATE SET           X.UPDATE_USER_ID =  Y.USER_ID,           X.VAL=Y.VAL,           X.UNIT=Y.UNIT,           X.UPLOAD_FILE_NAME=Y.UPLOAD_FILE_NAME,           X.UPDATE_TIME =Y.UPDATE_TIME when NOT matched then INSERT (  X.UPDATE_USER_ID ,            X.DATA_TYPE,           X.PID,           X.USE_COMPANY_CD,           X.USE_ORG_CD,           X.REQ_COMPANY_CD,           X.REQ_ORG_CD,           X.HPMS_ID,           X.INVEST_TYPE_CD,           X.APPLICATION,           X.ITEM_NAME,           X.CUSTOMER_CD ,           X.YYYYMM ,           X.VAL,           X.UNIT,           X.UPLOAD_FILE_NAME,           X.UPDATE_TIME            )            VALUES            (           Y.USER_ID ,           Y.DATA_TYPE,           Y.PID,           Y.USE_COMPANY_CD,           Y.USE_ORG_CD,           Y.REQ_COMPANY_CD,           Y.REQ_ORG_CD,           Y.HPMS_ID,           Y.INVEST_TYPE_CD,           Y.APPLICATION,           Y.ITEM_NAME,           Y.CUSTOMER_CD ,           Y.YYYYMM ,           Y.VAL,           Y.UNIT,           Y.UPLOAD_FILE_NAME,           Y.UPDATE_TIME )  \n");
        sobj.setSql(query.toString());
        sobj.setString("LOG_SEQ", LOG_SEQ);               //LOG_SEQ
        return sobj;
    }
    // Delete HP2D001W
    public DOBJ CALLWizFramePlanExcel_XIUD118(Connection Conn, DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"XIUD118","Delete HP2D001W");
        QExecutor qexe = new QExecutor(dobj);
        dobj.setRtnname("XIUD118");
        VOBJ dvobj = dobj.getRetObject("ER01");            //get LogSequenceInput Object.(CALLWizFramePlanExcel_ER01)
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        int        updcnt =0;
        dvobj.first();
        while(dvobj.next())
        {
            sobj = SQLWizFramePlanExcel_XIUD118(dobj, dvobj);
            updcnt += qexe.executeUpdate(Conn, sobj);
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT","INT");
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("XIUD118");
        dobj.setRetObject(rvobj);
        return dobj;
    }
    private SQLObject SQLWizFramePlanExcel_XIUD118(DOBJ dobj, VOBJ dvobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj);
        String   LOG_SEQ = dvobj.getRecord().get("LOG_SEQ");   //LOG_SEQ
        SQLObject sobj = new SQLObject();
        StringBuffer query = new StringBuffer();
        query.append(" DELETE FROM HP2D001T X  \n");
        query.append(" WHERE ( X.DATA_TYPE, X.PID, X.USE_COMPANY_CD,  \n");
        query.append(" X.USE_ORG_CD, X.REQ_COMPANY_CD,  \n");
        query.append(" X.REQ_ORG_CD, X.HPMS_ID, X.INVEST_TYPE_CD, X.APPLICATION, X.ITEM_NAME, X.CUSTOMER_CD ) IN (SELECT X.DATA_TYPE, X.PID, X.USE_COMPANY_CD,  \n");
        query.append(" X.USE_ORG_CD, X.REQ_COMPANY_CD,  \n");
        query.append(" X.REQ_ORG_CD, X.HPMS_ID, X.INVEST_TYPE_CD, X.APPLICATION, X.ITEM_NAME, X.CUSTOMER_CD FROM HP2D001W X  \n");
        query.append(" WHERE X.LOG_SEQ = :LOG_SEQ  \n");
        query.append(" AND X.PROCESSING_FLAG= 'D' )");
        sobj.setSql(query.toString());
        sobj.setString("LOG_SEQ", LOG_SEQ);               //LOG_SEQ
        return sobj;
    }
    //##**$$WizFramePlanExcel
    //##**$$end
}