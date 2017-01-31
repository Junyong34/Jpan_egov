
package hpms.common.service;
import java.util.*;
import egov.wizware.com.*;
public interface CommonCodeMgr
{
    public DOBJ AllDataTypeCombo(DOBJ dobj) throws Exception;
    public DOBJ ORGCombobox(DOBJ dobj) throws Exception;
    public DOBJ SessionCombo(DOBJ dobj) throws Exception;
    public DOBJ CommonCombo(DOBJ dobj) throws Exception;
    public DOBJ ORGAuthCombobox(DOBJ dobj) throws Exception;
    public DOBJ Company_Org_cd(DOBJ dobj) throws Exception;
}