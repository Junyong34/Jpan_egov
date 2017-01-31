
package hpms.batch.service;
import java.util.*;
import egov.wizware.com.*;
public interface BatchMain
{
    public DOBJ Day_Main(DOBJ dobj) throws Exception;
    public DOBJ Night_Main(DOBJ dobj) throws Exception;
}