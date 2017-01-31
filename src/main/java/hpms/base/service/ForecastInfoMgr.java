
package hpms.base.service;
import java.util.*;
import egov.wizware.com.*;
public interface ForecastInfoMgr
{
    public DOBJ FCSTCOPYCombo(DOBJ dobj) throws Exception;
    public DOBJ FCST_TITLE_COMBO(DOBJ dobj) throws Exception;
    public DOBJ Forecast_ORGCheck(DOBJ dobj) throws Exception;
    public DOBJ Forecast_PIDcheck(DOBJ dobj) throws Exception;
    public DOBJ ServiceID00(DOBJ dobj) throws Exception;
    public DOBJ ForecastDataCopy2(DOBJ dobj) throws Exception;
    public DOBJ ForecastDataCopy(DOBJ dobj) throws Exception;
    public DOBJ ForecastTitleSet2(DOBJ dobj) throws Exception;
    public DOBJ ForecastTitleSet(DOBJ dobj) throws Exception;
    public DOBJ loadpage(DOBJ dobj) throws Exception;
}