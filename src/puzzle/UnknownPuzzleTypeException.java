package puzzle;

/**
 * week 1 — zastosowano: Factory + obsługa błędów
 * Wyjątek, gdy fabryka dostaje nieznany typ zagadki.
 * Dzięki temu reszta programu nie musi samodzielnie wybierać konkretnej klasy.
 */
public final class UnknownPuzzleTypeException extends RuntimeException {
    public UnknownPuzzleTypeException(String message) {
        super(message);
    }
}
