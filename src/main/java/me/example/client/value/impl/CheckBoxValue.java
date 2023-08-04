package me.example.client.value.impl;

import me.example.client.mod.Mod;
import me.example.client.value.Value;

/**
 * Basic mixin client base.
 * @author Geuxy
 */
public class CheckBoxValue extends Value<Boolean> {

    public CheckBoxValue(String name, Mod mod) {
        super(name, mod, false);
    }

    public CheckBoxValue(String name, Mod mod, boolean value) {
        super(name, mod, value);
    }

}
