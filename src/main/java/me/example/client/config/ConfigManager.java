package me.example.client.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.Getter;

import me.example.client.BaseClient;
import me.example.client.config.impl.ModConfig;
import me.example.client.util.interfaces.IMinecraft;

import java.io.File;

/**
 * Basic mixin client base.
 * @author Geuxy
 */
@Getter
public class ConfigManager implements IMinecraft {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    // Mod configuration file
    private ModConfig modConfig;

    // Client config directory (eg: '.minecraft/BaseClient/')
    private File directory;

    /*
     * Called when client starts
     */
    public void onStart() {
        this.directory = new File(mc.mcDataDir, BaseClient.INSTANCE.getName());

        if(!directory.exists()) {
            directory.mkdirs();
        }

        this.modConfig = new ModConfig(new File(directory, "settings.json"), GSON);

        this.createModConfig();
    }

    /*
     * Load config if mod config file exists, else create one
     */
    public void createModConfig() {
        if(modConfig.getFile().exists()) {
            modConfig.load();

        } else {
            modConfig.save();
        }
    }

}
