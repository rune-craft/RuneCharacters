package org.runecraft.runecharacters.elements;

import org.runecraft.runecharacters.atributes.enums.StatType;

public enum HiddenElement {
    HEAVEN(6, "Heaven", PrimalElement.FIRE, PrimalElement.WATER, StatType.DEXTERITY, StatType.INTELLIGENCE),
    MAGMA(7, "Magma", PrimalElement.FIRE, PrimalElement.EARTH, StatType.STRENGHT, StatType.AGILITY),
    INFERNO(8, "Inferno" , PrimalElement.FIRE, PrimalElement.THUNDER, StatType.INTELLIGENCE, StatType.DEFENSE),
    BLAZE(9, "Blaze" ,PrimalElement.FIRE, PrimalElement.AIR, StatType.AGILITY, StatType.DEXTERITY),
    MUD(10, "Lama", PrimalElement.WATER, PrimalElement.EARTH, StatType.DEFENSE, StatType.AGILITY),
    METAL(11, "Metal" , PrimalElement.EARTH, PrimalElement.THUNDER, StatType.DEXTERITY, StatType.STRENGHT),
    ATOMIC(12, "Atômico" , PrimalElement.EARTH, PrimalElement.AIR, StatType.INTELLIGENCE, StatType.DEFENSE),
    STORM(13, "Tempestade" , PrimalElement.THUNDER, PrimalElement.AIR, StatType.AGILITY, StatType.INTELLIGENCE),
    CHAOS(14, "Chaos" , PrimalElement.THUNDER, PrimalElement.WATER, StatType.STRENGHT, StatType.DEXTERITY),
    ICE(15, "Gelo" , PrimalElement.WATER, PrimalElement.AIR, StatType.DEFENSE, StatType.STRENGHT);

    private int id;
    private String name;
    private PrimalElement firstElement;
    private PrimalElement secondElement;
    private StatType increasedStat;
    private StatType decreasedStat;

    HiddenElement(int id, String name, PrimalElement firstElement, PrimalElement secondElement, StatType increasedStat, StatType decreasedStat) {
        this.id = id;
        this.name = name;
        this.firstElement = firstElement;
        this.secondElement = secondElement;
        this.increasedStat = increasedStat;
        this.decreasedStat = decreasedStat;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public PrimalElement getFirstElement() {
        return firstElement;
    }

    public PrimalElement getSecondElement() {
        return secondElement;
    }

    public StatType getIncreasedStat() {
        return increasedStat;
    }

    public StatType getDecreasedStat() {
        return decreasedStat;
    }
}
