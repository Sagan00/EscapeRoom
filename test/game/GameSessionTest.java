package game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/** LAB 4, punkt 5: pięć testów jednostkowych zachowania sesji gry. */
class GameSessionTest {
    // Test 1/5: nowa sesja zaczyna w stanie READY.
    @Test
    void startsReady() {
        assertEquals("READY", new GameSession().stateName());
    }

    // Test 2/5: start przenosi sesję do stanu IN_GAME.
    @Test
    void startMovesSessionIntoGame() {
        GameSession session = new GameSession();
        session.start();
        assertEquals("IN_GAME", session.stateName());
    }

    // Test 3/5: podpowiedź jest dostępna podczas gry i zwiększa licznik.
    @Test
    void hintIncreasesHintCount() {
        GameSession session = activeSession();
        session.useHint();
        assertEquals(1, session.usedHints());
    }

    // Test 4/5: rozwiązanie zagadki jest zapisywane w sesji.
    @Test
    void solvingPuzzleIncreasesSolvedCount() {
        GameSession session = activeSession();
        session.solve("Szyfr", 7);
        assertEquals(1, session.solvedCount());
    }

    // Test 5/5: zakończona sesja nie pozwala na dalsze rozwiązywanie.
    @Test
    void finishedSessionRejectsPuzzleSolving() {
        GameSession session = activeSession();
        session.finish();
        assertThrows(IllegalStateException.class, () -> session.solve("Szyfr", 7));
    }

    private GameSession activeSession() {
        GameSession session = new GameSession();
        session.start();
        return session;
    }
}
