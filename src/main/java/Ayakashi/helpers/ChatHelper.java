package Ayakashi.helpers;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public final class ChatHelper {
    private static final Minecraft mc = Minecraft.getMinecraft();

    public static String fix(String string) {
        return string.replace('&', '§').replace(">>", "»");
    }

    public static void sendMessage(String message) {
        sendMessage(message, true);
    }

    public static void sendMessage(String message, boolean prefix) {
        if (prefix) {
            mc.thePlayer.addChatMessage(new ChatComponentText(fix("&4&lAyakashi &8>> &7" + message)));
        } else {
            mc.thePlayer.addChatMessage(new ChatComponentText(fix("" + message)));
        }

    }
}
