package christmas;

import christmas.exception.DuplicatedMenuException;
import christmas.exception.InvalidFoodNameException;
import christmas.exception.InvalidQuantityException;
import christmas.exception.OnlyDrinkOrderException;
import christmas.exception.OrderLimitViolationException;
import java.util.EnumMap;
import java.util.List;
import java.util.Map.Entry;

public class OrderItems {
    private final int MAX_ORDER_NUM = 20;
    private final int MIN_ORDER_NUM = 1;
    private EnumMap<Food, Integer> items = new EnumMap<>(Food.class);

    public OrderItems(String orderString) {
        generateOrderStringToMap(orderString);
        validateTotalMenuNum();
        validateIncludeOnlyDrink();
    }

    public int getFoodCount(FoodGroup foodGroup) {
        return items.entrySet().stream()
                .filter(food -> foodGroup.hasFood(food.getKey()))
                .mapToInt(Entry::getValue)
                .sum();
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
            throw new OnlyDrinkOrderException();
        }
    }

    private void validateMenuName(Food food) {
        if (food == Food.NONE) {
            throw new InvalidFoodNameException();
        }
    }

    private void validateDuplicatedMenu(Food food) {
        if (items.containsKey(food)) {
            throw new DuplicatedMenuException();
        }
    }

    private void validateMenuNum(int quantity) {
        if (quantity <= 0) {
            throw new InvalidQuantityException();
        }
    }

    private void validateTotalMenuNum() {
        long total = items.values().stream().mapToInt(Integer::intValue).sum();
        if (total > MAX_ORDER_NUM || total < MIN_ORDER_NUM) {
            throw new OrderLimitViolationException();
        }
    }

}
