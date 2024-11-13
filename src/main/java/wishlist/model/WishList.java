package wishlist.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Table("wishlists")
public class WishList {

    @Id
    private long wishListId; // Primary key

    private long userId; // Foreign key
    private String name;
    private String description;
    private List<Long> wishItemIds;

    public WishList() {
    }

    public WishList(long wishListId, String name, String description, List<Long> wishItemIds) {
        this.wishListId = wishListId;
        this.name = name;
        this.description = description;
        this.wishItemIds = wishItemIds;
    }

    public long getWishListId() {
        return wishListId;
    }

    public void setWishListId(long wishListId) {
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

    public List<Long> getWishItemIds() {
        return wishItemIds;
    }

    public void setWishItem(List<Long> wishItemIds) {
        this.wishItemIds = wishItemIds;

    }
}
