package me.example.client.mixins.client.gui;

import me.example.client.event.impl.Render2DEvent;

import net.minecraft.client.gui.GuiIngame;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Basic mixin client base.
 * @author Geuxy
 */
@Mixin(GuiIngame.class)
public class MixinGuiIngame {

    @Inject(method = "renderGameOverlay", at = @At("RETURN"))
    public void renderGameOverlay(CallbackInfo ignored) {
        new Render2DEvent().onFire();
    }

}
