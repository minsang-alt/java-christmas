package christmas.exception;

public class OnlyDrinkOrderException extends IllegalArgumentException {
    public static final String message = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";

    public OnlyDrinkOrderException() {
        super(message);
    }
}
