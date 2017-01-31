package egov.wizware.dao;

import java.sql.*;
import java.util.*;
import javax.sql.*;
import javax.naming.*;
import java.util.Properties;

public final class ServerConnection  implements IConnection
{

  public ServerConnection()
  {
  }

  public final java.sql.Connection getConnectionDirect (String _6464 , String _6476, String _USER, String _PWD) throws   java.sql.SQLException
  {
      java.sql.Connection _6773 = null;
      String _6158 = "oracle.jdbc.driver.OracleDriver";
      if(_6464.equals("oracle")){
          _6158 = "oracle.jdbc.driver.OracleDriver";
      }else if(_6464.equals("informix")){
          _6158 = "com.informix.jdbc.IfxDriver";
      }else if(_6464.equals("db2")){
          _6158 = "COM.ibm.db2.jdbc.app.DB2Driver";  //com.ibm.db2.jcc.DB2Driver  COM.ibm.db2.jdbc.app.DB2Driver
      }else if(_6464.equals("as400")){
          _6158 = "com.ibm.as400.access.AS400JDBCDriver";
      }else if(_6464.equals("unisql")){
          _6158 = "unisql.jdbc.driver.UniSQLDriver";
      }else if(_6464.equals("mssql")){
          _6158 = "com.microsoft.jdbc.sqlserver.SQLServerDriver";
      }else if(_6464.equals("mysql")){
          _6158 = "com.mysql.jdbc.Driver";
      }else if(_6464.equals("sybase")){
          _6158 = "com.sybase.jdbc2.jdbc.SybDriver";
      }else if(_6464.equals("tibero")){
          _6158 = "com.tmax.tibero.jdbc.TbDriver";
      }

      try
      {
          Class.forName(_6158);
      }
      catch(ClassNotFoundException e)
      {
          e.printStackTrace();
      }
      try {
          _6773 = DriverManager.getConnection(_6476, _USER, _PWD);
      }catch(Exception e) {
          System.out.println("Database Driver name:" + _6158);
          System.out.println("Database Type:" + _6464);
          System.out.println("Database Conntion URL:" + _6476);
          throw new java.sql.SQLException(":Database에 접속할수 없습니다.(접속정보 확인 필요:" + _6476 +":" + _USER +":" + _PWD +")");
      }
      return _6773;
  }

  public final java.sql.Connection getConnection(String _329 ,String _140) throws    Exception {
    java.sql.Connection _6773 = null;
    javax.sql.DataSource ds = null;

    if(_140.equals("jeus")){
        _6773 = _getConnectionJeus(_329);

    }else if(_140.equals("weblogic")){
      _6773 = getConnectionWeblogic(_329);

    }else if(_140.equals("interstage")){
        _6773 = getConnectionInterstageX(_329);

    }else if(_140.equals("tomcat")){
        _6773 = getConnectionTomcat(_329);

    }
    else if(_140.equals("jboss"))
    {
        _6773 = getConnectionJBossKdn(_329);

    }
    else if(_140.equals("jbossa"))
    {
        _6773 = getConnectionJBoss(_329);

    }
    else if(_140.equals("oc4j"))
    {
        _6773 = _getConnectionOc4j(_329);
    }else
    {
        System.out.println(_140 +":=======CONNECTION WAS TYPE ERR======:" + _329);
    }

    if(_6773 == null)
    {
        String _url = getDatabaseLookUpUrl(_329);
        if(_url!=null || !_url.trim().equals(""))
        {
            _6773 = _getConnectionUrl(_url);
            if(_6773==null)
            {
                System.out.println("Lookup:PROCESSBUILDER_DB_"+ _329 +"="+ _url +":" + _6773);
            }
        }
    }
    //System.out.println(_140 +":=======CONNECTION======:" + _6773);
    return _6773;
  }

  private String getDatabaseLookUpUrl(String _id) throws Exception  //lookup url을 직접환경파일에서 가져오기
  {
      String _rtn ="";
      _rtn=System.getenv("PROCESSBUILDER_DB_" + _id);
      return _rtn;
  }
  public final java.sql.Connection getConnection(String _326, Properties _6482) throws   java.sql.SQLException {
    java.sql.Connection _6773 = null;
    //net.sourceforge.jtds.jdbc.Driver _6158 = new net.sourceforge.jtds.jdbc.Driver();
    //_6773 = _6158.connect(_326,_6482);
    return _6773;
  }

