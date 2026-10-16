package app;

import game.CommandHistory;
import game.GameSession;
import game.SessionSummaryReport;
import game.SolvePuzzleCommand;
import game.UseHintCommand;
import puzzle.PuzzleRack;
import room.RoomGroup;

import java.util.Iterator;

/** LAB 4, punkt 4a: przebieg gry wydzielony z klasy startowej. */
public final class GameSessionDemonstration {
    public void run(RoomGroup room, int teamSkill) {
        GameSession session = new GameSession();
        session.start();
        CommandHistory history = executePuzzleCommands(room, session, teamSkill);
        history.undoLast();
        System.out.println("Po cofnięciu komendy: rozwiązane=" + session.solvedCount());
        session.finish();
        System.out.println(new SessionSummaryReport().generate(session));
    }

    private CommandHistory executePuzzleCommands(RoomGroup room, GameSession session, int skill) {
        CommandHistory history = new CommandHistory();
        history.execute(new UseHintCommand(session));
        PuzzleRack rack = new PuzzleRack(room.puzzleNames());
        Iterator<String> iterator = rack.iterator();
        while (iterator.hasNext()) {
            String puzzle = iterator.next();
            history.execute(new SolvePuzzleCommand(session, puzzle, skill));
        }
        return history;
    }
}
