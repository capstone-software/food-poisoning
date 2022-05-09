package core.backend.restaurantTag.dto;

import core.backend.restaurantTag.domain.RestaurantTag;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RestaurantTagSaveRequestdto {
    private Long tagId;
    private Long restaurantId;

    public RestaurantTagSaveRequestdto(Long tagId, Long restaurantId) {
        this.tagId = tagId;
        this.restaurantId = restaurantId;
    }

    public RestaurantTag toEntity() {
        return RestaurantTag.builder()
                .tagId(tagId)
                .restaurantId(restaurantId)
                .build();
    }
}
