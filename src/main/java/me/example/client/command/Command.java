package me.example.client.command;

import lombok.Getter;
import me.example.client.util.interfaces.Methods;

@Getter
public abstract class Command implements Methods {

    private final CommandInfo info = getClass().getAnnotation(CommandInfo.class);

    public abstract void onCommand(String[] args);

}
