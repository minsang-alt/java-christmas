package christmas.model.order;

import christmas.model.food.FoodGroup;

public class Order {

    private final OrderDate orderDate;

    private final OrderItems orderItems;

    public Order(int date, String orderString) {
        orderDate = new OrderDate(date);
        orderItems = new OrderItems(orderString);
    }

    public int getOrderDate() {
        return orderDate.getDate();
    }

    public int getTotalPrice() {
        return orderItems.getTotalOrderPrice();
    }

    public int getFoodCount(FoodGroup foodGroup) {
        return orderItems.getFoodCountByFoodGroup(foodGroup);
    }

    public OrderItems getOrderItems() {
        return orderItems;
    }
}
