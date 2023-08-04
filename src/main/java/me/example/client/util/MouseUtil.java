package me.example.client.util;

public class MouseUtil {

    public static boolean isMouseOver(int mouseX, int mouseY, float x, float y, float width, float height) {
        return (mouseX >= x && mouseX <= x + width) && (mouseY >= y && mouseY <= y + height);
    }

}
