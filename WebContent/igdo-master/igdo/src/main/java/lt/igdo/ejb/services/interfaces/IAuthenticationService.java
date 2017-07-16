/**
 * AuthenticationServiceRemote.java
 * Created: May 14, 2008 8:31:04 PM
 */
package lt.igdo.ejb.services.interfaces;

/**
 * Interface for Authentication related services.
 * 
 * @author Donatas
 * 
 */
public interface IAuthenticationService {

    /**
     * Authenticate user.
     * 
     * @return true if user is authenticated.
     */
    boolean authenticate(String userName, String password);

}
