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
    protected final File file;

    public Config(File file) {
        this.file = file;
    }

    public abstract void save();
    public abstract void load();
}
