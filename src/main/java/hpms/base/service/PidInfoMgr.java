
package hpms.base.service;
import java.util.*;
import egov.wizware.com.*;
public interface PidInfoMgr
{
    public DOBJ Result_loadPage(DOBJ dobj) throws Exception;
    public DOBJ MasterComboList(DOBJ dobj) throws Exception;
    public DOBJ CompletePage(DOBJ dobj) throws Exception;
    public DOBJ loadpage(DOBJ dobj) throws Exception;
}