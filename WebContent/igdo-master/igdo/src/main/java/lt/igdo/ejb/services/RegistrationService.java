/**
 * RegistrationServiceBean.java
 * Created: May 14, 2008 8:48:11 PM
 */
package lt.igdo.ejb.services;

import java.rmi.RemoteException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import lt.igdo.commons.utils.PersistenceUtils;
import lt.igdo.domain.User;
import lt.igdo.domain.UserConfirmation;
import lt.igdo.ejb.services.interfaces.IRegistrationService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Registration related services implementation.
 * 
 * @author Donatas
 * 
 */
@Service("registrationService")
public class RegistrationService implements IRegistrationService {

	/** Entity manager. */
	@PersistenceContext
    private EntityManager em;

    /**
     * @see lt.igdo.ejb.services.interfaces.IRegistrationService#confirm(java.lang.String)
     */
	@Transactional(readOnly = false)
    public void confirm(String code) throws RemoteException {
        Query query = em.createNamedQuery("UserConfirmation.findByCode");
        query.setParameter("code", code);
        UserConfirmation userConfirmation = (UserConfirmation) PersistenceUtils
                .getSingleResult(query.getResultList());
        if (userConfirmation != null) {
            User user = userConfirmation.getUser();
            em.remove(userConfirmation);
            user.setRegistered(true);
        } else {
            // TODO: Refine exceptions
            throw new RemoteException();
        }

    }

}
