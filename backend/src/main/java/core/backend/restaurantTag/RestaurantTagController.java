package core.backend.restaurantTag;

import core.backend.global.dto.DataResponse;
import core.backend.restaurantTag.domain.RestaurantTag;
import core.backend.restaurantTag.dto.RestaurantTagResponseDto;
import core.backend.restaurantTag.dto.RestaurantTagSaveRequestdto;
import core.backend.restaurantTag.service.RestaurantTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RestaurantTagController {

    private final RestaurantTagService restaurantTagService;

    @GetMapping("/restaurant-tag/{id}")
    public ResponseEntity<RestaurantTagResponseDto> findByIdV1(
            @PathVariable Long id) {
        RestaurantTag restaurantTag = restaurantTagService.findByIdOrThrow(id);
        return ResponseEntity.ok(new RestaurantTagResponseDto(restaurantTag));
    }

    @GetMapping("/restaurant-tags")
    public ResponseEntity<DataResponse> findAllV1(
            @PageableDefault Pageable pageable) {
        List<RestaurantTagResponseDto> result = restaurantTagService.findAll(pageable)
                .stream()
                .map(RestaurantTagResponseDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(DataResponse.builder().count(result.size()).data(result).build());
    }

    @PostMapping("/restaurant-tag")
    public ResponseEntity<RestaurantTagResponseDto> saveV1(
            @RequestBody RestaurantTagSaveRequestdto dto) {
        Long id = restaurantTagService.save(dto.toEntity());
        RestaurantTag restaurantTag = restaurantTagService.findByIdOrThrow(id);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new RestaurantTagResponseDto(restaurantTag));
    }

    @PostMapping("/restaurant-tags")
    public ResponseEntity<DataResponse> saveListV1(
            @RequestBody List<RestaurantTagSaveRequestdto> dtoList) {
        List<Long> savedIdList = dtoList.stream()
                .map(dto -> restaurantTagService.save(dto.toEntity()))
                .collect(Collectors.toList());

        List<RestaurantTagResponseDto> result = savedIdList.stream()
                .map(id -> new RestaurantTagResponseDto(restaurantTagService.findByIdOrThrow(id)))
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(DataResponse.builder().count(result.size()).data(result).build());
    }

    @DeleteMapping("/restaurant-tag/{id}")
    public HttpStatus deleteByIdV1(
            @PathVariable Long id) {
        restaurantTagService.deleteById(id);
        return HttpStatus.OK;
    }
}
