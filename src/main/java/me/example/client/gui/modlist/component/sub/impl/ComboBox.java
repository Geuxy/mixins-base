package me.example.client.gui.modlist.component.sub.impl;

import me.example.client.gui.modlist.component.sub.SubComponent;
import me.example.client.mod.value.impl.CheckBoxValue;
import me.example.client.mod.value.impl.ComboValue;
import me.example.client.util.visual.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

import java.util.Arrays;
import java.util.Objects;

/**
 * Basic mixin client base.
 * @author Geuxy
 */
public class ComboBox extends SubComponent<ComboValue> {

    public ComboBox(ComboValue value, float x, float y, float width, float height) {
        super(value, x, y, width, height);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY) {
        FontRenderer font = Minecraft.getMinecraft().fontRendererObj;

        String valDisplay = "[" + value.getValue().toUpperCase() + "]";

        font.drawString(value.getName() + " " + valDisplay, (int)x, (int)(y + height / 2 - font.FONT_HEIGHT / 2), -1);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if(isHovered(mouseX, mouseY)) {
            int newIndex = -1;

            String[] options = value.getOptions();

            for(int i = 0; i < options.length; i++) {
                if(options[i].equals(value.getValue())) {
                    newIndex = i;
                    break;
                }
            }

            int maxSize = options.length - 1;

            newIndex++;

            if(newIndex > maxSize) {
                newIndex = 0;
            }

            value.setValue(options[newIndex]);
        }
    }

}
