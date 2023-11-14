package christmas.controller;

import christmas.dto.EventResultDto;
import christmas.dto.OrderDto;
import christmas.service.ChristmasService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class ChristmasController {
    private final InputView inputView;
    private final OutputView outputView;
    private final ChristmasService christmasService;

    public ChristmasController(InputView inputView, OutputView outputView, ChristmasService christmasService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.christmasService = christmasService;
    }

    public void run() {
        outputView.printWelcome();
        OrderDto orderDto = receiveOrder();
        beforeEventAppliedOrder(orderDto);
        AfterEventAppliedOrder(orderDto.getEventResultDto());
    }

    private void AfterEventAppliedOrder(EventResultDto eventResultDto) {
        outputView.printGiftMenu(eventResultDto.getGift());
        outputView.printBenefitsHistory(eventResultDto.getBenefitDetails());
        outputView.printTotalBenefitOrderAmount(eventResultDto.getTotalBenefitsAmount());
        outputView.printEstimatedAmountAfterDiscount(eventResultDto.getSalesPriceAfterDiscount());
        outputView.printEventBadge(eventResultDto.getEventBadge());
    }

    private void beforeEventAppliedOrder(OrderDto orderDto) {
        outputView.printEventPreview(orderDto.getDate());
        outputView.printOrderMenuList(orderDto.getOrderMenuList());
        outputView.printTotalOrderAmount(orderDto.getTotalAmount());
    }

    private OrderDto receiveOrder() {
        while (true) {
            try {
                int date = inputView.readDate();
                String inputMenuWithCount = inputView.inputMenuWithCount();
                OrderDto orderDto = christmasService.createOrderAndApplyEvent(date, inputMenuWithCount);

                return orderDto;
            } catch (IllegalArgumentException e) {
                outputView.printExceptionMessage(e.getMessage());
            }
        }
    }
}
