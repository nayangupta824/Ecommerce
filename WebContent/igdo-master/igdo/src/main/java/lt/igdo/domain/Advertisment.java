/**
 * Advertisment.java
 * Created: Dec 9, 2007 8:05:10 PM
 */
package lt.igdo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lt.igdo.domain.base.BaseEntity;

/**
 * Advertisment domain object.
 * 
 * @author Donatas
 * 
 */
@Entity
@Table(name = "ADVERTISMENTS")
public class Advertisment extends BaseEntity {

    /** Generated UID for serializable classes. */
    private static final long serialVersionUID = 3813960059496606413L;

    /** Advertisment name. */
    @Column(name = "NAME", nullable = false)
    private String name;

    /** Description of advertisment. */
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    /** Link to webpage or item which is promoted. */
    @Column(name = "LINK", nullable = false)
    private String link;

    /** Advertisment can advertise sprecific Item. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID", nullable = true)
    private Item item;

    /** Advertisment can advertize specific shop. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SHOP_ID", nullable = true)
    private Shop shop;

    /** Image source unique id, for retrieving images from image server. */
    @Column(name = "IMAGE_SRC", nullable = true)
    @GeneratedValue
    private String imageSource;

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
     *            Name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Description getter.
     * 
     * @return Description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Description setter.
     * 
     * @param description
     *            Description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Link getter.
     * 
     * @return Link.
     */
    public String getLink() {
        return link;
    }

    /**
     * Link setter.
     * 
     * @param link
     *            Link to set.
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * Item getter.
     * 
     * @return Item.
     */
    public Item getItem() {
        return item;
    }

    /**
     * Item setter.
     * 
     * @param item
     *            Item to set.
     */
    public void setItem(Item item) {
        this.item = item;
    }

    /**
     * Shop getter.
     * 
     * @return Shop.
     */
    public Shop getShop() {
        return shop;
    }

    /**
     * Shop getter.
     * 
     * @param shop
     *            Shop to set.
     */
    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
