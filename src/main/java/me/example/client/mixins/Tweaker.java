package me.example.client.mixins;

import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.LaunchClassLoader;

import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Basic mixin client base.
 * @author Geuxy
 */
public class Tweaker implements ITweaker {

    private final List<String> ARGUMENTS = new ArrayList<>();

    @Override
    public void acceptOptions(List<String> args, File gameDir, File assetsDir, String profile) {
        this.ARGUMENTS.addAll(args);

        if (!args.contains("--version") && profile != null)
            this.addArgs("version", profile);

        if (!args.contains("--assetDir") && assetsDir != null)
            this.addArgs("assetDir", assetsDir.getAbsolutePath());

        if (!args.contains("--gameDir") && gameDir != null)
            this.addArgs("gameDir", gameDir.getAbsolutePath());
    }

    @Override
    public void injectIntoClassLoader(LaunchClassLoader classLoader) {
        MixinBootstrap.init();

        MixinEnvironment env = MixinEnvironment.getDefaultEnvironment();

        Mixins.addConfiguration("mixins.client.json");

        if (env.getObfuscationContext() == null)
            env.setObfuscationContext("notch");

        env.setSide(MixinEnvironment.Side.CLIENT);
    }

    @Override
    public String getLaunchTarget() {
        return "net.minecraft.client.main.Main";
    }

    @Override
    public String[] getLaunchArguments() {
        return ARGUMENTS.toArray(new String[0]);
    }

    private void addArgs(String arg, String profile) {
        ARGUMENTS.add("--" + arg);
        ARGUMENTS.add(profile);
    }

}
