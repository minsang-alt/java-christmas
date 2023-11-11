package christmas.service;

import christmas.EventApplyResponse;
import christmas.Order;
import christmas.event.Event;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EventManager {

    private final List<Event> events;
    private final Order order;

    private final int MIN_EVENT_PARTICIPATE = 10_000;

    public EventManager(List<Event> events, Order order) {
        this.events = events;
        this.order = order;
    }

    public List<EventApplyResponse> applyDiscount() {
        List<EventApplyResponse> discountDetails = new ArrayList<>();
        if (!isEventApply()) {
            return List.of();
        }
        for (Event event : events) {
            int discountAmount = event.calculateDiscount(order);
            discountDetails.add(new EventApplyResponse(event.getEventName(), discountAmount));
        }
        return Collections.unmodifiableList(discountDetails);
    }

    private boolean isEventApply() {
        return order.getTotalPrice() >= MIN_EVENT_PARTICIPATE;
    }

}
