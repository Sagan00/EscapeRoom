package game;

/**
 * week 2 — zastosowano: Command
 * Komenda użycia podpowiedzi w sesji.
 * Dzięki temu akcje można wykonać i cofnąć.
 */
public final class UseHintCommand implements SessionCommand {
    private final GameSession session;

    public UseHintCommand(GameSession session) {
        this.session = session;
    }

    @Override
    public void execute() {
        session.useHint();
    }

    @Override
    public void undo() {
        session.removeHint();
    }
}
