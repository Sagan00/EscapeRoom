package scenario;

/**
 * week 1 — zastosowano: Obsługa błędów (clean code)
 * Wyjątek, gdy podany scenariusz nie istnieje.
 * Dzięki temu blad ma czytelna nazwe i można go obsłużyc w jednym miejscu.
 */
public final class ScenarioNotFoundException extends RuntimeException {
    public ScenarioNotFoundException(String message) {
        super(message);
    }
}
