package christmas.event;

import christmas.Food;
import christmas.Order;

public class GiftEvent implements Event {

    private static final int START_DATE = 1;
    private static final int END_DATE = 31;

    private static final Food GIFT_NAME = Food.CHAMPAGNE;
    private static final int MIN_ORDER_MONEY = 120_000;

    public String getGiftName() {
        return GIFT_NAME.name();
    }

    @Override
    public String getEventName() {
        return "증정 이벤트";
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
