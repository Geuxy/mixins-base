package me.example.client.util.input;

import lombok.experimental.UtilityClass;

/**
 * Basic mixin client base.
 * @author Geuxy
 */
@UtilityClass
public class MouseUtil {

    /**
     * Checks if the mouse position is within the given bounds
     *
     * @return mouse cursor is inside bounds
     */
    public static boolean isMouseAt(int mouseX, int mouseY, float x, float y, float width, float height) {
        return (mouseX >= x && mouseX <= x + width) && (mouseY >= y && mouseY <= y + height);
    }

}
