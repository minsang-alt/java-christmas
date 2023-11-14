package christmas;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.calendar.Calendar;
import christmas.model.calendar.Week;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CalendarTest {

    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5, 6, 7, 10, 11, 31})
    @DisplayName("월,화,수,목,일은 평일 이어야 한다")
    void isWeekDay(int date) {
        assertThat(Calendar.isWeekday(date))
                .isTrue();

        assertThat(Calendar.isWeekend(date))
                .isFalse();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 8, 9, 15, 16, 22, 23, 29, 30})
    @DisplayName("토,금은 평일 이어야 한다")
    void isWeekend(int date) {
        assertThat(Calendar.isWeekend(date))
                .isTrue();
        assertThat(Calendar.isWeekday(date))
                .isFalse();
    }

    @DisplayName("잘못된 date를 입력하면 예외처리")
    @Test
    void throw_when_input_wrong_date() {
        Assertions.assertThatThrownBy(() -> Calendar.isWeekday(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 잘못된 date를 입력했습니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 24, 31})
    @DisplayName("해당 date가 일요일이면 true반환")
    void return_true_when_date_sun(int date) {
        assertThat(Calendar.isWeek(Week.SUN, date))
                .isTrue();
        assertThat(Calendar.isWeek(Week.MON, date))
                .isFalse();
    }


}