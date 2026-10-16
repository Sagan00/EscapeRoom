package payment;

/**
 * week 1 — zastosowano: Reguły biznesowe
 * Sprawdza, czy warunki płatności są spelnione.
 * Dzięki temu zasada jest sprawdzana w jednym miejscu przed dalszym dzialaniem.
 */
public final class PaymentRules {
    private PaymentRules() {
    }

    public static void requirePaid(boolean paid) {
        if (!paid) {
            throw new PaymentRequiredException("Rezerwacja musi być opłacona.");
        }
    }
}
