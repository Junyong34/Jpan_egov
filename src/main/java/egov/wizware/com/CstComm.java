package egov.wizware.com;

import java.util.*;
import java.text.SimpleDateFormat;
import egov.wizware.com.*;
import java.sql.*;

public class CstComm {
    public CstComm() {
    }

    public int getItemSeq(VOBJ vobj, String itemcd){
        int rtn =-1;
        vobj.first();
        while(vobj.next()){
            if(vobj.getRecord().get("I_ITEMCD").equals(itemcd)){
                rtn = vobj.current;
                break;
            }
        }
        return rtn;
    }

    //cvobj : 고객일일주문
    public VOBJ getItemcdPrt(VOBJ cvobj, VOBJ ivobj){
        VOBJ rtn =new VOBJ();
        HashMap rec = null;
        String firstcd="";
        String  nextcd="";
        boolean find =false;
        int x=0;
        int y=0;
        cvobj.first();
        while(cvobj.next()){
            rec = cvobj.cpRecord();
            y=-1;
            if(cvobj.current==0) {
                firstcd = cvobj.getRecord().get("I_CUSTOMERCD");
            }
            nextcd = cvobj.getRecord().get("I_CUSTOMERCD");
            if(!firstcd.equals(nextcd)){
                firstcd = nextcd;
                x++;
            }
            find =false;
            ivobj.first();
            while(ivobj.next()){
                if(ivobj.getRecord().get("I_ITEMCD").equals(cvobj.getRecord().get("I_ITEMCD"))){
                    y=ivobj.current;
                    find = true;
                    break;
                }
            }
            rec.put("X",x+"");
            rec.put("Y",y+"");
            rtn.addRecord(rec);
        }
        rtn.setRetcode(x);

        rtn.Println("ITEMCD X-Y SET");
        return rtn;
    }

    public HashMap getRecord(VOBJ vobj, int x, int y){
        HashMap rec = null;
        vobj.first();
        while(vobj.next()){
            if(vobj.getRecord().getInt("X") == x  && vobj.getRecord().getInt("Y") ==y ){
                rec = vobj.cpRecord();
                break;
            }
            if(vobj.getRecord().getInt("X") > x) {
                break;
            }
        }
        return rec;
    }
    //
    public HashMap sumItem(int y, HashMap rec, HashMap sum){
        if(sum.get(y+"") == null) {
            if(rec == null){
                sum.put(y+"", 0+"");
            }else {
                sum.put(y+"", rec.get("Q_ORDERQTY"));
            }
        }else {
            if(rec != null) {
                int sm =  Integer.parseInt(sum.get(y+"").toString());
                sm +=  Integer.parseInt(rec.get("Q_ORDERQTY").toString());
                sum.put(y+"", sm+"");
            }
        }
        return sum;
    }
    public String[] getCustomcd(VOBJ vobj, int x){
        String[] customcd ={"",""};

        vobj.first();
        while(vobj.next()){
          if(vobj.getRecord().getInt("X")==x ) {
              customcd[1] = vobj.getRecord().get("N_CUSTOMERNM");
              customcd[0] = vobj.getRecord().get("I_CUSTOMERCD");
              break;
          }
       }
        return customcd;
    }

