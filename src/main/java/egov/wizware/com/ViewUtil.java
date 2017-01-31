package egov.wizware.com;

import java.text.DecimalFormat;
import java.util.Calendar;
import org.apache.poi.hssf.usermodel.*;
import java.io.IOException;
import java.io.FileOutputStream;
import java.util.*;
import java.text.SimpleDateFormat;

import egov.wizware.com.*;
import java.sql.Connection;
import java.sql.SQLException;

public class ViewUtil {
    public ViewUtil() { 
    }
    public  int factorial(int n) {

		if (n == 1) return 1;

		else return n * factorial(n - 1);

	}

    //일자을 format yyyy/mm/dd 형식으로 foramt변경처리
    public String getDayFormat(String date){
        String _rtn ="";
        if(date.length() < 8) {
            return date;
        }
        _rtn = date.substring(0,4) +"/" + date.substring(4,6) +"/" + date.substring(6,8);
        return _rtn;
    }
    //수치에 고정 foramt처리하기
    public String numberFormat(String _val){
        String _wsTemp;
        if(_val==null || _val.equals("")) _val="0";
        double _wdNumber = Double.parseDouble(_val);
        DecimalFormat df = new DecimalFormat( "###,###,###.###" );
        _wsTemp = df.format(_wdNumber);
        return _wsTemp;
    }

