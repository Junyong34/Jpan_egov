package hpms.UserObject.BatchExe;
import java.io.*;
import java.net.*;
import java.util.concurrent.*;
import java.util.*;
import java.text.SimpleDateFormat;

import org.apache.log4j.helpers.LogLog;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;






import WIZ.FR.COM.*;

import org.apache.catalina.connector.Request;

import egov.wizware.com.VOBJ;
import egov.wizware.ria.EgovProperties;

public class RunningTask_bat implements Runnable
{
    private DOBJ indobj = null;
    public RunningTask_bat()
    {
    }
    public void setIndobj(DOBJ dobj)
    {
        indobj = dobj;
    }
    
    public void run()
    {
       // WIZ.FR.COM.DOBJ dobj = null;
        
        try
        {
        	
        	String Path = "";
        	String param ="";
        	String command ="";
        	String FileNM = "";
        	
        	 WIZ.FR.COM.VOBJ dvobj = indobj.getRetObject("lock_file");
        	 String path = dvobj.getRecord().get("PATH");
        	 String filename = dvobj.getRecord().get("FILENAME");
             boolean ExistFile = false;
             ExistFile = isExist(path,filename) ;
      
             // System.out.println( System.getProperty("os.name") + " OS NAME ckeck");
            	if(ExistFile){
            		
            		 //System.out.println(" Lock File Exist.... Stationary current operations. ");\
            		System.err.println("Lock File Exist.... Stationary current operations.");
            	
            		
            	}else{
            		
            		  param = indobj.getRetObject("batch").getRecord().get("D_TYPE");
                     /* if(indobj.getRetObject("batch").getRecord().get("PATH").equals("")){
                      	Path ="/batch";
                      }else{
                      	Path = indobj.getRetObject("batch").getRecord().get("PATH");
                      }*/
            		  Path = EgovProperties.getProperty("Globals.batchPath").toString();
            		  
            		  
            		  if (Path != null && Path.length() > 0 && !Path.substring(Path.length() - 1).equals("/")) {
                      	Path += "/";
              		  }
            		  
            		  
            		  
                      FileNM =indobj.getRetObject("batch").getRecord().get("FILE_NAME");
                      
                      String osName = System.getProperty("os.name");
                      
                      //윈도우일 경우
                      if (osName.indexOf("Windows") > -1) {
                    	  FileNM = FileNM+".bat";
                      }else{
                    	  FileNM = FileNM+".sh";  
                      }
                     
                      command =  Path + "/"+ FileNM  + " " + param; // + " > TTTEEESSSTTTT_.txt";
                      System.out.println(command);
                     //  dobj.getRetObject("S4").getRecord().get("FROM")  /batch/
                      // /batch/
                 
                      execute(command); // cmd & shell 실행
                      System.out.println( "batch Running................ ");
            		
            		
            	}
               
             
        	
          
           /* for(int i=0;i<10;i++)
            {
                Long duration = (long) (Math.random() * 10);
        
                System.out.println("EXECUTE:RunningTask: " + duration);
                TimeUnit.SECONDS.sleep(duration);
             
            }*/
        }
        finally
        {
        	
        
        //BatchFlag_HP1C001T FlagUpdate = new BatchFlag_HP1C001T();
        //	FlagUpdate.UI_BatchFlag(dobj);
        	
        }
    }
    
 
	public boolean isExist(String path, String FileNm) {
		boolean isFile = false;
		if (path != null && path.length() > 0 && !path.substring(path.length() - 1).equals("/")) {
			path += "/";
		}
		try{
			
			File Files = new File(path + FileNm);
			if (Files.isFile())isFile = true;
			
		 } catch (Exception e) {
			    e.printStackTrace();
		 } 
		
		return isFile;
	}
    
    public String execute(String command) {
        StringBuffer output = new StringBuffer();
        Process process = null;
        BufferedReader bufferReader = null;
        Runtime runtime = Runtime.getRuntime();
        String osName = System.getProperty("os.name");
        
        //윈도우일 경우
        if (osName.indexOf("Windows") > -1) {
         //   command = "cmd /c " + command;
        	//command = "C:\tmp\logs";
        }
 
        try {
            process = runtime.exec(command);
           
            // shell 실행이 정상 동작
            bufferReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String msg = null;
           
            while((msg=bufferReader.readLine()) != null) {
                output.append(msg + System.getProperty("line.separator"));
               //System.out.println( "## " +msg);
            }
            bufferReader.close();
 
            // shell 실행시 에러가 발생
            bufferReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            while((msg=bufferReader.readLine()) != null) {
                output.append(msg + System.getProperty("line.separator"));
            }
        } catch (IOException e) {
            output.append("IOException : " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                process.destroy();
                if(bufferReader != null) bufferReader.close();
            } catch(IOException e1) {
                e1.printStackTrace();
            }
        }
 
        return output.toString();
    }
    
}
       
 