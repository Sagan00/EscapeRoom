package game;

/**
 * week 2 — zastosowano: State
 * Stan sesji: gra zakończona.
 * Dzięki temu zachowanie sesji zależy od jej aktualnego stanu.
 */
public final class FinishedState implements SessionState {
    @Override
    public String name() {
        return "FINISHED";
    }

    @Override
    public void start(GameSession session) {
        throw new IllegalStateException("Zakończonej sesji nie można uruchomić ponownie.");
    }

    @Override
    public void finish(GameSession session) {
        // Sesja jest już zakończona.
    }

    @Override
    public void useHint(GameSession session) {
        throw new IllegalStateException("Zakończonej sesji nie można zmieniać.");
    }

    @Override
    public void solve(GameSession session, String puzzleName, int skill) {
        throw new IllegalStateException("Zakończonej sesji nie można zmieniać.");
    }
}
