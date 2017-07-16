/**
 * ShopManager.java
 * Created: Nov 9, 2007 2:58:05 PM
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
 * Manager domain object.
 * 
 * @author Ignas
 * 
 */
@Entity
@Table(name = "MANAGERS")
@NamedQueries( { @NamedQuery(name = "Manager.findByLogin", query = "select m from Manager m where m.login = :login") })
public class Manager extends BaseEntity {

    /** Generated UID for serializable classes. */
    private static final long serialVersionUID = 1586972502282282962L;

    /** Shop manager login name. */
    @Column(name = "LOGIN", nullable = false)
    private String login;

    /** Shop manager password. */
    @Column(name = "PASSWD", nullable = false)
    private String password;

    /** Shop which this manager administrates. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SHOP_ID", nullable = false)
    private Shop shop;

    /** Last visited date. */
    @Temporal(TemporalType.DATE)
    @Column(name = "VISITED_ON", nullable = false)
    private Date visitedOn;

    /**
     * Login getter.
     * 
     * @return Login.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Login setter.
     * 
     * @param login
     *            Login to set.
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Password getter.
     * 
     * @return Password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Password setter.
     * 
     * @param password
     *            Password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Shop getter.
     * 
     * @return Shop.
     */
    public Shop getShop() {
        return shop;
    }

    /**
     * Shop setter.
     * 
     * @param shop
     *            Shop to set.
     */
    public void setShop(Shop shop) {
        this.shop = shop;
    }

    /**
     * Last visited date getter.
     * 
     * @return Last visited date.
     */
    public Date getVisitedOn() {
        return visitedOn;
    }

    /**
     * Last visited date setter.
     * 
     * @param visitedOn
     *            Last visited date to set.
     */
    public void setVisitedOn(Date visitedOn) {
        this.visitedOn = visitedOn;
    }

}
