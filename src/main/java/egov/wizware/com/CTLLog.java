package egov.wizware.com;

import java.sql.*;
import java.util.*;
import egov.wizware.com.*;
import egov.wizware.ria.*;
public class CTLLog {
    private int gubun=0;
    private String SYSID="";
    private String MENUID="";
    private String EVENTID="";
    private String PACKAGE="";
    private String CLASS="";
    private String METHOD="";

    public CTLLog()
    {
    }
    public CTLLog(int gbn)
    {

        gubun = gbn;
    }
    public void setEventInfo(String sysid, String menuid, String eventid){
        this.SYSID  = sysid;
        this.MENUID = menuid;
        this.EVENTID= eventid;
    }
    public void setMethodInfo(String packagenm, String classnm, String methodnm){
        this.PACKAGE = packagenm;
        this.CLASS   = classnm;
        this.METHOD  = methodnm;
    }

    public void write(String userid, String resptime)
    {
        StdWrite(userid,  resptime);
    }
    public void write(Connection conn, String userid, String resptime)
    {
        if(gubun == 1){
            LogTableWrite(conn,  userid,  resptime);
        }else if(gubun == 2){
            StdWrite(  userid,  resptime);
            LogTableWrite( conn,  userid,  resptime);
        }else {
            StdWrite( userid,  resptime);
        }
    }


    public void excepWrite(Connection conn, String userid, String msg, String objectid){
        ExecpTableWrite(conn, userid, msg, objectid);
    }
    public void excepWrite(Connection conn, String userid, String msg, String objectid, DOBJ dobj ){
        ExecpTableWrite(conn, userid, msg, objectid, dobj);
    }

    private void ExecpTableWrite(Connection conn,String userid, String msg, String Objectid)
    {
        try {
            SQLObject sobj = new SQLObject();
            String sql = "";
            sql += " INSERT INTO PEXCEPLOG(SYSID ,MENUID ,EVENTID ,MSG ,OBJECTID ,USERID ,LOGTIME ,PACKAGE ,CLASS ,METHOD)     \n";
            sql += " VALUES(:SYSID, :MENUID, :EVENTID, :MSG, :OBJECTID, :USERID, SYSDATE, :PACKAGE, :CLASS, :METHOD)  \n";
            sobj.setSql(sql);
            sobj.setString("SYSID", SYSID);
            sobj.setString("MENUID", MENUID);
            sobj.setString("EVENTID", EVENTID);
            sobj.setString("MSG", msg);
            sobj.setString("OBJECTID", Objectid);
            sobj.setString("USERID", userid);
            sobj.setString("PACKAGE", PACKAGE);
            sobj.setString("CLASS", CLASS);
            sobj.setString("METHOD", METHOD);

            QExecutor exe = new QExecutor();
            // exe.DispSelectSql(sobj);
            exe.executeUpdate(conn, sobj);

        } catch (Exception e) {
            System.out.println("Exception LOG WRITE ERROR");
        }
    }

    private void ExecpTableWrite( Connection conn,String userid, String msg, String Objectid , DOBJ dobj )
    {
        ArrayList vlist = dobj.getRetObjectKeys();
        //dobj.dispRetObjectKeyNames();

        try {
            SQLObject sobj = new SQLObject();
            String sql = "";
            if(vlist != null && vlist.size() > 0){
                sql += " INSERT INTO PEXCEPLOG(SYSID ,MENUID ,EVENTID ,MSG ,OBJECTID ,USERID ,LOGTIME ,PACKAGE ,CLASS ,METHOD, INDATA)     \n";
                sql += " VALUES(:SYSID, :MENUID, :EVENTID, :MSG, :OBJECTID, :USERID, SYSDATE, :PACKAGE, :CLASS, :METHOD, :INDATA)  \n";
            }else {
                sql += " INSERT INTO PEXCEPLOG(SYSID ,MENUID ,EVENTID ,MSG ,OBJECTID ,USERID ,LOGTIME ,PACKAGE ,CLASS ,METHOD)     \n";
                sql += " VALUES(:SYSID, :MENUID, :EVENTID, :MSG, :OBJECTID, :USERID, SYSDATE, :PACKAGE, :CLASS, :METHOD)  \n";
            }

            sobj.setSql(sql);
            sobj.setString("SYSID", SYSID);
            sobj.setString("MENUID", MENUID);
            sobj.setString("EVENTID", EVENTID);
            sobj.setString("MSG", msg);
            sobj.setString("OBJECTID", Objectid);
            sobj.setString("USERID", userid);
            sobj.setString("PACKAGE", PACKAGE);
            sobj.setString("CLASS", CLASS);
            sobj.setString("METHOD", METHOD);

            if(vlist != null && vlist.size() > 0){
                XObject _50 = new XObject();
                StringBuffer indata = new StringBuffer(10000);
                indata.append("<root>");
                VOBJ _227 = null;
                for(int i=0;i<vlist.size();i++){
                    _227 = dobj.getRetObject(vlist.get(i).toString());
                    _227.setName(vlist.get(i).toString());
                    indata.append(_50.getErrLogXmlStringBuffer(_227).toString());
                }
                indata.append("</root>");
               // System.out.println("=============ERROR LOGGING INPUT DATA  ============");
               // System.out.println(indata.length());
               // System.out.println(indata);
               // System.out.println("===================================================");
                sobj.setString("INDATA", indata.toString());
            }

            QExecutor exe = new QExecutor();
            //exe.DispSelectSql(sobj);
            exe.executeUpdate(conn, sobj);
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Exception LOG WRITE ERROR");
        }
    }

