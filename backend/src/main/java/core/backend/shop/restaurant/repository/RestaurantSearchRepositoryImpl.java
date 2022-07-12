package core.backend.shop.restaurant.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import core.backend.global.error.exception.TotalNotFoundException;
import core.backend.shop.restaurant.domain.Restaurant;
import core.backend.shop.restaurant.dto.RestaurantSearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Field;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static core.backend.shop.restaurant.domain.QRestaurant.restaurant;

@Repository
@RequiredArgsConstructor
public class RestaurantSearchRepositoryImpl implements RestaurantSearchRepository {

    private final JPAQueryFactory queryFactory;
    private final ElasticsearchOperations elasticsearchOperations;

    @Override
    public Page<Restaurant> searchBy(RestaurantSearchCondition condition, Pageable pageable) {
        Query query = createQueryBy(condition, pageable);
        List<Restaurant> content = elasticsearchOperations.search(query, Restaurant.class)
                .stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());

        return new PageImpl<>(content, pageable, getRestaurantTotalCount());
    }

    private Query createQueryBy(RestaurantSearchCondition condition, Pageable pageable) {
        CriteriaQuery query = new CriteriaQuery(new Criteria());
        if (!condition.getName().equals("")) {
            query.addCriteria(Criteria.where("name").contains(condition.getName()));
        }
        if (!condition.getDescription().equals("")) {
            query.addCriteria(Criteria.where("description").contains(condition.getDescription()));
        }
        return query;
    }

    private Long getRestaurantTotalCount() {
        return Optional.ofNullable(
                queryFactory
                        .select(restaurant.count())
                        .from(restaurant)
                        .fetchOne()
        ).orElseThrow(TotalNotFoundException::new);
    }
}