    //수치에 설정값에 의한 format처리
    public String numberFormat(String _val, String fmt){
        String _wsTemp;
        if(_val==null || _val.equals("")) _val="0";
        double _wdNumber = Double.parseDouble(_val);
        DecimalFormat df = new DecimalFormat( fmt );
        _wsTemp = df.format(_wdNumber);
        return _wsTemp;
    }
    //1:일요일 2:월 3:화 4:수 5:목 6:금 7:토
    //오늘의 요일가져오기
    public int todayWeek(){
        Calendar cal= Calendar.getInstance ( );
        int day_of_week = cal.get(Calendar.DAY_OF_WEEK);
        return day_of_week;
    }
    //해당일자의 요일가져오기
    public int todayWeek(int yyyy, int mm, int dd){
        Calendar cal = Calendar.getInstance();
        cal.set(yyyy, mm-1, dd);
        int day_of_week = cal.get(Calendar.DAY_OF_WEEK);
        return day_of_week;
    }
    //해당일자의 요일가져오기
    public String todayWeek(String yyyymmdd){
        Calendar cal = Calendar.getInstance();
        int yyyy =Integer.parseInt(yyyymmdd.substring(0,4));
        int mm =Integer.parseInt(yyyymmdd.substring(4,6));
        int dd =Integer.parseInt(yyyymmdd.substring(6,8));
        cal.set(yyyy, mm-1, dd);
        int day_of_week = cal.get(Calendar.DAY_OF_WEEK);

        return day_of_week+"";
    }
    //수치일자 또는 월을  2자리 String으로 변환처리 한다.
    public String getDDFormat(int num){
        String _rtn="";
        if(num < 1) {
            _rtn ="00";
        }else if(num < 10) {
            _rtn ="0" + num;
        }else {
            _rtn =num +"";
        }
        return _rtn;
    }
    //수치일자 또는 월을  2자리 String으로 변환처리 한다.
    public String getDDFormat(Integer num){
        String _rtn="";
        if(num.intValue() < 1) {
            _rtn ="00";
        }else if(num.intValue() < 10) {
            _rtn ="0" + num.intValue();
        }else {
            _rtn =num.intValue() +"";
        }
        return _rtn;
    }
    //해당 월의 1일의 요일 가져오기
    public int firstDayWeek(String yyyymmdd){
        int yyyy =Integer.parseInt(yyyymmdd.substring(0,4));
        int mm =Integer.parseInt(yyyymmdd.substring(4,6));
        int dd =1;
        int day_of_week =todayWeek(yyyy, mm, dd);
        return day_of_week;
    }
    //오늘 일자 해당 format으로 가져오기: yyyyMMddHHmmssSSS
    public String getToday(String format){
        Date dt = new Date();
        SimpleDateFormat yyyyMMddE = new SimpleDateFormat(format);
        String rtn;
        rtn = yyyyMMddE.format(dt);
        return rtn;
    }
    //오늘 일자가져오기
    public String getToday(){
        Date dt = new Date();
        SimpleDateFormat yyyyMMddE = new SimpleDateFormat("yyyyMMdd");
        String rtn;
        rtn = yyyyMMddE.format(dt);
        return rtn;
    }
    //입력일자을 기준으로하여 기준일만큼의 차이일자 return한다
    public  String getToday(String _indate, int _gijun)
    {
        Calendar _rightNow = Calendar.getInstance();
        String _format ="";
        if(_indate.length()==8){
            _rightNow.set(Integer.parseInt(_indate.substring(0,4)),
                          Integer.parseInt(_indate.substring(4,6))-1,
                          Integer.parseInt(_indate.substring(6,8)));
            _format ="yyyyMMdd";
        }
        _rightNow.add(_rightNow.DATE , _gijun);
        Date _TodayDate =  _rightNow.getTime();
        SimpleDateFormat _yyyyMMddE = new SimpleDateFormat(_format);
        String _wsCurrentDate;
        _wsCurrentDate = _yyyyMMddE.format(_TodayDate);
        return _wsCurrentDate;
    }
    //오늘일자을 기준으로하여 기준일만큼의 차이일자 return한다
    public  String getToday(int _gijun)
    {
        String indate = getToday();
        Calendar _rightNow = Calendar.getInstance();
        String _format ="";
        if(indate.length()==8){
            _rightNow.set(Integer.parseInt(indate.substring(0,4)),
                          Integer.parseInt(indate.substring(4,6))-1,
                          Integer.parseInt(indate.substring(6,8)));
            _format ="yyyyMMdd";
        }
        _rightNow.add(_rightNow.DATE , _gijun);
        Date _TodayDate =  _rightNow.getTime();
        SimpleDateFormat _yyyyMMddE = new SimpleDateFormat(_format);
        String _wsCurrentDate;
        _wsCurrentDate = _yyyyMMddE.format(_TodayDate);
        return _wsCurrentDate;
    }
    //해당 월을 가져온다 , 현재일자를 기준으로 하여 월을 계산하여 return한다.
    public  String getMonth(int _gijun)
    {
        String indate = getToday();
        Calendar _rightNow = Calendar.getInstance();
        String _format ="";
        _rightNow.set(Integer.parseInt(indate.substring(0, 4)),
                      Integer.parseInt(indate.substring(4, 6)) - 1,
                      Integer.parseInt(indate.substring(6, 8)));
        _format = "yyyyMMdd";
        _rightNow.add(_rightNow.MONTH, _gijun);
        Date dt =  _rightNow.getTime();
        SimpleDateFormat _yyyyMMddE = new SimpleDateFormat(_format);
        String rtn;
        rtn = _yyyyMMddE.format(dt);
        return rtn;
    }
    //해당 월의 마지막 일자 가져오기
    public int getLastday(String yyyymmdd)  {
      int day;
      int month;
      int year;
      year = Integer.parseInt(yyyymmdd.substring(0,4));
      month = Integer.parseInt(yyyymmdd.substring(4,6));
      switch(month)
      {
          case 1:
          case 3:
          case 5:
          case 7:
          case 8:
          case 10:
          case 12: day = 31;
                   break;
          case 2: if ((year % 4) == 0) {
                      if ((year % 100) == 0 && (year % 400) != 0) { day = 28; }
                      else { day = 29; }
                  } else { day = 28; }
                  break;
          default: day = 30;
      }
      return day;
  }
  // 두일자간의 차이 월수
  public  int getMonthDiff(String _strStartDate, String _strEndDate ) throws Exception{
      int _smonthcnt=0;
      int _emonthcnt=0;
      _smonthcnt = (Integer.parseInt(_strStartDate.substring(0, 4)) * 12) + Integer.parseInt(_strStartDate.substring(4, 6));
      _emonthcnt = (Integer.parseInt(_strEndDate.substring(0, 4)) * 12) + Integer.parseInt(_strEndDate.substring(4, 6));
      return _smonthcnt - _emonthcnt;
  }
  //두일자간의 차이 년수
  public  int getYearDiff(String _strStartDate, String _strEndDate ) throws Exception{
      int _smonthcnt=0;
      int _emonthcnt=0;
      _smonthcnt = Integer.parseInt(_strStartDate.substring(0, 4));
      _emonthcnt = Integer.parseInt(_strEndDate.substring(0, 4));
      return  _smonthcnt - _emonthcnt;
  }
  //현재 시분초를 가져온다
  public String getCurrentTime()
  {
      Date dt = new Date();
      SimpleDateFormat format = new SimpleDateFormat("HHmmss");
      String rtn;
      rtn = format.format(dt);
      return rtn;
  }
  //코드변환처리
  public String getCvt(String str)
  {
      try
      {
          if(str !=null && !str.equals(""))
              str = new String(str.getBytes("8859_1"), "KSC5601");
      }catch(Exception e)
      {
          e.printStackTrace();
      }
      return str;
  }

