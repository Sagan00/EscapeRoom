package game;

import java.util.ArrayList;
import java.util.List;

/**
 * week 2 — zastosowano: State
 * Główny obiekt sesji przekazuje operacje cyklu gry do aktualnego stanu.
 */
public final class GameSession {
    private final List<String> solved = new ArrayList<>();
    private SessionState state = new ReadyState();
    private int usedHints;

    public void start() {
        state.start(this);
    }

    public void finish() {
        state.finish(this);
    }

    void changeState(SessionState newState) {
        state = newState;
    }

    public void useHint() {
        state.useHint(this);
    }

    void recordHint() {
        usedHints++;
    }

    public void removeHint() {
        if (usedHints > 0) {
            usedHints--;
        }
    }

    public void solve(String puzzleName, int skill) {
        state.solve(this, puzzleName, skill);
    }

    void recordSolvedPuzzle(String puzzleName, int skill) {
        solved.add(puzzleName + " (skill=" + skill + ")");
    }

    public void unsolveLast() {
        if (solved.isEmpty()) {
            return;
        }
        solved.removeLast();
    }

    public String stateName() {
        return state.name();
    }

    public int usedHints() {
        return usedHints;
    }

    public int solvedCount() {
        return solved.size();
    }
}
