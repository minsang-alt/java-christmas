package christmas.service;

import christmas.EventApplyResponse;
import christmas.Order;
import christmas.event.Event;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EventManager {

    private final List<Event> events;

    private final int MIN_EVENT_PARTICIPATE = 10_000;

    public EventManager(List<Event> events) {
        this.events = events;
    }

    public List<EventApplyResponse> applyDiscount(Order order) {
        List<EventApplyResponse> discountDetails = new ArrayList<>();
        if (!isEventApply(order)) {
            return List.of();
        }
        for (Event event : events) {
            int discountAmount = event.calculateDiscount(order);
            discountDetails.add(new EventApplyResponse(event.getEventName(), discountAmount));
        }
        return Collections.unmodifiableList(discountDetails);
    }

    private boolean isEventApply(Order order) {
        return order.getTotalPrice() >= MIN_EVENT_PARTICIPATE;
    }

}
