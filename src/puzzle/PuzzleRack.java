package puzzle;

import java.util.Iterator;
import java.util.List;

/**
 * week 2 — zastosowano: Iterator
 * Kolekcja zagadek, po której można przechodzić jedna po drugiej.
 * Dzięki temu klient przechodzi po elementach bez dostępu do wewnetrznej listy.
 */
public final class PuzzleRack implements Iterable<String> {
    private final List<String> puzzles;

    public PuzzleRack(List<String> puzzles) {
        this.puzzles = List.copyOf(puzzles);
    }

    @Override
    public Iterator<String> iterator() {
        return new PuzzleRackIterator(puzzles);
    }
}
