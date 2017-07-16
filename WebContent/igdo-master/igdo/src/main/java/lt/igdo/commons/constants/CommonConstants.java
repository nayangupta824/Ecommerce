/**
 * CommonConstants.java
 * Created: Oct 22, 2007 8:44:13 PM
 */
package lt.igdo.commons.constants;

/**
 * Commonly used constants.
 * 
 * @author Donatas
 * 
 */
public final class CommonConstants {

    /**
     * No need to instantiate this class.
     */
    private CommonConstants() {

    }

    /** Name of the cookie storing user id. */
    public static final String COOKIE_PARAM_USER_ID = "uid";

    /** Max age of cookie. */
    public static final int COOKIE_MAX_AGE = 60 * 60 * 24 * 365 * 10; // Ten
                                                                        // years
    
    /**
     * Identify that logout is performed
     */
    public static final String REQUEST_PARAM_LOGOUT = "lt.igdo.commons.constants.CommonConstants.REQUEST_PARAM_LOGOUT";

}
