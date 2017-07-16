/**
 * UserServiceRemote.java
 * Created: 5:51:30 PM Jul 21, 2008
 */
package lt.igdo.ejb.services.interfaces;

import lt.igdo.domain.Address;
import lt.igdo.domain.User;
import lt.igdo.domain.UserConfirmation;

/**
 * Interface for User related services.
 * 
 * @author Ignas
 * 
 */
public interface IUserService {

    /**
     * Saves new user.
     * 
     * @param user
     *            User to save.
     */
    User saveUser(User user);

    /**
     * Updates user information.
     * 
     * @param user
     *            User to update.
     */
    User updateUser(User user);

    /**
     * Finds user by unique id.
     * 
     * @param uniqueId
     *            Unique id.
     * @return User found.
     */
    User findUserByUniqueId(String uniqueId);

    /**
     * Finds user by email.
     * 
     * @param email
     *            User email.
     * @return User found.
     */
    User findUserByEmail(String email);

    /**
     * Saves user registration confirmation.
     * 
     * @param confirmation
     *            Confirmation to save.
     */
    void saveUserConfirmation(UserConfirmation confirmation);

    /**
     * Finds user registratio confirmation by user.
     * 
     * @param user
     *            User.
     * @return User registration confirmation.
     */
    UserConfirmation findConfirmationByUser(User user);

    /**
     * Saves new user address.
     * 
     * @param address
     *            Address to save.
     */
    void saveUserAddress(User user, Address address);

}
