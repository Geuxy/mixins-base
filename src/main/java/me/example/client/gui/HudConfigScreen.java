package me.example.client.gui;

import me.example.client.Base;
import me.example.client.mod.HudMod;
import me.example.client.mod.Mod;
import net.minecraft.client.gui.GuiScreen;

/**
 * Basic mixin client base.
 * @author Geuxy
 */
public class HudConfigScreen extends GuiScreen {

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);

        drawDefaultBackground();

        for(Mod mod : Base.INSTANCE.getModManager().getModList()) {

            if(mod.isHud() && mod.isEnabled())
                ((HudMod)mod).renderDummy((float) mouseX, (float) mouseY);
        }
    }

}
