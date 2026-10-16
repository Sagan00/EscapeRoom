package room;

import java.util.ArrayList;
import java.util.List;

/**
 * week 1 — zastosowano: Composite (wezel)
 * Pokój może zawierać wiele zagadek.
 * Dzięki temu pojedyncza zagadka i grupa zagadek są obsługiwane tak samo.
 */
public final class RoomGroup implements RoomComponent {
    private final String name;
    private final List<RoomComponent> children = new ArrayList<>();

    public RoomGroup(String name) {
        this.name = name;
    }

    public void add(RoomComponent child) {
        children.add(child);
    }

    public String name() {
        return name;
    }

    @Override
    public int puzzleCount() {
        int count = 0;
        for (RoomComponent child : children) {
            count += child.puzzleCount();
        }
        return count;
    }

    @Override
    public List<String> puzzleNames() {
        List<String> names = new ArrayList<>();
        for (RoomComponent child : children) {
            names.addAll(child.puzzleNames());
        }
        return names;
    }
}
