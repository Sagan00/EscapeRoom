package scenario;

import org.junit.jupiter.api.Test;
import puzzle.PuzzleSpec;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/** LAB 4, punkt 5: pięć testów katalogu scenariuszy. */
class ScenarioCatalogTest {
    private static final String CLASSIC_ROOM_ID = "classic-room";

    // Test 1/5: znany identyfikator wczytuje scenariusz o oczekiwanej nazwie.
    @Test
    void loadsClassicScenario() {
        assertEquals("Classic Escape", ScenarioCatalog.load(CLASSIC_ROOM_ID).name());
    }

    // Test 2/5: scenariusz zawiera trzy zagadki.
    @Test
    void classicScenarioHasThreePuzzles() {
        assertEquals(3, ScenarioCatalog.load(CLASSIC_ROOM_ID).puzzleSpecs().size());
    }

    // Test 3/5: katalog zachowuje kolejność i typy zagadek scenariusza.
    @Test
    void classicScenarioHasExpectedPuzzleTypes() {
        List<String> types = ScenarioCatalog.load(CLASSIC_ROOM_ID).puzzleSpecs().stream()
                .map(PuzzleSpec::type)
                .toList();

        assertEquals(List.of("logic", "lock", "search"), types);
    }

    // Test 4/5: katalog zwraca nazwy zagadek potrzebne do zbudowania pokoju.
    @Test
    void classicScenarioHasExpectedPuzzleNames() {
        List<String> names = ScenarioCatalog.load(CLASSIC_ROOM_ID).puzzleSpecs().stream()
                .map(PuzzleSpec::name)
                .toList();

        assertEquals(List.of("Szyfr Cezara", "Kłódka 4-cyfrowa", "Ukryty klucz"), names);
    }

    // Test 5/5: nieznany identyfikator zgłasza konkretny wyjątek domenowy.
    @Test
    void rejectsUnknownScenario() {
        assertThrows(ScenarioNotFoundException.class,
                () -> ScenarioCatalog.load("unknown-room"));
    }
}
