package Ayakashi.mods.hook;

import Ayakashi.command.impl.HudCommand;
import Ayakashi.helpers.ChatHelper;
import Ayakashi.helpers.HolderHelper;
import Ayakashi.helpers.PacketHelper;
import Ayakashi.helpers.TimeHelper;
import Ayakashi.mods.chunkanimator.ChunkAnimator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.text.DecimalFormat;

public class GuiInGameHook extends GuiIngame {
    private static final Minecraft mc = Minecraft.getMinecraft();
    public static ChunkAnimator chunkAnimator;
    DecimalFormat df = new DecimalFormat("#.##");

    public GuiInGameHook(Minecraft mcIn) {
        super(mcIn);
        chunkAnimator = new ChunkAnimator(mc);
    }

    public void renderGameOverlay(float partialTicks) {
        super.renderGameOverlay(partialTicks);
        if (!mc.isSingleplayer() && !GameSettings.showDebugInfo && HudCommand.isHud()) {
            mc.getTextureManager().bindTexture(new ResourceLocation("Ayakashi/logo.png"));
            drawModalRectWithCustomSizedTexture(-0, -5, 0.0F, 0.0F, 150, 75, 150.0F, 75.0F);
            this.drawInfo();
            String brand = null;
            if (mc.thePlayer.getClientBrand() != null) {
                brand = mc.thePlayer.getClientBrand().contains("<- ") ? mc.thePlayer.getClientBrand().split(" ")[0] + " -> " + mc.thePlayer.getClientBrand().split("<- ")[1] : mc.thePlayer.getClientBrand().split(" ")[0];
            }
            long lastPacketMS = (long) (TimeHelper.getCurrentTime() - (double) HolderHelper.getLastPacketMS());
            if (lastPacketMS > 1000L && HolderHelper.getTPS() > 0.0D) {
                HolderHelper.setTPS(HolderHelper.getTPS() - 0.01D);
            }
            mc.fontRendererObj.drawStringWithShadow(ChatHelper.fix("&4&lIP &8-> &f" + mc.getCurrentServerData().serverIP), 6.0F, 80.0F, -1);
            mc.fontRendererObj.drawStringWithShadow(ChatHelper.fix("&4&lEngine &8-> &f" + brand), 6.0F, 90.0F, -1);
            mc.fontRendererObj.drawStringWithShadow(ChatHelper.fix("&4&lOnline &8-> &f" + mc.getNetHandler().getPlayerInfoMap().size() + "/" + mc.getNetHandler().currentServerMaxPlayers), 6.0F, 100.0F, -1);
            if (HudCommand.fps) {
                mc.fontRendererObj.drawStringWithShadow(ChatHelper.fix("&4&lFPS &8-> &f" + Minecraft.debugFPS), 6.0F, 110.0F, -1);
            }
            if (HudCommand.tps) {
                mc.fontRendererObj.drawStringWithShadow(ChatHelper.fix("&4&lTPS &8-> &f" + this.df.format(PacketHelper.tps) + " TPS"), 6.0F, 120.0F, -1);
            }
            if (PacketHelper.getServerLagTime() > 30000) {
                mc.fontRendererObj.drawStringWithShadow(ChatHelper.fix("&4&lLast Packet &8-> &freceiving..."), 6.0F, 130.0F, -1);
            } else {
                mc.fontRendererObj.drawStringWithShadow(ChatHelper.fix("&4&lLast Packet &8-> &f" + PacketHelper.getServerLagTime() + "ms"), 6.0F, 130.0F, -1);
            }
        }

    }

    private void drawInfo() {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glEnable(3553);
        GL11.glEnable(2848);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("Ayakashi/menu/infobackground.png"));
        GL11.glBegin(7);
        GL11.glTexCoord2d(0.0D, 0.0D);
        GL11.glVertex2d(0.0D, 65.0D);
        GL11.glTexCoord2d(0.0D, 1.0D);
        GL11.glVertex2d(0.0D, 155.0D);
        GL11.glTexCoord2d(1.0D, 1.0D);
        GL11.glVertex2d(200.0D, 155.0D);
        GL11.glTexCoord2d(1.0D, 0.0D);
        GL11.glVertex2d(200.0D, 65.0D);
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glDisable(2848);
        GL11.glPopMatrix();
    }
}
