package Ayakashi.mods.viamcp.gui;

import Ayakashi.mods.viamcp.ViaMCP;
import Ayakashi.mods.viamcp.protocols.ProtocolCollection;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.opengl.GL11;

import java.io.IOException;
import java.util.Objects;

public class GuiProtocolSelector extends GuiScreen {
    private final GuiScreen parent;
    public SlotList list;

    public GuiProtocolSelector(GuiScreen parent) {
        this.parent = parent;
    }

    @Override
    public void initGui() {
        super.initGui();
        buttonList.add(new GuiButton(1, width / 2 - 100, height - 27, 200, 20, "Done"));
        list = new SlotList(mc, width, height, 32, height - 32, 10);
    }

    @Override
    protected void actionPerformed(GuiButton p_actionPerformed_1_) throws IOException {
        list.actionPerformed(p_actionPerformed_1_);

        if (p_actionPerformed_1_.id == 1) {
            mc.displayGuiScreen(parent);
        }
    }

    @Override
    public void handleMouseInput() throws IOException {
        list.handleMouseInput();
        super.handleMouseInput();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        list.drawScreen(mouseX, mouseY);
        GL11.glPushMatrix();
        GL11.glScalef(2.0F, 2.0F, 2.0F);
        drawCenteredString(this.fontRendererObj, EnumChatFormatting.BOLD + "Select your version!", width / 4, 6, 16777215);
        GL11.glPopMatrix();
        drawString(this.fontRendererObj, "Maintained by Hideri (1.8.x Version)", 3, 3, -1);
        drawString(this.fontRendererObj, "Discord: Hideri#9003", 3, 13, -1);
        drawString(this.fontRendererObj, "Credits", 3, height - 30, -1);
        drawString(this.fontRendererObj, "ViaForge: https://github.com/FlorianMichael/ViaForge", 3, height - 20, -1);
        drawString(this.fontRendererObj, "Original ViaMCP: https://github.com/LaVache-FR/ViaMCP", 3, height - 10, -1);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    class SlotList extends GuiSlot {
        public SlotList(Minecraft p_i1052_1_, int p_i1052_2_, int p_i1052_3_, int p_i1052_4_, int p_i1052_5_, int p_i1052_6_) {
            super(p_i1052_1_, p_i1052_2_, p_i1052_3_, p_i1052_4_, p_i1052_5_, p_i1052_6_);
        }

        @Override
        protected int getSize() {
            return ProtocolCollection.values().length;
        }

        @Override
        protected void elementClicked(int i, boolean b, int i1, int i2) {
            ViaMCP.getInstance().setVersion(ProtocolCollection.values()[i].getVersion().getVersion());
        }

        @Override
        protected boolean isSelected(int i) {
            return false;
        }

        @Override
        protected void drawBackground() {
            drawDefaultBackground();
        }

        @Override
        protected void drawSlot(int i, int i1, int i2, int i3, int i4, int i5) {
            drawCenteredString(mc.fontRendererObj, (ViaMCP.getInstance().getVersion() == ProtocolCollection.values()[i].getVersion().getVersion() ? EnumChatFormatting.GREEN.toString() + EnumChatFormatting.BOLD : EnumChatFormatting.GRAY.toString()) + Objects.requireNonNull(ProtocolCollection.getProtocolById(ProtocolCollection.values()[i].getVersion().getVersion())).getName(), width / 2, i2 + 2, -1);
        }
    }
}
