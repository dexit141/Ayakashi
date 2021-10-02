package Ayakashi.command.impl;

import Ayakashi.command.Command;
import Ayakashi.command.CommandInfo;
import Ayakashi.helpers.ChatHelper;
import net.minecraft.command.CommandException;

@CommandInfo(
        alias = "rpc",
        usage = ",rpc <on/off>"
)
public class RpcCommand extends Command {

    public static boolean rpc = true;

    public void execute(String... args) throws CommandException {
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("on")) {
                rpc = true;
                ChatHelper.sendMessage("Discord RPC was enabled", true);
            } else if (args[0].equalsIgnoreCase("off")) {
                rpc = false;
                ChatHelper.sendMessage("Discord RPC was disabled", true);
            } else {
                ChatHelper.sendMessage(",rpc <on/off>", true);
            }
            return;
        }
        ChatHelper.sendMessage(",rpc <on/off>", true);
        return;
    }
}
