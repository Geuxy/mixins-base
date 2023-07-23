package me.example.client.keybinding;

import lombok.Getter;
import me.example.client.event.EventProtocol;
import me.example.client.event.EventTarget;
import me.example.client.event.impl.TickEvent;
import me.example.client.keybinding.impl.ClientMenuKeybind;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.List;

/**
 * Basic mixin client base.
 * @author Geuxy
 */
@Getter
public class KeybindingManager {
    private final List<ClientKeybinding> keyBindingList = new ArrayList<>();

    public KeybindingManager() {
        keyBindingList.add(new ClientMenuKeybind());

        EventProtocol.register(this);
    }

    @EventTarget
    public void onTick(TickEvent event) {
        keyBindingList.stream().filter(k -> Keyboard.isKeyDown(k.getKeyCode())).forEach(ClientKeybinding::handle);
    }

}
