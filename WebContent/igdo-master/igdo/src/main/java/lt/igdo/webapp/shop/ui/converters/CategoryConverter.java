/**
 * CategoryConverter.java
 * Created: Apr 8, 2008 10:05:42 PM
 */
package lt.igdo.webapp.shop.ui.converters;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import lt.igdo.domain.Category;
import lt.igdo.webapp.shop.application.CategoryCache;

/**
 * Category converter using category cache.
 * 
 * @author Ignas
 * 
 */
@RequestScoped
@ManagedBean(name = "categoryConverter")
public class CategoryConverter implements Converter {

    /** Component were all system categories are cached. */
    @ManagedProperty(value = "#{lt.igdo.application.CategoryCache}")
    private CategoryCache categoriesCache;
    
    public void setCategoriesCache(CategoryCache categoriesCache) {
		this.categoriesCache = categoriesCache;
	}

	/**
     * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext,
     *      javax.faces.component.UIComponent, java.lang.String)
     */
    public Object getAsObject(FacesContext context, UIComponent component,
            String value) {

        if (value == null || value.trim().length() == 0)
            return null;

        Long id = Long.valueOf(value);

        return categoriesCache.getCategoryById(id);
    }

    /**
     * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext,
     *      javax.faces.component.UIComponent, java.lang.Object)
     */
    public String getAsString(FacesContext context, UIComponent component,
            Object object) {

        Category itemType = (Category) object;

        return itemType.getId().toString();
    }

}
