/**
 * IAdvertismentService.java
 * Created: May 14, 2008 8:31:04 PM
 */
package lt.igdo.ejb.services.interfaces;

import java.util.List;

import lt.igdo.domain.Advertisment;
import lt.igdo.domain.Category;

/**
 * Interface for Advertisments related services.
 * 
 * @author Donatas
 * 
 */
public interface IAdvertisementService {

    /**
     * Gets list of advertisments. Takes selected category into account.
     * 
     * @return Collection of categories.
     */
    List<Advertisment> getAdvertisments(Category selectedCategory);

}
