package me.example.client.mod;

import com.google.gson.JsonObject;

import lombok.Getter;
import lombok.Setter;

import me.example.client.mod.annotations.Bounds;
import me.example.client.util.console.ConsoleUtil;
import me.example.client.util.input.MouseUtil;

/**
 * Basic mixin client base.
 * @author Geuxy
 */
@Getter @Setter
public abstract class HudMod extends Mod {

    // Default bounds when there is no configuration
    private final Bounds bounds = getClass().getAnnotation(Bounds.class);

    // Mods current position
    private float posX = bounds.posX();
    private float posY = bounds.posY();

    // Mods last position, kind of
    private float lastX;
    private float lastY;

    /*
     * Render the mod on the screen when enabled
     */
    public abstract void render();

    /*
     * Render a example on what the mod would look like
     */
    public abstract void renderDummy();

    /**
     * Moves the mod position on the screen
     *
     * @param mouseX mouse X (horizontal) position
     * @param mouseY mouse Y (vertical) position
     */
    public void animateDrag(int mouseX, int mouseY) {
        this.posX = mouseX - lastX;
        this.posY = mouseY - lastY;
    }

    /**
     * Sets the last position after clicking
     *
     * @param mouseX mouse X (horizontal) position
     * @param mouseY mouse Y (vertical) position
     */
    public void drag(int mouseX, int mouseY) {
        this.lastX = mouseX - posX;
        this.lastY = mouseY - posY;
    }

    /**
     * Checks if the cursor is hovering on the mod.
     *
     * @param mouseX mouse X (horizontal) position
     * @param mouseY mouse Y (vertical) position
     */
    public boolean isHovered(int mouseX, int mouseY) {
        return MouseUtil.isMouseAt(mouseX, mouseY, posX, posY, bounds.width(), bounds.height());
    }

    @Override
    public JsonObject toJson() {
        JsonObject json = super.toJson();

        json.addProperty("x", posX);
        json.addProperty("y", posY);

        return json;
    }

    @Override
    public void parseJson(JsonObject json) {
        super.parseJson(json);

        try {
            this.setPosX(json.get("x").getAsFloat());
            this.setPosY(json.get("y").getAsFloat());

        } catch (Exception e) {
            ConsoleUtil.error("Failed to parse configuration for '" + getInfo().name() + "'!");
            ConsoleUtil.error(e.getMessage());
        }
    }

}
