package egov.wizware.com;
import java.util.*;

public class XFRLayout {
  private String    _layout = "";
  private ArrayList _clist = new ArrayList();
  private boolean   _containfilesize = false;

  public XFRLayout() {
  }
  public XFRLayout(String _str) {
    _layout = _str;
    _setColumnlayout(_str);
  }
  public ArrayList _getColumnList(){
    return _clist;
  }
  public void _setLayout(String _str){
    _layout = _str;
     _setColumnlayout(_str);
  }
  public String _getLayout(){
    return _layout;
  }
  public boolean _getContainfilesize(){
    return _containfilesize;
  }

  public void  _getLengthFormatColumnList(){
  }
  public ArrayList _getCSVColumnlist(String _str, String delim)
  {
    ArrayList       _clist = new ArrayList();
    //System.out.println(_str);
    StringTokenizer _stoken= new StringTokenizer(_str, delim);
    while(_stoken.hasMoreTokens())
    {
      _clist.add(_stoken.nextToken().trim());
    }
    /*
    for(int i=0;i<_clist.size();i++)
    {
        System.out.println("NAME:"+_clist.get(i) +":");
    }
    */

    return _clist;
  }
  public HashMap _getCVSRecord(String _recs, ArrayList _clist, char _delim){
    HashMap _rec = new HashMap();
    int _cnt=0;
    String _str="";
    char[] _tochar = _recs.toCharArray();
    for(int i=0;i<_tochar.length;i++){
      if( _tochar[i] == _delim ){
        _rec.put(_clist.get(_cnt),_str);
        _str ="";
        _cnt++;
      } else {
        _str+=_tochar[i];
      }
    }
    if(_cnt +1 == _clist.size()) {
       _rec.put(_clist.get(_cnt),_str);
    }

    return _rec;
  }

  public int _getSumDelimLength(int[] _delim)
  {
      int _rtn =0;
      for(int i=0;i<_delim.length;i++){
          _rtn += _delim[i];
      }
      return _rtn;
  }

  //byte 단윈로 절삭처리
  public HashMap _getCVSLenRecord(String _recs, ArrayList _clist, int[] _delim)
  {

    HashMap _rec = new HashMap();
    String _str="";
    byte[] _recbyte = _recs.getBytes();
    if(_clist.size() != _delim.length) return _rec;

    int _idx=0;
    for(int i=0;i<_clist.size();i++)
    {
        byte[] _colbyte = new byte[_delim[i]];
        for(int j=0;j<_colbyte.length;j++)
        {
            if(_recbyte.length <= _idx)
            {
                //System.out.println(_recbyte.length +":_getCVSLenRecord:" + _idx);
                _colbyte[j] = "".getBytes()[0];
            }
            else
            {
                _colbyte[j] = _recbyte[_idx];
            }
            _idx++;
        }
        _str = new String(_colbyte);
        _rec.put(_clist.get(i),_str);
    }
    return _rec;
  }

  public HashMap _getCVSLenRecord_bk(String _recs, ArrayList _clist, int[] _delim){
      HashMap _rec = new HashMap();
      String _str="";
      int _start=0;
      if(_clist.size() != _delim.length) return _rec;

      for(int i=0;i<_clist.size();i++)
      {
        if(_recs.length() > _delim[i]){
          _str = _recs.substring(_start,_start+_delim[i]);
          _rec.put(_clist.get(i),_str);
        }else {
          _rec.put(_clist.get(i),"");
        }
        _start +=_delim[i];
      }

      return _rec;
  }

  /*
   OBJECTID[Column명:3L0:’delim’,column:4R0:’tab’,column:10L0,column:11L1]
   Delim : TAB, 특수문자( \n, \r) 처리(newline처리) head가 여러 건 발생시
  */
 //Column명,3L0,’delim’:column,4R0,’tab’:column,10L0:column,11L1
 //&filesize()
 //column,3L0,'delim' ['\''
  private void _setColumnlayout(String _layout){

    String _columnstr="";
    String _column="";
    String _align="";
    String _delim="";
    String _charset="";

    int _scnt=0;
    int _order=0;
    boolean _first = true;
    int _delimfirst= -1;
    int _charsetfirst = -1;
    XFCLayout _xcol = null;
    char[] _tochar = _layout.toCharArray();
    for(int i=0;i<_tochar.length;i++){
      if(_first == true) _first = false;
      if(_tochar[i] == '\\'){
        _columnstr+=_tochar[i];
        i++;
        _columnstr+=_tochar[i];
        continue;
      }

      if( _tochar[i] =='\'' && _scnt != 3)
      {
          _delimfirst *= -1;
          if(_delimfirst == 1)  _scnt=2;
          continue;
      }

      if( _tochar[i] =='\'' && _scnt == 3)
      {
          continue;
      }

      if(_delimfirst==1) {
        _columnstr+=_tochar[i];
        continue;
      }

      if(_tochar[i] == ','){
        if(_scnt==0)
        {
          _column = _columnstr;
          _columnstr="";
          _scnt++;
        }else if( _scnt==1)
        {
            _align  =_columnstr;
            _columnstr="";
        }else if(_scnt ==2)
        {
            _delim  = _columnstr;
            _scnt=3;
            _columnstr="";
        }
        else if(_scnt ==3){
            _charset  = _columnstr;
            _columnstr="";
        }
        continue;
      }

      if(_tochar[i] == ':'){
        if( _scnt==1)
        {
          _align=_columnstr;
          _columnstr="";
        }
        else if(_scnt ==2)
        {
          _delim= _columnstr;
          _columnstr="";
        }
        else if(_scnt ==3){
            _charset= _columnstr;
            _columnstr="";
        }

        _xcol = new XFCLayout(_order);
        _xcol._setColumnLayout(_column, _align, _delim, _charset);
        if(_xcol._getColumntype() == 1) _containfilesize = true;
        _clist.add(_xcol);
        _order++;
        _scnt=0;
        _columnstr="";
        _column="";
        _align="";
        _delim="";
        _charset="";
        continue;
      }
      _columnstr+=_tochar[i];
    }

    if( _scnt==1)
    {
      _align=_columnstr;
      _columnstr="";
    }else if(_scnt ==2)
    {
      _delim= _columnstr;
      _columnstr="";
    }
    else if(_scnt ==3){
      _charset= _columnstr;
      _columnstr="";
    }

    _xcol = new XFCLayout(_order);
    //System.out.println(_column +":" + _align +":" +_delim);
    _xcol._setColumnLayout(_column, _align, _delim, _charset);
    if(_xcol._getColumntype() == 1) _containfilesize = true;
    _clist.add(_xcol);
  }


}
