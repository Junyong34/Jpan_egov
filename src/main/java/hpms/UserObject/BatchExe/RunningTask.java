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

import egov.wizware.com.*;
import egov.wizware.ria.EgovProperties;
import WIZ.FR.COM.*;

public class RunningTask implements Runnable
{
    private egov.wizware.com.DOBJ indobj = null;
    public RunningTask()
    {
    }
    public void setIndobj(egov.wizware.com.DOBJ dobj)
    {
        indobj = dobj;
    }
    
    public void run()
    {
        WIZ.FR.COM.DOBJ dobj = null;
        
        try
        {
        	
        	String Path = "";
        	String param ="";
        	String command ="";
        	String FileNM = "";
            dobj = getDOBJCasting();    //Input data Casting 처리 부 DOBJ 
            //EgovProperties.getProperty("Globals.Url");
            param = indobj.getRetObject("batch").getRecord().get("D_TYPE");
            /*if(indobj.getRetObject("batch").getRecord().get("PATH").equals("")){
            	Path ="/batch";
            }else{
            	Path = indobj.getRetObject("batch").getRecord().get("PATH");
            
            }*/
           // System.out.println( System.getProperty("os.name") + " OS NAME ckeck");
            Path = EgovProperties.getProperty("Globals.batchPath").toString();
            if (Path != null && Path.length() > 0 && !Path.substring(Path.length() - 1).equals("/")) {
            	Path += "/";
    		}
       
            FileNM =EgovProperties.getProperty("Globals.batchFileNmae").toString(); //indobj.getRetObject("batch").getRecord().get("FILE_NAME");
           
            command =  Path + "/"+ FileNM  + " " + param; // + " > TTTEEESSSTTTT_.txt";
            
            execute(command); // cmd & shell 실행
            System.out.println( "batch Running................ ");
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
    
    //================================= 추가 부분   ===============================
    private WIZ.FR.COM.DOBJ getDOBJCasting()                   //egovframe DOBJ을 Wizware frame DOBJ로 입력 data변경 처리
    {
        WIZ.FR.COM.DOBJ rdobj = new WIZ.FR.COM.DOBJ();
        WIZ.FR.COM.VOBJ _vobj = new WIZ.FR.COM.VOBJ();
        WIZ.FR.COM.VOBJ _vobj2 = new WIZ.FR.COM.VOBJ();
        HashMap rec  = indobj.getRetObject("S1").getRecordMap();    //S1.DATA_TYPE (UI:Input Data)
        HashMap rec1 =  indobj.getRetObject("batch").getRecordMap(); 
        _vobj.addRecord(rec);        
        rdobj.setRetObject("S1",_vobj);                              //batch에서 사용될 dataset명으로 input
        _vobj2.addRecord(rec1);
        rdobj.setRetObject("batch",_vobj2); 
     
        return rdobj;
    }
    //============================================================================
    
    
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
              // System.out.println( "## " +msg);
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
       
 
