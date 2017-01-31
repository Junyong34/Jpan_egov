package egov.wizware.util;

import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.reflect.Method;
import java.util.StringTokenizer;
import java.sql.PreparedStatement;
import java.sql.Connection;
import egov.wizware.com.*;
import egov.wizware.ulog.*;

public class WizUtil {
    private final  int _last=1;
    private final  int _3944=3;
    private DOBJ   inputdobj = null;
    //private DLog     _19G= null;  //log 처리 기능
    private boolean  _9c=false;

    public WizUtil() {
    }
    public WizUtil(DOBJ dobjx)
    {
        inputdobj = dobjx;
    }
    public WizUtil(DOBJ dobjx, String curid, String curname)
    {
        dobjx.setCurrStep(curid, curname);
        inputdobj = dobjx;
      //  _19G = dobj._getDlg();
    }
    public int getIntTotal(String objnm, String colnm)
    {
        int rtn = 0;
        VOBJ _ivobj = inputdobj.getRetObject(objnm);
        _ivobj.first();
        while(_ivobj.next())
        {
            rtn += _ivobj.getRecord().getInt(colnm);
        }
        return rtn;
    }
    public long getLongTotal(String objnm, String colnm)
    {
        long rtn = 0;
        VOBJ _ivobj = inputdobj.getRetObject(objnm);
        _ivobj.first();
        while(_ivobj.next())
        {
            rtn += _ivobj.getRecord().getLong(colnm);
        }
        return rtn;
    }
    public double getDoubleTotal(String objnm, String colnm)
    {
        double rtn = 0;
        VOBJ _ivobj = inputdobj.getRetObject(objnm);
        _ivobj.first();
        while(_ivobj.next())
        {
            rtn += _ivobj.getRecord().getDouble(colnm);
        }
        return rtn;
    }

    public void _19ginfo(String xxx)
    {
        /*
          if(this._19G != null) {
              _19G.info(xxx, null);
          }else {
              System.out.println(xxx);
          }
          */
  }
  public void _19gerr(String xxx, Exception e)
  {
      /*
      if(_19G != null)
      {
          _19G.error(xxx, e);
      }else
      {
          System.out.println(xxx);
          System.out.println(e.getMessage());
      }
*/
  }

  public String tochar(String _281, int _7630, int _epint, String _6590 ) throws Exception
  {
      String _2366 ="";
      if(_281 == null || _281.length() < _7630){
          this._19ginfo("char 처리 대상 value의 길이가 start point보다 작습니다.("+_281+")");
          throw new Exception("char 처리 대상 value의 길이가 start point보다 작습니다.("+_281+")");
      }

      if(_281 != null && _281.length() >= _epint){
          _2366 = _281.substring(_7630,_epint);

      }else {
          if(_6590.length() > 1) {
              this._19ginfo("char 처리의 채우는 것은 1개문자만 가능합니다.("+_6590+")");
              throw new Exception("char 처리의 채우는 것은 1개문자만 가능합니다.("+_6590+")");
          }
          _2366 = _281.substring(_7630,_281.length());
          for(int i=_281.length();i<_epint;i++){
              _2366 = _2366 + _6590;
          }
      }

      return _2366;
  }
  //tochar("aa")
  public String tochar(String _281, String _6590 ) throws Exception
  {
      String _2366 ="";
      if(_6590.equals("")) return "";
      if(_281.indexOf(_6590) == -1)
      {
          System.out.println("tochar string index -1:" + _281 +":" + _6590);
          return "";
      }
      _2366 = _281.substring(0, _281.indexOf(_6590));
      return _2366;
  }

  //tochar("-","-")
  public String tochar(String _281, String _start, String _end ) throws Exception
  {
      String _2366="";
      if(_start.equals("")) return "";
      if(_end.equals("")) return "";
      if(_281.indexOf(_start) == -1)
      {
          System.out.println("tochar string index -1:" + _281 +":" + _start);
          return "";
      }
      if(_281.indexOf(_end) == -1)
      {
          System.out.println("tochar string index -1:" + _281 +":" + _end);
          return "";
      }

      int intA =_281.indexOf(_start);
      int intB =_281.indexOf(_end);
      if( intA== -1 || intB == -1)
      {
          return "";
      }
      else
      {
          if(intA >= intB)
          {
              return "";
          }
          else
          {
              String _tmp = _281.substring(intA +_start.length());
              intB =_tmp.indexOf(_end);
              _2366 = _tmp.substring(0, intB);
          }
      }
      return _2366;
  }


  //tochar(1,"aa") , tochar(-1,"rr")
  public String tochar(String _281, int _6590, String _end ) throws Exception
  {
      String _2366 ="";
      if(_end.equals("")) return "";
      if(_6590 == -1)
      {
          _2366 = _281.substring(_281.lastIndexOf(_end)+_end.length());
      }
      else
      {
          int _spoint = getIndexString(_281, _6590, _end);
          if(_spoint == -1) return "";
          _2366 = _281.substring(_spoint+_end.length());
      }
      return _2366;
  }

  //tochar(0,"aa", 2, "aa")
  public String tochar(String _281, int _spin, String _start, int _epin, String _end ) throws Exception
  {
      String _2366 ="";
      int _spoint =0;
      int _epoint =0;
      if(_start.equals("")) return "";
      if(_end.equals("")) return "";

      _spoint = getIndexString(_281, _spin, _start);
      if(_spoint == -1) return "";

      _epoint = getIndexString(_281, _epin, _end);
      if(_epoint == -1) return "";

      if(_spoint >= _epoint) return "";
      _2366 = _281.substring(_spoint +_start.length(), _epoint);
      return _2366;
  }

  private int getIndexString(String _str, int _pin, String _pstr)
  {
      String lstr ="";
      int _findcnt =0;
      int _spin =0;
      int _rtn =0;
      boolean finded = false;
      if(_str.indexOf(_pstr)==-1)
      {
          return -1;
      }

      while((_spin = _str.indexOf(_pstr)) != -1)
      {
          lstr += _str.substring(0, _spin);
          _findcnt++;
          if(_pin == _findcnt)
          {
              finded = true;
              break;
          }
          lstr += _pstr;
          _str =_str.substring(_spin+_pstr.length());
      }
      if(finded == true)
      {
          _rtn = lstr.length();
      }
      else
      {
          _rtn =-1;
      }
      return _rtn;
  }







  public String getColvals(VOBJ _164, String _7028 , String _middle) throws Exception{
        String _2366="";

        _middle = _middle.substring(1,_middle.length()-1);
        if(_164.getRecordCnt() > 0){
            boolean _5879 = true;
            int _6644 = _164.current;
            _164.first();
            while(_164.next()){
                if(_5879 == true){
                    _2366 =  _2366 + _164.getRecord().get(_7028);
                    _5879 = false;
                }else {
                    _2366 =  _2366 +_middle+ _164.getRecord().get(_7028);
                }
            }
            _164.current = _6644;
        }
        return _2366;
    }

    public String getColvals(VOBJ _164, String _7028 , String _1109, String _middle, String _end) throws Exception{
        String _2366="";
        _1109 = _1109.substring(1,_1109.length()-1);
        _middle = _middle.substring(1,_middle.length()-1);
        _end = _end.substring(1,_end.length()-1);
        if(_164.getRecordCnt() > 0){
            boolean _5879 = true;
            int _6644 = _164.current;
            _164.first();
            while(_164.next()){
                if(_5879 == true){
                    _2366 =  _1109 + _164.getRecord().get(_7028);
                    _5879 = false;
                }else {
                    _2366 =  _2366 +_middle+ _164.getRecord().get(_7028);
                }
            }
            _2366 = _2366 +_end;
            _164.current = _6644;
        }

        return _2366;
    }


    public String newString(String _1073, String _5807) throws Exception {
        String _4262  = "";
        String _3113 ="";

        _4262 = this._3806(_5807.substring(1,_5807.indexOf(",")-1) ).trim();
        _3113= this._3806(_5807.substring(_5807.indexOf(",")+2,_5807.length()-1) ).trim();
        String _2366 = this.codeCvt(_1073, _4262,  _3113 );

        return _2366;
    }

    public String getReplace(String _5729, String _899 ,String _1175) throws Exception{
        return _5729.replaceAll(_899, _1175);
    }
    public String getReplace_(String _5729, String _899 ,String _1175) throws Exception
    {
        while(_5729.indexOf(_899) != -1)
        {
            _5729 = _5729.substring(0,_5729.indexOf(_899)) + _1175 +
                          _5729.substring(_5729.indexOf(_899)+_899.length());
        }
        return _5729;
    }

    /*
       public String getReplace(String _5729, String _5807) throws Exception{
         String _899= this._3806(_5807.substring(0,_5807.indexOf(","))).trim();
         String _1175   = this._3806(_5807.substring(_5807.indexOf(",")+1)).trim();

         _899 = _899.substring(1,_899.length()-1);
         _1175    = _1175.substring(1,_1175.length()-1);
         while(_5729.indexOf(_899) != -1){
      _5729 = _5729.substring(0,_5729.indexOf(_899)) + _1175 +
          _5729.substring(_5729.indexOf(_899)+_899.length());
         }
         return _5729;
       }
     */
    public String getNumberFormat(String _305,String _5807) throws Exception{
        String _65;
        if(_305==null || _305.equals("")) _305="0";
        double _137 = Double.parseDouble(_305);
        DecimalFormat df = new DecimalFormat( _5807.substring(1,_5807.length()-1).trim() );
        _65 = df.format(_137);
        return _65;
    }

    public String getStringFormatDel( String _281, String _5807) throws Exception{

        String _2366 ="";      //    value="2006-10-04;10;20";      //    _71="-;";
        _5807 = _5807.trim();
        char[] _7923 = _5807.toCharArray();
        char[] _7926 = _281.toCharArray();
        int _7100=0;

        if(_281.equals("")){
            return _281;
        }

        boolean _1340 = false;
        for(int i=0;i<_7926.length;i++){
            for(int ii=0;ii<_7923.length;ii++){
                if( _7926[i] == _7923[ii] ){
                    _1340=true;
                    break;
                }
            }
            if(_1340 == false)
                _2366 = _2366 +_7926[i];
            _1340= false;
        }

        return _2366;
    }

    private String _3806(String _1073 )  {
        String _2366 ="";
        char[] _7172 = _1073.toCharArray();
        int i=0;
        for(i=0;i<_7172.length;i++){
            if(_7172[i] != ' '){
                break;
            }
        }
        for(int ii=i;ii<_7172.length;ii++){
            _2366 = _2366 + _7172[ii];
        }

        return _2366;
   }

   public String codeCvt(String _1073, String _4262, String _3113)  throws Exception{   //DF0030
       String _3290 = null;
       try {
           _3290 = new String(_1073.getBytes(_4262), _3113);
       }catch(Exception e){
           this._19gerr("String newString code convert error",e);
           e.printStackTrace();
           throw new Exception(" String newString code convert error");
       }
       return _3290;
   }

   public String substr(String _str, int _start, int _end) throws Exception {
       String _rtn = _str.substring(_start, _end);
       return _rtn;
   }

   public int indexstr(String _str, String _indx, int nll) throws Exception {
       int _rtn =_str.indexOf(_indx);
       if(_rtn == -1) _rtn = nll;
       return _rtn;
    }

    //-----------------------------------
    public void ULogging(Exception e, DOBJ dobj)
    {
        try{
            ULog ulog = new ULog();
            ulog.writeLog(e, dobj);
        }catch(Exception ex)
        {
            System.out.println("ULOGGING ERROR:");
            ex.printStackTrace();
        }
    }
   //-------------------------------------------------------------------------------
   //-------------아래 날짜 처리 Util ------------------------------------------------
   //-------------------------------------------------------------------------------
   public int getDayDiff(String _1025, int _1043 ) throws Exception{
       return getDayDiff( _1025,  _1043+"" );
   }
   public int getDayDiff(int _1025, String _1043 ) throws Exception{
       return getDayDiff( _1025,  _1043 );
   }
   public int getDayDiff(int _1025, int _1043 ) throws Exception{
       return getDayDiff( _1025+"",  _1043+"" );
   }
   public  int getDayDiff(String _1025, String _1043 ) throws Exception{

       if(_1025.indexOf(".")!=-1) _1025 = _1025.substring(0,_1025.indexOf("."));
       if(_1043.indexOf(".")!=-1) _1043 = _1043.substring(0,_1043.indexOf("."));

       Calendar _1106 = Calendar.getInstance();
       Calendar _6092 = Calendar.getInstance();

       if(_1025.length() == 6 || _1043.length() == 6 ) {
           return getMonthDiff( _1025,  _1043 );

       }else  if(_1025.length() == 4 || _1043.length() != 4 ) {
           return getYearDiff( _1025,  _1043 );

       }else  if(_1025.length() != 8 || _1043.length() != 8 ) {
           this._19ginfo("일수 연산 일자가 8자리오류("+_1025 +":" +_1043);
           throw new Exception("일수 연산 일자가 8자리오류("+_1025 +":" +_1043);
       }
       _1106.set(Integer.parseInt(_1025.substring(0, 4))
                      , Integer.parseInt(_1025.substring(4, 6)) - 1
                      , Integer.parseInt(_1025.substring(6, 8)));
       _6092.set(Integer.parseInt(_1043.substring(0, 4))
                    , Integer.parseInt(_1043.substring(4, 6)) - 1
                    , Integer.parseInt(_1043.substring(6, 8)));

       Date sdate = _1106.getTime();
       Date edate = _6092.getTime();

       int intDiff = Math.round(((sdate.getTime()-edate.getTime())/(1000*60*60*24))); //계산결과를 일로 바꿈
       return intDiff;
   }

   private  int getMonthDiff(String _1025, String _1043 ) throws Exception{
       int _smonthcnt=0;
       int _emonthcnt=0;
       _smonthcnt = (Integer.parseInt(_1025.substring(0, 4)) * 12) + Integer.parseInt(_1025.substring(4, 6));
       _emonthcnt = (Integer.parseInt(_1043.substring(0, 4)) * 12) + Integer.parseInt(_1043.substring(4, 6));
       return _smonthcnt - _emonthcnt;
   }