  public final java.sql.Connection _getConnectionUrl(String _326 ) throws    java.sql.SQLException
  {
      Context ctx = null;
      javax.sql.DataSource _ds = null;
      java.sql.Connection _6773 = null;
      try {
          ctx = new InitialContext();
          _ds = (javax.sql.DataSource) ctx.lookup(_326);
          _6773 = _ds.getConnection();
      }
      catch( Exception e )
      {
          e.printStackTrace();
      }
      return _6773;
  }

//-------------------*********************************************----------------------------------------------------
  public final java.sql.Connection getConnectionTomcat(String _323 ) throws     java.sql.SQLException {
    //System.out.println("DATABASE CONNECTION NAME:"+URLZ);
    javax.sql.DataSource ds = null;
    java.sql.Connection _6773 = null;

    try
    {
        Context initCtx = new InitialContext();
        Context envCtx = (Context) initCtx.lookup("java:comp/env");
        ds = (javax.sql.DataSource) envCtx.lookup(_323);
    }catch(NamingException e)
    {
      e.printStackTrace();
    }
    try {
      _6773 = ds.getConnection();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return _6773;
  }

  public final java.sql.Connection _getConnectionJboss(String _326 ) throws    java.sql.SQLException
  {
      Context ctx = null;
      javax.sql.DataSource _ds = null;
      java.sql.Connection _6773 = null;
      try {
          ctx = new InitialContext();
          _ds = (javax.sql.DataSource) ctx.lookup("java:/"+_326);  // 수정 2007.10.11
          _6773 = _ds.getConnection();
       }catch( Exception e ) {
           e.printStackTrace();
       }
       return _6773;
   }

   public final java.sql.Connection getConnectionJBoss(String _323 ) throws     java.sql.SQLException
   {
       javax.sql.DataSource ds = null;
       java.sql.Connection conn = null;

       try
       {
           Context ctx = new InitialContext();
           ds = (DataSource)ctx.lookup("java:comp/env/"+_323);
       }catch(NamingException e){
           e.printStackTrace();
       }

       try
       {
           conn = ds.getConnection();
       }
       catch (Exception e)
       {
           e.printStackTrace();
       }

       return conn;
   }

   public final java.sql.Connection getConnectionJBossType1(String _323 ) throws     java.sql.SQLException
   {
       javax.sql.DataSource ds = null;
       java.sql.Connection conn = null;
       try
       {
           Context initCtx = new InitialContext();
           Context envCtx = (Context) initCtx.lookup("java:comp/env");
           ds = (DataSource) envCtx.lookup("jdbc/" + _323);
       }
       catch(NamingException e)
       {
           e.printStackTrace();
       }

       try
       {
           conn = ds.getConnection();
       }
       catch (Exception e)
       {
           e.printStackTrace();
       }
       return conn;
   }
   public final java.sql.Connection getConnectionJBossKdn(String _323 ) throws     java.sql.SQLException
   {
          javax.sql.DataSource ds = null;
          java.sql.Connection conn = null;
          try
          {
              Context initCtx = new InitialContext();
               ds = (DataSource) initCtx.lookup("java:"+ _323);
               System.out.println("jboss:jndi:lookup:"+ "java:"+ _323);
           }
          catch(NamingException e)
          {
              e.printStackTrace();
          }

          try
          {
              conn = ds.getConnection();
          }
          catch (Exception e)
          {
              e.printStackTrace();
          }
          return conn;
      }


  public final java.sql.Connection _getConnectionOc4j(String name ) throws    java.sql.SQLException {
      Context ctx = null;
      javax.sql.DataSource _ds = null;
      java.sql.Connection _6773 = null;
      try {
        ctx = new InitialContext();
        _ds = (javax.sql.DataSource) ctx.lookup("jdbc/"+name);
        _6773 = _ds.getConnection();
      }catch( Exception e ) {
        e.printStackTrace();
      }
      return _6773;
  }


  public final java.sql.Connection getConnectionWeblogic(String _323) throws   Exception {
      Context ctx = null;
      javax.sql.DataSource _ds = null;
      java.sql.Connection _6773 = null;
      try {
        ctx = new InitialContext();
        _ds = (javax.sql.DataSource) ctx.lookup("jdbc/"+_323);
        _6773 = _ds.getConnection();
      }catch( Exception e ) {
        e.printStackTrace();
      }
      return _6773;
  }

  public final java.sql.Connection _getConnectionJeus(String _326 ) throws    java.sql.SQLException {
      Context ctx = null;
      javax.sql.DataSource _ds = null;
      java.sql.Connection _6773 = null;
      try {
        ctx = new InitialContext();
        _ds = (javax.sql.DataSource) ctx.lookup(_326);
        _6773 = _ds.getConnection();
      }
      catch( Exception e )
      {
        e.printStackTrace();
      }
      return _6773;
  }



  public final java.sql.Connection getConnectionInterstageX(String _323 ) throws     java.sql.SQLException {
    javax.sql.DataSource ds = null;
    java.sql.Connection _6773 = null;
    String connString ="java:comp/env/jdbc/"+_323;

    try{
      InitialContext ctx= new InitialContext();
      ds = (javax.sql.DataSource)ctx.lookup(connString);
    }catch(NamingException e){
      e.printStackTrace();
    }

    try {
      _6773 = ds.getConnection();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return _6773;
  }

  public final java.sql.Connection getConnectionWeblogic_bkx(String _323) throws   Exception {
    java.sql.Connection _6773 = null;
    javax.sql.DataSource ds = null;

    try {
      Driver myDriver = (Driver) Class.forName("weblogic.jdbc.pool.Driver").newInstance();
      _6773 = myDriver.connect("jdbc:weblogic:pool:" + _323, null);
    } catch (Exception e) {
      throw new Exception("DataBase URL LOOK Up 오류 (" + _323 + ")");
    }

    if (_6773 == null) {
      throw new Exception("get Connection 오류");
    }
    return _6773;
  }

  public final java.sql.Connection getConnectionWeblogic_bk(String _323) throws   Exception {
    java.sql.Connection _6773 = null;
    javax.sql.DataSource ds = null;

    try {
      Driver myDriver = (Driver) Class.forName("weblogic.jdbc.pool.Driver").newInstance();
      _6773 = myDriver.connect("jdbc:weblogic:pool:" + _323, null);
    } catch (Exception e) {
      throw new Exception("DataBase URL LOOK Up 오류 (" + _323 + ")");
    }

    if (_6773 == null) {
      throw new Exception("get Connection 오류");
    }
    return _6773;
  }



}
