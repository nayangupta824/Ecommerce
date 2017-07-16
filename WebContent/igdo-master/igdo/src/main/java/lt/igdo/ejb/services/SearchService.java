/**
 * SearchServiceBean.java
 * Created: May 14, 2008 9:20:41 PM
 */
package lt.igdo.ejb.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import lt.igdo.domain.Attribute;
import lt.igdo.domain.Category;
import lt.igdo.domain.Item;
import lt.igdo.domain.Shop;
import lt.igdo.ejb.services.interfaces.ISearchService;

import org.compass.annotations.config.CompassAnnotationsConfiguration;
import org.compass.core.Compass;
import org.compass.core.CompassHit;
import org.compass.core.config.CompassConfiguration;
import org.compass.core.support.search.CompassSearchCommand;
import org.compass.core.support.search.CompassSearchHelper;
import org.compass.core.support.search.CompassSearchResults;
import org.springframework.stereotype.Service;

/**
 * Search related services implementation.
 * 
 * @author Donatas
 * 
 */
@Service("searchService")
public class SearchService implements ISearchService {

    /** Entity manager factory. */
    @PersistenceUnit
    private EntityManagerFactory emf;

    /**
     * @see lt.igdo.ejb.services.interfaces.ISearchService#search(java.lang.String,
     *      int, int)
     */
    public List<Item> search(String pattern, int page, int resultsPerPage) {
    	// quickfix after migration to not to reconfigure compass
    	CompassConfiguration conf = new CompassAnnotationsConfiguration();
    	conf.setConnection("C:\\temp");
    	conf.addClass(Item.class);
    	conf.addClass(Category.class);
    	conf.addClass(Shop.class);
    	conf.addClass(Attribute.class);
        Compass compass = conf.buildCompass();

        CompassSearchHelper searchHelper = new CompassSearchHelper(compass,
                resultsPerPage);
        CompassSearchResults results = searchHelper
                .search(new CompassSearchCommand(pattern, page));

        List<Item> itemsFound = new ArrayList<Item>();
        for (CompassHit hit : results.getHits()) {
            itemsFound.add((Item) hit.getData());
        }

        return itemsFound;
    }

    /**
     * @see lt.igdo.ejb.services.interfaces.ISearchService#totalResults(java.lang.String)
     */
    public int totalResults(String pattern) {
    	// quickfix after migration to not to reconfigure compass
        CompassConfiguration conf = new CompassAnnotationsConfiguration();
    	conf.setConnection("C:\\temp");
    	conf.addClass(Item.class);
    	conf.addClass(Category.class);
    	conf.addClass(Shop.class);
    	conf.addClass(Attribute.class);
        Compass compass = conf.buildCompass();

        CompassSearchHelper searchHelper = new CompassSearchHelper(compass);
        CompassSearchResults results = searchHelper
                .search(new CompassSearchCommand(pattern));
        return results.getTotalHits();
    }
}