   private  int getYearDiff(String _1025, String _1043 ) throws Exception{
       int _smonthcnt=0;
       int _emonthcnt=0;
       _smonthcnt = Integer.parseInt(_1025.substring(0, 4));
       _emonthcnt = Integer.parseInt(_1043.substring(0, 4));
       return  _smonthcnt - _emonthcnt;
   }

   //wutil.getComputeDate(dvobj.getRecord().get("D_STARTDAY"), 2,2,1) ;

   public  int getComputeDate(int _4253, int _4391, int _4415date , int _isminus)  throws Exception
   {
       String _str = getComputeDateX( _4253+"",  _4391,  _4415date+"" ,_isminus);
       int _rtn = 0;
       if(_str != null && !_str.equals("")){
           _rtn = Integer.parseInt(_str);
       }
       return _rtn;
   }
   public  int getComputeDate(String _4253, int _4391, int _4415date  , int _isminus)  throws Exception
   {
        String _str = getComputeDateX( _4253+"",  _4391,  _4415date+"" ,_isminus);
        int _rtn = 0;
        if(_str != null && !_str.equals("")){
            _rtn = Integer.parseInt(_str);
        }
       return _rtn;
   }

   public  int getComputeDate(String _4253, int _4391, String _4415date  , int _isminus)  throws Exception
   {
        String _str = getComputeDateX( _4253+"",  _4391,  _4415date+"" ,_isminus);
        int _rtn = 0;
        if(_str != null && !_str.equals("")){
            _rtn = Integer.parseInt(_str);
        }
        return _rtn;
   }


   private  String getComputeDateX(String _4253, int _4391, String _4415date , int _isminus)  throws Exception
   {
           //  isminus:0  not-minus:1
           int _4415 = Integer.parseInt(_4415date);
           if(_isminus==0 ){
               _4415 = _4415 * -1;
           }

           if(_4253.indexOf(".")!=-1) _4253 = _4253.substring(0,_4253.indexOf("."));
           Calendar _2417 = Calendar.getInstance();
           String format ="";
           String SSS ="";
           try{
               if(_4253.length()==4){
                   String _716ndate = _4253 + "0101";
                   _2417.set(Integer.parseInt(_716ndate.substring(0,4)),
                                 Integer.parseInt(_716ndate.substring(4,6))-1,
                                 Integer.parseInt(_716ndate.substring(6,8)));
                   format ="yyyyMMdd";
                   if(_4391 ==2 ||_4391 ==3 || _4391==4) {
                       this._19ginfo("일수 연산 일자가 4자리오류는 년 계산처리만 가능합니다");
                       throw new Exception("일수 연산 일자가 4자리오류는 년 계산처리만 가능합니다");
                   }
               }else   if(_4253.length()==6){
                   String _716ndate = _4253 + "01";
                   _2417.set(Integer.parseInt(_716ndate.substring(0,4)),
                                 Integer.parseInt(_716ndate.substring(4,6))-1,
                                 Integer.parseInt(_716ndate.substring(6,8)));
                   format ="yyyyMMdd";
                   if(_4391 ==3 || _4391==4) {
                       this._19ginfo("일수 연산 일자가 6자리오류는 년월 계산처리만 가능합니다");
                       throw new Exception("일수 연산 일자가 6자리오류는 년월 계산처리만 가능합니다");
                   }
               }else  if(_4253.length()==8){
                   _2417.set(Integer.parseInt(_4253.substring(0,4)),
                                 Integer.parseInt(_4253.substring(4,6))-1,
                                 Integer.parseInt(_4253.substring(6,8)));
                   format ="yyyyMMdd";
               }else if(_4253.length()==12){
                   _2417.set(Integer.parseInt(_4253.substring(0,4)),
                                 Integer.parseInt(_4253.substring(4,6))-1,
                                 Integer.parseInt(_4253.substring(6,8)),
                                 Integer.parseInt(_4253.substring(8,10)),
                                 Integer.parseInt(_4253.substring(10,12)));
                   format ="yyyyMMddHHmm";
               }else if(_4253.length()==14){
                   _2417.set(Integer.parseInt(_4253.substring(0,4)),
                                 Integer.parseInt(_4253.substring(4,6))-1,
                                 Integer.parseInt(_4253.substring(6,8)),
                                 Integer.parseInt(_4253.substring(8,10)),
                                 Integer.parseInt(_4253.substring(10,12)),
                                 Integer.parseInt(_4253.substring(12,14)));
                   format ="yyyyMMddHHmmss";
               }else {
                   _2417.set(Integer.parseInt(_4253.substring(0, 4)),
                                 Integer.parseInt(_4253.substring(4, 6)) - 1,
                                 Integer.parseInt(_4253.substring(6, 8)),
                                 Integer.parseInt(_4253.substring(8, 10)),
                                 Integer.parseInt(_4253.substring(10, 12)),
                                 Integer.parseInt(_4253.substring(12, 14)));
                   format = "yyyyMMddHHmmssSSS";
               }
           }catch(Exception e){
               this._19gerr("날자 연산오류입니다. 처리값의 길이 확인이 필요합니다.(" + _2417 +")",e);
               e.printStackTrace();
               throw new Exception("날자 연산오류입니다. 처리값의 길이 확인이 필요합니다.(" + _2417 +")");
           }

           if      (_4391 ==1) _2417.add(_2417.YEAR , _4415);
           else if (_4391 ==2) _2417.add(_2417.MONTH, _4415);
           else if (_4391 ==3) _2417.add(_2417.DATE , _4415);
           else if (_4391 ==4) _2417.add(_2417.HOUR , _4415);

           Date _524 =  _2417.getTime();
           SimpleDateFormat yyyyMMddE = new SimpleDateFormat(format);
           String _80;
           _80 = yyyyMMddE.format(_524);

           if(_4253.length()==6)   _80 =_80.substring(0, 6);
           if(_4253.length()==4)   _80 =_80.substring(0, 4);

           return _80;
       }

       public int getCurrentDate()
       {
           Date TodayDate = new Date();
           SimpleDateFormat yyyyMMddE = new SimpleDateFormat("yyyyMMdd");
           String _80;
           _80 = yyyyMMddE.format(TodayDate);
           return Integer.parseInt(_80);
       }
       public int getLastdate(int _yyyymmdd) {
           int _rtn=99991231;
           String _str = _getLastdateX(_yyyymmdd+"");
           if(_str !=null && !_str.equals("")){
               _rtn = Integer.parseInt(_str);
           }
           return _rtn;
       }
       public int getLastdate(String _yyyymmdd) {
           int _rtn=99991231;
           String _str = _getLastdateX(_yyyymmdd);
           if(_str !=null && !_str.equals("")){
               _rtn = Integer.parseInt(_str);
           }
           return _rtn;
       }
       private  String _getLastdateX(String _yyyymmdd)  {
           int _6527;
           int _3485;
           int _35;
           //  System.out.println("getLastdate:"+yyyymmdd);
           _35 = Integer.parseInt(_yyyymmdd.substring(0,4));
           _3485 = Integer.parseInt(_yyyymmdd.substring(4,6));

           switch(_3485)
           {
           case 1:
           case 3:
           case 5:
           case 7:
           case 8:
           case 10:
           case 12: _6527 = 31;
               break;
           case 2: if ((_35 % 4) == 0) {
                   if ((_35 % 100) == 0 && (_35 % 400) != 0) { _6527 = 28; }
                   else { _6527 = 29; }
               } else { _6527 = 28; }
               break;
           default: _6527 = 30;
           }

           return _yyyymmdd.substring(0,6)+_6527;
       }

       //---------------------------------------------------------
       //------------   File Utiliy   ----------------------------
       //---------------------------------------------------------
       public String move(String _1145, String _1151, String _6299,String _6701,String _6698) throws IOException {
           String _2366 = null;
           String _6308=_1151;
           if(_6701.equals("1"))
           {
               if(!this.dirExist(_6299)) this.makeDir(_6299);
           }
           if(_6698.equals("0"))
           {
               if(this.isExist(_6299,_6308))
               {
                   _6308 = this.getDupFileNewName(_6299,_6308);
               }
           }
           if(this.copy(_1145,_1151,_6299,_6308,"0","1")!=null)
           {
               this.delete(_1145,_1151);
               _2366 = _6308;
           }

           return _2366;
       }

       public  String copy(String _1145, String _1151, String _6299, String _6308,String _6701,String _6698) throws IOException
       {
           FileInputStream _5831 = null;
           FileOutputStream _5786 = null;
           String _2366 = null;

           if(_6701.equals("1")){
               if(!this.dirExist(_6299)) this.makeDir(_6299);
           }
           if(_6698.equals("0"))
           {
               if(this.isExist(_6299,_6308))
               {
                   _6308 = this.getDupFileNewName(_6299,_6308);
               }
           }

           if(_1145 != null && _1145.length() > 0  && !_1145.substring(_1145.length()-1).equals("/"))
           {
               _1145+="/";
           }
           if(_6299 != null && _6299.length() > 0  && !_6299.substring(_6299.length()-1).equals("/"))
           {
               _6299+="/";
           }

           try
           {
               System.out.println("IN:"+_1145 + _1151);
               System.out.println("OUT:"+_6299 + _6308);
               _5831 = new FileInputStream(_1145 + _1151);
               _5786 = new FileOutputStream(_6299 + _6308);

               final int _7298 = 1024;
               byte _8977[] = new byte[_7298];
               int _3905 = 0;

               while ( (_3905 = _5831.read(_8977)) > 0)
                   _5786.write(_8977, 0, _3905);
           }
           finally
           {
               if (_5831 != null) _5831.close();
               if (_5786 != null) _5786.close();
               _2366= _6308;
           }
           return _2366;
       }

       public boolean delete(String _2996,String _5963){
           if(_2996 != null && _2996.length() > 0  && !_2996.substring(_2996.length()-1).equals("/")){
               _2996+="/";
           }

           File _5777 = new File(_2996 + _5963);
           return _5777.delete();
       }

       public String rename(String _2996,String _5963,String _887)  throws IOException
       {
           String _2366=null;
           if(_2996 != null && _2996.length() > 0  && !_2996.substring(_2996.length()-1).equals("/"))
           {
               _2996+="/";
           }

           File _5777 = new File(_2996 + _5963);
           File _2546 = new File(_2996 + _887);
           _5777.renameTo(_2546);
           _2366 = _887;
           return _2366;
       }

       private boolean isExist(String _2996, String _5963)
       {
           boolean _2366 = false;
           if(_2996 != null && _2996.length() > 0  && !_2996.substring(_2996.length()-1).equals("/"))
           {
               _2996+="/";
           }

           File _5777 = new File(_2996+_5963);
           if(_5777.isFile()) _2366 = true;
           return _2366;
       }

       private boolean dirExist(String _2996)
       {
           boolean _2366 = false;
           File _5777 = new File(_2996);
           if(_5777.isDirectory()) _2366 = true;
           return _2366;
       }

       private boolean makeDir(String _2996)
       {
           boolean _2366 = false;
           if(!this.dirExist(_2996)){
               File _5777 = new File(_2996);
               _5777.mkdirs();
           }
           return _2366;
       }

       private String getDupFileNewName(String _2996, String _5963)
       {
           String _3311=_5963;
           String _3305="";
           String _3947="";
           String _3533="";
           if(_5963.indexOf(".")!= -1)
           {
               _3533 = _5963.substring(0,_5963.lastIndexOf("."));
               _3947= _5963.substring(_5963.lastIndexOf("."));
           }

           if(_2996.trim().substring(_2996.trim().length()-1).equals("/"))
           {
               _3305 = _2996 ;
           }
           else
           {
               _3305 = _2996 +"/";
           }
           File fp = new File(_3305+_3311);
           int i=1;
           while(fp.exists())
           {
               _3311 = _3533+"_V" + i + _3947;
               fp = new File(_3305+_3311);
               i++;
           }
           //System.out.println("FILE NEW NAME:"+ _3311);
           this._19ginfo("FILE NEW NAME:"+ _3311);
           return _3311;
       }

       //--------------------------------------------------------------------------
       //------------------------------- FILE 생성: FBD UTIL  ----------------------
       //--------------------------------------------------------------------------
       public String makeFile(DOBJ _dobj, VOBJ _vobj, String _path, String _filename, String _xopt,  String _Layout, String _nline, String _charset ) throws Exception {
           String rtn ="";
           XFObject _xobj = new XFObject(_dobj);
           _xobj._setFileinfo(_path, _filename, _xopt, _vobj);
           rtn = _xobj._makeFile(_dobj, _Layout, _nline, _charset.trim());
           return rtn;
       }

       public VOBJ makeCSVFile(DOBJ _dobj, String _path, String _filename,  String _Layout , String _delim) throws Exception {
           VOBJ _vobj = null;
           XFObject _xobj = new XFObject(_dobj);
           _vobj= _xobj._getCSVVobj(_path, _filename, _Layout, _delim);
           return _vobj;
       }

       public VOBJ makeCSVLFile(DOBJ _6185, String _2996, String _5963,  String _Layout , String _6347) throws Exception
       {
           VOBJ _227 = null;
           XFObject _50 = new XFObject(_6185);
           _227= _50._getCSVLVobj(_2996, _5963, _Layout, _6347);
           return _227;
       }

