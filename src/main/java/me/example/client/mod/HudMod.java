package me.example.client.mod;

import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.Setter;

import me.example.client.mod.annotations.Bounds;
import org.lwjgl.input.Mouse;

/**
 * Basic mixin client base.
 * @author Geuxy
 */
@Getter @Setter
public abstract class HudMod extends Mod {
    private final Bounds bounds = getClass().getAnnotation(Bounds.class);

    private float posX = bounds.posX(), lastX;
    private float posY = bounds.posY(), lastY;

    private boolean dragging, lastDragging;

    public abstract void render();

    public void renderDummy(float mouseX, float mouseY) {
        this.handleDrag(mouseX, mouseY);

        if(lastDragging && !dragging)
            this.save();

        this.lastDragging = dragging;
    }

    private void handleDrag(float mouseX, float mouseY) {
        if(this.isHovered(mouseX, mouseY)) {
            if(Mouse.isButtonDown(0)) {
                this.dragMod(mouseX, mouseY);
            }
        }
        this.releaseMod(mouseX, mouseY);
    }

    private void dragMod(float mouseX, float mouseY) {
        if(!dragging) {
            this.dragging = true;

            this.lastX = this.posX - mouseX;
            this.lastY = this.posY - mouseY;
        }
    }

    private void releaseMod(float mouseX, float mouseY) {
        if(dragging) {
            if(!Mouse.isButtonDown(0))
                this.dragging = false;

            this.posX = mouseX + this.lastX;
            this.posY = mouseY + this.lastY;
        }
    }

    public boolean isHovered(float mouseX, float mouseY) {
        return (mouseX >= posX && mouseX <= posX + bounds.width()) && (mouseY >= posY && mouseY <= posY + bounds.height());
    }

    @Override
    public JsonObject toJson() {
        JsonObject json = new JsonObject();

        json.addProperty("enabled", isEnabled());
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
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
