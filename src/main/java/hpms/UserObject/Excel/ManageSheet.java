package hpms.UserObject.Excel;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.io.FileInputStream;

import java.util.HashMap;
import java.util.Date;


import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;

import java.util.ArrayList;

import egov.wizware.com.*;
import egov.wizware.ria.EgovProperties;

import java.io.File;

import org.apache.poi.xssf.usermodel.XSSFCell;

import java.io.FileOutputStream;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import WIZ.FR.DAO.Connector;

public class ManageSheet
{
    private final String rootPath =EgovProperties.getProperty("Globals.rootPath");//"c:/tomcat7/webapps/hpms/";
    private  String planlayoutfile ="manage_std_layout.xlsx";
    private  ArrayList ordername= new ArrayList();
    private  String downtempfold = this.rootPath + "ExcelDownLoad";
    private  String layoutfilefold = this.rootPath + "ExcelLayout";

    public ManageSheet()
    {
    }

    //---------------------------------------------------------------------------------------------------------------
    //----------------------------   [ download method processing start  ] ------------------------------------------
    //---------------------------------------------------------------------------------------------------------------
    public DOBJ downloadSheet(DOBJ indobj)
    {
        indobj.dispRetObjectKeyNames();
        indobj = makeManageSheet(indobj);
        return indobj;
    }
   
    
    
    private Connection getConnection() throws Exception
       {
    	/* DB 접속 정보 가져오는 변수 설정 */
	    	String DB_name = EgovProperties.getProperty("Globals.DbType").toString();
	    	String DB_user = EgovProperties.getProperty("Globals.UserName").toString();
	    	String DB_pw =   EgovProperties.getProperty("Globals.Password").toString();
	    	String DB_URL =  EgovProperties.getProperty("Globals.Url").toString();
     
            Connection Conn = null;
            try
            {
                Connector connection = new Connector();
                Conn = connection.getConnectionDirect(DB_name,DB_URL,DB_user,DB_pw);
            }
            catch(Exception e)
            {
                e.printStackTrace();
                throw e;
            }
            return Conn;
       }
       
       private PreparedStatement getStatement(Connection Conn, DOBJ dobj) throws Exception
       {
    	   /* 경영종합엑셀 오른쪽 데이타 가져오는 쿼리 */
           String query ="select * from table( HP5D101T_F12(?, ?, ?, ?)  )   ";//HP5D101T_F12(:COMPANY_CD, :ORG_CD, :DATA_TYPE, :YYYYMM)
           //String query ="SELECT 1 AS SEQ , 'TITLE' AS TITLE , 'ID' AS ID , 'DD' dd FROM DUAL";
           PreparedStatement pstmt = Conn.prepareStatement(query) ;
          
           /* 쿼리 실행에 필요한 파라미터 설정 */
           pstmt.setString(1,dobj.getRetObject("S").getRecord().get("COMPANY_CD").substring(0, 3));
           pstmt.setString(2,dobj.getRetObject("S").getRecord().get("ORG_CD"));
           pstmt.setString(3,dobj.getRetObject("S").getRecord().get("DATA_TYPE"));
           pstmt.setString(4,dobj.getRetObject("S").getRecord().get("YYYYMM"));
           return pstmt;
       }
       
