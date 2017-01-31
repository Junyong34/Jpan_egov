package egov.wizware.ctl;
import egov.wizware.com.*;
import egov.wizware.util.*;
import java.util.*;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;

public class SessionObject {
    public SessionObject(){}

    public VOBJ getObject(HttpServletRequest _req) throws Exception{
        DOBJ _dobj = new DOBJ();
        VOBJ _vobj = new VOBJ();
        CONFIG _cfg = new CONFIG();
        VOBJ _robj = null;


        String _userid = _req.getParameter(_cfg.getPathfinderConfig("LOGIN_COLUMN"));
        HashMap _record = new HashMap();
        _record.put("USERID", _userid);
        _vobj.addRecord(_record);
        _vobj.setName("S");
        _dobj.setRetObject(_vobj);

        String packagenm  = _cfg.getPathfinderConfig("SESSION_PACKAGE");   //
        String classnm    = _cfg.getPathfinderConfig("SESSION_CLASS");
        String methodnm   = _cfg.getPathfinderConfig("SESSION_METHOD");
        String outobjid   = _cfg.getPathfinderConfig("SESSION_OUTOBJECTID");  // 해당 method가 생성하는 object중 outobjectid를 session object로 생성한다.

        if(!packagenm.trim().equals("") && !classnm.trim().equals("")  && !methodnm.trim().equals("") && !outobjid.trim().equals("") ){
            _robj= getObject(_dobj, packagenm, classnm, methodnm, outobjid);
            _robj.getRecord().put("IPADDR",_req.getRemoteAddr());
        }else {
            _robj= new VOBJ();
            _record = new HashMap();
            _record.put("USERID", _userid);
            _record.put("IPADDR",_req.getRemoteAddr());
            _robj.addRecord(_record);
            _robj.setName("G");
        }

        return _robj;
    }

    private VOBJ getObject(DOBJ _dobj, String packagenm, String classnm, String methodnm, String outobjid) throws Exception {

        VOBJ _vobj = null;
        CommUtil cu = new CommUtil(_dobj);
        HashMap   classinfo = new HashMap();

        classinfo.put("OBJECTID","G");
        classinfo.put("PACKAGE" ,packagenm);
        classinfo.put("CLASS"   ,classnm  );
        classinfo.put("METHOD"  ,methodnm );
        classinfo.put("INMAP"   ,"S:S"    );
        classinfo.put("OUTOBJ"  ,outobjid );
        _dobj = cu.callPSInternal(_dobj, null, classinfo );
        _vobj = _dobj.getRetObject("G");

        return _vobj;
    }

    private DOBJ CALLOBJ_OBJ1(DOBJ dobj) throws Exception
    {
        dobj.setRtnname("OBJ1");
        CommUtil cu = new CommUtil(dobj);
        HashMap   classinfo = new HashMap();
        classinfo.put("OBJECTID","OBJ1");
        classinfo.put("PACKAGE","MINISTOP.BOC1.SAMPLE");
        classinfo.put("CLASS","THRUPD");
        classinfo.put("METHOD","THRUPD");
        classinfo.put("INMAP","S:S");
        classinfo.put("OUTOBJ","");
        dobj = cu.callPSInternal(dobj, null, classinfo );
        return dobj;
    }

}
