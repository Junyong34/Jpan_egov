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

public class ManageSheet_V1
{
    private final String rootPath ="c:/tomcat7/webapps/hpms/";
    private  ArrayList ordername= new ArrayList();
    private  String downtempfold = this.rootPath + "ExcelDownLoad";
    private  String layoutfilefold = this.rootPath + "ExcelLayout";

    public ManageSheet_V1()
    {
    }

    //---------------------------------------------------------------------------------------------------------------
    //----------------------------   [ download method processing start  ] ------------------------------------------
    //---------------------------------------------------------------------------------------------------------------
    private void setDownloadOrderName()
    {
       ordername.add("MP4");
       ordername.add("LMFCST4");
       ordername.add("FCST4");

       ordername.add("MP5");
       ordername.add("LMFCST5");
       ordername.add("FCST5");

       ordername.add("MP6");
       ordername.add("LMFCST6");
       ordername.add("FCST6");

       ordername.add("MP7");
       ordername.add("LMFCST7");
       ordername.add("FCST7");

       ordername.add("MP8");
       ordername.add("LMFCST8");
       ordername.add("FCST8");

       ordername.add("MP9");
       ordername.add("LMFCST9");
       ordername.add("FCST9");

       ordername.add("MP10");
       ordername.add("LMFCST10");
       ordername.add("FCST10");

       ordername.add("MP11");
       ordername.add("LMFCST11");
       ordername.add("FCST11");

       ordername.add("MP12");
       ordername.add("LMFCST12");
       ordername.add("FCST12");

       ordername.add("MP1");
       ordername.add("LMFCST1");
       ordername.add("FCST1");

       ordername.add("MP2");
       ordername.add("LMFCST2");
       ordername.add("FCST2");

       ordername.add("MP3");
       ordername.add("LMFCST3");
       ordername.add("FCST3");
   }

   private SheetInfo[] getSheetInfo()
   {
       SheetInfo[] sinfo = new SheetInfo[2];
       sinfo[0] = new SheetInfo("IoT", 11, "SEL2", "IoT", 32);
       sinfo[1] = new SheetInfo("NWS", 12, "SEL2", "IoT", 32);
       return sinfo;
   }

   private String getCopyLayoutFilename(String yyyymm)
   {
       String filename ="";
       int mm = Integer.parseInt(yyyymm.substring(4));
       filename ="Manage_synthesis_"+mm+"Mon.xlsx";
       return filename;
   }

   public DOBJ buildSheet(DOBJ indobj)
   {
       String unifilename = "";
       File _fp = null;
       FileInputStream _fin = null;
       try
       {
           VOBJ dvobj = indobj.getRetObject("S");
           String filename = getCopyLayoutFilename(dvobj.getRecord().get("YYYYMM"));
           ordername= new ArrayList();
           setDownloadOrderName();

           unifilename = getUniFilename();
           if (layoutcopy(filename, unifilename) == false)
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

           _workbook.getCreationHelper().createFormulaEvaluator().evaluateAll();

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
       _rec.put("FILE_NAME","Manage_Synthesis.xlsx");
       _rvobj.addRecord(_rec);
       _rvobj.setName("DN01");
       indobj.setRetObject(_rvobj);
       _rvobj.Println("DOWNLOAD RETURN");
       indobj.dispRetObjectKeyNames();

       return indobj;
   }



   public  void addSheetPlanExcel(DOBJ indobj, SheetInfo sinfo, XSSFWorkbook _workbook)
   {
       VOBJ invobj = indobj.getRetObject(sinfo.getDatasetName());
       try
       {
           XSSFSheet _sheet = _workbook.getSheetAt(sinfo.getSheetNo());
           System.out.println("=========== Sheet Name:" + _sheet.getSheetName());
           XSSFCell _cell =null;
           XSSFRow _row = null;

           int idx =0 ;  //datarow
           int[] rownumber = sinfo.getWriteRowNo();
           invobj.first();
           while(invobj.next())
           {
               _row = _sheet.getRow(rownumber[idx]-1);
               if(_row == null) continue;
               for(int i=0;i<ordername.size();i++)
               {
                   _cell =_row.getCell(i+sinfo.getStartColumnIndex());
                   if(_cell == null) _cell =_row.createCell(i+sinfo.getStartColumnIndex());

                   _cell.setCellValue(invobj.getRecord().getDouble(ordername.get(i).toString().toUpperCase()));
               }
               idx++;
           }
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
        String filename = currentymd + "_mgr" ;
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

    //formula reset method
    private void resetFormulaSheet(XSSFSheet _sheet)
    {
        XSSFCell _cell =null;
        XSSFRow _row = null;
        for(int rowidx =0;rowidx<100;rowidx++)
        {
            _row = _sheet.getRow(rowidx);
            if(_row == null) continue;
            for(int cidx=0;cidx < 100;cidx++)
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
