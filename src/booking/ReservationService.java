package booking;

/** LAB 3 — DIP (klasa pochodna). */
public final class ReservationService extends AbstractReservationService {
    public ReservationService(ReservationGateway gateway) {
        super(gateway);
    }
}
