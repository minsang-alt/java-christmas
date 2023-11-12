package christmas;

import christmas.dto.EventApplyResponse;
import christmas.event.ChristmasDdayDiscount;
import christmas.event.Event;
import christmas.event.GiftEvent;
import christmas.event.SpecialDiscount;
import christmas.event.WeekdayDiscount;
import christmas.event.WeekendDiscount;
import christmas.service.EventManager;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class EventManagerTest {
    @ParameterizedTest
    @CsvSource({"'증정 이벤트', 25000",
            "'크리스마스 디데이 할인', 1200",
            "'주말 할인', 0",
            "'평일 할인', 4046",
            "'특별 할인', 1000"})
    @DisplayName("주문한 총 금액이 10000원이상이면 등록된 이벤트가 적용된 채로 map을 반환한다")
    void map_with_registeredEvent_applied(String eventName, int discount) {

        List<Event> events = List.of(new ChristmasDdayDiscount(), new WeekdayDiscount(), new WeekendDiscount(),
                new SpecialDiscount(), new GiftEvent());

        Order order = new Order(3, "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");

        EventManager eventManager = new EventManager(events);

        List<EventApplyResponse> eventApplyResponses = eventManager.applyDiscount(order);

        int i = eventApplyResponses.stream()
                .filter(dto -> dto.getEventName().getName().equals(eventName))
                .findFirst()
                .map(EventApplyResponse::getDiscountPrice)
                .orElse(0);

        Assertions.assertThat(i)
                .isEqualTo(discount);

    }
}