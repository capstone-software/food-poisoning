package core.backend.flask.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ModelResultRequestDto {
    // 평균 기온
    private float avgTa;
    // 최고 기온
    private float highTa;
    // 최저 기온
    private float lowTa;
    // 평균 습도
    private float avgRhm;
    // 강수량
    private float sumRn;
    // 평균 풍속
    private float avgWs;

    public ModelResultRequestDto(float avgTa, float highTa, float lowTa, float avgRhm, float sumRn, float avgWs) {
        this.avgTa = avgTa;
        this.highTa = highTa;
        this.lowTa = lowTa;
        this.avgRhm = avgRhm;
        this.sumRn = sumRn;
        this.avgWs = avgWs;
    }

    public List<Float> toList() {
        return Arrays.asList(
                avgTa, highTa, lowTa, avgRhm, sumRn, avgWs);
    }
}
