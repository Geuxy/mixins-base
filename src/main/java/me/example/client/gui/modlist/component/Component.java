package me.example.client.gui.modlist.component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import me.example.client.gui.modlist.GuiModlist;
import me.example.client.util.input.MouseUtil;

/**
 * Basic mixin client base.
 * @author Geuxy
 */
@Getter @Setter @AllArgsConstructor
public abstract class Component {
    protected GuiModlist parent;
    protected float x, y, width, height;

    public abstract void drawScreen(int mouseX, int mouseY);
    public abstract void mouseClicked(int mouseX, int mouseY, int mouseButton);

    public boolean isHovered(int mouseX, int mouseY) {
        return MouseUtil.isMouseAt(mouseX, mouseY, x, y, width, height);
    }

}
