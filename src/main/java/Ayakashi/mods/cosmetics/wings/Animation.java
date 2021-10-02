package Ayakashi.mods.cosmetics.wings;

public class Animation {
    public long time = System.nanoTime() / 1000000L;

    public boolean hasTimeElapsed() {
        if (this.getTime() >= 10L) {
            this.reset();
            return true;
        } else {
            return false;
        }
    }

    public long getTime() {
        return System.nanoTime() / 1000000L - this.time;
    }

    public void reset() {
        this.time = System.nanoTime() / 1000000L;
    }
}
