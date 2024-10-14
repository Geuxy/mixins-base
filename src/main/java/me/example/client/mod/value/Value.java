package me.example.client.mod.value;

import lombok.Getter;
import lombok.Setter;

import me.example.client.BaseClient;
import me.example.client.mod.value.impl.CheckBoxValue;
import me.example.client.mod.value.impl.ComboValue;

/**
 * Basic mixin client base.
 * @author Geuxy
 */
// TODO: IMPORTANT - If you add new value types, ensure they can be saved and loaded
@Getter @Setter
public class Value<T> {

    private final String name;
    private T value;

    public Value(String name, T value) {
        this.name = name;
        this.value = value;

        BaseClient.INSTANCE.getModManager().getRecentlyAddedMod().getValues().add(this);
    }

    public boolean isCheckBox() {
        return this instanceof CheckBoxValue;
    }

    public boolean isCombo() {
        return this instanceof ComboValue;
    }

}
