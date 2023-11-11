package christmas.event;

import christmas.Calendar;
import christmas.FoodGroup;
import christmas.Order;

public class WeekdayDiscount implements Event {

    private static final int DISCOUNT = 2023;

    @Override
    public String getEventName() {
        return "평일 할인";
    }

    @Override
    public int calculateDiscount(Order order) {
        if (!isApplicable(order.getOrderDate())) {
            return 0;
        }
        return order.getFoodCount(FoodGroup.DESSERT) * DISCOUNT;
    }

    @Override
    public boolean isApplicable(int date) {
        return Calendar.isWeekday(date);
    }
}
