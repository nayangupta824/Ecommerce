/**
 * RegistrationServiceRemote.java
 * Created: May 14, 2008 8:44:56 PM
 */
package lt.igdo.ejb.services.interfaces;

import java.rmi.RemoteException;

/**
 * User registration service interface.
 * 
 * @author Donatas
 * 
 */
public interface IRegistrationService {

    /**
     * Confirm user registration.
     * 
     * @param code
     * @throws RemoteException
     */
    void confirm(String code) throws RemoteException;
}
