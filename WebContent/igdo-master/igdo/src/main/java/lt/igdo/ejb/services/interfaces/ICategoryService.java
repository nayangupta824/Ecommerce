/**
 * CategoryServiceRemote.java
 * Created: May 24, 2008 11:41:32 PM
 */
package lt.igdo.ejb.services.interfaces;

import java.util.List;

import lt.igdo.domain.Category;

/**
 * Interface for Category related services.
 * 
 * @author Ignas
 * 
 */
public interface ICategoryService {

	/**
	 * Gets all categories in the system from database.
	 * 
	 * @return List of categories.
	 */
	List<Category> getCategories();

}
