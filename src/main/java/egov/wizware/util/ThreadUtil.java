package egov.wizware.util;

import java.io.*;
import java.util.*;
import java.sql.*;

import java.lang.reflect.Method;
import java.lang.reflect.*;
import egov.wizware.util.*;
import javax.servlet.http.HttpServletRequest;
import egov.wizware.com.*;
import egov.wizware.dao.Connector;


public class ThreadUtil {
    private String _9876="";
    public ThreadUtil (){}
    public ThreadUtil (DOBJ _dobj){

    }
    public ThreadUtil (String _9481){
        this._9876 = _9481;
    }

    public DOBJ run (Object _9199, String _7151, String _3554,  DOBJ _6185, SQLObject _1310x,  int _2315size) throws Exception{

        WizUtil wutil = new WizUtil();
        PreparedStatement _2699=null;
        ResultSet         _8980   = null;
        ResultSetMetaData _8978 = null;

        boolean _9157 = false;
        String  _6062 ="";
        ArrayList _9163 = new ArrayList();
        _9190 _818 =  null;

        Object _8394 = null;
        if(_9876 != null && !_9876.trim().equals("")){
            CONFIG _cfg = new CONFIG();
            try
            {
                Connector connection = new Connector();
                _8394  = connection.getConnectionDirect(_cfg.getPathfinderConfig(_9876+"_DATABASE"),
                        _cfg.getPathfinderConfig(_9876+"_DIRECT"  ),
                        _cfg.getPathfinderConfig(_9876+"_USER"    ),
                        _cfg.getPathfinderConfig(_9876+"_PASSWORD") );
            }
            catch(Exception e)
            {
                _6185.DlgError("Database Connection Error",e);
                throw new Exception("Database Connection Error");
            }
        }else {
            _8394 = (Connection)_9199;
        }

        try{
            _2699 = wutil.getPStmt(_8394, _1310x);
            _8980 = _2699.executeQuery();
            _8978 = _2699.getMetaData();
            int _7986=0;
            String[][] _7109 = new String[_8978.getColumnCount()][2];
            for (int x = 0; x < _8978.getColumnCount(); x++)
            {
                _7986++;
                _7109[x][0] = _8978.getColumnName(x + 1);
                _7109[x][1] = _8978.getColumnTypeName(x+1);
            }
            HashMap _2564 = null;


            //ExecutorService _exe = null;
            //_exe = Executors.newFixedThreadPool(_2315size);

            int i=0;
            while (_8980.next())
            {
                _2564 =  new HashMap();
                for (int x = 0; x < _7986; x++)
                {
                    if(_7109[x][1] != null)
                    {
                        _2564.put  (_7109[x][0], _8980.getString(_7109[x][0]));
                    }
                    else
                    {
                        _2564.put (_7109[x][0],"");
                    }
                }

                VOBJ _9187 = new VOBJ();
                _9187.setName("R");
                _9187.addRecord(_2564);
                _6185.setRetObject(_9187);


                _9187._parentrownum =i;

                _9190 _899= new _9190(_8394, _7151, _3554, _6185, _9187);

                /*
                _exe.submit(_899);
                _899.sleep(50);
                */


                _899.start();
                _9163.add(_899);
                _899.sleep(10);

                /*
                while(_9163.size() > _2315size){
                    for(int x=_9163.size()-1;x>=0;x--){
                        _818=  (_9190)_9163.get(x);
                        if(_818.isAlive() == false) {
                            if(_818._errcode == -2){
                                _9157= true;
                                _6062 = _818._6062;
                                break;
                            }
                            _9163.remove(x);
                        }
                    }

                    if(_9157 == true){
                        break;
                    }
                }
                //*/



                if(_9157 == true){
                    System.out.println("#Thread Error:"+_7151 +"." + _3554 +":" + _6062);
                    _6185.setRtncode(-2);
                    _6185.setRetmsg(_6062);
                    break;
                }
                i++;
            }
            //_exe.shutdown();
        }catch(Exception ex) {
            ex.printStackTrace();
            _6185.setRtncode(-2);
            _6185.setRetmsg(_6062);
            throw ex;
        }finally{
            if(_9876 != null && !_9876.trim().equals("")){
                try{
                    if(_8394 != null)
                        ((Connection)_8394).close();
                }catch(Exception e){
                     _6185.DlgError("Database Connection Close Error",e);
                    e.printStackTrace();
                }
            }
        }

        return _6185;
    }

