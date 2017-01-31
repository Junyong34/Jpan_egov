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
import egov.wizware.ria.EgovProperties;

import java.util.Calendar;
import java.io.File;

import org.apache.poi.xssf.usermodel.XSSFCell;

import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Cell;

public class MasterMgr
{
    private final String rootPath =EgovProperties.getProperty("Globals.rootPath");//"c:/tomcat7/webapps/hpms/";
    private  String planlayoutfile ="master_std_layout.xlsx";
    private  ArrayList ordername= new ArrayList();
    private  String downtempfold = this.rootPath + "ExcelDownLoad";
    private  String layoutfilefold = this.rootPath + "ExcelLayout";

    public MasterMgr()
    {
    }

    //---------------------------------------------------------------------------------------------------------------
    //----------------------------   [ download method processing start  ] ------------------------------------------
    //---------------------------------------------------------------------------------------------------------------
    public DOBJ MasterDownloadSheet(DOBJ indobj)
    {
        VOBJ dvobj = indobj.getRetObject("S");
        String flag = dvobj.getRecord().get("FLAG");
        SheetInfo sheetinfo = null;
        if(flag.toUpperCase().equals("CURRENCY"))   // 통화마스트
        {
            sheetinfo = new SheetInfo("master", 0, "SEL1", "mst", 0);
            String[] colname = {"CODE","NAME"};
            String[] title = {"Currency Code","Currency NAME"};
            sheetinfo.setFilename("currency_master.xlsx",  colname);
            sheetinfo.setHeadTitle(title);
        }
        else if(flag.toUpperCase().equals("APPLICATION"))
        {
            sheetinfo = new SheetInfo("master", 0, "SEL2", "mst", 0);
            String[] colname = {"CODE","NAME"};
            String[] title = {"Application Code","Application NAME"};
            sheetinfo.setFilename("application_master.xlsx",  colname);
            sheetinfo.setHeadTitle(title);
        }
        else if(flag.toUpperCase().equals("DEPARTMENT"))
        {
            sheetinfo = new SheetInfo("master", 0, "SEL3", "mst", 0);
            String[] colname = {"CODE","NAME"};
            String[] title = {"Department Code","Department NAME"};
            sheetinfo.setFilename("department_master.xlsx",  colname);
            sheetinfo.setHeadTitle(title);
        }

        indobj.dispRetObjectKeyNames();
        indobj = buildSheet(indobj, sheetinfo);
        return indobj;
    }


    private DOBJ buildSheet(DOBJ indobj, SheetInfo sheetinfo)
    {
        String unifilename = "";
        String Manage_FILE_NM = indobj.getRetObject("SEL5").getRecord().get("TEMPLATE_FILE_NAME");
        String ParamMonth = indobj.getRetObject("S").getRecord().get("YYYYMM");
        File _fp = null;
        FileInputStream _fin = null;
        
        ParamMonth= ParamMonth.substring(4,6);
        Manage_FILE_NM = Manage_FILE_NM.substring(0,7);
        Manage_FILE_NM= Manage_FILE_NM+ParamMonth+ ".xlsx";
     
        
        
        planlayoutfile =Manage_FILE_NM;
        try
        {
            ordername= new ArrayList();
            ordername = sheetinfo.getColname();
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
           VOBJ invobj = indobj.getRetObject(sinfo.getDatasetName());

           XSSFCell _cell =null;
           XSSFRow _row = null;
           int rowindex =0 ;
           _row = _sheet.createRow(rowindex);
           for(int i=0;i<sinfo.getHeadTitle().length;i++)
           {
               _cell = _row.createCell(i);
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
                   _cell =_row.createCell(i);
                   _cell.setCellValue(invobj.getRecord().get(ordername.get(i).toString().toUpperCase()));
               }
               rowindex++;
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
