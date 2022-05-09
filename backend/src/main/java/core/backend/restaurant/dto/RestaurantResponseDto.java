package core.backend.restaurant.dto;

import core.backend.menu.dto.MenuResponseDto;
import core.backend.restaurant.domain.Location;
import core.backend.restaurant.domain.Restaurant;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class RestaurantResponseDto {
    private Long id;
    private String name;
    private String description;
    private Location location;

    private List<MenuResponseDto> menuList;

    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

    public RestaurantResponseDto(Restaurant entity) {
        id = entity.getId();
        name = entity.getName();
        description = entity.getDescription();
        location = entity.getLocation();

        menuList = entity.getMenuList().stream()
                .map(MenuResponseDto::new)
                .collect(Collectors.toList());

        updatedAt = entity.getUpdatedAt();
        createdAt = entity.getCreatedAt();
    }
}
