<%@ page import="java.net.*" contentType="text/html;charset=utf-8" %>
<%@ page import="java.io.*"%>
<%@ page import="egov.wizware.com.*"%>

        <script type="text/javascript" src="/hpms/wizware/js/jquery-1.11.0.min.js"></script>
        <script type="text/javascript" src="/hpms/js/hcost.js"></script>
<% 	
	 
	String filename ="";
	String rfilename ="";
	String dir = "";
	File f = null;
	String poolMSG = "";
	
	System.out.println(  "File Download ... ");
	 // System.out.println(  " ============================= retobj =========================="  + request.getAttribute("retobj"));
	
	
	try 
	{
		if(request.getAttribute("WIZDOBJ") != null)
		{
		    DOBJ dobj = (DOBJ)request.getAttribute("WIZDOBJ");
		    
		    //System.out.println("=============dobj:"+dobj);
		    dobj.dispRetObjectKeyNames();
		    
		    pageContext.setAttribute("G"   , dobj.getRetObject("G").getRecordMap()  );
		    pageContext.setAttribute("DN01"   , dobj.getRetObject("DN01").getRecordMap()  );
		    pageContext.setAttribute("poolcode"   , dobj.getRetObject("PoolSize").getRecordMap()  );
		    VOBJ poolSize =new VOBJ();
		    poolSize =  dobj.getRetObject("poolcode");
		    poolMSG= dobj.getRetmsg();
		   // System.out.println("=============poolSize:"+poolSize.getRetmsg()+ " @@  " + poolSize.getRetcode());
		   //System.out.println("=============poolSize:"+dobj.getRetmsg());
		    VOBJ FInfo =  new VOBJ();
		    FInfo =  dobj.getRetObject("DN01");
			
	        filename = FInfo.getRecord().get("FILE_UNNAME");
		    rfilename = FInfo.getRecord().get("FILE_NAME");
	        rfilename = rfilename.replaceAll(" ","_"); 	   
		    dir = FInfo.getRecord().get("FILE_PATH");
			
			//System.out.println(" 1 : " + filename + " 2 "  + rfilename + " 3 " + dir);
		}
		
		// out.println("<script>\n parent.$('#MAIN_View').append("<div id='visiablestop' style='position:absolute;top:0px;left:0px;width:100%;height:100%;background:white;z-index:888;filter:alpha(opacity=50);opacity:0.5;' > </div>"); \n</script>");
		 // out.println("<script>\n  parent.$('#MAIN_View').append("<img id='prcess_Loading' style='position:absolute;left:50%;top:50%;z-index:890;'/>"); \n</script>");
		filename = dir +"/"+ filename;
		//System.out.println("filename ===>"+filename +":" + rfilename );
		f = new File(filename);
		
		//System.out.println("zz : " + f.getName());
		
		//String tmp = new String(f.getName().getBytes("EUC-KR"), "ISO-8859-1");   
		
		if (f == null || f.exists() == false || poolMSG == "00039") 
		{
			
			String resultMsg = "";
			resultMsg += "<script type='text/javascript'>";
			resultMsg += "Return_RTN_MSG('"+poolMSG+"');\n";	
			resultMsg += "window.history.go(-1)";
			resultMsg += "</script>";
			out.print(resultMsg); 

		} 
		else 
		{
			response.reset();
			response.setContentType("application/octet-stream");
			
			//response.setHeader("Content-Disposition", "attachment;filename=" + rfilename);    //h
			response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(rfilename,"UTF-8") );
			
		
			//response.setHeader("Content-Disposition", "attachment; filename=" + tmp);
			 byte b[] = new byte[(int) f.length()];
			  BufferedInputStream fin = new BufferedInputStream(new FileInputStream(f));
			  // response? ???? OutputStream()? out ????? ?? 
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
		
		if ( poolMSG == "00039") 
		{
		
		}else{
			out.clear();
			out=pageContext.pushBody();
			
		}
		
	
		// out.println("<script>\n parent.$('#visiablestop').remove();\n parent.$('#prcess_Loading').remove(); \n</script>");
		
	
	}
%>

