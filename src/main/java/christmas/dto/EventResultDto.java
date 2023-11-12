package christmas.dto;

import christmas.EventBadge;
import java.util.List;

public class EventResultDto {
    private final List<BenefitDetail> benefitDetails;
    private final boolean hasGift;
    private final int totalBenefitsAmount;
    private final int salesPriceAfterDiscount;
    private final EventBadge eventBadge;

    public EventResultDto(List<BenefitDetail> benefitDetails, boolean hasGift, int totalBenefitsAmount,
                          int salesPriceAfterDiscount, EventBadge eventBadge) {
        this.benefitDetails = benefitDetails;
        this.hasGift = hasGift;
        this.totalBenefitsAmount = totalBenefitsAmount;
        this.salesPriceAfterDiscount = salesPriceAfterDiscount;
        this.eventBadge = eventBadge;
    }

    public List<BenefitDetail> getBenefitDetails() {
        return benefitDetails;
    }

    public boolean isHasGift() {
        return hasGift;
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
