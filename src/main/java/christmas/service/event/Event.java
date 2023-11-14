package christmas.service.event;

import christmas.model.event.EventName;
import christmas.model.order.Order;

public interface Event {

    EventName getEventName();

    int calculateDiscount(Order order);

    boolean isApplicable(int date);

}
