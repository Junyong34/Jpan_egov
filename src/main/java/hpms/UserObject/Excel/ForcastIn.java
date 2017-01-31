package hpms.UserObject.Excel;
import org.apache.poi.poifs.filesystem.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;

import hpms.UserObject.BatchExe.FileUtility;

import java.util.*;
import java.io.*;
import java.text.*;
import WIZ.FR.COM.*;
import WIZ.FR.UTIL.*;
import egov.wizware.ria.EgovProperties;
public class ForcastIn
{
    private final String rootPath = EgovProperties.getProperty("Globals.rootPath"); //"c:/tomcat7/webapps/hpms/";
    private final String planlayoutfile ="forcastdown_std_layout.xlsx";    //forecast download layout file name

    private  ArrayList ordername= new ArrayList();
    private  String downtempfold = this.rootPath + "ExcelDownLoad";
    private  String layoutfilefold = this.rootPath + "ExcelLayout";

    public ForcastIn()
    {
    }

    //---------------------------------------------------------------------------------------------------------------
    //----------------------------   [ download method processing start  ] ------------------------------------------
    //---------------------------------------------------------------------------------------------------------------
    private void setDownloadOrderName()
    {
       ordername.add("PROCESSING_FLAG");
       ordername.add("DATA_TYPE");
       ordername.add("PID");
       ordername.add("PID_THEME");
       ordername.add("USE_COMPANY_CD");
       ordername.add("USE_ORG_CD");
       ordername.add("REQ_COMPANY_CD");
       ordername.add("REQ_ORG_CD");
       ordername.add("HPMS_ID");
       ordername.add("HPMS_ID_NM_JP");
       ordername.add("INVEST_TYPE_CD");
       ordername.add("INVEST_TYPE_NAME");
       ordername.add("APPLICATION");
       ordername.add("ITEM_NAME");
       ordername.add("CUSTOMER_CD");
       ordername.add("UNIT");
       ordername.add("UPDATE_TIME");
       ordername.add("UPDATE_USER_ID");
   }

