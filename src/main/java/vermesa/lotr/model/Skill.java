package vermesa.lotr.model;

public enum Skill {
    Ruse,
    Strength,
    Courage,
    Knowledge,
    Leadership;

    public int getIndex() {
        return this.ordinal(); // Returns 0, 1, 2, 3...
    }
}
