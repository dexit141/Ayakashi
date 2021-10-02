package Ayakashi.mods.cosmetics.wings;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class WingsModel extends ModelBase {
    private final Animation timer = new Animation();
    private float airTicks;

    public static void renderWingsIn3D(WorldRenderer worldrenderer) {
        Tessellator tessellator = Tessellator.getInstance();
        GL11.glPushMatrix();
        GL11.glTranslated(0.0D, 0.0D, 0.0D);
        GL11.glEnable(32826);
        GL11.glTranslatef(1.0F, -0.10000001F, 0.1F);
        GL11.glScalef(1.875F, 1.875F, 1.0F);
        GL11.glRotatef(0.0F, 0.0F, 200.0F, 0.0F);
        GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
        GL11.glTranslatef(-0.9375F, -0.0625F, 0.0F);
        GL11.glScaled(1.0D, 1.0D, 0.7D);
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_NORMAL);
        worldrenderer.pos(0.0D, 0.0D, 0.0D).tex(1.0D, 1.0D).normal(0.0F, 0.0F, 1.0F).endVertex();
        worldrenderer.pos(1.0D, 0.0D, 0.0D).tex(0.0D, 1.0D).normal(0.0F, 0.0F, 1.0F).endVertex();
        worldrenderer.pos(1.0D, 1.0D, 0.0D).tex(0.0D, 0.0D).normal(0.0F, 0.0F, 1.0F).endVertex();
        worldrenderer.pos(0.0D, 1.0D, 0.0D).tex(1.0D, 0.0D).normal(0.0F, 0.0F, 1.0F).endVertex();
        tessellator.draw();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_NORMAL);
        worldrenderer.pos(0.0D, 1.0D, -0.078125D).tex(1.0D, 0.0D).normal(0.0F, 0.0F, -1.0F).endVertex();
        worldrenderer.pos(1.0D, 1.0D, -0.078125D).tex(0.0D, 0.0D).normal(0.0F, 0.0F, -1.0F).endVertex();
        worldrenderer.pos(1.0D, 0.0D, -0.078125D).tex(0.0D, 1.0D).normal(0.0F, 0.0F, -1.0F).endVertex();
        worldrenderer.pos(0.0D, 0.0D, -0.078125D).tex(1.0D, 1.0D).normal(0.0F, 0.0F, -1.0F).endVertex();
        tessellator.draw();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_NORMAL);

        int k;
        float f1;
        float f2;
        for (k = 0; (float) k < 32.0F; ++k) {
            f1 = (float) k / 32.0F;
            f2 = 1.0F + -1.0F * f1 - 0.015625F;
            worldrenderer.pos(f1, 0.0D, -0.078125D).tex(f2, 1.0D).normal(-1.0F, 0.0F, 0.0F).endVertex();
            worldrenderer.pos(f1, 0.0D, 0.0D).tex(f2, 1.0D).normal(-1.0F, 0.0F, 0.0F).endVertex();
            worldrenderer.pos(f1, 1.0D, 0.0D).tex(f2, 0.0D).normal(-1.0F, 0.0F, 0.0F).endVertex();
            worldrenderer.pos(f1, 1.0D, -0.078125D).tex(f2, 0.0D).normal(-1.0F, 0.0F, 0.0F).endVertex();
        }

        tessellator.draw();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_NORMAL);

        for (k = 0; (float) k < 32.0F; ++k) {
            f1 = (float) k / 32.0F;
            f2 = 1.0F + -1.0F * f1 - 0.015625F;
            f1 += 0.03125F;
            worldrenderer.pos(f1, 1.0D, -0.078125D).tex(f2, 0.0D).normal(1.0F, 0.0F, 0.0F).endVertex();
            worldrenderer.pos(f1, 1.0D, 0.0D).tex(f2, 0.0D).normal(1.0F, 0.0F, 0.0F).endVertex();
            worldrenderer.pos(f1, 0.0D, 0.0D).tex(f2, 1.0D).normal(1.0F, 0.0F, 0.0F).endVertex();
            worldrenderer.pos(f1, 0.0D, -0.078125D).tex(f2, 1.0D).normal(1.0F, 0.0F, 0.0F).endVertex();
        }

        tessellator.draw();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_NORMAL);

        for (k = 0; (float) k < 32.0F; ++k) {
            f1 = (float) k / 32.0F;
            f2 = 1.0F + -1.0F * f1 - 0.015625F;
            f1 += 0.03125F;
            worldrenderer.pos(0.0D, f1, 0.0D).tex(1.0D, f2).normal(0.0F, 1.0F, 0.0F).endVertex();
            worldrenderer.pos(1.0D, f1, 0.0D).tex(0.0D, f2).normal(0.0F, 1.0F, 0.0F).endVertex();
            worldrenderer.pos(1.0D, f1, -0.078125D).tex(0.0D, f2).normal(0.0F, 1.0F, 0.0F).endVertex();
            worldrenderer.pos(0.0D, f1, -0.078125D).tex(1.0D, f2).normal(0.0F, 1.0F, 0.0F).endVertex();
        }

        tessellator.draw();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_NORMAL);

        for (k = 0; (float) k < 32.0F; ++k) {
            f1 = (float) k / 32.0F;
            f2 = 1.0F + -1.0F * f1 - 0.015625F;
            worldrenderer.pos(1.0D, f1, 0.0D).tex(0.0D, f2).normal(0.0F, -1.0F, 0.0F).endVertex();
            worldrenderer.pos(0.0D, f1, 0.0D).tex(1.0D, f2).normal(0.0F, -1.0F, 0.0F).endVertex();
            worldrenderer.pos(0.0D, f1, -0.078125D).tex(1.0D, f2).normal(0.0F, -1.0F, 0.0F).endVertex();
            worldrenderer.pos(1.0D, f1, -0.078125D).tex(0.0D, f2).normal(0.0F, -1.0F, 0.0F).endVertex();
        }

        tessellator.draw();
        GL11.glDisable(32826);
        GL11.glPopMatrix();
    }

    public void render(EntityPlayerSP player, boolean isSneaking, boolean isFlying, boolean onGround, boolean isSprinting) {
        this.postRender(player, isSneaking, isFlying, onGround, isSprinting);
    }

    private void postRender(EntityPlayerSP player, boolean isSneaking, boolean isFlying, boolean onGround, boolean isSprinting) {
        this.RenderWings(player, isSneaking, isFlying, onGround, isSprinting);
    }

    public void RenderWings(EntityPlayerSP player, boolean isSneaking, boolean isFlying, boolean onGround, boolean isSprinting) {
        Tessellator tessellator = Tessellator.getInstance();
        GL11.glPushMatrix();
        GL11.glScalef(1.1F, 1.1F, 1.1F);
        GL11.glTranslatef(0.0F, 0.125F, 0.125F);
        if (isSneaking) {
            GL11.glTranslatef(0.0F, 0.1F, 0.175F);
        }

        GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
        GL11.glPushMatrix();
        if (this.timer.hasTimeElapsed()) {
            if (isSneaking) {
                this.airTicks -= 0.1F;
            }

            if ((!isFlying || onGround) && !isSprinting) {
                this.airTicks += 0.2F;
            } else {
                this.airTicks += 0.4F;
            }
        }

        if (isSneaking) {
            GL11.glRotatef(30.0F + (float) Math.sin((double) this.airTicks / 4.0D) * 20.0F, 1.5F, 0.0F, 2.5F);
        } else {
            GL11.glRotatef(24.0F + (float) Math.sin((double) this.airTicks / 4.0D) * 20.0F, 0.0F, 0.0F, 1.0F);
        }

        GL11.glTranslatef(-0.125F, 0.125F, 0.18F);
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("Ayakashi/wings/wings.png"));
        GL11.glRotatef(100.0F, 0.0F, 1.0F, 0.0F);
        renderWingsIn3D(tessellator.getWorldRenderer());
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        if (isSneaking) {
            GL11.glRotatef(-30.0F - (float) Math.sin((double) this.airTicks / 4.0D) * 20.0F, -1.5F, 0.0F, 2.5F);
        } else {
            GL11.glRotatef(-24.0F - (float) Math.sin((double) this.airTicks / 4.0D) * 20.0F, 0.0F, 0.0F, 1.0F);
        }

        GL11.glTranslatef(0.0F, 0.125F, 0.18F);
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("Ayakashi/wings/wings.png"));
        GL11.glRotatef(80.0F, 0.0F, 1.0F, 0.0F);
        renderWingsIn3D(tessellator.getWorldRenderer());
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }
}
