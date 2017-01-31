package egov.wizware.com;

import java.util.*;

import javax.servlet.http.HttpServletResponse;

import java.io.OutputStream;

import egov.wizware.log.DLog;

public class VOBJ  implements java.io.Serializable {

//public class VOBJ  {

  private LinkedHashMap _datatype = new LinkedHashMap();
  private HashMap   _2564=null;
  private ArrayList _2558 = new ArrayList();

  public  int     current = 0;
  public  int     _parentrownum=0;
  private int     _3818=0;
  private String  _3827="";
  private String  _938=null;

  private String   _3188 = "";
  private String[] _3131 = null;
  private String   _2447 = null;
  private int      _2462;

  //--�ʱ� memory ó���� nulló�� ���� �ʿ� :memory tunning ó�� �ʿ��
  private ArrayList _1232 = new ArrayList();
  private HashMap _coltype=new HashMap();
  private HashMap _6929 = new HashMap();
  //  private HashMap columnsize=new HashMap();

  //�ӽ� ��� ó����:PC001ZT->sqlattrX��
  private Object   _107=null;
  public  String   _3248=null;
  public  String   _3038=null;
  public  String   _7055=null;
  private int      _6248mode=0;
  private DLog     _19G= null;  //log ó�� ���
  private String   _alias="";

  public void _19lg(DLog _s){
      _19G = _s;
  }
  public void setLpcvalue(int _3818){
    this._3818 = _3818;
  }
  public int getLpcvalue(){
    return this._3818;
  }

  public void setLpcmsg(String _3827){
    this._3827 = _3827;
  }
  public String getLpcmsg(){
    return this._3827;
  }

  public void setWorkObject(Object _3230){
    this._107 = _3230;
  }
  public Object getWorkObject(){
    return this._107;
  }
  public void setColumnRecordCount(String _3338,int _count){
    this._6929.put(_3338,""+_count);
  }
  public void setColumnRecordCounts(HashMap _count){
    this._6929 = _count;
  }
  public HashMap getColumnRecordCounts(){
    return this._6929;
  }
  public int getColumnRecordCount(String _3374){
    int _2366=0;
    if(this._6929.containsKey(_3374)){
      String _671 = this._6929.get(_3374).toString();
      if(_671 != null && !_671.equals(""))
        _2366 =Integer.parseInt(this._6929.get(_3374).toString());
    }
    return _2366;
  }
  public void _setDispmode(int _6248){
      _6248mode = _6248;
  }
  public ArrayList getColumnRecordCountnames()
  {
    ArrayList _7475  = new ArrayList();
    Iterator iter = this._6929.keySet().iterator();
    while(iter.hasNext()){
      _7475.add(iter.next().toString());
    }
    return _7475;
  }

  public void Println(String info )
  {
      if(_6248mode == 1) return;
      if(_2558 != null) {
          HashMap _4358 = null;
          ArrayList _7475 = this.getColumnNames();
          _19ginfo("=========["+info+"]==["+this.getName()+"]="+"==["+_2558.size()+"]=========");
          String _sxf="";
          for(int i=0;i<_7475.size();i++)
          {
             _sxf += ":"+_7475.get(i);
              //System.out.print(":"+_7475.get(i));
          }
          _19ginfo(_sxf);
          //_19ginfo("");
          _19ginfo("===========================================");

          for(int i=0;i<_2558.size();i++){
              _4358 = (HashMap)_2558.get(i);
              _sxf = new String();
              for(int ii=0;ii<_7475.size();ii++){
                  _sxf +=":" + _4358.get(_7475.get(ii));
                //  System.out.print(":" + _4358.get(_7475.get(ii)));
              }
              _19ginfo(_sxf);
              //_19ginfo("");
          }
      }else{
          _19ginfo("=========["+info+"] IS NOT FOUNDED RECORDS =============");
      }
      _19ginfo("===========================================");
  }

  public void setRetmsg(String m)
  {
    this._2447 = m;
  }

  public String getRetmsg()
  {
    return this._2447;
  }

  public void setRetcode(int m)
  {
    this._2462 = m;
  }

  public int getRetcode()
  {
    return this._2462;
  }

  //--------------------------------------------------
  public boolean findHeadName(ArrayList clist, String name)
  {
      boolean rtn = false;
      for(int i=0;i<clist.size();i++)
      {
          if(name.equals(clist.get(i).toString()))
          {
              rtn = true;
              break;
          }
      }
      return rtn;
  }
  public ArrayList getHeadFindName(ArrayList nlist)
  {
      ArrayList rlist = nlist;
      ArrayList clist = this.getColumnNames();
      for(int i=0;i<clist.size();i++){
          if(findHeadName(nlist, clist.get(i).toString()) == false){
              rlist.add(clist.get(i));
          }
      }
      return rlist;
  }
  public ArrayList getHeadnames(){
      ArrayList  nlist = new ArrayList();
      Iterator itor = this._datatype.keySet().iterator();
      while(itor.hasNext()){
          nlist.add(itor.next());
      }
      return nlist;
  }
  public LinkedHashMap getHeadColumn()
  {
      return this._datatype;
  }
  public void setHeadColumn(LinkedHashMap _type)
  {
      this._datatype=_type;
  }
  public void setHeadColumnType(HashMap _type)
  {
      String _key ="";
      _datatype = new LinkedHashMap();
      Iterator _it = _type.keySet().iterator();
      while(_it.hasNext())
      {
          _key = _it.next().toString();
          this._datatype.put(_key, _type.get(_key));
      }
  }

