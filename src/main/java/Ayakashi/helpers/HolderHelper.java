package Ayakashi.helpers;

import java.util.ArrayList;
import java.util.List;

public class HolderHelper {
    private static final TimeHelper TIME_HELPER = new TimeHelper();
    private static final List<Long> TPS_TIMES = new ArrayList();
    private static long lastPacketMS = -1L;
    private static double TPS = -1.0D;

    public static TimeHelper getTimeHelper() {
        return TIME_HELPER;
    }

    public static List<Long> getTpsTimes() {
        return TPS_TIMES;
    }

    public static long getLastPacketMS() {
        return lastPacketMS;
    }

    public static void setLastPacketMS(long lastPacketMS) {
        HolderHelper.lastPacketMS = lastPacketMS;
    }

    public static double getTPS() {
        return TPS;
    }

    public static void setTPS(double TPS) {
        HolderHelper.TPS = TPS;
    }
}
