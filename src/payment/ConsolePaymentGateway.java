package payment;

/** LAB 3 — DIP (implementacja interfejsu). */
public final class ConsolePaymentGateway implements PaymentGateway {
    @Override
    public void charge(String code) {
        System.out.println("DIP: opłacono " + code);
    }
}
