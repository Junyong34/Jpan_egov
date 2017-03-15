package egov.wizware.util;

import hpms.UserObject.Excel.ObjectPool.PoolSizeOutException;

import java.util.HashMap;
import java.util.Date;
import java.util.Calendar;
import java.text.DecimalFormat;
import java.util.Locale;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Enumeration;
import java.io.FileInputStream;
import java.io.DataInputStream;
import java.util.StringTokenizer;
import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.util.Iterator;
import java.sql.Connection;
import java.lang.reflect.*;

import egov.wizware.com.*;
import egov.wizware.ria.XObject;

import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class CommUtil {

    private final  int  _3599=150;
    public CommUtil(){
    }
    public CommUtil(DOBJ dobj){
    }

    public static boolean checkCharAlpha( char _ch ) {
        boolean _2471 = false ;
        int _7412 = ( int ) _ch ;
        if ( ( ( 65 <= _7412 ) && ( _7412 <= 90 ) ) ||
             ( ( 97 <= _7412 ) && ( _7412 <= 122 ) ) ) {
            _2471 = true ;
        }
        return _2471 ;
    }

    public static boolean checkCharNumber( char _ch ) {
        boolean _2471 = false ;
        int _7412 = ( int ) _ch ;
        if ( ( 48 <= _7412 ) && ( _7412 <= 57 ) ) {
            _2471 = true ;
        }
        return _2471 ;
    }

    public static char upperChar(char _ch){
        int _7412 = ( int ) _ch ;

        if ( ( 97 <= _7412 ) && ( _7412 <= 122 ) ){
            _7412 = _7412 - 32;
        }
        return (char) _7412;
    }

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

        Date _2237 = _1106.getTime();
        Date _6119 = _6092.getTime();

        int intDiff = Math.round(((_6119.getTime()-_2237.getTime())/(1000*60*60*24))); //����� �Ϸ� �ٲ�
        return intDiff;
    }

    /**
     * ���            ���� ���
     * @_3062 _4253  String  ����� �����̵Ǵ� ����
     * @_3062 _4391   int ��� ���� - 1:�⵵���, 2:���� , etc : ���� ���
     * @_3062 _4415   int ���ϰų� �� ��¥��
     * @return        String ���� ����
     */
    public static String getComputeDate(String _4253, int _4391, int _4415)
    {
        Calendar _2417 = Calendar.getInstance();
        _2417.set(Integer.parseInt(_4253.substring(0,4)),Integer.parseInt(_4253.substring(4,6))-1,Integer.parseInt(_4253.substring(6,8)));
        if (_4391 ==1) _2417.add(_2417.YEAR,_4415);
        else if (_4391 ==2) _2417.add(_2417.MONTH,_4415);
        else _2417.add(_2417.DATE, _4415);

        Date _524 =  _2417.getTime();

        SimpleDateFormat yyyyMMddE = new SimpleDateFormat("yyyyMMdd");
        String _80;
        _80 = yyyyMMddE.format(_524);

        return _80;
    }

       /**
        * ���            ������ �����~�Ͽ��� ��¥ ���
        * @_3062 _1049 �����̵Ǵ� ����(yyyyMMdd�� sring)
        * @return String ���� (������ ����ϳ�¥)~(������ �Ͽ��ϳ�¥) ; yyyMMdd~yyyMMdd
        */
       public static String GetBeforeWeek(String _1049){
           Calendar _6251 = Calendar.getInstance();
           _6251.set(Integer.parseInt(_1049.substring(0, 4))
                     , Integer.parseInt(_1049.substring(4, 6)) - 1
                     , Integer.parseInt(_1049.substring(6, 8)));
           int _5837 = _6251.get(_6251.DAY_OF_WEEK);
           return getComputeDate(_1049,3,1-_5837-6) + "~"  + getComputeDate(_1049,3,1-_5837);
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
            * @return String   pattern ���� ��¥/�ð�
            */
           public static String GetCurrentDate(String _71)
           {
               Date _524 = new Date();
               DateFormat df = DateFormat.getDateTimeInstance(DateFormat.FULL,
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
            * @_3062 _71 String   Number���� pattern(�ڼ��� ���ϻ����� java.text.DecimalFormat ����)
            * @_3062 _137 double   pattern�� ����� number�� ����Ÿ
            * @return String pattern ���� ����Ÿ
            */
           public static String FormatString(String _71, double _137)
           {
               DecimalFormat _6272 = new DecimalFormat(_71);
               return _6272.format(_137);
           }

           /**
            * ���    String���� ���ڵ���Ÿ�� ���ϴ� �������� ���Ѵ�
            * @_3062 _71 String   Number���� pattern(�ڼ��� ���ϻ����� java.text.DecimalFormat ����)
            * @_3062 _137 double   pattern�� ����� number�� ����Ÿ
            * @return String pattern ���� ����Ÿ
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
           public static String formatx(String value, int _4391) {
             String _2366 = "";
             for(int i=value.length()-1;i<_4391;i++) {
               value = value + "0";
             }
             return value;
           }

           /**
            * ��� String���� �Ҽ����� ���Ե� ����Ÿ�� 3�ڸ����� �޸��� �����Ҽ� �ְ�, �Ҽ����� ��� ����Ÿ��
            *     ���ϴ� �ڸ��� ��ŭ �Ҽ����� �߰������ν� ����Ÿ�� �ϰ��� ����� �ִ�
            * @_3062 src String  String  ���ڵ���Ÿ
            * @_3062 _4391 int   ���ڵ���Ÿ �ڿ� ���� �Ҽ��� �ڸ���
            * @return String  ���ڵ���Ÿ + .00   ex) 123,456,789.12
            */
           public static String subStringForm(String src,int _4391) {

             String tmp="",_788="",first="",second="",_7295="";
             int _7986,_3905gth,_mod,_mok,_su;
             String _5816="";

             if(src == null)
               src = " ";
             if(src.length() < 1)
               src = " ";

             if(src.indexOf("-") != -1) {
                     src = src.substring(1);
                     _7295="-";
                   }

             _7986 = src.indexOf(".");

             if(_7986 > 0 ) {
               first  = src.substring(0,_7986);
               second = src.substring(_7986);
               second = formatx(second,_4391);
             }
             else {
               first = src;
               second = formatx(".",_4391);
             }

             _3905gth = first.length();
             _mok = _3905gth / 3;
             _mod = _3905gth % 3;
             if(_mod > 0 ) {
               _su = _mok;
               _788 = src.substring(0,_mod);
             } else {
               _su = _mok -1;
               _788 = src.substring(0,3);
             }

             for(int loop=0;loop < _su;loop++) {
               tmp = src.substring(_3905gth-(3*(loop+1)),_3905gth-(3*loop)) + tmp;
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
              int _4250,_1109=0;

              while((_4250 = _1073.indexOf(_6347)) != -1){
                _818 = _818 + _1073.substring(_1109,_4250);
                _1073 = _1073.substring(_4250+1,_1073.length());
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


           public String subString(String _1175) {

             String tmp="",_788="",first="",second="",_7295="";
             int idx,length,mod,mok,su;

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
             }
             else {
               first = _1175;
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


     public boolean checkL1(HashMap parm){
       boolean _2366 = false;
       String L1 = parm.get("SYSID").toString().trim();

       if(L1.equals("LO") ||
          L1.equals("MS") ||
          L1.equals("ADMIN") ||
          L1.equals("RO") ||
          L1.equals("CM")){
         _2366=true;
       }
       return true;
     }

      //================================================= sort ��� util start

      public static String[][] _1259(String[][] _1175){
          _2657(_1175, 0, _1175.length-1 );
          return _1175;
      }

     private static void _2657(String[][] A, int p, int r){
         if(A == null) return;
         if(p<r){
             int q = _3008(A, p, r);
             _2657(A, p, q);
             _2657(A, q+1, r);
         }
     }

     private static int _3008(String[][] A, int p, int r){
         String x = A[p][0];
         int i=p-1;
         int j=r+1;
         while(true){
             do j--; while(_6869(A[j][0],x)>0);
             do i++; while(_6869(A[i][0],x)<0);
             if(i<j){
                 String s = A[i][0];
                 A[i][0]=A[j][0];
                 A[j][0]=s;

                 String t = A[i][1];
                 A[i][1]=A[j][1];
                 A[j][1]=t;

             }
             else return j;
         }
     }

     //------------ sort int[] ----------------------------
     public static int[] _1265(Enumeration _1175, int _1349){
         int[] A = new int[_1349];
         int n=0;
         for(;n<_1349 && _1175.hasMoreElements();n++) A[n] = ((Integer)_1175.nextElement()).intValue();
         _2660(A, 0, n-1);
         return A;
     }

     private static void _2660(int[] A, int p, int r){
         if(A == null) return;
         if(p<r){
             int q = _3011(A, p, r);
             _2660(A, p, q);
             _2660(A, q+1, r);
         }
     }

     private static int _3011(int[] A, int p, int r){
         int x = A[p];
         int i=p-1;
         int j=r+1;
         while(true){
             do j--; while(_6869(A[j],x)>0);
             do i++; while(_6869(A[i],x)<0);
             if(i<j){
                 int s = A[i];
                 A[i]=A[j];
                 A[j]=s;
             }
             else return j;
         }
     }

     // ---------------- _1253: double -----------------------------------------
     public static int[] _1253(int[] _899, double[] _4001){
         return _1253(_899,_4001,true); // forward sort
     }

     public static int[] _1253(int[] _899, double[] _4001, boolean _5789){
         if( _899 == null || _4001 == null || _899.length == 0 || _899.length != _4001.length) return new int[0];

         _2654(_899, 0, _899.length-1, _4001, _5789);
         return _899;
     }
     private static void _2654(int[] _899, int p, int r, double[] _4001, boolean _5789){
         if(p<r){
             int q = _3005(_899, p, r, _4001,_5789);
             _2654(_899, p, q, _4001, _5789);
             _2654(_899, q+1, r,_4001,_5789);
         }
     }
     private static int _3005(int[] _899, int p, int r, double[] _4001, boolean _5789){
         double x = _4001[p];
         int i=p-1;
         int j=r+1;
         while(true){
             if(_5789){
                 do j--; while(_6869(_4001[j],x)>0);
                 do i++; while(_6869(_4001[i],x)<0);
             }
             else{
                 do j--; while(_6869(x,_4001[j])>0);
                 do i++; while(_6869(x,_4001[i])<0);
             }
             if(i<j){
                 double k = _4001[i]; _4001[i]=_4001[j];_4001[j]=k;
                 int t = _899[i]; _899[i]=_899[j];_899[j]=t;
             }
             else return j;
         }
     }

     // ---------------- _1253: long/double -----------------------------------------
     public static long[] _1253(long[] _899, double[] _4001){
         return _1253(_899,_4001,true); // _5789 sort
     }

     public static long[] _1253(long[] _899, double[] _4001, boolean _5789){
         if( _899 == null || _4001 == null || _899.length == 0 || _899.length != _4001.length) return new long[0];

         _2654(_899, 0, _899.length-1, _4001, _5789);
         return _899;
     }

     private static void _2654(long[] _899, int p, int r, double[] _4001, boolean _5789){
         if(p<r){
             int q = _3005(_899, p, r, _4001,_5789);
             _2654(_899, p, q, _4001, _5789);
             _2654(_899, q+1, r,_4001,_5789);
         }
     }
     private static int _3005(long[] _899, int p, int r, double[] _4001, boolean _5789){
         double x = _4001[p];
         int i=p-1;
         int j=r+1;
         while(true){
             if(_5789){
                 do j--; while(_6869(_4001[j],x)>0);
                 do i++; while(_6869(_4001[i],x)<0);
             }
             else{
                 do j--; while(_6869(x,_4001[j])>0);
                 do i++; while(_6869(x,_4001[i])<0);
             }
             if(i<j){
                 double k = _4001[i]; _4001[i]=_4001[j];_4001[j]=k;
                 long t = _899[i]; _899[i]=_899[j];_899[j]=t;
             }
             else return j;
         }
     }

     // ---------------- _1253: long -----------------------------------------
     public static int[] _1253(int[] _899, long[] _4001, boolean _5789){
         if( _899 == null || _4001 == null || _899.length == 0 || _899.length != _4001.length) return new int[0];

         _2654(_899, 0, _899.length-1, _4001,_5789);
         return _899;
     }
      private static void _2654(int[] _899, int p, int r, long[] _4001, boolean _5789){
         if(p<r){
             int q = _3005(_899, p, r, _4001,_5789);
             _2654(_899, p, q, _4001, _5789);
             _2654(_899, q+1, r,_4001,_5789);
         }
     }
     private static int _3005(int[] _899, int p, int r, long[] _4001, boolean _5789){
         long x = _4001[p];
         int i=p-1;
         int j=r+1;
         while(true){
             if(_5789){
                 do j--; while(_6869(_4001[j],x)>0);
                 do i++; while(_6869(_4001[i],x)<0);
             }
             else{
                 do j--; while(_6869(x,_4001[j])>0);
                 do i++; while(_6869(x,_4001[i])<0);
             }
             if(i<j){
                 long k = _4001[i]; _4001[i]=_4001[j];_4001[j]=k;
                 int t = _899[i]; _899[i]=_899[j];_899[j]=t;
             }
             else return j;
         }
     }
     // ---------------- _1253: int -----------------------------------------
     public static int[] _1253(int[] _899, int[] _4001, boolean _5789){
         if( _899 == null || _4001 == null || _899.length == 0 || _899.length != _4001.length) return new int[0];

         _2654(_899, 0, _899.length-1, _4001,_5789);
         return _899;
     }
      private static void _2654(int[] _899, int p, int r, int[] _4001, boolean _5789){
         if(p<r){
             int q = _3005(_899, p, r, _4001,_5789);
             _2654(_899, p, q, _4001,_5789);
             _2654(_899, q+1, r,_4001,_5789);
         }
     }
     private static int _3005(int[] _899, int p, int r, int[] _4001, boolean _5789){
         int x = _4001[p];
         int i=p-1;
         int j=r+1;
         while(true){
             if(_5789){
                 do j--; while(_6869(_4001[j],x)>0);
                 do i++; while(_6869(_4001[i],x)<0);
             }
             else{
                 do j--; while(_6869(x,_4001[j])>0);
                 do i++; while(_6869(x,_4001[i])<0);
             }
             if(i<j){
                 int k = _4001[i]; _4001[i]=_4001[j];_4001[j]=k;
                 int t = _899[i]; _899[i]=_899[j];_899[j]=t;
             }
             else return j;
         }
     }
     // ---------------- _1253: float -----------------------------------------
     public static int[] _1253(int[] _899, float[] _4001, boolean _5789){
         if( _899 == null || _4001 == null || _899.length == 0 || _899.length != _4001.length) return new int[0];

         _2654(_899, 0, _899.length-1, _4001,_5789);
         return _899;
     }

     private static void _2654(int[] _899, int p, int r, float[] _4001, boolean _5789){
         if(p<r){
             int q = _3005(_899, p, r, _4001,_5789);
             _2654(_899, p, q, _4001,_5789);
             _2654(_899, q+1, r,_4001,_5789);
         }
     }
     private static int _3005(int[] _899, int p, int r, float[] _4001, boolean _5789){
         float x = _4001[p];
         int i=p-1;
         int j=r+1;
         while(true){
             if(_5789){
                 do j--; while(_6869(_4001[j],x)>0);
                 do i++; while(_6869(_4001[i],x)<0);
             }
             else{
                 do j--; while(_6869(x,_4001[j])>0);
                 do i++; while(_6869(x,_4001[i])<0);
             }
             if(i<j){
                 float k = _4001[i]; _4001[i]=_4001[j];_4001[j]=k;
                 int t = _899[i]; _899[i]=_899[j];_899[j]=t;
             }
             else return j;
         }
     }
     // ---------------- _1253: String -----------------------------------------
     public static int[] _1253(int[] _899, String[] _4001, boolean _5789){
         if( _899 == null || _4001 == null || _899.length == 0 || _899.length != _4001.length) return new int[0];
         _2654(_899, 0, _899.length-1, _4001, _5789);
         return _899;
     }

     private static void _2654(int[] _899, int p, int r, String[] _4001, boolean _5789){
         if(p<r){
             int q = _3005(_899, p, r, _4001,_5789);
             _2654(_899, p, q, _4001, _5789);
             _2654(_899, q+1, r,_4001, _5789);
         }
     }
      private static int _3005(int[] _899, int p, int r, String[] _4001, boolean _5789){
         String x = _4001[p];
         int i=p-1;
         int j=r+1;
         while(true){
             if(_5789){
                 do j--; while(_6869(_4001[j],x)>0);
                 do i++; while(_6869(_4001[i],x)<0);
             }
             else{
                 do j--; while(_6869(x,_4001[j])>0);
                 do i++; while(_6869(x,_4001[i])<0);
             }
             if(i<j){
                 String k = _4001[i]; _4001[i]=_4001[j];_4001[j]=k;
                 int t = _899[i]; _899[i]=_899[j];_899[j]=t;
             }
             else return j;
         }
     }
     // ---------------- _1253: long/String -----------------------------------------
     public static long[] _1253(long[] _899, String[] _4001, boolean _5789){
         if( _899 == null || _4001 == null || _899.length == 0 || _899.length != _4001.length) return new long[0];

         _2654(_899, 0, _899.length-1, _4001, _5789);
         return _899;
     }
     private static void _2654(long[] _899, int p, int r, String[] _4001, boolean _5789){
         if(p<r){
             int q = _3005(_899, p, r, _4001,_5789);
             _2654(_899, p, q, _4001, _5789);
             _2654(_899, q+1, r,_4001, _5789);
         }
     }
     private static int _3005(long[] _899, int p, int r, String[] _4001, boolean _5789){
         String x = _4001[p];
         int i=p-1;
         int j=r+1;
         while(true){
             if(_5789){
                 do j--; while(_6869(_4001[j],x)>0);
                 do i++; while(_6869(_4001[i],x)<0);
             }
             else{
                 do j--; while(_6869(x,_4001[j])>0);
                 do i++; while(_6869(x,_4001[i])<0);
             }
             if(i<j){
                 String k = _4001[i]; _4001[i]=_4001[j];_4001[j]=k;
                 long t = _899[i]; _899[i]=_899[j];_899[j]=t;
             }
             else return j;
         }
     }
      // -------------- compare functions ----------------------------
     private static int _6869(int i, int j){
         return (i-j);
     }
     private static int _6869(short i, short j){
         return (int)(i-j);
     }
     private static int _6869(long i, long j){
         return (int)(i-j);
     }
     private static int _6869(float i, float j){
         return (i>j)? 1: (i<j)? -1 : 0;
     }
     private static int _6869(double i, double j){
         return (i>j)? 1: (i<j)? -1 : 0;
     }
     private static int _6869(String s1, String s2){
         if(s1 == null){
             if(s2 == null) return 0;
             else return -1;
         }
         else if(s2 == null) return 1;
         //else
         return s1.compareTo(s2);
     }
     //================================================= Sort util end

     //=============== excel upload start =================
     public  VOBJ _1931(ArrayList _sheet,HashMap _233, String _5963, String _1367){

       boolean _5879 = true,_875=false,_881=false,_878=false,_6035=false;
       HashMap _821 = null;
       String _608="";

       VOBJ _2366 = null;
       try{

         for(int i=0;i<_sheet.size();i++){
           //System.out.println(sheet.get(i).toString());
           if(_sheet.get(i) == null || _sheet.get(i).toString().trim().equals("")) continue;

           if(_sheet.get(i).toString().indexOf("OBJECT-START") != -1){
             if(_5879==false) throw new Exception("OBJECT-START ���� Syntax ����");
             _5879 =false;
             _821 = new HashMap();
           }
           if(_5879==false){
             if(_sheet.get(i).toString().indexOf("object-name") != -1){
               if(_875 ==true) throw new Exception("object-name �ߺ����� Syntax ����");
               _875 =true;
               _608 = _sheet.get(i).toString();
               _608 = _3806(_608.substring(_608.indexOf("=")+1)).trim();
               _821.put("object-name",_608);
             }else if(_sheet.get(i).toString().indexOf("object-key") != -1){
               if(_881 ==true) throw new Exception("object-key �ߺ����� Syntax ����");
               _881 =true;
               _608 = _sheet.get(i).toString();
               _608 = _3806(_608.substring(_608.indexOf("=")+1)).trim();

               ArrayList _3995 = new ArrayList();
               StringTokenizer _1079 = new StringTokenizer(_608,",");
               while(_1079.hasMoreTokens()){
                 _3995.add(_1079.nextToken().toString());
               }
               _821.put("object-key",_3995);

             }else if(_sheet.get(i).toString().indexOf("object-mapping") != -1){
               if(_878 ==true) throw new Exception("object-mapping �ߺ����� Syntax ����");
               _878=true;
               ArrayList cols= new ArrayList();
               ArrayList _284= new ArrayList();
               _608 = _sheet.get(i).toString();
               _608 = _3806(_608.substring(_608.indexOf("=")+1)).trim();
               StringTokenizer _1079 = new StringTokenizer(_608,",");
               int ii=0;
               String _602="";
               while(_1079.hasMoreTokens()){
                 _602 = _3806(_1079.nextToken().toString()).trim();
                 cols.add(_602.substring(0,_602.indexOf(":")).trim());
                 _284.add(_3806(_602.substring(_602.indexOf(":")+1)));
                 ii++;
               }
               _821.put("cols",cols);
               _821.put("vals",_284);
             }else if(_sheet.get(i).toString().indexOf("etccols") != -1){
               if(_6035 ==true) throw new Exception("etccols �ߺ����� Syntax ����");
               _6035=true;

               ArrayList _7004= (ArrayList)_821.get("cols");
               ArrayList _284= (ArrayList)_821.get("vals");
               _608 = _sheet.get(i).toString();
               _608 = _3806(_608.substring(_608.indexOf("=")+1)).trim();
               StringTokenizer _1079 = new StringTokenizer(_608,",");
               int ii=0;
               String _602="";
               while(_1079.hasMoreTokens()){
                 _602 = _3806(_1079.nextToken().toString()).trim();
                 _7004.add(_602.substring(0,_602.indexOf(":")).trim());
                 _284.add(_3806(_602.substring(_602.indexOf(":")+1)));
                 ii++;
               }
               _821.put("cols",_7004);
               _821.put("vals",_284);

             }else if(_sheet.get(i).toString().indexOf("OBJECT-END") == -1){
               if(_878==true){
                 ArrayList _7004= (ArrayList)_821.get("cols");
                 ArrayList _284= (ArrayList)_821.get("vals");

                 _608 = _3806(_sheet.get(i).toString()).trim();
                 StringTokenizer _1079 = new StringTokenizer(_608,",");
                 int ii=0;
                 String _602="";
                 while(_1079.hasMoreTokens()){
                   _602 = _3806(_1079.nextToken().toString()).trim();
                   _7004.add(_602.substring(0,_602.indexOf(":")).trim());
                   _284.add(_3806(_602.substring(_602.indexOf(":")+1)));
                   ii++;
                 }
                 _821.put("cols",_7004);
                 _821.put("vals",_284);
               }
             }
           }

           if(_sheet.get(i).toString().indexOf("OBJECT-END") != -1){
             if(_5879==true)  throw new Exception("OBJECT-END ���� Syntax ����");
             _5879=true;
             _875=false;
             _881=false;
             _878=false;
             _6035=false;

             _2366 = _1928(_821,_233,_5963,_1367);
           }
         }
       }catch(Exception e){
         e.printStackTrace();
       }

       if(_821.get("object-name")  != null && !_821.get("object-name").toString().trim().equals("")) _2366.setName(_821.get("object-name").toString().trim());
       return _2366;
     }

     public  HashMap setSheet_7580Info(ArrayList sheet){
       HashMap _rmap = new HashMap();
       HashMap _smap = null;
       HashMap _mmap = null;
       boolean _1388=false,_6986=false;

       String _3188="";
       int _7100=0,_7097=0,_3848=0;
       String _1073 = "", _cstr="";
       StringTokenizer _6668 = null;
       ArrayList _3185 = new ArrayList();
       try {
         for(int i=0;i<sheet.size();i++){
           if(sheet.get(i).toString().indexOf("OBJECT") != -1){
             if(_1388 == false || _6986 ==false) throw new Exception("sheetno || ncolumn || 1column Syntax ���� ");
             break;
           }

           if(sheet.get(i).toString().indexOf("sheetno") != -1){
             String _1367 = sheet.get(i).toString().trim();
             if(_1367.indexOf("=") == -1) throw new Exception("sheetno�� '=': Syntax ���� ");
             _1367 = _3806(_1367.substring(_1367.indexOf("=")+1)).trim();
             _rmap.put("sheetno",_1367);
             _1388=true;

           }else if(sheet.get(i).toString().indexOf("cells") != -1){
             _smap = new HashMap();
             _smap.put("type","cells");
             _6986=true;
             _7100=0;_7097=0;_3848=0;
             _1073 = "";_cstr="";
             if(_1388 == false) throw new Exception("sheet_no�� ���� ���� �ϼž� �մϴ�: Syntax ���� ");
             String _6923 = sheet.get(i).toString().trim();
             if(_6923.indexOf("=") == -1) throw new Exception("cells '=': Syntax ���� ");

             _6923 = _3806(_6923.substring(_6923.indexOf("=")+1)).trim();
             StringTokenizer _1079 = new StringTokenizer(_6923,";");
             String[] _nms = new String[_1079.countTokens()-1];

             while(_1079.hasMoreTokens()){
               if(_3848 == 0) {
                 _3188 = _1079.nextToken().toString();
                 _3185.add(_3188);
                 _3848++;
               }else {
                 PointColumn _pcol = new PointColumn();
                 _1073 = _3806(_1079.nextToken().toString()).trim();
                 _6668 = new StringTokenizer(_1073,":");
                 _7100 =0;
                 while(_6668.hasMoreTokens()){
                   _cstr = _3806(_6668.nextToken().toString()).trim();
                   switch(_7100){
                     case 0:
                       _pcol.setName(_cstr);
                       _nms[_7097] =_cstr;
                       break;
                     case 1:
                       _pcol.setX(_3806(_cstr.substring(0,_cstr.indexOf(","))).trim());
                       _pcol.setY(_3806(_cstr.substring(_cstr.indexOf(",")+1)).trim());
                       break;
                     case 2:
                       _pcol._1763(_cstr);
                       break;
                     default:
                       throw new Exception(
                           "cells column info(columnnm:x,t:lang) Syntax ���� ");
                   }
                   _7100++;
                 }
                 _smap.put(_pcol.getName(), _pcol);
                 _7097++;
               }
             }
             _smap.put("names", _nms);
             _rmap.put(_3188,_smap);

           }else if(sheet.get(i).toString().indexOf("rows") != -1){
             _mmap = new HashMap();
             _mmap.put("type","rows");
             _6986=true;
             _7100=0;_7097=0;_3848=0;
             _1073 = "";_cstr="";

             if(_1388== false)throw new Exception("sheetno�� ���� ���� �ϼž� �մϴ�: Syntax ���� ");
             String _6923 = sheet.get(i).toString().trim();
             if(_6923.indexOf("=") == -1) throw new Exception("rows '=': Syntax ���� ");
             _6923 = _3806(_6923.substring(_6923.indexOf("=")+1)).trim();
             StringTokenizer _1079 = new StringTokenizer(_6923,";");
             String[] _nms = new String[_1079.countTokens()-2];
             while(_1079.hasMoreTokens()){
               if(_3848 == 0){
                 _3188 = _3806(_1079.nextToken().toString()).trim();
                 _3185.add(_3188);
                 _3848++;
               }else if(_3848 == 1){
                 _1073 = _1079.nextToken().toString();
                 _mmap.put("start",_3806(_1073.substring(0,_1073.indexOf("~"))).trim());
                 _mmap.put("end"  ,_3806(_1073.substring(_1073.indexOf("~")+1)).trim());
                 _3848++;
               }else{
                 PointColumn _pcol = new PointColumn();
                 _1073 = _1079.nextToken().toString();
                 _6668 = new StringTokenizer(_1073,":");
                 _7100 =0;
                 while(_6668.hasMoreTokens()){
                   _cstr = _3806(_6668.nextToken().toString()).trim();
                   switch(_7100){
                     case 0:
                       _pcol.setName(_cstr);
                       _nms[_7097] = _cstr;
                       break;
                     case 1:
                       _pcol.setX(_cstr);
                       break;
                     case 2:
                       _pcol._1763(_cstr);
                       break;
                     default:
                       throw new Exception("rows column info(columnnm:x:lang) Syntax ���� ");
                   }
                   _7100++;
                 }
                 _mmap.put(_pcol.getName(),_pcol);
                 _7097++;
               }
             }
             _mmap.put("names",_nms);
             _rmap.put(_3188,_mmap);

           }else if(sheet.get(i).toString().indexOf("ncols") != -1){
             _mmap = new HashMap();
             _mmap.put("type","ncols");
             _6986=true;
             _7100 = 0;
             _7097 = 0;
             _3848 = 0;
             _1073 = "";
             _cstr = "";

             if (_1388 == false)throw new Exception("sheetno�� ���� ���� �ϼž� �մϴ�: Syntax ���� ");
             String _6923 = sheet.get(i).toString().trim();
             if (_6923.indexOf("=") == -1)throw new Exception("ncols '=': Syntax ���� ");
             _6923 = _3806(_6923.substring(_6923.indexOf("=") + 1)).trim();
             StringTokenizer _1079 = new StringTokenizer(_6923, ";");
             String[] _nms = new String[_1079.countTokens() - 2];

             while (_1079.hasMoreTokens()) {
               if (_3848 == 0) {
                 _3188 = _3806(_1079.nextToken().toString()).trim();
                 _3185.add(_3188);
                 _3848++;
               }
               else if (_3848 == 1) {
                 _1073 = _1079.nextToken().toString();
                 _mmap.put("start", _3806(_1073.substring(0, _1073.indexOf("~"))).trim());
                 _mmap.put("end", _3806(_1073.substring(_1073.indexOf("~") + 1)).trim());
                 _3848++;
               }
               else {
                 PointColumn _2993 = new PointColumn();
                 _1073 = _1079.nextToken().toString();
                 _6668 = new StringTokenizer(_1073, ":");
                 _7100 = 0;
                 while (_6668.hasMoreTokens()) {
                   _cstr = _3806(_6668.nextToken().toString()).trim();
                   switch (_7100) {
                     case 0:
                       _2993.setName(_cstr);
                       _nms[_7097] = _cstr;
                       break;
                     case 1:
                       _2993.setY(_cstr);
                       break;
                     case 2:
                       _2993._1763(_cstr);
                       break;
                     default:
                       throw new Exception( "ncols column info(columnnm:x:lang) Syntax ���� ");
                   }
                   _7100++;
                 }
                 _mmap.put(_2993.getName(), _2993);
                 _7097++;
               }
             }
             _mmap.put("names", _nms);
             _rmap.put(_3188, _mmap);
           }
         }
       } catch (Exception e) {
         e.printStackTrace();
       }
       _rmap.put("objnames", _3185);
       return _rmap;
     }

     private String getExt(String _str)
     {
         String _ext = _str.substring(_str.lastIndexOf(".")+1);
         return _ext;
     }
     public  VOBJ ReadExcel(String _5951, String _5960, String[] _nms, HashMap _2954, int _1376) throws      IOException
     {
       FileInputStream _5981In = null;
       HashMap _4358 = null;
       VOBJ _227 = null;
       try {
         String _9010 = _5951.trim();
         if(!_9010.substring(_9010.length()-1).equals("/")) _9010 = _9010 +"/";

         _5981In = new FileInputStream(_9010 + _5960);

         //HSSFWorkbook _107book = new HSSFWorkbook(_5981System);


         int _8061;
         String _8018 = null;
         PointColumn _2993 = null;

         if(!getExt(_5960).equals("xlsx"))
         {
             POIFSFileSystem _5981System = new POIFSFileSystem(_5981In);
             HSSFWorkbook _107book_hssf = null;
             _107book_hssf = new HSSFWorkbook(_5981System);

             HSSFSheet _sheet = _107book_hssf.getSheetAt(_1376);
             _4358 = new HashMap();
             for (int n = 0; n < _nms.length; n++)
             {
                 _2993 = (PointColumn) _2954.get(_nms[n]);
                 HSSFRow _row = _sheet.getRow(_2993.getY());
                 if(_row == null) break;
                 HSSFCell _cell = _row.getCell((int)_2993.getX());
                 if (_cell != null)
                 {
                     _8061 = _cell.getCellType();
                     if (_8061 == HSSFCell.CELL_TYPE_FORMULA)
                         _8018 = _2498(_cell.getNumericCellValue());
                     else if (_8061 == HSSFCell.CELL_TYPE_STRING)
                         _8018 = _cell.getStringCellValue();
                     else if (_8061 == HSSFCell.CELL_TYPE_ERROR)
                         _8018 = Byte.toString(_cell.getErrorCellValue());
                     else if (_8061 == HSSFCell.CELL_TYPE_BOOLEAN)
                         _8018 = Boolean.toString(_cell.getBooleanCellValue());
                     else if (_8061 == HSSFCell.CELL_TYPE_BLANK)
                         _8018 = "";
                     else if (_8061 == HSSFCell.CELL_TYPE_NUMERIC)
                         _8018 = _2498(_cell.getNumericCellValue());
                 }
                 else
                 {
                     _8018 = "";
                 }
                 //System.out.println(_nms[n] +":" + _2993.getName() +":" + _8018.trim());
                 _4358.put(_2993.getName(), _8018.trim());
             }
         }
         else
         {
             XSSFWorkbook _107book_xssf = null;
             _107book_xssf  =  new XSSFWorkbook(_5981In);
             XSSFSheet  _sheet = _107book_xssf.getSheetAt(_1376);

             _4358 = new HashMap();
             for (int n = 0; n < _nms.length; n++)
             {
                 _2993 = (PointColumn) _2954.get(_nms[n]);
                 XSSFRow  _row = _sheet.getRow(_2993.getY());
                 if(_row == null) break;
                 XSSFCell  _cell = _row.getCell((int)_2993.getX());
                 if (_cell != null)
                 {
                     _8061 = _cell.getCellType();
                     if (_8061 == Cell.CELL_TYPE_FORMULA)
                     {
                         _8018 = _2498(_cell.getNumericCellValue());
                     }
                     else if (_8061 == Cell.CELL_TYPE_STRING)
                     {
                         _8018 = _cell.getStringCellValue();
                     }
                     else if (_8061 == Cell.CELL_TYPE_ERROR)
                     {
                         _8018 = Byte.toString(_cell.getErrorCellValue());
                     }
                     else if (_8061 == Cell.CELL_TYPE_BOOLEAN)
                     {
                         _8018 = Boolean.toString(_cell.getBooleanCellValue());
                     }
                     else if (_8061 == Cell.CELL_TYPE_BLANK)
                     {
                         _8018 = "";
                     }
                     else if (_8061 == Cell.CELL_TYPE_NUMERIC)
                     {
                         _8018 = _2498(_cell.getNumericCellValue());
                     }
                     else
                     {
                         _8018 = "";
                     }
                 }
                 else
                 {
                     _8018 = "";
                 }
                 //System.out.println(_nms[n] +":" + _2993.getName() +":" + _8018.trim());
                 _4358.put(_2993.getName(), _8018.trim());
             }



         }






       }
       finally
       {
         if (_5981In != null)
           _5981In.close();
       }

       _227 = new VOBJ();
       _227.addRecord(_4358);

       return _227;
     }
     public  VOBJ _2627(String _5951, String _5960, String[] _nms, HashMap _2954,int _col_1109, String _7046, int _1376, int _3956) throws IOException
     {
       FileInputStream _5981In = null;
       ArrayList _7475 = new ArrayList();
       HashMap _4358 = null;
       HashMap _3992 = null;
       VOBJ _227 = null;
       try
       {
         String _9010 = _5951.trim();
         if(!_9010.substring(_9010.length()-1).equals("/")) _9010 = _9010 +"/";

         _5981In = new FileInputStream(_9010 + _5960);

         int  _8061;
         String _8018  = null;
         PointColumn _2993 = null;

         if(!getExt(_5960).equals("xlsx"))
         {
             POIFSFileSystem _5981System = new POIFSFileSystem(_5981In);
             HSSFWorkbook _107book = new HSSFWorkbook(_5981System);
             HSSFSheet _sheet = _107book.getSheetAt(_1376);

             int _7040=0;
             switch(_3956)
             {
             case 1:   // this.last
             case 3:   // this._3944
                 _7040 = Integer.parseInt(_7046);
                 break;
             case 2:   // this._3977
                 _7040 = this._3599;
                 _3992 = new HashMap();
                 _7046 = _7046.substring(_7046.indexOf("[")+1,_7046.indexOf("]"));
                 StringTokenizer _3971 = new StringTokenizer(_7046,",");
                 while(_3971.hasMoreTokens()){
                     _3992.put(_3971.nextToken().toString(), "");
                 }
                 break;
             }

             for (short ii = (short)_col_1109; ii < _7040; ii++)
             {
                 _4358 = new HashMap();
                 for (short i = 0; i < _nms.length; i++)
                 {
                     _2993 =(PointColumn)_2954.get(_nms[i]);
                     HSSFRow _8067 = _sheet.getRow(_2993.getY());
                     if(_8067 == null) break;
                     HSSFCell _8064 = _8067.getCell(ii);

                     if (_8064 != null) {
                         _8061 = _8064.getCellType();
                         if (_8061 == HSSFCell.CELL_TYPE_FORMULA)
                             _8018 = _2498(_8064.getNumericCellValue());
                         else if (_8061 == HSSFCell.CELL_TYPE_STRING)
                             _8018 = _8064.getStringCellValue();
                         else if (_8061 == HSSFCell.CELL_TYPE_ERROR)
                             _8018 = Byte.toString(_8064.getErrorCellValue());
                         else if (_8061 == HSSFCell.CELL_TYPE_BOOLEAN)
                             _8018 = Boolean.toString(_8064.getBooleanCellValue());
                         else if (_8061 == HSSFCell.CELL_TYPE_BLANK)
                             _8018 = "";
                         else if (_8061 == HSSFCell.CELL_TYPE_NUMERIC)
                             _8018 = _2498(_8064.getNumericCellValue());
                     }
                     else
                         _8018 = "";

                     _4358.put(_2993.getName(), _8018.trim());
                 }
                 _7475.add(_4358);
             }

         }
         else
         {

             XSSFWorkbook _107book = new XSSFWorkbook(_5981In);
             XSSFSheet _sheet = _107book.getSheetAt(_1376);

             int _7040=0;
             switch(_3956)
             {
             case 1:   // this.last
             case 3:   // this._3944
                 _7040 = Integer.parseInt(_7046);
                 break;
             case 2:   // this._3977
                 _7040 = this._3599;
                 _3992 = new HashMap();
                 _7046 = _7046.substring(_7046.indexOf("[")+1,_7046.indexOf("]"));
                 StringTokenizer _3971 = new StringTokenizer(_7046,",");
                 while(_3971.hasMoreTokens()){
                     _3992.put(_3971.nextToken().toString(), "");
                 }
                 break;
             }

             for (short ii = (short)_col_1109; ii < _7040; ii++)
             {
                 _4358 = new HashMap();
                 for (short i = 0; i < _nms.length; i++)
                 {
                     _2993 =(PointColumn)_2954.get(_nms[i]);
                     XSSFRow _8067 = _sheet.getRow(_2993.getY());
                     if(_8067 == null) break;
                     XSSFCell _8064 = _8067.getCell(ii);

                     if (_8064 != null) {
                         _8061 = _8064.getCellType();
                         if (_8061 == Cell.CELL_TYPE_FORMULA)
                             _8018 = _2498(_8064.getNumericCellValue());
                         else if (_8061 == Cell.CELL_TYPE_STRING)
                             _8018 = _8064.getStringCellValue();
                         else if (_8061 == Cell.CELL_TYPE_ERROR)
                             _8018 = Byte.toString(_8064.getErrorCellValue());
                         else if (_8061 == Cell.CELL_TYPE_BOOLEAN)
                             _8018 = Boolean.toString(_8064.getBooleanCellValue());
                         else if (_8061 == Cell.CELL_TYPE_BLANK)
                             _8018 = "";
                         else if (_8061 == Cell.CELL_TYPE_NUMERIC)
                             _8018 = _2498(_8064.getNumericCellValue());
                     }
                     else
                         _8018 = "";

                     _4358.put(_2993.getName(), _8018.trim());
                 }
                 _7475.add(_4358);
             }

         }



       } finally {
         if (_5981In != null)
           _5981In.close();
       }

       _227 = new VOBJ();
       _227.setRecords(_7475);

       return _227;
     }

     public  VOBJ _2624(String _5951, String _5960,  String[] _nms, HashMap _2954,int _8067_1109, String _2387, int _1376, int _3938) throws Exception
     {
       FileInputStream _5981In = null;
       ArrayList _7475 = new ArrayList();
       HashMap _4358 = null;
       HashMap _3992 = null;
       VOBJ _227 = null;

       try
       {
         String _9010 = _5951.trim();
         if(!_9010.substring(_9010.length()-1).equals("/")) _9010 = _9010 +"/";

         _5981In = new FileInputStream(_9010 + _5960);

         int _2381=0,  _8061=0;
         String _8018 = null;
         PointColumn _2993 = null;

         if(!getExt(_5960).equals("xlsx"))
         {
             POIFSFileSystem _5981System = new POIFSFileSystem(_5981In);
             HSSFWorkbook _107book = new HSSFWorkbook(_5981System);


             HSSFSheet _sheet = _107book.getSheetAt(_1376);

             switch(_3938){
             case  1:
                 _2381 = _sheet.getPhysicalNumberOfRows();
                 break;
             case 3:
                 _2381 = Integer.parseInt(_2387);
                 break;
             case 2:
                 _2381 = _sheet.getPhysicalNumberOfRows();
                 _3992 = new HashMap();
                 _2387 = _2387.substring(_2387.indexOf("[")+1,_2387.indexOf("]"));
                 StringTokenizer _3971 = new StringTokenizer(_2387,",");
                 while(_3971.hasMoreTokens()){
                     _3992.put(_3971.nextToken().toString(), "");
                 }
                 break;
             }

             for (int n = _8067_1109; n < _2381; n++)
             {
                 _4358 = new HashMap();
                 HSSFRow row = _sheet.getRow(n);
                 if(row == null) break;
                 for (short r = 0; r < _nms.length; r++) {
                     _2993 =(PointColumn)_2954.get(_nms[r]);
                     HSSFCell _8064 = row.getCell(_2993.getX());
                     if (_8064 != null) {
                         _8061 = _8064.getCellType();
                         if (_8061 == HSSFCell.CELL_TYPE_FORMULA)
                             _8018 = _2498(_8064.getNumericCellValue());
                         else if (_8061 == HSSFCell.CELL_TYPE_STRING)
                             _8018 = _8064.getStringCellValue();
                         else if (_8061 == HSSFCell.CELL_TYPE_ERROR)
                             _8018 = Byte.toString(_8064.getErrorCellValue());
                         else if (_8061 == HSSFCell.CELL_TYPE_BOOLEAN)
                             _8018 = Boolean.toString(_8064.getBooleanCellValue());
                         else if (_8061 == HSSFCell.CELL_TYPE_BLANK)
                             _8018 = "";
                         else if (_8061 == HSSFCell.CELL_TYPE_NUMERIC)
                             _8018 = _2498(_8064.getNumericCellValue());
                     }
                     else
                         _8018 = "";
                     //     System.out.println("�ѱ� �ѱ�    :"+_2993._5162());
                     _4358.put(_2993.getName(), _8018.trim());      //System.out.print("cell("+_2993.getX()+":"+n+")="+cellValue+";");
                 }
                 _7475.add(_4358);                                    //System.out.print("\n");
             }

         }
         else
         {

             XSSFWorkbook _107book = new XSSFWorkbook(_5981In);
             XSSFSheet _sheet = _107book.getSheetAt(_1376);

             switch(_3938)
             {
             case  1:
                 _2381 = _sheet.getPhysicalNumberOfRows();
                 break;
             case 3:
                 _2381 = Integer.parseInt(_2387);
                 break;
             case 2:
                 _2381 = _sheet.getPhysicalNumberOfRows();
                 _3992 = new HashMap();
                 _2387 = _2387.substring(_2387.indexOf("[")+1,_2387.indexOf("]"));
                 StringTokenizer _3971 = new StringTokenizer(_2387,",");
                 while(_3971.hasMoreTokens()){
                     _3992.put(_3971.nextToken().toString(), "");
                 }
                 break;
             }

             for (int n = _8067_1109; n < _2381; n++)
             {
                 _4358 = new HashMap();
                 XSSFRow row = _sheet.getRow(n);
                 if(row == null) break;
                 for (short r = 0; r < _nms.length; r++) {
                     _2993 =(PointColumn)_2954.get(_nms[r]);
                     XSSFCell _8064 = row.getCell(_2993.getX());
                     if (_8064 != null)
                     {
                         _8061 = _8064.getCellType();
                         if (_8061 == Cell.CELL_TYPE_FORMULA)
                             _8018 = _2498(_8064.getNumericCellValue());
                         else if (_8061 == Cell.CELL_TYPE_STRING)
                             _8018 = _8064.getStringCellValue();
                         else if (_8061 == Cell.CELL_TYPE_ERROR)
                             _8018 = Byte.toString(_8064.getErrorCellValue());
                         else if (_8061 == Cell.CELL_TYPE_BOOLEAN)
                             _8018 = Boolean.toString(_8064.getBooleanCellValue());
                         else if (_8061 == Cell.CELL_TYPE_BLANK)
                             _8018 = "";
                         else if (_8061 == Cell.CELL_TYPE_NUMERIC)
                             _8018 = _2498(_8064.getNumericCellValue());
                         else
                             _8018 = "";
                     }
                     else
                         _8018 = "";
                     _4358.put(_2993.getName(), _8018.trim());      //System.out.print("cell("+_2993.getX()+":"+n+")="+cellValue+";");
                 }
                 _7475.add(_4358);                                    //System.out.print("\n");
             }

         }



       }catch(Exception e){
         e.printStackTrace();
         throw new Exception("ReadExcelMRow exception");
       }
       finally
       {
         if(_5981In != null)
           _5981In.close();
       }
       _227 = new VOBJ();
       _227.setRecords(_7475);

       return _227;
     }

     private ArrayList ReadExcel(String _5951, String _5960, String[] _7019,   boolean READ_WITH_HEADER, int _1376) throws IOException
     {
       FileInputStream _5981In = null;
       ArrayList _7475 = new ArrayList();
       HashMap _4358 = null;
       try
       {
         String _9010 = _5951.trim();
         if(!_9010.substring(_9010.length()-1).equals("/")) _9010 = _9010 +"/";

         _5981In = new FileInputStream(_9010 + _5960);
         POIFSFileSystem _5981System = new POIFSFileSystem(_5981In);
         HSSFWorkbook _107Book = new HSSFWorkbook(_5981System);
         //   int sheetCount = workBook.getNumberOfSheets(); //���� sheet ó�� �� ���
         //   int sheetCount = 1;
         int _7040 = _7019.length;
         int _2381, _8064Count, _8061;
         int _1109ingIndex = READ_WITH_HEADER == true ? 0 : 1;
         String _8018 = null;

         // �ش� sheet ���� �Ѵ�.
         HSSFSheet _sheet = _107Book.getSheetAt(_1376);
         _2381 = _sheet.getPhysicalNumberOfRows();
         for (int n = _1109ingIndex; n < _2381; n++) {
           _4358 = new HashMap();
           HSSFRow _8067 = _sheet.getRow(n);
            if(_8067 == null) break;
           // cellCount = row.getPhysicalNumberOfCells();
           // System.out.print(" row " + n + ":");
           for (short r = 0; r < _7040; r++) {
             HSSFCell _8064 = _8067.getCell(r);
             if (_8064 != null) {
               _8061 = _8064.getCellType();
               if (_8061 == HSSFCell.CELL_TYPE_FORMULA)
                 _8018 = _2498(_8064.getNumericCellValue());
               else if (_8061 == HSSFCell.CELL_TYPE_STRING)
                 _8018 = _8064.getStringCellValue();
               else if (_8061 == HSSFCell.CELL_TYPE_ERROR)
                 _8018 = Byte.toString(_8064.getErrorCellValue());
               else if (_8061 == HSSFCell.CELL_TYPE_BOOLEAN)
                 _8018 = Boolean.toString(_8064.getBooleanCellValue());
               else if (_8061 == HSSFCell.CELL_TYPE_BLANK)
                 _8018 = "";
               else if (_8061 == HSSFCell.CELL_TYPE_NUMERIC)
                 _8018 = _2498(_8064.getNumericCellValue());
             }
             else
               _8018 = "";

             _4358.put(_7019[r], _8018.trim());
             //System.out.println("cell" + r + " = " + cellValue + " ");
           }
           // valueObject._7514();
           _7475.add(_4358);        //	            System.out.print("\n");
         }
       }
       finally
       {
         if(_5981In != null)
           _5981In.close();
       }
       return _7475;
     }

     private ArrayList _2615(String _5951, String _5960, String[] _7025, int _1376) throws IOException
     {   return ReadExcel(_5951, _5960, _7025, true , _1376);   }
     private ArrayList _2612(String _5951, String _5960, String[] _7025, int _1376) throws IOException
     {   return ReadExcel(_5951, _5960, _7025, false, _1376);   }


     private  String _2498(double _3239ericValue) {
       double _ceiledValue = Math.ceil(_3239ericValue);
       if (_ceiledValue - _3239ericValue == 0.0)
         return Integer.toString( ( (int) _3239ericValue));
       else
         return Double.toString(_3239ericValue);
     }

     private boolean _4100(String _281) {
       if (_281 == null || _281.equals(""))
         return false;

       char _HIPPEN = '-';
       char _POINT = '.';
       char[] _281Array = _281.toCharArray();
       int _275 = _281Array.length;
       int _2888 = 0;

       for (int m = 0; m < _275; m++) {
         if (!_8030(_281Array[m])) {
           if (_281Array[m] == _HIPPEN)
             if (m != 0)
               return false;
             else
               continue;
           else if (_281Array[m] == _POINT)
             if (m == 0 || ++_2888 > 1 || m == _275 - 1)
               return false;
             else
               continue;
           else
             return false;
         }
       }

       if (_281Array[0] == '0' && (_275 < 3 || _281Array[1] != _POINT))
         return false;
       else
         return true;
     }

     private boolean _8030(char _2894er) {
       final char[] _8033 = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
       final int _6260 = _8033.length;
       for (int m = 0; m < _6260; m++) {
         if (_2894er == _8033[m])
           return true;
       }
       return false;
     }
     private  String _3806(String _1073){
       char[] _ltrim = _1073.toCharArray();
       String _2366 = "";
       boolean _flag = false;
       for(int i=0;i<_ltrim.length;i++){
         if(_ltrim[i] != ' ' ) _flag = true;
         if(_flag == true)     _2366 = _2366 + _ltrim[i];
       }
       return  _2366;
     }
     public  HashMap _4883(HashMap _smap){
       HashMap _2954 = new HashMap();
       Iterator _4070 = _smap.keySet().iterator();
       String _4001 = "";
       while(_4070.hasNext()){
         _4001 = _4070.next().toString();
         if(_smap.get(_4001) instanceof PointColumn){
           _2954.put(_4001,_smap.get(_4001));
         }
       }
       return _2954;
     }
     public  ArrayList _5207(String _4238_2996,String _4238_3374){
       ArrayList _sheets = new ArrayList();
       File _5777 = null;
       FileInputStream _8916 = null;
       DataInput _4271 = null;
       try {
         String _4238path = _4238_2996.trim();
         if(!_4238path.substring(_4238path.length()-1).equals("/")) _4238path = _4238path +"/";
         _5777 = new File(_4238_2996+_4238_3374);
         _8916 = new FileInputStream(_5777);
         _4271 = new DataInputStream(_8916);
         String _1073 = null;
         ArrayList _sheet = null;
         boolean _1109 = false;
         while ( (_1073 = _4271.readLine()) != null) {
           if(_1073.indexOf("//") != -1){
             _1073 = _3806(_1073.substring(0,_1073.indexOf("//")).trim());
           }
           if(_1073==null || _1073.trim().equals("")) continue;
           if(_1073.indexOf("SHEET-START") != -1){
             _sheet = new ArrayList();
             if(_1109 == true) throw new Exception("SHEET-START Syntax ����");
             _1109 = true;
           }
           if(_1109==true){
             _sheet.add(_1073);
           }
           if(_1073.indexOf("SHEET-END") != -1){
             if(_1109 == false) throw new Exception("SHEET-END Syntax ����");
             _sheets.add(_sheet);
             _1109 = false;
           }
         }
       }catch(Exception e){
         e.printStackTrace();
       }finally {
         try {
           if( _8916 != null ){
             _8916.close();
           }
         }catch(Exception e){
           e.printStackTrace();
         }
       }

       return _sheets;
     }


     public  VOBJ _1928( HashMap _821, HashMap _233, String _5963, String _1367) throws Exception{

       VOBJ _2306 = new VOBJ();
       boolean _3977 = false;
       ArrayList _3995 = (ArrayList)_821.get("objectkey");
       try{
         String _3188 = getMultiName(_821,_233);
         if(_3188.equals("")){
           VOBJ _227 = null;
           HashMap rec = new HashMap();
           String _3182="";
           String _7013="";


           ArrayList cols= (ArrayList)_821.get("cols");
           ArrayList _284= (ArrayList)_821.get("vals");
           for(int j=0;j<_284.size();j++){
             _3182 = _284.get(j).toString().substring(0,_284.get(j).toString().indexOf("."));
             _7013 = _284.get(j).toString().substring(_284.get(j).toString().indexOf(".")+1);
             _227 = (VOBJ)_233.get(_3182);


             if(_3995 != null && _3995.size()>0){
               if(this._4109(_3995,cols.get(j).toString()))
                 if(_227.getRecord().get(_7013).trim().equals("")) {
                   _3977=true;
                   break;
                 }
             }

             rec.put(cols.get(j).toString(),_227.getRecord().get(_7013));
           }

           if(_3977 != true){
             _2306.addRecord(rec);
           }else{
             System.out.println("WARNING: EXCEL UPLOAD KEY-NULL" );
           }


         }else{
           ArrayList _7004= (ArrayList)_821.get("cols");
           ArrayList _284= (ArrayList)_821.get("vals");
           VOBJ _3398 =  (VOBJ)_233.get(_3188);
           String _3182="";
           String _7013="";

           VOBJ _227 = null;
           HashMap _2594 = null;
           _3398.first();
           while(_3398.next()){
             _2594 = new HashMap();
             for(int j=0;j<_284.size();j++){
               _3182 = _284.get(j).toString().substring(0,_284.get(j).toString().indexOf("."));
               _7013 = _284.get(j).toString().substring(_284.get(j).toString().indexOf(".")+1);


               if(_3995 != null && _3995.size()>0){
                 if(this._4109(_3995,_7004.get(j).toString()))
                   if(_227.getRecord().get(_7013).trim().equals("")) {
                     _3977=true;
                     break;
                   }
               }


               if(_3182.equals(_3188)){
                 _2594.put(_7004.get(j).toString(),_3398.getRecord().get(_7013));
               }else{
                 _227 = (VOBJ)_233.get(_3182);
                 _2594.put(_7004.get(j).toString(),_227.getRecord().get(_7013));
               }
             }

             if(_3977 != true){
                _2306.addRecord(_2594);
             }else{
                System.out.println("WARNING: EXCEL UPLOAD KEY-NULL" );
                break;
             }
           }
         }

         _2306.Println("EXCEL UPLOAD _7583");
       }catch(Exception e){
         e.printStackTrace();
       }
       return _2306;
     }

     private String getMultiName(HashMap _821,HashMap _233) throws Exception{
       int mcnt =0;
       int _2651=0;
       String _2366 ="";
       VOBJ _227 = null;
       ArrayList _3197 = (ArrayList)_821.get("vals");
       String _3188 = "";
       String _656="";

       for(int i=0;i<_3197.size();i++){
         _656 = _3197.get(i).toString();
         if(_656.indexOf(".") == -1)	continue;
         _3188 = _3806(_656.substring(0,_656.indexOf("."))).trim();
         _227 = (VOBJ)_233.get(_3188);
         if(_227.getRecordCnt() > 1 && _2651 != _227.getRecordCnt()) {
           _2651 = _227.getRecordCnt();
           _2366 = _3188;
           System.out.println(_3188 +":" + _227.getRecordCnt());
           mcnt++;
         }
         if(mcnt > 1) throw new Exception("Object ���� ����:1���� ū Record count�� �ٸ� object���� ���� �մϴ�.");
       }
       return _2366;
     }

     private boolean  _4109(ArrayList _3995, String _3374){
       boolean _2366 = false;
       for(int i=0;i<_3995.size();i++){
         if(_3374.equals(_3995.get(i).toString())) {
           _2366 = true;
           break;
         }
       }
       return _2366;
     }

     //=============== OBJ-TYPE ȣ�� START ===============
     //Socket Client ȣ����



     //=============== excel upload end   =================
     //=============== OBJ-TYPE ȣ�� START ===============
     //�ܺ� ȣ�� ó�� ���
     public VOBJ callPSExternal(VOBJ _227, Connection _9199, HashMap _9093) throws Exception{
         XObject _50 = new XObject();
         StringBuffer _2279 = _50.getXmlStringBuffer(_227, "XTAG");
         String _2366 = _9120( _2279,  _9199,  _9093);
         _50.setNametype(0);
         _50.setXmlData(_2366);
         VOBJ _4040 = new VOBJ();
         _4040.setRecords(_50.getXmlData());
         return _4040;
     }
     // 외부 클래스 호출
     private String _9120(StringBuffer _2279 , Connection _9199, HashMap _9093) throws Exception{
         String _0298 = _9093.get("PACKAGE").toString() + "." +   _9093.get("CLASS").toString() ;
         String _938 = _9093.get("METHOD").toString();
         String _1073 = _2279.toString();
         Class _3948 = null;
         _3948 = Class.forName( _0298 );

         int _1349 = 1;
         if(_9199 != null ) _1349 =2;
         Object _847[] = new Object[_1349];
         Class[] _276 = new Class[_1349];

         _276[0] = _1073.getClass();
         _847[0] = _1073;
         if(_1349 ==2 ){
             _276[1] = Object.class;
             _847[1] = _9199;
         }
         Method _765 = _3948.newInstance().getClass().getMethod(_938, _276);
         String _2366 = (String) _765.invoke(_3948.newInstance(),_847);
         return _2366;
     }
     public DOBJ callPSExternal(DOBJ _87, Connection _384, HashMap _097)  throws Exception{
         _87 = _467287( _87,  _384,  _097);
         return _87;
     }

     private DOBJ _467287(DOBJ _6185 , Connection _0193, HashMap _1928) throws Exception{
         String objectname = _1928.get("PACKAGE").toString() + "." +   _1928.get("CLASS").toString() ;
         String _438 = _1928.get("METHOD").toString();
         DOBJ _3847 =  null;
         Class commandClass = null;
         commandClass = Class.forName( objectname );
         if(_6185._9234() == 1) {
             CONFIG _cfg = new CONFIG();
             String _classpath= _cfg.getPBConfig("RUNNINGCLASS_" + _6185._getGnm());
             if(_classpath == null || _classpath.trim().equals("")){
                 _classpath = _cfg.getPBConfig("RUNNINGCLASS");
             }
             if(!_6185._getRP().trim().equals("")){
                 _classpath = _6185._getRP();
             }
             SCLoader _sloader = new SCLoader(_classpath);
             commandClass = _sloader.findClass(objectname);
         }else {
             commandClass = Class.forName( objectname );
         }

         int _187 = 1;
         if(_0193 != null ) _187 =2;
         Object _2736[] = new Object[_187];
         Class[] _47537 = new Class[_187];

         _47537[0] = _6185.getClass();
         _2736[0] = _6185;
         if(_187 ==2 ){
             _47537[1] = Object.class;
             _2736[1] = _0193;
         }
         try{
             Method _6765 = commandClass.newInstance().getClass().getMethod(_438, _47537);
             _3847 = (DOBJ) _6765.invoke(commandClass.newInstance(),_2736);
        }catch(Exception e){
             e.printStackTrace();
             throw new Exception("EXTENAL OBJECT CALL ERROR");
         } 
   
         return _3847;
     }

     //���� ȣ�� ó�� ���
     //HashMap : INMAP, PACKAGE, CLASS, METHOD
     public void callExpCall(DOBJ _6185, HashMap _9093) throws Exception
     {
         ArrayList _9108 = _5078Name(_9093.get("INMAP").toString());
         _6185  = _2495CallingObject(_6185, _9108 );
         _9114Exp(_6185,  _9093 );
     }

     private void _9114Exp(DOBJ _6185, HashMap _9093) throws Exception
     {
         String objectname = _9093.get("PACKAGE").toString() + "." +   _9093.get("CLASS").toString() ;
         String methodname = _9093.get("METHOD").toString();
         //String methodname = "CTL"+_9093.get("METHOD").toString();
         Class commandClass = null;
         if(_6185._9234() == 1)
         {
             CONFIG _cfg = new CONFIG();
             String _classpath= _cfg.getPBConfig("RUNNINGCLASS_" + _6185._getGnm());
             if(_classpath == null || _classpath.trim().equals("")){
                 _classpath = _cfg.getPBConfig("RUNNINGCLASS");
             }
             if(!_6185._getRP().trim().equals("")){
                 _classpath = _6185._getRP();
             }
             SCLoader _sloader = new SCLoader(_classpath);
             commandClass = _sloader.findClass(objectname);
         }else
         {
             commandClass = Class.forName( objectname );
         }

         int _1349 = 1;
         Object arg[] = new Object[_1349];
         Class[] cls = new Class[_1349];
         DOBJ _6188 = _6611CallingDOBJ(_6185);

         cls[0] = _6188.getClass();
         arg[0] = _6188;

         Method _method = commandClass.newInstance().getClass().getMethod(methodname, cls);
         DOBJ _2468 = (DOBJ) _method.invoke(commandClass.newInstance(),arg);
     }

     public DOBJ callPSInternal(DOBJ _6185, Connection _9199, HashMap _9093) throws Exception{
         ArrayList _9108 = _5078Name(_9093.get("INMAP").toString());
         _6185  = _2495CallingObject(_6185, _9108 );
         _6185  = _9114(_6185, _9199, _9093 );
         _6185  = _2495CalledObject (_6185, _9108);
         return _6185;
     }

     private DOBJ _9114(DOBJ _6185, Connection _9199, HashMap _9093) throws Exception{
         String objectname = _9093.get("PACKAGE").toString() + "." +   _9093.get("CLASS").toString() ;
         String methodname = _9093.get("METHOD").toString();
         //String methodname = "CTL"+_9093.get("METHOD").toString();
         Class commandClass = null;
         if(_6185._9234() == 1) {
             CONFIG _cfg = new CONFIG();
             String _classpath= _cfg.getPBConfig("RUNNINGCLASS_" + _6185._getGnm());
             if(_classpath == null || _classpath.trim().equals("")){
                 _classpath = _cfg.getPBConfig("RUNNINGCLASS");
             }

             if(!_6185._getRP().trim().equals("")){
                 _classpath = _6185._getRP();
             }
             SCLoader _sloader = new SCLoader(_classpath);
             commandClass = _sloader.findClass(objectname);
         }else {
             commandClass = Class.forName( objectname );
         }

         int _1349 = 1;
         if(_9199 != null ) _1349 =2;
         Object arg[] = new Object[_1349];
         Class[] cls = new Class[_1349];
         DOBJ _6188 = _6611CallingDOBJ(_6185);

         cls[0] = _6188.getClass();
         arg[0] = _6188;

         if(_1349 ==2 ){
             //cls[1] = _9199.getClass();
             cls[1] = Object.class;
             arg[1] = _9199;
         }

         Method _method = commandClass.newInstance().getClass().getMethod(methodname, cls);
         DOBJ _2468 = (DOBJ) _method.invoke(commandClass.newInstance(),arg);

         //-- �ΰ� �ʵ�ó�� ����
         if(_9093.get("OUTOBJ1")!= null && !_9093.get("OUTOBJ1").toString().equals("")){
             ArrayList _mapout = this._5078Name(_9093.get("OUTOBJ1").toString());
             String _instr="";
             String _outstr="";
             HashMap _map = null;
             for(int i=0;i<_mapout.size();i++){
                 _map = (HashMap)_mapout.get(i);
                 _instr = _map.get("IN").toString();
                 _outstr = _map.get("OUT").toString();
                 VOBJ _rvobj = new VOBJ();
                 _rvobj.setRecords(_2468.getRetObject(_outstr).getRecords());
                 _rvobj.setName(_instr);
                 _rvobj.setRetcode(1);
                 _rvobj.setHeadColumn(_2468.getRetObject(_outstr).getHeadColumn());
                 _6185.setRetObject(_rvobj);
             }
         }

         //_2468.getRetObject(_9093.get("OUTOBJ").toString()).Println("rtn");
         if(!_9093.get("OUTOBJ").toString().equals("")){   //-- return�� ��� void������ ����
             _2468.dispRetObjectKeyNames();
             if(!_2468.isRtnObjectFoundname(_9093.get("OUTOBJ").toString())){
                 throw new Exception("CALL�Ǿ��� Process�� Return Object�� �ش� object�� �������� �ʽ��ϴ�.(" +_9093.get("OUTOBJ") +")");
             }

             VOBJ _2306 = new VOBJ();
             _2306.setRecords(_2468.getRetObject(_9093.get("OUTOBJ").toString()).getRecords());
             _2306.setName(_9093.get("OBJECTID").toString());
             _2306.setRetcode(1);
             _2306.setHeadColumn(_2468.getRetObject(_9093.get("OUTOBJ").toString()).getHeadColumn());
             _6185.setRetObject(_2306);
         }

         return _6185;
     }

     private DOBJ _6611CallingDOBJ(DOBJ _6185)
     {
         DOBJ _2366 = new DOBJ();
         _2366._9220(_6185._9234());
         ArrayList _7475 = _6185.getRetObjectKeys();
         for(int i=0;i<_7475.size();i++)
         {
             VOBJ _227 = new VOBJ();
             _227.setRecords(_6185.getRetObject(_7475.get(i).toString()).getRecords());
             _227.setName(_7475.get(i).toString());
             _2366.setRetObject(_227);
         }
         return _2366;
     }

     //DOBJ�� CALLING Name���� ó��
     public DOBJ _2495CallingObject(DOBJ _6185, ArrayList _9108)
     {
         HashMap _3353 = null;
         for(int i=0;i<_9108.size();i++){
             _3353 = (HashMap)_9108.get(i);
             // _6185.getRetObject(_3353.get("IN").toString()).Println("ȣ��:INPUT:"+_3353.get("OUT").toString());
             _6185._beforeCalledRetObject(_3353.get("IN").toString(), _3353.get("OUT").toString());   //_renameRetObject
         }
         return _6185;
     }

     //call�ϱ� ������ object name���� �纯�� ó�� �Ѵ�.
     private DOBJ _2495CalledObject(DOBJ _6185, ArrayList _9108)
     {
         HashMap _3353 = null;
         for(int i=0;i<_9108.size();i++)
         {
             _3353 = (HashMap)_9108.get(i);
             _6185._afterCalledRetObject (_3353.get("OUT").toString(), _3353.get("IN").toString());   //_renameRetObject
         }
         return _6185;
     }
     //�Է� name mapping data��
     public ArrayList _5078Name( String _4271map )
     {
         ArrayList _2366 = new ArrayList();
         if(_4271map.trim().equals("")) return _2366;
         _4271map = _4271map.trim();
         String[] _split = _4271map.split(",");
         for(int i=0;i<_split.length;i++){
             HashMap _map = new HashMap();
             String[] _mapnames = _split[i].split(":");
             _map.put("IN",_mapnames[0]);
             _map.put("OUT",_mapnames[1]);
             _2366.add(_map);
         }
         return _2366;
     }


     //=============== OBJ-TYE ȣ�� END  ===============

}
