
package hpms.common.service;
import java.util.*;
import egov.wizware.com.*;
public interface Control
{
    public DOBJ SearchUsingCheck(DOBJ dobj) throws Exception;
    public DOBJ saveUsingCheck(DOBJ dobj) throws Exception;
    public DOBJ MonthlyUsingCheck(DOBJ dobj) throws Exception;
}