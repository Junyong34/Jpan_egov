package egov.wizware.com;

import egov.wizware.msg.*;
import egov.wizware.log.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.text.*;
import java.util.*;

public class DOBJ implements java.io.Serializable {
  private HashMap  _3062 = null;
  private java.util.LinkedHashMap  _179 = null;
  private int      _2462= 0;
  private String   _2447 = null;
  private String   _2468name="";
  private String   _6293 = "0";
  private String   _6290 = "";
  private String   _6047 = "";
  private String   _2099="";
  private String   _2114="";
  private String   _3560="";
  private DLog     _dlg= null;
  public boolean   _6200=false;
  public TransFileInfo _6203=null;
  private String    _9234 = ""; //LIB-LC-ON : 해당 서버의 hostname
  // public ArrayList rtnobjname= new ArrayList();
  private int      _6248mode=0;
  private int      _2162no=0;
  private HashMap  _9235 = null;
  private int      _9233=0;
  private int      _92837=0;
  private String   _9283x="";
  private String   _3882="";
  private String   _9387="";//sys
  private HashMap  _renamedObject=null;

  private HashMap  _outMap = null;
  private String[] _inkeys = null;
  private String[] _outkeys = null;
  private boolean  _isPost = true;
  //----------------------------------
  private String _currid="";
  private String  _currname="";
  private String  _wastype=null;
  //-----------------------------------
  public Object request = null;

  //------------------------

  public DOBJ()
  {
    this._179 = new java.util.LinkedHashMap();
  }

  /*************************
   2012. 07 WIZCONFIG 정보를 Servlet의 init-param으로 전환처리 한다.
  */

 public String getWasType()
 {
     return _wastype;
 }
 public void setWasType(String _was)
 {
     _wastype = _was;
 }


  //*************** 2011. 02. 07일 추가 (Logging 처리부 추가함) COM package에 추가
  //-- 오류발생시 current_id정보와 current 논리명정보를 settting한다.
  //-- 사용처는 오류logging 정보처리시에 처리 된다.
  public String getCurrId()
  {
          return _currid;
  }
  public String getCurrName()
  {
          return _currname;
  }
  public void  setCurrStep(String id, String name)
  {
      _currid= id;
      _currname=name;
  }
  //*****************************************************************

  public void setIsPostMethod(boolean ispost){
          _isPost = ispost;
  }
  public boolean getIsPostMethod(){
          return _isPost;
  }
  public void setInkeys(String[] inkey)
  {
          _inkeys = inkey;
  }
  public void setOutkeys(String[] outkey)
  {
          _outkeys = outkey;
  }
  public String[] getOutkeys()
  {
       return this._outkeys;
  }
  public void setOutKeymap(HashMap _map)
  {
          _outMap = _map;
  }
  public void renameOutName(){
          String _name ="";
          if(_outMap != null){
                  Iterator itor = _outMap.keySet().iterator();
                  while(itor.hasNext()){
                          _name = itor.next().toString();

                          this.renameObject(_name, _outMap.get(_name).toString());
                  }
          }
  }
  public void deleteInKeyDatasets(){
          if(_inkeys != null){
                  for(int i=0;i<_inkeys.length;i++){
                          this._179.remove(_inkeys[i]);
                  }
          }
  }
  public void deleteOutKeyDatasets(){
          if(this._isPost == false) {
                  this._179.remove("S");
                  return;
          }
          this.dispRetObjectKeyNames();
          if(this._outkeys ==null || this._outkeys.length ==0) {
                  this._179 = new java.util.LinkedHashMap();
          }

          ArrayList _clist = this.getRetObjectKeys();

          this.dispRetObjectKeyNames();
          for(int i=0;i<_clist.size();i++)
          {
                  if(!isFoundOutKey(_clist.get(i).toString()))
                  {
                          this._179.remove(_clist.get(i).toString());
                  }
          }
  }
  private boolean isFoundOutKey(String name)
  {
          boolean rtn = false;
          for(int i=0;i< this._outkeys.length;i++ ){
                  if(name.equals(_outkeys[i])){
                          rtn = true;
                          break;
                  }
          }
          return rtn;
  }

  //------------------------------------- end


  public int _getRltype(){
      return _92837;
  }
  public void _setRltype(int _92837){
      this._92837 = _92837;
  }

  public String _getGnm(){
       return _9387;
   }
   public void _setGnm(String _9283x){
       this._9387 = _9283x;
   }

