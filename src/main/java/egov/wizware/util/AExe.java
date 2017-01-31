package egov.wizware.util;
import egov.wizware.com.DOBJ;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;


public class AExe {
    //internal 호출만 가능하다.
    public AExe(){}
    public _AUtil _getUtil(Object _Conn, DOBJ _idobj,  HashMap _classinfo){
        _AUtil _rutil = null;
        try{

           CommUtil  _cutil    = new CommUtil();
           String    _classnm =  _classinfo.get("PACKAGE").toString() + "." +   _classinfo.get("CLASS").toString();
           String    _methodnm= _classinfo.get("METHOD").toString();
           //String    _methodnm= "CTL"+_classinfo.get("METHOD").toString();
           ArrayList _maplist = _cutil._5078Name(_classinfo.get("INMAP").toString());
           _idobj  = _cutil._2495CallingObject(_idobj, _maplist );
            _rutil = new _AUtil(_Conn, _idobj, _classnm, _methodnm);

       }catch(Exception e){
           e.printStackTrace();
       }
       return _rutil;
    }

    private AExe(Object _Conn, DOBJ _idobj,  HashMap _classinfo) {
        try{

            CommUtil  _cutil    = new CommUtil();
            String    _classnm =  _classinfo.get("PACKAGE").toString() + "." +   _classinfo.get("CLASS").toString();
            String    _methodnm= _classinfo.get("METHOD").toString();
            //String    _methodnm= "CTL"+_classinfo.get("METHOD").toString();
            System.out.println(_classnm +":" + _methodnm);

            ArrayList _maplist = _cutil._5078Name(_classinfo.get("INMAP").toString());
            _idobj  = _cutil._2495CallingObject(_idobj, _maplist );
            _AUtil _rutil = new _AUtil(_Conn, _idobj, _classnm, _methodnm);

            //ExecutorService _exe = Executors.newSingleThreadExecutor();
            //Future future =  _exe.submit(_rutil);
            //System.out.println(future.get());
            //_exe.shutdown();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void Execute(){
    }


}


