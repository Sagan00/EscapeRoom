package booking;

import notification.AbstractNotificationService;
import payment.AbstractPaymentService;

/** LAB 3 — DIP (koordynacja przez abstrakcję). */
public final class BookingCoordinator {
    private final AbstractReservationService reservations;
    private final AbstractPaymentService payments;
    private final AbstractNotificationService notifications;

    public BookingCoordinator(
            AbstractReservationService reservations,
            AbstractPaymentService payments,
            AbstractNotificationService notifications
    ) {
        this.reservations = reservations;
        this.payments = payments;
        this.notifications = notifications;
    }

    public void process(String code) {
        reservations.create(code);
        payments.pay(code);
        notifications.notifyClient("Rezerwacja " + code + " gotowa.");
    }
}
