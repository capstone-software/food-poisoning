package core.backend.restaurant.dto;

import core.backend.restaurant.domain.Location;
import core.backend.restaurant.domain.Restaurant;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RestaurantSaveRequestDto {
    private String name;
    private String description;
    private Location location;

    public RestaurantSaveRequestDto(String name, String description, Location location) {
        this.name = name;
        this.description = description;
        this.location = location;
    }

    public Restaurant toEntity() {
        return Restaurant.builder()
                .name(name)
                .description(description)
                .location(location)
                .build();
    }
}
