package christmas.view;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InputValidatorTest {

    private final InputValidator inputValidator = new InputValidator();

    @ParameterizedTest
    @ValueSource(strings = {"abc", "32@1", " 3000"})
    @DisplayName("숫자가 아닌 문자가 입력되는 경우 예외처리")
    void throwExceptionWhenInputIsNotNumber(String value) {
        assertThatThrownBy(() -> inputValidator.validateNum(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", ""})
    @DisplayName("빈칸이나 null값 전달하면 예외처리")
    void throw_IllegalException_when_input_isNullOrBlank(String value) {
        assertThatThrownBy(() -> inputValidator.validateNull(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"해산물파스타-2,레드와인-1,초코케이크-1", "해산물파스타-2"})
    @DisplayName("정상적인 형식이면 통과")
    void format_is_normal(String value) {
        assertThatCode(() -> inputValidator.validateFormat(value))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(strings = {"해산물파스타-2,레드와인-1,초코케이크", "해산물파스타-2,레드와인-1,", ",레드와인-1,초코케이크-1", "해산물파스타-2레드와인-1,초코케이크-1",
            "해산물파스타-", "", ","})
    @DisplayName("문자열형식이 잘못되면 예외처리")
    void throw_when_wrong_format(String value) {
        assertThatThrownBy(() -> inputValidator.validateFormat(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");

    }


}