package me.example.client.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Method;

/**
 * Basic mixin client base.
 * @author Geuxy
 */
@Getter @Setter
@AllArgsConstructor
public class EventData {
    private final Object source;
    private final Method target;
    private final byte priority;
}
