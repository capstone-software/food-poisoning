package core.backend.global.error.exception;

import core.backend.menu.exception.MenuNotFoundException;
import core.backend.myRestaurant.exception.MyRestaurantNotFoundException;
import core.backend.restaurant.exception.RestaurantNotFoundException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
public enum ErrorCode {

    // * 400 Bad Request

    // * 404 Not Found,
    TOTAL_NOT_FOUND(NOT_FOUND, "지원하지 않는 형식의 합계 퀴리 결과입니다", TotalNotFoundException.class),
    RESTAURANT_NOT_FOUND(NOT_FOUND, "음식점 정보를 찾을 수 없습니다", RestaurantNotFoundException.class),
    CLASS_NOT_FOUND(NOT_FOUND, "에러 코드의 클래스를 찾을 수 없습니다", ClassNotFoundException.class),
    MENU_NOT_FOUND(NOT_FOUND, "메뉴 정보를 찾을 수 없습니다", MenuNotFoundException.class),
    MY_RESTAURANT_NOT_FOUND(NOT_FOUND, "나의 음식점 정보를 찾을 수 없습니다", MyRestaurantNotFoundException.class),
    ;

    private final HttpStatus httpStatus;
    private final String message;
    private final Class<? extends Exception> klass;

    ErrorCode(HttpStatus httpStatus, String message, Class<? extends Exception> klass) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.klass = klass;
    }

    public static ErrorCode findByClass(Class<? extends Exception> klass) {
        return Arrays.stream(ErrorCode.values())
                .filter(code -> code.klass.equals(klass))
                .findAny()
                .orElseThrow(ClassNotFoundException::new);
    }
}
