package puzzle;

/**
 * week 1 — zastosowano: Factory (dane wejściowe)
 * Prosta specyfikacja typu i nazwy zagadki dla fabryki.
 * Dzięki temu reszta programu nie musi samodzielnie wybierać konkretnej klasy.
 */
public record PuzzleSpec(String type, String name) {
}
