package egov.wizware.util;

import java.sql.DriverManager;
import java.util.ArrayList;
import egov.wizware.com.VOBJ;
import java.sql.SQLException;
import java.util.HashMap;
import egov.wizware.com.SQLObject;
import java.sql.Connection;
import egov.wizware.com.QExecutor;
import egov.wizware.dao.Connector;

public class SmsUtil {
    public SmsUtil() {
    }

  // snd_hno : 핸드폰 번호
  // snd_3437 : 전송 message
   public void send(String snd_hno, String snd_3437){
       Connection _9199x=null;
       Connection _9199sql=null;

       try
       {
           if(snd_hno==null || snd_hno.equals("")) return;
           if(snd_3437==null || snd_3437.equals("")) return;

           Connector connection = new Connector();

           _9199x = connection.getConnection("PFDB");   //기간 database 연결처리
           _9199x.setAutoCommit(false);

           _9199sql = connection.getConnection("SQL");  //sql서버 연결처리
           _9199sql.setAutoCommit(false);

           uniqueNumber uno = new uniqueNumber();       // 20자리 message 유일한 id새성

           String cmp_3437_id = uno.getNumber20() ;
           VOBJ mvobj= sndMsg(_9199sql, cmp_3437_id , snd_hno,  snd_3437, null , null , null);   //SQL-SERVER로 MESSAGE저장

           insertLog(_9199x, mvobj);                                        //ADSH.HANARO_SMS_LOG에 LOG저장

           _9199x.commit();
           _9199sql.commit();
       }
       catch(Exception e)
       {
           try{
               _9199x.rollback();
               _9199sql.rollback();
           }catch(Exception ee){
               ee.printStackTrace();
           }
           e.printStackTrace();
       }finally{
           try{
               _9199x.close();
               _9199sql.close();
           }catch(Exception ee){
               ee.printStackTrace();
           }
       }
   }

   public void send(String snd_hno, String snd_3437, String snd_dttm){
          Connection _9199x=null;
          Connection _9199sql=null;

          try
          {
              if(snd_hno==null || snd_hno.equals("")) return;
              if(snd_3437==null || snd_3437.equals("")) return;

              Connector connection = new Connector();

              _9199x = connection.getConnection("PFDB");   //기간 database 연결처리
              _9199x.setAutoCommit(false);

              _9199sql = connection.getConnection("SQL");  //sql서버 연결처리
              _9199sql.setAutoCommit(false);

              uniqueNumber uno = new uniqueNumber();       // 20자리 message 유일한 id새성

              String cmp_3437_id = uno.getNumber20() ;
              VOBJ mvobj= sndMsg(_9199sql, cmp_3437_id , snd_hno,  snd_3437,  snd_dttm, null , null);   //SQL-SERVER로 MESSAGE저장

              insertLog(_9199x, mvobj);                                        //ADSH.HANARO_SMS_LOG에 LOG저장

              _9199x.commit();
              _9199sql.commit();
          }
          catch(Exception e)
          {
              try{
                  _9199x.rollback();
                  _9199sql.rollback();
              }catch(Exception ee){
                  ee.printStackTrace();
              }
              e.printStackTrace();
          }finally{
              try{
                  _9199x.close();
                  _9199sql.close();
              }catch(Exception ee){
                  ee.printStackTrace();
              }
          }
   }


