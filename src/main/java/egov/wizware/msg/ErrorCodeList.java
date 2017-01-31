package egov.wizware.msg;

public class ErrorCodeList {
    public static final int NOTASSIGN = -1 ;
    public static final int SUCCESS = 0 ;
    public static final int BusinessDelegateError = 1 ;
    public static final String[] errorCodeName
        = {"SUCCESS","BusinessDelegateError"} ;
    public static final String[] errorCodeString
        = {"����","BusinessDelegate ����"} ;

    public static final String getErrorCodeName(int errorCode){
        if (errorCode == NOTASSIGN) {
            return "�Ҵ�ȵ�" ;
        }else {
            return errorCodeName[errorCode] ;
        }
    }

    public static final String getErrorCodeString(int errorCode){
        if (errorCode == NOTASSIGN) {
          return "�Ҵ�ȵ�" ;
      }else {
          return errorCodeString[errorCode] ;
      }
    }


}
