package room;

/**
 * week 1 — zastosowano: Adapter (docelowy interfejs)
 * Prosty kontrakt: jak otwierać drzwi w nowym kodzie.
 * Dzięki temu nowy kod korzysta ze starej klasy przez znany interfejs.
 */
public interface DoorControl {
    void open(String doorId);
}
