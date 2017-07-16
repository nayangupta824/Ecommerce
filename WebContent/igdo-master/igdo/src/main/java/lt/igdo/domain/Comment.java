/**
 * ItemComment.java
 * Created: Jan 27, 2008 5:49:43 PM
 */
package lt.igdo.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lt.igdo.domain.base.BaseEntity;

/**
 * Comment domain object.
 * 
 * @author Ignas
 * 
 */
@Entity
@Table(name = "COMMENTS")
@NamedQueries( {
        @NamedQuery(name = "Comment.findByItemId", query = "select c from Comment c left join fetch c.item left join fetch c.user where c.item.id = :id order by c.commentedOn desc"),
        @NamedQuery(name = "Comment.count", query = "select count(c) from Comment c where c.item.id = :id") })
public class Comment extends BaseEntity {

    /** Generated UID for serializable classes. */
    private static final long serialVersionUID = -6683415971704955900L;

    /** Commented item. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID", nullable = false)
    private Item item;

    /** Author of comment. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    /** Caption of user's comment */
    @Column(name = "CAPTION", nullable = false)
    private String caption;

    /** User's comment about item */
    @Column(name = "COMMENT", nullable = false)
    private String comment;

    /** Rating user gave to item. */
    @Column(name = "RATING", nullable = false)
    private int rating;

    /** How much users found this comment valuable. */
    @Column(name = "VALUABLE_COUNT", nullable = false)
    private int valuableCount;

    /** How much users found this comment not valuable. */
    @Column(name = "NOT_VALUABLE_COUNT", nullable = false)
    private int notValuableCount;

    /** Date when comment was added. */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "COMMENTED_ON", nullable = false)
    private Date commentedOn;

    /**
     * Caption getter.
     * 
     * @return the caption
     */
    public String getCaption() {
        return caption;
    }

    /**
     * Caption getter.
     * 
     * @param caption
     *            the caption to set
     */
    public void setCaption(String caption) {
        this.caption = caption;
    }

    /**
     * Item getter.
     * 
     * @return Item.
     */
    public Item getItem() {
        return item;
    }

    /**
     * Item setter.
     * 
     * @param item
     *            Item to set.
     */
    public void setItem(Item item) {
        this.item = item;
    }

    /**
     * User getter.
     * 
     * @return User.
     */
    public User getUser() {
        return user;
    }

    /**
     * User setter.
     * 
     * @param user
     *            User to set.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Comment getter.
     * 
     * @return Comment.
     */
    public String getComment() {
        return comment;
    }

    /**
     * Comment setter.
     * 
     * @param comment
     *            Comment to set.
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Comment date getter.
     * 
     * @return Comment date.
     */
    public Date getCommentedOn() {
        return commentedOn;
    }

    /**
     * Comment date setter.
     * 
     * @param commentedOn
     *            Comment date to set.
     */
    public void setCommentedOn(Date commentedOn) {
        this.commentedOn = commentedOn;
    }

    /**
     * Rating getter.
     * 
     * @return Rating.
     */
    public int getRating() {
        return rating;
    }

    /**
     * Rating setter.
     * 
     * @param rating
     *            Rating to set.
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * ValuableCount getter.
     * 
     * @return ValuableCount.
     */
    public long getValuableCount() {
        return valuableCount;
    }

    /**
     * ValuableCount setter.
     * 
     * @param valuableCount
     *            ValuableCount to set.
     */
    public void setValuableCount(int valuableCount) {
        this.valuableCount = valuableCount;
    }

    /**
     * NotValuableCount getter.
     * 
     * @return NotValuableCount.
     */
    public long getNotValuableCount() {
        return notValuableCount;
    }

    /**
     * NotValuableCount setter.
     * 
     * @param notValuableCount
     *            NotValuableCount to set.
     */
    public void setNotValuableCount(int notValuableCount) {
        this.notValuableCount = notValuableCount;
    }

}
