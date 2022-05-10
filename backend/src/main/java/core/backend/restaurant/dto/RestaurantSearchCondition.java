package core.backend.restaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RestaurantSearchCondition {
    private String name;
    private String description;
}
