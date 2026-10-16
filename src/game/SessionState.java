package game;

/**
 * week 2 — zastosowano: State (interfejs)
 * Wspólna umowa dla stanów sesji gry.
 * Dzięki temu zachowanie sesji zależy od jej aktualnego stanu.
 */
public interface SessionState {
    String name();

    void start(GameSession session);

    void finish(GameSession session);

    void useHint(GameSession session);

    void solve(GameSession session, String puzzleName, int skill);
}