    public   DOBJ downloadPlanExcel(DOBJ indobj)
    {
        String fromYYYYMM ="";
        String toYYYYMM ="";
        ordername= new ArrayList();
        setDownloadOrderName();
        //-----------------[INPUT:fromYYYYMM, toYYYYMM-->Dataset Name: S]------------------------------------------
        VOBJ dvobj = indobj.getRetObject("S");
        VOBJ invobj = null;
        if(indobj.containKey("EXDN1"))
        {
           invobj = indobj.getRetObject("EXDN1");
        }
        else
        {
            invobj = indobj.getRetObject("EXDN2");
        }
     
       
        dvobj.Println("INPUT SSSSSSS DATA");
        invobj.Println("INPUT HP1 DATA");
        //-----------------------------------------------------------------------Dataset Name 확인 필요-------------
        String unifilename = "";
        int _sheetno=0;
        File _fp = null;
        FileInputStream _fin = null;

        try
        {
            unifilename = getUniFilename();
            if(layoutcopy(planlayoutfile,  unifilename )==false)
            {
                return null;
            }
            _fp = new File(downtempfold + "/" + unifilename);
            fromYYYYMM =  dvobj.getRecord().get("FROM_YYYYMM");   //"201601";
            toYYYYMM = dvobj.getRecord().get("TO_YYYYMM");  //"201603";
            ArrayList ymlist =  getYYYYMM(fromYYYYMM,  toYYYYMM);

            _fin = new FileInputStream(_fp);
            XSSFWorkbook _workbook = null;
            _workbook = new XSSFWorkbook(_fin);

            //----------------------------------------------- [ Style ]
            XSSFCellStyle yellowstyle = _workbook.createCellStyle();
            yellowstyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
            yellowstyle.setFillPattern(yellowstyle.SOLID_FOREGROUND);
            yellowstyle.setBorderBottom(yellowstyle.BORDER_THIN);
            yellowstyle.setBorderTop(yellowstyle.BORDER_THIN);
            yellowstyle.setBorderRight(yellowstyle.BORDER_THIN);
            yellowstyle.setBorderLeft(yellowstyle.BORDER_THIN);

            XSSFCellStyle boardstyle = _workbook.createCellStyle();
            boardstyle.setBorderBottom(boardstyle.BORDER_THIN);
            boardstyle.setBorderTop(boardstyle.BORDER_THIN);
            boardstyle.setBorderRight(boardstyle.BORDER_THIN);
            boardstyle.setBorderLeft(boardstyle.BORDER_THIN);
            //-----------------------------------------------------------

            XSSFSheet _sheet = _workbook.getSheetAt(_sheetno);
            XSSFCell _cell =null;
           // System.out.println("step 4");
            //-------------------------SETTING YYYYMM : DATE HEAD COLUMN
           // System.out.println(":ymlist.size():" + ymlist.size());
            XSSFRow _row = _sheet.getRow(1);   //head
            int idx=0;
            for(idx=0;idx<ymlist.size();idx++)
            {
                _cell =_row.createCell(ordername.size() + idx);
                _cell.setCellStyle(yellowstyle);
                _cell.setCellValue(ymlist.get(idx)+"");
            }
            // System.out.println("step 5");
            //---------------EOL 처리--------------------------
            _row = _sheet.getRow(0);
            _cell =_row.createCell(ordername.size() + idx);
            _cell.setCellValue("EOC");
            //---------------------------------------------------------------------------
           //  System.out.println("step 6");
            int rowidx =2;  //datarow
            invobj.first();
            while(invobj.next())
            {
                _row = _sheet.createRow(rowidx);
                for(int i=0;i<ordername.size();i++)
                {
                    _cell =_row.createCell(i);
                    if(i==0)
                    {
                        _cell.setCellStyle(yellowstyle);
                    }
                    else
                    {
                        _cell.setCellStyle(boardstyle);
                    }
                    String cellValue = invobj.getRecord().get(ordername.get(i).toString().toUpperCase());
                    if(cellValue.equals("#")){
                    	_cell.setCellValue("");
                    }else{
                    	_cell.setCellValue(cellValue);
                    }
                    
                }
                for(int i=0;i<ymlist.size();i++)
                {
                     _cell =_row.createCell(i + ordername.size());
                //    double d = Double.parseDouble(invobj.getRecord().get(ymlist.get(i)).toString());
                    /*  String vvvaaa = invobj.getRecord().get(ymlist.get(i)).toString();
                      if(!vvvaaa.equals("")){
                    	  _cell.setCellType(_cell.CELL_TYPE_NUMERIC);
                    	  _cell.setCellValue(Double.parseDouble(vvvaaa));
                      }else{
                    	  _cell.setCellValue(invobj.getRecord().get(ymlist.get(i)).toString());
                      }*/
                     
                     _cell.setCellValue(invobj.getRecord().get(ymlist.get(i)).toString());
                     
                     _cell.setCellStyle(boardstyle);
                }
                rowidx++;
            }
            //---------------EOL 처리--------------------------
            _row = _sheet.createRow(rowidx);
            _cell =_row.createCell(0);
            _cell.setCellValue("EOL");
            //------------------------------------------------
           // System.out.println("step 7");
            _workbook.setForceFormulaRecalculation(true);
            
            
            FileOutputStream fileOut = new FileOutputStream(_fp);
            _workbook.write(fileOut);
          //  System.out.println("step 8");
       }
       catch(Exception ex)
       {
           ex.printStackTrace();
       }
       finally
       {
         try
         {
             if (_fin != null)    _fin.close();
         }
         catch(Exception f) {}
       }

       //---------------------------------------

       //System.out.println("------------------------- EXCEL DOWN---- FILE:" + downtempfold +":" +  unifilename +":" +"HPMS_Plan.xlsx" );
       
       FileUtil fu = new FileUtil();
       fu.getSYSDATE();
       String FileNM = "HPMS_Plan"+"_"+indobj.getRetObject("G").getRecord().get("USER_ID")+"_"+fu.getSYSDATE()+".xlsx";
       
       VOBJ _rvobj = new VOBJ();
       HashMap _rec = new HashMap();
       _rec.put("FILE_PATH",downtempfold);
       _rec.put("FILE_UNNAME",unifilename);
       _rec.put("FILE_NAME",FileNM);
       _rvobj.addRecord(_rec);
       _rvobj.setName("DN01");
       indobj.setRetObject(_rvobj);

       _rvobj.Println("DOWNLOAD RETURN");
       indobj.dispRetObjectKeyNames();
       return indobj;
    }

