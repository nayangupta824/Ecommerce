/**
 * CartServiceRemote.java
 * Created: Jun 11, 2008 8:11:01 PM
 */
package lt.igdo.ejb.services.interfaces;

import java.util.List;

import lt.igdo.domain.Cart;
import lt.igdo.domain.CartItem;
import lt.igdo.domain.Item;
import lt.igdo.domain.User;

/**
 * Interface for Authentication related services.
 * 
 * @author Ignas
 * 
 */
public interface CartServiceRemote {

    /**
     * Creates new cart for user.
     * 
     * @param user
     *            User to create cart for.
     */
    Cart createCartByUser(User user);

    /**
     * Remove cart.
     * 
     * @param cart
     *            Cart to remove.
     */
    void removeCart(Cart cart);

    /**
     * Set a cart as current cart.
     * 
     * @param cart
     *            Cart which need to be set as current.
     */
    void setCartCurrent(Cart cart) throws Exception;

    /**
     * Gets users current cart.
     * 
     * @param user
     *            User whose current cart we want to get.
     * @return Current user cart.
     */
    Cart getCurrentCartByUser(User user);

    /**
     * Gets users all carts.
     * 
     * @param user
     *            User whose carts we want to get.
     * @return List of carts.
     */
    List<Cart> getAllCartsByUser(User user) throws Exception;

    /**
     * Adds item to cart.
     * 
     * @param cart
     *            Cart to add item to.
     * @param item
     *            Item to add.
     */
    void addCartItem(Cart cart, Item item);

    /**
     * Removes cart item from cart.
     * 
     * @param cartItem
     *            CartItem to remove.
     */
    void removeCartItem(CartItem cartItem);

    /**
     * Gets all cart items.
     * 
     * @param cart
     *            Cart to get cart items from.
     * @return List of cart items.
     */
    List<CartItem> getCartItems(Cart cart);

    /**
     * Removes all cart items.
     * 
     * @param cart
     *            Cart remove cart items from
     */
    void removeAllCartItems(Cart cart);

}
