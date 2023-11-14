package christmas.configuration;

import christmas.controller.ChristmasController;
import christmas.event.ChristmasDdayDiscount;
import christmas.event.Event;
import christmas.event.GiftEvent;
import christmas.event.SpecialDiscount;
import christmas.event.WeekdayDiscount;
import christmas.event.WeekendDiscount;
import christmas.service.ChristmasService;
import christmas.service.EventService;
import christmas.view.InputValidator;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;

public class AppConfig {
    public ChristmasController christmasController() {
        return new ChristmasController(inputView(), outputView(), christmasService());
    }

    private InputValidator inputValidator() {
        return new InputValidator();
    }

    private InputView inputView() {
        return new InputView(inputValidator());
    }

    private OutputView outputView() {
        return new OutputView();
    }

    private List<Event> event() {
        return List.of(new ChristmasDdayDiscount(), new WeekdayDiscount(), new WeekendDiscount(),
                new SpecialDiscount(), new GiftEvent());
    }

    private EventService eventService() {
        return new EventService(event());
    }

    private ChristmasService christmasService() {
        return new ChristmasService(eventService());
    }


}
