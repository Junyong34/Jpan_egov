package hpms.UserObject.Excel;
import java.io.*;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Request;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class DefaultFileRenamePolicy implements FileRenamePolicy
{
	String User_ID = "";
 public void setUserId(String id){
	  User_ID = id;
	  
 }
  public File rename(File f)
  {
	
    if (createNewFile(f))
    {
      return f;
    }
    String name = f.getName();
    String body = null;
    String ext = null;
    
    int dot = name.lastIndexOf(".");
    if (dot != -1) {
      body = name.substring(0, dot);
      ext = name.substring(dot);
    }
    else {
      body = name;
      ext = "";
    }
    
   
	//System.out.println( " 999999999999 "  +itemList.get("USER_ID"));
    java.text.SimpleDateFormat formatter2 = new java.text.SimpleDateFormat ("yyyy-MM-dd HH.mm.ss.SSS", java.util.Locale.JAPAN);
    String dateString = formatter2.format(new java.util.Date());
  
    if(!User_ID.equals("")){
    
     String newName = body +"_"+User_ID+"_"+dateString + ext;
      f = new File(f.getParent(), newName);
      User_ID="";
    }else{
    	
    	int count = 0;
        while (!createNewFile(f) && count < 9999)
        {
          count++;
          String newName = body +"_#"+ count + ext;
          f = new File(f.getParent(), newName);
          
        }
        User_ID="";
    }
   
   

    return f;
  }

  private boolean createNewFile(File f) {
    try {
      return f.createNewFile();
    }
    catch (IOException ignored) {
      return false;
    }
  }
}