  //excel 파일 head정보입력하여 파일 생성하기
  // 파일 path, 파일명, sheet명, 대상column, head-title명, 생성대상 data(VOBJ)
  public void WriteWithHeader(String _path, String _name, String sheetName, String[] _colNames,  String[] _headerNames, VOBJ _vobj) throws IOException
  {
      _WriteExcel(_path, _name, sheetName, _colNames, _headerNames, _vobj);
  }
  // 파일 path, 파일명, sheet명, 대상column, 생성대상 data(VOBJ)
  public void WriteWithoutHeader(String _path, String _name, String sheetName, String[] _colNames,  VOBJ _vobj) throws IOException
  {
      _WriteExcel(_path, _name, sheetName, _colNames, null, _vobj);
  }
  public void WriteWithoutHeader(String _path, String _name, String sheetName,   VOBJ _vobj) throws IOException
  {
      ArrayList clist = _vobj.getColumnNames();
      String[] _colNames = new String[clist.size()];
      for(int i=0;i<clist.size();i++){
          _colNames[i] = clist.get(i).toString();
      }
      _WriteExcel(_path, _name, sheetName, _colNames, null, _vobj);
  }
  public void WriteWithHeader(String _path, String _name, String sheetName, VOBJ _vobj) throws IOException
  {
      ArrayList clist = _vobj.getColumnNames();
      String[] _colNames = new String[clist.size()];
      String[] _HeaderNames = new String[clist.size()];
      for(int i=0;i<clist.size();i++){
          _colNames[i] = clist.get(i).toString();
          _HeaderNames[i] = clist.get(i).toString();
      }
      _WriteExcel(_path, _name, sheetName, _colNames, _HeaderNames, _vobj);
  }

