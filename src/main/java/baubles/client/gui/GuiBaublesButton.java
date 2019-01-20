package baubles.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;

import baubles.common.network.PacketHandler;
import baubles.common.network.PacketOpenBaublesInventory;
import baubles.common.network.PacketOpenNormalInventory;
import org.lwjgl.opengl.GL11;

public class GuiBaublesButton extends GuiButton {

    private final GuiContainer parentGui;

    public GuiBaublesButton(int buttonId, GuiContainer parentGui, int x, int y, int width, int height, String buttonText) {
        super(buttonId, x, parentGui.getGuiTop() + y, width, height, buttonText);
        this.parentGui = parentGui;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        boolean pressed = super.mouseClicked(mouseX - this.parentGui.getGuiLeft(), mouseY, button);
        if (pressed) {
            if (parentGui instanceof GuiInventory) {
                PacketHandler.INSTANCE.sendToServer(new PacketOpenBaublesInventory());
            } else {
                ((GuiPlayerExpanded) parentGui).displayNormalInventory();
                PacketHandler.INSTANCE.sendToServer(new PacketOpenNormalInventory());
            }
        }
        return pressed;
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        if (this.visible) {
            int x = this.x + this.parentGui.getGuiLeft();

            FontRenderer fontrenderer = Minecraft.getInstance().fontRenderer;
            Minecraft.getInstance().getTextureManager().bindTexture(GuiPlayerExpanded.background);
            GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.hovered = mouseX >= x && mouseY >= this.y && mouseX < x + this.width && mouseY < this.y + this.height;
            int k = this.getHoverState(this.hovered);
            GlStateManager.enableBlend();
            GlStateManager.blendFuncSeparate(770, 771, 1, 0);
            GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

            GlStateManager.pushMatrix();
            GlStateManager.translated(0, 0, 200);
            if (k == 1) {
                this.drawTexturedModalRect(x, this.y, 200, 48, 10, 10);
            } else {
                this.drawTexturedModalRect(x, this.y, 210, 48, 10, 10);
                this.drawCenteredString(fontrenderer, I18n.format(this.displayString), x + 5, this.y + this.height, 0xffffff);
            }
            GlStateManager.popMatrix();

            //this.mouseDragged(mc, mouseX, mouseY);
        }
    }
}
