package me.example.client.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

/**
 * Basic mixin client base.
 * @author Geuxy
 */
public class RenderUtil {

    // Draws a centered string with a shadow
    public static void drawCenteredStringWithShadow(String text, float x, float y, int color) {
        Minecraft.getMinecraft().fontRendererObj.drawString(text, x - (float) Minecraft.getMinecraft().fontRendererObj.getStringWidth(text) / 2, y, color, true);
    }

    // Draws a centered string
    public static void drawCenteredString(String text, float x, float y, int color) {
        Minecraft.getMinecraft().fontRendererObj.drawString(text, x - (float) Minecraft.getMinecraft().fontRendererObj.getStringWidth(text) / 2, y, color, false);
    }

    // Draws a rectangle but in a cleaner(ish) way
    public static void drawRect(float x, float y, float width, float height, int color) {
        drawRectangle(x, y, x + width, y + height, color);
    }

    // Draws a rectangle
    public static void drawRectangle(float left, float top, float right, float bottom, int color) {
        if (left < right) {
            float i = left;
            left = right;
            right = i;
        }

        if (top < bottom) {
            float j = top;
            top = bottom;
            bottom = j;
        }

        float alpha = (float)(color >> 24 & 255) / 255.0F;
        float red = (float)(color >> 16 & 255) / 255.0F;
        float green = (float)(color >> 8 & 255) / 255.0F;
        float blue = (float)(color & 255) / 255.0F;

        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();

        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(red, green, blue, alpha);

        worldrenderer.begin(7, DefaultVertexFormats.POSITION);
        worldrenderer.pos(left, bottom, 0.0D).endVertex();
        worldrenderer.pos(right, bottom, 0.0D).endVertex();
        worldrenderer.pos(right, top, 0.0D).endVertex();
        worldrenderer.pos(left, top, 0.0D).endVertex();
        tessellator.draw();

        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

}
