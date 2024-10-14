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

        BaseClient.INSTANCE.getModManager().forEach(m -> json.add(m.getInfo().name(), m.toJson()));

        return json;
    }

    @Override
    public void save() {
        FileUtil.write(gson.toJson(modsToJson()), file);
    }

    @Override
    public void load() {
        BufferedReader reader = FileUtil.read(file);

        if(reader == null) {
            this.save();
            return;
        }

        JsonObject json = (JsonObject) new JsonParser().parse(reader);

        for(Map.Entry<String, JsonElement> entry : json.entrySet()) {
            Mod mod = BaseClient.INSTANCE.getModManager().getModByName(entry.getKey());

            if(mod == null) {
                continue;
            }

            mod.parseJson((JsonObject) entry.getValue());
        }
    }

}
