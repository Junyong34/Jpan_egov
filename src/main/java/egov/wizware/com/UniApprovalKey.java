package egov.wizware.com;
import java.math.*;

public class UniApprovalKey {
    private String unikey="";
    private String ipaddress="";
    private String userid="12345678";
    private String residentno="";
    public UniApprovalKey(){
    }
    public UniApprovalKey(String ipaddr, String userid, String residentno){
        this.ipaddress = ipaddr;
        this.userid = userid;
        this.residentno = residentno;
    }

    public int getUnikey(){
        int  useridno = Integer.parseInt(this.userid);
        int unikey = (int)(Math.random() * useridno);
        System.out.println("UNIKEY KEY:"+unikey +"");
        return unikey;
    }

}
