package hpms.UserObject.Excel;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import java.text.SimpleDateFormat;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Date;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.IndexedColors;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import java.util.ArrayList;
import egov.wizware.com.*;
import java.util.Calendar;
import java.io.File;
import org.apache.poi.xssf.usermodel.XSSFCell;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Cell;

public class ManageSheet_V2
{
    private final String rootPath ="c:/tomcat7/webapps/hpms/";
    private final String planlayoutfile ="manage_std_layout.xlsx";
    private  ArrayList ordername= new ArrayList();
    private  String downtempfold = this.rootPath + "ExcelDownLoad";
    private  String layoutfilefold = this.rootPath + "ExcelLayout";

    public ManageSheet_V2()
    {
    }

    //---------------------------------------------------------------------------------------------------------------
    //----------------------------   [ download method processing start  ] ------------------------------------------
    //---------------------------------------------------------------------------------------------------------------
    public DOBJ downloadSheet(DOBJ indobj)
    {
        VOBJ dvobj = indobj.getRetObject("S");
        String flag = dvobj.getRecord().get("FLAG");
        SheetInfo sheetinfo = null;
        sheetinfo = new SheetInfo("master", 0, "SEL02", "mst", 3);
        String[] colname = {"HPMS_ID" ,"DATA_TYPE" ,"YYYYMM", "VAL" ,"DST_ORG_NM" ,"USE_ORG_NM" };
        String[] title = {"項目CD" ,"DATA種別" ,"YYYYMM" ,"値" ,"配賦先" ,"配賦元"};
        sheetinfo.setFilename("ManageSheet.xlsx",  colname);
        sheetinfo.setHeadTitle(title);

        indobj.dispRetObjectKeyNames();
        indobj = makePLSheet(indobj, sheetinfo);
        return indobj;
    }

    private DOBJ makePLSheet(DOBJ indobj, SheetInfo sheetinfo)
    {
        String unifilename = "";
        File _fp = null;
        FileInputStream _fin = null;
        try
        {
            unifilename = getUniFilename();
            if (layoutcopy(planlayoutfile, unifilename) == false)
            {
                return null;
            }
            _fp = new File(downtempfold + "/" + unifilename);
            _fin = new FileInputStream(_fp);
            XSSFWorkbook _workbook = null;
            _workbook = new XSSFWorkbook(_fin);

            addSheetPlanExcel(indobj, sheetinfo,  _workbook);

            FileOutputStream fileOut = new FileOutputStream(_fp);
            _workbook.write(fileOut);
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

       VOBJ _rvobj = new VOBJ();
       HashMap _rec = new HashMap();
       _rec.put("FILE_PATH",downtempfold);
       _rec.put("FILE_UNNAME",unifilename);
       _rec.put("FILE_NAME",sheetinfo.getFilename());
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
           VOBJ invobj = null;
           if(indobj.containKey("SEL02"))
           {
                invobj = indobj.getRetObject("SEL02");
           }
           else
           {
               invobj = indobj.getRetObject("SEL03");
           }
           ordername= new ArrayList();
           ordername = sinfo.getColname();

           XSSFCell _cell =null;
           XSSFRow _row = null;

           int rowindex =0 ;
           _row = _sheet.createRow(rowindex);
           for(int i=0;i<sinfo.getHeadTitle().length;i++)
           {
               _cell = _row.createCell(i+sinfo.getStartColumnIndex());
               _cell.setCellValue(sinfo.getHeadTitle()[i]);
           }
           rowindex++;

           invobj.first();
           while(invobj.next())
           {
               _row = _sheet.createRow(rowindex);
               if(_row == null) continue;
               for(int i=0;i<ordername.size();i++)
               {
                   _cell =_row.createCell(i+sinfo.getStartColumnIndex());
                   if(i==3)
                   {
                       _cell.setCellValue(invobj.getRecord().getDouble(ordername.get(i).toString().toUpperCase()));
                   }
                   else
                   {
                       _cell.setCellValue(invobj.getRecord().get(ordername.get(i).toString().toUpperCase()));
                   }
               }
               rowindex++;
           }

           //--------------------  PID infomation set -----------------------
           VOBJ mvobj = indobj.getRetObject("S");
           _row = _sheet.getRow(0);
           _row.createCell(0).setCellValue("セグメント記號");
           _row.createCell(1).setCellValue(mvobj.getRecord().get("ORG_CD"));

           _row = _sheet.getRow(1);
           if(_row == null) _row = _sheet.createRow(1);
           _row.createCell(0).setCellValue("YYYYMM");
           _row.createCell(1).setCellValue(mvobj.getRecord().get("YYYYMM"));

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
        String filename = currentymd + "_mst" ;
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
