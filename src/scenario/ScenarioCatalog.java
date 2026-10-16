package scenario;

import puzzle.PuzzleSpec;

import java.util.List;

/**
 * week 1 — zastosowano: Fabryka scenariusza
 * Udostępnia gotowe scenariusze na podstawie identyfikatora.
 * Dzięki temu reszta programu nie musi samodzielnie wybierać konkretnej klasy.
 */
public final class ScenarioCatalog {
    private ScenarioCatalog() {
    }

    public static Scenario load(String scenarioId) {
        if ("classic-room".equals(scenarioId)) {
            return new Scenario("Classic Escape", classicSpecs());
        }
        throw new ScenarioNotFoundException("Nie znaleziono scenariusza: " + scenarioId);
    }

    private static List<PuzzleSpec> classicSpecs() {
        return List.of(
                new PuzzleSpec("logic", "Szyfr Cezara"),
                new PuzzleSpec("lock", "Kłódka 4-cyfrowa"),
                new PuzzleSpec("search", "Ukryty klucz")
        );
    }
}
