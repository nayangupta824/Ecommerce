/**
 * CommentAction.java
 * Created: 8:55:17 PM Jul 30, 2008
 */
package lt.igdo.webapp.shop.actions;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import lt.igdo.commons.paging.Pager;
import lt.igdo.commons.paging.StandartPager;
import lt.igdo.domain.Comment;
import lt.igdo.domain.Item;
import lt.igdo.domain.User;
import lt.igdo.ejb.services.interfaces.ICommentService;
import lt.igdo.ejb.services.interfaces.IItemService;
import lt.igdo.webapp.shop.beans.UserBean;

/**
 * Component for browsing loaded comments, adding new comments, voting for
 * valuable comments and other comments related stuff.
 * 
 * @author Ignas
 * 
 */
@ManagedBean(name = "commentAction")
@ViewScoped
public class CommentAction {

    /** Constant for number of comments per page. */
    private static final int COMMENTS_NUMBER_PER_PAGE = 5;

    /** Comment service remote ejb. */
    @ManagedProperty(value="#{commentService}")
    private ICommentService commentService;

    /** Item service remote ejb. */
    @ManagedProperty(value="#{itemService}")
    private IItemService itemService;

    /**
     * Injected user bean used for getting current logged in user. Needed when
     * user post comment about item.
     */
    @ManagedProperty(value="#{userBean}")
    private UserBean userBean;

    /**
     * Page request parameter. Used in all comment tab to identify which is
     * current page of comments.
     */
    private Integer page;

    /** Item id request parameter. Used to load selected item. */
    private Long itemId;

    /** New comment about item. */
    private Comment comment;

    /** Loaded selected item. */
    private Item item;

    public void setCommentService(ICommentService commentService) {
		this.commentService = commentService;
	}

	public void setItemService(IItemService itemService) {
		this.itemService = itemService;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	/**
     * Is invoked on creation of this component.
     */
    public void init() {
        item = itemService.getItemById(itemId);
        System.out.println("AAAAAAAAAAA " + item.getId());
        comment = new Comment();
    }

    /**
     * Loads comments for current item. It uses variables item and page to
     * decide which item and which page of comments to show. This is seam
     * factory method.
     */
    public List<Comment> getComments() {
        return commentService.getItemComments(item, page,
                CommentAction.COMMENTS_NUMBER_PER_PAGE);
    }

    /**
     * Gets comment for filling its values and saving new comment to database.
     * 
     * @return New comment
     */
    public Comment getComment() {
        return comment;
    }

    /**
     * Adding new comment to database.
     */
    public void addComment() {
        comment.setItem(item);
        comment.setUser(getCurrentLoggedInUser());
        comment.setCommentedOn(new Date());
        commentService.saveComment(comment);
        comment = new Comment();
    }

    /**
     * Getter of {@link Pager}.
     * 
     * @return {@link Pager} for paging comments in all comments subtab.
     */
    public Pager getCommentsPager() {
        return new StandartPager(page != null ? page : 1,
                CommentAction.COMMENTS_NUMBER_PER_PAGE, commentService
                        .getItemCommentsCount(item).intValue());
    }

    // TODO do it.
    /**
     * Vote if comment was valuable.
     * 
     * @param wasValuable
     */
    public void wasCommentValuable(Boolean wasValuable) {
        // TODO Auto-generated method stub
    }

    /**
     * Logged user getter.
     * 
     * @return Current logged in user.
     */
    public User getCurrentLoggedInUser() {
        User currentUser = userBean.getUser();
        return currentUser;
    }

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

}
