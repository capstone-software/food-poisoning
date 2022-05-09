package core.backend;

import core.backend.member.domain.Member;
import core.backend.member.domain.Role;
import core.backend.member.service.MemberService;
import core.backend.menu.domain.Menu;
import core.backend.myRestaurant.domain.MyRestaurant;
import core.backend.restaurant.domain.Location;
import core.backend.restaurant.domain.Restaurant;
import core.backend.restaurant.service.RestaurantService;
import core.backend.restaurantTag.domain.RestaurantTag;
import core.backend.tag.domain.Tag;
import core.backend.tag.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Component
@Profile("local")
@RequiredArgsConstructor
public class initDatabase {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.init(50);
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;

        private final TagService tagService;
        private final MemberService memberService;
        private final RestaurantService restaurantService;

        private final List<Long> memberIdList = new ArrayList<>();
        private final List<Long> restaurantIdList = new ArrayList<>();
        private final List<Long> tagIdList = new ArrayList<>();

        public void init(int count) {
            for (int i = 0; i < count; i++) {
                generateMember(i);
                generateRestaurant(i);
                generateTag(i);
                generateMyRestaurant(i, 3);
                generateRestaurantTag(i, 5);
            }
        }

        private void generateMember(int i) {
            Member member = Member.builder()
                    .nickname("nickname" + i)
                    .email("email" + i + "@gmail.com")
                    .role(Role.USER)
                    .password("password" + i)
                    .build();
            em.persist(member);
            memberIdList.add(member.getId());
        }

        private void generateRestaurant(int i) {
            Restaurant restaurant = Restaurant.builder()
                    .name("name" + i)
                    .description("description" + i)
                    .location(Location.builder()
                            .latitude(Double.parseDouble(String.valueOf(i)))
                            .longitude(Double.parseDouble(String.valueOf(i)))
                            .build())
                    .build();

            em.persist(restaurant);
            restaurantIdList.add(restaurant.getId());
            generateMenu(restaurant.getId(), i);
        }

        private void generateTag(int i) {
            Tag tag = Tag.builder()
                    .name("tag" + i)
                    .build();
            em.persist(tag);
            tagIdList.add(tag.getId());
        }

        private void generateMyRestaurant(int i, int count) {
            for (int j = 0; j < count; j++) {
                int restaurantId = (int) (Math.random() * 100) % restaurantIdList.size();
                MyRestaurant myRestaurant = MyRestaurant.builder()
                        .memberId(memberIdList.get(i))
                        .restaurantId(restaurantIdList.get(restaurantId))
                        .build();
                em.persist(myRestaurant);
            }

        }

        private void generateRestaurantTag(int i, int count) {
            for (int j = 0; j < count; j++) {
                int tagId = (int) (Math.random() * 100) % tagIdList.size();
                RestaurantTag restaurantTag = RestaurantTag.builder()
                        .restaurantId(restaurantIdList.get(i))
                        .tagId(tagIdList.get(tagId))
                        .build();
                em.persist(restaurantTag);
            }
        }

        private void generateMenu(Long id, int i) {
            Menu menu = Menu.builder()
                    .restaurant(restaurantService.findByIdOrThrow(id))
                    .name("name" + i)
                    .content("content" + i)
                    .price(i * 1000)
                    .build();
            em.persist(menu);
        }
    }
}
