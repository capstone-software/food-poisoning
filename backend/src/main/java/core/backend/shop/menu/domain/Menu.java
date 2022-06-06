package core.backend.shop.menu.domain;

import core.backend.global.domain.BaseTimeEntity;
import core.backend.shop.restaurant.domain.Restaurant;
import core.backend.shop.tag.domain.Tag;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Menu extends BaseTimeEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Tag> tagList = new ArrayList<>();

    //--연관관계 로직--//
    private void setRestaurant(Restaurant restaurant) {
        if (this.restaurant != null) {
            this.restaurant.getMenuList().remove(this);
        }
        this.restaurant = restaurant;
        restaurant.getMenuList().add(this);
    }

    //-비즈니스 로직-//
    @Builder
    public Menu(String name, String content, int price, Restaurant restaurant) {
        this.name = name;
        this.content = content;
        this.price = price;
        setRestaurant(restaurant);
    }
}