       public String  makeFileSeq(String _path, String _filename , Object inbyteobj) throws Exception{
           byte[] inbyte = (byte[])inbyteobj;
           String _fullname = "";
           _path = _path.trim();
           _filename = _filename.trim();
           if(!_path.substring(_path.length()-1).equals("/")) _path = _path +"/";
           _fullname = _path + _filename;
           File _fp = new File(_fullname);

           int _cnt=0;
           while(_fp.exists()){
               String _filenamex = _filename.substring(0,_filename.indexOf(".")) +"_"+ _cnt + _filename.substring(_filename.indexOf("."));
               _fullname  = _path  + _filenamex;
               _fp = new File(_fullname );
               _cnt++;
           }

           FileOutputStream _os = new FileOutputStream(_fp);
           _os.write(inbyte);
           _os.close();

           return _fullname;
       }
       public String  makeFileOverwirte(String _path, String _filename , Object inbyteobj) throws Exception{
           byte[] inbyte = (byte[])inbyteobj;
           String _fullname = "";
           _path = _path.trim();
           _filename = _filename.trim();
           if(!_path.substring(_path.length()-1).equals("/")) _path = _path +"/";
           _fullname = _path + _filename;
           File _fp = new File(_fullname);

           FileOutputStream _os = new FileOutputStream(_fp);
           _os.write(inbyte);
           _os.close();

           return _fullname;
       }
       public byte[] inputFileStream(String _path, String _filename) throws Exception{
           if(!_path.substring(_path.length()-1).equals("/")) _path = _path +"/";
           File _fp = new File(_path + _filename);
           int _size = (int)_fp.length();
           byte[] _rtn = new byte[_size];
           FileInputStream _is = new FileInputStream(_fp);
           _is.read(_rtn);
           _is.close();
           return _rtn;
       }

       public byte[] getFileStream(String _path, String _filename) throws Exception{
           File _fp = new File(_path + _filename);
           int _size = (int)_fp.length();
           byte[] _rtn = new byte[_size];
           FileInputStream _is = new FileInputStream(_fp);
           _is.read(_rtn);
           _is.close();
           return _rtn;
       }

       //--------------------------------------------------------------------------
       //------------------------------- VOBJ UTIL  -------------------------------
       //--------------------------------------------------------------------------
       public VOBJ getMergeObject(DOBJ _6185, String _4271objects, String _4271columns) throws Exception{
           String[] _INOBJNMS = _4271objects.split(",");
           String[] _INCOLNMS = null;
           VOBJ _INVOBJ = null;
           VOBJ _2369VOBJ = new VOBJ();
           HashMap _2564 = null;
           String _4271objnm="";
           String _4271colnm="";
           if(!_4271columns.trim().equals("")){
               _INCOLNMS = _4271columns.trim().split(",");
               for(int i=0;i<_INOBJNMS.length;i++){
                   _4271objnm = _3806(_INOBJNMS[i].trim());
                   if(_6185.containKey(_4271objnm)){
                       _INVOBJ = _6185.getRetObject(_4271objnm);
                       _INVOBJ.first();
                       while(_INVOBJ.next()){
                           _2564 = new HashMap();
                           for(int ii=0;ii<_INCOLNMS.length;ii++){
                               _4271colnm =_3806(_INCOLNMS[ii].trim());
                               _2564.put(_4271colnm,_INVOBJ.getRecord().get(_4271colnm));
                           }
                           _2369VOBJ.addRecord(_2564);
                       }
                   }
               }

           }else{

               boolean _5864 = true;
               ArrayList _mlist = new ArrayList();
               ArrayList _ilist = null;
               if(_INOBJNMS.length > 1 ){
                   for(int i=0;i<_INOBJNMS.length;i++){
                       _4271objnm = _3806(_INOBJNMS[i].trim());

                       if(_6185.containKey(_4271objnm)){
                           _INVOBJ = _6185.getRetObject(_4271objnm);
                           if(_INVOBJ.getRecordCnt()!=0){
                               if(_5864==true){
                                   _mlist = _INVOBJ.getColumnNames();
                                   _5864= false;
                               }else {
                                   _ilist = _INVOBJ.getColumnNames();
                                   _mlist = getMergeColumnnames(_mlist, _ilist);

                               }
                           }
                       }
                   }

                   for(int i=0;i<_INOBJNMS.length;i++){
                       _4271objnm = _3806(_INOBJNMS[i].trim());
                       if(_6185.containKey(_4271objnm)){
                           _INVOBJ = _6185.getRetObject(_4271objnm);
                           _INVOBJ.first();
                           while(_INVOBJ.next()){
                               _2564 = new HashMap();
                               for(int ii=0;ii<_mlist.size();ii++){
                                   _2564.put(_mlist.get(ii),_INVOBJ.getRecord().get(_mlist.get(ii)));
                               }
                               _2369VOBJ.addRecord(_2564);
                           }
                       }
                   }

               }else {
                   _4271objnm = _3806(_INOBJNMS[0].trim());
                   if(_6185.containKey(_4271objnm)){
                       _2369VOBJ = _6185.getRetObject(_4271objnm);
                   }
               }
           }

           return _2369VOBJ;
       }

       private ArrayList getMergeColumnnames(ArrayList _mlist , ArrayList _ilist){
           ArrayList _rlist = new ArrayList();
           String _mname ="";
           String _4271ame="";
           for(int i=0;i<_mlist.size();i++){
               _mname = _mlist.get(i).toString();
               for(int ii=0;ii<_ilist.size();ii++){
                   _4271ame = _ilist.get(ii).toString();
                   if(_mname.equals(_4271ame)){
                       _rlist.add(_mname);
                       break;
                   }
               }
           }
           return _rlist;
       }

       //      rvobj = wutil.getAddedVobj(dobj,"ADD11","IUDFLAG, MENUCD", dvobj );
       public VOBJ getAddedVobj(DOBJ _6185, String _Objectid, String _4271columns , VOBJ _4040){

           VOBJ _164 = null;
           String[] _4271colnames=null;
           String _671 ="";
           if(_6185.containKey(_Objectid)) {
               _164 = _6185.getRetObject(_Objectid);
               if(_4271columns!=null && !_4271columns.trim().equals("")){
                   _4271colnames = _4271columns.split(",");
               }else{
                   ArrayList _mlist = this.getMergeColumnnames(_164.getColumnNames(),_4040.getColumnNames());
                   _4271colnames = new String[_mlist.size()];
                   for(int i=0;i<_mlist.size();i++){
                       _4271colnames[i] = _mlist.get(i).toString();
                   }
               }

               HashMap _2564 = null;

               int _addcnt=0;
               _4040.first();
               while(_4040.next()){
                   _2564 = new HashMap();
                   for(int i=0;i<_4271colnames.length;i++){
                       _671 = _3806(_4271colnames[i]).trim();
                       if(_4040.ContainsColumnName(_671)){
                           _addcnt++;
                           _2564.put(_671,_4040.getRecord().get(_671));
                       }

                   }
                   if(_addcnt > 0)   _164.addRecord(_2564);
               }

           }else{
               _164 = new VOBJ();
               if(_4271columns!=null && !_4271columns.trim().equals("")){
                   _4271colnames = _4271columns.split(",");
                   HashMap _2564 = null;
                   int _addcnt=0;
                   _4040.first();
                   while(_4040.next()){
                       _2564 = new HashMap();
                       for(int i=0;i<_4271colnames.length;i++){
                           _671= _3806(_4271colnames[i]).trim();
                           if(_4040.ContainsColumnName(_671)){
                               _addcnt++;
                               _2564.put(_671,_4040.getRecord().get(_671));
                           }
                       }
                       if(_addcnt > 0)  _164.addRecord(_2564);
                   }
               }else{
                   _164 = _4040;
               }
           }

           _164.setName(_Objectid);
           return _164;
       }


       //-------------------------------------------------------------------------------------------
       //-------------------------  수치 DATA ROUND OR 작삭처리 기능 ---------------------------------
       //-------------------------------------------------------------------------------------------
       //sql처리문에서만 사용되어 진다 double일때 round없이 format에 의한 절삭 처리를 한다.
       //----_2390.equals("1"):  round처리   ,   _2390.equals("2") : 절삭처리
       //----_2390.equals("1"):  round처리   ,   _2390.equals("2") : 절삭처리
       public String getRoundFormat( String _6161, String _305 ) throws Exception {  //SQ0220
           if(!this.isNumber(_305)) return _305;
           String _2366="";
           _2366 = this.getNumberFormat(_6161, Double.parseDouble(_305));
           return _2366;
       }
       public String getRoundFormat( String _6161, double _305 ) throws Exception {  //SQ0220
           String _2366="";
           _2366 = this.getNumberFormat(_6161, _305);
           return _2366;
       }
       public String getRoundFormat( String _6161, int _305 ) throws Exception {  //SQ0220
           String _2366="";
           _2366 = this.getNumberFormat(_6161, _305);
           return _2366;
       }
       public double getRoundNumberFormat( String _6161, double _305 ) throws Exception {  //SQ0220
           String _2366="";
           _2366 = this.getNumberFormat(_6161, _305);
           if(_2366 ==null || _2366.equals("")) return 0;
           return Double.parseDouble(_2366);
       }
       public double getRoundNumberFormat( String _6161, String _doublestr ) throws Exception {  //SQ0220
          String _2366="";
          double _305=0;
          if(_doublestr != null && !_doublestr.trim().equals(""))
          {
              _305=Double.parseDouble(_doublestr);
          }
          _2366 = this.getNumberFormat(_6161, _305);
          if(_2366 ==null || _2366.equals("")) return 0;
          return Double.parseDouble(_2366);
      }
      public double getRoundNumberFormat( String _6161, Object _doubleobj ) throws Exception
      {
          String _doublestr="";
          if(_doubleobj != null )
         {
           _doublestr =  _doubleobj.toString();
         }
          return getRoundNumberFormat(_6161, _doublestr);
      }
       /*
       public String getNotRoundFormat(String _6161,String _305 ) throws Exception {
           int _4307=0;
           if(this.isNumber(_305)) return _305;
           String _2366="";
           if(_6161.indexOf(".") != -1) {
               int pin = _6161.length() - _6161.indexOf(".");

               if( _305.indexOf(".") != -1){
                   _4307 = _305.length() - _305.indexOf(".");
                   if( pin >  _4307 ) pin = _4307 ;
                   _2366 = _305.substring(0, _305.indexOf(".")) + _305.substring(_305.indexOf("."),_305.indexOf(".")+pin );

               }else{
                   _2366 = _305;
               }

           }else{
               if( _305.indexOf(".") != -1){
                   _2366 = _305.substring(0,_305.indexOf("."));
               }else{
                   _2366 = _305;
               }
           }
           return _2366;
       }
       */

       public String getNotRoundFormat(String _6161,String _305 ) throws Exception {
           String _2366="";
           double _2983=0;
           if(!this.isNumber(_305)) return _305;
           _2983 = Double.parseDouble(_305);
           _2366 =getNotRoundFormat(_6161,_2983 );
           return _2366;
       }

       public String getNotRoundFormat(String _6161,double _305 ) throws Exception {
           String _2366="";
           if(_6161.indexOf(".")!= -1)
           {
               int _873 = _6161.length() - _6161.indexOf(".") ;
               _2366 = this.getNumberFormat(_6161+"#", _305);
               if(_2366 != null && _2366.length() > 1){
                   if(_2366.indexOf(".") != -1)
                   {
                       if( (_2366.length() - _2366.indexOf(".") ) > _873) {
                           _2366 = _2366.substring(0,_2366.length()-1);
                       }
                   }
               }
           }else {
               _2366 = this.getNumberFormat(_6161, _305);
           }
           return _2366;
       }

       public String getNotRoundFormat(String _6161,int _305 ) throws Exception {
           String _2366="";
           if(_6161.indexOf(".")!= -1)
           {
               int _873 = _6161.length() - _6161.indexOf(".") ;
               _2366 = this.getNumberFormat(_6161+"#", _305);

               if(_2366 != null && _2366.length() > 1){
                   if(_2366.indexOf(".") != -1)
                   {
                       if( (_2366.length() - _2366.indexOf(".") ) > _873) {
                           _2366 = _2366.substring(0,_2366.length()-1);
                       }
                   }
               }
           }else {
               _2366 = this.getNumberFormat(_6161, _305);
           }
           return _2366;
       }

       public double getNotRoundNumberFormat(String _6161, double _305 ) throws Exception {
           String _2366="";
           if(_6161.indexOf(".")!= -1)
           {
               int _873 = _6161.length() - _6161.indexOf(".") ;
               _2366 = this.getNumberFormat(_6161+"#", _305);
               if(_2366 != null && _2366.length() > 1){
                   if(_2366.indexOf(".") != -1)
                   {
                       if( (_2366.length() - _2366.indexOf(".") ) > _873) {
                           _2366 = _2366.substring(0,_2366.length()-1);
                       }
                   }
               }
           }else {
               _2366 = this.getNumberFormat(_6161, _305);
           }
           if(_2366 == null || _2366.trim().equals("")) _2366 ="0";
           return  Double.parseDouble(_2366);
       }

