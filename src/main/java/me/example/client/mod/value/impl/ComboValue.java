package me.example.client.mod.value.impl;

import lombok.Getter;

import me.example.client.mod.value.Value;

@Getter
public class ComboValue extends Value<String> {

    private final String[] options;

    public ComboValue(String name, String... options) {
        super(name, options[0]);
        this.options = options;
    }

}
