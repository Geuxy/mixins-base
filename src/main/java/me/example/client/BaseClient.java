package me.example.client;

import lombok.Getter;

import me.example.client.command.CommandManager;
import me.example.client.config.ConfigManager;
import me.example.client.event.EventManager;
import me.example.client.keybinding.KeybindingManager;
import me.example.client.mod.ModManager;

/**
 * Basic mixin client base.
 * @author Geuxy
 */
@Getter
public class BaseClient {

    /*
     * Allows us to access non-static variables or methods from this class
     */
    public static final BaseClient INSTANCE = new BaseClient();

    /*
     * Client information
     */
    private final String name = "Base";
    private final String version = "0.1";
    private final String[] authors = {"Geuxy"};

    /*
     * Allows us to listen to event calls as long as the class is registered.
     */
    private final EventManager eventManager = new EventManager();

    /*
     * Allows us to register custom keybindings into game options.
     */
    private final KeybindingManager keybindingManager = new KeybindingManager();

    /*
     * File system for serialization and deserialization.
     */
    private final ConfigManager configManager = new ConfigManager();

    /*
     * The most important part of a client, the features!
     */
    private final ModManager modManager = new ModManager();

    /*
     * A client command system to feel like a hacker!!
     */
    private final CommandManager commandManager = new CommandManager();

    /*
     * Called a bit after the game starts
     */
    public void start() {
        this.keybindingManager.onStart();
        this.modManager.onStart();
        this.configManager.onStart();
        this.commandManager.onStart();
    }

    /*
     * Called when the game is shutting down
     */
    public void stop() {
        this.configManager.getModConfig().save();
        this.eventManager.clear();
    }

}
