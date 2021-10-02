package Ayakashi.command.impl;

import Ayakashi.command.Command;
import Ayakashi.command.CommandInfo;
import Ayakashi.exeptions.CommandException;
import Ayakashi.helpers.ChatHelper;
import Ayakashi.mods.ItemPhysics.ItemPhysics;
import net.minecraft.command.CommandBase;
import net.minecraft.command.NumberInvalidException;
import net.minecraft.world.WorldSettings;

@CommandInfo(
        alias = "fakegm",
        usage = ",fakegm <mode>",
        aliases = {"fakegamemode", "fgm", "gamemode", "gm"}
)
public class GamemodeCommand extends Command {
    private WorldSettings.GameType savedType;

    public void execute(String... args) throws CommandException {
        if (args.length <= 0) {
            throw new CommandException("Correct usage&8: &f" + this.getUsage());
        } else {
            try {
                WorldSettings.GameType gameType = this.getGameModeFromCommand(args[0]);
                if (this.savedType == null) {
                    this.savedType = ItemPhysics.mc.playerController.getCurrentGameType();
                }

                ItemPhysics.mc.playerController.setGameType(gameType);
                ChatHelper.sendMessage("Your gamemode was set to &f" + gameType.getName() + "&7!");
            } catch (Exception var3) {
                throw new CommandException("Correct usage&8: &f" + this.getUsage());
            }
        }
    }

    private WorldSettings.GameType getGameModeFromCommand(String argument) throws NumberInvalidException {
        return !argument.equalsIgnoreCase(WorldSettings.GameType.SURVIVAL.getName()) && !argument.equalsIgnoreCase("s") ? (!argument.equalsIgnoreCase(WorldSettings.GameType.CREATIVE.getName()) && !argument.equalsIgnoreCase("c") ? (!argument.equalsIgnoreCase(WorldSettings.GameType.ADVENTURE.getName()) && !argument.equalsIgnoreCase("a") ? (!argument.equalsIgnoreCase(WorldSettings.GameType.SPECTATOR.getName()) && !argument.equalsIgnoreCase("sp") ? WorldSettings.getGameTypeById(CommandBase.parseInt(argument, 0, WorldSettings.GameType.values().length - 2)) : WorldSettings.GameType.SPECTATOR) : WorldSettings.GameType.ADVENTURE) : WorldSettings.GameType.CREATIVE) : WorldSettings.GameType.SURVIVAL;
    }
}
