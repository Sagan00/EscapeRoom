package room;

import java.util.List;

/**
 * week 1 — zastosowano: Composite (interfejs komponentu)
 * Wspólna umowa dla elementu pojedynczego i grupy elementów.
 * Dzięki temu pojedyncza zagadka i grupa zagadek są obsługiwane tak samo.
 */
public interface RoomComponent {
    int puzzleCount();

    List<String> puzzleNames();
}