  public String _getRuser(){
       return _9283x;
   }
   public void _setRuser(String _9283x){
       this._9283x = _9283x;
   }
   public String _getRP(){
       return _3882;
   }
   public void _setRP(String _ssx){
       this._3882 = _ssx;
   }
   public int _9234(){
       return _9233;
   }
   public void _9220(int i){
      this._9233 =i;
  }
  public int getNextseq(){
      _2162no++;
      return _2162no;
  }
  public String getNextchars(){
      _2162no++;
      return _2162no+"";
  }
  public int getCurrentseq(){
      return _2162no;
  }
  public String getCurrentchars(){
      return _2162no+"";
  }
  public int clearSeq(){
      _2162no=0;
      return _2162no;
  }
  public void setSeq(int _2162){
      _2162no = _2162;
  }
  public boolean isRtnObjectFoundname(String _3374){
      boolean _2366 = false;
      Iterator _4070 = this._179.keySet().iterator();
      while(_4070.hasNext()){
          if(_3374.equals(_4070.next().toString())){
              _2366 = true;
              break;
          }
      }
      return _2366;
  }
  public boolean containKey(String _4001){
    return this._179.containsKey(_4001);
  }

  public void dispRetObjectKeyNames(){
    Iterator _4070 = this._179.keySet().iterator();
    while(_4070.hasNext()){
      System.out.print(":"+_4070.next());
    }
    System.out.println("");
  }
  public String getRetObjectKeyNames(){
    String _2366="";
    Iterator _4070 = this._179.keySet().iterator();
    while(_4070.hasNext()){
      _2366 = _2366+_4070.next().toString();
    }
    return _2366;
  }
  public ArrayList getRetObjectKeys()
  {
    ArrayList _7475 = new ArrayList();
    Iterator _4070 = this._179.keySet().iterator();
    while(_4070.hasNext()){
      _7475.add(_4070.next().toString());
    }
    return _7475;
  }



  public int getDispmode()
  {
       return this._6248mode;
  }
  public void setDispmode(int _6248)
  {
       _6248mode = _6248;
  }
  public void setServicepackage(String name)
  {
    this._2099 = name;
  }
  public String getServicepackage()
  {
    return this._2099;
  }
  public void setServicename(String _3374)
  {
    this._2114 = _3374;
  }
  public String getServicename(){
    return this._2114;
  }

  public void setMethodname(String _3374) {
    this._3560 = _3374;
  }
  public String getMethodname(){
    return this._3560;
  }

  public void setErrorMessage(String _6047) {
    this._6047 = _6047;
  }
  public String getErrorMessage() {
    return this._6047;
  }

  public void setDetailMessage(String _1073) {
    this._6290 = _1073;
  }
  public String getDetailMessage() {
    return _6290;
  }

  public void setDetailCode(String _7085) {
    this._6293 = _7085;
  }
  public String getDetailCode() {
    return this._6293;
  }

  public void setParam(HashMap _3035) {
    this._3062 = _3035;
  }
  public HashMap getParam() {
    return this._3062;
  }

  public Object getParmname(String _3374) {
    return this._3062.get(_3374);
  }

  public void setRetmsg(String _3437) {
    this._2447 = _3437;
  }
  public void setRetmsg(Exception _3437) {
      this._2462 = -1;
      this._2447 = _3437.getMessage();
  }
  public void setRetmsg(Exception _3437,String msg) {
      this._2462 = -1;
      this._2447 = msg;
  }
  public String getRetmsg() {
   if(this._2447 ==null ) this._2447  ="";
    return this._2447;
  }

  public void setRtncode(int _7085) {
    this._2462 = _7085;
  }
  public int getRtncode() {
    return this._2462;
  }
  public void setRtnname(String _7085) {
    this._2468name = _7085;
  }
  public String getRtnname() {
    return this._2468name;
  }

  public void setGVValue(String _3986, String _305){
      if(_9235 == null) _9235 = new HashMap();
      _9235.put(_3986, _305);
  }
  public String getGVValue(String _3986){
      if(_9235 == null)  return "";
      return _9235.get(_3986).toString();
  }
  public String getGVString(String _3986){
      if(_9235 == null)  return "";
      return _9235.get(_3986).toString();
  }
  public double getGVNumber(String _3986){
      if(_9235 == null)  return 0;
      if(_9235.get(_3986)== null) return 0;
      if(_9235.get(_3986).toString().trim().equals("")) return 0;
      return Double.parseDouble(_9235.get(_3986).toString());
  }
  public int getGVInt(String _3986){
      if(_9235 == null)  return 0;
      if(_9235.get(_3986)== null) return 0;
      if(_9235.get(_3986).toString().trim().equals("")) return 0;
      return Integer.parseInt(_9235.get(_3986).toString());
  }
  public long getGVLong(String _3986){
      if(_9235 == null)  return 0;
      if(_9235.get(_3986)== null) return 0;
      if(_9235.get(_3986).toString().trim().equals("")) return 0;
      return Long.parseLong(_9235.get(_3986).toString());
  }