       public String getNumberFormat(String _71, double value) throws Exception{   //SQ0560
           String _65 ="";
           java.text.DecimalFormat _df = new java.text.DecimalFormat(_71);
           _65 = _df.format(value);
           return _65;
       }
       public boolean isNumber(String s) throws Exception{
           boolean _2366=false;
           try{
               Double.parseDouble(s);
               _2366 = true;
           }catch(NumberFormatException e){
           }
           return _2366;
       }
       //------------UserFunction Call
       //DOBJ , VOBJ 의 CASTING 오류 문제 OPEN 소스와의 문제점이 있다 DOBJ,VOBJ를 COPY처리 하는 기능이 필요하다.(this._6734DOBJ)기능 사용할 것
       //open DOBJ, VOBJ 구성이 시급하다. 내일 구성 완료 할 것
       public Object callUserFunc(String _3068, String _7151, String _3554, String intype,  ArrayList _3062eters) throws Exception{
           Object _2366      ="";

           StringTokenizer _1079 = new StringTokenizer(intype,",");
           ArrayList _7136 = new ArrayList();
           while(_1079.hasMoreTokens()){
               _7136.add(_1079.nextToken().trim().toUpperCase());
           }

           Object _7454[] =null;
           Class[] _7133 = null;
           if(_3062eters!= null && _3062eters.size() !=0){

               _7454 = new Object[_3062eters.size()];
               _7133 = new Class[_3062eters.size()];
               Object _461 = null;
               for(int i=0;i<_3062eters.size();i++){
                   _461 = _7136.get(i);

                   if(_461.equals("STRING")){
                      // System.out.println(_7151+":"+ _3554+":"+_3062eters.size()+":"+ _461 +":" + i +":" + _7133.length);
                       _7133[i] =  new String().getClass();
                       _7454[i] = _3062eters.get(i).toString();
                   }else if(_461.equals("INTEGER")){
                       _7133[i] =  new Integer(0).getClass();
                       if(_3062eters.get(i).toString().equals("")) {
                           _7454[i] = new Integer(0);
                       }else {
                           _7454[i] =  new Integer(_3062eters.get(i).toString());
                       }
                   }else if(_461.equals("DOUBLE")){
                       _7133[i] = new Double(0).getClass();
                       if(_3062eters.get(i).toString().equals("")) {
                           _7454[i] = new Double(0);
                       }else {
                           _7454[i] = new Double(_3062eters.get(i).toString());
                       }
                   }
               }
           }

           Class _6893 = null;
           try {
               _6893 = Class.forName(_3068+"."+_7151);
           } catch (ClassNotFoundException ex) {
               this._19gerr("OBJECT NOT FOUND:"+ _3068 +"."+_7151,ex);
               ex.printStackTrace();
               throw new Exception("OBJECT NOT FOUND:"+ _3068 +"."+_7151);
           }

           try{
               Method _3545 = _6893.newInstance().getClass().getMethod(_3554, _7133);
               _2366 =_3545.invoke(_6893.newInstance(),_7454);

           }catch(Exception e){
                this._19gerr("OBJECT  INVOKE ERROR:"+ _3068 +"."+_7151 +"." + _3554 ,e);
               e.printStackTrace();
               throw new Exception("OBJECT  INVOKE ERROR:"+ _3068 +"."+_7151 +"." + _3554 );
           }
           return _2366;
       }


       public Object callUserFunc(String _3068, String _7151, String _3554, ArrayList _3062eters) throws Exception{
           Object _2366      ="";
           Object _7454[] =null;
           Class[] _7133 = null;
           if(_3062eters!= null && _3062eters.size() !=0){
               _7454 = new Object[_3062eters.size()];
               _7133 = new Class[_3062eters.size()];
               Object _461 = null;
               for(int i=0;i<_3062eters.size();i++){
                   _461 = _3062eters.get(i);
                   if(_461 instanceof String){
                       _7133[i] =  new String().getClass();
                       _7454[i] = _3062eters.get(i).toString();
                   }else if(_461 instanceof Integer){
                       _7133[i] =  new Integer(0).getClass();
                       _7454[i] =  new Integer(_3062eters.get(i).toString());
                   }else if(_461 instanceof Double){
                       _7133[i] = new Double(0).getClass();
                       _7454[i] = new Double(_3062eters.get(i).toString());
                   }
               }
           }

           Class _6893 = null;
           try {
               _6893 = Class.forName(_3068+"."+_7151);
           } catch (ClassNotFoundException ex) {
               this._19gerr("OBJECT NOT FOUND:"+ _3068 +"."+_7151,ex);
               ex.printStackTrace();
               throw new Exception("OBJECT NOT FOUND:"+ _3068 +"."+_7151);
           }

           try{
               Method _3545 = _6893.newInstance().getClass().getMethod(_3554, _7133);
               _2366 =_3545.invoke(_6893.newInstance(),_7454);
           }catch(Exception e){
               this._19gerr("OBJECT  INVOKE ERROR:"+ _3068 +"."+_7151 +"." + _3554 ,e);
               e.printStackTrace();
               throw new Exception("OBJECT  INVOKE ERROR:"+ _3068 +"."+_7151 +"." + _3554 );
           }
           return _2366;
       }


       public Object callUserFunc(String _3068, String _7151, String _3554, ArrayList _3062eters, DOBJ _6185) throws Exception
       {
           Object _2366      ="";
           Object _7454[] =null;
           Class[] _7133 = null;
           if(_3062eters!= null && _3062eters.size() !=0){
               _7454 = new Object[_3062eters.size()];
               _7133 = new Class[_3062eters.size()];
               Object _461 = null;
               _7133[0] =   _6185.getClass();
               _7454[0] = _6185;
               int j=0;
               for(int i=1;i<_3062eters.size()+1;i++){
                   _461 = _3062eters.get(i);
                   if(_461 instanceof String){
                       _7133[i] =  new String().getClass();
                       _7454[i] = _3062eters.get(j).toString();
                       j++;

                   }else if(_461 instanceof Integer){
                       _7133[i] =  new Integer(0).getClass();
                       _7454[i] =  new Integer(_3062eters.get(j).toString());
                       j++;

                   }else if(_461 instanceof Double){
                       _7133[i] = new Double(0).getClass();
                       _7454[i] = new Double(_3062eters.get(j).toString());
                       j++;
                   }else {
                       _7133[i] =  new String().getClass();
                       _7454[i] = _3062eters.get(j).toString();
                       j++;
                   }
               }
           }

           Class _6893 = null;
           try {
               _6893 = Class.forName(_3068+"."+_7151);
           } catch (ClassNotFoundException ex) {
               this._19gerr("OBJECT NOT FOUND:"+ _3068 +"."+_7151 ,ex);
               ex.printStackTrace();
               throw new Exception("OBJECT NOT FOUND:"+ _3068 +"."+_7151);
           }

           try{
               Method _3545 = _6893.newInstance().getClass().getMethod(_3554, _7133);
               _2366 =_3545.invoke(_6893.newInstance(),_7454);
           }catch(Exception e){
               this._19gerr("OBJECT  INVOKE ERROR:"+ _3068 +"."+_7151 +"." + _3554 ,e);
               e.printStackTrace();
               throw new Exception("OBJECT  INVOKE ERROR:"+ _3068 +"."+_7151 +"." + _3554 );
           }

           return _2366;

       }


       public Object callUserFunc(String _3068, String _7151, String _3554, String intype, ArrayList _parms, DOBJ _6185) throws Exception
       {
           Object _2366      ="";

           StringTokenizer _1079 = new StringTokenizer(intype,",");
           ArrayList _innames = new ArrayList();
           while(_1079.hasMoreTokens())
           {
               _innames.add(_1079.nextToken().trim().toUpperCase());
           }

           Object _inObjs[] =null;
           Class[] _inClass = null;
           if(_parms!= null && _parms.size() !=0)
           {
               _inObjs = new Object[_parms.size()+1];
               _inClass = new Class[_parms.size()+1];
               _inClass[0] =   _6185.getClass();
               _inObjs[0] = _6185;
               int j=0;
               for(int i=1;i<_parms.size()+1;i++)
               {
                   if(_innames.get(i).toString().equals("STRING"))
                   {
                       _inClass[i] =  new String().getClass();
                       _inObjs[i] = _parms.get(j).toString();
                   }
                   else if(_innames.get(i).toString().equals("INTEGER"))
                   {
                       _inClass[i] =  new Integer(0).getClass();
                       if(_parms.get(j).toString().equals("")) {
                           _inObjs[i] = new Integer(0);
                       }else {
                           _inObjs[i] =  new Integer(_parms.get(j).toString());
                       }
                   }
                   else if(_innames.get(i).toString().equals("DOUBLE"))
                   {
                       _inClass[i] = new Double(0).getClass();
                       if(_parms.get(i).toString().equals("")) {
                           _inObjs[i] = new Double(0);
                       }else {
                           _inObjs[i] = new Double(_parms.get(j).toString());
                       }
                   }
                   else
                   {
                       _inClass[i] =  new String().getClass();
                       _inObjs[i] = _parms.get(j).toString();
                   }
                   j++;
               }
           }

           Class _6893 = null;
           try {
               _6893 = Class.forName(_3068+"."+_7151);
           } catch (ClassNotFoundException ex) {
               this._19gerr("OBJECT NOT FOUND:"+ _3068 +"."+_7151 ,ex);
               ex.printStackTrace();
               throw new Exception("OBJECT NOT FOUND:"+ _3068 +"."+_7151);
           }
           try{
               Method _3545 = _6893.newInstance().getClass().getMethod(_3554, _inClass);
               _2366 =_3545.invoke(_6893.newInstance(),_inObjs);
           }catch(Exception e){
               this._19gerr("OBJECT  INVOKE ERROR:"+ _3068 +"."+_7151 +"." + _3554 ,e);
               e.printStackTrace();
               throw new Exception("OBJECT  INVOKE ERROR:"+ _3068 +"."+_7151 +"." + _3554 );
           }
           return _2366;
       }
       //------------------------------
       // distinct , duplicate , sort
       //-----------------------------------------------------------------------------------------------
       public VOBJ getDistinct(VOBJ _167, String[] _6992 )  throws Exception {   //VU0140
           VOBJ _2303 = null;
           if(this._6755(_167,_6992)){
               _2303 = _5396(_167,_6992);
               _2303.setAlias(_167.getAlias());
               _2303.setName(_167.getName());
               return _2303;
           }else{
               return _167;
           }
       }
       public VOBJ getSort_BK(String[] _3374, VOBJ _227){
           VOBJ _2366 = new VOBJ();
           _2366.setRecords(_1709(_3374, _227));

           _2366.setAlias(_227.getAlias());
           _2366.setName(_227.getName());
           return _2366;
       }
       public VOBJ getSort(VOBJ _vobj, String[] _name){
           VOBJ _rtn = new VOBJ();
             _rtn.setRecords(_setMergeSort(_name,_vobj));
           _rtn.setAlias(_vobj.getAlias());
           _rtn.setName(_vobj.getName());
           return _rtn;
       }


       public VOBJ getDuplication(VOBJ _167, String[] _6992 )  throws Exception {   //VU0160
           VOBJ _2303 = null;
           if(this._6755(_167,_6992)){
               _2303 = _5357(_167,_6992);
               _2303.setAlias(_167.getAlias());
               _2303.setName(_167.getName());
               return _2303;
           }else{
               this._19ginfo("Duplication 처리 대상 Column명이 해당 Object에 존재 하지 않습니다.");
               throw new Exception("Duplication 처리 대상 Column명이 해당 Object에 존재 하지 않습니다.");
           }
       }

       private VOBJ _5396(VOBJ _167 , String[] _6992)  throws Exception {    //VU0150
           HashMap _2588 = null;
           HashMap _2582 = null;
           String _6959 = null;
           String _6938 = null;
           VOBJ _158 = new VOBJ();
           ArrayList _2555 = new ArrayList();
           ArrayList _2558 = _167.getRecords();

           if(_2558.size() > 0 ) {
               VOBJ _1283 = new VOBJ();
               _1283.setRecords(_2558);
               String[] _1262 = _6992;
               ArrayList _1268 = _1709(_1262, _1283);

               int ii = 0;
               for (int i = 0; i < _1268.size(); i++) {
                   _2588 = (HashMap) _1268.get(i);
                   _6959="";
                   for(int ix=0;ix<_6992.length;ix++)
                       _6959 = _6959 + _2588.get(_6992[ix]).toString().trim();

                   _2555.add(_2588);
                   for (ii = i + 1; ii < _1268.size(); ii++) {
                       _2582 = (HashMap) _1268.get(ii);
                       _6938="";
                       for(int ix=0;ix<_6992.length;ix++)
                           _6938 = _6938 + _2582.get(_6992[ix]).toString().trim();

                       if (!_6959.equals(_6938)) {
                           break;
                       }
                   }
                   i = ii - 1;
               }
               _158.setRecords(_2555);
           }

           return _158;
       }

       public boolean _6755(VOBJ _167, String[] _3338)  throws Exception {   //VU0190
           boolean _2366 = true;
           for(int i=0;i<_3338.length;i++) {
               if (!_167.ContainsColumnName(_3338[i])) {
                   _2366 = false;
                   break;
               }
           }
           return _2366;
       }
       private VOBJ _5357(VOBJ _167, String[] _6992)  throws Exception {  //VU0170

           HashMap _2588 = null;
           HashMap _2582 = null;
           String _6959 = null;
           String _6938 = null;
           boolean _5879 = true;
           VOBJ _158 = new VOBJ();
           ArrayList _2555 = new ArrayList();
           ArrayList _2558 = _167.getRecords();

           if(_2558.size() > 0 ) {
               VOBJ _1283 = new VOBJ();
               _1283.setRecords(_2558);
               String[] _1262 = _6992;
               ArrayList _1268 = _1709(_1262, _1283);

               int ii = 0;
               for (int i = 0; i < _1268.size(); i++) {
                   _5879 = true;
                   _2588 = (HashMap) _1268.get(i);
                   _6959="";
                   for(int ix=0;ix<_6992.length;ix++)
                       _6959 = _6959+ _2588.get(_6992[ix]).toString().trim();

                   for (ii = i + 1; ii < _1268.size(); ii++) {
                       _2582 = (HashMap) _1268.get(ii);
                       _6938="";
                       for(int ix=0;ix<_6992.length;ix++)
                           _6938 = _6938 + _2582.get(_6992[ix]).toString().trim();

                       if (_6959.equals(_6938)) {
                           if (_5879 == true) {
                               _5879 = false;
                               _2555.add(_2588);
                               _2555.add(_2582);
                           }
                           else {
                               _2555.add(_2582);
                           }
                       }
                       else {
                           break;
                       }
                   }

                   i = ii - 1;
               }
               _158.setRecords(_2555);
           }
           return _158;
       }

