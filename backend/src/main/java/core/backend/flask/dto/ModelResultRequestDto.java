package core.backend.flask.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ModelResultRequestDto {
    private float avgTa;
    private float avgPa;
    private float argPv;
    private float avgTd;
    private float avgRhm;
    private float sumRn;
    private float avgWs;
    private float sumSsHr;
    private float isolation;

    public ModelResultRequestDto(float avgTa, float avgPa, float argPv, float avgTd, float avgRhm, float sumRn, float avgWs, float sumSsHr, float isolation) {
        this.avgTa = avgTa;
        this.avgPa = avgPa;
        this.argPv = argPv;
        this.avgTd = avgTd;
        this.avgRhm = avgRhm;
        this.sumRn = sumRn;
        this.avgWs = avgWs;
        this.sumSsHr = sumSsHr;
        this.isolation = isolation;
    }
}
