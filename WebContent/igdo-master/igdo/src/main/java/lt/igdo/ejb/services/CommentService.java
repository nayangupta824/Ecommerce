/**
 * CommentServiceBean.java
 * Created: May 11, 2008 10:21:24 PM
 */
package lt.igdo.ejb.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import lt.igdo.domain.Comment;
import lt.igdo.domain.Item;
import lt.igdo.ejb.services.interfaces.ICommentService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Comment related services implementation.
 * 
 * @author Ignas
 * 
 */
@Service("commentService")
public class CommentService implements ICommentService {

    /** Count number of comment by item query constant. */
    private static final String COUNT_COMMENTS_BY_ITEM_QUERY = "Comment.count";

    /** Find comments by item query constant. */
    private static final String FIND_COMMENTS_BY_ITEM_QUERY = "Comment.findByItemId";

    /** Entity manager. */
	@PersistenceContext
    private EntityManager em;

    /**
     * @see lt.igdo.ejb.services.interfaces.ICommentService#getItemComments(lt.igdo.domain.Item,
     *      int, int)
     */
    @SuppressWarnings("unchecked")
    public List<Comment> getItemComments(Item item, int page,
            int commentsPerPage) {
        Query query = em
                .createNamedQuery(CommentService.FIND_COMMENTS_BY_ITEM_QUERY);
        query.setParameter("id", item.getId());
        query.setFirstResult((page - 1) * commentsPerPage);
        query.setMaxResults(commentsPerPage);
        return query.getResultList();
    }

    /**
     * @see lt.igdo.ejb.services.interfaces.ICommentService#getItemCommentsCount(lt.igdo.domain.Item)
     */
    public Long getItemCommentsCount(Item item) {
        Query query = em
                .createNamedQuery(CommentService.COUNT_COMMENTS_BY_ITEM_QUERY);
        query.setParameter("id", item.getId());
        return (Long) query.getSingleResult();
    }

    /**
     * @see lt.igdo.ejb.services.interfaces.ICommentService#saveComment(lt.igdo.domain.Comment)
     */
    @Transactional(readOnly = false)
    public void saveComment(Comment comment) {
        em.persist(comment);
    }
}