    //월주문화면에서 주문DATA를 출력하기 위하여 사용함.
    public  VOBJ getMonOrderCalendar(VOBJ vobj, String yyyymm){
        ViewUtil vu = new ViewUtil();
        int lastdd = 0;
        VOBJ rvobj = new VOBJ();
        try{
            lastdd = vu.getLastday(yyyymm);
            if(vobj.getRecordCnt()==0 || vobj == null ||  yyyymm ==null || yyyymm.equals("") ){
                rvobj.setRetcode(lastdd);
                return rvobj;
            }

            String  f_custcd="",f_itemcd="";
            String  n_custcd="",n_itemcd="";
            ArrayList tlist = new ArrayList();

            vobj.first();
            while (vobj.next()) {
                if (vobj.current == 0) {
                    f_custcd = vobj.getRecord().get("I_CUSTOMERCD");
                    f_itemcd = vobj.getRecord().get("I_ITEMCD");
                    n_custcd = vobj.getRecord().get("I_CUSTOMERCD");
                    n_itemcd = vobj.getRecord().get("I_ITEMCD");
                }
                n_custcd = vobj.getRecord().get("I_CUSTOMERCD");
                n_itemcd = vobj.getRecord().get("I_ITEMCD");
                if (!f_custcd.equals(n_custcd) || !f_itemcd.equals(n_itemcd)) {
                    f_custcd = n_custcd;
                    f_itemcd = n_itemcd;
                    rvobj =setMonOrder( rvobj, tlist, lastdd);
                    tlist = new ArrayList();
                    tlist.add(vobj.cpRecord());
                } else {
                    tlist.add(vobj.cpRecord());
                }
            }
            rvobj =setMonOrder( rvobj, tlist, lastdd);
        }catch(Exception e){
            e.printStackTrace();
        }
        //rvobj = new VOBJ();
        //rvobj.setRecords(records);
        rvobj.setRetcode(lastdd);

        //rvobj.Println("getMonOrderCalendar");
        return rvobj;
    }
    private VOBJ setMonOrder(VOBJ rvobj, ArrayList tlist, int ldd){
        ArrayList rtn = new ArrayList();
        HashMap fmap =null ;
        HashMap smap =null ;
        HashMap rec = null;
        String sdd="";

        String span="";
        int sumcnt=0;
        long   sumamt=0;
        String tmpsu = "";

        for(int i=1;i<=ldd;i++){
            if(i==1) {
                fmap = (HashMap)tlist.get(0);
            }
            if(i < 10) sdd ="0"+i;
            else       sdd ="" +i;
            if((rec = getDDFind(tlist, sdd)) != null){
               // System.out.println(rec);
               if(rec.get("Q_ORDERQTY")!=null && !rec.get("Q_ORDERQTY").toString().equals("") && !rec.get("Q_ORDERQTY").toString().equals("0")){
                   sumcnt =sumcnt + Integer.parseInt(rec.get("Q_ORDERQTY").toString());
                   if( rec.get("W_CUSTPRICE")!=null &&
                      !rec.get("W_CUSTPRICE").toString().equals("") &&
                      !rec.get("W_CUSTPRICE").toString().equals("0"))
                   {
                       sumamt = sumamt +( Integer.parseInt(rec.get("W_CUSTPRICE").toString()) * Integer.parseInt(rec.get("Q_ORDERQTY").toString()) ) ;
                   }
               }
               rec.put("Q_ORDERQTY_SUM",sumcnt+"");
               rec.put("W_CUSTPRICE_SUM",sumamt+"");
               rvobj.addRecord(rec);
            }else {
                smap = new HashMap();
                smap.put("D_DD",sdd);
                smap.put("Q_ORDERQTY","");
                smap.put("I_CUSTOMERCD",fmap.get("I_CUSTOMERCD"));
                smap.put("I_ITEMCD",fmap.get("I_ITEMCD"));
                smap.put("N_DLVBASICADDR",fmap.get("N_DLVBASICADDR"));
                smap.put("I_ITEMCLSCD",fmap.get("I_ITEMCLSCD"));
                smap.put("W_CUSTPRICE",fmap.get("W_CUSTPRICE"));
                smap.put("N_ITEMNM",fmap.get("N_ITEMNM"));
                smap.put("N_WELLD",fmap.get("N_WELLD"));
                smap.put("N_CUSTOMERNM",fmap.get("N_CUSTOMERNM"));
                smap.put("N_DLVDETAILADDR",fmap.get("N_DLVDETAILADDR"));
                smap.put("Q_ORDERQTY_SUM",sumcnt+"");
                smap.put("W_CUSTPRICE_SUM",sumamt+"");
                rvobj.addRecord(smap);
            }
        }
        return rvobj;
    }
    private HashMap getDDFind(ArrayList tlist, String sdd){
        HashMap rec = null;
        for(int i=0;i<tlist.size();i++){
            rec = (HashMap)tlist.get(i);
            if(rec.get("D_DD").toString().equals(sdd)){
                break;
            }else {
                rec = null;
            }
        }
        return rec;
    }




}















