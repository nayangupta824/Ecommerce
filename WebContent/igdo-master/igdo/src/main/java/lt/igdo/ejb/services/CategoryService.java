/**
 * CategoryServiceBean.java
 * Created: May 24, 2008 11:41:32 PM
 */
package lt.igdo.ejb.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import lt.igdo.domain.Category;
import lt.igdo.ejb.services.interfaces.ICategoryService;

import org.springframework.stereotype.Service;

/**
 * Category related services implementation.
 * 
 * @author Ignas
 * 
 */
@Service("categoryService")
public class CategoryService implements ICategoryService {

	/** Entity manager. */
	@PersistenceContext
    private EntityManager em;

    /**
     * @see lt.igdo.ejb.services.interfaces.ICategoryService#getCategories()
     */
    @SuppressWarnings("unchecked")
    public List<Category> getCategories() {
        Query query = em
                .createQuery("select c from Category c left join fetch c.parentCategory");
        return query.getResultList();
    }

}
