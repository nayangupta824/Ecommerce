/**
 * OrderAction.java
 * Created: 9:36:27 PM Aug 25, 2008
 */
package lt.igdo.webapp.shop.actions;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import lt.igdo.commons.helpers.CartHelper;
import lt.igdo.commons.utils.FacesUtils;
import lt.igdo.domain.CartItem;
import lt.igdo.domain.Order;
import lt.igdo.ejb.services.interfaces.IOrderService;
import lt.igdo.webapp.shop.beans.CartBean;
import lt.igdo.webapp.shop.beans.UserBean;

/**
 * Component for order items to buy.
 * 
 * @author Ignas
 * 
 */
@ManagedBean(name = "orderAction")
@ViewScoped
public class OrderAction {

    /**
     * Cart bean user for looking what user has in cart and other cart related
     * stuff.
     */
	@ManagedProperty(value="#{cartBean}")
    private CartBean cartBean;

    /** Injected user bean. */
	@ManagedProperty(value="#{userBean}")
    private UserBean userBean;

    /** Item service remote ejb. */
	@ManagedProperty(value="#{orderService}")
    private IOrderService orderService;

    /** */
    private List<CartItem> cartItems;

    private Order orderToShow;

    
    public void setCartBean(CartBean cartBean) {
		this.cartBean = cartBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public void setOrderService(IOrderService orderService) {
		this.orderService = orderService;
	}

	/**
     * @return cartItems.
     */
    public List<CartItem> getCartItems() {
        return cartItems;
    }

    /**
     * Is invoked on creation of this component.
     */
    @PostConstruct
    public void create() {
        cartItems = cartBean.getCartItems();
    }

    /**
     * Gets current orders waiting for deliver.
     * 
     * @return List of orders not delivered yet.
     */
    public List<Order> getCurrentOrders() {
        return orderService.getWaitingOrders(userBean.getUser());
    }

    /**
     * Gets delivered orders.
     * 
     * @return List of orders already delivered.
     */
    public List<Order> getDeliveredOrders() {
        return orderService.getDeliveredOrders(userBean.getUser());
    }

    /**
     * @return Total price for an order.
     */
    public BigDecimal getOrderTotalPrice() {
        return CartHelper.calculateTotalPrice(cartItems);
    }

    public String showOrder(Order order) {
        this.orderToShow = order;
        return "/pages/user/preferences/orderView.xhtml";
    }

    /**
     * @return orderToShow.
     */
    public Order getOrderToShow() {
        return orderToShow;
    }

    /**
     * Orders items in users current cart.
     */
    public void order() {
        if (cartItems == null || cartItems.size() == 0) {
            FacesUtils.error("order.noItems");
        }
        Order order = orderService.placeAnOrder(cartItems, userBean.getUser());
        if (order != null) {
        	FacesUtils.info("order.placed");
        } else {
        	FacesUtils.error("order.notPlaced");
        }
    }

}
