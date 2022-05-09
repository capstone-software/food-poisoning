package core.backend.myRestaurant.service;

import core.backend.myRestaurant.domain.MyRestaurant;
import core.backend.myRestaurant.exception.MyRestaurantNotFoundException;
import core.backend.myRestaurant.repository.MyRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MyRestaurantService {

    private final MyRestaurantRepository myRestaurantRepository;

    @Transactional
    public Long save(MyRestaurant myRestaurant) {
        myRestaurantRepository.save(myRestaurant);
        return myRestaurant.getId();
    }

    @Transactional
    public void deleteById(Long id) {
        myRestaurantRepository.deleteById(id);
    }

    public MyRestaurant findByIdOrThrow(Long id) {
        return myRestaurantRepository.findById(id)
                .orElseThrow(MyRestaurantNotFoundException::new);
    }

    public Page<MyRestaurant> findAll(Pageable pageable) {
        return myRestaurantRepository.findAll(pageable);
    }
}
