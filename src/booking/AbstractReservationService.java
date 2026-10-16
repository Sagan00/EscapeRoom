package booking;

/** LAB 3 — DIP (abstrakcja serwisu rezerwacji). */
public abstract class AbstractReservationService {
    private final ReservationGateway gateway;

    public AbstractReservationService(ReservationGateway gateway) {
        this.gateway = gateway;
    }

    public void create(String code) {
        gateway.saveReservation(code);
    }
}
