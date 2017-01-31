<%@ page import="java.net.*" contentType="text/html;charset=utf-8" %>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="java.io.*"%>
<%@page import="javax.servlet.*"%>
<%@page import="java.util.*"%>
<%@page import="org.springframework.web.multipart.*"%>
<%@page import="org.springframework.util.*"%>

<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page import="org.json.*"%>
<%@ page import="egov.wizware.com.*"%>


<% 	
	

	String filename = request.getParameter("filename");
	String rfilename = request.getParameter("rfilename");
    ServletContext cont = request.getServletContext(); //xml에서 지정
    String dir = cont.getInitParameter("ExcelFilePath");
    String fileType = filename.substring(filename.lastIndexOf(".")+1);;

	File f = null;

	//String dir = BaseProperty.get("notice.upload.dir");
	//String dir = "C:\\Users\\Administrator\\workspace2\\ProcessBuilder_Sample\\src\\main\\webapp\\uploadfile\\";
	
	try 
	{
		
		//filename = URLEncoder.encode(filename,"utf-8");
		//rfilename = URLEncoder.encode(rfilename,"utf-8");
		filename = dir +"\\" + filename;
		
		f = new File(filename);
	System.out.println( filename + "=============================================="+ rfilename +  " @@ " + filename );
		//System.out.println("zz : " + f.getName());
			//local 한글 깨짐 방지
		//String tmp = new String(f.getName().getBytes("EUC-KR"), "ISO-8859-1");  
	//	System.out.println( f.toString()  + " #### " + filename);
		if (f == null || f.exists() == false) 
		{
			%>
				<title> WizGridExcel </title>
				<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
				<script type="text/javascript">
				
				history.back();
				</script>
			<%
			System.out.println("======================= 그리드 데이타가 존재하지 않습니다. ========================");
		  
		} 
		else 
		{
			//response.reset();
			if (fileType.equals("hwp")){
				response.setContentType("application/x-hwp");
				} else if (fileType.equals("pdf")){
				response.setContentType("application/pdf");
				} else if (fileType.equals("ppt") || fileType.equals("pptx")){
				response.setContentType("application/vnd.ms-powerpoint");
				} else if (fileType.equals("doc") || fileType.equals("docx")){
				response.setContentType("application/msword");
				} else if (fileType.equals("xls") || fileType.equals("xlsx")){
				response.setContentType("application/vnd.ms-excel charset=utf-8");
				//System.out.println("======================= 엑셀 파일 ========================");
				} else {
				response.setContentType("application/octet-stream");
				}
				//response.setContentType("application/octet-stream");
				//response.setContentType("application/download; charset=utf-8");
			//response.setHeader("Content-Disposition", "attachment;filename=" + rfilename);    //h
			
			response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(rfilename,"UTF-8") );
			
			
			
			//local 한글 깨짐 방지
			//response.setHeader("Content-Disposition", "attachment; filename=" + tmp);
			 byte b[] = new byte[(int) f.length()];
			 
			
				 BufferedInputStream fin = new BufferedInputStream(new FileInputStream(f));
				  // response가 사용하는 OutputStream()을 out 스트림으로 사용 
				  BufferedOutputStream fout = new BufferedOutputStream(response.getOutputStream());
				  
				  int count = 0;
				 while((count = fin.read(b)) != -1){
					  fout.write(b,0,count);
					 
				  }
				  //FileCopyUtils.copy(fin, fout);
				  
				  if (fin != null)
					fin.close();
					if (fout != null)
						fout.close();
			 
					//파일 삭제
					/*if( f.exists() ){

						f.delete();

					 }*/
			  
			  
			/*
			fin = new FileInputStream(f);
			fout = response.getOutputStream();
			
			int leng = 0;
	
			while ((leng = fin.read(b)) > 0) 
			{
				fout.write(b, 0, leng);
			}*/
		}

	} 
	catch (Exception e) 
	{
	} 
	finally 
	{
	out.clear();
    out=pageContext.pushBody();

	
	}
%>

