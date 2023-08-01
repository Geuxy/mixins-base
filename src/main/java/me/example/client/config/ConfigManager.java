package me.example.client.config;

import lombok.Getter;

import me.example.client.Base;
import me.example.client.config.impl.ModConfig;

import net.minecraft.client.Minecraft;

import java.io.File;

/**
 * Basic mixin client base.
 * @author Geuxy
 */
@Getter
public class ConfigManager {
    private final ModConfig modConfig;

    private static final File DIRECTORY = new File(Minecraft.getMinecraft().mcDataDir, Base.INSTANCE.clientName);

    public ConfigManager() {

        if (!DIRECTORY.exists()) {
            DIRECTORY.mkdirs();
        }

        this.modConfig = new ModConfig(new File(DIRECTORY, "settings.json"));
    }

    public void onInit() {
        if (modConfig.getFile().exists()) {
            modConfig.load();
        }
        else {
            modConfig.save();
        }
    }

}
