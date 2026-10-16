package team;

/** LAB 3 — nazwane stałe określają dozwolony zakres liczebności drużyny. */
public final class TeamRules {
    public static final int MINIMUM_SIZE = 2;
    public static final int MAXIMUM_SIZE = 6;

    private TeamRules() {
    }

    public static void validateSize(int size) {
        if (size < MINIMUM_SIZE || size > MAXIMUM_SIZE) {
            throw new InvalidTeamSizeException(
                    "Drużyna musi mieć od " + MINIMUM_SIZE + " do " + MAXIMUM_SIZE + " osób."
            );
        }
    }
}
