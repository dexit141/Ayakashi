package Ayakashi.command.impl;

import Ayakashi.command.Command;
import Ayakashi.command.CommandInfo;
import Ayakashi.exeptions.CommandException;
import Ayakashi.helpers.ChatHelper;

@CommandInfo(
        alias = "muteirc",
        aliases = {"mute", "ircmute", "ircoff"}
)
public class MuteIRCCommand extends Command {
    public static boolean isMuted = true;

    public void execute(String... args) throws CommandException {
        ChatHelper.sendMessage(String.format("IRC has been &f%s&7!", !isMuted ? "enabled" : "disabled"));
        isMuted = !isMuted;
    }
}