  public void setHeadColumnType(LinkedHashMap _type)
  {
      this._datatype=_type;
  }
  public void setHeadColumn(String _name, String _type)
  {
      this._datatype.put(_name, _type);
  }
  public void setHeadColumn(String _name, Object _type)
  {
      this._datatype.put(_name, _type);
  }
  public void setHeadColumnType(String _name, Object _type)
  {
      this._datatype.put(_name, _type);
  }
  public void setHeadColumnType(String _name, String _type){
      this._datatype.put(_name, _type);
  }
  public ColumnHeadInfo getHeadColumnInfo(String name)
  {
      return (ColumnHeadInfo)this._datatype.get(name);
  }


  public HashMap getHeadColumnType()
  {
      HashMap rtn = new HashMap();
      if(this._datatype != null)
      {
          return this._datatype;
      }else
      {
          return this._datatype;
      }
  }
  public int getHeadColumnType(String name)
  {
      if(!this._datatype.containsKey(name)){
          return java.sql.Types.VARCHAR;
      }
      return getHeadColumnInfo(name).getType();
  }

  public String getHeadColumnTypename(String name)
  {
      if(!this._datatype.containsKey(name)){
          return "VARCHAR";
      }
      return getHeadColumnInfo(name).getTypename();
  }
  public int getHeadColumnLength(String name)
  {
      if(!this._datatype.containsKey(name)){
          return 200;
      }
      return getHeadColumnInfo(name).getLength();
  }
  public int getHeadColumnNumberSize(String name)
  {
      if(!this._datatype.containsKey(name)){
          return 15;
      }
      return getHeadColumnInfo(name).getNumbersize();
  }
  public int getHeadColumnRoundSize(String name)
  {
      if(!this._datatype.containsKey(name)){
          return 5;
      }
      return getHeadColumnInfo(name).getRoundsize();
  }
  //-----------------------------------------------------------





  public void setColumntype(String name, Object value){
    this._coltype.put(name,value);
  }
  public RecObject getRecordColumntype() {
      RecObject recObj = new RecObject();
      recObj.setRecord(this._coltype);
      return recObj;
  }
  public void setOrderName(String[] name){
    this._3131 = name;
  }

  public void _19ginfo(String xxx){
      if(this._19G != null)
      {
          _19G.info(xxx, null);
      }else
      {
          System.out.println(xxx);
      }
  }

  public VOBJ setColumnKeyGen(String[] _6923, String _3998){

    VOBJ _170 = new VOBJ();
    ArrayList _3293 = new ArrayList();
    HashMap   _4358  = new HashMap();

    String _3962="";
    for(int i=0;i<this._2558.size();i++){
      _4358 = (HashMap)this._2558.get(i);
      for(int ii=0; ii < _6923.length;ii++){
        _3962= _3962 + (String)_4358.get(_6923[ii]);
      }
      _4358.put(_3998,_3962);
      _3293.add(_4358);
    }

    _170.setRecords(_3293);
    _170._setDispmode(_6248mode);
    return _170;
  }

  public String getSymbol(){
    return this._938;
  }


//--------- �߰� �۾�  Proto start
  public boolean ContainsColumnName(String name){
    boolean _2366 =false;
    HashMap _2594 = null;
    if(_2558 != null && _2558.size() > 0) {
      _2594 = (HashMap)_2558.get(0);
      if(_2594 != null)
        _2366 = _2594.containsKey(name);
    }

    return _2366;
  }

  public ArrayList getColumnNames(){
    ArrayList _7475 = new ArrayList();
    if(this._938 != null && !this._938.equals("")){
      for(int i=0;i<this._3131.length ;i++) _7475.add(_3131[i]);
    }else{
      if(this._2558!= null && this._2558.size() > 0){
        HashMap _2594 = (HashMap) _2558.get(0);
        if(_2594 != null){
          Iterator _4070 = _2594.keySet().iterator();
          while (_4070.hasNext()) {
            _7475.add(_4070.next().toString());
          }
        }
      }
    }
    return _7475;
  }

  public ArrayList getColumnNames(ArrayList _7136){
    ArrayList _2366 = new ArrayList();
    boolean find = false;
    if(this._2558!= null && this._2558.size() > 0){
      String _671="";
      HashMap _2594 = (HashMap) _2558.get(0);

      if(_2594 != null){
        Iterator _4070 = _2594.keySet().iterator();
        while (_4070.hasNext()) {
          find = false;
          _671 = _4070.next().toString();
          for(int i=0;i<_7136.size();i++){
            if(_7136.get(i).toString().equals(_671)) {
              find = true;
              break;
            }
          }
          if(find == false){
//             System.out.println("MORDER LIST:"+_671);
            _2366.add(_671);
          }
        }

        for(int i=0;i<_7136.size();i++){        //------------------
          //if(_2594.containsKey(clist.get(i).toString())){
           // System.out.println("MORDER LIST:"+clist.get(i).toString());
            _2366.add(_7136.get(i).toString());
          //}else{
           // System.out.println("VOBJ:508 REC CONTAIN MORDER LIST:"+clist.get(i).toString());
          //}
        }
      }
    }
    return _2366;
  }

