
package hpms.base.service;
import java.util.*;
import egov.wizware.com.*;
public interface PlanMgr
{
    public DOBJ initCombo(DOBJ dobj) throws Exception;
    public DOBJ errorConfirm(DOBJ dobj) throws Exception;
    public DOBJ planExcelDownload(DOBJ dobj) throws Exception;
    public DOBJ ExcelNoChecking(DOBJ dobj) throws Exception;
    public DOBJ loadpage(DOBJ dobj) throws Exception;
}