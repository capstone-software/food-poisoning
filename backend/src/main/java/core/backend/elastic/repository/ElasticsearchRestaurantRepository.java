package core.backend.elastic.repository;

import core.backend.shop.restaurant.domain.Restaurant;
import core.backend.shop.restaurant.repository.RestaurantSearchRepository;
import org.springframework.stereotype.Repository;

public interface ElasticsearchRestaurantRepository extends ElasticsearchRepository<Restaurant, Long>, RestaurantSearchRepository {
}
