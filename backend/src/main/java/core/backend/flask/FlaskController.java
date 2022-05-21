package core.backend.flask;

import core.backend.flask.dto.ModelDataRequestDto;
import core.backend.flask.dto.ModelResultRequestDto;
import core.backend.flask.dto.ModelResultResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class FlaskController {

    private final RestTemplate restTemplate;

    @Value("${flask.url}")
    private String url;

    @PostMapping("/model-predict")
    public ResponseEntity<ModelResultResponseDto> predictV1(
            @RequestBody ModelResultRequestDto dto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ModelDataRequestDto requestDto = new ModelDataRequestDto(dto.toList());

        ModelResultResponseDto result = restTemplate.postForObject(
                url + "/model-predict",
                new HttpEntity<>(requestDto, headers),
                ModelResultResponseDto.class);
        return ResponseEntity.ok(result);
    }
}
