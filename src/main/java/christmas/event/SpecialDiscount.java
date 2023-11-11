package christmas.event;

import christmas.Order;
import christmas.calendar.Calendar;
import christmas.calendar.Week;

public class SpecialDiscount implements Event {


    private static final int DISCOUNT = 1000;

    @Override
    public String getEventName() {
        return "특별 할인";
    }

    @Override
    public int calculateDiscount(Order order) {
        if (!isApplicable(order.getOrderDate())) {
            return 0;
        }
        return DISCOUNT;
    }

    @Override
    public boolean isApplicable(int date) {
        return date == 25 || Calendar.isWeek(Week.SUN, date);
    }
}
