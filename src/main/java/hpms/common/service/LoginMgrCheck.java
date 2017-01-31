
package hpms.common.service;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import egov.wizware.com.*;
public interface LoginMgrCheck
{
    public DOBJ SessionLogInfo(DOBJ dobj , HttpServletRequest request) throws Exception;
    public DOBJ wizLoginSession(DOBJ dobj, HttpServletRequest request) throws Exception;
    public DOBJ System_Developer_Check(DOBJ dobj, HttpServletRequest request) throws Exception;
}