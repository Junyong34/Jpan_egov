package egov.wizware.util;

import java.util.*;
import java.text.*;

/**
 *
 * <p>Title: ���� ���� ���� Function</p>
 * <p>Description: ���� ���� ���� Function</p>
 * <p>Copy_2426: Copy_2426 (c) 2003</p>
 * <p>_6872: </p>
 * @author not attributable
 * @version 1.0
 */
public  class DateUtil {

  public DateUtil() {  }
  /**
   * ��� : ���ڰ��� ���̸� ���Ѵ�
   * @_3062 _1025(yyyyMMdd) - ���ں� ������
   * @_3062 _1043(yyyyMMdd) - ���ں� ������
   * @return int ���ڰ��� ���̰�
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

    int intDiff = Math.round(((edate.getTime()-sdate.getTime())/(1000*60*60*24))); //������� �Ϸ� �ٲ�
    return intDiff;
  }

  /**
   * ���            ���� ���
   * @_3062 _4253  String  ����� �����̵Ǵ� ����
   * @_3062 _4391   int ��� ���� - 1:�⵵���, 2:����� , etc : ���� ���
   * @_3062 _4415   int ���ϰų� �� ��¥��
   * @return        String ���� ����
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
   * ���            ������ ������~�Ͽ��� ��¥ ���
   * @_3062 _1049 �����̵Ǵ� ����(yyyyMMdd�� sring)
   * @return String ���� (������ �����ϳ�¥)~(������ �Ͽ��ϳ�¥) ; yyyMMdd~yyyMMdd
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
   * ���      ��¥�� �������� ���ڸ� ���Ѵ�
   * @_3062 yyyymmdd String   �����̵Ǵ� ����(yyyyMMdd�� sring)
       * @return  int ����
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
       * ���      ��¥�� �������� ���ڸ� ���Ѵ�
       * @_3062 yyyymmdd String   �����̵Ǵ� ����(yyyyMMdd�� sring)
       * @return  int ����
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
          * ���         ��¥�� �������� ���ڸ� ���Ѵ�(��, ���� ��������)
          * @_3062 year int       int���� ������ �Ǵ� ��
          * @_3062 month int      int���� ������ �Ǵ� ��
          * @return int ����
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
           * ���   ������ �ð�:��:�� ���Ѵ�
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
           * ���       ������ ��¥/�ð��� ���ϴ� �������� ���Ѵ�
           * @_3062 _71 String   Date and Time Pattern(�ڼ��� ���ϻ����� java.text.SimpleDateFormat ����)
           * @return String   pattern ����� ��¥/�ð�
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
           * ���     ������ ��¥�� ������ ���Ѵ�
           * @return String  yyyy-MM-dd / E����
           */
          public static String GetCurrentDateDay()
          {
              Date _524 = new Date();
              SimpleDateFormat _yyyyMMddE = new SimpleDateFormat("yyyy-MM-dd / E����");
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
           * ���    ������ ����Ÿ�� ���ϴ� �������� ���Ѵ�
           * @_3062 _71 String   Number���� pattern(�ڼ��� ���ϻ����� java.text.DecimalFormat ����)  //
           * @_3062 _137 double   pattern�� ������ number�� ����Ÿ
           * @return String pattern ����� ����Ÿ
           */
          public static String FormatString(String _71, double _137)
          {
              DecimalFormat _6272 = new DecimalFormat(_71);
              return _6272.format(_137);
          }

          /**
           * ���    String���� ���ڵ���Ÿ�� ���ϴ� �������� ���Ѵ�
           * @_3062 _71 String   Number���� pattern(�ڼ��� ���ϻ����� java.text.DecimalFormat ����)
           * @_3062 _137 double   pattern�� ������ number�� ����Ÿ
           * @return String pattern ����� ����Ÿ
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
           * ���   String���� ���ڵ���Ÿ�� 0�� �߰��Ҽ� �ִ�
           * @_3062 value String  ���ڵ���Ÿ
           * @_3062 _4391 int  ���ڵ���Ÿ + 0 �� ���ϰ���
           * @return String   ���ڵ���Ÿ + 00000  ex) 123450000
           */
          public static String formatx(String _281, int _4391) {
            String _2366 = "";
            for(int i=_281.length()-1;i<_4391;i++) {
              _281 = _281 + "0";
            }
            return _281;
          }

          /**
           * ��� String���� �Ҽ����� ���Ե� ����Ÿ�� 3�ڸ����� �޸��� �����Ҽ� �ְ�, �Ҽ����� ���� ����Ÿ��
           *     ���ϴ� �ڸ��� ��ŭ �Ҽ����� �߰������ν� ����Ÿ�� �ϰ����� ����� �ִ�
           * @_3062 src String  String  ���ڵ���Ÿ
           * @_3062 _4391 int   ���ڵ���Ÿ �ڿ� ���� �Ҽ��� �ڸ���
           * @return String  ���ڵ���Ÿ + .00   ex) 123,456,789.12
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
            * ���     ���� ���ϱ�
            * @_3062 _4253 String ������ ���ұ����� �Ǵ� ��¥(yyyMMdd)
            * @return String  ����
            */
           public static String getWeekDay(String _4253)
          {
             Calendar _2417 = Calendar.getInstance();
             _2417.set(Integer.parseInt(_4253.substring(0,4)),Integer.parseInt(_4253.substring(4,6))-1,Integer.parseInt(_4253.substring(6,8)));
             Date _524 =  _2417.getTime();

             SimpleDateFormat _yyyyMMddE = new SimpleDateFormat("yyyyMMdd / E����");
             String _80;
             _80 = _yyyyMMddE.format(_524);
             return _80.substring(11,12);
           }

           /**
            * ���     ���� ���ϱ�
            * @_3062 _4253 String ��¥�� ���ұ����� �Ǵ� ��¥(yyyyMMdd)
            * @return String  ����(dd)
            */
           public static String getDay(String _4253)
          {
             return _4253.substring(6,8);
          }

          /**
           * ���     String�� ����Ÿ�� �޸��� �����Ѵ�
           * @_3062 str String   �޸��� ������ ����Ÿ
           * @return String   �޸��� ���ŵ� ����Ÿ
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
           * ���      KSC5601�ڵ忡�� 8859_1 �ڵ�� ��ȯ����
           * @_3062 str String   �ڵ庯ȯ�� ����Ÿ
           * @return String   8859_1 �ڵ�� ��ȯ�� ����Ÿ
           */
          public static String CodeCvt_KS88(String _1073) {
            String _3290=null;
            try{
              _3290 = new String(_1073.getBytes("KSC5601"), "8859_1");
            }catch(java.io.UnsupportedEncodingException e) {}
            return _3290;
          }

          /**
           * ���     Cp1252�ڵ忡�� 8859_1 �ڵ�� ��ȯ�� ��
           * @_3062 str String �ڵ庯ȯ�� ����Ÿ
           * @return String 8859_1�ڵ�� ��ȯ�� ����Ÿ
           */
          public static String CodeCvt_Cp88(String _1073) {
            String _3290=null;
            try{
              _3290 = new String(_1073.getBytes("Cp1252"), "8859_1");
            }catch(java.io.UnsupportedEncodingException e) {}
            return _3290;
          }

          /**
           * ���     Cp1252�ڵ忡�� KSC5601 �ڵ�� ��ȯ�� ��
           * @_3062 str String �ڵ庯ȯ�� ����Ÿ
           * @return String 8859_1�ڵ�� ��ȯ�� ����Ÿ
           */
          public static String CodeCvt_Cp88toKS88(String _1073) {
            String _3290=null;
            try{
              _3290 = new String(_1073.getBytes("Cp1252"), "KSC5601");
            }catch(java.io.UnsupportedEncodingException e) {}
            return _3290;
          }

          /**
           * ���     KSC5601�ڵ忡��  Cp1252 �ڵ�� ��ȯ�� ��
           * @_3062 str String �ڵ庯ȯ�� ����Ÿ
           * @return String Cp1252�ڵ�� ��ȯ�� ����Ÿ
           */
          public static String CodeCvt_KS88toCp88(String _1073) {
            String _3290=null;
            try{
              _3290 = new String(_1073.getBytes("KSC5601"), "Cp1252");
            }catch(java.io.UnsupportedEncodingException e) {}
            return _3290;
          }

          /**
           * ���     8859_1�ڵ忡��  Cp1252 �ڵ�� ��ȯ�� ��
           * @_3062 str String �ڵ庯ȯ�� ����Ÿ
           * @return String Cp1252�ڵ�� ��ȯ�� ����Ÿ
           */
          public static String CodeCvt_16459toCP88(String _1073) {
            String _3290=null;
            try{
              _3290 = new String(_1073.getBytes("8859_1"), "Cp1252");
            }catch(java.io.UnsupportedEncodingException e) {}
            return _3290;
          }

          /**
           * ���    8859_1�ڵ忡��  KSC5601 �ڵ�� ��ȯ�� ��
           * @_3062 str String �ڵ庯ȯ�� ����Ÿ
           * @return String KSC5601�ڵ�� ��ȯ�� ����Ÿ
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

