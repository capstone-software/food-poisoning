package core.backend.shop.menu.dto;

import core.backend.shop.menu.domain.Menu;
import core.backend.shop.tag.dto.TagResponseDto;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class MenuResponseDto {
    private Long id;
    private String name;
    private String content;
    private int price;
    private List<TagResponseDto> tagList;

    public MenuResponseDto(Menu entity) {
        id = entity.getId();
        name = entity.getName();
        content = entity.getContent();
        price = entity.getPrice();
        tagList = entity.getTagList().stream()
                .map(TagResponseDto::new)
                .collect(Collectors.toList());
    }
}
