package core.backend.restaurant.dto;

import core.backend.menu.dto.MenuResponseDto;
import core.backend.restaurant.domain.Location;
import core.backend.restaurant.domain.Restaurant;
import core.backend.tag.dto.TagResponseDto;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class RestaurantWithTagResponseDto {
    private Long id;
    private String name;
    private String description;
    private Location location;

    private List<MenuResponseDto> menuList;

    private List<TagResponseDto> tagList;

    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

    public RestaurantWithTagResponseDto(Restaurant entity, List<TagResponseDto> tagResponseList) {
        id = entity.getId();
        name = entity.getName();
        description = entity.getDescription();
        location = entity.getLocation();

        menuList = entity.getMenuList().stream()
                .map(MenuResponseDto::new)
                .collect(Collectors.toList());
        tagList = tagResponseList;

        updatedAt = entity.getUpdatedAt();
        createdAt = entity.getCreatedAt();
    }
}
