package christmas;

import christmas.event.ChristmasDdayDiscount;
import christmas.event.Event;
import christmas.event.GiftEvent;
import christmas.event.SpecialDiscount;
import christmas.event.WeekdayDiscount;
import christmas.event.WeekendDiscount;
import christmas.service.ChristmasService;
import christmas.service.EventService;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        List<Event> events = List.of(new ChristmasDdayDiscount(), new WeekdayDiscount(), new WeekendDiscount(),
                new SpecialDiscount(), new GiftEvent());
        EventService eventService = new EventService(events);
        ChristmasService christmasService = new ChristmasService(eventService);

        ChristmasController controller = new ChristmasController(inputView, outputView, christmasService);
        controller.run();

    }
}
