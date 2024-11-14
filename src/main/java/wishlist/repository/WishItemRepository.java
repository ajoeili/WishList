package wishlist.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import wishlist.model.WishItem;

import java.util.List;

@Repository
public interface WishItemRepository extends CrudRepository<WishItem, Long> {
    List<WishItem> findByWishListId(Long wishListId);
}
