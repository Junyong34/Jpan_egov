package egov.wizware.com;

import java.util.*;

public class XcltRcvLayout {
  private ArrayList _clist = null;
  private DOBJ _dobj = null;

  public XcltRcvLayout(DOBJ _dOBJ1x)
  {
    _dobj=_dOBJ1x;
  }

  public ArrayList _getLayoutList(){
    return _clist;
  }
  public void _setColumnLayout(String _layout)
  {
    if(_clist == null) _clist = new ArrayList();
    _layout = _layout.trim();
    String _columnstr="";
    String _column="";
    String _align="";
    String _delim="";
    String _charset="";

    int    _len=0;
    char   _calign='L';
    char  _spacechar=' ';

    int _scnt=0;
    int _order=0;
    boolean _first = true;
    int _delimfirst= -1;
    int _charsetfirst = -1;
    char[] _tochar = _layout.toCharArray();
    for(int i=0;i<_tochar.length;i++)
    {
      if(_first == true) _first = false;
      if(_tochar[i] == '\\')
      {
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

      if(_delimfirst==1)
      {
        _columnstr+=_tochar[i];
        continue;
      }

      if(_tochar[i] == ','){
        if(_scnt==0){
          _column = _columnstr;
          _columnstr="";
          _scnt++;
        }else if( _scnt==1){
          _align  = _columnstr;
          _columnstr="";
        }else if(_scnt ==2){
          _delim  = _columnstr;
          _scnt=3;
          _columnstr="";
        }else if(_scnt ==3){
          _charset  = _columnstr;
          _columnstr="";
        }

        continue;
      }

      if(_tochar[i] == ':'){
        if( _scnt==1){
          _align=_columnstr;
          _columnstr="";
        }else if(_scnt ==2){
          _delim= _columnstr;
          _columnstr="";
        }else if(_scnt ==3){
          _charset= _columnstr;
          _columnstr="";
        }

        String[] _aligns = this._getAlign(_align);
        _len = Integer.parseInt(_aligns[0]);
        if(_aligns[1].length() > 0)  _calign =_aligns[1].charAt(0);
        else _calign='L';
        if(_aligns[2].length() > 0)  _spacechar =_aligns[2].charAt(0);
        else _spacechar = ' ';
        //System.out.println("COLUMN:" + _column);
        //System.out.println("ALIGN:" + _align);
        //System.out.println("DELIM:" + _delim);
        //System.out.println("_charset:" + _charset);
        XcltLayoutObject _sobj = new XcltLayoutObject(_column.trim(), _len, _calign, _spacechar, _delim, _charset);
        _clist.add(_sobj);

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

    if( _scnt==1){
      _align=_columnstr;
      _columnstr="";
    }else if(_scnt ==2){
      _delim= _columnstr;
      _columnstr="";
    }else if(_scnt ==3){
      _charset= _columnstr;
      _columnstr="";
    }

    String[] _aligns = this._getAlign(_align);
    _len = Integer.parseInt(_aligns[0]);
    if(_aligns[1].length() > 0)  _calign =_aligns[1].charAt(0);
    else _calign='L';
    if(_aligns[2].length() > 0)  _spacechar =_aligns[2].charAt(0);
    else _spacechar = ' ';
    //System.out.println("COLUMN:" + _column);
    //System.out.println("ALIGN:" + _align);
    //System.out.println("DELIM:" + _delim);
    //System.out.println("_charset:" + _charset);
    XcltLayoutObject _sobj = new XcltLayoutObject( _column.trim(), _len, _calign, _spacechar, _delim, _charset);
    _clist.add(_sobj);
  }
  public String[] _getAlign(String _align)
  {
    _align = _align.trim();
    int _idx=0;
    String _value="";
    String[] _rtn = {"","",""};
    char[] _tochar = _align.toCharArray();
    for(int i=0;i<_tochar.length;i++)
    {
      if( (_tochar[i]=='L' || _tochar[i]=='R') && _idx!=1  ) {
        _rtn[0] = _value.trim();
        _rtn[1] += _tochar[i];
        _value="";
        _idx=1;
        continue;
      }
      _value+=_tochar[i];
    }
    _rtn[2]=_value;
    return _rtn;
  }

}
