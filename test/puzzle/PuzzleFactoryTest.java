package puzzle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

/** LAB 4, punkt 5: pięć testów jednostkowych fabryki zagadek. */
class PuzzleFactoryTest {
    private final PuzzleFactory factory = new PuzzleFactory();

    // Test 1/5: typ logic tworzy LogicPuzzle.
    @Test
    void createsLogicPuzzle() {
        assertInstanceOf(LogicPuzzle.class, factory.create("logic", "Znaki"));
    }

    // Test 2/5: typ lock tworzy LockPuzzle.
    @Test
    void createsLockPuzzle() {
        assertInstanceOf(LockPuzzle.class, factory.create("lock", "Kłódka"));
    }

    // Test 3/5: typ search tworzy SearchPuzzle.
    @Test
    void createsSearchPuzzle() {
        assertInstanceOf(SearchPuzzle.class, factory.create("search", "Mapa"));
    }

    // Test 4/5: nazwa przekazana do fabryki trafia do produktu.
    @Test
    void preservesPuzzleName() {
        assertEquals("Szyfr", factory.create("logic", "Szyfr").name());
    }

    // Test 5/5: nieznany typ kończy się konkretnym wyjątkiem.
    @Test
    void rejectsUnknownPuzzleType() {
        assertThrows(UnknownPuzzleTypeException.class, () -> factory.create("maze", "Labirynt"));
    }
}
