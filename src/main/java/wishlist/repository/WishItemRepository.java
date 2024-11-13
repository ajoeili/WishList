package wishlist.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import wishlist.model.WishItem;

@Repository
public interface WishItemRepository extends CrudRepository<WishItem, Long> {
}
