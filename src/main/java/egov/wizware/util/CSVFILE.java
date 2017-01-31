package egov.wizware.util;
import  egov.wizware.com.*;
import  java.util.*;
import  java.io.*;
public class CSVFILE {
    public CSVFILE() {
    }

    public String createCSVSM(DOBJ dobj, String objname, String loc, String fname, String ordercols) throws Exception{

        loc = loc.trim();
        if(!loc.substring(loc.length()-1).equals("/"))loc += "/";

        FileOutputStream ostream = new FileOutputStream(loc +  fname);
        DataOutputStream dstream = new DataOutputStream(ostream);
        String wstring = "";
        try{
            VOBJ vobj = dobj.getRetObject(objname);
            vobj.Println("yyyyyyyyy");

            String _tmp = "";
            vobj.first();
            while(vobj.next()){
                _tmp  =  vobj.getRecord().get(ordercols);
                char[] _tochar = _tmp.toCharArray();
                for(int x=0;x<_tmp.length();x++){
                    if(_tochar[x] == '\n'){
                        System.out.print("newline find");
                    }else if(_tochar[x] == '\r'){
                        System.out.print("cariage find");
                    }else {
                        System.out.print(_tochar[x]);
                    }
                }
                wstring ="";
                wstring +=  vobj.getRecord().get(ordercols);
                wstring +=  "\n";
                dstream.write(wstring.getBytes());
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception("FILE WRITE ERROR");
        }finally{
            dstream.close();
            ostream.close();
        }
        return "1";
    }


    public String createCSVPS(DOBJ dobj, String objname, String loc, String fname, String ordercols, String numbercols) throws Exception{

        ArrayList columns = getColumnOrder(ordercols);
        HashMap   nmap= getNumbercols(numbercols);

        System.out.println(columns);
        System.out.println(nmap);

        loc = loc.trim();
        if(!loc.substring(loc.length()-1).equals("/"))loc += "/";

        //************   파일생성 1step
         System.out.println("FILE CREATE STEP 1");
        FileOutputStream ostream = new FileOutputStream(loc +  fname);
        DataOutputStream dstream = new DataOutputStream(ostream);

        String wstring = "";
        boolean first = true;
        String  delim ="";
        try{
            VOBJ vobj = dobj.getRetObject(objname);
            vobj.first();
            while(vobj.next()){
                wstring ="";
                delim ="";
                first = true;
                for(int i=0;i<columns.size();i++){

                    if(nmap.containsKey(columns.get(i).toString())){
                        wstring += delim + getNumberstring(vobj.getRecord().get(columns.get(i).toString()), Integer.parseInt(nmap.get(columns.get(i).toString()).toString()) );
                    }else {
                        wstring += delim + vobj.getRecord().get(columns.get(i).toString());
                    }

                    if(first == true){
                        first =false;
                        delim=",";
                    }
                }
                wstring+="\r\n";
                dstream.write(wstring.getBytes());
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception("FILE WRITE ERROR");
        }finally{
            dstream.close();
            ostream.close();
        }

        //******************  파일생성 2step
         System.out.println("FILE CREATE STEP 2");
         String YYYY=fname.substring(6,10);
         String MM=fname.substring(10,12);
         String DD=fname.substring(12,14);
         String hh=fname.substring(14,16);
         String mm=fname.substring(16,18);

        File fp =  new File(loc +  fname);
        long datafile_1349 = fp.length();
        fp.delete();

        VOBJ vobj = dobj.getRetObject(objname);

        datafile_1349 = datafile_1349 + this.getHeadInfo_ASize(fname);
        int datafile_2561 = vobj.getRecordCnt();
        String datafile_4373string = this.getHeadInfoA(fname, datafile_1349,  datafile_2561, YYYY, MM, DD, hh, mm);

        ostream = new FileOutputStream(loc +  fname);
        dstream = new DataOutputStream(ostream);
        dstream.write(datafile_4373string.getBytes());

        wstring = "";
        first = true;
        delim ="";
        try{

            vobj.first();
            while(vobj.next()){
                wstring ="";
                delim ="";
                first = true;
                for(int i=0;i<columns.size();i++){

                    if(nmap.containsKey(columns.get(i).toString())){
                        wstring += delim + getNumberstring(vobj.getRecord().get(columns.get(i).toString()), Integer.parseInt(nmap.get(columns.get(i).toString()).toString()) );
                    }else {
                        wstring += delim + vobj.getRecord().get(columns.get(i).toString());
                    }

                    if(first == true){
                        first =false;
                        delim=",";
                    }
                }
                System.out.println("new line 추가");
                wstring+="\r\n";
                dstream.write(wstring.getBytes());
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception("FILE WRITE ERROR");
        }finally{
            dstream.close();
            ostream.close();
        }

        System.out.println("FILE CREATE STEP 3:" + loc + this.getHDSFilename(fname) );
        int headfile_1349 = this.getHeadInfo_BSize(fname);
        ostream = new FileOutputStream(loc + this.getHDSFilename(fname) );
        dstream = new DataOutputStream(ostream);
        try{
            String ww = this.getHeadInfoB(fname, datafile_1349, headfile_1349, datafile_2561, YYYY,MM,DD,hh,mm);
            System.out.println(ww);
            dstream.write(ww.getBytes());
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception("FILE WRITE ERROR:FILE CREATE STEP 3");
        }finally{
            dstream.close();
            ostream.close();
        }

        return "1";
    }

   private String getHeadInfoA(String _5963, long size, int recordcnt,String YYYY, String MM, String DD, String hh, String mm){
       String rtn ="";
       rtn += "1,";               //고정값
       rtn += _5963;          //filename;
       rtn += "               ,";  // 공백 15자리
       rtn += this.getFormat(size+"", 12) +",";     //12자리 파일사이즈   //rtn += "123456789012,";                 //12자리 파일사이즈
       rtn += YYYY +"/"+ MM +"/" + DD +",";    //rtn += "2009/08/01,";     //10자리 일자
       rtn += _5963.substring(0,6) +",";   //rtn += "20008";           //점코드
       rtn += YYYY +"/"+ MM +"/" + DD +",";    //rtn += "2009/08/01,";     //10자리 일자
       rtn += hh + ":" + mm + ",";          // 5자리 시분  rtn += "18:01,";          // 5자리 시분

       rtn += this.getFormat(recordcnt+"", 7)+ ",";  // 7자리 record 건수   rtn += "1234567";                // 7자리 record 건수
       rtn += "1,";              // 1자리 고정잘
       rtn += "                                  "; //34자리 공백
       rtn += "\r\n"; //34자리 공백
       return rtn;
   }
    private String getHeadInfoB(String _5963, long datafilesize,  int size, int recordcnt, String YYYY, String MM, String DD, String hh, String mm){
        String rtn ="";
        //첫번째 LINE
        rtn += this.getFormat(size+"", 5) +",";      // 5자리 파일사이즈  rtn += "12345,";               // 5자리 파일사이즈
        rtn += "1,";          //filename;
        rtn += getHDSFilename(_5963) ;          //filename + HDS;
        rtn += "               ,";  // 공백 15자리

        rtn += this.getFormat(datafilesize+"", 12) + ","; // rtn += "123456789012,";                 //12자리 파일사이즈

        rtn += YYYY +"/"+ MM +"/" + DD +",";    //rtn += "2009/08/01,";     //10자리 일자
        rtn += hh + ":" + mm ;          // 5자리 시분  rtn += "18:01,";          // 5자리 시분
        rtn += "\r\n";                         // 5자리 시분

        //두번째 LINE
        rtn += _5963;          //filename ;
        rtn += "               ,";  // 공백 15자리
        rtn += "000,";                // 3자리 고정
        rtn += _5963.substring(0,6);   //rtn += "200008";           //점코드
        rtn += "    ,";            //공백 4자리
        rtn += YYYY + MM + DD +hh +mm+",";   //rtn += "200901121201,";   //일자시분
        rtn += YYYY +"/"+ MM +"/" + DD +",";    //rtn += "2009/08/01,";     //10자리 일자
        rtn += hh + ":" + mm + ",";          // 5자리 시분  rtn += "18:01";           // 5자리 시분

        rtn += this.getFormat(datafilesize+"", 12) + ",";   // rtn += "123456789012,";     //12자리 파일사이즈
        rtn += "1";               // 1자리 고정잘
        rtn += "\r\n";                         // 5자리 시분
        return rtn;
    }


    private int getHeadInfo_ASize(String _5963){
         String rtn ="";
         rtn += "1,";               //고정값
         rtn += _5963;          //filename;
         rtn += "               ,";  // 공백 15자리
         rtn += "123456789012,";                 //12자리 파일사이즈
         rtn += "2009/08/01,";     //10자리 일자
         rtn += "200006,";          // 점코드 5자리
         rtn += "2009/08/01,";     //10자리 일자
         rtn += "18:01,";          // 5자리 시분

         rtn += "1234567,";                // 7자리 record 건수
         rtn += "1,";              // 1자리 고정잘
        rtn += "                                  "; //34자리 공백
        rtn += "\r\n"; //34자리 공백
        return rtn.length();
    }
    private int getHeadInfo_BSize(String _5963){
        String rtn ="";
        //첫번째 LINE
        rtn += "12345,";               //파일사이즈
        rtn += "1,";          //filename;
        rtn += _5963;          //filename + HDS;
        rtn += "               ,";  // 공백 15자리
        rtn += "123456789012,";                 //12자리 파일사이즈
        rtn += "2009/08/01,";     //10자리 일자
        rtn += "18:01";          // 5자리 시분
        rtn += "\r\n";          // 5자리 시분

        //두번째 LINE
        rtn += _5963;          //filename ;
        rtn += "               ,";  // 공백 15자리
        rtn += "000,";                // 3자리 고정
        rtn += "200008";           //점코드
        rtn += "    ,";           //공백 4자리
        rtn += "200901121201,";           //일자시분
        rtn += "2009/08/01,";     //10자리 일자
        rtn += "18:01,";          // 5자리 시분
        rtn += "123456789012,";                 //12자리 파일사이즈
        rtn += "1";              // 1자리 고정잘
        rtn += "\r\n";          // 5자리 시분
        return rtn.length();
    }

    private String getFormat(String size, int formatlen){
        String rtn ="";
        char[] charsize = size.trim().toCharArray();
        int  spacecnt = formatlen - charsize.length;
        for(int i=0;i<spacecnt;i++){
            rtn += "0";
        }
        rtn += size.trim();
        return rtn;
    }

    private String getHDSFilename(String filename){
       return filename.substring(0,filename.indexOf(".")) +".HDS";
   }

    private String getNumberstring(String value, int len){
        String rtn ="";
        for(int i=0;i<len-value.length();i++){
            rtn += "0";
        }
        rtn += value;
        return rtn;
    }

    private HashMap getNumbercols(String cols){
        HashMap cmap = new HashMap();
        StringTokenizer stoken = new StringTokenizer(cols, " ");
        String tmpstr ="";
        String name ="";
        String length="";
        while(stoken.hasMoreTokens()){
            tmpstr = stoken.nextToken();
            if(tmpstr.indexOf(":") == -1) {
                name = tmpstr.trim();
                cmap.put(name, "0");
            }else {
                name = tmpstr.substring(0,tmpstr.indexOf(":")).trim();
                name = LTrim(name);
                length = tmpstr.substring(tmpstr.indexOf(":") + 1).trim();
                length = LTrim(length);
                cmap.put(name, length);
            }
        }
        return cmap;
    }
    private ArrayList getColumnOrder(String cols){
        ArrayList columns = new ArrayList();
        String name ="";
        StringTokenizer stoken = new StringTokenizer(cols, " ");
        while(stoken.hasMoreTokens()){
            name = stoken.nextToken().trim();
            name = LTrim(name);
            if(!name.equals(""))  columns.add(name);
        }
        return columns;
    }
    private String LTrim(String _1073 )  {
        String _2366 ="";
        char[] _7172 = _1073.toCharArray();
        int i=0;
        for(i=0;i<_7172.length;i++){
            if(_7172[i] != ' '){
                break;
            }
        }
        for(int ii=i;ii<_7172.length;ii++){
            _2366 = _2366 + _7172[ii];
        }

        return _2366;
    }


}
