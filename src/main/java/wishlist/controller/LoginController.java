package wishlist.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import wishlist.exception.InvalidCredentialsException;
import wishlist.exception.UserNotFoundException;
import wishlist.model.User;
import wishlist.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Controller
@RequestMapping("/wishlist")
public class LoginController {

    private final UserService userService;
    private static final Logger log = LoggerFactory.getLogger(UserController.class); // Just used this to debug


    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    // Show landing page
    @GetMapping("")
    public String showLandingPage() {
        return "wishlist-app"; // Returns wishlist-app.html
    }

    // Show login form
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login"; // Returns login.html
    }

    // Handle login submission
    @PostMapping("/login")
    public String loginUser(@ModelAttribute User user, Model model, RedirectAttributes redirectAttributes) {
        try {
            log.info("Attempting to authenticate user with username: {}", user.getUsername());
            User authenticatedUser = userService.authenticate(user.getUsername(), user.getPassword());
            log.info("User authenticated successfully: {}", authenticatedUser.getUserId());

            redirectAttributes.addAttribute("id", authenticatedUser.getUserId());
            return "redirect:/users/{id}"; // Redirects to user profile
        } catch (UserNotFoundException e) {
            log.error("User not found: {}", user.getUsername(), e);
            model.addAttribute("message", "User not found, please try again");
            return "login"; // Stays on login page
        } catch (InvalidCredentialsException e) {
            log.error("Invalid credentials for user: {}", user.getUsername(), e);
            model.addAttribute("message", "Invalid password, please try again");
            return "login"; // Stays on login page
        }
    }
}