  public void SetCodeConvert(String _6320,String _1172){
    HashMap _2594 = (HashMap)_2558.get(0);
    Iterator _4070 = _2594.keySet().iterator();
    ArrayList _7475 = new ArrayList();
    while(_4070.hasNext()){
      _7475.add(_4070.next().toString());
    }

    ArrayList _6599 = new ArrayList();
    HashMap _6602 = null;
    String _554 = null;
//    System.out.println("�ѱۺ�ȯ("+descharset+"-->"+srccharset+")");
    for(int i=0;i<_2558.size();i++) {
      _2594  = (HashMap)_2558.get(i);
      _6602 = new HashMap();
      for(int ii=0;ii<_7475.size();ii++){

        if(_2594.get( _7475.get(ii).toString()) != null){
          _554 = _2594.get( _7475.get(ii).toString()).toString();
        }else {
          _554="";
        }

        try {
          _554 = new String(_554.getBytes(_6320), _1172);
        }catch(java.io.UnsupportedEncodingException e) {
            this._19ginfo("FM:EjbComm:CodeCvt:�ѱۺ�ȯ ����("+_6320+"-->"+_1172+")");
          //System.out.println("FM:EjbComm:CodeCvt:�ѱۺ�ȯ ����("+_6320+"-->"+_1172+")");
        }
        _6602.put(_7475.get(ii).toString(),_554);
      }
      _6599.add(_6602);
    }
    this._2558 = _6599;
  }


  public void SetCodeConvertColumns(String[] _3338, String _6320,String _1172){

    HashMap _2594 = null;
    ArrayList _6599 = new ArrayList();
    String _554 = null;

    for(int i=0;i<_2558.size();i++) {
      _2594  = (HashMap)_2558.get(i);
      for(int ii=0;ii<_3338.length;ii++){

        if(_2594.get(_3338[ii]) != null) {
          _554 = _2594.get(_3338[ii]).toString();
        }else {
          _554="";
        }
        try {
          _554 = new String(_554.getBytes(_6320), _1172);
        }catch(java.io.UnsupportedEncodingException e) {
            this._19ginfo("FM:EjbComm:CodeCvt:�ѱۺ�ȯ ����("+_6320+"-->"+_1172+")");
          //System.out.println("FM:EjbComm:CodeCvt:�ѱۺ�ȯ ����("+_6320+"-->"+_1172+")");
        }
        _2594.put(_3338[ii],_554);
      }
      _6599.add(_2594);
    }
    this._2558 = _6599;
  }

  public int getColumnCnt(){
    int _2465=0;
    if(_2558 != null && _2558.size() > 0) {
      HashMap _2594 = (HashMap) _2558.get(0);
      _2465 = _2594.size();
    }
    return _2465;
  }

  public void getColumnNamesDisplay(HashMap _2594){
    Iterator _4070 = _2594.keySet().iterator();
    String disp = "";
    while(_4070.hasNext()){
      disp = disp + ":" + _4070.next();
    }
    _19ginfo("RECORD COLUMN NAMES : " + disp);
    //System.out.println("RECORD COLUMN NAMES : " + disp);
  }

  public void getColumnNamesDisplay(){
    String disp = "";
    if(_2558 != null && _2558.size() > 0) {
      HashMap _2594 = (HashMap) _2558.get(0);
      Iterator _4070 = _2594.keySet().iterator();
      while (_4070.hasNext()) {
        disp = disp + ":" + _4070.next();
      }
    }
    _19ginfo("RECORD COLUMN NAMES : " + disp);
    //System.out.println("RECORD COLUMN NAMES : " + disp);
  }

  public void addColumn(String _6989, String _305){
    HashMap _2594 = new HashMap();
    ArrayList _2633 = new ArrayList();
    if(_305 == null) { _305 = "";  }

    for(int i=0;i<_2558.size();i++){
      _2594 = (HashMap)_2558.get(i);
      _2594.put(_6989,_305);
      _2633.add(_2594);
    }
    _2558 = _2633;
  }


  public void DelColumn(String _6989){
    HashMap _2594 = new HashMap();
    ArrayList _2633 = new ArrayList();
    for(int i=0;i<_2558.size();i++){
      _2594 = (HashMap)_2558.get(i);
      _2594.remove(_6989);
      _2633.add(_2594);
    }
    _2558 = _2633;

  }


  public void DelColumns(String[] _6923){
    HashMap _2594 = new HashMap();
    ArrayList _2633 = new ArrayList();

    if(_2558 != null && _2558.size() > 0){
      for(int i=0;i<_2558.size();i++){
        _2594 = (HashMap)_2558.get(i);
        for(int ii=0;ii<_6923.length;ii++) {
          _2594.remove(_6923[ii]);
        }
        _2633.add(_2594);
      }
      _2558 = _2633;
    }
  }

  public void DelRecord(int num){
    this._2558.remove(num);
  }

  public String[] getColumnValue(String _6989){
    String[] _284 = null;
    HashMap _2594 = null;
    if(_2558.size() > 0){
      _284 = new String[_2558.size()];
      for(int i=0;i<_2558.size();i++){
        _2594 = (HashMap)_2558.get(i);
        _284[i] = _2594.get(_6989).toString();
      }
    }
    return _284;
  }

