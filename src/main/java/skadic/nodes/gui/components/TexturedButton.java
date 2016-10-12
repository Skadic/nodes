package skadic.nodes.gui.components;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

/**
 * Created by eti22 on 11.10.2016.
 */


public class TexturedButton extends GuiButton {

    ResourceLocation resLoc;
    int textureX, textureY;

    public TexturedButton(int buttonId, int x, int y,int width, int height , String buttonText, ResourceLocation resLoc, int texX, int texY) {
        super(buttonId, x, y, width, height, buttonText);

        this.resLoc = resLoc;

        this.textureX = texX;
        this.textureY = texY;
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if(visible) {
            FontRenderer fontRenderer = mc.fontRendererObj;
            mc.getTextureManager().bindTexture(resLoc);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
            int hoverState = this.getHoverState(this.hovered);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            this.drawTexturedModalRect(this.xPosition, this.yPosition, textureX, textureY + hoverState * this.height, this.width, this.height);
            this.mouseDragged(mc, mouseX, mouseY);
            int textColor = 14737632;

            if (packedFGColour != 0) {
                textColor = packedFGColour;
            } else if (!this.enabled) {
                textColor = 10526880;
            } else if (this.hovered) {
                textColor = 16777120;
            }

            this.drawCenteredString(fontRenderer, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, textColor);
        }
    }
}

