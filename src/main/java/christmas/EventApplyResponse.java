package christmas;

public class EventApplyResponse {
    private String eventName;
    private int discountPrice;

    public EventApplyResponse(String eventName, int discountPrice) {
        this.eventName = eventName;
        this.discountPrice = discountPrice;
    }

    public String getEventName() {
        return eventName;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }
}
