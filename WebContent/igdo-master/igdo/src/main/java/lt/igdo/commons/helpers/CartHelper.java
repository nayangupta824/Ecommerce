/**
 * CartHelper.java
 * Created: 12:06:21 AM Aug 18, 2008
 */
package lt.igdo.commons.helpers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import lt.igdo.domain.CartItem;

/**
 * Helper class for cart and cart related services.
 * 
 * @author Ignas
 * 
 */
public class CartHelper {

    /**
     * No need to create util instance.
     */
    private CartHelper() {

    }

    /**
     * Calculates total quantity of items by provided cart items.
     * 
     * @param cartItems
     *            List of provided {@link CartItem} objects.
     * @return Total quantity.
     */
    public static Long calculateTotalQuantity(List<CartItem> cartItems) {
        Long totalQuantity = 0L;
        for (CartItem cartItem : cartItems) {
            totalQuantity += cartItem.getQuantity();
        }
        return totalQuantity;
    }

    /**
     * Calculates total price of items by provided cart items.
     * 
     * @param cartItems
     *            List of provided {@link CartItem} objects.
     * @return Total price.
     */
    public static BigDecimal calculateTotalPrice(List<CartItem> cartItems) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        totalPrice.setScale(2, RoundingMode.HALF_UP);
        for (CartItem cartItem : cartItems) {
            totalPrice = totalPrice.add(cartItem.getItem().getPrice().multiply(
                    new BigDecimal(cartItem.getQuantity())));
        }
        return totalPrice;
    }
}
