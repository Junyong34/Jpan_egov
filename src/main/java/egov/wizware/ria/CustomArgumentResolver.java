package egov.wizware.ria;

import javax.servlet.http.HttpServletRequest;
import org.springframework.asm.Attribute;
import org.springframework.core.MethodParameter;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;

public class CustomArgumentResolver  implements WebArgumentResolver
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

        System.out.println("CustomRiaArgumentResolver : getModelName ==>" + uiA.getModelName());
        System.out.println("CustomRiaArgumentResolver : type ==>" + type);
        //System.out.println("getMethod =============>" + methodParameter.getMethod().toString());
        if (uiA == null)
            return UNRESOLVED;

        if (type.equals(uiA.getModelName()))
        {
            HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
            System.out.println("####################### CustomRiaArgumentResolver ==========> 1");
            // uiObject = (UdDTO) uiA.convert(request);

            uiObject = (Object) uiA.convert(request);
            System.out.println("####################### CustomRiaArgumentResolver ==========> 2");
            return uiObject;
        }
        return UNRESOLVED;
    }

}
