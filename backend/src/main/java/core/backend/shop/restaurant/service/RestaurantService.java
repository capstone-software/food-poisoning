package core.backend.shop.restaurant.service;

import core.backend.shop.restaurant.domain.Restaurant;
import core.backend.shop.restaurant.dto.RestaurantSearchCondition;
import core.backend.shop.restaurant.exception.RestaurantNotFoundException;
import core.backend.shop.restaurant.repository.RestaurantRepository;
import core.backend.shop.restaurant.repository.RestaurantSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantSearchRepository restaurantSearchRepository;

    @Transactional
    public Long save(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
        return restaurant.getId();
    }

    @Transactional
    public void deleteById(Long id) {
        restaurantRepository.deleteById(id);
    }

    public Restaurant findByIdOrThrow(Long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(RestaurantNotFoundException::new);
    }

    public Page<Restaurant> findAll(Pageable pageable) {
        return restaurantRepository.findAll(pageable);
    }

    public Page<Restaurant> search(RestaurantSearchCondition condition, Pageable pageable) {
        return restaurantSearchRepository.search(condition, pageable);
    }
}
