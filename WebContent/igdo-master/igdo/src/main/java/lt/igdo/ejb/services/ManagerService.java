/**
 * ManagerServiceBean.java
 * Created: 11:08:18 PM Jul 20, 2008
 */
package lt.igdo.ejb.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import lt.igdo.commons.utils.PersistenceUtils;
import lt.igdo.domain.Manager;
import lt.igdo.ejb.services.interfaces.IManagerService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Manager related services implementation.
 * 
 * @author Ignas
 * 
 */
@Service("managerService")
public class ManagerService implements IManagerService {

	/** Entity manager. */
	@PersistenceContext
    private EntityManager em;

    /**
     * @see lt.igdo.ejb.services.interfaces.IManagerService#authenticateManager(java.lang.String,
     *      java.lang.String)
     */
	@Transactional(readOnly = false)
    public boolean authenticateManager(String managerLogin, String password) {

        Query query = em.createNamedQuery("Manager.findByLogin");
        query.setParameter("login", managerLogin);

        Manager manager = (Manager) PersistenceUtils.getSingleResult(query
                .getResultList());

        if (manager == null) {
            // TODO add message such user does not exists
            return false;
        } else {
            if (manager.getPassword().equals(password)) {
                // TODO add success message
                // TODO remove getName()
                // shopManager.getShop().getName();
                // shopSession.setCurrentShop(shopManager.getShop());
                return true;
            } else {
                // TODO add message bad password
                return false;
            }
        }
    }

}
