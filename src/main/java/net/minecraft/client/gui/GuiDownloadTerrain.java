package net.minecraft.client.gui;

import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.resources.I18n;
import net.minecraft.network.play.client.C00PacketKeepAlive;
import net.optifine.CustomLoadingScreen;
import net.optifine.CustomLoadingScreens;

import java.io.IOException;

public class GuiDownloadTerrain extends GuiScreen {
    private final NetHandlerPlayClient netHandlerPlayClient;
    private final CustomLoadingScreen customLoadingScreen = CustomLoadingScreens.getCustomLoadingScreen();
    private int progress;

    public GuiDownloadTerrain(NetHandlerPlayClient netHandler) {
        this.netHandlerPlayClient = netHandler;
    }

    protected void keyTyped(char typedChar, int keyCode) throws IOException {
    }

    public void initGui() {
        this.buttonList.clear();
    }

    public void updateScreen() {
        ++this.progress;
        if (this.progress % 20 == 0) {
            this.netHandlerPlayClient.addToSendQueue(new C00PacketKeepAlive());
        }

    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        if (this.customLoadingScreen != null) {
            this.customLoadingScreen.drawBackground(width, height);
        } else {
            this.drawBackground(0);
        }

        drawCenteredString(this.fontRendererObj, I18n.format("multiplayer.downloadingTerrain"), width / 2, height / 2 - 50, 16777215);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    public boolean doesGuiPauseGame() {
        return false;
    }
}
