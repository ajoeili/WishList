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
    public void createWishList(WishList wishList) {
        if (wishList != null) {
            if (wishList.getName().isEmpty()) {
                throw new IllegalArgumentException("Name cannot be empty");
            }
            wishListRepository.save(wishList);
        }
    }

    // Add item to wishlist
    public void addItemToWishlist(Long wishListId, Long itemId) {
        WishList wishList = wishListRepository.findById(wishListId).orElse(null);
        if (wishList == null) {
            throw new WishListNotFoundException("Wishlist not found");
        }
        List<Long> wishItemIds = wishList.getWishItemIds();
        if (wishItemIds.contains(itemId)) {
            throw new IllegalArgumentException("Wish item already exists");
        }
        wishItemIds.add(itemId);

        wishList.setWishItem(wishItemIds);
        wishListRepository.save(wishList);
    }

    // Delete item from wishlist
    public void deleteItemFromWishlist(Long wishListId, Long itemId) {
        WishList wishList = wishListRepository.findById(wishListId).orElse(null);
        if (wishList == null) {
            throw new WishListNotFoundException("Wishlist not found");
        }
        List<Long> wishItemIds = wishList.getWishItemIds();
        if (!wishItemIds.contains(itemId)) {
            throw new IllegalArgumentException("Wish item does not exist");
        }
        wishItemIds.remove(itemId);
        wishList.setWishItem(wishItemIds);
        wishListRepository.save(wishList);
    }

    // Get wishlist by ID
    public WishList getWishListById(long wishListId) {
        WishList wishList = wishListRepository.findById(wishListId).orElse(null);
        if (wishList == null) {
            throw new WishListNotFoundException("Wishlist not found");
        }
        return wishList;
    }

    // Delete Wishlist by ID
    public void deleteWishListById(long wishListId) {
        WishList wishList = wishListRepository.findById(wishListId).orElse(null);
        if (wishList == null) {
            throw new WishListNotFoundException("Wishlist not found");
        }
        wishListRepository.delete(wishList);
    }

    // Get wish items by ID
    public List<WishItem> getWishItemsByWishlistId(long wishlistId) {
        WishList wishList = getWishListById(wishlistId);
        List<Long> wishItemIds = wishList.getWishItemIds();

        return (List<WishItem>) wishItemRepository.findAllById(wishItemIds);
    }

    // Get wishlists by user ID
    public List<WishList> getWishListsByUserId(Long userId) {
        return wishListRepository.findByUserId(userId);
    }


}