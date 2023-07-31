package me.example.client.event.impl;

import lombok.Getter;
import me.example.client.event.Event;

public class Render3DEvent extends Event {

    @Getter
    private float partialTicks;

    public Render3DEvent(float partialTicks) {
        this.partialTicks = partialTicks;
    }

}
