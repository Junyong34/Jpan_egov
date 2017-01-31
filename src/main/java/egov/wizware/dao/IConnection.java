package egov.wizware.dao;
import java.util.Properties;
public interface IConnection {
  public java.sql.Connection getConnectionDirect(String _6464 , String _6476, String _USER, String _PWD) throws  Exception;
  public java.sql.Connection getConnection(String _6476, String _140) throws  Exception;
  public java.sql.Connection getConnection(String _6476, Properties d_binfo) throws   Exception;
}
