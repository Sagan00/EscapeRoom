package notification;

/** LAB 3 — DIP (implementacja interfejsu). */
public final class ConsoleMessageGateway implements MessageGateway {
    @Override
    public void send(String message) {
        System.out.println("DIP: " + message);
    }
}
