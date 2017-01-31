package egov.wizware.util;

import java.io.*;
import java.util.*;
import egov.wizware.com.*;
public class CPrc {

  private DOBJ _dobj = null;
  public CPrc(DOBJ _dobjx)
  {
    _dobj = _dobjx;
  }
  public CPrc(){}

  public String[] processing(String[] _args, String _keywords) throws Exception
  {

    String _line="";
    Process _process = new ProcessBuilder(_args).start();
    InputStream _is  = _process.getInputStream();
    InputStreamReader _isr = new InputStreamReader(_is);
    BufferedReader _br = new BufferedReader(_isr);
    ArrayList _kwords  = _getStringToken(_keywords);

    StringBuffer _sbuf = new StringBuffer(1000);
    while ((_line = _br.readLine()) != null)
    {
      _sbuf.append(_line +"\n");
    }

    boolean _aa = _finded(_sbuf.toString() ,  _kwords);
    String[] _rtn = new String[2];
    if(_aa == true) _rtn[0] ="1";
    else  _rtn[0] ="0";
    _rtn[1] = _sbuf.toString();

    return _rtn;

  }

  private boolean _finded(String _instr, ArrayList _kwords)
  {
    _LogCmd _rcmd = new _LogCmd();
    boolean _rtn =false;
    String _word="";
    int _left=0;
    ArrayList _klist = new ArrayList();
    for(int i=0;i<_kwords.size();i++)
    {
      _word = _kwords.get(i).toString();
      if(_word.trim().equals("(")){
        _left++;
        continue;
      }
      if(_word.trim().equals(")")){
        _left--;
        if(_left ==0 ) {
          _rtn =_finded( _instr,  _klist);
          _rcmd._setValue(_rtn);
        }else {
          continue;
        }
      }
      if(_left!=0){
        _klist.add(_word);
        continue;
      }

      if(_word.trim().equals("||")){
        _rcmd._setOper(1);
        continue;
      }
      if(_word.trim().equals("&&")){
        _rcmd._setOper(2);
        continue;
      }

      if(_instr.indexOf(_word)!=-1) {
        _rcmd._setValue(true);
      }else {
        _rcmd._setValue(false);
      }
    }
    return _rcmd._getLeft();
  }

  private ArrayList _getStringToken(String _keywords){
    _keywords = _keywords.trim();
    int _strindex =0;
    boolean _skip = false;
    ArrayList _alist = new ArrayList();
    String _kword ="";
    char[] _tochar = _keywords.toCharArray();
    for(int i=0;i<_tochar.length;i++){

      if(_tochar[i] == '\\')
      {
        if(i+1 < _tochar.length) {
          i++;
          _kword+=_tochar[i];
          continue;
        }
      }

      if(_tochar[i] == '"')
      {
        _strindex++;
        if(_strindex%2 == 0)
        {
          if(!_kword.trim().equals(""))
            _alist.add(_kword);
          _kword="";
          continue;
        }else {
          continue;
        }
      }

      if(_strindex%2 != 0)
      {
        _kword+=_tochar[i];
        continue;
      }

      if(_tochar[i] == ' ') continue;
      if(_tochar[i] == '(')
      {
        if(!_kword.trim().equals(""))
          _alist.add(_kword);
        _alist.add("(");
        _kword="";
        continue;
      }

      if(_tochar[i] == ')')
      {
        if(!_kword.trim().equals(""))
          _alist.add(_kword);
        _alist.add(")");
        _kword="";
        continue;
      }

      if(_tochar[i] == '|')
      {
        if(_isOR(_tochar,i) == true)
        {
          if(!_kword.trim().equals(""))
            _alist.add(_kword);
          _alist.add("||");
          _kword="";
          i++;
          continue;
        }
      }

      if(_tochar[i] == '&')
      {
        if(_isAND(_tochar,i) == true){
          if(!_kword.trim().equals(""))
            _alist.add(_kword);
          _alist.add("&&");
          _kword="";
          i++;
          continue;
        }
      }

      _kword+=_tochar[i];
    }

    if(!_kword.trim().equals("")){
      _alist.add(_kword);
    }

    //for(int i=0;i<_alist.size();i++){
    //  System.out.println("kworklist :" + _alist.get(i));
    //}

    return _alist;
  }

  private boolean _isOR(char[] _tochar, int _idx){
    if(_idx+1 < _tochar.length ) {
      if(_tochar[_idx+1] == '|') {
        return true;
      }
    }
    return false;
  }

  private boolean  _isAND(char[] _tochar, int _idx){
    if(_idx+1 < _tochar.length ) {
      if(_tochar[_idx+1] == '&') {
        return true;
      }
    }
    return false;
  }

}


