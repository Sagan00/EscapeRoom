package lab4;

import java.math.BigDecimal;

import booking.AbstractReservationService;
import booking.BookingCoordinator;
import booking.BookingDetails;
import booking.BookingFacade;
import booking.InMemoryReservationGateway;
import booking.ReservationGateway;
import booking.ReservationService;
import game.CommandHistory;
import game.GameMasterActions;
import game.GameSession;
import game.SessionSummaryReport;
import game.SolvePuzzleCommand;
import game.UseHintCommand;
import notification.AbstractNotificationService;
import notification.ConsoleMessageGateway;
import notification.MessageGateway;
import notification.NotificationService;
import offer.BaseOffer;
import offer.HintPackDecorator;
import offer.Offer;
import offer.PhotoPackDecorator;
import payment.AbstractPaymentService;
import payment.ConsolePaymentGateway;
import payment.PaymentGateway;
import payment.PaymentRequiredException;
import payment.PaymentRules;
import payment.PaymentService;
import puzzle.Puzzle;
import puzzle.PuzzleFactory;
import puzzle.PuzzleRack;
import puzzle.PuzzleSpec;
import room.BasicDoorUnit;
import room.DoorAdapter;
import room.DoorControl;
import room.DoorUnit;
import room.LegacyDoorController;
import room.PuzzleRoomLeaf;
import room.RoomGroup;
import room.SmartDoorUnit;
import scenario.Scenario;
import scenario.ScenarioCatalog;
import scenario.ScenarioNotFoundException;
import staff.ReceptionActions;
import staff.TechnicianActions;
import team.InvalidTeamSizeException;
import team.Team;
import team.TeamRules;
import venue.VenueConfiguration;

/**
 * LAB 4, punkt 4: celowo niepoprawna wersja istniejącej klasy startowej.
 * Zawiera te same operacje i typy co aplikacja po refaktoryzacji.
 * Nie jest uruchamiana; służy wyłącznie do porównania przed/po.
 */
public final class BadEscapeRoomApplication {
    // Jedna metoda tworzy rezerwację, uruchamia grę i pokazuje LAB 3.
    // Dziewięć argumentów bez nazw znaczących jest trudnych do zapamiętania.
    public void doIt(String a, int b, int c, boolean d, BigDecimal e,
                     String f, String g, String h, int i) {
        // Sprawdź rozmiar ręcznie, mimo że istnieje TeamRules.
        if (b < 2 || b > 6) {
            throw new IllegalArgumentException("Bad team size");
        }
        Team t = new Team.Builder().name(a).size(b).skill(c).paid(d).build();

        // Konfiguracja singletona i wypisywanie nagłówka to kolejna rola.
        System.out.println("Escape Room: " + VenueConfiguration.instance().venueName());

        // Oferta składa się z dekoratorów, ale cała konfiguracja trafia do tej klasy.
        Offer o = new PhotoPackDecorator(
                new HintPackDecorator(new BaseOffer(e)));

        // Adapter, fasada, kontrola płatności i obsługa rezerwacji są związane razem.
        DoorControl dc = new DoorAdapter(new LegacyDoorController());
        BookingFacade bf = new BookingFacade(dc);
        PaymentRules.requirePaid(t.paid());
        bf.complete(f, g, new BookingDetails(t, o));
        System.out.println("Potwierdzenie " + f + " dla " + t.name()
                + ", osób=" + t.size() + ", oferta=" + o.label()
                + ", cena=" + o.price());

        // Załadowanie scenariusza, tworzenie zagadek i budowa pokoju.
        Scenario s = ScenarioCatalog.load(h);
        PuzzleFactory pf = new PuzzleFactory();
        RoomGroup rg = new RoomGroup(s.name());
        for (PuzzleSpec ps : s.puzzleSpecs()) {
            Puzzle p = pf.create(ps.type(), ps.name());
            rg.add(new PuzzleRoomLeaf(p));
        }

        // Ta sama metoda steruje jeszcze całą sesją gry.
        GameSession gs = new GameSession();
        gs.start();
        CommandHistory ch = new CommandHistory();
        ch.execute(new UseHintCommand(gs));
        for (String pn : new PuzzleRack(rg.puzzleNames())) {
            ch.execute(new SolvePuzzleCommand(gs, pn, i));
        }
        ch.undoLast();
        System.out.println("Po cofnięciu komendy: rozwiązane=" + gs.solvedCount());
        gs.finish();
        System.out.println(new SessionSummaryReport().generate(gs));

        // Demonstracja LSP trafia do metody odpowiedzialnej za całą aplikację.
        DoorUnit du1 = new BasicDoorUnit();
        DoorUnit du2 = new SmartDoorUnit();
        System.out.println("LSP door: " + du1.status() + " / " + du2.status());

        // Tworzenie bramek i serwisów DIP również dopisano w tym miejscu.
        ReservationGateway r1 = new InMemoryReservationGateway();
        PaymentGateway p1 = new ConsolePaymentGateway();
        MessageGateway m1 = new ConsoleMessageGateway();
        AbstractReservationService ar = new ReservationService(r1);
        AbstractPaymentService ap = new PaymentService(p1);
        AbstractNotificationService an = new NotificationService(m1);
        new BookingCoordinator(ar, ap, an).process("RES-102");

        // Obsługa ról personelu i ISP to kolejny niezależny fragment.
        ReceptionActions ra = new ReceptionActions();
        GameMasterActions ga = new GameMasterActions();
        TechnicianActions ta = new TechnicianActions();
        ra.create("RES-103");
        ra.cancel("RES-103");
        ra.remind("RES-103");
        ga.startGame("RES-103");
        ga.giveHint("Szukaj relacji między znakami.");
        ga.stopGame("RES-103");
        ta.open("A-02");
        ta.close("A-02");
        ta.toggleLight("A-02");

        // Łapanie wyjątków i prezentacja komunikatów też należy do tej metody.
        try {
            TeamRules.validateSize(1);
        } catch (InvalidTeamSizeException ex) {
            System.out.println("Wyjątek #1: " + ex.getMessage());
        }
        try {
            PaymentRules.requirePaid(false);
        } catch (PaymentRequiredException ex) {
            System.out.println("Wyjątek #2: " + ex.getMessage());
        }
        try {
            ScenarioCatalog.load("unknown-room");
        } catch (ScenarioNotFoundException ex) {
            System.out.println("Wyjątek #3: " + ex.getMessage());
        }
    }
}
