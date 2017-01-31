
package hpms.base.dao;
import java.util.*;
import egov.wizware.com.*;
import egov.wizware.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import com.ibatis.sqlmap.client.SqlMapClient;
import javax.annotation.Resource;
@Repository("WorkDataInput20160912111169Dao")
public class WorkDataInputDao extends EgovAbstractDAO
{
    // ExcelUpload Data
    public VOBJ workCountUpload_PEX2(DOBJ dobj) throws Exception
    {
        CommUtil cu = new CommUtil(dobj);
        HashMap   classinfo = new HashMap();
        classinfo.put("OBJECTID", "PEX2" );
        classinfo.put("PACKAGE", "hpms.UserObject.Excel" );
        classinfo.put("CLASS", "WorkOutputUpload" );
        classinfo.put("METHOD", "uploadWorkOutputExcel" );
        dobj = cu.callPSExternal(dobj, null, classinfo );
        VOBJ dvobj = dobj.getRetObject("UP01");
        dvobj.setName("PEX2");
        dvobj.Println("PEX2");
        return dvobj;
    }
    // TEST
    public VOBJ workCountUpload_CVT40(DOBJ dobj) throws Exception
    {
        dobj.setRtnname("CVT40");
        WizUtil wutil = new WizUtil(dobj,"CVT40", "TEST" );
        VOBJ dvobj = dobj.getRetObjectCopy("PEX2");        //ExcelUpload Data Input Object(CALLworkCountUpload_PEX2)
        String[] outcolumns = 
        {
        "APPLICATION", "CUSTOMER_CD", "DATA_TYPE", "HPMS_ID", "HPMS_ID_NM_JP", "INVEST_TYPE_CD", "INVEST_TYPE_NAME", "ITEM_NAME", "PID", "PID_THEME", "PROCESSING_FLAG", "REQ_COMPANY_CD", "REQ_ORG_CD", "UNIT", "UPDATE_NAME", "UPDATE_TIME", "USE_COMPANY_CD", "USE_ORG_CD", "VAL", "YYYYMM"
        }
        ;;
        HashMap    record =null;
        dvobj.first();
        while(dvobj.next())
        {
            record = dvobj.getRecordMap();
            boolean isfind=false;
            ArrayList alist = new ArrayList();
            Iterator itor = record.keySet().iterator();
            while(itor.hasNext())
            {
            
                alist.add(itor.next().toString());
            }
            for(int i=0;i<alist.size();i++)
            {
            
                isfind = false;
                for(int j=0;j<outcolumns.length;j++)
                {
                
                    if(alist.get(i).equals(outcolumns[j])) 
                    {
                    
                        isfind=true;
                    }
                }
                if(isfind == false)
                {
                
                    record.remove(alist.get(i));
                }
            }
            dvobj.setRecord(record);
        }
        dvobj.setName("CVT40") ;
        dvobj.Println("CVT40");
        return dvobj;
    }
    // get LogSequence
    public VOBJ workCountUpload_ER01(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"ER01", "get LogSequence" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("WorkDataInput_20160912111169.workCountUpload_ER01", param);
        dvobj.setName("ER01");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // MasterSearch(Error Check)
    public VOBJ workCountUpload_SEL17(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL17", "MasterSearch(Error Check)" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("UNIT", dobj.getRetObject("R").getRecord().get("UNIT"));   //통화단위
        param.put("PID", dobj.getRetObject("R").getRecord().get("PID"));   //PID
        param.put("HPMS_ID", dobj.getRetObject("R").getRecord().get("HPMS_ID"));   //HPMS_ID
        param.put("USE_ORG_CD", dobj.getRetObject("R").getRecord().get("USE_ORG_CD"));   //費用？生元部CD
        param.put("DATA_TYPE", dobj.getRetObject("R").getRecord().get("DATA_TYPE"));   //デ？タタイプ
        param.put("USE_COMPANY_CD", dobj.getRetObject("R").getRecord().get("USE_COMPANY_CD"));   //費用？生元？社CD
        List rlist = list("WorkDataInput_20160912111169.workCountUpload_SEL17", param);
        dvobj.setName("SEL17");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // WorkTable Save HP2D001W
    public VOBJ workCountUpload_UNI38(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"UNI38", "WorkTable Save HP2D001W" );
        VOBJ dvobj = dobj.getRetObject("R");           //Loop (MPD,MIUD) Currect Record Object
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        HashMap    param= null;
        int        inscnt=0, updcnt =0 ,unicnt=0,  rtncnt=0;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("MSGCODE", dobj.getRetmsg());   //MSGCODE
            param.put("UPDATE_TIME", "");   //갱신일시
            param.put("USE_COMPANY_CD", dvobj.getRecord().get("USE_COMPANY_CD"));   //費用？生元？社CD
            param.put("USE_ORG_CD", dvobj.getRecord().get("USE_ORG_CD"));   //費用？生元部CD
            param.put("ITEM_NAME", dvobj.getRecord().get("ITEM_NAME"));   //ITEM명
            param.put("YYYYMM", dvobj.getRecord().get("YYYYMM"));   //실적월
            param.put("VAL", dvobj.getRecord().getDouble("VAL")+"");   //Value
            param.put("INVEST_TYPE_CD", dvobj.getRecord().get("INVEST_TYPE_CD"));   //投資？分CD
            param.put("HPMS_ID", dvobj.getRecord().get("HPMS_ID"));   //HPMS_ID
            param.put("APPLICATION", dvobj.getRecord().get("APPLICATION"));   //用途
            param.put("PID", dvobj.getRecord().get("PID"));   //PID
            param.put("UNIT", dvobj.getRecord().get("UNIT"));   //통화단위
            param.put("CUSTOMER_CD", dvobj.getRecord().get("CUSTOMER_CD"));   //顧客CD
            param.put("DATA_TYPE", "ACT");   //デ？タタイプ
            param.put("LOG_SEQ", dobj.getRetObject("ER01").getRecord().get("LOG_SEQ"));   //LOG_SEQ
            param.put("PROCESSING_FLAG", "I");   //？理フラグ
            param.put("REQ_COMPANY_CD", "#");   //要求元？社CD
            param.put("REQ_ORG_CD", "#");   //要求元部CD
            param.put("UPLOAD_FILE_NAME", dobj.getRetObject("S1").getRecord().get("FILE_NAME"));   //UPLOAD_FILE_NAME
            param.put("USER_ID", dobj.getRetObject("G").getRecord().get("USER_ID"));   //사용자ID
            String  ERR_FLAG="" ;          //エラ？フラグ
            if(!param.get("MSGCODE").toString().equals("") && dobj.getGVString ( "FLAG" ).equals(""))
            {
               ERR_FLAG = "1";
                
            }
            else 
            {
               ERR_FLAG = "0";
                
            }
              param.put("ERR_FLAG", ERR_FLAG);   //エラ？フラグ
            String  ERR_MSG="" ;          //오류메시지
            if(param.get("MSGCODE").toString().equals("2000") && dobj.getGVString ( "FLAG" ).equals(""))
            {
               ERR_MSG = "TEST";
                
            }
            else if(param.get("MSGCODE").toString().equals("1000") && dobj.getGVString ( "FLAG" ).equals(""))
            {
               ERR_MSG = "PID not Found";
                
            }
            else if(param.get("MSGCODE").toString().equals("1001") && dobj.getGVString ( "FLAG" ).equals(""))
            {
               ERR_MSG = "DataType not Found";
                
            }
            else if(param.get("MSGCODE").toString().equals("1002") && dobj.getGVString ( "FLAG" ).equals(""))
            {
               ERR_MSG = "Investment code not Found";
                
            }
            else if(param.get("MSGCODE").toString().equals("1003") && dobj.getGVString ( "FLAG" ).equals(""))
            {
               ERR_MSG = "USE_COMPANY/ORG not Found";
                
            }
            else if(param.get("MSGCODE").toString().equals("1004") && dobj.getGVString ( "FLAG" ).equals(""))
            {
               ERR_MSG = "REQ_COMPANY_ORG not Found";
                
            }
            else if(param.get("MSGCODE").toString().equals("1005") && dobj.getGVString ( "FLAG" ).equals(""))
            {
               ERR_MSG = "HPMS_ID not Found";
                
            }
            else if(param.get("MSGCODE").toString().equals("1006") && dobj.getGVString ( "FLAG" ).equals(""))
            {
               ERR_MSG = "Investment code not Found";
                
            }
            else if(param.get("MSGCODE").toString().equals("1007") && dobj.getGVString ( "FLAG" ).equals(""))
            {
               ERR_MSG = "Customer Code not Found";
                
            }
            else if(param.get("MSGCODE").toString().equals("1008") && dobj.getGVString ( "FLAG" ).equals(""))
            {
               ERR_MSG = "Insert Error not Found";
                
            }
            else if(param.get("MSGCODE").toString().equals("1009") && dobj.getGVString ( "FLAG" ).equals(""))
            {
               ERR_MSG = "DATA TYPE(AUTH) not Found";
                
            }
            else if(param.get("MSGCODE").toString().equals("1010") && dobj.getGVString ( "FLAG" ).equals(""))
            {
               ERR_MSG = "YYYYMM Format Error";
                
            }
            else if(param.get("MSGCODE").toString().equals("1011") && dobj.getGVString ( "FLAG" ).equals(""))
            {
               ERR_MSG = "Number Format Error";
                
            }
            else if(param.get("MSGCODE").toString().equals("1012") && dobj.getGVString ( "FLAG" ).equals(""))
            {
               ERR_MSG = "Length Error(overflow)";
                
            }
            else if(param.get("MSGCODE").toString().equals("1013") && dobj.getGVString ( "FLAG" ).equals(""))
            {
               ERR_MSG = "Insert Error(Key not Found)";
                
            }
            else if(param.get("MSGCODE").toString().equals("1014") && dobj.getGVString ( "FLAG" ).equals(""))
            {
               ERR_MSG = "Insert Error Duplicated Data";
                
            }
            else if(param.get("MSGCODE").toString().equals("1015") && dobj.getGVString ( "FLAG" ).equals(""))
            {
               ERR_MSG = "Currency Code not Found";
                
            }
            else 
            {
               ERR_MSG = "";
                
            }
              param.put("ERR_MSG", ERR_MSG);   //오류메시지
            rtncnt= update("WorkDataInput_20160912111169.workCountUpload_UNI38_UPD",param);
            if(rtncnt < 1)
            {
                insert("WorkDataInput_20160912111169.workCountUpload_UNI38_INS",param);
                inscnt++;
                unicnt++;
                
            }
            else
            {
                updcnt += rtncnt;
                unicnt += rtncnt;
            }
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        rvobj.setHeadColumn("INSCNT" , "INT" );
        rvobj.setHeadColumn("UNICNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        recordx.put("INSCNT",inscnt+"");
        recordx.put("UNICNT",unicnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("UNI38") ;
        return rvobj;
    }
    // ORG_LVL (Login User)
    public VOBJ workCountUpload_SEL33(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL33", "ORG_LVL (Login User)" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("ORG_CD", dobj.getRetObject("G").getRecord().get("ORG_CD"));   //ORG_CD
        param.put("COMPANY_CD", dobj.getRetObject("G").getRecord().get("COMPANY_CD"));   //COMPANY_CD
        List rlist = list("WorkDataInput_20160912111169.workCountUpload_SEL33", param);
        dvobj.setName("SEL33");
        dvobj.setRecords(rlist);
        dvobj.Println("SEL33");
        return dvobj;
    }
    // OrgCode(AUTH) Count
    public VOBJ workCountUpload_SEL35(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL35", "OrgCode(AUTH) Count" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("USER_ID", dobj.getRetObject("G").getRecord().get("USER_ID"));   //사용자ID
        param.put("ORG_CD", dobj.getRetObject("R").getRecord().get("USE_ORG_CD"));   //ORG_CD
        param.put("COMPANY_CD", dobj.getRetObject("R").getRecord().get("USE_COMPANY_CD"));   //COMPANY_CD
        List rlist = list("WorkDataInput_20160912111169.workCountUpload_SEL35", param);
        dvobj.setName("SEL35");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // HP3D001T(Insert)
    public VOBJ workCountUpload_UNI36(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"UNI36", "HP3D001T(Insert)" );
        VOBJ dvobj = dobj.getRetObject("PEX2");           //ExcelUpload Data Input Object(CALLworkCountUpload_PEX2)
        SQLObject  sobj = null;
        VOBJ       rvobj= null;
        HashMap    param= null;
        int        inscnt=0, updcnt =0 ,unicnt=0,  rtncnt=0;
        dvobj.first();
        while(dvobj.next())
        {
            param = new HashMap();
            param.put("UPDATE_TIME", "");   //갱신일시
            param.put("YYYYMM", dvobj.getRecord().get("YYYYMM"));   //실적월
            param.put("VAL", dvobj.getRecord().getDouble("VAL")+"");   //Value
            param.put("HPMS_ID", dvobj.getRecord().get("HPMS_ID"));   //HPMS_ID
            param.put("PID", dvobj.getRecord().get("PID"));   //PID
            param.put("UNIT", dvobj.getRecord().get("UNIT"));   //통화단위
            param.put("APPLICATION", "#");   //用途
            param.put("CUSTOMER_CD", "#");   //顧客CD
            param.put("DATA_TYPE", "ACT");   //デ？タタイプ
            param.put("INVEST_TYPE_CD", "#");   //投資？分CD
            param.put("ITEM_NAME", "#");   //ITEM명
            param.put("REQ_COMPANY_CD", "#");   //要求元？社CD
            param.put("REQ_ORG_CD", "#");   //要求元部CD
            param.put("UNIT2", dvobj.getRecord().get("UNIT"));   //UNIT2
            param.put("UNIT3", dvobj.getRecord().get("UNIT"));   //UNIT2
            param.put("UNIT4", dvobj.getRecord().get("UNIT"));   //UNIT4
            param.put("UPDATE_USER_ID", dobj.getRetObject("G").getRecord().get("USER_ID"));   //갱신자ID
            param.put("UPLOAD_FILE_NAME", dobj.getRetObject("S1").getRecord().get("FILE_UNNAME"));   //UPLOAD_FILE_NAME
            param.put("USE_COMPANY_CD", dvobj.getRecord().get("USE_COMPANY_CD"));   //費用？生元？社CD
            param.put("USE_ORG_CD", dvobj.getRecord().get("USE_ORG_CD"));   //費用？生元部CD
            param.put("VAL2", dvobj.getRecord().getDouble("VAL"));   //VAL2
            param.put("VAL3", dvobj.getRecord().getDouble("VAL"));   //VAL3
            param.put("VAL4", dvobj.getRecord().getDouble("VAL"));   //VAL4
            rtncnt= update("WorkDataInput_20160912111169.workCountUpload_UNI36_UPD",param);
            if(rtncnt < 1)
            {
                insert("WorkDataInput_20160912111169.workCountUpload_UNI36_INS",param);
                inscnt++;
                unicnt++;
                
            }
            else
            {
                updcnt += rtncnt;
                unicnt += rtncnt;
            }
        }
        rvobj = new VOBJ();
        rvobj.setHeadColumn("UPDCNT" , "INT" );
        rvobj.setHeadColumn("INSCNT" , "INT" );
        rvobj.setHeadColumn("UNICNT" , "INT" );
        HashMap recordx = new HashMap();
        recordx.put("UPDCNT",updcnt+"");
        recordx.put("INSCNT",inscnt+"");
        recordx.put("UNICNT",unicnt+"");
        rvobj.addRecord(recordx);
        rvobj.setName("UNI36") ;
        return rvobj;
    }
    // get Cost Data
    public VOBJ errorConfirm_SEL1(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL1", "get Cost Data" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        param.put("LOG_SEQ", dobj.getRetObject("S9").getRecord().get("LOG_SEQ"));   //LOG_SEQ
        List rlist = list("WorkDataInput_20160912111169.errorConfirm_SEL1", param);
        dvobj.setName("SEL1");
        dvobj.setRecords(rlist);
        dvobj.Println("SEL1");
        return dvobj;
    }
    // FILE_NAME_Info
    public VOBJ errorConfirm_SEL5(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL5", "FILE_NAME_Info" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("WorkDataInput_20160912111169.errorConfirm_SEL5", param);
        dvobj.setName("SEL5");
        dvobj.setRecords(rlist);
        return dvobj;
    }
    // ROOTPATH
    public VOBJ errorConfirm_PEX5(DOBJ dobj) throws Exception
    {
        CommUtil cu = new CommUtil(dobj);
        HashMap   classinfo = new HashMap();
        classinfo.put("OBJECTID", "PEX5" );
        classinfo.put("PACKAGE", "hpms.UserObject.BatchExe" );
        classinfo.put("CLASS", "FileUtility" );
        classinfo.put("METHOD", "rootPath" );
        dobj = cu.callPSExternal(dobj, null, classinfo );
        VOBJ dvobj = dobj.getRetObject("FN02");
        dvobj.setName("PEX5");
        return dvobj;
    }
    // CSV File Build
    public VOBJ errorConfirm_FBD4(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"FBD4", "CSV File Build" );
        VOBJ dvobj = dobj.getRetObject("SEL1");           //get Cost Data Input Object(CALLerrorConfirm_SEL1)
        String path = dobj.getRetObject("PEX5").getRecord().get("ROOTPATH") + "/ExcelDownLoad";
        String filename = "errorData" + "_" + dobj.getRetObject("G").getRecord().get("USER_ID") + "_" + dobj.getRetObject("SEL5").getRecord().get("CDATE") + ".csv";
        String creategbn ="3";
        String layout ="ERR_MSG,1D,',','' : ERR_FLAG,1D,',','' : DATA_TYPE,1D,',','' : PID,1D,',','' : USE_COMPANY_CD,1D,',','' : USE_ORG_CD,1D,',','' : REQ_COMPANY_CD,1D,',','' : HPMS_ID,1D,',','' : REQ_ORG_CD,1D,',','' : CUSTOMER_CD,1D,',','' : INVEST_TYPE_CD,1D,',','' : APPLICATION,1D,',','' : ITEM_NAME,1D,',','' : UPLOAD_FILE_NAME,1D,'\\r\\n',''";
        String _4622 ="\n";
        String charset ="UTF-8";
        String filenamex = wutil.makeFile(dobj, dvobj, path, filename, creategbn, layout, _4622, charset);
        VOBJ rvobj = new VOBJ();
        HashMap rec = new HashMap();
        rec.put("FILENAME",filenamex);
        rvobj.addRecord(rec);
        rvobj.setName("FBD4");
        return rvobj;
    }
    // downfile seting
    public VOBJ errorConfirm_DN01(DOBJ dobj) throws Exception
    {
        dobj.setRtnname("DN01");
        WizUtil wutil = new WizUtil(dobj,"DN01", "downfile seting" );
        VOBJ dvobj = dobj.getRetObjectCopy("FBD4");        //CSV File Build Input Object(CALLerrorConfirm_FBD4)
        String[] outcolumns =
        {
            "FILE_PATH", "FILE_UNNAME", "FILE_NAME"
        }
        ;;
        HashMap    record =null;
        dvobj.first();
        while(dvobj.next())
        {
            record = dvobj.getRecordMap();
            String   FILE_NAME = "errorData"+"_"+dobj.getRetObject("G").getRecord().get("USER_ID")+"_"+dobj.getRetObject("SEL5").getRecord().get("CDATE")+".csv";         //파일이름
            record.put("FILE_NAME",FILE_NAME);
            String   FILE_PATH = dobj.getRetObject("PEX5").getRecord().get("ROOTPATH")+"/ExcelDownLoad";         //파일path
            record.put("FILE_PATH",FILE_PATH);
            String   FILE_UNNAME = dvobj.getRecord().get("FILENAME");         //유니크파일
            record.put("FILE_UNNAME",FILE_UNNAME);
            boolean isfind=false;
            ArrayList alist = new ArrayList();
            Iterator itor = record.keySet().iterator();
            while(itor.hasNext())
            {
                alist.add(itor.next().toString());
            }
            for(int i=0;i<alist.size();i++)
            {
                isfind = false;
                for(int j=0;j<outcolumns.length;j++)
                {
                    if(alist.get(i).equals(outcolumns[j]))
                    {
                        isfind=true;
                    }
                }
                if(isfind == false)
                {
                    record.remove(alist.get(i));
                }
            }
            dvobj.setRecord(record);
        }
        dvobj.setName("DN01") ;
        return dvobj;
    }
    // get LogSequence
    public VOBJ workCountUpload_SEL87(DOBJ dobj) throws Exception
    {
        WizUtil wutil = new WizUtil(dobj,"SEL87", "get LogSequence" );
        HashMap param = null;
        VOBJ dvobj = new VOBJ();
        param = new HashMap();
        List rlist = list("WorkDataInput_20160912111169.workCountUpload_SEL87", param);
        dvobj.setName("SEL87");
        dvobj.setRetcode(1);
        dvobj.setRecords(rlist);
        return dvobj;
    }
}