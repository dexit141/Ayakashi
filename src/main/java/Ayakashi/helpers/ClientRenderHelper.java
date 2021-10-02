package Ayakashi.helpers;

import net.minecraft.client.gui.Gui;
import org.lwjgl.opengl.GL11;

public enum ClientRenderHelper {
    INSTANCE;

    public static void drawVerticalLine(int x, int y, int height, int color) {
        Gui.drawRect(x, y, x + 1, height, color);
    }

    public static void drawHorizontalLine(int x, int y, int width, int color) {
        Gui.drawRect(x, y, width, y + 1, color);
    }

    public static void drawBorderedRect(int x, int y, int x1, int y1, int bord, int color) {
        Gui.drawRect(x + 1, y + 1, x1, y1, color);
        drawVerticalLine(x, y, y1, bord);
        drawVerticalLine(x1, y, y1, bord);
        drawHorizontalLine(x + 1, y, x1, bord);
        drawHorizontalLine(x, y1, x1 + 1, bord);
    }

    public void drawLine(int fromX, int fromY, int toX, int toY, int steps, int color) {
        double count = 0.0D;
        double distX = toX - fromX;
        double distY = toY - fromY;
        double dist = Math.sqrt(distX * distX + distY * distY);

        for (int i = 0; (double) i < dist; i += steps) {
            ++count;
        }

        int i2 = 0;
        float red = (float) (color >> 16 & 255) / 255.0F;
        float blue = (float) (color >> 8 & 255) / 255.0F;
        float green = (float) (color & 255) / 255.0F;

        for (float alpha = (float) (color >> 24 & 255) / 255.0F; (double) i2 < count; ++i2) {
            GL11.glColor4d(red, blue, green, alpha);
            double x = (double) fromX + (double) i2 * (distX / count);
            double y = (double) fromY + (double) i2 * (distY / count);
            double x1 = (double) fromX + (double) (i2 + 1) * (distX / count);
            double y1 = (double) fromY + (double) (i2 + 1) * (distY / count);
            GL11.glLineWidth(3.0F);
            GL11.glDisable(2884);
            GL11.glDisable(3553);
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 771);
            GL11.glBegin(1);
            GL11.glVertex2d(x, y);
            GL11.glVertex2d(x1, y1);
            GL11.glEnd();
            GL11.glEnable(3553);
        }

    }
}
