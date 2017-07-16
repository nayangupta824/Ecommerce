/**
 * ItemAction.java
 * Created: 8:55:17 PM Jul 30, 2008
 */
package lt.igdo.webapp.shop.actions;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import lt.igdo.domain.Comment;
import lt.igdo.domain.Item;
import lt.igdo.domain.User;
import lt.igdo.ejb.services.interfaces.ICommentService;
import lt.igdo.ejb.services.interfaces.IItemService;
import lt.igdo.webapp.shop.beans.UserBean;

/**
 * Component for showing item details related information.
 * 
 * @author Ignas
 * 
 */
@ManagedBean(name = "itemAction")
@ViewScoped
public class ItemAction {

    /** Constant for number of comments to show in item details. */
    private static final int COMMENTS_NUMBER = 3;

    /** Item service remote ejb. */
    @ManagedProperty(value="#{itemService}")
    private IItemService itemService;

    /** Comment service remote ejb. */
    @ManagedProperty(value="#{commentService}")
    private ICommentService commentService;
    
    /**
     * Injected user bean used for getting current logged in user. Needed when
     * user post comment about item.
     */
    @ManagedProperty(value="#{userBean}")
    private UserBean userBean;

    /** Item id request parameter. Used to load selected item. */
    private Long itemId;

    /** Item to show it's details for the user. */
    private Item item;
    
    private Comment comment;

    public void setItemService(IItemService itemService) {
		this.itemService = itemService;
	}

	public void setCommentService(ICommentService commentService) {
		this.commentService = commentService;
	}
	
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	/**
     * Is invoked on creation of this component.
     */
    public void init() {
        item = itemService.getItemById(itemId);
        comment = new Comment();
    }

    /**
     * Getter of item details page comments.
     * 
     * @return Comments to show in item details page.
     */
    public List<Comment> getComments() {
        return commentService.getItemComments(item, 1, ItemAction.COMMENTS_NUMBER);
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
    
    public Comment getComment() {
    	return comment;
    }

    /**
     * Getter of item.
     * 
     * @return Item to show.
     */
    public Item getItem() {
        return item;
    }

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	
	public User getCurrentLoggedInUser() {
        User currentUser = userBean.getUser();
        return currentUser;
    }
	
	public void wasCommentValuable(Boolean wasValuable) {
        // TODO Auto-generated method stub
    }
    
}
