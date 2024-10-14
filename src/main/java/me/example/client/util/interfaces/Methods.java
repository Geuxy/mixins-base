package me.example.client.util.interfaces;

import net.minecraft.util.ChatComponentText;

public interface Methods extends IMinecraft {

    default void sendClientMessage(String message) {
        mc.thePlayer.addChatMessage(new ChatComponentText(message));
    }

}
