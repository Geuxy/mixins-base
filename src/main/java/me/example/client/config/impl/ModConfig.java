package me.example.client.config.impl;

import me.example.client.Base;
import me.example.client.config.Config;
import me.example.client.mod.HudMod;
import me.example.client.mod.Mod;

import java.io.*;
import java.util.ArrayList;

/**
 * Basic mixin client base.
 * @author Geuxy
 */
public class ModConfig extends Config {

    public ModConfig() {
        super("settings.json");

        if(!getData().exists()) {
            try {
                getData().createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void save() {
        ArrayList<String> saves = new ArrayList<>();

        for(Mod mod : Base.INSTANCE.getModManager().getModList()) {
            String name = mod.getInfo().name();

            saves.add("M:" + name + ":" + mod.isEnabled());

            if(mod.isHud()) {
                HudMod hudMod = (HudMod) mod;

                float x = hudMod.getPosX();
                float y = hudMod.getPosY();

                saves.add("H:" + name + ":" + x + ":" + y);
            }
        }

        try {
            PrintWriter pw = new PrintWriter(this.getData());

            for (String str : saves)
                pw.println(str);

            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void load() {
        ArrayList<String> lines = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.getData()));
            String line = reader.readLine();

            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(String line : lines) {
            String[] args = line.split(":");

            if(line.startsWith("M")) {
                Mod mod = Base.INSTANCE.getModManager().getMod(args[1]);

                if(mod != null)
                    mod.setEnabled(Boolean.parseBoolean(args[2]));
            }

            if(line.startsWith("H")) {
                HudMod hudMod = (HudMod) Base.INSTANCE.getModManager().getMod(args[1]);

                float x = Float.parseFloat(args[2]);
                float y = Float.parseFloat(args[3]);

                hudMod.setPosX(x);
                hudMod.setPosY(y);
            }
        }
    }

}
