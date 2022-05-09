package core.backend.tag.dto;

import core.backend.tag.domain.Tag;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TagSaveRequestDto {
    private String name;

    public TagSaveRequestDto(String name) {
        this.name = name;
    }

    public Tag toEntity() {
        return Tag.builder()
                .name(name)
                .build();
    }
}
