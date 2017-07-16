/**
 * ItemServiceBean.java
 * Created: May 24, 2008 9:11:01 PM
 */
package lt.igdo.ejb.services;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import lt.igdo.domain.Attribute;
import lt.igdo.domain.Category;
import lt.igdo.domain.Item;
import lt.igdo.ejb.services.interfaces.IItemService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Item related services implementation.
 * 
 * @author Donatas
 * 
 */
@Service("itemService")
public class ItemService implements IItemService {

    /** Select items query constant. */
    private static final String SELECT_ITEMS_QUERY = "select i from Item i join fetch i.types";

    /** Entity manager. */
	@PersistenceContext
    private EntityManager em;

    /**
     * @see lt.igdo.ejb.services.interfaces.IItemService#getItems()
     */
    @SuppressWarnings("unchecked")
    public List<Item> getItems() {
        return em.createQuery(ItemService.SELECT_ITEMS_QUERY)
                .getResultList();
    }

    /**
     * @see lt.igdo.ejb.services.interfaces.IItemService#getAttributes(lt.igdo.domain.Category)
     */
    @SuppressWarnings("unchecked")
    public List<Attribute> getAttributes(Category selectedCategory) {
        if (selectedCategory != null) {
            Query attributesQuery = em
                    .createQuery("select a from Attribute a, Category c "
                            + "join c.attributeTemplate at "
                            + "where c.id = :categoryId and a.attributeName.id = at.id");
            attributesQuery
                    .setParameter("categoryId", selectedCategory.getId());
            // List<Attribute> attributesFound =
            // attributesQuery.getResultList();
            // Set<Attribute> uniqueAttributes = new HashSet<Attribute>();
            // uniqueAttributes.addAll(attributesFound);
            // return new ArrayList(uniqueAttributes);
            return attributesQuery.getResultList();
        } else {
            return Collections.EMPTY_LIST;
        }
    }

    /**
     * @see lt.igdo.ejb.services.interfaces.IItemService#getFirstPageItems(lt.igdo.domain.Category, java.lang.Long)
     */
    @SuppressWarnings("unchecked")
    public List<Item> getFirstPageItems(Category selectedCategory,
            Long attributeId) {
        Query query = null;
        if (selectedCategory == null) {
            query = em.createQuery("select i from Item i");
        } else if (selectedCategory != null && attributeId == null) {
            query = em.createQuery("select i from Item i, in (i.categories) c "
                    + "where c.id = :categoryId");
            query.setParameter("categoryId",
                    selectedCategory != null ? selectedCategory.getId() : null);
        } else if (selectedCategory != null && attributeId != null) {
            query = em
                    .createQuery("select i from Item i, in (i.categories) c, in (i.attributes) a "
                            + "where c.id = :categoryId and a.id = :attributeId");
            query.setParameter("categoryId",
                    selectedCategory != null ? selectedCategory.getId() : null);
            query.setParameter("attributeId", attributeId);
        }

        return query.getResultList();
    }

    /**
     * @see lt.igdo.ejb.services.interfaces.IItemService#getTopItems(lt.igdo.domain.Category, java.lang.Long)
     */
    @SuppressWarnings("unchecked")
    public List<Item> getTopItems(Category selectedCategory, Long attributeId) {
        Query query = null;
        if (selectedCategory == null) {
            query = em.createQuery("select i from Item i");
        } else if (selectedCategory != null && attributeId == null) {
            query = em.createQuery("select i from Item i, in (i.categories) c "
                    + "where c.id = :categoryId");
            query.setParameter("categoryId",
                    selectedCategory != null ? selectedCategory.getId() : null);
        } else if (selectedCategory != null && attributeId != null) {
            query = em
                    .createQuery("select i from Item i, in (i.categories) c, in (i.attributes) a "
                            + "where c.id = :categoryId and a.id = :attributeId");
            query.setParameter("categoryId",
                    selectedCategory != null ? selectedCategory.getId() : null);
            query.setParameter("attributeId", attributeId);
        }

        query.setMaxResults(10);
        // TODO no hardcode
        return query.getResultList();
    }

    /**
     * @see lt.igdo.ejb.services.interfaces.IItemService#saveItem(lt.igdo.domain.Item)
     */
    @Transactional(readOnly = false)
    public Item saveItem(Item item) {
        em.persist(item);
        return item;
    }

    /**
     * @see lt.igdo.ejb.services.interfaces.IItemService#getItemById(java.lang.Long)
     */
    public Item getItemById(Long id) {
        return em.find(Item.class, id);
    }

}
