package egov.wizware.util;

import java.util.*;
import java.text.*;

/**
 *
 * <p>Title: 날자 관련 공통 Function</p>
 * <p>Description: 날자 관련 공통 Function</p>
 * <p>Copy_2426: Copy_2426 (c) 2003</p>
 * <p>_6872: </p>
 * @author not attributable
 * @version 1.0
 */
public  class DateUtil {

  public DateUtil() {  }
  /**
   * 기능 : 일자간의 차이를 구한다
   * @_3062 _1025(yyyyMMdd) - 일자비교 시작일
   * @_3062 _1043(yyyyMMdd) - 일자비교 종료일
   * @return int 일자간의 차이값
   */
  public static int GetDayDiff(String _1025, String _1043){
    Calendar _1106 = Calendar.getInstance();
    Calendar _6092 = Calendar.getInstance();

    _1106.set(Integer.parseInt(_1025.substring(0, 4))
                  , Integer.parseInt(_1025.substring(4, 6)) - 1
                  , Integer.parseInt(_1025.substring(6, 8)));
    _6092.set(Integer.parseInt(_1043.substring(0, 4))
                , Integer.parseInt(_1043.substring(4, 6)) - 1
                , Integer.parseInt(_1043.substring(6, 8)));

    Date sdate = _1106.getTime();
    Date edate = _6092.getTime();

    int intDiff = Math.round(((edate.getTime()-sdate.getTime())/(1000*60*60*24))); //계산결과를 일로 바꿈
    return intDiff;
  }

  /**
   * 기능            날자 계산
   * @_3062 _4253  String  계산의 기준이되는 일자
   * @_3062 _4391   int 계산 구분 - 1:년도계산, 2:월계산 , etc : 일자 계산
   * @_3062 _4415   int 더하거나 뺄 날짜값
   * @return        String 계산된 일자
   *  YYYYMMDDhhmmss
   */
  public static String getComputeDate(String _4253, int _4391, int _4415)
  {
    Calendar _2417 = Calendar.getInstance();
    String _5807 ="";
    String SSS ="";
    if(_4253.length()==8){
      _2417.set(Integer.parseInt(_4253.substring(0,4)),
                   Integer.parseInt(_4253.substring(4,6))-1,
                   Integer.parseInt(_4253.substring(6,8)));
      _5807 ="yyyyMMdd";
    }else if(_4253.length()==12){
      _2417.set(Integer.parseInt(_4253.substring(0,4)),
                   Integer.parseInt(_4253.substring(4,6))-1,
                   Integer.parseInt(_4253.substring(6,8)),
                   Integer.parseInt(_4253.substring(8,10)),
                   Integer.parseInt(_4253.substring(10,12)));
      _5807 ="yyyyMMddHHmm";
    }else if(_4253.length()==14){
      _2417.set(Integer.parseInt(_4253.substring(0,4)),
                   Integer.parseInt(_4253.substring(4,6))-1,
                   Integer.parseInt(_4253.substring(6,8)),
                   Integer.parseInt(_4253.substring(8,10)),
                   Integer.parseInt(_4253.substring(10,12)),
                   Integer.parseInt(_4253.substring(12,14)));
      _5807 ="yyyyMMddHHmmss";
    }else {
      _2417.set(Integer.parseInt(_4253.substring(0, 4)),
                   Integer.parseInt(_4253.substring(4, 6)) - 1,
                   Integer.parseInt(_4253.substring(6, 8)),
                   Integer.parseInt(_4253.substring(8, 10)),
                   Integer.parseInt(_4253.substring(10, 12)),
                   Integer.parseInt(_4253.substring(12, 14)));
      _5807 = "yyyyMMddHHmmssSSS";
    }

    if      (_4391 ==1) _2417.add(_2417.YEAR , _4415);
    else if (_4391 ==2) _2417.add(_2417.MONTH, _4415);
    else if (_4391 ==3) _2417.add(_2417.DATE , _4415);
    else if (_4391 ==4) _2417.add(_2417.HOUR , _4415);

    Date _524 =  _2417.getTime();
    SimpleDateFormat _yyyyMMddE = new SimpleDateFormat(_5807);
    String _80;
    _80 = _yyyyMMddE.format(_524);

    return _80;
  }

