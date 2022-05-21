package core.backend.flask.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ModelDataRequestDto {
    private List<Float> data;

    public ModelDataRequestDto(List<Float> data) {
        this.data = data;
    }
}
