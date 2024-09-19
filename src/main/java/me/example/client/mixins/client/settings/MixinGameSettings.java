package me.example.client.mixins.client.settings;

import me.example.client.BaseClient;
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
    // Adds keybindings from KeybindingManager to GameSettings
    @Inject(method = "<init>*", at = @At("RETURN"))
    private void gameSettingsConstructor(CallbackInfo ignored) {
        this.keyBindings = ArrayUtils.addAll(keyBindings, BaseClient.INSTANCE.getKeybindingManager().toArray(new ClientKeybinding[0]));
    }

}
