/**
 * SearchIndexerServiceRemote.java
 * Created: Feb 20, 2008 10:14:50 PM
 */
package lt.igdo.ejb.services.interfaces;

/**
 * Search indexer service interface.
 * 
 * @author Ignas
 * 
 */
public interface ISearchIndexerService {

    /**
     * Index all searchable domain entities to file system.
     */
    void indexEntities();

}
