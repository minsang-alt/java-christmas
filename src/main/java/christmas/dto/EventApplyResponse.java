package christmas.dto;

import christmas.EventName;

public class EventApplyResponse {
    private EventName eventName;
    private int discountPrice;

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
