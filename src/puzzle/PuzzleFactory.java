package puzzle;

/**
 * week 1 — zastosowano: Factory
 * Tworzy odpowiedni obiekt zagadki na podstawie typu.
 * Dzięki temu reszta programu nie musi samodzielnie wybierać konkretnej klasy.
 */
public final class PuzzleFactory {
    public Puzzle create(String type, String name) {
        if ("logic".equals(type)) {
            return new LogicPuzzle(name);
        }
        if ("lock".equals(type)) {
            return new LockPuzzle(name);
        }
        if ("search".equals(type)) {
            return new SearchPuzzle(name);
        }
        throw new UnknownPuzzleTypeException("Nieznany typ zagadki: " + type);
    }
}
