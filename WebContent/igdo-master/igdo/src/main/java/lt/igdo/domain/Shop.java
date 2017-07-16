/**
 * Shop.java
 * Created: Sep 4, 2007 11:17:29 PM
 */
package lt.igdo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lt.igdo.domain.base.BaseEntity;

import org.compass.annotations.Searchable;
import org.compass.annotations.SearchableId;

/**
 * Shop domain object.
 * 
 * @author Donatas
 * 
 */
@Entity
@Table(name = "SHOPS")
@Searchable
public class Shop extends BaseEntity {

    /** Generated UID for serializable classes. */
    private static final long serialVersionUID = -5839055053496203469L;

    /** Shop name field */
    @Column(name = "NAME", nullable = false)
    private String name;

    /** Unique id of the shop's logotype. */
    @Column(name = "IMAGE_SRC", nullable = true)
    private String imageSource;

    /** Additional shop information */
    @OneToOne
    @JoinColumn(name = "SHOP_INFO_ID", nullable = false)
    private ShopInfo shopInfo;

    /**
     * Pure constructor.
     */
    public Shop() {
        super();
        shopInfo = new ShopInfo();
    }

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
     * ImageSource getter.
     * 
     * @return ImageSource.
     */
    public String getImageSource() {
        return imageSource;
    }

    /**
     * ImageSource setter.
     * 
     * @param imageSource
     *            ImageSource to set.
     */
    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }

    /**
     * Additional shop info getter.
     * 
     * @return ShopInfo.
     */
    public ShopInfo getShopInfo() {
        return shopInfo;
    }

    /**
     * Additional shop info setter.
     * 
     * @param shopInfo
     *            ShopInfo to set.
     */
    public void setShopInfo(ShopInfo shopInfo) {
        this.shopInfo = shopInfo;
    }

}
