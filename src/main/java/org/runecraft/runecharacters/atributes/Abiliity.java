package org.runecraft.runecharacters.atributes;

public class Abiliity {
    private int level;
    private double xp;

    public Abiliity(int level, double xp) {
        this.level = level;
        this.xp = xp;
    }

    public int getLevel() {
        return level;
    }

    public double getXp() {
        return xp;
    }
}
