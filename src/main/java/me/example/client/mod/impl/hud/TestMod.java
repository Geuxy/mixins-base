package me.example.client.mod.impl.hud;

import me.example.client.mod.annotations.Bounds;
import me.example.client.mod.HudMod;
import me.example.client.mod.annotations.ModInfo;
import me.example.client.mod.value.impl.ComboValue;
import me.example.client.util.visual.RenderUtil;
import me.example.client.mod.value.impl.CheckBoxValue;

/**
 * Basic mixin client base.
 * @author Geuxy

 * The values in @Bounds are the default for when there is no config, if there is one, the pos values are
 * useless, except the size because there is no in-game mod scaling for now.
 */
@Bounds(posX = 20, posY = 5, width = 100, height = 20)
@ModInfo(name = "TestModule", description = "A cool example mod")
public class TestMod extends HudMod {

    // Setting example
    private final CheckBoxValue border = new CheckBoxValue("Border", false);
    private final ComboValue alignment = new ComboValue("Alignment", "Left", "Centre", "Right");

    /*
     * Renders the mod while in game
     */
    @Override
    public void render() {
        RenderUtil.drawRect(this.getPosX(), this.getPosY(), this.getBounds().width(), this.getBounds().height(), 0x90000000);

        if(border.getValue()) {
            RenderUtil.drawBorderRect(this.getPosX(), this.getPosY(), this.getBounds().width(), this.getBounds().height(), 1, 0x90000000);
        }

        String text = this.getInfo().name();

        float centerX = this.getPosX() + this.getBounds().width() / 2;
        float centerY = this.getPosY() + this.getBounds().height() / 2;

        float centerText = this.getPosX() + this.getBounds().width() - (float) mc.fontRendererObj.getStringWidth(text) / 2;
        float centerTextHeight = (float) mc.fontRendererObj.FONT_HEIGHT / 2;

        switch(alignment.getValue()) {
            case "Left":
                mc.fontRendererObj.drawStringWithShadow(text, this.getPosX(), centerY - centerTextHeight, -1);
                break;

            case "Right":
                RenderUtil.drawCenteredStringWithShadow(text, centerText, centerY - centerTextHeight, -1);
                break;

            default:
                RenderUtil.drawCenteredStringWithShadow(text, centerX, centerY - centerTextHeight, -1);
                break;
        }
    }

    /*
     * Renders the mod while in HudConfigScreen
     */
    @Override
    public void renderDummy() {
        this.render();
    }

}
