package me.example.client.gui.modlist.component.impl;

import me.example.client.gui.modlist.GuiModlist;
import me.example.client.gui.modlist.component.Component;
import me.example.client.gui.modlist.value.GuiModValues;
import me.example.client.mod.Mod;
import me.example.client.util.visual.RenderUtil;
import net.minecraft.client.Minecraft;

/**
 * Basic mixin client base.
 * @author Geuxy
 */
public class ModButton extends Component {
    private final Mod mod;

    public ModButton(Mod mod, GuiModlist parent, float x, float y, float width, float height) {
        super(parent, x, y, width, height);
        this.mod = mod;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY) {
        RenderUtil.drawRect(x, y, width, height, 0x3F000000);
        RenderUtil.drawBorderRect(x, y, width, height, 0.5F, mod.isEnabled() ? 0xFF4FFF4F : 0xFFFF4F4F);
        RenderUtil.drawCenteredString(mod.getInfo().name() + " §7" + mod.getInfo().description(), x + width / 2, y + height / 2 - 4, -1);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if(isHovered(mouseX, mouseY)) {
            if(mouseButton == 0) {
                mod.toggle();
            } else if(mouseButton == 1) {
                Minecraft.getMinecraft().displayGuiScreen(new GuiModValues(parent, mod));
            }
        }
    }

}
