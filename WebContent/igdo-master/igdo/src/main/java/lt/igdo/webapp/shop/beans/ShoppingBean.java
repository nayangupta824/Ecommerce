/**
 * ShoppingBean.java
 * Created: 11:45:11 PM Feb 12, 2008
 */
package lt.igdo.webapp.shop.beans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import lt.igdo.domain.Advertisment;
import lt.igdo.domain.Attribute;
import lt.igdo.domain.AttributeName;
import lt.igdo.domain.Category;
import lt.igdo.domain.Item;
import lt.igdo.ejb.services.interfaces.IAdvertisementService;
import lt.igdo.ejb.services.interfaces.IItemService;
import lt.igdo.webapp.shop.application.CategoryCache;

/**
 * Shopping related services component.
 * 
 * @author Donatas
 * 
 */
@ManagedBean(name = "shoppingBean")
@ViewScoped
public class ShoppingBean {

    /** Item service remote ejb. */
	@ManagedProperty(value="#{itemService}")
    private IItemService itemService;

    /** Advertisments service remote ejb. */
	@ManagedProperty(value="#{advertismentService}")
    private IAdvertisementService advertismentService;

    /** Component were all system item types are cached. */
	@ManagedProperty(value="#{categoryCache}")
    private CategoryCache categoryCache;

    /** Loaded attributes. */
    private Map<AttributeName, List<Attribute>> loadedAttributes;

    /** Category id request parameter for selected category identification. */
    private Long categoryId;

    /** Attribute id request parameter for selected attribute identification. */
    private Long attributeId;


    public void setItemService(IItemService itemService) {
		this.itemService = itemService;
	}

	public void setAdvertismentService(IAdvertisementService advertismentService) {
		this.advertismentService = advertismentService;
	}

	public void setCategoryCache(CategoryCache categoryCache) {
		this.categoryCache = categoryCache;
	}

	/**
     * Gets items by selected category to show on first page.
     * 
     * @return List of items.
     */
    public List<Item> getFirstPageItems() {
        return itemService
                .getFirstPageItems(getSelectedCategory(), attributeId);
    }

    /**
     * Gets all attributes of items of selected category.
     * 
     * @return Map of grouped by name attributes.
     */
    public List<AttributeName> getItemAttributeNames() {
        if (loadedAttributes == null) {
            loadAttributes();
        }

        return new ArrayList<AttributeName>(loadedAttributes.keySet());
    }

    /**
     * Gets item attributes by attribute name (e.g. name:color{value:white,
     * value:black, value:red})
     * 
     * @param attributeName
     *            Name of attribute.
     * @return List of different attributes of that name.
     */
    public List<Attribute> getItemAttributes(AttributeName attributeName) {
        return loadedAttributes.get(attributeName);
    }

    /**
     * Gets items by selected category to show in top items box.
     * 
     * @return List of items.
     */
    public List<Item> getTopItems() {
        return itemService.getTopItems(getSelectedCategory(), attributeId);
    }

    /**
     * Gets advertisments by selected category.
     * 
     * @return List of advertisments.
     */
    public List<Advertisment> getAdvertisments() {
        return advertismentService.getAdvertisments(getSelectedCategory());
    }

    /**
     * Gets categories to show in categories box. Returned categories are
     * children of currently selected category.
     * 
     * @return List of categories.
     */
    public List<Category> getCategories() {
        List<Category> subCategories = categoryCache
                .getCategoriesByParent(getSelectedCategory());
        return subCategories != null ? subCategories : categoryCache
                .getCategoriesByParent(getSelectedCategory()
                        .getParentCategory());
    }

    /**
     * Gets categories to show in in search bar dropdown.
     * 
     * @return List of categories.
     */
    public List<Category> getCategoriesForSearch() {
        List<Category> searchCategories = categoryCache
                .getCategoriesByParent(getSelectedCategory());
        return searchCategories != null ? searchCategories : Arrays.asList(getSelectedCategory());
    }

    /**
     * Gets parent categories of currently selected category. Used in
     * breadcrums.
     * 
     * @return List of categories.
     */
    public List<Category> getAllParentCategories() {
        Category category = getSelectedCategory();
        List<Category> allParentCategories = new ArrayList<Category>();
        while (category != null) {
            allParentCategories.add(category);
            category = category.getParentCategory();
        }
        // null represents parent type for all item types
        Collections.reverse(allParentCategories);
        return allParentCategories;
    }

    /**
     * Currently selected category getter.
     * 
     * @return Selected category.
     */
    public Category getSelectedCategory() {
        return categoryCache.getCategoryById(categoryId);
    }

    /**
     * Gets currently selected attribute id.
     * 
     * @return attributeId Selected attribute id.
     */
    public Long getSelectedAttributeId() {
        return attributeId;
    }

    /**
     * Loads attributes by selected category and sorts it to map in
     * loadedAttributes variable.
     */
    private void loadAttributes() {
        List<Attribute> attributes = itemService
                .getAttributes(getSelectedCategory());
        loadedAttributes = new HashMap<AttributeName, List<Attribute>>();
        for (Attribute attribute : attributes) {
            List<Attribute> attributesByName = loadedAttributes.get(attribute
                    .getAttributeName());
            if (attributesByName != null) {
                attributesByName.add(attribute);
            } else {
                attributesByName = new ArrayList<Attribute>();
                attributesByName.add(attribute);
                loadedAttributes.put(attribute.getAttributeName(),
                        attributesByName);
            }
        }
    }

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getAttributeId() {
		return attributeId;
	}

	public void setAttributeId(Long attributeId) {
		this.attributeId = attributeId;
	}
    
}
