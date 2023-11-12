package christmas.dto;

public class BenefitDetail {
    private final String eventName;
    private final int discountPrice;

    public BenefitDetail(String eventName, int discountPrice) {
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
