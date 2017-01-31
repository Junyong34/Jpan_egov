
package hpms.base.service;
import java.util.*;
import egov.wizware.com.*;
public interface ManagementComprehensive
{
    public DOBJ FCST_Title_ComboMgr(DOBJ dobj) throws Exception;
    public DOBJ buildSheetDownload(DOBJ dobj) throws Exception;
    public DOBJ loadpage(DOBJ dobj) throws Exception;
    public DOBJ initCombo(DOBJ dobj) throws Exception;
}