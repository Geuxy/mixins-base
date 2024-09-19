package me.example.client.keybinding;

import lombok.Getter;

import me.example.client.BaseClient;

import net.minecraft.client.settings.KeyBinding;

/**
 * Basic mixin client base.
 * @author Geuxy
 */
@Getter
public abstract class ClientKeybinding extends KeyBinding {

    public ClientKeybinding(String description, int keyCode) {
        super(description, keyCode, BaseClient.INSTANCE.getName() + " Client");
    }

    /*
     * Action when the key code is pressed
     */
    public abstract void onKeyPressed();

}
