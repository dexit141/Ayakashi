package Ayakashi;

import Ayakashi.command.impl.RpcCommand;
import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;
import net.arikia.dev.drpc.DiscordRichPresence.Builder;
import net.arikia.dev.drpc.DiscordUser;
import net.arikia.dev.drpc.callbacks.ReadyCallback;
import net.minecraft.client.Minecraft;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DiscordRP implements ReadyCallback {
    private static final Minecraft mc = Minecraft.getMinecraft();
    DiscordRichPresence richPresence = (new Builder("Loading Ayakashi..."))
            .setBigImage("logo", "Ayakashi 1.8.9")
            .setDetails("Loading Ayakashi...")
            .setStartTimestamps(System.currentTimeMillis()).build();
    private boolean enabled = true;

    public DiscordRP() {
        this.init();
        this.startTask();
        DiscordRPC.discordUpdatePresence(this.richPresence);
    }

    public void apply(DiscordUser discordUser) {
        System.out.println("Initialized DiscordRichPresence API.");
    }

    private void init() {
        DiscordEventHandlers handlers = (new net.arikia.dev.drpc.DiscordEventHandlers.Builder()).setReadyEventHandler((user) -> System.out.printf("Connected to %s#%s (%s)%n", user.username, user.discriminator, user.userId)).build();
        DiscordRPC.discordInitialize("859471756314148895", handlers, true);

    }

    public void startTask() {
        Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(() -> {
            if (RpcCommand.rpc) {

                if (!enabled) {
                    DiscordEventHandlers handlers = (new net.arikia.dev.drpc.DiscordEventHandlers.Builder()).setReadyEventHandler((user) -> System.out.printf("Connected to %s#%s (%s)%n", user.username, user.discriminator, user.userId)).build();
                    DiscordRPC.discordInitialize("859471756314148895", handlers, true);
                    enabled = true;
                }

                this.richPresence.details = mc.thePlayer == null ? "Main Menu" : "Nick: " + mc.session.getUsername();
                this.richPresence.state = mc.getCurrentServerData() == null ? "Offline" : "Server: " + mc.getCurrentServerData().serverIP;
                DiscordRPC.discordUpdatePresence(this.richPresence);
            } else {
                DiscordRPC.discordShutdown();
                enabled = false;
            }
        }, 10L, 10L, TimeUnit.SECONDS);
    }
}
