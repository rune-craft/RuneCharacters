package org.runecraft.runecharacters;

import org.runecraft.runecharacters.util.Utils;
import org.runecraft.runecore.User;
import org.runecraft.runecharacters.atributes.Abiliity;
import org.runecraft.runecharacters.atributes.Stat;
import org.runecraft.runecharacters.atributes.enums.AbiliityType;
import org.runecraft.runecharacters.atributes.enums.StatType;
import org.runecraft.runecharacters.enums.CharacterClass;
import org.runecraft.runeelements.HiddenElement;
import org.runecraft.runeelements.PrimalElement;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.InventoryArchetypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.property.SlotPos;
import org.spongepowered.api.item.inventory.query.QueryOperationTypes;

import java.util.*;

public class Character {

    public static class Builder {

        private UUID uid;
        private User owner;
        private boolean online;
        private int health;
        private int level;
        private PrimalElement primalElement;
        private Optional<HiddenElement> hiddenElement;
        private CharacterClass clazz;
        private Map<AbiliityType, Abiliity> abilities = new HashMap<>();
        private Map<StatType, Stat> stats = new HashMap<>();
        private boolean isRegistering;

        public Character build(){
            if(isRegistering){
                stats.put(StatType.AGILITY, new Stat(1));
                stats.put(StatType.DEFENSE, new Stat(1));
                stats.put(StatType.DEXTERITY, new Stat(1));
                stats.put(StatType.INTELLIGENCE, new Stat(1));
                stats.put(StatType.STRENGHT, new Stat(1));

                abilities.put(AbiliityType.HARVESTING, new Abiliity(1, 0));

                uid = UUID.randomUUID();

                return new Character(owner, uid, clazz, 0, 100, primalElement, stats, abilities);
            }
            if(hiddenElement.isPresent()){
                return new Character(owner, uid, clazz, level, health, primalElement, stats, abilities, hiddenElement.get());
            }
            return  new Character(owner, uid, clazz, level, health, primalElement, stats, abilities);
        }

        public void setUUID(UUID uid) {
            this.uid = uid;
        }

        public void setOwner(User owner) {
            this.owner = owner;
        }

        public void setHiddenElement(HiddenElement hiddenElement) {
            this.hiddenElement = Optional.ofNullable(hiddenElement);
        }

        public void setCharacterClass(CharacterClass clazz) {
            this.clazz = clazz;
        }

        public void setLevel(int level){
            this.level = level;
        }

        public void setPrimalElement(PrimalElement element){
            this.primalElement = element;
        }

        public void setStat(StatType type, int level){
            stats.remove(type);
            stats.put(type, new Stat(level));
        }

        public void setAbiliity(AbiliityType abiliity, int level, double xp){
            abilities.remove(abiliity);
            abilities.put(abiliity, new Abiliity(level, xp));
        }

        public void setHealth(int health){
            this.health = health;
        }

        public void setOnline(boolean bool){
            this.online = bool;
        }

        public void setRegister(boolean bool){
            isRegistering = bool;
        }

    }

    private UUID uid;
    private User owner;
    private boolean online;
    private int health;
    private int level;
    private PrimalElement primalElement;
    private HiddenElement hiddenElement;
    private CharacterClass clazz;
    private Map<AbiliityType, Abiliity> abilities = new HashMap<>();
    private Map<StatType, Stat> stats = new HashMap<>();

    public Character(User owner, UUID uid, CharacterClass clazz, int level, int health, PrimalElement primalElement, Map<StatType, Stat> stats, Map<AbiliityType, Abiliity> abilities) {
        this.owner = owner;
        this.level = level;
        this.primalElement = primalElement;
        this.health = health;
        this.stats = stats;
        this.abilities = abilities;
    }

    public Character(User owner, UUID uid, CharacterClass clazz, int level, int health, PrimalElement primalElement, Map<StatType, Stat> stats, Map<AbiliityType, Abiliity> abilities, HiddenElement hiddenElement) {
        this(owner, uid ,clazz, level, health, primalElement, stats, abilities);
        this.hiddenElement = hiddenElement;
    }

    public static Optional<Character> getCurrentCharacter(User user){
        Set<Character> characters = RuneCharacters.getCharactersManager().getCharacters(user);
        for(Character chara : characters){
            if(chara.isOnline()){
                return Optional.ofNullable(chara);
            }
        }
        return Optional.empty();
    }

    public boolean isOnline() {return online;}

    public void setOnline() { online = true; };

    public void setOffline() { online = false; };

    public UUID getUUID(){
        return uid;
    }

    public User getOwner() {
        return owner;
    }

    public int getLevel() {
        return level;
    }

    public int getHealth() {
        return health;
    }

    public CharacterClass getCharacterClass() {
        return clazz;
    }

    public Optional<HiddenElement> getHiddenElement() {
        return Optional.ofNullable(hiddenElement);
    }

    public Abiliity getAbiliity(AbiliityType type) {
        return abilities.get(type);
    }

    public void setAbilities(AbiliityType type, Abiliity abiliity) {
        abilities.remove(type);
        abilities.put(type, abiliity);
    }

    public Stat getStat(StatType type) {
        return stats.get(type);
    }

    public void setStat(StatType type, Stat stat) {
        stats.remove(type);
        stats.put(type, stat);
    }

    public PrimalElement getPrimalElement() { return primalElement; }

    public void saveCharacterInventory(){

    }

    public Inventory loadInventory(){
        if(owner.getPlayer().isPresent()){
            Inventory inv = Inventory.builder()
                    .forCarrier(owner.getPlayer().get())
                    .of(InventoryArchetypes.PLAYER).build(RuneCharacters.getInstance());

            for(int y = 0; y<4; y++){
                for(int x = 0; x<9; x++){
                    Optional<ItemStack> readed = Utils.readItemStack(x,y,owner);
                    if(readed.isPresent()){
                        inv.query(QueryOperationTypes.INVENTORY_PROPERTY.of(SlotPos.of(x,y))).set(readed.get());
                    }
                }
            }
            return inv;
        }
        return null;
    }

}
