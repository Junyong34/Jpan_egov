package egov.wizware.util;

public class _LogCmd {
  private boolean _left=false;
  private int     _oper =0;   // 1: or , 2:and
  private boolean _right = false;
  private int     _setflag = 0;
  public _LogCmd(){}
  public _LogCmd(boolean _leftx){
  }

  public void _setValue(boolean _val){
    if(_setflag ==0){
      _left = _val;
      _setflag = 1;
    }else if(_setflag ==1){    // right set시에 계산 결과를 자동으로 처리 한다.
      _right = _val;
      _setResult();
    }
  }
  public void _setLeft(boolean _leftx){
    _left = _leftx;
  }
  public boolean _getLeft(){
    return _left;
  }

  public void _setOper(int _operx){
    System.out.println("oper:"+_operx);
    _oper = _operx;
  }
  public int _getOper(){
    return _oper;
  }

  public void _setRight(boolean _nowx){
    _right = _nowx;
  }
  public boolean _getRight(){
    return _right;
  }

  public boolean _getResult(){
    boolean _rtn=false;
    switch(_oper){
      case 1:
        _rtn = _left || _right;
        break;
      case 2:
        _rtn = _left && _right;
        break;
      case 0:
        _rtn = _left;
        break;
    }
    return _rtn;
  }

  public void _setResult(){
    boolean _rtn=false;
    switch(_oper){
      case 1:
        _rtn = _left || _right;
        break;
      case 2:
        _rtn = _left && _right;
        break;
      case 0:
        _rtn = _left;
        break;
    }
    _oper=0;
    _left = _rtn;
    _setflag =1;
  }

}





