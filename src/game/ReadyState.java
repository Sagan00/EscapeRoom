package game;

/**
 * week 2 — zastosowano: State
 * Stan sesji: gotowa do startu.
 * Dzięki temu zachowanie sesji zależy od jej aktualnego stanu.
 */
public final class ReadyState implements SessionState {
    @Override
    public String name() {
        return "READY";
    }

    @Override
    public void start(GameSession session) {
        session.changeState(new InGameState());
    }

    @Override
    public void finish(GameSession session) {
        throw new IllegalStateException("Nie można zakończyć gry przed jej rozpoczęciem.");
    }

    @Override
    public void useHint(GameSession session) {
        throw new IllegalStateException("Podpowiedzi można użyć tylko w trakcie gry.");
    }

    @Override
    public void solve(GameSession session, String puzzleName, int skill) {
        throw new IllegalStateException("Zagadki można rozwiązywać tylko w trakcie gry.");
    }
}
