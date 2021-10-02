package Ayakashi.methods;

import net.minecraft.client.Minecraft;
import org.apache.commons.lang3.Validate;

public abstract class Crash {
    protected static final Minecraft mc = Minecraft.getMinecraft();
    private final String name;

    public Crash() {
        CrashInfo crashInfo = this.getClass().getAnnotation(CrashInfo.class);
        Validate.notNull(crashInfo, "CONFUSED ANNOTATION EXCEPTION");
        this.name = crashInfo.name();
    }

    public abstract void execute(Object... var1);

    public String getName() {
        return this.name;
    }
}