    public DOBJ run (Object _9199, String _7151, String _3554,  DOBJ _6185, SQLObject _1310x, String spname ,
                  int[] intype, String[] outcolnms, int[] outtypes, int _2315size) throws Exception{

        return _6185;
    }
    public DOBJ  run (Object _9199, String _7151, String _3554,  DOBJ _6185, VOBJ _170, String spname ,
                      int[] intype, String[] outcolnms, int[] outtypes, int _2315size) throws Exception{
        return _6185;
    }


    //WIZ.FR.UTIL.ThreadUtil.run(Ljava/sql/Connection;Ljava/lang/String;Ljava/lang/String;LWIZ/FR/COM/DOBJ;LWIZ/FR/COM/VOBJ;I)LWIZ/FR/COM/DOBJ;
    public DOBJ run (Object _9199, String _7151, String _3554,  DOBJ _6185, VOBJ _170,  int _2315size) throws Exception{
        //Connection _9199 = (java.sql.Connection)_9199x;
        boolean _9157 = false;
        String  _6062 ="";
        ArrayList _9163 = new ArrayList();
        _9190 _818 =  null;
     // System.out.println("TOTAL RECORD COUNT:" + _170.getRecordCnt());
      //_170.Println("");
      _170.first();
      while(_170.next()){

        _9190 _899= new _9190(_9199, _7151, _3554, _6185, _170, _170.current);

        _899.start();
        _9163.add(_899);
        _899.sleep(50);
        //System.out.println("STARTY ..:" + _899._getThreadno() );
        //System.out.println(_9163.size() +":" +_2315size +":"+  _170.getRecordCnt());

        /*
        while(_9163.size() >= _2315size && _9163.size() <= _170.getRecordCnt()){

          for(int x=_9163.size()-1;x>=0;x--){
            _818=  (_9190)_9163.get(x);
            if(_818.isAlive() == false) {
              if(_818._errcode == -2){
                _9157= true;
                _6062 = _818._6062;
                break;
              }
              _9163.remove(x);
            }
          }

          if(_9157 == true){
            break;
          }
        }
        */

        if(_9157 == true){
          //System.out.println("#Thread Error:"+_7151 +"." + _3554 +":" + _6062);
          _6185.setRtncode(-2);
          _6185.setRetmsg(_6062);
          break;
        }

      }
      return _6185;
    }




    public ThreadUtil (int _2315size)
    {
      try
      {
        ArrayList _9163 = new ArrayList();
        for(int i=0;i<1000;i++)
        {
          _9190 _899= new _9190(i);
          _899.start();
          _9163.add(_899);

          while(_9163.size() > _2315size){
            _9190 _818 =  null;
            for(int x=_9163.size()-1;x>=0;x--){
              _818=  (_9190)_9163.get(x);
              if(_818.isAlive() == false) {
                  _9163.remove(x);
              }
            }
          }
        }
      }catch (Exception e)
      {
        e.printStackTrace();
      }
    }

    class _9190 extends Thread{
        private int _9228=0;
        private Object _9199=null;
        private String _7151="";
        private String _3554="";
        private DOBJ _6185 = null;
        private VOBJ _227 = null;
        private int _2579=0;
        private int _errcode=0;
        private String _6062="";
        public _9190( int _s )
        {
            try{
                _9228=_s;
            }catch( Exception e )
            {
                e.printStackTrace();
            }
        }
        public int _getThreadno(){
          return _9228;
        }

        public _9190(Object _9209, String _4337lassnm, String _9218, DOBJ _9200, VOBJ _4040, int _6644)
       {
           try{
             _9199     = _9209;
             _7151  = _4337lassnm;
             _3554 = _9218;
             _6185     = _9200;
             _227     = _4040;

             _2579 = _6644;
             _9228 = _6644;

           }catch( Exception e )
           {
               _9200.DlgError("Thread Error:",e);
               e.printStackTrace();
           }
       }