       private ArrayList _1709(String[] _3338, VOBJ _227){
           ArrayList _2558 = _227.getRecords();
           String[][] _284 = _5060(_3338, _227);
           _284 = CommUtil._1259(_284);
           ArrayList _1283 = new ArrayList();
           int _2579=0;
           for(int i=0;i<_284.length;i++){
               _2579 = Integer.parseInt(_284[i][1]);
               _1283.add(_2558.get(_2579));
           }
           return _1283;
       }
       private ArrayList _setMergeSort(String[] _names, VOBJ _vobj){
           ArrayList _records = _vobj.getRecords();
           //System.out.println("bbbbbbbb :" + _vobj.getRecordCnt());
           String[][] _vals = _getMergeKeyColumns(_names, _vobj);
           _vals = CommUtil._1259(_vals);
           ArrayList _sorted = new ArrayList();
           int _recno=0;
           for(int i=0;i<_vals.length;i++){
               _recno = Integer.parseInt(_vals[i][1]);
               _sorted.add(_records.get(_recno));
           }
           return _sorted;
       }
       public String[][] _getMergeKeyColumns(String[] _names, VOBJ _vobj){

                  ArrayList _records = _vobj.getRecords();
                  HashMap   _maxlen  = null;

                  String[][] _columns = new String[_vobj.getRecordCnt()][2];
                  int[] _maxsize = new int[_names.length];
                  //System.out.println("aaaaaaaaaaaa :" + _vobj.getRecordCnt());
                  _maxlen= _setColumnMaxLength(_names, _vobj);

                  HashMap _rec = null;
                  for(int i=0;i<_names.length;i++){
                      if(_maxlen.get(_names[i])== null || _maxlen.get(_names[i]).toString().trim().equals("")) _maxsize[i] =0;
                      else _maxsize[i] = Integer.parseInt(_maxlen.get(_names[i]).toString());
                  }

                  String _tmpVals=null;
                  String _colvals = null;
                  for(int i=0;i<_records.size();i++){
                      _rec = (HashMap)_records.get(i);
                      _tmpVals = new String();
                      for(int ii=0;ii<_names.length;ii++) {
                          if(_rec.get(_names[ii]) != null) {
                              _colvals = _rec.get(_names[ii]).toString();
                          }else {
                              _colvals="";
                          }
                          _tmpVals = _tmpVals + _getSpaceAdd(_maxsize[ii],_colvals );
                      }

                      _columns[i][0] = _tmpVals;
                      _columns[i][1] = i+"";
                  }
                  return _columns;
              }

              public HashMap _setColumnMaxLength(String[] names, VOBJ _vobj){
                  HashMap _maxlen = new HashMap();
                  String _tmpval = null;
                  String[] _colvals=null;
                  int max=0;
                  for(int i=0;i<names.length;i++){
                      _colvals = this._getColumnValue(names[i], _vobj);
                      max = this._getMaxlen(_colvals);
                      _maxlen.put(names[i],max+"");
                  }
                  return _maxlen;
              }

              public String[] _getColumnValue(String _column , VOBJ _vobj){

                  ArrayList _records = _vobj.getRecords();
                  String[] _vals = null;
                  HashMap _rec = null;
                  if(_records.size() > 0){
                      _vals = new String[_records.size()];
                      for(int i=0;i<_records.size();i++){
                          _rec = (HashMap)_records.get(i);
                          _vals[i] = _rec.get(_column).toString();
                      }
                  }
                  return _vals;
              }

              private int _getMaxlen(String[] _vals){

                  int _tmpMax=0;
                  for(int i=0;i<_vals.length;i++){
                      if(_vals[i].length() > _tmpMax){
                          _tmpMax = _vals[i].length();
                      }
                  }
                  return _tmpMax;
              }

              public String _getSpaceAdd(int  _len, String _vals){
                  String _space="";

                  for(int i=0;i<_len-_vals.length();i++)
                      _space = _space + " " ;
                  _vals = _vals + _space;

                  return _vals;
              }

              public String _getSpaceZero(int  len, String _vals){
                  String _zero="";
                  for(int i=0;i<len-_vals.length();i++)
                      _zero = _zero + "0" ;

                  _vals = _zero + _vals;
                  return _vals;
              }








       public String[][] _5060(String[] _3338, VOBJ _227){

           ArrayList _2558 = _227.getRecords();
           HashMap   _3587  = null;

           String[][] _6923 = new String[_227.getRecordCnt()][2];
           int[] _3584 = new int[_3338.length];
           _3587= _1997(_3338, _227);

           HashMap _2594 = null;
           for(int i=0;i<_3338.length;i++){
               if(_3587.get(_3338[i])== null || _3587.get(_3338[i]).toString().trim().equals("")) _3584[i] =0;
               else _3584[i] = Integer.parseInt(_3587.get(_3338[i]).toString());
           }

           String _551=null;
           String _6902 = null;
           for(int i=0;i<_2558.size();i++){
               _2594 = (HashMap)_2558.get(i);

               _551 = new String();
               for(int ii=0;ii<_3338.length;ii++) {
                   if(_2594.get(_3338[ii]) != null) {
                       _6902 = _2594.get(_3338[ii]).toString();
                   }else {
                       _6902="";
                   }
                   _551 = _551 + _4676(_3584[ii],_6902 );
               }

               _6923[i][0] = _551;
               _6923[i][1] = i+"";
           }
           return _6923;
       }

       public HashMap _1997(String[] names, VOBJ _227){


           //for(int i=0;i<names.length;i++){
           //    System.out.println("length:::"+names.length +":" + names[i]);
           //}


           HashMap _3587 = new HashMap();
           String _554 = null;
           String[] _6902=null;
           int max=0;
           for(int i=0;i<names.length;i++){
               //System.out.println(names[i]);
               _6902 = this._5561(names[i], _227);
               max = this._5063(_6902);
               _3587.put(names[i],max+"");
           }
           return _3587;
       }

       public String[] _5561(String _6989 , VOBJ _227){
           ArrayList _2558 = _227.getRecords();
           //System.out.println("RECORDCNT:"+_227.getRecordCnt() );
           String[] _284 = null;
           HashMap _2594 = null;
           if(_2558.size() > 0){
               _284 = new String[_2558.size()];
               for(int i=0;i<_2558.size();i++){
                   _2594 = (HashMap)_2558.get(i);
                   _284[i] = _2594.get(_6989).toString();
               }
           }
           return _284;
       }

       private int _5063(String[] _284){

           int _683=0;
           for(int i=0;i<_284.length;i++){
               if(_284[i].length() > _683){
                   _683 = _284[i].length();
               }
           }
           return _683;
       }

       public String _4676(int  _3905, String _284){
           String _1238="";

           for(int i=0;i<_3905-_284.length();i++)
               _1238 = _1238 + " " ;
           _284 = _284 + _1238;

           return _284;
       }

       public String _4673(int  len, String _284){
           String _17="";
           for(int i=0;i<len-_284.length();i++)
               _17 = _17 + "0" ;

           _284 = _17 + _284;
           return _284;
       }


       //-------------------------------------------------------
       //-------------- SUM 기능  start
       //-------------------------------------------------------

       //-------------------------------------------------------
   //-------------- SUM 기능  start
   //-------------------------------------------------------
   public VOBJ getSUM(VOBJ _vOBY5, HashMap _suminfo)  throws Exception  {   //VU0250
       if(_vOBY5 == null || _vOBY5.getRecordCnt() == 0 ) return _vOBY5;

       String _gijun     = _suminfo.get("GJ").toString();
       String _sumcol    = _suminfo.get("SC").toString();
       String _sumtype   = _suminfo.get("ST").toString();

       String _roundtype = _suminfo.get("RT").toString();
       String _decimalformat =_suminfo.get("DF").toString();

       String[] _SUMGJ  = null;
       String[] _SUMCOLS= null;

       //StringTokenizer _GTOKEN = new StringTokenizer(_gijun,",");
       //StringTokenizer _STOKEN = new StringTokenizer(_sumcol,",");
       char[] _SUMFLAG = _sumtype.toCharArray();

       VOBJ _RTN=new VOBJ();
       VOBJ _vOBJY = null;
       int _ADDCNT=0;

       boolean _inaddedflag = false;
       int _LOOPCNT;
       VOBJ _SORTWORK = new VOBJ();
       if(_SUMFLAG[1]=='1'){   //소계 DATA ADD
           //_LOOPCNT=_GTOKEN.countTokens();
           //for(int i=0;i<_LOOPCNT;i++){
               _SUMGJ  =this._getCOLUMNNAMES(_gijun.trim());
               _SUMCOLS=this._getCOLUMNNAMES(_sumcol.trim());

               if(_inaddedflag==false && _SUMFLAG[0]=='1'){
                   _inaddedflag=true;
                   _ADDCNT=1;
                   _SORTWORK = this.getSort(_vOBY5, _SUMGJ  );
                   _SORTWORK.first();
                   while(_SORTWORK.next()){
                       _SORTWORK.getRecord().put("PARTCNT","0");
                       _RTN.addRecord(_SORTWORK.cpRecord());
                   }
               }

               _vOBJY =this._getPARTSUM(_vOBY5,_SUMGJ,_SUMCOLS,_roundtype,_decimalformat);
               //_vOBJY =this._getPARTSUM(_vOBY5,_SUMGJ,_SUMCOLS,_roundtype,_decimalformat);
               //_vOBJY.Println("SORTED XXX& SUM");

               _ADDSUMRECORD(_RTN, _vOBJY, _ADDCNT , _SUMFLAG[0]);
               //     _RTN._Println("소계 ADD SUM PART MID");
         //  }
       }

       if(_SUMFLAG[2]=='1'){   //전체 합계 DATA
           //_STOKEN = new StringTokenizer(_sumcol,":");
           _SUMCOLS=this._getCOLUMNNAMES(_sumcol.trim());

           if(_inaddedflag=false && _SUMFLAG[0]=='1'){
               _inaddedflag=true;
               _RTN.setRecords(_vOBY5.getRecords());
           }

           HashMap _TOTSUM = this._getTOTALSUM(_vOBY5, _SUMCOLS, _roundtype, _decimalformat);
           _TOTSUM.put("PARTCNT",_vOBY5.getRecordCnt()+"");
           _RTN.addRecord(_TOTSUM);
       }

       //   _RTN._Println("_TOTSUM ADD SUM PART MID");
       //--------------------------------  전체 SUM 추가  end
       return _RTN;
   }

   private String[] _getCOLUMNNAMES(String _COLSTR )  throws Exception {
       StringTokenizer _STOKEN = new StringTokenizer(_COLSTR,",");
       //System.out.println("SUM COLS:"+_COLSTR +":" + _STOKEN.countTokens());
       String[] _RTNSTR= new String[_STOKEN.countTokens()];
       int i=0;
       while(_STOKEN.hasMoreTokens()){
           _RTNSTR[i] = _STOKEN.nextToken().trim();
           i++;
       }
       return _RTNSTR;
   }

   private HashMap _getTOTALSUM(VOBJ _IN_VOBJX, String[] _SUMCOLS , String _roundtype, String _decimalformat) throws Exception {      //VU0230
       String _vals = "";
       double[] _TSUM = new double[_SUMCOLS.length];
       _IN_VOBJX.first();
       while(_IN_VOBJX.next()){
           for (int i = 0; i < _SUMCOLS.length; i++) {
               if(_IN_VOBJX.getRecord().get(_SUMCOLS[i]).equals("") ) _vals="0";
               else _vals = _IN_VOBJX.getRecord().get(_SUMCOLS[i]).toString();
               if(_vals==null || _vals.trim().equals("") ) _vals ="0";
               _TSUM[i] = _TSUM[i] + Double.parseDouble(_vals);
           }
       }

       HashMap _TOTSUM= _IN_VOBJX.cpRecord();
       ArrayList _TOTNAMES  = _IN_VOBJX.getColumnNames();
       boolean _exist=false;
       int _LOCORDER=0;
       for(int i=0;i<_TOTNAMES.size();i++){
           _exist=false;
           for(int ii=0;ii<_SUMCOLS.length;ii++){
               if(_TOTNAMES.get(i).toString().equals(_SUMCOLS[ii])){
                   _LOCORDER=ii;
                   _exist=true;
                   break;
               }
           }
           if(_exist==true){
               _TOTSUM.put(_TOTNAMES.get(i),_TSUM[_LOCORDER]+"");
           }else{
               _TOTSUM.put(_TOTNAMES.get(i),"");
           }
       }

       if(!_roundtype.equals("0")){
           String _SRdata="";
           for(int i=0;i<_SUMCOLS.length;i++){
               _SRdata=this._getRound(_roundtype, _decimalformat, _TOTSUM.get(_SUMCOLS[i]) );
               _TOTSUM.put(_SUMCOLS[i],_SRdata);
           }
       }

       return _TOTSUM;
   }

   private VOBJ _ADDSUMRECORD(VOBJ _vOBY6, VOBJ _vOBJY, int _tmplasthh4, char _SFLAG0 )  throws Exception {  //VU0240

       int _RECSUM=0;
       _vOBJY.first();
       while(_vOBJY.next()){
           if(_SFLAG0 =='1'){
               _RECSUM = _RECSUM +  Integer.parseInt(_vOBJY.getRecord().get("PARTCNT"));
               _vOBY6.addRecord(_RECSUM, _vOBJY.getRecord());
               _RECSUM = _RECSUM+_tmplasthh4;
           }else{
               _vOBY6.addRecord(_vOBJY.getRecord());
           }
       }
       return _vOBY6;
   }

