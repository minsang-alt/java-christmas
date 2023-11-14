package christmas.model.food;

import java.util.Arrays;
import java.util.Objects;

public enum Food {
    BUTTON_MUSHROOM_SOUP("양송이수프", 6_000),
    TAPAS("타파스", 5_500),
    CAESAR_SALAD("시저샐러드", 8_000),
    T_BONE_STEAK("티본스테이크", 55_000),
    BBQ_RIBS("바비큐립", 54_000),
    SEAFOOD_PASTA("해산물파스타", 35_000),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000),
    CHOCOLATE_CAKE("초코케이크", 15_000),
    ICE_CREAM("아이스크림", 5_000),
    COKE_ZERO("제로콜라", 3_000),
    RED_WINE("레드와인", 60_000),
    CHAMPAGNE("샴페인", 25_000),
    NONE("메뉴아님", 0);

    private final String title;
    private final int price;

    Food(String title, int price) {
        this.title = title;
        this.price = price;
    }

    public static Food findByTitle(String foodTitle) {
        return Arrays.stream(Food.values())
                .filter(food -> Objects.equals(food.title, foodTitle))
                .findAny()
                .orElse(NONE);
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }


}
