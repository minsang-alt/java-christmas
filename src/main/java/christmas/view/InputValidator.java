package christmas.view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {
    private final String REGEXP_ONLY_NUM = "^[1-9][0-9]*$";
    private final Pattern ONLY_NUM = Pattern.compile(REGEXP_ONLY_NUM);

    private final String REGEXP_FORMAT_MENU = "([가-힣a-zA-Z]+-\\d+)(,[가-힣a-zA-Z]+-\\d+)*";
    private final Pattern FORMAT_MENU = Pattern.compile(REGEXP_FORMAT_MENU);

    public void validateNum(String input) {
        Matcher matcher = ONLY_NUM.matcher(input);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("[ERROR] 숫자로만 입력해주세요");
        }
    }

    public void validateNull(String amount) {
        if (amount == null || amount.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 빈 값을 입력할 수 없습니다.");
        }
    }

    public void validateFormat(String input) {
        Matcher matcher = FORMAT_MENU.matcher(input);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("[ERROR] 제공된 형식대로 입력하세요");
        }
    }

}
