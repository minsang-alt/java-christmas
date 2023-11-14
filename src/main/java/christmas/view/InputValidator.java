package christmas.view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {

    private final String REGEXP_ONLY_NUM = "^[1-9][0-9]*$";
    private final Pattern ONLY_NUM = Pattern.compile(REGEXP_ONLY_NUM);

    private final String REGEXP_FORMAT_MENU = "([가-힣a-zA-Z]+-\\d+)(,[가-힣a-zA-Z]+-\\d+)*";
    private final Pattern FORMAT_MENU = Pattern.compile(REGEXP_FORMAT_MENU);

    private final int FIRST_DAY = 1;
    private final int LAST_DAY = 31;

    public void validateNum(String input) {
        Matcher matcher = ONLY_NUM.matcher(input);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    public void validateNull(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    public void validateDate(int date) {
        if (date < FIRST_DAY || date > LAST_DAY) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    public void validateFormat(String input) {
        Matcher matcher = FORMAT_MENU.matcher(input);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

}
