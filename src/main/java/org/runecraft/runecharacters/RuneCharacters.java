package org.runecraft.runecharacters;

import com.google.common.reflect.TypeToken;
import com.google.inject.Inject;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import org.runecraft.runecharacters.command.SelectCommand;
import org.runecraft.runecharacters.manager.CharactersManager;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.util.TypeTokens;

import java.io.File;
import java.io.IOException;

@Plugin(
        id = "runecharacters",
        name = "RuneCharacters",
        authors = {
                "Azure"
        }
)
public class RuneCharacters {

    @Inject
    private Logger logger;

    @Inject
    @DefaultConfig(sharedRoot = true)
    private File config;

    @Inject
    @DefaultConfig(sharedRoot = true)
    private ConfigurationLoader<CommentedConfigurationNode> configLoader;

    private CommentedConfigurationNode configNode;

    private static CharactersManager charactersManager;

    private static RuneCharacters instance;

    @Listener
    public void onInit(GameInitializationEvent event){
        try{
            if(!config.exists()){
                config.createNewFile();
                configNode = configLoader.load();
                configNode.getNode("bruh").getNode("uuid").getNode("itemfoda").setValue("D0J328DJ38");
                configLoader.save(configNode);
            }
            configNode = configLoader.load();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        instance = this;
        charactersManager = new CharactersManager();
        CommandSpec selectCmd = CommandSpec.builder().executor(new SelectCommand()).build();
        Sponge.getCommandManager().register(this, selectCmd, "select", "selecionar", "escolher", "selectcharacter", "selecionarpersonagem");
    }

    public static CharactersManager getCharactersManager(){
        return charactersManager;
    }

    public static Object getInstance(){
        return Sponge.getPluginManager().getPlugin("runecharacters").get().getInstance().get();
    }

    public static RuneCharacters get(){
        return instance;
    }

    public CommentedConfigurationNode getConfig(){
        return configNode;
    }
}
