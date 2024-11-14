package wishlist.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.ArrayList;
import java.util.List;

@Table("WISHLISTS")
public class WishList {

    @Id
    @Column("WISHLIST_ID")
    private long wishListId; // Primary key

    @Column("USER_ID")
    private long userId; // Foreign key

    @Column("NAME")
    private String name;

    @Column("DESCRIPTION")
    private String description;

    public WishList() {
    }

    public WishList(long wishListId, String name, String description) {
        this.wishListId = wishListId;
        this.name = name;
        this.description = description;
    }

    public long getWishListId() {
        return wishListId;
    }

    public void setWishListId(long wishListId) {
        this.wishListId = wishListId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

}

