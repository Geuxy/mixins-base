package me.example.client.keybinding;

import lombok.Getter;
import net.minecraft.client.settings.KeyBinding;

/**
 * Basic mixin client base.
 * @author Geuxy
 */
@Getter
public abstract class ClientKeybinding extends KeyBinding {

    public ClientKeybinding(String description, int keyCode) {
        super(description, keyCode, "Base Client");
    }

    public abstract void handle();
}
