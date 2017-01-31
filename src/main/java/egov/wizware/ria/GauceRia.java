package egov.wizware.ria;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import egov.wizware.com.*;
import com.gauce.*;
import com.gauce.http.*;
import com.gauce.io.*;
import javax.servlet.http.*;
import java.sql.Types;
import java.io.*;
import java.util.Date;
import java.util.Locale;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

public class GauceRia
{

	public GauceRia(){}
	public DOBJ inputObjectGauce(HttpServletRequest reqx) throws Exception {
		DOBJ _dobj = null;
		if (reqx.getMethod().equals("POST")) {      // 트랜젝선을 통한 처리
			_dobj = inputObjectGaucePost( reqx);
			_dobj.setIsPostMethod(true);
		} else {    // 데이터셋을 통한 조회
			_dobj = inputObjectGauceGet( reqx);
			_dobj.setIsPostMethod(false);
		}
		return _dobj;
	}




        //***********************************************************************************************************
        //******************************** UPLOAD START *****************************************************
         private String uploadFile(InputStream in) throws Exception{
             String filename ="";
             CONFIG cfg = new CONFIG();
             String upload_path = cfg.getPathfinderConfig("UploadTemp").trim();
             String tmpfile = GetCurrentDate("yyyyMMddHH_mmssSSS");   //년월일시_분초Mill초
             if(upload_path != null && !upload_path.substring(upload_path.length()-1).equals("/")){
                upload_path +="/";
            }
            int i=0;
            File _fp = new File(upload_path + tmpfile + ".tmp");
            if(_fp.exists()){
                i++;
                tmpfile = tmpfile  +"_" + i;
                _fp = new File(upload_path + tmpfile + ".tmp");
            }
            return tmpfile + ".tmp" ;
        }

        private String GetCurrentDate(String wsFormat) {
          Date TodayDate = new Date();
          DateFormat df = DateFormat.getDateTimeInstance(DateFormat.FULL,
                  DateFormat.MEDIUM,
                  Locale.KOREA);
          SimpleDateFormat yyyyMMddE = new SimpleDateFormat(wsFormat);
          String wsCurrentDate;
          wsCurrentDate = yyyyMMddE.format(TodayDate);

          return wsCurrentDate;
        }
        public DOBJ inputObjectGauceFile(HttpServletRequest reqx) throws Exception {
            HttpGauceRequest req = (HttpGauceRequest)reqx;

            DOBJ _dobj = new DOBJ();

            GauceInputStream gis = req.getGauceInputStream();
            String[] inputKeys = gis.inputKeys();
            String[] outputKeys = gis.outputKeys();

            _dobj.setInkeys(inputKeys);
            _dobj.setOutkeys(outputKeys);

            GauceDataRow row = null;
            GauceDataColumn[] colinfo = null;
            ArrayList   recs = null;
            HashMap     record=null;

            if(inputKeys.length != 0 )
            {
                for(int i=0;i<inputKeys.length;i++)
                {
                    GauceDataSet dset = gis.read(inputKeys[i]);

                    colinfo = dset.getDataColumns();

                    recs = new ArrayList();
                    for(int x=0;x<dset.getDataRowCnt();x++)
                    {
                        record = new HashMap();
                        row = dset.getDataRow(x);
                        for(int y=0;y<colinfo.length;y++)
                        {
                            // upload 파일시 column명, upload한 유일한 TEMP파일명으로 vobj을 생성한다.
                            if(colinfo[y].getColType() == GauceDataColumn.TB_BLOB)   // 확인필요 DATA-TYPE이 불명확함.
                                record.put(colinfo[y].getColName(), uploadFile(row.getInputStream(y)) ) ;
                        }
                        recs.add(record);
                    }

                    VOBJ _vobj = new VOBJ();
                    _vobj.setRecords(recs);

                    _vobj.setName(inputKeys[i]);
                    _dobj.setRetObject( inputKeys[i], _vobj);
                }
            }


            java.util.Enumeration e = reqx.getParameterNames();
            String  _namex = "";
            HashMap _sysinfo = new HashMap();
            while (e.hasMoreElements()) {
                _namex = (String) e.nextElement();
                if (_namex.equals("SYSID")) {
                    _sysinfo.put("SYSID", reqx.getParameter("SYSID"));
                    continue;
                }
                if (_namex.equals("MENUID")) {
                    _sysinfo.put("MENUID", reqx.getParameter("MENUID"));
                    continue;
                }
                if (_namex.equals("EVENTID")) {
                    _sysinfo.put("EVENTID", reqx.getParameter("EVENTID"));
                    continue;
                }

            }
            _dobj.setParam(_sysinfo);
            return _dobj;
        }
        //***********************************************************************************************************
        //******************************** UPLOAD END *****************************************************



