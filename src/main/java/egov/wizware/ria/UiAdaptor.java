package egov.wizware.ria;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UiAdaptor
{
	public Object convert(HttpServletRequest request  ) throws Exception;
        public Object convertDev(HttpServletRequest request  ) throws Exception;
      
	public Class getModelName();
}

