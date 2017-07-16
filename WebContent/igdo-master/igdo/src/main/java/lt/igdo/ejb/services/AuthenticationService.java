/**
 * AuthenticationServiceBean.java
 * Created: May 14, 2008 8:31:04 PM
 */
package lt.igdo.ejb.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import lt.igdo.commons.utils.PersistenceUtils;
import lt.igdo.domain.User;
import lt.igdo.ejb.services.interfaces.IAuthenticationService;

import org.springframework.stereotype.Service;

/**
 * Authentication related services implementation.
 * 
 * @author Donatas
 * 
 */
@Service("authenticationService")
public class AuthenticationService implements IAuthenticationService {

	/** Entity manager. */
	@PersistenceContext
    private EntityManager em;

    /**
     * @see lt.igdo.ejb.services.interfaces.IAuthenticationService#authenticate(java.lang.String, java.lang.String)
     */
    public boolean authenticate(String userLogin, String password) {
        Query loginQuery = em.createNamedQuery("User.login");
        loginQuery.setParameter("email", userLogin);
        User user = (User) PersistenceUtils.getSingleResult(loginQuery
                .getResultList());

        if (user == null) {

            return false;
        } else {
            if (user.getPassword().equals(password)) {
                // TODO add success message
                return true;
            } else {
                // TODO add message bad password
                return false;
            }
        }
    }

}
