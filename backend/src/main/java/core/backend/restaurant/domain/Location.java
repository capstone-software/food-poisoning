package core.backend.restaurant.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Location {
    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;
}