  public HashMap getHashRecord(){
    HashMap _2594 =  new HashMap();
    if(_2558 != null && _2558.size() > 0) {
      _2594 = (HashMap) _2558.get(this.current);
    }
    return _2594;
  }

  public VOBJ getRecVobj(){
    VOBJ _2366 = new VOBJ();
    HashMap _2594 =  new HashMap();
    if(_2558 != null && _2558.size() > 0) {
      _2594 = (HashMap) _2558.get(this.current);
    }
    _2366.addRecord(_2594);
    _2366._setDispmode(_6248mode);
    _2366._parentrownum = this.current;
    return _2366;
  }

  public VOBJ getRecVobj(String name){
    VOBJ _2366 = new VOBJ();
    HashMap _2594 =  new HashMap();
    if(_2558 != null && _2558.size() > 0) {
      _2594 = (HashMap) _2558.get(this.current);
    }
    _2366.addRecord(_2594);
    _2366.setName(name);
    _2366._setDispmode(_6248mode);
    _2366._parentrownum = this.current;
    return _2366;
  }

  public String getColumnFirstValue(String column){
    String _284 = null;
    HashMap _2594 = null;
    if(_2558.size() > 0){
        _2594 = (HashMap)_2558.get(0);
        _284 = _2594.get(column).toString();
    }
    return _284;
  }

  public String getColumnLastValue(String column){
    String _284 = null;
    HashMap _2594 = null;
    if(_2558.size() > 0){
        _2594 = (HashMap)_2558.get(_2564.size()-1);
        _284 = _2594.get(column).toString();
    }
    return _284;
  }

  public VOBJ setChangeKeyName(String column, String newcolumn){
    HashMap _2594 = null;
    ArrayList _2633 = new ArrayList();
    VOBJ _2468 = new VOBJ();
    boolean setflag = false;
    boolean setflag1= false;

    for(int i=0;i<_2558.size();i++){
      _2594 = (HashMap)_2558.get(i);

      if(_2594.containsKey(column)){
        _2594.put(newcolumn, _2594.get(column));
        _2594.remove(column);
        setflag=true;
        setflag1=true;
      }else{
        setflag=false;
      }

      if(setflag != setflag1){
          this._19ginfo("setChangeKeyNameó����:Record���� Mapping�� ���� �ʴ� Record�� �����մϴ�.");
          //System.out.println("setChangeKeyNameó����:Record���� Mapping�� ���� �ʴ� Record�� �����մϴ�.");
          return null;
      }else {
          _2633.add(_2594);
      }
    }

    _2468.setRecords(_2633);
    _2468._setDispmode(_6248mode);
    return _2468;
  }

  //----------- setEQColumnValues 2006.05.22(���� �ǿ� �ش� �ϴ� �κи� ó�� ����)
  private VOBJ setEQColumnValues(String _4412, String _4409, String column, String val){
//    System.out.println("setEQColumnValues 2006.05.22");
    HashMap _2594 = null;
    ArrayList _3293 = new ArrayList();
    VOBJ _2468 = new VOBJ();
    String _554="";
    if(_2558 != null) {
      for(int i=0;i<_2558.size();i++){
        _2594 = (HashMap)_2558.get(i);
        if(!this.isNullValue(_2594.get(_4412))){
          if(_4409.trim().equals(_2594.get(_4412).toString().trim())){
            _2594.put(column,val);
            _3293.add(_2594);
          }else {
            _3293.add(_2594);
          }
        }else {
          _3293.add(_2594);
        }
      }
    }

    _2468.setRecords(_3293);
    _2468._setDispmode(_6248mode);
    return _2468;
  }




  public VOBJ getEQRecords(HashMap columns){
//    System.out.println("getEQRecords 2006.04.20");
    HashMap _2594 = null;
    ArrayList _2633 = new ArrayList();
    VOBJ _2468 = new VOBJ();
    String _554="";
    Object _653 = null;

    Iterator _4070 = columns.keySet().iterator();
    ArrayList _7475 = new ArrayList();
    while(_4070.hasNext()){
      _7475.add(_4070.next().toString());
    }

    if(_2558 != null ) {
      boolean eqflag= false;
      for(int i=0;i<_2558.size();i++){
        _2594 = (HashMap)_2558.get(i);
        eqflag= false;
        for(int ii=0;ii<_7475.size();i++) {
          _653 = _2594.get(_7475.get(ii).toString());
          if(_653 == null) _554 ="";
          else _554 = (String)_653;

          if(columns.get(_7475.get(ii).toString()).toString().equals(_554)){
            eqflag = true;
          }else {
            break;
          }
        }
        if(eqflag)
          _2633.add(_2594);
      }
    }
    _2468.setRecords(_2633);
    _2468._setDispmode(_6248mode);
    return _2468;
  }


  public void addRecord(RecObject _2594){
    this._2558.add(_2594.getRecord());
  }

  public void addRecord(HashMap _2594){
    this._2558.add(_2594);
  }

  public void addRecord() {
    this._2558.add(this._2564);
  }


