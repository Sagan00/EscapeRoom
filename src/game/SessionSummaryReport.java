package game;

/**
 * week 2 — zastosowano: Template Method
 * Konkretna wersja raportu podsumowującego sesję.
 * Dzięki temu kolejność kroków jest stała, a szczegóły można zmienić.
 */
public final class SessionSummaryReport extends AbstractSessionReportGenerator {
    @Override
    protected String header() {
        return "Raport sesji";
    }

    @Override
    protected String body(GameSession session) {
        return "Stan=" + session.stateName() + ", podpowiedzi=" + session.usedHints();
    }

    @Override
    protected String footer(GameSession session) {
        return "Rozwiązane=" + session.solvedCount();
    }
}
