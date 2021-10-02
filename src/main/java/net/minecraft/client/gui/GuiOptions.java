package net.minecraft.client.gui;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.EnumDifficulty;

import java.awt.*;
import java.io.IOException;
import java.net.URI;

public class GuiOptions extends GuiScreen implements GuiYesNoCallback {
    private static final GameSettings.Options[] field_146440_f;

    static {
        field_146440_f = new GameSettings.Options[]{GameSettings.Options.FOV};
    }

    private final GuiScreen field_146441_g;
    private final GameSettings game_settings_1;
    protected String field_146442_a = "Options";
    private GuiButton field_175357_i;
    private GuiLockIconButton field_175356_r;

    public GuiOptions(GuiScreen p_i1046_1_, GameSettings p_i1046_2_) {
        this.field_146441_g = p_i1046_1_;
        this.game_settings_1 = p_i1046_2_;
    }

    public void initGui() {
        int i = 0;
        this.field_146442_a = I18n.format("options.title");

        for (GameSettings.Options gamesettings$options : field_146440_f) {
            if (gamesettings$options.getEnumFloat()) {
                this.buttonList.add(new GuiOptionSlider(gamesettings$options.returnEnumOrdinal(), width / 2 - 155 + i % 2 * 160, height / 6 - 12 + 24 * (i >> 1), gamesettings$options));
            } else {
                GuiOptionButton guioptionbutton = new GuiOptionButton(gamesettings$options.returnEnumOrdinal(), width / 2 - 155 + i % 2 * 160, height / 6 - 12 + 24 * (i >> 1), gamesettings$options, this.game_settings_1.getKeyBinding(gamesettings$options));
                this.buttonList.add(guioptionbutton);
            }

            ++i;
        }

        if (this.mc.theWorld != null) {
            EnumDifficulty enumdifficulty = this.mc.theWorld.getDifficulty();
            this.field_175357_i = new GuiButton(108, width / 2 - 155 + i % 2 * 160, height / 6 - 12 + 24 * (i >> 1), 150, 20, this.func_175355_a(enumdifficulty));
            this.buttonList.add(this.field_175357_i);
            if (this.mc.isSingleplayer() && !this.mc.theWorld.getWorldInfo().isHardcoreModeEnabled()) {
                this.field_175356_r = new GuiLockIconButton(109, this.field_175357_i.xPosition + this.field_175357_i.getButtonWidth(), this.field_175357_i.yPosition);
                this.buttonList.add(this.field_175356_r);
                this.field_175356_r.func_175229_b(this.mc.theWorld.getWorldInfo().isDifficultyLocked());
                this.field_175356_r.enabled = this.field_175356_r.func_175230_c();
                this.field_175357_i.enabled = this.field_175356_r.func_175230_c();
            } else {
                this.field_175357_i.enabled = false;
            }
        }

        this.buttonList.add(new GuiButton(110, width / 2 - 155, height / 6 + 48 - 6, 150, 20, I18n.format("options.skinCustomisation")));
        this.buttonList.add(new GuiButton(8675309, width / 2 + 5, height / 6 + 48 - 6, 150, 20, "Developers Webiste..."));
        this.buttonList.add(new GuiButton(106, width / 2 - 155, height / 6 + 72 - 6, 150, 20, I18n.format("options.sounds")));
        this.buttonList.add(new GuiButton(107, width / 2 + 5, height / 6 + 72 - 6, 150, 20, I18n.format("options.stream")));
        this.buttonList.add(new GuiButton(101, width / 2 - 155, height / 6 + 96 - 6, 150, 20, I18n.format("options.video")));
        this.buttonList.add(new GuiButton(100, width / 2 + 5, height / 6 + 96 - 6, 150, 20, I18n.format("options.controls")));
        this.buttonList.add(new GuiButton(102, width / 2 - 155, height / 6 + 120 - 6, 150, 20, I18n.format("options.language")));
        this.buttonList.add(new GuiButton(103, width / 2 + 5, height / 6 + 120 - 6, 150, 20, I18n.format("options.chat.title")));
        this.buttonList.add(new GuiButton(105, width / 2 - 155, height / 6 + 144 - 6, 150, 20, I18n.format("options.resourcepack")));
        this.buttonList.add(new GuiButton(104, width / 2 + 5, height / 6 + 144 - 6, 150, 20, I18n.format("options.snooper.view")));
        this.buttonList.add(new GuiButton(200, width / 2 - 100, height / 6 + 168, I18n.format("gui.done")));
        this.buttonList.add(new GuiButton(201, width / 2 - 75, height / 6 + 22 - 6, 150, 20, I18n.format("Ayakashi (アヤカシ)")));
    }