  public VOBJ getEQRecords(String column, String _305){
//    System.out.println("getEQRecords 2006.01.20");
    HashMap _2594 = null;
    ArrayList _2633 = new ArrayList();
    VOBJ _2468 = new VOBJ();
    String _554="";
    if(_2558 != null) {
      for(int i=0;i<_2558.size();i++){
        _2594 = (HashMap)_2558.get(i);

        if(_305 !=null && _2594 != null && !this.isNullValue(_2594.get(column))){   // 2006.01.19 �߰� if��
          if(_305.trim().equals(_2594.get(column).toString().trim())){
            _2633.add(_2594);
          }
        }
      }
      _2468.setRecords(_2633);
      _2468._setDispmode(_6248mode);
    }
    return _2468;
  }

  public VOBJ getNERecords(String column, String val){
    HashMap _2594 = null;
    ArrayList _2633 = new ArrayList();
    VOBJ _2468 = new VOBJ();

    for(int i=0;i<_2558.size();i++){
      _2594 = (HashMap)_2558.get(i);
      if(!this.isNullValue(_2594.get(column))){   // 2006.01.19 �߰� if��
        if (!val.trim().equals(_2594.get(column).toString().trim())) {
          _2633.add(_2594);
        }
      }
    }

    _2468.setRecords(_2633);
    _2468._setDispmode(_6248mode);
    return _2468;
  }


  public VOBJ getLTRecords(String column, String val){
    HashMap _2594 = null;
    ArrayList _2633 = new ArrayList();
    VOBJ _2468 = new VOBJ();
    String _554="";

    for(int i=0;i<_2558.size();i++){
      _2594 = (HashMap)_2558.get(i);
      if(!this.isNullValue(_2594.get(column))){   // 2006.01.19 �߰� if��
        try{
          if(Double.parseDouble(_2594.get(column).toString().trim()) < Double.parseDouble(val.trim())){
            _2633.add(_2594);
          }
        }catch(java.lang.NumberFormatException nfe){
            this._19ginfo("��ġ �׸� �񱳰� ���� �մϴ�.(" + _2594.get(column) +")");
            //System.out.println("��ġ �׸� �񱳰� ���� �մϴ�.(" + _2594.get(column) +")");
            return null;
        }
      }
    }
    _2468.setRecords(_2633);
    _2468._setDispmode(_6248mode);
    return _2468;
  }


  public VOBJ getGTRecords(String column, String val){
    HashMap _2594 = null;
    ArrayList _2633 = new ArrayList();
    VOBJ _2468 = new VOBJ();
    String _554="";

    for(int i=0;i<_2558.size();i++){
      _2594 = (HashMap)_2558.get(i);
      if(!this.isNullValue(_2594.get(column))){   // 2006.01.19 �߰� if��
        try{
          if(Double.parseDouble(_2594.get(column).toString().trim()) > Double.parseDouble(val.trim())){
            _2633.add(_2594);
          }
        }catch(java.lang.NumberFormatException nfe){
            this._19ginfo("��ġ �׸� �񱳰� ���� �մϴ�.(" + _2594.get(column) +")");
            //System.out.println("��ġ �׸� �񱳰� ���� �մϴ�.(" + _2594.get(column) +")");
            return null;
        }
      }
    }

    _2468.setRecords(_2633);
    _2468._setDispmode(_6248mode);
    return _2468;
  }


  public VOBJ getLQRecords(String column, String val){
    HashMap _2594 = null;
    ArrayList _2633 = new ArrayList();
    VOBJ _2468 = new VOBJ();
    String _554="";

    for(int i=0;i<_2558.size();i++){
      _2594 = (HashMap)_2558.get(i);
      if(!this.isNullValue(_2594.get(column))){   // 2006.01.19 �߰� if��
        try{
          if(Double.parseDouble(_2594.get(column).toString().trim()) <= Double.parseDouble(val.trim())){
            _2633.add(_2594);
          }
        }catch(java.lang.NumberFormatException nfe){
            this._19ginfo("��ġ �׸� �񱳰� ���� �մϴ�.(" + _2594.get(column) +")");
          //System.out.println("��ġ �׸� �񱳰� ���� �մϴ�.(" + _2594.get(column) +")");
          return null;
        }
      }
    }

    _2468.setRecords(_2633);
    _2468._setDispmode(_6248mode);
    return _2468;
  }


  public String getColumnMaxValue(String column){
    HashMap _2594 = null;
    String _2366 ="";

    double tmpval=0;
    _2594 = (HashMap)_2558.get(0);
    tmpval = Double.parseDouble(_2594.get(column).toString().trim());
    _2366 =_2594.get(column).toString().trim();

    for(int i=1;i<_2558.size();i++){
      _2594 = (HashMap)_2558.get(i);
      if(!this.isNullValue(_2594.get(column))){   // 2006.01.19 �߰� if��
        try{
          if(Double.parseDouble(_2594.get(column).toString().trim()) > tmpval){
            _2366 = _2594.get(column).toString().trim() ;
            tmpval = Double.parseDouble(_2594.get(column).toString().trim());
          }
        }catch(java.lang.NumberFormatException nfe){
            this._19ginfo("��ġ �׸� �񱳰� ���� �մϴ�.(" + _2594.get(column) +")");
            //System.out.println("��ġ �׸� �񱳰� ���� �մϴ�.(" + _2594.get(column) +")");
            return null;
        }
      }
    }
    return _2366;
  }

