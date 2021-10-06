package Ayakashi.command.impl;

import Ayakashi.command.Command;
import Ayakashi.command.CommandInfo;
import Ayakashi.exeptions.CommandException;
import Ayakashi.helpers.ChatHelper;

@CommandInfo(
        alias = "help"
)
public class HelpCommand extends Command {
    public void execute(String... args) throws CommandException {
        ChatHelper.sendMessage("", false);
        ChatHelper.sendMessage(",help &8-> &fAvailable commands", true);
        ChatHelper.sendMessage(",wings &8-> &fSwitch for wings visibility", true);
        ChatHelper.sendMessage(",crash &8-> &fSend packets to server", true);
        ChatHelper.sendMessage(",exploit &8-> &fSend plugins exploits", true);
        ChatHelper.sendMessage(",fakegm &8-> &fChange gamemode for player", true);
        ChatHelper.sendMessage(",methods &8-> &fAvailable crash methods", true);
        ChatHelper.sendMessage(",threads &8-> &fInformations about threads", true);
        ChatHelper.sendMessage(",hud &8-> &fSwitch for in game visibility", true);
        ChatHelper.sendMessage(",clearchat &8-> &fClears your chat", true);
        ChatHelper.sendMessage(",protocols &8-> &fShows all minecraft protocols", true);
        ChatHelper.sendMessage(",rpc &8-> &fSwitch for discord rpc", true);
        ChatHelper.sendMessage(",acrash &8-> &fAdvenced crash system", true);
        ChatHelper.sendMessage("", false);
    }
}
