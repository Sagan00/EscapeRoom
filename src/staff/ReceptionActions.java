package staff;

/** LAB 3 — ISP (implementacja małych interfejsów). */
public final class ReceptionActions implements ReceptionPanel {
    @Override
    public void create(String code) {
        System.out.println("ISP: create " + code);
    }

    @Override
    public void cancel(String code) {
        System.out.println("ISP: cancel " + code);
    }

    @Override
    public void remind(String code) {
        System.out.println("ISP: remind " + code);
    }
}