    public String func_175355_a(EnumDifficulty p_175355_1_) {
        IChatComponent ichatcomponent = new ChatComponentText("");
        ichatcomponent.appendSibling(new ChatComponentTranslation("options.difficulty"));
        ichatcomponent.appendText(": ");
        ichatcomponent.appendSibling(new ChatComponentTranslation(p_175355_1_.getDifficultyResourceKey()));
        return ichatcomponent.getFormattedText();
    }

    public void confirmClicked(boolean result, int id) {
        this.mc.displayGuiScreen(this);
        if (id == 109 && result && this.mc.theWorld != null) {
            this.mc.theWorld.getWorldInfo().setDifficultyLocked(true);
            this.field_175356_r.func_175229_b(true);
            this.field_175356_r.enabled = false;
            this.field_175357_i.enabled = false;
        }

    }

    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.enabled) {
            if (button.id < 100 && button instanceof GuiOptionButton) {
                GameSettings.Options gamesettings$options = ((GuiOptionButton) button).returnEnumOptions();
                this.game_settings_1.setOptionValue(gamesettings$options, 1);
                button.displayString = this.game_settings_1.getKeyBinding(GameSettings.Options.getEnumOptions(button.id));
            }

            if (button.id == 108) {
                this.mc.theWorld.getWorldInfo().setDifficulty(EnumDifficulty.getDifficultyEnum(this.mc.theWorld.getDifficulty().getDifficultyId() + 1));
                this.field_175357_i.displayString = this.func_175355_a(this.mc.theWorld.getDifficulty());
            }

            if (button.id == 109) {
                this.mc.displayGuiScreen(new GuiYesNo(this, (new ChatComponentTranslation("difficulty.lock.title")).getFormattedText(), (new ChatComponentTranslation("difficulty.lock.question", new ChatComponentTranslation(this.mc.theWorld.getWorldInfo().getDifficulty().getDifficultyResourceKey()))).getFormattedText(), 109));
            }

            if (button.id == 110) {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(new GuiCustomizeSkin(this));
            }

            if (button.id == 101) {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(new GuiVideoSettings(this, this.game_settings_1));
            }

            if (button.id == 100) {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(new GuiControls(this, this.game_settings_1));
            }

            if (button.id == 102) {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(new GuiLanguage(this, this.game_settings_1, this.mc.getLanguageManager()));
            }

            if (button.id == 103) {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(new ScreenChatOptions(this, this.game_settings_1));
            }

            if (button.id == 104) {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(new GuiSnooper(this, this.game_settings_1));
            }

            if (button.id == 200) {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(this.field_146441_g);
            }

            if (button.id == 105) {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(new GuiScreenResourcePacks(this));
            }

            if (button.id == 106) {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(new GuiScreenOptionsSounds(this, this.game_settings_1));
            }

            if (button.id == 107) {
                this.mc.gameSettings.saveOptions();
            }

            if (button.id == 201) {
                try {
                    URI uri = new URI("https://en.wikipedia.org/wiki/Ayakashi_(y%C5%8Dkai)");
                    Desktop.getDesktop().browse(uri);
                } catch (Exception var3) {
                    var3.printStackTrace();
                }
            }
            if (button.id == 8675309) {
                try {
                    URI uri = new URI("https://discord.gg/CW7sN5bqn5");
                    Desktop.getDesktop().browse(uri);
                } catch (Exception var3) {
                    var3.printStackTrace();
                }
            }
        }

    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        drawCenteredString(this.fontRendererObj, this.field_146442_a, width / 2, 15, 16777215);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
