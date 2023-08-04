package me.example.client.gui.modlist.value;

import me.example.client.Base;
import me.example.client.gui.elements.BaseButton;
import me.example.client.gui.modlist.GuiModlist;
import me.example.client.gui.modlist.component.impl.ModButton;
import me.example.client.gui.modlist.component.sub.SubComponent;
import me.example.client.gui.modlist.component.sub.impl.CheckBox;
import me.example.client.mod.Mod;

import me.example.client.util.MouseUtil;
import me.example.client.util.RenderUtil;
import me.example.client.value.impl.CheckBoxValue;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Basic mixin client base.
 * @author Geuxy
 */
public class GuiModValues extends GuiScreen {
    private final GuiModlist modlist;
    private final Mod mod;

    private int valueScroll;

    private final List<SubComponent<?>> VALUES = new ArrayList<>();

    public GuiModValues(GuiModlist modlist, Mod mod) {
        this.modlist = modlist;
        this.mod = mod;

        Base.INSTANCE.getValueManager().getValues(mod).forEach(v -> {
            float compWidth = modlist.getWindowWidth() * 2 - 24;
            float compHeight = 16;

            if(v instanceof CheckBoxValue) {
                VALUES.add(new CheckBox((CheckBoxValue) v, 0, 0, compWidth, compHeight));
            }
        });
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();

        modlist.setHalfWidth((float) width / 2);
        modlist.setHalfHeight((float) height / 2);

        this.handleScrolling();
        this.updateValuePosition();
        modlist.drawWindow();

        GL11.glEnable(GL11.GL_SCISSOR_TEST);
        RenderUtil.glScissor(modlist.getHalfWidth() - modlist.getWindowWidth(), modlist.getHalfHeight() - modlist.getWindowHeight() + 31, modlist.getWindowWidth() * 2, modlist.getWindowHeight() * 2 - 31);
        VALUES.forEach(v -> v.drawScreen(mouseX, mouseY));
        GL11.glDisable(GL11.GL_SCISSOR_TEST);
        super.drawScreen(mouseX, mouseY, partialTicks);

        int backX = (int) (modlist.getHalfWidth() + modlist.getWindowWidth());
        int backY = (int) (modlist.getHalfHeight() - modlist.getWindowHeight());

        mc.fontRendererObj.drawString("<-", backX - 20, backY + 15 - mc.fontRendererObj.FONT_HEIGHT / 2, isOverBack(mouseX, mouseY) ? 0xFFFF4F4F : -1);
    }

    private void updateValuePosition() {
        int i = 0;
        for(SubComponent<?> value : VALUES) {
            i += 30;

            float valueX = modlist.getHalfWidth() - modlist.getWindowWidth() + 12;
            float valueY = modlist.getHalfHeight() - modlist.getWindowHeight() + 12 + i;

            value.setX(valueX);
            value.setY(valueY + valueScroll);
        }
    }

    private void handleScrolling() {
        int wheel = Mouse.getDWheel();
        int amount = 10;

        if(wheel > 0) {
            this.valueScroll += amount;
        } else if(wheel < 0) {
            this.valueScroll -= amount;
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);

        if(modlist.isOverModArea(mouseX, mouseY)) {
            VALUES.forEach(v -> v.mouseClicked(mouseX, mouseY, mouseButton));

        } else if(isOverBack(mouseX, mouseY)) {
            Minecraft.getMinecraft().displayGuiScreen(modlist);
        }
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        Minecraft.getMinecraft().displayGuiScreen(this);
        super.actionPerformed(button);
    }

    private boolean isOverBack(int mouseX, int mouseY) {
        float backX = modlist.getHalfWidth() + modlist.getWindowWidth() - 30;
        float backY = modlist.getHalfHeight() - modlist.getWindowHeight();

        return MouseUtil.isMouseOver(mouseX, mouseY, backX, backY, 30, 30);
    }

}
