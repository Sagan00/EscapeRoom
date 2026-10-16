package puzzle;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * week 2 — zastosowano: Iterator
 * Iterator, który zwraca kolejne zagadki z kolekcji.
 * Dzięki temu klient przechodzi po elementach bez dostępu do wewnetrznej listy.
 */
public final class PuzzleRackIterator implements Iterator<String> {
    private final List<String> puzzles;
    private int index;

    public PuzzleRackIterator(List<String> puzzles) {
        this.puzzles = puzzles;
    }

    @Override
    public boolean hasNext() {
        return index < puzzles.size();
    }

    @Override
    public String next() {
        if (!hasNext()) {
            throw new NoSuchElementException("Brak kolejnych zagadek.");
        }
        String name = puzzles.get(index);
        index++;
        return name;
    }
}
