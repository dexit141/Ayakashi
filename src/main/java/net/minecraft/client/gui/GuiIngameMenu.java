package net.minecraft.client.gui;

import Ayakashi.helpers.MainMenuHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.resources.I18n;

import java.io.IOException;

public class GuiIngameMenu extends GuiScreen {
    private final ServerData field_146301_f = Minecraft.getMinecraft().getCurrentServerData();
    private final ServerListEntryNormal entry;
    private GuiTextField field_146302_g;

    public GuiIngameMenu() {
        String serverIp = Minecraft.getMinecraft().getNetHandler().getNetworkManager().getRemoteAddress().toString().split("/")[1];
        ServerData serverData = new ServerData(serverIp, serverIp, false);
        this.entry = new ServerListEntryNormal(new GuiMultiplayer(null), serverData);
    }

    public void initGui() {
        Minecraft.getMinecraft().gameSettings.ofFastRender = true;
        this.buttonList.clear();
        int i = -16;
        this.buttonList.add(new GuiButton(1, width / 2 - 100, height / 4 + 120 + i, I18n.format("menu.returnToMenu")));
        if (!this.mc.isIntegratedServerRunning()) {
            this.buttonList.get(0).displayString = I18n.format("menu.disconnect");
        }

        this.buttonList.add(new GuiButton(4, width / 2 - 100, height / 4 + 24 + i, I18n.format("menu.returnToGame")));
        this.buttonList.add(new GuiButton(0, width / 2 - 100, height / 4 + 96 + i, 98, 20, I18n.format("menu.options")));
        GuiButton guibutton;
        this.buttonList.add(guibutton = new GuiButton(7, width / 2 + 2, height / 4 + 96 + i, 98, 20, I18n.format("menu.shareToLan")));
        this.buttonList.add(new GuiButton(5, width / 2 - 100, height / 4 + 48 + i, 98, 20, "Reconnect"));
        this.buttonList.add(new GuiButton(6, width / 2 + 2, height / 4 + 48 + i, 98, 20, "Copy IP"));
        guibutton.enabled = this.mc.isSingleplayer() && !this.mc.getIntegratedServer().getPublic();
    }

    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id) {
            case 0:
                this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
                break;
            case 1:
                boolean flag = this.mc.isIntegratedServerRunning();
                button.enabled = false;
                this.mc.theWorld.sendQuittingDisconnectingPacket();
                this.mc.loadWorld(null);
                Minecraft.isGameMenu = false;
                if (flag) {
                    this.mc.displayGuiScreen(new MainMenuHelper());
                } else {
                    this.mc.displayGuiScreen(new GuiMultiplayer(new MainMenuHelper()));
                }
            case 2:
            case 3:
            default:
                break;
            case 4:
                this.mc.displayGuiScreen(null);
                this.mc.setIngameFocus();
                Minecraft.isGameMenu = false;
                break;
            case 5:
                ServerData serverData = this.mc.getCurrentServerData();
                this.mc.theWorld.sendQuittingDisconnectingPacket();
                this.mc.displayGuiScreen(new GuiConnecting(new GuiMultiplayer(new MainMenuHelper()), this.mc, serverData));
            case 6:
                setClipboardString(this.mc.getCurrentServerData().serverIP);
        }

    }

    public void updateScreen() {
        super.updateScreen();
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.drawInfo();
        if (this.mc.theWorld != null && this.mc.getCurrentServerData() != null && !this.mc.isSingleplayer()) {
            this.entry.drawEntry(0, width / 2 - 150, height / 4 + 120 - 165, 275, 35, 0, 0, false);
            super.drawScreen(mouseX, mouseY, partialTicks);
        }

    }

    private void drawInfo() {
        this.mc.fontRendererObj.drawString("§fPlayers§8: §7" + this.field_146301_f.populationInfo, width / 2 - 154, 50, 8421504);
        this.mc.fontRendererObj.drawString("§fVersion§8: §7" + this.mc.getCurrentServerData().gameVersion.replaceAll("git:", "").replaceAll("Bootstrap", "").replaceAll("SNAPSHOT", "").replaceAll(":", "").replaceAll("BungeeCord ", "").replaceAll("Waterfall 1.8.x, 1.9.x, 1.10.x, 1.11.x, 1.12.x, 1.13.x, 1.14.x, 1.15.x, 1.16.x", "Waterfall 1.8.x - 1.16.x").replaceAll("Firefall ", "").replaceAll("SkillCord ", "").replaceAll("MelonBungee ", "").replaceAll("unkown", ""), width / 2 - 154, 60, 8421504);
        this.mc.fontRendererObj.drawString("§fProtocolID§8: §7" + this.field_146301_f.version, width / 2 + 66, 50, 8421504);
        if (this.field_146301_f.pingToServer != -2L && this.field_146301_f.pingToServer != -1L) {
            this.mc.fontRendererObj.drawString("§fPing§8: §7" + this.field_146301_f.pingToServer + "ms", width / 2 + 66, 60, 8421504);
        } else {
            this.mc.fontRendererObj.drawString("§fPing§8: §70ms", width / 2 + 66, 60, 8421504);
        }

    }
}
