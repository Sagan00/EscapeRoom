package notification;

/** LAB 3 — DIP (abstrakcja serwisu powiadomień). */
public abstract class AbstractNotificationService {
    private final MessageGateway gateway;

    public AbstractNotificationService(MessageGateway gateway) {
        this.gateway = gateway;
    }

    public void notifyClient(String message) {
        gateway.send(message);
    }
}
