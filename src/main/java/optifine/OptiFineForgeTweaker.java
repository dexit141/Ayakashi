package optifine;

import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.LaunchClassLoader;

import java.io.File;
import java.util.List;

public class OptiFineForgeTweaker implements ITweaker {
    private static void dbg(String str) {
        System.out.println(str);
    }

    public void acceptOptions(List<String> args, File gameDir, File assetsDir, String profile) {
        dbg("OptiFineForgeTweaker: acceptOptions");
    }

    public void injectIntoClassLoader(LaunchClassLoader classLoader) {
        if (LaunchUtils.isForgeServer()) {
            dbg("OptiFineForgeTweaker: Forge server detected, skipping class transformer");
        } else {
            dbg("OptiFineForgeTweaker: injectIntoClassLoader");
            classLoader.registerTransformer("optifine.OptiFineClassTransformer");
        }
    }

    public String getLaunchTarget() {
        dbg("OptiFineForgeTweaker: getLaunchTarget");
        return "net.minecraft.client.main.Main";
    }

    public String[] getLaunchArguments() {
        dbg("OptiFineForgeTweaker: getLaunchArguments");
        return new String[0];
    }
}
