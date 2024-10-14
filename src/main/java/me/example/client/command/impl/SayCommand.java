package me.example.client.command.impl;

import me.example.client.command.Command;
import me.example.client.command.CommandInfo;

import net.minecraft.network.play.client.C01PacketChatMessage;


/*
 * A special command that lets you send a message with the command prefix at the start
 * so it won't be caught by the event and be considered an event and cancel from being
 * sent to the server.
 *
 * --- gibberish incoming ---
 * There are so many stupid low IQ players that believe that you are a cheater if you
 * cant send a message starting with "." because of the command system, as long as they
 * don't ask you to send a message starting with "." and goes to the max amount of characters
 * you can add, you should be fine, or else just try to prove it's not a cheat and flex that
 * you have more knowledge or something idk.
 */
@CommandInfo(alias = "say", description = "Send a chat message that's not modifiable by events!")
public class SayCommand extends Command {

    @Override
    public void onCommand(String[] args) {
        if(args.length == 0) {
            sendClientMessage("Message can't be empty");
            return;
        }

        StringBuilder message = new StringBuilder();

        for(int i = 1; i < args.length; i++) {
            message.append(" ").append(args[i]);
        }

        mc.thePlayer.sendQueue.addToSendQueue(new C01PacketChatMessage(message.toString()));
    }

}
