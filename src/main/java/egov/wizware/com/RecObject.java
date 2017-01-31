package egov.wizware.com;

import java.util.HashMap;
import java.text.*;
import java.util.*;
import java.io.*;

public class RecObject {
  private HashMap _2594 = new HashMap();
  private String[] _3131 = null;

  public RecObject()
  {
  }
  public RecObject(HashMap hmap)
  {
     _2594 = hmap;
  }
  public void setOrderName(String[] _3374)
  {
    this._3131 = _3374;
  }

  public void setRecord(HashMap _2594){
    this._2594 = _2594;
  }

  public HashMap getRecord()
  {
    return this._2594;
  }

  public void put(String _3374,Object _305)
  {
    this._2594.put(_3374,_305);
  }

  public String get(int _order)
  {
    String _305="";
    String _3374 = this._3131[_order];
    if(_2594 == null || _2594.get(_3374) == null){
      _305="";
    }else {
      _305=_2594.get(_3374).toString();
    }
    return _305;
  }

  public String get(Object _3374)
  {
    String _305="";
    if(_2594==null || _2594.get(_3374) == null)
    {
      _305="";
    }
    else
    {
      _305=_2594.get(_3374).toString();
    }
    return _305;
  }

  public byte[] getBytes(Object _3374)
  {
    byte[] _305=null;

    if (_2594 == null || _2594.get(_3374) == null) {
        _305 = null;
    } else {
        //_305 = (byte[])_2594.get(_3374);
        Object xxx = _2594.get(_3374);
        if(xxx instanceof byte[]){
            _305=(byte[])_2594.get(_3374);
        }else if(xxx instanceof String){
            _305=_2594.get(_3374).toString().getBytes();
        }
    }
    return _305;
  }

  public String getStr(Object _3374) {
      String _305="";
      if(_2594==null || _2594.get(_3374) == null){
        _305="";
      }else {
        _305=_2594.get(_3374).toString();
        if(!isNumber(_305)){
          _305 = "\"" + _305 +"\"";
        }
      }
      return _305;
  }

  public boolean isNumber(String s){    //SQ0470
      boolean rtn=false;
      if(s!=null && !s.equals("")){
          try{
              Double.parseDouble(s);
              rtn = true;
          }catch(NumberFormatException e){}
      }
      return rtn;
  }
  public String getTrim(int _order)
  {
    String _305="";
    String _3374 = this._3131[_order];
    if(_2594==null || _2594.get(_3374) == null){
      _305="";
    }else {
      _305=_2594.get(_3374).toString().trim();
    }
    return _305;
  }

  public String getTrim(Object _3374){
      String _305="";
      if(_2594==null || _2594.get(_3374) == null){
        _305="";
      }else {
        _305=_2594.get(_3374).toString().trim();
      }
      return _305;
  }
  public String getNontrim(Object _3374){

    String _305="";
    if(_2594==null || _2594.get(_3374) == null){
      _305="";
    }else {
      _305=_2594.get(_3374).toString();
    }
    return _305;
  }
  public String getLtrim(Object name)
  {
      String _305="";
      if(_2594==null || _2594.get(name) == null){
          _305="";
     }else {
         _305= _Ltrim(_2594.get(name.toString().trim()).toString());
     }
     return _305;
 }
 public String getRtrim(Object name){
     String _305="";
     if(_2594==null || _2594.get(name) == null){
         _305="";
     }else {
         _305= _Rtrim(_2594.get(name.toString().trim()).toString());
     }
     return _305;
 }

 private String _Ltrim(String s)  {    //SQ0570
     String _rtn ="";
     char[] c = s.toCharArray();
     int i=0;
     for(i=0;i<c.length;i++){
         if(c[i] != ' '){
             break;
         }
     }
     for(int ii=i;ii<c.length;ii++){
         _rtn = _rtn + c[ii];
     }
     return _rtn;
 }
 private String _Rtrim(String s)
 {
     String _rtn ="";
     char[] c = s.toCharArray();
     int i=0;

     if(c!=null  && c.length > 0) {
         for(i=c.length-1; i>=0; i--){
             if(c[i] != ' '){
                 break;
             }
         }
     }
     for(int ii=0;ii<i;ii++)
     {
         _rtn = _rtn + c[ii];
     }
     return _rtn;
 }


  public double getDouble(String _71, String _3374){
    String _305="";
    String _65;
    if(_2594==null || _2594.get(_3374) == null){
      _305="";
    }else {
      _305=_2594.get(_3374).toString().trim();
    }
    double _137=0.0;
    if(!_305.equals("")){
      _305 = getFMFormatSubstring(_71 ,_305); //-------소숫점 자리 절삭 처리 한다.
      _137 = Double.parseDouble(_305);
    }
    return _137;
  }