   public void send_SendId(String callback, String recv_hno , String snd_3437){
       Connection _9199x=null;
       Connection _9199sql=null;

       try
       {
           if(recv_hno==null || recv_hno.equals("")) return;
           if(snd_3437==null || snd_3437.equals("")) return;

           Connector connection = new Connector();

           _9199x = connection.getConnection("PFDB");   //기간 database 연결처리
           _9199x.setAutoCommit(false);

           _9199sql = connection.getConnection("SQL");  //sql서버 연결처리
           _9199sql.setAutoCommit(false);

           uniqueNumber uno = new uniqueNumber();       // 20자리 message 유일한 id새성

           String cmp_3437_id = uno.getNumber20() ;
           VOBJ mvobj= sndMsg(_9199sql, cmp_3437_id , recv_hno,  snd_3437, null , callback , null );   //SQL-SERVER로 MESSAGE저장

           insertLog(_9199x, mvobj);                                        //ADSH.HANARO_SMS_LOG에 LOG저장

           _9199x.commit();
           _9199sql.commit();
       }
       catch(Exception e)
       {
           try{
               _9199x.rollback();
               _9199sql.rollback();
           }catch(Exception ee){
               ee.printStackTrace();
           }
           e.printStackTrace();
       }finally{
           try{
               _9199x.close();
               _9199sql.close();
           }catch(Exception ee){
               ee.printStackTrace();
           }
       }
   }


   public void send_Userid(String userid, String recv_hno , String snd_3437){
       Connection _9199x=null;
       Connection _9199sql=null;

       try
       {
           if(recv_hno==null || recv_hno.equals("")) return;
           if(snd_3437==null || snd_3437.equals("")) return;

           Connector connection = new Connector();

           _9199x = connection.getConnection("PFDB");   //기간 database 연결처리
           _9199x.setAutoCommit(false);

           _9199sql = connection.getConnection("SQL");  //sql서버 연결처리
           _9199sql.setAutoCommit(false);

           uniqueNumber uno = new uniqueNumber();       // 20자리 message 유일한 id새성

           String cmp_3437_id = uno.getNumber20() ;
           VOBJ mvobj= sndMsg(_9199sql, cmp_3437_id , recv_hno,  snd_3437, null , null ,userid );   //SQL-SERVER로 MESSAGE저장

           insertLog(_9199x, mvobj);                                        //ADSH.HANARO_SMS_LOG에 LOG저장

           _9199x.commit();
           _9199sql.commit();
       }
       catch(Exception e)
       {
           try{
               _9199x.rollback();
               _9199sql.rollback();
           }catch(Exception ee){
               ee.printStackTrace();
           }
           e.printStackTrace();
       }finally{
           try{
               _9199x.close();
               _9199sql.close();
           }catch(Exception ee){
               ee.printStackTrace();
           }
       }
   }

   private  VOBJ sndMsg(Connection _6773, String cmp_3437_id, String snd_hno, String msg, String snd_dttm , String callback, String userid) throws Exception {
       VOBJ _dmsg = new VOBJ();
       HashMap rec = new HashMap();
       rec.put("CMP_MSG_ID", cmp_3437_id);
       rec.put("CMP_USR_ID", "HMSTP");
       rec.put("ODR_FG"    , "2");
       rec.put("SMS_GB"    , "1");
       rec.put("USED_CD"   , "00");
       rec.put("MSG_GB"    , "A");
       rec.put("SND_PHN_ID", "HMSTP");
       rec.put("RCV_PHN_ID", snd_hno);
       rec.put("SND_MSG"   , msg);
       rec.put("EXPIRE_VAL", "0");
       rec.put("SMS_ST"    , "0");

       if(callback != null)
           rec.put("CALLBACK", callback);
       if(userid != null)
           rec.put("userid"    , userid);
       if(snd_dttm != null)
           rec.put("SND_DTTM"  , snd_dttm);

       ArrayList recs = new ArrayList();
       recs.add(rec);
       _dmsg.setRecords(recs);
       _dmsg.Println("INPUT MSG");
       insertMsg(_6773, _dmsg);
       return _dmsg;
   }


   private  void insertMsg(Connection _6773, VOBJ _dmsg) throws Exception {
       QExecutor dexe = new QExecutor();
       SQLObject sobj = null;
       _dmsg.first();
       while(_dmsg.next()){
           sobj = SQLinsertMsg(_dmsg) ;
           dexe.DispSelectSql(sobj);
           dexe.executeUpdate(_6773, sobj);
       }
   }

