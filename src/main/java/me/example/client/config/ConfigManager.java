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

    public ConfigManager() {
        File directory = new File(Minecraft.getMinecraft().mcDataDir + File.separator + Base.INSTANCE.clientName);

        if(!directory.exists())
            directory.mkdir();

        this.modConfig = new ModConfig();
    }

}
