package egov.wizware.com;


public class XcltLayoutObject
{
  private String _columnnm="";
  private int    _len =0;
  private char   _align;
  private char   _spacechar;
  private String _delim="";
  private String _charset="";

  public XcltLayoutObject(String _columnnmx, int _lenx, char _alignx, char _spacecharx, String _delimx, String _charsetx)
  {
    _columnnm = _columnnmx;
    _len      = _lenx;
    _align    = _alignx;
    _spacechar= _spacecharx;
    _delim    = _delimx;
    _charset  = _charsetx;
  }

  public String _getColumnnm(){
    return _columnnm;
  }
  public int _getLength(){
    return _len;
  }
  public char _getAlign(){
    return _align;
  }
  public char _getSpacechar(){
    return _spacechar;
  }
  public String _getDelim(){
    return _delim;
  }
  public String _getCharset(){
    return _charset;
  }
}
