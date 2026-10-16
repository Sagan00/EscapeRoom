package offer;

import java.math.BigDecimal;

/**
 * week 1 — zastosowano: Decorator
 * Dodaje pakiet zdjęć do oferty bazowej.
 * Dzięki temu dodatki można łączyc bez zmieniania oferty bazowej.
 */
public final class PhotoPackDecorator extends OfferDecorator {
    private static final BigDecimal PHOTO_PACK_PRICE = new BigDecimal("15.00");

    public PhotoPackDecorator(Offer base) {
        super(base);
    }

    @Override
    public BigDecimal price() {
        return base().price().add(PHOTO_PACK_PRICE);
    }

    @Override
    public String label() {
        return base().label() + " + pakiet foto";
    }
}
