package me.example.client.gui.elements;

import me.example.client.util.MouseUtil;
import me.example.client.util.RenderUtil;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

/**
 * Basic mixin client base.
 * @author Geuxy
 */
public class BaseButton extends GuiButton {

    public BaseButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) {
        super(buttonId, x, y, widthIn, heightIn, buttonText);
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        this.hovered = MouseUtil.isMouseOver(mouseX, mouseY, xPosition, yPosition, width, height);
        RenderUtil.drawRect(xPosition, yPosition, width, height, 0x90000000);
        RenderUtil.drawBorderRect(xPosition, yPosition, width, height, 0.5f, 0xFF000000);

        this.mouseDragged(mc, mouseX, mouseY);

        int textColor = this.hovered ? 0xFFFF4F4F : -1;

        RenderUtil.drawCenteredString(this.displayString, this.xPosition + (float) this.width / 2, this.yPosition + (float) (this.height - 8) / 2, textColor);
    }

}