  public void setRecords(ArrayList _2558)
  {
    this._2558 = _2558;
  }
  public void setRecords(List _2558)
  {
      this._2558  = new ArrayList();
      for(int i=0;i<_2558.size();i++)
      {
          this._2558.add(_2558.get(i));
      }
  }
  public void setRecord(RecObject _2564) {
    this._2558.set(this.current,_2564.getRecord());
  }
  public void setRecord(HashMap _2564) {
    this._2558.set(this.current,_2564);
  }

  public void setRecord(int i,HashMap _2564) {
    this._2558.set(i,_2564);
  }

  public void setRecord(int i,RecObject _2564) {
    this._2558.set(i,_2564.getRecord());
  }

  public void addRecord(int i,RecObject _2564) {
    this._2558.add(i,_2564.getRecord());
  }

  public void addRecord(int i,HashMap _2564) {
    this._2558.add(i,_2564);
  }


  public int getRecordCnt() {
    if (_2558 != null) {
      return _2558.size();
    }
    else {
      return 0;
    }
  }

  public String getName() {
    return this._3188;
  }
  public void setName(String name) {
    this._3188 = name;
  }

  public String getAlias() {
      return this._alias;
  }
  public void setAlias(String name) {
      this._alias = name;
  }

  public boolean first() {
    this.current = -1;
    return false;
  }

  public boolean next() {
    if (_2558 != null && this.getRecordCnt() > 0 ) {
      if (_2558.size() > this.current + 1) {
        this.current = current + 1;
        return true;
      }
      else {
        return false;
      }
    }
    else {
      return false;
    }
  }

  public boolean last() {
    this.current = this._2558.size();
    return false;
  }

  public boolean priv() {
    if (_2558 != null) {
      if (this.current  >  0) {
        this.current = current - 1;
        return true;
      }
      else {
        return false;
      }
    }
    else {
      return false;
    }
  }

  public void makeRecord() {
    this._2564 = new HashMap();
  }

  public void setColumn(String name, Object value) {

    if (value != null)
      this._2564.put(name, value);
    else
      this._2564.put(name, "");
  }




  public ArrayList getRecords(){
	 
    return this._2558;
  }

  public HashMap cpRecord(){
    HashMap _2366 = null;
    HashMap _899 = null;
    if(_2558 != null && _2558.size() > 0) {
      _899 = (HashMap) _2558.get(this.current);
      _2366 = new HashMap();

      String _608 ="";
      ArrayList _7475 = this.getColumnNames();
      for(int i=0;i<_7475.size();i++){

          if(_899.get(_7475.get(i)) == null) _608="";
          else   _608 = _899.get(_7475.get(i)).toString();

          _2366.put(_7475.get(i).toString(),_608);
      }
    }
    return _2366;
  }
  public HashMap cpRecord(int _7100){
    HashMap _2366 = null;
    HashMap _899 = null;
    if (_7100 < _2558.size()) {
      _899 = (HashMap) _2558.get(_7100);
      _2366 = new HashMap();

      String _608 ="";
      ArrayList _7475 = this.getColumnNames();
      for(int i=0;i<_7475.size();i++){
        _608 = _899.get(_7475.get(i)).toString();
        _2366.put(_7475.get(i).toString(),_608);
      }
    }
    return _2366;
  }

  public RecObject firstRecord() {

        RecObject _2573 = null;
        HashMap _2594 =  new HashMap();
        if(_2558 != null && _2558.size() > 0) {
          _2594 = (HashMap) _2558.get(0);
        }
        _2573 = new RecObject();
        _2573.setRecord(_2594);
        _2573.setOrderName(this._3131);
        return _2573;
  }
  public RecObject lastRecord() {

      RecObject _2573 = null;
      HashMap _2594 =  new HashMap();
      if(_2558 != null && _2558.size() > 0 ) {
        _2594 = (HashMap) _2558.get(_2558.size()-1);
      }
      _2573 = new RecObject();
      _2573.setRecord(_2594);
      _2573.setOrderName(this._3131);
      return _2573;
  }
  public RecObject privRecord() {

    RecObject _2573 = null;
    HashMap _2594 =  new HashMap();
    if(_2558 != null && _2558.size() > 0 && this.current > 0) {
      _2594 = (HashMap) _2558.get(this.current-1);
    }
    _2573 = new RecObject();
    _2573.setRecord(_2594);
    _2573.setOrderName(this._3131);
    return _2573;
  }
  public RecObject getRecord()
  {

    RecObject _2573 = null;
    HashMap _2594 =  new HashMap();
    if(_2558 != null && _2558.size() > 0) {
      _2594 = (HashMap) _2558.get(this.current);
    }
    _2573 = new RecObject();
    _2573.setRecord(_2594);
    _2573.setOrderName(this._3131);
    return _2573;
  }
  public HashMap getRecordMap()
  {
      HashMap _2594 =  new HashMap();
      if(_2558 != null && _2558.size() > 0)
      {
          _2594 = (HashMap) _2558.get(this.current);
      }
      return _2594;
  }

  public RecObject getRecord(int _6656) {
    HashMap _2594 = null;
    RecObject _2573 = new RecObject();
    if (_6656 >= 0 && _6656 < _2558.size())
      _2594 = (HashMap) _2558.get(_6656);
    _2573.setRecord(_2594);
    _2573.setOrderName(this._3131);
    return _2573;
  }

  public int getCurrentRecordNumber(){
      if(this.getName().equals("R")){
          return this._parentrownum;
      }else {
          return this.current;
      }
  }

