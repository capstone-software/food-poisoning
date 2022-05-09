package core.backend.myRestaurant;

import core.backend.global.dto.DataResponse;
import core.backend.myRestaurant.domain.MyRestaurant;
import core.backend.myRestaurant.dto.MyRestaurantResponseDto;
import core.backend.myRestaurant.dto.MyRestaurantSaveRequestDto;
import core.backend.myRestaurant.service.MyRestaurantService;
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
public class MyRestaurantController {

    private final MyRestaurantService myRestaurantService;

    @GetMapping("/my-restaurant/{id}")
    public ResponseEntity<MyRestaurantResponseDto> findByIdV1(
            @PathVariable Long id) {
        MyRestaurant myRestaurant = myRestaurantService.findByIdOrThrow(id);
        return ResponseEntity.ok(new MyRestaurantResponseDto(myRestaurant));
    }

    @GetMapping("/my-restaurants")
    public ResponseEntity<DataResponse> findAllV1(
            @PageableDefault Pageable pageable) {
        List<MyRestaurantResponseDto> result = myRestaurantService.findAll(pageable)
                .stream()
                .map(MyRestaurantResponseDto::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(DataResponse.builder().count(result.size()).data(result).build());
    }

    @PostMapping("/my-restaurant")
    public ResponseEntity<MyRestaurantResponseDto> saveV1(
            @RequestBody MyRestaurantSaveRequestDto dto) {
        Long id = myRestaurantService.save(dto.toEntity());
        MyRestaurant myRestaurant = myRestaurantService.findByIdOrThrow(id);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new MyRestaurantResponseDto(myRestaurant));
    }

    @PostMapping("/my-restaurants")
    public ResponseEntity<DataResponse> saveListV1(
            @RequestBody List<MyRestaurantSaveRequestDto> dtoList) {
        List<Long> savedIdList = dtoList.stream()
                .map(dto -> myRestaurantService.save(dto.toEntity()))
                .collect(Collectors.toList());

        List<MyRestaurantResponseDto> result = savedIdList.stream()
                .map(id -> new MyRestaurantResponseDto(myRestaurantService.findByIdOrThrow(id)))
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(DataResponse.builder().count(result.size()).data(result).build());
    }

    @DeleteMapping("/my-restaurant/{id}")
    public HttpStatus deleteByIdV1(
            @PathVariable Long id) {
        myRestaurantService.deleteById(id);
        return HttpStatus.OK;
    }
}
