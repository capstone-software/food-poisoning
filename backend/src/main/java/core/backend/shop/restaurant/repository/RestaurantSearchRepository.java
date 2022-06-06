package core.backend.shop.restaurant.repository;

import core.backend.shop.restaurant.domain.Restaurant;
import core.backend.shop.restaurant.dto.RestaurantSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RestaurantSearchRepository {
    Page<Restaurant> search(RestaurantSearchCondition condition, Pageable pageable);
}
