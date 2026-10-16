package offer;

import java.math.BigDecimal;

/**
 * week 1 — zastosowano: Decorator
 * Dodaje pakiet podpowiedzi do oferty bazowej.
 * Dzięki temu dodatki można łączyc bez zmieniania oferty bazowej.
 */
public final class HintPackDecorator extends OfferDecorator {
    private static final BigDecimal HINT_PACK_PRICE = new BigDecimal("20.00");

    public HintPackDecorator(Offer base) {
        super(base);
    }

    @Override
    public BigDecimal price() {
        return base().price().add(HINT_PACK_PRICE);
    }

    @Override
    public String label() {
        return base().label() + " + podpowiedzi";
    }
}
