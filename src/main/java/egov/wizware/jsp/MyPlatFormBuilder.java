package egov.wizware.jsp;
import javax.servlet.http.*;
import java.sql.*;
import java.util.*;
import egov.wizware.com.*;
import java.io.*;
import java.text.SimpleDateFormat;
import com.tobesoft.platform.*;
import com.tobesoft.platform.data.*;

public class MyPlatFormBuilder
{
    public MyPlatFormBuilder()
    {
        System.out.println("ACTION URL DEV convertDev:bbbbbbbbbbbbbbbbbbbbbbbbbbbb");
    }

    public DOBJ inputObject(HttpServletRequest request)
    {
        System.out.println("ACTION URL DEV convertDev:1");
        DOBJ dobj = new DOBJ();
        HashMap param = new HashMap();
        HashMap record = null;
        ArrayList records = null;
        VOBJ vobj = null;
        System.out.println("ACTION URL DEV convertDev:1");
        /** Input DataSet 부분 초기화 **/
        System.out.println("pReq:01:");
        DatasetList indl = new DatasetList();
        System.out.println("pReq:02:");
        int maxlen = 0;
        boolean makeflag = false;
        boolean addflag = false;
        try
        {
           System.out.println("pReq:0:");
           String charset = "utf-8";
           PlatformRequest pReq = new PlatformRequest(request, charset); //Q/A 대상 해당 Character-set 구성 확인
           System.out.println("pReq:1:" + pReq.getCharset());

           pReq.receiveData();
           indl = pReq.getDatasetList();
           String _datesetid = "";
           System.out.println("dataset size:" + indl.size() + ":");
           for (int i = 0; i < indl.size(); i++) {

               records = new ArrayList();
               Dataset ds = indl.getDataset(i);
               for (int x = 0; x < ds.getDeleteRowCount(); x++)
               {
                   record = new HashMap();
                   for (int y = 0; y < ds.getColumnCount(); y++) {
                       ColumnInfo cinfo = ds.getColumnInfo(y);
                       String value = "";
                       if (cinfo.getColumnType() == cinfo.COLUMN_TYPE_INT) {
                           if (ds.getDeleteColumn(x, cinfo.getId()) != null &&
                               ds.getDeleteColumn(x, cinfo.getId()).getInteger() != null) {
                               value = ds.getDeleteColumn(x, cinfo.getId()).getInteger() + "";
                           }
                       } else if (cinfo.getColumnType() ==
                                  cinfo.COLUMN_TYPE_LONG) {
                           if (ds.getDeleteColumn(x, cinfo.getId()) != null &&
                               ds.getDeleteColumn(x, cinfo.getId()).getInteger() != null) {
                               value = ds.getDeleteColumn(x, cinfo.getId()).getInteger() + "";
                           }
                       } else if (cinfo.getColumnType() ==
                                  cinfo.COLUMN_TYPE_DECIMAL) {
                           if (ds.getDeleteColumn(x, cinfo.getId()) != null &&
                               ds.getDeleteColumn(x, cinfo.getId()).getDouble() != null) {
                               value = ds.getDeleteColumn(x, cinfo.getId()).getDouble() + "";
                           }
                       } else {
                          if (ds.getDeleteColumn(x, cinfo.getId()) != null &&
                              ds.getDeleteColumn(x, cinfo.getId()).getString() != null) {
                              value = ds.getDeleteColumn(x, cinfo.getId()).getString();
                          }
                      }
                      record.put(cinfo.getId().toUpperCase(), value);
                  }
                  record.put("IUDFLAG", "D");
                  records.add(record);
              }

              for (int x = 0; x < ds.getRowCount(); x++) {
                  record = new HashMap();
                  for (int y = 0; y < ds.getColumnCount(); y++) {
                      ColumnInfo cinfo = ds.getColumnInfo(y);
                      String value = "";
                      if (cinfo.getColumnType() == cinfo.COLUMN_TYPE_INT) {
                          if (ds.getColumn(x, cinfo.getId()) != null &&
                              ds.getColumn(x, cinfo.getId()).getInteger() != null) {
                              value = ds.getColumn(x, cinfo.getId()).getInteger() +  "";
                          }
                      } else if (cinfo.getColumnType() ==
                                 cinfo.COLUMN_TYPE_LONG) {
                          if (ds.getColumn(x, cinfo.getId()) != null &&
                              ds.getColumn(x, cinfo.getId()).getInteger() != null) {
                              value = ds.getColumn(x, cinfo.getId()).getInteger() + "";
                          }
                      } else if (cinfo.getColumnType() ==
                                 cinfo.COLUMN_TYPE_DECIMAL) {
                          if (ds.getColumn(x, cinfo.getId()) != null &&
                              ds.getColumn(x, cinfo.getId()).getDouble() != null) {
                              value = ds.getColumn(x, cinfo.getId()).getDouble() +  "";
                          }
                      } else {
                          if (ds.getColumn(x, cinfo.getId()) != null &&
                              ds.getColumn(x, cinfo.getId()).getString() != null) {
                              value = ds.getColumn(x, cinfo.getId()).getString();
                          }
                      }
                      record.put(cinfo.getId().toUpperCase(), value);
                  }

                  String rowstatus = ds.getRowStatus(x);
                  if (rowstatus.equals("insert") == true) {
                      record.put("IUDFLAG", "I");
                  } else if (rowstatus.equals("update") == true) {
                      record.put("IUDFLAG", "U");
                  } else if (rowstatus.equals("delete") == true) {
                      record.put("IUDFLAG", "D");
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
              if (key.equals("SYSID")) {
                  param.put("SYSID", request.getParameter("SYSID"));
                  continue;
              }
              if (key.equals("MENUID")) {
                  param.put("MENUID", request.getParameter("MENUID"));
                  continue;
              }
              if (key.equals("EVENTID")) {
                  param.put("EVENTID", request.getParameter("EVENTID"));
                  continue;
              }
              System.out.println("SYSID=" + param.get("SYSID") + ":MENUID=" + param.get("MENUID") + ":EVENTID=" +
                                 param.get("EVENTID"));

              keyVals = request.getParameterValues(key);
              if (keyVals.length == 1) {
                  if (vobj.getRecordCnt() > 0) {
                      vobj.first();
                      while (vobj.next()) {
                          vobj.getRecord().put(key.toUpperCase(), keyVals[0]);
                      }
                  } else {
                      record = new HashMap();
                      record.put(key.toUpperCase(), keyVals[0]);
                      vobj.addRecord(record);
                  }
              } else {
                  if (vobj.getRecordCnt() >= keyVals.length) {
                      for (int i = 0; i < keyVals.length; i++) {
                          vobj.getRecord(i).put(key.toUpperCase(), keyVals[i]);
                      }
                  } else {
                      if (vobj.getRecordCnt() > 0) {
                          int ccnt = 0;
                          vobj.first();
                          while (vobj.next()) {
                              vobj.getRecord().put(key.toUpperCase(),  keyVals[ccnt]);
                              ccnt++;
                          }
                          for (int i = ccnt; i < keyVals.length; i++) {
                              record = new HashMap();
                              record.put(key.toUpperCase(), keyVals[i]);
                              vobj.addRecord(record);
                          }
                      } else {
                          for (int i = 0; i < keyVals.length; i++) {
                              record = new HashMap();
                              record.put(key.toUpperCase(), keyVals[i]);
                              vobj.addRecord(record);
                          }
                      }
                  }
              }
          }
          if (vobj != null && vobj.getRecordCnt() > 0)
          {
              dobj.setRetObject("S", vobj);
          }
      }
      catch (Exception e)
      {
          e.printStackTrace();
      }
      dobj.setParam(param);
      if (records != null) vobj.setRecords(records);
      //-----별도 inteface를 이용하여 외부에서 setting가능하게 한다. global object 생성 처리를 Module단위로 가능하게 설정한다.
      VOBJ vobjy = new VOBJ();
      HashMap grec = new HashMap();
      /** 부분 초기화 **/
      String requestaddr = request.getRemoteAddr();
      String userid = "999999";
      grec.put("IPADDR", requestaddr);
      grec.put("USERID", userid);
      vobjy.addRecord(grec);
      vobjy.setName("G");
      dobj.setRetObject(vobjy);
      //------------------------------------------------------------------------------------------------------
      return dobj;
    }

    public void outputObject(DOBJ dobj,  HttpServletResponse _request)
    {
        VariableList _vlist = new VariableList();
        DatasetList _dlist = new DatasetList();
        String charset = "euc-kr";  //utf-8

        try
        {
            String _omsg = "";
            VOBJ _vobj = null;
            ArrayList _klist = dobj.getRetObjectKeys();
            Dataset dsetet = null;
            if (dobj.getRetmsg() != null) _omsg = dobj.getRetmsg();
            for (int i = 0; i < _klist.size(); i++)
            {
                _vobj = dobj.getRetObject(_klist.get(i).toString());
                dsetet = setColumninfo(_vobj, charset);
                _dlist.addDataset(_vobj.getName(), dsetet);
            }
            System.out.println("RTN CODE VALUE:" + dobj.getRtncode() + ":" +   dobj.getRetmsg());
            if (dobj.getRtncode() < 0)
            {
                _vlist.addStr("ErrorCode", dobj.getRtncode() + "");
                _vlist.addStr("ErrorMsg", new String(dobj.getRetmsg().getBytes("KSC5601"), "UTF-8"));
                _vlist.addStr("out_var",  new String(dobj.getRetmsg().getBytes("KSC5601"), "UTF-8"));
            }
            else
            {
                _vlist.addStr("ErrorCode", dobj.getRtncode() + "");
                _vlist.addStr("ErrorMsg", dobj.getRetmsg());
                _vlist.addStr("out_var", dobj.getRetmsg());
            }
            PlatformResponse presponse = new PlatformResponse(_request,  PlatformRequest.XML, charset);
            presponse.sendData(_vlist, _dlist);
        }
        catch (Exception e)
        {
            _vlist.addStr("ErrorCode", "-1");
            _vlist.addStr("ErrorMsg", e.getMessage());
            _vlist.addStr("out_var", getCodecvt(dobj.getRetmsg())); //dobj.getRetmsg()
            e.printStackTrace();
            try
            {
                PlatformResponse presponse = new PlatformResponse(_request,   PlatformRequest.XML, charset);
                presponse.sendData(_vlist, _dlist);
            }
            catch (Exception ee)
            {
                ee.printStackTrace();
            }
        }
    }

    private Dataset setColumninfo(VOBJ  _vobj, String charset){
      ArrayList _clist = null;
      _clist = _vobj.getColumnNames();
      Dataset _dataset = new Dataset(_vobj.getName(), charset);
      for(int i=0;i<_clist.size();i++)
      {
        _dataset.addColumn(_clist.get(i).toString(),ColumnInfo.COLUMN_TYPE_STRING, (short)255);
      }
      String colname="";
      _vobj.first();
      while(_vobj.next()){
        int rownum = _dataset.appendRow();
        for(int i=0;i<_clist.size();i++)
        {
          colname=_clist.get(i).toString();
          _dataset.setColumn(rownum, colname,_vobj.getRecord().get(colname));
        }
      }
      return _dataset;
    }

  private String getCodecvt(String _str){
      try {
          _str = new String(_str.getBytes("KSC5601"), "UTF-8");
      }catch(Exception e){
          e.printStackTrace();
      }
      return _str;
  }



}
