package room;

/**
 * week 1 — zastosowano: Obsługa błędów (clean code)
 * Wyjątek, gdy nie da się otworzyć drzwi.
 * Dzięki temu blad ma czytelna nazwe i można go obsłużyc w jednym miejscu.
 */
public final class DoorOpenException extends RuntimeException {
    public DoorOpenException(String message) {
        super(message);
    }
}