   private VOBJ _getPARTSUM(VOBJ _IN_VOBJX, String[] _SUMGICOLNM, String[] _SUMCOLS , String _roundtype, String _decimalformat) throws Exception {      //VU0220

       //for(int x=0;x<_SUMGICOLNM.length;x++){
       //    System.out.println("GIJUN::"+_SUMGICOLNM[x]);
       //}
       //for(int x=0;x<_SUMCOLS.length;x++){
       //    System.out.println("SUM::"+_SUMCOLS[x]);
       //}

       VOBJ _vOBJY = new VOBJ();
       ArrayList _newRecords = new ArrayList();
       HashMap _newRec = null;

       HashMap _firstrec = null;
       HashMap _nextrec = null;
       String _firstStr = "", _tmpfirst = "";
       String _nextStr = "" , _tmpnext = "";
       String _vals="";
       VOBJ _vOBJYs = this.getSort(_IN_VOBJX, _SUMGICOLNM );

       ArrayList _sortedrecords = _vOBJYs.getRecords();

       double[] _sum = new double[_SUMCOLS.length];
       boolean _firstflag = false;
       int _sumCnt=1;
       for (int i = 0; i < _sortedrecords.size() - 1; i++) {
           _firstrec = (HashMap) _sortedrecords.get(i);
           _nextrec = (HashMap) _sortedrecords.get(i + 1);
           _firstStr = "";
           _tmpfirst = "";
           _nextStr = "";
           _tmpnext = "";
           for (int ii = 0; ii < _SUMGICOLNM.length; ii++) {
               if (_firstrec.get(_SUMGICOLNM[ii]) != null)
                   _tmpfirst = _firstrec.get(_SUMGICOLNM[ii]).toString().trim();
               else
                   _tmpfirst = "";

               if (_nextrec.get(_SUMGICOLNM[ii]) != null)
                   _tmpnext = _nextrec.get(_SUMGICOLNM[ii]).toString().trim();
               else
                   _tmpnext = "";

               _firstStr = _firstStr + _tmpfirst;
               _nextStr = _nextStr + _tmpnext;
           }

           if (_firstStr.equals(_nextStr)) {
               _sumCnt = _sumCnt + 1;
               for (int ii = 0; ii < _SUMCOLS.length; ii++) {
                   if(_firstrec.get(_SUMCOLS[ii])==null ||  _firstrec.get(_SUMCOLS[ii]).equals("") ) _vals="0";
                   else _vals = _firstrec.get(_SUMCOLS[ii]).toString();
                    if(_vals == null || _vals.trim().equals("")) _vals="0";
                   _sum[ii] = _sum[ii] +  Double.parseDouble(_vals);
               }
           } else {
               _newRec = new HashMap();
               for (int ii = 0; ii < _IN_VOBJX.getColumnNames().size(); ii++) {
                   _newRec.put(_IN_VOBJX.getColumnNames().get(ii), _firstrec.get(_IN_VOBJX.getColumnNames().get(ii)));
               }

               for (int ii = 0; ii < _SUMCOLS.length; ii++) {
                   if(_firstrec.get(_SUMCOLS[ii])==null || _firstrec.get(_SUMCOLS[ii]).equals("") ) _vals="0";
                   else _vals = _firstrec.get(_SUMCOLS[ii]).toString();
                   if(_vals == null || _vals.trim().equals("")) _vals="0";
                   _sum[ii] = _sum[ii] + Double.parseDouble(_vals);
               }

               for (int ii = 0; ii < _SUMCOLS.length; ii++) {
                   _newRec.put(_SUMCOLS[ii], _sum[ii] + "");
                   _sum[ii] = 0;
               }
               _newRec.put("PARTCNT", _sumCnt + "");
               _newRecords.add(_newRec);
               _sumCnt=1;
           }

           if (i == _sortedrecords.size() - 2) {
               if (_firstflag == false) {

                   _newRec = new HashMap();
                   for (int ii = 0; ii < _IN_VOBJX.getColumnNames().size(); ii++) {
                       _newRec.put(_IN_VOBJX.getColumnNames().get(ii), _nextrec.get(_IN_VOBJX.getColumnNames().get(ii)));
                   }

                   for (int ii = 0; ii < _SUMCOLS.length; ii++) {
                       if(_firstrec.get(_SUMCOLS[ii])==null ||  _nextrec.get(_SUMCOLS[ii]).equals("") ) _vals="0";
                       else _vals = _nextrec.get(_SUMCOLS[ii]).toString();
                       if(_vals == null || _vals.trim().equals("")) _vals="0";
                       _sum[ii] = _sum[ii] + Double.parseDouble(_vals);
                   }

                   for (int ii = 0; ii < _SUMCOLS.length; ii++) {
                       _newRec.put(_SUMCOLS[ii], _sum[ii] + "");
                       _sum[ii] = 0;
                   }
               }

               _newRec.put("PARTCNT", _sumCnt + "");
               _newRecords.add(_newRec);
           }
       }

       _vOBJY.setRecords(_newRecords);
       if(!_roundtype.equals("0"))
       {
           String _SRdata="";
           _vOBJY.first();
           while(_vOBJY.next())
           {
               for(int i=0;i<_SUMCOLS.length;i++)
               {
                   _SRdata=this._getRound(_roundtype, _decimalformat, _vOBJY.getRecord().get(_SUMCOLS[i]));
                   _vOBJY.getRecord().put(_SUMCOLS[i],_SRdata);
               }
           }
       }
       return _vOBJY;
   }

   public double getDouble(Object _valx,  String _round, String _dpin ) throws Exception
   {
       String _val="";
       if(_valx == null) _val ="";
       else _val = _valx.toString();

       int _idxtmp=0;
       if(!_isNumber(_val)) return 0;
       String _rtn="";
       if(_round.equals("up"))
       {                                          //-- round처리
           _rtn = getNumberFormat(_dpin,Double.parseDouble(_val));
       }
       else if(_round.equals("dn"))
       {                                    //-- 절삭처리
           _val = getNumberFormat("###########.000000",Double.parseDouble(_val));
           if(_dpin.indexOf(".") != -1)
           {
               int pin = _dpin.length() - _dpin.indexOf(".");
               if( _val.indexOf(".") != -1)
               {
                   _idxtmp = _val.length() - _val.indexOf(".");
                   if( pin >  _idxtmp ) pin = _idxtmp ;
                   _rtn = _val.substring(0, _val.indexOf(".")) + _val.substring(_val.indexOf("."),_val.indexOf(".")+pin );
               }
               else
               {
                   _rtn = _val;
               }
           }
           else
           {
               if( _val.indexOf(".") != -1)
               {
                   _rtn = _val.substring(0,_val.indexOf("."));
               }
               else
               {
                   _rtn = _val;
               }
           }
       }
       else
       {
           _rtn = _val;
       }
       if(_rtn.trim().equals("")) return 0;
       return Double.parseDouble(_rtn);
   }


   public String _getRound(String _round, String _dpin, Object _valx ) throws Exception {  //SQ0220

       String _val="";
       if(_valx == null) _val ="";
       else _val = _valx.toString();

       int _idxtmp=0;
       if(!_isNumber(_val)) return _val;
       String _rtn="";
       if(_round.equals("1")){                                          //-- round처리

           _rtn = getNumberFormat(_dpin,Double.parseDouble(_val));
       }else if(_round.equals("2")){                                    //-- 절삭처리
           if(_dpin.indexOf(".") != -1) {
               int pin = _dpin.length() - _dpin.indexOf(".");
               if( _val.indexOf(".") != -1){
                   _idxtmp = _val.length() - _val.indexOf(".");
                   if( pin >  _idxtmp ) pin = _idxtmp ;
                   _rtn = _val.substring(0, _val.indexOf(".")) + _val.substring(_val.indexOf("."),_val.indexOf(".")+pin );
               }else{
                   _rtn = _val;
               }
           }else{
               if( _val.indexOf(".") != -1){
                   _rtn = _val.substring(0,_val.indexOf("."));
               }else{
                   _rtn = _val;
               }
           }
       }else{
           _rtn = _val;
       }

       return _rtn;
   }

   private boolean _isNumber(String s) throws Exception{    //SQ0470
       boolean _rtn=false;
       try{
           Double.parseDouble(s);
           _rtn = true;
       }catch(NumberFormatException e){}
       return _rtn;
   }


       public VOBJ getSUM_bk(VOBJ _vOBY5, HashMap _980info)  throws Exception  {   //VU0250

           if(_vOBY5 == null || _vOBY5.getRecordCnt() ==0) return _vOBY5;

         String _4415     = _980info.get("GJ").toString();
         String _980col    = _980info.get("SC").toString();
         String _956   = _980info.get("ST").toString();
         String _2390type = _980info.get("RT").toString();
         String _decimalformat =_980info.get("DF").toString();

         String[] _962  = null;
         String[] _974= null;

         StringTokenizer _4394 = new StringTokenizer(_4415,":");
         StringTokenizer _1082 = new StringTokenizer(_980col,":");
         char[] _968 = _956.toCharArray();

         VOBJ _2369=new VOBJ();
         VOBJ _167 = null;
         int _7553=0;

         boolean _4271addedflag = false;
         int _3851;
         if(_968[1]=='1'){   //소계 DATA ADD
             _3851=_4394.countTokens();
             for(int i=0;i<_3851;i++){
                 _962  =this._5588(_4394.nextToken().trim());
                 _974=this._5588(_1082.nextToken().trim());

                 if(_4271addedflag==false && _968[0]=='1'){
                     _4271addedflag=true;
                     _7553=1;
                     VOBJ _1250 = this.getSort(_vOBY5, _962  );
                     _1250.first();
                     while(_1250.next()){
                         _1250.getRecord().put("PARTCNT","0");
                         _2369.addRecord(_1250.cpRecord());
                     }
                 }

                 _167 =this._4898(_vOBY5,_962,_974,_2390type,_decimalformat);
                 _7502(_2369, _167, i+_7553 , _968[0]);
                 //     _2369._2825("소계 ADD SUM PART MID");
             }
         }

         if(_968[2]=='1'){   //전체 합계 DATA
             _1082 = new StringTokenizer(_980col,":");
             _974=this._5588(_1082.nextToken());

             if(_4271addedflag=false && _968[0]=='1'){
                 _4271addedflag=true;
                 _2369.setRecords(_vOBY5.getRecords());
             }

             HashMap _503 = this._4535(_vOBY5, _974, _2390type, _decimalformat);
             _503.put("PARTCNT",_vOBY5.getRecordCnt()+"");
             _2369.addRecord(_503);
         }

         //   _2369._2825("_503 ADD SUM PART MID");
         //--------------------------------  전체 SUM 추가  end
         return _2369;
     }


       public VOBJ getSUM_BK1(VOBJ _vOBY5, HashMap _980info)  throws Exception  {   //VU0250

           String _4415     = _980info.get("GJ").toString();
           String _980col    = _980info.get("SC").toString();
           String _956   = _980info.get("ST").toString();
           String _2390type = _980info.get("RT").toString();
           String _decimalformat =_980info.get("DF").toString();

           String[] _962  = null;
           String[] _974= null;

           //StringTokenizer _4394 = new StringTokenizer(_4415,":");
           //StringTokenizer _1082 = new StringTokenizer(_980col,":");
           char[] _968 = _956.toCharArray();

           VOBJ _2369=new VOBJ();
           VOBJ _167 = null;
           int _7553=0;

           boolean _4271addedflag = false;
           int _3851;
           if(_968[1]=='1'){   //소계 DATA ADD
               //_3851=_4394.countTokens();
               //for(int i=0;i<_3851;i++){
                   _962  =this._5588(_4415.trim());
                   _974=this._5588(_980col.trim());

                   //System.out.println("===========================");
                   //System.out.println(_962 +":" + _962.length);

                   if(_4271addedflag==false && _968[0]=='1'){
                       _4271addedflag=true;
                       _7553=1;
                       VOBJ _1250 = this.getSort(_vOBY5, _962  );
                       _1250.first();
                       while(_1250.next()){
                           _1250.getRecord().put("PARTCNT","0");
                           _2369.addRecord(_1250.cpRecord());
                       }
                   }

                   _167 =this._4898(_vOBY5,_962,_974,_2390type,_decimalformat);
                   _7502(_2369, _167, _7553 , _968[0]);
                   //     _2369._2825("소계 ADD SUM PART MID");
           //    }
           }

           if(_968[2]=='1'){   //전체 합계 DATA
               //_1082 = new StringTokenizer(_980col,":");
               _974=this._5588(_980col.trim());

               if(_4271addedflag=false && _968[0]=='1'){
                   _4271addedflag=true;
                   _2369.setRecords(_vOBY5.getRecords());
               }

               HashMap _503 = this._4535(_vOBY5, _974, _2390type, _decimalformat);
               _503.put("PARTCNT",_vOBY5.getRecordCnt()+"");
               _2369.addRecord(_503);
           }

           //   _2369._2825("_503 ADD SUM PART MID");
           //--------------------------------  전체 SUM 추가  end
           return _2369;
       }

       private String[] _5588(String _6995 )  throws Exception {
           StringTokenizer _1082 = new StringTokenizer(_6995,",");
           //System.out.println("SUM COLS:"+_6995 +":" + _1082.countTokens());
           String[] _2333= new String[_1082.countTokens()];
           int i=0;
           String _name="";
           while(_1082.hasMoreTokens()){
               _name = _1082.nextToken().trim();
               _2333[i] = _name;
               i++;
           }
           return _2333;
       }

       private HashMap _4535(VOBJ _IN_7583X, String[] _974 , String _2390type, String _decimalformat) throws Exception {      //VU0230
           String _284 = "";
           double[] _467 = new double[_974.length];
           _IN_7583X.first();
           while(_IN_7583X.next()){
               for (int i = 0; i < _974.length; i++) {
                   if(_IN_7583X.getRecord().get(_974[i]).equals("") ) _284="0";
                   else _284 = _IN_7583X.getRecord().get(_974[i]).toString();
                   if(_284==null || _284.trim().equals("") ) _284 ="0";
                   _467[i] = _467[i] + Double.parseDouble(_284);
               }
           }

           HashMap _503= _IN_7583X.cpRecord();
           ArrayList _506  = _IN_7583X.getColumnNames();
           boolean _5996=false;
           int _3878=0;
           for(int i=0;i<_506.size();i++){
               _5996=false;
               for(int ii=0;ii<_974.length;ii++){
                   if(_506.get(i).toString().equals(_974[ii])){
                       _3878=ii;
                       _5996=true;
                       break;
                   }
               }
               if(_5996==true){
                   _503.put(_506.get(i),_467[_3878]+"");
               }else{
                   _503.put(_506.get(i),"");
               }
           }

           if(!_2390type.equals("0")){
               String _1142="";
               for(int i=0;i<_974.length;i++){
                   _1142=this._4757(_2390type, _decimalformat, _503.get(_974[i]) );
                   _503.put(_974[i],_1142);
               }
           }

           return _503;
       }

