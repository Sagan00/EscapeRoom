package app;

import java.math.BigDecimal;

import booking.BookingDetails;
import booking.BookingFacade;
import offer.BaseOffer;
import offer.HintPackDecorator;
import offer.Offer;
import offer.PhotoPackDecorator;
import puzzle.Puzzle;
import puzzle.PuzzleFactory;
import puzzle.PuzzleSpec;
import room.DoorAdapter;
import room.DoorControl;
import room.LegacyDoorController;
import room.PuzzleRoomLeaf;
import room.RoomGroup;
import scenario.Scenario;
import scenario.ScenarioCatalog;
import team.Team;
import venue.VenueConfiguration;

/**
 * LAB 4, punkt 4a: klasa startowa składa istniejące elementy aplikacji.
 * Szczegóły przebiegu gry i demonstracje LAB 3 są delegowane do używanych klas.
 */
public final class EscapeRoomApplication {
    private static final String SCENARIO_ID = "classic-room";
    private static final String RESERVATION_CODE = "RES-101";
    private static final String DOOR_ID = "A-01";
    private static final int EXAMPLE_TEAM_SIZE = 4;
    private static final int EXAMPLE_TEAM_SKILL = 7;
    private static final BigDecimal BASE_OFFER_PRICE = new BigDecimal("120.00");

    private final VenueConfiguration config = VenueConfiguration.instance();
    private final BookingFacade bookingFacade = buildBookingFacade();
    private final GameSessionDemonstration gameDemo = new GameSessionDemonstration();
    private final DesignPrinciplesDemonstration principlesDemo = new DesignPrinciplesDemonstration();

    // Uruchamia aplikację i jej demonstracje.
    public void run() {
        printHeader();
        Team team = createTeam();
        RoomGroup room = createRoom();
        Offer offer = createOffer();
        executeBooking(team, offer);
        gameDemo.run(room, team.skill());
        principlesDemo.run();
    }

    // Singleton: wyświetla nazwę obiektu z jego wspólnej konfiguracji.
    private void printHeader() {
        System.out.println("Escape Room: " + config.venueName());
    }

    // Builder: krok po kroku tworzy i waliduje na koniec rozmiar drużyny.
    private Team createTeam() {
        return new Team.Builder()
                .name("Alpha")
                .size(EXAMPLE_TEAM_SIZE)
                .skill(EXAMPLE_TEAM_SKILL)
                .paid(true)
                .build();
    }

    // Wczytuje scenariusz i przygotowuje kompozyt pokoju.
    private RoomGroup createRoom() {
        Scenario scenario = ScenarioCatalog.load(SCENARIO_ID);
        PuzzleFactory factory = new PuzzleFactory();
        RoomGroup room = new RoomGroup(scenario.name());
        for (PuzzleSpec spec : scenario.puzzleSpecs()) {
            addPuzzle(room, factory, spec);
        }
        return room;
    }

    // Simple Factory tworzy zagadkę, a Composite dodaje ją do pokoju.
    private void addPuzzle(RoomGroup room, PuzzleFactory factory, PuzzleSpec spec) {
        Puzzle puzzle = factory.create(spec.type(), spec.name());
        room.add(new PuzzleRoomLeaf(puzzle));
    }

    // Decorator: składa ofertę bazową z pakietami dodatków.
    private Offer createOffer() {
        Offer base = new BaseOffer(BASE_OFFER_PRICE);
        Offer hints = new HintPackDecorator(base);
        return new PhotoPackDecorator(hints);
    }

    // Zleca opłaconą rezerwację fasadzie, która otwiera drzwi i pokazuje podsumowanie.
    private void executeBooking(Team team, Offer offer) {
        bookingFacade.complete(RESERVATION_CODE, DOOR_ID, new BookingDetails(team, offer));
    }

    // Adapter dopasowuje stary kontroler drzwi do interfejsu fasady.
    private BookingFacade buildBookingFacade() {
        DoorControl doorControl = new DoorAdapter(new LegacyDoorController());
        return new BookingFacade(doorControl);
    }
}
