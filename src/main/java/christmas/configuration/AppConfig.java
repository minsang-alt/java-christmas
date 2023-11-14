package christmas.configuration;

import christmas.controller.ChristmasController;
import christmas.service.ChristmasService;
import christmas.service.EventService;
import christmas.service.event.ChristmasDdayDiscount;
import christmas.service.event.Event;
import christmas.service.event.GiftEvent;
import christmas.service.event.SpecialDiscount;
import christmas.service.event.WeekdayDiscount;
import christmas.service.event.WeekendDiscount;
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