       private VOBJ _7502(VOBJ _155, VOBJ _167, int _701, char _1391 )  throws Exception {  //VU0240

           int _2549=0;
           _167.first();
           while(_167.next()){
               if(_1391 =='1'){
                   _2549 = _2549 +  Integer.parseInt(_167.getRecord().get("PARTCNT"));
                   _155.addRecord(_2549, _167.getRecord());
                   _2549 = _2549+_701;
               }else{
                   _155.addRecord(_167.getRecord());
               }
           }
           return _155;
       }

       private VOBJ _4898(VOBJ _IN_7583X, String[] _965, String[] _974 , String _2390type, String _decimalformat) throws Exception {      //VU0220
           VOBJ _167 = new VOBJ();
           ArrayList _3296 = new ArrayList();
           HashMap _3302 = null;

           HashMap _5852 = null;
           HashMap _3272 = null;
           String _5843 = "", _734 = "";
           String _3263 = "", _659 = "";
           String _284="";
           VOBJ _161 = this.getSort(_IN_7583X, _965 );
           ArrayList _1271 = _161.getRecords();

           double[] _980 = new double[_974.length];
           boolean _5864 = false;
           int _977=1;
           for (int i = 0; i < _1271.size() - 1; i++) {
               _5852 = (HashMap) _1271.get(i);
               _3272 = (HashMap) _1271.get(i + 1);
               _5843 = "";
               _734 = "";
               _3263 = "";
               _659 = "";
               for (int ii = 0; ii < _965.length; ii++) {
                   if (_5852.get(_965[ii]) != null)
                       _734 = _5852.get(_965[ii]).toString().trim();
                   else
                       _734 = "";

                   if (_3272.get(_965[ii]) != null)
                       _659 = _3272.get(_965[ii]).toString().trim();
                   else
                       _659 = "";

                   _5843 = _5843 + _734;
                   _3263 = _3263 + _659;
               }

               if (_5843.equals(_3263)) {
                   _977 = _977 + 1;
                   for (int ii = 0; ii < _974.length; ii++) {
                       if(_5852.get(_974[ii])==null ||  _5852.get(_974[ii]).equals("") ) _284="0";
                       else _284 = _5852.get(_974[ii]).toString();
                        if(_284 == null || _284.trim().equals("")) _284="0";
                       _980[ii] = _980[ii] +  Double.parseDouble(_284);
                   }
               } else {
                   _3302 = new HashMap();
                   for (int ii = 0; ii < _IN_7583X.getColumnNames().size(); ii++) {
                       _3302.put(_IN_7583X.getColumnNames().get(ii), _5852.get(_IN_7583X.getColumnNames().get(ii)));
                   }

                   for (int ii = 0; ii < _974.length; ii++) {
                       if(_5852.get(_974[ii])==null || _5852.get(_974[ii]).equals("") ) _284="0";
                       else _284 = _5852.get(_974[ii]).toString();
                       if(_284 == null || _284.trim().equals("")) _284="0";
                       _980[ii] = _980[ii] + Double.parseDouble(_284);
                   }

                   for (int ii = 0; ii < _974.length; ii++) {
                       _3302.put(_974[ii], _980[ii] + "");
                       _980[ii] = 0;
                   }
                   _3302.put("PARTCNT", _977 + "");
                   _3296.add(_3302);
                   _977=1;
               }

               if (i == _1271.size() - 2) {
                   if (_5864 == false) {

                       _3302 = new HashMap();
                       for (int ii = 0; ii < _IN_7583X.getColumnNames().size(); ii++) {
                           _3302.put(_IN_7583X.getColumnNames().get(ii), _3272.get(_IN_7583X.getColumnNames().get(ii)));
                       }

                       for (int ii = 0; ii < _974.length; ii++) {
                           if(_5852.get(_974[ii])==null ||  _3272.get(_974[ii]).equals("") ) _284="0";
                           else _284 = _3272.get(_974[ii]).toString();
                           if(_284 == null || _284.trim().equals("")) _284="0";
                           _980[ii] = _980[ii] + Double.parseDouble(_284);
                       }

                       for (int ii = 0; ii < _974.length; ii++) {
                           _3302.put(_974[ii], _980[ii] + "");
                           _980[ii] = 0;
                       }
                   }

                   _3302.put("PARTCNT", _977 + "");
                   _3296.add(_3302);
               }
           }
           _167.setRecords(_3296);
           if(!_2390type.equals("0")){
               String _1142="";
               _167.first();
               while(_167.next()){
                   for(int i=0;i<_974.length;i++){
                       _1142=this._4757(_2390type, _decimalformat, _167.getRecord().get(_974[i]));
                       _167.getRecord().put(_974[i],_1142);
                   }
               }
           }
           return _167;
       }

       public String _4757(String _2390, String _6161, Object _305x ) throws Exception {  //SQ0220

           String _305="";
           if(_305x == null) _305 ="";
           else _305 = _305x.toString();

           int _4307=0;
           if(!_4100(_305)) return _305;
           String _2366="";
           if(_2390.equals("1")){                                          //-- round처리

               _2366 = getNumberFormat(_6161,Double.parseDouble(_305));
           }else if(_2390.equals("2")){                                    //-- 절삭처리
               if(_6161.indexOf(".") != -1) {
                   int pin = _6161.length() - _6161.indexOf(".");
                   if( _305.indexOf(".") != -1){
                       _4307 = _305.length() - _305.indexOf(".");
                       if( pin >  _4307 ) pin = _4307 ;
                       _2366 = _305.substring(0, _305.indexOf(".")) + _305.substring(_305.indexOf("."),_305.indexOf(".")+pin );
                   }else{
                       _2366 = _305;
                   }
               }else{
                   if( _305.indexOf(".") != -1){
                       _2366 = _305.substring(0,_305.indexOf("."));
                   }else{
                       _2366 = _305;
                   }
               }
           }else{
               _2366 = _305;
           }

           return _2366;
       }

       private boolean _4100(String s) throws Exception{    //SQ0470
           boolean _2366=false;
           try{
               Double.parseDouble(s);
               _2366 = true;
           }catch(NumberFormatException e){}
           return _2366;
       }

       //======= XUP : Excel Upload == start

       //xpath, _xfile : excel file 정보 , ipath,ifile : layout 정보 파일이다
       public VOBJ load(String _44, String _xfile, String _ipath, String _ifile){
           CommUtil _cmutil = new CommUtil();

           int _3938=0;
           int _3956=0;
           VOBJ _2366 = null;
           ArrayList _1373 = null;
           HashMap _smap = null;
           ArrayList _1364 = _cmutil._5207(_ipath,_ifile);
           HashMap _233= new HashMap();

           try{
               for(int i=0;i<_1364.size();i++){
                   _1373 = (ArrayList)_1364.get(i);
                   HashMap _1361 = _cmutil.setSheet_7580Info(_1373);
                   ArrayList _3185 = (ArrayList)_1361.get("objnames");
                   String _3182="";
                   for(int ii=0;ii<_3185.size();ii++){
                       _3182 = _3185.get(ii).toString();
                       _smap = (HashMap)_1361.get(_3182);
                       if(_smap.get("type").toString().equals("cells")){
                           VOBJ _227 =_cmutil.ReadExcel( _44
                                   ,_xfile
                                   ,(String[])_smap.get("names")
                                   ,_cmutil._4883(_smap)
                                   ,Integer.parseInt(_1361.get("sheetno").toString()));

                           _227.setName(_3182);
                           // _227._2825("CELLS VOBJ");
                           _233.put(_3182,_227);
                       } else if(_smap.get("type").toString().equals("rows")){

                           String last = _3806(_smap.get("end").toString()).trim();

                           if(last.equals("*")){
                               _3938 = this._last;
                           }else {
                               _3938 = this._3944;
                           }

                           // System.out.println("MROW LAST:" + last +":" + _3938);
                           VOBJ _227 = _cmutil._2624(_44, _xfile
                                   ,(String[])_smap.get("names")
                                   ,_cmutil._4883(_smap)
                                   ,Integer.parseInt(_smap.get("start").toString())
                                   ,_smap.get("end").toString()
                                   ,Integer.parseInt(_1361.get("sheetno").toString())
                                   ,_3938  );

                           _227.setName(_3182);
                           // _227._2825("MROW VOBJ");
                           _233.put(_3182, _227 );

                       }else if(_smap.get("type").toString().equals("ncols")){
                           String _last = _3806(_smap.get("end").toString()).trim();
                           if(_last.equals("last")){
                               _3938 = this._last;
                           }else {
                               _3938 = this._3944;
                           }
                           VOBJ _227 =_cmutil._2627(_44, _xfile
                                                              ,(String[])_smap.get("names")
                                                              ,_cmutil._4883(_smap)
                                                              ,Integer.parseInt(_smap.get("start").toString())
                                                              ,_smap.get("end").toString()
                                                              ,Integer.parseInt(_1361.get("sheetno").toString())
                                                              ,_3956 );

                           _227.setName(_3182);
                           // _227._2825("MCOL VOBJ");
                           _233.put(_3182, _227 );
                       }
                   }
                   _2366 = _cmutil._1931(_1373, _233, _xfile, _1361.get("sheetno").toString());
               }
           }catch(Exception e){
               this._19gerr("load Error:",e);
               e.printStackTrace();
           }
           _233.clear();
           // System.out.println("**     EXCEL파일명:"+xfile+"        ");
           return _2366;
       }
       //======= XUP : Excel Upload ==[ END ] ======


        public PreparedStatement  getPStmt(Object _6773x, SQLObject _1178) throws Exception
       {
           Connection _6773 = (java.sql.Connection)_6773x;
           PreparedStatement _2699 = null;
           String _2675 = "";
           try{
               _2675 = _1178.getCvtsql();
               _2699 = _6773.prepareStatement(_2675);
               SQLParam _sparm = null;
               for (int i = 0; i < _1178.getColumninfo().size(); i++) {

                   _sparm = (SQLParam) _1178.getColumninfo().get(i);
                   switch (_sparm.getColumnType()) {

                   case 4:
                       _2699.setInt(i + 1, _sparm.getInt());
                       break;

                   case 3:
                       _2699.setLong(i + 1, _sparm.getLong());
                       break;

                   case 2:
                       _2699.setDouble(i + 1, _sparm.getDouble());
                       break;

                   case 1:
                       _2699.setString(i + 1, _sparm.getString());
                       break;
                   }
               }
           }catch(Exception e){
                this._19gerr("getPStmt:",e);
               e.printStackTrace();
               _2699 = null;
           }
           return _2699;
       }

       /*
       public String  makeFileSeq(String _path, String _filename , byte[] inbyte) throws Exception{
           if(inbyte == null) return "";
           String _fullname = "";
           _path = _path.trim();
           _filename = _filename.trim();
           if(!_path.substring(_path.length()-1).equals("/")) _path = _path +"/";
           _fullname = _path + _filename;
           File _fp = new File(_fullname);

           int _cnt=0;
           while(_fp.exists()){
               String _filenamex = _filename.substring(0,_filename.indexOf(".")) +"_"+ _cnt + _filename.substring(_filename.indexOf("."));
               _fullname  = _path  + _filenamex;
               _fp = new File(_fullname );
               _cnt++;
           }

           FileOutputStream _os = new FileOutputStream(_fp);
           _os.write(inbyte);
           _os.close();

           return _fullname;
       }

       public String  makeFileOverwirte(String _path, String _filename , byte[] inbyte) throws Exception{
           //System.out.println(inbyte);
            if(inbyte == null) return "";
           String _fullname = "";
           _path = _path.trim();
           _filename = _filename.trim();
           if(!_path.substring(_path.length()-1).equals("/")) _path = _path +"/";
           _fullname = _path + _filename;
           File _fp = new File(_fullname);

           FileOutputStream _os = new FileOutputStream(_fp);
           _os.write(inbyte);
           _os.close();

           return _fullname;
       }
       public byte[] inputFileStream(String _path, String _filename) throws Exception{
           if(!_path.substring(_path.length()-1).equals("/")) _path = _path +"/";
           File _fp = new File(_path + _filename);

           int _size = (int)_fp.length();
           byte[] _rtn = new byte[_size];

           FileInputStream _is = new FileInputStream(_fp);
           _is.read(_rtn);
           _is.close();
           return _rtn;
       }
       */


       public int fget(String _3948, String _9384, String _0294, String _21938, String _2838, String _1928, String _138, String _765) throws Exception{
           Ftp _ftp = new Ftp(_9384);
           if(_ftp._sgetJnet(_9384, _0294, _21938, _2838, _1928, _138, _765) == true)
           {
               return 1;
           }
           return 0;
       }


       public int fput(String _3948  , String _9384 , String _0294, String _21938, String _2838, String _1928, String _138, String _765) throws Exception{
           Ftp _ftp = new Ftp(_9384);
           //if(_ftp._sputJnet(_3948, _0294, _21938, _2838, _1928, _138, _765) == true)
           if(_ftp._sputJnet(_9384, _0294, _21938, _2838, _1928, _138, _765) == true)
           {
               return 1;
           }
           return 0;
       }
       public String[] processing(String[] _73, String _2943) throws Exception{
           String _rtn[] = null;
           CPrc _cprc = new CPrc();
          _rtn = _cprc.processing(_73,  _2943) ;
           return _rtn;
       }


