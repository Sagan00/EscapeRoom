# Escape Room — przewodnik po projekcie

To jest prosta aplikacja konsolowa, która składa przykładową rezerwację Escape Room i uruchamia demonstracje wzorców projektowych z LAB 1–3. Kod jest napisany w zwykłej Javie 21.

## Uruchomienie

Projekt uruchamiaj bezpośrednio z IntelliJ IDEA:

1. Otwórz projekt `EscapeRoom` w IntelliJ IDEA.
2. Otwórz `src/app/EscapeRoomApp.java`.
3. Uruchom metodę `main()` przyciskiem Run obok jej deklaracji albo wybierz konfigurację `EscapeRoomApp`.

IntelliJ kompiluje pliki źródłowe z katalogu `src` i zapisuje wynikowe pliki `.class` w katalogu wyjściowym modułu (`out`). Nie uruchamiaj ani nie edytuj plików z `out` ręcznie.

## LAB 1 — wzorce tworzenia i składania obiektów

- **Factory:** `PuzzleFactory` wybiera konkretną klasę zagadki. Kod korzystający z fabryki operuje na typie `Puzzle`, więc nie musi sam tworzyć każdego rodzaju zagadki.
- **Singleton:** `VenueConfiguration` ma prywatny konstruktor i jedną statyczną instancję. Aplikacja pobiera ją przez `instance()`.
- **Builder:** `Team.Builder` pozwala nazwać kolejne wartości podczas budowania drużyny. `build()` waliduje rozmiar drużyny przez `TeamRules` i tworzy `Team`.
- **Adapter:** `DoorAdapter` dopasowuje `LegacyDoorController` do `DoorControl`, którego wymaga `BookingFacade`.
- **Composite:** `RoomGroup` i `PuzzleRoomLeaf` implementują `RoomComponent`. Dzięki temu pokój może traktować grupę zagadek i pojedynczą zagadkę przez ten sam kontrakt.
- **Decorator:** `HintPackDecorator` i `PhotoPackDecorator` owijają `Offer`. Każdy dodaje swoją cenę i opis, zachowując kontrakt oferty.
- **Facade:** `BookingFacade.complete()` ukrywa kilka kroków finalizacji za jednym wywołaniem.

## LAB 2 — wzorce zachowania

- **Command:** akcja jest obiektem. `CommandHistory` uruchamia `SolvePuzzleCommand` lub `UseHintCommand` i może wywołać `undo()` ostatniej komendy.
- **Iterator:** `PuzzleRack` pozwala przechodzić po zagadkach w pętli `for`, bez dostępu do wewnętrznej listy.
- **State:** `GameSession` deleguje rozpoczęcie, zakończenie, podpowiedź i rozwiązanie zagadki do aktualnego stanu. `ReadyState`, `InGameState` i `FinishedState` definiują dozwolone zachowania oraz przejścia.
- **Template Method:** `AbstractSessionReportGenerator.generate()` ustala kolejność tworzenia raportu, a `SessionSummaryReport` dostarcza jego nagłówek, treść i zakończenie.

## LAB 3 — zasady projektowe

Poniższe przykłady znajdują się w działającym kodzie `src`; celowo niepoprawna klasa `src/lab4/BadEscapeRoomApplication.java` jest wyjątkiem i służy wyłącznie do porównania w LAB 4.

Najłatwiej zacząć od `DesignPrinciplesDemonstration.run()`: wywołuje kolejno demonstracje LSP, DIP, ISP i wyjątków. Każda z nich składa się z krótkich metod o nazwach opisujących przykład. Komentarze `LSP`, `DIP` i `ISP` w tej klasie wskazują, na co zwrócić uwagę.

### 1. LSP — podstawienie Liskov

Demonstracja obejmuje trzy typy bazowe i ich klasy pochodne: `DoorUnit` (`BasicDoorUnit`, `SmartDoorUnit`), `OfferDecorator` (`HintPackDecorator`, `PhotoPackDecorator`) oraz `AbstractSessionReportGenerator` (`SessionSummaryReport`, `SessionDetailedReport`). W `DesignPrinciplesDemonstration.demonstrateLsp()` obiekty pochodne są przypisywane do zmiennych typów bazowych, a następnie używane przez wspólne metody (`status`, `price`/`label`, `generate`). Można więc podmienić implementację bez zmiany kodu klienta.

### 2. DIP — odwrócenie zależności

Projekt zawiera trzy abstrakcyjne serwisy. Każdy przyjmuje interfejs dostępu do usługi zamiast zależeć od konkretnej implementacji:

| Abstrakcyjny serwis | Interfejs zależności | Konkretna implementacja serwisu | Implementacja interfejsu |
|---|---|---|---|
| `AbstractReservationService` | `ReservationGateway` | `ReservationService` | `InMemoryReservationGateway` |
| `AbstractPaymentService` | `PaymentGateway` | `PaymentService` | `ConsolePaymentGateway` |
| `AbstractNotificationService` | `MessageGateway` | `NotificationService` | `ConsoleMessageGateway` |

