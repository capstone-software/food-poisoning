package core.backend.shop.tag.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Category {
    MEET("육류"),
    DAIRY("유제품"),
    SEAFOOD("해산물"),
    EGG("계란"),
    DRIED("건조식품"),
    FRUIT("과일"),
    VEGETABLE("야채"),
    COMPOUND("복합 조리 식품");
    private String content;
}
