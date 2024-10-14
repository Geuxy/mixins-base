package me.example.client.event.impl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import me.example.client.event.callables.EventCancellable;

@Getter @RequiredArgsConstructor
public class ChatEvent extends EventCancellable {

    private final String message;

}