        public _9190(Object _9209, String _4337lassnm, String _9218, DOBJ _9200, VOBJ _4040)
        {
            try{

              _9199     = _9209;
              _9228 = _4040.current;
              _7151  = _4337lassnm;
              _3554 = _9218;
              _6185     = _9200;
              _227     = _4040;
            }catch( Exception e )
            {
                _9200.DlgError("Thread Error S1:",e);
                e.printStackTrace();
            }
        }
        public void run(){
            try{
             _227.current = _2579;

             VOBJ _4040 = new VOBJ();
             _4040.addRecord(_227.getRecord(_2579));
             _9198(_9199, _6185, _4040, _7151, _3554);
              sleep(5000);
            }catch( Exception e)  {
              _errcode=-2;
              _6062 = e.getMessage();
              _6185.DlgError("Thread Running S1:",e);
              e.printStackTrace();
            }
        }

        public DOBJ _9198(Object _9199, DOBJ _9200, VOBJ _4040, String _7151, String _3554) {
            Class _6893 = null;
            try {
                if(_9200._9234() == 1) {
                    SCLoader _3029 = new SCLoader(_9200._getRP());
                    _6893 = _3029.findClass(_7151);
                }else {
                    _6893 = Class.forName( _7151 );
                }



            } catch (Exception ex) {
                ex.printStackTrace();
                _9200.DlgError("ClassNotFoundException:",ex);
            }

            Object _7454[] = new Object[3];
            Class[] _7133 = new Class[3];


            _7133[0] =  Object.class;
            _7454[0] = _9199;

            _7133[1] = _9200.getClass();
            _7454[1] = _9200;

            _7133[2] = _4040.getClass();
            _7454[2] = _4040;

            DOBJ _2468 = null;
            try {

                Method _3572 = _6893.newInstance().getClass().getMethod(_3554, _7133);
                _3572.invoke(_6893.newInstance(),_7454);
                return _2468;
            }
            catch (Exception e) {
                try{
                    System.out.println("XXXXXX ISCLOSED ZZZZ:"+((Connection)_9199).isClosed());
                }catch(Exception eeee){

                }
                e.printStackTrace();
                _9200.DlgError("Exception Method Running:",e);
                return _2468;
            }
          }


    }
}



/*
public DOBJ CALLEventID01_829211(Connection Conn, DOBJ dobj) throws Exception
{
     WizUtil wutil = new WizUtil();
     VOBJ dvobj = dobj.getRetObject("MRG9");
     //return 처리 구성이 필요한가(가능 한가에 대한 고려가 필요하다)
     _9154 _thrutil = new _9154();
     dobj = _thrutil._2315(Conn, classname, "THREADEventID01_829211" , dobj , dvobj , runsize);
     if(dobj.getRtncode()==-2) {
        //---- throw new DAOException("Thread Runn Error");
     }
     return dobj;
}

public DOBJ THREADEventID01_829211(Connection Conn, DOBJ dobj, VOBJ dvobj) throws Exception
{
     if(!dvobj.getRecord().get("RECV_NO").equals(""))
     {
         dobj.setRetObject(dvobj.getRecVobj("R"));
         dobj  = CALLEventID01_INS14(Conn, dobj);
     }
     return dobj;
 }

 public DOBJ CALLEventID01_829211(Connection Conn, DOBJ dobj) throws Exception
 {
     WizUtil wutil = new WizUtil();
     VOBJ dvobj = dobj.getRetObject("MRG9");
     //return 처리 구성이 필요한가(가능 한가에 대한 고려가 필요하다)
     _9154 _thrutil = new _9154();
     dobj = _thrutil._2315(Conn, classname, "THREADEventID01_829211" , dobj , dvobj , runsize);
     if(dobj.getRtncode()==-2) {
        //---- throw new DAOException("Thread Runn Error");
     }
     return dobj;
 }

 public DOBJ THREADEventID01_829211(Connection Conn, DOBJ dobj, HashMap record ) throws Exception
 {
      VOBJ dovjx = new VOBJ();
      dovjx.setName("R");
      dovjx.addRecord(record);
      dobj.setRetObject(dovjx);
      dobj  = CALLxiud_82712(Conn, dobj);
      return dobj;
 }

*/
