package christmas.service;

import christmas.EventBadge;
import christmas.EventName;
import christmas.Order;
import christmas.dto.BenefitDetail;
import christmas.dto.EventApplyResponse;
import christmas.dto.EventResultDto;
import christmas.event.Event;
import java.util.List;
import java.util.stream.Collectors;

public class EventService {
    private final EventManager eventManager;

    public EventService(List<Event> events) {
        this.eventManager = new EventManager(events);
    }

    public EventResultDto applyEvents(Order order) {
        List<EventApplyResponse> eventApplyResponses = eventManager.applyDiscount(order);
        boolean hasGift = isGiftEventApplied(eventApplyResponses);
        List<BenefitDetail> benefitDetails = createBenefitDetails(eventApplyResponses);
        int totalBenefitsAmount = getTotalBenefitsAmount(eventApplyResponses);
        int salesPriceAfterDiscount = salesPriceAfterDiscount(eventApplyResponses, order);
        EventBadge eventBadge = getEventBadge(eventApplyResponses);

        return new EventResultDto(benefitDetails, hasGift, totalBenefitsAmount, salesPriceAfterDiscount, eventBadge);
    }

    public boolean isGiftEventApplied(List<EventApplyResponse> eventApplyResponses) {
        return eventApplyResponses.stream()
                .filter(response -> response.getEventName() == EventName.GIFT_EVENT)
                .findFirst()
                .map(response -> response.getDiscountPrice() != 0)
                .orElse(false);
    }

    public List<BenefitDetail> createBenefitDetails(List<EventApplyResponse> eventApplyResponses) {
        return eventApplyResponses.stream()
                .filter(response -> response.getDiscountPrice() != 0)
                .map(response -> new BenefitDetail(response.getEventName().getName(), response.getDiscountPrice()))
                .collect(Collectors.toList());
    }

    public int getTotalBenefitsAmount(List<EventApplyResponse> eventApplyResponses) {
        return eventApplyResponses.stream()
                .mapToInt(EventApplyResponse::getDiscountPrice)
                .sum();
    }

    public int salesPriceAfterDiscount(List<EventApplyResponse> eventApplyResponses, Order order) {
        int cost = order.getTotalPrice();
        int discountPrice = eventApplyResponses.stream()
                .filter(response -> response.getEventName() != EventName.GIFT_EVENT)
                .mapToInt(EventApplyResponse::getDiscountPrice)
                .sum();
        return cost - discountPrice;
    }

    public EventBadge getEventBadge(List<EventApplyResponse> eventApplyResponses) {
        int totalBenefitsAmount = getTotalBenefitsAmount(eventApplyResponses);

        return EventBadge.getBadgeByBenefit(totalBenefitsAmount);
    }

}