    private void LogTableWrite(Connection conn,String userid, String resptime)
    {
        try{
            SQLObject sobj = new SQLObject();
            String sql ="";
            sql += " INSERT INTO PCTLLOG(SYSID,MENUID,EVENTID,INTERVAL,USERID,LOGTIME,PACKAGE,CLASS,METHOD)     \n";
            sql += " VALUES(:SYSID, :MENUID, :EVENTID, :INTERVAL, :USERID, SYSDATE, :PACKAGE, :CLASS, :METHOD)  \n";
            sobj.setSql(sql);
            sobj.setString("SYSID"   ,SYSID);
            sobj.setString("MENUID"  ,MENUID);
            sobj.setString("EVENTID" ,EVENTID);
            sobj.setString("INTERVAL",resptime);
            sobj.setString("USERID"  ,userid );
            sobj.setString("PACKAGE" ,PACKAGE);
            sobj.setString("CLASS"   ,CLASS );
            sobj.setString("METHOD"  ,METHOD);

            QExecutor exe = new QExecutor();
            exe.executeUpdate(conn, sobj);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("RESPONSE CTLLOG WRITE ERROR");
        }
    }

    public void DevLog(Connection conn, String userid, DOBJ dobj){
        System.out.println("DEVLOG WRITE;..............");

        ArrayList vlist = dobj.getRetObjectKeys();
        if(vlist != null && vlist.size() > 0) {
            if(DevLogTableUpdate(conn,userid,vlist,dobj) ==0){
                DevLogTableWrite(conn,userid,vlist,dobj);
            }
        }
    }
    private void DevLogTableWrite(Connection _conn, String _userid, ArrayList _vlist,  DOBJ _dobj)
    {
        try{
            SQLObject sobj = new SQLObject();
            String qry="";
            qry= "INSERT INTO PDEVLOG( SYSID ,MENUID ,EVENTID ,USERID ,PACKAGE  ,CLASS ,METHOD ,INDATA,LOGTIME) \n";
            qry+="            VALUES (:SYSID,:MENUID,:EVENTID,:USERID,:PACKAGE,:CLASS,:METHOD,:INDATA,SYSDATE)  \n";
            sobj.setSql(qry);
            sobj.setString("SYSID"   ,SYSID  );
            sobj.setString("MENUID"  ,MENUID );
            sobj.setString("EVENTID" ,EVENTID);
            sobj.setString("USERID"  ,_userid );
            sobj.setString("PACKAGE" ,PACKAGE);
            sobj.setString("CLASS"   ,CLASS  );
            sobj.setString("METHOD"  ,METHOD );

            if(_vlist != null && _vlist.size() > 0){
                XObject _xobj = new XObject();
                StringBuffer indata = new StringBuffer(10000);
                indata.append("<root>");
                VOBJ _vobj = null;
                for(int i=0;i<_vlist.size();i++){
                    if(_vlist.get(i).toString().equals("G")) continue;
                    if(_vlist.get(i).toString().equals("R")) continue;

                    _vobj =_dobj.getRetObject(_vlist.get(i).toString());
                    _vobj.setName(_vlist.get(i).toString());
                    indata.append(_xobj.getErrLogXmlStringBuffer(_vobj).toString());
                }
                indata.append("</root>");
                sobj.setClob("INDATA", indata.toString());
            }
            QExecutor exe = new QExecutor();
            exe.executeUpdate(_conn, sobj);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("DLOG WRITE ERROR");
        }
    }
    private int DevLogTableUpdate(Connection _conn,String _userid, ArrayList _vlist, DOBJ _dobj)
    {
        int rtn=0;
        try{
            SQLObject sobj = new SQLObject();
            String qry="";
            qry= "UPDATE PDEVLOG SET  USERID =:USERID,  \n";
            qry+="                    PACKAGE=:PACKAGE, \n";
            qry+="                    CLASS  =:CLASS,   \n";
            qry+="                    METHOD =:METHOD,  \n";
            qry+="                    INDATA =:INDATA,  \n";
            qry+="                    LOGTIME=SYSDATE  \n";
            qry+="WHERE  SYSID  =:SYSID    \n";
            qry+="AND    MENUID =:MENUID   \n";
            qry+="AND    EVENTID=:EVENTID  \n";
            sobj.setSql(qry);
            sobj.setString("SYSID"   ,SYSID  );
            sobj.setString("MENUID"  ,MENUID );
            sobj.setString("EVENTID" ,EVENTID);
            sobj.setString("USERID"  ,_userid );
            sobj.setString("PACKAGE" ,PACKAGE);
            sobj.setString("CLASS"   ,CLASS  );
            sobj.setString("METHOD"  ,METHOD );
            if(_vlist != null && _vlist.size() > 0){
                XObject _xobj = new XObject();
                StringBuffer indata = new StringBuffer(10000);
                indata.append("<root>");
                VOBJ _vobj = null;
                for(int i=0;i<_vlist.size();i++){
                    if(_vlist.get(i).toString().equals("G")) continue;
                    _vobj =_dobj.getRetObject(_vlist.get(i).toString());
                    _vobj.setName(_vlist.get(i).toString());
                    indata.append(_xobj.getErrLogXmlStringBuffer(_vobj).toString());
                }
                indata.append("</root>");
                sobj.setClob("INDATA", indata.toString());
            }
            QExecutor exe = new QExecutor();
            //exe.DispSelectSql(sobj);
            rtn=exe.executeUpdate(_conn, sobj);
        }catch(Exception e){
            e.printStackTrace();
            rtn=1;
        }
        return rtn;
    }

    private void StdWrite(String userid, String resptime)
    {
        System.out.println("#RESPONSE-TIME:" + SYSID +":" + MENUID +":" + EVENTID + ":" +PACKAGE + ":" +CLASS +  ":" +METHOD+ ":" + userid +":" + resptime );
    }

}
