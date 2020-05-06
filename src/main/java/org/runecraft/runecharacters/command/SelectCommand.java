package org.runecraft.runecharacters.command;

import org.runecraft.runecharacters.gui.CharacterSelector;
import org.runecraft.runecore.User;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;

public class SelectCommand implements CommandExecutor {

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        if(src instanceof Player){
            CharacterSelector selector = new CharacterSelector(User.by((Player) src).get());
        }
        return CommandResult.builder().build();
    }
}
