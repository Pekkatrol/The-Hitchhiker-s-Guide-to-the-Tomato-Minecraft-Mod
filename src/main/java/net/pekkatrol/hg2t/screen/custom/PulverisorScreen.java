package net.pekkatrol.hg2t.screen.custom;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.pekkatrol.hg2t.HG2Tomato;
import net.pekkatrol.hg2t.screen.renderer.EnergyInfoArea;
import net.pekkatrol.hg2t.util.MouseUtil;

public class PulverisorScreen extends AbstractContainerScreen<PulverisorMenu> {

    private static final ResourceLocation GUI_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(HG2Tomato.MOD_ID, "textures/gui/pulverisor/pulverisor_gui.png");
    private static final ResourceLocation ARROW_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(HG2Tomato.MOD_ID, "textures/gui/arrow_progress.png");

    private static final ResourceLocation COMBUSTION_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(HG2Tomato.MOD_ID, "textures/gui/pulverision.png");

    private EnergyInfoArea energyInfoArea;

    public PulverisorScreen(PulverisorMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
        assignEnergyInfoArea();
    }

    private void assignEnergyInfoArea() {
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        energyInfoArea = new EnergyInfoArea(x + 145, y + 15, menu.blockEntity.getEnergyStorage());
    }

    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, GUI_TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        pGuiGraphics.blit(GUI_TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        renderProgressArrow(pGuiGraphics, x, y);
        energyInfoArea.draw(pGuiGraphics);
    }

    @Override
    protected void renderLabels(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY) {
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        renderEnergyAreaTooltips(pGuiGraphics, pMouseX, pMouseY, x, y);
    }

    private void renderEnergyAreaTooltips(GuiGraphics guiGraphics, int mouseX, int mouseY, int x, int y) {
        if (isMouseAboveArea(mouseX, mouseY, x, y, 145, 15, 15, 53)) {
            guiGraphics.renderComponentTooltip(font, energyInfoArea.getTooltips(),
                    mouseX - x, mouseY - y);
        }
    }

    private boolean isMouseAboveArea(int pMouseX, int pMouseY, int x, int y, int offsetX, int offsetY, int width, int height) {
        return MouseUtil.isMouseOver(pMouseX, pMouseY, x + offsetX, y + offsetY, width, height);
    }

    private void renderProgressArrow(GuiGraphics guiGraphics, int x, int y) {
        if (menu.isCrafting()) {
            guiGraphics.blit(ARROW_TEXTURE, x + 49, y + 34, 0, 0,
                    menu.getScaledArrowProgress(), 16, 24, 16);

            int progress = menu.getScaledCombustionProgress();
            int maxHeight = 16;
            guiGraphics.blit(
                    COMBUSTION_TEXTURE,
                    x + 21, y + 53 + (maxHeight - progress), 0, maxHeight - progress,
                    24, progress, 24, maxHeight);
        }
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pGuiGraphics, pMouseX, pMouseY);
    }
}