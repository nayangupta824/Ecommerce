/**
 * ServletUtils.java
 * Created: Oct 23, 2007 10:09:01 PM
 */
package lt.igdo.commons.utils;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet utilities.
 * 
 * @author Donatas
 * 
 */
public final class ServletUtils {

    /**
     * No need to instantiate utils.
     */
    private ServletUtils() {

    }

    /**
     * Get servlet request from Faces context.
     * 
     * @return HttpServletRequest object.
     */
    public static HttpServletRequest getRequest() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) facesContext
                .getExternalContext().getRequest();
        return request;
    }

    /**
     * Get servlet response from Faces context.
     * 
     * @return HttpServletResponse object.
     */
    public static HttpServletResponse getResponse() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) facesContext
                .getExternalContext().getResponse();
        return response;
    }

}