    private  ArrayList getYYYYMM(String fromYYYYMM, String toYYYYMM)
    {
        ArrayList _rlist = new ArrayList();
        _rlist.add(fromYYYYMM);

        if(fromYYYYMM.trim().equals(toYYYYMM.trim())) return _rlist;

        SimpleDateFormat sdfmt = null;
        Date ymddate =null;
        String plusyyyymm="";
        int startyyyy = Integer.parseInt(fromYYYYMM.substring(0,4));
        int startmm = Integer.parseInt(fromYYYYMM.substring(4));
         Calendar _calendar = Calendar.getInstance();
         _calendar.set(startyyyy, startmm, 0);
         ymddate =  _calendar.getTime();
         sdfmt = new SimpleDateFormat("yyyyMM");
         plusyyyymm = sdfmt.format(ymddate);

         while(true)
         {
             _calendar.add(_calendar.MONTH,1);
              ymddate =  _calendar.getTime();
              sdfmt = new SimpleDateFormat("yyyyMM");
              plusyyyymm = sdfmt.format(ymddate);
              if(Integer.parseInt(plusyyyymm) >= Integer.parseInt(toYYYYMM)) break;
              _rlist.add(plusyyyymm);
         }
         _rlist.add(toYYYYMM);

         for(int i=0;i<_rlist.size();i++) System.out.print(_rlist.get(i)+":");
         System.out.println("\nfrom:" + fromYYYYMM +":to:" + toYYYYMM);


         return _rlist;
    }

    private  boolean layoutcopy(String layoutfile,  String unifilename )
    {
        boolean rtn = true;
        final int buffersize = 1024;
        FileInputStream _infp = null;
        FileOutputStream _outfp = null;
        try
        {
            _infp = new FileInputStream(layoutfilefold +"/"+ layoutfile);
            _outfp = new FileOutputStream(downtempfold +"/"+ unifilename);
            byte buffer[] = new byte[buffersize];
            int _3905 = 0;
            while ( (_3905 = _infp.read(buffer)) > 0)
            {
                _outfp.write(buffer, 0, _3905);
            }
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
            rtn = false;
        }
        finally
        {
            try
            {
                if (_infp != null) _infp.close();
                if (_outfp != null) _outfp.close();
            }
            catch(IOException ex)
            {
                rtn = false;
            }
        }
        return rtn;
    }

    private  String getUniFilename()
    {
        String currentymd =GetCurrentDate();
        String extname =".xlsx" ;
        String filename = currentymd + "_dwn" ;
        _makeDir(downtempfold);
        int seq =1;
        File _fp = null;
        while(true)
        {
            _fp = new File(downtempfold +"/" + filename + extname);
            if(_fp.exists())
            {
                filename += seq;
                seq++;
                continue;
            }
            break;
        }
        return filename + extname;
    }
    public  String GetCurrentDate()
    {
        Date _dt = new Date();
        SimpleDateFormat _yyyyMMddE = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String yyyymdd;
        yyyymdd = _yyyyMMddE.format(_dt);
        return yyyymdd;
    }
    public  boolean _makeDir(String path)
    {
        boolean rtn = false;
        if(!_dirExist(path)){
            File fp = new File(path);
            fp.mkdirs();
        }
        return rtn;
    }
    private  boolean _dirExist(String path)
    {
        boolean rtn = false;
        File fp = new File(path);
        if(fp.isDirectory()) rtn = true;
        return rtn;
    }

    //---------------------------------------------------------------------------------------- ----------------------
    //-----------------------------------[        download end       ]----------------------------------
    //---------------------------------------------------------------------------------------- ----------------------


