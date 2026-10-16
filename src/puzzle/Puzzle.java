package puzzle;

/**
 * week 1 — zastosowano: Factory (interfejs produktu)
 * Wspólna umowa dla wszystkich typów zagadek.
 * Dzięki temu reszta programu nie musi samodzielnie wybierać konkretnej klasy.
 */
public interface Puzzle {
    String name();
}