  public RecObject getSpans() {
    HashMap _2594 = (HashMap) this._1232.get(this.current);
    RecObject _2573 = new RecObject();
    _2573.setRecord(_2594);
    return _2573;
  }


  public boolean isEmpty() {
    if (_2558 != null) {
      if (_2558.size() > 0) {
        return false;
      }
      else {
        return true;
      }
    }
    else {
      return true;
    }
  }

  public boolean isNull() {
    if (_2558 != null) {
      return false;
    }
    else {
      return true;
    }
  }

  public boolean isNullValue(Object value) {
    if (value != null) {
      return false;
    }
    else {
      return true;
    }
  }

  public String NullStringClear(String value) {
    if (value != null) {
      return value;
    }
    else {
      return "";
    }
  }


//-----------------------------------------------------------------
//--------------  �߰� �ؾ� �� �κ�  END------------------------------
//-----------------------------------------------------------------
  public String getNumberFormat(String wsFormat, double value){
    String wsTemp ="";
    java.text.DecimalFormat df = new java.text.DecimalFormat(wsFormat);
    wsTemp = df.format(value);
    return wsTemp;
  }


  public String getSpaceAdd(int  len, String vals){
    String space="";

    for(int i=0;i<len-vals.length();i++)
      space = space + " " ;
    vals = vals + space;

    return vals;
  }

  public String getSpaceZero(int  len, String vals){
    String zero="";
    for(int i=0;i<len-vals.length();i++)
      zero = zero + "0" ;

    vals = zero + vals;
    return vals;
  }



  public VOBJ  setColumnValue(String kcolumn,String key ,String vcolumn, String value){
    ArrayList _7475 = new ArrayList();
    VOBJ _2366 = new VOBJ();
    HashMap _2594 = null;
    String _545="";
    for(int i=0;i<_7475.size();i++){
      _2594 = (HashMap)_7475.get(i);
      _545 = _2594.get(kcolumn).toString();
      if(_545.equals(key)){
        _2594.put(vcolumn,value);
      }
      _7475.add(_2594);
    }
    _2366.setRecords(_7475);
    _2366._setDispmode(_6248mode);
    return _2366;
  }


  public VOBJ  setColumnValue(String vcolumn, String value){
    ArrayList _7475 = new ArrayList();
    VOBJ _2366 = new VOBJ();
    HashMap _2594 = null;
    String _545="";
    for(int i=0;i<_7475.size();i++){
      _2594 = (HashMap)_7475.get(i);
      _2594.put(vcolumn,value);
      _7475.add(_2594);
    }
    _2366.setRecords(_7475);
    _2366._setDispmode(_6248mode);
    return _2366;
  }

  public boolean containCvtedcolumn(String name){
    boolean _2366 = false;
    if(this._7055 != null){
      StringTokenizer _521 = new StringTokenizer(this._7055,",");
      while(_521.hasMoreTokens()){
        if(name.equals(_521.nextToken().toString())) _2366=true;
      }
    }
    return _2366;
  }


  public HttpServletResponse getXmlDataName(HttpServletResponse response){
      HashMap _4358 = null;
      ArrayList _7475 = this.getColumnNames();
      String _7022="";
      boolean _5879=true;
      try {
        ArrayList _7136 =  this.getColumnNames();
        for(int i=0;i<_7136.size();i++){
          if(_5879 == true){
            _7022 = _7136.get(i).toString();
            _5879 = false;
          }else{
            _7022 = _7022 +":"+ _7136.get(i).toString();
          }
        }

        String vmsg = "";
        if(this.getRetmsg() != null && !this.getRetmsg().equals("")) vmsg = this.getRetmsg();
        response.getWriter().write("<"+this.getName()+" id=\""+this.getName()+"\""+ " RCNT=\""+this.getRecordCnt()+"\""  + " COLNMS=\""+_7022+"\""+ " VMSG=\""+ vmsg +"\" " +  " >");

        for(int i=0;i<_2558.size();i++){
          _4358 = (HashMap)_2558.get(i);
          response.getWriter().write("<REC id=\""+(i+1)+"\" IUDFLAG=\"\"  " +  " esp=\""+")"+"\"" + " >");
          for(int ii=0;ii<_7475.size();ii++){
            response.getWriter().write("<"+_7475.get(ii)+">");
            response.getWriter().write(_4358.get(_7475.get(ii)).toString());
            response.getWriter().write("</"+_7475.get(ii)+">");
          }
          response.getWriter().write("</REC>");
        }

        response.getWriter().write("</"+this.getName()+">");
      }catch(java.io.IOException e){
        e.printStackTrace();
      }
      return response;
 }


