package egov.wizware.jsp;

import javax.servlet.http.*;
import org.springframework.asm.Attribute;
import org.springframework.core.MethodParameter;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;

public class DevCustomArgumentResolver implements WebArgumentResolver
{
    private UiAdaptor uiA;
    public void setUiAdaptor(UiAdaptor uiA)
    {
        this.uiA = uiA;
    }

    public Object resolveArgument(MethodParameter methodParameter, NativeWebRequest webRequest) throws Exception
    {
        Class<?> type = methodParameter.getParameterType();
        Object uiObject = null;
       // System.out.println("DevCustomArgumentResolver : getModelName ==>" + uiA.getModelName()  );

        if (uiA == null)
            return UNRESOLVED;

        if (type.equals(uiA.getModelName()))
        {
            HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
          uiObject = (Object) uiA.convertDev(request);
          //  uiObject = (Object) uiA.convert(request);
            return uiObject;
        }
        return UNRESOLVED;
    }
}
