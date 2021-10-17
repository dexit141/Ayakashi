package Ayakashi.command.impl;

import Ayakashi.command.Command;
import Ayakashi.command.CommandInfo;
import Ayakashi.exeptions.CommandException;
import Ayakashi.helpers.ChatHelper;

@CommandInfo(
        alias = "hud",
        usage = ",hud",
        aliases = {"ui", "gui"}
)
public class HudCommand extends Command {
    public static Boolean hud = true;

    public static boolean showHud() {
        return hud;
    }

    public void setHud(boolean enabled) {
        hud = enabled;
    }

    public void execute(String... args) throws CommandException {
        ChatHelper.sendMessage(String.format("Hud has been &f%s&7!", !showHud() ? "enabled" : "disabled"));
        this.setHud(!showHud());
    }
}
