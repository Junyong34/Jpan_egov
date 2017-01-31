package hpms.UserObject.BatchExe;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import egov.wizware.com.*;
import egov.wizware.util.CommUtil;
import egov.wizware.ria.EgovProperties;
import WIZ.FR.COM.*;
import WIZ.FR.UTIL.*;

public class WorkExcel {
	
	 private WIZ.FR.COM.DOBJ Wiz_indobj = null;
	 private egov.wizware.com.DOBJ egov_indobj = null;
	    
    public WorkExcel() {
    }
   
   
    public void setIndobj_egov(egov.wizware.com.DOBJ dobj)
    {
    	egov_indobj = dobj;
    }
    
    public void setIndobj_Wiz(WIZ.FR.COM.DOBJ dobj)
    {
    	Wiz_indobj= dobj;
    }
    public egov.wizware.com.DOBJ DataCheck(egov.wizware.com.DOBJ indobj)
    {
    	 WIZ.FR.COM.DOBJ  Wiz_dobj = null;
    	 egov.wizware.com.DOBJ egov_dobj=null; 
    	
    	System.out.println( " ======================      STRAT       ========================== ");
      try{
    	  
    	  /****************** WIZFRAME 캐스팅 처리하여 PEX 호출 **********************/
    	  setIndobj_egov(indobj);   
    	  Wiz_dobj = getDOBJCasting();   // Egov를 Wizframe으로 캐스팅
    	  
    	  WIZ.FR.COM.VOBJ _vobj = new WIZ.FR.COM.VOBJ();
    	  HashMap record = new HashMap();
    	  record.put("DBURL", EgovProperties.getProperty("Globals.Url").toString());
          record.put("DBID", EgovProperties.getProperty("Globals.UserName").toString());
          record.put("DBPWD", EgovProperties.getProperty("Globals.Password").toString());
          _vobj.addRecord(record);      
          Wiz_dobj.setRetObject("S",_vobj); 
          
    	  System.out.println( " ======================     egov------ > WIZFRAME 111111     ========================== ");
    	  WIZ.FR.UTIL.CommUtil cu = new WIZ.FR.UTIL.CommUtil(Wiz_dobj);
          HashMap   classinfo = new HashMap();
          classinfo.put("OBJECTID", "WIZFRAME_Plan" );
          classinfo.put("PACKAGE", "hpms.base" );
          classinfo.put("CLASS", "WorkDataInputWizFrame" );
          classinfo.put("METHOD", "WizFrameWorkingExcel" );
          Wiz_dobj = cu.callPSExternal(Wiz_dobj, null, classinfo );
          
          
          /**************************           끝                **************************/ 
          
        //  System.out.println( " ======================     WIZFRAME ---- > EGOV   222222  ========================== ");
          
          /************************** egov로 다시 셋팅 처리 해서 리턴 **************************/
          setIndobj_Wiz(Wiz_dobj);
          egov_dobj = getDOBJCastin2();  //  Wizframe  Egov를 으로 캐스팅
         
          egov.wizware.com.VOBJ dvobj = egov_dobj.getRetObject("LOG");
           dvobj.setName("LOG");
          indobj.setRetObject(dvobj);
         // System.out.println(indobj.getRetObject("LOG").getRecord().get("LOG_SEQ") + " @@@@@@@@@22"  + indobj.getRetObject("LOG").getRecord().get("CODE"));
     
           
          /**************************           끝                **************************/ 
           
           
           
       }  catch (Exception e) {
       
           e.printStackTrace();
       }
       return indobj;
    }
    
    //================================= 추가 부분   ===============================
    private WIZ.FR.COM.DOBJ getDOBJCasting()                   //egovframe DOBJ을 Wizware frame DOBJ로 입력 data변경 처리
    {
        WIZ.FR.COM.DOBJ rdobj = new WIZ.FR.COM.DOBJ();
        WIZ.FR.COM.VOBJ _vobj = new WIZ.FR.COM.VOBJ();
        WIZ.FR.COM.VOBJ _vobj2 = new WIZ.FR.COM.VOBJ();
        HashMap rec  = egov_indobj.getRetObject("S1").getRecordMap();    
        HashMap rec1  = egov_indobj.getRetObject("G").getRecordMap();    
        _vobj.addRecord(rec);     
        _vobj2.addRecord(rec1);  
        rdobj.setRetObject("G",_vobj2); 
        rdobj.setRetObject("S1",_vobj);                         
     
        return rdobj; 
    }
    private  egov.wizware.com.DOBJ getDOBJCastin2()                  
    {
    	 egov.wizware.com.DOBJ rdobj = new  egov.wizware.com.DOBJ();
    	 egov.wizware.com.VOBJ _vobj = new  egov.wizware.com.VOBJ();
    
        HashMap rec  = Wiz_indobj.getRetObject("CVT25").getRecordMap();    
        
       //  System.out.println( rec.get("LOG_SEQ")  + " @@ " + rec.get("CODE")  + " @@ " + rec.toString());
          
        _vobj.addRecord(rec);        
        rdobj.setRetObject("LOG",_vobj);      // 시퀀스 번호 리턴 받는다.                       
        
        return rdobj;
    }
    //============================================================================

}
