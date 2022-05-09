package core.backend.menu.dto;

import core.backend.menu.domain.Menu;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuSaveRequestDto {
    private String name;
    private String content;
    private int price;

    public MenuSaveRequestDto(String name, String content, int price) {
        this.name = name;
        this.content = content;
        this.price = price;
    }

    public Menu toEntity() {
        return Menu.builder()
                .name(name)
                .content(content)
                .price(price)
                .build();
    }
}
