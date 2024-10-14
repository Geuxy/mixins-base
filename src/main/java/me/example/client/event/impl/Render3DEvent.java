package me.example.client.event.impl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import me.example.client.event.callables.Event;

@Getter @RequiredArgsConstructor
public class Render3DEvent extends Event {

    private final float partialTicks;

}
