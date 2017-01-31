package egov.wizware.com;

import java.util.Date;
import java.util.Calendar;

public class TimeInterval {
    private String Starttime="";
    private String Endtime ="";
    Calendar startDate = null;
    Calendar endDate = null;

    public TimeInterval() {
        startDate= Calendar.getInstance();
    }
    public void setStartTime(){
        startDate= Calendar.getInstance();
    }
    public void setEndTime(){
        endDate = Calendar.getInstance();
    }
    public int getIntervalDay(){
        Date sdate = startDate.getTime();
        Date edate = endDate.getTime();
        int intDiff = Math.round(((sdate.getTime()-edate.getTime())/(1000*60*60*24))); //계산결과를 일로 바꿈
        return intDiff;
    }
    public long getIntervalSS(){
        Date sdate = startDate.getTime();
        Date edate = endDate.getTime();
         int intDiff = Math.round(((sdate.getTime()-edate.getTime())/(1000))); //계산결과를 일로 바꿈
        return intDiff;
    }
    public long getInterval(){
        Date sdate = startDate.getTime();
        Date edate = endDate.getTime();
        long intDiff = Math.round(sdate.getTime()-edate.getTime()); //계산결과를 일로 바꿈
        return intDiff;
    }
    private  int getDayDiff(String _1025, String _1043 ) throws Exception{
       startDate.set(Integer.parseInt(_1025.substring(0, 4))
                      , Integer.parseInt(_1025.substring(4, 6)) - 1
                      , Integer.parseInt(_1025.substring(6, 8)));
       endDate.set(Integer.parseInt(_1043.substring(0, 4))
                    , Integer.parseInt(_1043.substring(4, 6)) - 1
                    , Integer.parseInt(_1043.substring(6, 8)));
       Date sdate = startDate.getTime();
       Date edate = endDate.getTime();
       int intDiff = Math.round(((edate.getTime()-sdate.getTime())/(1000*60*60*24))); //계산결과를 일로 바꿈
       return intDiff;
   }

}
