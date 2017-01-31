package egov.wizware.msg;


public class ErrMsgFilter {
    private String errmsg="";
    private String triggermsg="";

    public ErrMsgFilter(String msg) {
        if(msg != null) {
            this.errmsg = msg;
        }
    }

    public String getErrmsg(){
        return this.errmsg;
    }

    public void SetErrmsg(String msg) {
        this.errmsg = msg;
    }

    public String getSQLExceptionCode(){
        int Sqlindex=0;
        String tmpMsg="";
        String sqlerr = "ORA-";
        if( (Sqlindex = this.errmsg.indexOf(sqlerr)) != -1){
            tmpMsg = this.errmsg.substring(Sqlindex+sqlerr.length());
            tmpMsg = tmpMsg.substring(0,tmpMsg.indexOf(":"));
            tmpMsg = tmpMsg.substring(tmpMsg.indexOf("-")+1);
        }else {
            tmpMsg="99999";
        }
        if(Integer.parseInt(tmpMsg) >= 20000){
            this.triggermsg = this.setTriggermsg(errmsg);
        }
        return tmpMsg;
    }

    public String getSQLExceptionMsg(){
        int Sqlindex=0;
        String sqlerr = "java.sql.SQLException:";
        Sqlindex = this.errmsg.indexOf(sqlerr);
        String tmpMsg = this.errmsg.substring(Sqlindex+sqlerr.length());

        return tmpMsg;
    }

    public String getExceptionName(){
        int Sqlindex=0;
        String sqlerr = "is: ";
        String tmpMsg="";

        if((Sqlindex = this.errmsg.lastIndexOf(sqlerr)) != -1){
            tmpMsg = this.errmsg.substring(Sqlindex+sqlerr.length());
            if(tmpMsg != null){
                tmpMsg = tmpMsg.substring(0,tmpMsg.indexOf(":"));
            }else {
                tmpMsg="????Exception";
            }
        }else{
            tmpMsg="????Exception";
        }
        return tmpMsg;
    }

    public String setTriggermsg(String expmsg){
        String retmsg ="";
        if(expmsg.indexOf("|") != -1) {
            retmsg = expmsg.substring(0, expmsg.indexOf("|"));
            retmsg = retmsg.substring(retmsg.lastIndexOf("ORA-"), retmsg.length());
        }
        return retmsg;
    }

    public String setTriggermsg(){
        String retmsg ="";
        retmsg = errmsg.substring(0,errmsg.indexOf("|"));
        retmsg = retmsg.substring(retmsg.lastIndexOf("ORA-"),retmsg.length());
        return retmsg;
    }

    public String getTriggermsg(){
        return this.triggermsg;
    }

    public String getSplMsg(String expmsg){
        String retmsg ="";
        retmsg = expmsg.substring(0,expmsg.indexOf("|"));
        retmsg = retmsg.substring(retmsg.lastIndexOf("ORA-"),retmsg.length());
        return retmsg;
    }

    public String getSplMsg(){
        String retmsg ="";
        retmsg = errmsg.substring(0,errmsg.indexOf("|"));
        retmsg = retmsg.substring(retmsg.lastIndexOf("ORA-"),retmsg.length());
        return retmsg;
    }

    public String getSplCode(String expmsg){
        String retCode="";
        retCode = expmsg.substring(expmsg.indexOf("ORA-")+4,expmsg.indexOf(":"));
        return retCode;
    }

    public String getSplCode(){
        String retCode="";
        retCode = errmsg.substring(errmsg.indexOf("ORA-")+4,errmsg.indexOf(":"));
        return retCode;
    }

}


