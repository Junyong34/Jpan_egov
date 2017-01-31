package hpms.UserObject.BatchExe;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import egov.wizware.com.DOBJ;
import egov.wizware.com.VOBJ;
import egov.wizware.ria.EgovProperties;

public class FileUtility {

	public FileUtility() {

	}

	public DOBJ isFile(DOBJ indobj) {
		
	
		  VOBJ dvobj = indobj.getRetObject("SEL10");
	      String path = dvobj.getRecord().get("PATH");
	      String filename = dvobj.getRecord().get("FILENAME");
          boolean ExistFile = false;
          ExistFile = isExist(path,filename) ;
	         
	      
	       VOBJ _rvobj = new VOBJ();
	       HashMap _rec = new HashMap();
	       _rec.put("ISEXIST_FILE",ExistFile);
	       _rvobj.addRecord(_rec);
	       _rvobj.setName("FN01");
	       indobj.setRetObject(_rvobj);

	       _rvobj.Println("File Exist Check");
	       indobj.dispRetObjectKeyNames();
	       
	       
		return indobj;
	}
	
	
	public DOBJ rootPath(DOBJ indobj) {
		
		String path = "";
		
		path = EgovProperties.getProperty("Globals.rootPath").toString();
		
		 VOBJ _rvobj = new VOBJ();
	       HashMap _rec = new HashMap();
	       _rec.put("ROOTPATH",path);
	       _rvobj.addRecord(_rec);
	       _rvobj.setName("FN02");
	       indobj.setRetObject(_rvobj);

	       _rvobj.Println("File Exist Check");
	       indobj.dispRetObjectKeyNames();
	       
		return indobj;
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
}
