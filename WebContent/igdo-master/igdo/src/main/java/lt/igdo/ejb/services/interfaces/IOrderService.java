/**
 * PreferencesServiceRemote.java
 * Created: 8:44:39 PM Aug 17, 2008
 */
package lt.igdo.ejb.services.interfaces;

import java.util.List;

import lt.igdo.domain.CartItem;
import lt.igdo.domain.Order;
import lt.igdo.domain.User;

/**
 * Interface for user preferences related services.
 * 
 * @author Ignas
 * 
 */
public interface IOrderService {

    /**
     * Gets orders which is already delivered. Useful for orders history.
     * 
     * @param user
     *            User whose orders history to return
     * @return List of delivered orders.
     */
    public List<Order> getDeliveredOrders(User user);

    /**
     * Gets orders which are going to be delivered to user. Useful for tracking
     * orders by looking at current order status.
     * 
     * @param user
     *            User whose orders history to return
     * @return List of orders not delivered yet.
     */
    public List<Order> getWaitingOrders(User user);

    /**
     * Place an order for cart items.
     * 
     * @param cartItems
     *            Cart items that will form an order.
     */
    public Order placeAnOrder(List<CartItem> cartItems, User user);

}
