/**
 * CommentServiceRemote.java
 * Created: May 11, 2008 10:21:24 PM
 */
package lt.igdo.ejb.services.interfaces;

import java.util.List;

import lt.igdo.domain.Comment;
import lt.igdo.domain.Item;

/**
 * Interface for Comment related services.
 * 
 * @author Ignas
 * 
 */
public interface ICommentService {

    /**
     * Saves a new comment.
     * 
     * @param comment
     */
    void saveComment(Comment comment);

    /**
     * Gets gets pageable item comments.
     * 
     * @param item
     *            Item whose comments are loaded.
     * @param page
     *            Page number of comments.
     * @param commentsPerPage
     *            Number of comments per page.
     * @return List of comments.
     */
    List<Comment> getItemComments(Item item, int page, int commentsPerPage);

    /**
     * Gets total number of comments item has.
     * 
     * @param item
     *            Item whose comments are counted.
     * @return Number of comments.
     */
    Long getItemCommentsCount(Item item);

}
