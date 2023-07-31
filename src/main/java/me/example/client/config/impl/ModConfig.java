package me.example.client.config.impl;

import com.google.gson.*;
import me.example.client.Base;
import me.example.client.config.Config;
import me.example.client.mod.HudMod;
import me.example.client.mod.Mod;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;

/**
 * Basic mixin client base.
 * @author Geuxy
 */
public class ModConfig extends Config {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public ModConfig(File file) {
        super(file);
    }

    private JsonObject convertModsToJson() {
        JsonObject json = new JsonObject();
        Base.INSTANCE.getModManager().getModList().forEach(module ->
            json.add(module.getInfo().name(), module.toJson())
        );

        return json;
    }

    @Override
    public void save() {

        try {
            PrintWriter printWriter = new PrintWriter(new FileWriter(file));
            printWriter.println(GSON.toJson(convertModsToJson()));
            printWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void load() {

        JsonObject json = null;

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            json = (JsonObject) new JsonParser().parse(bufferedReader);
            bufferedReader.close();

        }
        catch (Exception e){
            e.printStackTrace();
        }

        for (Map.Entry<String, JsonElement> entry : json.entrySet()) {
            Mod mod = Base.INSTANCE.getModManager().getMod(entry.getKey());

            if (mod == null) {
                continue;
            }

            JsonObject jsonModule = (JsonObject) entry.getValue();
            mod.parseJson(jsonModule);

        }

    }

}
