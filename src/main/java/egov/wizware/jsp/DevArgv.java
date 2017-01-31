package egov.wizware.jsp;

import java.sql.*;
import java.util.*;
import egov.wizware.com.DOBJ;
import egov.wizware.com.VOBJ;
import javax.naming.*;
import java.io.*;
public class DevArgv
{
    private String SYSID="";
    private String MENUID="";
    private String EVENTID="";
    private String PACKAGE="";
    private String CLASS="";
    private String METHOD="";

    //---------- Meta Repository DB -------------------------------------
    private boolean saveOn = true;
    //*/-------------------------------------------------------------------
    private String dbconnstr="jdbc:oracle:thin:@192.168.0.12:1521:WIZHOME";
    private String dbdriver="oracle.jdbc.driver.OracleDriver";
    private String dbuserid="DEMO011";
    private String dbpwd="DEMO01";
    //*/

    /*/ �뱸���ð��
    private String dbconnstr="jdbc:oracle:thin:@D-DGAP:1521:ORCL";
    private String dbdriver = "oracle.jdbc.driver.OracleDriver";
    private String dbuserid = "PBSH";
    private String dbpwd = "PBSH";
    //*/

    //----------- File Upload :Temp Folder Path -------------------------
    private String uploadTempFolder ="D:/temp1/upload";
    //-------------------------------------------------------------------
    public DevArgv()
    {
    }
    public String getUploadTempFolder()
    {
        return uploadTempFolder;
    }
  /*  public void DevLog(String url, String userid, DOBJ dobj)
    {
        if(saveOn==false) return;
        Connection conn= getConnection();
        try
        {
            url = url.substring(url.lastIndexOf("/"));
            System.out.println("DEVLOG WRITE;..............(" + url +")");

            getUrlInfo(conn, url);
            ArrayList vlist = dobj.getRetObjectKeys();
            //System.out.println("size:" + vlist.size());
            if(vlist != null && vlist.size() > 0)
            {
                if(DevLogTableUpdate(conn,userid,vlist,dobj) ==0)
                {
                    DevLogTableWrite(conn,userid,vlist,dobj);
                }
            }
            conn.commit();
        }
        catch(Exception e)
        {
            try
            {
                conn.rollback();
            }catch(Exception ee)
            {
            }
        }
        finally
        {
            try
            {
                if(conn != null) conn.close();
            }catch(Exception e)
            {
            }
        }
    }
*/
    private final Connection getConnection()
    {
        java.sql.Connection _conn = null;
        try
        {
            Class.forName(dbdriver);
        }
        catch(ClassNotFoundException e)
        {
            System.out.println("Processbuilder Devlog Connection fail:Driver loading error:" + e.getMessage());
        }

    	try
    	{
            _conn = DriverManager.getConnection(dbconnstr, dbuserid, dbpwd);
            _conn.setAutoCommit(false);
    	}
    	catch(Exception ex)
    	{
            System.out.println("Processbuilder Devlog Connection fail:connection error:"+ ex.getMessage());
    	}
    	return _conn;
    }
    private void getUrlInfo(Connection _conn, String _url)
    {
    	PreparedStatement _pstat = null;
    	ResultSet _rset = null;
    	String qry="";
    	qry +="SELECT SYSID, MENUID, EVENTID, PACKAGE, CLASS, METHOD ";
    	qry += "FROM PBUILDZT ";
    	qry += "WHERE ACTION_URL= ? ";
    	qry += "AND   FILETYPE='CTL'";
    	System.out.println(_conn+":"+qry +":" + _url);
    	try
        {
            _pstat = _conn.prepareStatement(qry);
            _pstat.setString( 1, _url);
            _rset = _pstat.executeQuery();

            while (_rset.next())
            {
                SYSID  =_rset.getString("SYSID");
                MENUID =_rset.getString("MENUID");
                EVENTID=_rset.getString("EVENTID");
                PACKAGE=_rset.getString("PACKAGE");
                CLASS  =_rset.getString("CLASS");
                METHOD =_rset.getString("METHOD");
                System.out.println(SYSID +":" + MENUID +":" + EVENTID);
            }
    	}
    	catch(Exception e)
    	{
            e.printStackTrace();
    	}
    	finally
    	{
            try
            {
                  if(_rset != null) _rset.close();
                  if(_pstat != null) _pstat.close();
            }
            catch(Exception ex)
            {
            }
    	}
    }
    private void DevLogTableWrite(Connection _conn, String _userid, ArrayList _vlist,  DOBJ _dobj)
    {
        try
        {
            if(SYSID == null || SYSID.equals(""))
            {
                System.out.println("SERVICE IS NOT FOUNDED IN PROCESSBUILDER-SERVICE");
                return;
            }

            String qry="";
            qry= "INSERT INTO PDEVLOG( SYSID ,MENUID ,EVENTID ,USERID ,PACKAGE  ,CLASS ,METHOD ,INDATA,LOGTIME) \n";
            qry+="            VALUES (?, ?, ?, ?, ?, ?, ?, ? ,SYSDATE)  \n";

            StringBuffer indata = new StringBuffer(10000);
            if(_vlist != null && _vlist.size() > 0)
            {
                indata.append("<root>");
                VOBJ _vobj = null;
                for(int i=0;i<_vlist.size();i++){
                    if(_vlist.get(i).toString().equals("G")) continue;
                    if(_vlist.get(i).toString().equals("R")) continue;

                    _vobj =_dobj.getRetObject(_vlist.get(i).toString());
                    _vobj.setName(_vlist.get(i).toString());
                    indata.append(getErrLogXmlStringBuffer(_vobj).toString());
                }
                indata.append("</root>");
            }

            PreparedStatement _pstat = null;
            try
            {
                int idx =0;
                _pstat = _conn.prepareStatement(qry);
                _pstat.setString(1, SYSID);
                _pstat.setString(2, MENUID);
                _pstat.setString(3, EVENTID);
                _pstat.setString(4, _userid);
                _pstat.setString(5, PACKAGE);
                _pstat.setString(6, CLASS);
                _pstat.setString(7, METHOD);
                _pstat.setCharacterStream(8, new StringReader(indata.toString()),indata.toString().length());
                System.out.println("insert count:"+_pstat.executeUpdate());

            }
            finally
            {
                if(_pstat != null)_pstat.close();
            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("DLOG WRITE ERROR");
        }
    }
    private int DevLogTableUpdate(Connection _conn,String _userid, ArrayList _vlist, DOBJ _dobj)
    {
        int rtn=0;
        try
        {
            String qry="";
            qry= "UPDATE PDEVLOG SET  USERID =?,   \n";
            qry+="                    PACKAGE=?,   \n";
            qry+="                    CLASS  =?,   \n";
            qry+="                    METHOD =?,   \n";
            qry+="                    INDATA =?,   \n";
            qry+="                    LOGTIME=SYSDATE  \n";
            qry+="WHERE  SYSID  =?   \n";
            qry+="AND    MENUID =?   \n";
            qry+="AND    EVENTID=?   \n";

            StringBuffer indata = new StringBuffer(10000);
            if(_vlist != null && _vlist.size() > 0)
            {
                indata.append("<root>");
                VOBJ _vobj = null;
                for(int i=0;i<_vlist.size();i++){
                    if(_vlist.get(i).toString().equals("G")) continue;
                    _vobj =_dobj.getRetObject(_vlist.get(i).toString());
                    _vobj.setName(_vlist.get(i).toString());
                    indata.append(getErrLogXmlStringBuffer(_vobj).toString());
                }
                indata.append("</root>");
            }

            PreparedStatement _pstat = null;
            try
            {
                int idx =0;
                _pstat = _conn.prepareStatement(qry);
                _pstat.setString(1, _userid);
                _pstat.setString(2, PACKAGE);
                _pstat.setString(3, CLASS);
                _pstat.setString(4, METHOD);
                _pstat.setCharacterStream(5, new StringReader(indata.toString()),indata.toString().length());
                _pstat.setString(6, SYSID);
                _pstat.setString(7, MENUID);
                _pstat.setString(8, EVENTID);

                rtn = _pstat.executeUpdate();
                //System.out.println("update count:"+_pstat.executeUpdate());
            }
            finally
            {
                if(_pstat != null)_pstat.close();
            }
        }catch(Exception e)
        {
            e.printStackTrace();
            rtn=1;
        }
        return rtn;
    }

    private int executeUpdate(Connection _conn ,String _qry,  ArrayList _alist ) throws Exception
    {
        PreparedStatement _pstat = null;
        int rtn=0 ;
        try
        {
            int idx =0;
            _pstat = _conn.prepareStatement(_qry);
            for(int i=0;i<_alist.size(); i++)
            {

            }
            _pstat.setString(1, "value");


            rtn = _pstat.executeUpdate();
        }
        finally
        {
            if(_pstat != null)_pstat.close();
        }
        return rtn;
    }

    private StringBuffer getErrLogXmlStringBuffer(VOBJ _vobj)
    {
        StringBuffer _sbuf = new StringBuffer(4000);
        ArrayList _alist =null;
        if(!_vobj.isEmpty())
        	_alist = _vobj.getColumnNames();

        if(_alist != null){
        	_sbuf.append("<DATASET name=\""+_vobj.getName()+"\" count=\""+_vobj.getRecordCnt()+"\">\n");
        	_vobj.first();
            while(_vobj.next())
            {
            	_sbuf.append("<REC>\n");
                for(int i=0;i<_alist.size();i++)
                {
                    //_2279.append("<"+_7475.get(i)+"><![CDATA[");
                	_sbuf.append("<"+_alist.get(i)+">");
                	_sbuf.append(_vobj.getRecord().get(_alist.get(i).toString()));
                	_sbuf.append("</"+_alist.get(i)+">\n");
                    //_2279.append("]]</"+_7475.get(i)+">\n");
                }
                _sbuf.append("</REC>\n");
            }
            _sbuf.append("</DATASET>\n");
        }
        return _sbuf;
    }

}
