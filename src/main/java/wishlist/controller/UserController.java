package wishlist.controller;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import wishlist.exception.InvalidCredentialsException;
import wishlist.exception.UserNotFoundException;
import wishlist.model.User;
import wishlist.model.WishList;
import wishlist.service.UserService;
import wishlist.service.WishListService;

import java.util.List;


@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final WishListService wishListService;

    // Constructer-based injection
    @Autowired
    public UserController(UserService userService, WishListService wishListService) {
        this.userService = userService;
        this.wishListService = wishListService;
    }

    // Show the registration form
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register"; // Returns register.html
    }

    // Handle registration form submission
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        userService.createUser(user);
        return "redirect:/wishlist/login"; // Redirects to login page
    }

    // Show user profile
    @GetMapping("/{id}")
    public String getUserProfile(@PathVariable long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);

        List<WishList> wishLists = wishListService.getWishListsByUserId(id);

        model.addAttribute("user", user);
        model.addAttribute("wishLists", wishLists);

        return "profile"; // Returns profile.html
    }

    // Show edit form
    @GetMapping("/{id}/edit")
    public String showEditProfileForm(@PathVariable long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "edit-profile-form"; // Returns edit-profile-form.html
    }

    // Handle edit form submission
    @PostMapping("/{id}/edit")
    public String updateUserProfile(@PathVariable long id, @ModelAttribute User user, RedirectAttributes redirectAttributes) {
        try {
            userService.updateUser(id, user);
            redirectAttributes.addAttribute("id", id);
            return "redirect:/users/{id}"; // Redirects to user profile
        } catch (UserNotFoundException e) {
            redirectAttributes.addAttribute("message", "User not found, please try again");
            return "redirect:/users/{id}/edit"; // Redirects back to edit form with error message
        }

    }

    // Show delete confirmation page
    @GetMapping("/{id}/delete")
    public String ShowDeleteConfirmation(@PathVariable long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "delete-confirmation"; // Returns delete-confirmation.html
    }

    // Handle delete user submission
    @PostMapping("/{id}/delete")
    public String deleteUser(@PathVariable long id, RedirectAttributes redirectAttributes) {
        try {
            userService.deleteUser(id);
            redirectAttributes.addAttribute("message", "User deleted successfully");
            return "redirect:/users/login"; // Redirects to login.html
        } catch (Exception e) {
            redirectAttributes.addAttribute("message", "An error occured while deleting your account, please try again later");
            return "redirect:/users/{id}"; // Redirects back to profile
        }
    }


    // Exception handler methods
    @ExceptionHandler(UserNotFoundException.class)
    public String handleUserNotFoundException(UserNotFoundException e, Model model) {
        model.addAttribute("error", e.getMessage());
        return "login"; // Redirect to login with error message
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public String handleInvalidCredentialsException(InvalidCredentialsException e, Model model) {
        model.addAttribute("error", e.getMessage());
        return "login"; // Redirect to login with error message
    }
}