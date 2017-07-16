/**
 * ShopServiceBean.java
 * Created: 6:51:30 PM Jul 20, 2008
 */
package lt.igdo.ejb.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import lt.igdo.domain.Manager;
import lt.igdo.domain.Shop;
import lt.igdo.ejb.services.interfaces.IShopService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Shop related services implementation.
 * 
 * @author Ignas
 * 
 */
@Service("shopService")
public class ShopService implements IShopService {

	/** Entity manager. */
	@PersistenceContext
    private EntityManager em;

    /**
     * @see lt.igdo.ejb.services.interfaces.IShopService#getShopById(java.lang.Long)
     */
    public Shop getShopById(Long id) {
        Shop shop = em.find(Shop.class, id);
        return shop;
    }

    /**
     * @see lt.igdo.ejb.services.interfaces.IShopService#getShops()
     */
    @SuppressWarnings("unchecked")
    public List<Shop> getShops() {
        Query query = em
                .createQuery("select s from Shop s join fetch s.shopInfo");
        return query.getResultList();
    }

    /**
     * @see lt.igdo.ejb.services.interfaces.IShopService#saveShop(lt.igdo.domain.Shop,
     *      lt.igdo.domain.Manager)
     */
    @Transactional(readOnly = false)
    public void saveShop(Shop shop, Manager manager) {
        em.persist(shop.getShopInfo());
        em.persist(shop);
        em.persist(manager);
    }

}
