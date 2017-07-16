/**
 * CartServiceBean.java
 * Created: Jun 11, 2008 8:11:01 PM
 */
package lt.igdo.ejb.services;

import java.util.List;

import javax.naming.OperationNotSupportedException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import lt.igdo.commons.utils.PersistenceUtils;
import lt.igdo.domain.Cart;
import lt.igdo.domain.CartItem;
import lt.igdo.domain.Item;
import lt.igdo.domain.User;
import lt.igdo.ejb.services.interfaces.CartServiceRemote;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Cart related services implementation.
 * 
 * @author Ignas
 * 
 */
@Service("cartService")
public class CartService implements CartServiceRemote {

    /** Find current cart by user query constant. */
    private static final String FIND_CURRENT_CART_BY_USER_QUERY = "Cart.findByUser";

    /** Select cart items query constant. */
    private static final String SELECT_CARTITEMS_QUERY = "select ci from CartItem ci join fetch ci.item join fetch ci.cart where ci.cart.id = :cartId";

    /** Remove cart item query constant. */
    private static final String REMOVE_SINGLE_CARTITEM_QUERY = "delete from CartItem ci where ci.id = :cartItemId";

    /** Remove all cart items query constant. */
    private static final String REMOVE_CARTITEMS_QUERY = "delete from CartItem ci where ci.cart.id = :cartId";

    /** Entity manager. */
	@PersistenceContext
    private EntityManager em;

	@Transactional(readOnly = false)
    public Cart createCartByUser(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        em.persist(cart);
        return cart;
    }

    /**
     * @see lt.igdo.ejb.services.interfaces.CartServiceRemote#removeCart(lt.igdo.domain.Cart)
     */
    @Transactional(readOnly = false)
    public void removeCart(Cart cart) {
        em.remove(cart);
    }

    /**
     * @see lt.igdo.ejb.services.interfaces.CartServiceRemote#setCartCurrent(lt.igdo.domain.Cart)
     */
    public void setCartCurrent(Cart cart) throws Exception {
        throw new OperationNotSupportedException();
    }

    /**
     * @see lt.igdo.ejb.services.interfaces.CartServiceRemote#getCurrentCartByUser(lt.igdo.domain.User)
     */
    public Cart getCurrentCartByUser(User user) {
        Query query = em
                .createNamedQuery(CartService.FIND_CURRENT_CART_BY_USER_QUERY);
        query.setParameter("userId", user.getId());
        Cart cart = (Cart) PersistenceUtils.getSingleResult(query
                .getResultList());
        if (cart == null) {
            cart = createCartByUser(user);
        }
        return cart;
    }

    /**
     * @see lt.igdo.ejb.services.interfaces.CartServiceRemote#getAllCartsByUser(lt.igdo.domain.User)
     */
    public List<Cart> getAllCartsByUser(User user) throws Exception {
        throw new OperationNotSupportedException();
    }

    /**
     * @see lt.igdo.ejb.services.interfaces.CartServiceRemote#addCartItem(lt.igdo.domain.Cart,
     *      lt.igdo.domain.Item)
     */
    @Transactional(readOnly = false)
    public void addCartItem(Cart cart, Item item) {
        if (cart.isTransient()) {
        	em.persist(cart);
        }
        CartItem cartItem = new CartItem(item, cart);
        em.persist(cartItem);
    }

    /**
     * @see lt.igdo.ejb.services.interfaces.CartServiceRemote#removeCartItem(lt.igdo.domain.CartItem)
     */
    @Transactional(readOnly = false)
    public void removeCartItem(CartItem cartItem) {
        Query query = em
                .createQuery(CartService.REMOVE_SINGLE_CARTITEM_QUERY);
        query.setParameter("cartItemId", cartItem.getId());
        query.executeUpdate();
    }

    /**
     * @see lt.igdo.ejb.services.interfaces.CartServiceRemote#getCartItems(lt.igdo.domain.Cart)
     */
    @SuppressWarnings("unchecked")
    public List<CartItem> getCartItems(Cart cart) {
        Query query = em.createQuery(CartService.SELECT_CARTITEMS_QUERY);
        query.setParameter("cartId", cart.getId());
        return query.getResultList();
    }

    /**
     * @see lt.igdo.ejb.services.interfaces.CartServiceRemote#removeAllCartItems(lt.igdo.domain.Cart)
     */
    @Transactional(readOnly = false)
    public void removeAllCartItems(Cart cart) {
        Query query = em.createQuery(CartService.REMOVE_CARTITEMS_QUERY);
        query.setParameter("cartId", cart.getId());
        query.executeUpdate();
    }

}