  public void removeObject(String _3986)
  {
    this._179.remove(_3986);
  }

  //-------------- 2011. 04. 06일 추가  MPD,MIUD에서 RESET기능추가 -----
  public void resetObject(String _objs)
  {
      StringTokenizer stoken = new StringTokenizer(",");
      while(stoken.hasMoreTokens())
      {
          removeObject(stoken.nextToken());
      }
  }
  //-----------------------------------------------------------------

  public void setRetObject(String _3986, Object _281)
  {
      if(_281 instanceof VOBJ)
      {
          ((VOBJ)_281).setName(_3986);
      }

      this._179.put(_3986, _281);
  }
  public void setRetObject(VOBJ _227)
  {
    this._179.put(_227.getName(), _227);
  }

  public  VOBJ getRetObject(String _3986)
  {
      VOBJ _2444 = new VOBJ();
      if(this._179.containsKey(_3986))
      {
          _2444 = (VOBJ)this._179.get(_3986);
      }
      if(_2444 == null)
      {
          _2444 = new VOBJ();
      }
      _2444._setDispmode(_6248mode);
      _2444._19lg(this._dlg);
      return _2444;
  }
  public  VOBJ getRetObjectCopy(String _3986)
  {

      VOBJ _vobj = new VOBJ();
      if(this._179.containsKey(_3986))
      {
          _vobj = (VOBJ)this._179.get(_3986);
      }
      if(_vobj == null)
      {
          _vobj = new VOBJ();
      }

      HashMap rec = null;
      VOBJ _rvobj = new VOBJ();
      _rvobj.setName("");
      _vobj.first();
      while(_vobj.next())
      {
          rec = _vobj.cpRecord();
          _rvobj.addRecord(rec);
      }
      _rvobj._setDispmode(_6248mode);
      _rvobj._19lg(this._dlg);
      return _rvobj;
  }



  public void renameObject(String _3986, String _2495){
      if(this._179 != null){
          VOBJ _2444 = (VOBJ)this._179.get(_3986);
          this._179.remove(_3986);
          if(_2444 != null){
              _2444.setName(_2495);
              setRetObject(_2444);
          }
      }
  }


  public  Object getObject(String _3986) {
    return this._179.get(_3986);
  }

  private VOBJ _6710(VOBJ _1175){
    VOBJ _227=null;
    if(_1175 != null && _1175.getRecordCnt() > 0){
      _227 = new VOBJ();
      _1175.first();
      while (_1175.next()) {
        _227.addRecord(_1175.cpRecord());
      }
      _227.setName(_1175.getName());
    }
    _227._setDispmode(_6248mode);
    _227._19lg(this._dlg);
    return _227;
  }

  public void setSQLMessage(ErrMsgFilter _6062,int _3428){
    if (_6062.getExceptionName().indexOf("java.sql.SQLException") != -1) {
      this.setRtncode( -1);
    }else {
      this.setRtncode(_3428);
      this.setDetailCode( _6062.getSQLExceptionCode() ) ;
    }
  }

  public void setSQLMessage(ErrMsgFilter _6062, int _3428, String _6284){
    if (_6062.getExceptionName().indexOf("java.sql.SQLException") != -1) {
      this.setRtncode( -1);
    }else {
      this.setRtncode(_3428);
      this.setDetailCode( _6062.getSQLExceptionCode() ) ;
      if(_6284== null) _6284="";
      this.setDetailMessage(_6284);
    }
  }

  public void setSQLMessage(int _3428, int _6293){
    this.setRtncode(_3428);
    this.setDetailCode(_6293+"");
  }

  public void setSQLMessage(int _3428, String _6293,String _6284){
    this.setRtncode(_3428);
    this.setDetailCode(_6293);
    if(_6284== null) _6284="";
    this.setDetailMessage(_6284);
  }


  public void _renameRetObjectXXXX(String _3986, String _2495){
      VOBJ _227 = getRetObject(_3986);
      removeObject(_3986);
      setRetObject(_2495, _227);
  }



