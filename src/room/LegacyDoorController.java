package room;

/**
 * week 1 — zastosowano: Adapter (stary system)
 * Stary kontroler drzwi, który trzeba dostosować adapterem.
 * Dzięki temu nowy kod korzysta ze starej klasy przez znany interfejs.
 */
public final class LegacyDoorController {
    public String unlockLegacy(String doorId) {
        return "legacy-open:" + doorId;
    }
}
