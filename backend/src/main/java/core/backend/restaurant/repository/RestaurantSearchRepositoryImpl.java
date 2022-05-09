package core.backend.restaurant.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import core.backend.global.error.exception.TotalNotFoundException;
import core.backend.restaurant.domain.Restaurant;
import core.backend.restaurant.dto.RestaurantSearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

import static core.backend.restaurant.domain.QRestaurant.restaurant;

@Repository
@RequiredArgsConstructor
public class RestaurantSearchRepositoryImpl implements RestaurantSearchRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Restaurant> search(RestaurantSearchCondition condition, Pageable pageable) {
        List<Restaurant> content = queryFactory
                .selectFrom(restaurant)
                .where(
                        nameContains(condition.getName()),
                        descriptionContains(condition.getDescription())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return new PageImpl<>(content, pageable, getRestaurantTotalCount());
    }

    private Long getRestaurantTotalCount() {
        return Optional.ofNullable(
                queryFactory
                        .select(restaurant.count())
                        .from(restaurant)
                        .fetchOne()
        ).orElseThrow(TotalNotFoundException::new);
    }

    private BooleanExpression nameContains(String name) {
        return StringUtils.hasText(name) ? restaurant.name.contains(name) : null;
    }

    private BooleanExpression descriptionContains(String description) {
        return StringUtils.hasText(description) ? restaurant.description.contains(description) : null;
    }
}
