package me.example.client.config;

import lombok.Getter;
import lombok.Setter;

import me.example.client.Base;

import net.minecraft.client.Minecraft;

import java.io.File;

/**
 * Basic mixin client base.
 * @author Geuxy
 */
@Getter @Setter
public abstract class Config {
    private final String name;
    private final File data;

    public Config(String name) {
        this.name = name;
        this.data = new File(Minecraft.getMinecraft().mcDataDir + File.separator + Base.INSTANCE.clientName + File.separator + name);
    }

    public abstract void save();
    public abstract void load();
}
