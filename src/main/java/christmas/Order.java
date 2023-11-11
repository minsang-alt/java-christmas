package christmas;

public class Order {

    private OrderDate orderDate;

    private OrderItems orderItems;

    public Order(int date, String orderString) {
        orderDate = new OrderDate(date);
        orderItems = new OrderItems(orderString);
    }

    public int getOrderDate() {
        return orderDate.getDATE();
    }

    public int getTotalPrice() {
        return orderItems.getTotalOrderPrice();
    }

    public int getFoodCount(FoodGroup foodGroup) {
        return orderItems.getFoodCountByFoodGroup(foodGroup);
    }
    
}
