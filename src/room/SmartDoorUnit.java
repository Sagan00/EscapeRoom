package room;

/** LAB 3 — LSP (hierarchia drzwi). */
public final class SmartDoorUnit extends DoorUnit {
    @Override
    public String status() {
        return "Door closed (sensor check)";
    }
}
