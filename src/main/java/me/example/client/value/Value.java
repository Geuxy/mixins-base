package me.example.client.value;

import lombok.Getter;
import lombok.Setter;

import me.example.client.Base;
import me.example.client.mod.Mod;

/**
 * Basic mixin client base.
 * @author Geuxy
 */
@Getter @Setter
public class Value<T> {

    private final String name;
    private final Mod mod;
    private T value;

    public Value(String name, Mod mod, T value) {
        this.name = name;
        this.mod = mod;
        this.value = value;

        Base.INSTANCE.getValueManager().VALUES.add(this);
    }

}
