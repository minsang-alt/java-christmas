package christmas;

public enum EventBadge {
    STAR("별", 5_000, 10_000),
    TREE("트리", 10_000, 20_000),
    SANTA("산타", 20_000, Integer.MAX_VALUE),
    NONE("없음", 0, 5000);

    private final String badge;
    private final int minBenefit;
    private final int maxBenefit;

    EventBadge(String badge, int minBenefit, int maxBenefit) {
        this.badge = badge;
        this.minBenefit = minBenefit;
        this.maxBenefit = maxBenefit;
    }

    public static EventBadge getBadgeByBenefit(int benefit) {
        for (EventBadge badge : values()) {
            if (benefit >= badge.minBenefit && benefit < badge.maxBenefit) {
                return badge;
            }
        }
        throw new IllegalArgumentException("적절한 혜택금액 범위를 찾을 수 없습니다: " + benefit);
    }

    @Override
    public String toString() {
        return badge;
    }
}
