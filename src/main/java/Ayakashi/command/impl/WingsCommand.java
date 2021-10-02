package Ayakashi.command.impl;

import Ayakashi.command.Command;
import Ayakashi.command.CommandInfo;
import Ayakashi.exeptions.CommandException;
import Ayakashi.helpers.ChatHelper;

@CommandInfo(
        alias = "wings"
)
public class WingsCommand extends Command {
    public static Boolean wings = true;

    public static boolean isWings() {
        return wings;
    }

    public void setWings(boolean enabled) {
        wings = enabled;
    }

    public void execute(String... args) throws CommandException {
        ChatHelper.sendMessage(String.format("Wings has been &f%s&7!", !isWings() ? "enabled" : "disabled"));
        this.setWings(!isWings());
    }
}
