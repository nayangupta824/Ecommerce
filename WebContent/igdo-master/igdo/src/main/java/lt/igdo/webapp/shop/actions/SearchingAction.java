/**
 * SearchingAction.java
 * Created: Jan 22, 2008 8:43:08 PM
 */
package lt.igdo.webapp.shop.actions;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import lt.igdo.commons.paging.Pager;
import lt.igdo.commons.paging.StandartPager;
import lt.igdo.domain.Category;
import lt.igdo.domain.Item;
import lt.igdo.ejb.services.interfaces.ISearchService;

/**
 * Search component.
 * 
 * @author Ignas
 */
@ManagedBean(name = "searchingAction")
@ViewScoped
public class SearchingAction {

    /** Constant for number of comments per page. */
    private static final int SEARCH_RESULTS_PER_PAGE = 5;

    /** Search service remote ejb. */
    @ManagedProperty(value="#{searchService}")
    private ISearchService searchService;

    /**
     * Page request parameter. Used to identify which is current page of search
     * results.
     */
    private Integer page;

    /** Text entered in search box by user. */
    private String searchPattern;

    /**
     * Category selected in combobox. Search is limited to this category and
     * it's children categories. It can be null, to represent all catgeories.
     */
    @SuppressWarnings("unused")
    private Category searchCategory;

    /** Number of total results found. */
    private Integer totalResults;


    public void setSearchService(ISearchService searchService) {
		this.searchService = searchService;
	}

	/**
     * Factory method for searching items by search pattern.
     * 
     * @return Items found on current page by search pattern.
     */
    public List<Item> findItems() {
        return searchService.search(searchPattern,
                (getPager().getCurrentPage() - 1),
                SearchingAction.SEARCH_RESULTS_PER_PAGE);
    }

    /**
     * Gets number of total results found by search pattern.
     * 
     * @return Total results of search query.
     */
    public int getTotalResults() {
        if (totalResults == null) {
            totalResults = searchService.totalResults(searchPattern);
        }
        return totalResults;
    }

    /**
     * Search pattern getter.
     * 
     * @return Search pattern.
     */
    public String getSearchPattern() {
        return searchPattern;
    }

    /**
     * Search pattern setter.
     * 
     * @param searchPattern
     *            Search pattern to set.
     */
    public void setSearchPattern(String searchPattern) {
        this.searchPattern = searchPattern;
    }

    /**
     * Getter of {@link Pager}.
     * 
     * @return {@link Pager} for paging search results.
     */
    public Pager getPager() {
        return new StandartPager(page != null ? page : 1,
                SearchingAction.SEARCH_RESULTS_PER_PAGE, getTotalResults());
    }

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}
    
}
