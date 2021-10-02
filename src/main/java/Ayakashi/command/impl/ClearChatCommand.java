package Ayakashi.command.impl;

import Ayakashi.command.Command;
import Ayakashi.command.CommandInfo;
import Ayakashi.exeptions.CommandException;
import net.minecraft.client.Minecraft;

@CommandInfo(
        alias = "clearchat",
        aliases = {"cc", "clearc"}
)
public class ClearChatCommand extends Command {
    public void execute(String... args) throws CommandException {
        Minecraft.getMinecraft().ingameGUI.getChatGUI().clearChatMessages();
    }
}
