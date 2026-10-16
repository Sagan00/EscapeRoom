package game;

/** LAB 3 — LSP: alternatywna implementacja generatora raportu sesji. */
public final class SessionDetailedReport extends AbstractSessionReportGenerator {
    @Override
    protected String header() {
        return "Raport szczegółowy sesji";
    }

    @Override
    protected String body(GameSession session) {
        return "Stan gry: " + session.stateName()
                + "; wykorzystane podpowiedzi: " + session.usedHints();
    }

    @Override
    protected String footer(GameSession session) {
        return "Łącznie rozwiązane zagadki: " + session.solvedCount();
    }
}
