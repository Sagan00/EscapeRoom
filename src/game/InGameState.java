package game;

/**
 * week 2 — zastosowano: State
 * Stan sesji: gra w trakcie.
 * Dzięki temu zachowanie sesji zależy od jej aktualnego stanu.
 */
public final class InGameState implements SessionState {
    @Override
    public String name() {
        return "IN_GAME";
    }

    @Override
    public void start(GameSession session) {
        // Sesja już trwa, więc ponowne uruchomienie nie zmienia jej stanu.
    }

    @Override
    public void finish(GameSession session) {
        session.changeState(new FinishedState());
    }

    @Override
    public void useHint(GameSession session) {
        session.recordHint();
    }

    @Override
    public void solve(GameSession session, String puzzleName, int skill) {
        session.recordSolvedPuzzle(puzzleName, skill);
    }
}
