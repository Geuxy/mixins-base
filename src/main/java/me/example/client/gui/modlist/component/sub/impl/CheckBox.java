package me.example.client.gui.modlist.component.sub.impl;

import me.example.client.gui.modlist.component.sub.SubComponent;
import me.example.client.util.RenderUtil;
import me.example.client.value.impl.CheckBoxValue;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

/**
 * Basic mixin client base.
 * @author Geuxy
 */
public class CheckBox extends SubComponent<CheckBoxValue> {

    public CheckBox(CheckBoxValue value, float x, float y, float width, float height) {
        super(value, x, y, width, height);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY) {
        FontRenderer font = Minecraft.getMinecraft().fontRendererObj;

        RenderUtil.drawRect(x, y, height, height, 0x3F000000);
        RenderUtil.drawBorderRect(x, y, height, height, 0.5F, 0x3F000000);

        if(value.getValue()) {
            RenderUtil.drawRect(x + 4, y + 4, height - 8, height - 8, 0xFF4FFF4F);

        }

        font.drawString(value.getName(), (int)(x + height + 5), (int)(y + height / 2 - font.FONT_HEIGHT / 2), -1);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if(isHovered(mouseX, mouseY)) {
            value.setValue(!value.getValue());
        }
    }

}
