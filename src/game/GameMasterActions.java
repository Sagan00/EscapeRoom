package game;

/** LAB 3 — ISP (implementacja małych interfejsów). */
public final class GameMasterActions implements GameMasterPanel {
    @Override
    public void startGame(String code) {
        System.out.println("ISP: start " + code);
    }

    @Override
    public void stopGame(String code) {
        System.out.println("ISP: stop " + code);
    }

    @Override
    public void giveHint(String hint) {
        System.out.println("ISP: hint -> " + hint);
    }
}
