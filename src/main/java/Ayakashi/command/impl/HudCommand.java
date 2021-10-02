package Ayakashi.command.impl;

import Ayakashi.command.Command;
import Ayakashi.command.CommandInfo;
import Ayakashi.exeptions.CommandException;
import Ayakashi.helpers.ChatHelper;

@CommandInfo(
        alias = "hud",
        usage = ",hud <none/tps/fps>",
        aliases = {"ui", "gui"}
)
public class HudCommand extends Command {
    public static Boolean hud = true;

    public static Boolean tps = true;
    public static Boolean fps = true;

    public static boolean isHud() {
        return hud;
    }

    public void setHud(boolean enabled) {
        hud = enabled;
    }

    public void execute(String... args) throws CommandException {
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("tps")) {
                ChatHelper.sendMessage(String.format("Tps has been &f%s&7!", !isHud() ? "enabled" : "disabled"));
                tps = !tps;
            } else if (args[0].equalsIgnoreCase("fps")) {
                ChatHelper.sendMessage(String.format("Fps has been &f%s&7!", !isHud() ? "enabled" : "disabled"));
                fps = !fps;
            } else {
                ChatHelper.sendMessage(String.format("Hud has been &f%s&7!", !isHud() ? "enabled" : "disabled"));
                this.setHud(!isHud());
            }
        } else {
            ChatHelper.sendMessage(String.format("Hud has been &f%s&7!", !isHud() ? "enabled" : "disabled"));
            this.setHud(!isHud());
        }
    }
}
