package egov.wizware.jsp;
import javax.servlet.http.HttpServletRequest;

public interface UiAdaptor
{
	public Object convert(HttpServletRequest request) throws Exception;
        public Object convertDev(HttpServletRequest request) throws Exception;
	public Class getModelName();
}

