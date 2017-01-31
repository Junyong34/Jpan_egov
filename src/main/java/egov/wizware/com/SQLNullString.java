package egov.wizware.com;

public class SQLNullString {
  private int  _no=0;
  private String _val="";

  public SQLNullString()
  {
  }

  public SQLNullString(int _nox, String _strx)
  {
    _no = _nox;
    _val =  _strx;
  }
  public int getNo()
  {
    return _no;
  }
  public String getValue()
  {
    return _val;
  }

}