  public void WriteWithoutHeader( String _name, String sheetName,   VOBJ _vobj) throws IOException
  {
      ArrayList clist = _vobj.getColumnNames();
      String[] _colNames = new String[clist.size()];
      for(int i=0;i<clist.size();i++){
          _colNames[i] = clist.get(i).toString();
      }
      String _fname = this.getToday("yyyyMMddHHmmssSSS");
      _WriteExcel("c:/", _name, sheetName, _colNames, null, _vobj);
  }
  //EXCEL 파일 생성
  private void _WriteExcel(String _path, String _name, String _sheetName, String[] _colNames, String[] _headerNames, VOBJ _vobj) throws IOException
  {
      if(_vobj == null)   throw new IllegalArgumentException("Invalid Argument: valueObject == null");
      if(_colNames == null || _colNames.length == 0) throw new IllegalArgumentException("Invalid Argument: _colNames == null || _colNames.length == 0");

      HSSFWorkbook _workbook = new HSSFWorkbook();
      HSSFSheet _sheet = _workbook.createSheet();
      //_workbook.setSheetName(0, _sheetName, HSSFWorkbook.ENCODING_UTF_16);

      HSSFFont font = _workbook.createFont();
      font.setFontHeightInPoints((short)11);
      font.setFontName("돋움");

      HSSFCellStyle _style = _workbook.createCellStyle();
      _style.setFont(font);
      _style.setBorderBottom(HSSFCellStyle.BORDER_NONE);
      _style.setBorderLeft(HSSFCellStyle.BORDER_NONE);
      _style.setBorderRight(HSSFCellStyle.BORDER_NONE);
      _style.setBorderTop(HSSFCellStyle.BORDER_NONE);

      HSSFRow _row = null;
      HSSFCell _cell = null;

      int _rowIndex = 0;
      if(_headerNames != null)
      {
          _row = _sheet.createRow(_rowIndex++);
          for(int m = 0; m < _headerNames.length; m++)
          {
              _cell = _row.createCell((short)m);
              //_cell.setEncoding(HSSFCell.ENCODING_UTF_16);
              _cell.setCellStyle(_style);
              _cell.setCellValue(_headerNames[m]);
          }
      }
      String _cellValue = null;
      int _colCount = _colNames.length;

      _vobj.first();
      while(_vobj.next())
      {
          _row = _sheet.createRow(_rowIndex++);
          for(int m = 0; m < _colCount; m++)
          {
              _cell = _row.createCell((short)m);
             // _cell.setEncoding(HSSFCell.ENCODING_UTF_16);
              _cell.setCellStyle(_style);
              _cellValue = _vobj.getRecord().get(_colNames[m]).toString();
              _cell.setCellValue(_cellValue);
          }
      }

      FileOutputStream _fileOut = null;
      try
      {
          _fileOut = new FileOutputStream(_path + _name);
          _workbook.write(_fileOut);
      }
      finally
      {
          if(_fileOut != null)
              _fileOut.close();
      }
  }
  //주민번호 format처리
  public String getJuminno(String date){
      String _rtn ="";
      if(date.length() < 13) {
          return date;
      }
      _rtn = date.substring(0,6) +"-" + date.substring(6,13) ;
      return _rtn;
  }

  //특정key의 값과동일한 record를 가져오는 method
  public VOBJ getOBJEquals(HashMap keys, VOBJ _ivobj){
      VOBJ _rtn = new VOBJ();
      boolean eq=true;
      ArrayList klist = new ArrayList();
      Iterator it =keys.keySet().iterator();
      while(it.hasNext()){
          klist.add(it.next());
      }
      _ivobj.first();
      while(_ivobj.next()){
          eq = true;
          for(int i=0;i<klist.size();i++){
              if(!keys.get(klist.get(i)).toString().equals(_ivobj.getRecord().get(klist.get(i).toString()))){
                  eq=false;
                  break;
              }
          }
          if(eq==true){
              _rtn.addRecord(_ivobj.getHashRecord());
          }
      }


      return _rtn;
  }



