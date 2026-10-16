package team;

/**
 * week 1 — zastosowano: Obsługa błędów (clean code)
 * Wyjątek, gdy liczba osób w drużynie jest niepoprawna.
 * Dzięki temu błąd ma czytelną nazwę i można go obsłużyc w jednym miejscu.
 */
public final class InvalidTeamSizeException extends RuntimeException {
    public InvalidTeamSizeException(String message) {
        super(message);
    }
}
