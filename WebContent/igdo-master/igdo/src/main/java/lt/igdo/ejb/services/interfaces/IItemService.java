/**
 * ItemServiceRemote.java
 * Created: May 24, 2008 9:11:01 PM
 */
package lt.igdo.ejb.services.interfaces;

import java.util.List;

import lt.igdo.domain.Attribute;
import lt.igdo.domain.Category;
import lt.igdo.domain.Item;

/**
 * Interface for Item related services.
 * 
 * @author Donatas
 * 
 */
public interface IItemService {

    /**
     * Gets item by id.
     * 
     * @param id
     *            Item id.
     * @return Item.
     */
    Item getItemById(Long id);

    /**
     * Save new item.
     * 
     * @param item
     *            Item to save.
     * @return Saved item.
     */
    Item saveItem(Item item);

    /**
     * Gets all attributes of items of selected category grouped by attribute
     * name.
     * 
     * @param selectedCategory
     *            Selected category for items navigation.
     * @return Map of attributes grouped by names.
     */
    List<Attribute> getAttributes(Category selectedCategory);

    /**
     * Gets all items.
     * 
     * @return Collection of categories.
     */
    List<Item> getItems();

    /**
     * Gets first page items.
     * 
     * @param selectedCategory
     *            Selected category for items navigation.
     * @param attributeId
     *            Selected attribute for items navigation.
     * @return List of first page items.
     */
    List<Item> getFirstPageItems(Category selectedCategory, Long attributeId);

    /**
     * Gets top items.
     * 
     * @param selectedCategory
     *            Selected category for items navigation.
     * @param attributeId
     *            Selected attribute for items navigation.
     * @return List of top items.
     */
    List<Item> getTopItems(Category selectedCategory, Long attributeId);

}
