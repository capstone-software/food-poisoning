package core.backend.tag.service;

import core.backend.tag.domain.Tag;
import core.backend.tag.exception.TagNotFoundException;
import core.backend.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TagService {

    private final TagRepository tagRepository;

    @Transactional
    public Long save(Tag tag) {
        tagRepository.save(tag);
        return tag.getId();
    }

    @Transactional
    public void deleteById(Long id) {
        tagRepository.deleteById(id);
    }

    public Tag findByIdOrThrow(Long id) {
        return tagRepository.findById(id)
                .orElseThrow(TagNotFoundException::new);
    }

    public Page<Tag> findAll(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }
}
