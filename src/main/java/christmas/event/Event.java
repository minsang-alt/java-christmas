package christmas.event;

import christmas.Order;

public interface Event {
    int calculateDiscount(Order order);

    boolean isApplicable(int date);

}
