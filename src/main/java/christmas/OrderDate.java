package christmas;

public class OrderDate {
    private final int MONTH = 12;
    private final int FIRST_DAY = 1;
    private final int LAST_DAY = 31;
    private final int DATE;

    public OrderDate(int date) {
        validateDate(date);
        this.DATE = date;
    }


    public int getDate() {
        return DATE;
    }

    private void validateDate(int date) {
        if (date < FIRST_DAY || date > LAST_DAY) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

}
