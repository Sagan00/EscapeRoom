package offer;

import java.math.BigDecimal;

/**
 * week 1 — zastosowano: Decorator (interfejs komponentu)
 * Wspólna umowa dla oferty i jej dekoratorów.
 * Dzięki temu dodatki można łączyc bez zmieniania oferty bazowej.
 */
public interface Offer {
    BigDecimal price();

    String label();
}
