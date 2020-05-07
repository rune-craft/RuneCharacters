package org.runecraft.runecharacters.enums;

import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.ItemTypes;

import java.util.Arrays;
import java.util.Optional;

public enum CharacterClass {

    MAGE(1,"Mago", ItemTypes.STICK),
    WARRIOR(2,"Guerreiro", ItemTypes.STONE_AXE),
    ARCHER(3,"Arqueiro", ItemTypes.BOW);

    private ItemType type;
    private String name;
    private int id;

    private CharacterClass(int id, String name, ItemType type){
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public static Optional<CharacterClass> by(int id){
        return Arrays.stream(values()).filter(x -> x.getId() == id).findFirst();
    }

    public ItemType getItemType(){
        return type;
    }

    public int getId(){
        return id;
    }

    public String getName() {
        return name;
    }
}
