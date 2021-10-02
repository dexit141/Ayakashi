package Ayakashi.helpers;

import Ayakashi.managers.GuiAltManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.util.ResourceLocation;

public class MainMenuHelper extends GuiScreen {
    private int fade = 0;
    private int speed = 0;
    private boolean In = false;
    private boolean start = false;

    public void drawScreen(int mouseX, int mouseY, float p) {
        Minecraft.getMinecraft().gameSettings.ofFastRender = true;
        this.mc.getTextureManager().bindTexture(new ResourceLocation("Ayakashi/menu/MainMenu.png"));
        drawModalRectWithCustomSizedTexture(0, 0, 0.0F, 0.0F, width, height, (float) width, (float) height);
        this.fadeSideBar(mouseX);
        Gui.drawRect(this.fade, 0, width, height, Integer.MIN_VALUE);
        Gui.drawRect(this.fade + 2, 0, this.fade, height, 15597803);
        ClientRenderHelper.INSTANCE.drawLine(this.fade + 1, height / 2 + 30, this.fade - 20, height / 2, 10, 15597803);
        ClientRenderHelper.INSTANCE.drawLine(this.fade + 1, height / 2 - 30, this.fade - 20, height / 2, 10, 15597803);
        if (this.In) {
            this.mc.fontRendererObj.drawString(ChatHelper.fix("&0&l(&4&l<&0&l)"), this.fade - 20, height / 2 - 3, -1);
        } else {
            this.mc.fontRendererObj.drawString(ChatHelper.fix("&0&l(&4&l>&0&l)"), this.fade - 20, height / 2 - 3, -1);
        }

        super.drawScreen(mouseX, mouseY, p);
    }

    public void initGui() {
        if (!this.start) {
            this.start = true;
        }

        this.fade = GuiScreen.width;
        this.speed = 17;
        this.createButtons();
    }

    public void actionPerformed(GuiButton button) {
        if (button.id == 0) {
            this.mc.displayGuiScreen(new GuiSelectWorld(new MainMenuHelper()));
        }

        if (button.id == 1) {
            this.mc.displayGuiScreen(new GuiMultiplayer(new MainMenuHelper()));
        }

        if (button.id == 2) {
            this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
        }

        if (button.id == 3) {
            this.mc.displayGuiScreen(new GuiAltManager());
        }

        if (button.id == 4) {
            this.mc.shutdown();
        }

    }

    public void createButtons() {
        int y = 90;
        this.buttonList.add(new GuiButton(0, this.fade + 30, height / 2 - 150 + y, 100, 20, "Singleplayer"));
        this.buttonList.add(new GuiButton(1, this.fade + 30, height / 2 - 125 + y, 100, 20, "Multiplayer"));
        this.buttonList.add(new GuiButton(2, this.fade + 30, height / 2 - 100 + y, 100, 20, "Settings"));
        this.buttonList.add(new GuiButton(3, this.fade + 30, height / 2 - 75 + y, 100, 20, "AltManager"));
        this.buttonList.add(new GuiButton(4, this.fade + 30, height / 2 - 50 + y, 100, 20, "Quit"));
        this.buttonList.add(new GuiButton(10, this.fade + 1000, 1000 + y, 1050, 1020, ""));
    }

    public void onGuiClosed() {
        this.start = false;
    }

    private void fadeSideBar(int mouseX) {
        if (mouseX > width - 150 - 4) {
            if (this.fade >= width - 150) {
                this.fade -= this.speed;
                if (this.speed != 2) {
                    --this.speed;
                }

                this.buttonList.clear();
                this.createButtons();
                this.In = false;
            } else {
                this.fade = width - 150 - 1;
            }
        } else {
            if (this.fade <= width) {
                this.fade += this.speed;
                if (this.speed != 2) {
                    this.speed = 17;
                }

                this.buttonList.clear();
                this.createButtons();
                this.In = true;
            } else {
                this.fade = width + 1;
            }

            this.speed = 17;
        }

    }
}
