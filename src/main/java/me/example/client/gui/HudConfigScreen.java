package me.example.client.gui;

import me.example.client.BaseClient;
import me.example.client.gui.elements.BaseButton;
import me.example.client.gui.modlist.GuiModlist;
import me.example.client.mod.HudMod;
import me.example.client.mod.Mod;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

import java.io.IOException;

/**
 * Basic mixin client base.
 * @author Geuxy
 */
public class HudConfigScreen extends GuiScreen {

    private GuiModlist guiModlist;

    private HudMod draggingMod;

    @Override
    public void initGui() {
        draggingMod = null;

        this.buttonList.add(new BaseButton(0, width / 2 - 60, height / 2 - 10, 120, 20, "Mods"));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();

        super.drawScreen(mouseX, mouseY, partialTicks);

        for(Mod mod : BaseClient.INSTANCE.getModManager()) {
            if(mod.isHud() && mod.isEnabled()) {
                HudMod hudMod = (HudMod) mod;

                if(draggingMod == hudMod) {
                    hudMod.animateDrag(mouseX, mouseY);
                }

                hudMod.renderDummy();
            }
        }
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);

        if(guiModlist == null) {
            guiModlist = new GuiModlist();
        }

        mc.displayGuiScreen(guiModlist);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);

        if(draggingMod == null) {
            this.draggingMod = (HudMod) BaseClient.INSTANCE.getModManager()
                .findFirst(m -> m.isHud() && m.isEnabled() && ((HudMod)m).isHovered(mouseX, mouseY));
        }

        if(draggingMod != null) {
            this.draggingMod.drag(mouseX, mouseY);
        }
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);
        if(draggingMod != null) {
            BaseClient.INSTANCE.getConfigManager().getModConfig().save();
        }
        this.draggingMod = null;
    }
}
