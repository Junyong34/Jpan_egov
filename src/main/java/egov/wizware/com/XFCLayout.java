package egov.wizware.com;

import  egov.wizware.util.DateUtil;
import  java.util.ArrayList;
public class XFCLayout {
  private int    _columntype=0;  // 0:column   1:fiesize , 2:createdate
  private String _columnnm="";
  private String _dateformat="";
  private int    _order=0;
  private String _charset="";

  private boolean _findalign=false;
  private String  _align="";
  private int     _len=0;
  private char    _blankchar;

  private ArrayList _delimtype=null;   // 1: tab , 10: newline , 100: carrage return ,
  private String _delim="";    //특수문자처리(\n, \r, \t)일때는 값을 clear한다.
  private String _newline="";  // \r\n , \n 처리
  private long    _fsize=0;

  //private String _formatvalue="";
  public XFCLayout()
  {
  }
  public XFCLayout(int _ord)
  {
    _order=_ord;
  }
  public void _setOrder(int _ord){
    _order = _ord;
  }
  public int _getColumntype(){
    return _columntype;
  }
  public String _getColumnnm(){
    return _columnnm;
  }
  public String _getCharset(){
    return _charset;
  }


  public void _setFsize(long _filesize, int _recordsize){
    _fsize = _filesize + _recordsize;
  }
  public String _getFormatvalue(String _val)
  {
    String _rtn="";
    // 0:column   1:fiesize , 2:filelastsize , 3:createdate
    if(_columntype==0){ // column
      _rtn = _getAlignValue(_val);
      _rtn +=_getDelimValue();
    }else if(_columntype==1){//filesize


    }else if(_columntype==2){//createdate
      _rtn = _getCreateDate();
      _rtn +=_getDelimValue();
    }
    return _rtn;
  }

  public byte[] setByte(byte[] target, byte[] src, int startidx, int len)
  {
      int j=0;
      if(src ==null || src.length ==0 ) return target;
      for(int i=startidx;i<len;i++){
          target[i] = src[j];
          j++;
      }
      return target;
  }

  public byte[]  _getFormatBytevalue(String _val)
  {
    byte[] _rtn=null;
    // 0:column   1:fiesize , 2:filelastsize , 3:createdate
    if(_columntype==0){ // column
        byte[] _rtnx = _getAlignByteValue(_val);
        byte[] _rtndelm = _getDelimByteValue() ;
        _rtn = new byte[_rtnx.length+_rtndelm.length];


        _rtn = setByte(_rtn , _rtnx, 0, _rtnx.length);
        _rtn = setByte(_rtn , _rtndelm, _rtnx.length, _rtnx.length + _rtndelm.length);

    }else if(_columntype==1){//filesize

    }else if(_columntype==2){//createdate
        byte[] _rtnx =_getCreateByteDate();
        byte[] _rtndelm = _getDelimByteValue() ;
        _rtn = new byte[_rtnx.length+_rtndelm.length];
        _rtn = setByte(_rtn , _rtnx, 0, _rtnx.length);
        _rtn = setByte(_rtn , _rtndelm, _rtnx.length, _rtnx.length + _rtndelm.length);

    }

    return _rtn;
  }

  public byte[] _getLastFormatBytevalue(String _val)
    {
      byte[] _rtn=null;
      // 0:column   1:fiesize , 2:filelastsize , 3:createdate
      if(_columntype==0){ // column
          _rtn = _getAlignByteValue(_val);
      }else if(_columntype==1){//filesize

      }else if(_columntype==2){//createdate
        _rtn=_getCreateByteDate();
      }
      return _rtn;
  }
  public String _getLastFormatvalue(String _val)
  {
    String _rtn="";
    // 0:column   1:fiesize , 2:filelastsize , 3:createdate
    if(_columntype==0){ // column
      _rtn = _getAlignValue(_val);
    }else if(_columntype==1){//filesize


    }else if(_columntype==2){//createdate
      _rtn = _getCreateDate();
    }
    return _rtn;
  }


