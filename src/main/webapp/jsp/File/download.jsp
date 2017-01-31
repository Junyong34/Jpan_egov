<%@ page import="java.net.*" contentType="text/html;charset=utf-8" %>
<%@ page import="java.io.*"%>

<% 	
	
	//String filename = String2.toKor(request.getParameter("filename"));
  
	String filename = request.getParameter("filename");
	String rfilename = request.getParameter("rfilename");
  rfilename = rfilename.replaceAll(" ","_");

	//FileInputStream fin = null;
	//ServletOutputStream fout = null;
	File f = null;
	
	//String dir = BaseProperty.get("notice.upload.dir");
	String dir = request.getParameter("filepath");
	
	try 
	{
		filename = dir + filename;
		//System.out.println("filename ===>"+filename +":" + rfilename );
		f = new File(filename);
		
		//System.out.println("zz : " + f.getName());
		//local 한글 깨짐 방지
		//String tmp = new String(f.getName().getBytes("EUC-KR"), "ISO-8859-1");  
		
		if (f == null || f.exists() == false) 
		{
		    out.println("<script>\nalert('파일이 존재하지 않거나, 읽을수 없습니다.\\n다시 시도해 주세요...');\nwindow.history.go(-1);\n</script>");
		} 
		else 
		{
			response.reset();
			response.setContentType("application/octet-stream");
			
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
				  fout.write(b);
			  }
			
			  if (fin != null)
					fin.close();
				if (fout != null)
					fout.close();
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
  <script>this.close();</script>
