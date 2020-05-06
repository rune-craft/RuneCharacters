package org.runecraft.runecharacters.gui;

import org.runecraft.runecharacters.Character;
import org.runecraft.runecharacters.RuneCharacters;
import org.runecraft.runecharacters.atributes.Stat;
import org.runecraft.runecharacters.atributes.enums.AbiliityType;
import org.runecraft.runecharacters.atributes.enums.StatType;
import org.runecraft.runecharacters.enums.CharacterClass;
import org.runecraft.runecore.User;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ChangeInventoryEvent;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.InventoryArchetypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.property.InventoryDimension;
import org.spongepowered.api.item.inventory.property.InventoryTitle;
import org.spongepowered.api.item.inventory.property.SlotIndex;
import org.spongepowered.api.item.inventory.property.SlotPos;
import org.spongepowered.api.item.inventory.query.QueryOperationTypes;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColor;
import org.spongepowered.api.text.format.TextColors;

import java.util.*;

public class CharacterSelector {
    private User user;
    private int slotIndex = 0;
    private Set<Character> userCharacters;
    private Map<Integer, Character> characterMap = new HashMap<>();
    private Inventory gui;

    public CharacterSelector(User user){
        userCharacters = RuneCharacters.getCharactersManager().getCharacters(user);
    }


    private void build(){
        Set<ItemStack> stacks = new HashSet<>();

        gui = Inventory.builder()
                .of(InventoryArchetypes.CHEST)
                .withCarrier(user.getPlayer().get())
                .property(InventoryTitle.PROPERTY_NAME, new InventoryTitle(Text.of("Selecione um personagem:")))
                .property(InventoryDimension.PROPERTY_NAME, new InventoryDimension(9, 4))
                .listener(ClickInventoryEvent.class,
                        event -> {
                            event.setCancelled(true);
                            Optional<SlotIndex> index = event.getTransactions().get(0).getSlot().getInventoryProperty(SlotIndex.class);
                            if(index.isPresent()){
                                if(index.get().getValue() == 9){
                                    CharacterBuilder charBuilder = new CharacterBuilder(user);
                                    charBuilder.open();
                                }
                                if(getCharacterBySlot(index.get().getValue()).isPresent()){
                                    select(getCharacterBySlot(index.get().getValue()).get());
                                }
                            }
                        })
                .build(RuneCharacters.getInstance());

        Set<Character> userCharacters = RuneCharacters.getCharactersManager().getCharacters(user);
        userCharacters.forEach(ch ->{
            List<Text> lore = new ArrayList<>();
            CharacterClass clazz = ch.getCharacterClass();

            lore.add(Text.of(""));

            lore.add(Text.of(""));

            lore.add(Text.builder()
                    .color(TextColors.YELLOW)
                    .append(Text.of("Stats: ")).build());

            Arrays.asList(StatType.values()).stream().forEach(x -> {
                lore.add(Text.builder()
                        .color(TextColors.GRAY)
                        .append(Text.of("   " + x.getName() + ": "))
                        .color(TextColors.GREEN)
                        .append(Text.of(ch.getStat(x).getLevel())).build());
            });

            lore.add(Text.of(""));

            lore.add(Text.builder()
                    .color(TextColors.YELLOW)
                    .append(Text.of("Habilidades: ")).build());

            Arrays.asList(AbiliityType.values()).stream().forEach(x -> {
                lore.add(Text.builder()
                        .color(TextColors.GRAY)
                        .append(Text.of("   " + x.getName() + ": "))
                        .color(TextColors.GREEN)
                        .append(Text.of(ch.getAbiliity(x).getLevel())).build());
            });

            lore.add(Text.of(""));

            lore.add(Text.builder()
                    .color(TextColors.YELLOW)
                    .append(Text.of("UUID:"))
                    .color(TextColors.GRAY)
                    .append(Text.of(ch.getUUID())).build());

            ItemStack.Builder builder = ItemStack.builder();
            builder.add(Keys.ITEM_LORE, lore);
            builder.add(Keys.DISPLAY_NAME, Text.builder()
                    .color(TextColors.GOLD)
                    .append(Text.of(clazz.getName()))
                    .color(TextColors.GRAY)
                    .append(Text.of(" | "))
                    .color(TextColors.YELLOW)
                    .append(Text.of("Level "))
                    .append(Text.of(ch.getLevel())).build());

            gui.query(QueryOperationTypes.INVENTORY_PROPERTY.of(SlotPos.of(slotIndex, 1))).set(builder.build());
            characterMap.put(slotIndex, ch);
            slotIndex++;
        });

        List<Text> createLore = new ArrayList<>();
        createLore.add(Text.of(""));
        createLore.add(Text.builder().color(TextColors.GRAY).append(Text.of("Crie um novo personagem para sua aventura!")).build());


        ItemStack createChar = ItemStack.builder()
                .add(Keys.DISPLAY_NAME, Text.builder().color(TextColors.GREEN).append(Text.of("Novo personagem")).build())
                .itemType(ItemTypes.TOTEM_OF_UNDYING)
                .add(Keys.ITEM_LORE, createLore)
                .build();

        gui.query(QueryOperationTypes.INVENTORY_PROPERTY.of(SlotPos.of(9, 1))).set(createChar);
    }

    public void open(){
        if(user.getPlayer().isPresent()){
            Player p = user.getPlayer().get();
            p.closeInventory();
            p.openInventory(gui);
        }
    }

    private Optional<Character> getCharacterBySlot(int slot){
        return Optional.ofNullable(characterMap.get(slot));
    }

    private void select(Character character){

    }

}
