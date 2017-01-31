package hpms.UserObject.Excel;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import java.text.SimpleDateFormat;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Date;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.IndexedColors;
import WIZ.FR.COM.DOBJ;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import java.util.ArrayList;
import WIZ.FR.COM.VOBJ;
import java.util.Calendar;
import java.io.File;
import org.apache.poi.xssf.usermodel.XSSFCell;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;

public class PLSheet_V1
{
    private final String rootPath ="c:/tomcat7/webapps/hpms/";
    private final String planlayoutfile ="PLSheet_std_layout.xlsx";
    private  ArrayList ordername= new ArrayList();
    private  String downtempfold = this.rootPath + "ExcelDownLoad";
    private  String layoutfilefold = this.rootPath + "ExcelLayout";
    private String[] dsnames = {"SEL01","SEL02","SEL03","SEL04","SEL05","SEL06","SEL07","SEL08","SEL09","SEL10"};

    public PLSheet_V1()
    {
    }

    //---------------------------------------------------------------------------------------------------------------
    //----------------------------   [ download method processing start  ] ------------------------------------------
    //---------------------------------------------------------------------------------------------------------------
    private void setDownloadOrderName()
    {
       ordername.add("MON04");
       ordername.add("MON05");
       ordername.add("MON06");
       ordername.add("MON07");
       ordername.add("MON08");
       ordername.add("MON09");
       ordername.add("MON10");
       ordername.add("MON11");
       ordername.add("MON12");
       ordername.add("MON01");
       ordername.add("MON02");
       ordername.add("MON03");
   }

   private SheetInfo[] getSheetInfo()
   {
       SheetInfo[] sinfo = new SheetInfo[1];
       sinfo[0] = new SheetInfo("Original Plan", 0, "SEL", "OrgPlan", 70); //sinfo[0] = new SheetInfo("Original Plan", 7, "SEL", "OrgPlan", 70);
       return sinfo;
   }


   private void resetFormulaSheet(XSSFWorkbook _workbook)
   {
        XSSFCell _cell =null;
        XSSFRow _row = null;
        XSSFSheet _sheet = null;
        FormulaEvaluator evaluator = _workbook.getCreationHelper().createFormulaEvaluator();
        for(int sidx = 0;sidx < _workbook.getNumberOfSheets();sidx++)
        {
            _sheet = _workbook.getSheetAt(sidx);
            for(int rowidx =0;rowidx<239;rowidx++)
            {
                _row = _sheet.getRow(rowidx);
                if(_row == null) continue;
                for(int cidx=0;cidx < 202;cidx++)
                {
                    _cell = _row.getCell(cidx);
                    if (_cell == null)  continue;
                    if (_cell.getCellType() == Cell.CELL_TYPE_FORMULA )
                    {
                        try
                        {
                            evaluator.evaluateFormulaCell(_cell);
                        }
                        catch(Exception ex)
                        {
                            System.out.println( _sheet.getSheetName() + ":(" + rowidx +","+ cidx +")");
                            ex.printStackTrace();
                        }
                    }
                }
            }
        }
    }


   public DOBJ buildSheet(DOBJ indobj)
   {
       String unifilename = "";
       File _fp = null;
       FileInputStream _fin = null;
       try
       {
           VOBJ dvobj = indobj.getRetObject("S");
           ordername= new ArrayList();
           setDownloadOrderName();

           unifilename = getUniFilename();
           if (layoutcopy(planlayoutfile, unifilename) == false)
           {
               return null;
           }

           _fp = new File(downtempfold + "/" + unifilename);
           _fin = new FileInputStream(_fp);
           XSSFWorkbook _workbook = null;
           _workbook = new XSSFWorkbook(_fin);
           SheetInfo[] sheetinfo = getSheetInfo();

           for(int i=0;i<sheetinfo.length;i++)
           {
               addSheetPlanExcel(indobj, sheetinfo[i],  _workbook);
           }

           try
           {
               //_workbook.getCreationHelper().createFormulaEvaluator().evaluateAll();
               _workbook.setForceFormulaRecalculation(true);
               //resetFormulaSheet(_workbook);
           }
           catch(Exception exe)
           {
               exe.printStackTrace();
           }

           FileOutputStream fileOut = new FileOutputStream(_fp);
           _workbook.write(fileOut);

       }catch(Exception ex)
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

       VOBJ _rvobj = new VOBJ();
       HashMap _rec = new HashMap();
       _rec.put("FILE_PATH",downtempfold);
       _rec.put("FILE_UNNAME",unifilename);
       _rec.put("FILE_NAME","PLSheet.xlsx");
       _rvobj.addRecord(_rec);
       _rvobj.setName("DN01");
       indobj.setRetObject(_rvobj);
       _rvobj.Println("DOWNLOAD RETURN");
       indobj.dispRetObjectKeyNames();

       return indobj;
   }

   private  void addSheetPlanExcel(DOBJ indobj, SheetInfo sinfo, XSSFWorkbook _workbook)
   {
       try
       {
           XSSFSheet _sheet = _workbook.getSheetAt(sinfo.getSheetNo());
           System.out.println("=========== Sheet Name:" + _sheet.getSheetName());
           XSSFCell _cell =null;
           XSSFRow _row = null;

           int StartColIndex = sinfo.getStartColumnIndex();
           for(int dsidx=0;dsidx<dsnames.length;dsidx++)
           {
               VOBJ invobj = indobj.getRetObject(dsnames[dsidx]);
               int idx =0 ;  //datarow
               int[] rownumber = sinfo.getWriteRowNo();
               invobj.first();
               while(invobj.next())
               {
                   _row = _sheet.getRow(rownumber[idx]-1);
                   if(_row == null) continue;
                   for(int i=0;i<ordername.size();i++)
                   {
                       _cell =_row.getCell(i+StartColIndex);
                       if(_cell == null) _cell =_row.createCell(i+sinfo.getStartColumnIndex());
                       _cell.setCellValue(invobj.getRecord().getDouble(ordername.get(i).toString().toUpperCase()));
                       //System.out.print("("+(rownumber[idx]-1) +"," + (i+StartColIndex) + "," + invobj.getRecord().getDouble(ordername.get(i).toString().toUpperCase()));
                   }
                   //System.out.println("");
                   idx++;
               }
               StartColIndex += 12;
           }

           _row = _sheet.getRow(175);
           for(int i=70;i<ordername.size();i++)
           {
               _cell =_row.getCell(i);
               _cell.setCellFormula(_cell.getCellFormula());
           }

           //resetFormulaSheet(_sheet);

       }
       catch(Exception ex)
       {
           ex.printStackTrace();
       }
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
        String filename = currentymd + "_pl" ;
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
    private  String GetCurrentDate()
    {
        Date _dt = new Date();
        SimpleDateFormat _yyyyMMddE = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String yyyymdd;
        yyyymdd = _yyyyMMddE.format(_dt);
        return yyyymdd;
    }
    private  boolean _makeDir(String path)
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

    //formula reset method
    private void resetFormulaSheet(XSSFSheet _sheet)
    {
        XSSFCell _cell =null;
        XSSFRow _row = null;
        for(int rowidx =0;rowidx<239;rowidx++)    //239
        {
            _row = _sheet.getRow(rowidx);
            if(_row == null) continue;
            for(int cidx=0;cidx < 202;cidx++)          //202
            {
                _cell = _row.getCell(cidx);
                if (_cell == null)  continue;
                if (_cell.getCellType() == Cell.CELL_TYPE_FORMULA )
                {
                    _cell.setCellFormula(_cell.getCellFormula());
                }
            }
        }
    }





}
