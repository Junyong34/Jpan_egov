
package hpms.base.service;
import java.util.*;
import egov.wizware.com.*;
public interface WorkDataInput
{
    public DOBJ errorConfirm(DOBJ dobj) throws Exception;
    public DOBJ workCountUpload(DOBJ dobj) throws Exception;
    public DOBJ loadpage(DOBJ dobj) throws Exception;
}