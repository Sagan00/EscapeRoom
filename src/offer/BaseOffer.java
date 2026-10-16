package offer;

import java.math.BigDecimal;

/**
 * week 1 — zastosowano: Decorator (obiekt bazowy)
 * Podstawowa oferta: cena startowa i nazwa bez dodatków.
 * Dzięki temu dodatki można łączyc bez zmieniania oferty bazowej.
 */
public final class BaseOffer implements Offer {
    private final BigDecimal price;

    public BaseOffer(BigDecimal price) {
        this.price = price;
    }

    @Override
    public BigDecimal price() {
        return price;
    }

    @Override
    public String label() {
        return "Oferta bazowa";
    }
}
