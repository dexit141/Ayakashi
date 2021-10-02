package Ayakashi.mods.crashitems;

import Ayakashi.helpers.ItemStackHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;
import org.lwjgl.input.Keyboard;

import java.io.IOException;

public class GuiItemCreator extends GuiScreen {
    private final GuiScreen previous;
    private GuiTextField nameOrId;
    private GuiTextField name;

    public GuiItemCreator(GuiScreen previous) {
        this.previous = previous;
    }

    public static String withColors(String identifier, String input) {
        String output = input;
        input.indexOf(identifier);

        while (output.contains(identifier)) {
            output = output.replace(identifier, "§");
            output.indexOf(identifier);
        }

        return output;
    }

    public void initGui() {
        Keyboard.enableRepeatEvents(true);
        this.buttonList.clear();
        this.buttonList.add(new GuiButton(0, width / 2 - 100, height / 5 + 100, "Give Item"));
        (this.nameOrId = new GuiTextField(0, this.fontRendererObj, width / 2 - 100, height / 5 + 30, 200, 20)).setText("diamond_sword 1 0");
        this.nameOrId.setMaxStringLength(Integer.MAX_VALUE);
    }

    public void updateScreen() {
        this.nameOrId.updateCursorCounter();
    }

    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 0) {
            Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C10PacketCreativeInventoryAction(36, ItemStackHelper.stringtostack(this.nameOrId.getText())));
            this.mc.displayGuiScreen(this.previous);
        }

        if (button.id == 1) {
            this.mc.displayGuiScreen(this.previous);
        }

    }

    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        this.nameOrId.textboxKeyTyped(typedChar, keyCode);
        super.keyTyped(typedChar, keyCode);
    }

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        this.nameOrId.mouseClicked(mouseX, mouseY, mouseButton);
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        this.nameOrId.drawTextBox();
        drawCenteredString(this.fontRendererObj, "Name/ID", width / 2, this.nameOrId.yPosition - 15, -1);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
