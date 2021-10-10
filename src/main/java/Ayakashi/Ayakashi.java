package Ayakashi;

import Ayakashi.command.impl.*;
import Ayakashi.helpers.MemoryHelper;
import Ayakashi.helpers.OpenGlHelper;
import Ayakashi.managers.CommandManager;
import Ayakashi.managers.CrashManager;
import Ayakashi.methods.impl.*;
import Ayakashi.mods.blockparticles.FBP;
import Ayakashi.mods.crashitems.TabArmor;
import Ayakashi.mods.crashitems.TabExploits;
import Ayakashi.mods.crashitems.TabSword;
import Ayakashi.mods.viamcp.ViaMCP;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.Display;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public enum Ayakashi {
    INSTANCE;
    private final CommandManager commandManager;
    private final CrashManager crashManager;


    Ayakashi() {
        new DiscordRP();
        this.commandManager = new CommandManager(
                new AdvancedCrashCommand(),
                new CrashCommand(),
                new HelpCommand(),
                new WingsCommand(),
                new MethodsCommand(),
                new GamemodeCommand(),
                new ThreadsCommand(),
                new ExploitCommand(),
                new ClearChatCommand(),
                new HudCommand(),
                new ProtocolsCommand(),
                new RpcCommand());
        this.crashManager = new CrashManager(
                new CrashYokai1(),
                new CrashYokai2(),
                new CrashYokai3(),
                new CrashShade1(),
                new CrashShade2(),
                new CrashShade3(),
                new CrashMare1(),
                new CrashMare2(),
                new CrashMare3(),
                new CrashHantu1(),
                new CrashHantu2(),
                new CrashTengu1(),
                new CrashTengu2(),
                new CrashTengu3(),
                new CrashMyling1(),
                new CrashMyling2());
        ViaMCP.getInstance().start();
        new FBP();
        new MemoryHelper().run();
        FBP.INSTANCE.onStart();
        new TabArmor();
        new TabExploits();
        new TabSword();
        Minecraft.getMinecraft().gameSettings.gammaSetting += 9999.0F;
        Minecraft.getMinecraft().gameSettings.ofFastRender = true;
    }

    public void setDisplay() throws IOException {
        Display.setTitle("Ayakashi 1.8.9");
        OpenGlHelper.setWindowIcon("https://i.imgur.com/tsb5f3N.png", "https://i.imgur.com/DVNhGaK.png");

        File folder = new File("data");
        if (!folder.exists()) folder.mkdir();

        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < Objects.requireNonNull(listOfFiles).length; i++) {
            AdvancedCrashCommand.methods.add(listOfFiles[i].getName());
        }
        
    }

    public CommandManager getCommandManager() {
        return this.commandManager;
    }

    public CrashManager getExploitManager() {
        return this.crashManager;
    }
}
