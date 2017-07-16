/**
 * User.java
 * Created: Oct 23, 2007 8:05:46 PM
 */
package lt.igdo.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lt.igdo.domain.base.BaseEntity;

/**
 * User domain object.
 * 
 * @author Donatas
 * 
 */
@Entity
@Table(name = "USERS")
@NamedQueries( {
        @NamedQuery(name = "User.findByUniqueId", query = "select u from User u left join fetch u.userAddresses where u.uniqueId = :id"),
        @NamedQuery(name = "User.findByEmail", query = "select u from User u left join fetch u.userAddresses where u.email = :email"),
        @NamedQuery(name = "User.login", query = "select u from User u where u.email = :email") })
public class User extends BaseEntity {

    /** Generated UID for serializable classes. */
    private static final long serialVersionUID = 5586972502289888964L;

    /** Unique identifier. */
    @Column(name = "UNIQUE_ID", nullable = false)
    private String uniqueId;

    /** First name. */
    @Column(name = "FIRST_NAME", nullable = true)
    private String firstName;

    /** Last name. */
    @Column(name = "LAST_NAME", nullable = true)
    private String lastName;

    /** Last visited date. */
    @Temporal(TemporalType.DATE)
    @Column(name = "VISITED_ON", nullable = false)
    private Date visitedOn;

    /** User name. */
    @Column(name = "USER_NAME", nullable = true)
    private String userName;

    /** Email. */
    @Column(name = "EMAIL", nullable = true)
    private String email;

    /** User password. */
    @Column(name = "PASSWD", nullable = true)
    // TODO: validate password strength
    private String password;

    /** User registered or an anonymous one? */
    @Column(name = "REGISTERED", nullable = false)
    private boolean registered;

    /** User addresses book. */
    @OneToMany
    @JoinTable(name = "USER_ADDRESSES", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = @JoinColumn(name = "ADDRESS_ID"))
    private List<Address> userAddresses;

    /** Prefered payment method for this user (last one he used when buying). */
    @Column(name = "PAYMENT_METHOD", nullable = true)
    private PaymentMethodEnum paymentMethod;

    /** Prefered shipping address for this user (last one he used when buying). */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SHIPPING_ADDRESS_ID", nullable = true)
    private Address shippingAddress;

    /**
     * UniqueId getter.
     * 
     * @return the UniqueId.
     */
    public String getUniqueId() {
        return uniqueId;
    }

    /**
     * UniqueId setter.
     * 
     * @param uniqueId
     *            UniqueId to set.
     */
    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    /**
     * First name getter.
     * 
     * @return First name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * FirstName setter.
     * 
     * @param firstName
     *            First name to set.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Last name getter.
     * 
     * @return Last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Last name setter.
     * 
     * @param lastName
     *            the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    /**
     * User name getter.
     * 
     * @return User name.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * User name setter.
     * 
     * @param userName
     *            User name to set.
     */
    public void setUserName(String userName) {
        this.userName = userName;
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
     * Registered getter.
     * 
     * @return true if registered and false otherwise.
     */
    public boolean isRegistered() {
        return registered;
    }

    /**
     * Registered setter.
     * 
     * @param registered
     *            Registered boolean value to set.
     */
    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    /**
     * Email getter.
     * 
     * @return Email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Email setter.
     * 
     * @param email
     *            Email to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * User addresses getter.
     * 
     * @return Addresses.
     */
    public List<Address> getUserAddresses() {
        return userAddresses;
    }

    /**
     * User addresses setter.
     * 
     * @param userAddresses
     *            Addresses to set.
     */
    public void setUserAddresses(List<Address> userAddresses) {
        this.userAddresses = userAddresses;
    }

    /**
     * Payment method setter.
     * 
     * @return PaymentMethod.
     */
    public PaymentMethodEnum getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * Payment method setter.
     * 
     * @param paymentMethod
     *            PaymentMethod to set.
     */
    public void setPaymentMethod(PaymentMethodEnum paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * Shipping address getter.
     * 
     * @return Shipping address.
     */
    public Address getShippingAddress() {
        return shippingAddress;
    }

    /**
     * Shipping address setter.
     * 
     * @param shippingAddress
     *            Shipping address to set.
     */
    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result
                + ((uniqueId == null) ? 0 : uniqueId.hashCode());
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
        final User other = (User) obj;
        if (uniqueId == null) {
            if (other.uniqueId != null)
                return false;
        } else if (!uniqueId.equals(other.uniqueId))
            return false;
        return true;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return super.toString() + ",userName=" + userName + ",firstName="
                + firstName + ",lastName=" + lastName + ",uniqueId=" + uniqueId;
    }

}
