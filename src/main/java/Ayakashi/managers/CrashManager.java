package Ayakashi.managers;

import Ayakashi.methods.Crash;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CrashManager {
    private final List<Crash> exploits;

    public CrashManager(Crash... exploits) {
        this.exploits = Arrays.asList(exploits);
    }

    public Optional<Crash> getExploit(String name) {
        return this.exploits.stream().filter((exploit) -> exploit.getName().equalsIgnoreCase(name)).findFirst();
    }

    public List<Crash> getExploits() {
        return this.exploits;
    }
}
