package game;

/**
 * week 2 — zastosowano: Template Method
 * Szkielet tworzenia raportu sesji z krokami do uzupełnienia.
 * Dzięki temu kolejność kroków jest stała, a szczegóły można zmienić.
 */
public abstract class AbstractSessionReportGenerator {
    public final String generate(GameSession session) {
        return header() + '\n' +
                body(session) + '\n' +
                footer(session);
    }

    protected abstract String header();

    protected abstract String body(GameSession session);

    protected abstract String footer(GameSession session);
}
