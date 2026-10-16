package room;

import puzzle.Puzzle;

import java.util.List;

/**
 * week 1 — zastosowano: Composite (liść)
 * Pojedynczy element drzewa pokoju: jedna zagadka.
 * Dzięki temu pojedyncza zagadka i grupa zagadek są obsługiwane tak samo.
 */
public final class PuzzleRoomLeaf implements RoomComponent {
    private static final int PUZZLE_COUNT = 1;

    private final Puzzle puzzle;

    public PuzzleRoomLeaf(Puzzle puzzle) {
        this.puzzle = puzzle;
    }

    @Override
    public int puzzleCount() {
        return PUZZLE_COUNT;
    }

    @Override
    public List<String> puzzleNames() {
        return List.of(puzzle.name());
    }
}
