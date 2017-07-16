/**
 * SearchServiceRemote.java
 * Created: May 14, 2008 9:17:59 PM
 */
package lt.igdo.ejb.services.interfaces;

import java.util.List;

import lt.igdo.domain.Item;

/**
 * Search service interface.
 * 
 * @author Donatas
 * 
 */
public interface ISearchService {

    /**
     * Finds items by provided search pattern. Search results are pageable.
     * 
     * @param pattern
     *            Search pattern.
     * @param page
     *            Results page number.
     * @param resultsPerPage
     *            Results per page to show.
     * @return List of found items.
     */
    List<Item> search(String pattern, int page, int resultsPerPage);

    /**
     * Counts total results that is found with given search pattern.
     * 
     * @param pattern
     *            Search pattern.
     * @return Number of results.
     */
    int totalResults(String pattern);
}
