/**
 * UserConfirmation.java
 * Created: Nov 15, 2007 9:08:26 PM
 */
package lt.igdo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lt.igdo.domain.base.BaseEntity;

/**
 * User confirmation domain object.
 * 
 * @author Donatas
 * 
 */
@Entity
@Table(name = "USER_CONFIRMATIONS")
@NamedQueries( {
        @NamedQuery(name = "UserConfirmation.findByCode", query = "select uc from UserConfirmation uc where uc.code = :code"),
        @NamedQuery(name = "UserConfirmation.findByUser", query = "select uc from UserConfirmation uc where uc.user = :user") })
public class UserConfirmation extends BaseEntity {

    /** Generated UID for serializable classes. */
    private static final long serialVersionUID = -6760014534260076732L;

    /** Confirmation code. */
    @Column(name = "CODE", nullable = false)
    private String code;

    /** User to be confirmed. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    /**
     * Code getter.
     * 
     * @return Code.
     */
    public String getCode() {
        return code;
    }

    /**
     * Code setter.
     * 
     * @param code
     *            Code to set.
     */
    public void setCode(String code) {
        this.code = code;
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

}
