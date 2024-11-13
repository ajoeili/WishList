package wishlist.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import wishlist.model.WishList;

import java.util.List;

@Repository
public interface WishListRepository extends CrudRepository<WishList, Long> {
        List<WishList> findByUserId(Long userId);  // Finds all wishlists for a specific user
}


