package core.backend.myRestaurant.dto;

import core.backend.myRestaurant.domain.MyRestaurant;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MyRestaurantSaveRequestDto {
    private Long memberId;
    private Long restaurantId;

    public MyRestaurantSaveRequestDto(Long memberId, Long restaurantId) {
        this.memberId = memberId;
        this.restaurantId = restaurantId;
    }

    public MyRestaurant toEntity() {
        return MyRestaurant.builder()
                .memberId(memberId)
                .restaurantId(restaurantId)
                .build();
    }
}