    //경영종합표엑셀 만드는 함수
    private DOBJ makeManageSheet(DOBJ indobj)
    {
        String unifilename = ""; // 유니크 파일이름 
        String Manage_FILE_NM = indobj.getRetObject("SEL5").getRecord().get("TEMPLATE_FILE_NAME"); // 템프 파일 이름
        String ParamMonth = indobj.getRetObject("S").getRecord().get("YYYYMM");  // 년월 
        File _fp = null;
        FileInputStream _fin = null;
        FileOutputStream fileOut =null;
        try 
        {
		    ParamMonth= ParamMonth.substring(4,6);  
		    Manage_FILE_NM = Manage_FILE_NM.substring(0,7); 
		    Manage_FILE_NM= Manage_FILE_NM+ParamMonth+ ".xlsx";
		   
		    planlayoutfile =Manage_FILE_NM;
             
            unifilename = getUniFilename();
            if (layoutcopy(planlayoutfile, unifilename) == false)
            {
                return null;
            }
            _fp = new File(downtempfold + "/" + unifilename);
            _fin = new FileInputStream(_fp);
            XSSFWorkbook _workbook = null;
            _workbook = new XSSFWorkbook(_fin);
           
            SXSSFWorkbook LageWorkbook = new SXSSFWorkbook(_workbook,100);

            addSheetPlanExcel(indobj, LageWorkbook);
            
            LageWorkbook.setForceFormulaRecalculation(true);// 계산식 적용처리
           
            fileOut = new FileOutputStream(_fp);
            //_workbook.write(fileOut);
            LageWorkbook.write(fileOut);
            
            LageWorkbook.dispose();
            // 파일에 암호 걸기 
            /* POIFSFileSystem poifs = new POIFSFileSystem();
	         EncryptionInfo info = new EncryptionInfo(EncryptionMode.agile);

	         Encryptor enc = info.getEncryptor();
	         enc.confirmPassword("1111");

	         OPCPackage opc = OPCPackage.open(_fp, PackageAccess.READ_WRITE);
	         OutputStream os = enc.getDataStream(poifs);
	         opc.save(os);
	         opc.close();

	         FileOutputStream fos = new FileOutputStream(_fp);
	         poifs.writeFilesystem(fos);
	         fos.close();   */

        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
           try
           {
               if (_fin != null)    _fin.close();
               if (fileOut != null)    fileOut.close();
           }
           catch(Exception f) {}
       }
       
        
       FileUtil fu = new FileUtil();
 	   fu.getSYSDATE();
 	    
 	   String FileNM = Manage_FILE_NM+"_"+indobj.getRetObject("G").getRecord().get("USER_ID")+"_"+fu.getSYSDATE()+".xlsx";
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

   private  void addSheetPlanExcel(DOBJ indobj, Workbook _workbook) throws SQLException
   {
	   
	   
	   Connection Conn = null;
	   PreparedStatement pstmt = null;
	   ResultSet rset = null;
	   
       try
       {
    	  
                //-------------------------------------------------------------------------------------------------
    	   

    	   int rowcnt = 0;
           int leftColumSize = 2;                //left column size
                        //left row index
           int rightStartIndex = 26;             //right start index
           int rightColumnMaxsize = 30;          //right max column count
           int rightrowindex =0;                 //right row index

           //XSSFSheet _sheet = _workbook.getSheetAt( 1 );       //2번 sheet(data)
           Sheet _sheet = _workbook.getSheetAt(1); 
           
           VOBJ invobj = indobj.getRetObject( "S" );          //UI Input Dataset
           VOBJ leftvobj = indobj.getRetObject( "SEL02" );     //Left Dataset
         //  VOBJ rightvobj = indobj.getRetObject( "SEL03" );    //Right Dataset
           
           VOBJ LEFTCOLUMN = indobj.getRetObject( "leftColumn" );   
           ArrayList LeftCol =  getMaxCol(LEFTCOLUMN);
           
           VOBJ RIGHTCOLUMN  = indobj.getRetObject( "RightColumn" );
           ArrayList RightCol = getMaxCol(RIGHTCOLUMN);

           Cell _cell =null;
           Row _row = null;
           
          
           //=====================================UI Input Data Set =======================================
           int leftrowindex =0 ;
           _row = _sheet.createRow(leftrowindex);
           _cell = _row.createCell(0);  _cell.setCellValue("COMPANY_CD");
           _cell = _row.createCell(1);  _cell.setCellValue(invobj.getRecord().get("COMPANY_CD").substring(0, 3));

           leftrowindex++;
           _row = _sheet.createRow(leftrowindex);
           _cell = _row.createCell(0);  _cell.setCellValue("ORG_CD");
           _cell = _row.createCell(1);  _cell.setCellValue(invobj.getRecord().get("ORG_CD"));
           
           leftrowindex++;
           _row = _sheet.createRow(leftrowindex);
           _cell = _row.createCell(0);  _cell.setCellValue("DATA_TYPE");
           _cell = _row.createCell(1);  _cell.setCellValue(invobj.getRecord().get("DATA_TYPE"));
           
           leftrowindex++;
          
           _row = _sheet.createRow(leftrowindex);
           _cell = _row.createCell(0);  _cell.setCellValue("YYYYMM");
           _cell = _row.createCell(1);  _cell.setCellValue(invobj.getRecord().get("YYYYMM"));
     
           //=============================================================================================

           //=====================================Left Data Set =======================================
           leftvobj.first();
           while(leftvobj.next())
           {
               leftrowindex++;
               _row = _sheet.createRow(leftrowindex);
               for(int idx=0;idx<leftColumSize;idx++)
               {
            	 
                      _cell = _row.createCell(idx);
                   // _cell.setCellValue(leftvobj.getRecord().get("COL"+(idx+1) ));
                      String Colinfo = LEFTCOLUMN.getRecord().get(  LeftCol.get(idx).toString());
                      String cellValue = leftvobj.getRecord().get(Colinfo )+"";
                      
                      if(Colinfo.indexOf("VAL") == -1){
                     	 if(cellValue.length() == 0 || cellValue == null|| cellValue.equals("null")){
                     		 
                     			_cell.setCellValue("");
                     	 }else{
                     		 _cell.setCellValue(cellValue);
                     	 }
                      	
                      	
                      	
                      }else{
                      	if(cellValue.length() == 0 || cellValue == null || cellValue.equals("null")){
                      		
                      		_cell.setCellValue(""); // cell null 
                      		
                      	}else{
                      		
                      		_cell.setCellType(_cell.CELL_TYPE_NUMERIC);
                          	_cell.setCellValue(Double.parseDouble(cellValue));
                      		
                      	}
                      	
                      }
                   
               }
           }
           _sheet.autoSizeColumn(0); 
           //=============================================================================================

           HashMap<Object,Object> record = null;
           Conn = getConnection();
           pstmt = getStatement(Conn, indobj);
           rset = pstmt.executeQuery();
           ResultSetMetaData rmeta = pstmt.getMetaData();
           
           rightrowindex=0; //엑셀 Rightrowindex
           
          
          
          
           while (rset.next())
           {
        	  
               //-----------------------------------------------------------------------------------------------------
                record = new HashMap<Object,Object>();
                for(int cindex=1; cindex <= rmeta.getColumnCount(); cindex++)
                {
                  
                  record.put(rmeta.getColumnName(cindex), rset.getString(rmeta.getColumnName(cindex))+"");
                  
                }
                rowcnt++;
              //=====================================Right Data Set =======================================
                   
                    if(leftrowindex < rightrowindex)
                    {
                    	
                        _row = _sheet.createRow(rightrowindex);
                    }
                    else
                    {
                        _row = _sheet.getRow(rightrowindex);
                        if(_row == null)
                        {
                            _row = _sheet.createRow(rightrowindex);
                        }
                    }

                    for(int i=0; i <= rmeta.getColumnCount()-1; i++)
                    {
                        _cell =_row.createCell(i + rightStartIndex);
                        
                       // _cell.setCellValue(rightvobj.getRecord().get(clist.get(i).toString()));
                       //  String cellValue = rightvobj.getRecord().get(clist.get(i).toString());
                       // String cellValue = rightvobj.getRecord().get(RIGHTCOLUMN.getRecord().get(RightCol.get(i).toString()))+"";
                      //  System.out.println(RIGHTCOLUMN.getRecord().get(RightCol.get(i).toString()) + " ++++++++++"+record.get("A0") );
                        String cellValue = record.get(RIGHTCOLUMN.getRecord().get(RightCol.get(i).toString()))+"";
                        String Colinfo = RIGHTCOLUMN.getRecord().get(RightCol.get(i).toString());
                        	
                    //   System.out.println(cellValue + " <-- VALUE "  + Colinfo);
                        
                         if(Colinfo.indexOf("VAL") == -1){
                        	 if(cellValue.length() == 0 || cellValue == null|| cellValue.equals("null")){
                        		 
                        			_cell.setCellValue("");
                        	 }else{
                        		 _cell.setCellValue(cellValue);
                        	 }
                         	
                         	
                         	
                         }else{
                         	if(cellValue.length() == 0 || cellValue == null || cellValue.equals("null")){
                         		
                         		_cell.setCellValue(""); // cell null 
                         		
                         	}else{
                         		
                         		_cell.setCellType(_cell.CELL_TYPE_NUMERIC);
                             	_cell.setCellValue(Double.parseDouble(cellValue));
                         		
                         	}
                         	
                         }
                         
                    }
                    rightrowindex++;
                }
           
           
          // System.out.println( " -==========================-" + rowcnt + "-===================- " +  rmeta.getColumnCount());

   
           /*ArrayList clist = getMaxColumn(rightvobj, rightColumnMaxsize);
           rightrowindex=0;
           rightvobj.first();
           while(rightvobj.next())
           {
               if(leftrowindex < rightrowindex)
               {
                   _row = _sheet.createRow(rightrowindex);
               }
               else
               {
                   _row = _sheet.getRow(rightrowindex);
                   if(_row == null)
                   {
                       _row = _sheet.createRow(rightrowindex);
                   }
               }

               for(int i=0;i<clist.size();i++)
               {
                   _cell =_row.createCell(i + rightStartIndex);
                   
                  // _cell.setCellValue(rightvobj.getRecord().get(clist.get(i).toString()));
                  //  String cellValue = rightvobj.getRecord().get(clist.get(i).toString());
                   String cellValue = rightvobj.getRecord().get(RIGHTCOLUMN.getRecord().get(RightCol.get(i).toString()))+"";
                   String Colinfo = RIGHTCOLUMN.getRecord().get(RightCol.get(i).toString());
                   	
                    if(Colinfo.indexOf("VAL") == -1){
                    	_cell.setCellValue(cellValue);
                    }else{
                    	if(cellValue==null || cellValue.equals("")){
                    		_cell.setCellValue(cellValue);  // cell null 
                    	}else{
                    		_cell.setCellType(_cell.CELL_TYPE_NUMERIC);
                        	_cell.setCellValue(Double.parseDouble(cellValue.toString()));
                    		
                    	}
                    	
                    }
                    
               }
               rightrowindex++;
           }*/
           //=============================================================================================
           
          // XSSFSheet _sheet2 = _workbook.getSheetAt( 0 );       //1번 sheet(PL)
          // resetFormulaSheet(_sheet2);
       }
       catch(Exception ex) 
       {
           ex.printStackTrace();
       }finally{
    	     
         if(pstmt !=null) pstmt.close();
         if(rset !=null) rset.close();
         if(Conn !=null) Conn.close();
          /* ResultSet rset = pstmt.executeQuery();
           ResultSetMetaData rmeta = pstmt.getMetaData();*/
       }
   }

   private ArrayList getMaxColumn(VOBJ rvobj, int maxsize)
   {
       ArrayList clist = new ArrayList();
       for(int i=1;i<rvobj.getColumnCnt()+1;i++)
       {
           if(i>maxsize) break;
           clist.add("COL" + i);
       }
       return clist;
   }
   
   private ArrayList getMaxCol(VOBJ rvobj)
   {
       ArrayList clist = new ArrayList();
      
       for(int i=0;i<rvobj.getColumnCnt();i++)
       {
         
           clist.add("A" + i);
       }
       return clist;
   }



   private ArrayList getMaxColumn_BK(VOBJ rvobj, int maxsize)
   {
       ArrayList clist = new ArrayList();
       if(rvobj == null) return clist;
       if(rvobj.getRecordCnt() == 0) return clist;
       rvobj.first();
       rvobj.next();
       HashMap rec = rvobj.getRecordMap();
       int cnt =0;
       for(int i=1;i<rvobj.getColumnCnt()+1;i++)
       {
           if(cnt>maxsize) break;
           if(rec.containsKey("COL" + i))
           {
               clist.add("COL" + i);
               cnt++;
           }
       }
       return clist;
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
