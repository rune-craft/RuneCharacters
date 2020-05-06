package org.runecraft.runecharacters.util;

import com.google.common.reflect.TypeToken;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.SimpleConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import org.runecraft.runecharacters.RuneCharacters;
import org.runecraft.runecore.User;
import org.spongepowered.api.item.inventory.ItemStack;

import javax.xml.stream.events.Comment;
import java.util.Optional;

public class Utils {
    public static Optional<String> writeItemStack(int slotX, int slotY, User owner, ItemStack item) {
        try {

            CommentedConfigurationNode configNode = RuneCharacters.get().getConfig();

            configNode.getNode("inventorys")
                    .getNode(owner.getUUID().toString())
                    .getNode(slotX + ":" + slotY)
                    .setValue(TypeToken.of(ItemStack.class), item);

            return Optional.of(configNode.toString());
        } catch (ObjectMappingException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public static Optional<ItemStack> readItemStack(int slotX, int slotY, User owner) {
        try {

            CommentedConfigurationNode configNode = RuneCharacters.get().getConfig();

            ItemStack is = configNode.getNode("inventorys")
                    .getNode(owner.getUUID().toString())
                    .getNode(slotX + ":" + slotY)
                    .getValue(TypeToken.of(ItemStack.class));

            return Optional.of(is);
        } catch (ObjectMappingException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
