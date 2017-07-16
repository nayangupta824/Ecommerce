/**
 * CommpentsPager.java
 * Created: Apr 15, 2008 6:25:03 PM
 */
package lt.igdo.commons.paging;

/**
 * @author Ignas
 * 
 */
public class StandartPager implements Pager {

    /** Total number of entities. */
    private int count;

    /** Current page. */
    private int currentPage;

    /** Number of entities to show in one page. */
    private int pageSize;

    /**
     * Standart pager constructor.
     * 
     * @param currentPage
     *            Current page.
     * @param pageSize
     *            Entities per page to show.
     * @param count
     *            Total number of entities.
     */
    public StandartPager(int currentPage, int pageSize, int count) {
        super();
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.count = count;
    }

    /**
     * @see lt.igdo.commons.paging.Pager#getCurrentPage()
     */
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * @see lt.igdo.commons.paging.Pager#hasLess()
     */
    public boolean hasLess() {
        return currentPage <= 1 ? false : true;
    }

    /**
     * @see lt.igdo.commons.paging.Pager#hasMore()
     */
    public boolean hasMore() {
        return pageSize * currentPage >= count ? false : true;
    }

}
