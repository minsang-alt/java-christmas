package christmas.event;

import christmas.Order;

public interface Event {

    String getName();

    int calculateDiscount(Order order);

    boolean isApplicable(int date);

}
