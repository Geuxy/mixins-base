package me.example.client.mod;

import lombok.Getter;
import lombok.Setter;

import me.example.client.Base;
import me.example.client.mod.annotations.Bounds;
import me.example.client.mod.annotations.ModInfo;

import net.minecraft.client.Minecraft;

/**
 * Basic mixin client base.
 * @author Geuxy
 */
@Getter @Setter
public class Mod {
    private ModInfo info = this.getClass().getAnnotation(ModInfo.class);
    private boolean enabled;

    protected final Minecraft mc = Minecraft.getMinecraft();

    // Checks if module is a hud module by checking if it has Bounds annotation
    public boolean isHud() {
        return this.getClass().getAnnotation(Bounds.class) != null;
    }

    // Called when module is enabled
    public void onEnable() {}

    // Called when module is disabled
    public void onDisable() {}

    // Toggles mod to the opposite state
    public void toggle() {
        this.setEnabled(!enabled);
    }

    // Sets mod state
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;

        if(enabled) {
            this.onEnable();
        } else {
            this.onDisable();
        }
        this.save();
    }

    // Saves configuration
    protected void save() {
        if(Base.INSTANCE.getConfigManager() != null)
            Base.INSTANCE.getConfigManager().getModConfig().save();
    }

}