 public VOBJ getFFilter(String _column, String _value){
     VOBJ _rtn =null;
     if(_value.equals("MAX")){
       _rtn = this._getMaxRecord(_column);
     }else if(_value.equals("MIN")){
         _rtn = this._getMinRecord(_column);
     }else {
       _rtn = new VOBJ();
       _rtn.setRecords(this._2558);
     }
     return _rtn;
   }
   private VOBJ _getMinRecord(String _column)
   {
     HashMap _rec = null;
     ArrayList _rds = new ArrayList();
     VOBJ _ret = new VOBJ();
     if(this._2558 ==null && this._2558.size() < 2 ) {
       _ret.setRecords(this._2558);
       return _ret;
     }
     HashMap _retrec = null;
     double _minvalue=0;
     for(int i=0;i<this._2558.size();i++){
       _rec = (HashMap)this._2558.get(i);
       if(i==0 && !_rec.containsKey(_column)){
         _ret.setRetcode(-1);
         _ret.setRetmsg("�ּҰ� ��Ʈó��:�������� �ʴ� Column�Դϴ�.[" + _column +"]");
         return _ret;
       }
       Double _dbl= _getDouble (_rec, _column);
       if(_dbl == null) {
         _ret.setRetcode(-1);
         _ret.setRetmsg("�ּҰ� ��Ʈó��:��ġ�׸� �� ����.[" + _column +":" + _rec.get(_column) +"]");
         return _ret;
       }
       if(i==0) { _minvalue = _dbl.doubleValue(); _retrec =_rec;  continue; }
       if(_minvalue > _dbl.doubleValue() )
       {
         _minvalue = _dbl.doubleValue();
         _retrec =_rec;
       }
     }
     _ret = new VOBJ();
     _ret.addRecord(_retrec);
     return _ret;
   }
   private VOBJ _getMaxRecord(String _column)
   {
     HashMap _rec = null;
     ArrayList _rds = new ArrayList();
     VOBJ _ret = new VOBJ();
     if(this._2558 ==null && this._2558.size() < 2 ) {
       _ret.setRecords(this._2558);
       return _ret;
     }
     HashMap _retrec = null;
     double _maxvalue=0;
     for(int i=0;i<this._2558.size();i++){
       _rec = (HashMap)this._2558.get(i);
       if(i==0 && !_rec.containsKey(_column)){
         _ret.setRetcode(-1);
         _ret.setRetmsg("�ִ밪 ��Ʈó��:�������� �ʴ� Column�Դϴ�.[" + _column +"]");
         return _ret;
       }
       Double _dbl= _getDouble (_rec, _column);
       if(_dbl == null) {
         _ret.setRetcode(-1);
         _ret.setRetmsg("�ִ밪 ��Ʈó��:��ġ�׸� �� ����.[" + _column +":" + _rec.get(_column) +"]");
         return _ret;
       }
       if(i==0) { _maxvalue = _dbl.doubleValue(); _retrec =_rec;  continue; }
       if(_maxvalue < _dbl.doubleValue() )
       {
         _maxvalue = _dbl.doubleValue();
         _retrec =_rec;
       }
     }
     _ret = new VOBJ();
     _ret.addRecord(_retrec);
     return _ret;
   }
   private Double _getDouble(HashMap _rec, String _column)
   {
     Double _rtn =null;
     if(_rec.get(_column) == null) return  new Double(0);
     if(_rec.get(_column).toString().trim().equals("")) return  new Double(0);
     try{
       _rtn = new Double( Double.parseDouble(_rec.get(_column).toString()));
     }catch(NumberFormatException e){
       _rtn = null;
     }
     return _rtn;
  }

}

/*

  //----------- �߰� �۾� Proto end
    public void addParamNames(String name) {
      this._3338.add(name);
    }

  public void setNewColumnNames(Object names){
    if(names instanceof String){
      this._3338.add(names.toString().toUpperCase());
    }else if(names instanceof String[]){
      String[] tmpnames = (String[])names;
      for(int i=0;i<tmpnames.length;i++){
        this._3338.add(tmpnames[i].toUpperCase());
      }
    }else if(names instanceof ArrayList[]) {
      this._3338=(ArrayList)names;
    }
  }

  public String setNewRecords(HashMap param)  {
    //-- _2558 clear --> column name set --> setNewRecords
    int _2585 = 1;
    String _6989[] = null;
    boolean _1352 = false;
    String _2450 = null;


    if (param.get(_3338.get(0).toString()) instanceof String[]) {

      _6989 = (String[]) param.get(_3338.get(0).toString());
      _2585 = _6989.length;
      _1352 = false;

      for (int i = 1; i < _3338.size(); i++) {
        try {
          String[] _611 = (String[]) param.get(_3338.get(i).toString());
          if (_2585 != _611.length) {
            _2450 = "Input Array Size ����(Firstsize:Compsize) : " +
                _3338.get(0) + "(" + _2585 + "):" + _3338.get(i) + "(" +
                _611.length + ")";
            return _2450;
          }
        }
        catch (NullPointerException e) {
          _2450 = "ValueNullPoint : variable name : " + _3338.get(i);
          return _2450;
        }
      }
    } else if (param.get(_3338.get(0).toString()) instanceof String) {
      _2585 = 1;
      _6989 = new String[_2585];
      _1352 = true;
    }

    if (_1352) {
      this.makeRecord();
      for (int i = 0; i < _3338.size(); i++) {
        _2564.put(_3338.get(i), param.get(_3338.get(i).toString()).toString());
      }
      this._2558.add(0, _2564);
    } else {
      String[] _611 = null;
      for (int reccnt = 0; reccnt < _2585; reccnt++) {
        this.makeRecord();
        for (int columncnt = 0; columncnt < _3338.size(); columncnt++) {
          _611 = (String[]) param.get(_3338.get(columncnt).toString());
          _2564.put(_3338.get(columncnt), _611[reccnt]);
        }
        this._2558.add(reccnt, _2564);
      }
    }
    return _2450;
  }

*/
