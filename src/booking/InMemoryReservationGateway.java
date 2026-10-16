package booking;

/** LAB 3 — DIP (implementacja interfejsu). */
public final class InMemoryReservationGateway implements ReservationGateway {
    @Override
    public void saveReservation(String code) {
        System.out.println("DIP: zapisano rezerwację " + code);
    }
}
