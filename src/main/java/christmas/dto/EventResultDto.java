package christmas.dto;

import christmas.model.event.EventBadge;
import java.util.List;

public class EventResultDto {
    private final List<BenefitDetail> benefitDetails;
    private final String gift;
    private final int totalBenefitsAmount;
    private final int salesPriceAfterDiscount;
    private final EventBadge eventBadge;

    public EventResultDto(List<BenefitDetail> benefitDetails, String gift, int totalBenefitsAmount,
                          int salesPriceAfterDiscount, EventBadge eventBadge) {
        this.benefitDetails = benefitDetails;
        this.gift = gift;
        this.totalBenefitsAmount = totalBenefitsAmount;
        this.salesPriceAfterDiscount = salesPriceAfterDiscount;
        this.eventBadge = eventBadge;
    }

    public List<BenefitDetail> getBenefitDetails() {
        return benefitDetails;
    }

    public String getGift() {
        return gift;
    }

    public int getTotalBenefitsAmount() {
        return totalBenefitsAmount;
    }

    public int getSalesPriceAfterDiscount() {
        return salesPriceAfterDiscount;
    }

    public EventBadge getEventBadge() {
        return eventBadge;
    }
}
