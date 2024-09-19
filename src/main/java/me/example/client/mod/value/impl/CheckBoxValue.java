package me.example.client.mod.value.impl;

import me.example.client.mod.value.Value;

/**
 * Basic mixin client base.
 * @author Geuxy
 */
public class CheckBoxValue extends Value<Boolean> {

    public CheckBoxValue(String name) {
        super(name, false);
    }

    public CheckBoxValue(String name, boolean value) {
        super(name, value);
    }

}
