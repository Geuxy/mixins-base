package me.example.client.keybinding.impl;

import me.example.client.gui.HudConfigScreen;
import me.example.client.keybinding.ClientKeybinding;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

/**
 * Basic mixin client base.
 * @author Geuxy
 */
public class ClientMenuKeybind extends ClientKeybinding {
    private HudConfigScreen hudConfigScreen;

    public ClientMenuKeybind() {
        super("Client Menu", Keyboard.KEY_RSHIFT);
    }

    @Override
    public void handle() {
        if(Minecraft.getMinecraft().currentScreen == null) {
            if(hudConfigScreen == null)
                hudConfigScreen = new HudConfigScreen();

            Minecraft.getMinecraft().displayGuiScreen(hudConfigScreen);
        }
    }

}
