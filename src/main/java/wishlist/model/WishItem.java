package wishlist.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Table("WISHITEMS")
public class WishItem {

    @Id
    @Column("WISH_ITEM_ID")
    private Long id; // Primary key

    @Column("NAME")
    private String name;

    @Column("DESCRIPTION")
    private String description;

    @Column("PRICE")
    private Double price;

    @Column("WISHLIST_ID")
    private Long wishListId; // Foreign key

    public WishItem() {}

    public WishItem(Long id, String name, String description, Double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getWishListId() {
        return wishListId;
    }

    public void setWishListId(Long wishlistId) {
        this.wishListId = wishlistId;
    }
}
