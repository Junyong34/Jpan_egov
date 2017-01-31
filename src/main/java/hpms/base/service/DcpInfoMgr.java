
package hpms.base.service;
import java.util.*;
import egov.wizware.com.*;
public interface DcpInfoMgr
{
    public DOBJ BLOB_DCPFileDown(DOBJ dobj) throws Exception;
    public DOBJ DCP_PIDCheck(DOBJ dobj) throws Exception;
    public DOBJ Approval_applicant_ORG_Combo(DOBJ dobj) throws Exception;
    public DOBJ DCP_Calculate(DOBJ dobj) throws Exception;
    public DOBJ ApprovalDataSearch(DOBJ dobj) throws Exception;
    public DOBJ PIDConfirm(DOBJ dobj) throws Exception;
    public DOBJ DCPApproval_Flow(DOBJ dobj) throws Exception;
    public DOBJ DCPInfoUpdate(DOBJ dobj) throws Exception;
    public DOBJ FileInfoAdd(DOBJ dobj) throws Exception;
    public DOBJ DCPSearchInfo(DOBJ dobj) throws Exception;
    public DOBJ loadpage(DOBJ dobj) throws Exception;
}