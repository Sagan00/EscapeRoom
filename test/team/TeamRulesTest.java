package team;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

/** LAB 4, punkt 5: pięć testów jednostkowych reguł rozmiaru drużyny. */
class TeamRulesTest {
    // Test 1/5: najmniejszy dozwolony rozmiar.
    @Test
    void acceptsMinimumTeamSize() {
        assertDoesNotThrow(() -> TeamRules.validateSize(2));
    }

    // Test 2/5: największy dozwolony rozmiar.
    @Test
    void acceptsMaximumTeamSize() {
        assertDoesNotThrow(() -> TeamRules.validateSize(6));
    }

    // Test 3/5: wartość wewnątrz dozwolonego zakresu.
    @Test
    void acceptsTeamSizeInsideRange() {
        assertDoesNotThrow(() -> TeamRules.validateSize(4));
    }

    // Test 4/5: odrzuca rozmiar poniżej minimum.
    @Test
    void rejectsTeamSmallerThanMinimum() {
        assertThrows(InvalidTeamSizeException.class, () -> TeamRules.validateSize(1));
    }

    // Test 5/5: odrzuca rozmiar powyżej maksimum.
    @Test
    void rejectsTeamLargerThanMaximum() {
        assertThrows(InvalidTeamSizeException.class, () -> TeamRules.validateSize(7));
    }
}
