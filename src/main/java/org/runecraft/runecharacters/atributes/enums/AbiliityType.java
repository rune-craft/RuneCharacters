package org.runecraft.runecharacters.atributes.enums;

import java.util.Arrays;

public enum AbiliityType {

    HARVESTING(1, "Plantio");

    private int id;
    private String name;

    private AbiliityType(int id, String name) {
        this.name = name;
        this.id = id;
    }

    public static AbiliityType by(int id){
        return Arrays.asList(values()).stream().filter(x -> x.getId() == id).findFirst().get();
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