  //**************************************** BilAmout **************************************************
  //입력object data를 가로형태의 data로 변형처리 한다.
  //입력object,  기준columns,  반복columns,  label-column
  public  VOBJ BilAmount_CMerage(VOBJ vobj, ArrayList glist, ArrayList mlist , String label)
  {
      VOBJ rvobj = new VOBJ();
      if(vobj.getRecordCnt()==0 || vobj == null ){
          return rvobj;
      }
      HashMap fmap = new HashMap();
      HashMap nmap = new HashMap();
      ArrayList tlist = new ArrayList();
      HashMap   record = null;
      try{
          String fval="";
          String nval="";
          String lbl="";
          boolean eq = true;
          vobj.first();
          while (vobj.next())
          {
              if (vobj.current == 0)
              {
                  record = new HashMap();
                  for(int i=0;i<glist.size();i++)
                  {
                      fmap.put(glist.get(i),vobj.getRecord().get(glist.get(i)));
                      nmap.put(glist.get(i),vobj.getRecord().get(glist.get(i)));
                  }
                  record = vobj.cpRecord();
                  for(int i=0;i<mlist.size();i++)
                  {
                      record.remove(mlist.get(i).toString().trim());
                      lbl = vobj.getRecord().get(label);
                      record.put(mlist.get(i).toString().trim()+lbl, vobj.getRecord().get(mlist.get(i).toString()));
                  }
              }
              for(int i=0;i<glist.size();i++)
              {
                  nmap.put(glist.get(i),vobj.getRecord().get(glist.get(i)));
              }
              eq= true;
              for(int i=0;i<glist.size();i++)
              {
                  fval = fmap.get(glist.get(i)).toString();
                  nval = nmap.get(glist.get(i)).toString();
                  if (!fval.equals(nval))
                  {
                      eq = false;
                      break;
                  }
              }
              if(eq == true)
              {
                  for(int i=0;i<mlist.size();i++)
                  {
                      lbl = vobj.getRecord().get(label);
                      record.put(mlist.get(i).toString().trim()+lbl, vobj.getRecord().get(mlist.get(i).toString()));
                  }
              }
              else
              {
                  tlist.add(record);
                  record = new HashMap();
                  for(int i=0;i<glist.size();i++)
                  {
                      fmap.put(glist.get(i),vobj.getRecord().get(glist.get(i)));
                  }
                  record = vobj.cpRecord();
                  for(int i=0;i<mlist.size();i++)
                  {
                      record.remove(mlist.get(i).toString().trim());
                      lbl = vobj.getRecord().get(label);
                      record.put(mlist.get(i).toString().trim()+lbl, vobj.getRecord().get(mlist.get(i).toString()));
                  }
              }
          }
          tlist.add(record);
          rvobj = new VOBJ();
          rvobj.setRecords(tlist);

      }catch(Exception e){
          e.printStackTrace();
      }
      return rvobj;
  }

  //N-OBJECT, 연결자columns, 1-OBJECT
  public VOBJ BilAmount_LinkObject(VOBJ _vobjn, ArrayList glist, VOBJ _vobj1) throws Exception
  {
      VOBJ rvobj = new VOBJ();
      try{
          if(_vobjn == null || _vobjn.getRecordCnt()==0 ){
              return rvobj;
          }
          ArrayList _alistx = _vobj1.getColumnNames();
          ArrayList _alist = new ArrayList();
          for(int i=0;i<_alistx.size();i++){
              if(!BilAmount_find(glist, _alistx.get(i).toString())) {
                  _alist.add(_alistx.get(i).toString());
              }
          }
          ArrayList _records = new ArrayList();
          HashMap recx = null;
            _vobjn.first();
            while(_vobjn.next()){
                recx = _vobjn.getHashRecord();
                recx = BilAmount_Eqrecord(recx, _vobj1, glist, _alist);
                _records.add(recx);
            }
            rvobj.setRecords(_records);
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception("");
        }
        return rvobj;
    }
    private HashMap BilAmount_Eqrecord(HashMap currec, VOBJ _vobj1, ArrayList glist, ArrayList _alist)
    {
        boolean eq=false;
        String valn="";
        String val1="";
        _vobj1.first();
        while(_vobj1.next()){
            eq = true;
            for(int i=0;i<glist.size();i++)
            {
                valn = currec.get(glist.get(i)).toString();
                val1 = _vobj1.getRecord().get(glist.get(i));
                if(!valn.equals(val1)){
                    eq= false;
                    break;
                }
            }

            if(eq==true){
                currec.putAll(_vobj1.getHashRecord());
                break;
            }
        }
        if(eq==false){
            for(int i=0;i<_alist.size();i++){
                currec.put(_alist.get(i),"");
            }
        }
        return currec;
    }
    private boolean BilAmount_find(ArrayList _alist, String _name)
    {
        boolean _rtn= false;
        for(int i=0;i<_alist.size();i++){
            if(_name.equals(_alist.get(i))) {
                _rtn = true;
                break;
            }
        }
        return _rtn;
  }
  //********************************************************************************************
}