	public DOBJ inputObjectGauceGet(HttpServletRequest req) throws Exception {
		DOBJ _dobj = new DOBJ();
	    HashMap record = null;
	    HashMap processkey= new HashMap();
	    java.util.Enumeration e = req.getParameterNames();
	    String key = null;
	    String value = "";
	    while (e.hasMoreElements()) {
	        key = (String) e.nextElement();
	        //System.out.println("KEYNAME:" + key );

	        if(key.equals("SYSID")){
	            processkey.put("SYSID",req.getParameter(key));
	            continue;
	        }else if(key.equals("MENUID")){
	            processkey.put("MENUID",req.getParameter(key));
	            continue;
	        }else if(key.equals("EVENTID")){
	            processkey.put("EVENTID",req.getParameter(key));
	            continue;
	        }

	        if (!key.equals("X-UIClient")) {
	        	if(record==null) record = new HashMap();
	        	value = req.getParameter(key);
	        	record.put(key, value);
	        }

	    }

            VOBJ _vobj = new VOBJ();
            _vobj.setName("S");
            if(record != null)
                _vobj.addRecord(record);

            //_vobj.Println("S");

            _dobj.setRetObject("S",_vobj);
            _dobj.setParam(processkey);
            return _dobj;
	}

	public DOBJ inputObjectGaucePost(HttpServletRequest reqx) throws Exception {
		HttpGauceRequest req = (HttpGauceRequest)reqx;

		DOBJ _dobj = new DOBJ();

		GauceInputStream gis = req.getGauceInputStream();
		String[] inputKeys = gis.inputKeys();
		String[] outputKeys = gis.outputKeys();

		//System.out.println("OUTPUT DSET LENGTH:" + outputKeys.length);
		//for(int x=0;x<outputKeys.length;x++){
		//	System.out.println("OUTPUT DSET NAME:" + outputKeys[x]);
		//}
		//----------------------------

		_dobj.setInkeys(inputKeys);
		_dobj.setOutkeys(outputKeys);

		GauceDataRow row = null;
		GauceDataColumn[] colinfo = null;
		ArrayList   recs = null;
		HashMap     record=null;

		/*//-----------------------
		HashMap  outkeyMap = new HashMap();
		for (int i=0; i<outputKeys.length; i++)
		{
			GauceDataSet ds = gis.read( outputKeys[i]  );
			outkeyMap.put(outputKeys[i] , ds.getName() );
		}
		System.out.println("OUTKEYMAP:" + outkeyMap);
		_dobj.setGauceOutKeymap(outkeyMap);
		//--------------------------------------
		 */

                if(inputKeys.length != 0 )
                {
                    for(int i=0;i<inputKeys.length;i++)
                    {
                        GauceDataSet dset = gis.read(inputKeys[i]);

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

                            recs.add(record);
                        }


                        VOBJ _vobj = new VOBJ();
                        _vobj.setRecords(recs);

                        _vobj.setName(inputKeys[i]);
                        //_vobj.Println("INPUT");
                        _dobj.setRetObject( inputKeys[i], _vobj);
                    }
                }


                 java.util.Enumeration e = reqx.getParameterNames();
                 String  _namex = "";
                 HashMap _sysinfo = new HashMap();
                 while (e.hasMoreElements()) {
                     _namex = (String) e.nextElement();
                     if (_namex.equals("SYSID")) {
                         _sysinfo.put("SYSID", reqx.getParameter("SYSID"));
                         continue;
                     }
                     if (_namex.equals("MENUID")) {
                         _sysinfo.put("MENUID", reqx.getParameter("MENUID"));
                         continue;
                     }
                     if (_namex.equals("EVENTID")) {
                         _sysinfo.put("EVENTID", reqx.getParameter("EVENTID"));
                         continue;
                     }
                 }
                 _dobj.setParam(_sysinfo);

