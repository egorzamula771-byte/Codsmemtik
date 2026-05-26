package ru.cosmod.cosmeticclient.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.StringTextComponent;
import ru.cosmod.cosmeticclient.features.*;

public class ModMenu extends Screen {
    private final Screen parentScreen;
    private boolean killauraEnabled = true;
    private boolean espEnabled = true;
    private boolean speedhackEnabled = true;
    private boolean flyEnabled = true;
    private boolean triggerbotEnabled = true;

    public ModMenu(Screen parentScreen) {
        super(new StringTextComponent("CosmeticClient Menu"));
        this.parentScreen = parentScreen;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTick) {
        this.renderDirtBackground(matrixStack);

        int centerX = this.width / 2;
        int centerY = this.height / 2;
        int menuWidth = 200;
        int menuHeight = 250;
        int startX = centerX - menuWidth / 2;
        int startY = centerY - menuHeight / 2;

        fill(matrixStack, startX, startY, startX + menuWidth, startY + menuHeight, 0xFF1a1a1a);
        fill(matrixStack, startX, startY, startX + menuWidth, startY + 25, 0xFF00d4ff);

        this.font.draw(matrixStack, "CosmeticClient", startX + 10, startY + 8, 0xFFFFFF);

        int buttonY = startY + 35;
        int buttonWidth = menuWidth - 20;
        int buttonHeight = 20;
        int buttonX = startX + 10;

        drawButton(matrixStack, buttonX, buttonY, buttonWidth, buttonHeight, "Killaura", killauraEnabled, mouseX, mouseY);
        if (isMouseOver(mouseX, mouseY, buttonX, buttonY, buttonWidth, buttonHeight)) {
            killauraEnabled = !killauraEnabled;
        }

        buttonY += 25;
        drawButton(matrixStack, buttonX, buttonY, buttonWidth, buttonHeight, "ESP", espEnabled, mouseX, mouseY);
        if (isMouseOver(mouseX, mouseY, buttonX, buttonY, buttonWidth, buttonHeight)) {
            espEnabled = !espEnabled;
        }

        buttonY += 25;
        drawButton(matrixStack, buttonX, buttonY, buttonWidth, buttonHeight, "Speedhack", speedhackEnabled, mouseX, mouseY);
        if (isMouseOver(mouseX, mouseY, buttonX, buttonY, buttonWidth, buttonHeight)) {
            speedhackEnabled = !speedhackEnabled;
        }

        buttonY += 25;
        drawButton(matrixStack, buttonX, buttonY, buttonWidth, buttonHeight, "Fly", flyEnabled, mouseX, mouseY);
        if (isMouseOver(mouseX, mouseY, buttonX, buttonY, buttonWidth, buttonHeight)) {
            flyEnabled = !flyEnabled;
        }

        buttonY += 25;
        drawButton(matrixStack, buttonX, buttonY, buttonWidth, buttonHeight, "TriggerBot", triggerbotEnabled, mouseX, mouseY);
        if (isMouseOver(mouseX, mouseY, buttonX, buttonY, buttonWidth, buttonHeight)) {
            triggerbotEnabled = !triggerbotEnabled;
        }

        int closeButtonY = startY + menuHeight - 25;
        drawButton(matrixStack, buttonX, closeButtonY, buttonWidth, buttonHeight, "Close", false, mouseX, mouseY);

        super.render(matrixStack, mouseX, mouseY, partialTick);
    }

    private void drawButton(MatrixStack matrixStack, int x, int y, int width, int height, String text, boolean enabled, int mouseX, int mouseY) {
        int color = enabled ? 0xFF00ff00 : 0xFFFF0000;
        if (isMouseOver(mouseX, mouseY, x, y, width, height)) {
            color = enabled ? 0xFF00cc00 : 0xFFcc0000;
        }

        fill(matrixStack, x, y, x + width, y + height, color);
        this.font.draw(matrixStack, text + (enabled ? " [ON]" : " [OFF]"), x + 5, y + 6, 0xFFFFFF);
    }

    private boolean isMouseOver(int mouseX, int mouseY, int x, int y, int width, int height) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }

    @Override
    public void onClose() {
        this.minecraft.setScreen(parentScreen);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}