package wishlist.model;

import java.util.List;

public class WishList {
    private long id;
    private String name;
    private String description;
    private List<Long> wishItemIds;

    public WishList() {
    }

    public WishList(int id, String name, String description, List<Long> wishItemIds) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.wishItemIds = wishItemIds;
    }
    public long getId() {
        return id;
    }
    public void setId(int id) {
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
    public List<Long> getWishItemIds() {
        return wishItemIds;
    }
    public void setWishItem(List<Long> wishItemIds) {
        this.wishItemIds = wishItemIds;

    }
}
