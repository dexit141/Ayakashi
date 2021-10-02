package Ayakashi.managers;

public class AdvancedCrashManager {

    private String packetType;

    private final Integer packets;
    private final Integer delay;
    private final Integer amount;

    public AdvancedCrashManager(String _packetType, Integer _packets, Integer _delay, Integer _amount) {
        this.packetType = _packetType;
        this.packets = _packets;
        this.delay = _delay;
        this.amount = _amount;
    }

}