  //---------
  //-- 2010. 05. 03 pex처리시 중복명칭 처리 추가 out측 name이 존재할때 overwrite방지
  public void _setRenameObject(String _rename, Object _value)
  {
     if(_renamedObject == null) this._renamedObject = new HashMap();
     this._renamedObject.put(_rename, _value);
   }
   public void _removeRenameObject(String _rename)
   {
     this._renamedObject.remove(_rename);
   }
   public void _beforeCalledRetObject(String _in, String _out){
     if(this.containKey(_out)){
       VOBJ _vobjo = getRetObject(_out);
       _setRenameObject(_out, _vobjo);
     }
     VOBJ _vobj = getRetObject(_in);
     setRetObject(_out, _vobj);

  }
  public void _afterCalledRetObject(String _in, String _out)
  {
    VOBJ _vobj = getRetObject(_in);
    _setRenameObject(_out, _vobj);
    if(this._renamedObject.containsKey(_in)){
      VOBJ _vobjo = (VOBJ)_renamedObject.get(_in);
      setRetObject(_in, _vobjo);

    }else {
      removeObject(_in);
    }
  }
  //-- 2010. 05. 03 pex처리시 중복명칭 처리 추가 out측 name이 존재할때 overwrite방지 END
  //-----------

  public String DisplayDate(String _6347,String _date){

    String _746="";
    if(_date.length() != 8 ) return null;

    _746 = _746 + _date.substring(0,4) + _6347;
    _746 = _746 + _date.substring(4,6) + _6347;
    _746 = _746 + _date.substring(6);

    return _746;
  }

  public static String getCurrentDate(String _5807)
  {
      Date _524 = new Date();
      SimpleDateFormat _yyyyMMddE = new SimpleDateFormat(_5807);
      String _80;
      _80 = _yyyyMMddE.format(_524);

      return _80;
  }

  public static String getCurrentDate()
  {
      Date _524 = new Date();
      SimpleDateFormat _yyyyMMddE = new SimpleDateFormat("yyyy/MM/dd");
      String _80;
      _80 = _yyyyMMddE.format(_524);

      return _80;
  }

  public static String getCurrentDateDay()
  {
      Date _524 = new Date();
      SimpleDateFormat _yyyyMMddE = new SimpleDateFormat("yyyy/MM/dd / E요일");
      String _80;
      _80 = _yyyyMMddE.format(_524);

      return _80;
  }


   public String getKSC5601(String _281, String _7190){

     if(_281 == null){
       _281="";
     }else {
       try {
        _281 = new String(_281.getBytes(_7190), "KSC5601" );
       }catch(java.io.UnsupportedEncodingException e) {
         System.out.println("FM:CodeCvt:한글변환 오류(getKSC5601-->"+_7190+")");
       }
     }
     return _281;
   }

   public String get8859(String _281, String _7190)
   {
     if(_281 == null)
     {
       _281="";
     }
     else
     {
       try
       {
         _281 = new String(_281.getBytes(_7190), "8859_1" );
       }
       catch(java.io.UnsupportedEncodingException e)
       {
         System.out.println("FM:CodeCvt:한글변환 오류(get 8859_1-->"+_7190+")");
       }
     }
     return _281;
   }

   public void Dlg(String head, String path, String filenm)
   {
       //10M가 단위로 파일 6개가 ring형으로 생성되면 마지막 index가 5 처음 인덱스가 1인 파일명이 생성된다
       this._dlg = (DLog) DLog.getLogger(head, "10000000", 5, 1, path, filenm, true );
   }
   public void Dlg(String path, String filenm)
   {
       this._dlg = (DLog) DLog.getLogger("PBUILDER", "10000000", 5, 1, path, filenm, true );
   }
   public void Dlg(String filenm)
   {
       this._dlg = (DLog) DLog.getLogger("PBUILDER", "10000000", 5, 1, "./log", filenm, true );
   }
   public void Dlg()
   {
       this._dlg = (DLog) DLog.getLogger("PBUILDER", "10000000", 5, 1, "./log", "pb.log", true );
   }
   public void _setdlg(DLog _dlg){
       this._dlg = _dlg;
   }
   public DLog _getDlg(){
       return this._dlg;
   }
   public void DlgInfo(String xxx){
       if(_dlg != null) {
           _dlg.info(xxx, null);
       }else {
           System.out.println(xxx);
       }
   }
   public void DlgError(String xxx, Exception e){
       if(_dlg != null)
       {
           _dlg.error(xxx, e);
       }else
       {
           System.out.println(xxx);
           System.out.println(e.getMessage());
       }
   }

