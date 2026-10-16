package booking;

/*
  week 1 — zastosowano: Dane potrzebne do podsumowania rezerwacji
  Łączy drużyne i oferte w jednym obiekcie szczegółów.
  Dzięki temu drużyna i oferta są przekazywane razem do BookingFacade.
 */

import offer.Offer;
import team.Team;

public record BookingDetails(Team team, Offer offer) {
}
