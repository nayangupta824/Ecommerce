/**
 * FullTextSearchIndexer.java
 * Created: Feb 20, 2008 7:09:13 PM
 */
package lt.igdo.ejb.search;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import lt.igdo.domain.Attribute;
import lt.igdo.domain.Category;
import lt.igdo.domain.Item;
import lt.igdo.domain.Shop;
import lt.igdo.ejb.services.interfaces.ISearchIndexerService;

import org.compass.annotations.config.CompassAnnotationsConfiguration;
import org.compass.core.Compass;
import org.compass.core.config.CompassConfiguration;
import org.compass.gps.CompassGps;
import org.compass.gps.CompassGpsDevice;
import org.compass.gps.device.jpa.JpaGpsDevice;
import org.compass.gps.impl.SingleCompassGps;
import org.springframework.stereotype.Service;

/**
 * Full text search indexer service.
 * 
 * @author Ignas
 * 
 */
@Service("searchIndexerService")
public class SearchIndexerService implements ISearchIndexerService {

    /** Entity manager factory */
    @PersistenceUnit
    private EntityManagerFactory emf;

    /**
     * This method indexes data, so we could search it later.
     */
    @PostConstruct
    public void indexEntities() {
    	CompassConfiguration conf = new CompassAnnotationsConfiguration();
    	conf.setConnection("C:\\temp");
    	conf.addClass(Item.class);
    	conf.addClass(Category.class);
    	conf.addClass(Shop.class);
    	conf.addClass(Attribute.class);
        Compass compass = conf.buildCompass();
		CompassGps gps = new SingleCompassGps(compass);
		CompassGpsDevice jpaDevice = new JpaGpsDevice("jpa", emf);
		gps.addGpsDevice(jpaDevice);
		gps.start();
		gps.index();
    }
}
