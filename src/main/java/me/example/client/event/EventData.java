package me.example.client.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import me.example.client.event.callables.Event;

import java.lang.reflect.Method;

@Getter @RequiredArgsConstructor
public class EventData {

    private final Object source;
    private final Method target;
    private final Class<? extends Event> eventType;
    private final int priority;

}
