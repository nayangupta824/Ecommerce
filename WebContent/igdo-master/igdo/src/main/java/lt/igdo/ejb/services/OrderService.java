/**
 * OrderServiceBean.java
 * Created: 10:36:22 PM Aug 17, 2008
 */
package lt.igdo.ejb.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import lt.igdo.commons.helpers.CartHelper;
import lt.igdo.domain.CartItem;
import lt.igdo.domain.Order;
import lt.igdo.domain.OrderStatusEnum;
import lt.igdo.domain.User;
import lt.igdo.ejb.services.interfaces.IOrderService;
import lt.igdo.exceptions.NoItemsForOrderException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Order related services implementation.
 * 
 * @author Ignas
 * 
 */
@Service("orderService")
public class OrderService implements IOrderService {

	/** Entity manager. */
	@PersistenceContext
    private EntityManager em;

    /**
     * @see lt.igdo.ejb.services.interfaces.IOrderService#getDeliveredOrders(lt.igdo.domain.User)
     */
    @SuppressWarnings("unchecked")
    public List<Order> getDeliveredOrders(User user) {
        Query query = em.createQuery("select o from Order o "
                + "where o.user.id = :userId and o.status = :statusDelivered");
        query.setParameter("statusDelivered", OrderStatusEnum.DELIVERED);
        query.setParameter("userId", user.getId());
        return query.getResultList();
    }

    /**
     * @see lt.igdo.ejb.services.interfaces.IOrderService#getWaitingOrders(lt.igdo.domain.User)
     */
    @SuppressWarnings("unchecked")
    public List<Order> getWaitingOrders(User user) {
        Query query = em.createQuery("select o from Order o "
                + "where o.user.id = :userId and o.status <> :statusDelivered");
        query.setParameter("statusDelivered", OrderStatusEnum.DELIVERED);
        query.setParameter("userId", user.getId());
        return query.getResultList();
    }

    /**
     * @see lt.igdo.ejb.services.interfaces.IOrderService#placeAnOrder(java.util.List, lt.igdo.domain.User)
     */
    @Transactional(readOnly = false)
    public Order placeAnOrder(List<CartItem> cartItems, User user) {
        if (cartItems == null || cartItems.size() == 0) {
            throw new NoItemsForOrderException();
        }
        Order order = new Order();
        order.setUser(cartItems.get(0).getCart().getUser());
        order.setOrderedItems(cartItems);
        order.setOrderPrice(CartHelper.calculateTotalPrice(cartItems));
        order.setShippingAddress(user.getShippingAddress());
        order.setStatus(OrderStatusEnum.PROCESSING);
        em.persist(order);
        return order;
    }
}
