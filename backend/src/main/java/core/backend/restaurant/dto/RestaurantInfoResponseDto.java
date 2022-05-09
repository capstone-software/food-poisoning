package core.backend.restaurant.dto;

import core.backend.restaurant.domain.Location;
import core.backend.restaurant.domain.Restaurant;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RestaurantInfoResponseDto {
    private Long id;
    private String name;
    private String description;
    private Location location;

    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

    public RestaurantInfoResponseDto(Restaurant entity) {
        id = entity.getId();
        name = entity.getName();
        description = entity.getDescription();
        location = entity.getLocation();

        updatedAt = entity.getUpdatedAt();
        createdAt = entity.getCreatedAt();
    }
}
