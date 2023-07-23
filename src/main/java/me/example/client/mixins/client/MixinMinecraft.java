package me.example.client.mixins.client;

import me.example.client.Base;

import me.example.client.event.impl.TickEvent;
import net.minecraft.client.Minecraft;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Basic mixin client base.
 * @author Geuxy
 */
@Mixin(Minecraft.class)
public class MixinMinecraft {

    // Called when game starts
    @Inject(method = "startGame", at = @At("HEAD"))
    public void startGame(CallbackInfo ignored) {
        Base.INSTANCE.init();
    }

    // Called when game shuts down
    @Inject(method = "shutdownMinecraftApplet", at = @At("HEAD"))
    public void shutdownMinecraftApplet(CallbackInfo ignored) {
        Base.INSTANCE.stop();
    }

    // Called every tick
    @Inject(method = "runTick", at = @At("HEAD"))
    public void runTick(CallbackInfo ignored) {
        new TickEvent().onFire();
    }

}