`BookingCoordinator` przyjmuje trzy abstrakcyjne serwisy w konstruktorze. `DesignPrinciplesDemonstration.demonstrateDip()` składa konkretne bramki, serwisy i koordynator. Koordynator nie zależy od klas konsolowych.

### 3. ISP — segregacja interfejsów

Trzy szerokie interfejsy ról składają się z mniejszych kontraktów:

| Interfejs roli | Wąskie interfejsy |
|---|---|
| `ReceptionPanel` | `ReservationCreator`, `ReservationCanceller`, `BookingReminderSender` |
| `GameMasterPanel` | `GameStarter`, `GameStopper`, `HintProvider` |
| `TechnicianPanel` | `DoorSwitch`, `LightSwitch` |

`ReceptionActions`, `GameMasterActions` i `TechnicianActions` implementują odpowiednie interfejsy roli. `DesignPrinciplesDemonstration` przekazuje je do metod-klientów, których parametry mają typy wąskich interfejsów (`runReceptionClient`, `runGameMasterClient`, `runTechnicianClient`). Dzięki temu klient jest zależny tylko od potrzebnych operacji, a klasy akcji nadal mogą implementować pełny panel roli.

### 4–8. Czytelność i podział odpowiedzialności

- Nazwy klas, metod i zmiennych w działającym kodzie opisują ich rolę, np. `BookingCoordinator.process`, `PuzzleFactory.create` i `reservationCode`. Niejasne nazwy w klasie `BadEscapeRoomApplication` są celowe i należą do przykładu „przed” z LAB 4.
- Metody działającego kodu są krótkie (do 20 linii treści); dłuższy przepływ rozbito na metody pomocnicze. Przykładem jest `EscapeRoomApplication.run()`, które koordynuje kroki przez `createTeam`, `createRoom`, `createOffer` i `executeBooking`.
- Klasy mają wydzielone role: `BookingFacade` finalizuje rezerwację, `GameSessionDemonstration` uruchamia przebieg gry, a `DesignPrinciplesDemonstration` prezentuje zasady LAB 3.
- Metody pozostają na jednym poziomie szczegółowości i delegują szczegóły niżej: `EscapeRoomApplication.run()` wywołuje kroki aplikacji, a `GameSessionDemonstration.run()` deleguje wykonanie komend do `executePuzzleCommands()`.
- Metody i konstruktory działającego kodu przyjmują najwyżej trzy argumenty. Przykładowo `BookingFacade.complete()` przyjmuje kod rezerwacji, identyfikator drzwi i szczegóły rezerwacji.

### 9–10. Wyjątki i stałe

- Trzy przykłady wyjątków domenowych pokazuje `DesignPrinciplesDemonstration.demonstrateExceptions()`: `InvalidTeamSizeException`, `PaymentRequiredException` i `ScenarioNotFoundException`. W kodzie są też wyjątki dla błędnego typu zagadki, nieudanej obsługi drzwi i niepoprawnych operacji na stanie gry. Błędy nie są przekazywane jako kody liczbowe.
- Istotne wartości konfiguracyjne mają nazwy: `TeamRules.MINIMUM_SIZE`, `TeamRules.MAXIMUM_SIZE`, stałe danych przykładowej aplikacji oraz ceny dekoratorów. `EscapeRoomApplication` przechodzi po wszystkich specyfikacjach zagadek w pętli, bez zakodowanych indeksów.

## LAB 4 — programowanie funkcyjne, refaktoryzacja i testy

- **Punkt 4 — wersja błędna:** `src/lab4/BadEscapeRoomApplication.java` celowo skupia budowanie drużyny i oferty, finalizację rezerwacji, tworzenie pokoju, przebieg sesji oraz demonstracje LAB 3 w jednej długiej metodzie. Ma nieczytelne nazwy, wiele parametrów i nadmiarowe komentarze. Używa prawdziwych typów projektu i służy do omówienia; aplikacja jej nie uruchamia.
- **Punkt 4a — wersja poprawiona:** poprawiony przepływ znajduje się w działającej `src/app/EscapeRoomApplication.java` oraz wywoływanych przez nią `GameSessionDemonstration` i `DesignPrinciplesDemonstration`. Klasa startowa składa obiekty, a wydzielone klasy obsługują sesję gry i demonstracje LAB 3. `BookingFacade` finalizuje rezerwację i kontroluje płatność. W kodzie komentarze `LAB 4, punkt 4` oznaczają wersję błędną, a `LAB 4, punkt 4a` — poprawioną.
- **Punkt 5:** testy JUnit 5 znajdują się w katalogu `test`, obok `src`. Cztery proste zestawy po pięć testów sprawdzają `TeamRules`, `PuzzleFactory`, `GameSession` i `ScenarioCatalog`; komentarze `LAB 4, punkt 5` i `Test N/5` ułatwiają ich znalezienie.
- Uruchom testy poleceniem `mvn test`. Plik `pom.xml` zachowuje obecny katalog źródeł `src` i konfiguruje `test` jako katalog testów.