  public String getFMFormatSubstring(String _71,String _281)
  {
    if(_281.length() > 0 ) {
      if(_71.indexOf(".") == -1) {
        if(_281.indexOf(".") != -1) {
          _281 = _281.substring(0 , _281.indexOf("."));
        }
      }else {
        int _decimalPoint = _71.indexOf(".");
        int _2885 = _71.length() - _decimalPoint;
        if(_281.indexOf(".") != -1){
          int _6086 =0;
          if(_281.indexOf(".")+_2885 > _281.length()){
            _6086 = _281.length();
          }else{
            _6086 = _281.indexOf(".")+_2885;
          }
          _281 = _281.substring(0, _281.indexOf(".")) + _281.substring( _281.indexOf("."), _6086);   // value.indexOf(".")+pointLenEnd );
        }
      }
    }else {
      _281="0";
    }

    return _281;
  }


  public double getDouble(String _3374)
  {
    String _305="";
    String _65;
    if(_2594==null || _2594.get(_3374) == null)
    {
      _305="";
    }
    else
    {
      _305=_2594.get(_3374).toString().trim();
    }
    double _137=0.0;
    if(!_305.equals(""))
    {
      _137 = Double.parseDouble(_305);
    }
    return _137;
  }

  public long getLong(String _3374){
    String _305="";
    String _65;
    if(_2594==null || _2594.get(_3374) == null){
      _305="";
    }else {
      _305=_2594.get(_3374).toString().trim();
    }
    long _137=0;
    if(!_305.equals("")){
      _137 = Long.parseLong(_305);
    }
    return _137;
  }

  public int getInt(String _3374){
    String _305="";
    String _65;
    if(_2594==null || _2594.get(_3374) == null){
      _305="";
    }else {
      _305=_2594.get(_3374).toString().trim();
    }
    int _137 =0;
    if(!_305.equals("")){
      if(_305.indexOf(".")!= -1){
          String _608 = _305.substring(0, _305.indexOf("."));
          if(!_608.trim().equals("")){
              _137 =Integer.parseInt(_608);
          }
      }else {
         _137 =Integer.parseInt(_305);
      }
    }
    return _137;
  }

//-----  table에 정의된 column의 길이
  public int getColumnsize(Object _3374){
    String _305="";
    int _2366=0;
    if(_2594==null || _2594.get(_3374) == null){
      _2366=0;
    }else {
      _305=_2594.get(_3374).toString().trim();
      if(_305.length()!=0){
        _2366 = Integer.parseInt(_305);
      }
    }
    return _2366;
  }

//---- 입력 data가 8자리 일자를 format처리 한다.
  public String getDateDelimFormat(String _6347eter,Object _3374){
    String val="";
    if(_2594==null || _2594.get(_3374) == null){
      val="";
    }else {
      val=_2594.get(_3374).toString().trim();
    }

    String yyyy = val.substring(0,4);
    String mm   = val.substring(4,6);
    String dd   = val.substring(6,8);

    String wsDate = yyyy + _6347eter + mm + _6347eter + dd;
    return wsDate;
  }

  private String getDateFormat(String _5807,Object _3374){
    String _305="";
    if(_2594==null || _2594.get(_3374) == null){
      _305="";
    }else {
      _305=_2594.get(_3374).toString().trim();
    }

    int _yyyy = Integer.parseInt(_305.substring(0,4));
    int _mm   = Integer.parseInt(_305.substring(4,6));
    int _dd   = Integer.parseInt(_305.substring(6,8));

    Calendar _2417 = Calendar.getInstance();
    _2417.set(_yyyy,_mm,_dd);
    Date _524 =  _2417.getTime();

    SimpleDateFormat _yyyyMMddE = new SimpleDateFormat(_5807);
    String _74;
    _74 = _yyyyMMddE.format(_524);

    return _74;
  }




  public String getNumberFormat(String _71, String _3374){
    String _305="";
    String _65;
    if(_2594==null || _2594.get(_3374) == null){
      _305="0";
    }else {
      _305=_2594.get(_3374).toString().trim();
    }
    _305 = getFMFormatSubstring(_71 ,_305); //-------소숫점 자리 절삭 처리 한다.
    double _137 = Double.parseDouble(_305);
    DecimalFormat _df = new DecimalFormat(_71);
    _65 = _df.format(_137);

    return _65;
  }


  public String getKSC5601(Object _3374, String _7190){
    String _305="";
    if(_2594==null || _2594.get(_3374) == null){
      _305="";
    }else {
      _305=_2594.get(_3374).toString().trim();
      try {
        _305 = new String(_305.getBytes(_7190), "KSC5601" );
      }catch(java.io.UnsupportedEncodingException e) {
        System.out.println("FM:CodeCvt:한글변환 오류(getKSC5601-->"+_7190+")");
      }
    }
    return _305;
  }

}
