package me.example.client.gui.modlist;

import lombok.Getter;
import lombok.Setter;
import me.example.client.BaseClient;
import me.example.client.gui.modlist.component.impl.ModButton;
import me.example.client.util.input.MouseUtil;
import me.example.client.util.visual.RenderUtil;
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

@Getter @Setter
public class GuiModlist extends GuiScreen {

    private final List<ModButton> MODS = new ArrayList<>();

    private float windowWidth = 200;
    private float windowHeight = 130;

    private float halfWidth = width / 2;
    private float halfHeight = height / 2;

    private int modScroll;

    // Add module buttons
    public GuiModlist() {
        float modWidth = windowWidth * 2 - 24;
        float modHeight = 25;

        BaseClient.INSTANCE.getModManager().forEach(m -> MODS.add(new ModButton(m, this, 0, 0, modWidth, modHeight)));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();

        this.halfWidth = (float) width / 2;
        this.halfHeight = (float) height / 2;

        this.handleScrolling();
        this.updateModPosition();

        super.drawScreen(mouseX, mouseY, partialTicks);

        this.drawWindow();

        // Render mod button
        GL11.glEnable(GL11.GL_SCISSOR_TEST);
        RenderUtil.glScissor(halfWidth - windowWidth, halfHeight - windowHeight + 31, windowWidth * 2, windowHeight * 2 - 31);
        MODS.forEach(m -> m.drawScreen(mouseX, mouseY));
        GL11.glDisable(GL11.GL_SCISSOR_TEST);
    }

    public void drawWindow() {
        RenderUtil.drawRect(halfWidth - windowWidth, halfHeight - windowHeight, windowWidth * 2, windowHeight * 2, 0x90000000);
        RenderUtil.drawBorderRect(halfWidth - windowWidth, halfHeight - windowHeight, windowWidth * 2, windowHeight * 2, 0.5F, 0x90000000);
        RenderUtil.drawBorderRect(halfWidth - windowWidth, halfHeight - windowHeight, windowWidth * 2, 30, 0.5F, 0x90000000);

        String title = BaseClient.INSTANCE.getName() + " Client";
        mc.fontRendererObj.drawString(title, (int) (halfWidth - windowWidth + 10), (int) (halfHeight - windowHeight + 15) - mc.fontRendererObj.FONT_HEIGHT / 2, -1);
    }

    private void handleScrolling() {
        int wheel = Mouse.getDWheel();
        int amount = 10;

        if(wheel > 0) {
            this.modScroll += amount;
        } else if(wheel < 0) {
            this.modScroll -= amount;
        }
    }

    private void updateModPosition() {
        int i = 0;
        for(ModButton mod : MODS) {
            i += 30;

            float modX = halfWidth - windowWidth + 12;
            float modY = halfHeight - windowHeight + 12 + i;

            mod.setX(modX);
            mod.setY(modY + modScroll);
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);

        if (isOverModArea(mouseX, mouseY)) {
            MODS.forEach(m -> m.mouseClicked(mouseX, mouseY, mouseButton));
        }
    }

    public boolean isOverModArea(int mouseX, int mouseY) {
        return MouseUtil.isMouseAt(mouseX, mouseY, halfWidth - windowWidth, halfHeight - windowHeight + 31, windowWidth * 2, windowHeight * 2 - 31);
    }

}
