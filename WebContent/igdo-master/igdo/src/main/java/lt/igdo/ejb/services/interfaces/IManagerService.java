/**
 * ManagerServiceRemote.java
 * Created: 11:02:23 PM Jul 20, 2008
 */
package lt.igdo.ejb.services.interfaces;

/**
 * Interface for Manager related services.
 * 
 * @author Ignas
 * 
 */
public interface IManagerService {

    /**
     * Authenticate manager.
     * 
     * @return true if manager is authenticated.
     */
    boolean authenticateManager(String userName, String password);

}