  private String _getDelimValue(){
    String _rtn ="";
    if(_delim.equals("")){
        if(_delimtype != null) {
            if(_delimtype.size() > 0){
                for(int i=0;i<_delimtype.size();i++){
                    if(_delimtype.get(i).toString().equals("r")){
                        _rtn+="\r";
                    }else if(_delimtype.get(i).toString().equals("n")){
                        _rtn+="\n";
                    }else if(_delimtype.get(i).toString().equals("t")){
                        _rtn+="\t";
                    }
                }
            }
        }
    }else {
      _rtn = _delim;
    }

    return _rtn;
  }
  private byte[] _getDelimByteValue(){
    String str="";
    if(_delim.equals(""))
    {
        if(_delimtype != null)
        {
            if(_delimtype.size() > 0)
            {
                for(int i=0;i<_delimtype.size();i++)
                {

                    if(_delimtype.get(i).toString().equals("r"))
                    {
                        str +="\r";
                    }
                    else if(_delimtype.get(i).toString().equals("n"))
                    {
                        str +="\n";
                    }
                    else if(_delimtype.get(i).toString().equals("t"))
                    {
                        str +="\t";
                    }
                }
            }
        }
    }
    else
    {
      str = _delim;
    }



    return str.getBytes();
  }

  private String _getAlignValue(String _value){
    String _rtn ="";
    char[] _tochar = _value.toCharArray();

    if(_findalign == false) return  _value;
    if(_align.equals("L"))
    {
      int i=0;
      for(i=0; i<_tochar.length && i<_len ;i++){
        _rtn+=_tochar[i];
      }
      for(int j=i;j<_len;j++){
        _rtn+=this._blankchar;
      }
    }
    else if(_align.equals("R"))
    {
      int i=0;
      for(i=0; i<_tochar.length && i<_len ;i++){
        _rtn+=_tochar[i];
      }
      for(int j=i;j<_len;j++){
        _rtn= this._blankchar + _rtn;
      }
    }
    else if(_align.equals("D"))
    {
        _rtn = _value;
    }

    return _rtn;
  }


  private byte[] _getAlignByteValue(String _value){
      //System.out.println(_value +":" + _len + ":" + this._blankchar +":" + _value.length());
     byte[] _rtn =new byte[_len];
     byte[] _tochar = _value.getBytes();

     if(_findalign == false) return  _value.getBytes();

     if(_align.equals("L"))
     {
       int i=0;
       for(i=0; i<_tochar.length && i<_len ;i++){
         _rtn[i]=_tochar[i];
       }
       for(int j=i;j<_len;j++){
         _rtn[j]=(this._blankchar+"").getBytes()[0];
       }
     }
     else if(_align.equals("R"))
     {
       int i=0;
       for(i=0; i<_len-_tochar.length;i++)
       {
          _rtn[i]=(this._blankchar+"").getBytes()[0];
       }
        int x=0;
       for(int j=i;j<_len;j++)
       {
         _rtn[j]=_tochar[x];
         x++;
       }
     }
     else if(_align.equals("D"))
     {
         _rtn = _value.getBytes();
     }

     return _rtn;
   }

  private byte[] _getAlignByteValue_bk(String _value){
   byte[] _rtn =new byte[_len];
   byte[] _tochar = _value.getBytes();

   if(_findalign == false) return  _value.getBytes();
   if(_align.equals("L"))
   {
     int i=0;
     for(i=0; i<_tochar.length && i<_len ;i++){
       _rtn[i]=_tochar[i];
     }
     for(int j=i;j<_len;j++){
       _rtn[i]=(this._blankchar+"").getBytes()[0];
     }

   }
   else if(_align.equals("R"))
   {
     int i=0;
     for(i=0; i<_tochar.length && i<_len ;i++)
     {
       _rtn[i]=_tochar[i];
     }
     for(int j=i;j<_len;j++)
     {
       _rtn[i]=(this._blankchar+"").getBytes()[0];
     }
   }
   else if(_align.equals("D"))
   {
       _rtn = _value.getBytes();
   }
   return _rtn;
 }


  //YYYYMMDD hh:mm:ss SSS
  private String _getCreateDate(){
    String _rtn ="";
    String _format = _getCreateDateJavaFormat();
    DateUtil _dutil = new DateUtil();
    _rtn=_dutil.GetCurrentDate(_format);
    return _rtn;
  }
  private byte[] _getCreateByteDate(){
    String _rtn ="";
    String _format = _getCreateDateJavaFormat();
    DateUtil _dutil = new DateUtil();
    _rtn=_dutil.GetCurrentDate(_format);
    return _rtn.getBytes();
  }


