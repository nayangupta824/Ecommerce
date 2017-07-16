/**
 * Pager.java
 * Created: Apr 15, 2008 6:23:48 PM
 */
package lt.igdo.commons.paging;

/**
 * Pager helper class used in igdo:pager or igdo:commandPager composite jsf
 * components. It wraps information about current page, first page, last page,
 * total number of entities, entities per page etc.
 * 
 * @author Ignas
 * 
 */
public interface Pager {

    /**
     * Finds out if the are less pages.
     * 
     * @return true if current page is not the first one and false otherwise.
     */
    public boolean hasLess();

    /**
     * Finds out if the are more pages.
     * 
     * @return true if current page is not the last one and false otherwise.
     */
    public boolean hasMore();

    /**
     * Gets current page.
     * 
     * @return Current page.
     */
    public int getCurrentPage();

}
