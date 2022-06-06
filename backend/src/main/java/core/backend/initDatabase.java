package core.backend;

import core.backend.member.domain.Member;
import core.backend.member.domain.Role;
import core.backend.member.service.MemberService;
import core.backend.myRestaurant.domain.MyRestaurant;
import core.backend.restaurantTag.domain.RestaurantTag;
import core.backend.shop.menu.domain.Menu;
import core.backend.shop.restaurant.domain.Address;
import core.backend.shop.restaurant.domain.Location;
import core.backend.shop.restaurant.domain.Restaurant;
import core.backend.shop.restaurant.service.RestaurantService;
import core.backend.shop.tag.domain.Category;
import core.backend.shop.tag.domain.Tag;
import core.backend.shop.tag.service.TagService;
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
    private final InitShopService initShopService;

    @PostConstruct
    public void init() {
        initService.init(0);
        initShopService.init();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitShopService {
        private final EntityManager em;

        public void init() {
            hansot();
            issac();
            submeal();
            afterSchool();
        }

        private void afterSchool() {
            Restaurant restaurant = Restaurant.builder()
                    .name("수업이끝난오후")
                    .description("콩나물 불고기가 맛있는 수업이 끝난 오후 입니다.")
                    .location(Location.builder()
                            .latitude(36.8328612)
                            .longitude(127.175439)
                            .build())
                    .address(Address.builder()
                            .city("천안시")
                            .province("동남구")
                            .county("상명대길")
                            .build())
                    .build();
            em.persist(restaurant);

            Menu menu1 = Menu.builder()
                    .name("콩나물불고기+볶음밥")
                    .content("콩나물불고기 먹은 후에 볶아 먹는 밥까지")
                    .restaurant(restaurant)
                    .price(7000)
                    .build();
            em.persist(menu1);
            setTags(menu1, Category.MEET);
            setTags(menu1, Category.VEGETABLE);

            Menu menu2 = Menu.builder()
                    .name("대패삼겹살")
                    .content("얇은 삼겹, 대패 삼겹살 (150g)")
                    .restaurant(restaurant)
                    .price(4000)
                    .build();
            em.persist(menu2);
            setTags(menu2, Category.MEET);

            Menu menu3 = Menu.builder()
                    .name("냉동삼겹살")
                    .content("추억의 냉동 삼겹살 (150g)")
                    .restaurant(restaurant)
                    .price(6000)
                    .build();
            em.persist(menu3);
            setTags(menu3, Category.MEET);
        }

        private void submeal() {
            Restaurant restaurant = Restaurant.builder()
                    .name("SubMeal")
                    .description("멕시코,남미음식")
                    .location(Location.builder()
                            .latitude(36.8327979)
                            .longitude(127.175652)
                            .build())
                    .address(Address.builder()
                            .city("천안시")
                            .province("동남구")
                            .county("상명대길")
                            .build())
                    .build();
            em.persist(restaurant);

            Menu menu1 = Menu.builder()
                    .name("브리또 단품")
                    .content("내 입맛대로 맛을 고르는 브리또")
                    .restaurant(restaurant)
                    .price(3900)
                    .build();
            em.persist(menu1);
            setTags(menu1, Category.MEET);
            setTags(menu1, Category.COMPOUND);
            setTags(menu1, Category.SEAFOOD);
            setTags(menu1, Category.VEGETABLE);
            setTags(menu1, Category.EGG);
        }

        private void issac() {
            Restaurant restaurant = Restaurant.builder()
                    .name("이삭토스트 천안상명대점")
                    .description("이삭토스트 천안상명대점의 스마트주문 페이지입니다.")
                    .location(Location.builder()
                            .latitude(36.8323731)
                            .longitude(127.176503)
                            .build())
                    .address(Address.builder()
                            .city("천안시")
                            .province("동남구")
                            .county("상명대길")
                            .build())
                    .build();
            em.persist(restaurant);

            Menu menu1 = Menu.builder()
                    .name("트리플소세지 토스트")
                    .content("탱글하고 뽀득한 국내산 소세지(3개)와 달콤한 이삭토스트의 만남!")
                    .restaurant(restaurant)
                    .price(4500)
                    .build();
            em.persist(menu1);
            setTags(menu1, Category.MEET);
            setTags(menu1, Category.COMPOUND);

            Menu menu2 = Menu.builder()
                    .name("베이컨 포테이토 피자")
                    .content("쫄깃한 모짜렐라 치즈, 해쉬브라운과 베이컨의 만남!")
                    .restaurant(restaurant)
                    .price(4500)
                    .build();
            em.persist(menu2);
            setTags(menu2, Category.EGG);
            setTags(menu2, Category.MEET);
            setTags(menu2, Category.COMPOUND);

            Menu menu3 = Menu.builder()
                    .name("허니갈릭햄치즈")
                    .content("보리맥아추출물이 첨가되어 부드럽고 촉촉한 프리미엄 식빵에 진한 풍미가 있는 허니갈릭소스로 맛을 낸 토스트")
                    .restaurant(restaurant)
                    .price(3200)
                    .build();
            em.persist(menu3);
            setTags(menu3, Category.EGG);
            setTags(menu3, Category.MEET);
            setTags(menu3, Category.COMPOUND);

            Menu menu4 = Menu.builder()
                    .name("딥치즈 베이컨")
                    .content("짭조름한 베이컨과 쫄깃한 모짜렐라 치즈, 부드러운 치즈소스가 듬뿍 들어간 토스트")
                    .restaurant(restaurant)
                    .price(3700)
                    .build();
            em.persist(menu4);
            setTags(menu4, Category.EGG);
            setTags(menu4, Category.MEET);
            setTags(menu4, Category.COMPOUND);

            Menu menu5 = Menu.builder()
                    .name("그릴드 불갈비")
                    .content("불맛이 살아있는 그릴향 가득한 두툼한 패티로 고기 함량 UP! 브라운 브레드 프리미엄 토스트로 업그레이드 된 그릴드 불갈비 토스트")
                    .restaurant(restaurant)
                    .price(4100)
                    .build();
            em.persist(menu5);
            setTags(menu5, Category.EGG);
            setTags(menu5, Category.MEET);
            setTags(menu5, Category.COMPOUND);
            setTags(menu5, Category.VEGETABLE);
        }

        private void hansot() {
            Restaurant restaurant = Restaurant.builder()
                    .name("한솥도시 천안상명대점")
                    .description("처음부터 끝까지 성심을 다해 맛있게 만들겠습니다. 한솥 홈페이지에서 한솥메뉴에 들어가시면 보다 상세한 메뉴정보를 보실 수 있습니다. 점포로 전화 주시면 성심껏 설명해 드리겠습니다.내용 더보기")
                    .location(Location.builder()
                            .latitude(36.8328691)
                            .longitude(127.176213)
                            .build())
                    .address(Address.builder()
                            .city("천안시")
                            .province("동남구")
                            .county("상명대길")
                            .build())
                    .build();
            em.persist(restaurant);

            Menu menu1 = Menu.builder()
                    .name("치킨 제육")
                    .content("짭조름한 치킨가라아게와 매콤하면서도 부드러운 제육볶음으로 구성된 든든한 도시락입니다.")
                    .restaurant(restaurant)
                    .price(4700)
                    .build();
            em.persist(menu1);
            setTags(menu1, Category.MEET);
            setTags(menu1, Category.COMPOUND);

            Menu menu2 = Menu.builder()
                    .name("빅치킨마요")
                    .content("배부르게 먹고 싶은 날 즐겨보세요. 치킨마요에 밥(+70g), 치킨(+15g), 소스(+10g)까지 모두 빅으로 푸짐하게 담았습니다.")
                    .restaurant(restaurant)
                    .price(4100)
                    .build();
            em.persist(menu2);
            setTags(menu2, Category.MEET);
            setTags(menu2, Category.EGG);
            setTags(menu2, Category.COMPOUND);

            Menu menu3 = Menu.builder()
                    .name("돈까스 카레")
                    .content("두툼한 국내산 등심 돈까스와 누구도 흉내낼 수 없는 한결같은 맛을 고수해 온 한솥 카레를 함께 즐겨보세요.")
                    .restaurant(restaurant)
                    .price(4500)
                    .build();
            em.persist(menu3);
            setTags(menu3, Category.MEET);
            setTags(menu3, Category.COMPOUND);

            Menu menu4 = Menu.builder()
                    .name("소불고기 철판볶음밥")
                    .content("짭조름한 베이컨과 양파, 대파, 홍 피망을 넣어 불 맛을 잘 살렸습니다. 달콤한 소불고기와 담백한 지단 채 그리고 김 가루와 함께 풍미 가득한 불고기철판볶음밥을 즐겨보세요.")
                    .restaurant(restaurant)
                    .price(4700)
                    .build();
            em.persist(menu4);
            setTags(menu4, Category.MEET);
            setTags(menu4, Category.COMPOUND);

            Menu menu5 = Menu.builder()
                    .name("진달래")
                    .content("한솥 도시락 Top 5 메뉴 중 하나로 떡 햄버그, 돈까스, 새우튀김, 치킨 가라아게, 제육볶음이 모두 들어 있어 푸짐합니다. 생수와 조미 김이 함께 제공 됩니다.")
                    .restaurant(restaurant)
                    .price(7500)
                    .build();
            em.persist(menu5);
            setTags(menu5, Category.MEET);
            setTags(menu5, Category.COMPOUND);
            setTags(menu5, Category.SEAFOOD);
        }

        private void setTags(Menu menu, Category category) {
            Tag tag = Tag.builder()
                    .menu(menu)
                    .category(category)
                    .build();

            em.persist(tag);
        }
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
                    .category(Category.COMPOUND)
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
