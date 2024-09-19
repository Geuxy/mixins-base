package me.example.client.keybinding;

import lombok.Getter;

import me.example.client.BaseClient;
import me.example.client.event.EventSubscriber;
import me.example.client.event.impl.GameTickEvent;
import me.example.client.keybinding.impl.ClientMenuKeybind;

import me.example.client.util.containers.Storage;
import org.lwjgl.input.Keyboard;

/**
 * Basic mixin client base.
 * @author Geuxy
 */
@Getter
public class KeybindingManager extends Storage<ClientKeybinding> {

    /*
     * Called when client starts
     */
    public void onStart() {
        this.addAll(
                ClientMenuKeybind::new
        );

        BaseClient.INSTANCE.getEventManager().register(this);
    }

    /*
     * For all keys on tick event, if a key is pressed then perform their action
     */
    @EventSubscriber
    public void onTick(GameTickEvent event) {
        this.stream().filter(k -> Keyboard.isKeyDown(k.getKeyCode())).forEach(ClientKeybinding::onKeyPressed);
    }

}
