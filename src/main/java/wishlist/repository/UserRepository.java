package wishlist.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import wishlist.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
