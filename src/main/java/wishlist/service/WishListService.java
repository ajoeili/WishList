package wishlist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wishlist.exception.WishListNotFoundException;
import wishlist.model.WishItem;
import wishlist.model.WishList;
import wishlist.repository.WishItemRepository;
import wishlist.repository.WishListRepository;

import java.util.List;

@Service
public class WishListService {

    private final WishListRepository wishListRepository;
    private final WishItemRepository wishItemRepository;

    // Constructor-based injection
    @Autowired
    public WishListService(WishListRepository wishListRepository, WishItemRepository WishItemRepository) {
        this.wishListRepository = wishListRepository;
        this.wishItemRepository = WishItemRepository;
    }

    // Create new wishlist
    public void createWishList(WishList wishList, long userId) {
        if (wishList != null) {
            if (wishList.getName().isEmpty()) {
                throw new IllegalArgumentException("Name cannot be empty");
            }
            wishList.setUserId(userId);
            wishListRepository.save(wishList);
        }
    }

    // Add item to wishlist
    public void addItemToWishlist(Long wishListId, Long itemId) {
        WishList wishList = wishListRepository.findById(wishListId)
                .orElseThrow(() -> new WishListNotFoundException("Wishlist not found"));

        WishItem wishItem = wishItemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Item not found"));

        if (wishItem.getWishListId() == wishListId) {
            throw new IllegalArgumentException("Item already exists in wishlist");
        }

        wishItem.setWishListId(wishListId);
        wishItemRepository.save(wishItem);
    }

    // Get items for wishlist by fetching full objects from IDs
    public List<WishItem> getWishItemsForWishList(Long wishListId) {
        wishListRepository.findById(wishListId)
                .orElseThrow(() -> new WishListNotFoundException("Wishlist not found"));

        return wishItemRepository.findByWishListId(wishListId);
    }

    // Delete item from wishlist
    public void deleteItemFromWishlist(Long wishListId, Long itemId) {
        WishList wishList = wishListRepository.findById(wishListId)
                .orElseThrow(() -> new WishListNotFoundException("Wishlist not found"));

        WishItem wishItem = wishItemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Item not found"));

        if (wishItem.getWishListId() != wishListId) {
            throw new IllegalArgumentException("Item not found in this wishlist");
        }

        wishItem.setWishListId(null);
        wishItemRepository.save(wishItem);
    }

    // Get wishlist by ID
    public WishList getWishListById(long wishListId) {
        return wishListRepository.findById(wishListId)
                .orElseThrow(() -> new WishListNotFoundException("Wishlist not found"));
    }

    // Delete Wishlist by ID
    public void deleteWishListById(long wishListId) {
        WishList wishList = wishListRepository.findById(wishListId)
                .orElseThrow(() -> new WishListNotFoundException("Wishlist not found"));

        List<WishItem> wishItems = wishItemRepository.findByWishListId(wishListId);
        for (WishItem wishItem : wishItems) {
            wishItem.setWishListId(null);
            wishItemRepository.save(wishItem);
        }
        wishListRepository.delete(wishList);
    }

    // Get wish items by ID
    public List<WishItem> getWishItemsByWishlistId(long wishlistId) {
        getWishListById(wishlistId);
        return wishItemRepository.findByWishListId(wishlistId);
    }

    // Get wishlists by user ID
    public List<WishList> getWishListsByUserId(Long userId) {
        return wishListRepository.findByUserId(userId);
    }

}