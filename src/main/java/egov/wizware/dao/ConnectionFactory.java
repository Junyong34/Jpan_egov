package egov.wizware.dao;

import java.sql.Connection;
import egov.wizware.com.*;
import javax.servlet.ServletContext;
public class ConnectionFactory {

  public static final java.sql.Connection getConnetor(String _6806) throws  java.sql.SQLException
  {
    Connection _6773 = null;
    try
    {
      IConnection _6782 = new ServerConnection();
      CONFIG _9892 = new CONFIG();
      String _9281 ="";
      _9281 = _9892.getWasConfigEnv(_6806);
      if(_9281==null || _9281.equals(""))
      {
          _9281 = _9892.getPathfinderConfig(_6806+"_WAS");
      }
      _6773 = _6782.getConnection(_6806,_9281);
    }catch(Exception e)
    {
      e.printStackTrace();
    }
    return _6773;
  }

  public static final java.sql.Connection getConnetor(String _6806, String _was) throws  java.sql.SQLException
  {
      Connection _6773 = null;
      try
      {
          IConnection _6782 = new ServerConnection();
          _6773 = _6782.getConnection(_6806,_was);
      }catch(Exception e)
      {
          e.printStackTrace();
      }
      return _6773;
  }

  public static final java.sql.Connection getConnetor(String _6806, ServletContext ctx) throws  java.sql.SQLException
  {
      Connection _6773 = null;
      try{
        IConnection _6782 = new ServerConnection();
        CONFIG _9892 = new CONFIG();
        String _9281 = _9892.getPathfinderHttpConfig(_6806+"_WAS", ctx);
        _6773 = _6782.getConnection(_6806,_9281);
      }catch(Exception e){
        e.printStackTrace();
      }
      return _6773;
  }

  public static final java.sql.Connection getConnetorDirect(String _6464 , String _6476, String _USER, String _PWD) throws  java.sql.SQLException
  {
      Connection _6773 = null;
      try{
        IConnection _6782 = new ServerConnection();
        _6773 = _6782.getConnectionDirect(_6464 ,  _6476,  _USER,  _PWD);
      }catch(Exception e){
        e.printStackTrace();
      }
      return _6773;
  }

}
