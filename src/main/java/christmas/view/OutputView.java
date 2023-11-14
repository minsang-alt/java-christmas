package christmas.view;

import christmas.dto.BenefitDetail;
import christmas.model.event.EventBadge;
import java.util.List;

public class OutputView {

    private final String STAMP_EVERY_THOUSANDS_COMMA = "\\B(?=(\\d{3})+(?!\\d))";

    public void printWelcome() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void printEventPreview(int date) {
        System.out.printf("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n", date);
    }

    public void printOrderMenuList(String order) {
        System.out.println("\n<주문 메뉴>");
        System.out.println(order);
    }

    public void printTotalOrderAmount(int amount) {
        System.out.println("\n<할인 전 총주문 금액>");
        // 3만큼 끊어서 출력하기

        System.out.println(changeFormat(amount) + "원");
    }

    public void printGiftMenu(String gift) {
        System.out.println("\n<증정 메뉴>");
        System.out.println(gift);
    }

    public void printBenefitsHistory(List<BenefitDetail> benefitDetails) {
        System.out.println("\n<혜택 내역>");
        if (benefitDetails.isEmpty()) {
            System.out.println("없음");
            return;
        }
        for (BenefitDetail benefitDetail : benefitDetails) {
            System.out.println(
                    benefitDetail.getEventName() + ": -" + changeFormat(benefitDetail.getDiscountPrice()) + "원");
        }
    }

    public void printTotalBenefitOrderAmount(int totalBenefitsAmount) {
        System.out.println("\n<총혜택 금액>");
        if (totalBenefitsAmount == 0) {
            System.out.println("0원");
            return;
        }
        System.out.println("-" + changeFormat(totalBenefitsAmount) + "원");
    }


    public void printEstimatedAmountAfterDiscount(int salesPriceAfterDiscount) {
        System.out.println("\n<할인 후 예상 결제 금액>");
        System.out.println(changeFormat(salesPriceAfterDiscount) + "원");
    }


    public void printEventBadge(EventBadge eventBadge) {
        System.out.println("\n<12월 이벤트 배지>");
        System.out.println(eventBadge);
    }

    private String changeFormat(int amount) {
        return String.valueOf(amount).replaceAll(STAMP_EVERY_THOUSANDS_COMMA, ",");
    }

    public void printExceptionMessage(String message) {
        System.out.println(message);
    }
}
