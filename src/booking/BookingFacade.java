package booking;

import offer.Offer;
import payment.PaymentRules;
import room.DoorControl;
import team.Team;

import java.util.Objects;

/**
 * week 1 — zastosowano: Facade
 * Jeden prosty punkt wejścia do finalizacji rezerwacji.
 * Dzięki temu klient wykonuje kilka kroków przez jedno proste wywołanie.
 */
public final class BookingFacade {
    private final DoorControl doors;

    public BookingFacade(DoorControl doors) {
        this.doors = Objects.requireNonNull(doors);
    }

    public void complete(String reservationCode, String doorId, BookingDetails details) {
        Objects.requireNonNull(details, "details");
        PaymentRules.requirePaid(details.team().paid());
        doors.open(doorId);
        printBookingSummary(reservationCode, details);
    }

    private void printBookingSummary(String reservationCode, BookingDetails details) {
        System.out.println("Rezerwacja " + reservationCode + " aktywna.");
        Team team = details.team();
        Offer offer = details.offer();
        System.out.println("Drużyna: " + team.name() + ", osoby=" + team.size());
        System.out.println("Oferta: " + offer.label() + ", cena=" + offer.price());
    }
}
