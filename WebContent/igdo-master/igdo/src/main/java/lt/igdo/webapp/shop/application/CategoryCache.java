/**
 * CategoriesCache.java
 * Created: Feb 5, 2008 10:23:31 PM
 */
package lt.igdo.webapp.shop.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import lt.igdo.commons.exceptions.fatal.UnrecoverableApplicationException;
import lt.igdo.domain.Category;
import lt.igdo.ejb.services.interfaces.ICategoryService;

/**
 * Component in application scope for keeping cached {@link Category} objects.
 * On application startup it loads all categories and sorts them. Categories in
 * application are used only from this cache.
 * 
 * @author Ignas
 * 
 */
@ManagedBean(name = "categoryCache")
@ViewScoped
public class CategoryCache {

    /** Category service remote ejb. */
	@ManagedProperty(value="#{categoryService}")
    private ICategoryService categoryService;

    /**
     * Map of all application {@link Category} objects. Parent category is map
     * key and value is list of children categories.
     */
    private Map<Category, List<Category>> sortedCategories;

    /** List of all categories. */
    private List<Category> allCategories;

    
	public void setCategoryService(ICategoryService categoryService) {
		this.categoryService = categoryService;
	}

	/**
     * Gets children categories by parent category.
     * 
     * @param parent
     *            Parent category.
     * @return Children categories of parent.
     */
    public List<Category> getCategoriesByParent(Category parent) {
        if (allCategories == null || sortedCategories == null) {
            buildCategories();
        }
        return sortedCategories.get(parent);
    }

    /**
     * Gets category by given id.
     * 
     * @param id
     *            Identifier of category we want to get.
     * @return Category.
     */
    public Category getCategoryById(Long id) {
        if (allCategories == null || sortedCategories == null) {
            buildCategories();
        }

        Category categoryToReturn = null;
        for (Category category : allCategories) {
            if (category.getId().equals(id)) {
                categoryToReturn = category;
            }
        }
        return categoryToReturn;
    }

    /**
     * Builds categories map from database.
     */
    private void buildCategories() {
        List<Category> categories = categoryService.getCategories();
        if (categories == null || categories.size() == 0) {
            throw new UnrecoverableApplicationException(
                    "no item types found in database");
        }

        Map<Category, List<Category>> categoriesMap = new HashMap<Category, List<Category>>();
        for (Category subCategory : categories) {
            Category parent = subCategory.getParentCategory();
            if (categoriesMap.get(parent) == null) {
                categoriesMap.put(parent, new ArrayList<Category>());
                categoriesMap.get(parent).add(subCategory);
            } else {
                categoriesMap.get(parent).add(subCategory);
            }
        }

        this.allCategories = categories;
        this.sortedCategories = categoriesMap;
    }

    /**
     * Refresh map of {@link Category} objects. Usually is usable when new item
     * type is added to database. For now refresh categories is not implemented
     * and changes in categories table in database are not mirrored to
     * application.
     */
    @SuppressWarnings("unused")
    private void refreshCategories() {
    }
}
