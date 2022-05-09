package core.backend.restaurant.repository;

import core.backend.restaurant.domain.Restaurant;
import core.backend.restaurant.dto.RestaurantSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RestaurantSearchRepository {
    Page<Restaurant> search(RestaurantSearchCondition condition, Pageable pageable);
}
