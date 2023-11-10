package christmas;

import java.util.EnumMap;
import java.util.List;

public class OrderItems {
    private final int MAX_ORDER_NUM = 20;
    private final int MIN_ORDER_NUM = 1;
    private EnumMap<Food, Integer> items = new EnumMap<>(Food.class);

    public OrderItems(String orderString) {
        generateOrderStringToMap(orderString);
        validateTotalMenuNum();
        validateIncludeOnlyDrink();
    }

    public int getTotalOrderPrice() {
        return items.entrySet().stream()
                .mapToInt(food -> food.getKey().getPrice() * food.getValue())
                .sum();
    }

    private void generateOrderStringToMap(String orderString) {
        String[] orderItems = orderString.split(",");
        for (String item : orderItems) {
            String[] parts = item.split("-");
            addOrderItem(parts[0], Integer.parseInt(parts[1]));
        }
    }

    private void addOrderItem(String menuName, int quantity) {
        Food food = Food.findByTitle(menuName);
        validateMenuName(food);
        validateDuplicatedMenu(food);
        validateMenuNum(quantity);
        items.put(food, quantity);
    }

    private void validateIncludeOnlyDrink() {
        List<Food> foods = items.keySet().stream()
                .filter(food -> FoodGroup.findByFood(food) != FoodGroup.DRINK)
                .toList();
        if (foods.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 음료만 주문 시, 주문할 수 없습니다. 다시 입력해 주세요.");
        }
    }

    private void validateMenuName(Food food) {
        if (food == Food.NONE) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private void validateDuplicatedMenu(Food food) {
        if (items.containsKey(food)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private void validateMenuNum(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private void validateTotalMenuNum() {
        long total = items.values().stream().mapToInt(Integer::intValue).sum();
        if (total > MAX_ORDER_NUM || total < MIN_ORDER_NUM) {
            throw new IllegalArgumentException("[ERROR] 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.");
        }
    }
}
