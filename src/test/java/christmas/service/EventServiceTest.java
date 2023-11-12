package christmas.service;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.EventBadge;
import christmas.EventName;
import christmas.Order;
import christmas.dto.BenefitDetail;
import christmas.dto.EventApplyResponse;
import christmas.event.ChristmasDdayDiscount;
import christmas.event.Event;
import christmas.event.GiftEvent;
import christmas.event.SpecialDiscount;
import christmas.event.WeekdayDiscount;
import christmas.event.WeekendDiscount;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventServiceTest {

    @DisplayName("증정이벤트에 적용되면 true반환")
    @Test
    void when_apply_giftEvent_returnTrue() {
        List<Event> events = List.of(new ChristmasDdayDiscount(), new WeekdayDiscount(), new WeekendDiscount(),
                new SpecialDiscount(), new GiftEvent());

        EventService eventService = new EventService(events);
        List<EventApplyResponse> eventApplyResponses = List.of(new EventApplyResponse(EventName.WEEKEND_DISCOUNT, 2023),
                new EventApplyResponse(EventName.GIFT_EVENT, 25000));

        assertThat(eventService.isGiftEventApplied(eventApplyResponses))
                .isTrue();
    }

    @DisplayName("증정이벤트에 적용되지 않다면 false")
    @Test
    void when_apply_giftEvent_returnFalse() {
        List<Event> events = List.of(new ChristmasDdayDiscount(), new WeekdayDiscount(), new WeekendDiscount(),
                new SpecialDiscount(), new GiftEvent());

        EventService eventService = new EventService(events);
        List<EventApplyResponse> eventApplyResponses = List.of(new EventApplyResponse(EventName.WEEKEND_DISCOUNT, 2023),
                new EventApplyResponse(EventName.GIFT_EVENT, 0));

        assertThat(eventService.isGiftEventApplied(eventApplyResponses))
                .isFalse();
    }

    @DisplayName("이벤트가 적용되지 않다면 false")
    @Test
    void when_notApply_giftEvent_returnFalse() {
        List<Event> events = List.of(new ChristmasDdayDiscount(), new WeekdayDiscount(), new WeekendDiscount(),
                new SpecialDiscount(), new GiftEvent());

        EventService eventService = new EventService(events);
        List<EventApplyResponse> eventApplyResponses = List.of();

        assertThat(eventService.isGiftEventApplied(eventApplyResponses))
                .isFalse();
    }

    @DisplayName("정상적으로 해택내역을 가져오는 지 확인")
    @Test
    void when_benefitDeatils_isNormal() {
        List<Event> events = List.of(new ChristmasDdayDiscount(), new WeekdayDiscount(), new WeekendDiscount(),
                new SpecialDiscount(), new GiftEvent());

        EventService eventService = new EventService(events);
        List<EventApplyResponse> eventApplyResponses = List.of(new EventApplyResponse(EventName.WEEKEND_DISCOUNT, 2023),
                new EventApplyResponse(EventName.GIFT_EVENT, 25000),
                new EventApplyResponse(EventName.SPECIAL_DISCOUNT, 0));

        List<BenefitDetail> benefitDetails = eventService.createBenefitDetails(eventApplyResponses);

        assertThat(benefitDetails)
                .size()
                .isEqualTo(2);

    }

    @DisplayName("만약 이벤트를 적용할 수 없다면 해택 내역은 없음을 반환한다")
    @Test
    void benefitDetails_isBlank_when_doNot_apply_event() {
        List<Event> events = List.of(new ChristmasDdayDiscount(), new WeekdayDiscount(), new WeekendDiscount(),
                new SpecialDiscount(), new GiftEvent());
        EventService eventService = new EventService(events);
        List<EventApplyResponse> eventApplyResponses = List.of();
        List<BenefitDetail> benefitDetails = eventService.createBenefitDetails(eventApplyResponses);

        assertThat(benefitDetails)
                .size()
                .isEqualTo(0);

    }


    @DisplayName("이벤트가 적용되어 총해택금액이 들어가는 지 확인")
    @Test
    void verify_that_event_isApplied_and_theTotal_impactAmountIsEntered() {
        List<Event> events = List.of(new ChristmasDdayDiscount(), new WeekdayDiscount(), new WeekendDiscount(),
                new SpecialDiscount(), new GiftEvent());

        EventService eventService = new EventService(events);
        List<EventApplyResponse> eventApplyResponses = List.of(new EventApplyResponse(EventName.WEEKEND_DISCOUNT, 2023),
                new EventApplyResponse(EventName.GIFT_EVENT, 25000),
                new EventApplyResponse(EventName.SPECIAL_DISCOUNT, 0));

        assertThat(eventService.getTotalBenefitsAmount(eventApplyResponses))
                .isEqualTo(27023);
    }

    @DisplayName("이벤트가 적용되어 총 결제 금액이 잘 반환되는 지 확인")
    @Test
    void verify_event_isApplied_theTotalPayment_returned_correctly() {
        List<Event> events = List.of(new ChristmasDdayDiscount(), new WeekdayDiscount(), new WeekendDiscount(),
                new SpecialDiscount(), new GiftEvent());

        Order order = new Order(3, "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        EventService eventService = new EventService(events);
        List<EventApplyResponse> eventApplyResponses = List.of(
                new EventApplyResponse(EventName.CHRISTMAS_DDAY_DISCOUNT, 1200),
                new EventApplyResponse(EventName.WEEKDAY_DISCOUNT, 4046),
                new EventApplyResponse(EventName.SPECIAL_DISCOUNT, 1000),
                new EventApplyResponse(EventName.GIFT_EVENT, 25000));

        assertThat(eventService.salesPriceAfterDiscount(eventApplyResponses, order))
                .isEqualTo(135_754);
    }

    @DisplayName("혜택 금액이 5000원이상 10000원 미만일 경우 별 배지를 받는다.")
    @Test
    void nomral_starBadge_when_BenefitsMoney_is8000() {
        List<Event> events = List.of(new ChristmasDdayDiscount(), new WeekdayDiscount(), new WeekendDiscount(),
                new SpecialDiscount(), new GiftEvent());
        EventService eventService = new EventService(events);
        List<EventApplyResponse> eventApplyResponses = List.of(new EventApplyResponse(EventName.WEEKEND_DISCOUNT, 8000),
                new EventApplyResponse(EventName.GIFT_EVENT, 0),
                new EventApplyResponse(EventName.SPECIAL_DISCOUNT, 0));

        assertThat(eventService.getEventBadge(eventApplyResponses))
                .isEqualTo(EventBadge.STAR);
    }

    @DisplayName("혜택 금액이 10000원이상 20000원 미만일 경우 트리 배지를 받는다.")
    @Test
    void nomral_treeBadge_when_BenefitsMoney_is19000() {
        List<Event> events = List.of(new ChristmasDdayDiscount(), new WeekdayDiscount(), new WeekendDiscount(),
                new SpecialDiscount(), new GiftEvent());
        EventService eventService = new EventService(events);
        List<EventApplyResponse> eventApplyResponses = List.of(
                new EventApplyResponse(EventName.WEEKEND_DISCOUNT, 19000),
                new EventApplyResponse(EventName.GIFT_EVENT, 0),
                new EventApplyResponse(EventName.SPECIAL_DISCOUNT, 0));

        assertThat(eventService.getEventBadge(eventApplyResponses))
                .isEqualTo(EventBadge.TREE);
    }

    @DisplayName("혜택 금액이 20000원 이상일 경우 산타 배지를 받는다.")
    @Test
    void nomral_santaBadge_when_BenefitsMoney_is30000() {
        List<Event> events = List.of(new ChristmasDdayDiscount(), new WeekdayDiscount(), new WeekendDiscount(),
                new SpecialDiscount(), new GiftEvent());
        EventService eventService = new EventService(events);
        List<EventApplyResponse> eventApplyResponses = List.of(
                new EventApplyResponse(EventName.WEEKEND_DISCOUNT, 30000),
                new EventApplyResponse(EventName.GIFT_EVENT, 0),
                new EventApplyResponse(EventName.SPECIAL_DISCOUNT, 0));

        assertThat(eventService.getEventBadge(eventApplyResponses))
                .isEqualTo(EventBadge.SANTA);
    }

    @DisplayName("해택 받는 금액이 5000원 미만일 경우 없음을 반환한다.")
    @Test
    void noneBedge_when_benefitsMoney_lessThan_5000() {
        List<Event> events = List.of(new ChristmasDdayDiscount(), new WeekdayDiscount(), new WeekendDiscount(),
                new SpecialDiscount(), new GiftEvent());
        EventService eventService = new EventService(events);
        List<EventApplyResponse> eventApplyResponses = List.of(
                new EventApplyResponse(EventName.WEEKEND_DISCOUNT, 2023),
                new EventApplyResponse(EventName.GIFT_EVENT, 0),
                new EventApplyResponse(EventName.SPECIAL_DISCOUNT, 0));

        assertThat(eventService.getEventBadge(eventApplyResponses))
                .isEqualTo(EventBadge.NONE);
    }

}