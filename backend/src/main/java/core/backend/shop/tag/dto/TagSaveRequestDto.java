package core.backend.shop.tag.dto;

import core.backend.shop.menu.domain.Menu;
import core.backend.shop.tag.domain.Category;
import core.backend.shop.tag.domain.Tag;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TagSaveRequestDto {

    private Long menuId;
    private Category category;

    public TagSaveRequestDto(Long menuId, Category category) {
        this.menuId = menuId;
        this.category = category;
    }

    public Tag toEntity(Menu menu) {
        return Tag.builder()
                .menu(menu)
                .category(category)
                .build();
    }
}