   private  SQLObject SQLinsertMsg(VOBJ dvobj) throws Exception
   {
       String   CMP_MSG_ID  = dvobj.getRecord().get("CMP_MSG_ID");   //메시지 고유아이디
       String   CMP_USR_ID  = dvobj.getRecord().get("CMP_USR_ID");   //서비스 아이디(하나로 지정)
       String   ODR_FG      = dvobj.getRecord().get("ODR_FG");       //전송우선 순위 구분(1높음, 2보통)
       String   SMS_GB      = dvobj.getRecord().get("SMS_GB");       //전송위치 구분 (1 기업등 디폴트 1)
       String   USED_CD     = dvobj.getRecord().get("USED_CD");      //사용구분 (00: 일반SMS, 디폴트 00)
       String   MSG_GB      = dvobj.getRecord().get("MSG_GB");       //메시지 구분(A:SMS, B:URL, C:해외SMS, D:해외URL)
       String   SND_PHN_ID  = dvobj.getRecord().get("SND_PHN_ID");   //받는 사람휴대폰 번호(공백없이)
       String   RCV_PHN_ID  = dvobj.getRecord().get("RCV_PHN_ID");   //받는 사람휴대폰 번호(공백없이)
       String   SND_MSG     = dvobj.getRecord().get("SND_MSG");      //메시지 내용
       String   EXPIRE_VAL  = dvobj.getRecord().get("EXPIRE_VAL");   //유효시간 (디폴트:0)
       String   SMS_ST      = dvobj.getRecord().get("SMS_ST");       //메시지 상태(상태값 참조: (0:전송할 메시지, 1:하나로텔레콤으로 전송한 메시지, 2:메시지
       String   SND_DTTM    = "";
       if(dvobj.ContainsColumnName("SND_DTTM"))
       {
           SND_DTTM = dvobj.getRecord().get("SND_DTTM");
           if(SND_DTTM.length() == 14){
           }else if(SND_DTTM.length() == 12){
               SND_DTTM += SND_DTTM +"01";
           }else if(SND_DTTM.length() == 10){
               SND_DTTM += SND_DTTM +"0101";
           }else if(SND_DTTM.length() == 8){
               SND_DTTM += SND_DTTM +"120101";
           }else {
               System.out.println("예약일시 Format 14자리 변환처리 규칙");
               System.out.println("년월일     [20080101 --> 20080101120101]");
               System.out.println("년월일시   [2008010101 --> 20080101010101]");
               System.out.println("년월일시분  [2008010101 --> 20080101010101]");
               System.out.println("년월일시분초는 변환없음");
               throw new  Exception("예약일시 Format오류 입니다.[입력Format길이");
           }
       }
       String CALLBACK =null;
       if(dvobj.ContainsColumnName("CALLBACK"))
       {
           CALLBACK = dvobj.getRecord().get("CALLBACK");
       }

       SQLObject sobj = new SQLObject();
       String    query="";
       query +=" Insert into HANARO_SMS ( \n";
       query      +="  CMP_MSG_ID, \n";
       query      +="  CMP_USR_ID, \n";
       query      +="  ODR_FG,     \n";
       query      +="  SMS_GB,     \n";
       query      +="  USED_CD,    \n";
       query      +="  MSG_GB,     \n";
       query      +="  WRT_DTTM,   \n";
       query      +="  SND_DTTM,   \n";
       query      +="  SND_PHN_ID, \n";
       query      +="  RCV_PHN_ID, \n";
       query      +="  SND_MSG,    \n";
       query      +="  EXPIRE_VAL, \n";
       query      +="  SMS_ST  )   \n";
       query +=" VALUES(   \n";
       query      +="  :CMP_MSG_ID, \n";
       query      +="  :CMP_USR_ID, \n";
       query      +="  :ODR_FG,     \n";
       query      +="  :SMS_GB,     \n";
       query      +="  :USED_CD,    \n";
       query      +="  :MSG_GB,     \n";

       if(dvobj.ContainsColumnName("SND_DTTM"))
       {
           query      +="  :SND_DTTM,     \n";
       }
       else
       {
           query      +="  CONVERT(VARCHAR(8),GETDATE(),112)+SUBSTRING(convert(varchar(8), getdate(), 108),1,2)+SUBSTRING(convert(varchar(8), getdate(), 108),4,2)+SUBSTRING(convert(varchar(8), getdate(), 108),7,2),   \n";
       }

       query      +="  CONVERT(VARCHAR(8),GETDATE(),112)+SUBSTRING(convert(varchar(8), getdate(), 108),1,2)+SUBSTRING(convert(varchar(8), getdate(), 108),4,2)+SUBSTRING(convert(varchar(8), getdate(), 108),7,2),   \n";
       query      +="  :SND_PHN_ID, \n";
       query      +="  :RCV_PHN_ID, \n";
       query      +="  :SND_MSG,    \n";
       query      +="  :EXPIRE_VAL, \n";
       if(dvobj.ContainsColumnName("CALLBACK"))
       {
           query      +="  :CALLBACK, \n";
       }
       query      +="  :SMS_ST  )   \n";

       sobj.setSql(query);
       sobj.setString("CMP_MSG_ID", CMP_MSG_ID);
       sobj.setString("CMP_USR_ID", CMP_USR_ID);
       sobj.setString("ODR_FG", ODR_FG);
       sobj.setString("SMS_GB", SMS_GB);
       sobj.setString("USED_CD", USED_CD);
       sobj.setString("MSG_GB", MSG_GB);
       sobj.setString("SND_PHN_ID", SND_PHN_ID);
       sobj.setString("RCV_PHN_ID", RCV_PHN_ID);
       sobj.setString("SND_MSG", SND_MSG);
       sobj.setString("EXPIRE_VAL", EXPIRE_VAL);
       sobj.setString("SMS_ST", SMS_ST);
       if(dvobj.ContainsColumnName("SND_DTTM"))
       {
           sobj.setString("SND_DTTM", SND_DTTM);
       }
       if(dvobj.ContainsColumnName("CALLBACK"))
       {
           sobj.setString("CALLBACK", CALLBACK);
       }
       return sobj;
   }

