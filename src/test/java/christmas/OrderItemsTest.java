package christmas;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderItemsTest {


    @DisplayName("주문내역에 중복된 메뉴이름이 있다면 예외처리")
    @Test
    void throw_exception_when_duplicated_foodName() {
        assertThatThrownBy(() -> new OrderItems("티본스테이크-1,바비큐립-1,초코케이크-2,티본스테이크-1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("잘못된 메뉴이름을 입력 시 예외처리")
    @Test
    void throw_exception_when_wrong_menuName() {
        assertThatThrownBy(() -> new OrderItems("티본스테이크-1,바비큐립-1,초코케이크-2,메뉴아닌이름입력-1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("주문을 20개보다 더 많이 주문하면 예외처리")
    @Test
    void throw_exception_when_orderNum_moreThan_20() {
        assertThatThrownBy(() -> new OrderItems("티본스테이크-21"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.");
    }

    @DisplayName("주문을 0개 주문하면 예외처리")
    @Test
    void throw_exception_when_orderNum_littleThan_1() {
        assertThatThrownBy(() -> new OrderItems("티본스테이크-0,바비큐립-0"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("음료수만 주문하면 예외처리")
    @Test
    void throw_exception_when_order_only_drink() {
        assertThatThrownBy(() -> new OrderItems("제로콜라-1,레드와인-1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 음료만 주문 시, 주문할 수 없습니다. 다시 입력해 주세요.");
    }

    @DisplayName("주문한 음식 총 값을 정상적으로 가져오는 지 확인")
    @Test
    void normal_totalOrderPrice() {
        OrderItems orderItems = new OrderItems("티본스테이크-1,바비큐립-1,초코케이크-2");
        int totalPrice = Food.T_BONE_STEAK.getPrice() + Food.BBQ_RIBS.getPrice() + Food.CHOCOLATE_CAKE.getPrice() * 2;
        assertThat(orderItems.getTotalOrderPrice())
                .isEqualTo(totalPrice);
    }
}