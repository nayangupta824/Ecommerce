/**
 * ShopServiceRemote.java
 * Created: 6:49:52 PM Jul 20, 2008
 */
package lt.igdo.ejb.services.interfaces;

import java.util.List;

import lt.igdo.domain.Manager;
import lt.igdo.domain.Shop;

/**
 * Interface for Shop related services.
 * 
 * @author Ignas
 * 
 */
public interface IShopService {

    /**
     * Gets shop by id.
     * 
     * @param id
     *            Shop id.
     * @return Shop found.
     */
    Shop getShopById(Long id);

    /**
     * Gets all shop in the system.
     * 
     * @return List of shops.
     */
    List<Shop> getShops();

    /**
     * Saves new shop.
     * 
     * @param shop
     *            Shop to save.
     * @param manager
     *            Manager for the shop.
     */
    void saveShop(Shop shop, Manager manager);

}
