package org.runecraft.runecharacters.enums;

import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.ItemTypes;

public enum CharacterClass {

    MAGE("Mago", ItemTypes.STICK),
    WARRIOR("Guerreiro", ItemTypes.STONE_AXE),
    ARCHER("Arqueiro", ItemTypes.BOW);

    private ItemType type;
    private String name;

    private CharacterClass(String name, ItemType type){
        this.name = name;
        this.type = type;
    }

    public ItemType getItemType(){
        return type;
    }

    public String getName() {
        return name;
    }
}
