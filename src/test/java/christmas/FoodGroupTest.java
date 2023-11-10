package christmas;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FoodGroupTest {
    @DisplayName("food를 전달하면 해당하는 FoodGroup이 반환된다")
    @Test
    void food를_전달하면_해당하는_FoodGroup이_반환된다() {
        assertThat(FoodGroup.findByFood(Food.BUTTON_MUSHROOM_SOUP))
                .isEqualTo(FoodGroup.APPETIZER);

        assertThat(FoodGroup.findByFood(Food.CHRISTMAS_PASTA))
                .isEqualTo(FoodGroup.MAIN);

        assertThat(FoodGroup.findByFood(Food.RED_WINE))
                .isEqualTo(FoodGroup.DRINK);
    }

    @DisplayName("FoodGroup이_해당메뉴를_가지고있으면_true_반환")
    @Test
    void FoodGroup이_해당메뉴를_가지고있으면_true_반환() {
        FoodGroup foodGroup = FoodGroup.DRINK;
        assertThat(foodGroup.hasFood(Food.COKE_ZERO))
                .isTrue();
    }

    @DisplayName("음식이름을_전달하면_Food가_반환")
    @Test
    void 음식이름을_전달하면_Food가_반환() {
        assertThat(Food.findByTitle("해산물파스타"))
                .isEqualTo(Food.SEAFOOD_PASTA);
    }

}