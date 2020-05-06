package org.runecraft.runecharacters.atributes.enums;

import java.util.Arrays;

public enum StatType {
    DEXTERITY(1, "Destreza"),
    INTELLIGENCE(2, "Inteligência"),
    STRENGHT(3, "Força"),
    AGILITY(4, "Agilidade"),
    DEFENSE(5, "Defesa");

    private int id;
    private String name;

    StatType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static StatType by(int id){
        return Arrays.asList(values()).stream().filter(x -> x.getId() == id).findFirst().get();
    }

    public String getName(){
        return name;
    }

    public int getId() {
        return id;
    }
}
