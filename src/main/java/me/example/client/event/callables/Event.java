package me.example.client.event.callables;

import me.example.client.BaseClient;

public class Event {

    public void call() {
        BaseClient.INSTANCE.getEventManager().call(this);
    }

}
