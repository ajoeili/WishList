package wishlist.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Table("wishitems")
public class WishItem {

    @Id
    private Long id; // Primary key

    private Long wishListId; // Foreign key
    private String name;
    private String description;
    private Double price;
    private List<String> manufacturersLinks;

    public WishItem() {
    }

    public WishItem(Long id, String name, String description, Double price, Boolean isReserved, String imageUrl, List<String> manufacturersLinks) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.manufacturersLinks = manufacturersLinks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWishListId() {
        return wishListId;
    }

    public void setWishListId(Long wishListId) {
        this.wishListId = wishListId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<String> getManufacturersLinks() {
        return manufacturersLinks;
    }

    public void setManufacturersLinks(List<String> manufacturersLinks) {
        this.manufacturersLinks = manufacturersLinks;
    }
}
