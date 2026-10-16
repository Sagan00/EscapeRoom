package app;

import booking.AbstractReservationService;
import booking.BookingCoordinator;
import booking.InMemoryReservationGateway;
import booking.ReservationGateway;
import booking.ReservationService;
import game.GameMasterActions;
import game.GameMasterPanel;
import game.AbstractSessionReportGenerator;
import game.GameSession;
import game.GameStarter;
import game.GameStopper;
import game.HintProvider;
import game.SessionDetailedReport;
import game.SessionSummaryReport;
import notification.AbstractNotificationService;
import notification.ConsoleMessageGateway;
import notification.MessageGateway;
import notification.NotificationService;
import offer.BaseOffer;
import offer.HintPackDecorator;
import offer.OfferDecorator;
import offer.PhotoPackDecorator;
import payment.AbstractPaymentService;
import payment.ConsolePaymentGateway;
import payment.PaymentGateway;
import payment.PaymentRequiredException;
import payment.PaymentRules;
import payment.PaymentService;
import room.BasicDoorUnit;
import room.DoorUnit;
import room.SmartDoorUnit;
import scenario.ScenarioCatalog;
import scenario.ScenarioNotFoundException;
import staff.BookingReminderSender;
import staff.DoorSwitch;
import staff.LightSwitch;
import staff.ReceptionActions;
import staff.ReceptionPanel;
import staff.ReservationCanceller;
import staff.ReservationCreator;
import staff.TechnicianActions;
import staff.TechnicianPanel;
import team.InvalidTeamSizeException;
import team.TeamRules;

import java.math.BigDecimal;

/** LAB 4, punkt 4a: wydzielony punkt uruchamiania demonstracji LAB 3. */
public final class DesignPrinciplesDemonstration {
    private static final String RESERVATION_FOR_DIP = "RES-102";
    private static final String RESERVATION_FOR_ISP = "RES-103";
    private static final String TECHNICIAN_ROOM_ID = "A-02";
    private static final String UNKNOWN_SCENARIO_ID = "unknown-room";
    private static final BigDecimal LSP_BASE_OFFER_PRICE = new BigDecimal("100.00");

    public void run() {
        demonstrateLsp();
        demonstrateDip();
        demonstrateIsp();
        demonstrateExceptions();
    }

    // LSP: trzy typy bazowe mogą przyjąć obiekty klas pochodnych przez wspólny kontrakt.
    private void demonstrateLsp() {
        DoorUnit regularDoor = new BasicDoorUnit();
        DoorUnit smartDoor = new SmartDoorUnit();
        System.out.println("LSP door: " + regularDoor.status() + " / " + smartDoor.status());

        OfferDecorator hintOffer = new HintPackDecorator(new BaseOffer(LSP_BASE_OFFER_PRICE));
        OfferDecorator photoOffer = new PhotoPackDecorator(new BaseOffer(LSP_BASE_OFFER_PRICE));
        System.out.println("LSP offer: " + hintOffer.label() + " = " + hintOffer.price()
                + " / " + photoOffer.label() + " = " + photoOffer.price());

        AbstractSessionReportGenerator summaryReport = new SessionSummaryReport();
        AbstractSessionReportGenerator detailedReport = new SessionDetailedReport();
        GameSession session = new GameSession();
        System.out.println("LSP report: " + summaryReport.generate(session).replace('\n', ' ')
                + " / " + detailedReport.generate(session).replace('\n', ' '));
    }

    // DIP: koordynator dostaje serwisy zbudowane na trzech bramkach interfejsów.
    private void demonstrateDip() {
        ReservationGateway reservations = new InMemoryReservationGateway();
        PaymentGateway payments = new ConsolePaymentGateway();
        MessageGateway messages = new ConsoleMessageGateway();
        AbstractReservationService reservationService = new ReservationService(reservations);
        AbstractPaymentService paymentService = new PaymentService(payments);
        AbstractNotificationService notificationService = new NotificationService(messages);
        BookingCoordinator coordinator = new BookingCoordinator(
                reservationService, paymentService, notificationService
        );
        coordinator.process(RESERVATION_FOR_DIP);
    }

    private void demonstrateIsp() {
        demonstrateReceptionInterfaces();
        demonstrateGameMasterInterfaces();
        demonstrateTechnicianInterfaces();
        System.out.println("ISP: szerokie panele łączą małe interfejsy.");
    }

    // ISP: klient recepcji korzysta z oddzielnych, wąskich kontraktów.
    private void demonstrateReceptionInterfaces() {
        ReceptionPanel reception = new ReceptionActions();
        // Jeden panel spełnia trzy małe kontrakty wymagane przez klienta recepcji.
        runReceptionClient(reception, reception, reception);
    }

    // Sygnatura klienta pokazuje dokładnie, z jakich umiejętności recepcji korzysta.
    private void runReceptionClient(
            ReservationCreator creator,
            ReservationCanceller canceller,
            BookingReminderSender reminderSender
    ) {
        creator.create(RESERVATION_FOR_ISP);
        canceller.cancel(RESERVATION_FOR_ISP);
        reminderSender.remind(RESERVATION_FOR_ISP);
    }

    // ISP: klient prowadzącego wybiera tylko kontrakt potrzebnej operacji.
    private void demonstrateGameMasterInterfaces() {
        GameMasterPanel gameMaster = new GameMasterActions();
        // Panel prowadzącego jest przekazany przez trzy potrzebne kontrakty.
        runGameMasterClient(gameMaster, gameMaster, gameMaster);
    }

    // Klient gry nie potrzebuje zależności od całego GameMasterPanel.
    private void runGameMasterClient(GameStarter starter, GameStopper stopper, HintProvider hints) {
        starter.startGame(RESERVATION_FOR_ISP);
        hints.giveHint("Szukaj relacji między znakami.");
        stopper.stopGame(RESERVATION_FOR_ISP);
    }

    // ISP: sterowanie drzwiami i światłem ma osobne typy interfejsów.
    private void demonstrateTechnicianInterfaces() {
        TechnicianPanel technician = new TechnicianActions();
        // Klient techniczny rozdziela sterowanie drzwiami od sterowania światłem.
        runTechnicianClient(technician, technician);
    }

    // Parametry ograniczają wymagane możliwości do dwóch małych interfejsów.
    private void runTechnicianClient(DoorSwitch doors, LightSwitch lights) {
        doors.open(TECHNICIAN_ROOM_ID);
        doors.close(TECHNICIAN_ROOM_ID);
        lights.toggleLight(TECHNICIAN_ROOM_ID);
    }

    private void demonstrateExceptions() {
        demonstrateTeamSizeException();
        demonstratePaymentException();
        demonstrateScenarioException();
    }

    private void demonstrateTeamSizeException() {
        try {
            TeamRules.validateSize(TeamRules.MINIMUM_SIZE - 1);
        } catch (InvalidTeamSizeException exception) {
            System.out.println("Wyjątek #1: " + exception.getMessage());
        }
    }

    private void demonstratePaymentException() {
        try {
            //noinspection DataFlowIssue — false celowo demonstruje wyjątek reguły płatności.
            PaymentRules.requirePaid(false);
        } catch (PaymentRequiredException exception) {
            System.out.println("Wyjątek #2: " + exception.getMessage());
        }
    }

    private void demonstrateScenarioException() {
        try {
            ScenarioCatalog.load(UNKNOWN_SCENARIO_ID);
        } catch (ScenarioNotFoundException exception) {
            System.out.println("Wyjątek #3: " + exception.getMessage());
        }
    }
}
