/**
 * Order.java
 * Created: 4:09:09 PM Aug 17, 2008
 */
package lt.igdo.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lt.igdo.domain.base.BaseEntity;

/**
 * Order domain object.
 * 
 * @author Ignas
 * 
 */
@Entity
@Table(name = "ORDERS")
public class Order extends BaseEntity {

    /** Generated serialVersionUID. */
    private static final long serialVersionUID = 466275624553410517L;

    @Temporal(TemporalType.DATE)
    @Column(name = "ORDERED_ON")
    private Date orderedOn;

    @Column(name = "STATUS")
    private OrderStatusEnum status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @OneToMany
    @JoinTable(name = "ORDER_ITEMS", joinColumns = { @JoinColumn(name = "ORDER_ID") }, inverseJoinColumns = @JoinColumn(name = "CART_ITEM_ID"))
    private List<CartItem> orderedItems;

    @Column(name = "ORDER_PRICE")
    private BigDecimal orderPrice;

    @Temporal(TemporalType.DATE)
    @Column(name = "DELIVERED_ON")
    private Date deliveredOn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SHIPPING_ADDRESS_ID", nullable = true)
    private Address shippingAddress;

    /**
     * @return deliveredOn.
     */
    public Date getDeliveredOn() {
        return deliveredOn;
    }

    /**
     * @param deliveredOn
     *            deliveredOn to set.
     */
    public void setDeliveredOn(Date deliveredOn) {
        this.deliveredOn = deliveredOn;
    }

    /**
     * @return shippingAddress.
     */
    public Address getShippingAddress() {
        return shippingAddress;
    }

    /**
     * @param shippingAddress
     *            shippingAddress to set.
     */
    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    /**
     * @return orderPrice.
     */
    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    /**
     * @param orderPrice
     *            orderPrice to set.
     */
    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    /**
     * @return status.
     */
    public OrderStatusEnum getStatus() {
        return status;
    }

    /**
     * @param status
     *            status to set.
     */
    public void setStatus(OrderStatusEnum status) {
        this.status = status;
    }

    /**
     * @return user.
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user
     *            user to set.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return orderedItems.
     */
    public List<CartItem> getOrderedItems() {
        return orderedItems;
    }

    /**
     * @param orderedItems
     *            orderedItems to set.
     */
    public void setOrderedItems(List<CartItem> orderedItems) {
        this.orderedItems = orderedItems;
    }

    /**
     * @return orderedOn.
     */
    public Date getOrderedOn() {
        return orderedOn;
    }

    /**
     * @param orderedOn
     *            orderedOn to set.
     */
    public void setOrderedOn(Date orderedOn) {
        this.orderedOn = orderedOn;
    }

}
