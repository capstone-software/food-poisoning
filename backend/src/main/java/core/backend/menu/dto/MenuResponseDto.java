package core.backend.menu.dto;

import core.backend.menu.domain.Menu;
import lombok.Getter;

@Getter
public class MenuResponseDto {
    private Long id;
    private String name;
    private String content;
    private int price;

    public MenuResponseDto(Menu entity) {
        id = entity.getId();
        name = entity.getName();
        content = entity.getContent();
        price = entity.getPrice();
    }
}
