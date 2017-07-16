/**
 * Cart.java
 * Created: Sep 4, 2007 11:24:04 PM
 */
package lt.igdo.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lt.igdo.domain.base.BaseEntity;

/**
 * Shopping cart domain object.
 * 
 * @author Donatas
 * 
 */
@Entity
@Table(name = "CARTS")
@NamedQueries( { @NamedQuery(name = "Cart.findByUser", query = "select c from Cart c where c.user.id = :userId") })
public class Cart extends BaseEntity {

    /** Generated UID for serializable classes. */
    private static final long serialVersionUID = 5922802610511121510L;

    /** Cart's user */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

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
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        return result;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Cart other = (Cart) obj;
        if (user == null) {
            if (other.user != null)
                return false;
        } else if (!user.equals(other.user))
            return false;
        return true;
    }

}
