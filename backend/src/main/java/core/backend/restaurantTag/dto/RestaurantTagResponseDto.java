package core.backend.restaurantTag.dto;

import core.backend.restaurantTag.domain.RestaurantTag;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RestaurantTagResponseDto {
    private Long id;
    private Long tagId;
    private Long restaurantId;

    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

    public RestaurantTagResponseDto(RestaurantTag entity) {
        id = entity.getId();
        tagId = entity.getTagId();
        restaurantId = entity.getRestaurantId();

        updatedAt = entity.getUpdatedAt();
        createdAt = entity.getCreatedAt();
    }
}
