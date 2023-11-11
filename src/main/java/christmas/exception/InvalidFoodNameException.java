package christmas.exception;

public class InvalidFoodNameException extends IllegalArgumentException {
    public static final String message = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";
    public InvalidFoodNameException() {
        super(message);
    }
}
