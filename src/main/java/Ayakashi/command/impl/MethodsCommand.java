package Ayakashi.command.impl;

import Ayakashi.command.Command;
import Ayakashi.command.CommandInfo;
import Ayakashi.exeptions.CommandException;
import Ayakashi.helpers.ChatHelper;

@CommandInfo(
        alias = "methods"
)
public class MethodsCommand extends Command {
    public void execute(String... args) throws CommandException {
        ChatHelper.sendMessage("", false);
        ChatHelper.sendMessage("Available methods&8:", false);
        ChatHelper.sendMessage("In () there is recommended packet amount", false);
        ChatHelper.sendMessage("", false);
        ChatHelper.sendMessage("&lYOKAI1 &8-> &fExploitFixer crasher &8(&f1-10&8)", true);
        ChatHelper.sendMessage("&lYOKAI2 &8-> &fExploitFixer crasher &8(&f1-10&8)", true);
        ChatHelper.sendMessage("&lYOKAI3 &8-> &fExploitFixer crasher &8(&f1-10&8)", true);
        ChatHelper.sendMessage("", false);
        ChatHelper.sendMessage("&lSHADE1 &8-> &fNetty crasher &8(&f150-500s&8)", true);
        ChatHelper.sendMessage("&lSHADE2 &8-> &fNetty crasher &8(&f150-500&8)", true);
        ChatHelper.sendMessage("&lSHADE3 &8-> &fNetty crasher &8(&f150-500&8)", true);
        ChatHelper.sendMessage("", false);
        ChatHelper.sendMessage("&lMARE1 &8-> &fExtra unicode crasher &8(&f10-100&8)", true);
        ChatHelper.sendMessage("&lMARE2 &8-> &fExtra unicode crasher &8(&f10-100&8)" , true);
        ChatHelper.sendMessage("&lMARE3 &8-> &fExtra unicode crasher &8(&f10-100&8)", true);
        ChatHelper.sendMessage("", false);
        ChatHelper.sendMessage("&lHANTU1 &8-> &f1.9-1.17.1 PositionCrasher &8(&f2-5&8)", true);
        ChatHelper.sendMessage("&lHANTU2 &8-> &f1.9-1.17.1 PositionCrasher &8(&f2-5&8)" , true);
        ChatHelper.sendMessage("&lHANTU3 &8-> &f1.9-1.17.1 PositionCrasher &8(&f2-5&8)", true);
        ChatHelper.sendMessage("&lHANTU4 &8-> &f1.9-1.17.1 PositionCrasher &8(&f2-5&8)" , true);
        ChatHelper.sendMessage("&lHANTU5 &8-> &f1.9-1.17.1 PositionCrasher &8(&f2-5&8)" , true);
        ChatHelper.sendMessage("", false);
        ChatHelper.sendMessage("&lMYLING1 &8-> &fADVANCED BYPASS &8(&f1-50&8)", true);
        ChatHelper.sendMessage("&lMYLING2 &8-> &fADVANCED BYPASS &8(&f1-50&8)", true);
        ChatHelper.sendMessage("&lMYLING3 &8-> &fADVANCED BYPASS &8(&f1-50&8)", true);
        ChatHelper.sendMessage("", false);
        ChatHelper.sendMessage("&lTENGU1 &8-> &fULTIMATE BYPASS &8(&f1-10&8)", true);
        ChatHelper.sendMessage("&lTENGU2 &8-> &fULTIMATE BYPASS &8(&f1-10&8)", true);
        ChatHelper.sendMessage("&lTENGU3 &8-> &fULTIMATE BYPASS &8(&f1-10&8)", true);
        ChatHelper.sendMessage("", false);
    }
}
