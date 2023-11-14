package christmas.service;

import christmas.dto.EventResultDto;
import christmas.dto.OrderDto;
import christmas.model.order.Order;
import christmas.model.order.OrderItems;

public class ChristmasService {

    private final EventService eventService;

    public ChristmasService(EventService eventService) {
        this.eventService = eventService;
    }

    public OrderDto createOrderAndApplyEvent(int date, String inputMenuWithCount) {
        Order order = new Order(date, inputMenuWithCount);
        OrderItems items = order.getOrderItems();
        EventResultDto eventResultDto = applyBenefitOrder(order);

        return new OrderDto(order.getOrderDate(), items.mapToString(), order.getTotalPrice(), eventResultDto);
    }

    private EventResultDto applyBenefitOrder(Order order) {
        return eventService.applyEvents(order);
    }

}
