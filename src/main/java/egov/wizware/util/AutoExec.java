package egov.wizware.util;
import java.util.*;
import egov.wizware.com.*;

public class AutoExec {
    public AutoProc before = null;
    public AutoProc after = null;
    public AutoExec(DOBJ _dobj) {
        before = new AutoProc(_dobj,1);
        after = new AutoProc(_dobj,2);
    }
    public void setRecord(HashMap _rmap)
    {
        VOBJ _vobj = new VOBJ();
        _vobj.addRecord(_rmap);
        _vobj.setName("A");
        before.setVobj(_vobj);
        after.setVobj(_vobj);
    }

}


/*
 // ������� ���
 public DOBJ CALLINS_INS1(Connection Conn, DOBJ dobj) throws Exception
 {

     AutoExec auto = new AutoExec(dobj);           //������ ȣ��
     auto.before.add("SYSID","MENUID","EVENTID");  //BEFORE:NOTE
     auto.before.add("SYSID","MENUID","EVENTID");  //BEFORE:NOTE
     auto.after.add("SYSID","MENUID","EVENTID");   //AFTER:NOTE
     auto.after.add("SYSID","MENUID","EVENTID");   //AFTER:NOTE

     dvobj.first();
     while(dvobj.next())
     {

         auto.setRecObject(vobj.getRecord());
         sobj = SQLINS_INS1(dobj, dvobj);

         auto.before.executor(Conn);

         updcnt += qexe.executeUpdate(Conn, sobj);

         auto.after.executor(Conn);

     }

     return dobj;
 }
 */
