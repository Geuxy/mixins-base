package me.example.client.command;

import me.example.client.BaseClient;
import me.example.client.command.impl.SayCommand;
import me.example.client.event.EventSubscriber;
import me.example.client.event.impl.ChatEvent;
import me.example.client.util.containers.Storage;
import me.example.client.util.interfaces.Methods;

public class CommandManager extends Storage<Command> implements Methods {

    /*
     * A character used at the start of a message to indicate
     * that the player is typing a command.
     */
    private final String prefix = ".";

    @Override
    public void onStart() {
        addAll(
            SayCommand::new

        );
        BaseClient.INSTANCE.getEventManager().register(this);
    }

    @EventSubscriber
    public void onChat(ChatEvent event) {
        if(!event.getMessage().startsWith(prefix)) {
            return;
        }

        String[] splitMessage = event.getMessage().split(" ");

        Command cmd = findFirst(c -> splitMessage[0].equalsIgnoreCase(prefix + c.getInfo().alias()));

        if(cmd != null) {
            cmd.onCommand(splitMessage);

        } else {
            sendClientMessage("Command not found");
        }

        event.cancel();
    }

}
