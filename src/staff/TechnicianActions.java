package staff;

/** LAB 3 — ISP (implementacja małych interfejsów). */
public final class TechnicianActions implements TechnicianPanel {
    @Override
    public void open(String doorId) {
        System.out.println("ISP: open " + doorId);
    }

    @Override
    public void close(String doorId) {
        System.out.println("ISP: close " + doorId);
    }

    @Override
    public void toggleLight(String roomId) {
        System.out.println("ISP: light " + roomId);
    }
}
