package egov.wizware.com;
import java.util.*;

public class JObject {
    private int _6644=0;
    private ArrayList _6155names=null;
    private HashMap   _6155columns = null;
    private HashMap   _6155recordmaxsize = null;
    private String _6347=".";
    public JObject() {
        _6155names = new ArrayList();
        _6155columns = new HashMap();
        _6155recordmaxsize = new HashMap();
    }


    public void _5879(){
        _6644 = -1;
    }
    public boolean _3287(){
        boolean _2366 = true;
        _6644++;
        if(_6644 >= _6155names.size()){
            _2366= false;
        }
        return _2366;
    }
    public int _4685(){
        return _6155names.size();
    }


    public int _5510DsMaxsize(){
        String _6155name = _6155names.get(_6644).toString();
        return ((Integer)_6155recordmaxsize.get(_6155name)).intValue();
    }
    public String _5510Dsname(){
        return _6155names.get(_6644).toString();
    }
    public ArrayList _getDscolumn(){
        String _6155name = _6155names.get(_6644).toString();
        return (ArrayList)_6155columns.get(_6155name);
    }

    public void _1670(String _3374, HashMap _3062){
        String _6155name="S";
        String _7028="";
        _setMaxsize(_3374,_3062);
        if(_3374.indexOf(_6347) != -1){
            _6155name = _3374.substring(0,_3374.indexOf(_6347));
            _7028= _3374.substring(_3374.indexOf(_6347)+1);
        }else {
            _7028=_3374;
        }

        if(_5996Ds(_6155name)){
            ArrayList _7136 = (ArrayList)_6155columns.get(_6155name);
            _7136.add(_7028);
            _6155columns.put(_6155name,_7136);
        }else {
            _6155names.add(_6155name);
            ArrayList _7136 = new ArrayList();
            _7136.add(_7028);
            _6155columns.put(_6155name, _7136);
        }
    }

    private void _setMaxsize(String _3374x, HashMap _3062){
        int _1349 = ((ArrayList)_3062.get(_3374x)).size();
        String _6155name = _3374x.substring(0,_3374x.indexOf(_6347));
        if(_6155recordmaxsize.containsKey(_6155name)){
            int _max  = ((Integer)_6155recordmaxsize.get(_6155name)).intValue();
            if(_1349 > _max) _max = _1349;
            Integer _imax = new Integer(_max);
            _6155recordmaxsize.put(_6155name,_imax);
        }else{
            Integer _imax = new Integer(_1349);
            _6155recordmaxsize.put(_6155name,_imax);
        }
    }
    private boolean _5996Ds(String name){
        boolean _2366 = false;
        for(int i=0;i<_6155names.size();i++){
            if(_6155names.get(i).toString().equals(name)) {
                _2366 = true;
                break;
            }
        }
        return _2366;
    }

}
