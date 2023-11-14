package christmas.model.calendar;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public class Calendar {

    private static final int MONTH = 12;
    private static final int START_DAY = 1;
    private static final int LAST_DAY = 31;

    private static final EnumSet<Week> WEEKDAYS = EnumSet.of(Week.MON, Week.TUE, Week.WED, Week.THU, Week.SUN);

    private static final EnumSet<Week> WEEKEND = EnumSet.of(Week.FRI, Week.SAT);

    private static final Map<Integer, Week> CALENDAR = new HashMap<>();

    static {
        Week[] weeks = Week.values();
        //1일 = 금요일 부터 시작
        for (int i = START_DAY; i <= LAST_DAY; i++) {
            CALENDAR.put(i, weeks[(i + 3) % weeks.length]);
        }
    }

    public static boolean isWeekday(int date) {
        validateDate(date);
        return WEEKDAYS.contains(CALENDAR.get(date));
    }

    public static boolean isWeekend(int date) {
        validateDate(date);
        return WEEKEND.contains(CALENDAR.get(date));
    }

    public static boolean isWeek(Week week, int date) {
        Week realWeek = CALENDAR.get(date);
        return week == realWeek;
    }

    private static void validateDate(int date) {
        if (START_DAY > date || LAST_DAY < date) {
            throw new IllegalArgumentException("[ERROR] 잘못된 date를 입력했습니다.");
        }
    }

}
