package venue;

/**
 * week 1 — zastosowano: Singleton
 * Jedna wspólna konfiguracja obiektu dostępna z całej aplikacji.
 * Dzięki temu aplikacja korzysta z jednej wspólnej konfiguracji.
 */
public final class VenueConfiguration {
    private static final VenueConfiguration INSTANCE = new VenueConfiguration("Escape Room City");
    private final String venueName;

    private VenueConfiguration(String venueName) {
        this.venueName = venueName;
    }

    public static VenueConfiguration instance() {
        return INSTANCE;
    }

    public String venueName() {
        return venueName;
    }
}
