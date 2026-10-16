package payment;

/** LAB 3 — DIP (klasa pochodna). */
public final class PaymentService extends AbstractPaymentService {
    public PaymentService(PaymentGateway gateway) {
        super(gateway);
    }
}
