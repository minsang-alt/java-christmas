package christmas;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.exception.DuplicatedMenuException;
import christmas.exception.InvalidFoodNameException;
import christmas.exception.InvalidQuantityException;
import christmas.exception.OnlyDrinkOrderException;
import christmas.exception.OrderLimitViolationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderItemsTest {


    @DisplayName("주문내역에 중복된 메뉴이름이 있다면 예외처리")
    @Test
    void throw_exception_when_duplicated_foodName() {
        assertThatThrownBy(() -> new OrderItems("티본스테이크-1,바비큐립-1,초코케이크-2,티본스테이크-1"))
                .isInstanceOf(DuplicatedMenuException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("잘못된 메뉴이름을 입력 시 예외처리")
    @Test
    void throw_exception_when_wrong_menuName() {
        assertThatThrownBy(() -> new OrderItems("티본스테이크-1,바비큐립-1,초코케이크-2,메뉴아닌이름입력-1"))
                .isInstanceOf(InvalidFoodNameException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("주문을 20개보다 더 많이 주문하면 예외처리")
    @Test
    void throw_exception_when_orderNum_moreThan_20() {
        assertThatThrownBy(() -> new OrderItems("티본스테이크-21"))
                .isInstanceOf(OrderLimitViolationException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("주문을 0개 주문하면 예외처리")
    @Test
    void throw_exception_when_orderNum_littleThan_1() {
        assertThatThrownBy(() -> new OrderItems("티본스테이크-0,바비큐립-0"))
                .isInstanceOf(InvalidQuantityException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("음료수만 주문하면 예외처리")
    @Test
    void throw_exception_when_order_only_drink() {
        assertThatThrownBy(() -> new OrderItems("제로콜라-1,레드와인-1"))
                .isInstanceOf(OnlyDrinkOrderException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("주문한 음식 총 값을 정상적으로 가져오는 지 확인")
    @Test
    void normal_totalOrderPrice() {
        OrderItems orderItems = new OrderItems("티본스테이크-1,바비큐립-1,초코케이크-2");
        int totalPrice = Food.T_BONE_STEAK.getPrice() + Food.BBQ_RIBS.getPrice() + Food.CHOCOLATE_CAKE.getPrice() * 2;
        assertThat(orderItems.getTotalOrderPrice())
                .isEqualTo(totalPrice);
    }

    @DisplayName("foodGroup에 대한 음식만을 개수를 세어 반환")
    @Test
    void count_foodByFoodGroup() {
        OrderItems orderItems = new OrderItems("양송이수프-2,타파스-1,초코케이크-2,아이스크림-1");
        assertThat(orderItems.getFoodCountByFoodGroup(FoodGroup.DESSERT))
                .isEqualTo(3);
    }

    @DisplayName("구매이력 출력")
    @Test
    void orderItems_recipe() {
        OrderItems orderItems = new OrderItems("양송이수프-2,타파스-1,초코케이크-2,아이스크림-1");
        String recipe = "양송이수프 2개\n" + "타파스 1개\n" + "초코케이크 2개\n" + "아이스크림 1개";

        assertThat(orderItems.mapToString())
                .isEqualTo(recipe);
    }
}