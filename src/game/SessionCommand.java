package game;

/**
 * week 2 — zastosowano: Command (interfejs)
 * Wspólna umowa dla komend wykonywanych na sesji.
 * Dzięki temu akcje można wykonać i cofnąć.
 */
public interface SessionCommand {
    void execute();

    void undo();
}
