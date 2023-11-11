package christmas;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.event.ChristmasDdayDiscount;
import christmas.event.Event;
import christmas.event.GiftEvent;
import christmas.event.SpecialDiscount;
import christmas.event.WeekdayDiscount;
import christmas.event.WeekendDiscount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class EventTest {

    private Event event;

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 20, 21, 22, 23, 24, 25})
    @DisplayName("크리스마스이벤트의 할인 금액이 정상적으로 이루어지는 지 확인")
    void verify_that_discountAmount_ChristmasEvent_is_working_correctly(int date) {
        String orderString = "티본스테이크-1,바비큐립-1,초코케이크-2";
        Order order = new Order(date, orderString);

        event = new ChristmasDdayDiscount();

        assertThat(event.calculateDiscount(order)).isEqualTo(1000 + (date - 1) * 100);
    }


    @DisplayName("평일 할인 금액이 정상적으로 이루어지는 지 확인")
    @Test
    void verify_that_discountAmount_weekDayEvent_is_working_correctly() {
        String orderString = "티본스테이크-1,바비큐립-1,초코케이크-2";
        int date = 25;
        Order order = new Order(date, orderString);

        event = new WeekdayDiscount();

        assertThat(event.calculateDiscount(order)).isEqualTo(4046);
    }

    @DisplayName("주말 할인 금액이 정상적으로 이루어지는 지 확인")
    @Test
    void verify_that_discountAmount_weekendEvent_is_working_correctly() {
        String orderString = "티본스테이크-1,바비큐립-1,초코케이크-2";
        int date = 29;
        Order order = new Order(date, orderString);

        event = new WeekendDiscount();

        assertThat(event.calculateDiscount(order)).isEqualTo(4046);
    }


    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    @DisplayName("특별 할인 금액이 정상적으로 이루어지는 지 확인")
    void verify_that_discountAmount_specialEvent_is_working_correctly(int date) {
        String orderString = "티본스테이크-1,바비큐립-1,초코케이크-2";
        Order order = new Order(date, orderString);

        event = new SpecialDiscount();

        assertThat(event.calculateDiscount(order))
                .isEqualTo(1000);
    }

    @DisplayName("주문 금액이 12만원 미만이면 증정이벤트 적용 안됨")
    @Test
    void when_the_order_totalPrice_isLessThan_120000_bonusOffer_notBeApplied() {
        String orderString = "티본스테이크-1,바비큐립-1";
        int date = 31;
        Order order = new Order(date, orderString);

        event = new GiftEvent();

        assertThat(event.calculateDiscount(order))
                .isEqualTo(0);
    }

    @DisplayName("주문 금액이 12만원 이상이면 증정이벤트 적용 안됨")
    @Test
    void when_the_order_totalPrice_isMoreThan_120000_bonusOffer_Applied() {
        String orderString = "티본스테이크-1,바비큐립-1,초코케이크-2";
        int date = 31;
        Order order = new Order(date, orderString);

        event = new GiftEvent();

        assertThat(event.calculateDiscount(order))
                .isEqualTo(25000);
    }
}
