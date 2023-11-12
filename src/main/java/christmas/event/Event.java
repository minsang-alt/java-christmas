package christmas.event;

import christmas.EventName;
import christmas.Order;

public interface Event {

    EventName getEventName();

    int calculateDiscount(Order order);

    boolean isApplicable(int date);

}
