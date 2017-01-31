
package hpms.base.service;
import java.util.*;
import egov.wizware.com.*;
public interface CostInput
{
    public DOBJ Condition_Combo(DOBJ dobj) throws Exception;
    public DOBJ ServiceID00(DOBJ dobj) throws Exception;
    public DOBJ errorConfirm(DOBJ dobj) throws Exception;
    public DOBJ csvdownload(DOBJ dobj) throws Exception;
    public DOBJ CostUpload(DOBJ dobj) throws Exception;
    public DOBJ loadpage(DOBJ dobj) throws Exception;
}