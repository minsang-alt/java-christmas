package christmas.event;

import christmas.EventName;
import christmas.Food;
import christmas.Order;

public class GiftEvent implements Event {

    private static final int START_DATE = 1;
    private static final int END_DATE = 31;

    private static final Food GIFT_NAME = Food.CHAMPAGNE;
    private static final int MIN_ORDER_MONEY = 120_000;

    public static String getGiftName() {
        return GIFT_NAME.getTitle() + " " + "1개";
    }

    @Override
    public EventName getEventName() {
        return EventName.GIFT_EVENT;
    }

    @Override
    public int calculateDiscount(Order order) {

        if (!isApplicable(order.getOrderDate())) {
            return 0;
        }
        if (order.getTotalPrice() < MIN_ORDER_MONEY) {
            return 0;
        }

        return GIFT_NAME.getPrice();
    }

    @Override
    public boolean isApplicable(int date) {
        return START_DATE <= date && date <= END_DATE;
    }
}
