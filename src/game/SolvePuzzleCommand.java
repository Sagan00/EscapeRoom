package game;

/**
 * week 2 — zastosowano: Command
 * Komenda rozwiązywania konkretnej zagadki w sesji.
 * Dzięki temu akcje można wykonać i cofnąć.
 */
public final class SolvePuzzleCommand implements SessionCommand {
    private final GameSession session;
    private final String puzzleName;
    private final int skill;

    public SolvePuzzleCommand(GameSession session, String puzzleName, int skill) {
        this.session = session;
        this.puzzleName = puzzleName;
        this.skill = skill;
    }

    @Override
    public void execute() {
        session.solve(puzzleName, skill);
    }

    @Override
    public void undo() {
        session.unsolveLast();
    }
}
