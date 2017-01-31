
package hpms.base.service;
import java.util.*;
import egov.wizware.com.*;
public interface SubPidInfoMgr
{
    public DOBJ Result_loadPage(DOBJ dobj) throws Exception;
    public DOBJ subpidreslut_page(DOBJ dobj) throws Exception;
    public DOBJ subPIDResult(DOBJ dobj) throws Exception;
    public DOBJ SubPIDPopupPage(DOBJ dobj) throws Exception;
    public DOBJ sub_PID_NumberSearch(DOBJ dobj) throws Exception;
    public DOBJ loadpage(DOBJ dobj) throws Exception;
}