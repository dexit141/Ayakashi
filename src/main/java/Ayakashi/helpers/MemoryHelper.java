package Ayakashi.helpers;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MemoryHelper {
    public void run() {
        Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(() -> {
            System.gc();
            System.runFinalization();
            //Ayakashi.checkHwid();
        }, 0L, 2L, TimeUnit.MINUTES);
    }
}
