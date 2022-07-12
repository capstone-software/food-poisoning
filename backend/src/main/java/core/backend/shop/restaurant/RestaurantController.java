package core.backend.shop.restaurant;

import core.backend.global.dto.DataResponse;
import core.backend.shop.restaurant.domain.Restaurant;
import core.backend.shop.restaurant.dto.*;
import core.backend.shop.restaurant.service.RestaurantService;
import core.backend.restaurantTag.service.RestaurantTagService;
import core.backend.shop.tag.dto.TagResponseDto;
import core.backend.shop.tag.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final RestaurantTagService restaurantTagService;
    private final TagService tagService;

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<RestaurantResponseDto> findByIdV1(
            @PathVariable Long id,
            @PageableDefault Pageable pageable) {
        Restaurant restaurant = restaurantService.findByIdOrThrow(id);
        return ResponseEntity.ok(new RestaurantResponseDto(restaurant));
    }

    @GetMapping("/restaurants")
    public ResponseEntity<DataResponse> findAllV1(
            @PageableDefault Pageable pageable) {
        List<RestaurantResponseDto> result = restaurantService.findAll(pageable).stream()
                .map(RestaurantResponseDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(DataResponse.builder().count(result.size()).data(result).build());
    }

    @GetMapping("/restaurant/search")
    public ResponseEntity<DataResponse> searchV1(
            @RequestParam(defaultValue = "") String name,
            @RequestParam(defaultValue = "") String description,
            @PageableDefault Pageable pageable) {
        List<RestaurantInfoResponseDto> result = restaurantService.search(
                        new RestaurantSearchCondition(name, description), pageable)
                .stream()
                .map(RestaurantInfoResponseDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(DataResponse.builder().count(result.size()).data(result).build());
    }

    @PostMapping("/restaurant")
    public ResponseEntity<RestaurantInfoResponseDto> saveV1(
            @RequestBody RestaurantSaveRequestDto dto) {
        Long id = restaurantService.save(dto.toEntity());
        Restaurant restaurant = restaurantService.findByIdOrThrow(id);
        return ResponseEntity.status(CREATED)
                .body(new RestaurantInfoResponseDto(restaurant));
    }

    @DeleteMapping("/restaurant/{id}")
    public HttpStatus deleteById(
            @PathVariable Long id) {
        restaurantService.deleteById(id);
        return OK;
    }
}
