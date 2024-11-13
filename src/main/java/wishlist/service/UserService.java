package wishlist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wishlist.exception.InvalidCredentialsException;
import wishlist.exception.UserNotFoundException;
import wishlist.model.User;
import wishlist.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    // Constructor-base injection
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Create a new user
    public void createUser(User user) {
        userRepository.save(user);
    }

    // Get a user by ID
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    // Authenticate user by username and password
    public User authenticate(String username, String password) {
        Iterable<User> users = userRepository.findAll();
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                if (user.getPassword().equals(password)) {
                    return user;
                } else {
                    throw new InvalidCredentialsException("Invalid password");
                }
            }
        }
        throw new UserNotFoundException("User not found");
    }

    // Update user information
    public User updateUser(Long userId, User updatedUser) {
        if (userId == null || updatedUser == null) {
            throw new UserNotFoundException("User not found");
        }
        User existingUser = getUserById(userId);
        if (existingUser == null) {
            throw new UserNotFoundException("User not found");
        }
        if (updatedUser.getUsername() != null) {
            existingUser.setUsername(updatedUser.getUsername());
        }
        if (updatedUser.getPassword() != null) {
            existingUser.setPassword(updatedUser.getPassword());
        }
        if (updatedUser.getEmail() != null) {
            existingUser.setEmail(updatedUser.getEmail());
        }
        return userRepository.save(existingUser);
    }

    // Delete user
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
