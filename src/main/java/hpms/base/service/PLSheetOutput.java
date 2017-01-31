
package hpms.base.service;
import java.util.*;
import egov.wizware.com.*;
public interface PLSheetOutput
{
    public DOBJ ServiceID01(DOBJ dobj) throws Exception;
    public DOBJ ServiceID00(DOBJ dobj) throws Exception;
    public DOBJ initCombo(DOBJ dobj) throws Exception;
    public DOBJ PIDCheck(DOBJ dobj) throws Exception;
    public DOBJ buildPLSheetDownload(DOBJ dobj) throws Exception;
    public DOBJ loadpage(DOBJ dobj) throws Exception;
}