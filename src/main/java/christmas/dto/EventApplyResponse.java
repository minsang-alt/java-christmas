package christmas.dto;

import christmas.model.event.EventName;

public class EventApplyResponse {
    private final EventName eventName;
    private final int discountPrice;

    public EventApplyResponse(EventName eventName, int discountPrice) {
        this.eventName = eventName;
        this.discountPrice = discountPrice;
    }

    public EventName getEventName() {
        return eventName;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }
}
