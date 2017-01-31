package egov.wizware.ria;
import javax.servlet.http.*;
import java.sql.*;
import java.util.*;
import egov.wizware.com.*;
import java.io.*;
import java.text.SimpleDateFormat;
import com.tobesoft.xplatform.*;
import com.tobesoft.xplatform.tx.*;
import com.tobesoft.xplatform.data.*;

public class XPlatFormBuilder
{
    public XPlatFormBuilder()
    {
        System.out.println("ACTION URL DEV convertDev:bbbbbbbb XPlatFormBuilder bbbbbbbbbbb");
    }

    public DOBJ inputObject(HttpServletRequest request)
    {
        System.out.println("ACTION URL DEV convertDev:1");
        DOBJ dobj = new DOBJ();
        HashMap param = new HashMap();
        HashMap record = null;
        ArrayList records = null;
        VOBJ vobj = null;
        /** Input DataSet 부분 초기화 **/
        DataSetList indl = null;
        try
        {
           String charset = "UTF-8";
           HttpPlatformRequest pReq = new HttpPlatformRequest(request, PlatformType.CONTENT_TYPE_XML); //Q/A 대상 해당 Character-set 구성 확인
           pReq.receiveData();
           indl = pReq.getData().getDataSetList();
           System.out.println("dataset size:" + indl.size() + ":");
           for (int i = 0; i < indl.size(); i++)
           {
               records = new ArrayList();
               DataSet ds = indl.get(i);
               //delete row setting 처리
               for (int x = 0; x < ds.getRemovedRowCount(); x++)
               {
                  System.out.println("Record Status:" +  ds.getRowType(x)  +":" + ds.getRemovedRowCount() +":" + x);
                  record = new HashMap();
                  for (int y = 0; y < ds.getColumnCount(); y++)
                  {
                      ColumnHeader cinfo = ds.getColumn(y);
                      String value = "";
                      if (cinfo.getDataType() == DataTypes.INT)
                      {
                          if (ds.getRemovedData(x, cinfo.getName()) != null )
                          {
                              if( ds.getRemovedData(x, cinfo.getName()).toString().trim().equals(""))
                              {
                                  value="";
                              }
                              else
                              {
                                  value = ds.getRemovedData(x, cinfo.getName()).toString().trim() + "";
                              }
                          }
                      }
                      else if (cinfo.getDataType() ==  DataTypes.LONG)
                      {
                          if (ds.getRemovedData(x, cinfo.getName()) != null )
                          {
                              if( ds.getRemovedData(x, cinfo.getName()).toString().trim().equals(""))
                              {
                                  value="";
                              }
                              else
                              {
                                  value = ds.getRemovedData(x, cinfo.getName()).toString().trim() + "";
                              }
                          }
                      }
                      else if (cinfo.getDataType() == DataTypes.DOUBLE)
                      {
                          if (ds.getRemovedData(x, cinfo.getName()) != null)
                          {
                              if( ds.getRemovedData(x, cinfo.getName()).toString().trim().equals(""))
                              {
                                  value="";
                              }
                              else
                              {
                                  value = ds.getRemovedData(x, cinfo.getName()).toString().trim() + "";
                              }
                          }
                      }
                      else
                      {
                          if (ds.getRemovedData(x, cinfo.getName()) != null )
                          {
                              value = ds.getRemovedData(x, cinfo.getName()).toString().trim() + "";
                          }
                      }
                      record.put(cinfo.getName().toUpperCase(), value);
                  }
                  record.put("IUDFLAG", "D");
                  records.add(record);
              }


               for (int x = 0; x < ds.getRowCount(); x++)
               {
                  System.out.println("Record Status:" +  ds.getRowType(x)  +":" + ds.getRemovedRowCount() +":" + x);
                  record = new HashMap();
                  for (int y = 0; y < ds.getColumnCount(); y++) {
                      ColumnHeader cinfo = ds.getColumn(y);
                      String value = "";
                      if (cinfo.getDataType() == DataTypes.INT) {
                          if (ds.getObject(x, cinfo.getName()) != null )
                          {
                              if( ds.getObject(x, cinfo.getName()).toString().trim().equals(""))
                              {
                                  value="";
                              }
                              else
                              {
                                  value = ds.getObject(x, cinfo.getName()).toString().trim() + "";
                              }
                          }
                      } else if (cinfo.getDataType() ==  DataTypes.LONG) {
                          if (ds.getObject(x, cinfo.getName()) != null )
                          {
                              if( ds.getObject(x, cinfo.getName()).toString().trim().equals(""))
                              {
                                  value="";
                              }
                              else
                              {
                                  value = ds.getObject(x, cinfo.getName()).toString().trim() + "";
                              }
                          }
                      } else if (cinfo.getDataType() == DataTypes.DOUBLE) {
                          if (ds.getObject(x, cinfo.getName()) != null)
                          {
                              if( ds.getObject(x, cinfo.getName()).toString().trim().equals(""))
                              {
                                  value="";
                              }
                              else
                              {
                                  value = ds.getObject(x, cinfo.getName()).toString().trim() + "";
                              }
                          }
                      }
                      else
                      {
                          if (ds.getObject(x, cinfo.getName()) != null )
                          {
                              value = ds.getObject(x, cinfo.getName()).toString().trim() + "";
                          }
                      }
                      record.put(cinfo.getName().toUpperCase(), value);
                  }

                  if (ds.getRowType(x) == ds.ROW_TYPE_INSERTED) {
                      record.put("IUDFLAG", "I");
                  }
                  else if (ds.getRowType(x) == ds.ROW_TYPE_UPDATED)
                  {
                      record.put("IUDFLAG", "U");
                  }
                  else
                  {
                      record.put("IUDFLAG", "");
                  }
                  records.add(record);
              }

              vobj = new VOBJ();
              vobj.setRecords(records);
              vobj.setName(ds.getName());
              vobj.Println("========================= INPUT DATASETS ========================");
              dobj.setRetObject(vobj);
          }
          /** List 획득 및 Dataset, 변수 획득 **/
          VariableList invl = new VariableList();
          invl = pReq.getData().getVariableList();
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

    public void outputObject(DOBJ dobj,  HttpServletResponse resp)
    {
        System.out.println("outputObject 1:" + dobj);
        String charset = "UTF-8";  //utf-8
        HttpPlatformResponse presponse = null;
        PlatformData sndData = null;
        try
        {
            presponse = new HttpPlatformResponse(resp,  PlatformType.CONTENT_TYPE_XML , charset);
            sndData = new PlatformData();
            String _omsg = "";
            VOBJ _vobj = null;
            ArrayList _klist = dobj.getRetObjectKeys();
            DataSet dset = null;
            if (dobj.getRetmsg() != null) _omsg = dobj.getRetmsg();

            for (int i = 0; i < _klist.size(); i++)
            {
                _vobj = dobj.getRetObject(_klist.get(i).toString());
                dset = setColumninfo(_vobj, charset);
                dset.setName( _klist.get(i).toString());

                System.out.println("outputObject 31:" + _klist.get(i).toString() +":" + _vobj.getRecordCnt() +":" + dset.getRowCount() +":" + dset.getColumnCount());
                sndData.addDataSet(dset);
            }
            System.out.println("RTN CODE VALUE:" + dobj.getRtncode() + ":" +   dobj.getRetmsg());
            if (dobj.getRtncode() < 0)
            {
                sndData.addVariable(new Variable("ErrorCode", DataTypes.STRING, dobj.getRtncode() + ""));
                sndData.addVariable(new Variable("ErrorMsg", DataTypes.STRING, new String(dobj.getRetmsg().getBytes("KSC5601"), "UTF-8")));
                sndData.addVariable(new Variable("out_var", DataTypes.STRING, new String(dobj.getRetmsg().getBytes("KSC5601"), "UTF-8")));
            }
            else
            {
                sndData.addVariable(new Variable("ErrorCode", DataTypes.STRING, dobj.getRtncode() + ""));
                sndData.addVariable(new Variable("ErrorMsg", DataTypes.STRING, new String(dobj.getRetmsg().getBytes("KSC5601"), "UTF-8")));
                sndData.addVariable(new Variable("out_var", DataTypes.STRING, new String(dobj.getRetmsg().getBytes("KSC5601"), "UTF-8")));
            }
            //System.out.println(  sndData.saveXml());

            presponse.setData(sndData);
            presponse.sendData();
            //presponse.sendData(_vlist, _dlist);
            // presponse.setData();
        }
        catch (Exception e)
        {
            System.out.println("--------------------------Excp");
            e.printStackTrace();
            sndData.addVariable(new Variable("ErrorCode", DataTypes.STRING, "-1"));
            sndData.addVariable(new Variable("ErrorMsg", DataTypes.STRING, e.getMessage()));
            sndData.addVariable(new Variable("out_var", DataTypes.STRING, getCodecvt(dobj.getRetmsg())));
            try
            {
               presponse.setData(sndData);
               presponse.sendData();
            }
            catch (Exception ee)
            {
                ee.printStackTrace();
            }
        }
    }

    private DataSet setColumninfo(VOBJ  _vobj, String charset)
    {
      ArrayList _clist = null;
      _clist = _vobj.getColumnNames();
      DataSet _dataset = new DataSet(_vobj.getName(), _vobj.getName());
      for(int i=0;i<_clist.size();i++)
      {
        ColumnHeader col = new ColumnHeader(_clist.get(i).toString(), DataTypes.STRING);
        _dataset.addColumn(col);
      }
      String colname="";
      _vobj.first();
      while(_vobj.next())
      {
        int rownum = _dataset.newRow();
        for(int i=0;i<_clist.size();i++)
        {
          colname=_clist.get(i).toString();
          _dataset.set(rownum, colname, _vobj.getRecord().get(colname));
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
