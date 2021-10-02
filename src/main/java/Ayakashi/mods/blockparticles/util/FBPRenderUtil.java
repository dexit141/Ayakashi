package Ayakashi.mods.blockparticles.util;

import Ayakashi.mods.blockparticles.FBP;
import Ayakashi.mods.blockparticles.vector.FBPVector3d;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import org.lwjgl.util.vector.Vector2f;

public class FBPRenderUtil {
    public static void renderCubeShaded_S(final WorldRenderer buf, final Vector2f[] uvs, final float f5, final float f6, final float f7, final double scale, final FBPVector3d rotVec, final int j, final int k, final float r, final float g, final float b, final float a) {
        Tessellator.getInstance().draw();
        Minecraft.getMinecraft().getRenderManager().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
        buf.begin(7, FBP.POSITION_TEX_COLOR_LMAP_NORMAL);
        GlStateManager.enableCull();
        RenderHelper.enableStandardItemLighting();
        buf.setTranslation(f5, f6, f7);
        putCube_S(buf, uvs, scale, rotVec, j, k, r, g, b, a, FBP.cartoonMode);
        buf.setTranslation(0.0, 0.0, 0.0);
        Tessellator.getInstance().draw();
        Minecraft.getMinecraft().getRenderManager().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
        buf.begin(7, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);
        RenderHelper.disableStandardItemLighting();
    }

    public static void renderCubeShaded_WH(final WorldRenderer buf, final Vector2f[] uvs, final float f5, final float f6, final float f7, final double width, final double height, final FBPVector3d rotVec, final int j, final int k, final float r, final float g, final float b, final float a) {
        Tessellator.getInstance().draw();
        Minecraft.getMinecraft().getRenderManager().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
        buf.begin(7, FBP.POSITION_TEX_COLOR_LMAP_NORMAL);
        GlStateManager.enableCull();
        RenderHelper.enableStandardItemLighting();
        buf.setTranslation(f5, f6, f7);
        putCube_WH(buf, uvs, width, height, rotVec, j, k, r, g, b, a, FBP.cartoonMode);
        buf.setTranslation(0.0, 0.0, 0.0);
        Tessellator.getInstance().draw();
        Minecraft.getMinecraft().getRenderManager().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
        buf.begin(7, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);
        RenderHelper.disableStandardItemLighting();
    }

    static void putCube_S(final WorldRenderer worldRendererIn, final Vector2f[] par, final double scale, final FBPVector3d rotVec, final int j, final int k, final float r, final float g, final float b, final float a, final boolean cartoon) {
        final float radsX = (float) Math.toRadians(rotVec.x);
        final float radsY = (float) Math.toRadians(rotVec.y);
        final float radsZ = (float) Math.toRadians(rotVec.z);
        for (int i = 0; i < FBP.CUBE.length; i += 4) {
            Vec3 v1 = FBP.CUBE[i];
            Vec3 v2 = FBP.CUBE[i + 1];
            Vec3 v3 = FBP.CUBE[i + 2];
            Vec3 v4 = FBP.CUBE[i + 3];
            v1 = rotatef_d(v1, radsX, radsY, radsZ);
            v2 = rotatef_d(v2, radsX, radsY, radsZ);
            v3 = rotatef_d(v3, radsX, radsY, radsZ);
            v4 = rotatef_d(v4, radsX, radsY, radsZ);
            final Vec3 normal = rotatef_d(FBP.CUBE_NORMALS[i / 4], radsX, radsY, radsZ);
            if (!cartoon) {
                addVt_S(worldRendererIn, scale, v1, par[0].x, par[0].y, j, k, r, g, b, a, normal);
                addVt_S(worldRendererIn, scale, v2, par[1].x, par[1].y, j, k, r, g, b, a, normal);
                addVt_S(worldRendererIn, scale, v3, par[2].x, par[2].y, j, k, r, g, b, a, normal);
                addVt_S(worldRendererIn, scale, v4, par[3].x, par[3].y, j, k, r, g, b, a, normal);
            } else {
                addVt_S(worldRendererIn, scale, v1, par[0].x, par[0].y, j, k, r, g, b, a, normal);
                addVt_S(worldRendererIn, scale, v2, par[0].x, par[0].y, j, k, r, g, b, a, normal);
                addVt_S(worldRendererIn, scale, v3, par[0].x, par[0].y, j, k, r, g, b, a, normal);
                addVt_S(worldRendererIn, scale, v4, par[0].x, par[0].y, j, k, r, g, b, a, normal);
            }
        }
    }

