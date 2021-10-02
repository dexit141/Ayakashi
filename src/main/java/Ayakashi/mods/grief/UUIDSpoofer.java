package Ayakashi.mods.grief;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.Session;

import java.io.IOException;

public class UUIDSpoofer extends GuiScreen {
    protected GuiTextField ipField;

    protected GuiTextField fakeNickField;

    protected GuiTextField realNickField;

    protected GuiScreen prevScreen;

    public UUIDSpoofer(GuiScreen screen) {
        this.prevScreen = screen;
    }

    public void initGui() {
        int fieldWidth = 200;
        int fieldHeight = 20;
        this.buttonList.clear();
        this.buttonList.add(new GuiButton(1, width / 2 - 100, height / 4 + 145, fieldWidth, fieldHeight, "START"));
        this.buttonList.add(new GuiButton(2, width / 2 - 210, height / 4 + 95 + fieldHeight + 55, fieldWidth, fieldHeight, "Back"));
        this.buttonList.add(new GuiButton(3, width / 2 + 10, height / 4 + 95 + fieldHeight + 55, fieldWidth, fieldHeight, getEnableButtonText()));
        this.realNickField = new GuiTextField(2, this.fontRendererObj, width / 2 - 100, height / 5 + 50, fieldWidth, fieldHeight);
        this.fakeNickField = new GuiTextField(1, this.fontRendererObj, width / 2 - 100, height / 5 + 90, fieldWidth, fieldHeight);
        this.ipField = new GuiTextField(0, this.fontRendererObj, width / 2 - 100, height / 5 + 130, fieldWidth, fieldHeight);
        this.ipField.setText(this.mc.getFakeIp());
        this.fakeNickField.setText(this.mc.getFakeNick());
        this.realNickField.setText(this.mc.getSession().getUsername());
    }

    private String getEnableButtonText() {
        return this.mc.isUUIDHack ? ("§aEnabled") : ("§cDisabled");
    }

    public void handleMouseInput() throws IOException {
        super.handleMouseInput();
    }

    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 1) {
            Session realSession = this.mc.getSession();
            this.mc.setSession(new Session(this.realNickField.getText(), realSession.getPlayerID(), realSession.getToken(), Session.Type.LEGACY.name()));
            this.mc.setFakeNick(this.fakeNickField.getText());
            this.mc.setFakeIp(this.ipField.getText());
            this.mc.displayGuiScreen(this.prevScreen);
            if (this.mc.getServerData() != null && this.mc.theWorld != null) {
                ServerData data = this.mc.getServerData();
                this.mc.theWorld.sendQuittingDisconnectingPacket();
                this.mc.loadWorld(null);
                this.mc.displayGuiScreen(new GuiConnecting(this.prevScreen, this.mc, data));
            } else {
                this.mc.displayGuiScreen(this.prevScreen);
            }
        } else if (button.id == 2) {
            this.mc.displayGuiScreen(this.prevScreen);
        } else if (button.id == 3) {
            this.mc.isUUIDHack = !this.mc.isUUIDHack;
            button.displayString = getEnableButtonText();
        }
    }

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        this.ipField.mouseClicked(mouseX, mouseY, mouseButton);
        this.fakeNickField.mouseClicked(mouseX, mouseY, mouseButton);
        this.realNickField.mouseClicked(mouseX, mouseY, mouseButton);
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if (keyCode == 1) {
            this.mc.displayGuiScreen(this.prevScreen);
            return;
        }
        if (keyCode == 15)
            if (this.realNickField.isFocused()) {
                this.realNickField.setFocused(false);
                this.fakeNickField.setFocused(true);
            } else if (this.fakeNickField.isFocused()) {
                this.fakeNickField.setFocused(false);
                this.ipField.setFocused(true);
            } else if (this.ipField.isFocused()) {
                this.ipField.setFocused(false);
                this.realNickField.setFocused(true);
            }
        if (this.ipField.isFocused())
            this.ipField.textboxKeyTyped(typedChar, keyCode);
        if (this.fakeNickField.isFocused())
            this.fakeNickField.textboxKeyTyped(typedChar, keyCode);
        if (this.realNickField.isFocused())
            this.realNickField.textboxKeyTyped(typedChar, keyCode);
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        drawCenteredString(this.fontRendererObj, "Your Nick", width / 2, this.realNickField.yPosition - 15, 16777215);
        drawCenteredString(this.fontRendererObj, "Nick of Player from which UUID you want use", width / 2, this.fakeNickField.yPosition - 15, 16777215);
        drawCenteredString(this.fontRendererObj, "Fake IP", width / 2, this.ipField.yPosition - 15, 16777215);
        this.ipField.drawTextBox();
        this.fakeNickField.drawTextBox();
        this.realNickField.drawTextBox();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
