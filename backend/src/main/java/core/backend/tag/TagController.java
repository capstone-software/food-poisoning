package core.backend.tag;

import core.backend.global.dto.DataResponse;
import core.backend.tag.domain.Tag;
import core.backend.tag.dto.TagResponseDto;
import core.backend.tag.dto.TagSaveRequestDto;
import core.backend.tag.service.TagService;
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
public class TagController {

    private final TagService tagService;

    @GetMapping("/tag/{id}")
    public ResponseEntity<TagResponseDto> findByIdV1(
            @PathVariable Long id) {
        Tag tag = tagService.findByIdOrThrow(id);
        return ResponseEntity.ok(new TagResponseDto(tag));
    }

    @GetMapping("/tags")
    public ResponseEntity<DataResponse> findAllV1(
            @PageableDefault Pageable pageable) {
        List<TagResponseDto> result = tagService.findAll(pageable)
                .stream()
                .map(TagResponseDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(DataResponse.builder().count(result.size()).data(result).build());
    }

    @PostMapping("/tag")
    public ResponseEntity<TagResponseDto> saveV1(
            @RequestBody TagSaveRequestDto dto) {
        Long id = tagService.save(dto.toEntity());
        Tag tag = tagService.findByIdOrThrow(id);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new TagResponseDto(tag));
    }

    @PostMapping("/tags")
    public ResponseEntity<DataResponse> saveListV1(
            @RequestBody List<TagSaveRequestDto> dtoList) {
        List<Long> savedIdList = dtoList.stream()
                .map(dto -> tagService.save(dto.toEntity()))
                .collect(Collectors.toList());

        List<TagResponseDto> result = savedIdList.stream()
                .map(id -> new TagResponseDto(tagService.findByIdOrThrow(id)))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(DataResponse.builder().count(result.size()).data(result).build());
    }

    @DeleteMapping("/tag/{id}")
    public HttpStatus deleteByIdV1(
            @PathVariable Long id) {
        tagService.deleteById(id);
        return HttpStatus.OK;
    }
}
