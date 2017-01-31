
package hpms.base.service;
import java.util.*;
import egov.wizware.com.*;
public interface BatchControl
{
    public DOBJ LMP_ConfirmCheck(DOBJ dobj) throws Exception;
    public DOBJ getConfirmInfo(DOBJ dobj) throws Exception;
    public DOBJ pageInit(DOBJ dobj) throws Exception;
    public DOBJ copyMiddle(DOBJ dobj) throws Exception;
    public DOBJ copyMP(DOBJ dobj) throws Exception;
    public DOBJ copyLMP(DOBJ dobj) throws Exception;
    public DOBJ managedatabuild(DOBJ dobj) throws Exception;
    public DOBJ writeRelease(DOBJ dobj) throws Exception;
    public DOBJ writeStop(DOBJ dobj) throws Exception;
    public DOBJ loadpage(DOBJ dobj) throws Exception;
}