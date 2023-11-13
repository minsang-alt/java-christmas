package christmas.dto;

public class OrderDto {

    private final int date;

    private final String orderMenuList;

    private final int totalAmount;

    private final EventResultDto eventResultDto;

    public OrderDto(int date, String orderMenuList, int totalAmount, EventResultDto eventResultDto) {
        this.date = date;
        this.orderMenuList = orderMenuList;
        this.totalAmount = totalAmount;
        this.eventResultDto = eventResultDto;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public EventResultDto getEventResultDto() {
        return eventResultDto;
    }

    public int getDate() {
        return date;
    }

    public String getOrderMenuList() {
        return orderMenuList;
    }

}
