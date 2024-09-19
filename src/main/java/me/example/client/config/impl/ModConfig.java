package me.example.client.config.impl;

import com.google.gson.*;
import me.example.client.BaseClient;
import me.example.client.config.Config;
import me.example.client.mod.Mod;
import me.example.client.util.console.ConsoleUtil;
import me.example.client.util.io.FileUtil;

import java.io.*;
import java.util.Map;

/**
 * Basic mixin client base.
 * @author Geuxy
 */
public class ModConfig extends Config {

    public ModConfig(File file, Gson gson) {
        super(file, gson);
    }

    private JsonObject modsToJson() {
        JsonObject json = new JsonObject();

        for(Mod mod : BaseClient.INSTANCE.getModManager()) {
            json.add(mod.getInfo().name(), mod.toJson());
        }
        return json;
    }

    @Override
    public void save() {
        FileUtil.write(gson.toJson(modsToJson()), file);
    }

    @Override
    public void load() {
        JsonObject json = (JsonObject) new JsonParser().parse(FileUtil.read(file));

        for(Map.Entry<String, JsonElement> entry : json.entrySet()) {
            Mod mod = BaseClient.INSTANCE.getModManager().getModByName(entry.getKey());

            if(mod == null) {
                continue;
            }

            mod.parseJson((JsonObject) entry.getValue());
        }
    }

}
