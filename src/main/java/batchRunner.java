
import java.lang.reflect.Method;
import java.lang.reflect.*;
import java.sql.Connection;
import java.util.*;
import WIZ.FR.COM.VOBJ;
import WIZ.FR.COM.DOBJ;
import WIZ.FR.UTIL.WizUtil;
import egov.wizware.ria.EgovProperties;

public class batchRunner {
    public batchRunner() {
    }

    //batchRunner classname methodname VAR=value
    public static void main(String[] args) {
        batchRunner batchrunner = new batchRunner();
        //java batchRunner classnm methodnm argv
        String classname="";
        String methodname="";
        WizUtil wutil = new WizUtil();
        HashMap record = new HashMap();
        for(int i=0;i<args.length;i++)
        {
            if(i==0)
            {
                classname = args[0];
                continue;
            }

            if(i==1)
            {
                methodname=args[1];
                continue;
            }

            System.out.println(args[i].substring(0,args[i].indexOf("=")) +":" + args[i].substring(args[i].indexOf("=")+1) );
            String value = args[i].substring(args[i].indexOf("=")+1).trim();
            if(value.equals("beforeday"))
            {
                try
                {
                    value = wutil.getComputeDate(wutil.getCurrentDate(), 3, 1, 0)+"";
                    System.out.println("beforeday:" + value);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }

            }
            else if(value.equals("currentday"))
            {
                value = wutil.getCurrentDate()+"";
                System.out.println("currentday:" + value);

            }
            else if(value.equals("lastday")){
                 value = wutil.getLastdate(wutil.getCurrentDate()+"")+"";
                 System.out.println("lastday:" + value);

            }
            else if(value.equals("currentmonth"))
            {
                value = wutil.getLastdate(wutil.getCurrentDate()+"")+"";
                value = value.substring(0,6);
                System.out.println("currentmonth:" + value);
            }

            record.put(args[i].substring(0,args[i].indexOf("=")) , value );
        }
        
        // ORCLE ID PW URL   dvobj.getRecord().get("DBURL"), dvobj.getRecord().get("DBID"), dvobj.getRecord().get("DBPWD") );
     
        record.put("DBURL", EgovProperties.getProperty("Globals.Url").toString());
        record.put("DBID", EgovProperties.getProperty("Globals.UserName").toString());
        record.put("DBPWD", EgovProperties.getProperty("Globals.Password").toString());
        
        record.put("DBURLDWH", EgovProperties.getProperty("Globals.Url2").toString());
        record.put("DBIDDWH", EgovProperties.getProperty("Globals.UserName2").toString());
        record.put("DBPWDDWH", EgovProperties.getProperty("Globals.Password2").toString());

        batchrunner.execute(classname, methodname, record);
    }

    private void execute( String _530, String _4352, HashMap record) {

        DOBJ _98645 = new DOBJ();
        VOBJ _7403 = new VOBJ();
        
        String displaylog = EgovProperties.getProperty("Globals.batch_Log").toString();
        if(!displaylog.equals("true")){
        	
        	_98645.setDispmode(1);
        }
        
        _7403.setName("S");
        _7403.addRecord(record);
        _98645.setRetObject(_7403);
        _7403.Println("INPUT DATA");
        Class _821 = null;
        try
        {
            _821 = Class.forName( _530 );
        }
        catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }

        Object _170[] = new Object[1];
        Class[] _554 = new Class[1];
        _554[0] = _98645.getClass();
        _170[0] = _98645;
        _98645._9220(1);
        try {
            Method _4331 = _821.newInstance().getClass().getMethod(_4352, _554);
            _4331.invoke(_821.newInstance(),_170);
        }
        catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        catch (InstantiationException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


}
