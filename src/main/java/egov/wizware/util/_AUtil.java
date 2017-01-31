package egov.wizware.util;

import java.lang.reflect.Method;
import java.lang.reflect.*;
import egov.wizware.util.*;
import egov.wizware.com.*;

public class _AUtil  implements  java.io.Serializable {
    private Object _Conn=null;
    private String _classnm="";
    private String _methodnm="";
    private DOBJ   _dobj = null;  // thread 상태에서 global 형태의 중복오류 발생 확인 필요하다.
    private VOBJ   _vobj = null;  // thread 상태에서 global 형태의 중복오류 발생 확인 필요하다.

    public _AUtil() {
    }

    public _AUtil( Object _Conn,  DOBJ _dobj, String _classnm, String _methodnm ) {
         this._Conn    = _Conn;
         this._classnm = _classnm;
         this._methodnm= _methodnm;
         this._dobj    = _dobj;
    }

    public void run(){

        if (_Conn != null) {
            _executeA(_Conn, _dobj, _classnm, _methodnm);
        } else {
            _executeA(_dobj, _classnm, _methodnm);
        }
    }

    public DOBJ _executeA(Object _Conn, DOBJ _idobj,  String _classnm, String _methodnm) {

        Class _commandClass = null;
        try {
            if(_idobj._getRltype()==1){
                CONFIG _cfg = new CONFIG();
                String _classpath= _cfg.getPathfinderConfig("RUNNINGCLASS_" + _idobj._getRuser());
                if(_classpath == null || _classpath.trim().equals("")){
                    _classpath = _cfg.getPathfinderConfig("RUNNINGCLASS");
                }
                SCLoader _sloader = new SCLoader(_classpath);
                _commandClass = _sloader.findClass(_classnm);

            }else {
                 _commandClass = Class.forName( _classnm );
            }
        } catch (Exception ex) {
            _idobj.DlgError("class not found:",ex);
          ex.printStackTrace();
        }

        Object _arg[] = new Object[2];
        Class[] _cls = new Class[2];

        _cls[0] = Object.class;
        _arg[0] = _Conn;

        _cls[1] = _idobj.getClass();
        _arg[1] = _idobj;


        DOBJ _ret = null;
        try {
          Method _method = _commandClass.newInstance().getClass().getMethod(_methodnm, _cls);
          _method.invoke(_commandClass.newInstance(),_arg);
          return _ret;
        }
        catch (NoSuchMethodException e) {
            _idobj.DlgError("NoSuchMethodException:",e);
          e.printStackTrace();
          return _ret;
        }
        catch (InvocationTargetException e) {
            _idobj.DlgError("InvocationTargetException:",e);
          e.printStackTrace();
          return _ret;
        }
        catch (IllegalAccessException e) {
            _idobj.DlgError("IllegalAccessException:",e);
          e.printStackTrace();
          return _ret;
        }
        catch (InstantiationException e) {
            _idobj.DlgError("InstantiationException:",e);
          e.printStackTrace();
          return _ret;
        }
        catch (Exception e) {
            _idobj.DlgError("class Running Error:",e);
          e.printStackTrace();
          return _ret;
        }
      }

      public DOBJ _executeA(DOBJ _idobj,  String _classnm, String _methodnm) {

          Class _commandClass = null;
          try {
              if(_idobj._getRltype()==1){
                  CONFIG _cfg = new CONFIG();
                  String _classpath= _cfg.getPathfinderConfig("RUNNINGCLASS_" + _idobj._getRuser());
                  if(_classpath == null || _classpath.trim().equals("")){
                      _classpath = _cfg.getPathfinderConfig("RUNNINGCLASS");
                  }
                  SCLoader _sloader = new SCLoader(_classpath);
                  _commandClass = _sloader.findClass(_classnm);

              }else {
                  _commandClass = Class.forName( _classnm );
              }
          } catch (Exception ex) {
               _idobj.DlgError("Class Not Found:",ex);
              ex.printStackTrace();
          }

          Object  _arg[] = new Object[1];
          Class[] _cls = new Class[1];

          _cls[0] = _idobj.getClass();
          _arg[0] = _idobj;


          DOBJ _ret = null;
          try {
              Method _method = _commandClass.newInstance().getClass().getMethod(_methodnm, _cls);
              _method.invoke(_commandClass.newInstance(), _arg);
              return _ret;
          }
          catch (NoSuchMethodException e) {
              _idobj.DlgError("NoSuchMethodException:",e);
              e.printStackTrace();
              return _ret;
          }
          catch (InvocationTargetException e) {
              _idobj.DlgError("InvocationTargetException:",e);
              e.printStackTrace();
              return _ret;
          }
          catch (IllegalAccessException e) {
              _idobj.DlgError("IllegalAccessException:",e);
              e.printStackTrace();
              return _ret;
          }
          catch (InstantiationException e) {
              _idobj.DlgError("InstantiationException:",e);
              e.printStackTrace();
              return _ret;
          }
          catch (Exception e) {
              _idobj.DlgError("Exception:",e);
              e.printStackTrace();
              return _ret;
          }

      }

}
