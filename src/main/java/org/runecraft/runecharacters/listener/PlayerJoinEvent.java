package org.runecraft.runecharacters.listener;

import org.runecraft.runecharacters.gui.CharacterSelector;
import org.runecraft.runecore.User;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.EventListener;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.filter.cause.Root;
import org.spongepowered.api.event.network.ClientConnectionEvent;

public class PlayerJoinEvent {

    @Listener
    public void onPlayerJoin(ClientConnectionEvent.Join event, @Root Player player){
        CharacterSelector selector = new CharacterSelector(User.by(player).get());
        selector.open();
    }

}
