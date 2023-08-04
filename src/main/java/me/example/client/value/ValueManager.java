package me.example.client.value;

import lombok.Getter;
import me.example.client.mod.Mod;

import java.util.ArrayList;
import java.util.List;

/**
 * Basic mixin client base.
 * @author Geuxy
 */
@Getter
public class ValueManager {

    public final List<Value<?>> VALUES = new ArrayList<>();

    // Get a value from a mod
    public <T extends Value<?>> T getValue(String name, Class<?> moduleClass) {
        return (T) VALUES.stream().filter(v -> v.getName().equalsIgnoreCase(name) && v.getMod().getClass().equals(moduleClass)).findFirst().orElse(null);
    }

    // Get all values from a mod by class
    public List<Value<?>> getValues(Class<?> moduleClass) {
        return new ArrayList<Value<?>>() {{
            VALUES.stream().filter(v -> v.getMod().getClass().equals(moduleClass)).forEach(this::add);
        }};
    }

    // Get all values from a mod by name
    public List<Value<?>> getValues(Mod module) {
        return new ArrayList<Value<?>>() {{
            VALUES.stream().filter(v -> v.getMod().equals(module)).forEach(this::add);
        }};
    }

}
