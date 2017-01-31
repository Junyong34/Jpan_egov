package egov.wizware.com;
import java.util.*;
import java.sql.*;
import java.lang.reflect.Method;
import java.lang.reflect.*;

public class AutoProc {
    private ArrayList  _aproc=null;
    private VOBJ  _ivobj = null;
    private DOBJ  _dobj = null;
    private ArrayList _pklist=null;
    private int   _type =0; //1:before, 2:after
    public AutoProc() {
    }
    public AutoProc(DOBJ  _obj)
    {
        _dobj = _obj;
    }
    public AutoProc(DOBJ  _obj, int _type)
    {
        _dobj = _obj;
        this._type = _type;
    }

    public void setVobj(VOBJ _obj)
    {
        _ivobj = _obj;
        _dobj.setRetObject("A",_ivobj);
    }
    public void setDobj(DOBJ _obj)
    {
        _dobj = _obj;
    }
    public void setPklist(ArrayList _pk)
    {
        _pklist = _pk;
    }

    public void add(String packagenm, String classnm, String methodnm)
    {
        if(_aproc == null)
        {
            _aproc = new ArrayList();
        }
        Object[] _info = new Object[3];
        _info[0] = packagenm;
        _info[1] = classnm;
        _info[2] = methodnm;
        _aproc.add(_info);
    }
    public void add(String packagenm, String classnm, String methodnm, int condi)
    {
        if(_aproc == null)
        {
            _aproc = new ArrayList();
        }
        Object[] _info = new Object[4];
        _info[0] = packagenm;
        _info[1] = classnm;
        _info[2] = methodnm;
        _info[3] = new Integer(condi);
        _aproc.add(_info);
    }
    public void executor(Connection conn) throws Exception
    {
        if(_aproc == null) return;
        Object[] obj = null;
        for(int i=0;i<_aproc.size();i++){
            obj = (Object[])_aproc.get(i);
            if(obj.length ==4) {
                Integer condi = (Integer)obj[3];
                if(condi.intValue()==1) {
                    callAutoProc(conn, obj);
                }else if(condi.intValue()==2){
                    callAutoProc(conn, obj);
                    obj[3] = new Integer(0);
                    _aproc.set(i, obj);
                }
            }else {
                callAutoProc(conn, obj);
            }
        }
    }

    public void ConditionExe(int row)
    {
        Object[] obj =(Object[])_aproc.get(row);
        obj[3] = new Integer(2);
        _aproc.set(row, obj);
    }

    private void callAutoProc(Connection _conn, Object[] classinfo) throws Exception{
        String objectname = classinfo[0].toString() + "." +   classinfo[1].toString() ;
        String methodname = classinfo[2].toString();
        //String methodname = "CTL"+classinfo[2].toString();

        if(this._type ==1)
        {
            System.out.println("AUTO EXE INFO:BEFORE:" + objectname +":" + methodname );
        }else
        {
            System.out.println("AUTO EXE INFO:AFTER:" + objectname +":" + methodname );
        }

        Class commandClass = null;
        if(_dobj._9234() == 1)
        {
            CONFIG _cfg = new CONFIG();
            String _classpath= _cfg.getPBConfig("RUNNINGCLASS_" + _dobj._getRuser());
            if(_classpath == null || _classpath.trim().equals("")){
                _classpath = _cfg.getPBConfig("RUNNINGCLASS");
            }

            if(!_dobj._getRP().trim().equals("")){
                _classpath = _dobj._getRP();
            }
            SCLoader _sloader = new SCLoader(_classpath);
            commandClass = _sloader.findClass(objectname);
        }else
        {
            commandClass = Class.forName( objectname );
        }

        Object arg[] = new Object[2];
        Class[] cls = new Class[2];

        cls[0] = _dobj.getClass();
        arg[0] = _dobj;

        cls[1] = Object.class;
        arg[1] = _conn;

        Method _method = commandClass.newInstance().getClass().getMethod(methodname, cls);
        DOBJ _2468 = (DOBJ) _method.invoke(commandClass.newInstance(),arg);

    }



}
