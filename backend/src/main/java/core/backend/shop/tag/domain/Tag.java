package core.backend.shop.tag.domain;

import core.backend.global.domain.BaseTimeEntity;
import core.backend.shop.menu.domain.Menu;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tag extends BaseTimeEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    //-연관관계 로직-//
    public void setMenu(Menu menu) {
        if (this.menu != null) {
            this.menu.getTagList().remove(this);
        }
        this.menu = menu;
        menu.getTagList().add(this);
    }

    //-비즈니스 로직-//
    @Builder
    public Tag(Category category, Menu menu) {
        this.name = category.getContent();
        setMenu(menu);
    }
}
