package christmas;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum FoodGroup {
    APPETIZER("애피타이저", Arrays.asList(Food.BUTTON_MUSHROOM_SOUP, Food.TAPAS, Food.CAESAR_SALAD)),
    MAIN("메인", Arrays.asList(Food.T_BONE_STEAK, Food.BBQ_RIBS, Food.SEAFOOD_PASTA, Food.CHRISTMAS_PASTA)),
    DESSERT("디저트", Arrays.asList(Food.CHOCOLATE_CAKE, Food.ICE_CREAM)),
    DRINK("음료", Arrays.asList(Food.COKE_ZERO, Food.RED_WINE, Food.CHAMPAGNE)),
    EMPTY("없음", Collections.emptyList());

    private static final Map<Food, FoodGroup> foodGroupMap = new HashMap<>();

    private String title;
    private List<Food> foods;

    // findByFood메서드를 자주 쓸거라 예상되어, 미리 맵에 캐싱
    static {
        for (FoodGroup foodGroup : FoodGroup.values()) {
            for (Food food : foodGroup.foods) {
                foodGroupMap.put(food, foodGroup);
            }
        }
    }

    FoodGroup(String title, List<Food> foods) {
        this.title = title;
        this.foods = foods;
    }

    public static FoodGroup findByFood(Food food) {
        return foodGroupMap.getOrDefault(food, EMPTY);
    }

    public boolean hasFood(Food foodType) {
        return foods.stream()
                .anyMatch(food -> food == foodType);
    }

    public String getTitle() {
        return title;
    }
}
