package wishlist.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import wishlist.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
