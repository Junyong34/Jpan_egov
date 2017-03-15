package hpms.UserObject.Excel;
import org.apache.poi.poifs.filesystem.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;

import java.util.*;
import java.io.*;
import java.text.*;

import egov.wizware.com.*;
import egov.wizware.ria.EgovProperties;
public class WorkOutputUpload
{
    private final String rootPath = EgovProperties.getProperty("Globals.rootPath"); //"c:/tomcat7/webapps/hpms/";
    private final String planlayoutfile ="forcastdown_std_layout.xlsx";    //forecast download layout file name

    private  ArrayList ordername= new ArrayList();
    private  String downtempfold = this.rootPath + "ExcelDownLoad";
    private  String layoutfilefold = this.rootPath + "ExcelLayout";

    public WorkOutputUpload()
    {
    }


    //---------------------------------------------------------------------- upload method processing start
    public   DOBJ uploadWorkOutputExcel( DOBJ _indobj )
    {
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
                    if(continuecnt > 3)
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
                        _rvobj.setRetcode(1024);
                        continue;
                    }

                    String flag ="";
                    if(_hdata.get(0) == null) flag ="";
                    else  flag =  _hdata.get(0).toString().toUpperCase();

                    if(flag.equals("SKIP")) continue;
                    //if(flag.trim().equals("")) continue;

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
                    
                  /*  if(value.equals("") || value==null){
                    	continue; 
                    }*/ 

                    
	                  if(value == null || value.equals("")){
	                	 
	                	  //value = null; 
	                	  rec.put("VAL", 0);
	                   }else{
	                	 
	                	   if(isNumber(value)== false)
	                       {
	                		  
	                		   rec.put("VAL", -1); 
	                           _rvobj.setRetcode(1011);
	                       }else{
	                    	   rec.put("VAL", Double.parseDouble(value)); 
	                       }
	                	   
	                   }

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
        	
        	//System.out.println(val.toString().getBytes().length + " %% " +  val.length() + " ## " + rec.get(tinfo[i].getName()).toString().getBytes("UTF-8").length);
            if(tinfo[i] == null) continue;
            if( rec.get(tinfo[i].getName()) == null ) continue;
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
        nlist.add("PID");
        nlist.add("USE_COMPANY_CD");
        nlist.add("USE_ORG_CD");
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
       return _value;
   }
   private  String getString(double _3239ericValue)
   {
	   
       double _ceiledValue = Math.ceil(_3239ericValue);
       String CellSize =  Double.toString(_ceiledValue);
       String pattern = " ####.#######################";
       DecimalFormat dformat = new DecimalFormat(pattern);
       //System.out.println(  dformat.format(_ceiledValue));
       if (_ceiledValue - _3239ericValue == 0.0 &&  dformat.format(_ceiledValue).length() < 11 )
           return Integer.toString(((int) _3239ericValue));
       else
           return Double.toString(_3239ericValue);
   }
  //----------------------------------------------------------------------------------------------------------------

}
