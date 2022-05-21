package core.backend.flask.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ModelResultResponseDto {
    private List<Float> floatList;

    public ModelResultResponseDto(List<Float> floatList) {
        this.floatList = floatList;
    }
}