    //---------------------------------------------------------------------- upload method processing start
    public   DOBJ uploadPlanExcel( DOBJ _indobj )
    {
    	 System.out.println(" ================================ " + "START " + "==============================================");
        ordername= new ArrayList();
        setUploadOrderName();

        int _sheetno =0;
        FileInputStream _fin = null;
        HashMap rec = null;
        VOBJ _rvobj = new VOBJ();
        ArrayList _hdata = new ArrayList();

        XSSFRow _headRow_1Line = null;
        XSSFRow _headRow_2Line = null;
        ArrayList _headYMDList = null;

        _indobj.dispRetObjectKeyNames();

        VOBJ _vobj = _indobj.getRetObject("S1");
        String FilePath =_vobj.getRecord().get("FILE_PATH");
        String FileName =_vobj.getRecord().get("FILE_NAME");
        String FileUnName =_vobj.getRecord().get("FILE_UNNAME");
        String FileSize =_vobj.getRecord().get("FILE_SIZE");
        String fullname = FilePath + FileName;
        String value ="";
        
        /*System.out.println( "  FilePath " + FilePath);
        System.out.println( "  FileName " + FileName);
        System.out.println( "  FileUnName " + FileUnName);
        System.out.println( "  fullname " + fullname);*/
        try
        {
        	
            _fin = new FileInputStream(fullname);
            XSSFWorkbook _workbook_hssf = null;
            _workbook_hssf = new XSSFWorkbook(_fin);
            XSSFSheet _sheet = _workbook_hssf.getSheetAt(_sheetno);
            int rowidx =0;
            int continuecnt = 0;
            XSSFRow _row = null;
            while(true)
            {
            	
                //--------------------------------- head 정보 추출 ------------------------------------------------
                if(rowidx == 0)
                {
                    _headRow_1Line =_sheet.getRow(rowidx);
                    rowidx++;
                    continue;
                }
                if(rowidx == 1)
                {
                    _headRow_2Line =_sheet.getRow(rowidx);
                    _headYMDList = getHeadColumnYMD(_headRow_1Line, _headRow_2Line);
                    // setHeadVarName(_headRow_2Line);      //Excel file 내부의 column정보 사용시
                    rowidx++;
                    continue;
                }
                //------------------------------------------------------------------ head processing end --------

                _row = _sheet.getRow(rowidx);
               
                if(_row == null)
                {
                	
                    if(continuecnt > 3) // 5번째 다음부터 데이타로 안받는다 .
                    {
                        break;
                    }
                    rowidx++;
                    continuecnt++;
                    continue;
                }
                continuecnt = 0;

                if(isEol(_row))
                {
                	
                    break;
                }

                _hdata = new ArrayList();
                _hdata = getDataStepOne(_row);
                //-------------------------------------  Create Record  -----------------------------------------
                for(int ymdindex =0;ymdindex<_headYMDList.size();ymdindex++)
                {
                    if(_headYMDList.get(ymdindex).toString().equals("SKIP"))
                    {
                        continue;
                    }

                    if(_headYMDList.get(ymdindex).toString().equals("ERR"))
                    {
                        //처리 어떻게 할것인가 오류 처리확인 필요.
                        continue;
                    }

                    if(isValidateYMD(_headYMDList.get(ymdindex).toString())==false)
                    {
                        //날짜 오입력에 대한 처리 필요한가?.
                        _rvobj.setRetcode(1010);
                        continue;
                    }

                    String flag ="";
                    if(_hdata.get(0) == null) flag ="";
                    else  flag =  _hdata.get(0).toString().toUpperCase();

                    if(flag.equals("SKIP")) continue;
                    if(flag.trim().equals("")) continue;   //FLAG : SPACE SKIP
                    
                    if( !flag.trim().equals("I") && !flag.trim().equals("U") && !flag.trim().equals("D") )
                    { 
                      continue; 
                    }
                      
                    rec = new HashMap(); 
                    int colindex=0;
                    for(colindex=0;colindex<ordername.size();colindex++)
                    {
                        if(colindex == 0)
                        {
                            rec.put("PROCESSING_FLAG", flag);
                            rec.put("IUDFLAG", flag);
                        }
                        else
                        {
                            if(isSetDefault(ordername.get(colindex).toString().toUpperCase(), _hdata.get(colindex)))
                            {
                                rec.put(ordername.get(colindex).toString().toUpperCase(), "#");
                            }
                            else
                            {
                            	
                            
                                rec.put(ordername.get(colindex).toString().toUpperCase(), _hdata.get(colindex));
                               
                            }
                        }
                    }

                    rec.put("YYYYMM",_headYMDList.get(ymdindex));
                    value = getCellValue(_row.getCell(colindex+ymdindex));
                    if(value.equals("null")){
                    	continue; 
                    }
                    
                    if(isNumber(value)== false)
                    {
                        _rvobj.setRetcode(1011);
                    }
                    rec.put("VAL", value);

                    int vchk = 0;
                    if( (vchk=isValidateRecord(rec)) != 0)
                    {
                       _rvobj.setRetcode(vchk);
                    }
                    _rvobj.addRecord(rec);
                }
                //-----------------------------------------------------------------------------------------------
                rowidx++;
            }
            _rvobj.Println("EXCEL UPLOAD DATA");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                if (_fin != null)    _fin.close();
            }
            catch(Exception f) {}
        }

