package game;

import java.util.ArrayList;
import java.util.List;

/**
 * week 2 — zastosowano: Command
 * Uruchamia komendy i trzyma historię, żeby można było cofnąć ostatnia.
 * Dzięki temu akcje można wykonać i cofnąć.
 */
public final class CommandHistory {
    private final List<SessionCommand> history = new ArrayList<>();

    public void execute(SessionCommand command) {
        command.execute();
        history.add(command);
    }

    public void undoLast() {
        if (history.isEmpty()) {
            return;
        }
        SessionCommand command = history.removeLast();
        command.undo();
    }
}
