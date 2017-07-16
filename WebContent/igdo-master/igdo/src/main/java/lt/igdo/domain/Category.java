/**
 * Category.java
 * Created: Sep 4, 2007 11:13:36 PM
 */
package lt.igdo.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lt.igdo.domain.base.BaseEntity;

import org.compass.annotations.Searchable;
import org.compass.annotations.SearchableId;

/**
 * Shop category domain object.
 * 
 * @author Donatas
 * 
 */
@Entity
@Table(name = "CATEGORIES")
@Searchable
public class Category extends BaseEntity {

    /** Generated UID for serializable classes. */
    private static final long serialVersionUID = 6601798071915573427L;

    /** Name of the item type. */
    @Column(name = "NAME", nullable = false)
    private String name;

    /** Parent type. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ID", nullable = true)
    private Category parentCategory;

    /**
     * Attribute template which is list of attribute names. So items of this
     * category should have those attributes set.
     */
    @ManyToMany
    @JoinTable(name = "ATTRIBUTE_TEMPLATES", joinColumns = { @JoinColumn(name = "CATEGORY_ID") }, inverseJoinColumns = @JoinColumn(name = "ATTRIBUTE_NAME_ID"))
    private List<AttributeName> attributeTemplate;

    /**
     * Compass search is used for full text search. By design Compass search
     * needs to store an id in the index. <i>@SearchableId</i> annotation marks
     * the property to use for this purpose. Since our item id is inherited from
     * {@link BaseEntity} we need transient method to get that id and
     * annotate it for Compass search.
     * 
     * @return Item ID
     */
    @Override
    @SearchableId
    public Long getId() {
        return super.getId();
    }

    /**
     * Parent category getter.
     * 
     * @return Parent category
     */
    public Category getParentCategory() {
        return parentCategory;
    }

    /**
     * Parent category setter.
     * 
     * @param parentCategory
     *            Parent category to set.
     */
    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    /**
     * Name getter.
     * 
     * @return Name.
     */
    public String getName() {
        return name;
    }

    /**
     * Name setter.
     * 
     * @param name
     *            Name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Attribute template (list of attribute names) getter.
     * 
     * @return Attribute template.
     */
    public List<AttributeName> getAttributeTemplate() {
        return attributeTemplate;
    }

    /**
     * Attribute template (list of attribute names) setter.
     * 
     * @param attributeTemplate
     *            AttributeTemplate to set.
     */
    public void setAttributeTemplate(List<AttributeName> attributeTemplate) {
        this.attributeTemplate = attributeTemplate;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result
                + ((parentCategory == null) ? 0 : parentCategory.hashCode());
        return result;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Category other = (Category) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (parentCategory == null) {
            if (other.parentCategory != null)
                return false;
        } else if (!parentCategory.equals(other.parentCategory))
            return false;
        return true;
    }

}
