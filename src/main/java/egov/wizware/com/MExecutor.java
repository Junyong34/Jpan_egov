package egov.wizware.com;

import java.util.HashMap;
import java.lang.reflect.Method;
import java.lang.reflect.*;

public class MExecutor {
    public MExecutor() {
    }

    public DOBJ execute(DOBJ _6185, HashMap _6674) {
      String _3227name = _6674.get("PACKAGEX").toString() + "." +   _6674.get("CLASSX").toString() ;
      String _3560 = _6674.get("METHODX").toString();
      //String _3560 = "CTL"+_6674.get("METHODX").toString();
      Class _6893 = null;
      try {
        _6893 = Class.forName( _3227name );
      } catch (ClassNotFoundException ex) {
        ex.printStackTrace();
      }

      Object _7454[] = new Object[1];
      Class[] _7133 = new Class[1];
      _7133[0] = _6185.getClass();
      _7454[0] = _6185;
      DOBJ _2468 = null;
      try {
        Method _3572 = _6893.newInstance().getClass().getMethod(_3560, _7133);
        _2468 = (DOBJ) _3572.invoke(_6893.newInstance(),_7454);
        return _2468;
      }
      catch (NoSuchMethodException e) {
        e.printStackTrace();
        return _2468;
      }
      catch (InvocationTargetException e) {
        e.printStackTrace();
        return _2468;
      }
      catch (IllegalAccessException e) {
        e.printStackTrace();
        return _2468;
      }
      catch (InstantiationException e) {
        e.printStackTrace();
        return _2468;
      }
      catch (Exception e) {
        e.printStackTrace();
        return _2468;
      }
    }

    public VOBJ execute(VOBJ _6131, HashMap _6674) {
        VOBJ _227 = null;
        String _3227name = _6674.get("PACKAGEX").toString() + "." +   _6674.get("CLASSX").toString() ;
        String _3560 = _6674.get("METHODX").toString();
        //String _3560 = "CTL"+_6674.get("METHODX").toString();
        Class _6893 = null;
        try {
            _6893 = Class.forName( _3227name );
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        Object _7454[] = new Object[1];
        Class[] _7133 = new Class[1];
        _7133[0] = _6131.getClass();
        _7454[0] = _6131;
        try {
            Method _3572 = _6893.newInstance().getClass().getMethod(_3560, _7133);
            _227 = (VOBJ) _3572.invoke(_6893.newInstance(),_7454);
            return _227;
        }
        catch (NoSuchMethodException e) {
            e.printStackTrace();
            return _227;
        }
        catch (InvocationTargetException e) {
            e.printStackTrace();
            return _227;
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
            return _227;
        }
        catch (InstantiationException e) {
            e.printStackTrace();
            return _227;
        }
        catch (Exception e) {
            e.printStackTrace();
            return _227;
        }
    }

}



