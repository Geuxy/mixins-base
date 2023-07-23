package me.example.client.mixins.client.settings;

import me.example.client.Base;
import me.example.client.keybinding.ClientKeybinding;

import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;

import org.apache.commons.lang3.ArrayUtils;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Basic mixin client base.
 * @author Geuxy
 */
@Mixin(GameSettings.class)
public class MixinGameSettings {

    // Shadows
    @Shadow
    public KeyBinding[] keyBindings;

    // Called when GameSettings constructor is initialised
    @Inject(method = "<init>()V", at = @At("RETURN"))
    private void gameSettingsConstructor(CallbackInfo ignored) {
        this.addKeybinds();
    }

    // Called when GameSettings constructor is initialised
    @Inject(method = "<init>(Lnet/minecraft/client/Minecraft;Ljava/io/File;)V", at = @At("RETURN"))
    private void gameSettingsConstructor2(CallbackInfo ignored) {
        this.addKeybinds();
    }

    // Adds keybindings from KeybindingManager to GameSettings
    private void addKeybinds() {
        this.keyBindings = ArrayUtils.addAll(keyBindings, Base.INSTANCE.getKeybindingManager().getKeyBindingList().toArray(new ClientKeybinding[0]));
    }

}
