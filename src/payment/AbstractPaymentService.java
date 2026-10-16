package payment;

/** LAB 3 — DIP (abstrakcja serwisu płatności). */
public abstract class AbstractPaymentService {
    private final PaymentGateway gateway;

    public AbstractPaymentService(PaymentGateway gateway) {
        this.gateway = gateway;
    }

    public void pay(String code) {
        gateway.charge(code);
    }
}
