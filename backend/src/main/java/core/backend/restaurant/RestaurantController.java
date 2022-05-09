package core.backend.restaurant;

import core.backend.global.dto.DataResponse;
import core.backend.restaurant.domain.Restaurant;
import core.backend.restaurant.dto.RestaurantInfoResponseDto;
import core.backend.restaurant.dto.RestaurantResponseDto;
import core.backend.restaurant.dto.RestaurantSaveRequestDto;
import core.backend.restaurant.service.RestaurantService;
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

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<RestaurantResponseDto> findByIdV1(
            @PathVariable Long id) {
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
