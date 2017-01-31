
package hpms.base.service;
import java.util.*;
import egov.wizware.com.*;
public interface PidListMgr
{
    public DOBJ ServiceITest(DOBJ dobj) throws Exception;
    public DOBJ PIDListinfo(DOBJ dobj) throws Exception;
    public DOBJ loadpage(DOBJ dobj) throws Exception;
}