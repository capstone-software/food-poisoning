package core.backend.shop.menu;

import core.backend.global.dto.DataResponse;
import core.backend.shop.menu.domain.Menu;
import core.backend.shop.menu.dto.MenuResponseDto;
import core.backend.shop.menu.dto.MenuSaveRequestDto;
import core.backend.shop.menu.service.MenuService;
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
public class MenuController {

    private final MenuService menuService;

    @GetMapping("/menu/{id}")
    public ResponseEntity<MenuResponseDto> findById(
            @PathVariable Long id) {
        Menu menu = menuService.findByIdOrThrow(id);
        return ResponseEntity.ok(new MenuResponseDto(menu));
    }

    @GetMapping("/menus")
    public ResponseEntity<DataResponse> findAll(
            @PageableDefault Pageable pageable) {
        List<MenuResponseDto> result = menuService.findAll(pageable).stream()
                .map(MenuResponseDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(DataResponse.builder().count(result.size()).data(result).build());
    }

    @PostMapping("/menu")
    public ResponseEntity<MenuResponseDto> save(
            @RequestBody MenuSaveRequestDto dto) {
        Long id = menuService.save(dto.toEntity());
        Menu menu = menuService.findByIdOrThrow(id);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new MenuResponseDto(menu));
    }

    @PostMapping("/menus")
    public ResponseEntity<DataResponse> saveList(
            @RequestBody List<MenuSaveRequestDto> dtoList) {
        List<Long> savedIdList = dtoList.stream()
                .map(dto -> menuService.save(dto.toEntity()))
                .collect(Collectors.toList());

        List<MenuResponseDto> result = savedIdList.stream()
                .map(id -> new MenuResponseDto(menuService.findByIdOrThrow(id)))
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(DataResponse.builder().count(result.size()).data(result).build());
    }

    @DeleteMapping("/menu/{id}")
    public HttpStatus deleteById(
            @PathVariable Long id) {
        menuService.deleteById(id);
        return HttpStatus.OK;
    }
}
