package christmas.service.event;

import christmas.model.calendar.Calendar;
import christmas.model.event.EventName;
import christmas.model.food.FoodGroup;
import christmas.model.order.Order;

public class WeekdayDiscount implements Event {

    private static final int DISCOUNT = 2023;

    @Override
    public EventName getEventName() {
        return EventName.WEEKDAY_DISCOUNT;
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