   private  void insertLog(Connection _6773, VOBJ _dmsg) throws Exception {
          QExecutor dexe = new QExecutor();
          SQLObject sobj = null;
          _dmsg.first();
          while(_dmsg.next()){
              sobj = SQLinsertLog(_dmsg) ;
              dexe.DispSelectSql(sobj);
              dexe.executeUpdate(_6773, sobj);
          }
   }

   private  SQLObject SQLinsertLog(VOBJ dvobj) throws Exception
  {
      String   CMP_MSG_ID  = dvobj.getRecord().get("CMP_MSG_ID");   //메시지 고유아이디
      String   CMP_USR_ID  = dvobj.getRecord().get("CMP_USR_ID");   //서비스 아이디(하나로 지정)
      String   ODR_FG      = dvobj.getRecord().get("ODR_FG");       //전송우선 순위 구분(1높음, 2보통)
      String   SMS_GB      = dvobj.getRecord().get("SMS_GB");       //전송위치 구분 (1 기업등 디폴트 1)
      String   USED_CD     = dvobj.getRecord().get("USED_CD");      //사용구분 (00: 일반SMS, 디폴트 00)
      String   MSG_GB      = dvobj.getRecord().get("MSG_GB");       //메시지 구분(A:SMS, B:URL, C:해외SMS, D:해외URL)
      String   SND_PHN_ID  = dvobj.getRecord().get("SND_PHN_ID");   //받는 사람휴대폰 번호(공백없이)
      String   RCV_PHN_ID  = dvobj.getRecord().get("RCV_PHN_ID");   //받는 사람휴대폰 번호(공백없이)
      String   SND_MSG     = dvobj.getRecord().get("SND_MSG");      //메시지 내용
      String   EXPIRE_VAL  = dvobj.getRecord().get("EXPIRE_VAL");   //유효시간 (디폴트:0)
      String   SMS_ST      = dvobj.getRecord().get("SMS_ST");       //메시지 상태(상태값 참조: (0:전송할 메시지, 1:하나로텔레콤으로 전송한 메시지, 2:메시지

      String   SND_EMP    =null;
      if(dvobj.ContainsColumnName("userid"))
      {
          SND_EMP    = dvobj.getRecord().get("userid");
      }

      SQLObject sobj = new SQLObject();
      String    query="";
      query +=" Insert into ADSH.HANARO_SMS_LOG ( \n";
      query      +="  CMP_MSG_ID, \n";
      query      +="  CMP_USR_ID, \n";
      query      +="  ODR_FG,     \n";
      query      +="  SMS_GB,     \n";
      query      +="  USED_CD,    \n";
      query      +="  MSG_GB,     \n";
      query      +="  WRT_DTTM,   \n";
      query      +="  SND_DTTM,   \n";
      query      +="  SND_PHN_ID, \n";
      query      +="  RCV_PHN_ID, \n";
      query      +="  SND_MSG,    \n";
      query      +="  EXPIRE_VAL, \n";
      query      +="  SMS_ST  )   \n";
      query +=" VALUES(   \n";
      query      +="  :CMP_MSG_ID, \n";
      query      +="  :CMP_USR_ID, \n";
      query      +="  :ODR_FG,     \n";
      query      +="  :SMS_GB,     \n";
      query      +="  :USED_CD,    \n";
      query      +="  :MSG_GB,     \n";
      query      +="  SYSDATE,     \n";
      query      +="  SYSDATE,     \n";
      query      +="  :SND_PHN_ID, \n";
      query      +="  :RCV_PHN_ID, \n";
      query      +="  :SND_MSG,    \n";
      query      +="  :EXPIRE_VAL, \n";
      if(dvobj.ContainsColumnName("userid"))
      {
          query      +="  :SND_EMP, \n";
      }
      query      +="  :SMS_ST  )   \n";
      sobj.setSql(query);
      sobj.setString("CMP_MSG_ID", CMP_MSG_ID);
      sobj.setString("CMP_USR_ID", CMP_USR_ID);
      sobj.setString("ODR_FG", ODR_FG);
      sobj.setString("SMS_GB", SMS_GB);
      sobj.setString("USED_CD", USED_CD);
      sobj.setString("MSG_GB", MSG_GB);
      sobj.setString("SND_PHN_ID", SND_PHN_ID);
      sobj.setString("RCV_PHN_ID", RCV_PHN_ID);
      sobj.setString("SND_MSG", SND_MSG);
      sobj.setString("EXPIRE_VAL", EXPIRE_VAL);
      sobj.setString("SMS_ST", SMS_ST);
      if(dvobj.ContainsColumnName("userid"))
      {
          sobj.setString("SND_EMP", SND_EMP);
      }

      return sobj;
  }

  private  VOBJ _getTableInfo(Connection _6773) throws Exception{
      VOBJ _227 = null;
      QExecutor dexe = new QExecutor();
      SQLObject _1310 = _getSQLTableInfo();
      _227 = dexe.executeQuery(_6773,_1310);
      _227.Println("SMS:HANARO_SMS");
      return _227;
  }

  private  SQLObject _getSQLTableInfo(){
      SQLObject _1310 = new SQLObject();
      String _2675 ="";
      _2675 =" Select * from HANARO_SMS";
      _1310.setSql(_2675);
      return _1310;
  }

  private  void deleteMsg(Connection _6773, String msg_id)  throws Exception {
      QExecutor dexe = new QExecutor();
      SQLObject sobj = SQLDeleteMsg(msg_id);
      dexe.DispSelectSql(sobj);
      dexe.executeUpdate(_6773, sobj);
  }

  private  SQLObject SQLDeleteMsg(String msg_id) throws Exception {
      SQLObject sobj = new SQLObject();
      String    query="";
      query += " delete from  HANARO_SMS where CMP_MSG_ID=:CMP_MSG_ID \n";
      sobj.setSql(query);
      sobj.setString("CMP_MSG_ID",msg_id);
      return sobj;
   }

}
