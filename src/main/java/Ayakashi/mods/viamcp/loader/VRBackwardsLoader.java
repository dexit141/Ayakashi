package Ayakashi.mods.viamcp.loader;

import Ayakashi.mods.viamcp.ViaMCP;
import com.viaversion.viabackwards.api.ViaBackwardsPlatform;

import java.io.File;
import java.util.logging.Logger;

public class VRBackwardsLoader implements ViaBackwardsPlatform {
    private final File file;

    public VRBackwardsLoader(final File file) {
        this.init(this.file = new File(file, "ViaBackwards"));
    }

    @Override
    public Logger getLogger() {
        return ViaMCP.getInstance().getjLogger();
    }

    @Override
    public void disable() {
    }

    @Override
    public boolean isOutdated() {
        return false;
    }

    @Override
    public File getDataFolder() {
        return new File(this.file, "config.yml");
    }
}
