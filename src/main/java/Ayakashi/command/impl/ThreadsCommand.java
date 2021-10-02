package Ayakashi.command.impl;

import Ayakashi.command.Command;
import Ayakashi.command.CommandInfo;
import Ayakashi.exeptions.CommandException;
import Ayakashi.helpers.ChatHelper;

import java.util.Set;

@CommandInfo(
        alias = "Threads",
        usage = ",threads <list/count>"
)
public class ThreadsCommand extends Command {
    public void execute(String... args) throws CommandException {
        Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
        if (args.length <= 0) {
            throw new CommandException("Correct usage&8: &f" + this.getUsage());
        } else {
            System.gc();
            System.runFinalization();
            if (args[0].equalsIgnoreCase("count")) {
                ChatHelper.sendMessage("", false);
                ChatHelper.sendMessage("Threads count: &f" + Thread.activeCount(), true);
                ChatHelper.sendMessage("Current: &f" + Thread.currentThread(), true);
                ChatHelper.sendMessage("", false);
            } else if (args[0].equalsIgnoreCase("list")) {
                ChatHelper.sendMessage("", false);
                ChatHelper.sendMessage("All threads in usage: ", true);
                threadSet.forEach((thread) -> ChatHelper.sendMessage("&4&lThread &8-> &f" + thread, false));
                ChatHelper.sendMessage("", false);
            }

        }
    }
}