        _rvobj.setName("UP01");
        
        if(_rvobj.getRecordCnt() == 0){
        	
        	_rvobj.setRetcode(1020);
        
        }
        	
        _indobj.setRetObject(_rvobj);

        if(_rvobj.getRetcode() != 0)
        {
            _indobj.setRtncode(_rvobj.getRetcode());
            _indobj.setRetmsg(_rvobj.getRetcode() + "");
        }

        return _indobj;
    }
    private boolean isSetDefault(String colnm, Object value)
    {
        if(colnm.toUpperCase().equals("INVEST_TYPE_CD")
           || colnm.toUpperCase().equals("APPLICATION")
           || colnm.toUpperCase().equals("ITEM_NAME")
           || colnm.toUpperCase().equals("CUSTOMER_CD") )
        {
            if(value == null || value.toString().trim().equals(""))
            {
                return true;
            }
        }
        return false;
    }
 
    private int isValidateRecord(HashMap rec)
    {
        String[] msg = null;
        //------------------------------------------------------------------------------ not null column check
        ArrayList nlist = getNotNull();
        for(int i=0;i<nlist.size();i++)
        {
            if( rec.get(nlist.get(i)) == null || rec.get(nlist.get(i)).toString().trim().equals(""))
            {
                return 1013;
            }
        }
        //---------------------------------------------------------------------------------------------------

        //------------------------------------------------------------------------------ Length Check
        TblChkInfo[] tinfo = getLengthNull();
        for(int i=0;i<tinfo.length;i++)
        {
            if(tinfo[i] == null) continue;
            if(rec.get(tinfo[i].getName()) == null ) continue;
            try {
				if(rec.get(tinfo[i].getName()).toString().getBytes("UTF-8").length > tinfo[i].getLength()) return 1012;
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        //---------------------------------------------------------------------------------------------------
        return 0;
    }

    private boolean isNumber(String value)
    {
        try
        {
        	
            if(value == null) return true;
            if(value.trim().equals(""))
            {
                return true;
            }
            Double.parseDouble(value);
        }catch(Exception ex)
        {
            return false;
        }

        return true;
    }


    private ArrayList getNotNull()
    {
        ArrayList nlist = new ArrayList();
        nlist.add("DATA_TYPE");
        nlist.add("PID");
        nlist.add("USE_COMPANY_CD");
        nlist.add("USE_ORG_CD");
        nlist.add("REQ_COMPANY_CD");
        nlist.add("REQ_ORG_CD");
        nlist.add("HPMS_ID");
        return nlist;
   }
   private TblChkInfo[] getLengthNull()
   {
       TblChkInfo[] tinfo = new TblChkInfo[13];
       tinfo[0] = new TblChkInfo("PROCESSING_FLAG", 4);
       tinfo[1] = new TblChkInfo("DATA_TYPE", 10);
       tinfo[2] = new TblChkInfo("PID", 6);
       tinfo[3] = new TblChkInfo("USE_COMPANY_CD", 4);
       tinfo[4] = new TblChkInfo("USE_ORG_CD", 7);
       tinfo[5] = new TblChkInfo("REQ_COMPANY_CD", 4);
       tinfo[6] = new TblChkInfo("REQ_ORG_CD", 7);
       tinfo[7] = new TblChkInfo("HPMS_ID", 6);
       tinfo[8] = new TblChkInfo("INVEST_TYPE_CD", 4);
       tinfo[9] = new TblChkInfo("APPLICATION", 100);
       tinfo[10] = new TblChkInfo("ITEM_NAME", 30);
       tinfo[11] = new TblChkInfo("CUSTOMER_CD", 8);
       tinfo[12] = new TblChkInfo("UNIT", 3);
       return tinfo;
   }
   private void setUploadOrderName()
   {
       ordername.add("PROCESSING_FLAG");
       ordername.add("DATA_TYPE");
       ordername.add("PID");
       ordername.add("PID_THEME");
       ordername.add("USE_COMPANY_CD");
       ordername.add("USE_ORG_CD");
       ordername.add("REQ_COMPANY_CD");
       ordername.add("REQ_ORG_CD");
       ordername.add("HPMS_ID");
       ordername.add("HPMS_ID_NM_JP");
       ordername.add("INVEST_TYPE_CD");
       ordername.add("INVEST_TYPE_NAME");
       ordername.add("APPLICATION");
       ordername.add("ITEM_NAME");
       ordername.add("CUSTOMER_CD");
       ordername.add("UNIT");
       ordername.add("UPDATE_TIME");
       ordername.add("UPDATE_NAME");
   }
   private  boolean isValidateYMD(String ymd)
   {
       if(ymd == null ) return false;
       if(ymd.length() != 6) return false;
       int yyyy = Integer.parseInt(ymd.substring(0,4));
       int mm = Integer.parseInt(ymd.substring(4));

       if(mm < 1 || mm > 12) return false;
       if(yyyy < 1970 || yyyy > 2500) return false;
       return true;
   }
   private  boolean isEol(XSSFRow _row)
   {
       XSSFCell _cell = _row.getCell(0);
       if (_cell!=null && _cell.getCellType() == Cell.CELL_TYPE_STRING)
       {
           String _value = _cell.getStringCellValue();
           if(_value!=null && _value.trim().toUpperCase().equals("EOL"))
           {
               return true;
           }
       }
       return false;
   }
   private  ArrayList getHeadColumnYMD(XSSFRow row_1Line, XSSFRow row_2Line)
   {
       ArrayList alist = new ArrayList();
       int eocindex = ordername.size();
       XSSFCell _cell_1Line = null;
       XSSFCell _cell_2Line = null;
       String _val_1Line="";
       String _val_2Line="";
       int continuecnt = 0;

       while(true)
       {
           _cell_1Line = row_1Line.getCell(eocindex);
           _cell_2Line = row_2Line.getCell(eocindex);
           //System.out.println("start:" + eocindex +":" + getCellValue(_cell_2Line) );
           if(continuecnt > 2)
           {
               break;
           }

           if(_cell_2Line == null && _cell_1Line == null)
           {
               continuecnt++;
               eocindex++;
               continue;
           }
           if (_cell_1Line!=null )
           {
               _val_1Line = getCellValue(_cell_1Line);
           }
           if( _val_1Line != null && _val_1Line.trim().toUpperCase().equals("EOC"))
           {
               break;
           }
           if( _val_1Line != null && _val_1Line.trim().toUpperCase().equals("SKIP"))
           {
               alist.add("SKIP");
               eocindex++;
               continuecnt=0;
               continue;
           }

           if (_cell_2Line!=null)
           {
               _val_2Line = getCellValue(_cell_2Line);
           }

           if( (_val_2Line == null && _val_2Line == null)  || (_val_2Line.trim().equals("")&& _val_2Line.trim().equals("") ))
           {
               alist.add("ERR");
               continuecnt++;
               eocindex++;
               continue;
           }

           if(!_val_1Line.trim().toUpperCase().equals("SKIP") && (_val_2Line == null || _val_2Line.trim().equals("")))
           {
               alist.add("ERR");
               eocindex++;
               continuecnt=0;
               continue;
           }

           alist.add(_val_2Line);
           continuecnt=0;
           eocindex++;
       }

//       for(int i=0;i<alist.size();i++)
//       {
//           System.out.print(":" + alist.get(i));
//       }
       return alist;
   }

   private  ArrayList getDataStepOne(XSSFRow row)
   {
       ArrayList data = new ArrayList();
        XSSFCell _cell = null;
        String _value;
        for(int i=0;i<ordername.size();i++)
        {
            _cell = row.getCell(i);
            if (_cell != null)
            {
                if (_cell.getCellType() == Cell.CELL_TYPE_FORMULA)
                    _value = getString(_cell.getNumericCellValue());
                else if (_cell.getCellType() == Cell.CELL_TYPE_STRING)
                    _value = _cell.getStringCellValue();
                else if (_cell.getCellType() == Cell.CELL_TYPE_ERROR)
                    _value = Byte.toString(_cell.getErrorCellValue());
                else if (_cell.getCellType() == Cell.CELL_TYPE_BOOLEAN)
                    _value = Boolean.toString(_cell.getBooleanCellValue());
                else if (_cell.getCellType() == Cell.CELL_TYPE_BLANK)
                    _value = "";
                else if (_cell.getCellType() == Cell.CELL_TYPE_NUMERIC)
                {
                    if(DateUtil.isCellDateFormatted(_cell))
                    {
                        Date _dt =_cell.getDateCellValue();
                        SimpleDateFormat yyyyMMddE = new SimpleDateFormat("yyyyMMdd");
                        _value = yyyyMMddE.format(_dt);
                    }
                    else
                    {
                        try
                        {
                            _value = getString(_cell.getNumericCellValue());
                        }
                        catch(java.lang.IllegalStateException ee)
                        {
                            _value ="";
                        }
                    }
                }
                else
                {
                    _value = _cell.getRichStringCellValue().getString();
                }
            }
            else
            {
                _value="";
            }
            data.add(_value);
        }
        return data;
    }
    private  boolean isValidateRowColumnName(XSSFRow _row)
    {
        String[] EssentialColname = {"DATA_TYPE", "PID", "ITEM_CODE"};
        int[] colidx = new int[EssentialColname.length];
        int idx =0;
        for(int i=0;i<EssentialColname.length;i++)
        {
            idx = getColIndex(EssentialColname[i]);
            colidx[i] = idx;
        }
        return isValidateRowIndex( _row, colidx);
    }
    private  boolean isValidateRowIndex(XSSFRow _row, int[] EssentialColIndex)
    {
        String value="";
        for(int i=0;i<EssentialColIndex.length;i++)
        {
            value = getCellValue(_row.getCell(EssentialColIndex[i]));
            if(value == null || value.trim().equals(""))
            {
                //System.out.println("=====================[false]:" +EssentialColIndex[i] + ":" + value);
                return false;
            }
        }
        return true;
   }
   private  int getColIndex(String colname)
   {
       for(int i=0;i<ordername.size();i++)
       {
           if(ordername.get(i).toString().toUpperCase().equals(colname))
           {
               //System.out.println("=====================[colname]:" + i + ":" + colname);
               return i;
           }
       }
       return -1;
   }
   //--------------------------------------------------------------------------------------------- 계획 Upload 처리  end


   //-------------- Common Method ----------------------------------------------------------------
   private XSSFCellStyle getCellStyle(XSSFCellStyle style)
   {
       style.setFillBackgroundColor(IndexedColors.YELLOW.getIndex());

       style.setBorderBottom(style.BORDER_THIN);
       style.setBorderTop(style.BORDER_THIN);
       style.setBorderRight(style.BORDER_THIN);
       style.setBorderLeft(style.BORDER_THIN);

       return style;
   }

   private  String getCellValue(XSSFCell _cell)
   {
       String _value ="";
       if (_cell != null)
       {
           if (_cell.getCellType() == Cell.CELL_TYPE_FORMULA)
               _value = getString(_cell.getNumericCellValue());
           else if (_cell.getCellType() == Cell.CELL_TYPE_STRING)
               _value = _cell.getStringCellValue();
           else if (_cell.getCellType() == Cell.CELL_TYPE_ERROR)
               _value = Byte.toString(_cell.getErrorCellValue());
           else if (_cell.getCellType() == Cell.CELL_TYPE_BOOLEAN)
               _value = Boolean.toString(_cell.getBooleanCellValue());
           else if (_cell.getCellType() == Cell.CELL_TYPE_BLANK)
               _value = "";
           else if (_cell.getCellType() == Cell.CELL_TYPE_NUMERIC)
           {
               if(DateUtil.isCellDateFormatted(_cell))
               {
                   Date _dt =_cell.getDateCellValue();
                   SimpleDateFormat yyyyMMddE = new SimpleDateFormat("yyyyMMdd");
                   _value = yyyyMMddE.format(_dt);
               }
               else
               {
                   try
                   {
                       _value = getString(_cell.getNumericCellValue());
                   }
                   catch(java.lang.IllegalStateException ee)
                   {
                       _value ="null";
                   }
               }
           }
           else
           {
               _value = _cell.getRichStringCellValue().getString();
           }
       }
       else
       {
           _value="";
       }
       return _value;
   }
   private  String getString(double _3239ericValue)
   {
	   
       double _ceiledValue = Math.ceil(_3239ericValue);
       String CellSize =  Double.toString(_ceiledValue);
       if (_ceiledValue - _3239ericValue == 0.0 && CellSize.length() < 11 )
           return Integer.toString(((int) _3239ericValue));
       else
           return Double.toString(_3239ericValue);
   }
  //----------------------------------------------------------------------------------------------------------------

}
