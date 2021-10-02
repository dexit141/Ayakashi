package Ayakashi.helpers;

import java.awt.*;

public class ColorHelper {

    public static float lerp(float start, float stop, float amt) {
        return start + (stop - start) * amt;
    }

    public static int getColor(Color color) {
        return getColor(color.getRed(), color.getGreen(), color.getBlue());
    }

    public static int getColor(int r, int g, int b) {
        return (r & 255) << 16 | (g & 255) << 8 | b & 255;
    }

    public static int getRainbow(long delay) {
        double rainBowstate = Math.ceil((double) (System.currentTimeMillis() + delay) / 20.0D);
        return Color.getHSBColor(359.0F, 1F, 0.47F).getRGB();
    }

    public static int getRealStringLength(String str, int virtualStrLen) {
        for (int i = 0; i < virtualStrLen && i < str.length(); ++i) {
            char chr = str.charAt(i);
            if (chr == 167) {
                ++i;
                virtualStrLen += 2;
            }
        }

        if (virtualStrLen >= str.length()) {
            virtualStrLen = str.length();
        }

        return virtualStrLen;
    }
}
