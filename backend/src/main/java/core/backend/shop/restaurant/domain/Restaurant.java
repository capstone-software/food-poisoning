package core.backend.shop.restaurant.domain;

import core.backend.global.domain.BaseTimeEntity;
import core.backend.shop.menu.domain.Menu;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Document(indexName = "i_restaurant")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Restaurant extends BaseTimeEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Embedded
    private Location location;

    @Embedded
    private Address address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private List<Menu> menuList = new ArrayList<>();

    @Builder
    public Restaurant(String name, String description, Location location, Address address) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.address = address;
    }
}