  private String _getCreateDateJavaFormat(){
    String _rtn ="";
    char[] _tochar = _dateformat.toCharArray();
    String _javaformat =""; //yyyy :년 , MM:월 , dd, HH:시 , mm:분 , ss:초
    for(int i=0;i<_tochar.length;i++){
      if(_tochar[i] == 'Y') {
        _rtn +='y';
      }else if(_tochar[i] == 'y') {
        _rtn +='y';
      }else if(_tochar[i] == 'D') {
        _rtn +='d';
      }else if(_tochar[i] == 'd') {
        _rtn +='d';
      }else if(_tochar[i] == 'H') {
        _rtn +='H';
      }else if(_tochar[i] == 'h') {
        _rtn +='H';
      }else {
        _rtn +=_tochar[i];
      }
    }
    return _rtn;
  }

  //column,3L0,'delim' ['\''
  //this._delim 가 space이면
  public void _setColumnLayout(String _column, String _align, String _delim , String _charset)
  {
    _column = _Ltrim(_column).trim();
    _setColumntype(_column);

    _align  = _Ltrim(_align).trim();
    if(!_align.equals("")){
      _setAlign(_align);
      _findalign=true;
    }

    //_delim  = _Ltrim(_delim).trim();

    if(!_delim.equals("")){
      if(_delim.indexOf("\\") != -1 ){
        _setSpecialDelim(_delim);
        this._delim ="";
      }else {
        this._delim = _delim;
      }
    }

    this._charset = _charset;
  }

  //_delimtype 0:tab, 1: newline , 2:carrrage return
  private void _setSpecialDelim(String delim){
    _delimtype = new ArrayList();
    boolean _finded = false;
    char[] _tochar = delim.toCharArray();
    for(int i=0;i<_tochar.length;i++){
      if(_tochar[i] ==' ') continue;
      if(_tochar[i] == '\\'){
        _finded = true;
      }else if(_finded == true){
        if(_tochar[i] == 't'){
          _delimtype.add("t");
        }else if(_tochar[i] == 'n'){
          _delimtype.add("n");
        }else if(_tochar[i] == 'r'){
          _delimtype.add("r");
        }
        _finded = false;
      }
    }
  }

  //3L0
  private void _setAlign(String _align){
    String _lenstr ="";
    boolean _blankfinded = false;
    char[] _tochar = _align.toCharArray();
    for(int i=0;i<_tochar.length;i++){
      if(_tochar[i] == ' ') continue;
      if(_tochar[i] == 'L')
      {
        this._align ="L";
        _blankfinded = true;
        continue;
      }
      else  if(_tochar[i] == 'R')
      {
        this._align ="R";
        _blankfinded = true;
        continue;
      }
      else  if(_tochar[i] == 'D')
      {
          this._align ="D";
          _blankfinded = true;
          continue;
      }

      if(_blankfinded== true){
        this._blankchar = _tochar[i];
        break;
      }
      _lenstr += _tochar[i];
    }
    _len = Integer.parseInt(_lenstr);
  }

  //예약명칭 &filesize(),&filelastsize(),&string(String)
  //&createdate(format):YYYY,MM,DD,hh,mm,ss를 이용한 format처리 가능
  //ex) &createdate(YYYY/MM/DD hh:mm:ss)
  private void _setColumntype(String _column){
    char[] _tochar = _column.toCharArray();
    String _funcstr ="";
    String _parmstr="";
    boolean _parmflag=false;
    boolean _finded=false;
    if(_tochar.length==0 || _tochar[0] != '&') {
      this._columnnm = _column;
      this._columntype = 0;
      return;
    }

    for(int i=1;i<_tochar.length;i++){
      if(_finded==false && _tochar[i] ==' '){
        continue;
      }
      _finded = true;
      if(_tochar[i] != '(' ){
        _parmflag= true;
        continue;
      }else if( _tochar[i] != ')'){
        break;
      }
      if(_parmflag== true){
        _parmstr +=  _tochar[i];
      }else {
        _funcstr += _tochar[i];
      }
    }

    if(_funcstr.equals("filesize")){
       this._columntype = 1;

    }else if(_funcstr.equals("createdate")){
      this._dateformat = _parmstr;
       this._columntype = 2;
    }
    this._columnnm = _funcstr;
  }

  private String _Ltrim(String _sss) {
    String _rtn ="";
    char[] _ccc = _sss.toCharArray();
    int i=0;
    for(i=0;i<_ccc.length;i++){
      if(_ccc[i] != ' '){
        break;
      }
    }
    for(int ii=i;ii<_ccc.length;ii++){
      _rtn = _rtn + _ccc[ii];
    }
    return _rtn;
  }

}
