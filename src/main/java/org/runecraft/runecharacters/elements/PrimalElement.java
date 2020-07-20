package org.runecraft.runecharacters.elements;

import org.runecraft.runecharacters.atributes.enums.StatType;
import org.spongepowered.api.text.format.TextColor;
import org.spongepowered.api.text.format.TextColors;

import java.util.Arrays;

public enum PrimalElement {
    AIR(1, "Ar", 2, StatType.AGILITY, StatType.DEFENSE, TextColors.WHITE),
    THUNDER(2, "Raio", 5, StatType.DEXTERITY, StatType.AGILITY, TextColors.YELLOW),
    FIRE(3, "Fogo" ,4, StatType.STRENGHT, StatType.INTELLIGENCE, TextColors.RED),
    WATER(4, "Água" ,1, StatType.INTELLIGENCE, StatType.STRENGHT, TextColors.AQUA),
    EARTH(5, "Terra" ,3, StatType.DEFENSE, StatType.DEXTERITY, TextColors.DARK_GREEN);

    private int id;
    private String name;
    private int weakId;
    private StatType increasedStat;
    private StatType decreasedStat;
    private TextColor color;

    PrimalElement(int id, String name, int weakId, StatType increasedStat, StatType decreasedStat, TextColor color) {
        this.weakId = weakId;
        this.name = name;
        this.color = color;
        this.increasedStat = increasedStat;
        this.decreasedStat = decreasedStat;
    }

    public static PrimalElement by(int id){
        return Arrays.stream(values()).filter(x -> x.getId() == id).findFirst().get();
    }

    public int getId(){ return id; }

    public String getName() {
        return name;
    }

    public TextColor getColor() {
        return color;
    }

    public PrimalElement getWeakness() {
        return by(weakId);
    }

    public StatType getIncreasedStat() {
        return increasedStat;
    }

    public StatType getDecreasedStat() {
        return decreasedStat;
    }
}