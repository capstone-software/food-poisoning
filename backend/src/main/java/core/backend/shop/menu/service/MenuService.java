package core.backend.shop.menu.service;

import core.backend.shop.menu.domain.Menu;
import core.backend.shop.menu.exception.MenuNotFoundException;
import core.backend.shop.menu.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenuService {

    private final MenuRepository menuRepository;

    @Transactional
    public Long save(Menu menu) {
        menuRepository.save(menu);
        return menu.getId();
    }

    @Transactional
    public void deleteById(Long id) {
        menuRepository.deleteById(id);
    }

    public Menu findByIdOrThrow(Long id) {
        return menuRepository.findById(id)
                .orElseThrow(MenuNotFoundException::new);
    }

    public Page<Menu> findAll(Pageable pageable) {
        return menuRepository.findAll(pageable);
    }
}
