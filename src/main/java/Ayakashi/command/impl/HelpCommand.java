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
        ChatHelper.sendMessage(",help &8-> &fCheck available commands", true);
        ChatHelper.sendMessage(",wings &8-> &fChange wings visibility", true);
        ChatHelper.sendMessage(",crash &8-> &fSend package to server", true);
        ChatHelper.sendMessage(",exploit &8-> &fSend plugins exploits", true);
        ChatHelper.sendMessage(",fakegm &8-> &fChange gamemode for player", true);
        ChatHelper.sendMessage(",methods &8-> &fCheck available crash methods", true);
        ChatHelper.sendMessage(",threads &8-> &fSee informations about threads", true);
        ChatHelper.sendMessage(",hud &8-> &fChange gui in game visibility", true);
        ChatHelper.sendMessage(",clearchat &8-> &fClears your chat", true);
        ChatHelper.sendMessage(",rpc &8-> &fEnable/Disable Discord rpc", true);
        ChatHelper.sendMessage(",acrash &8-> &fAdvenced Crash", true);
        ChatHelper.sendMessage("", false);
        ChatHelper.sendMessage("# <message> &8-> &fSend IRC message", true);
        ChatHelper.sendMessage(",muteirc &8-> &fSwitch IRC message reciving", true);
        ChatHelper.sendMessage(",list &8-> &fSee the online users of Ayakashi", true);
        ChatHelper.sendMessage(",outgame &8-> &fSend exploits form vps to server", true);
        ChatHelper.sendMessage(",protocols &8-> &fShows all minecraft protocols", true);
        ChatHelper.sendMessage("", false);
    }
}
