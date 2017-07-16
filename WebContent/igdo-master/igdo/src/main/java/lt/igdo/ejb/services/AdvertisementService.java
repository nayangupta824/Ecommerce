/**
 * AdvertismentServiceBean.java
 * Created: May 14, 2008 8:31:04 PM
 */
package lt.igdo.ejb.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import lt.igdo.domain.Advertisment;
import lt.igdo.domain.Category;
import lt.igdo.ejb.services.interfaces.IAdvertisementService;

import org.springframework.stereotype.Service;

/**
 * Advertisement related services implementation.
 * 
 * @author Donatas
 * 
 */
@Service("advertismentService")
public class AdvertisementService implements IAdvertisementService {

    /** Entity manager. */
	@PersistenceContext
    private EntityManager em;

    /**
     * @see lt.igdo.ejb.services.interfaces.IAdvertisementService#getAdvertisments(lt.igdo.domain.Category)
     */
    @SuppressWarnings("unchecked")
    public List<Advertisment> getAdvertisments(Category selectedCategory) {
        return em.createQuery("select a from Advertisment a").getResultList();
    }

}