  /**
   * 기능            전주의 월요일~일요일 날짜 계산
   * @_3062 _1049 기준이되는 일자(yyyyMMdd의 sring)
   * @return String 일자 (전주의 월요일날짜)~(전주의 일요일날짜) ; yyyMMdd~yyyMMdd
   */
  public static String GetBeforeWeek(String _1049){
    Calendar _6251 = Calendar.getInstance();
    _6251.set(Integer.parseInt(_1049.substring(0, 4))
                , Integer.parseInt(_1049.substring(4, 6)) - 1
                , Integer.parseInt(_1049.substring(6, 8)));
    int _5837 = _6251.get(_6251.DAY_OF_WEEK);
    return getComputeDate(_1049,3,1-_5837-6) + "~"
        + getComputeDate(_1049,3,1-_5837);

  }


  /**
   * 기능      날짜의 마지막의 일자를 구한다
   * @_3062 yyyymmdd String   기준이되는 일자(yyyyMMdd의 sring)
       * @return  int 일자
       */
      public static String getLastdate(String _yyyymmdd)  {
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

      /**
       * 기능      날짜의 마지막의 일자를 구한다
       * @_3062 yyyymmdd String   기준이되는 일자(yyyyMMdd의 sring)
       * @return  int 일자
       */
      public static int getLastday(String _yyyymmdd)  {
             int _6527;
             int _3485;
             int _35;

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

             return _6527;
         }

         /**
          * 기능         날짜의 마지막의 일자를 구한다(년, 월을 기준으로)
          * @_3062 year int       int형의 기준이 되는 년
          * @_3062 month int      int형의 기준이 되는 월
          * @return int 일자
          */
         public static int getLastday(int _35, int _3485)  {
              int _6527 = 0;
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
              return _6527;
          }

          /**
           * 기능   현재의 시간:분:초 구한다
           * @return String  Am/pm HH:MM:SS
           */
          public static String GetCurrentTime()
          {
              Date _524 = new Date();

              SimpleDateFormat _7481 = new SimpleDateFormat("a KK:mm:ss");
              String _77;
              _77 = _7481.format(_524);
              return _77;
          }

          public static String GetCurrentTime1()
          {
              Date _524 = new Date();
              SimpleDateFormat _7481 = new SimpleDateFormat("HH:MM:SS");
              String _77;
              _77 = _7481.format(_524);
              return _77;
          }

          /**
           * 기능       현재의 날짜/시간을 원하는 패턴으로 구한다
           * @_3062 _71 String   Date and Time Pattern(자세한 패턴사항은 java.text.SimpleDateFormat 참조)
           * @return String   pattern 적용된 날짜/시간
           */
          public static String GetCurrentDate(String _71)
          {
              Date _524 = new Date();
              DateFormat _df = DateFormat.getDateTimeInstance(DateFormat.FULL,
                                                             DateFormat.MEDIUM,
                                                             Locale.KOREA);
              SimpleDateFormat _yyyyMMddE = new SimpleDateFormat(_71);
              String _80;
              _80 = _yyyyMMddE.format(_524);

              return _80;
          }

          /**
           * 기능     현재의 날짜와 요일을 구한다
           * @return String  yyyy-MM-dd / E요일
           */
          public static String GetCurrentDateDay()
          {
              Date _524 = new Date();
              SimpleDateFormat _yyyyMMddE = new SimpleDateFormat("yyyy-MM-dd / E요일");
              String _80;
              _80 = _yyyyMMddE.format(_524);

              return _80;
          }

          public static String GetCurrentDate()
          {
              Date _524 = new Date();
              SimpleDateFormat _yyyyMMddE = new SimpleDateFormat("yyyy/MM/dd");
              String _80;
              _80 = _yyyyMMddE.format(_524);

              return _80;
          }


          /**
           * 기능    숫자형 데이타를 원하는 패턴으로 구한다
           * @_3062 _71 String   Number형의 pattern(자세한 패턴사항은 java.text.DecimalFormat 참조)  //
           * @_3062 _137 double   pattern을 적용할 number형 데이타
           * @return String pattern 적용된 데이타
           */
          public static String FormatString(String _71, double _137)
          {
              DecimalFormat _6272 = new DecimalFormat(_71);
              return _6272.format(_137);
          }

          /**
           * 기능    String형의 숫자데이타를 원하는 패턴으로 구한다
           * @_3062 _71 String   Number형의 pattern(자세한 패턴사항은 java.text.DecimalFormat 참조)
           * @_3062 _137 double   pattern을 적용할 number형 데이타
           * @return String pattern 적용된 데이타
           */
          public static String FormatString(String _71, String _68)
          {
             String _65;

             double _137 = Double.parseDouble(_68.trim());
             DecimalFormat _6272 = new DecimalFormat(_71);
             _65 = _6272.format(_137);

             return _65;
          }

          /**
           * 기능   String형의 숫자데이타에 0을 추가할수 있다
           * @_3062 value String  숫자데이타
           * @_3062 _4391 int  숫자데이타 + 0 을 붙일갯수
           * @return String   숫자데이타 + 00000  ex) 123450000
           */
          public static String formatx(String _281, int _4391) {
            String _2366 = "";
            for(int i=_281.length()-1;i<_4391;i++) {
              _281 = _281 + "0";
            }
            return _281;
          }

          /**
           * 기능 String형의 소수점이 포함된 데이타를 3자리마다 콤마로 구분할수 있고, 소수점이 없는 데이타는
           *     원하는 자리수 만큼 소수점을 추가함으로써 데이타의 일관성을 맞출수 있다
           * @_3062 src String  String  숫자데이타
           * @_3062 _4391 int   숫자데이타 뒤에 넣을 소수점 자리수
           * @return String  숫자데이타 + .00   ex) 123,456,789.12
           */
          public static String _986(String _1175,int _4391) {

            String tmp="",_788="",first="",second="",_7295="";
            int idx,length,mod,mok,su;
            String _5816="";

            if(_1175 == null)
              _1175 = " ";
            if(_1175.length() < 1)
              _1175 = " ";

            if(_1175.indexOf("-") != -1) {
                    _1175 = _1175.substring(1);
                    _7295="-";
                  }

            idx = _1175.indexOf(".");

            if(idx > 0 ) {
              first  = _1175.substring(0,idx);
              second = _1175.substring(idx);
              second = formatx(second,_4391);
            }
            else {
              first = _1175;
              second = formatx(".",_4391);
            }

            length = first.length();
            mok = length / 3;
            mod = length % 3;
            if(mod > 0 ) {
              su = mok;
              _788 = _1175.substring(0,mod);
            } else {
              su = mok -1;
              _788 = _1175.substring(0,3);
            }

            for(int loop=0;loop < su;loop++) {
              tmp = _1175.substring(length-(3*(loop+1)),length-(3*loop)) + tmp;
              tmp = "," + tmp;
            }
            tmp = _788 + tmp;

            return _7295+tmp+second;
          }


           /**
            * 기능     요일 구하기
            * @_3062 _4253 String 요일을 구할기준이 되는 날짜(yyyMMdd)
            * @return String  요일
            */
           public static String getWeekDay(String _4253)
          {
             Calendar _2417 = Calendar.getInstance();
             _2417.set(Integer.parseInt(_4253.substring(0,4)),Integer.parseInt(_4253.substring(4,6))-1,Integer.parseInt(_4253.substring(6,8)));
             Date _524 =  _2417.getTime();

             SimpleDateFormat _yyyyMMddE = new SimpleDateFormat("yyyyMMdd / E요일");
             String _80;
             _80 = _yyyyMMddE.format(_524);
             return _80.substring(11,12);
           }

           /**
            * 기능     일자 구하기
            * @_3062 _4253 String 날짜를 구할기준이 되는 날짜(yyyyMMdd)
            * @return String  일자(dd)
            */
           public static String getDay(String _4253)
          {
             return _4253.substring(6,8);
          }

          /**
           * 기능     String형 데이타의 콤마를 제거한다
           * @_3062 str String   콤마를 제거할 데이타
           * @return String   콤마가 제거된 데이타
           */
          public static String subStringDel(String _1073) {
             String _818="";
             String _6347=",";
             int index,start=0;

             while((index = _1073.indexOf(_6347)) != -1){
               _818 = _818 + _1073.substring(start,index);
               _1073 = _1073.substring(index+1,_1073.length());
             }
             _818 = _818 + _1073;
             return _818;

           }


          /**
           * 기능      KSC5601코드에서 8859_1 코드로 변환해줌
           * @_3062 str String   코드변환할 데이타
           * @return String   8859_1 코드로 변환된 데이타
           */
          public static String CodeCvt_KS88(String _1073) {
            String _3290=null;
            try{
              _3290 = new String(_1073.getBytes("KSC5601"), "8859_1");
            }catch(java.io.UnsupportedEncodingException e) {}
            return _3290;
          }

          /**
           * 기능     Cp1252코드에서 8859_1 코드로 변환해 줌
           * @_3062 str String 코드변환할 데이타
           * @return String 8859_1코드로 변환된 데이타
           */
          public static String CodeCvt_Cp88(String _1073) {
            String _3290=null;
            try{
              _3290 = new String(_1073.getBytes("Cp1252"), "8859_1");
            }catch(java.io.UnsupportedEncodingException e) {}
            return _3290;
          }

          /**
           * 기능     Cp1252코드에서 KSC5601 코드로 변환해 줌
           * @_3062 str String 코드변환할 데이타
           * @return String 8859_1코드로 변환된 데이타
           */
          public static String CodeCvt_Cp88toKS88(String _1073) {
            String _3290=null;
            try{
              _3290 = new String(_1073.getBytes("Cp1252"), "KSC5601");
            }catch(java.io.UnsupportedEncodingException e) {}
            return _3290;
          }

          /**
           * 기능     KSC5601코드에서  Cp1252 코드로 변환해 줌
           * @_3062 str String 코드변환할 데이타
           * @return String Cp1252코드로 변환된 데이타
           */
          public static String CodeCvt_KS88toCp88(String _1073) {
            String _3290=null;
            try{
              _3290 = new String(_1073.getBytes("KSC5601"), "Cp1252");
            }catch(java.io.UnsupportedEncodingException e) {}
            return _3290;
          }

          /**
           * 기능     8859_1코드에서  Cp1252 코드로 변환해 줌
           * @_3062 str String 코드변환할 데이타
           * @return String Cp1252코드로 변환된 데이타
           */
          public static String CodeCvt_16459toCP88(String _1073) {
            String _3290=null;
            try{
              _3290 = new String(_1073.getBytes("8859_1"), "Cp1252");
            }catch(java.io.UnsupportedEncodingException e) {}
            return _3290;
          }

          /**
           * 기능    8859_1코드에서  KSC5601 코드로 변환해 줌
           * @_3062 str String 코드변환할 데이타
           * @return String KSC5601코드로 변환된 데이타
           */
          public static String CodeCvt_16459toKSC(String _1073) {
            String _3290=null;
            try{
              _3290 = new String(_1073.getBytes("8859_1"), "KSC5601");
            }catch(java.io.UnsupportedEncodingException e) {}
            return _3290;
          }


          public String subString(String src) {

            String tmp="",_788="",first="",second="",_7295="";
            int idx,length,mod,mok,su;

            if(src == null)
              src = " ";
            if(src.length() < 1)
              src = " ";

            if(src.indexOf("-") != -1) {
              src = src.substring(1);
              _7295="-";
            }

            idx = src.indexOf(".");

            if(idx > 0 ) {
              first  = src.substring(0,idx);
              second = src.substring(idx);
            }
            else {
              first = src;
            }


            length = first.length();
            mok = length / 3;
            mod = length % 3;
            if(mod > 0 ) {
              su = mok;
              _788 = src.substring(0,mod);
            } else {
              su = mok -1;
              _788 = src.substring(0,3);
            }

            for(int loop=0;loop < su;loop++) {
              tmp = src.substring(length-(3*(loop+1)),length-(3*loop)) + tmp;
              tmp = "," + tmp;
            }
            tmp = _788 + tmp;

            return _7295+tmp+second;
          }

}

