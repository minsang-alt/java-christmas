package christmas;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderDateTest {
    @DisplayName("유효하지 않는 주문날짜를 전달하면 예외처리")
    @Test
    void throw_exception_when_nonValidate_orderDate() {
        assertThatThrownBy(() -> new OrderDate(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");

        assertThatThrownBy(() -> new OrderDate(32))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

}