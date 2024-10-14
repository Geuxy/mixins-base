package me.example.client.config;

import com.google.gson.Gson;

import lombok.Getter;
import lombok.Setter;

import java.io.File;

/**
 * Basic mixin client base.
 * @author Geuxy
 */
@Getter @Setter
public abstract class Config {

    protected final File file;
    protected final Gson gson;

    public Config(File file, Gson gson) {
        this.file = file;
        this.gson = gson;

        if(file.exists()) {
            this.load();

        } else {
            this.save();
        }
    }

    public abstract void save();
    public abstract void load();

}
