package egov.wizware.ria;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.*;
import egov.wizware.com.*;
import egov.wizware.dao.*;
import java.io.*;
import java.io.*;
import java.text.SimpleDateFormat;
import com.gauce.*;
import com.gauce.http.*;
import com.gauce.io.*;

import com.tobesoft.platform.*;
import com.tobesoft.platform.data.*;
//import oz.fxapi.dm.*;


public class Xinternet
{

  public Xinternet()
  {
  }


  private String getDupFileNewName(String path,String filename){
       System.out.println(path + ":" + filename);
       String newname=filename;
       String newpath="";
       String lastname="";
       String midname="";
       if(filename.indexOf(".")!= -1){
           midname = filename.substring(0,filename.lastIndexOf("."));
           lastname= filename.substring(filename.lastIndexOf("."));
       }
       if(path.trim().substring(path.trim().length()-1).equals("/")){
           newpath = path ;
       }else{
           newpath = path +"/";
       }

       String strDateForm="";
       SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
       strDateForm = sdf.format(new java.util.Date(System.currentTimeMillis()));
       newname = midname+ "V" + strDateForm + lastname;
       System.out.println("FILE NEW NAME:"+ newpath+newname);
       return newpath+newname;

  }

  private String[] writeToFile(byte[] file , String filename) {
        String[] _2366 = new String[4];
        int retsize=0;
        CONFIG _6812 = new CONFIG();
        OutputStream _8403 = null;
        String filefullpath ="";
        String path="";
        String filenamex="";
        try{
            path = _6812.getPathfinderConfig("UploadTemp");
            filefullpath = this.getDupFileNewName(path, filename);
            filenamex = filefullpath.substring(filefullpath.lastIndexOf("/")+1);

            ByteArrayInputStream _4271 = new ByteArrayInputStream(file);
            _8403 = new FileOutputStream(filefullpath);
            byte[] buffer = new byte[8192];
            int size = buffer.length;
            while (true) {
                int n = _4271.read(buffer);
                if (n <= 0) {
                    break;
                }
                retsize+=n;
                _8403.write(buffer, 0, n);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                _8403.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        _2366[0] = filefullpath;
        _2366[1] = retsize+"";
        _2366[2] = path;       // path
        _2366[3] = filenamex;  //파일 name
        System.out.println("size:" +path +":" + filenamex +":"+ retsize +":" + filefullpath);
        return _2366;
  }


  private Dataset setColumninfo1000(VOBJ  _227, String _7190){
      ArrayList _7109 = null;
      _7109 = _227.getColumnNames();
      Dataset _6155 = new Dataset(_227.getName(), _7190);
      for(int i=0;i<_7109.size();i++){
        _6155.addColumn(_7109.get(i).toString(),ColumnInfo.COLUMN_TYPE_STRING, (short)255);
      }
      String _3374="";
      _227.first();
      while(_227.next()){
        int _8067 = _6155.appendRow();
        for(int i=0;i<_7109.size();i++){
          _3374=_7109.get(i).toString();
          _6155.setColumn(_8067, _3374,_227.getRecord().get(_3374));

        }
      }
      return _6155;
    }

  private String getCodecvt(String _str){
      try {
          _str = new String(_str.getBytes("KSC5601"), "UTF-8");
      }catch(Exception e){
          e.printStackTrace();
      }
      return _str;
  }


  public SQLObject makeQryCmdInfo() {

    SQLObject sqlx = new SQLObject();
    String query ="";
    query ="SELECT * FROM KOSH.MISAWON " ;

    sqlx.setSql(query);


    return sqlx;
  }


  public HashMap makeJeonmunVobj(HttpServletRequest _req, HttpServletResponse _res) {
    HashMap _3062 = new HashMap();
    HashMap _719 = new HashMap();
    HashMap _2564 = null;
    ArrayList _2558 = new ArrayList();
    HashMap _7037 = new HashMap();
    ArrayList _6935 = new ArrayList();
    int _3584 =0;

    Enumeration _e = _req.getParameterNames();
    String _4001 = null;
    String[] _3965 = null;
    String _3968 = "";
    while (_e.hasMoreElements()) {
      _4001 = (String) _e.nextElement();
      _3965 = _req.getParameterValues(_4001);

      if (_3965.length < 2) {
        _3968 = _3965[0];
        _3062.put(_4001, _3968);
      } else {
        _3062.put(_4001, _3965);
      }

      _7037.put(_4001,""+_3965.length);
      _719.put(_4001, _3965);
      _6935.add(_4001);
      if(_3584 < _3965.length) _3584 = _3965.length;
    }

    String[] _551=null;
    for(int i=0;i<_3584;i++){
      _2564 = new HashMap();
      for(int ii=0;ii<_6935.size();ii++){
        _551 = (String[])_719.get(_6935.get(ii).toString());
        if(_551.length > i){
          _2564.put(_6935.get(ii),_551[i]);
        }else{
          _2564.put(_6935.get(ii),_551[_551.length-1]);
        }
      }
      _2558.add(_2564);
    }

    VOBJ _227 = new VOBJ();
    _227.setRecords(_2558);
    _227.setColumnRecordCounts(_7037);
    _3062.put("JVOBJECTXX",_227);
    return _3062;
  }


  public DOBJ inputObject1000(HttpServletRequest request, HttpServletResponse response){
      DOBJ dobj = new DOBJ();
      //-- 전문 data생성 처리 기능
      HashMap param = new HashMap();
      HashMap   record = null;
      ArrayList records= null;
      VOBJ     vobj   = null;

      /** Input DataSet 부분 초기화 **/
      DatasetList indl = new DatasetList();
      int maxlen=0;
      boolean makeflag = false;
      boolean addflag = false;
      try{
        CONFIG CONF = new CONFIG();
        String charset = CONF.getPathfinderConfig("InputInterfaceCharSet");
        PlatformRequest pReq = new PlatformRequest(request, charset);   //Q/A 대상 해당 Character-set 구성 확인

        pReq.receiveData();
        indl = pReq.getDatasetList();
        String _datesetid="";
        System.out.println("dataset size:"+indl.size() + ":");
        for(int i=0;i<indl.size();i++){

          records = new ArrayList();
          Dataset ds = indl.getDataset(i);

          for(int x=0;x<ds.getDeleteRowCount();x++){
              record= new HashMap();
              for(int y=0;y<ds.getColumnCount();y++){
                  ColumnInfo cinfo = ds.getColumnInfo(y);
                  String value ="";

                  //System.out.println("cinfo.getColumnType():" + cinfo.getId()+ ":" + cinfo.getColumnType() );
                  if(cinfo.getColumnType() == cinfo.COLUMN_TYPE_INT){
                      if(ds.getDeleteColumn(x,cinfo.getId()) != null && ds.getDeleteColumn(x,cinfo.getId()).getInteger() != null) {
                           value = ds.getDeleteColumn(x, cinfo.getId()).getInteger() +  "";
                      }
                  }else  if(cinfo.getColumnType() == cinfo.COLUMN_TYPE_LONG){
                      if(ds.getDeleteColumn(x,cinfo.getId()) != null && ds.getDeleteColumn(x,cinfo.getId()).getInteger() != null ) {
                          value = ds.getDeleteColumn(x,cinfo.getId()).getInteger() +"";
                      }
                  }else  if(cinfo.getColumnType() == cinfo.COLUMN_TYPE_DECIMAL){
                      if(ds.getDeleteColumn(x,cinfo.getId())!= null && ds.getDeleteColumn(x,cinfo.getId()).getDouble() != null) {
                          value = ds.getDeleteColumn(x, cinfo.getId()).getDouble() + "";
                      }
                  }else {
                      if(ds.getDeleteColumn(x,cinfo.getId()) != null && ds.getDeleteColumn(x,cinfo.getId()).getString() != null) {
                          value = ds.getDeleteColumn(x, cinfo.getId()).getString();
                      }
                  }
                  record.put(cinfo.getId().toUpperCase(),value);
              }
              record.put("IUDFLAG","D");
              records.add(record);
          }

          for(int x=0;x<ds.getRowCount();x++) {
            record= new HashMap();

            for(int y=0;y<ds.getColumnCount();y++){
              ColumnInfo cinfo = ds.getColumnInfo(y);
              String value ="";
              //System.out.println("cinfo.getColumnType():" + cinfo.getId()+ ":" + cinfo.getColumnType() );
              if(cinfo.getColumnType() == cinfo.COLUMN_TYPE_INT){
                  if(ds.getColumn(x,cinfo.getId())!= null &&  ds.getColumn(x,cinfo.getId()).getInteger() != null)
                      value = ds.getColumn(x,cinfo.getId()).getInteger() +"";

              }else  if(cinfo.getColumnType() == cinfo.COLUMN_TYPE_LONG){
                  if(ds.getColumn(x,cinfo.getId())!= null && ds.getColumn(x,cinfo.getId()).getInteger() != null)
                      value = ds.getColumn(x,cinfo.getId()).getInteger() +"";

              }else  if(cinfo.getColumnType() == cinfo.COLUMN_TYPE_DECIMAL){
                  if(ds.getColumn(x,cinfo.getId())!= null && ds.getColumn(x,cinfo.getId()).getDouble()!= null)
                      value = ds.getColumn(x,cinfo.getId()).getDouble() +"";

              }else {
                  if( ds.getColumn(x,cinfo.getId())!= null && ds.getColumn(x,cinfo.getId()).getString()!= null)
                      value = ds.getColumn(x,cinfo.getId()).getString();

              }

              record.put(cinfo.getId().toUpperCase(),value);
            }

            String rowstatus = ds.getRowStatus(x);
            if( rowstatus.equals("insert") == true ) {
              record.put("IUDFLAG","I");
            }else if(rowstatus.equals("update") == true ) {
              record.put("IUDFLAG","U");
            }else if(rowstatus.equals("delete") == true ) {
              record.put("IUDFLAG","D");
            }
            records.add(record);
          }

          vobj = new VOBJ();
          vobj.setRecords(records);
          vobj.setName(ds.getId());
          dobj.setRetObject(vobj);

        }

        /** List 획득 및 Dataset, 변수 획득 **/
        VariableList invl = new VariableList();
        invl = pReq.getVariableList();
        vobj = new VOBJ();

        Enumeration e = request.getParameterNames();
        String key = null;
        String[] keyVals = null;
        while (e.hasMoreElements()) {
          key = (String) e.nextElement();
          if(key.equals("SYSID")) {
            param.put("SYSID",request.getParameter("SYSID"));
            continue;
          }
          if(key.equals("MENUID")) {
            param.put("MENUID",request.getParameter("MENUID"));
            continue;
          }
          if(key.equals("EVENTID")) {
            param.put("EVENTID",request.getParameter("EVENTID"));
            continue;
          }
          System.out.println("SYSID=" + param.get("SYSID") + ":MENUID=" + param.get("MENUID") + ":EVENTID=" + param.get("EVENTID"));

          keyVals = request.getParameterValues(key);
          if (keyVals.length ==1 ) {
            //------ USERID
            //------ IPADDR  추가해서 TEST필요 하다.
            if(vobj.getRecordCnt() > 0){
              vobj.first();
              while(vobj.next()){
                vobj.getRecord().put(key.toUpperCase(),keyVals[0]);
              }
            }else {
              record = new HashMap();
              record.put(key.toUpperCase(),keyVals[0]);
              vobj.addRecord(record);
            }

          } else {

            if(vobj.getRecordCnt() >= keyVals.length){
              for(int i=0;i<keyVals.length;i++){
                vobj.getRecord(i).put(key.toUpperCase(),keyVals[i]);
              }
            } else {

              if(vobj.getRecordCnt() > 0){
                int ccnt=0;
                vobj.first();
                while(vobj.next()){
                  vobj.getRecord().put(key.toUpperCase(),keyVals[ccnt]);
                  ccnt++;
                }
                for(int i=ccnt;i<keyVals.length;i++){
                  record = new HashMap();
                  record.put(key.toUpperCase(),keyVals[i]);
                  vobj.addRecord(record);
                }
              } else {
                for(int i=0;i<keyVals.length;i++){
                  record = new HashMap();
                  record.put(key.toUpperCase(),keyVals[i]);
                  vobj.addRecord(record);
                }
              }
            }
          }
        }
        if(vobj != null && vobj.getRecordCnt() >0 ) {
          dobj.setRetObject("S",vobj);
        }
      }catch(Exception e){
        e.printStackTrace();
      }
      dobj.setParam(param);
      if(records!= null)   vobj.setRecords(records);

      //-----별도 inteface를 이용하여 외부에서 setting가능하게 한다. global object 생성 처리를 Module단위로 가능하게 설정한다.

      VOBJ vobjy = new VOBJ();
      HashMap grec = new HashMap();
      /** 부분 초기화 **/
      String requestaddr = request.getRemoteAddr();
      String userid      = "999999";

      grec.put("IPADDR",requestaddr);
      grec.put("USERID",userid);
      vobjy.addRecord(grec);
      vobjy.setName("G");
      dobj.setRetObject(vobjy);
      //------------------------------------------------------------------------------------------------------
      return dobj;
    }


    public DOBJ inputObjectGauce(HttpGauceRequest req) throws Exception {
        DOBJ _dobj = new DOBJ();
        GauceDataSet[]  gdsets = req.getGauceInputStream().readAllOutput();
        GauceDataSet dset = null;
        GauceDataRow row = null;
        GauceDataColumn[] colinfo = null;
        ArrayList   recs = null;
        HashMap     record=null;
        if(gdsets != null )
        {
            for(int i=0;i<gdsets.length;i++)
            {
                dset = gdsets[i];
                colinfo = dset.getDataColumns();
                recs = new ArrayList();
                for(int x=0;x<dset.getDataRowCnt();x++)
                {
                    record = new HashMap();
                    row = dset.getDataRow(x);
                    for(int y=0;y<colinfo.length;y++){
                        record.put(colinfo[y].getColName(), row.getColumnValue(y)) ;
                    }
                    if (row.getJobType() == GauceDataRow.TB_JOB_INSERT)
                    {
                        record.put("IUDFLAG", "I") ;
                    }
                    else if (row.getJobType() == GauceDataRow.TB_JOB_UPDATE)
                    {
                        record.put("IUDFLAG", "U") ;
                    }
                    else if (row.getJobType() == GauceDataRow.TB_JOB_DELETE)
                    {
                        record.put("IUDFLAG", "D") ;
                    }
                    else if (row.getJobType() == GauceDataRow.TB_JOB_NORMAL)
                    {
                       record.put("IUDFLAG", "") ;
                    }else {
                        record.put("IUDFLAG", "") ;
                    }
                }
                VOBJ _vobj = new VOBJ();
                _vobj.setRecords(recs);
                _vobj.setName(dset.getName());
                _dobj.setRetObject( dset.getName(), _vobj);
            }
        }
        return _dobj;
    }

    private GauceDataSet getGauceDataSet(VOBJ _vobj){
        GauceDataSet dset = new GauceDataSet(_vobj.getName());
        ArrayList clist  = _vobj.getColumnNames();
        _vobj.first();
        while(_vobj.next()){
            for(int y=0;y < clist.size();y++)
            {
                dset.put(clist.get(y).toString(), _vobj.getRecord().get(clist.get(y)), _vobj.getRecord().get(clist.get(y)).length());
            }
            dset.heap();
        }
        return dset;
    }

    public void outputObjectGauce(HttpGauceResponse resp, DOBJ _dobj) throws Exception {
       GauceOutputStream ios = null;
       try{
          ios = resp.getGauceOutputStream();
          ArrayList objnames = _dobj.getRetObjectKeys();
          String keyname ="";
          GauceDataSet dset = null;
          VOBJ _vobj = null;
          for(int i=0;i<objnames.size();i++)
          {
              keyname = objnames.get(i).toString();
              _vobj = _dobj.getRetObject(keyname);
              dset =  getGauceDataSet(_vobj);
              ios.write(dset);
          }

          resp.addMessage(_dobj.getRetmsg());
        } catch(Exception e)
        {
            if (resp != null && ios != null) {
                resp.addException(e);
            }
            else
            {
                e.printStackTrace();
            }
        }
        finally
        {
            if (ios != null)
            {
                try
                {
                    ios.close();
                } catch(IOException ioe)
                {
                    ios = null;
                }
            }
        }
    }

    public DOBJ inputObject1000(HttpServletRequest request){
        DOBJ dobj = new DOBJ();
        HashMap _3062 = new HashMap();
        HashMap   _2564 = null;
        ArrayList _2558= null;
        VOBJ     vobj   = null;

        /** 부분 초기화 **/
        String requestaddr = request.getRemoteAddr();
        String userid = "999999";

        /** Unikey Gen Test **/
        //UniApprovalKey unigen = new UniApprovalKey();
        //unigen.getUnikey();
        //---------------------------------------

        /** Input DataSet 부분 초기화 **/
        DatasetList _4271dl = new DatasetList();
        int maxlen = 0;
        boolean makeflag = false;
        boolean addflag = false;
        try {
            CONFIG _6815 = new CONFIG();
            String _7190 = _6815.getPathfinderConfig("InputInterfaceCharSet");
            PlatformRequest _pReq = new PlatformRequest(request, _7190); //Q/A 대상 해당 Character-set 구성 확인
            _pReq.receiveData();

            //_pReq.getDatasetList().printDatasets();
            _4271dl = _pReq.getDatasetList();
            String _datesetid = "";
            System.out.println("dataset size:" + _4271dl.size() + ":");
            for (int i = 0; i < _4271dl.size(); i++) {

                _2558 = new ArrayList();
                Dataset dset = _4271dl.getDataset(i);

                for (int x = 0; x < dset.getDeleteRowCount(); x++) {
                    _2564 = new HashMap();
                    for (int y = 0; y < dset.getColumnCount(); y++) {
                        ColumnInfo _8920 = dset.getColumnInfo(y);
                        String _281 = "";

                        //System.out.println("_8920.getColumnType():" + _8920.getId()+ ":" + _8920.getColumnType() +":" + dset.getDeleteColumn(x,_8920.getId()));
                        if (_8920.getColumnType() == _8920.COLUMN_TYPE_INT) {
                            if (dset.getDeleteColumn(x, _8920.getId()) != null &&
                                dset.getDeleteColumn(x, _8920.getId()).getInteger() != null) {
                                _281 = dset.getDeleteColumn(x, _8920.getId()).
                                       getInteger() + "";
                            }
                        } else if (_8920.getColumnType() == _8920.COLUMN_TYPE_LONG) {
                            if (dset.getDeleteColumn(x, _8920.getId()) != null &&
                                dset.getDeleteColumn(x, _8920.getId()).getInteger() != null) {
                                _281 = dset.getDeleteColumn(x, _8920.getId()).
                                       getInteger() + "";
                            }
                        } else if (_8920.getColumnType() ==
                                   _8920.COLUMN_TYPE_DECIMAL) {
                            if (dset.getDeleteColumn(x, _8920.getId()) != null &&
                                dset.getDeleteColumn(x, _8920.getId()).getDouble() != null) {
                                _281 = dset.getDeleteColumn(x, _8920.getId()).
                                       getDouble() + "";
                            }
                        } else {
                            if (dset.getDeleteColumn(x, _8920.getId()) != null &&
                                dset.getDeleteColumn(x, _8920.getId()).getString() != null) {
                                _281 = dset.getDeleteColumn(x, _8920.getId()).
                                       getString();
                            }
                        }

                        _2564.put(_8920.getId().toUpperCase(), _281);
                    }
                    _2564.put("IUDFLAG", "D");
                    _2558.add(_2564);
                }

                for (int x = 0; x < dset.getRowCount(); x++) {
                    _2564 = new HashMap();

                    for (int y = 0; y < dset.getColumnCount(); y++) {
                        ColumnInfo _8920 = dset.getColumnInfo(y);
                        String _281 = "";
                        // System.out.println("_8920.getColumnType():" + _8920.getId()+ ":" + _8920.getColumnType() +":" + dset.getColumn(x,_8920.getId()));
                        if (_8920.getColumnType() == _8920.COLUMN_TYPE_INT) {
                            if (dset.getColumn(x, _8920.getId()) != null &&
                                dset.getColumn(x, _8920.getId()).getInteger() != null)
                                _281 = dset.getColumn(x, _8920.getId()).getInteger() +
                                       "";

                        } else if (_8920.getColumnType() == _8920.COLUMN_TYPE_LONG) {
                            if (dset.getColumn(x, _8920.getId()) != null &&
                                dset.getColumn(x, _8920.getId()).getInteger() != null)
                                _281 = dset.getColumn(x, _8920.getId()).getInteger() +
                                       "";

                        } else if (_8920.getColumnType() ==
                                   _8920.COLUMN_TYPE_DECIMAL) {
                            if (dset.getColumn(x, _8920.getId()) != null &&
                                dset.getColumn(x, _8920.getId()).getDouble() != null)
                                _281 = dset.getColumn(x, _8920.getId()).getDouble() +
                                       "";

                        } else {
                            if (dset.getColumn(x, _8920.getId()) != null &&
                                dset.getColumn(x, _8920.getId()).getString() != null)
                                _281 = dset.getColumn(x, _8920.getId()).getString();

                        }

                        _2564.put(_8920.getId().toUpperCase(), _281);
                    }

                    String _8067status = dset.getRowStatus(x);
                    if (_8067status.equals("insert") == true) {
                        _2564.put("IUDFLAG", "I");
                    } else if (_8067status.equals("update") == true) {
                        _2564.put("IUDFLAG", "U");
                    } else if (_8067status.equals("delete") == true) {
                        _2564.put("IUDFLAG", "D");
                    }
                    _2558.add(_2564);
                }

                vobj = new VOBJ();
                vobj.setRecords(_2558);
                vobj.setName(dset.getId());
                dobj.setRetObject(vobj);

            }

            /** List 획득 및 Dataset, 변수 획득 **/
            VariableList _4271vl = new VariableList();
            _4271vl = _pReq.getVariableList();
            vobj = new VOBJ();

            Enumeration e = request.getParameterNames();
            String _4001 = null;
            String[] _3965 = null;
            while (e.hasMoreElements()) {
                _4001 = (String) e.nextElement();
                if (_4001.equals("SYSID")) {
                    _3062.put("SYSID", request.getParameter("SYSID"));
                    continue;
                }
                if (_4001.equals("MENUID")) {
                    _3062.put("MENUID", request.getParameter("MENUID"));
                    continue;
                }
                if (_4001.equals("EVENTID")) {
                    _3062.put("EVENTID", request.getParameter("EVENTID"));
                    continue;
                }
                System.out.println("SYSID=" + _3062.get("SYSID") + ":MENUID=" +
                                   _3062.get("MENUID") + ":EVENTID=" +
                                   _3062.get("EVENTID"));

                _3965 = request.getParameterValues(_4001);
                if (_3965.length == 1) {
                    //------ USERID
                    //------ IPADDR  추가해서 TEST필요 하다.
                    if (vobj.getRecordCnt() > 0) {
                        vobj.first();
                        while (vobj.next()) {
                            vobj.getRecord().put(_4001.toUpperCase(), _3965[0]);
                        }
                    } else {
                        _2564 = new HashMap();
                        _2564.put(_4001.toUpperCase(), _3965[0]);
                        vobj.addRecord(_2564);
                    }

                } else {

                    if (vobj.getRecordCnt() >= _3965.length) {
                        for (int i = 0; i < _3965.length; i++) {
                            vobj.getRecord(i).put(_4001.toUpperCase(), _3965[i]);
                        }
                    } else {

                        if (vobj.getRecordCnt() > 0) {
                            int ccnt = 0;
                            vobj.first();
                            while (vobj.next()) {
                                vobj.getRecord().put(_4001.toUpperCase(),
                                                     _3965[ccnt]);
                                ccnt++;
                            }
                            for (int i = ccnt; i < _3965.length; i++) {
                                _2564 = new HashMap();
                                _2564.put(_4001.toUpperCase(), _3965[i]);
                                vobj.addRecord(_2564);
                            }
                        } else {
                            for (int i = 0; i < _3965.length; i++) {
                                _2564 = new HashMap();
                                _2564.put(_4001.toUpperCase(), _3965[i]);
                                vobj.addRecord(_2564);
                            }
                        }
                    }
                }
            }
            if (vobj != null && vobj.getRecordCnt() > 0) {
                dobj.setRetObject("S", vobj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        dobj.setParam(_3062);
        if (_2558 != null) vobj.setRecords(_2558);

        //-----별도 inteface를 이용하여 외부에서 setting가능하게 한다. global object 생성 처리를 Module단위로 가능하게 설정한다.

        VOBJ _164 = new VOBJ();
        HashMap grec = new HashMap();
        grec.put("IPADDR", requestaddr);
        grec.put("USERID", userid);
        _164.addRecord(grec);
        _164.setName("G");
        dobj.setRetObject(_164);
        //------------------------------------------------------------------------------------------------------

        return dobj;
  }

  public DOBJ downLoadObject1000(HttpServletRequest _request, HttpServletResponse _2480){
     DOBJ dobj = new DOBJ();
     //-- 전문 data생성 처리 기능
     ArrayList _3119list = new ArrayList();
     /** Input DataSet 부분 초기화 **/
     DatasetList _4271dl = new DatasetList();
     try{
         CONFIG _6815 = new CONFIG();
         String _7190 = _6815.getPathfinderConfig("InputInterfaceCharSet");
         PlatformRequest _pReq = new PlatformRequest(_request, _7190);   //Q/A 대상 해당 Character-set 구성 확인
         _pReq.receiveData();
         _4271dl = _pReq.getDatasetList();
         for(int i=0;i<_4271dl.size();i++){
             Dataset dset = _4271dl.getDataset(i);
             System.out.println("dataset size:"+dset.getColumnCount() + ":" + dset.getRowCount() );
             for(int y=0;y<dset.getColumnCount();y++){
                 ColumnInfo _8920 = dset.getColumnInfo(y);
                 System.out.println("down column:" + _8920.getId().toString());
                 if(_8920.getId().toString().equals("DOWNFILE")){
                     _3119list.add(dset);
                     break;
                 }
             }
         }
     }catch(Exception e){
         e.printStackTrace();
     }

     //-- output 처리 기능
     VariableList _8901 = new VariableList();
     DatasetList _8903 = new DatasetList();
     String _7190 = "";
     try{
         CONFIG _6815 = new CONFIG();
         _7190 = _6815.getPathfinderConfig("OutputInterfaceCharSet");
     }catch(Exception e){
         _7190 = "euc-kr";
         e.printStackTrace();
     }


     try{
         String _PATH="";
         String _8912="";
         Dataset dsetet = null;
         for(int i=0;i<_3119list.size();i++){
             dsetet = (Dataset)_3119list.get(i);
             dsetet.addColumn("CONTENT",ColumnInfo.COLUMN_TYPE_BLOB, (short)255);
             for(int y=0;y<dsetet.getRowCount();y++){
                 _PATH = dsetet.getColumn(y, "PATH").toString();
                 _8912 = dsetet.getColumn(y, "DOWNFILE").toString();
                 dsetet.setColumn(y,"CONTENT", downloadFile( _PATH,  _8912));
             }
             _8903.addDataset(dsetet.getId(), dsetet);
         }

         PlatformResponse _8907 = new PlatformResponse(_2480, PlatformRequest.XML, _7190);
         _8907.sendData(_8901, _8903);

     }catch(Exception e){
         _8901.addStr("ErrorCode", "-1");
         _8901.addStr("ErrorMsg", e.getMessage());
         _8901.addStr("out_var", dobj.getRetmsg());
         e.printStackTrace();
     }

     return dobj;
 }
 private byte[] downloadFile(String _2996, String _downfile){

     File _4271fp = new File(_2996 +"/"+ _downfile );
     int _1349 = (int)_4271fp.length();
     byte[] _8914= new byte[_1349+10];

     BufferedInputStream _8916 = null;
     int _3905=0;
     int _8918 = 0;
     byte _b[] = new byte[4096];
     try {
         _8916 = new BufferedInputStream( new FileInputStream( _2996 +"/"+ _downfile ) );
         _8918 = _8916.read(_8914);
         // while ((read = fin.read(b)) != -1){
        //     len+=read;
        // }
         _8916.close();
     } catch (Exception e) {
     } finally {
         try{
             if(_8916!=null) _8916.close();
         }catch(Exception ee){
             ee.printStackTrace();
         }
     }
     System.out.println("DOWNLOAD FILE SIZE:" + _8918 +":contents size:" + _8914.length);
     return _8914;
 }


  private String getDupFileNewNameX(String _2996,String _5963){
      System.out.println(_2996 + ":" + _5963);
      String _3311=_5963;
      String _3305="";
      String _3947="";
      String _3533="";
      if(_5963.indexOf(".")!= -1){
          _3533 = _5963.substring(0,_5963.lastIndexOf("."));
          _3947= _5963.substring(_5963.lastIndexOf("."));
      }
      if(_2996.trim().substring(_2996.trim().length()-1).equals("/")){
          _3305 = _2996 ;
      }else{
          _3305 = _2996 +"/";
      }
      File fp = new File(_3305+_3311);
      int i=1;
      while(fp.exists()){
          _3311 = _3533+"V" + i + _3947;
          fp = new File(_3305+_3311);
          i++;
      }
      System.out.println("FILE NEW NAME:"+ _3305+_3311);
      return _3305+_3311;

  }

  private boolean isUpFileStream(Dataset dset){
      boolean _2366 = false;
      for(int y=0;y<dset.getColumnCount();y++){
          ColumnInfo _8920 = dset.getColumnInfo(y);
          if(_8920.getId().toString().equals("UPFILENAME")) {
              _2366 = true;
              break;
          }
      }
      return _2366;
  }
  private boolean isDownFileStream(Dataset dset){
      boolean _2366 = false;
      for(int y=0;y<dset.getColumnCount();y++){
          ColumnInfo _8920 = dset.getColumnInfo(y);
          if(_8920.getId().toString().equals("DOWNFILENAME")) {
              _2366 = true;
              break;
          }
      }
      return _2366;
  }

  public DOBJ inputObjectUpload1000(HttpServletRequest request, HttpServletResponse response){
      System.out.println("inputObjectUpload1000");
     DOBJ dobj = new DOBJ();
     //-- 전문 data생성 처리 기능
     HashMap _3062 = new HashMap();
     HashMap   _amap = null;
     ArrayList _alist= null;
     VOBJ     vobj   = null;

     //**
     String requestaddr = request.getRemoteAddr();
     String userid      = "999999";

     /** Input DataSet 부분 초기화 **/
     DatasetList _4271dl = new DatasetList();
     int maxlen=0;
     boolean makeflag = false;
     boolean addflag = false;
     try{
       CONFIG CONF = new CONFIG();
       String _7190 = CONF.getPathfinderConfig("InputInterfaceCharSet");
       PlatformRequest _pReq = new PlatformRequest(request, _7190);      //Q/A 대상 해당 Character-set 구성 확인
       _pReq.receiveData();

       _4271dl = _pReq.getDatasetList();
       String _datesetid="";
       System.out.println("dataset size:"+_4271dl.size() + ":");
       for(int i=0;i<_4271dl.size();i++){

         _alist = new ArrayList();
         Dataset dset = _4271dl.getDataset(i);

         if(this.isUpFileStream(dset)){

             for(int x=0;x<dset.getRowCount();x++) {
                 _amap= new HashMap();
                 for(int y=0;y<dset.getColumnCount();y++){
                     ColumnInfo _8920 = dset.getColumnInfo(y);
                     String _281 ="";
                     if(_8920.getId().toString().equals("UPCONTENT")){
                         byte[] file = dset.getColumn(x, "UPCONTENT").getBinary();
                         System.out.println(dset.getColumn(x, "UPFILENAME"));

                         String[] _5888 = this.writeToFile(file,dset.getColumn(x, "UPFILENAME").getString());
                         System.out.println(_5888[0] +":" + _5888[1]);

                         _amap.put("FULLPATH"   ,_5888[0]);
                         _amap.put("FILESIZE"   ,_5888[1]);
                         _amap.put("PATH"       ,_5888[2]);
                         _amap.put("UNIFILENAME",_5888[3]);

                     }else {
                         if(dset.getColumn(x,_8920.getId())!=null &&  dset.getColumn(x,_8920.getId()).getString()!= null) {
                             _281 = dset.getColumn(x, _8920.getId()).getString();
                         }
                         _amap.put(_8920.getId().toUpperCase(),_281);
                     }
                 }

                 String _8067status = dset.getRowStatus(x);
                 if( _8067status.equals("insert") == true )      {
                     _amap.put("IUDFLAG","I");
                 }else if(_8067status.equals("update") == true ) {
                     _amap.put("IUDFLAG","U");
                 }else if(_8067status.equals("delete") == true ) {
                     _amap.put("IUDFLAG","D");
                 }
                 _alist.add(_amap);
             }

         }else {

             for(int x=0;x<dset.getDeleteRowCount();x++){
                 _amap= new HashMap();
                 for(int y=0;y<dset.getColumnCount();y++){
                     ColumnInfo _8920 = dset.getColumnInfo(y);
                     String _281 ="";
                     if(dset.getDeleteColumn(x,_8920.getId()) != null && dset.getDeleteColumn(x,_8920.getId()).getString()!= null)
                         _281 = dset.getDeleteColumn(x,_8920.getId()).getString();
                     _amap.put(_8920.getId().toUpperCase(),_281);
                 }
                 _amap.put("IUDFLAG","D");
                 _alist.add(_amap);
             }

             for(int x=0;x<dset.getRowCount();x++) {
                 _amap= new HashMap();
                 for(int y=0;y<dset.getColumnCount();y++){
                     ColumnInfo _cinfo = dset.getColumnInfo(y);
                     String _281 ="";
                     if(dset.getColumn(x,_cinfo.getId()) != null && dset.getColumn(x,_cinfo.getId()).getString()!= null)
                         _281 = dset.getColumn(x,_cinfo.getId()).getString();
                     _amap.put(_cinfo.getId().toUpperCase(),_281);
                 }

                 String _8067status = dset.getRowStatus(x);
                 if( _8067status.equals("insert") == true )      {
                     _amap.put("IUDFLAG","I");
                 }else if(_8067status.equals("update") == true ) {
                     _amap.put("IUDFLAG","U");
                 }else if(_8067status.equals("delete") == true ) {
                     _amap.put("IUDFLAG","D");
                 }
                 _alist.add(_amap);
             }
         }

         vobj = new VOBJ();
         vobj.setRecords(_alist);
         vobj.setName(dset.getId());
         vobj.setRetcode(1);
         dobj.setRetObject(vobj);

       }

       /** List 획득 및 Dataset, 변수 획득 **/
       VariableList invl = new VariableList();
       invl = _pReq.getVariableList();
       vobj = new VOBJ();

       Enumeration e = request.getParameterNames();
       String _4001 = null;
       String[] _3965 = null;
       while (e.hasMoreElements()) {
         _4001 = (String) e.nextElement();
         if(_4001.equals("SYSID")) {
           _3062.put("SYSID",request.getParameter("SYSID"));
           continue;
         }
         if(_4001.equals("MENUID")) {
           _3062.put("MENUID",request.getParameter("MENUID"));
           continue;
         }
         if(_4001.equals("EVENTID")) {
           _3062.put("EVENTID",request.getParameter("EVENTID"));
           continue;
         }
         System.out.println("SYSID=" + _3062.get("SYSID") + ":MENUID=" + _3062.get("MENUID") + ":EVENTID=" + _3062.get("EVENTID"));

         _3965 = request.getParameterValues(_4001);
         if (_3965.length ==1 ) {
           //------ USERID
           //------ IPADDR  추가해서 TEST필요 하다.
           if(vobj.getRecordCnt() > 0){
             vobj.first();
             while(vobj.next()){
               vobj.getRecord().put(_4001.toUpperCase(),_3965[0]);
             }
           }else {
             _amap = new HashMap();
             _amap.put(_4001.toUpperCase(),_3965[0]);
             vobj.addRecord(_amap);
           }

         } else {

           if(vobj.getRecordCnt() >= _3965.length){
             for(int i=0;i<_3965.length;i++){
               vobj.getRecord(i).put(_4001.toUpperCase(),_3965[i]);
             }
           } else {

             if(vobj.getRecordCnt() > 0){
               int ccnt=0;
               vobj.first();
               while(vobj.next()){
                 vobj.getRecord().put(_4001.toUpperCase(),_3965[ccnt]);
                 ccnt++;
               }
               for(int i=ccnt;i<_3965.length;i++){
                 _amap = new HashMap();
                 _amap.put(_4001.toUpperCase(),_3965[i]);
                 vobj.addRecord(_amap);
               }
             } else {
               for(int i=0;i<_3965.length;i++){
                 _amap = new HashMap();
                 _amap.put(_4001.toUpperCase(),_3965[i]);
                 vobj.addRecord(_amap);
               }
             }
           }
         }
       }

       if(vobj != null && vobj.getRecordCnt() >0 ) {
         dobj.setRetObject("S",vobj);
       }

     }catch(Exception e){
       e.printStackTrace();
     }
     dobj.setParam(_3062);
     if(_alist!= null)   vobj.setRecords(_alist);

     //-----별도 inteface를 이용하여 외부에서 setting가능하게 한다. global object 생성 처리를 Module단위로 가능하게 설정한다.
     VOBJ _164 = new VOBJ();
     HashMap _grec = new HashMap();
     _grec.put("IPADDR",requestaddr);
     _grec.put("USERID",userid);
     _164.addRecord(_grec);
     _164.setName("G");
     dobj.setRetObject(_164);
     //------------------------------------------------------------------------------------------------------
     return dobj;
   }




  public void outputObject10000(DOBJ dobj,  HttpServletResponse _2474){
    VariableList _8901 = new VariableList();
    DatasetList _8903 = new DatasetList();
    String _7190 = "";

    try{
      CONFIG _6815 = new CONFIG();
      _7190 = _6815.getPathfinderConfig("OutputInterfaceCharSet");
    }catch(Exception e){
      _7190 = "euc-kr";
      e.printStackTrace();
    }

    try{
      String    _omsg = "";
      VOBJ _539=null;
      //ArrayList _7475 = dobj.rtnobjname;
      ArrayList _7475 =dobj.getRetObjectKeys();
      Dataset dsetet = null;
      if(dobj.getRetmsg()!=null) _omsg = dobj.getRetmsg();
      for(int i=0;i<_7475.size();i++){
          _539 = dobj.getRetObject(_7475.get(i).toString());
          dsetet = setColumninfo1000(_539, _7190);
          _8903.addDataset(_539.getName(), dsetet);
      }

      System.out.println("RTN CODE VALUE:" + dobj.getRtncode() +":" + dobj.getRetmsg());
      if(dobj.getRtncode() < 0){
          _8901.addStr("ErrorCode", dobj.getRtncode()+"");
          _8901.addStr("ErrorMsg", new String(dobj.getRetmsg().getBytes("KSC5601"), "UTF-8"));  //new String(str.getBytes("KSC5601"), "8859_1");
          _8901.addStr("out_var",  new String(dobj.getRetmsg().getBytes("KSC5601"), "UTF-8"));  //dobj.getRetmsg()
      }else {
          _8901.addStr("ErrorCode", dobj.getRtncode()+"");
          _8901.addStr("ErrorMsg", dobj.getRetmsg());
          _8901.addStr("out_var" , dobj.getRetmsg());
      }

      PlatformResponse _8907 = new PlatformResponse(_2474, PlatformRequest.XML, _7190);
      _8907.sendData(_8901, _8903);

    }catch(Exception e){
      _8901.addStr("ErrorCode", "-1");
      _8901.addStr("ErrorMsg", e.getMessage());
      _8901.addStr("out_var", getCodecvt(dobj.getRetmsg()) );  //dobj.getRetmsg()
      e.printStackTrace();
      try {
          PlatformResponse _8907 = new PlatformResponse(_2474, PlatformRequest.XML, _7190);
          _8907.sendData(_8901, _8903);
      }catch(Exception ee){
          ee.printStackTrace();
      }
    }
  }

  public void outputObject100001(DOBJ dobj, HttpServletRequest _request, HttpServletResponse _2474){

    VOBJ _539=null;
    //ArrayList _7475 = dobj.rtnobjname;
    ArrayList _7475 =dobj.getRetObjectKeys();
    String _omsg = "";
    if(dobj.getRetmsg()!=null) _omsg = dobj.getRetmsg();
    for(int i=0;i<_7475.size();i++){
      _539 = dobj.getRetObject(_7475.get(i).toString());
    }

    VariableList _8901 = new VariableList();
    DatasetList _8903 = new DatasetList();
    String _7190 = "";

    try{
      CONFIG _6815 = new CONFIG();
      _7190 = _6815.getPathfinderConfig("InputIntefaceCharSet");
    }catch(Exception e){
      e.printStackTrace();
    }

    Dataset dset = new Dataset("dssawon", _7190);
    dset.addColumn("name",ColumnInfo.COLUMN_TYPE_STRING, (short)255);
    dset.addColumn("dept",ColumnInfo.COLUMN_TYPE_STRING, (short)255);
    dset.addColumn("sabun",ColumnInfo.COLUMN_TYPE_STRING, (short)255);
    dset.addColumn("jikgup",ColumnInfo.COLUMN_TYPE_STRING, (short)255);
    dset.addColumn("sex",ColumnInfo.COLUMN_TYPE_STRING, (short)255);
    dset.addColumn("ipsa_date",ColumnInfo.COLUMN_TYPE_STRING, (short)255);
    dset.addColumn("marry",ColumnInfo.COLUMN_TYPE_STRING, (short)255);
    try{
      Connector _6782 = new Connector();
      Connection _6773 = _6782.getConnection("PFDB");
      SQLObject _1178 = this.makeQryCmdInfo();
      QExecutor _6278 = new QExecutor();

      VOBJ _4238  = _6278.executeQuery(_6773, _1178);
      _4238.first();
      while(_4238.next()){
        int _8067 = dset.appendRow();
        dset.setColumn(_8067, "name",_4238.getRecord().get("NAME"));
        dset.setColumn(_8067, "sabun",_4238.getRecord().get("SABUN"));
        dset.setColumn(_8067, "jikgup",_4238.getRecord().get("JIKGUP"));
        dset.setColumn(_8067, "dept",_4238.getRecord().get("DEPT"));
        dset.setColumn(_8067, "sex",_4238.getRecord().get("SEX"));
        dset.setColumn(_8067, "ipsadate",_4238.getRecord().get("IPSADATE"));
        dset.setColumn(_8067, "marry",_4238.getRecord().get("MARRY"));
      }

      _8903.addDataset("dssawon", dset);
      _8901.addStr("ErrorCode", "0");
      _8901.addStr("ErrorMsg", "SUCC");

      PlatformResponse _8907 = new PlatformResponse(_2474, PlatformRequest.XML, _7190);
      _8907.sendData(_8901, _8903);

    }catch(Exception e){
      _8901.addStr("ErrorCode", "-1");
      _8901.addStr("ErrorMsg", e.getMessage());
      e.printStackTrace();
    }
  }


}
