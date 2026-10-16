package room;

/**
 * week 1 — zastosowano: Adapter
 * Dostosowuje stary kontroler drzwi do nowego interfejsu DoorControl.
 * Dzięki temu nowy kod korzysta ze starej klasy przez znany interfejs.
 */
public final class DoorAdapter implements DoorControl {
    private final LegacyDoorController legacy;

    public DoorAdapter(LegacyDoorController legacy) {
        this.legacy = legacy;
    }

    @Override
    public void open(String doorId) {
        String response = legacy.unlockLegacy(doorId);
        if (!response.startsWith("legacy-open:")) {
            throw new DoorOpenException("Nie można otworzyć drzwi: " + doorId);
        }
    }
}