    static void putCube_WH(final WorldRenderer worldRendererIn, final Vector2f[] par, final double width, final double height, final FBPVector3d rotVec, final int j, final int k, final float r, final float g, final float b, final float a, final boolean cartoon) {
        final float radsX = (float) Math.toRadians(rotVec.x);
        final float radsY = (float) Math.toRadians(rotVec.y);
        final float radsZ = (float) Math.toRadians(rotVec.z);
        for (int i = 0; i < FBP.CUBE.length; i += 4) {
            Vec3 v1 = FBP.CUBE[i];
            Vec3 v2 = FBP.CUBE[i + 1];
            Vec3 v3 = FBP.CUBE[i + 2];
            Vec3 v4 = FBP.CUBE[i + 3];
            v1 = rotatef_d(v1, radsX, radsY, radsZ);
            v2 = rotatef_d(v2, radsX, radsY, radsZ);
            v3 = rotatef_d(v3, radsX, radsY, radsZ);
            v4 = rotatef_d(v4, radsX, radsY, radsZ);
            final Vec3 normal = rotatef_d(FBP.CUBE_NORMALS[i / 4], radsX, radsY, radsZ);
            if (!cartoon) {
                addVt_WH(worldRendererIn, width, height, v1, par[0].x, par[0].y, j, k, r, g, b, a, normal);
                addVt_WH(worldRendererIn, width, height, v2, par[1].x, par[1].y, j, k, r, g, b, a, normal);
                addVt_WH(worldRendererIn, width, height, v3, par[2].x, par[2].y, j, k, r, g, b, a, normal);
                addVt_WH(worldRendererIn, width, height, v4, par[3].x, par[3].y, j, k, r, g, b, a, normal);
            } else {
                addVt_WH(worldRendererIn, width, height, v1, par[0].x, par[0].y, j, k, r, g, b, a, normal);
                addVt_WH(worldRendererIn, width, height, v2, par[0].x, par[0].y, j, k, r, g, b, a, normal);
                addVt_WH(worldRendererIn, width, height, v3, par[0].x, par[0].y, j, k, r, g, b, a, normal);
                addVt_WH(worldRendererIn, width, height, v4, par[0].x, par[0].y, j, k, r, g, b, a, normal);
            }
        }
    }

    static void addVt_S(final WorldRenderer worldRendererIn, final double scale, final Vec3 pos, final double u, final double v, final int j, final int k, final float r, final float g, final float b, final float a, final Vec3 n) {
        worldRendererIn.pos(pos.xCoord * scale, pos.yCoord * scale, pos.zCoord * scale).tex(u, v).color(r, g, b, a).lightmap(j, k).normal((float) n.xCoord, (float) n.yCoord, (float) n.zCoord).endVertex();
    }

    static void addVt_WH(final WorldRenderer worldRendererIn, final double width, final double height, final Vec3 pos, final double u, final double v, final int j, final int k, final float r, final float g, final float b, final float a, final Vec3 n) {
        worldRendererIn.pos(pos.xCoord * width, pos.yCoord * height, pos.zCoord * width).tex(u, v).color(r, g, b, a).lightmap(j, k).normal((float) n.xCoord, (float) n.yCoord, (float) n.zCoord).endVertex();
    }

    public static Vec3 rotatef_d(Vec3 vec, final float AngleX, final float AngleY, final float AngleZ) {
        final FBPVector3d sin = new FBPVector3d(MathHelper.sin(AngleX), MathHelper.sin(AngleY), MathHelper.sin(AngleZ));
        final FBPVector3d cos = new FBPVector3d(MathHelper.cos(AngleX), MathHelper.cos(AngleY), MathHelper.cos(AngleZ));
        vec = new Vec3(vec.xCoord, vec.yCoord * cos.x - vec.zCoord * sin.x, vec.yCoord * sin.x + vec.zCoord * cos.x);
        vec = new Vec3(vec.xCoord * cos.z - vec.yCoord * sin.z, vec.xCoord * sin.z + vec.yCoord * cos.z, vec.zCoord);
        vec = new Vec3(vec.xCoord * cos.y + vec.zCoord * sin.y, vec.yCoord, vec.xCoord * sin.y - vec.zCoord * cos.y);
        return vec;
    }
}
