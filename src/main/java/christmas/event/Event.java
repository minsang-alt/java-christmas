package christmas.event;

import christmas.Order;

public interface Event {

    String getEventName();

    int calculateDiscount(Order order);

    boolean isApplicable(int date);

}
