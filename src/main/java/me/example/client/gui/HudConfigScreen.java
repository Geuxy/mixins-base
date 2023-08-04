package me.example.client.gui;

import me.example.client.Base;
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

    @Override
    public void initGui() {
        super.initGui();

        this.buttonList.add(new BaseButton(0, width / 2 - 60, height / 2 - 10, 120, 20, "Mods"));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();

        super.drawScreen(mouseX, mouseY, partialTicks);

        for(Mod mod : Base.INSTANCE.getModManager().getModList()) {

            if(mod.isHud() && mod.isEnabled())
                ((HudMod)mod).renderDummy((float) mouseX, (float) mouseY);
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

}
