/**
 * ShopInfo.java
 * Created: Sep 14, 2007 12:41:25 PM
 */
package lt.igdo.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lt.igdo.domain.base.BaseEntity;

/**
 * Shop info domain object.
 * 
 * @author Ignas
 * 
 */
@Entity
@Table(name = "SHOP_INFO")
public class ShopInfo extends BaseEntity implements Serializable {

    /** Generated UID for serializable classes. */
    private static final long serialVersionUID = 7730482700847257662L;

    /** Shop which information this object holds */
    @OneToOne(mappedBy = "shopInfo", cascade = CascadeType.ALL)
    private Shop shop;

    /** Shop address */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ADDRESS_ID", nullable = false)
    private Address address;

    /** Contacts of reponsible person */
    @Embedded
    private Contact contact;

    /**
     * Pure constructor.
     */
    public ShopInfo() {
        super();
        address = new Address();
        contact = new Contact();
    }

    /**
     * Contact getter.
     * 
     * @return Contact.
     */
    public Contact getContact() {
        return contact;
    }

    /**
     * Contact setter.
     * 
     * @param contact
     *            Contact to set.
     */
    public void setContact(Contact contact) {
        this.contact = contact;
    }

    /**
     * Address getter.
     * 
     * @return Address.
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Address setter.
     * 
     * @param address
     *            Address to set.
     */
    public void setAddress(Address address) {
        this.address = address;
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

}
