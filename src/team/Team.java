package team;

/**
 * week 1 — zastosowano: Builder
 * Model drużyny z builderem, żeby tworzenie obiektu było czytelne.
 * Dzięki temu obiekt można składać krok po kroku i sprawdzić przed utworzeniem.
 */
public final class Team {
    private final String name;
    private final int size;
    private final int skill;
    private final boolean paid;

    private Team(Builder builder) {
        this.name = builder.name;
        this.size = builder.size;
        this.skill = builder.skill;
        this.paid = builder.paid;
    }

    public String name() {
        return name;
    }

    public int size() {
        return size;
    }

    public int skill() {
        return skill;
    }

    public boolean paid() {
        return paid;
    }

    public static final class Builder {
        private String name;
        private int size;
        private int skill;
        private boolean paid;

        public Builder name(String value) {
            this.name = value;
            return this;
        }

        public Builder size(int value) {
            this.size = value;
            return this;
        }

        public Builder skill(int value) {
            this.skill = value;
            return this;
        }

        public Builder paid(boolean value) {
            this.paid = value;
            return this;
        }

        public Team build() {
            TeamRules.validateSize(size);
            return new Team(this);
        }
    }
}
