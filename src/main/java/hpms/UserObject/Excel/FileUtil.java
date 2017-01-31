package hpms.UserObject.Excel;
import java.io.*;
import java.util.*;
import java.text.*;
public class FileUtil
{
    public FileUtil()
    {
    }

    public void renameTo(File source, String flag)
    {
        File targetFile = new File("D:/temp1/TEST1.TXT");
        getUniFilename("D:/temp1", "TEST1.TXT");
        try
        {
            source.renameTo(targetFile);
            System.out.println("end");
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
   public String getSYSDATE(){
	   java.text.SimpleDateFormat formatter2 = new java.text.SimpleDateFormat ("yyyy-MM-dd HH.mm.ss", java.util.Locale.JAPAN);
	    String dateString = formatter2.format(new java.util.Date());
	    
	    return dateString;
   
   }
    public String getUpfilename(String filename, String USER_ID)
    {
        String fname ="";  //_#
        String extname ="";
        
        int idx = 0;
        if( ( idx = filename.lastIndexOf("."))== -1)
        {
            fname = filename;
        }
        else
        {
            extname = filename.substring(idx);
            fname = filename.substring(0, idx);
        }

        java.text.SimpleDateFormat formatter2 = new java.text.SimpleDateFormat ("yyyy-MM-dd HH.mm.ss.SSS", java.util.Locale.JAPAN);
        String dateString = formatter2.format(new java.util.Date());
         fname = fname +"_"+USER_ID+"_"+dateString + extname;
      // System.out.println(fname + " 999999999999999999");
       /* String seq="";
        if( ( idx = fname.lastIndexOf("_#"))== -1)
        {
            fname = fname + extname;
        }
        else
        {
            seq = fname.substring(idx+2);
            if(isNumber(seq))
            {
                fname = fname.substring(0, idx);
                fname = fname + extname;
            }
            else
            {
                fname = fname + extname;
            }
        }*/
        //System.out.println("========>" + fname +":"  +":" + extname);
        return fname;
    }
    public String getUpfilename(String filename)
    {
        String fname ="";  //_#
        String extname ="";
        
        int idx = 0;
        if( ( idx = filename.lastIndexOf("."))== -1)
        {
            fname = filename;
        }
        else
        {
            extname = filename.substring(idx);
            fname = filename.substring(0, idx);
        }

      
       
        String seq="";
        if( ( idx = fname.lastIndexOf("_#"))== -1)
        {
            fname = fname + extname;
        }
        else
        {
            seq = fname.substring(idx+2);
            if(isNumber(seq))
            {
                fname = fname.substring(0, idx);
                fname = fname + extname;
            }
            else
            {
                fname = fname + extname;
            }
        }
      //  System.out.println("========>" + fname +":"  +":" + extname);
        return fname;
    }

    private boolean isNumber(String seq)
    {
        if(seq == null) return false;
        if(seq.trim().equals("")) return false;
        try
        {
            Integer.parseInt(seq);
        }catch(Exception ex)
        {
            return false;
        }
        return true;
    }
    public  String getUniFilename(String path, String filename)
    {
       String extname ="" ;
       String fname ="";
       int idx = 0;
       if( ( idx = filename.lastIndexOf("."))== -1)
       {
           fname = filename;
       }
       else
       {
            extname = filename.substring(idx);
            fname = filename.substring(0, idx);
       }


       makeDir(path);
       int seq =1;
       File _fp = null;
       while(true)
       {
           _fp = new File(path +"/" + fname + extname);

            System.out.println(_fp.exists() +"[========]" + path +"/" + fname + extname);

           if(_fp.exists())
           {
               fname = fname + "_" + seq;
               seq++;
               continue;
           }
           break;
       }
       return fname + extname;
   }
   private  boolean makeDir(String path)
   {
       boolean rtn = false;
       if(!dirExist(path)){
           File fp = new File(path);
           fp.mkdirs();
       }
       return rtn;
   }
   private  boolean dirExist(String path)
   {
       boolean rtn = false;
       File fp = new File(path);
       if(fp.isDirectory()) rtn = true;
       return rtn;
   }


   //-------------------------------------------------------------------------------------------
   //------------------------- upload Fold file 삭제 기능 ---------------------------------------
   //-------------------------------------------------------------------------------------------
   public int delteOldFile(String path, int daycnt)
   {
       int rtn =0 ;
       File dir = new File(path);
       if(dir.isDirectory() == false) rtn = 0;
       File[] files = dir.listFiles();

       for(int i=0;i<files.length;i++)
       {
           if(files[i].isDirectory()) continue;
           if(isDeletefile(files[i], daycnt))
           {
               files[i].delete();
           }
       }
       return rtn;
   }
   private boolean isDeletefile(File fp,int datcnt)
   {
       Date dt = new Date();
       dt.setTime(fp.lastModified());
       SimpleDateFormat _yyyyMMddE = new SimpleDateFormat("yyyyMMdd");
       int    modfied =  Integer.parseInt(_yyyyMMddE.format(dt));
       int    calcdate = getComputeDate(3, datcnt);
       if(modfied >= calcdate) return false;
       return true;
   }
   public  String GetCurrentDate()
   {
       Date _dt = new Date();

       SimpleDateFormat _yyyyMMddE = new SimpleDateFormat("yyyyMMdd");
       String yyyymdd;
       yyyymdd = _yyyyMMddE.format(_dt);
       return yyyymdd;
    }
    public  int getComputeDate(int _flag, int daycnt)
    {
        String _yyyymmdd = GetCurrentDate();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(_yyyymmdd.substring(0,4)),Integer.parseInt(_yyyymmdd.substring(4,6))-1,Integer.parseInt(_yyyymmdd.substring(6,8)));
        if (_flag ==1) calendar.add(calendar.YEAR,daycnt);
        else if (_flag ==2) calendar.add(calendar.MONTH,daycnt);
        else if (_flag ==2) calendar.add(calendar.DATE, daycnt);

        Date _dt =  calendar.getTime();
        SimpleDateFormat yyyyMMddE = new SimpleDateFormat("yyyyMMdd");
        String _rtn;
        _rtn = yyyyMMddE.format(_dt);

        return Integer.parseInt(_rtn);
    }
   //-----------------------------------------------------------------------------------------------------------------

}
