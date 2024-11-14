package wishlist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import wishlist.exception.WishListNotFoundException;
import wishlist.model.WishItem;
import wishlist.model.WishList;
import wishlist.service.WishListService;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Controller
@RequestMapping("/wishlists")
public class WishListController {

    private final WishListService wishListService;
    private static final Logger log = LoggerFactory.getLogger(UserController.class); // Just used this to debug

    // Constructor-based injection
    @Autowired
    public WishListController(WishListService wishListService) {
        this.wishListService = wishListService;
    }

    // Show create wishlist form
    @GetMapping("/create")
    public String showCreateWishListForm(Model model) {
        model.addAttribute("wishlist", new WishList());
        return "create-wishlist-form"; // Returns createWishListForm.html
    }

    // Handle create wishlist form submission
    @PostMapping("/create")
    public String createWishList(@ModelAttribute("wishlist") WishList wishList, long userId) {
        wishListService.createWishList(wishList, userId);
        return "redirect:/wishlists/user/{userId}"; // Redirects to user's wishlists
    }

    // Add item to wishlist
    @PostMapping("/{wishlistId}/items")
    public String addItemToWishlist(@PathVariable long wishlistId, @RequestParam Long wishItemId) {
        wishListService.addItemToWishlist(wishlistId, wishItemId);
        return "redirect:/wishlists/{wishlistId}"; // Redirects back to the wishlist page
    }

    // Delete an item from wishlist
    @PostMapping("/{wishlistId}/items/{itemId}/delete")
    public String deleteItemFromWishlist(@PathVariable Long wishlistId, @PathVariable Long itemId, RedirectAttributes redirectAttributes) {
        log.info("Attempting to delete item with ID {} from wishlist with ID {}", itemId, wishlistId);

        try {
            wishListService.deleteItemFromWishlist(wishlistId, itemId);
            redirectAttributes.addAttribute("message", "Item deleted successfully");
            return "redirect:/{wishlistId}/" + wishlistId;  // Redirect back to the wishlist page
        } catch (Exception e) {
            redirectAttributes.addAttribute("message", "Error occurred while deleting the item, try again later");
            return "redirect:/{wishlistId}/" + wishlistId;  // Redirect back to the wishlist page if error occurs
        }
    }


    // Show delete wishlist form
    @GetMapping("/{wishlistId}/delete")
    public String showDeleteWishlistForm(@PathVariable long wishlistId, Model model) {
        try {
            WishList wishlist = wishListService.getWishListById(wishlistId);
            model.addAttribute("wishlist", wishlist);
            return "delete-wishlist-form";
        } catch (WishListNotFoundException e) {
            model.addAttribute("message", "Wishlist not found.");
            return "redirect:/wishlists/user/{userId}"; // Redirect to the user's wishlist page
        }
    }

    // Handle delete wishlist submission
    @PostMapping("/{wishlistId}/delete")
    public String deleteWishList(@PathVariable long wishlistId, RedirectAttributes redirectAttributes) {
        try {

            WishList wishlist = wishListService.getWishListById(wishlistId);
            long userId = wishlist.getUserId();

            wishListService.deleteWishListById(wishlistId);

            redirectAttributes.addAttribute("message", "Wishlist deleted successfully.");
            return "redirect:/wishlists/user/{userId}"; // Redirect to the user's wishlist page

        } catch (WishListNotFoundException e) {
            redirectAttributes.addAttribute("errorMessage", "Wishlist not found.");
            return "redirect:/wishlists/user/{userId}"; // Redirect to the user's wishlist page
        }
    }

    // Display specific wishlist and its items
    @GetMapping("/{wishlistId}")
    public String showWishListDetails(@PathVariable long wishlistId, Model model) {
        WishList wishList = wishListService.getWishListById(wishlistId);
        List<WishItem> items = wishListService.getWishItemsByWishlistId(wishlistId);

        // Debugging
        System.out.println("Wishlist: " + wishList);
        System.out.println("Items: " + items);

        model.addAttribute("wishlist", wishList);
        model.addAttribute("items", items);

        return "wishlist-details"; // Returns wishlist-details.html
    }
}
