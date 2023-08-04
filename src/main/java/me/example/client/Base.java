package me.example.client;

import lombok.Getter;
import me.example.client.config.ConfigManager;
import me.example.client.event.EventProtocol;
import me.example.client.keybinding.KeybindingManager;
import me.example.client.mod.ModManager;
import me.example.client.value.ValueManager;

/**
 * Basic mixin client base.
 * @author Geuxy
 */
@Getter
public class Base {

    public static final Base INSTANCE = new Base();

    public final String clientName = "Base";
    public final String clientBuild = "0.1";

    private final KeybindingManager keybindingManager = new KeybindingManager();
    private final ValueManager valueManager = new ValueManager();
    private ModManager modManager;
    private ConfigManager configManager;

    public void init() {
        this.modManager = new ModManager();

        this.configManager = new ConfigManager();
        this.configManager.onInit();

        EventProtocol.register(this);
    }

    public void stop() {
        this.configManager.getModConfig().save();
        EventProtocol.unregister(this);
    }

}
