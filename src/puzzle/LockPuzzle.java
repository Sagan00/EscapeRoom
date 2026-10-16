package puzzle;

/**
 * week 1 — zastosowano: Factory (produkt)
 * Konkretny typ zagadki tworzony przez fabrykę.
 * Dzięki temu reszta programu nie musi samodzielnie wybierać konkretnej klasy.
 */
public final class LockPuzzle implements Puzzle {
    private final String name;

    public LockPuzzle(String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }
}