       //hashmap
       public VOBJ getVobjSrcd(VOBJ _4040, HashMap _smap) {
         VOBJ _227 = new VOBJ();

         String _899s =_smap.get("TARGET").toString();
         String _4001s    =_smap.get("KEYS").toString();
         String _8003   = _smap.get("TITLE").toString();
         String _nullyn  =_smap.get("NULL").toString();

         boolean _seqflag=false;
         if(_smap.get("SEQ") != null && !_smap.get("SEQ").toString().trim().equals("") ) _seqflag = true;

         ArrayList _827 = new ArrayList();
         ArrayList _klist = new ArrayList();
         String _3374 = "";
         StringTokenizer _1079 = new StringTokenizer(_899s,",");
         while(_1079.hasMoreTokens()){
           _3374  = _1079.nextToken().trim();
           _827.add(_3374);
         }
         _1079 = new StringTokenizer(_4001s,",");
         while(_1079.hasMoreTokens()){
           _3374  = _1079.nextToken().trim();
           _klist.add(_3374);
         }

         int _flag=0;
         if(_nullyn != null && _nullyn.trim().equals("1")) _flag =1;

         HashMap _2594 = null;

         _4040.first();
         while(_4040.next()){
           for(int i=0;i<_827.size();i++){
             _2594 = new HashMap();
             if(_4040.ContainsColumnName(_827.get(i).toString())== false) {
                 this._19ginfo("SRCD COLUMN NOT FOUNDED SKIP:" + _827.get(i).toString());
               //System.out.println("SRCD COLUMN NOT FOUNDED SKIP:" + _827.get(i).toString());
               continue;
             }

             if(_flag  == 0) {
               if(_4040.getRecord().get(_827.get(i).toString()).equals("")) {
                 continue;
               }
             }

             for(int j=0;j<_klist.size();j++){
               if(_4040.ContainsColumnName(_klist.get(j).toString())== false) {
                   this._19ginfo("SRCD COLUMN NOT FOUNDED SKIP:" + _klist.get(j).toString());
                 //System.out.println("SRCD COLUMN NOT FOUNDED SKIP:" + _klist.get(j).toString());
                 continue;
               }
               _2594.put(_klist.get(j).toString() , _4040.getRecord().get(_klist.get(j).toString()));
             }
             _2594.put(_8003 , _4040.getRecord().get(_827.get(i).toString()));
             if(_seqflag == true)
                 _2594.put(_smap.get("SEQ").toString().trim() , (i+1) +"");

             _227.addRecord(_2594);
           }
         }
         return _227;
       }


       //--------------------Single Record 분할 처리 start-----------------------------
         // 대상 column, 분할조건값,  나머지 처리 유효  : 0:false , 1:true
         public VOBJ getSRDL(VOBJ _4040, String _6989, String _scolumn,  String _flagx , String _seq) {

             int _flag=0;
             if(_flagx!= null && _flagx.trim().equals("1")) _flag =1;
             VOBJ _2366 = new VOBJ();

             if(_4040 == null || _4040.getRecordCnt() == 0 ) {
                 return _4040;
             }

             boolean _seqflag=false;
             if(_seq != null && !_seq.trim().equals("") ) _seqflag = true;

             HashMap _2594 = null;
             ArrayList _3338 = _4040.getColumnNames();
             long  _6899 = 0;
             long _3848 =0;
             long _dbls = 0;
             long _281  =0;

             _4040.first();
             while(_4040.next()){

                 _281 = _4040.getRecord().getLong(_scolumn);
                 _6899 =  Long.parseLong(_4040.getRecord().get(_6989).toString());
                 _3848 = _6899 / _281 ;
                 _dbls =  _6899 % _281 ;

                 //System.out.println(_3848 +":" + _dbls  + ":" + _281 +":"  + _6899 );
                 int i=0;
                 for( i=0;i<_3848 ;i++){
                     _2594 = new HashMap();
                     for(int j=0;j<_3338.size();j++){
                         _2594.put(_3338.get(j),_4040.getRecord().get(_3338.get(j)));
                     }
                     _2594.put(_6989, _281 +"");
                     if(_seqflag == true)
                         _2594.put(_seq.trim() , (i+1) +"");

                     _2366.addRecord(_2594);
                 }

                 if(_flag ==1 && _dbls > 0){
                     _2594 = new HashMap();
                     for(int j=0;j<_3338.size();j++){
                         _2594.put(_3338.get(j),_4040.getRecord().get(_3338.get(j)));
                     }
                     _2594.put(_6989, _dbls +"");
                     if(_seqflag == true)
                         _2594.put(_seq.trim() , 0 +"");

                     _2366.addRecord(_2594);
                 }
             }
             return _2366;
         }

         public VOBJ getSRDDbl(VOBJ _4040, String _6989, String _scolumn,  String _flagx , String _seq) {

            int _flag=0;
            if(_flagx!= null && _flagx.trim().equals("1")) _flag =1;
            VOBJ _2366 = new VOBJ();

            if(_4040 == null || _4040.getRecordCnt() == 0 ) {
                return _4040;
            }

            boolean _seqflag=false;
            if(_seq != null && !_seq.trim().equals("") ) _seqflag = true;

            HashMap _2594 = null;
            ArrayList _3338 = _4040.getColumnNames();
            double  _6899 = 0;
            int _3848 =0;
            double _dbls = 0;
            double _281  =0;

            _4040.first();
            while(_4040.next()){

                _281 = _4040.getRecord().getDouble(_scolumn);
                _6899 =  _4040.getRecord().getDouble(_6989);
                Double _tmp = new Double(_6899 / _281);
                _3848 =  _tmp.intValue();
                _dbls =  _6899 % _281 ;

                //System.out.println(_3848 +":" + _dbls  + ":" + _281 +":"  + _6899 );
                int i=0;
                for( i=0;i<_3848 ;i++){
                    _2594 = new HashMap();
                    for(int j=0;j<_3338.size();j++){
                        _2594.put(_3338.get(j),_4040.getRecord().get(_3338.get(j)));
                    }
                    _2594.put(_6989, _281 +"");
                    if(_seqflag == true)
                        _2594.put(_seq.trim() , (i+1) +"");

                    _2366.addRecord(_2594);
                }

                if(_flag ==1 && _dbls > 0){
                    _2594 = new HashMap();
                    for(int j=0;j<_3338.size();j++){
                        _2594.put(_3338.get(j),_4040.getRecord().get(_3338.get(j)));
                    }
                    _2594.put(_6989, _dbls +"");
                    if(_seqflag == true)
                        _2594.put(_seq.trim() , 0 +"");

                    _2366.addRecord(_2594);
                }


            }

            return _2366;
        }

  //--------------------Single Record 분할 처리 END----------------------------


  //---------------------------------MCV START---------------------------------------------------------
  //입력object,  기준columns,  반복columns,  label-object,  label-column
  public  VOBJ setMCV(VOBJ _227, String _98722, String _98724, VOBJ _928, String _8220) throws Exception
  {
      VOBJ _2306 = new VOBJ();
      if(_227.getRecordCnt()==0 || _227 == null ){
          return _2306;
      }
      StringTokenizer _1079 = new StringTokenizer(_98722,",");
      ArrayList _98753 = new ArrayList();
      while(_1079.hasMoreTokens()){
          _98753.add(_1079.nextToken().trim());
      }
      _1079 = new StringTokenizer(_98724,",");
      ArrayList _98735 = new ArrayList();
      while(_1079.hasMoreTokens()){
          _98735.add(_1079.nextToken().trim());
      }
      HashMap _5822 = new HashMap();
      HashMap _98755 = new HashMap();
      ArrayList _827 = new ArrayList();
      HashMap   _2564 = null;
      try{
          String _98751="";
          String _98757="";
          String _98746="";
          boolean _eq = true;
          _227.first();
          while (_227.next())
          {
              if (_227.current == 0)
              {
                  _2564 = new HashMap();
                  for(int i=0;i<_98753.size();i++)
                  {
                      _5822.put(_98753.get(i),_227.getRecord().get(_98753.get(i)));
                      _98755.put(_98753.get(i),_227.getRecord().get(_98753.get(i)));
                  }

                  _2564 = _227.cpRecord();
                  for(int i=0;i<_98735.size();i++)
                  {
                      _2564.remove(_98735.get(i).toString().trim());
                      _98746 = _227.getRecord().get(_8220);
                      _2564.put(_98735.get(i).toString().trim()+_98746, _227.getRecord().get(_98735.get(i).toString()));
                  }
              }
              for(int i=0;i<_98753.size();i++)
              {
                  _98755.put(_98753.get(i),_227.getRecord().get(_98753.get(i)));
              }
              _eq= true;
              for(int i=0;i<_98753.size();i++)
              {
                  _98751 = _5822.get(_98753.get(i)).toString();
                  _98757 = _98755.get(_98753.get(i)).toString();
                  if (!_98751.equals(_98757))
                  {
                      _eq = false;
                      break;
                  }
              }
              if(_eq == true)
              {
                  for(int i=0;i<_98735.size();i++)
                  {
                      _98746 = _227.getRecord().get(_8220);
                      _2564.put(_98735.get(i).toString().trim()+_98746, _227.getRecord().get(_98735.get(i).toString()));
                  }
              }
              else
              {
                  _827.add(_2564);
                  _2564 = new HashMap();
                  for(int i=0;i<_98753.size();i++)
                  {
                      _5822.put(_98753.get(i),_227.getRecord().get(_98753.get(i)));
                  }
                  _2564 = _227.cpRecord();
                  for(int i=0;i<_98735.size();i++)
                  {
                      _2564.remove(_98735.get(i).toString().trim());
                      _98746 = _227.getRecord().get(_8220);
                      _2564.put(_98735.get(i).toString().trim()+_98746, _227.getRecord().get(_98735.get(i).toString()));
                  }
              }
          }
          _827.add(_2564);
          _2306 = new VOBJ();
          _2306.setRecords(_827);

          //--------- head title 보정처리
          _2306.first();
          _2306.next();
          HashMap _2594 = _2306.getRecord().getRecord();
          _928.first();
          while(_928.next()){
              for(int j=0;j<_98735.size();j++)
              {
                  if(!_2306.ContainsColumnName(_98735.get(j).toString().trim()+_928.getRecord().get(_8220))){
                      _2594.put(_98735.get(j).toString().trim()+_928.getRecord().get(_8220),"");
                  }
              }
          }
          _2306.setRecord(0,_2594);
          //------------------------------------

      }catch(Exception e){
          this._19gerr("setMCV:",e);
          e.printStackTrace();
          throw new Exception("");
      }
      return _2306;
  }
  //---------------------------------MCV END---------------------------------------------------------

  //---------------------------------LNK START---------------------------------------------------------
  //n-1의 관계의 object을 연결하여 1개의 object로 변환처리 한다.
  //n건object , link-column명, 1건object
  public VOBJ setLink(VOBJ _227n, String _gnms, VOBJ _1701) throws Exception
  {
      //-- n건 obbject sorted처리 기능 flag
      VOBJ _2271 = new VOBJ();
      _1701.first();
      while(_1701.next()){
        _2271.addRecord(_1701.cpRecord());
      }

      VOBJ _2306 = new VOBJ();
      try{
          if(_227n == null || _227n.getRecordCnt()==0 ){
              return _2306;
          }
          StringTokenizer _1079 = new StringTokenizer(_gnms,",");
          ArrayList _98753 = new ArrayList();
          while(_1079.hasMoreTokens()){
              _98753.add(_1079.nextToken().trim());
          }
          ArrayList _7475x = _2271.getColumnNames();
          ArrayList _7475 = new ArrayList();
          for(int i=0;i<_7475x.size();i++){
              if(!_98757(_98753, _7475x.get(i).toString())) {
                  _7475.add(_7475x.get(i).toString());
              }
          }
          ArrayList _2558 = new ArrayList();
          HashMap _2594x = null;
          _227n.first();
          while(_227n.next()){
              _2594x = _227n.getRecord().getRecord();
              _2594x = _98756(_2594x, _2271, _98753, _7475);
              _2558.add(_2594x);
          }
          _2306.setRecords(_2558);

      }catch(Exception e){
          this._19gerr("setLink:",e);
          e.printStackTrace();
          throw new Exception("");
      }
      return _2306;
  }
  //-- 동일 record를 찾으면 해당 record는  record에서 삭제처리 한다.
  private HashMap _98756(HashMap _6650, VOBJ _2271, ArrayList _98753, ArrayList _7475)
  {
      boolean _eq=false;
      String _305n="";
      String _290="";
      _2271.first();
      while(_2271.next()){
          _eq = true;
          for(int i=0;i<_98753.size();i++)
          {
              _305n = _6650.get(_98753.get(i)).toString();
              _290 = _2271.getRecord().get(_98753.get(i));
              if(!_305n.equals(_290)){
                  _eq= false;
                  break;
              }
          }
          if(_eq==true){
              _6650.putAll(_2271.getRecord().getRecord());
              _2271.DelRecord(_2271.current);
              break;
          }
      }
      if(_eq==false){
          for(int i=0;i<_7475.size();i++){
              _6650.put(_7475.get(i),"");
          }
      }
      return _6650;
  }
  private boolean _98757(ArrayList _7475, String _3374)
  {
      boolean _2366= false;
      for(int i=0;i<_7475.size();i++){
          if(_3374.equals(_7475.get(i))) {
              _2366 = true;
              break;
          }
      }
      return _2366;
  }
  //---------------------------------LNK END---------------------------------------------------------
 //_SocketSimPlex(dobj, dvobj, server,  port, String  sendlayout , sendsize)
  public void sendSimplex(DOBJ dobj, VOBJ vobj, String server, String  port, String slayout, int ssize ) throws Exception
  {
      XFObject xobj = new XFObject(dobj);
      xobj._SocketSimPlex(dobj, vobj, server,  port,  slayout , ssize);
  }

  //SocketDuPlex (dobj, dvobj, String  sendlayout, rcvlayout,  server, port, sendsize, rcvsize)
  public VOBJ sendDuplex(DOBJ dobj, VOBJ vobj, String server, String  port, String slayout, int ssize, String rlayout, int rsize) throws Exception
  {
      XFObject xobj = new XFObject(dobj);
      VOBJ robj = xobj._SocketDuPlex( dobj,  vobj,   slayout,  rlayout,   server,  port, ssize , rsize) ;
      return robj;
  }


   }

