package christmas.service.event;

import christmas.model.calendar.Calendar;
import christmas.model.event.EventName;
import christmas.model.food.FoodGroup;
import christmas.model.order.Order;

public class WeekendDiscount implements Event {

    private static final int DISCOUNT = 2023;

    @Override
    public EventName getEventName() {
        return EventName.WEEKEND_DISCOUNT;
    }

    @Override
    public int calculateDiscount(Order order) {

        if (!isApplicable(order.getOrderDate())) {
            return 0;
        }

        return order.getFoodCount(FoodGroup.MAIN) * DISCOUNT;
    }

    @Override
    public boolean isApplicable(int date) {
        return Calendar.isWeekend(date);
    }
}
