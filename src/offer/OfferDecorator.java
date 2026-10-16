package offer;

/**
 * week 1 — zastosowano: Decorator (baza dekoratorów)
 * Klasa bazowa, która opakowuje inna oferte i rozszerza jej zachowanie.
 * Dzięki temu dodatki można łączyc bez zmieniania oferty bazowej.
 */
public abstract class OfferDecorator implements Offer {
    private final Offer base;

    public OfferDecorator(Offer base) {
        this.base = base;
    }

    public Offer base() {
        return base;
    }
}
