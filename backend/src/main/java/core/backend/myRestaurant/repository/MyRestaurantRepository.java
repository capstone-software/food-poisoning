package core.backend.myRestaurant.repository;

import core.backend.myRestaurant.domain.MyRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyRestaurantRepository extends JpaRepository<MyRestaurant, Long> {
}
