<%@page import="org.json.simple.JSONArray"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="java.io.*"%>
<%@page import="javax.servlet.*"%>
<%@page import="java.util.*"%>
<%@page import="org.springframework.web.multipart.*"%>
<%@page import="egovframework.com.cmm.service.*"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page import="org.json.*"%>


<%@ page language="java" contentType="application/j-son; charset=utf-8"
    pageEncoding="UTF-8"%>

<%

String path = "C:\\tomcat7\\webapps\\PBCloud\\uploadfile"; // 이미지가 저장될 주소
String fileName = "";
String fileSize = "";
String filePath = "";
String fileExt = "";
String originalName = "";
int maxsize =  300*1024*1024 ; //파일 사이즈
//if(request.getContentLength() > 1*1024*1024 ){
%>

<%
	//return;

//} else {

	try {
		
		
        	MultipartRequest multi=new MultipartRequest(request, path, maxsize, "UTF-8", new DefaultFileRenamePolicy());
        	  String fpath = path + "\\";
    		java.text.SimpleDateFormat formatter2 = new java.text.SimpleDateFormat ("yyyyMMddHHmmss", java.util.Locale.KOREA);
    		 JSONObject filemap = new JSONObject();
    		 
    		Enumeration<String> parms = multi.getParameterNames();
    		while(parms.hasMoreElements()){
    			String name = parms.nextElement();
    			String value = multi.getParameter(name);
        
    			//out.print(name+ "="+ value + "<br/>" );
    		}
    		
    		Enumeration<String>files = multi.getFileNames();
    		
    		JSONArray result = new JSONArray();
		    JSONObject jsonobj = new JSONObject();

    		int cnt = 1;
    		while(files.hasMoreElements()){
    			
   			String name = files.nextElement();
   			
   			String dateString = formatter2.format(new java.util.Date());
   			String upfile = (multi.getFilesystemName(name.toString()));
   			String moveFileName = dateString+cnt + upfile.substring(upfile.lastIndexOf(".") );
   			fileName = multi.getFilesystemName(name);
   			originalName = multi.getOriginalFileName(name);
   			fileExt = multi.getContentType(name);
   			
   			File f = new File(path+moveFileName);
   			File sourceFile = new File(path + File.separator + upfile);
   			File targetFile = new File(path + File.separator + moveFileName);
   			sourceFile.renameTo(targetFile);
   		/*	System.out.println("실제 파일 이름 : " + fileName +" cnt = " +cnt);  //파일 이름
   			System.out.println("서버 저장파일 경로 : " + path +" cnt = " +cnt); //파일 경로
   			System.out.println("Input FIle Name정보 : " + name.toString() +" cnt = " +cnt);//유닉크네임 
   			System.out.println("유니크 파일 이름 : " + moveFileName +" cnt = " +cnt);
   			System.out.println("파일 사이즈 : " + f.getName().length()  +" cnt = " +cnt  +  "    size  " + request.getContentLength());*/
   			
   			filemap.put("FILENAME", fileName);
   			filemap.put("PATH", fpath);
   			filemap.put("UNFILENAME", moveFileName);
   			filemap.put("FILESIZE", request.getContentLength() +"");
   			result.add(filemap);
    			cnt++;
    			sourceFile.delete();
    		}
    		jsonobj.put("File", result);
    		response.getWriter().print(jsonobj);
    		System.out.println(" jsonobject : " + jsonobj);

			%>
			
			<%
		
        
	} catch (Exception e) {
		System.out.println("e : " + e.getMessage());
		%> 
		
		
		<%
	//}
}
%>