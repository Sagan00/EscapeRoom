package payment;

/**
 * week 1 — zastosowano: Obsługa błędów (clean code)
 * Wyjątek, gdy wymagana jest płatność przed dalszym krokiem.
 * Dzięki temu blad ma czytelna nazwe i można go obsłużyc w jednym miejscu.
 */
public final class PaymentRequiredException extends RuntimeException {
    public PaymentRequiredException(String message) {
        super(message);
    }
}
