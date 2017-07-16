/**
 * CartBean.java
 * Created: 10:45:12 PM Feb 10, 2008
 */
package lt.igdo.webapp.shop.beans;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import lt.igdo.commons.helpers.CartHelper;
import lt.igdo.domain.Cart;
import lt.igdo.domain.CartItem;
import lt.igdo.domain.Item;
import lt.igdo.ejb.services.interfaces.CartServiceRemote;

/**
 * Cart related services component.
 * 
 * @author Ignas
 * 
 */
@ManagedBean(name = "cartBean")
@ViewScoped
public class CartBean {

    /** Cart service remote ejb. */
	@ManagedProperty(value="#{cartService}")
    private CartServiceRemote cartService;

    /** Injected user bean. */
    @ManagedProperty(value="#{userBean}")
    private UserBean userBean;

    /** Current user's cart. */
    private Cart cart;

    /** Total price of items in the cart. */
    private BigDecimal totalPrice;

    /** Total quantity of items in the cart. */
    private Long totalQuantity;

    
    public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public void setCartService(CartServiceRemote cartService) {
		this.cartService = cartService;
	}

	/**
     * Is invoked on creation of this component. Loads cart by current user.
     */
    @PostConstruct
    public void create() {
        cart = cartService.getCurrentCartByUser(userBean.getUser());
    }

    /**
     * Gets all cart items of current cart, and calculates total price and total
     * quantity.
     */
    public List<CartItem> getCartItems() {
        List<CartItem> cartItems = cartService.getCartItems(cart);

        totalPrice = CartHelper.calculateTotalPrice(cartItems);
        totalQuantity = CartHelper.calculateTotalQuantity(cartItems);

        return cartItems;
    }

    /**
     * Add new item to current cart.
     */
    public void addCartItem(Item itemToAdd) {
        cartService.addCartItem(cart, itemToAdd);
    }

    /**
     * Remove all items from current cart.
     */
    public void removeAllCartItems() {
        cartService.removeAllCartItems(cart);
    }

    /**
     * Remove concrete item from current cart.
     * 
     * @param selectedItem
     *            Cart item to remove.
     */
    public void removeCartItem(CartItem selectedItem) {
        cartService.removeCartItem(selectedItem);
    }

    /**
     * Total price getter.
     * 
     * @return Total price.
     */
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    /**
     * Total quantity getter.
     * 
     * @return Total quantity.
     */
    public Long getTotalQuantity() {
        return totalQuantity;
    }

    /**
     * Checkout current cart.
     * 
     * @return Ceckout view.
     */
    public String checkout() {
        return "/pages/cart/checkout.xhtml";
    }

}
