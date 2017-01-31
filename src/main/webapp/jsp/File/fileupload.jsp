<%@page import="org.json.simple.JSONArray"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="hpms.UserObject.Excel.DefaultFileRenamePolicy"%>
<%@page import="java.io.*"%>
<%@page import="javax.servlet.*"%>
<%@page import="java.util.*"%>
<%@page import="org.springframework.web.multipart.*"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="hpms.UserObject.Excel.FileUtil"%>
<%@ page import="org.json.*"%>
<%@ page language="java" contentType="application/j-son; charset=utf-8"   pageEncoding="UTF-8"%>
<%
String path = "C:/tomcat7/webapps/hpms/uploadfile"; 
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
        FileUtil futil = new FileUtil();
		
		 HashMap itemList = (HashMap)session.getAttribute("G"); 
       
		
	     DefaultFileRenamePolicy DFRP = new DefaultFileRenamePolicy();
		 DFRP.setUserId(itemList.get("USER_ID").toString());
         MultipartRequest multi=new MultipartRequest(request, path, maxsize, "UTF-8", DFRP);
       	
       	String fpath = path + "/";
    		java.text.SimpleDateFormat formatter2 = new java.text.SimpleDateFormat ("yyyyMMddHHmmss", java.util.Locale.KOREA);
    		JSONObject filemap = new JSONObject();
    		 
    		Enumeration<String> parms = multi.getParameterNames();
    		while(parms.hasMoreElements())
    		{
    			String name = parms.nextElement();
    			String value = multi.getParameter(name);
    		}
    		Enumeration<String>files = multi.getFileNames();
    		
    		JSONArray result = new JSONArray();
		    JSONObject jsonobj = new JSONObject();

    		int cnt = 1;
    		while(files.hasMoreElements()){
    			
   			String name = files.nextElement();
   			
   			String dateString = formatter2.format(new java.util.Date());
   			String upfile = (multi.getFilesystemName(name.toString()));
   			//String moveFileName = dateString+cnt + upfile.substring(upfile.lastIndexOf(".") );
   			
   			//System.out.println("fileupload filename info=======>:"+upfile + ":" + futil.getUpfilename(upfile) );
   			
   			//fileName = multi.getFilesystemName(name);
   			//originalName = multi.getOriginalFileName(name);
   			//fileExt = multi.getContentType(name);
   			//File f = new File(path+moveFileName);
   			//File sourceFile = new File(path + File.separator + upfile);
   			//File targetFile = new File(path + File.separator + moveFileName);
   			//sourceFile.renameTo(targetFile);
   			
   		/*	System.out.println("실제 파일 이름 : " + fileName +" cnt = " +cnt);  //파일 이름
   			System.out.println("서버 저장파일 경로 : " + path +" cnt = " +cnt); //파일 경로
   			System.out.println("Input FIle Name정보 : " + name.toString() +" cnt = " +cnt);//유닉크네임 
   			System.out.println("유니크 파일 이름 : " + moveFileName +" cnt = " +cnt);
   			System.out.println("파일 사이즈 : " + f.getName().length()  +" cnt = " +cnt  +  "    size  " + request.getContentLength());*/
   			/* 멀티파트파일업로드는 파일명을 변경해서 올리 수 없음 그러므로 올린다음 파일이름을 바꿔야함 */
   			
   			String uniqueFileNm = futil.getUpfilename(upfile,itemList.get("USER_ID").toString());
   			File oldFile = new File(fpath + upfile);
            File newFile = new File(fpath + uniqueFileNm); 
        
	        byte[] buf = new byte[1024];
	        FileInputStream fin = null;
	        FileOutputStream fout = null;
	        oldFile.renameTo(newFile);
	       if(!oldFile.renameTo(newFile)){
		        buf = new byte[1024];
		        fin = new FileInputStream(oldFile);
		        fout = new FileOutputStream(newFile);
		     
		        int read = 0;
		        while((read=fin.read(buf,0,buf.length))!=-1){
		            fout.write(buf, 0, read);
		        }
		         
		        fin.close();
		        fout.close();
		        oldFile.delete();
	     	}
         /* 파일 리네임은 실패하는경우 true / false 경우 만 반영하기때문에 안전장치로 실패처리시 복사하여 만들어준다. */
   		
   			
   			filemap.put("FILENAME", uniqueFileNm);
   			filemap.put("PATH", fpath);
   			filemap.put("UNFILENAME", upfile);
   			filemap.put("FILESIZE", request.getContentLength() +"");
   			result.add(filemap);
    			cnt++;
    		   //sourceFile.delete();
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

	
}

%>