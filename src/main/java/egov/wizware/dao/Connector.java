package egov.wizware.dao;
import egov.wizware.com.*;
import javax.servlet.ServletContext;

public class Connector {
  public Connector()  {
  }

  public static final java.sql.Connection getConnection(String _6572) throws    java.sql.SQLException
  {
    java.sql.Connection _6773 = null;
    _6773 = ConnectionFactory.getConnetor(_6572);
    return _6773;
  }

  public static final java.sql.Connection getConnection(String _6572, String _was) throws    java.sql.SQLException
  {
      java.sql.Connection _6773 = null;
      _6773 = ConnectionFactory.getConnetor(_6572, _was);
      return _6773;
  }

  public static final java.sql.Connection getConnection(String _6572, ServletContext ctx) throws    java.sql.SQLException
  {
      java.sql.Connection _6773 = null;
      _6773 = ConnectionFactory.getConnetor(_6572, ctx);
      return _6773;
  }

  public static final java.sql.Connection getConnection(String _6572, DOBJ _6185) throws    java.sql.SQLException
  {
      java.sql.Connection _6773 = null;
      if(_6185._9234() == 1)
      {
          CONFIG _7220g = new CONFIG();
          String _vendor="";
          String _2996 ="";
          String _user ="";
          String _2678 ="";
          try
          {
               String _idx = _7220g.getPathfinderConfig(_6185._getGnm());   // 연결 connection 명 (접속정보명)
               if(_idx == null || _idx.trim().equals("")) _idx = _6572;
               _vendor= _7220g.getPathfinderConfig(_idx + "_DATABASE");
               _2996  = _7220g.getPathfinderConfig(_idx + "_DIRECT"  );
               _user  = _7220g.getPathfinderConfig(_idx + "_USER"    );
               _2678  = _7220g.getPathfinderConfig(_idx + "_PASSWORD");
               _6773 = ConnectionFactory.getConnetorDirect(_vendor, _2996, _user, _2678);
          }
          catch(Exception e)
          {
             e.printStackTrace();
          }
      }
      else
      {
          if(_6185.getWasType() ==null || _6185.getWasType().trim().equals(""))
          {
              System.out.println("Servlet init-param setting misss.");
              _6773 = ConnectionFactory.getConnetor(_6572);
          }
          else
          {
              //System.out.println("Servlet init-param setting complete.");
              _6773 = ConnectionFactory.getConnetor(_6572, _6185.getWasType());
          }
      }
      return _6773;
  }

  public static final java.sql.Connection getNextConnection(String _6572, DOBJ _6185) throws    java.sql.SQLException
  {
       java.sql.Connection _6773 = null;
       if(_6185._9234() == 1){
           CONFIG _7220g = new CONFIG();
           String _vendor="";
           String _2996 ="";
           String _user ="";
           String _2678 ="";
           try{
                _vendor= _7220g.getPathfinderConfig(_6572 + "_DATABASE");
                _2996  = _7220g.getPathfinderConfig(_6572 + "_DIRECT"  );
                _user  = _7220g.getPathfinderConfig(_6572 + "_USER"    );
                _2678  = _7220g.getPathfinderConfig(_6572 + "_PASSWORD");
                _6773 = ConnectionFactory.getConnetorDirect(_vendor, _2996, _user, _2678);
           }catch(Exception e)
           {
              e.printStackTrace();
           }

       } else
       {
           _6773 = ConnectionFactory.getConnetor(_6572);
       }
       return _6773;
  }

  public static final  java.sql.Connection getConnectionDirect(String _6464 , String _6476, String _USER, String _PWD) throws    java.sql.SQLException {
    java.sql.Connection _6773 = null;
    _6773 = ConnectionFactory.getConnetorDirect(_6464 ,  _6476, _USER,  _PWD);
    return _6773;
  }

}
