package core.backend.flask.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ModelResultResponseDto {
    private List<Float> floatList;

    public ModelResultResponseDto(List<Float> floatList) {
        this.floatList = floatList;
    }
}
