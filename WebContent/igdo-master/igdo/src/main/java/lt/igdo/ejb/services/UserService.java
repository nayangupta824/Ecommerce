/**
 * UserServiceBean.java
 * Created: 5:51:30 PM Jul 21, 2008
 */
package lt.igdo.ejb.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import lt.igdo.commons.utils.PersistenceUtils;
import lt.igdo.domain.Address;
import lt.igdo.domain.User;
import lt.igdo.domain.UserConfirmation;
import lt.igdo.ejb.services.interfaces.IUserService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * User related services implementation.
 * 
 * @author Ignas
 * 
 */
@Service("userService")
public class UserService implements IUserService {

    /** Find confirmation query constant. */
    private static final String FIND_CONFIRMATION_BY_USER_QUERY = "UserConfirmation.findByUser";

    /** Find user by email query constant. */
    private static final String FIND_USER_BY_EMAIL_QUERY = "User.findByEmail";

    /** Find user by unique id query constant. */
    private static final String FIND_USER_BY_UNIQUE_ID = "User.findByUniqueId";

    /** Entity manager. */
	@PersistenceContext
    private EntityManager em;

    /**
     * @see lt.igdo.ejb.services.interfaces.IUserService#findUserByUniqueId(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public User findUserByUniqueId(String uniqueId) {
        Query query = em
                .createNamedQuery(UserService.FIND_USER_BY_UNIQUE_ID);
        query.setParameter("id", uniqueId);
        return getUniqueUserFromResults(query.getResultList());
    }

    /**
     * @see lt.igdo.ejb.services.interfaces.IUserService#findUserByEmail(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public User findUserByEmail(String email) {
        Query query = em
                .createNamedQuery(UserService.FIND_USER_BY_EMAIL_QUERY);
        query.setParameter("email", email);
        return getUniqueUserFromResults(query.getResultList());
    }

    /**
     * @see lt.igdo.ejb.services.interfaces.IUserService#saveUser(lt.igdo.domain.User)
     */
    @Transactional(readOnly = false)
    public User saveUser(User user) {
        em.persist(user);
        return user;
    }

    /**
     * @see lt.igdo.ejb.services.interfaces.IUserService#updateUser(lt.igdo.domain.User)
     */
    @Transactional(readOnly = false)
    public User updateUser(User user) {
        return em.merge(user);
    }

    /**
     * @see lt.igdo.ejb.services.interfaces.IUserService#saveUserConfirmation(lt.igdo.domain.UserConfirmation)
     */
    @Transactional(readOnly = false)
    public void saveUserConfirmation(UserConfirmation confirmation) {
        em.persist(confirmation);
    }

    /**
     * @see lt.igdo.ejb.services.interfaces.IUserService#findConfirmationByUser(lt.igdo.domain.User)
     */
    public UserConfirmation findConfirmationByUser(User user) {
        Query query = em
                .createNamedQuery(UserService.FIND_CONFIRMATION_BY_USER_QUERY);
        query.setParameter("user", user);
        return (UserConfirmation) query.getSingleResult();
    }

    /**
     * @see lt.igdo.ejb.services.interfaces.IUserService#saveUserAddress(lt.igdo.domain.User, lt.igdo.domain.Address)
     */
    @Transactional(readOnly = false)
    public void saveUserAddress(User user, Address address) {
        em.persist(address);
        em.flush();
        user.getUserAddresses().add(address);
        updateUser(user);
    }

    /**
     * Because user has list of addresses to fetch we should use HashSet to get
     * only unique users (because if user have 3 addresses JPA will return 3
     * same users).
     * 
     * @param queryResults
     *            Reuslts of users query with fetched list of addresses.
     * @return Unique user.
     */
    private User getUniqueUserFromResults(List<User> queryResults) {
        Set<User> uniqueUsers = new HashSet<User>();
        uniqueUsers.addAll(queryResults);
        return (User) PersistenceUtils.getSingleResult(new ArrayList<User>(
                uniqueUsers));
    }

}
