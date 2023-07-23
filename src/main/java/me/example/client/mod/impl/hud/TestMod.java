package me.example.client.mod.impl.hud;

import me.example.client.mod.annotations.Bounds;
import me.example.client.mod.HudMod;
import me.example.client.mod.annotations.ModInfo;
import me.example.client.util.RenderUtil;

/**
 * Basic mixin client base.
 * @author Geuxy
 */
@Bounds(posX = 20, posY = 5, width = 100, height = 20)
@ModInfo(name = "TestModule", description = "A cool example module")
public class TestMod extends HudMod {

    // Renders mod while in game
    @Override
    public void render() {
        RenderUtil.drawRect(this.getPosX(), this.getPosY(), this.getBounds().width(), this.getBounds().height(), 0x90000000);

        float centerX = this.getPosX() + this.getBounds().width() / 2;
        float centerY = this.getPosY() + this.getBounds().height() / 2;

        RenderUtil.drawCenteredStringWithShadow(this.getInfo().name(), centerX, centerY - (float) mc.fontRendererObj.FONT_HEIGHT / 2, -1);
    }

    // Renders mod while in HudConfigScreen
    @Override
    public void renderDummy(float mouseX, float mouseY) {
        super.renderDummy(mouseX, mouseY);

        this.render();
    }

}
