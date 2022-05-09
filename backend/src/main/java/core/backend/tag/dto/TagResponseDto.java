package core.backend.tag.dto;

import core.backend.tag.domain.Tag;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TagResponseDto {
    private Long id;
    private String name;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

    public TagResponseDto(Tag entity) {
        id = entity.getId();
        name = entity.getName();

        updatedAt = entity.getUpdatedAt();
        createdAt = entity.getCreatedAt();
    }
}