   //dobj.getPage();
   //JSTL <PAGE-INATE-TAG처리 정보>
   public HashMap getPage(int recordcnt, int maxrow, String curidx)
   {
       int maxgroup=10;
       int pagecount = (recordcnt / maxrow) + 1;
       HashMap hmap = new HashMap();
       hmap.put("TOTAL", recordcnt + "");
       hmap.put("MAXROW", maxrow + "");
       hmap.put("PAGECOUNT", pagecount + "");

       ArrayList nlist = new ArrayList();
       HashMap rmap = null;
       for (int i = 0; i < maxgroup; i++)
       {
           rmap = new HashMap();
           rmap.put("INDEX", i + "");
           nlist.add(rmap);
       }
       hmap.put("PAGEINDEX", nlist);

       if (curidx == null || curidx.trim().equals(""))
       {
           hmap.put("CURRENT", "0");
       }
       else
       {
           hmap.put("CURRENT", curidx);
       }

       int cnt = recordcnt % 10;
       int amt = recordcnt / 10;
       System.out.println("===============================::::::::::::::>>>>>>>>" + cnt +":" + amt +":" + recordcnt);

       return hmap;
   }
   public HashMap getPage(int recordcnt, int maxrow, String curidx, int curgrp)
   {
       int maxgroup=10;
       int pagecount = (recordcnt / maxrow) + 1;
       HashMap hmap = new HashMap();
       hmap.put("TOTAL", recordcnt + "");
       hmap.put("MAXROW", maxrow + "");
       hmap.put("PAGECOUNT", pagecount + "");

       ArrayList nlist = new ArrayList();
       HashMap rmap = null;
       int gidx=0;
       gidx = curgrp * maxrow;
       for (int i = gidx; i < gidx + 10; i++)
       {
           if(i == pagecount) break;
           rmap = new HashMap();
           rmap.put("INDEX", i + "");
           nlist.add(rmap);
       }
       hmap.put("PAGEINDEX", nlist);

       if (curidx == null || curidx.trim().equals(""))
       {
           hmap.put("CURRENT", "0");
       }
       else
       {
           hmap.put("CURRENT", curidx);
       }

       if(pagecount > 10)
       {
           if(curgrp ==0)
           {
               hmap.put("GRPLEFT" , "0");
               hmap.put("GRPLEFTINDEX" , "0");
               hmap.put("GRPRIGHT", "1");
               hmap.put("GRPRIGHTINDEX", "10");
           }
           else
           {
               hmap.put("GRPLEFT" , (curgrp-1)+"");
               hmap.put("GRPLEFTINDEX" , ((curgrp-1) * maxrow) + 10  +"");

               int grpcnt = pagecount / maxgroup;
               int modex  = 0;
               //if( (pagecount % maxgroup) > 0) modex =1;
               //if( (curgrp+1) > (grpcnt + modex) )

               if( ( recordcnt % (maxrow * maxgroup)) > 0) modex =1;
               if( curgrp+1  >= ( ( recordcnt / (maxrow * maxgroup)) + modex) )
               {
                   hmap.put("GRPRIGHT" ,  "0");
                   hmap.put("GRPRIGHTINDEX" , "0");
               }
               else
               {
                   hmap.put("GRPRIGHT" , (curgrp+1)+"");
                   hmap.put("GRPRIGHTINDEX" , (curgrp * maxgroup+1) +"");
               }
           }
       }
       else
       {
           hmap.put("GRPLEFT" , "0");
           hmap.put("GRPLEFTINDEX" , "0");
           hmap.put("GRPRIGHT", "0");
           hmap.put("GRPRIGHTINDEX" , "0");
       }
       hmap.put("GRPCURRENT", curgrp + "");

       System.out.println(hmap);
       int cnt = recordcnt % 10;
       int amt = recordcnt / 10;
       System.out.println("===============================::::::::::::::>>>>>>>>" + cnt +":" + amt +":" + recordcnt);

       return hmap;
   }

   public void _92384()
   {
       String hnm="";
       try
       {
           Runtime rt = java.lang.Runtime.getRuntime();
           Process proc = rt.exec("hostname");
           int x;
           while((x = proc.getInputStream().read()) != -1)
           {
               hnm += (char)x;
           }
           proc.waitFor();
       }
       catch(Exception ex)
       {
       }
       _9234 = hnm;
   }
   public String _92837()
   {
       return _9234;
   }

}
