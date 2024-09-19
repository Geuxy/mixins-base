package me.example.client.mod;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.Setter;

import me.example.client.BaseClient;
import me.example.client.mod.annotations.Bounds;
import me.example.client.mod.annotations.ModInfo;

import me.example.client.mod.value.impl.CheckBoxValue;
import me.example.client.util.interfaces.IMinecraft;
import me.example.client.mod.value.Value;

import java.util.ArrayList;
import java.util.List;

/**
 * Basic mixin client base.
 * @author Geuxy
 */
@Getter @Setter
public abstract class Mod implements IMinecraft {

    private final List<Value<?>> values = new ArrayList<>();

    private ModInfo info = this.getClass().getAnnotation(ModInfo.class);

    private boolean enabled;

    public Mod() {
        BaseClient.INSTANCE.getModManager().setRecentlyAddedMod(this);
    }

    // Checks if module is a hud module by checking if it has Bounds annotation
    public boolean isHud() {
        return this.getClass().getAnnotation(Bounds.class) != null;
    }

    // Called when module is enabled
    public void onEnable() {
    }

    // Called when module is disabled
    public void onDisable() {
    }

    // Toggles mod to the opposite state
    public void toggle() {
        this.setEnabled(!enabled);
    }

    /*
     * Changes the modules state, calls onEnable or onDisable, and saves changes to file.
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;

        if(enabled) {
            this.onEnable();

        } else {
            this.onDisable();
        }

        // Save module state
        if(BaseClient.INSTANCE.getConfigManager() != null) {
            BaseClient.INSTANCE.getConfigManager().getModConfig().save();
        }
    }

    /*
     * Serializes the mod and its values into a json
     */
    public JsonObject toJson() {
        JsonObject valuesJson = new JsonObject();

        /* TODO: When adding new values, copy what i did here
         *       with check box but the value type and value return type instead */
        for(Value<?> value : values) {
            if(value.isCheckBox()) {
                valuesJson.addProperty(value.getName(), (Boolean) value.getValue());
            }
        }

        JsonObject json = new JsonObject();

        json.add("values", valuesJson);
        json.addProperty("enabled", isEnabled());

        return json;
    }

    /*
     * Deserializes the json
     */
    public void parseJson(JsonObject json) {
        JsonObject valuesJson = json.get("values").getAsJsonObject();

        /* TODO: When adding new values, copy what i did here
         *       with check box but the value type and value return type instead */
        for (Value<?> value : values) {
            if(value.isCheckBox()) {
                ((CheckBoxValue)value).setValue(valuesJson.get(value.getName()).getAsBoolean());
            }
        }
        this.setEnabled(json.get("enabled").getAsBoolean());
    }

}
