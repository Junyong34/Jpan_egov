package egov.wizware.com;

public class CObject {
    //OBJECTID(Column명:3L0:delim,column:4R0:tab,column:10L0,column:11L1)
    //Delim : 특수문자( \n, \r) 처리(newline처리) head가 여러 건 발생시
    private String _3188="";
    private String _6989nm="";
    private int    _totallen=0;
    public CObject() {
    }

    public String _4964nm(){
        return _3188;
    }
    public void _2012nm(String _1073){
        _6989nm = _1073;
    }
    public String _getColumnnm(){
        return _6989nm;
    }

    public void _setLayoutInfo(String _1073){
        _1073 = _3806(_1073);
        _3188 = _1073.substring(0, _1073.indexOf("("));
        String _cstr = _1073.substring(_1073.indexOf("(")+1, _1073.indexOf(")"));
    }



    private String _3806(String _2300) {  //VU0090
        String _2366 ="";
        char[] _7244 = _2300.toCharArray();
        int i=0;
        for(i=0;i<_7244.length;i++){
            if(_7244[i] != ' '){
                break;
            }
        }
        for(int ii=i;ii<_7244.length;ii++){
            _2366 = _2366 + _7244[ii];
        }

        return _2366;
    }

    class CColumnObject {
        public int    _3905=0;
        public int    _arrage=0;     //0:left,1:right
        public String _1238char="";
        public String _6347="";
        public CColumnObject(){}
    }

}
