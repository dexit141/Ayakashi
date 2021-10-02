package Ayakashi.command;

import net.minecraft.command.CommandException;
import org.apache.commons.lang3.Validate;

import java.util.Arrays;
import java.util.List;

public abstract class Command {
    private final String alias;
    private final String usage;
    private final List<String> aliases;

    public Command() {
        CommandInfo commandInfo = this.getClass().getDeclaredAnnotation(CommandInfo.class);
        Validate.notNull(commandInfo, "CONFUSED ANNOTATION EXCEPTION");
        this.alias = commandInfo.alias();
        this.usage = commandInfo.usage();
        this.aliases = Arrays.asList(commandInfo.aliases());
    }

    public abstract void execute(String... var1) throws CommandException;

    public boolean is(String string) {
        return string.equalsIgnoreCase(this.alias) || this.aliases.stream().anyMatch((stream) -> stream.equalsIgnoreCase(string));
    }

    public String getAlias() {
        return this.alias;
    }

    public String getUsage() {
        return this.usage;
    }
}
