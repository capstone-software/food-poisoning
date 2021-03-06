package core.backend.restaurantTag.repository;

import core.backend.restaurantTag.domain.RestaurantTag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantTagRepository extends JpaRepository<RestaurantTag, Long> {
    Page<RestaurantTag> findByRestaurantId(Long restaurantId, Pageable pageable);
}
