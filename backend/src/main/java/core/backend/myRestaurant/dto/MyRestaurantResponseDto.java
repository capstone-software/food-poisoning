package core.backend.myRestaurant.dto;

import core.backend.myRestaurant.domain.MyRestaurant;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MyRestaurantResponseDto {
    private Long id;
    private Long memberId;
    private Long restaurantId;

    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

    public MyRestaurantResponseDto(MyRestaurant entity) {
        id = entity.getId();
        memberId = entity.getMemberId();
        restaurantId = entity.getRestaurantId();

        updatedAt = entity.getUpdatedAt();
        createdAt = entity.getCreatedAt();
    }
}
