package christmas.event;


import christmas.EventName;
import christmas.Order;

public class ChristmasDdayDiscount implements Event {
    private static final int START_DISCOUNT = 1000;
    private static final int INCREASE_AMOUNT = 100;

    private static final int START_DATE = 1;
    private static final int END_DATE = 25;

    @Override
    public EventName getEventName() {
        return EventName.CHRISTMAS_DDAY_DISCOUNT;
    }

    @Override
    public int calculateDiscount(Order order) {
        int orderDate = order.getOrderDate();
        if (!isApplicable(orderDate)) {
            return 0;
        }

        return START_DISCOUNT + (orderDate - 1) * INCREASE_AMOUNT;
    }

    @Override
    public boolean isApplicable(int date) {
        return START_DATE <= date && date <= END_DATE;
    }

}
