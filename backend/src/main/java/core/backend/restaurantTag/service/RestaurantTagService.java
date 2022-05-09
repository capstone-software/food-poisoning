package core.backend.restaurantTag.service;

import core.backend.restaurantTag.domain.RestaurantTag;
import core.backend.restaurantTag.exception.RestaurantTagNotFoundException;
import core.backend.restaurantTag.repository.RestaurantTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RestaurantTagService {

    private final RestaurantTagRepository restaurantTagRepository;

    @Transactional
    public Long save(RestaurantTag restaurantTag) {
        restaurantTagRepository.save(restaurantTag);
        return restaurantTag.getId();
    }

    @Transactional
    public void deleteById(Long id) {
        restaurantTagRepository.deleteById(id);
    }

    public RestaurantTag findByIdOrThrow(Long id) {
        return restaurantTagRepository.findById(id)
                .orElseThrow(RestaurantTagNotFoundException::new);
    }

    public Page<RestaurantTag> findAll(Pageable pageable) {
        return restaurantTagRepository.findAll(pageable);
    }
}
