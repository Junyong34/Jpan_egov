package egov.wizware.util;

import egov.wizware.com.*;

import java.util.*;
import java.text.*;
import java.io.*;

import org.apache.poi.poifs.filesystem.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ExcelDownload {
	private ArrayList orderlist = null;
	private ArrayList datalist = null;
	private ArrayList spanlist = null;
	private HashMap headinfo = null;
	private HashMap spanJson= null;
	private VOBJ invobj = null;
    ArrayList<Integer> ColspanNmList = null;
	//private JSONObject spanJson= null;
	
	public ExcelDownload(ArrayList orderlistx, HashMap headx, VOBJ _vobj, HashMap  headJson,ArrayList spanlistx,ArrayList datalistx) {
		orderlist = orderlistx;
		headinfo = headx;
		invobj = _vobj;
		spanJson = headJson;
		spanlist = spanlistx;
		datalist = datalistx;
		
	}

	private String _Escape(String data) {
		data = data.replaceAll("", "\"");
		data = data.replaceAll("", "%");
		data = data.replaceAll("", "&");
		data = data.replaceAll("", "\\");
		return data;
	}

	// 엑셀 파일 이름 가져오기
	public String FIleSearh(String location, String filename) {

		String SaveFileName = "";
		try {
			int pos = filename.lastIndexOf(".");
			int len = filename.length();

			String FileNM = filename.substring(0, pos); // 파일이름
			String extname = filename.substring(pos + 1, len); // 확장자명

			if (new File(location + "/" + filename).isFile()) {
				int i;
				for (i = 1; i < 999; i++) {
					if (!new File(location + "/" + FileNM + "(" + i + ")."
							+ extname).isFile()) {
						break;
					}

				}
				SaveFileName = FileNM + "(" + i + ")." + extname;
			} else {

				SaveFileName = filename;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return SaveFileName;
	}

	// headinfo:VARIABLE:HEADTXT,VARIABLE:HEADTXT,VARIABLE:HEADTXT,VARIABLE:HEADTXT
	// VOBJ
	public void makeFile(String location, String filename) {

	
		
		XSSFWorkbook wook = new XSSFWorkbook(); // Excel 2007 이상
		XSSFSheet sheet1 = wook.createSheet("firstSheet"); // Sheet 생성
		XSSFFont font = wook.createFont();
		font.setBold(true);
		// Cell 스타일 생성
		XSSFCellStyle headStyle = wook.createCellStyle();
		headStyle.setWrapText(true); // 줄 바꿈
		headStyle.setFont(font);
		headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		headStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		headStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		headStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		headStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// headStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
		headStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(223,
				233, 255)));
		headStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		// Cell 색깔, 무늬 채우기
		// cellStyle.setFillForegroundColor(new XSSFColor().getIndexed());
		// cellStyle.setFillPattern(CellStyle.BIG_SPOTS);
		XSSFCellStyle bodystyle = wook.createCellStyle();
		bodystyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		bodystyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		bodystyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		bodystyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		bodystyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		
		
		// 헤더가 두줄이 아닐때 .
		if(spanlist.size() ==0 || spanlist == null){
			if (orderlist == null || orderlist.size() == 0)
				return;
			
			// wook.setSheetName(0, "firstSheet");

			// 컬럼 너비 설정
			// sheet1.setColumnWidth(0, 10000);
			// sheet1.setColumnWidth(9, 10000);
			

			Row row = null;
			Cell cell = null;
			// 첫 번째 줄 : head 정보 생성
			row = sheet1.createRow(0);

			for (int i = 0; i < orderlist.size(); i++) {
				cell = row.createCell(i);
				cell.setCellValue(_Escape(headinfo.get(orderlist.get(i).toString())
						+ ""));
				// System.out.println("======================"
				// +headinfo.get(orderlist.get(i).toString()) +
				// "======================" );
				cell.setCellStyle(headStyle); // 셀 스타일 적용

			}

			int rowidx = 1;
			invobj.first();

			while (invobj.next()) {
				row = sheet1.createRow(rowidx);
				for (int i = 0; i < orderlist.size(); i++) {

					cell = row.createCell(i);
					cell.setCellValue(_Escape(invobj.getRecord().get(
							orderlist.get(i).toString())));
					cell.setCellStyle(bodystyle);
					// System.out.println(orderlist.size() +"222222222"
					// +invobj.getRecord().get(orderlist.get(i).toString()) +
					// "+++++++++++" );

				}
				rowidx++;
			}
			
			
			
			
		}else{
			//헤더가 두줄 일때  2줄 이상 현재 구현안됨
			if (orderlist == null || orderlist.size() == 0)
				return;
			
			// wook.setSheetName(0, "firstSheet");

			// 컬럼 너비 설정
			// sheet1.setColumnWidth(0, 10000);
			// sheet1.setColumnWidth(9, 10000);
			

			
			Cell cell = null;
			Cell cell2 = null;
			Row row1 = null;
			Row row2 = null;
			//첫번쨰 row
			row1 = sheet1.createRow(0);
			// 두번쨰 row
			row2 = sheet1.createRow(1);
			
			Boolean isfirst_row = true;
			Boolean isfirst_col = true;
			// 값 초기화
			int cellindex = 0;
			int colspanindex = 0;
			// 셀안에 label 넣기
			for (int i = 0; i < orderlist.size(); i++) {
	           
	            int line2_row = spanlist.size();
	            if(i < line2_row){      	
	            	 String HeadNM = headinfo.get(orderlist.get(i).toString()).toString();
		            if(spanlist.get(i).toString().indexOf("col") !=-1){
		            //	System.out.println("Head 정보 : "+ headinfo.get(orderlist.get(i).toString()) + "  OderList : "	+ orderlist.get(i).toString() + " i 인덱스 : " + i);
			            	if(isfirst_col){
			            		// colpan이 먼저일때 인덱스 값 -1 처리
			            		if(i ==0 ){
			            			cellindex = colspanindex;
				            		colspanindex = Integer.parseInt((String) spanJson.get(spanlist.get(i)))+ colspanindex-1;
			            		
			            		}else{
			            			cellindex = colspanindex+ 1;
				            		colspanindex = Integer.parseInt((String) spanJson.get(spanlist.get(i)))+ colspanindex;
			            		}
			            	
			            		
			            		
			            		cell = row1.createCell(cellindex);
			            		cell.setCellValue(new XSSFRichTextString(_Escape(HeadNM)));
			            		cell.setCellStyle(headStyle); // 셀 스타일 적용
			            		//row1.createCell(cellindex).setCellValue(new XSSFRichTextString(HeadNM));
			            		//System.out.println( "first col@@ " + cellindex + " HeadNM :" + HeadNM + " @@"  +headinfo.get(orderlist.get(3).toString()).toString());
			            		
			        			
			            		//System.out.println("cellindex1 " +cellindex + "  i : " + i );
			            		isfirst_col = false;
			            		isfirst_row = false;
			            	}else{
			            		cellindex = colspanindex+ 1;
			            		colspanindex = Integer.parseInt((String) spanJson.get(spanlist.get(i)))+ colspanindex;
			            		cell = row1.createCell(cellindex);
			            		cell.setCellValue(new XSSFRichTextString(_Escape(HeadNM)));
			            		cell.setCellStyle(headStyle); // 셀 스타일 적용
			            		//row1.createCell(cellindex).setCellValue(new XSSFRichTextString(HeadNM));
			            	 // System.out.println("cellindex2@@ " +cellindex + "  i : " + i );
			            		//System.out.println( "not first col@@  " + cellindex+ " i :" + i);
			            		//isfirst_row = true;
			            	}
		            	          	
			            }else{
			            //	System.out.println("Head 정보2 : "+ headinfo.get(orderlist.get(i).toString()) + "  OderList : "	+ orderlist.get(i).toString() + " i 인덱스 : " + i);	
			            	if(isfirst_row){
			            		cellindex = i ;
			            		colspanindex = i;
			            		cell = row1.createCell(cellindex);
			            		cell.setCellValue(new XSSFRichTextString(_Escape(HeadNM)));
			            		cell.setCellStyle(headStyle); // 셀 스타일 적용
			            		// 병합했을떄 border 값을 주기위해 똑같은 값 넣음
			               		cell2 = row2.createCell(cellindex);
			            		cell2.setCellValue(new XSSFRichTextString(_Escape(HeadNM)));
			            		cell2.setCellStyle(headStyle); // 셀 스타일 적용
			            		
			            		
				            	//row1.createCell(cellindex).setCellValue(new XSSFRichTextString(HeadNM));
				            	
				            	//System.out.println( "first row " + cellindex+ " i :" + i);
			            	}else{
			            		cellindex = colspanindex +1 ;
			            		colspanindex = colspanindex +1;
			            		cell = row1.createCell(cellindex);	
			            		cell.setCellValue(new XSSFRichTextString(_Escape(HeadNM)));
			            		cell.setCellStyle(headStyle); // 셀 스타일 적용
			            		// 병합했을떄 border 값을 주기위해 똑같은 값 넣음
			            		cell2 = row2.createCell(cellindex);	
			            		cell2.setCellValue(new XSSFRichTextString(_Escape(HeadNM)));
			            		cell2.setCellStyle(headStyle); // 셀 스타일 적용
				            	//row1.createCell(cellindex).setCellValue(new XSSFRichTextString(HeadNM));
				            
				            	//System.out.println( "not first row " + cellindex+ " i :" + i);
				            
			            	}
			            	isfirst_col = true;
			            }
	            
	            }else{
	             	
	            }
				//System.out.println("Head 정보 : "+ headinfo.get(orderlist.get(i).toString()) + "  OderList : "	+ orderlist.get(i).toString() + " i 인덱스 : " + i);
			}
			
			
			// 값 초기화
			cellindex = 0 ;
			colspanindex = 0;
			Boolean isfirst_col_1 =true;
			Boolean isfirst_row_1 =true;
			// 두번째 row에 넣어야 할 label 인덱스 값 넣기 Arraylist
		   ColspanNmList =new ArrayList<Integer>();
		   ArrayList<Integer> cellList =new ArrayList<Integer>();
			for (int i = 0; i < orderlist.size(); i++) {
				
	            int line2_row = spanlist.size();
	            if(i < line2_row){      	
	            	
		            if(spanlist.get(i).toString().indexOf("col") !=-1){
		            	
			            	if(isfirst_col_1){
			            		// colpan이 먼저일때 인덱스 값 -1 처리
			            		if(i ==0){
			            			cellindex = colspanindex;
				            		colspanindex = Integer.parseInt((String) spanJson.get(spanlist.get(i)))+ colspanindex-1;
			            			
			            		}else{
			            			cellindex = colspanindex+ 1;
				            		colspanindex = Integer.parseInt((String) spanJson.get(spanlist.get(i)))+ colspanindex;
			            			
			            		}
			            		
			            		int total_index = cellindex +   Integer.parseInt((String) spanJson.get(spanlist.get(i)));
			            		
			            		for(int in = cellindex; in < total_index; in++){
			            			
			            			//row2.createCell(in).setCellValue(new XSSFRichTextString(HeadNM));
			            			//System.out.println(in);
			            			
			            			ColspanNmList.add(in);
			            		//	cellList.add(in);
			            		}
			            		
			            		//System.out.println( "first col " + cellindex + " HeadNM :"  + " span " + Integer.parseInt((String) spanJson.get(spanlist.get(i))));
			            		//System.out.println("cellindex1 " +cellindex + "  i : " + i );
			            		isfirst_col_1 = false;
			            		isfirst_row_1 = false;
			            	}else{
			            		cellindex = colspanindex+ 1;
			            		colspanindex = Integer.parseInt((String) spanJson.get(spanlist.get(i)))+ colspanindex;
			            		int total_index = cellindex +   Integer.parseInt((String) spanJson.get(spanlist.get(i)));
			            		for(int in = cellindex; in < total_index; in++){
			            			
			            			//row2.createCell(in).setCellValue(new XSSFRichTextString(HeadNM));
			            			ColspanNmList.add(in);
			            			//cellList.add(in);
			            		}
			            		
			            		
			            		//row2.createCell(cellindex).setCellValue(new XSSFRichTextString(HeadNM));
			            	//	System.out.println("cellindex2 " +cellindex + "  i : " + i );
			            	//	System.out.println( "not first col " + cellindex+ " HeadNM :"  + " span " + Integer.parseInt((String) spanJson.get(spanlist.get(i))));
			            		//isfirst_row = true;
			            	}
		            	          	
			            }else{
			            	if(isfirst_row_1){
			            		cellindex = i ;
			            		colspanindex = i;	
			            		//System.out.println(cellindex + " !" );
			            		//cellList.add(cellindex);
			            	}else{
			            		cellindex = colspanindex +1 ;
			            		colspanindex = colspanindex +1;
			            		//System.out.println(cellindex + " 2" );
			            		//cellList.add(cellindex);
				            
			            	}
			            	//System.out.println("Head 정보 : "+ headinfo.get(orderlist.get(i).toString()) + "  OderList : "	+ orderlist.get(i).toString() + " i 인덱스 : " + i);
			            	
			            	isfirst_col_1 = true;
			            }        	
	            }
			}
			
			
			
			   
			
			/*for(int i =0 ; i < cellList.size(); i++){
				
				System.out.println(" CEll Index " + cellList.get(i) + " orderlist.get(i).toString() " + orderlist.get(cellList.get(i)).toString() + "  : " );
			}*/
			//System.out.println(ColspanNmList.size() +  " @@ " + orderlist.size() + " @@ " +  spanlist.size());
			// 담긴 라벨 인덱스 두번쨰 row label 값 넣기
			for(int i =0 ; i < ColspanNmList.size(); i++){
				
				int head_size = orderlist.size();  // 50
				int span_size = spanlist.size();   // 17
				int cellnmIndex = span_size + i ;
				String HeadNM = headinfo.get(orderlist.get(cellnmIndex).toString()).toString();
			//	System.out.println(" CEll Index2 " + ColspanNmList.get(i) + " NM " + HeadNM + " cellnmIndex : " + cellnmIndex);
				cell = row2.createCell(ColspanNmList.get(i));
	    		cell.setCellValue(new XSSFRichTextString(_Escape(HeadNM)));
	    		cell.setCellStyle(headStyle); // 셀 스타일 적용
				//row2.createCell(ColspanNmList.get(i)).setCellValue(new XSSFRichTextString(HeadNM));
				//row2.setRowStyle(headStyle);
				
			}
			
			
			   
			 //시작
			int StartCol = 0;
			//끝
			int EndCol = 0;
			//colspan 되는 cell 수
			int colspancnt = 0;
			// 첫번쨰 구분
			Boolean isfirst_col1 = true;
			//첫번쨰 구분
			Boolean isfirst_row2 = true;
			//col 병합이 먼저인지 row병합이 먼저 인지 판단.
			Boolean Iscolrowspan = true;
			// 셀 머지 포멧만들기
			for (int i = 0; i < spanlist.size(); i++) {
				// row 스판일 경우 
	             if(spanlist.get(i).toString().indexOf("row") !=-1){
	            	 if(isfirst_row2){
	            		 
	            		 
	            		 StartCol = i ;
	                	 EndCol = i;
	                	
	                	// System.out.println("first rowsatrt : "  + StartCol  + " colend :  "+ EndCol);
	            	 }else{
	            		 StartCol = EndCol +1 ;
	                	 EndCol = EndCol +1;
	                	// System.out.println("not first rowsatrt : "  + StartCol  + " colend :  "+ EndCol);
	            	 }
	            	 
	            	 // 시트 머지 실행
	            	 sheet1.addMergedRegion(new CellRangeAddress((int) 0, (short) 1,	(int) StartCol, (short) EndCol)); 
	            
	            	 isfirst_col1 = true;
	             }
	             //col span 경우
	             if(spanlist.get(i).toString().indexOf("col") !=-1){
	            	 colspancnt++;
	            	 if(isfirst_col1){
	            		// colpan이 먼저일때 인덱스 값 -1 처리
	            		if(i == 0 ){
	            			StartCol = EndCol;
	            			 EndCol = Integer.parseInt((String) spanJson.get(spanlist.get(i)))+ EndCol-1;
	            		
	            		}else{
	            			StartCol = EndCol+ 1;
	            			 EndCol = Integer.parseInt((String) spanJson.get(spanlist.get(i)))+ EndCol;
	            		}
	            			 	 
	            		 //System.out.println("first colsatrt : "  + StartCol  + " colend :  "+ EndCol + " I " + i);
	            		 //시트 머지 실행
	                 	 sheet1.addMergedRegion(new CellRangeAddress((int) 0, (short) 0,	(int) StartCol, (short) EndCol)); 
	                 	 //값 초기화
	                 	isfirst_row2 = false;
	                 	// 값 초기화 
	                 	isfirst_col1 = false;
	            	 }else{
	            		 StartCol = EndCol+ 1;
	            		 EndCol = Integer.parseInt((String) spanJson.get(spanlist.get(i)))+ EndCol;
	            		 //System.out.println("not first colsatrt : "  + StartCol  + " colend :  "+ EndCol);
	            		 //시트 머지 실행
	                  	 sheet1.addMergedRegion(new CellRangeAddress((int) 0, (short) 0,	(int) StartCol, (short)EndCol)); 
	            		 
	            	 }
	            	
	             }
	             //값 다시 초기화
	           
				//System.out.println("span정보 : "+ spanJson.get(spanlist.get(i).toString()) + "  spanJson : "	+ spanlist.get(i).toString() + " i 인덱스 : " + i + " @ @ "+  spanlist.size());

			}
			
			       
			/*for (int i = 0; i < spanlist.size(); i++) {

				System.out.println("span정보 : "+ spanJson.get(spanlist.get(i).toString()) + "  spanJson : "	+ spanlist.get(i).toString() + " i 인덱스 : " + i + " @ @ "+  spanlist.size());

			}
		
		for (int i = 0; i < orderlist.size(); i++) {
	        
			System.out.println("Head 정보 : "+ headinfo.get(orderlist.get(i).toString()) + "  OderList : "	+ orderlist.get(i).toString() + " i 인덱스 : " + i);
		        
				//System.out.println(spanJson.get(spanlist.get(i).toString()));
			}*/
			
			int rowidx = 2;
			invobj.first();
			
	       //body 데이타 생성 부분
			while (invobj.next()) {
				row1 = sheet1.createRow(rowidx);
				// colspan 있는 갯수 만큼 제외하고 데이타 생성
				for (int i = 0; i < datalist.size(); i++) {
					
	            // System.out.println( "i " + i + " COLNM " + datalist.get(i).toString() + " data "  + invobj.getRecord().get(datalist.get(i).toString()));
					cell = row1.createCell(i);
					cell.setCellValue(_Escape(invobj.getRecord().get(datalist.get(i).toString())));
					cell.setCellStyle(bodystyle);
					
	             
					// System.out.println(orderlist.size() +"222222222"
					// +invobj.getRecord().get(orderlist.get(i).toString()) +
					// "+++++++++++" );

				}
				rowidx++;
			}
			
		}
			
			
			
			
		
		

		try {

			File xlsFile = new File(location + "/" + filename);
			FileOutputStream fileOut = new FileOutputStream(xlsFile);
			wook.write(fileOut);
			fileOut.close();

			/*
			 * response.setContentType("Application/Msexcel");
			 * response.setHeader("Content-Disposition",
			 * "Attachment; Filename="+filename);
			 */
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