                 return _dobj;
             }

             private GauceDataSet getGauceDataSet(VOBJ _vobj, GauceOutputStream _gos, GauceInputStream _gis, boolean ispost){

                 GauceDataSet dset = null;
                 if (ispost) {
                     dset = _gis.read(_vobj.getName());
                 } else {
                     dset = new GauceDataSet();
                 }
                 _gos.fragment(dset);

                 // 데이터셋의 헤더 정의
                 ArrayList hlist = _vobj.getHeadnames();
                 String _cname ="";
                 int   dtype =0;
                 for(int i=0;i<hlist.size();i++){
                     _cname = hlist.get(i).toString();

                     //--------------------수정처리 부 2011. 06. 09 수요일
			dtype = _vobj.getHeadColumnType(_cname);
                        switch(dtype)
                        {
                        case Types.BLOB :
                            dset.addDataColumn(new GauceDataColumn(_cname, GauceDataColumn.TB_BLOB, 4000, 0,	GauceDataColumn.TB_NORMAL ));
                            break;
                        case Types.VARCHAR :
                        case Types.CHAR :
                            dset.addDataColumn(new GauceDataColumn(_cname, GauceDataColumn.TB_STRING, _vobj.getHeadColumnLength(_cname), 0,	GauceDataColumn.TB_NORMAL ));
                            break;
                        case Types.CLOB :
                            dset.addDataColumn(new GauceDataColumn(_cname, GauceDataColumn.TB_STRING, 4000, 0,	GauceDataColumn.TB_NORMAL ));
                            break;
                        case Types.DATE :
                            dset.addDataColumn(new GauceDataColumn(_cname, GauceDataColumn.TB_STRING, 20, 0,	GauceDataColumn.TB_NORMAL ));
                            break;
                        case Types.NULL :
                            dset.addDataColumn(new GauceDataColumn(_cname, GauceDataColumn.TB_STRING, 200, 0,	GauceDataColumn.TB_NORMAL ));
                            break;
                        case Types.DECIMAL :
                        case Types.INTEGER :
                        case Types.DOUBLE :
                        case Types.NUMERIC :
                            dset.addDataColumn(new GauceDataColumn(_cname, GauceDataColumn.TB_DECIMAL, _vobj.getHeadColumnNumberSize(_cname), _vobj.getHeadColumnRoundSize(_cname),	GauceDataColumn.TB_NORMAL ));
                            break;
                        default :
                            dset.addDataColumn(new GauceDataColumn(_cname, GauceDataColumn.TB_STRING, 200, 0,	GauceDataColumn.TB_NORMAL ));
                            break;
                        }
                    }

                    // 데이터셋의 Data 추가
                    ArrayList clist = _vobj.getColumnNames();
                    GauceDataRow row = null;
                    _vobj.first();
                    while(_vobj.next()){
                        row = dset.newDataRow();
                        for(int y=0;y < clist.size();y++)
                        {
                            row.addColumnValue(_vobj.getRecord().get(clist.get(y)));
                            //dset.put(clist.get(y).toString(), _vobj.getRecord().get(clist.get(y)), _vobj.getRecord().get(clist.get(y)).length());
                        }
                        dset.addDataRow(row); // FirstRow적용.
                        //dset.heap();
                    }

                    return dset;
                }

	public void outputObjectGauce(HttpServletRequest reqx, HttpServletResponse respx, DOBJ _dobj) throws Exception {

		HttpGauceResponse resp  = (HttpGauceResponse)respx;
		HttpGauceRequest  reqp =  (HttpGauceRequest)reqx;
		GauceOutputStream ios = null;
		GauceInputStream iis = null;
		try{
			ios = resp.getGauceOutputStream();
			iis = reqp.getGauceInputStream();
			ArrayList objnames = _dobj.getRetObjectKeys();
			String keyname ="";
			GauceDataSet dset = null;
			VOBJ _vobj = null;
			//System.out.println("RETURN OBJECT COUNT:"+objnames.size());
			for(int i=0;i<objnames.size();i++)
			{
				keyname = objnames.get(i).toString();
				_vobj = _dobj.getRetObject(keyname);
				//_vobj.Println("OUTPUT");
				dset =  getGauceDataSet(_vobj, ios, iis, _dobj.getIsPostMethod());
				ios.write(dset);
			}

			if (!_dobj.getRetmsg().equals("")) {
				resp.addMessage(_dobj.getRetmsg());
			}

		} catch(Exception e)
		{

			if (resp != null && ios != null) {
				GauceException ge = new GauceException("SVC", "CODE", e.getMessage());
				resp.addException(ge);
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


}
