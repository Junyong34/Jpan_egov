package egov.wizware.com;

import java.util.ArrayList;
import java.math.BigDecimal;
import java.sql.*;



public class SQLObject extends CommonTypes
{

  private String    _4271sql  ="";
  private String    _6596 = "";
  private ArrayList _6953 = new ArrayList();
  private ArrayList _2669=null;

  public ArrayList getQueryColumnnames(){
    return this._2669;
  }
  public ArrayList getColumninfo() {
    return this._6953;
  }
  public String getCvtsql() {
    return this._6596;
  }
  public String getInsql(){
    return this._4271sql;
  }

  public void setDbfunc(String _7112,String _6263) {
    String _737 = "", _632 = "";
    String _2468sql = this._6596;
    while (_2468sql.indexOf(_7112) != -1) {
      _737 = _2468sql.substring(0, _2468sql.indexOf(_7112));
      _632 = _2468sql.substring(_2468sql.indexOf(_7112) + _7112.length());
      _2468sql = _737 + _6263 + _632;
    }
    _6596 = _2468sql;
  }

  public void setSqlX(String _1178)
  {
    this._4271sql = _1178;
    SQLParam _3023 = null;
    String _737 = "", _632 = "", _578 = "";
    String _3374 = "";

    this._2669 = new ArrayList();
    while (_1178.indexOf(":") != -1) {

      _737 = _1178.substring(0, _1178.indexOf(":"));
      _632 = Ltrim(_1178.substring(_1178.indexOf(":") + 1));

      if(this.getSqlDelimPoint(_632) != -1){
        _3374 = _632.substring(0, this.getSqlDelimPoint(_632));
        _578 = Ltrim(_632.substring(  this.getSqlDelimNextPoint(_632) + 1));
      }else{
        _3374 = _632;
        _578 ="";
      }

      _3023 = new SQLParam();
      _3023.setColumnname(_3374);
      this._2669.add(_3374);

      _1178 = _737 + " ? " + _578;
      _6953.add(_3023);
    }
    _6596 = _1178;
  }

  public void setSql(String _1178)
  {
      SQLParam _3023 = null;
      this._2669 = new ArrayList();
      this._4271sql = _1178;
      String _608="";
      String _7028="";
      boolean _3338tart=false;
      char[] _7650sql = _1178.toCharArray();
      int _7100=0;
      for(int i=0;i<_7650sql.length;i++){
        if(_7650sql[i]=='\''){    // ' 를 만나면 sql에 추가 하고 continue
          if((_7100%2) ==0 ) _7100++;
          else _7100--;
          _608+=_7650sql[i];
          continue;
        }

        if(_7650sql[i]==':'){
          if(_7100==0) {
            _3338tart =true;
            continue;
          }
        }

        if(_3338tart== true)
        {
            _7028 = _7028.toUpperCase();
            if( (!_7028.equals("") && _7650sql[i]==' ') || _7650sql[i]==',' || _7650sql[i]==')' ||
                _7650sql[i]=='(' || _7650sql[i]=='%' || _7650sql[i]=='|' || _7650sql[i]=='*' ||
                _7650sql[i]=='&' || _7650sql[i]=='/' || _7650sql[i]=='=' ||
                _7650sql[i]=='<' || _7650sql[i]=='>' || _7650sql[i]=='?')
            {
                _3338tart=false;
                this._2669.add(_7028);
                _608+=" ? ";
                _608+=_7650sql[i];

                _3023 = new SQLParam();
                _3023.setColumnname(_7028);
                _6953.add(_3023);
                _7028="";
                continue;
            }
            else
            {
                if(_7650sql[i]==' ') continue;
                _7028+=_7650sql[i];
            }

        }
        else
        {
            _608+=_7650sql[i];
        }

    }

    if(_3338tart==true){
        _7028 = _7028.toUpperCase();
        if(!_7028.equals("")) this._2669.add(_7028);
        _608+=" ? ";
        _3023 = new SQLParam();
        _3023.setColumnname(_7028);
        _6953.add(_3023);
    }
    _6596 = _608;

    //  System.out.println("CVTSQL:" + _6596);
    //  for( int i=0;i<this._2669.size();i++){
    //    System.out.println("PARM_NAME:"+_2669.get(i));
    //  }

  }


  public void setInt(String _3374, int _281)
  {
    SQLParam _818 = null;
    int _3605 = 0;
    _3605 = _6953.size();

    for (int i = 0; i < _3605; i++) {
      _818 = (SQLParam) _6953.get(i);

      if (_818.getColumnname().equals(_3374)) {
        _818.setInt(_281);
        _818.setColumnType(INTEGER);
        _6953.set(i, _818);
      }
    }
  }

  public void setString(String _3374, String _281)
  {
    SQLParam _818 = null;
    int _3605 = 0;
    _3605 = _6953.size();

    for (int i = 0; i < _3605; i++) {
      _818 = null;
      _818 = (SQLParam) _6953.get(i);
      if (_818.getColumnname().trim().equals(_3374.trim())) {
        _818.setString(_281);
        _818.setColumnType(STRING);
        _6953.set(i, _818);
      }
    }
  }

