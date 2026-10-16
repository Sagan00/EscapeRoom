package scenario;

import puzzle.PuzzleSpec;

import java.util.List;

/**
 * week 1 — zastosowano: Model scenariusza
 * Trzyma dane jednego scenariusza pokoju.
 * Dzięki temu dane jednego scenariusza są przechowywane razem.
 */
public final class Scenario {
    private final String name;
    private final List<PuzzleSpec> puzzleSpecs;

    public Scenario(String name, List<PuzzleSpec> puzzleSpecs) {
        this.name = name;
        this.puzzleSpecs = List.copyOf(puzzleSpecs);
    }

    public String name() {
        return name;
    }

    public List<PuzzleSpec> puzzleSpecs() {
        return puzzleSpecs;
    }
}
