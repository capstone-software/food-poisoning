package core.backend.restaurantTag.domain;

import core.backend.global.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RestaurantTag extends BaseTimeEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Long tagId;

    @Column(nullable = false)
    private Long restaurantId;

    //-비즈니스 로직-//
    @Builder
    public RestaurantTag(Long tagId, Long restaurantId) {
        this.tagId = tagId;
        this.restaurantId = restaurantId;
    }
}