  public void setNull(String _3374, String _281)
  {
    SQLParam _818 = null;
    int _3605 = 0;
    _3605 = _6953.size();

    for (int i = 0; i < _3605; i++) {
      _818 = null;
      _818 = (SQLParam) _6953.get(i);
      if (_818.getColumnname().trim().equals(_3374.trim())) {
        _818.setString(_281);
        _818.setColumnType(NULL);
        _6953.set(i, _818);
      }
    }
  }



  public void setClob(String _3374, String _281) {
       SQLParam _818 = null;
       int _3605 = 0;
       _3605 = _6953.size();
       for (int i = 0; i < _3605; i++) {
           _818 = null;
           _818 = (SQLParam) _6953.get(i);
           if (_818.getColumnname().trim().equals(_3374.trim())) {
               _818.setString(_281);
               _818.setColumnType(CLOB);
               _6953.set(i, _818);
           }
       }
   }

  public void setClobX(String _3374, byte[] _281) {
      SQLParam _818 = null;
      int _3605 = 0;
      _3605 = _6953.size();
      for (int i = 0; i < _3605; i++) {
          _818 = (SQLParam) _6953.get(i);
          if (_818.getColumnname().equals(_3374)) {
              _818.setClob(_281);
              _818.setColumnType(CLOB);
              _6953.set(i, _818);
          }
      }
  }




  public void setDouble(String _3374, double _281) {
    _3374 = _3374.toUpperCase();
    SQLParam _818 = null;
    int _3605 = 0;
    _3605 = _6953.size();

    for (int i = 0; i < _3605; i++) {
      _818 = (SQLParam) _6953.get(i);
      if (_818.getColumnname().equals(_3374)) {
        _818.setDouble(_281);
        _818.setColumnType(NUMBER);
        _6953.set(i, _818);
      }
    }
  }

  public void setLong(String _3374, long _281) {

    SQLParam _818 = null;
    int _3605 = 0;
    _3605 = _6953.size();

    for (int i = 0; i < _3605; i++) {
      _818 = (SQLParam) _6953.get(i);
      if (_818.getColumnname().equals(_3374)) {
        _818.setLong(_281);
        _818.setColumnType(LONG);
        _6953.set(i, _818);
      }
    }
  }

  public void setBlob(String _3374, byte[] _281) {

    SQLParam _818 = null;
    int _3605 = 0;
    _3605 = _6953.size();

    for (int i = 0; i < _3605; i++) {
      _818 = (SQLParam) _6953.get(i);
      if (_818.getColumnname().equals(_3374)) {
        _818.setBlob(_281);
        _818.setColumnType(BLOB);
        _6953.set(i, _818);
      }
    }
  }



  public void setBigDecimal(String _3374, BigDecimal _281) {

    SQLParam _818 = null;
    int _3605 = 0;
    _3605 = _6953.size();

    for (int i = 0; i < _3605; i++) {
      _818 = (SQLParam) _6953.get(i);
      if (_818.getColumnname().equals(_3374)) {
        _818.setBigDecimal(_281);
        _818.setColumnType(NUMBER);
        _6953.set(i, _818);
      }
    }
  }

  private String Ltrim(String _1133) {
    String _2366 ="";
    char[] _7244 = _1133.toCharArray();
    int i=0;
    for(i=0;i<_7244.length;i++){
      if(_7244[i] != ' '){
        break;
      }
    }
    for(int ii=i;ii<_7244.length;ii++){
      _2366 = _2366 + _7244[ii];
    }
    return _2366;
  }

  private int getSqlDelimPoint(String _1073){
    int _2366=-1;
    char[] _2261 = _1073.toCharArray();
    for(int i=0;i<_2261.length;i++){
      if(_2261[i]==' ' || _2261[i] == ',' || _2261[i] == ')' || _2261[i] == '(' || _2261[i] == '\'' ||  _2261[i] == '%' ||  _2261[i] == '|' ||  _2261[i] == '&' ||  _2261[i] == '#') {
        _2366 =i;
        break;
      }
    }
    return _2366;
  }

  private int getSqlDelimNextPoint(String _1073){
     int _2366=-1;
     char[] _2261 = _1073.toCharArray();
     for(int i=0;i<_2261.length;i++){
       if(_2261[i]==' ') {
         _2366 =i;
         break;
       }
       if( _2261[i] == ','){
         _2366 =i-1;
         break;
       }

       if( _2261[i] == ')' ||  _2261[i] == '(' || _2261[i] == '\'' ||  _2261[i] == '%' ||  _2261[i] == '|' ||  _2261[i] == '&' ||  _2261[i] == '#'){
         _2366 =i-1;
         break;
       }

     }
     return _2366;
  }


}

