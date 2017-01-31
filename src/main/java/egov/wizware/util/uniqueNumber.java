package egov.wizware.util;

import java.util.Date;
import java.text.SimpleDateFormat;

public class uniqueNumber {
    public uniqueNumber() {
    }

    public synchronized String getNumber17(){
       return GetCurrentDate17();
    }
    public synchronized String getNumber20(){
        GetCurrentDate20();
        return GetCurrentDate17()+"000";
    }


    private  String GetCurrentDate17()
    {
        Date _524 = new Date();
        SimpleDateFormat _yyyyMMddE = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String _80;
        _80 = _yyyyMMddE.format(_524);
        return _80;
    }
    private  String GetCurrentDate20()
    {
        Date _524 = new Date();
        SimpleDateFormat _yyyyMMddE = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String _80;
        _80 = _yyyyMMddE.format(_524);
        _80 += getUnikey3();
        return _80;
    }
    private int getUnikey3(){
        int  useridno =123;
        int unikey = (int)(Math.random() * useridno);
        System.out.println("UNIKEY KEY:"+unikey +"");
        return unikey;
    }

}
